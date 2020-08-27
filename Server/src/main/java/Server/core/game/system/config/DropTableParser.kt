package core.game.system.config

import core.ServerConstants
import core.cache.def.impl.NPCDefinition
import core.game.node.entity.npc.drop.DropFrequency
import core.game.node.entity.npc.drop.NPCDropTables
import core.game.node.item.ChanceItem
import core.game.system.SystemLogger
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader
import java.util.*

class DropTableParser {
    val parser = JSONParser()
    var reader: FileReader? = null
    fun load(){
        var count = 0
        reader = FileReader(ServerConstants.CONFIG_PATH + "drop_tables.json")
        val configs = parser.parse(reader) as JSONArray
        for(config in configs){
            val e = config as JSONObject
            val id = e["npc_id"].toString().toInt()
            val defaultTable = parseTable(e["default"].toString())
            val mainTable = parseTable(e["main"].toString())
            val charmTable = parseTable(e["charm"].toString())
            val def = NPCDefinition.forId(id)
            val tables = NPCDropTables(def)
            def.dropTables = tables
            tables.defaultTable.addAll(defaultTable as List<ChanceItem>)
            tables.mainTable.addAll(mainTable as List<ChanceItem>)
            tables.charmTable.addAll(charmTable as List<ChanceItem>)
            count++
        }
        SystemLogger.log("[Config Parser]: Parsed $count drop tables.")
    }

    private fun parseTable(data: String?): List<ChanceItem>? {
        val table: MutableList<ChanceItem> = ArrayList()
        if (data == null || data.length == 0) {
            return table
        }
        val datas = data.split("~".toRegex()).toTypedArray()
        var tokens: Array<String>? = null
        for (d in datas) {
            tokens = d.replace("{", "").replace("}", "").split(",".toRegex()).toTypedArray()
            if(tokens.size == 1){
                continue
            }
            try {
                table.add(ChanceItem(Integer.valueOf(tokens[0].trim { it <= ' ' }), Integer.valueOf(tokens[1].trim { it <= ' ' }), Integer.valueOf(tokens[2].trim { it <= ' ' }), 1000, tokens[3].trim { it <= ' ' }.toDouble(), DropFrequency.valueOf(tokens[4]), if (tokens.size == 6) Integer.valueOf(tokens[5].trim { it <= ' ' }) else -1))
            } catch (e: NumberFormatException) {
                System.err.println("Error parsing NPC drops!  Data -> " + data + ", tokens -> " + ", exception -> " + e.message)
            }
        }
        return table
    }
}