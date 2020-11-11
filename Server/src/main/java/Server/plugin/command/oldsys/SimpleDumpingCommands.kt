package plugin.command.oldsys

import core.cache.def.impl.ItemDefinition
import core.cache.def.impl.NPCDefinition
import core.cache.def.impl.ObjectDefinition
import core.game.node.entity.player.Player
import core.game.system.command.CommandPlugin
import core.game.system.command.CommandSet
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import java.io.BufferedWriter
import java.io.File

@InitializablePlugin
class SimpleDumpingCommands : CommandPlugin() {
    override fun parse(player: Player?, name: String?, args: Array<String?>?): Boolean {
        when(name) {
            "make" -> handleMake(player, args?.toList() as List<String>?).also { return true }
        }
        return false
    }

    private fun handleMake(player: Player?,args: List<String>?){
        val dataType = args?.getOrNull(1)
        val outputType = args?.getOrNull(2)

        if(dataType == null || outputType == null) player?.sendMessage("Usage: ::make item|object|npc list|doc")

        when(outputType){
            "list" -> makeDumpList(dataType!!).also { player?.sendMessage("Creating $dataType dump list...") }
            "doc" -> makeDumpDoc(dataType!!).also { player?.sendMessage("Creating $dataType dump doc...") }
        }
    }

    private fun makeDumpList(type: String){
        val f = File(System.getProperty("user.dir") + File.separator + "${type}list.txt")
        val writer = f.bufferedWriter()
        when(type){
            "item" -> for (i in ItemDefinition.getDefinitions().values){
                            writer.writeLn("${i.name}(${i.id}) - ${i.examine}")
                       }
            "object" -> for(i in ObjectDefinition.getDefinitions().values){
                            writer.writeLn("${i.name}(${i.id}) - ${i.examine}")
                       }
            "npc" -> for(i in NPCDefinition.getDefinitions().values){
                            writer.writeLn("${i.name}(${i.id}) - ${i.examine}")
                       }
        }
        writer.close()
    }

    private fun makeDumpDoc(type: String){
        val f = File(System.getProperty("user.dir") + File.separator + "${type}list.html")
        println(f.absolutePath)
        val writer = f.bufferedWriter()
        writer.writeLn("<head>")
        writer.writeLn("<link rel=\"stylesheet\" type=\"text/css\" href=\"tableformat.css\">")
        writer.writeLn("</head>")
        writer.writeLn("<table style='width:100%'>")
        writer.writeLn("<tr>")
        writer.writeLn("<th>$type name</th>")
        writer.writeLn("<th>$type ID</th>")
        writer.writeLn("<th>Examine Text</th>")
        writer.writeLn("</tr>")
        when(type){
            "item" -> for (i in ItemDefinition.getDefinitions().values){
                writer.writeLn("<tr>")
                writer.writeLn("<td>${i.name}</td>")
                writer.writeLn("<td id=\"id\">${i.id}</td>")
                writer.writeLn("<td>${i.examine}</td>")
                writer.writeLn("</tr>")
            }
            "object" -> for(i in ObjectDefinition.getDefinitions().values){
                writer.writeLn("<tr>")
                writer.writeLn("<td>${i.name}</td>")
                writer.writeLn("<td>${i.id}</td>")
                writer.writeLn("<td>${i.examine}</td>")
                writer.writeLn("</tr>")
            }
            "npc" -> for(i in NPCDefinition.getDefinitions().values){
                writer.writeLn("<tr>")
                writer.writeLn("<td>${i.name}</td>")
                writer.writeLn("<td>${i.id}</td>")
                writer.writeLn("<td>${i.examine}</td>")
                writer.writeLn("</tr>")
            }
        }
        writer.writeLn("</table>")
        writer.close()
    }

    override fun newInstance(arg: Any?): Plugin<Any?>? {
        link(CommandSet.ADMINISTRATOR)
        return this
    }

    private fun BufferedWriter.writeLn(line: String){
        this.write(line)
        this.newLine()
    }

}