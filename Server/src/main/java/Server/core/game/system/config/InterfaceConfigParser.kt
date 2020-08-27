package core.game.system.config

import core.ServerConstants
import core.game.component.ComponentDefinition
import core.game.system.SystemLogger
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader

class InterfaceConfigParser{
    val parser = JSONParser()
    var reader: FileReader? = null
    fun load() {
        var count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "interface_configs.json")
        val configlist = parser.parse(reader) as JSONArray
        for(config in configlist){
            val e = config as JSONObject
            val id = e["id"].toString().toInt()
            if (ComponentDefinition.getDefinitions().containsKey(id)) {
                ComponentDefinition.getDefinitions()[id]!!.parse(e["interfaceType"].toString(),e["walkable"].toString(),e["tabIndex"].toString())
            }
            ComponentDefinition.getDefinitions()[id] = ComponentDefinition().parse(e["interfaceType"].toString(),e["walkable"].toString(),e["tabIndex"].toString())
            count++
        }
        SystemLogger.log("[Config Parser]: Parsed $count interface configs.")
    }
}