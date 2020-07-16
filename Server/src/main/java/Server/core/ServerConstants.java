package core;

import core.game.system.mysql.SQLManager;
import core.game.world.map.Location;
import core.tools.mysql.Database;

import java.io.File;

/**
 * A class holding constants of the server.
 * @author Emperor
 * @author Vexia
 * 
 */
public final class ServerConstants {
	
	/**
	 * The cache path.
	 */
	public static final String CACHE_PATH = "data/cache/";

	/**
	 * The store path.
	 */
	public static final String STORE_PATH = "data/store/";
	
	/**
	 * The player account path.
	 */
	public static final String PLAYER_SAVE_PATH = "data/players/";

	public static final String PLAYER_ATTRIBUTE_PATH = "data" + File.separator + "players" + File.separator + "attributes" + File.separator;

	/**
	 * The maximum amount of players.
	 */
	public static final int MAX_PLAYERS = (1 << 11) - 1;

	/**
	 * The maximum amount of NPCs.
	 */
	public static final int MAX_NPCS = (1 << 15) - 1;
	
	/**
	 * The start location for a fresh account.
	 */
	public static final Location START_LOCATION = Location.create(3094, 3107, 0);
	
	/**
	 * The main home teleport location.
	 */
	public static final Location HOME_LOCATION = Location.create(3222, 3218, 0);
	
	/**
	 * The teleport destinations.
	 */
	public static final Object[][] TELEPORT_DESTINATIONS = {
			{ Location.create(2974, 4383, 2), "corp", "corporal", "corporeal" },
			{ Location.create(2659, 2649, 0), "pc", "pest control", "pest" },
			{ Location.create(3293, 3184, 0), "al kharid", "alkharid", "kharid" },
			{ Location.create(3222, 3217, 0), "lumbridge", "lumby" },
			{ Location.create(3110, 3168, 0), "wizard tower", "wizards tower", "tower", "wizards" },
			{ Location.create(3083, 3249, 0), "draynor", "draynor village" },
			{ Location.create(3019, 3244, 0), "port sarim", "sarim" },
			{ Location.create(2956, 3209, 0), "rimmington" },
			{ Location.create(2965, 3380, 0), "fally", "falador" },
			{ Location.create(2895, 3436, 0), "taverly" },
			{ Location.create(3080, 3423, 0), "barbarian village", "barb" },
			{ Location.create(3213, 3428, 0), "varrock" },
			{ Location.create(3164, 3485, 0), "grand exchange", "ge" },
			{ Location.create(2917, 3175, 0), "karamja" },
			{ Location.create(2450, 5165, 0), "tzhaar" },
			{ Location.create(2795, 3177, 0), "brimhaven" },
			{ Location.create(2849, 2961, 0), "shilo village", "shilo" },
			{ Location.create(2605, 3093, 0), "yanille" },
			{ Location.create(2663, 3305, 0), "ardougne", "ardy" },
			{ Location.create(2450, 3422, 0), "gnome stronghold", "gnome" },
			{ Location.create(2730, 3485, 0), "camelot", "cammy", "seers" },
			{ Location.create(2805, 3435, 0), "catherby" },
			{ Location.create(2659, 3657, 0), "rellekka" },
			{ Location.create(2890, 3676, 0), "trollheim" },
			{ Location.create(2914, 3746, 0), "godwars", "gwd", "god wars" },
			{ Location.create(3180, 3684, 0), "bounty hunter", "bh" },
			{ Location.create(3272, 3687, 0), "clan wars", "clw" },
			{ Location.create(3090, 3957, 0), "mage arena", "mage", "magearena", "arena" },
			{ Location.create(3069, 10257, 0), "king black dragon", "kbd" },
			{ Location.create(3359, 3416, 0), "digsite" }, 
			{ Location.create(3488, 3489, 0), "canifis" },
			{ Location.create(3428, 3526, 0), "slayer tower", "slayer" },
			{ Location.create(3502, 9483, 2), "kalphite queen", "kq", "kalphite hive", "kalphite" },
			{ Location.create(3233, 2913, 0), "pyramid" },
			{ Location.create(3419, 2917, 0), "nardah" },
			{ Location.create(3482, 3090, 0), "uzer" },
			{ Location.create(3358, 2970, 0), "pollnivneach", "poln" },
			{ Location.create(3305, 2788, 0), "sophanem" },
			{ Location.create(2898, 3544, 0), "burthorpe", "burthorp" },
			{ Location.create(3088, 3491, 0), "edge", "edgeville" },
			{ Location.create(3169, 3034, 0), "bedabin" },
			{ Location.create(3565, 3289, 0), "barrows" },
		};
	
