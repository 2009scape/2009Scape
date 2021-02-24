package plugin.ai.general.scriptrepository.runecrafting

import core.cache.def.impl.ItemDefinition
import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.interaction.inter.FairyRing
import core.game.node.`object`.GameObject
import core.game.node.entity.player.link.RunScript
import core.game.node.entity.skill.Skills
import core.game.node.entity.skill.crafting.armour.HardCraftPulse
import core.game.world.map.Location
import core.tools.Items
import core.tools.stringtools.colorize
import plugin.ai.general.GeneralBotCreator
import plugin.ai.general.ScriptAPI
import plugin.ai.general.scriptrepository.*

@PlayerCompatible
@ScriptName("Simple Rune Crafting (No pouches)")
@ScriptDescription("Start anywhere between altar and bank",
    "with pure essence in bank or inv + tiara on head/talisman on hand.",
    "nats req dramen staff and use zanaris, laws req no weps/armor",
    "Otherwise this algo is pretty lenient with player starting conditions")
@ScriptIdentifier("runes")
class SimpleRunecrafting() : Script() {
    var state = State.SETUP
    var overlay: ScriptAPI.BottingOverlay?= null
    lateinit var dat : RCData
    var craftedAmt = 0
    var talismanChild : GameObject? = null
    var interactChild : GameObject? = null
    var sentFailedMessage = false
    var returning = false
    var waitLeft = 0
    var cachedTeleportLocation = Location.create(0, 0, 0)
    var pathfindingState = 0
    val FAIRY_RETURN_LOC = Location.create(2412, 4434, 0)

    enum class State {
        CRAFTING,
        IN_ALTAR,
        TO_ALTAR,
        OUT_ALTAR,
        TO_BANK,
        TO_BANK1,
        BANKING1,
        BANKING2,
        ENTER_ALTAR,
        FAILED,
        LEAVING_ALTAR,
        FAIRY_RING_WAITING,
        INIT,
        SETUP_WAIT,
        SETUP
    }

    enum class SpecialZones(val zoneID: Int) {
        CROSS_WILDY(-10),
        FAIRY_RING(-9),
        BOARD_SHIP(-8)
    }

    enum class Rings {
        CKR,
        DKR
    }

    val teleportLines = listOf(
        "%PReticulating splines...",
        "%PThreatening a minor god in exchange for teleportation...",
        "%PAligning stars...",
        "%PFinding an instance in the teleporter room...",
        "%PChunks loading... generating new area...",
        "%P16 // 16 // 16 // Atlas error, warping to last discovered planet...",
        "%PActivating dream nail...",
        "%PMorphing gateways to warpgates...",
        "%PActivating warp prism for teleportation mode...",
        "%PPhysics engine settling...",
        "%PAiming cannon towards destination...",
        "%PFinding a free landing area for vessel...",
        "%PDelivering batteries for rocket launch...",
        "%PWavedashing to destination area...",
        "%PCornerboosting over impassable terrain...",
        "%PBackwards long jumping to a parallel universe...",
        "%PTurning on the portal gun...",
        "%PActivating recall ability...",
        "%PRocket jumping to destination...",
        "%PScanning for multiplayer instances in desired location...",
        "%PAligning quantum bits to quantum tunnel to destination...",
        "%PLaunching cosmic ray at server to overwrite your position...",
        "%PActivating ultra rare teleportation artifact...",
        "%PSpending hidden gems on single use teleport ability...",
        "%PAstrally projecting to destination...",
        "%PRotating world to reach destination through the 4th dimension...",
        "%PEnabling non-euclidean movement mods to reach target...",
        "%PRotating fairy ring dials... [TODO: make less boring sounding]",
        "%PBegging for a teleport from the underworld...",
        "%PConstructing bridge to destination...",
        "%PZooming through the body of a nydus worm...",
        "%PActivating magic mirror with /sethome to teleport to arbitrary point...",
        "%PSprinting across nether highway...",
        "%PHitching a ride on a gryphon...",
        "%PTricking a player into casting teleother on you...",
        "%PBunny hopping across impassable terrain...",
        "%PActivating magical leyline teleport network...",
        "%PRiding a rook on an infinite chess board...",
        "%PHolding on tight to yellow bloons...",
        "%PDoing a sick dance to impress the fairies so they'll warp you...",
        "%PEnabling Pip-Boy 3000 fast travel functionality...",
        "%PLying that your character is stuck so you are teleported to nearest safe region...",
        "%PEnabling VPN in target destination, so it's like you are there...",
        "%PStraight up driving to destination... like in a regular car...",
        "%PDriving to destination... like in a futuristic solar powered paper plane...",
        "%PPassing out so you can skip the waiting to reach the target destination...",
        "%PSelecting destination from a bonfire menu...",
        "%PUsing your last genie wish on warping somewhere...",
        "%PPossessing an object at your destination and switching it to look like you...",
        "%PSending an army to your destination to clear the path for you...",
        "%PSending your trusty crow to the destination and using your hammer to teleport to it...",
        "%PCasting a 5th level teleport spell...",
        "%PUsing RNG manipulation to cast misty step from the wild magic table...",
        "%PLetting your familiar carry you to the destination...",
        "%PShipping yourself with drone delivery...",
        "%PFlying there... with wings you didn't know you had...",
        "%Pmov player acc... mov acc destination...",
        "%PDigging until you maybe reach the destination...",
        "%PPolitely asking fungi about the nature of the divine...",
        "%PGetting a ferry ride...",
        "%PSwitching server shards...",
        "%PRunning extremely fast in a LEAF suit...",
        "%PActivating an ancient alien portal...",
        "%PReverse engineering portal coordinates system to warp to ideal destination...",
        "%PSurfing electrical lines to destination...",
        "%PJumping every frame to preserve momentum..."
    )

