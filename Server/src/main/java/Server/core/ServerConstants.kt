package core

import core.game.system.mysql.SQLManager
import core.game.world.map.Location
import core.tools.mysql.Database
import org.json.simple.JSONObject
import java.io.File

/**
 * A class holding various variables for the server.
 * @author Ceikry
 */
class ServerConstants {
	companion object {
		//path to the cache
		@JvmField
		var CACHE_PATH: String? = null

		//path for the server store (obsolete, but kept for the sake of system sanity.)
		@JvmField
		var STORE_PATH: String? = null

		//path for player saves
		@JvmField
		var PLAYER_SAVE_PATH: String? = null

		@JvmField
		var PLAYER_ATTRIBUTE_PATH = "ish";

		//path to the various config files, such as npc_spawns.json
		var CONFIG_PATH: String? = null

		@JvmField
		var TRIVIA_CONFIG_PATH: String? = null

		@JvmField
		var GRAND_EXCHANGE_DATA_PATH: String? = null

		@JvmField
		var RDT_DATA_PATH: String? = null

		@JvmField
		var OBJECT_PARSER_PATH: String? = null

		@JvmField
		var DIALOGUE_SCRIPTS_PATH: String? = null

		//the max number of players.
		@JvmField
		var MAX_PLAYERS = 0

		//the max number of NPCs
		@JvmField
		var MAX_NPCS = 0

		//the location where new players are placed on login.
		@JvmField
		var START_LOCATION: Location? = null

		//Location for all home teleports/respawn location
		@JvmField
		var HOME_LOCATION: Location? = null

		//the name for the database
		@JvmField
		var DATABASE_NAME: String? = null

		//username for the database
		@JvmField
		var DATABASE_USER: String? = null

		//password for the database
		@JvmField
		var DATABASE_PASS: String? = null

		//address for the database
		@JvmField
		var DATABASE_ADDRESS: String? = null

		@JvmField
		var DATABASE_PORT: String? = null

		//location names for the ::to command.
		val TELEPORT_DESTINATIONS = arrayOf(
				arrayOf(Location.create(2974, 4383, 2), "corp", "corporal", "corporeal"),
				arrayOf(Location.create(2659, 2649, 0), "pc", "pest control", "pest"),
				arrayOf(Location.create(3293, 3184, 0), "al kharid", "alkharid", "kharid"),
				arrayOf(Location.create(3222, 3217, 0), "lumbridge", "lumby"),
				arrayOf(Location.create(3110, 3168, 0), "wizard tower", "wizards tower", "tower", "wizards"),
				arrayOf(Location.create(3083, 3249, 0), "draynor", "draynor village"),
				arrayOf(Location.create(3019, 3244, 0), "port sarim", "sarim"),
				arrayOf(Location.create(2956, 3209, 0), "rimmington"),
				arrayOf(Location.create(2965, 3380, 0), "fally", "falador"),
				arrayOf(Location.create(2895, 3436, 0), "taverly"),
				arrayOf(Location.create(3080, 3423, 0), "barbarian village", "barb"),
				arrayOf(Location.create(3213, 3428, 0), "varrock"),
				arrayOf(Location.create(3164, 3485, 0), "grand exchange", "ge"),
				arrayOf(Location.create(2917, 3175, 0), "karamja"),
				arrayOf(Location.create(2450, 5165, 0), "tzhaar"),
				arrayOf(Location.create(2795, 3177, 0), "brimhaven"),
				arrayOf(Location.create(2849, 2961, 0), "shilo village", "shilo"),
				arrayOf(Location.create(2605, 3093, 0), "yanille"),
				arrayOf(Location.create(2663, 3305, 0), "ardougne", "ardy"),
				arrayOf(Location.create(2450, 3422, 0), "gnome stronghold", "gnome"),
				arrayOf(Location.create(2730, 3485, 0), "camelot", "cammy", "seers"),
				arrayOf(Location.create(2805, 3435, 0), "catherby"),
				arrayOf(Location.create(2659, 3657, 0), "rellekka"),
				arrayOf(Location.create(2890, 3676, 0), "trollheim"),
				arrayOf(Location.create(2914, 3746, 0), "godwars", "gwd", "god wars"),
				arrayOf(Location.create(3180, 3684, 0), "bounty hunter", "bh"),
				arrayOf(Location.create(3272, 3687, 0), "clan wars", "clw"),
				arrayOf(Location.create(3090, 3957, 0), "mage arena", "mage", "magearena", "arena"),
				arrayOf(Location.create(3069, 10257, 0), "king black dragon", "kbd"),
				arrayOf(Location.create(3359, 3416, 0), "digsite"),
				arrayOf(Location.create(3488, 3489, 0), "canifis"),
				arrayOf(Location.create(3428, 3526, 0), "slayer tower", "slayer"),
				arrayOf(Location.create(3502, 9483, 2), "kalphite queen", "kq", "kalphite hive", "kalphite"),
				arrayOf(Location.create(3233, 2913, 0), "pyramid"),
				arrayOf(Location.create(3419, 2917, 0), "nardah"),
				arrayOf(Location.create(3482, 3090, 0), "uzer"),
				arrayOf(Location.create(3358, 2970, 0), "pollnivneach", "poln"),
				arrayOf(Location.create(3305, 2788, 0), "sophanem"),
				arrayOf(Location.create(2898, 3544, 0), "burthorpe", "burthorp"),
				arrayOf(Location.create(3088, 3491, 0), "edge", "edgeville"),
				arrayOf(Location.create(3169, 3034, 0), "bedabin"),
				arrayOf(Location.create(3565, 3289, 0), "barrows")
		)

		@JvmField
		var DATABASE: Database? = null

		//if SQL is enabled
		@JvmField
		var MYSQL = true

		//the server name
		@JvmField
		var SERVER_NAME: String = ""


		/**
		 * Parses a JSONObject and retrieves the values for all settings in this file.
		 * @author Ceikry
		 * @param data : The JSONObject to parse.
		 */
		fun parse(data: JSONObject) {
			MAX_PLAYERS = data["max_players"].toString().toInt()
			MAX_NPCS = data["max_npcs"].toString().toInt()

			START_LOCATION = JSONUtils.parseLocation(data["new_player_location"].toString())
			HOME_LOCATION = JSONUtils.parseLocation(data["home_location"].toString())

			CACHE_PATH = JSONUtils.parsePath(data["cache_path"].toString())
			STORE_PATH = JSONUtils.parsePath(data["store_path"].toString())
			PLAYER_SAVE_PATH = JSONUtils.parsePath(data["save_path"].toString())
			CONFIG_PATH = JSONUtils.parsePath(data["configs_path"].toString())
			PLAYER_ATTRIBUTE_PATH = PLAYER_SAVE_PATH + "attributes" + File.separator
			GRAND_EXCHANGE_DATA_PATH = JSONUtils.parsePath(data["grand_exchange_data_path"].toString())
			RDT_DATA_PATH = JSONUtils.parsePath(data["rare_drop_table_path"].toString())
			OBJECT_PARSER_PATH = JSONUtils.parsePath(data["object_parser_path"].toString())
			DIALOGUE_SCRIPTS_PATH = JSONUtils.parsePath(data["dialogue_scripts_path"].toString())

			DATABASE_NAME = data["database_name"].toString()
			DATABASE_USER = data["database_username"].toString()
			DATABASE_PASS = data["database_password"].toString()
			DATABASE_ADDRESS = data["database_address"].toString()
			DATABASE_PORT = data["database_port"].toString()

			SQLManager.SQL_SERVER_ADDRESS = DATABASE_ADDRESS
			SQLManager.USERNAME = DATABASE_USER
			SQLManager.PASSWORD = DATABASE_PASS
			SQLManager.PORT = DATABASE_PORT
			DATABASE = Database(DATABASE_ADDRESS, DATABASE_NAME, DATABASE_USER, DATABASE_PASS)
		}
	}
}