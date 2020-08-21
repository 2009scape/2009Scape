package plugin.skill.agility

import core.cache.def.impl.ItemDefinition
import core.cache.def.impl.NPCDefinition
import core.cache.def.impl.ObjectDefinition
import core.game.content.global.action.ClimbActionHandler
import core.game.content.global.action.DoorActionHandler
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.combat.ImpactHandler.HitsplatType
import core.game.node.entity.player.Player
import plugin.quest.miniquest.barcrawl.BarcrawlManager
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import core.plugin.InitializablePlugin
import core.plugin.PluginManager
import plugin.dialogue.DialoguePlugin

/**
 * Handles the barbarian outpost course.
 * @author Woah
 *
 */
@InitializablePlugin
class BarbarianOutpostCourse

/**
 * Constructs a new `BarbarianOutpostCourse` `Object`.
 * @param player the player.
 */
@JvmOverloads constructor(player: Player? = null) : AgilityCourse(player, 6, 46.2) {
    override fun createInstance(player: Player): AgilityCourse {
        return BarbarianOutpostCourse(player)
    }

    override fun handle(player: Player, node: Node, option: String): Boolean {
        val id = node.id
        getCourse(player)
        when (id) {
            2115, 2116 -> if (!player.barcrawlManager.isFinished || player.barcrawlManager.isStarted) {
                player.dialogueInterpreter.open(384)
            } else {
                DoorActionHandler.handleAutowalkDoor(player, node as GameObject)
            }
            2282 -> handleRopeSwing(player, node as GameObject)
            2294 -> handleLogBalance(player, node as GameObject)
            2302 -> handleLedgeBalance(player, node as GameObject)
            20211 -> {
                if (player.location.x < node.location.x) {
                    return true
                }
                AgilityHandler.climb(player, 2, ClimbActionHandler.CLIMB_UP, player.location.transform(-1, 0, 1), 8.0, "You climb the netting...")
            }
            1948 -> {
                if (player.location.x > node.location.x) {
                    player.packetDispatch.sendMessage("You cannot climb from this side.")
                }
                val flag = if (node.location == Location(2536, 3553, 0)) 4 else if (node.location == Location(2539, 3553, 0)) 5 else 6
                player.packetDispatch.sendMessage("You climb the low wall...")
                AgilityHandler.forceWalk(player, flag, node.location.transform(-1, 0, 0), node.location.transform(1, 0, 0), Animation.create(839), 10, 13.5, null)
            }
            455 -> player.barcrawlManager.read()
            385 -> {
                player.packetDispatch.sendMessage("The scorpion stings you!")
                player.impactHandler.manualHit(player, 3, HitsplatType.NORMAL)
            }
        }
        return true
    }

    /**
     * Handles the rope swing obstacle.
     * @param player the player.
     * @param object the object.
     */
    private fun handleRopeSwing(player: Player, `object`: GameObject) {
        if (player.location.y < 3554) {
            player.packetDispatch.sendMessage("You cannot do that from here.")
            return
        }
        if (ropeDelay > GameWorld.getTicks()) {
            player.packetDispatch.sendMessage("The rope is being used.")
            return
        }
        if (AgilityHandler.hasFailed(player, 1, 0.1)) {
            AgilityHandler.fail(player, 0, Location.create(2549, 9951, 0), null, getHitAmount(player), "You slip and fall to the pit bellow.")
            return
        }
        ropeDelay = GameWorld.getTicks() + 2
        player.packetDispatch.sendObjectAnimation(`object`, Animation.create(497), true)
        AgilityHandler.forceWalk(player, 0, player.location, Location.create(2551, 3549, 0), Animation.create(751), 50, 22.0, "You skillfully swing across.", 1)
    }

    /**
     * Handles the log balance obstacle.
     * @param player the player.
     * @param object the object.
     */
    private fun handleLogBalance(player: Player, `object`: GameObject) {
        val failed = AgilityHandler.hasFailed(player, 1, 0.5)
        val end = if (failed) Location.create(2545, 3546, 0) else Location.create(2541, 3546, 0)
        player.packetDispatch.sendMessage("You walk carefully across the slippery log...")
        AgilityHandler.walk(player, if (failed) -1 else 1, Location.create(2551, 3546, 0), end, Animation.create(155), if (failed) 0.0 else 13.5, if (failed) null else "...You make it safely to the other side.")
        if (failed) {
            AgilityHandler.walk(player, -1, player.location, Location.create(2545, 3546, 0), Animation.create(155), 0.0, null)
            GameWorld.Pulser.submit(getSwimPulse(player))
            return
        }
    }

    /**
     * Gets the pulse used to swim when failing the log balance.
     * @param player the player.
     * @return `Pulse` the pulse.
     */
    private fun getSwimPulse(player: Player): Pulse {
        return object : Pulse(1, player) {
            var counter = 0
            override fun pulse(): Boolean {
                when (++counter) {
                    6 -> {
                        player.animate(Animation.create(771))
                        player.packetDispatch.sendMessages("...You loose your footing and fall into the water.", "Something in the water bites you.")
                    }
                    7 -> {
                        player.graphics(Graphics.create(68))
                        player.properties.teleportLocation = Location.create(2545, 3545, 0)
                        player.impactHandler.manualHit(player, getHitAmount(player), HitsplatType.NORMAL)
                        AgilityHandler.walk(player, -1, Location.create(2545, 3545, 0), Location.create(2545, 3543, 0), Animation.create(152), 0.0, null)
                    }
                    11 -> {
                        player.properties.teleportLocation = Location.create(2545, 3542, 0)
                        return true
                    }
                }
                return false
            }
        }
    }

    /**
     * Handles the ledge balance obstacle.
     * @param player the player.
     * @param object the object.
     */
    private fun handleLedgeBalance(player: Player, `object`: GameObject) {
        val failed = AgilityHandler.hasFailed(player, 1, 0.3)
        val end = if (failed) Location.create(2534, 3547, 1) else Location.create(2532, 3547, 1)
        AgilityHandler.walk(player, if (failed) -1 else 3, Location.create(2536, 3547, 1), end, Animation.create(157), if (failed) 0.0 else 22.0, if (failed) null else "You skillfully edge across the gap.")
        player.packetDispatch.sendMessage("You put your foot on the ledge and try to edge across..")
        if (failed) {
            AgilityHandler.fail(player, 3, Location.create(2534, 3545, 0), Animation(760), getHitAmount(player), "You slip and fall to the pit bellow.")
            return
        }
    }

    override fun configure() {
        ObjectDefinition.forId(2115).configurations["option:open"] = this
        ObjectDefinition.forId(2116).configurations["option:open"] = this
        ObjectDefinition.forId(2282).configurations["option:swing-on"] = this
        ObjectDefinition.forId(2294).configurations["option:walk-across"] = this
        ObjectDefinition.forId(20211).configurations["option:climb-over"] = this
        ObjectDefinition.forId(2302).configurations["option:walk-across"] = this
        ObjectDefinition.forId(1948).configurations["option:climb-over"] = this
        ItemDefinition.forId(455).configurations["option:read"] = this
        NPCDefinition.forId(385).configurations["option:pick-up"] = this
        PluginManager.definePlugin(BarbarianGuardDialogue())
    }

    override fun getDestination(node: Node, n: Node): Location? {
        if (n is GameObject) {
            when (n.getId()) {
                2282 -> return Location.create(2551, 3554, 0)
            }
        }
        return null
    }

    /**
     * The barbarian guard dialogue plugin.
     * @author Woah
     */
    class BarbarianGuardDialogue : DialoguePlugin {
        /**
         * Constructs a new `BarbarianGuardDialogue` `Object`.
         */
        constructor() {
            /**
             * empty.
             */
        }

        /**
         * Constructs a new `BarbarianGuardDialogue` `Object`.
         * @param player the player.
         */
        constructor(player: Player?) : super(player) {}

        override fun newInstance(player: Player): DialoguePlugin {
            return BarbarianGuardDialogue(player)
        }

        override fun open(vararg args: Any): Boolean {
            if (!player.barcrawlManager.isStarted) {
                npc("O, waddya want?")
            } else if (player.barcrawlManager.isFinished && !player.barcrawlManager.isStarted) {
                npc("'Ello friend.")
                stage = 50
            } else {
                npc("So, how's the Barcrawl coming along?")
                stage = 20
            }
            return true
        }

        override fun handle(interfaceId: Int, buttonId: Int): Boolean {
            when (stage) {
                0 -> {
                    options("I want to come through this gate.", "I want some money.")
                    stage++
                }
                1 -> when (buttonId) {
                    1 -> {
                        player("I want to come throught his gate.")
                        stage = 5
                    }
                    2 -> {
                        player("I want some money.")
                        stage = 3
                    }
                }
                3 -> {
                    npc("Do I look like a bank to you?")
                    stage++
                }
                4 -> end()
                5 -> if (player.barcrawlManager.isFinished) {
                    npc("You may pass if you like. You are a true", "barbarian now.")
                    stage = 4
                } else {
                    npc("Barbarians only. Are you a barbarian? You don't look", "like one.")
                    stage++
                }
                6 -> {
                    options("Hmm, yep you've got me there.", "Looks can be deceiving, I am in fact a barbarian.")
                    stage++
                }
                7 -> when (buttonId) {
                    1 -> {
                        player("Hmm, yep you've got me there.")
                        stage = 4
                    }
                    2 -> {
                        player("Looks can be deceiving, I am in fact a barbarian.")
                        stage++
                    }
                }
                8 -> {
                    npc("If you're a barbarian you need to be able to drink like", "one. We barbarians like a good drink.")
                    stage++
                }
                9 -> {
                    npc("I have the perfect challenge for you... the Alfred", "Grimhand Barcrawl! First completed by Alfred", "Grimhand.")
                    stage++
                }
                10 -> {
                    player.barcrawlManager.reset()
                    player.barcrawlManager.isStarted = true
                    player.inventory.add(BarcrawlManager.BARCRAWL_CARD, player)
                    interpreter.sendDialogue("The guard hands you a Barcrawl card.")
                    stage++
                }
                11 -> {
                    npc("Take that card to each of the bards named on it. The", "bartenders will know what it means. We're kinda well", "known.")
                    stage++
                }
                12 -> {
                    npc("They'll give you their strongest drink and sign your", "card. When you've done all that, we'll be happy to let", "you in.")
                    stage++
                }
                13 -> end()
                20 -> if (!player.barcrawlManager.hasCard()) {
                    player("I've lost my barcrawl card...")
                    stage = 23
                } else if (player.barcrawlManager.isFinished) {
                    player("I tink I jusht 'bout done dem all... but I losht count...")
                    stage = 24
                } else {
                    player("I haven't finished it yet.")
                    stage++
                }
                21 -> {
                    npc("Well come back when you have, you lightweight.")
                    stage++
                }
                22 -> end()
                23 -> {
                    npc("What are you like? You're gonna have to start all over", "now.")
                    stage = 10
                }
                24 -> {
                    if (!player.inventory.containsItem(BarcrawlManager.BARCRAWL_CARD)) {
                        end()
                    }
                    player.barcrawlManager.isStarted = false
                    player.bank.remove(BarcrawlManager.BARCRAWL_CARD)
                    player.inventory.remove(BarcrawlManager.BARCRAWL_CARD)
                    interpreter.sendDialogue("You give the card to the barbarian.")
                    stage++
                }
                25 -> {
                    npc("Yep that seems fine, you can come in now. I never", "learned to read, but you look like you've drunk plenty.")
                    stage = 22
                }
                50 -> end()
            }
            return true
        }

        override fun getIds(): IntArray {
            return intArrayOf(384)
        }
    }

    companion object {
        /**
         * The rope delay.
         */
        private var ropeDelay = 0
    }
}