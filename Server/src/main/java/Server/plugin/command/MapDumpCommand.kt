package plugin.command

import core.game.node.entity.player.Player
import core.game.system.command.CommandPlugin
import core.game.system.command.CommandSet
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import java.util.*

@InitializablePlugin
class MapDumpCommand : CommandPlugin() {

    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any?>? {
        link(CommandSet.ADMINISTRATOR)
        return this
    }

    override fun parse(player: Player?, name: String?, args: Array<String?>?): Boolean {
        when (name) {
            "mapredo" -> redoMap(3000, 3000, 1).also { return true }
            "findobj" -> findObj(player, args?.toList() as List<String>?).also { return true }
        }
        return false
    }

    companion object {
        private val GameObjectMap = HashMap<Location, Int>()
        private val LocationObjectMap = HashMap<Int, MutableList<Location>?>()
        private fun redoMap(xmax: Int, ymax: Int, zmax: Int) {
            GameObjectMap.clear()
            LocationObjectMap.clear()
            for (x in 0 until xmax - 1) {
                for (y in 0 until ymax - 1) {
                    for (z in 0 until zmax - 1) {
                        val temp = RegionManager.getObject(z, x, y)
                        if (temp != null) {
                            GameObjectMap[Location(x, y, z)] = temp.id
                        }
                    }
                }
            }
            for ((key, value) in GameObjectMap) {
                if (LocationObjectMap.containsKey(value)) {
                    LocationObjectMap[value]!!.add(key)
                } else {
                    val locations: MutableList<Location> = ArrayList()
                    locations.add(key)
                    LocationObjectMap[value] = locations
                }
            }
            return;
        }

        private fun findObj(player: Player?, args: List<String>?) {
            val objId = Integer.parseInt(args?.getOrNull(1))
            val xmin = 50
            val ymin = 50
            val zmin = 0
            val xmax = 55
            val ymax = 55
            val zmax = 1

            player!!.packetDispatch.sendMessage("Searching...")

            for (x in xmin until xmax - 1) {
                for (y in ymin until ymax - 1) {
                    for (z in zmin until zmax - 1) {
                        player.packetDispatch.sendMessage(Location(x,y,z).toString())
                        val temp = RegionManager.getObject(Location(x,y,z))
                        if (temp != null && temp.id == objId) {
                            player.packetDispatch.sendMessage("Absolute: [$x,$y,$z]")
                        }
                    }
                }
            }

            return;
        }
    }
}