    // val ring: FairyRing? = FairyRing.valueOf(code.toUpperCase())

    private fun getNearestZone() : Int {
        var dist = 999999.0
        var closestZone = 0
        for ((i, z) in dat.ALTAR_ZONES.withIndex()) {
            if (z.southWestX != SPECIAL_ZONE) {
                val zdist = bot.location.getDistance(z.randomLoc)
                if (zdist < dist) {
                    dist = zdist
                    closestZone = i
                }
            }
        }
        return closestZone
    }

    private fun parseSpecialZone() : Boolean {
        if (dat.ALTAR_ZONES[pathfindingState].southWestY == SpecialZones.CROSS_WILDY.zoneID) {
            val ditch = scriptAPI.getNearestNode("Wilderness Ditch",true)
            if (ditch == null) {
                state = State.FAILED
                return false
            }
            ditch.interaction.handle(bot, ditch.interaction[0])
            return true
        } else if (dat.ALTAR_ZONES[pathfindingState].southWestY == SpecialZones.FAIRY_RING.zoneID) {
            if (!bot.equipment.containsAtLeastOneItem(772)) {
                bot.sendMessage(colorize("%RTeleporting requires a dramen staff and all appropriate quests completed."))
                state = State.FAILED
                return false
            }

            if (!returning) {
                waitLeft = dat.ALTAR_ZONES[pathfindingState].northEastX
                cachedTeleportLocation = FairyRing.valueOf(Rings.values()[dat.ALTAR_ZONES[pathfindingState].northEastY].name).tile
                bot.sendMessage(colorize(teleportLines.random()))
                state = State.FAIRY_RING_WAITING
            } else {
                scriptAPI.teleport(FAIRY_RETURN_LOC)
            }
            return true
        } else if (dat.ALTAR_ZONES[pathfindingState].southWestY == SpecialZones.BOARD_SHIP.zoneID) {
            waitLeft = dat.ALTAR_ZONES[pathfindingState].northEastX
            if (!returning && !ItemDefinition.canEnterEntrana(bot)) {
                bot.sendMessage(colorize("%RUnable to warp to destination... illegal items detected."))
                state = State.FAILED
                return false
            }
            bot.sendMessage(colorize(teleportLines.random()))
            cachedTeleportLocation = if (returning) {
                dat.ALTAR_ZONES[pathfindingState - 1].randomLoc
            } else {
                dat.ALTAR_ZONES[pathfindingState + 1].randomLoc
            }
            state = State.FAIRY_RING_WAITING
            return true
        }

        state = State.FAILED
        return false
    }