	/**
	 * The teleport destinations, intended for Grandpa Jack.
	 */
	public static final Object[][] TELEPORT_DESTINATIONS_DONATOR = {
			{ Location.create(2914, 3746, 0), "godwars", "gwd", "god wars" },
			{ Location.create(2659, 2649, 0), "pc", "pest control", "pest" },
			{ Location.create(3293, 3184, 0), "al kharid", "alkharid", "kharid" },
			{ Location.create(3222, 3217, 0), "lumbridge", "lumby" },
			{ Location.create(3110, 3168, 0), "wizard tower", "wizards tower", "tower", "wizards" },
			{ Location.create(3083, 3249, 0), "draynor", "draynor village" },
			{ Location.create(3019, 3244, 0), "port sarim", "sarim" },
			{ Location.create(2956, 3209, 0), "rimmington" },
			{ Location.create(2965, 3380, 0), "fally", "falador" },
			{ Location.create(2895, 3436, 0), "taverly" },
			{ Location.create(3080, 3423, 0), "barbarian village", "barb" },
			{ Location.create(3213, 3428, 0), "varrock" },
			{ Location.create(3164, 3485, 0), "grand exchange", "ge" },
			{ Location.create(2917, 3175, 0), "karamja" },
			{ Location.create(2450, 5165, 0), "tzhaar" },
			{ Location.create(2795, 3177, 0), "brimhaven" },
			{ Location.create(2849, 2961, 0), "shilo village", "shilo" },
			{ Location.create(2605, 3093, 0), "yanille" },
			{ Location.create(2663, 3305, 0), "ardougne", "ardy" },
			{ Location.create(2450, 3422, 0), "gnome stronghold", "gnome" },
			{ Location.create(2730, 3485, 0), "camelot", "cammy", "seers" },
			{ Location.create(2805, 3435, 0), "catherby" },
			{ Location.create(2659, 3657, 0), "rellekka" },
			{ Location.create(2890, 3676, 0), "trollheim" },
			{ Location.create(3180, 3684, 0), "bounty hunter", "bh" },
			{ Location.create(3272, 3687, 0), "clan wars", "clw" },
			{ Location.create(3090, 3957, 0), "mage arena", "mage", "magearena", "arena" },
			{ Location.create(3359, 3416, 0), "digsite" }, 
			{ Location.create(3488, 3489, 0), "canifis" },
			{ Location.create(3428, 3526, 0), "slayer tower", "slayer" },
			{ Location.create(3233, 2913, 0), "pyramid" },
			{ Location.create(3419, 2917, 0), "nardah" },
			{ Location.create(3482, 3090, 0), "uzer" },
			{ Location.create(3358, 2970, 0), "pollnivneach", "poln" },
			{ Location.create(3305, 2788, 0), "sophanem" },
			{ Location.create(2898, 3544, 0), "burthorpe", "burthorp" },
			{ Location.create(3088, 3491, 0), "edge", "edgeville" },
			{ Location.create(3169, 3034, 0), "bedabin" },
			{ Location.create(3565, 3311, 0), "barrows" },
		};
	
	/**
	 * The string of donation messages displayed on an interface.
	 */
	public static final String[] MESSAGES = new String[] { "Welcome!" };
	
	public static final String[] DATABASE_NAMES = {
			"server",
			"global",
	};

	public static final Database[] DATABASES = {
			new Database(SQLManager.SQL_SERVER_ADDRESS, DATABASE_NAMES[0], (SQLManager.LOCAL ? "root" : "username"), (SQLManager.LOCAL ? "" : "password")),
			new Database(SQLManager.SQL_SERVER_ADDRESS, DATABASE_NAMES[1], (SQLManager.LOCAL ? "root" : "username"), (SQLManager.LOCAL ? "" : "password"))
	};
	
	/**
	 * If MySQL is enabled.
	 */
	public static boolean MYSQL = true;

	public static boolean VALIDATED = false;
	
	/**
	 * Constructs a new {@Code ServerConstants} {@Code Object}
	 */
	private ServerConstants() {
		/*
		 * empty.
		 */
	}
	
	public static final Boolean NEW_PLAYER_DEFAULT_CLAN = true;
	public static final String SERVER_NAME = "2009Scape";
	
}
