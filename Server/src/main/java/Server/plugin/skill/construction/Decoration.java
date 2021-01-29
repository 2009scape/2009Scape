package plugin.skill.construction;


import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.map.Location;

/**
 * Represents the decorations.
 * @author Emperor
 *
 */
public enum Decoration {

	/**
	 * Garden centrepiece decorations.
	 */
	PORTAL(13405, 8168, 1, 100.0, new Item(2351, 10)),
	ROCK(13406, 8169, 5, 100.0, new Item(3420, 5)),
	POND(13407, 8170, 10, 100.0, new Item(1761, 10)),
	IMP_STATUE(13408, 8171, 15, 150.0, new Item(3420, 5), new Item(1761, 5)),
	DUNGEON_ENTRANCE(13409, 8172, 70, 500.0, new Item(8786)),

	
	/**
	 * Garden big tree decorations.
	 */
	BIG_DEAD_TREE(13411, 8173, 5, 31.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8417)),
	BIG_TREE(13412, 8174, 10, 44.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8419)),
	BIG_OAK_TREE(13413, 8175, 15, 70.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8421)),
	BIG_WILLOW_TREE(13414, 8176, 30, 100.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8423)),
	BIG_MAPLE_TREE(13415, 8177, 45, 122.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8425)),
	BIG_YEW_TREE(13416, 8178, 60, 141.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8427)),
	BIG_MAGIC_TREE(13417, 8179, 75, 223.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8429)),
	
	/**
	 * Garden tree decorations.
	 */
	DEAD_TREE(13418, 8173, 5, 31.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8417)),
	TREE(13419, 8174, 10, 44.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8419)),
	OAK_TREE(13420, 8175, 15, 70.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8421)),
	WILLOW_TREE(13421, 8176, 30, 100.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8423)),
	MAPLE_TREE(13423, 8177, 45, 122.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8425)),
	YEW_TREE(13422, 8178, 60, 141.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8427)),
	MAGIC_TREE(13424, 8179, 75, 223.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8429)), 
	
	/**
	 * Garden big plant 1 decorations.
	 */
	FERN(13425, 8186, 1, 31.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8431)),
	BUSH(13426, 8187, 6, 70.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8433)),
	TALL_PLANT(13427, 8188, 12, 100.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8435)),
	
	/**
	 * Garden big plant 2 decorations.
	 */
	SHORT_PLANT(13428, 8189, 1, 31.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8431)),
	LARGE_LEAF_PLANT(13429, 8190, 6, 70.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8433)),
	HUGE_PLANT(13430, 8191, 12, 100.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8435)), 

	/**
	 * Garden small plant 1 decorations.
	 */
	PLANT(13431, 8180, 1, 31.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8431)), 
	SMALL_FERN(13432, 8181, 6, 70.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8433)), 
	FERN_SP(13433, 8182, 12, 100.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8435)), 

	/**
	 * Garden small plant 2 decorations.
	 */
	DOCK_LEAF(13434, 8183, 1, 31.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8431)), 
	THISTLE(13435, 8184, 6, 70.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8433)), 
	REEDS(13436, 8185, 12, 100.0, new int[] {BuildingUtils.WATERING_CAN}, new Item(8435)), 
	
	/**
	 * Parlour chair spot
	 */
	CRUDE_CHAIR(13581, 8309, 1, 66.0, new Item(960, 2)),
	WOODEN_CHAIR(13582, 8310, 8, 96.0, new Item(960, 3)),
	ROCKING_CHAIR(13583, 8311, 14, 96.0, new Item(960, 3)),
	OAK_CHAIR(13584, 8312, 19, 120.0, new Item(8778, 2)),
	OAK_ARMCHAIR(13585, 8313, 26, 180.0, new Item(8778, 3)),
	TEAK_ARMCHAIR(13586, 8314, 35, 180.0, new Item(8780, 2)),
	MAHOGANY_ARMCHAIR(13587, 8315, 50, 280.0, new Item(8782, 2)),

	/**
	 * Rugs rugs rugs
	 */
	BROWN_RUG_CORNER(13588, 8316, 2, 30.0, new Item(8790, 2)),
	RED_RUG_CORNER(13591, 8317, 13, 60.0, new Item(8790, 4)),
	OPULENT_RUG_CORNER(13594, 8318, 65, 360.0, new Item(8790, 4), new Item(8784, 1)),
	
	BROWN_RUG_END(13589, 8316, 2, 30.0, new Item(8790, 2)),
	RED_RUG_END(13592, 8317, 13, 60.0, new Item(8790, 4)),
	OPULENT_RUG_END(13595, 8318, 65, 360.0, new Item(8790, 4), new Item(8784, 1)),
	
	BROWN_RUG_CENTER(13590, 8316, 2, 30.0, new Item(8790, 2)),
	RED_RUG_CENTER(13593, 8317, 13, 60.0, new Item(8790, 4)),
	OPULENT_RUG_CENTER(13596, 8318, 65, 360.0, new Item(8790, 4), new Item(8784, 1)),

	/**
	 * Parlour fireplaces
	 */
	CLAY_FIREPLACE(13609, 8325, 3, 30.0, new Item(1761, 3)),
	STONE_FIREPLACE(13611, 8326, 33, 40.0, new Item(3420, 2)),
	MARBLE_FIREPLACE(13613, 8327, 63, 500.0, new Item(8786, 1)),

	/**
	 * Parlour curtain spot
	 */
	TORN_CURTAINS(13603, 8322, 2, 132.0, new Item(960, 3), new Item(8790, 3)),
	CURTAINS(13604, 8323, 18, 225.0, new Item(8778, 3), new Item(8790, 3)),
	OPULENT_CURTAINS(13605, 8324, 40, 315.0, new Item(8780, 3), new Item(8790, 3)),

	/**
	 * Parlour bookcases
	 */
	WOODEN_BOOKCASE(13597, 8319, 4, 132.0, new Item(960, 4)),
	OAK_BOOKCASE(13598, 8320, 29, 225.0, new Item(8778, 3)),
	MAHOGANY_BOOKCASE(13599, 8321, 40, 315.0, new Item(8782, 3)),

	
	/**
	 * Kitchen Beer Barrels
	 * TODO: These also require cooking levels!
	 * Basic: 1, Cider: 14, Asgarnian: 24, Greenman's: 29, D.Bitter: 39, Chef's: 54
	 *
	 */
	BASIC_BEER_BARREL(13568, 8239, 7, 87.0, new Item(960, 3)),
	CIDER_BARREL(13569, 8240, 12, 91.0, new Item(960, 3), new Item(5763, 8)),
	ASGARNIAN_ALE_BARREL(13570, 8241, 18, 184.0, new Item(8778, 3), new Item(1905, 8)),
	GREENMANS_ALE_BARREL(13571, 8242, 26, 184.0, new Item(8778, 3), new Item(1909, 8)),
	DRAGON_BITTER_BARREL(13572, 8243, 36, 224.0, new Item(8778, 3), new Item(1911, 8), new Item(2353, 2)),
	CHEFS_DELIGHT_BARREL(13573, 8244, 48, 224.0, new Item(8778, 3), new Item(5755, 8), new Item(2353, 2)),
	
	
	/**
	 * Kitchen Tables!
	 */
	KITCHEN_WOODEN_TABLE(13577, 8246, 12, 87.0, new Item(960, 3)),
	KITCHEN_OAK_TABLE(13578, 8247, 32, 180.0, new Item(8778, 3)),
	KITCHEN_TEAK_TABLE(13579, 8248, 52, 270.0, new Item(8780, 3)),
	
	
	/**
	 * Kitchen Stoves
	 */
	BASIC_FIREPIT(13528, 8216, 5, 40.0, new Item(1761, 2), new Item(2353, 1)),
	FIREPIT_WITH_HOOK(13529, 8217, 11, 60.0, new Item(1761, 2), new Item(2353, 2)),
	FIREPIT_WITH_POT(13531, 8218, 17, 80.0, new Item(1761, 2), new Item(2353, 3)),
	SMALL_OVEN(13533, 8219, 24, 80.0, new Item(2353, 4)),
	LARGE_OVEN(13536, 8220, 29, 100.0, new Item(2353, 5)),
	BASIC_RANGE(13539, 8221, 34, 120.0, new Item(2353, 6)),
	FANCY_RANGE(13542, 8222, 42, 160.0, new Item(2353, 8)),
	
	/**
	 * Kitchen larders
	 */
	WOODEN_LARDER(13565, 8233, 9, 228.0, new Item(960, 8)),
	OAK_LARDER(13566, 8234, 33, 480.0, new Item(8778, 8)),
	TEAK_LARDER(13567, 8235, 43, 750.0, new Item(8780, 8), new Item(8790, 2)),
	
	
	/**
	 * Kitchen shelves
	 */
	WOODEN_SHELVES_1(13545, 8223, 6, 87.0, new Item(960, 3)),
	WOODEN_SHELVES_2(13546, 8224, 12, 147.0, new Item(960, 3), new Item(1761, 6)),
	WOODEN_SHELVES_3(13547, 8225, 23, 147.0, new Item(960, 3), new Item(1761, 6)),
	OAK_SHELVES_1(13548, 8226, 34, 240.0, new Item(8778, 3), new Item(1761, 6)),
	OAK_SHELVES_2(13549, 8227, 45, 240.0, new Item(8778, 3), new Item(1761, 6)),
	TEAK_SHELVES_1(13550, 8228, 56, 330.0, new Item(8780, 3), new Item(1761, 6)),
	TEAK_SHELVES_2(13551, 8229, 67, 930.0, new Item(8780, 3), new Item(1761, 6), new Item(8784, 2)),
	
	/**
	 * Kitchen sinks
	 */
	PUMP_AND_DRAIN(13559, 8230, 7, 100.0, new Item(2353, 5)),
	PUMP_AND_TUB(13561, 8231, 27, 200.0, new Item(2353, 10)),
	SINK(13563, 8232, 47, 300.0, new Item(2353, 15)),
	
	
	/**
	 * Kitchen cat baskets/blankets
	 */
	CAT_BLANKET(13574, 8236, 5, 15.0, new Item(8790, 1)),
	CAT_BASKET(13575, 8237, 19, 58.0, new Item(960, 2)),
	CAST_BASKET_CUSHIONED(13576, 8238, 33, 58.0, new Item(960, 2), new Item(1737, 2)),
	
	
	/**
	 * Dining room tables
	 */
	DINING_TABLE_WOOD(13293, 8246, 10, 115.0, new Item(960, 4)),
	DINING_TABLE_OAK(13294, 8247, 22, 240.0, new Item(8778, 4)),
	DINING_TABLE_CARVED_OAK(13295, 8247, 31, 360.0, new Item(8778, 6)),
	DINING_TABLE_TEAK(13296, 8248, 38, 360.0, new Item(8780, 4)),
	DINING_TABLE_CARVED_TEAK(13297, 8248, 45, 600.0, new Item(8780, 6), new Item(8790, 4)),
	DINING_TABLE_MAHOGANY(13298, 8120, 52, 840.0, new Item(8782, 6)),
	DINING_TABLE_OPULENT(13299, 8121, 72, 3100.0, new Item(8782, 6), new Item(8790, 4), 
			new Item(8784, 4), new Item(8786, 2)),
		

	/**
	 * Dining room benches
	 */
	BENCH_WOODEN(13300, 8108, 10, 115.0, new Item(960, 4)),
	BENCH_OAK(13301, 8109, 22, 240.0, new Item(8778, 4)),
	BENCH_CARVED_OAK(13302, 8110, 31, 240.0, new Item(8778, 4)),
	BENCH_TEAK(13303, 8111, 38, 360.0, new Item(8780, 4)),
	BENCH_CARVED_TEAK(13304, 8112, 44, 360.0, new Item(8780, 4)),
	BENCH_MAHOGANY(13305, 8113, 52, 560.0, new Item(8782, 6)),
	BENCH_GILDED(13306, 8114, 61, 1760.0, new Item(8782, 4), new Item(8784, 4)),	
	
	/**
	 * Dining room bell-pulls
	 */
	ROPE_PULL(13307, 8099, 5, 15.0, new Item(954, 1), new Item(8778, 1)),
	BELL_PULL(13308, 8100, 19, 58.0, new Item(8780, 1), new Item(8790, 2)),
	FANCY_BELL_PULL(13309, 8101, 33, 58.0, new Item(8780, 1), new Item(8790, 2), new Item(8784, 1)),
	
	/**
	 * Workshop workbench
	 */
	WORKBENCH_WOODEN(13704, 8375, 17, 145.0, new Item(960, 1)),
	WORKBENCH_OAK(13705, 8376, 32, 300.0, new Item(8778, 5)),
	WORKBENCH_STEEL_FRAME(13706, 8377, 46, 440.0, new Item(8778, 6), new Item(2353, 4)),
	WORKBENCH_WITH_VICE(13707, 8378, 62, 750.0, new Item(8377, 1), new Item(8778, 2), new Item(2353, 1)),
	WORKBENCH_WITH_LATHE(13708, 8379, 77, 1000.0, new Item(8376, 1), new Item(8778, 2), new Item(2353, 1)),
	
	/**
	 * Workshop repair benches/stands
	 */
	REPAIR_BENCH(13713, 8389, 15, 120.0, new Item(8778, 2)),
	WHETSTONE(13714, 8390, 35, 260.0, new Item(8778, 4), new Item(3420, 1)),
	ARMOUR_STAND(13715, 8391, 55, 500.0, new Item(8778, 8), new Item(3420, 1)),
	
	/**
	 * Workshop easels
	 */
	PLUMING_STAND(13716, 8392, 16, 120.0, new Item(8778, 2)),
	SHIELD_EASEL(13717, 8393, 41, 240.0, new Item(8778, 4)),
	BANNER_EASEL(13718, 8394, 66, 510.0, new Item(8778, 8), new Item(8790, 2)),
	
	/**
	 * Workshop crafting tables
	 * 	TODO: These are upgradable hotspots, therefore crafting table 3 would require
	 * 	crafting table 2 to be already built in that spot.
	 */
	CRAFTING_TABLE_1(13709, 8380, 16, 50.0, new Item(8778, 4)),
	CRAFTING_TABLE_2(13710, 8381, 25, 100.0, new Item(1775, 1)),
	CRAFTING_TABLE_3(13711, 8382, 34, 175.0, new Item(1775, 2)),
	CRAFTING_TABLE_4(13712, 8383, 42, 240.0, new Item(8778, 2)),
	
	/**
	 * Workshop tool stores
	 * 	These are also upgradable just like the tables above.
	 */
	TOOL_STORE_1(13699, 8384, 15, 120.0, new Item(8778, 2)),
	TOOL_STORE_2(13700, 8385, 25, 120.0, new Item(8778, 2)),
	TOOL_STORE_3(13701, 8386, 35, 120.0, new Item(8778, 2)),
	TOOL_STORE_4(13702, 8387, 44, 120.0, new Item(8778, 2)),
	TOOL_STORE_5(13703, 8388, 55, 120.0, new Item(8778, 2)),

	
	/**
	 * Wall-mounted decorations
	 */
	OAK_DECORATION(13606, 8102, 16, 120.0, new Item(8778, 2)),
	TEAK_DECORATION(13606, 8103, 36, 180.0, new Item(8780, 2)),
	GILDED_DECORATION(13607, 8104, 56, 1020.0, new Item(8782, 3), new Item(8784, 2)),
	
	/**
	 * Staircases.
	 */
	OAK_STAIRCASE(13497, 8249, 27, 680.0, new Item(8778, 10), new Item(2353, 4)),
	TEAK_STAIRCASE(13499, 8252, 48, 980.0, new Item(8780, 10), new Item(2353, 4)),
	SPIRAL_STAIRCASE(13503, 8258, 67, 1040.0, new Item(8780, 10), new Item(3420, 7)),
	MARBLE_STAIRCASE(13501, 8257, 82, 3200.0, new Item(8782, 5), new Item(8786, 5)),
	MARBLE_SPIRAL(13505, 8259, 97, 4400.0, new Item(8780, 10), new Item(8786, 7)),
	
	/**
	 * Staircases going down.
	 */
	OAK_STAIRS_DOWN(13498, 8249, 27, 680.0, new Item(8778, 10), new Item(2353, 4)),
	TEAK_STAIRS_DOWN(13500, 8252, 48, 980.0, new Item(8780, 10), new Item(2353, 4)),
	SPIRAL_STAIRS_DOWN(13504, 8258, 67, 1040.0, new Item(8780, 10), new Item(3420, 7)),
	MARBLE_STAIRS_DOWN(13502, 8257, 82, 3200.0, new Item(8782, 5), new Item(8786, 5)),
	MARBLE_SPIRAL_DOWN(13506, 8259, 97, 4400.0, new Item(8780, 10), new Item(8786, 7)),
	
	/**
	 * Portal room decorations.
	 */
	TEAK_PORTAL(13636, 8328, 50, 270.0, new Item(8780, 3)),
	MAHOGANY_PORTAL(13637, 8329, 65, 420.0, new Item(8782, 3)),
	MARBLE_PORTAL(13638, 8330, 80, 1500.0, new Item(8786, 3)),
	TELEPORT_FOCUS(13640, 8331, 50, 40, new Item(3420, 2)),
	GREATER_TELEPORT_FOCUS(13641, 8332, 65, 500.0, new Item(8786, 1)),
	SCRYING_POOL(13639, 8333, 80, 2000.0, new Item(8786, 4)),
	TEAK_VARROCK_PORTAL(13615, true),
	MAHOGANY_VARROCK_PORTAL(13622, true),
	MARBLE_VARROCK_PORTAL(13629, true),	
	TEAK_LUMBRIDGE_PORTAL(13616, true),
	MAHOGANY_LUMBRIDGE_PORTAL(13623, true),
	MARBLE_LUMBRIDGE_PORTAL(13630, true),	
	TEAK_FALADOR_PORTAL(13617, true),
	MAHOGANY_FALADOR_PORTAL(13624, true),
	MARBLE_FALADOR_PORTAL(13631, true),	
	TEAK_CAMELOT_PORTAL(13618, true),
	MAHOGANY_CAMELOT_PORTAL(13625, true),
	MARBLE_CAMELOT_PORTAL(13632, true),	
	TEAK_ARDOUGNE_PORTAL(13619, true),
	MAHOGANY_ARDOUGNE_PORTAL(13626, true),
	MARBLE_ARDOUGNE_PORTAL(13633, true),
	TEAK_YANILLE_PORTAL(13620, true),
	MAHOGANY_YANILLE_PORTAL(13627, true),
	MARBLE_YANILLE_PORTAL(13634, true),
	TEAK_KHARYRLL_PORTAL(13621, true),
	MAHOGANY_KHARYRLL_PORTAL(13628, true),
	MARBLE_KHARYRLL_PORTAL(13635, true),
	
	/**
	 * Skill hall decorations.
	 */
	MITHRIL_ARMOUR(13491, 8270, 28, 135.0, new Item(8778, 2), new Item(1159, 1), new Item(1121, 1), new Item(1085, 1)),
	ADAMANT_ARMOUR(13492, 8271, 28, 150.0, new Item(8778, 2), new Item(1161, 1), new Item(1123, 1), new Item(1091, 1)),
	RUNE_ARMOUR(13493, 8272, 28, 165.0, new Item(8778, 2),new Item(1163, 1), new Item(1127, 1), new Item(1093, 1)),
	CRAWLING_HAND(13481, 8260, 38, 211.0, new Item(8780, 2), new Item(7982, 1)),
	COCKATRICE_HEAD(13482, 8261, 38, 224.0, new Item(8780, 2), new Item(7983, 1)),
	BASILISK_HEAD(13483, 8262, 38, 243.0, new Item(8780, 2), new Item(7984, 1)),
	KURASK_HEAD(13484, 8263, 58, 357.0, new Item(8782, 2), new Item(7985, 1)),
	ABYSSAL_DEMON_HEAD(13485, 8264, 58, 389.0, new Item(8782, 2), new Item(7986, 1)),
	KBD_HEAD(13486, 8265, 78, 1103.0, new Item(8782, 2), new Item(7987, 1)),	
	KQ_HEAD(13487, 8266, 78, 1103.0, new Item(8782, 2), new Item(7988, 1)),	
	MOUNTED_BASS(13488, 8267, 36, 151.0, new Item(8778, 2), new Item(7990, 1)),
	MOUNTED_SWORDFISH(13489, 8268, 56, 230.0, new Item(8780, 2), new Item(7992, 1)),
	MOUNTED_SHARK(13490, 8269, 76, 350.0, new Item(8782, 2), new Item(7994, 1)),
	RUNE_CASE1(13507, 8095, 41, 190.0, new Item(8780, 2), new Item(1775, 2), new Item(554, 1), new Item(556, 1), new Item(557, 1), new Item(555, 1)),
	RUNE_CASE2(13508, 8095, 41, 212.0, new Item(8780, 2), new Item(1775, 2), new Item(559, 1), new Item(564, 1), new Item(562, 1), new Item(561, 1)),
			
	
	/**
	 * Games room decorations.
	 */
	CLAY_STONE(13392, 8153, 39, 100.0, new Item(1761, 10)),
	LIMESTONE_STONE(13393, 8154, 59, 200.0, new Item(3420, 10)),
	MARBLE_STONE(13394, 8155, 79, 2000.0, new Item(8786, 4)),
	HOOP_AND_STICK(13398, 8162, 30, 120.0, new Item(8778, 2)),
	DARTBOARD(13400, 8163, 54, 290.0, new Item(8780, 3), new Item(2353, 1)),
	ARCHERY_TARGET(13402, 8164, 81, 600.0, new Item(8780, 6), new Item(2353, 3)),
	BALANCE_1(13395, 8156, 37, 176.0, new Item(554, 500), new Item(556, 500), new Item(557, 500), new Item(555, 500)),
	BALANCE_2(13396, 8157, 57, 252.0, new Item(554, 1000), new Item(556, 1000), new Item(557, 1000), new Item(555, 1000)),
	BALANCE_3(13397, 8158, 77, 356.0, new Item(554, 2000), new Item(556, 2000), new Item(557, 2000), new Item(555, 2000)),
	OAK_CHEST(13385, 8165, 34, 240.0, new Item(8778, 4)),
	TEAK_CHEST(13387, 8166, 44, 660.0, new Item(8780, 4), new Item(8784, 1)),
	MAHOGANY_CHEST(13389, 8167, 54, 860.0, new Item(8782, 4), new Item(8784, 1)),
	JESTER(13390, 8159, 39, 360.0, new Item(8780, 4)),
	TREASURE_HUNT(13379, 8160, 49, 800.0, new Item(8780, 8), new Item(2353, 4)),
	HANGMAN(13404, 8161, 59, 1200.0, new Item(8780, 12), new Item(2353, 6)),	
	
	
	/**
	* Combat room decorations.
	*/
	BOXING_RING(13129, 8023, 32, 570.0, new Item(8778, 6), new Item(8790, 4)),
	FENCING_RING(13133, 8024, 41, 570.0, new Item(8770, 8), new Item(8790, 6)),
	COMBAT_RING(13137, 8025, 51, 630.0, new Item(8780, 6), new Item(8790, 6)),
	BALANCE_BEAM_LEFT(13143, 8027, 81, 1000.0, new Item(8780, 10), new Item(2353, 5)),
	BALANCE_BEAM_CENTER(13142, 8027, 81, 1000.0, new Item(8780, 10), new Item(2353, 5)),
	BALANCE_BEAM_RIGHT(13144, 8027, 81, 1000.0, new Item(8780, 10), new Item(2353, 5)),
	RANGING_PEDESTALS(13147, 8026, 71, 720.0, new Item(8780, 8)),
	MAGIC_BARRIER(13145, 8026, 71, 720.0, new Item(8780, 8)),
	NOTHING(13721, 8027, 81, 1000.0, new Item(8780, 10), new Item(2353, 5)),
	NOTHING2(13721, 8026, 71, 720.0, new Item(8780, 8)),
	INVISIBLE_WALL(15283, 8023, 32, 570.0, new Item(8778, 6), new Item(8790, 4)),
	INVISIBLE_WALL2(15284, 8023, 32, 570.0, new Item(8778, 6), new Item(8790, 4)),
	INVISIBLE_WALL3(15285, 8023, 32, 570.0, new Item(8778, 6), new Item(8790, 4)),
	GLOVE_RACK(13381, 8028, 34, 120.0, new Item(8778, 2)),
	WEAPONS_RACK(13382, 8029, 44, 180.0, new Item(8780, 2)),
	EXTRA_WEAPONS_RACK(13383, 8030, 54, 440.0, new Item(8780, 4), new Item(2353, 4)),
	BOXING_MAT_CORNER(13126, 8023, 32, 570.0, new Item(8778, 6), new Item(8790, 4)),
	FENCING_MAT_CORNER(13135, 8024, 41, 570.0, new Item(8770, 8), new Item(7991, 6)),
	COMBAT_MAT_CORNER(13138, 8025, 51, 630.0, new Item(8780, 6), new Item(7991, 6)),
	BOXING_MAT_SIDE(13128, 8023, 32, 570.0, new Item(8778, 6), new Item(8790, 4)),
	FENCING_MAT_SIDE(13134, 8024, 41, 570.0, new Item(8770, 8), new Item(7991, 6)),
	COMBAT_MAT_SIDE(13139, 8025, 51, 630.0, new Item(8780, 6), new Item(7991, 6)),
	BOXING_MAT(13127, 8023, 32, 570.0, new Item(8778, 6), new Item(8790, 4)),
	FENCING_MAT(13136, 8024, 41, 570.0, new Item(8770, 8), new Item(7991, 6)),
	COMBAT_MAT(13140, 8025, 51, 630.0, new Item(8780, 6), new Item(7991, 6)),
	
	
	/**
	 * Formal garden decorations
	 */
	GAZEBO(13477, 8192, 65, 1200, new Item(8782, 8), new Item(2353, 4)),
	SMALL_FOUNTAIN(13478, 8193, 71, 500, new Item(8786, 1)),
	LARGE_FOUNTAIN(13479, 8194, 75, 1000, new Item(8786, 2)),
	POSH_FOUNTAIN(13480, 8195, 81, 1500, new Item(8786, 3)),
	SUNFLOWER(13446, 8213, 66, 70, new int[] {BuildingUtils.WATERING_CAN}, new Item(8457, 1)),
	MARIGOLDS(13447, 8214, 71, 100, new int[] {BuildingUtils.WATERING_CAN}, new Item(8459, 1)),
	ROSES(13448, 8215, 76, 122, new int[] {BuildingUtils.WATERING_CAN}, new Item(8461, 1)),
	SUNFLOWER_BIG(13443, 8213, 66, 70, new int[] {BuildingUtils.WATERING_CAN}, new Item(8457, 1)),
	MARIGOLDS_BIG(13444, 8214, 71, 100, new int[] {BuildingUtils.WATERING_CAN}, new Item(8459, 1)),
	ROSES_BIG(13445, 8215, 76, 122, new int[] {BuildingUtils.WATERING_CAN}, new Item(8461, 1)),
	ROSEMARY(13440, 8210, 66, 70, new int[] {BuildingUtils.WATERING_CAN}, new Item(8451, 1)),
	DAFFODILS(13441, 8211, 71, 100, new int[] {BuildingUtils.WATERING_CAN}, new Item(8453, 1)),
	BLUEBELLS(13442, 8212, 76, 122, new int[] {BuildingUtils.WATERING_CAN}, new Item(8455, 1)),
	ROSEMARY_BIG(13437, 8210, 66, 70, new int[] {BuildingUtils.WATERING_CAN}, new Item(8451, 1)),
	DAFFODILS_BIG(13438, 8211, 71, 100, new int[] {BuildingUtils.WATERING_CAN}, new Item(8453, 1)),
	BLUEBELLS_BIG(13439, 8212, 76, 122, new int[] {BuildingUtils.WATERING_CAN}, new Item(8455, 1)),
	THORNY_HEDGE1(13456, 8203, 56, 70, new int[] {BuildingUtils.WATERING_CAN}, new Item(8437, 1)),
	THORNY_HEDGE2(13457, 8203, 56, 70, new int[] {BuildingUtils.WATERING_CAN}, new Item(8437, 1)),
	THORNY_HEDGE3(13458, 8203, 56, 70, new int[] {BuildingUtils.WATERING_CAN}, new Item(8437, 1)),
	NICE_HEDGE1(13459, 8204, 60, 100, new int[] {BuildingUtils.WATERING_CAN}, new Item(8439, 1)),
	NICE_HEDGE2(13461, 8204, 60, 100, new int[] {BuildingUtils.WATERING_CAN}, new Item(8439, 1)),
	NICE_HEDGE3(13460, 8204, 60, 100, new int[] {BuildingUtils.WATERING_CAN}, new Item(8439, 1)),
	SMALL_BOX_HEDGE1(13462, 8205, 64, 122, new int[] {BuildingUtils.WATERING_CAN}, new Item(8441, 1)),
	SMALL_BOX_HEDGE2(13464, 8205, 64, 122, new int[] {BuildingUtils.WATERING_CAN}, new Item(8441, 1)),
	SMALL_BOX_HEDGE3(13463, 8205, 64, 122, new int[] {BuildingUtils.WATERING_CAN}, new Item(8441, 1)),
	TOPIARY_HEDGE1(13465, 8206, 68, 141, new int[] {BuildingUtils.WATERING_CAN}, new Item(8443, 1)),
	TOPIARY_HEDGE2(13467, 8206, 68, 141, new int[] {BuildingUtils.WATERING_CAN}, new Item(8443, 1)),
	TOPIARY_HEDGE3(13466, 8206, 68, 141, new int[] {BuildingUtils.WATERING_CAN}, new Item(8443, 1)),
	FANCY_HEDGE1(13468, 8207, 72, 158, new int[] {BuildingUtils.WATERING_CAN}, new Item(8445, 1)),
	FANCY_HEDGE2(13470, 8207, 72, 158, new int[] {BuildingUtils.WATERING_CAN}, new Item(8445, 1)),
	FANCY_HEDGE3(13469, 8207, 72, 158, new int[] {BuildingUtils.WATERING_CAN}, new Item(8445, 1)),
	TALL_FANCY_HEDGE1(13471, 8208, 76, 223, new int[] {BuildingUtils.WATERING_CAN}, new Item(8447, 1)),
	TALL_FANCY_HEDGE2(13473, 8208, 76, 223, new int[] {BuildingUtils.WATERING_CAN}, new Item(8447, 1)),
	TALL_FANCY_HEDGE3(13472, 8208, 76, 223, new int[] {BuildingUtils.WATERING_CAN}, new Item(8447, 1)),
	TALL_BOX_HEDGE1(13474, 8209, 80, 316, new int[] {BuildingUtils.WATERING_CAN}, new Item(8449, 1)),
	TALL_BOX_HEDGE2(13476, 8209, 80, 316, new int[] {BuildingUtils.WATERING_CAN}, new Item(8449, 1)),
	TALL_BOX_HEDGE3(13475, 8209, 80, 316, new int[] {BuildingUtils.WATERING_CAN}, new Item(8449, 1)),
	BOUNDARY_STONES(13449, 8196, 55, 100, new Item(1761, 10)),
	WOODEN_FENCE(13450, 8197, 59, 280, new Item(960, 10)),
	STONE_WALL(13451, 8198, 63, 200, new Item(3420, 10)),
	IRON_RAILINGS(13452, 8199, 67, 220, new Item(2351, 10), new Item(3420, 6)),
	PICKET_FENCE(13453, 8200, 71, 640, new Item(8778, 10), new Item(2353, 2)),
	GARDEN_FENCE(13454, 8201, 75, 940, new Item(8780, 10), new Item(2353, 2)),
	MARBLE_WALL(13455, 8202, 79, 4000, new Item(8786, 10)),
	
	
	/**
	 * Bedroom decorations.
	 */
	WOODEN_BED(13148, 8031, 20, 117, new Item(960, 3), new Item(8790, 2)),
	OAK_BED(13149, 8032, 30, 210, new Item(8778, 3), new Item(8790, 2)),
	LARGE_OAK_BED(13150, 8033, 34, 330, new Item(8778, 5), new Item(8790, 2)),
	TEAK_BED(13151, 8034, 40, 300, new Item(8780, 3), new Item(8790, 2)),
	LARGE_TEAK_BED(13152, 8035, 45, 480, new Item(8780, 5), new Item(8790, 2)),
	FOUR_POSTER(13153, 8036, 53, 450, new Item(8782, 3), new Item(8790, 2)),
	GILDED_FOUR_POSTER(13154, 8037, 60, 1330, new Item(8782, 5), new Item(8790, 2), new Item(8784, 2)),
	OAK_CLOCK(13169, 8052, 25, 142, new Item(8778, 2), new Item(8792, 1)),
	TEAK_CLOCK(13170, 8053, 55, 202, new Item(8780, 2), new Item(8792, 1)),
	GILDED_CLOCK(13171, 8054, 85, 602, new Item(8782, 2), new Item(8792, 1), new Item(8784, 1)),
	SHAVING_STAND(13162, 8045, 21, 30, new Item(960, 1), new Item(1775, 1)),
	OAK_SHAVING_STAND(13163, 8046, 29, 61, new Item(8778, 1), new Item(1775, 1)),
	OAK_DRESSER(13164, 8047, 37, 121, new Item(8778, 2), new Item(1775, 1)),
	TEAK_DRESSER(13165, 8048, 46, 181, new Item(8780, 2), new Item(1775, 1)),
	FANCY_TEAK_DRESSER(13166, 8049, 56, 182, new Item(8780, 2), new Item(1775, 2)),
	MAHOGANY_DRESSER(13167, 8050, 64, 281, new Item(8782, 2), new Item(1775, 1)),
	GILDED_DRESSER(13168, 8051, 74, 582, new Item(8782, 2), new Item(1775, 2), new Item(8784, 1)),
	SHOE_BOX(13155, 8038, 20, 58, new Item(960, 2)),
	OAK_DRAWERS(13156, 8039, 27, 120, new Item(8778, 2)),
	OAK_WARDROBE(13157, 8040, 39, 180, new Item(8778, 3)),
	TEAK_DRAWERS(13158, 8041, 51, 180, new Item(8780, 2)),
	TEAK_WARDROBE(13159, 8042, 63, 270, new Item(8780, 3)),
	MAHOGANY_WARDROBE(13160, 8043, 75, 420, new Item(8782, 2)),
	GILDED_WARDROBE(13161, 8044, 87, 720, new Item(8782, 2), new Item(8784, 1)),
	
	
	/**
	 * Quest hall decorations.
	 */
	ANTIDRAGON_SHIELD(13522, 8282, 47, 280, new Item(8780, 3), new Item(1540, 1)),
	AMULET_OF_GLORY(13523, 8283, 47, 290, new Item(8780, 3), new Item(1704, 1)),
	CAPE_OF_LEGENDS(13524, 8284, 47, 300, new Item(8780, 3), new Item(1052, 1)),
	KING_ARTHUR(13510, 8285, 35, 211, new Item(8780, 3), new Item(7995, 1)),
	ELENA(13511, 8286, 35, 211, new Item(8780, 3), new Item(7996, 1)),
	GIANT_DWARF(13512, 8287, 35, 211, new Item(8780, 3), new Item(7997, 1)),
	MISCELLANIANS(13513, 8288, 35, 311, new Item(8780, 3), new Item(7998, 1)),
	LUMBRIDGE(13517, 8289, 44, 314, new Item(8780, 3), new Item(8002, 1)),
	THE_DESERT(13514, 8290, 44, 314, new Item(8780, 3), new Item(7999, 1)),
	MORYTANIA(13518, 8291, 44, 314, new Item(8780, 3), new Item(8003, 1)),
	KARAMJA(13516, 8292, 65, 464, new Item(8782, 3), new Item(8001, 1)),
	ISAFDAR(13515, 8293, 65, 464, new Item(8782, 3), new Item(8000, 1)),
	SILVERLIGHT(13519, 8279, 42, 187, new Item(8780, 3), new Item(2402, 1)),
	EXCALIBUR(13521, 8280, 42, 194, new Item(8780, 3), new Item(35, 1)),
	DARKLIGHT(13520, 8281, 42, 202, new Item(8780, 3), new Item(6746, 1)),
	SMALL_MAP(13525, 8294, 38, 211, new Item(8780, 3), new Item(8004, 1)),
	MEDIUM_MAP(13526, 8295, 58, 451, new Item(8782, 3), new Item(8005, 1)),
	LARGE_MAP(13527, 8296, 78, 591, new Item(8782, 4), new Item(8006, 1)),

	/**
	 * Menagerie Decorations
	 */
	//OBELISK
	MINI_OBELISK(44837, 15236, 41, 676, new Item(8786, 1), new Item(12183, 1000), new Item(12158, 10), new Item(12159, 10), new Item(12160, 10), new Item(12163, 10))
	//PET_FEEDER
	,
	OAK_PET_FEEDER(44834, 15233, 37, 240, new Item(8778, 4)),
	TEAK_PET_FEEDER(44835, 15234, 52, 380, new Item(8780, 4)),
	MAHOGANY_PET_FEEDER(44836, 15235, 67, 880, new Item(8782, 4), new Item(8784, 1))
	//PET_HOUSE
	,
	OAK_PET_HOUSE(44828, 15227, 37, 240, new Item(8778, 4)),
	TEAK_PET_HOUSE(44829, 15228, 52, 380, new Item(8780, 4)),
	MAHOGANY_PET_HOUSE(44830, 15229, 67, 580, new Item(8782, 4)),
	CONSECRATED_PET_HOUSE(44831, 15230, 92, 1580, new Item(8782, 4), new Item(8788, 1)),
	DESECRATED_PET_HOUSE(44832, 15231, 92, 1580, new Item(8782, 4), new Item(8788, 1)),
	NATURAL_PET_HOUSE(44833, 15232, 92, 1580, new Item(8782, 4), new Item(8788, 1))
	//HABITAT_SPACE
	,
	GARDEN_HABITAT(new int[]{
			4497,
			4498,
			44500,
			44501,
			44502,
			44503,
			44504,
			44505,
			44506,
			44507,
			44508,
			44509,
			44510,
			44511,
			44512,
			44513,
			44514,
			44515,
			44516,
			44517,
			44518,
			44519,
			44520,
			44521,
			44522,
			44523,
			44524,
			44525,
			44526,
			44527,
			44528,
			44529,
			44530,
			44531,
			44532,
			44533,
			44534,
			44535,
			44536,
			44537,
			44538,
			44539,
			44540,
			44541,
			44542,
			44543,
			44544,
			44545,
			44546,
			44547,
			44548,
			44549,
			44550,
			44551,
			44552,
			44553,
			44554,
			44555,
			44556,
			44557,
			44558,
			44559,
			44560,
			44561,
			44562,
			44563 }, 15222, 37, 201, new Item(8431, 1), new Item(8433, 1), new Item(8435, 1)),
	JUNGLE_HABITAT(new int[]
			{
					44564,
					44565,
					44566,
					44567,
					44568,
					44569,
					44570,
					44571,
					44572,
					44573,
					44574,
					44575,
					44576,
					44577,
					44578,
					44579,
					44580,
					44581,
					44582,
					44583,
					44584,
					44585,
					44586,
					44587,
					44588,
					44589,
					44590,
					44591,
					44592,
					44593,
					44594,
					44595,
					44596,
					44597,
					44598,
					44599,
					44600,
					44601,
					44602,
					44603,
					44604,
					44605,
					44606,
					44607,
					44608,
					44609,
					44610,
					44611,
					44612,
					44613,
					44614,
					44615,
					44616,
					44617,
					44618,
					44619,
					44620,
					44621,
					44622,
					44623,
					44624,
					44625,
					44626,
					44627,
					44628,
					44629 }, 15223, 47, 278, new Item(8435, 3), new Item(8423, 1), new Item(1929, 5)),
	DESERT_HABITAT(new int[]
			{
					44630,
					44631,
					44632,
					44633,
					44634,
					44635,
					44636,
					44637,
					44638,
					44639,
					44640,
					44641,
					44642,
					44643,
					44644,
					44645,
					44646,
					44647,
					44648,
					44649,
					44650,
					44651,
					44652,
					44653,
					44654,
					44655,
					44656,
					44657,
					44658,
					44659,
					44660,
					44661,
					44662,
					44663,
					44664,
					44665,
					44666,
					44667,
					44668,
					44669,
					44670,
					44671,
					44672,
					44673,
					44674,
					44675,
					44676,
					44677,
					44678,
					44679,
					44680,
					44681,
					44682,
					44683,
					44684,
					44685,
					44686,
					44687,
					44688,
					44689,
					44690,
					44691,
					44692,
					44693,
					44694,
					44695 }, 15224, 57, 238, new Item(1783, 10), new Item(3420, 5), new Item(15237, 1)),
	POLAR_HABITAT(new int[]
			{
					44696,
					44697,
					44698,
					44699,
					44700,
					44701,
					44702,
					44703,
					44704,
					44705,
					44706,
					44707,
					44708,
					44709,
					44710,
					44711,
					44712,
					44713,
					44714,
					44715,
					44716,
					44717,
					44718,
					44719,
					44720,
					44721,
					44722,
					44723,
					44724,
					44725,
					44726,
					44727,
					44728,
					44729,
					44730,
					44731,
					44732,
					44733,
					44734,
					44735,
					44736,
					44737,
					44738,
					44739,
					44740,
					44741,
					44742,
					44743,
					44744,
					44745,
					44746,
					44747,
					44748,
					44749,
					44750,
					44751,
					44752,
					44753,
					44754,
					44755,
					44756,
					44757,
					44758,
					44759,
					44760,
					44761 }, 15225, 67, 373, new Item(556, 1000), new Item(555, 1000), new Item(15239, 1)),
	VOLCANIC_HABITAT(new int[]
			{
					44762,
					44763,
					44764,
					44765,
					44766,
					44767,
					44768,
					44769,
					44770,
					44771,
					44772,
					44773,
					44774,
					44775,
					44776,
					44777,
					44778,
					44779,
					44780,
					44781,
					44782,
					44783,
					44784,
					44785,
					44786,
					44787,
					44788,
					44789,
					44790,
					44791,
					44792,
					44793,
					44794,
					44795,
					44796,
					44797,
					44798,
					44799,
					44800,
					44801,
					44802,
					44803,
					44804,
					44805,
					44806,
					44807,
					44808,
					44809,
					44810,
					44811,
					44812,
					44813,
					44814,
					44815,
					44816,
					44817,
					44818,
					44819,
					44820,
					44821,
					44822,
					44823,
					44824,
					44825,
					44826,
					44827 }, 15226, 77, 77, new Item(554, 1000), new Item(557, 1000), new Item(8417, 1), new Item(13245, 5)),


	/**
	 * Study decorations.
	 */
	GLOBE(13649, 8341, 41, 180, new Item(8778, 3)),
	ORNAMENTAL_GLOBE(13650, 8342, 50, 270, new Item(8780, 3)),	
	LUNAR_GLOBE(13651, 8343, 59, 570, new Item(8780, 3), new Item(8784, 1)),	
	CELESTIAL_GLOBE(13652, 8344, 68, 570, new Item(8780, 3), new Item(8784, 1)),	
	ARMILLARY_SPHERE(13653, 8345, 77, 960, new Item(8782, 2), new Item(8784, 2), new Item(2353, 4)),	
	SMALL_ORREY(13654, 8346, 86, 1320, new Item(8782, 3), new Item(8784, 3)),	
	LARGE_ORREY(13655, 8347, 95, 1420, new Item(8782, 3), new Item(8784, 5)),	
	OAK_LECTERN(13642, 8334, 40, 60, new Item(8778, 1)),	
	EAGLE_LECTERN(13643, 8335, 47, 120, new Item(8778, 2)),	
	DEMON_LECTERN(13644, 8336, 47, 120, new Item(8778, 2)),	
	TEAK_EAGLE_LECTERN(13645, 8337, 57, 180, new Item(8780, 2)),	
	TEAK_DEMON_LECTERN(13646, 8338, 57, 180, new Item(8780, 2)),	
	MAHOGANY_EAGLE_LECTERN(13647, 8339, 67, 580, new Item(8782, 2), new Item(8784, 1)),	
	MAHOGANY_DEMON_LECTERN(13648, 8340, 67, 580, new Item(8782, 2), new Item(8784, 1)),	
	CRYSTAL_BALL(13659, 8351, 42, 280, new Item(8780, 3), new Item(567, 1)),	
	ELEMENTAL_SPHERE(13660, 8352, 54, 580, new Item(8780, 3), new Item(567, 1), new Item(8784, 1)),
	CRYSTAL_OF_POWER(13661, 8353, 66, 890, new Item(8782, 2), new Item(567, 1), new Item(8784, 2)),
	ALCHEMICAL_CHART(13662, 8354, 43, 30, new Item(8790, 2)),
	ASTRONOMICAL_CHART(13663, 8355, 63, 45, new Item(8790, 3)),
	INFERNAL_CHART(13664, 8356, 83, 60, new Item(8790, 4)),
	TELESCOPE1(13656, 8348, 44, 121, new Item(8778, 2), new Item(1775, 1)),
	TELESCOPE2(13657, 8349, 64, 181, new Item(8780, 2), new Item(1775, 1)),
	TELESCOPE3(13658, 8350, 84, 580, new Item(8782, 2), new Item(1775, 1)),
	
	/**
	 * Costume room decorations.
	 */
	OAK_TREASURE_CHEST(18804, 9839, 48, 120, new Item(8778, 2)),
	TEAK_TREASURE_CHEST(18806, 9840, 66, 180, new Item(8780, 2)),
	MAHOGANY_TREASURE_CHEST(18808, 9841, 84, 280, new Item(8782, 2)),
	OAK_ARMOUR_CASE(18778, 9826, 46, 180, new Item(8778, 3)),
	TEAK_ARMOUR_CASE(18780, 9827, 64, 270, new Item(8780, 3)),
	MGANY_ARMOUR_CASE(18782, 9828, 82, 420, new Item(8782, 3)),
	OAK_MAGIC_WARDROBE(18784, 9829, 42, 240, new Item(8778, 4)),
	C_OAK_MAGIC_WARDROBE(18786, 9830, 51, 360, new Item(8778, 6)),
	TEAK_MAGIC_WARDROBE(18788, 9831, 60, 360, new Item(8780, 4)),
	C_TEAK_MAGIC_WARDROBE(18790, 9832, 69, 540, new Item(8780, 6)),
	MGANY_MAGIC_WARDROBE(18792, 9833, 78, 560, new Item(8782, 4)),
	GILDED_MAGIC_WARDROBE(18794, 9834, 87, 860, new Item(8782, 4), new Item(8784, 1)),
	MARBLE_MAGIC_WARDROBE(18796, 9835, 96, 500, new Item(8786, 1)),
	OAK_CAPE_RACK(18766, 9817, 54, 240, new Item(8778, 4)),
	TEAK_CAPE_RACK(18767, 9818, 63, 360, new Item(8780, 4)),
	MGANY_CAPE_RACK(18768, 9819, 72, 560, new Item(8782, 4)),
	GILDED_CAPE_RACK(18769, 9820, 81, 860, new Item(8782, 4), new Item(8784, 1)),
	MARBLE_CAPE_RACK(18770, 9821, 90, 500, new Item(8786, 1)),
	MAGIC_CAPE_RACK(18771, 9822, 99, 1000, new Item(8788, 1)),
	OAK_TOY_BOX(18798, 9836, 50, 120, new Item(8778, 2)),
	TEAK_TOY_BOX(18800, 9837, 68, 180, new Item(8780, 2)),
	MAHOGANY_TOY_BOX(18802, 9838, 86, 280, new Item(8782, 2)),
	OAK_COSTUME_BOX(18772, 9823, 44, 120, new Item(8778, 2)),
	TEAK_COSTUME_BOX(18774, 9824, 62, 180, new Item(8780, 2)),
	MAHOGANY_COSTUME_BOX(18776, 9825, 80, 280, new Item(8782, 2)),
	
	/**
	 * Chapel decorations.
	 */
	OAK_ALTAR(13179, 8062, 45, 240, new Item(8778, 4)),	
	TEAK_ALTAR(13182, 8063, 50, 360, new Item(8780, 4)),	
	CLOTH_ALTAR(13185, 8064, 56, 390, new Item(8780, 4), new Item(8790, 2)),	
	MAHOGANY_ALTAR(13188, 8065, 60, 590, new Item(8782, 4), new Item(8790, 2)),	
	LIMESTONE_ALTAR(13191, 8066, 64, 910, new Item(8782, 6), new Item(8790, 2), new Item(3420, 2)),	
	MARBLE_ALTAR(13194, 8067, 70, 1030, new Item(8786, 2), new Item(8790, 2)),	
	GILDED_ALTAR(13197, 8068, 75, 2230, new Item(8786, 2), new Item(8790, 2), new Item(8784, 4)),	
	SMALL_STATUE(13271, 8082, 49, 40, new Item(3420, 2)),	
	MEDIUM_STATUE(13272, 8083, 69, 500, new Item(8786, 1)),	
	LARGE_STATUE(13282, 8084, 89, 1500, new Item(8786, 3)),	
	WINDCHIMES(13214, 8079, 49, 323, new Item(8778, 4), new Item(2353, 4)),	
	BELLS(13215, 8080, 58, 480, new Item(8780, 4), new Item(2353, 6)),	
	ORGAN(13216, 8081, 69, 680, new Item(8782, 4), new Item(2353, 6)),	
	SARADOMIN_SYMBOL(13172, 8055, 48, 120, new Item(8778, 2)),	
	ZAMORAK_SYMBOL(13173, 8056, 48, 120, new Item(8778, 2)),
	GUTHIX_SYMBOL(13174, 8057, 48, 120, new Item(8778, 2)),
	SARADOMIN_ICON(13175, 8058, 59, 960, new Item(8780, 4), new Item(8784, 2)),	
	ZAMORAK_ICON(13176, 8059, 59, 960, new Item(8780, 4), new Item(8784, 2)),	
	GUTHIX_ICON(13177, 8060, 59, 960, new Item(8780, 4), new Item(8784, 2)),	
	ICON_OF_BOB(13178, 8061, 71, 1160, new Item(8782, 4), new Item(8784, 2)),	
	STEEL_TORCHES(13202, 8070, 45, 80, new Item(2353, 2)),	
	WOODEN_TORCHES(13200, 8069, 49, 58, new Item(960, 2)),	
	STEEL_CANDLESTICKS(13204, 8071, 53, 124, new Item(2353, 6), new Item(36, 6)),	
	GOLD_CANDLESTICKS(13206, 8072, 57, 46, new Item(2357, 6), new Item(36, 6)),	
	INCENSE_BURNERS(13208, 8073, 61, 280, new Item(8778, 4), new Item(2353, 2)),	
	MAHOGANY_BURNERS(13210, 8074, 65, 600, new Item(8782, 4), new Item(2353, 2)),	
	MARBLE_BURNERS(13212, 8075, 69, 1600, new Item(8786, 2), new Item(2353, 2)),	
	SHUTTERED_WINDOW(new int[] { 13253, 13226, 13235, 13244, 13217, 13262 }, 8076, 49, 228, new Item(960, 8)),	
	DECORATIVE_WINDOW(new int[] { 13254, 13227, 13236, 13245, 13218, 13263 }, 8077, 69, 200, new Item(1775, 8)),	
	STAINED_GLASS(new int[] { 13255, 13228, 13237, 13246, 13219, 13264 }, 8078, 89, 400, new Item(1775, 16)),

	/**
	 * Throne room
	 */
	OAK_THRONE(13665, 8357, 60, 800, new Item(8778, 5), new Item(8786, 1)),
	TEAK_THRONE(13666, 8358, 67, 1450, new Item(8780, 5), new Item(8786, 2)),
	MAHOGANY_THRONE(13667, 8359, 74, 2200, new Item(8782, 5), new Item(8786, 3)),
	GILDED_THRONE(13668, 8360, 81, 1700, new Item(8782, 5), new Item(8786, 2), new Item(8784, 3)),
	SKELETON_THRONE(13669, 8361, 88, 7003, new Item(8788, 5), new Item(8786, 4), new Item(526, 5), new Item(964, 2)),
	CRYSTAL_THRONE(13670, 8362, 95, 15000, new Item(8788, 15)),
	DEMONIC_THRONE(13671, 8363, 99, 25000, new Item(8788, 25)),
	OAK_LEVER(13672, 8364, 68, 300, new Item(8778, 5)),
	TEAK_LEVER(13673, 8365, 78, 450, new Item(8780, 5)),
	MAHOGANY_LEVER(13674, 8366, 88, 700, new Item(8782, 5)),
	FLOOR_DECORATION(new int[] { 13689, 13686, 13687, 13688, 13684, 13685 }, 8370, 61, 700, new Item(8782, 5)), 
	STEEL_CAGE(new int[] { 13689, 13686, 13687, 13688, 13684, 13685 }, 8371, 68, 1100, new Item(8782, 5), new Item(2353, 20)), 
	FLOOR_TRAP(new int[] { 13689, 13686, 13687, 13688, 13684, 13685 }, 8372, 74, 770, new Item(8782, 5), new Item(8792, 10)), 
	MAGIC_CIRCLE(new int[] { 13689, 13686, 13687, 13688, 13684, 13685 }, 8373, 82, 2700, new Item(8782, 5), new Item(8788, 2)), 
	MAGIC_CAGE(new int[] { 13689, 13686, 13687, 13688, 13684, 13685 }, 8374, 89, 4700, new Item(8782, 5), new Item(8788, 4)), 
	OAK_TRAPDOOR(13675, 8367, 68, 300, new Item(8778, 5)),
	TEAK_TRAPDOOR(13676, 8368, 78, 450, new Item(8780, 5)),
	MAHOGANY_TRAPDOOR(13677, 8369, 88, 700, new Item(8782, 5)),
	CARVED_TEAK_BENCH(13694, 8112, 44, 360, new Item(8780, 4)),
	MAHOGANY_BENCH(13695, 8113, 52, 560, new Item(8782, 4)),
	GILDED_BENCH(13696, 8114, 61, 1760, new Item(8782, 4), new Item(8784, 4)),
	OAK_DECO(13798, 8102, 16, 120.0, new Item(8778, 2)),
	TEAK_DECO(13814, 8103, 36, 180.0, new Item(8780, 2)),
	GILDED_DECO(13782, 8104, 56, 1020.0, new Item(8782, 3), new Item(8784, 2)),
	ROUND_SHIELD(13734, 8105, 66, 120, new Item(8778, 2)),
	SQUARE_SHIELD(13766, 8106, 76, 360, new Item(8780, 4)),
	KITE_SHIELD(13750, 8107, 86, 420, new Item(8782, 3)),
	
	/**
	 * Oubliette
	 */
	SPIKES_MID(13334, 8302, 65, 623, new Item(2353, 20), new Item(995, 50000)),
	SPIKES_SIDE(13335, 8302, 65, 623, new Item(2353, 20), new Item(995, 50000)),
	SPIKES_CORNER(13336, 8302, 65, 623, new Item(2353, 20), new Item(995, 50000)),
	SPIKES_FL(13338, 8302, 65, 623, new Item(2353, 20), new Item(995, 50000)),
	TENTACLE_MID(13331, 8303, 71, 326, new Item(1929, 20), new Item(995, 100000)),
	TENTACLE_SIDE(13332, 8303, 71, 326, new Item(1929, 20), new Item(995, 100000)),
	TENTACLE_CORNER(13333, 8303, 71, 326, new Item(1929, 20), new Item(995, 100000)),
	TENTACLE_FL(13338, 8303, 71, 326, new Item(1929, 20), new Item(995, 100000)),
	FP_FLOOR_MID(13371, 8304, 77, 357, new Item(590, 20), new Item(995, 125000)),
	FP_FLOOR_SIDE(13371, 8304, 77, 357, new Item(590, 20), new Item(995, 125000)),
	FP_FLOOR_CORNER(13371, 8304, 77, 357, new Item(590, 20), new Item(995, 125000)),
	FLAME_PIT(13337, 8304, 77, 357, new Item(590, 20), new Item(995, 125000)),
	ROCNAR_FLOOR_MID(13371, 8305, 83, 387, new Item(995, 150000)),
	ROCNAR_FLOOR_SIDE(13371, 8305, 83, 387, new Item(995, 150000)),
	ROCNAR_FLOOR_CORNER(13371, 8305, 83, 387, new Item(995, 150000)),
	ROCNAR(13373, 8305, 83, 387, new Item(995, 150000)),
	ROCNAR_FL(13338, 8305, 83, 387, new Item(995, 150000)),
	OAK_CAGE(13313, 8297, 65, 640, new Item(8778, 10), new Item(2353, 2)),
	OAK_CAGE_DOOR(13314, 8297, 65, 640, new Item(8778, 10), new Item(2353, 2)),
	OAK_STEEL_CAGE(13316, 8298, 70, 800, new Item(8778, 10), new Item(2353, 10)),
	OAK_STEEL_CAGE_DOOR(13317, 8298, 70, 800, new Item(8778, 10), new Item(2353, 10)),
	STEEL_CAGE_OU(13319, 8299, 75, 400, new Item(2353, 20)),
	STEEL_CAGE_DOOR(13320, 8299, 75, 400, new Item(2353, 20)),
	SPIKED_CAGE(13322, 8300, 80, 500, new Item(2353, 25)),
	SPIKED_CAGE_DOOR(13323, 8300, 80, 500, new Item(2353, 25)),
	BONE_CAGE(13325, 8301, 85, 603, new Item(8778, 10), new Item(526, 10)),
	BONE_CAGE_DOOR(13326, 8301, 85, 603, new Item(8778, 10), new Item(526, 10)),
	SKELETON_GUARD(13366, 8131, 70, 223, new Item(995, 50000)),
	GUARD_DOG(13367, 8132, 74, 273, new Item(995, 75000)),
	HOBGOBLIN(13368, 8133, 78, 316, new Item(995, 100000)),
	BABY_RED_DRAGON(13372, 8134, 82, 387, new Item(995, 150000)),
	HUGE_SPIDER(13370, 8135, 86, 447, new Item(995, 200000)),
	TROLL(13369, 8136, 90, 1000, new Item(995, 1000000)),
	HELLHOUND(2715, 8137, 94, 2236, new Item(995, 5000000)),
	OAK_LADDER(13328, 8306, 68, 300, new Item(8778, 5)),
	TEAK_LADDER(13329, 8307, 78, 450, new Item(8780, 5)),
	MAHOGANY_LADDER(13330, 8308, 88, 700, new Item(8782, 5)),
	DECORATIVE_BLOOD(13312, 8125, 72, 4, new Item(1763, 4)),
	DECORATIVE_PIPE(13311, 8126, 83, 120, new Item(2353, 6)),
	HANGING_SKELETON(13310, 8127, 94, 3, new Item(964, 2), new Item(526, 6)),
	CANDLE(13342, 8128, 72, 243, new Item(8778, 4), new Item(33, 4)),
	TORCH(13341, 8129, 84, 244, new Item(8778, 4), new Item(594, 4)),
	SKULL_TORCH(13343, 8130, 94, 246, new Item(8778, 4), new Item(594, 4), new Item(964, 4)),
	
	/**
	 * Dungeon corridor, junction, stairs & pit
	 */
	OAK_DOOR_LEFT(13344, 8122, 74, 600, new Item(8778, 10)),
	OAK_DOOR_RIGHT(13345, 8122, 74, 600, new Item(8778, 10)),
	STEEL_DOOR_LEFT(13346, 8123, 84, 800, new Item(8778, 10), new Item(2353, 10)),
	STEEL_DOOR_RIGHT(13347, 8123, 84, 800, new Item(8778, 10), new Item(2353, 10)),
	MARBLE_DOOR_LEFT(13348, 8124, 94, 2000, new Item(8786, 4)),
	MARBLE_DOOR_RIGHT(13349, 8124, 94, 2000, new Item(8786, 4)),
	SPIKE_TRAP(13356, 8143, 72, 223, new Item(995, 50000)),
	MAN_TRAP(13357, 8144, 76, 273, new Item(995, 75000)),
	TANGLE_TRAP(13358, 8145, 80, 316, new Item(995, 100000)),
	MARBLE_TRAP(13359, 8146, 84, 387, new Item(995, 150000)),
	TELEPORT_TRAP(13360, 8147, 88, 447, new Item(995, 200000)),

	/*     objID, int, lvl, exp, materials */
	PIT_DOG(39260, 18791, 70, 200, new Item(995, 40000)),
	PIT_OGRE(39261, 18792, 73, 234, new Item(995, 55000)),
	PIT_ROCK_PROTECTOR(39262, 18793, 79, 300, new Item(995, 90000)),
	PIT_SCABARITE(39263, 18794, 84, 387, new Item(995, 150000)),
	PIT_BLACK_DEMON(39264, 18795, 89, 547, new Item(995, 300000)),
	PIT_IRON_DRAGON(39265, 18796, 97, 2738, new Item(995, 7500000)),

	/**
	 * Treasure room
	 */
	DEMON(13378, 8138, 75, 707, new Item(995, 500000)),
	KALPHITE_SOLDIER(13374, 8139, 80, 866, new Item(995, 750000)),
	TOK_XIL(13377, 8140, 85, 2236, new Item(995, 5000000)),
	DAGANNOTH(13376, 8141, 90, 2738, new Item(995, 7500000)),
	STEEL_DRAGON(13375, 8142, 95, 3162, new Item(995, 1000000)),
	WOODEN_CRATE(13283, 8148, 75, 143, new Item(960, 5)),
	OAK_T_CHEST(13285, 8149, 79, 340, new Item(8778, 5), new Item(2353, 2)),
	TEAK_T_CHEST(13287, 8150, 83, 530, new Item(8780, 5), new Item(2353, 4)),
	MGANY_T_CHEST(13289, 8151, 87, 1000, new Item(8782, 5), new Item(8784, 1)),
	MAGIC_CHEST(13291, 8152, 91, 1000, new Item(8788, 1)),
	
	/**
	 * Style related decoration.
	 */
	BASIC_WOOD_WINDOW(13099, -1, 1, 0.0), 
	BASIC_STONE_WINDOW(13091, -1, 1, 0.0),
	WHITEWASHED_STONE_WINDOW(13005, -1, 1, 0.0), 
	FREMENNIK_WINDOW(13112, -1, 1, 0.0), 
	TROPICAL_WOOD_WINDOW(10816, -1, 1, 0.0), 
	FANCY_STONE_WINDOW(13117, -1, 1, 0.0),
	
	;
	/**
	 * The object id.
	 */
	private final int objectId;
	
	/**
	 * The item id for the interface.
	 */
	private final int interfaceItem;

	/**
	 * The level requirement.
	 */
	private final int level;
	
	/**
	 * The experience gained for building this decoration.
	 */
	private final double experience;
	
	/**
	 * The item required.
	 */
	private final Item[] items;
	
	/**
	 * The tools required.
	 */
	private final int[] tools;

	/**
	 * The object ids depending on styling.
	 */
	private final int[] objectIds;
	
	/**
	 * If this node should be invisible to user build options
	 */
	private boolean invisibleNode;
	
	/**
	 * Constructs a new {@code Portal} {@code Object}.
	 * @param objectId The object id.
	 * @param interfaceItem The item id for the building interface.
	 * @param level The level required.
	 * @param experience The experience gained.
	 * @param items The items required.
	 */
	private Decoration(int objectId, int interfaceItem, int level, double experience, Item... items) {
		this(objectId, interfaceItem, level, experience, new int[] { 2347, 8794 }, items);
	}
	
	/**
	 * Constructs a new {@code Portal} {@code Object}.
	 * @param objectId The object id.
	 * @param interfaceItem The item id for the building interface.
	 * @param level The level required.
	 * @param experience The experience gained.
	 * @param items The items required.
	 */
	private Decoration(int objectId, int interfaceItem, int level, double experience, int[] tools, Item... items) {
		this.objectId = objectId;
		this.objectIds = null;
		this.interfaceItem = interfaceItem;
		this.level = level;
		this.experience = experience;
		this.tools = tools;
		this.items = items;
	}
	
	/**
	 * Decoration
	 * @param objectId
	 * @param invisibleNode
	 */
	private Decoration(int objectId, boolean invisibleNode) {
		this(objectId, -1, -1, -1);
		this.invisibleNode = true;
	}
	
	/**
	 * Constructs a new {@code Portal} {@code Object}.
	 * @param objectIds The object id.
	 * @param interfaceItem The item id for the building interface.
	 * @param level The level required.
	 * @param experience The experience gained.
	 * @param items The items required.
	 */
	private Decoration(int[] objectIds, int interfaceItem, int level, double experience, Item... items) {
		this(objectIds, interfaceItem, level, experience, new int[] { 2347, 8794 }, items);
	}
	
	/**
	 * Constructs a new {@code Portal} {@code Object}.
	 * @param objectIds The object id.
	 * @param interfaceItem The item id for the building interface.
	 * @param level The level required.
	 * @param experience The experience gained.
	 * @param items The items required.
	 */
	private Decoration(int[] objectIds, int interfaceItem, int level, double experience, int[] tools, Item... items) {
		this.objectId = objectIds[0];
		this.objectIds = objectIds;
		this.interfaceItem = interfaceItem;
		this.level = level;
		this.experience = experience;
		this.tools = tools;
		this.items = items;
	}

	/**
	 * Gets the decoration on the given location.
	 * @param player The player.
	 * @return The decoration.
	 */
	public static Decoration getDecoration(Player player, GameObject object) {
		Location l = object.getLocation();
		int z = l.getZ();
		if (HouseManager.isInDungeon(player)) {
			z = 3;
		}
		Room room = player.getHouseManager().getRooms()[z][l.getChunkX()][l.getChunkY()];
		for (Hotspot h : room.getHotspots()) {
			if (h.getCurrentX() == l.getChunkOffsetX() && h.getCurrentY() == l.getChunkOffsetY()) {
				if (h.getDecorationIndex() != -1) {
					Decoration deco = h.getHotspot().getDecorations()[h.getDecorationIndex()];
					if (deco.getObjectId(player.getHouseManager().getStyle()) == object.getId()) {
						return deco;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets a decoration for the given object id
	 * @param objectId - the object id of the built object
	 * @return the decoration or null
	 */
	public static Decoration forObjectId(int objectId) {
		for (Decoration d : Decoration.values()) {
			if (d.getObjectId() == objectId) {
				return d;
			}
		}
		return null;
	}
	
	public static Decoration forName(String name) {
		for (Decoration d : Decoration.values()) {
			if (d.name().equals(name)) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Gets the amount of nails required for this hotspot.
	 * @return The amount of nails.
	 */
	public int getNailAmount() {
		for (Item item : items) {
			if (item.getId() == 960) { //1 nail per normal plank required.
				return item.getAmount();
			}
		}
		return 0;
	}
	
	/**
	 * Gets the objectId.
	 * @param style The current housing style.
	 * @return The objectId.
	 */
	public int getObjectId(HousingStyle style) {
		if (objectIds != null) {
			return objectIds[style.ordinal()];
		}
		return objectId;
	}
	
	/**
	 * Gets the objectId.
	 * @return The objectId.
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the experience.
	 * @return The experience.
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * Gets the items.
	 * @return The items.
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * Gets the tools.
	 * @return The tools.
	 */
	public int[] getTools() {
		return tools;
	}

	/**
	 * Gets the interfaceItem.
	 * @return the interfaceItem
	 */
	public int getInterfaceItem() {
		return interfaceItem;
	}

	/**
	 * Gets the objectIds value.
	 * @return The objectIds.
	 */
	public int[] getObjectIds() {
		return objectIds;
	}

	public boolean isInvisibleNode() {
		return invisibleNode;
	}
	
	

}