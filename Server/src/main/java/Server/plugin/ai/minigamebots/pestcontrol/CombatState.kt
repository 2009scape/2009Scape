package plugin.ai.minigamebots.pestcontrol

import core.game.node.Node
import core.game.node.entity.player.link.prayer.PrayerType
import core.game.world.map.Direction
import plugin.activity.pestcontrol.PestControlHelper
import java.util.*

class CombatState(private val bot: PestControlTestBot) {
    private val Random = Random()

    fun goToPortals() {
        bot.customState = "I'm at portals."
        val gate = bot.getClosestNodeWithEntry(75, PestControlHelper.GATE_ENTRIES)
        val portal = bot.getClosestNodeWithEntry(75, PestControlHelper.PORTAL_ENTRIES)
        if (bot.justStartedGame && Random().nextInt(2) == 0) {
            return
        }

        if (bot.justStartedGame || gate == null && portal == null) {
            bot.customState = "Walking randomly"
            bot.justStartedGame = false
            bot.randomWalkAroundPoint(PestControlHelper.getMyPestControlSession(bot).squire.location, 25)
            bot.movetimer = bot.random10 + 7
            return
        }

        if (gate != null) {
            bot.customState = "Interacting gate ID " + gate.id
            bot.interact(gate)
            bot.openedGate = true
            if (Random.nextInt(4) == 1 && bot.randomType < 50) {
                bot.movetimer = bot.random5 + 1
            }
            return
        }

        if (portal != null) {
            bot.customState = "Walking to portals"
            bot.randomWalkAroundPoint(portal.location, 30)
            bot.movetimer = bot.random10 + 5
        }
        //bot.customState = "Absolutely nothing. Everything is dead"
    }

    fun fightNPCs() {
        bot.customState = "Fight NPCs"
        //Npc Combat
        if (bot.tick == 0) {
            if (!bot.inCombat()) bot.AttackNpcsInRadius(30)
            bot.tick = 10
        } else bot.tick--
        bot.eat(379)

        if (!bot.prayer.active.contains(PrayerType.PROTECT_FROM_MELEE))
            bot.prayer.toggle(PrayerType.PROTECT_FROM_MELEE)
            bot.prayer.toggle(PrayerType.PIETY)

        if (!bot.inCombat()) {
            if (bot.combatMoveTimer <= 0) {
                if (bot.FindTargets(bot, 30) == null) bot.randomWalk(5, 5)
                bot.combatMoveTimer = 5
            }
        }
    }

    fun goToEastPortals() {
        bot.customState = "Go to east portals"
        val easternGate = bot.getClosestNodeWithEntryAndDirection(75, 14233, Direction.SOUTH)
        val easternPortal: Node? = PestControlHelper.getMyPestControlSession(bot).portals[1]
        if (easternGate != null) {
            bot.interact(easternGate)
        } else if (easternPortal != null) {
            bot.walkToPosSmart(easternPortal.location)
        } else {
            bot.customState = "Everything is null!"
        }
    }
}