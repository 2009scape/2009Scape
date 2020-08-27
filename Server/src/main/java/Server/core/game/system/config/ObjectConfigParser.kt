package core.game.system.config

import core.ServerConstants
import core.cache.def.impl.ObjectDefinition
import core.game.system.SystemLogger
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

class ObjectConfigParser {
    val parser = JSONParser()
    var reader: FileReader? = null
    fun load(){
        var count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "object_configs.json")
        val configlist = parser.parse(reader) as JSONArray
        for(config in configlist){
            val e = config as JSONObject
            val ids = e["ids"].toString().split(",").map { it.toInt() }
            for (id in ids) {
                val def = ObjectDefinition.forId(id)
                val configs = def.configurations
                e.map {
                    if (it.value.toString().isNotEmpty() && it.value.toString() != "null") {
                        when (it.key.toString()) {
                            //Strings
                            "examine" -> configs.put(it.key.toString(), it.value.toString())

                            //Animations
                            "render_anim" -> def.animationId = it.value.toString().toInt()
                        }
                    }
                }
                count++
            }
        }
        SystemLogger.log("[Config Parser]: Parsed $count object configs.")
    }
}

