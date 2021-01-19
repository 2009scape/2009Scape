package plugin.ge

import core.cache.def.impl.ItemDefinition
import core.game.component.CloseEvent
import core.game.component.Component
import core.game.container.Container
import core.game.container.ContainerEvent
import core.game.container.ContainerListener
import core.game.container.access.InterfaceContainer
import core.game.node.entity.player.Player
import core.game.node.entity.player.info.login.SavingModule
import core.game.node.entity.player.link.audio.Audio
import core.game.node.item.Item
import core.game.system.SystemLogger.log
import core.game.system.monitor.PlayerMonitor
import core.net.packet.PacketRepository
import core.net.packet.context.ConfigContext
import core.net.packet.context.ContainerContext
import core.net.packet.context.GrandExchangeContext
import core.net.packet.out.Config
import core.net.packet.out.ContainerPacket
import core.net.packet.out.GrandExchangePacket
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import plugin.ge.OfferManager.Companion.dispatch
import plugin.ge.OfferManager.Companion.updateOffer
import java.lang.StringBuilder
import java.nio.ByteBuffer
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import core.game.component.InterfaceType
import core.game.container.access.BitregisterAssembler

import plugin.activity.barrows.BarrowsActivityPlugin.sendConfiguration
import plugin.activity.barrows.BarrowsActivityPlugin.sendConfiguration










/**
 * Handles the grand exchange interfaces for the player.
 *
 * @author Emperor
 * @author Angle
 */

class PlayerGrandExchange(private val player: Player) : SavingModule {

    var history = arrayOfNulls<GrandExchangeOffer>(5)

    public val offers = arrayOfNulls<GrandExchangeOffer>(6)

    private var openedIndex = -1

    public var temporaryOffer: GrandExchangeOffer? = null

    /**
     * Opens the Grand Exchange menu.
     *
     * @NOTICE: The amount of GE boxes is limited at LoginWriteEvent.java (2 or 6 GE boxes, set it 0 for 2, 1 for 6)
     */
    fun open() {
        if (player.ironmanManager.checkRestriction()) {
            return
        }
        if (!player.bankPinManager.isUnlocked) {
            player.bankPinManager.openType(4)
            return
        }
        player.interfaceManager.open(Component(105)).closeEvent =
            CloseEvent { player, _ ->
                temporaryOffer = null
                player.packetDispatch.sendRunScript(571, "")
                player.interfaceManager.closeChatbox()
                player.interfaceManager.closeSingleTab()
                true
            }
        player.packetDispatch.sendInterfaceConfig(105, 193, true)
        player.packetDispatch.sendAccessMask(6, 211, 105, -1, -1)
        player.packetDispatch.sendAccessMask(6, 209, 105, -1, -1)
        toMainInterface()
    }

    /**
     * Opens the collection box.
     */
    fun openCollectionBox() {
        if (!player.bankPinManager.isUnlocked) {
            player.bankPinManager.openType(3)
            return
        }
        player.interfaceManager.openComponent(109)
        player.packetDispatch.sendAccessMask(6, 18, 109, 0, 2)
        player.packetDispatch.sendAccessMask(6, 23, 109, 0, 2)
        player.packetDispatch.sendAccessMask(6, 28, 109, 0, 2)
        player.packetDispatch.sendAccessMask(6, 36, 109, 0, 2)
        player.packetDispatch.sendAccessMask(6, 44, 109, 0, 2)
        player.packetDispatch.sendAccessMask(6, 52, 109, 0, 2)
        for (offer in offers) {
            if (offer != null) {
                PacketRepository.send(
                    ContainerPacket::class.java,
                    ContainerContext(player, -1, -1757, 523 + offer.index, offer.withdraw, false)
                )
            }
        }
    }

