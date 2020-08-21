package plugin.quest.members.thefremenniktrials

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.player.Player
import core.game.world.GameWorld
import core.game.world.map.Location
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class SwensenPortals : PluginInteraction(2273,2274,2506,2507,2508,2505,2503,2504,5138){

    class destRoom(val swx: Int, val swy: Int, val nex: Int, val ney: Int)

    fun destRoom.getCenter(): Location{
        return Location((swx + nex) / 2, (swy + ney) / 2).transform(1,0,0)
    }

    override fun handle(player: Player?, node: Node?): Boolean {
        val portal: GameObject? = node as GameObject
        var toLocation: Location? = Location(0,0,0)
        if(portal != null){
            class toPortal(val to: GameObject?) : MovementPulse(player,DestinationFlag.OBJECT.getDestination(player,portal)){
                override fun pulse(): Boolean {
                    when(to?.id){
                        2273 -> toLocation = destRoom(2639,10012,2645,10018).getCenter()
                        2274 -> toLocation = destRoom(2650,10034,2656,10040).getCenter()
                        2506 -> toLocation = destRoom(2662,10023,2669,10029).getCenter()
                        2507 -> toLocation = destRoom(2626,10023,2633,10029).getCenter()
                        2505 -> toLocation = destRoom(2650,10001,2656,10007).getCenter()
                        2503 -> toLocation = destRoom(2662,10012,2668,10018).getCenter()
                        2504 -> {toLocation = destRoom(2662,10034,2668,10039).getCenter(); player?.setAttribute("/save:fremtrials:maze-complete",true)}
                        5138 -> toLocation = getRandomLocation(player)
                    }
                    player?.properties?.teleportLocation = toLocation
                    return true;
                }
            }
            GameWorld.submit(toPortal(portal))
            return true
        }
        return false
    }

    fun getRandomLocation(player: Player?): Location{
        var obj: GameObject? = null

        while(obj?.id != 5138) {
            val objects = player?.viewport?.chunks?.random()?.random()?.objects
            obj = objects?.random()?.random()
            if(obj == null || obj.location?.equals(Location(0,0,0))!!){
                continue
            }
        }
        return obj.location
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        PluginInteractionManager.register(this,PluginInteractionManager.InteractionType.OBJECT)
        return this
    }

}