    override fun tick() {
        when (state) {

            State.SETUP -> {
                var craftType = 0
                if (arguments.size > 0) {
                    if (arguments[0].contains("help")) {
                        bot.sendMessage(colorize("%RUsage: %B::script rune_s [rune_type]"))
                        bot.sendMessage("Valid types: air - 0, water - 1, earth - 2, fire - 3, mind - 4, body - 5")
                        bot.sendMessage("cosmic - 6, chaos - 7, nature - 8, law - 9")
                        bot.sendMessage("You can use either the number or rune type. eg:")
                        bot.sendMessage(colorize("%B::script rune_s 0"))
                        bot.sendMessage(colorize("%B::script rune_s air"))
                        sentFailedMessage = true
                        state = State.FAILED
                        return
                    }

                    state = State.FAILED
                    for (option in RCData.values()) {
                        if (arguments[0].toLowerCase().contains(option.SIMPLE_NAME) || arguments[0].toIntOrNull() == option.ordinal) {
                            dat = option
                            state = State.INIT
                            bot.sendMessage(colorize("%PStarting runecrafting for " + option.TASK_TITLE))
                        }
                    }
                } else {
                    bot.setAttribute("runscript", object : RunScript() {
                        override fun handle(): Boolean {
                            var v = value as String
                            state = State.FAILED
                            for (option in RCData.values()) {
                                if (v.toLowerCase().contains(option.SIMPLE_NAME) || v.toIntOrNull() == option.ordinal) {
                                    dat = option
                                    state = State.INIT
                                    bot.sendMessage(colorize("%PStarting runecrafting for " + option.TASK_TITLE))
                                }
                            }
                            return true
                        }
                    })
                    bot.dialogueInterpreter.sendInput(true,"Enter rune type. Options: air, water, earth, fire, mind, body, cosmic, chaos, nature, law. Or the associated rune number from 0-9")
                    state = State.SETUP_WAIT
                }
            }

            State.INIT -> {
                sentFailedMessage = false
                if (dat.ALTAR.rune.level > bot.skills.getLevel(Skills.RUNECRAFTING)) {
                    state = State.FAILED
                    bot.sendMessage(colorize("%RERROR, RC Level insufficient to craft runes"))
                    return
                }

                overlay = scriptAPI.getOverlay()
                overlay!!.init()
                overlay!!.setTitle(dat.TASK_TITLE)
                overlay!!.setTaskLabel("Runes Crafted: ")
                overlay!!.setAmount(0)
                pathfindingState = getNearestZone()
                state = if (dat.REG_ESSENCE_ALLOWED) {
                    if (bot.inventory.containsAtLeastOneItem(Items.RUNE_ESSENCE_1436) ||
                        bot.inventory.containsAtLeastOneItem(Items.PURE_ESSENCE_7936)
                    ) {
                        State.TO_ALTAR
                    } else {
                        State.TO_BANK
                    }
                } else {
                    if (bot.inventory.containsAtLeastOneItem(Items.PURE_ESSENCE_7936)) {
                        State.TO_ALTAR
                    } else {
                        State.TO_BANK
                    }
                }
            }

            State.FAIRY_RING_WAITING -> {
                bot.locks.lockMovement(2)
                waitLeft--
                if (waitLeft == 0) {
                    bot.teleport(cachedTeleportLocation)
                } else if (waitLeft < 0) {
                    bot.locks.unlockMovement()
                    state = if (returning) {
                        State.TO_BANK1
                    } else {
                        State.TO_ALTAR
                    }
                }
            }

            State.TO_BANK -> {
                if (bot.settings.runEnergy > 10 && !bot.settings.isRunToggled) {
                    bot.settings.toggleRun()
                }
                if (!dat.BANK_ZONE.insideBorder(bot)) {
                    scriptAPI.walkTo(dat.BANK_ZONE.randomLoc)
                } else {
                    var bank = scriptAPI.getNearestNode("Bank Booth", true)
                    if (bank == null) {
                        bank = scriptAPI.getNearestNode("Banker")
                    } else {
                        bot.pulseManager.run(object : MovementPulse(bot, bank, DestinationFlag.OBJECT) {
                            override fun pulse(): Boolean {
                                bot.faceLocation(bank.location)
                                state = State.BANKING1
                                return true
                            }
                        })
                    }
                    bank ?: return
                    bot.faceLocation(bank.location)
                    state = State.BANKING1
                }
            }

            State.TO_BANK1 -> {
                if (dat.ALTAR_ZONES[pathfindingState].southWestX == Companion.SPECIAL_ZONE) {
                    if (parseSpecialZone()) {
                        pathfindingState--
                    }
                    return
                }
                if (!dat.ALTAR_ZONES[pathfindingState].insideBorder(bot)) {
                    scriptAPI.walkTo(dat.ALTAR_ZONES[pathfindingState].randomLoc)
                } else if (pathfindingState > 0) {
                    pathfindingState--
                } else {
                    state = State.TO_BANK
                }
            }

            State.BANKING1 -> {
                scriptAPI.bankItem(dat.ALTAR.rune.rune.id)
                state = State.BANKING2
            }

            State.BANKING2 -> {
                if (dat.REG_ESSENCE_ALLOWED) {
                    scriptAPI.withdraw(Items.RUNE_ESSENCE_1436, 28)
                }
                if (!bot.inventory.isFull) {
                    scriptAPI.withdraw(Items.PURE_ESSENCE_7936, 28)
                }
                pathfindingState = 0
                returning = false
                state = State.TO_ALTAR
            }

            State.TO_ALTAR -> {
                if (bot.settings.runEnergy > 10 && !bot.settings.isRunToggled) {
                    bot.settings.toggleRun()
                }
                if ((dat.REG_ESSENCE_ALLOWED && !bot.inventory.containsAtLeastOneItem(Items.RUNE_ESSENCE_1436) &&
                            !bot.inventory.containsAtLeastOneItem(Items.PURE_ESSENCE_7936)) ||
                    (!dat.REG_ESSENCE_ALLOWED && !bot.inventory.containsAtLeastOneItem(Items.PURE_ESSENCE_7936))) {
                    state = State.FAILED
                }
                if (dat.ALTAR_ZONES[pathfindingState].southWestX == Companion.SPECIAL_ZONE) {
                    if (parseSpecialZone()) {
                        pathfindingState++
                    }
                    return
                }

                if (!dat.ALTAR_ZONES[pathfindingState].insideBorder(bot)) {
                    scriptAPI.walkTo(dat.ALTAR_ZONES[pathfindingState].randomLoc)
                } else if (pathfindingState < (dat.ALTAR_ZONES.size - 1)) {
                    pathfindingState++
                } else {
                    val altar = scriptAPI.getNearestNode(dat.ALTAR.ruin.`object`[0], true)
                    if (altar == null) {
                        bot.sendMessage("ERROR_ altar IS NULL")
                    } else {
                        interactChild = altar.asObject().childs[1]
                    }
                    altar ?: return
                    bot.pulseManager.run(object: MovementPulse(bot,altar, DestinationFlag.OBJECT){
                        override fun pulse(): Boolean {
                            bot.faceLocation(altar.location)
                            state = State.ENTER_ALTAR
                            return true
                        }
                    })
                }
            }
            State.ENTER_ALTAR -> {
                if (bot.inventory.containsAtLeastOneItem(dat.ALTAR.ruin.talisman.talisman.id)) {
                    var e = NodeUsageEvent(bot, 0,
                        dat.ALTAR.ruin.talisman.talisman,
                        interactChild)
                    UseWithHandler.run(e)
                    state = State.IN_ALTAR
                } else if (bot.equipment.containsAtLeastOneItem(dat.ALTAR.ruin.tiara.tiara.id)) {
                    interactChild!!.interaction.handle(bot, interactChild!!.interaction[0])
                    state = State.IN_ALTAR
                } else {
                    state = State.FAILED
                }
            }

            State.IN_ALTAR -> {
                // Waiting for teleport
                if (!dat.INSIDE_ZONE.insideBorder(bot)) {
                    return
                }

                var runes = bot.inventory.getAmount(Items.PURE_ESSENCE_7936)
                if (dat.REG_ESSENCE_ALLOWED) {
                    runes += bot.inventory.getAmount(Items.RUNE_ESSENCE_1436)
                }
                if (runes > 0) {
                    val altar = scriptAPI.getNearestNode(dat.ALTAR.`object`, true)
                    altar!!.interaction.handle(bot, altar.interaction[0])
                } else {
                    state = State.OUT_ALTAR
                }
            }

            State.OUT_ALTAR -> {
                val portal = scriptAPI.getNearestNode(dat.ALTAR.portal, true)
                bot.pulseManager.run(object: MovementPulse(bot,portal, DestinationFlag.OBJECT){
                    override fun pulse(): Boolean {
                        bot.faceLocation(portal!!.location)
                        craftedAmt += bot.inventory.getAmount(dat.ALTAR.rune.rune.id)
                        overlay!!.setAmount(craftedAmt)
                        state = State.LEAVING_ALTAR
                        return true
                    }
                })
            }

            State.LEAVING_ALTAR -> {
                returning = true
                if (!dat.INSIDE_ZONE.insideBorder(bot)) {
                    state = State.TO_BANK1
                } else {
                    val portal = scriptAPI.getNearestNode(dat.ALTAR.portal, true)
                    portal!!.interaction.handle(bot, portal.interaction[0])
                }
            }

            State.FAILED -> {
                if (!sentFailedMessage) {
                    sentFailedMessage = true
                    bot.sendMessage(colorize("%RRunecrafting failed."))
                    bot.sendMessage(colorize("%REither you lack the levels, items, tried crafting invalid runes, or something broke."))
                    val pulse: GeneralBotCreator.BotScriptPulse? = bot.getAttribute("botting:script", null)
                    pulse?.stop()
                    bot.interfaceManager.closeOverlay()
                }
            }
        }
    }


    override fun newInstance(): Script {
        return this
    }

    companion object {
        const val SPECIAL_ZONE = -42069
    }
}