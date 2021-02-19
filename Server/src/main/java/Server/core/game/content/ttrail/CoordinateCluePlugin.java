package core.game.content.ttrail;

import core.game.world.map.Location;
import core.plugin.Plugin;

/**
 * Initializes the coordinate clues.
 * @author Vexia
 */
public final class CoordinateCluePlugin extends CoordinateClueScroll {

	/**
	 * Constructs a new {@code CoordinateCluePlugin} {@code Object}
	 */
	public CoordinateCluePlugin() {
		this(null, -1, null, null, null);
	}

	/**
	 * Constructs a new {@code CoordinateCluePlugin} {@code Object}
	 * @param name the name.
	 * @param clueId the clue id.
	 * @param level the level.
	 * @param location the location.
	 * @param clue the clue.
	 */
	public CoordinateCluePlugin(String name, int clueId, ClueLevel level, Location location, String clue) {
		super(name, clueId, level, location, clue);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		register(new CoordinateCluePlugin("tree-gnome-coord", 2807, ClueLevel.MEDIUM, new Location(2478, 3158, 0), "00 degrees 05 minutes south<br>01 degrees 13 minutes east"));
		register(new CoordinateCluePlugin("karamja-coord", 2809, ClueLevel.MEDIUM, new Location(2888, 3153, 0), "00 degrees 13 minutes south<br>13 degrees 58 minutes east"));
		register(new CoordinateCluePlugin("brimhaven-coord", 2811, ClueLevel.MEDIUM, new Location(2743, 3151, 0), "00 degrees 18 minutes south<br>09 degrees 28 minutes east"));
		register(new CoordinateCluePlugin("lumbridge-swamp-coord", 2813, ClueLevel.MEDIUM, new Location(3183, 3151, 0), "00 degrees 20 minutes south<br>23 degrees 15 minutes east"));
		register(new CoordinateCluePlugin("zanaris-shed-coord", 2815, ClueLevel.MEDIUM, new Location(3217, 3178, 0), "00 degrees 30 minutes north<br>24 degrees 16 minutes east"));
		register(new CoordinateCluePlugin("port-sarim-coord", 2817, ClueLevel.MEDIUM, new Location(3007, 3145, 0), "00 degrees 31 minutes south<br>17 degrees 43 minutes east"));
		register(new CoordinateCluePlugin("musa-point-coord", 2819, ClueLevel.MEDIUM, new Location(2896, 3119, 0), "01 degrees 18 minutes south<br>14 degrees 15 minutes east"));
		register(new CoordinateCluePlugin("moss-giant-coord", 2821, ClueLevel.MEDIUM, new Location(2696, 3206, 0), "01 degrees 26 minutes north<br>08 degrees 01 minutes east"));
		register(new CoordinateCluePlugin("hazelmere-coord", 2823, ClueLevel.MEDIUM, new Location(2680, 3111, 0), "01 degrees 35 minutes south<br>07 degrees 28 minutes east"));
		register(new CoordinateCluePlugin("uzer-coord", 2825, ClueLevel.MEDIUM, new Location(3510, 3075, 0), "02 degrees 43 minutes south<br>33 degrees 26 minutes east"));
		register(new CoordinateCluePlugin("ham-coord", 2827, ClueLevel.MEDIUM, new Location(3161, 3251, 0), "02 degrees 48 minutes north<br>22 degrees 30 minutes east"));
		register(new CoordinateCluePlugin("ardy-zoo-coord", 2829, ClueLevel.MEDIUM, new Location(2644, 3251, 0), "02 degrees 50 minutes north<br>06 degrees 20 minutes east"));
		register(new CoordinateCluePlugin("nature-altar-coord", 2831, ClueLevel.HARD, new Location(2874, 3047, 0), "03 degrees 35 minutes south<br>13 degrees 35 minutes east"));
		register(new CoordinateCluePlugin("shilo-mine-coord", 2833, ClueLevel.MEDIUM, new Location(2849, 3033, 0), "04 degrees 00 minutes south<br>12 degrees 46 minutes east"));
		register(new CoordinateCluePlugin("bedapin-camp-coord", 2835, ClueLevel.HARD, new Location(3168, 3041, 0), "03 degrees 45 minutes south<br>22 degrees 45 minutes east"));
		// register(new CoordinateCluePlugin("gorad-coord", 2837,
		// ClueLevel.HARD, new Location(2580, 3029, 0),
		// "04 degrees 05 minutes south<br>04 degrees 24 minutes east"));
		register(new CoordinateCluePlugin("crandor-coord", 2839, ClueLevel.MEDIUM, new Location(2848, 3297, 0), "04 degrees 13 minutes north<br>12 degrees 45 minutes east"));
		register(new CoordinateCluePlugin("champions-guild-coord", 2841, ClueLevel.MEDIUM, new Location(3179, 3343, 0), "05 degrees 43 minutes north<br>23 degrees 05 minutes east"));
		register(new CoordinateCluePlugin("cairn-island-coord", 2843, ClueLevel.HARD, new Location(2764, 2974, 0), "05 degrees 50 minutes south<br>10 degrees 05 minutes east"));
		register(new CoordinateCluePlugin("bandit-camp-coord", 2845, ClueLevel.HARD, new Location(3139, 2969, 0), "06 degrees 00 minutes south<br>21 degrees 48 minutes east"));
		// register(new CoordinateCluePlugin("east-karamja-coord", 2847,
		// ClueLevel.HARD, new Location(2924, 2963, 0),
		// "06 degrees 11 minutes south<br>15 degrees 07 minutes east"));
		register(new CoordinateCluePlugin("three-ponds-coord", 2849, ClueLevel.MEDIUM, new Location(2383, 3369, 0), "06 degrees 31 minutes north<br>01 degrees 46 minutes west"));
		register(new CoordinateCluePlugin("draynor-manor-coord", 2851, ClueLevel.MEDIUM, new Location(3120, 3384, 0), "06 degrees 58 minutes north<br>21 degrees 16 minutes east"));
		register(new CoordinateCluePlugin("nature-spirit-coord", 2853, ClueLevel.MEDIUM, new Location(3430, 3388, 0), "07 degrees 05 minutes north<br>30 degrees 56 minutes east"));
		register(new CoordinateCluePlugin("southeast-taverly-coord", 2855, ClueLevel.MEDIUM, new Location(2920, 3404, 0), "07 degrees 33 minutes north<br>15 degrees 00 minutes east"));
		register(new CoordinateCluePlugin("swamp-coord", 2856, ClueLevel.HARD, new Location(3441, 3419, 0), "08 degrees 03 minutes north<br>31 degrees 16 minutes east"));
		register(new CoordinateCluePlugin("feldip-coord", 2857, ClueLevel.MEDIUM, new Location(2593, 2899, 0), "08 degrees 11 minutes south<br>4 degrees 48 minutes east"));
		register(new CoordinateCluePlugin("gnome-terribirds-coord", 2858, ClueLevel.MEDIUM, new Location(2387, 3435, 0), "08 degrees 33 minutes north<br>01 degrees 39 minutes west"));
		register(new CoordinateCluePlugin("north-gnome-terribirds-coord", 3490, ClueLevel.MEDIUM, new Location(2382, 3467, 0), "09 degrees 35 minutes north<br>01 degrees 50 minutes west"));
		register(new CoordinateCluePlugin("ice-mountain-coord", 3491, ClueLevel.MEDIUM, new Location(3006, 3475, 0), "09 degrees 48 minutes north<br>17 degrees 39 minutes east"));
		register(new CoordinateCluePlugin("coal-line-coord", 3492, ClueLevel.MEDIUM, new Location(2585, 3505, 0), "10 degrees 45 minutes north<br>04 degrees 31 minutes east"));
		register(new CoordinateCluePlugin("slayer-tower-coord", 3493, ClueLevel.MEDIUM, new Location(3442, 3515, 0), "11 degrees 03 minutes north<br>31 degrees 20 minutes east"));
		register(new CoordinateCluePlugin("gnome-stronghold-coord", 3494, ClueLevel.MEDIUM, new Location(2416, 3515, 0), "11 degrees 05 minutes north<br>00 degrees 45 minutes west"));
		register(new CoordinateCluePlugin("eagles-peak-coord", 3495, ClueLevel.MEDIUM, new Location(2362, 3531, 0), "11 degrees 33 minutes north<br>02 degrees 24 minutes west"));
		register(new CoordinateCluePlugin("burthorpe-coord", 3496, ClueLevel.MEDIUM, new Location(2920, 3534, 0), "11 degrees 41 minutes north<br>14 degrees 58 minutes east"));
		register(new CoordinateCluePlugin("fenkenstrain-castle-coord", 3497, ClueLevel.MEDIUM, new Location(3548, 3559, 0), "12 degrees 28 minutes north<br>34 degrees 37 minutes east"));
		register(new CoordinateCluePlugin("lvl-11-wildy-coord", 3498, ClueLevel.HARD, new Location(3113, 3602, 0), "13 degrees 46 minutes north<br>21 degrees 01 minutes east"));
		register(new CoordinateCluePlugin("swaying-tree-coord", 3499, ClueLevel.MEDIUM, new Location(2735, 3639, 0), "14 degrees 54 minutes north<br>9 degrees 13 minutes east"));
		register(new CoordinateCluePlugin("rellekka-coord", 3500, ClueLevel.MEDIUM, new Location(2680, 3652, 0), "15 degrees 22 minutes north<br>07 degrees 31 minutes east"));
		register(new CoordinateCluePlugin("trollheim-coord", 3501, ClueLevel.HARD, new Location(2892, 3674, 0), "16 degrees 03 minutes north<br>14 degrees 07 minutes east"));
		register(new CoordinateCluePlugin("graveyard-wildy-coord", 3502, ClueLevel.HARD, new Location(3229, 3687, 0), "16 degrees 07 minutes north<br>22 degrees 45 minutes east"));
		// not sure if this is accessible
		register(new CoordinateCluePlugin("troll-stronghold-coord", 3503, ClueLevel.HARD, new Location(2853, 3689, 0), "16 degrees 31 minutes north<br>12 degrees 54 minutes east"));
		register(new CoordinateCluePlugin("level-22-wildy-coord", 3504, ClueLevel.HARD, new Location(3304, 3692, 0), "16 degrees 35 minutes north<br>27 degrees 01 minutes east"));
		register(new CoordinateCluePlugin("bandit-camp-coord", 3505, ClueLevel.HARD, new Location(3055, 3696, 0), "16 degrees 43 minutes north<br>19 degrees 13 minutes east"));
		register(new CoordinateCluePlugin("level-23-wildy-coord", 3506, ClueLevel.HARD, new Location(3302, 3696, 0), "16 degrees 43 minutes north<br>26 degrees 56 minutes east"));
		register(new CoordinateCluePlugin("northeast-rellekka-coord", 3507, ClueLevel.HARD, new Location(2711, 3732, 0), "17 degrees 50 minutes north<br>08 degrees 30 minutes east"));
		register(new CoordinateCluePlugin("level-29-wildy-coord", 3508, ClueLevel.HARD, new Location(2970, 3749, 0), "18 degrees 22 minutes north<br>16 degrees 33 minutes east"));
		register(new CoordinateCluePlugin("level-31-wildy-coord", 3509, ClueLevel.HARD, new Location(3034, 3804, 0), "18 degrees 50 minutes north<br>20 degrees 26 minutes east"));
		register(new CoordinateCluePlugin("level-35-wildy-coord", 3510, ClueLevel.HARD, new Location(3244, 3792, 0), "19 degrees 43 minutes north<br>25 degrees 07 minutes east"));
		register(new CoordinateCluePlugin("level-36-wildy-coord", 3512, ClueLevel.HARD, new Location(3139, 3803, 0), "20 degrees 05 minutes north<br>21 degrees 52 minutes east"));
		register(new CoordinateCluePlugin("kbd-wildy-coord", 3513, ClueLevel.HARD, new Location(3014, 3847, 0), "21 degrees 24 minutes north<br>17 degrees 54 minutes east"));
		register(new CoordinateCluePlugin("chaos-temple-wildy-coord", 3514, ClueLevel.HARD, new Location(2946, 3818, 0), "20 degrees 33 minutes north<br>15 degrees 48 minutes east"));
		register(new CoordinateCluePlugin("rune-rocks-wildy-coord", 3515, ClueLevel.HARD, new Location(3059, 3884, 0), "22 degrees 35 minutes north<br>19 degrees 18 minutes east"));
		register(new CoordinateCluePlugin("demonic-ruins-wildy-coord", 3516, ClueLevel.HARD, new Location(3290, 3889, 0), "22 degrees 45 minutes north<br>26 degrees 33 minutes east"));
		register(new CoordinateCluePlugin("rogues-castle-coord", 3518, ClueLevel.HARD, new Location(3285, 3943, 0), "24 degrees 24 minutes north<br>26 degrees 24 minutes east"));
		register(new CoordinateCluePlugin("wildy-lever-coord", 3520, ClueLevel.HARD, new Location(3158, 3958, 0), "24 degrees 56 minutes north<br>22 degrees 28 minutes east"));
		register(new CoordinateCluePlugin("pirates-hideout-coord", 3522, ClueLevel.HARD, new Location(3039, 3960, 0), "24 degrees 58 minutes north<br>18 degrees 43 minutes east"));
		register(new CoordinateCluePlugin("agility-wildy-coord", 3524, ClueLevel.HARD, new Location(2987, 3964, 0), "25 degrees 03 minutes north<br>17 degrees 05 minutes east"));
		register(new CoordinateCluePlugin("axe-hut-coord", 3525, ClueLevel.HARD, new Location(3190, 3963, 0), "25 degrees 03 minutes north<br>23 degrees 24 minutes east"));
		return this;
	}

}
