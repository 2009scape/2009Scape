package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.world.map.Location
import core.tools.Items
import core.tools.RandomFunction
import plugin.ai.AIPlayer
import plugin.ge.OfferManager
import plugin.skill.Skills
import kotlin.random.Random

/*
Grabs all amounts in GE
val offers = HashMap<Int, Int>()
for (offer in GEOfferDispatch.offerMapping.values) {
    val item = offer.itemId
    val amount = offer.amount - offer.completedAmount
    if (offers[item] == null) { offers[item] = amount }
    else { offers[item] = offers[item]!!.plus(amount) }

    if (offers[383]!!.plus(amount) >= 1000) {
*/
/*
Grabs just one offer
for (offer in GEOfferDispatch.offerMapping.values) {
if (offer.itemId == 383 && offer.amount >= 1000) {
*/
/**
 * A bot script for fishing sharks in the Fishing Guild - Fishes, Banks, Sells.
 * @param mycounter used in the bots random idling function.
 * @param pause is currently not used.
 * @param limit is the number of Raw Sharks in the GE the bots will sleep at.
 * @author Sir Kermit
 * Training Wheel Manufacturer @Ceikry
 * Very slight ge modifications by @Angle
 */
class SharkCatcher : Script() {
    //val shark = Items.RAW_SHARK
    val pause = (1000..3000)
    val limit = 5000
    var myCounter = 0
    //val fishzone = ZoneBorders(2597, 3410, 2612, 3426)
    internal enum class Sets(val equipment: List<Item>) {
        SET_1(listOf(Item(10721), Item(8283), Item(10412), Item(10414), Item(88), Item(1007))),
        SET_2(listOf(Item(10721), Item(8283), Item(10412), Item(10414), Item(88), Item(1007))),
        SET_3(listOf(Item(10721), Item(8283), Item(10412), Item(10414), Item(88), Item(1019))),
        SET_4(listOf(Item(10721), Item(8283), Item(10412), Item(10414), Item(88), Item(1019))),
        SET_5(listOf(Item(10721), Item(8283), Item(10412), Item(10414), Item(88), Item(3765))),
        SET_6(listOf(Item(10721), Item(8283), Item(10412), Item(10414), Item(88), Item(3765)));

    }
    private var state = State.FIND_SPOT
    private var tick = 0

    override fun tick() {
        val fishguild = Location.create(2596, 3410, 0)
        if (tick++ >= 500){
            scriptAPI.teleport(fishguild)
            state = State.FIND_SPOT
            return
        }
        when(state){


            State.BANKING -> {
                scriptAPI.bankItem(Items.RAW_SHARK_383)
                state = if(bot.bank.getAmount(Items.RAW_SHARK_383) > 1000){
                    State.TELEPORT_GE//.also {println("There are: $total_amount sharks in the GE.") }
                } else {
                    State.FIND_SPOT
                }
            }

            State.STOP -> {
                val botAmount = bot.bank.getAmount(Items.RAW_SHARK_383) + 1
                var geAmount = OfferManager.getQuantitySoldForItem(Items.RAW_SHARK_383)
                val totalAmount = (geAmount + botAmount) + 1
                    if((totalAmount > limit) && myCounter++ == 300){
                        bot.randomWalk(5,5)
                        myCounter = 0
                        return
                    } else if (myCounter++ == 300){
                        myCounter = 0
                        State.TELE_FISH
                    }
                return

            }

            State.IDLE -> {
                if (Random.nextBoolean()){
                    state = State.FIND_SPOT
                }
                else if(myCounter++ >= RandomFunction.random(1,25)){
                        myCounter = 0
                        state = State.FIND_SPOT
                    }
                }

            State.FISHING -> {
                if (Random.nextBoolean()) {
                    tick = 0
                    val spot = scriptAPI.getNearestNode(334, false)
                    spot!!.interaction.handle(bot, spot.interaction[2])
                    state = State.FIND_BANK
                }
                else{
                    state = State.IDLE
                }
            }

            State.FIND_SPOT -> {
                val spot = scriptAPI.getNearestNode(334, false)
                if (spot != null) {
                    bot.walkingQueue.reset()
                    state = State.FISHING
                } else {
                    if(bot.location.x < 2591) {
                        scriptAPI.walkTo(Location.create(2604, 3421, 0))
                    } else {
                        scriptAPI.walkTo(Location.create(2608, 3414, 0))
                    }
                    }
            }

            State.FIND_BANK -> {
                val bank = scriptAPI.getNearestGameObject(bot.location, 2213)
                class BankingPulse : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                    override fun pulse(): Boolean {
                        bot.faceLocation(bank!!.location)
                        state = State.BANKING
                        return true
                    }
                }
                if(bank != null){
                    bot.pulseManager.run(BankingPulse())
                } else {
                    if (bot.location.x != 2596) {
                        scriptAPI.walkTo(Location.create(2596, 3415, 0))
                    } else if (bot.location.x < 2591) {
                        scriptAPI.walkTo(Location.create(2591, 3415, 0))
                    } else if (bot.location.x < 2587){
                        scriptAPI.walkTo(Location.create(2587, 3420, 0))
                    }
                }
            }

            State.TELEPORT_GE -> {
                scriptAPI.teleportToGE()
                state = State.SELL_GE
            }

            State.SELL_GE -> {
                val botAmount = bot.bank.getAmount(Items.RAW_SHARK_383) + 1
                var geAmount = OfferManager.getQuantitySoldForItem(Items.RAW_SHARK_383)
                val totalAmount = (geAmount + botAmount) + 1
                if(totalAmount > limit){
                    scriptAPI.walkTo(Location.create(3164, 3487, 0))
                    scriptAPI.sellOnGE(Items.RAW_SHARK_383)
                    state = State.STOP//.also { println("STOPPING") }
                } else {
                    scriptAPI.walkTo(Location.create(3164, 3487, 0))
                    scriptAPI.sellOnGE(Items.RAW_SHARK_383)
                    state = State.TELE_FISH
                }
            }

            State.TELE_FISH -> {
                scriptAPI.teleport(fishguild)
                state = State.FIND_SPOT
            }


        }
    }


    init {
        val setUp = RandomFunction.random(Sets.values().size)
        equipment.addAll(Sets.values()[setUp].equipment)
        inventory.add(Item(311))
        skills[Skills.FISHING] = 90
    }

    enum class State{
        FISHING,
        BANKING,
        FIND_BANK,
        FIND_SPOT,
        TELEPORT_GE,
        SELL_GE,
        TELE_FISH,
        IDLE,
        STOP
    }
    override fun newInstance(): Script {
        val script = SharkCatcher()
        script.bot = AIPlayer(bot.startLocation)
        script.state = State.FIND_SPOT
        return script
    }
}