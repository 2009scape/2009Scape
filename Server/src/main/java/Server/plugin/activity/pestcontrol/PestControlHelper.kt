package plugin.activity.pestcontrol

import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders
import java.util.*

object PestControlHelper {
    var GATE_ENTRIES = Arrays.asList(14233, 14235)
    var PORTAL_ENTRIES = Arrays.asList(*PCPortalNPC.portalIds)
    val PestControlIslandLocation = Location.create(2659, 2649, 0)
    val PestControlIslandLocation2 = Location.create(2648, 2648, 0)
    fun isInPestControlInstance(p: Player): Boolean {
        return p.getAttribute<Any?>("pc_zeal") != null
    }

    enum class BoatInfo(var boatBorder: ZoneBorders, var outsideBoatBorder: ZoneBorders, var ladderId: Int) {
        NOVICE(ZoneBorders(2660, 2638, 2664, 2644), ZoneBorders(2657, 2637, 2657, 2643), 14315),
        INTERMEDIATE(ZoneBorders(2638, 2642, 2641, 2647), ZoneBorders(2644, 2642, 2644, 2646), 25631),
        VERTERAN(ZoneBorders(2632, 2649, 2635, 2654), ZoneBorders(2639, 2652, 2638, 2655), 25632);
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
        return p.getExtension(PestControlSession::class.java)
    }

}