package plugin.activity.pestcontrol

import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.game.world.map.zone.ZoneBorders
import plugin.ai.minigamebots.pestcontrol.PestControlTestBot
import plugin.ai.minigamebots.pestcontrol.PestControlTestBot2
import kotlin.random.Random


object PestControlHelper {
    var GATE_ENTRIES = listOf(14233, 14235)
    var Portals_AttackableN = listOf(6142, 6143, 6144, 6145).toMutableList()
    var Portals_AttackableI = listOf(6150, 6151, 6152, 6153).toMutableList()
    var Portals_AttackableV = listOf(7551, 7552, 7553, 7554).toMutableList()
    val portalIds = arrayOf(
            6142, 6143, 6144, 6145, 6146, 6147, 6148, 6149,
            6150, 6151, 6152, 6153, 6154, 6155, 6156, 6157,
            7551, 7552, 7553, 7554, 7555, 7556, 7557, 7558)
    var PORTAL_ENTRIES = listOf(*portalIds)
    val PestControlLanderIntermediate = Location.create(2644, 2646, 0)
    val PestControlLanderNovice = Location.create(2657, 2642, 0)
    val PestControlIslandLocation = Location.create(2659, 2676, 0)
    val PestControlIslandLocation2 = Location.create(2659, 2676, 0)

    var pclocations =
            if (Random.nextBoolean()){ Location.create(2667, 2653,0)
             }else if (Random.nextBoolean()){ Location.create(2658, 2654, 0)
             }else if (Random.nextBoolean()){ Location.create(2651, 2659, 0)
             }else if(Random.nextBoolean()){ Location.create(2650, 2664,0)
             }else{ Location.create(2665, 2660, 0) }

    ////////////Begin functions///////////////
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

    fun getMyPestControlSession1(p: PestControlTestBot): PestControlSession? {
        return p.getExtension(PestControlSession::class.java)
    }

    fun getMyPestControlSession2(p: PestControlTestBot2): PestControlSession? {
        return p.getExtension(PestControlSession::class.java)
    }

}