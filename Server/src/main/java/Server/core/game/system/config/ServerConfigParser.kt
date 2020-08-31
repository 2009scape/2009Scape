package core.game.system.config

import core.JSONUtils
import core.ServerConstants
import core.game.world.GameSettings
import core.game.world.GameWorld
import core.plugin.PluginManager
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader

/**
 * Class for parsing the server config, I.E default.json
 * @param path the path to the JSON file to parse.
 * @author Ceikry
 */
class ServerConfigParser(path: String) {
    val pathTo = JSONUtils.parsePath(path)
    val confFile = File(pathTo)
    val parser = JSONParser()
    var reader: FileReader? = null
    var data: JSONObject? = null

    init {
        if(!confFile.exists()){
            println("File specified for the config file does not exist!!")
        } else if(!pathTo.contains(".json")) {
            println("Config file MUST be a JSON file!!")
            println("(Got $pathTo)")
        } else {
            reader = FileReader(pathTo)
            data = parser.parse(reader) as JSONObject
            ServerConstants.SERVER_NAME = data!!["name"] as String
            parseGameSettings()
            parseServerSettings()
            parsePluginToggles()
        }
    }

    private fun parseGameSettings(){
        data ?: return
        val gsData = data!!["GameSettings"] as JSONObject
        GameWorld.setSettings(GameSettings.parse(gsData))
    }

    private fun parseServerSettings(){
        data ?: return
        val ssData = data!!["ServerSettings"] as JSONObject
        ServerConstants.parse(ssData)
    }

    private fun parsePluginToggles(){
        data ?: return
        val dpData = data!!["PluginToggles"] as JSONObject
        dpData.map {
            if(!(it.value as Boolean)){
                PluginManager.disabledPlugins.put(it.key.toString(),it.value as Boolean)
            }
        }
    }
}