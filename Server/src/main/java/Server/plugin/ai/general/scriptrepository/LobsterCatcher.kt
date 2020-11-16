package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.map.path.Pathfinder
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import core.tools.ItemNames
import core.tools.RandomFunction
import plugin.ai.AIPlayer
import plugin.ge.GEOfferDispatch

import plugin.skill.Skills
import kotlin.random.Random

class LobsterCatcher : Script() {
    private val ANIMATION = Animation(714)
    val offers = HashMap<Int, Int>()
    val limit = 2000
    var myCounter = 0
    /**
     * Represents the graphics to use.
     */
    private val GRAPHICS = Graphics(308, 100, 50)
    internal enum class Sets(val equipment: List<Item>) {
        SET_1(listOf(Item(2643), Item(9470), Item(10756), Item(10394), Item(88), Item(9793))),
        SET_2(listOf(Item(2643), Item(6585), Item(10750), Item(10394), Item(88), Item(9793))),
        SET_3(listOf(Item(9472), Item(9470), Item(10750), Item(10394), Item(88), Item(9786))),
        SET_4(listOf(Item(2639), Item(6585), Item(10752), Item(10394), Item(88), Item(9786))),
        SET_5(listOf(Item(2639), Item(9470), Item(10750), Item(10394), Item(88), Item(9784))),
        SET_6(listOf(Item(2639), Item(6585), Item(10750), Item(10394), Item(88), Item(9784)));

    }


    private var state = State.FIND_SPOT
    private var tick = 0
    override fun tick() {
        when(state){


            State.BANKING -> {
                scriptAPI.bankItem(ItemNames.RAW_LOBSTER)
                state = if(bot.bank.getAmount(ItemNames.RAW_LOBSTER) > 100){
                    State.TELEPORT_GE
                } else {
                    State.FIND_SPOT
                }
            }


            State.FISHING -> {
                if (Random.nextBoolean()){
                    val spot = scriptAPI.getNearestNode(333, false)
                    spot!!.interaction.handle(bot, spot.interaction[0])
                    state = State.FIND_BANK
                } else {
                    state = State.IDLE
                }

            }

            State.IDLE -> {
                if (Random.nextBoolean()){
                    state = State.FIND_SPOT
                }
                else if(myCounter++ >= RandomFunction.random(1,25)){
                    state = State.FIND_SPOT
                }
            }

            State.FIND_SPOT -> {
                val spot = scriptAPI.getNearestNode(333, false)
                if (spot != null) {
                    bot.walkingQueue.reset()
                    state = State.FISHING
                } else {
                    //Change location to fishing guild
                    if (bot.location.x < 2837) {
                        Pathfinder.find(bot, Location.create(2837, 3435, 0)).walk(bot)
                    } else {
                        Pathfinder.find(bot, Location.create(2854, 3427, 0)).walk(bot)
                    }
                }
            }


            State.FIND_BANK -> {
                //Change location to fishing guild
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
                    //Change location to fishing guild
                    if (bot.location.x > 2837) {
                        Pathfinder.find(bot, Location.create(2837, 3435, 0)).walk(bot)
                    } else if (bot.location.x > 2821) {
                        Pathfinder.find(bot, Location.create(2821, 3435, 0)).walk(bot)
                    } else if (bot.location.x > 2809){
                        Pathfinder.find(bot,Location.create(2809, 3436, 0)).walk(bot)
                    }
                }
            }


            State.TELEPORT_GE -> {
                scriptAPI.teleportToGE()
                state = State.SELL_GE
            }


            State.SELL_GE -> {
                val botAmount = bot.bank.getAmount(377)
                var amount = 0
                GEOfferDispatch.offerMapping.values.filter { it.itemId == 377 && it.isSell}.map{amount += it.amount}
                if(amount + botAmount >= limit){
                        state = State.STOP
                    } else {
                        scriptAPI.sellOnGE(ItemNames.RAW_LOBSTER)
                        state = State.TELE_CATH
                    }

            }

            State.STOP -> {
                val botAmount = bot.bank.getAmount(377)
                var amount = 0
                GEOfferDispatch.offerMapping.values.filter { it.itemId == 377 && it.isSell}.map{amount += it.amount}
                if(amount + botAmount >= limit && myCounter++ >= 300){
                        bot.randomWalk(5,5)
                        State.STOP
                    } else {
                        State.TELE_CATH
                    }

            }

            //Change location to fishing guild
            State.TELE_CATH -> {
                if(tick++ == 10) {
                    bot.lock()
                    bot.visualize(ANIMATION, GRAPHICS)
                    bot.impactHandler.disabledTicks = 4
                    //Change location to fishing guild
                    val location = Location.create(2819, 3437, 0)
                    GameWorld.Pulser.submit(object : Pulse(4, bot) {
                        override fun pulse(): Boolean {
                            bot.unlock()
                            bot.properties.teleportLocation = location
                            bot.animator.reset()
                            state = State.FIND_SPOT
                            return true
                        }
                    })
                }
            }


        }
    }


    init {
        val setUp = RandomFunction.random(Sets.values().size)
        equipment.addAll(Sets.values()[setUp].equipment)
        inventory.add(Item(301))
        skills[Skills.FISHING] = 40
    }

    enum class State{
        FISHING,
        BANKING,
        FIND_BANK,
        FIND_SPOT,
        TELEPORT_GE,
        SELL_GE,
        TELE_CATH,
        STOP,
        IDLE
    }
    //Change location to fishing guild
    override fun newInstance(): Script {
        val script = LobsterCatcher()
        script.bot = AIPlayer(bot.startLocation)
        script.state = State.FIND_SPOT
        return script
    }
}