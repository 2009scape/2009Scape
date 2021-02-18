package core.game.content.ttrail;

import core.game.node.entity.player.link.emote.Emotes;
import core.game.world.map.zone.ZoneBorders;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Initializes the emote clue plugins.
 * @author Vexia
 */
public final class EmoteCluePlugin extends EmoteClueScroll {

	/**
	 * Constructs a new {@Code EmoteCluePlugin} {@Code Object}
	 */
	public EmoteCluePlugin() {
		super(null, -1, null, null, null, null, null);
	}

	/**
	 * Constructs a new {@Code EmoteCluePlugin} {@Code Object}
	 * @param name the name.
	 * @param clueId the clue id.
	 * @param level the level.
	 * @param emote the Emotes.
	 * @param commenceEmote the commence Emotes.
	 * @param equipment the equipment.
	 * @param clue the clue.
	 * @param borders the borders.
	 */
	public EmoteCluePlugin(String name, int clueId, ClueLevel level, Emotes emote, Emotes commenceEmote, int[][] equipment, String clue, ZoneBorders... borders) {
		super(name, clueId, level, emote, commenceEmote, equipment, clue, borders);
	}

	/**
	 * Constructs a new {@Code EmoteCluePlugin} {@Code Object}
	 * @param name the name.
	 * @param clueId the clue id.
	 * @param level the level.
	 * @param emote the Emotes.
	 * @param equipment the equipment.
	 * @param clue the clue.
	 * @param borders the borders.
	 */
	public EmoteCluePlugin(String name, int clueId, ClueLevel level, Emotes emote, int[][] equipment, String clue, ZoneBorders... borders) {
		super(name, clueId, level, emote, null, equipment, clue, borders);
	}

