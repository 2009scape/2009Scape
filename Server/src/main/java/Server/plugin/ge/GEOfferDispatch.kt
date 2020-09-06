package plugin.ge

import core.cache.def.impl.ItemDefinition
import core.game.content.eco.EcoStatus
import core.game.content.eco.EconomyManagement
import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.game.node.entity.player.link.audio.Audio
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.callback.CallBack
import core.game.world.repository.Repository
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import plugin.ai.AIRepository.Companion.addOffer
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.*
import javax.script.ScriptEngineManager

/**
 * Handles the Grand Exchange offers.
 * @author Ceikry
 * @author Emperor
 */
class GEOfferDispatch : Pulse(), CallBack {
    override fun call(): Boolean {
        init()
        delay = 1
        GameWorld.Pulser.submit(this)
        return true
    }

    override fun pulse(): Boolean {
        if (GameWorld.getTicks() % 24000 == 0) {
            for (offer in OFFER_MAPPING.values) {
                if (offer.isActive && offer.isLimitation) {
                    updateOffer(offer)
                }
            }
            BuyingLimitation.clear()
        }
        return false
    }

    companion object {
        /**
         * The update notification.
         */
        const val UPDATE_NOTIFICATION = "One or more of your grand exchange offers have been updated."

        /**
         * The database path.
         */
        private val DB_PATH = "data" + File.separator + "eco" + File.separator + "offer_dispatch.json"

        /**
         * The offset of the offer UIDs.
         */
        private var offsetUID: Long = 1

        /**
         * The mapping of all current offers.
         */
        private val OFFER_MAPPING: MutableMap<Long, GrandExchangeOffer> = HashMap()

        /**
         * If the database should be dumped.
         */
        private var dumpDatabase = false

        /**
         * Initializes the Grand Exchange.
         */
        fun init() {
            val file = File(DB_PATH)
            if (!file.exists()) {
                return
            }
            val parser = JSONParser()
            val reader: FileReader? = FileReader(DB_PATH)
            val saveFile = parser.parse(reader) as JSONObject

            offsetUID = saveFile["offsetUID"].toString().toLong()

            if(saveFile.containsKey("offers")) {
                val offers = saveFile["offers"] as JSONArray
                for(offer in offers){
                    val o = offer as JSONObject
                    val itemId = o["itemId"].toString().toInt()
                    val sale = o["sale"] as Boolean
                    val no = GrandExchangeOffer(itemId, sale)
                    no.offeredValue = o["offeredValue"].toString().toInt()
                    no.amount = o["amount"].toString().toInt()
                    no.timeStamp = o["timeStamp"].toString().toLong()
                    no.uid = o["uid"].toString().toLong()
                    no.completedAmount = o["completedAmount"].toString().toInt()
                    no.playerUID = o["playerUID"].toString().toInt()
                    no.state = OfferState.values()[o["offerState"].toString().toInt()]
                    no.totalCoinExchange = o["totalCoinExchange"].toString().toInt()
                    val withdrawData = o["withdrawItems"] as JSONArray
                    var index = 0
                    for(data in withdrawData){
                        val item = data as JSONObject
                        val it = Item(item["id"].toString().toInt(), item["amount"].toString().toInt())
                        no.withdraw[index] = it
                        index++
                    }
                    OFFER_MAPPING.put(no.uid, no)
                }
            }
        }

        @JvmStatic
        fun save(){
            val root = JSONObject()
            val offers = JSONArray()

            for(entry in OFFER_MAPPING){
                val offer = entry.value
                if (offer.state == OfferState.REMOVED) {
                    continue
                }
                if (!offer.isSell && offer.playerUID == PlayerDetails.getDetails("2009scape").uid) {
                    continue
                }
                val o = JSONObject()
                o.put("uid", entry.key.toString())
                o.put("itemId", offer.itemId.toString())
                o.put("sale", offer.isSell)
                o.put("amount", offer.amount.toString())
                o.put("completedAmount", offer.completedAmount.toString())
                o.put("offeredValue", offer.offeredValue.toString())
                o.put("timeStamp", offer.timeStamp.toString())
                o.put("offerState", offer.state.ordinal.toString())
                o.put("totalCoinExchange", offer.totalCoinExchange.toString())
                o.put("playerUID", offer.playerUID.toString())
                val withdrawItems = JSONArray()
                for(item in offer.withdraw){
                    item ?: continue
                    val it = JSONObject()
                    it.put("id", item.id.toString())
                    it.put("amount", item.amount.toString())
                    withdrawItems.add(it)
                }
                o.put("withdrawItems", withdrawItems)
                offers.add(o)
            }
            root.put("offsetUID", offsetUID.toString())
            root.put("offers", offers)

            val manager = ScriptEngineManager()
            val scriptEngine = manager.getEngineByName("JavaScript")
            scriptEngine.put("jsonString", root.toJSONString())
            scriptEngine.eval("result = JSON.stringify(JSON.parse(jsonString), null, 2)")
            val prettyPrintedJson = scriptEngine["result"] as String

            try {
                FileWriter(DB_PATH).use { file ->
                    file.write(prettyPrintedJson)
                    file.flush()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        /**
         * Dumps the grand exchange offers.
         * @param directory The directory to save to.
         */
		@JvmStatic
		fun dump() {
            save()
        }

        /**
         * Dispatches an offer.
         * @param player The player.
         * @param offer The grand exchange offer.
         * @return `True` if successful.
         */
		@JvmStatic
		fun dispatch(player: Player, offer: GrandExchangeOffer): Boolean {
            if (offer.amount < 1) {
                player.packetDispatch.sendMessage("You must choose the quantity you wish to buy!")
                println("amountthing")
                return false
            }
            if (offer.offeredValue < 1) {
                player.packetDispatch.sendMessage("You must choose the price you wish to buy for!")
                println("pricethng")
                return false
            }
            if (offer.state != OfferState.PENDING || offer.uid != 0L) {
                println("pendingthing")
                return false
            }
            if (player.isArtificial) {
                offer.playerUID = PlayerDetails.getDetails("2009scape").uid
                addOffer(player, offer)
            } else {
                offer.playerUID = player.details.uid
            }
            offer.uid = nextUID()
            offer.state = OfferState.REGISTERED
            OFFER_MAPPING[offer.uid] = offer
            offer.timeStamp = System.currentTimeMillis()
            player.grandExchange.update(offer)
            if (offer.isSell) {
                Repository.sendNews(player.username + " just offered " + offer.amount + " " + ItemDefinition.forId(offer.itemId).name.toLowerCase() + " on the GE.")
            } else {
                updateOffer(offer)
            }
            dumpDatabase = true
            return true
        }

        /**
         * Updates the offer.
         * @param offer The G.E. offer to update.
         */
		@JvmStatic
		fun updateOffer(offer: GrandExchangeOffer) {
            if (!offer.isActive) {
                return
            }
            for (o in OFFER_MAPPING.values) {
                if (o.isSell != offer.isSell && o.itemId == offer.itemId && o.isActive) {
                    exchange(offer, o)
                    if (offer.state == OfferState.COMPLETED) {
                        break
                    }
                }
            }
        }

        /**
         * Exchanges between 2 offers.
         * @param offer The grand exchange offer to update.
         * @param o The other offer to exchange with.
         */
        private fun exchange(offer: GrandExchangeOffer, o: GrandExchangeOffer) {
            if (o.isSell == offer.isSell) {
                return
            }
            if (offer.isSell && o.offeredValue < offer.offeredValue || !offer.isSell && o.offeredValue > offer.offeredValue) {
                return
            }
            var amount = offer.getAmountLeft(true)
            if (amount > o.getAmountLeft(true)) {
                amount = o.getAmountLeft(true)
            }
            if (amount < 1) {
                return
            }
            var coinDifference = if (offer.isSell) o.offeredValue - offer.offeredValue else offer.offeredValue - o.offeredValue
            if (coinDifference < 0) {
                return
            }
            if (EconomyManagement.getEcoState() == EcoStatus.DRAINING) {
                coinDifference *= (1.0 - EconomyManagement.getModificationRate()).toInt()
            }
            offer.completedAmount = offer.completedAmount + amount
            o.completedAmount = o.completedAmount + amount
            offer.state = if (offer.amountLeft < 1) OfferState.COMPLETED else OfferState.UPDATED
            o.state = if (o.amountLeft < 1) OfferState.COMPLETED else OfferState.UPDATED
            if (offer.isSell) {
                if (offer.amountLeft < 1 && offer.player != null) {
                    offer.player.audioManager.send(Audio(4042, 1, 1))
                }
                offer.addWithdraw(995, amount * offer.offeredValue)
                o.addWithdraw(o.itemId, amount)
                BuyingLimitation.updateBoughtAmount(o.itemId, o.playerUID, amount)
            } else {
                if (o.amountLeft < 1 && o.player != null) {
                    o.player.audioManager.send(Audio(4042, 1, 1))
                }
                offer.addWithdraw(offer.itemId, amount)
                o.addWithdraw(995, amount * o.offeredValue)
                BuyingLimitation.updateBoughtAmount(offer.itemId, offer.playerUID, amount)
            }
            if (coinDifference > 0) {
                addCoinDifference(offer, o, coinDifference, amount)
            }
            offer.entry.influenceValue(offer.offeredValue)
            offer.notify(UPDATE_NOTIFICATION)
            o.notify(UPDATE_NOTIFICATION)
            dumpDatabase = true
            if (o.playerUID == BotGrandExchange.MAGIC_UID && o.state == OfferState.COMPLETED) {
                remove(o.uid);
            }
            if (offer.playerUID == BotGrandExchange.MAGIC_UID && offer.state == OfferState.COMPLETED) {
                remove(offer.uid);
            }
        }

        /**
         * Adds the coin difference between 2 offers.
         * @param offer The offer.
         * @param o The other offer.
         * @param coinDifference The difference in prices.
         */
        private fun addCoinDifference(offer: GrandExchangeOffer, o: GrandExchangeOffer, coinDifference: Int, amount: Int) {
            if (offer.isSell) {
                o.addWithdraw(995, coinDifference * amount)
            } else {
                offer.addWithdraw(995, coinDifference * amount)
            }
        }

        /**
         * Gets the offer for the given UID.
         * @param uid The unique ID given to the offer.
         * @return The grand exchange offer.
         */
		@JvmStatic
		fun forUID(uid: Long): GrandExchangeOffer? {
            return OFFER_MAPPING[uid]
        }

        /**
         * Removes the offer for the given UID.
         * @param uid The UID.
         * @return `True` if successfully removed.
         */
		@JvmStatic
		fun remove(uid: Long): Boolean {
            return OFFER_MAPPING.remove(uid) != null
        }

        /**
         * Gets the next UID.
         * @return The UID.
         */
        fun nextUID(): Long {
            val id = offsetUID++
            return if (id == 0L) {
                nextUID()
            } else id
        }

        /**
         * Sets the offer mapping
         */
        @JvmStatic
        fun setOfferMap(offer: GrandExchangeOffer) {
            OFFER_MAPPING[offer.uid] = offer
        }

        /**
         * Gets the current UID without incrementing for use in BotGrandExchange
         * @return The UID
         */
        @JvmStatic
        val lastItemUID: Long
            get() = this.offsetUID

        /**
         * Gets the offerMapping.
         * @return the offerMapping
         */
		@JvmStatic
		val offerMapping: Map<Long, GrandExchangeOffer>
            get() = OFFER_MAPPING

        /**
         * Sets the dumping flag.
         * @param dump The dump to set.
         */
		@JvmStatic
		fun setDumpDatabase(dump: Boolean) {
            dumpDatabase = dump
        }
    }
}