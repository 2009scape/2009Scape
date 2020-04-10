package emperor;

import com.alex.loaders.clientscripts.CS2Mapping;
import com.alex.store.Store;


public final class MusicPropertiesPacker {

	/**
	 * The music zones.
	 */

	/**
	 * Configures the music data.
	 */
	private static void configureMusic() {
		//Don't remove anything from this!
		//Also make sure zones don't overlap!
		add(40, "brew hoo hoo!", 338, (14747));
		add(53, "chef surprise", 399, (7507));
		add(76, "davy jones' locker", 394, (11924));
		add(102, "etceteria", 227, (10300));
		add(156, "hells bells", 254, (11066));
		add(175, "jolly-r", 65, (11058));
		add(189, "land of the dwarves", 310, (11423));
		add(205, "mad eadgar", 213, (11677));
		add(254, "pharaoh's tomb", 355, (13356), (12105));
		add(259, "pirates of peril", 262, (12093));
		add(304, "spirits of elid", 331, (13461));
		add(318, "subterranea", 362, (10142));
		add(322, "tale of keldagrim", 309, (11678));
		add(323, "talking forest", 119, (10550));
		add(328, "cellar dwellers", 342, (10135));
		add(329, "chosen", 324, (9805));
		add(330, "desert", 120, (12591));
		add(331, "desolate isle", 330, (10042));
		add(333, "far side", 314, (12111));
		add(335, "genie", 332, (13457));
		add(336, "the golem", 295, (13616), (13872));
		add(338, "the lost melody", 315, (13206));
		add(341, "the mad mole", 393, (6992));
		add(342, "monsters below", 329, (9886));
		add(343, "the navigator", 255, (10652));
		add(345, "other side", 278, (14646));
		add(347, "quiz master", 318, (7754));
		add(348, "rogues' den", 313, (11853), (12109));
		add(349, "shadow", 121, (11314));
		add(350, "the slayer", 269, (11164));
		add(351, "terrible tower", 267, (13623));
		add(352, "tower", 122, (10292), (10136));
		add(360, "tomorrow", 163, (12081));
		add(361, "too many cooks...", 398, (11930));
		add(413, "zogre dance", 306, (9775));
		add(2, "adventure", 0, (12854));
		add(5, "alone", 2, (12086), (10134));
		add(6, "ambient jungle", 3, (11310));
		add(7, "anywhere", 240, (10795));
		add(11, "arabique", 7, (11417));
		add(12, "army of darkness", 8, (12088));
		add(13, "arrival", 9, (11572));
		add(14, "artistry", 200, (8010));
		add(15, "attack 1", 10, (10034));
		add(16, "attack 2", 11, (11414));
		add(17, "attack 3", 12, (12192));
		add(18, "attack 4", 13, (10289), (10389));
		add(19, "attack 5", 14, (9033));
		add(20, "attack 6", 15, (10387));
		add(21, "attention", 16, (11825));
		add(22, "autumn voyage", 17, (12851));
		add(23, "aye car rum ba", 351, (8527));
		add(24, "aztec", 201, (11157));
		add(25, "background", 18, (11060), (7758));
		add(26, "ballad of enchantment", 19, (10290));
		add(27, "bandit camp", 214, (12590));
		add(28, "barbarianism", 257, (12341), (12441));
		add(29, "barking mad", 274, (14234));
		add(30, "baroque", 20, (10547));
		add(31, "beyond", 21, (11418), (11419));
		add(32, "big chords", 22, (10032));
		add(33, "blistering barnacles", 352, (8528));
		add(34, "body parts", 270, (13979));
		add(35, "bone dance", 183, (13619));
		add(36, "bone dry", 216, (12946));
		add(37, "book of spells", 23, (12593));
		add(38, "borderland", 233, (10809));
		add(39, "breeze", 194, (9010));
		add(42, "bubble and squeak", 347, (7753));
		add(43, "camelot", 24, (11062));
		add(44, "castlewars", 247, (9520));
		add(45, "catch me if you can", 344, (10646));
		add(46, "cave background", 25, (12184), (11929));
		add(47, "cave of beasts", 280, (11165));
		add(48, "cave of the goblins", 304, (12693));
		add(49, "cavern", 26, (12193), (10388));
		add(50, "cellar song", 173, (12697));
		add(51, "chain of command", 27, (10648), (10905));
		add(52, "chamber", 225, (10821), (11078));
		add(54, "chickened out", 395, (9796));
		add(55, "chompy hunt", 178, (10542), (10642));
		add(56, "city of the dead", 300, (12843), (13099));
		add(57, "claustrophobia", 291, (9293));
		add(58, "close quarters", 175, (12602));
		add(59, "competition", 217, (8781));
		add(60, "complication", 258, (9035));
		add(61, "contest", 208, (11576));
		add(62, "corporal punishment", 323, (12619));
		add(64, "courage", 260, (11673));
		add(65, "crystal castle", 210, (9011));
		add(66, "crystal cave", 28, (9797));
		add(67, "crystal sword", 29, (12855), (10647));
		add(68, "cursed", 186, (9623));
		add(69, "dagannoth dawn", 365, (7236), (7748));
		add(71, "dance of the undead", 298, (14131));
		add(72, "dangerous road", 263, (11413));
		add(73, "dangerous way", 299, (14231));
		add(74, "dangerous", 30, (12343), (13115));
		add(75, "dark", 31, (13113));
		add(77, "dead can dance", 341, (12601));
		add(78, "dead quiet", 181, (13621), (9294));
		add(79, "deadlands", 230, (14134));
		add(80, "deep down", 224, (10823), (10822));
		add(81, "deep wildy", 32, (11835));
		add(82, "desert heat", 333, (13614));
		add(83, "desert voyage", 33, (13102), (13359));
		add(84, "diango's little helpers", 371, (8005));
		add(86, "distant land", 353, (13873));
		add(89, "doorways", 34, (12598));
		add(90, "down below", 284, (12438));
		add(91, "down to earth", 259, (10571));
		add(92, "dragontooth island", 281, (15159));
		add(93, "dream", 35, (12594));
		add(95, "dunjun", 36, (11672));
		add(96, "dynasty", 275, (13358));
		add(98, "elven mist", 202, (9266));
		add(99, "emotion", 38, (10033), (10309), (10133));
		add(100, "emperor", 39, (11570), (11670));
		add(101, "escape", 176, (10903));
		add(103, "everlasting fire", 417, (13373));
		add(104, "everywhere", 219, (8499));
		add(105, "evil bob's island", 316, (10058));
		add(106, "expanse", 40, (12605), (12852), (12952));
		add(107, "expecting", 41, (9778), (9878));
		add(108, "expedition", 42, (11676));
		add(109, "exposed", 220, (8752));
		add(110, "faerie", 43, (9540));
		add(111, "faithless", 265, (12856));
		add(112, "fanfare", 44, (11828));
		add(113, "fanfare 2", 162, (11823));
		add(114, "fanfare 3", 45, (10545));
		add(116, "far away", 292, (9265));
		add(118, "fenkenstrain's refrain", 271, (13879));
		add(119, "fight or flight", 293, (7752));
		add(120, "find my way", 246, (10894));
		add(121, "fire and brimstone", 334, (9552));
		add(122, "fishing", 46, (11317));
		add(123, "flute salad", 47, (12595));
		add(125, "forbidden", 185, (13111));
		add(126, "forest", 203, (9009));
		add(127, "forever", 48, (12342), (12442));
		add(130, "frogland", 336, (9802));
		add(131, "frostbite", 236, (11323));
		add(132, "fruits de mer", 273, (11059));
		add(133, "funny bunnies", 406, (9810));
		add(134, "gaol", 49, (12090), (10031), (10131));
		add(135, "garden", 50, (12853));
		add(136, "gnome king", 51, (9782));
		add(138, "gnome village", 53, (9781));
		add(139, "gnome village 2", 54, (9269));
		add(141, "gnomeball", 56, (9270));
		add(142, "goblin game", 252, (10393));
		add(144, "greatness", 57, (12596));
		add(146, "grotto", 198, (13720));
		add(148, "grumpy", 177, (10286));
		add(151, "harmony 2", 167, (12950));
		add(152, "haunted mine", 222, (11077));
		add(153, "have a blast", 325, (7757));
		add(155, "heart and mind", 174, (10059));
		add(157, "hermit", 191, (9034));
		add(158, "high seas", 59, (11057));
		add(159, "horizon", 60, (11573));
		add(161, "iban", 61, (8519));
		add(162, "ice melody", 165, (11318));
		add(163, "in between", 290, (10061));
		add(164, "in the brine", 370, (14638));
		add(165, "in the clink", 360, (8261));
		add(166, "in the manor", 62, (10287));
		add(167, "in the pits", 335, (9808));
		add(169, "insect queen", 212, (13972));
		add(170, "inspiration", 63, (12087));
		add(171, "into the abyss", 317, (12107));
		add(172, "intrepid", 64, (9369));
		add(173, "island life", 242, (10794));
		add(176, "jungle island", 66, (11313), (11309));
		add(177, "jungle troubles", 343, (11568));
		add(178, "jungly 1", 67, (11054), (11154));
		add(179, "jungly 2", 68, (10802));
		add(180, "jungly 3", 69, (11055));
		add(182, "kingdom", 190, (11319));
		add(183, "knightly", 70, (10291));
		add(184, "la mort", 192, (8779));
		add(185, "lair", 229, (13975));
		add(187, "lament", 381, (12433));
		add(190, "landlubber", 169, (10801));
		add(192, "lasting", 71, (10549));
		add(193, "legend", 235, (10808));
		add(194, "legion", 72, (12089), (10039));
		add(196, "lighthouse", 251, (10040));
		add(197, "lightness", 73, (12599));
		add(198, "lightwalk", 74, (11061));
		add(200, "lonesome", 149, (13203));
		add(201, "long ago", 75, (10544));
		add(202, "long way home", 76, (11826));
		add(203, "lost soul", 204, (9008));
		add(204, "lullaby", 77, (13365), (10551));
		add(206, "mage arena", 78, (12349), (10057));
		add(207, "magic dance", 79, (10288));
		add(208, "magical journey", 80, (10805));
		add(209, "making waves", 378, (9273), (9272));
		add(211, "march", 81, (10036));
		add(212, "marooned", 241, (11562), (12117));
		add(213, "marzipan", 211, (11166), (11421));
		add(214, "masquerade", 268, (10908));
		add(216, "mausoleum", 184, (13722));
		add(218, "medieval", 82, (13109));
		add(219, "mellow", 83, (10293));
		add(220, "melodrama", 248, (9776));
		add(221, "meridian", 205, (8497));
		add(223, "miles away", 84, (11571), (10569));
		add(225, "miracle dance", 85, (11083));
		add(226, "mirage", 303, (13199));
		add(227, "miscellania", 226, (10044));
		add(228, "monarch waltz", 86, (10807));
		add(229, "monkey madness", 239, (11051));
		add(230, "monster melee", 272, (12694));
		add(231, "moody", 87, (12600), (9523));
		add(232, "morytania", 180, (13622));
		add(233, "mudskipper melody", 361, (11824));
		add(234, "narnode's theme", 513, (9882));
		add(235, "natural", 197, (13620), (9038));
		add(236, "neverland", 88, (9780));
		add(239, "nightfall", 90, (12861), (11827));
		add(241, "no way out", 403, (13209), (12369), (12113));
		add(242, "nomad", 171, (11056));
		add(243, "null and void", 400, (10537));
		add(245, "oriental", 91, (11666));
		add(246, "out of the deep", 253, (10140));
		add(247, "over to nardah", 328, (13613));
		add(248, "overpass", 207, (9267));
		add(249, "overture", 92, (10806));
		add(250, "parade", 93, (13110));
		add(251, "path of peril", 307, (10575));
		add(253, "pest control", 401, (10536));
		add(255, "phasmatys", 277, (14746));
		add(256, "pheasant peasant", 321, (10314));
		add(258, "principality", 188, (11575));
		add(260, "quest", 94, (10315));
		add(261, "rat a tat tat", 345, (11599));
		add(262, "rat hunt", 349, (11343));
		add(263, "ready for battle", 249, (9620));
		add(264, "regal", 95, (13117));
		add(265, "reggae", 96, (11565));
		add(266, "reggae 2", 97, (11567));
		add(267, "rellekka", 231, (10297));
		add(269, "righteousness", 223, (9803));
		add(270, "riverside", 98, (10803), (8496));
		add(272, "romancing the crone", 264, (11068));
		add(273, "romper chomper", 312, (9263));
		add(274, "royale", 99, (11671));
		add(275, "rune essence", 100, (11595));
		add(276, "sad meadow", 101, (10035), (11081));
		add(277, "saga", 232, (10296));
		add(278, "sarcophagus", 283, (12945));
		add(279, "sarim's vermin", 348, (11926));
		add(280, "scape cave", 102, (12698), (12437));
		add(283, "scape sad", 104, (13116));
		add(286, "scape soft", 159, (11829));
		add(287, "scape wild", 105, (12857), (12604));
		add(288, "scarab", 282, (12589));
		add(290, "sea shanty", 106, (11569));
		add(289, "sea shanty 2", 107, (12082));
		add(291, "serenade", 108, (9521));
		add(292, "serene", 109, (11837), (11936), (11339));
		add(293, "settlement", 279, (11065));
		add(294, "shadowland", 228, (13618), (13875), (8526));
		add(296, "shining", 160, (12858));
		add(297, "shipwrecked", 276, (14391));
		add(298, "showdown", 245, (10895));
		add(300, "sojourn", 209, (11321));
		add(301, "soundscape", 111, (9774));
		add(302, "sphinx", 302, (13100));
		add(303, "spirit", 112, (12597));
		add(305, "splendour", 113, (11574));
		add(306, "spooky jungle", 115, (11053), (11668));
		add(307, "spooky", 114, (12340));
		add(308, "spooky 2", 218, (13718));
		add(309, "stagnant", 193, (13876), (8782));
		add(310, "starlight", 116, (11925), (12949));
		add(311, "start", 117, (12339));
		add(312, "still night", 118, (13108));
		add(313, "stillness", 250, (13977));
		add(314, "stranded", 234, (11322));
		add(316, "stratosphere", 195, (8523));
		add(319, "sunburn", 215, (12846), (13357));
		add(320, "superstition", 261, (11153));
		add(324, "tears of guthix", 311, (12948));
		add(325, "technology", 238, (10310));
		add(326, "temple of light", 294, (7496));
		add(327, "temple", 243, (11151));
		add(353, "theme", 123, (10294), (10138));
		add(355, "time out", 196, (11591));
		add(356, "time to mine", 289, (11422));
		add(357, "tiptoe", 266, (12440));
//		add(358, "title fight", 367, (12696));
		add(362, "trawler minor", 125, (7755));
		add(363, "trawler", 124, (7499));
		add(364, "tree spirits", 126, (9268));
		add(365, "tremble", 189, (11320));
		add(367, "tribal background", 127, (11312), (11412));
		add(368, "tribal", 128, (11311));
		add(366, "tribal 2", 129, (11566));
		add(369, "trinity", 130, (10804), (10904));
		add(371, "troubled", 131, (11833));
		add(372, "twilight", 179, (10906));
		add(373, "tzhaar!", 339, (9551));
		add(374, "undercurrent", 170, (12345));
		add(376, "underground pass", 134, (9621));
		add(375, "underground", 132, (13368), (11416));
		add(377, "understanding", 187, (9547));
		add(378, "unknown land", 133, (12338));
		add(379, "upcoming", 135, (10546));
		add(380, "venture", 136, (13364));
		add(381, "venture 2", 168, (13464));
		add(382, "victory is mine", 368, (12696));
		add(383, "village", 182, (13878));
		add(384, "vision", 137, (12337), (12436));
		add(385, "voodoo cult", 138, (9545), (11665));
		add(386, "voyage", 139, (10038));
		add(388, "wander", 140, (12083));
		add(389, "warrior", 237, (10653));
		add(391, "waterfall", 141, (10037), (10137));
		add(392, "waterlogged", 199, (13877), (8014));
		add(394, "wayward", 308, (9875));
		add(396, "well of voyage", 221, (9366));
		add(397, "wild side", 340, (12092));
		add(398, "wilderness", 142, (11832), (12346));
		add(399, "wilderness 2", 143, (12091));
		add(400, "wilderness 3", 144, (11834));
		add(401, "wildwood", 256, (12344));
		add(402, "witching", 145, (13114));
		add(403, "woe of the wyvern", 369, (12181));
		add(405, "wonder", 146, (11831));
		add(406, "wonderous", 147, (10548));
		add(407, "woodland", 206, (8498));
		add(408, "workshop", 148, (12084));
		add(410, "xenophobe", 366, (7492), (11589));
		add(411, "yesteryear", 161, (12849));
		add(412, "zealot", 172, (10827));
		
			//Al kharid/desert
		add(3, "al kharid", 1, (13105), (13361));
		add(8, "arabian2", 5, (13107));
		add(9, "arabian3", 6, (12848));
		add(10, "arabian", 4, (13106), (13617));
		add(94, "duel arena", 164, (13362));
		add(295, "shine", 110, (13363));
		add(97, "egypt", 37, (13104));
			//Brimhaven
		add(1, "7th realm", 285, (10645), (10644));
		add(181, "karamja jam", 286, (10900), (10899));
		add(252, "pathways", 287, (10901));
			//Tutorial island
//		add(237, "newbie melody", 89, new ZoneBorders(3052, 3055, 3155, 3135));
			//Lumbridge
		add(150, "harmony", 58, (12850));
	}

	static CS2Mapping indexes;
	static CS2Mapping ids;
	/**
	 * Adds a new music entry.
	 * @param musicId The music id.
	 * @param name The song name.
	 * @param index The list index.
	 * @param borders The zone borders.
	 */
	private static void add(int musicId, String name, int index, int... regions) {
		String n = (String) indexes.getMap().get(index);
		System.out.print("add(" + ids.getMap().get(index) + ", \"" + n + "\", " + index);
		for (int id : regions) {
			System.out.print(", forRegion(" + id + ")");
		}
		System.out.println(");");
	}

	/**
	 * The main method.
	 * @param args The arguments cast on runtime.
	 * @throws Throwable When an exception occurs.
	 */
	public static void main(String[] args) throws Throwable {
		Store store = new Store("./666/");
		indexes = CS2Mapping.forId(1345, store);
		ids = CS2Mapping.forId(1351, store);
		configureMusic();
	}
}
