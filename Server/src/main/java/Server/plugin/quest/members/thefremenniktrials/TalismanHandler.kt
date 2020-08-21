package plugin.quest.members.thefremenniktrials

import core.cache.def.impl.ItemDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.Entity
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import kotlin.math.abs
import kotlin.math.atan2

@InitializablePlugin
class TalismanHandler : OptionHandler() {

    val possibleLocations = listOf(Location(2625,3608),
                                   Location(2602,3628),
                                   Location(2668,3714),
                                   Location(2711,3602),
                                   Location(2664,3592))


    fun Location.getDirection(entity: Entity): String{
        val loc: Location = this
        val difX: Double = (loc.x - entity.location.x).toDouble()
        val dify: Double = (loc.y - entity.location.y).toDouble()
        val angle = Math.toDegrees(atan2(difX,dify))
        val NORTH  = 0.toDouble()
        val SOUTH = 180.toDouble()
        val EAST = (-90).toDouble()
        val WEST = 90.toDouble()
        val NORTHEAST = (-135).toDouble()
        val NORTHWEST = 135.toDouble()
        val SOUTHEAST = (-45).toDouble()
        val SOUTHWEST = 45.toDouble()
        if(diff(angle,NORTH) < 3){
            return "north"
        }
        if(diff(angle,SOUTH) < 3){
            return "south"
        }
        if(diff(angle,EAST) < 3){
            return "west"
        }
        if(diff(angle,WEST) < 3){
            return "east"
        }
        if(diff(angle,SOUTHEAST) < 45){
            return "north-west"
        }
        if(diff(angle,SOUTHWEST) < 45){
            return "north-east"
        }
        if(diff(angle,NORTHEAST) < 45){
            return "south-west"
        }
        if(diff(angle,NORTHWEST) < 45){
            return "south-east"
        }
        return "Dunno. ${angle}"
     }

    fun diff(x: Double,y: Double): Double{
        return abs(x - y)
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {

        class DraugenPulse(player: Player?) : Pulse(){
            var count = 0
            override fun pulse(): Boolean {
                when(count++){
                    3 -> Draugen(player).init().also { return true }
                }
                return false
            }
        }

        var item: Item? = Item(0)
        if(node is Item){
            item = node
        } else {
            return false
        }
        if(item.id == 3696){
            var locationString = player?.getAttribute("fremtrials:draugen-loc","none")
            if(locationString == "none"){
                val newLoc = possibleLocations.random()
                player?.setAttribute("/save:fremtrials:draugen-loc","${newLoc.x},${newLoc.y}")
                locationString = "${newLoc.x},${newLoc.y}"
            }
            val locationComponents = locationString?.split(",")
            val draugenLoc = Location(Integer.parseInt(locationComponents?.get(0)),Integer.parseInt(locationComponents?.get(1)))

            if(player?.location?.withinDistance(draugenLoc,5)!!){
                player.dialogueInterpreter.sendDialogue("The Draugen is nearby, be careful!")
                GameWorld.submit(DraugenPulse(player))
            } else {
                val neededDirection = draugenLoc.getDirection(player as Entity)
                player.sendMessage("The talisman pulls you to the $neededDirection")
            }
            return true
        }
        return false
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        ItemDefinition.forId(3696).configurations["option:locate"] = this
        return this
    }

}