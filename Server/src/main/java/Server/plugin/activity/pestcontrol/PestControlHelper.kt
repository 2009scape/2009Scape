package plugin.activity.pestcontrol

import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders
import java.util.*

object PestControlHelper {
    var GATE_ENTRIES = Arrays.asList(14233, 14235)
    var PORTAL_ENTRIES = Arrays.asList(*PCPortalNPC.portalIds)
    val PestControlLanderIntermediate = Location.create(2644, 2646, 0)
    val PestControlLanderNovice = Location.create(2657, 2642, 0)
    val PestControlIslandLocation = Location.create(2659, 2676, 0)
    val PestControlIslandLocation2 = Location.create(2659, 2676, 0)
    // bank val PestControlIslandLocation2 = Location.create(2667, 2653, 0)
    fun isInPestControlInstance(p: Player): Boolean {
        return p.getAttribute<Any?>("pc_zeal") != null
    }

    enum class BoatInfo(var boatBorder: ZoneBorders, var outsideBoatBorder: ZoneBorders, var ladderId: Int) {
        NOVICE(ZoneBorders(2660, 2638, 2663, 2643), ZoneBorders(2658, 2635, 2656, 2646), 14315),
        INTERMEDIATE(ZoneBorders(2638, 2642, 2641, 2647), ZoneBorders(2645, 2639, 2643, 2652), 25631),
        VETERAN(ZoneBorders(2632, 2649, 2635, 2654), ZoneBorders(2638, 2652, 2638, 2655), 25632);
    }

    fun landerContainsLoc(l: Location?): Boolean {
        for (i in BoatInfo.values()) if (i.boatBorder.insideBorder(l)) return true
        return false
    }

    fun outsideGangplankContainsLoc(l: Location?): Boolean {
        for (i in BoatInfo.values()) if (i.outsideBoatBorder.insideBorder(l)) return true
        return false
    }

    fun landerContainsLoc2(l: Location?): Boolean {
        for (n in BoatInfo.values()) if (n.boatBorder.insideBorder(l)) return true
        return false
    }

    fun outsideGangplankContainsLoc2(l: Location?): Boolean {
        for (n in BoatInfo.values()) if (n.outsideBoatBorder.insideBorder(l)) return true
        return false
    }

    fun getMyPestControlSession(p: Player): PestControlSession {
        return p.getExtension(PestControlSession::class.java)!!
    }

}