package plugin.quest.members.familycrest


import core.game.node.entity.Entity
import core.game.node.entity.combat.ImpactHandler
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.system.SystemLogger
import core.game.system.config.NPCSpawner
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Direction
import core.game.world.map.Location
import core.game.world.map.Region
import core.game.world.map.RegionManager
import core.game.world.map.zone.MapZone
import core.game.world.map.zone.ZoneBorders
import core.game.world.map.zone.ZoneBuilder
import core.game.world.map.zone.ZoneMonitor
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.RandomFunction

@InitializablePlugin
class ChronozonCaveZone: MapZone("FC ChronozoneZone", true), Plugin<Unit> {

    val triggers = ArrayList<Location>()
    var chronozon = ChronozonNPC(667, Location(3086, 9936, 0))
    override fun configure() {
        register(ZoneBorders(3082, 9929, 3091, 9940))
        triggers.add(Location.create(3083, 9939))
        triggers.add(Location.create(3084, 9939))
        triggers.add(Location.create(3085, 9939))
        triggers.add(Location.create(3086, 9939))
        triggers.add(Location.create(3087, 9939))
        triggers.add(Location.create(3088, 9939))
        triggers.add(Location.create(3089, 9939))
        triggers.add(Location.create(3090, 9939))
    }

    override fun move(e: Entity?, from: Location?, to: Location?): Boolean {
        if(triggers.contains(from) && e is Player){
            if(e.direction == Direction.getDirection(Location.create(0,0,0), Location.create(0, -1,0))){

                chronozon = ChronozonNPC(667, Location(3086, 9936, 0))

                if(e.questRepository.getQuest("Family Crest").getStage(e) == 19 && !RegionManager.getLocalNpcs(Location(3086, 9936, 0), 5).contains(chronozon) ){
                    chronozon.setPlayer(e);
                    chronozon.isRespawn = false
                    chronozon.init()
                    e.debug("Spanwed Chronozon")
                }
            }
            else {

            }
        }
        return super.move(e, from, to)
    }
    fun trigger(player: Player){

    }
    override fun newInstance(arg: Unit?): Plugin<Unit> {
        ZoneBuilder.configure(this)
        return this
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return UInt
    }


}