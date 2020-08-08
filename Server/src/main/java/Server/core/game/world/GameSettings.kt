package core.game.world

import core.game.system.SystemLogger
import core.plugin.PluginManager
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Represents the game settings used for this game instance.
 * @author Vexia
 */
class GameSettings
/**
 * Constructs a new `GameSettings` `Object`.
 * @param name the name.
 * @param beta the beta.
 * @param type the game type.
 * @param gui if gui is enabled.
 * @param worldId the world id.
 * @param countryIndex The country index.
 * @param members If the world is members only.
 * @param msAddress The address of the Management server.
 */ internal constructor(
        /**
         * The name of the namme.
         */
        val name: String,
        /**
         * If the game is in beta mode.
         */
        val isBeta: Boolean,
        /**
         * If the game is in developer mode.
         */
        val isDevMode: Boolean,
        /**
         * If the gui is enabled.
         */
        val isGui: Boolean,
        /**
         * The world id of the server.
         */
        val worldId: Int,
        /**
         * The country index.
         */
        val countryIndex: Int,
        /**
         * The activity.
         */
        val activity: String,
        /**
         * If the world is members only.
         */
        val isMembers: Boolean,
        /**
         * If the world is a pvp world.
         */
        val isPvp: Boolean,
        /**
         * If only quick chat can be used on the world.
         */
        val isQuickChat: Boolean,
        /**
         * If lootshare option is enabled on this world.
         */
        val isLootshare: Boolean,
        /**
         * The address of the Management server.
         */
        val msAddress: String,
        val default_xp_rate: Double,
        val allow_slayer_reroll: Boolean,
        val enable_default_clan: Boolean,
        val enable_bots: Boolean,
        val autostock_ge: Boolean,
        val allow_token_purchase: Boolean
        ) {
    val isHosted: Boolean
        get() = !isDevMode

    override fun toString(): String {
        return "GameSettings [name=$name, debug=$isBeta, devMode=$isDevMode, gui=$isGui, worldId=$worldId]"
    }

    companion object {
        /**
         * Parses the game settings from the program arguments.
         * @param args The program arguments.
         * @return The game settings.
         */
        fun parse(args: Array<String>): GameSettings? {
            return parse(args[0])
        }

        /**
         * Parses a game settings file.
         * @param path the path.
         * @return the settings.
         */
        fun parse(path: String): GameSettings? {
            val f = File(path)
            if (!f.exists()) {
                return null
            }
            val factory = DocumentBuilderFactory.newInstance()
            try {
                val builder = factory.newDocumentBuilder()
                val doc = builder.parse(path)
                val settings = doc.getElementsByTagName("GameSettings").item(0) as Element
                val name = settings.getAttribute("name")
                val beta = java.lang.Boolean.parseBoolean(settings.getAttribute("debug"))
                val devMode = java.lang.Boolean.parseBoolean(settings.getAttribute("dev"))
                val startGui = java.lang.Boolean.parseBoolean(settings.getAttribute("startGui"))
                val worldId = settings.getAttribute("worldID").toInt()
                val countryId = settings.getAttribute("countryID").toInt()
                val activity = settings.getAttribute("activity")
                val pvp = java.lang.Boolean.parseBoolean(settings.getAttribute("pvpWorld"))
                val ipAddress = settings.getAttribute("msip")
                val defaultXpRate = settings.getAttribute("default_xp_rate").toDouble()
                val allowSlayerReroll = settings.getAttribute("allow_slayer_reroll")!!.toBoolean()
                val enableDefaultClan = settings.getAttribute("enable_default_clan")!!.toBoolean()
                val enableBots = settings.getAttribute("enable_bots")!!.toBoolean()
                val autostockGe = settings.getAttribute("autostock_ge")!!.toBoolean()
                val allow_token_purchase = settings.getAttribute("allow_token_purchase")!!.toBoolean()
                val pluginSettings = doc.getElementsByTagName("PluginSetting")
                for (i in 0 until pluginSettings.length) {
                    val settingsNode = pluginSettings.item(i)
                    if (settingsNode.nodeType == Node.ELEMENT_NODE) {
                        val pluginSetting = settingsNode as Element
                        val pName = pluginSetting.getAttribute("name")
                        val enabled = java.lang.Boolean.parseBoolean(pluginSetting.getAttribute("enabled"))
                        if (!enabled) {
                            println("Setting $pName as disabled.")
                            PluginManager.disabledPlugins.putIfAbsent(pName, false)
                        }
                    }
                }
                return GameSettings(name, beta, devMode, startGui, worldId, countryId, activity, true, pvp, false, false, ipAddress,defaultXpRate,allowSlayerReroll,enableDefaultClan,enableBots,autostockGe,allow_token_purchase)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        /**
         * Gets the properties.
         * @param path the path.
         * @return the properties.
         */
        private fun getProperties(path: String): Properties {
            val file: FileInputStream
            val properties = Properties()
            try {
                file = FileInputStream(path)
                properties.load(file)
            } catch (e: IOException) {
                println("Warning: Could not find file in " + System.getProperty("user.dir") + path)
                e.printStackTrace()
            }
            return properties
        }
    }
}