    /**
     * Opens the history log.
     *
     * @param p The player to open it for.
     */
    fun openHistoryLog(p: Player) {
        p.interfaceManager.open(Component(643))
        for (i in history.indices) {
            val o: GrandExchangeOffer? = history[i]
            if (o == null) {
                for (j in 0..3) {
                    p.packetDispatch.sendString("-", 643, 25 + i + j * 5)
                }
                continue
            }
            p.packetDispatch.sendString(if (o.sell) "You sold" else "You bought", 643, 25 + i)
            p.packetDispatch.sendString(
                NumberFormat.getNumberInstance(Locale.US).format(o.completedAmount.toLong()),
                643,
                30 + i
            )
            p.packetDispatch.sendString(ItemDefinition.forId(o.itemID).name, 643, 35 + i)
            p.packetDispatch.sendString(
                NumberFormat.getNumberInstance(Locale.US).format(o.totalCoinExchange.toLong()) + " gp", 643, 40 + i
            )
        }
    }

    /**
     * Opens the item sets interface.
     */
    fun openItemSets() {
        player.inventory.listeners.add(object : ContainerListener {
            override fun update(c: Container, event: ContainerEvent) {
                player.setAttribute(
                    "container-key",
                    InterfaceContainer.generateItems(
                        player,
                        player.inventory.toArray(),
                        arrayOf("Examine", "Exchange", "Components"),
                        644,
                        0,
                        7,
                        4
                    )
                )
                InterfaceContainer.generateItems(
                    player,
                    GEItemSet.getItemArray(),
                    arrayOf("Examine", "Exchange", "Components"),
                    645,
                    16,
                    15,
                    10
                )
            }

            override fun refresh(c: Container) {
                player.setAttribute(
                    "container-key",
                    InterfaceContainer.generateItems(
                        player,
                        player.inventory.toArray(),
                        arrayOf("Examine", "Exchange", "Components"),
                        644,
                        0,
                        7,
                        4
                    )
                )
                InterfaceContainer.generateItems(
                    player,
                    GEItemSet.getItemArray(),
                    arrayOf("Examine", "Exchange", "Components"),
                    645,
                    16,
                    15,
                    10
                )
            }
        })
        player.interfaceManager.open(Component(645)).closeEvent =
            CloseEvent { player, _ ->
                player.inventory.listeners.removeAt(1)
                player.interfaceManager.closeSingleTab()
                player.removeAttribute("container-key")
                true
            }
        player.interfaceManager.openSingleTab(Component(644)).open(player)
        player.setAttribute(
            "container-key",
            InterfaceContainer.generateItems(
                player,
                player.inventory.toArray(),
                arrayOf("Examine", "Exchange", "Components"),
                644,
                0,
                7,
                4
            )
        )
        InterfaceContainer.generateItems(
            player,
            GEItemSet.getItemArray(),
            arrayOf("Examine", "Exchange", "Components"),
            645,
            16,
            15,
            10
        )
    }


