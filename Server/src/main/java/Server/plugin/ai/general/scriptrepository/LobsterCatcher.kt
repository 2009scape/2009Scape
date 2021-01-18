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
import core.tools.Items
import core.tools.RandomFunction
import plugin.ai.AIPlayer

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

    private var bots = 0
    private var lobstopper = false

    private var state = State.FIND_SPOT
    private var tick = 0
    override fun tick() {
        when(state){


            State.BANKING -> {
                scriptAPI.bankItem(Items.RAW_LOBSTER_377)
                state = if(bot.bank.getAmount(Items.RAW_LOBSTER_377) > 100){
                    State.TELEPORT_GE
                } else {
                    State.IDLE
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
                    if (bot.location.x < 2837) {
                        Pathfinder.find(bot, Location.create(2837, 3435, 0)).walk(bot)
                    } else {
                        Pathfinder.find(bot, Location.create(2854, 3427, 0)).walk(bot)
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
                scriptAPI.sellOnGE(Items.RAW_LOBSTER_377)
                state = State.TELE_CATH
            }

            State.TELE_CATH -> {
                if(tick++ == 10) {
                    bot.lock()
                    bot.visualize(ANIMATION, GRAPHICS)
                    bot.impactHandler.disabledTicks = 4
                    val location = Location.create(2819, 3437, 0)
                    GameWorld.Pulser.submit(object : Pulse(4, bot) {
                        override fun pulse(): Boolean {
                            bot.unlock()
                            bot.properties.teleportLocation = location
                            bot.animator.reset()
                            state = State.IDLE
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
        IDLE
    }

    override fun newInstance(): Script {
        if (!lobstopper && bots <= 0) {
            val script = LobsterCatcher()
            script.bot = AIPlayer(bot.startLocation)
            script.state = State.FIND_SPOT
            bots = 1
            return script
        }else if (tick++ == 6000 && lobstopper) {
            tick = 0
            lobstopper = false
        }
        return newInstance()
    }
}