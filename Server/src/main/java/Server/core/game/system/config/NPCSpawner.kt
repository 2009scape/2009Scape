package core.game.system.config

import core.ServerConstants
import core.game.node.entity.npc.NPC
import core.game.system.SystemLogger
import core.game.world.map.Direction
import core.game.world.map.Location
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

class NPCSpawner {
    val parser = JSONParser()
    var reader: FileReader? = null
    fun load(){
        var count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "npc_spawns.json")
        val configs = parser.parse(reader) as JSONArray
        for(config in configs){
            val e = config as JSONObject
            val datas: Array<String> = e["loc_data"].toString().split("-".toRegex()).toTypedArray()
            var tokens: Array<String>? = null
            val id = e["npc_id"].toString().toInt()
            for (d in datas) {
                if(d.isEmpty()){
                    continue
                }
                tokens = d.replace("{", "").replace("}", "").split(",".toRegex()).toTypedArray()
                val npc = NPC.create(id, Location.create(Integer.valueOf(tokens[0].trim { it <= ' ' }), Integer.valueOf(tokens[1].trim { it <= ' ' }), Integer.valueOf(tokens[2].trim { it <= ' ' })))
                npc.isWalks = tokens[3].trim { it <= ' ' } == "1"
                npc.direction = Direction.values()[Integer.valueOf(tokens[4].trim { it <= ' ' })]
                npc.setAttribute("spawned:npc", true)
                npc.init()
                count++
            }
        }
        SystemLogger.log("[Config Parser]: Spawned $count NPCs")
    }
}