	// register(new EmoteCluePlugin(clue, clueId, level, commenceEmote,
	// commenceEmote, equipment, clue, borders));
	// register(new EmoteCluePlugin(clue, clueId, level, commenceEmote,
	// equipment, clue, borders));
	// register(new EmoteCluePlugin());

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Emotes emote = Emotes.BECKON;
		register(new EmoteCluePlugin("digsite-beckon", 2677, ClueLevel.MEDIUM, emote, Emotes.BOW, new int[][] { { 6328 }, { 1267 }, { 658 } }, "Beckon in the Digsite, near the<br>eastern winch. Bow or curtsy<br>before you talk to me.<br>Equip a green gnome hat,<br>snakeskin boots and an<br>iron pickaxe.", new ZoneBorders(3368, 3425, 3371, 3429)));
		register(new EmoteCluePlugin("tai-bwo-beckon", 2678, ClueLevel.MEDIUM, emote, Emotes.CLAP, new int[][] { { 1099 }, { 2552, 2554, 2556, 2558, 2560, 2562, 2564, 2568 }, { 1143 } }, "Beckon in Tai Bwo<br>Wannai. Clap before<br>you talk to me.<br>Equip green<br>dragonhide chaps, a<br>ring of dueling and a<br>mithril medium helmet.", new ZoneBorders(2771, 3053, 2814, 3074)));
		emote = Emotes.BLOW_KISS;
		register(new EmoteCluePlugin("shilo-kiss", 2681, ClueLevel.HARD, emote, new int[][] { { 4089 }, { 5016 }, { 1127 } }, "Blow a kiss between<br>the tables in Shilo<br>Village bank. Beware<br>of double agents!<br>Equip a blue mystic<br>hat, bone spear and<br>rune plate body.", new ZoneBorders(2849, 2950, 2855, 2957)));
		emote = Emotes.BOW;
		register(new EmoteCluePlugin("legends-guild", 2682, ClueLevel.EASY, emote, new int[][] { { 1067 }, { 1696 }, { 845 } }, "Bow outside the entrance<br>to the Legends' Guild.<br>Equip iron platelegs, an<br>emerald amulet and an oak<br>longbow.", new ZoneBorders(2726, 3346, 2731, 3349)));
		emote = Emotes.RASPBERRY;
		register(new EmoteCluePlugin("monkey-cage", 2679, ClueLevel.EASY, emote, new int[][] { { 1133 }, { 1075 }, { 1379 } }, "Blow a raspberry at the<br>monkey cage in<br>Ardougne Zoo.<br>Equip a studded<br>leather body, bronze<br>platelegs and a normal<br>staff with no orb.", new ZoneBorders(2597, 3274, 2609, 3284)));
		register(new EmoteCluePlugin("keep-le-faye", 2680, ClueLevel.EASY, emote, new int[][] { { 1169 }, { 1115 }, { 1059 } }, "Blow raspberries out<br>the entrance to Keep Le<br>Faye.<br>Equip a coif, an iron<br>platebody and leather<br>gloves.", new ZoneBorders(2759, 3401, 2762, 3403)));
		// register(new EmoteCluePlugin("fishing-guild-blow", 2683,
		// ClueLevel.HARD, emote, new int[][] {{2890}, {2493}, {1347}},
		// "Blow a raspberry in the<br>Fighing Guild bank. Beware<br>of double agents!<br>Equip and elemental shield<br>,blue dragonhide chaps<br>and a rune warhammer.",
		// new ZoneBorders(2586, 3418, 2590, 3422)));
		emote = Emotes.CHEER;
		register(new EmoteCluePlugin("druid-circle-cheer", 2684, ClueLevel.EASY, emote, new int[][] { { 4310 }, { 579 }, { 1307 } }, "Cheer at the Druids'<br>Circle.<br>Equip a blue wizard<br>hat, a bronze<br>two-handed sword and<br>HAM boots.", new ZoneBorders(2920, 3478, 2930, 3940)));
		register(new EmoteCluePlugin("games-room-cheer", 2685, ClueLevel.EASY, emote, new int[][] {}, "Cheer at the games<br>room.<br>Have nothing equipped<br>at all when you do.", new ZoneBorders(2194, 4946, 2221, 4973)));
		register(new EmoteCluePlugin("barb-agility-cheer", 2686, ClueLevel.MEDIUM, emote, new int[][] { { 1119 }, { 853 }, { 4315, 4316, 4317, 4318, 4319, 4320, 4321, 4322, 4323, 4324, 4325, 4326, 4327, 4328, 4329, 4330, 4331, 4332, 4333, 4334, 4335, 4336, 4337, 4338, 4339, 4340, 4341, 4342, 4343, 4344, 4345, 4346, 4347, 4348, 4349, 4350, 4351, 4352, 4353, 4354, 4355, 4356, 4357, 4358, 4359, 4360, 4361, 4362, 4363, 4364, 4365, 4366, 4367, 4368, 4369, 4370, 4371, 4372, 4373, 4374, 4375, 4376, 4377, 4378, 4379, 4380, 4381, 4382, 4383, 4384, 4385, 4386, 4387, 4388, 4389, 4390, 4391, 4392, 4393, 4394, 4395, 4396, 4397, 4398, 4399, 4400, 4401, 4402, 4403, 4404, 4405, 4406, 4407, 4408, 4409, 4410, 4411, 4412, 4413, 4414, 10638 } }, "Cheer in the Barbarian<br>Agility Arena.<br>Headbang before you<br>talk to me.<br>Equip a steel plate<br>body, maple shortbow, and a wilderness<br>cape.", new ZoneBorders(2550, 3556, 2553, 3559), new ZoneBorders(2529, 3542, 2553, 3559)));
		register(new EmoteCluePlugin("edge-gen-cheer", 2687, ClueLevel.MEDIUM, emote, Emotes.DANCE, new int[][] { { 1757 }, { 1061 }, { 1059 } }, "Cheer in the Edgeville general<br>store. Dance<br>before you talk to me.<br>Equip a brown apron,<br>leather boots and<br>leather gloves.", new ZoneBorders(3076, 3507, 3084, 3513)));
		register(new EmoteCluePlugin("ogre-pen-cheer", 2688, ClueLevel.MEDIUM, emote, Emotes.ANGRY, new int[][] { { 1135 }, { 1099 }, { 1177 } }, "Cheer in the Ogre Pen<br>in the Training Camp.<br>Show you are angry<br>before you talk to me.<br>Equip a green<br>dragonhide body and<br>chaps and a steel<br>square shield.", new ZoneBorders(2523, 3373, 2533, 3377)));
		emote = Emotes.CLAP;
		register(new EmoteCluePlugin("exam-room-clap", 2689, ClueLevel.EASY, emote, new int[][] { { 1005 }, { 628 }, { 1059 } }, "Clap in the main exam room<br>in the Exam Centre.<br>Equip a white apron, green<br>gnome boots and leather<br>gloves.", new ZoneBorders(3357, 3332, 3367, 3348)));
		register(new EmoteCluePlugin("wizard-tower-clap", 2690, ClueLevel.EASY, emote, new int[][] { { 1137 }, { 1639 }, { 1005 } }, "Clap on the causeway to the<br>Wizards' Tower.<br>Equip an iron medium<br>helmet, emerald ring and a<br>white apron.", new ZoneBorders(3112, 3173, 3115, 3206)));
		// 2nd plane for ardy mill
		register(new EmoteCluePlugin("ardgoune-mill-clap", 2691, ClueLevel.EASY, emote, new int[][] { { 640 }, { 4300 }, { 5525 } }, "Clap on the top level of the<br>mill, north of East Ardougne.<br>Equip a blue gnome robe top,<br>HAM robe bottom and an<br>unenchanted tiara.", new ZoneBorders(2628, 3382, 2636, 3390, 2)));
		register(new EmoteCluePlugin("seers-court-clap", 2692, ClueLevel.MEDIUM, emote, Emotes.SPIN, new int[][] { { 3200 }, { 4093 }, { 1643 } }, "Clap in Seers court house.<br>Spin before you talk to me.<br>Equip an adamant halberd,<br>blue mystic robe bottom and<br>a diamond ring.", new ZoneBorders(2732, 3467, 2739, 3471)));
		//Fix clue remove vexia
		//emote = Emotes.CRY;
		register(new EmoteCluePlugin("catherby-range-cry", 2693, ClueLevel.MEDIUM, Emotes.CRY, Emotes.BOW, new int[][] { { 630 }, { 1131 }, { 2961 } }, "Cry in the Catherby<br>Ranging shop. Bow before you talk to me.<br>Equip blue gnome<br>boots, a hard leather<br>body and an<br>unblessed silver<br>sickle.", new ZoneBorders(2821, 3441, 2825, 3445)));
		register(new EmoteCluePlugin("catherby-shore-cry", 2694, ClueLevel.MEDIUM, Emotes.CRY, Emotes.LAUGH, new int[][] { { 1183 }, { 8872 }, { 1121 } }, "Cry on the shore of<br>Catherby beach.<br>Laugh before you talk to me.<br>Equip an adamant sq<br>shield, a bone dagger<br>and mithril platebody.", new ZoneBorders(2849, 3423, 2857, 3430), new ZoneBorders(2845, 3428, 2852, 3430)));
		emote = Emotes.DANCE;
		register(new EmoteCluePlugin("draynor-cross-dance", 2695, ClueLevel.EASY, emote, new int[][] { { 1101 }, { 1637 }, { 839 } }, "Dance at the<br>crossroads north of<br>Draynor.<br>Equip an iron chain<br>body, a sapphire ring<br>and a longbow.", new ZoneBorders(3109, 3293, 3110, 3296), new ZoneBorders(3108, 3294, 3111, 3295)));
		register(new EmoteCluePlugin("fally-party-dance", 2696, ClueLevel.EASY, emote, new int[][] { { 1157 }, { 1119 }, { 1081 } }, "Dance in the Party<br>Room.<br>Equip a steel full<br>helmet, steel<br>platebody and an iron<br>plateskirt.", new ZoneBorders(3041, 3372, 3050, 3384), new ZoneBorders(3051, 3371, 3054, 3385), new ZoneBorders(3073, 3371, 3040, 3385)));
		register(new EmoteCluePlugin("fish-guild-jig", 2697, ClueLevel.EASY, Emotes.JIG, new int[][] { { 1639 }, { 1694 }, { 1103 } }, "Dance a jig by the<br>entrance to the<br>Fishing Guild.<br>Equip an emerald ring,<br>a sapphire amulet,<br>and a bronze chain<br>body.", new ZoneBorders(2610, 3392, 2612, 3393)));
		register(new EmoteCluePlugin("lumb-shack-dance", 2698, ClueLevel.EASY, emote, new int[][] { { 1205 }, { 1153 }, { 1635 } }, "Dance in<br>the shack in Lumbridge Swamp.<br>Equip a bronze<br>dagger, iron full helmet<br>and a gold ring.", new ZoneBorders(3202, 3167, 3205, 3170)));
		register(new EmoteCluePlugin("lumb-cave-dance", 2699, ClueLevel.MEDIUM, emote, Emotes.BLOW_KISS, new int[][] { { 1381 }, { 1155 }, { 1731 } }, "Dance in the dark<br>cave beneath<br>Lumbridge Swamp.<br>Blow a kiss before<br>you talk to me.<br>Equip an air staff,<br>bronze full helm and<br>an amulet of power.", new ZoneBorders(3139, 9534, 3260, 9587)));
		register(new EmoteCluePlugin("shantay-guild-jig", 2700, ClueLevel.MEDIUM, Emotes.JIG, Emotes.BOW, new int[][] { { 3343 }, { 1381 }, { 1173 } }, "Dance a jig under<br>Shantay's Awning.<br>Bow before you talk to<br>me.<br>Equip a pointed blue<br>snail helmet, an air<br>staff and a bronze<br>square shield.", new ZoneBorders(3302, 3122, 3305, 3125)));
		register(new EmoteCluePlugin("cat-entrance-dance", 2701, ClueLevel.HARD, emote, new int[][] { { 2570 }, { 1704 }, { 1317 } }, "Dance at the<br>cat doored pyramid in<br>Sophanem. Beware of double agents!<br>Equip a ring of life,<br>an uncharged amulet<br>of glory and an adamant two handed<br>sword.", new ZoneBorders(3293, 2781, 3296, 2782)));
		emote = Emotes.HEADBANG;
		register(new EmoteCluePlugin("al-kharid-headbang", 2702, ClueLevel.EASY, emote, new int[][] { { 1833 }, { 1059 }, { 1061 } }, "Headbang in the mine north of Al<br>Kharid.<br>Equip a desert shirt, leather gloves and<br>leather boots.", new ZoneBorders(3297, 3286, 3301, 3316)));
		//Removed Vexia code that bricked emotes for certain clues.
		//Fixed JFJ and Laugh Clues
		//When Vexia emote is removed below.
		//emote = Emotes.JUMP_FOR_JOY;
		register(new EmoteCluePlugin("beehive-jump", 2703, ClueLevel.EASY, Emotes.JUMP_FOR_JOY, new int[][] { { 1833 }, { 648 }, { 1353 } }, "Jump for joy at the beehives.<br>Equip a desert shirt, green<br>gnome robe bottoms and a<br>steel axe.", new ZoneBorders(2762, 3439, 2764, 3441)));
		// And emote, is replaced with what it is shortcutting Emotes.JUMP_FOR_JOY
		// the clues work perfect. It seems to only be a problem with multiple emote clues.
		// If you look around EmoteCluePlugin you will see there are multiple emote, shortcuts
		// I do not know what should be done as there are so many.
		register(new EmoteCluePlugin("yanille-jump", 2704, ClueLevel.MEDIUM, Emotes.JUMP_FOR_JOY, Emotes.JIG, new int[][] { { 1757 }, { 1145 }, { 6324 } }, "Jump for joy in Yanille<br>bank. Dance a jig before you<br>talk to me.<br>Equip a brown apron,<br>adamantite medium helmet<br>and snakeskin chaps.", new ZoneBorders(2609, 3088, 2614, 3097)));
		register(new EmoteCluePlugin("tzhaar-jump", 2705, ClueLevel.MEDIUM, Emotes.JUMP_FOR_JOY, Emotes.SHRUG, new int[][] { { 1295 }, { 2499 }, { 4095 } }, "Jump for joy in the TzHaar<br>sword shop. Shrug before you<br>talk to me.<br>Equip a Steel longsword,<br>Blue D'hide body and blue<br>mystic gloves.", new ZoneBorders(2477, 5144, 2480, 5147)));
		//Removed Vexia code that bricked emotes for certain clues.
		register(new EmoteCluePlugin("jokul-tent-laugh", 2706, ClueLevel.HARD, Emotes.LAUGH, new int[][] { { 1163 }, { 2493 }, { 1393 } }, "Laugh in the Jokul's tent in the<br>Mountain Camp.<br>Beware of double agents! Equip a<br>rune full helmet, blue dragonhide<br>chaps and a fire battlestaff.", new ZoneBorders(2811, 3678, 2813, 3682)));
		emote = Emotes.PANIC;
		register(new EmoteCluePlugin("limestone-mine-panic", 2707, ClueLevel.EASY, emote, new int[][] { { 1075 }, { 1269 }, { 1141 } }, "Panic in the<br>Limestone Mine.<br>Equip bronze<br>platelegs, a steel<br>pickaxe and a steel<br>medium helmet.", new ZoneBorders(3368, 3496, 3374, 3505)));
		register(new EmoteCluePlugin("fish-trawler-panic", 2708, ClueLevel.EASY, emote, new int[][] {}, "Panic on the pier<br>where you catch the<br>Fishing trawler.<br>Have nothing equipped<br>at all when you do.", new ZoneBorders(2675, 3162, 2677, 3175)));
		emote = Emotes.SALUTE;
		// vexia make banana saulte so it won't work if u are wearing anything
		// on chest or legs
		register(new EmoteCluePlugin("banana-salute", 2710, ClueLevel.HARD, emote, new int[][] { { 1643 }, { 1731 } }, "Salute in the banana<br>plantation. Beware of<br>double agents!<br>Equip a diamond ring,<br>amulet of power, and nothing on<br>your chest and legs.", new ZoneBorders(2911, 3156, 2935, 3175)));
		emote = Emotes.SHRUG;
		register(new EmoteCluePlugin("rimmington-shrug", 2711, ClueLevel.EASY, emote, new int[][] { { 1654 }, { 1635 }, { 1237 } }, "Shrug in the mine near<br>Rimmington.<br>Equip a gold<br>necklace, a gold ring<br>and a bronze spear.", new ZoneBorders(2970, 3230, 2987, 3251)));
		register(new EmoteCluePlugin("catherby-shrug", 2712, ClueLevel.MEDIUM, emote, Emotes.YAWN, new int[][] { { 851 }, { 1099 }, { 1137 } }, "Shrug in Catherby<br>bank. Yawn before<br>you talk to me.<br>Equip a maple<br>longbow, green d'hide<br>chaps and an iron<br>med helm.", new ZoneBorders(2806, 3438, 2812, 3442)));
		register(new EmoteCluePlugin("zammy-altar-shrug", 2713, ClueLevel.HARD, emote, new int[][] { { 1079 }, { 1115 }, { 2487 } }, "Shrug in the Zamorak<br>temple found in the<br>Eastern Wilderness.<br>Beware of double<br>agents!<br>Equip rune plate legs,<br>an iron plate body and<br>blue dragonhide<br>vambraces.", new ZoneBorders(3233, 3603, 3246, 3614)));
		emote = Emotes.SPIN;
		register(new EmoteCluePlugin("rimmington-cross-spin", 2716, ClueLevel.EASY, emote, new int[][] { { 658 }, { 642 }, { 1095 } }, "Spin at the crossroads<br>north of Rimmington.<br>Equip a green gnome<br>hat, cream gnome top<br>and leather chaps.", new ZoneBorders(2979, 3275, 2985, 3279)));
		register(new EmoteCluePlugin("draynor-manor-spin", 2719, ClueLevel.EASY, emote, new int[][] { { 1115 }, { 1097 }, { 1155 } }, "Spin in the Draynor<br>Manor by the fountain.<br>Equip an iron<br>platebody, studded<br>leather chaps and a<br>bronze full helmet.", new ZoneBorders(3085, 3332, 3090, 3337)));
		register(new EmoteCluePlugin("varrock-court-spin", 2722, ClueLevel.EASY, emote, new int[][] { { 1361 }, { 1169 }, { 1641 } }, "Spin in the Varrock<br>Castle courtyard.<br>Equip a black axe, a<br>coif and a ruby ring.", new ZoneBorders(3202, 3459, 3223, 3468)));
		register(new EmoteCluePlugin("barb-bridge-spin", 2723, ClueLevel.MEDIUM, emote, Emotes.SALUTE, new int[][] { { 2942 }, { 1193 }, { 1159 } }, "Spin on the bridge by<br>the Barbarian Village.<br>Salute before you talk<br>to me.<br>Equip purple gloves, a<br>steel kiteshield and a<br>mithril full helmet.", new ZoneBorders(3102, 3419, 3108, 3422)));
		emote = Emotes.THINK;
		register(new EmoteCluePlugin("lumb-wheat-think", 2725, ClueLevel.EASY, emote, new int[][] { { 640 }, { 654 }, { 843 } }, "Think in middle of the<br>wheat field by the<br>lumbridge mill.<br>Equip a blue gnome<br> robetop, a turquoise<br> gnome robe bottom<br>and an oak shortbow.", new ZoneBorders(3157, 3298, 3159, 3300)));
		// register(new EmoteCluePlugin("observatory-think", 2727,
		// ClueLevel.MEDIUM, emote, Emotes.SPIN, new int[][] {{1109}, {1099},
		// {1698}},
		// "Think in the centre of<br>the Observatory. Spin<br>before you talk to me.<br>Equip a mithril chain<br>body, green<br>dragonhide chaps and<br>a ruby amulet.",
		// new ZoneBorders(2439, 3160, 2442, 3163)));
		emote = Emotes.WAVE;
		register(new EmoteCluePlugin("lumber-wave", 2729, ClueLevel.EASY, emote, new int[][] { { 1131 }, { 1095 }, { 1351 } }, "Wave along the south fence of<br>the Lumber Yard.<br>Equip a hard leather body,<br>leather chaps and a<br>bronze axe.", new ZoneBorders(3306, 3490, 3309, 3492)));
		register(new EmoteCluePlugin("fally-gem-wave", 2731, ClueLevel.EASY, emote, new int[][] { { 1273 }, { 1125 }, { 1191 } }, "Wave in the Falador<br>gem store.<br>Equip a Mithril pickaxe,<br>Black platebody and<br>an Iron Kiteshield.", new ZoneBorders(2944, 3332, 2946, 3337)));
		register(new EmoteCluePlugin("mudskipper-wave", 2733, ClueLevel.EASY, emote, new int[][] { { 1019 }, { 1095 }, { 1424 } }, "Wave on Mudskipper Point.<br>Equip a black cape, leather<br>chaps and a steel mace.", new ZoneBorders(2979, 3105, 3008, 3115)));
		emote = Emotes.YAWN;
		register(new EmoteCluePlugin("varrock-library-yawn", 2735, ClueLevel.EASY, emote, new int[][] { { 638 }, { 4300 }, { 1335 } }, "Yawn in the Varrock<br>library.<br>Equip a green gnome<br>robe top, HAM robe<br>bottom and an iron<br>warhammer.", new ZoneBorders(3207, 3490, 3214, 3497), new ZoneBorders(3214, 3494, 3217, 3497)));
		register(new EmoteCluePlugin("draynor-market-yawn", 2737, ClueLevel.EASY, emote, new int[][] { { 1097 }, { 1191 }, { 1295 } }, "Yawn in Draynor<br>Marketplace.<br>Equip studded leather<br>chaps, an iron<br>kiteshield and a steel<br>longsword.", new ZoneBorders(3075, 3245, 3086, 3255)));
		register(new EmoteCluePlugin("castle-wars-yawn", 2739, ClueLevel.MEDIUM, emote, Emotes.SHRUG, new int[][] { { 1698 }, { 1329 }, WILDY_CAPES }, "Yawn in the Castle<br>Wars lobby. Shrug<br>before you talk to me.<br>Equip ruby amulet, a<br>mithril scimitar and a<br>Wilderness cape.", new ZoneBorders(2434, 3061, 2464, 3102)));
		register(new EmoteCluePlugin("rogue-gen-yawn", 2741, ClueLevel.HARD, emote, new int[][] { { 1183 }, { 2487 }, { 1275 } }, "Yawn in the rogues'<br>general store. Beware<br>of double agents!<br>Equip an adamant<br>square shield, blue<br>dragon vambraces<br>and a rune pickaxe.", new ZoneBorders(3024, 3699, 3027, 3704)));
		PluginManager.definePlugin(new UriNPC());
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

}
