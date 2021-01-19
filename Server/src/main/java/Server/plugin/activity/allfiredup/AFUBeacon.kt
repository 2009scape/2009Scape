package plugin.activity.allfiredup

import core.game.node.entity.player.Player
import core.game.system.SystemLogger
import core.game.world.map.Location

/**
 * Various data for beacons, such as varp and offset, required FM level, etc
 * @author Ceikry
 */
enum class AFUBeacon(val title: String, val fmLevel: Int, val varpId: Int, val offset: Int, val location: Location, val experience: Double, val keeper: Int = 0) {
    RIVER_SALVE("",43,1283,0,Location.create(3396, 3464, 0),216.2,8065),
    RAG_AND_BONE("",43,1283,3,Location.create(3343, 3510, 0),235.8,8066),
    JOLLY_BOAR("",48,1283,6,Location.create(3278, 3525, 0), 193.8,8067),
    NORTH_VARROCK_CASTLE("",53,1283,9,Location.create(3236, 3527, 0),178.5,8068),
    GRAND_EXCHANGE("",59,1283,12,Location.create(3170, 3536, 0),194.3,8069),
    EDGEVILLE("",62,1283,15,Location.create(3087, 3516, 0),86.7,8070),
    MONASTERY("",68,1283,18,Location.create(3034, 3518, 0),224.4,8071),
    GOBLIN_VILLAGE("",72,1283,21,Location.create(2968, 3516, 0),194.8,8072),
    BURTHORPE("",76,1283,24,Location.create(2940, 3565, 0),195.3,8073),
    DEATH_PLATEAU("",79,1288,0,Location.create(2944, 3622, 0),249.9,8074),
    TROLLHEIM("",83,1288,3,Location.create(2939, 3680, 0),201.0,8075),
    GWD("",87,1288,6,Location.create(2937, 3773, 0),255.0,8076),
    TEMPLE("",89,1288,9,Location.create(2946, 3836, 0),198.9),
    PLATEAU("",92,1288,12,Location.create(2964, 3931, 0),147.9);

    companion object {
        fun forLocation(location: Location): AFUBeacon {
            for (beacon in values()) {
                if (beacon.location.equals(location)) return beacon
            }
            return RIVER_SALVE.also { SystemLogger.log("!!!!Unhandled Beacon Location ${location.toString()}") }
        }

        fun resetAllBeacons(player: Player){
            for(beacon in values()){
                player.varpManager.get(beacon.varpId).setVarbit(beacon.offset,0).send(player)
            }
        }
    }

    fun light(player: Player){
        player.varpManager.get(varpId).setVarbit(offset,2).send(player)
    }

    fun diminish(player: Player){
        player.varpManager.get(varpId).setVarbit(offset,3).send(player)
    }

    fun extinguish(player: Player){
        player.varpManager.get(varpId).setVarbit(offset,0).send(player)
    }

    fun lightGnomish(player: Player){
        player.varpManager.get(varpId).setVarbit(offset,4).send(player)
    }

    fun fillWithLogs(player: Player){
        player.varpManager.get(varpId).setVarbit(offset,1).send(player)
    }

    fun getState(player: Player): BeaconState{
        return BeaconState.values()[player.varpManager.get(varpId).getVarbit(offset)?.value ?: 0]
    }
}

enum class BeaconState{
    EMPTY,
    FILLED,
    LIT,
    DYING,
    WARNING
}