    /**
     * Returns to the main interface.
     */
    fun toMainInterface() {
        PacketRepository.send(Config::class.java, ConfigContext(player, 1112, -1))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1112, -1))
        player.interfaceManager.closeChatbox()
        player.interfaceManager.closeSingleTab()
        openedIndex = -1
    }


    override fun save(buffer: ByteBuffer?) {
        TODO("Not yet implemented. NOT Ever implemented. Saving a bytebuffer is dumb")
    }

    fun parse(geData: JSONObject) {
        val offersRaw = geData["offers"]

        if (offersRaw != null) {
            val offersJSON = offersRaw as JSONArray
            val history = geData["history"] as JSONArray
            for (i in offersJSON.indices) {
                val offer = offersJSON[i] as JSONObject
                val index = offer["offerIndex"].toString().toInt()
                if (index > offersJSON.size) {
                    log("Grand Exchange: INVALID OFFER INDEX FOR " + player.name + " INDEX: " + index + ", SKIPPING!")
                    log("IF YOU SEE THIS MESSAGE, THE GRAND EXCHANGE NEEDS TO BE FIXED.")
                    println("Check your logs, AVENGING ANGLE might have fucked up HARD and now " + player.name + "'s trade with index " + index + "is gone :(")
                    continue
                }
                OfferManager.setIndex(offer["offerUID"].toString().toInt().toLong(), index)
            }
            for (i in history.indices) {
                val offer = history[i] as JSONObject
                var o = GrandExchangeOffer()
                o.itemID = offer["itemId"].toString().toInt()
                o.sell = offer["isSell"] as Boolean
                o.totalCoinExchange = (offer["totalCoinExchange"].toString().toInt())
                o.completedAmount = (offer["completedAmount"].toString().toInt())
                history[i] = o
            }
        }
    }

    override fun parse(buffer: ByteBuffer) {
        var index = -1
        while (buffer.get().also { index = it.toInt() }.toInt() != -1) {
            val key = buffer.long
            OfferManager.setIndex(key, index)
        }
        for (i in history.indices) {
            val s = buffer.get().toInt()
            if (s == -1) {
                continue
            }
            var o = GrandExchangeOffer()
            o.itemID = buffer.short.toInt()
            o.sell = true
            o.totalCoinExchange = buffer.int
            o.completedAmount = buffer.int
            history[i] = o
        }
    }

    /**
     * Initializes the grand exchange.
     */
    fun init() {
        // Were trades made while gone?
        var updated = false
        for (offer in offers) {
            if (offer != null) {
                offer.player = player
                if (!updated && (offer.withdraw[0] != null || offer.withdraw[1] != null)) {
                    updated = true
                }
            }
        }
        update()
        if (updated) {
            player.packetDispatch.sendMessage("You have items from the Grand Exchange waiting in your collection box.")
        }
    }

    /**
     * Updates the client with the grand exchange data.
     */
    fun update() {
        for (offer in offers) {
            update(offer)
        }
    }

    /**
     * Updates a grand exchange offer.
     *
     * @param offer The offer to update.
     */
    fun update(offer: GrandExchangeOffer?) {
        if (offer != null) {
            PacketRepository.send(
                GrandExchangePacket::class.java,
                GrandExchangeContext(player, offer.index.toByte(), offer.offerState.ordinal.toByte(), offer.itemID.toShort(),
                    offer.sell, offer.offeredValue, offer.amount, offer.completedAmount, offer.totalCoinExchange)
            )
        }
    }

    /**
     * Constructs a new buy offer.
     *
     * @param itemId The item id.
     */
    fun constructBuy(itemId: Int) {
        if (openedIndex < 0) {
            return
        }
        temporaryOffer = GrandExchangeOffer()
        temporaryOffer!!.itemID = itemId
        temporaryOffer!!.sell = false
        var itemDb = GrandExchangeDatabase.getDatabase()[itemId]
        if (itemDb == null) {
            player.packetDispatch.sendMessage("This item has been blacklisted from the Grand Exchange.")
            return
        }
        temporaryOffer!!.player = player
        temporaryOffer!!.amount = 1
        temporaryOffer!!.offeredValue = itemDb.value
        temporaryOffer!!.index = openedIndex
        sendConfiguration(temporaryOffer, false)
    }

    // GrandExchangeDatabase.getDatabase()[offer?.itemID]

    /**
     * Constructs a new sale offer.
     *
     * @param item The item to sell.
     */
    fun constructSale(item: Item) {
        if (openedIndex < 0 || offers[openedIndex] != null) {
            return
        }
        if (item.id == 995) {
            player.packetDispatch.sendMessage("You can't offer money!")
            return
        }
        var id = item.id
        if (!item.definition.isUnnoted) {
            id = item.noteChange
        }
        var itemDb = GrandExchangeDatabase.getDatabase()[id]
        if (itemDb == null) {
            player.packetDispatch.sendMessage("This item can't be sold on the Grand Exchange.")
            return
        }
        temporaryOffer = GrandExchangeOffer()
        temporaryOffer!!.itemID = id
        temporaryOffer!!.sell = true
        temporaryOffer!!.player = player
        temporaryOffer!!.offeredValue = itemDb.value
        temporaryOffer!!.amount = item.amount
        temporaryOffer!!.index = openedIndex
        sendConfiguration(temporaryOffer, true)
    }

    /**
     * Gets the total amount of this item in the inventory (including noted
     * version).
     *
     * @param player The player.
     * @param itemId the item id.
     * @return The amount of items + notes in the inventory.
     */
    fun getInventoryAmount(player: Player, itemId: Int): Int {
        val item = Item(itemId)
        var amount = player.inventory.getAmount(item)
        if (item.definition.noteId > -1) {
            amount += player.inventory.getAmount(Item(item.definition.noteId))
        }
        return amount
    }

    /**
     * Confirms the current offer.
     */
    fun confirmOffer() {
        if (openedIndex < 0 || temporaryOffer == null || temporaryOffer!!.amount == 0) {
            return
        }
        if (temporaryOffer!!.offeredValue < 1) {
            player.audioManager.send(Audio(4039, 1, 1))
            player.packetDispatch.sendMessage("You can't make an offer for 0 coins.")
            return
        }
        if (temporaryOffer!!.amount > Int.MAX_VALUE / temporaryOffer!!.offeredValue) {
            player.audioManager.send(Audio(4039, 1, 1))
            player.packetDispatch.sendMessage("You can't " + (if (temporaryOffer!!.sell) "sell " else "buy ") + " this much!")
            return
        }
        temporaryOffer!!.index = openedIndex
        if (temporaryOffer!!.sell) {
            val maxAmount = getInventoryAmount(player, temporaryOffer!!.itemID)
            if (temporaryOffer!!.amount > maxAmount) {
                player.audioManager.send(Audio(4039, 1, 1))
                player.packetDispatch.sendMessage("You do not have enough of this item in your inventory to cover the")
                player.packetDispatch.sendMessage("offer.")
                return
            }
            var item: Item
            val amountLeft: Int =
                temporaryOffer!!.amount - player.inventory.getAmount(Item(temporaryOffer!!.itemID))
            val remove = player.inventory.remove(Item(temporaryOffer!!.itemID, temporaryOffer!!.amount).also {
                item = it
            })
            var note: Int
            if (amountLeft > 0) {
                if (item.noteChange.also { note = it } > 0) {
                    player.inventory.remove(Item(note, amountLeft))
                } else if (remove) {
                    player.inventory.add(Item(temporaryOffer!!.itemID, temporaryOffer!!.amount - amountLeft))
                    return
                }
            }
            if (dispatch(player, temporaryOffer!!)) {
                offers[openedIndex] = temporaryOffer
                updateOffer(temporaryOffer!!)
            }
        } else {
            val total: Int = temporaryOffer!!.amount * temporaryOffer!!.offeredValue
            if (total > player.inventory.getAmount(Item(995))) {
                player.audioManager.send(Audio(4039, 1, 1))
                player.packetDispatch.sendMessage("You do not have enough coins to cover the offer.")
                return
            }
            if (dispatch(player, temporaryOffer!!) && player.inventory.remove(Item(995, total))) {
                offers[openedIndex] = temporaryOffer
                updateOffer(temporaryOffer!!)
            }
        }
        player.monitor.log(
            (if (temporaryOffer!!.sell) "selling" else "buying") + " offer => item => " + ItemDefinition.forId(
                temporaryOffer!!.itemID
            ).name + " => amount => " + temporaryOffer!!.amount + " => price => " + temporaryOffer!!.offeredValue,
            PlayerMonitor.GRAND_EXCHANGE_LOG
        )
        toMainInterface()
        player.audioManager.send(Audio(4043, 1, 1))
        temporaryOffer = null
    }

    /**
     * Aborts an offer.
     *
     * @param index The offer index.
     */
    fun abort(index: Int) {
        val offer: GrandExchangeOffer? = offers[index]
        player.packetDispatch.sendMessage("Abort request acknowledged. Please be aware that your offer may")
        player.packetDispatch.sendMessage("have already been completed.")
        if (offer == null || !offer.isActive) {
            return
        }
        offer.offerState = OfferState.ABORTED
        if (offer.sell) {
            OfferManager.addWithdraw(offer, offer.itemID, offer.amountLeft, true)
        } else {
            OfferManager.addWithdraw(offer, 995, offer.amountLeft * offer.offeredValue, true)
        }
        update(offer)
        player.monitor.log(
            "aborted offer => item => " + ItemDefinition.forId(offer.itemID).name + " => amount => " + offer.amount + "",
            PlayerMonitor.GRAND_EXCHANGE_LOG
        )
        OfferManager.dumpDatabase = true
    }

    /**
     * Removes an offer.
     *
     * @param index The offer index.
     */
    fun remove(index: Int): Boolean {
        if (offers[index] == null) {
            return false
        }
        var offer: GrandExchangeOffer = offers[index]!!
        if (offer.completedAmount > 0) {
            val newHistory = arrayOfNulls<GrandExchangeOffer>(5)
            newHistory[0] = offer
            System.arraycopy(history, 0, newHistory, 1, 4)
            history = newHistory
            player.monitor.log(
                "offer removed => item => " + ItemDefinition.forId(offer.itemID).name + " =>  amount => " + offer.amount + " => amount_left => " + offer.amountLeft + " => completed_amount => " + offer.completedAmount + "",
                PlayerMonitor.GRAND_EXCHANGE_LOG
            )
        }
        offer.withdraw = arrayOfNulls(2)
        var didExist = OfferManager.removeEntry(offer)
        offer.uid = 0
        offer.offerState = OfferState.REMOVED
        offers[index] = null
        update(offer)
        toMainInterface()
        return didExist
    }

    fun hasActiveOffer(): Boolean {
        for (i in offers) {
            if (i != null)
                return true
        }
        return false
    }


    /**
     * Sends the configuration packets for the offer.
     *
     * @param offer The grand exchange offer.
     * @param sell  If it's a selling offer.
     */
    fun sendConfiguration(offer: GrandExchangeOffer?, sell: Boolean) {
        var entry: GrandExchangeEntry? = GrandExchangeDatabase.getDatabase()[offer?.itemID]
        var examine: String? = ""
        val formatter = DecimalFormat("###,###,###,###")
        val text = StringBuilder()
        var lowestOfferValue = 0
        var totalAmounts = 0
        val foundOffers: MutableList<Int> = ArrayList()
        val foundAmounts: MutableList<Int> = ArrayList()
        if (offer != null) {
            examine = ItemDefinition.forId(offer.itemID).examine
            var count = 0
            if (!offer.sell) {
                for (o in OfferManager.getOffersForItem(offer.itemID)) {
                    if (o != null) if (o.sell && o.isActive) {
                        foundOffers.add(o.offeredValue)
                        foundAmounts.add(o.amountLeft)
                        count++
                    }
                }
                val botSales = OfferManager.amtBotsSelling(offer.itemID)
                if (botSales > 0) {
                    foundAmounts.add(botSales)
                    foundOffers.add(BotPrices.getPrice(offer.itemID))
                    count++
                }
                if (foundOffers.isNotEmpty()) {
                    lowestOfferValue = Collections.min(foundOffers)
                    totalAmounts = foundAmounts.stream().mapToInt { obj: Int -> obj }.sum()
                }
                if (count == 0) text.append("<col=8A0808>There are currently no sell offers for this item.")
                else text.append("Lowest price: <col=FFFFFF>")
                     .append(formatter.format(lowestOfferValue.toLong())).append(" gp")
                     .append("</col><br>Quantity: <col=FFFFFF>")
                     .append(formatter.format(totalAmounts.toLong())).append("</col><br>Users: <col=FFFFFF>")
                     .append(formatter.format(count.toLong()))
            }
        }
        player.packetDispatch.sendString(if (offer != null && !offer.sell) text.toString() else examine, 105, 142)
        PacketRepository.send(Config::class.java, ConfigContext(player, 1114, entry?.value ?: 0))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1115, (if (entry == null) 0 else entry.value * 0.95).toInt()))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1116, (if (entry == null) 0 else entry.value * 1.05).toInt()))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1112, openedIndex))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1113, if (sell) 1 else 0))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1109, offer?.itemID ?: -1))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1110, offer?.amount ?: 0))
        PacketRepository.send(Config::class.java, ConfigContext(player, 1111, offer?.offeredValue ?: 0))
        if (offer != null) {
            PacketRepository.send(
                ContainerPacket::class.java,
                ContainerContext(player, -1, -1757, 523 + offer.index, offer.withdraw, false)
            )
        }
    }

    /**
     * Opens the search interface.
     */
    fun openSearch() {
        val c = Component(389)
        c.definition.type = InterfaceType.CS_CHATBOX
        c.closeEvent = CloseEvent { player, c ->
            player.packetDispatch.sendRunScript(571, "")
            true
        }
        player.packetDispatch.sendRunScript(570, "s", "Grand Exchange Item Search")
        player.interfaceManager.openChatbox(c)
    }

    /**
     * Opens the buying screen.
     *
     * @param index The offer index.
     */
    fun openBuy(index: Int) {
        openedIndex = index
        sendConfiguration(offers[index], false)
        openSearch()
    }

    /**
     * Opens the selling screen.
     */
    fun openSell(index: Int) {
        openedIndex = index
        sendConfiguration(offers[index], true)
        player.interfaceManager.openSingleTab(Component(107)).open(player)
        player.packetDispatch.sendRunScript(
            149, "IviiiIsssss", "", "", "", "Examine", "Offer",
            -1, 0, 7, 4, 93, 7012370
        )
        BitregisterAssembler.send(player, 107, 18, 0, 27, BitregisterAssembler(0, 1))
    }

    /**
     * Withdraws an item.
     *
     * @param offer The offer to withdraw from.
     * @param index The item index.
     */
    fun withdraw(offer: GrandExchangeOffer, index: Int) {
        val item = offer.withdraw[index] ?: return
        if (player.inventory.getMaximumAdd(item) < item.amount) {
            val note = item.noteChange
            if (note == -1 || player.inventory.getMaximumAdd(Item(note)) < item.amount) {
                player.audioManager.send(Audio(4039, 1, 1))
                player.packetDispatch.sendMessage("You do not have enough room in your inventory.")
                return
            }
            player.inventory.add(Item(note, item.amount))
        } else {
            player.inventory.add(item)
        }
        offer.withdraw[index] = null
        if (!offer.isActive && offer.withdraw[0] == null && offer.withdraw[1] == null) {
            remove(offer.index)
        }
        player.audioManager.send(Audio(4040, 1, 1))
        PacketRepository.send(
            ContainerPacket::class.java,
            ContainerContext(player, -1, -1757, 523 + offer.index, offer.withdraw, false)
        )
        OfferManager.dumpDatabase = true
    }

    /**
     * Gets the currently opened offer.
     *
     * @return The grand exchange offer currently opened.
     */
    fun getOpenedOffer(): GrandExchangeOffer? {
        return if (openedIndex < 0) {
            null
        } else offers[openedIndex]
    }

    /**
     * Views a registered offer.
     *
     * @param index The index.
     */
    fun view(index: Int) {
        if (offers[index] == null) {
            return
        }
        openedIndex = index
        sendConfiguration(offers[index], false)
    }

    /**
     * Formats the grand exchange.
     *
     * @return the formatted offer for the SQL database.
     */
    fun format(): String? {
        var log = ""
        for (offer in offers) {
            if (offer != null) {
                log += offer.itemID.toString() + "," + offer.amount + "," + offer.sell + "|"
            }
        }
        if (log.isNotEmpty() && log[log.length - 1] == '|') {
            log = log.substring(0, log.length - 1)
        }
        return log
    }


}