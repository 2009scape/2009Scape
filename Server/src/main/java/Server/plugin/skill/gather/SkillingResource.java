package plugin.skill.gather;

import core.ServerConstants;
import plugin.skill.Skills;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a skill resource.
 * @author Emperor
 */
public enum SkillingResource {

	/**
	 * Standard tree (Woodcutting).
	 */
	STANDARD_TREE_1(1276, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_2(1277, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1343, Skills.WOODCUTTING),
	STANDARD_TREE_3(1278, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_4(1279, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1345, Skills.WOODCUTTING),
	STANDARD_TREE_5(1280, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1343, Skills.WOODCUTTING),
	STANDARD_TREE_6(1330, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1341, Skills.WOODCUTTING),
	STANDARD_TREE_7(1331, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1341, Skills.WOODCUTTING),
	STANDARD_TREE_8(1332, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1341, Skills.WOODCUTTING),
	STANDARD_TREE_9(2409, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_10(3033, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1345, Skills.WOODCUTTING),
	STANDARD_TREE_11(3034, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1345, Skills.WOODCUTTING),
	STANDARD_TREE_12(3035, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1347, Skills.WOODCUTTING),
	STANDARD_TREE_13(3036, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1351, Skills.WOODCUTTING),
	STANDARD_TREE_14(3879, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 3880, Skills.WOODCUTTING),
	STANDARD_TREE_15(3881, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 3880, Skills.WOODCUTTING),
	STANDARD_TREE_16(3882, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 3880, Skills.WOODCUTTING),
	STANDARD_TREE_17(3883, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 3884, Skills.WOODCUTTING),
	STANDARD_TREE_18(10041, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_19(14308, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_20(14309, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_21(16264, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_22(16265, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_23(30132, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_24(30133, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_25(37477, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 1342, Skills.WOODCUTTING),
	STANDARD_TREE_26(37478, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 37653, Skills.WOODCUTTING),
	STANDARD_TREE_27(37652, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "tree", null, 37653, Skills.WOODCUTTING),

	/**
	 * Fruit trees.
	 */
	APPLE_TREE(7941, 1, 0.05, 50 | 100 << 16, 25.0, -1, 1, "tree", null, 37653, Skills.WOODCUTTING, true),
	BANANA_TREE(8000, 1, 0.05, 50 | 100 << 16, 25.0, -1, 1, "tree", null, 37653, Skills.WOODCUTTING, true),
	ORANGE_TREE(8057, 1, 0.05, 50 | 100 << 16, 25.0, -1, 1, "tree", null, 37653, Skills.WOODCUTTING, true),
	CURRY_TREE(8026, 1, 0.05, 50 | 100 << 16, 25.0, -1, 1, "tree", null, 37653, Skills.WOODCUTTING, true),
	PINEAPPLE_TREE(7972, 1, 0.05, 50 | 100 << 16, 25.0, -1, 1, "tree", null, 37653, Skills.WOODCUTTING, true),
	PAPAYA_TREE(8111, 1, 0.05, 50 | 100 << 16, 25.0, -1, 1, "tree", null, 37653, Skills.WOODCUTTING, true),
	PALM_TREE(8084, 1, 0.05, 50 | 100 << 16, 25.0, -1, 1, "tree", null, 37653, Skills.WOODCUTTING, true),

	/**
	 * Dead tree (Woodcutting).
	 */
	DEAD_TREE_1(1282, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1347, Skills.WOODCUTTING),
	DEAD_TREE_2(1283, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1347, Skills.WOODCUTTING),
	DEAD_TREE_3(1284, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1348, Skills.WOODCUTTING),
	DEAD_TREE_4(1285, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1349, Skills.WOODCUTTING),
	DEAD_TREE_5(1286, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1351, Skills.WOODCUTTING),
	DEAD_TREE_6(1289, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1353, Skills.WOODCUTTING),
	DEAD_TREE_7(1290, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1354, Skills.WOODCUTTING),
	DEAD_TREE_8(1291, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 23054, Skills.WOODCUTTING),
	DEAD_TREE_9(1365, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1352, Skills.WOODCUTTING),
	DEAD_TREE_10(1383, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1358, Skills.WOODCUTTING),
	DEAD_TREE_11(1384, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1359, Skills.WOODCUTTING),
	DEAD_TREE_12(5902, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1347, Skills.WOODCUTTING),
	DEAD_TREE_13(5903, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1353, Skills.WOODCUTTING),
	DEAD_TREE_14(5904, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1353, Skills.WOODCUTTING),
	DEAD_TREE_15(32294, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1353, Skills.WOODCUTTING),
	DEAD_TREE_16(37481, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1347, Skills.WOODCUTTING),
	DEAD_TREE_17(37482, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1351, Skills.WOODCUTTING),
	DEAD_TREE_18(37483, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dead tree", null, 1358, Skills.WOODCUTTING),
	DEAD_TREE_19(24168, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "dying tree", null, 24169, Skills.WOODCUTTING),

	/**
	 * Dramen tree (Woodcutting/Lost City quest).
	 */
	DRAMEN_TREE(1292, 1, 0.05, -1, 25.0, 771, Integer.MAX_VALUE, "dramen tree", null, -1, Skills.WOODCUTTING),

	/**
	 * Evergreen (Woodcutting).
	 */
	EVERGREEN_1(1315, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "evergreen", null, 1342, Skills.WOODCUTTING),
	EVERGREEN_2(1316, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "evergreen", null, 1355, Skills.WOODCUTTING),
	EVERGREEN_3(1318, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "evergreen", null, 1355, Skills.WOODCUTTING),
	EVERGREEN_4(1319, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 1, "evergreen", null, 1355, Skills.WOODCUTTING),

	/**
	 * Jungle tree (Woodcutting).
	 */
	JUNGLE_TREE_1(2887, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 2, "jungle tree", null, 0, Skills.WOODCUTTING),
	JUNGLE_TREE_2(2889, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 2, "jungle tree", null, 0, Skills.WOODCUTTING),
	JUNGLE_TREE_3(2890, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 2, "jungle tree", null, 0, Skills.WOODCUTTING),
	JUNGLE_TREE_4(4818, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 2, "jungle tree", null, 0, Skills.WOODCUTTING),
	JUNGLE_TREE_5(4820, 1, 0.05, 50 | 100 << 16, 25.0, 1511, 2, "jungle tree", null, 0, Skills.WOODCUTTING),

	JUNGLE_BUSH_1(2892, 1, 0.15, 50 | 100 << 16, 100.0, 1511, 1, "jungle bush", null, 2894, Skills.WOODCUTTING),
	JUNGLE_BUSH_2(2893, 1, 0.15, 50 | 100 << 16, 100.0, 1511, 1, "jungle bush", null, 2895, Skills.WOODCUTTING),

	/**
	 * Achey tree (Woodcutting).
	 */
	ACHEY_TREE(2023, 1, 0.05, 50 | 100 << 16, 25.0, 2862, 1, "achey tree", null, 3371, Skills.WOODCUTTING),

	/**
	 * Oak tree (Woodcutting).
	 */
	OAK_TREE_1(1281, 15, 0.15, 14 | 22 << 16, 37.5, 1521, 10, "oak tree", null, 1356, Skills.WOODCUTTING),
	OAK_TREE_2(3037, 15, 0.15, 14 | 22 << 16, 37.5, 1521, 10, "oak tree", null, 1357, Skills.WOODCUTTING),
	OAK_TREE_3(37479, 15, 0.15, 14 | 22 << 16, 37.5, 1521, 10, "oak tree", null, 1356, Skills.WOODCUTTING),
	OAK_TREE_4(8467, 15, 0.15, 14 | 22 << 16, 37.5, 1521, 10, "oak tree", null, 1356, Skills.WOODCUTTING, true),

	/**
	 * Willow tree (Woodcutting).
	 */
	WILLOW_TREE_1(1308, 30, 0.3, 14 | 22 << 16, 67.8, 1519, 20, "willow tree", null, 7399, Skills.WOODCUTTING),
	WILLOW_TREE_2(5551, 30, 0.3, 14 | 22 << 16, 67.8, 1519, 20, "willow tree", null, 5554, Skills.WOODCUTTING),
	WILLOW_TREE_3(5552, 30, 0.3, 14 | 22 << 16, 67.8, 1519, 20, "willow tree", null, 5554, Skills.WOODCUTTING),
	WILLOW_TREE_4(5553, 30, 0.3, 14 | 22 << 16, 67.8, 1519, 20, "willow tree", null, 5554, Skills.WOODCUTTING),
	WILLOW_TREE_5(37480, 30, 0.3, 14 | 22 << 16, 67.8, 1519, 20, "willow tree", null, 7399, Skills.WOODCUTTING),
	WILLOW_TREE_6(8488, 30, 0.3, 14 | 22 << 16, 67.8, 1519, 20, "willow tree", null, 7399, Skills.WOODCUTTING, true),

	/**
	 * Teak (Woodcutting).
	 */
	TEAK_1(9036, 35, 0.7, 35 | 60 << 16, 85.0, 6333, 25, "teak", null, 9037, Skills.WOODCUTTING),
	TEAK_2(15062, 35, 0.7, 35 | 60 << 16, 85.0, 6333, 25, "teak", null, 9037, Skills.WOODCUTTING),

	/**
	 * Maple tree (Woodcutting).
	 */
	MAPLE_TREE_1(1307, 45, 0.65, 58 | 100 << 16, 100.0, 1517, 30, "maple tree", null, 7400, Skills.WOODCUTTING),
	MAPLE_TREE_2(4674, 45, 0.65, 58 | 100 << 16, 100.0, 1517, 30, "maple tree", null, 7400, Skills.WOODCUTTING),
	MAPLE_TREE_3(8444, 45, 0.65, 58 | 100 << 16, 100.0, 1517, 30, "maple tree", null, 7400, Skills.WOODCUTTING, true),

	/**
	 * Hollow tree (Woodcutting).
	 */
	HOLLOW_TREE_1(2289, 45, 0.6, 58 | 100 << 16, 82.5, 3239, 30, "hollow tree", null, 2310, Skills.WOODCUTTING),
	HOLLOW_TREE_2(4060, 45, 0.6, 58 | 100 << 16, 82.5, 3239, 30, "hollow tree", null, 4061, Skills.WOODCUTTING),

	/**
	 * Mahogany (Woodcutting).
	 */
	MAHOGANY(9034, 50, 0.7, 62 | 115 << 16, 125.0, 6332, 35, "mahogany", null, 9035, Skills.WOODCUTTING),

	/**
	 * Arctic pine (Woodcutting).
	 */
	ARCTIC_PINE(21273, 54, 0.73, 75 | 130 << 16, 140.2, 10810, 35, "arctic pine", null, 21274, Skills.WOODCUTTING),

	/**
	 * Eucalyptus tree (Woodcutting).
	 */
	EUCALYPTUS_1(28951, 58, 0.77, 80 | 140 << 16, 165.0, 12581, 35, "eucalyptus tree", null, 28954, Skills.WOODCUTTING),
	EUCALYPTUS_2(28952, 58, 0.77, 80 | 140 << 16, 165.0, 12581, 35, "eucalyptus tree", null, 28955, Skills.WOODCUTTING),
	EUCALYPTUS_3(28953, 58, 0.77, 80 | 140 << 16, 165.0, 12581, 35, "eucalyptus tree", null, 28956, Skills.WOODCUTTING),

	/**
	 * Yew tree (Woodcutting).
	 */
	YEW(1309, 60, 0.8, 100 | 162 << 16, 175.0, 1515, 40, "yew", null, 7402, Skills.WOODCUTTING),
	YEW_1(8513, 60, 0.8, 100 | 162 << 16, 175.0, 1515, 40, "yew", null, 7402, Skills.WOODCUTTING, true),

	/**
	 * Magic tree (Woodcutting).
	 */
	MAGIC_TREE_1(1306, 75, 0.9, 200 | 317 << 16, 250.0, 1513, 50, "magic tree", null, 7401, Skills.WOODCUTTING),
	MAGIC_TREE_2(37823, 75, 0.9, 200 | 317 << 16, 250.0, 1513, 50, "magic tree", null, 37824, Skills.WOODCUTTING),
	MAGIC_TREE_3(8409, 75, 0.9, 200 | 317 << 16, 250.0, 1513, 50, "magic tree", null, 37824, Skills.WOODCUTTING, true),

	CURSED_MAGIC_TREE(37821, 82, 0.95, 200 | 317 << 16, 275.0, 1513, 50, "magic tree", null, 37822, Skills.WOODCUTTING),

	/**
	 * Copper ore (Mining).
	 */
	COPPER_ORE_0(2090, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 450, Skills.MINING),
	COPPER_ORE_1(2091, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 452, Skills.MINING),
	COPPER_ORE_2(4976, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 4994, Skills.MINING),
	COPPER_ORE_3(4977, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 4995, Skills.MINING),
	COPPER_ORE_4(4978, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 4996, Skills.MINING),
	COPPER_ORE_5(9710, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 18954, Skills.MINING),
	COPPER_ORE_6(9709, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 32448, Skills.MINING),
	COPPER_ORE_7(9708, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 32447, Skills.MINING),
	COPPER_ORE_8(11960, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 11555, Skills.MINING),
	COPPER_ORE_9(11961, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 11556, Skills.MINING),
	COPPER_ORE_10(11962, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 11557, Skills.MINING),
	COPPER_ORE_11(11937, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 11553, Skills.MINING),
	COPPER_ORE_12(11936, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 11552, Skills.MINING),
	COPPER_ORE_13(11938, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 11554, Skills.MINING),
	COPPER_ORE_14(12746, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 450, Skills.MINING),
	COPPER_ORE_15(14906, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 14894, Skills.MINING),
	COPPER_ORE_16(14907, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 14895, Skills.MINING),
	COPPER_ORE_17(20448, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 20445, Skills.MINING),
	COPPER_ORE_18(20451, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 20445, Skills.MINING),
	COPPER_ORE_19(20446, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 20443, Skills.MINING),
	COPPER_ORE_20(20447, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 20444, Skills.MINING),
	COPPER_ORE_21(20408, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 20407, Skills.MINING),
	COPPER_ORE_22(18993, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 19005, Skills.MINING),
	COPPER_ORE_23(18992, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 19004, Skills.MINING),
	COPPER_ORE_24(19007, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 19016, Skills.MINING),
	COPPER_ORE_25(19006, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 19021, Skills.MINING),
	COPPER_ORE_26(18991, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 19003, Skills.MINING),
	COPPER_ORE_27(19008, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 19017, Skills.MINING),
	COPPER_ORE_28(21285, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 21297, Skills.MINING),
	COPPER_ORE_29(21284, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 21296, Skills.MINING),
	COPPER_ORE_30(21286, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 21298, Skills.MINING),
	COPPER_ORE_31(29231, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 29219, Skills.MINING),
	COPPER_ORE_32(29230, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 29218, Skills.MINING),
	COPPER_ORE_33(29232, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 29220, Skills.MINING),
	COPPER_ORE_34(31082, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 37650, Skills.MINING),
	COPPER_ORE_35(31081, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 37649, Skills.MINING),
	COPPER_ORE_36(31080, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 37639, Skills.MINING),
	COPPER_ORE_37(37647, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 37650, Skills.MINING),
	COPPER_ORE_38(37646, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 37649, Skills.MINING),
	COPPER_ORE_39(37645, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 37639, Skills.MINING),
	COPPER_ORE_40(37637, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 37639, Skills.MINING),
	COPPER_ORE_41(37688, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 21298, Skills.MINING),
	COPPER_ORE_42(37686, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 21296, Skills.MINING),
	COPPER_ORE_43(37687, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 21297, Skills.MINING),
	COPPER_ORE_44(3042, 1, 0.05, 4 | 8 << 16, 17.5, 436, 1, "copper rocks", null, 450, Skills.MINING),

	/**
	 * Tin ore (Mining).
	 */
	TIN_ORE_0(2094, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 450, Skills.MINING),
	TIN_ORE_1(2095, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 452, Skills.MINING),
	TIN_ORE_2(3043, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 450, Skills.MINING),
	TIN_ORE_3(4979, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 4994, Skills.MINING),
	TIN_ORE_4(4980, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 4995, Skills.MINING),
	TIN_ORE_5(4981, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 4996, Skills.MINING),
	TIN_ORE_6(11957, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 11555, Skills.MINING),
	TIN_ORE_7(11958, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 11556, Skills.MINING),
	TIN_ORE_8(11959, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 11557, Skills.MINING),
	TIN_ORE_9(11934, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 11553, Skills.MINING),
	TIN_ORE_10(11935, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 11554, Skills.MINING),
	TIN_ORE_11(11933, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 11552, Skills.MINING),
	TIN_ORE_12(14902, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 14894, Skills.MINING),
	TIN_ORE_13(14903, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 14895, Skills.MINING),
	TIN_ORE_14(18995, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 19004, Skills.MINING),
	TIN_ORE_15(18994, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 19003, Skills.MINING),
	TIN_ORE_16(18996, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 19005, Skills.MINING),
	TIN_ORE_17(19025, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 19016, Skills.MINING),
	TIN_ORE_18(19024, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 19021, Skills.MINING),
	TIN_ORE_19(19026, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 19017, Skills.MINING),
	TIN_ORE_20(21293, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 21296, Skills.MINING),
	TIN_ORE_21(21295, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 21298, Skills.MINING),
	TIN_ORE_22(21294, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 21297, Skills.MINING),
	TIN_ORE_23(29227, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 29218, Skills.MINING),
	TIN_ORE_24(29229, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 29220, Skills.MINING),
	TIN_ORE_25(29228, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 29219, Skills.MINING),
	TIN_ORE_26(31079, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 37650, Skills.MINING),
	TIN_ORE_27(31078, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 37649, Skills.MINING),
	TIN_ORE_28(31077, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 37639, Skills.MINING),
	TIN_ORE_29(37644, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 37650, Skills.MINING),
	TIN_ORE_30(37643, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 37649, Skills.MINING),
	TIN_ORE_31(37642, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 37639, Skills.MINING),
	TIN_ORE_32(37638, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 37639, Skills.MINING),
	TIN_ORE_33(37685, 1, 0.05, 4 | 8 << 16, 17.5, 438, 1, "tin rocks", null, 21298, Skills.MINING),

	/**
	 * Rune/Pure essence (Mining).
	 */
	RUNE_ESSENCE(2491, 1, 0.1, 1 | 1 << 16, 5.0, 1436, Integer.MAX_VALUE, "rune essence", null, -1, Skills.MINING),

	/**
	 * Clay (Mining).
	 */
	CLAY_0(2109, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 452, Skills.MINING),
	CLAY_1(2108, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 450, Skills.MINING),
	CLAY_2(9712, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 32448, Skills.MINING),
	CLAY_3(9713, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 18954, Skills.MINING),
	CLAY_4(9711, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 32447, Skills.MINING),
	CLAY_5(10949, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 10945, Skills.MINING),
	CLAY_6(11190, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 21297, Skills.MINING),
	CLAY_7(11191, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 21298, Skills.MINING),
	CLAY_8(11189, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 21296, Skills.MINING),
	CLAY_9(12942, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 4995, Skills.MINING),
	CLAY_10(12943, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 4996, Skills.MINING),
	CLAY_11(12941, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 4994, Skills.MINING),
	CLAY_12(14904, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 14894, Skills.MINING),
	CLAY_13(14905, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 14895, Skills.MINING),
	CLAY_14(15505, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 11557, Skills.MINING),
	CLAY_15(15504, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 11556, Skills.MINING),
	CLAY_16(15503, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 11555, Skills.MINING),
	CLAY_17(20449, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 20443, Skills.MINING),
	CLAY_18(20450, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 20444, Skills.MINING),
	CLAY_19(20409, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 20407, Skills.MINING),
	CLAY_20(32429, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 33400, Skills.MINING),
	CLAY_21(32430, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 33401, Skills.MINING),
	CLAY_22(32431, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 33402, Skills.MINING),
	CLAY_23(31062, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 37639, Skills.MINING),
	CLAY_24(31063, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 37649, Skills.MINING),
	CLAY_25(31064, 1, 0.1, 1 | 1 << 16, 5.0, 434, 1, "clay", null, 37650, Skills.MINING),

	/**
	 * Limestone.
	 */
	LIMESTONE_0(4027, 10, 0.2, 10 | 20 << 16, 26.5, 3211, 1, "limestone", null, 12564, Skills.MINING),
	LIMESTONE_1(4028, 10, 0.2, 10 | 20 << 16, 26.5, 3211, 1, "limestone", null, 12565, Skills.MINING),
	LIMESTONE_2(4029, 10, 0.2, 10 | 20 << 16, 26.5, 3211, 1, "limestone", null, 12566, Skills.MINING),
	LIMESTONE_3(4030, 10, 0.2, 10 | 20 << 16, 26.5, 3211, 1, "limestone", null, 12567, Skills.MINING),

	/**
	 * Blurite ore.
	 */
	BLURITE_ORE_0(33220, 10, 0.2, 10 | 20 << 16, 17.5, 668, 1, "blurite rocks", null, 33222, Skills.MINING),
	BLURITE_ORE_1(33221, 10, 0.2, 10 | 20 << 16, 17.5, 668, 1, "blurite rocks", null, 33223, Skills.MINING),

	/**
	 * Iron ore.
	 */
	IRON_ORE_0(2092, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 450, Skills.MINING),
	IRON_ORE_1(2093, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 452, Skills.MINING),
	IRON_ORE_2(4982, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 4994, Skills.MINING),
	IRON_ORE_3(4983, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 4995, Skills.MINING),
	IRON_ORE_4(4984, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 4996, Skills.MINING),
	IRON_ORE_5(6943, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 21296, Skills.MINING),
	IRON_ORE_6(6944, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 21297, Skills.MINING),
	IRON_ORE_7(9718, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 32448, Skills.MINING),
	IRON_ORE_8(9719, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 18954, Skills.MINING),
	IRON_ORE_9(9717, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 32447, Skills.MINING),
	IRON_ORE_10(11956, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 11557, Skills.MINING),
	IRON_ORE_11(11954, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 11555, Skills.MINING),
	IRON_ORE_12(11955, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 11556, Skills.MINING),
	IRON_ORE_13(14914, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 14895, Skills.MINING),
	IRON_ORE_14(14913, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 14894, Skills.MINING),
	IRON_ORE_15(14858, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 25373, Skills.MINING),
	IRON_ORE_16(14857, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 25372, Skills.MINING),
	IRON_ORE_17(14856, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 25371, Skills.MINING),
	IRON_ORE_18(14900, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 14894, Skills.MINING),
	IRON_ORE_19(14901, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 14895, Skills.MINING),
	IRON_ORE_20(20423, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 20444, Skills.MINING),
	IRON_ORE_21(20422, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 20443, Skills.MINING),
	IRON_ORE_22(20425, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 20407, Skills.MINING),
	IRON_ORE_23(20424, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 20445, Skills.MINING),
	IRON_ORE_24(19002, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 19005, Skills.MINING),
	IRON_ORE_25(19001, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 19004, Skills.MINING),
	IRON_ORE_26(19000, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 19003, Skills.MINING),
	IRON_ORE_27(21281, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 21296, Skills.MINING),
	IRON_ORE_28(21283, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 21298, Skills.MINING),
	IRON_ORE_29(21282, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 21297, Skills.MINING),
	IRON_ORE_30(29221, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 29218, Skills.MINING),
	IRON_ORE_31(29223, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 29220, Skills.MINING),
	IRON_ORE_32(29222, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 29219, Skills.MINING),
	IRON_ORE_33(32441, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 33400, Skills.MINING),
	IRON_ORE_34(32443, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 33402, Skills.MINING),
	IRON_ORE_35(32442, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 33401, Skills.MINING),
	IRON_ORE_36(32452, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 32448, Skills.MINING),
	IRON_ORE_37(32451, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 32447, Skills.MINING),
	IRON_ORE_38(31073, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 37650, Skills.MINING),
	IRON_ORE_39(31072, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 37649, Skills.MINING),
	IRON_ORE_40(31071, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 37639, Skills.MINING),
	IRON_ORE_41(37307, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 11552, Skills.MINING),
	IRON_ORE_42(37309, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 11554, Skills.MINING),
	IRON_ORE_43(37308, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 11553, Skills.MINING),
	IRON_ORE_49(42034, 15, 0.2, 15 | 25 << 16, 35.0, 440, 1, "iron rocks", null, 450, Skills.MINING),

	/**
	 * Silver ore.
	 */
	SILVER_ORE_0(2101, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 452, Skills.MINING),
	SILVER_ORE_1(2100, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 450, Skills.MINING),
	SILVER_ORE_2(6945, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 21296, Skills.MINING),
	SILVER_ORE_3(6946, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 21297, Skills.MINING),
	SILVER_ORE_4(9716, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 18954, Skills.MINING),
	SILVER_ORE_5(9714, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 32447, Skills.MINING),
	SILVER_ORE_6(9715, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 32448, Skills.MINING),
	SILVER_ORE_7(11188, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 21298, Skills.MINING),
	SILVER_ORE_8(11186, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 21296, Skills.MINING),
	SILVER_ORE_9(11187, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 21297, Skills.MINING),
	SILVER_ORE_10(15581, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 14834, Skills.MINING),
	SILVER_ORE_11(15580, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 14833, Skills.MINING),
	SILVER_ORE_12(15579, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 14832, Skills.MINING),
	SILVER_ORE_13(16998, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 14915, Skills.MINING),
	SILVER_ORE_14(16999, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 14916, Skills.MINING),
	SILVER_ORE_15(17007, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 14915, Skills.MINING),
	SILVER_ORE_16(17000, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 31061, Skills.MINING),
	SILVER_ORE_17(17009, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 31061, Skills.MINING),
	SILVER_ORE_18(17008, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 14916, Skills.MINING),
	SILVER_ORE_19(17385, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 32447, Skills.MINING),
	SILVER_ORE_20(17387, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 18954, Skills.MINING),
	SILVER_ORE_21(17386, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 32448, Skills.MINING),
	SILVER_ORE_22(29225, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 29219, Skills.MINING),
	SILVER_ORE_23(29224, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 29218, Skills.MINING),
	SILVER_ORE_24(29226, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 29220, Skills.MINING),
	SILVER_ORE_25(32445, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 33401, Skills.MINING),
	SILVER_ORE_26(32444, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 33400, Skills.MINING),
	SILVER_ORE_27(32446, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 33402, Skills.MINING),
	SILVER_ORE_28(31075, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 37649, Skills.MINING),
	SILVER_ORE_29(31074, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 37639, Skills.MINING),
	SILVER_ORE_30(31076, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 37650, Skills.MINING),
	SILVER_ORE_31(37305, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 11553, Skills.MINING),
	SILVER_ORE_32(37304, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 11552, Skills.MINING),
	SILVER_ORE_33(37306, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 11554, Skills.MINING),
	SILVER_ORE_34(37670, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 11552, Skills.MINING),
	SILVER_ORE_35(11948, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 11555, Skills.MINING),
	SILVER_ORE_36(11949, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 11556, Skills.MINING),
	SILVER_ORE_37(11950, 20, 0.3, 100 | 200 << 16, 40.0, 442, 1, "silver rocks", null, 11557, Skills.MINING),

	/**
	 * Coal.
	 */
	COAL_0(2097, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 452, Skills.MINING),
	COAL_1(2096, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 450, Skills.MINING),
	COAL_2(4985, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 4994, Skills.MINING),
	COAL_3(4986, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 4995, Skills.MINING),
	COAL_4(4987, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 4996, Skills.MINING),
	COAL_5(4676, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 450, Skills.MINING),
	COAL_6(10948, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 10944, Skills.MINING),
	COAL_7(11964, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 11556, Skills.MINING),
	COAL_8(11965, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 11557, Skills.MINING),
	COAL_9(11963, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 11555, Skills.MINING),
	COAL_10(11932, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 11554, Skills.MINING),
	COAL_11(11930, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 11552, Skills.MINING),
	COAL_12(11931, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 11553, Skills.MINING),
	COAL_13(15246, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 15249, Skills.MINING),
	COAL_14(15247, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 15250, Skills.MINING),
	COAL_15(15248, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 15251, Skills.MINING),
	COAL_16(14852, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 25373, Skills.MINING),
	COAL_17(14851, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 25372, Skills.MINING),
	COAL_18(14850, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 25371, Skills.MINING),
	COAL_19(20410, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 20443, Skills.MINING),
	COAL_20(20411, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 20444, Skills.MINING),
	COAL_21(20412, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 20445, Skills.MINING),
	COAL_22(20413, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 20407, Skills.MINING),
	COAL_23(18999, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 19005, Skills.MINING),
	COAL_24(18998, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 19004, Skills.MINING),
	COAL_25(18997, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 19003, Skills.MINING),
	COAL_26(21287, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21296, Skills.MINING),
	COAL_27(21289, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21298, Skills.MINING),
	COAL_28(21288, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21297, Skills.MINING),
	COAL_29(23565, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21298, Skills.MINING),
	COAL_30(23564, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21297, Skills.MINING),
	COAL_31(23563, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21296, Skills.MINING),
	COAL_32(29215, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 29218, Skills.MINING),
	COAL_33(29217, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 29220, Skills.MINING),
	COAL_34(29216, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 29219, Skills.MINING),
	COAL_35(32426, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 33400, Skills.MINING),
	COAL_36(32427, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 33401, Skills.MINING),
	COAL_37(32428, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 33402, Skills.MINING),
	COAL_38(32450, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 32448, Skills.MINING),
	COAL_39(32449, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 32447, Skills.MINING),
	COAL_40(31068, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 37639, Skills.MINING),
	COAL_41(31069, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 37649, Skills.MINING),
	COAL_42(31070, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 37650, Skills.MINING),
	COAL_43(31168, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 14833, Skills.MINING),
	COAL_44(31169, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 14834, Skills.MINING),
	COAL_45(31167, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 14832, Skills.MINING),
	COAL_46(37699, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21298, Skills.MINING),
	COAL_47(37698, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21297, Skills.MINING),
	COAL_48(37697, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 21296, Skills.MINING),
	COAL_49(42035, 30, 0.4, 50 | 100 << 16, 50.0, 453, 1, "coal", null, 452, Skills.MINING),

	/**
	 * Sandstone.
	 */
	SANDSTONE(10946, 35, 0.2, 30 | 60 << 16, 30.0, 6971, 1, "sandstone", null, 10944, Skills.MINING),

	/**
	 * Gold ore.
	 */
	GOLD_ORE_0(2099, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 452, Skills.MINING),
	GOLD_ORE_1(2098, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 450, Skills.MINING),
	GOLD_ORE_2(2611, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21298, Skills.MINING),
	GOLD_ORE_3(2610, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21297, Skills.MINING),
	GOLD_ORE_4(2609, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21296, Skills.MINING),
	GOLD_ORE_5(9722, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 18954, Skills.MINING),
	GOLD_ORE_6(9720, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 32447, Skills.MINING),
	GOLD_ORE_7(9721, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 32448, Skills.MINING),
	GOLD_ORE_8(11183, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21296, Skills.MINING),
	GOLD_ORE_9(11184, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21297, Skills.MINING),
	GOLD_ORE_10(11185, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21298, Skills.MINING),
	GOLD_ORE_11(11952, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 11556, Skills.MINING),
	GOLD_ORE_12(11953, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 11557, Skills.MINING),
	GOLD_ORE_13(11951, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 11555, Skills.MINING),
	GOLD_ORE_14(15578, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 14834, Skills.MINING),
	GOLD_ORE_15(15577, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 14833, Skills.MINING),
	GOLD_ORE_16(15576, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 14832, Skills.MINING),
	GOLD_ORE_17(17002, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 14916, Skills.MINING),
	GOLD_ORE_18(17003, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 31061, Skills.MINING),
	GOLD_ORE_19(17001, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 14915, Skills.MINING),
	GOLD_ORE_20(21291, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21297, Skills.MINING),
	GOLD_ORE_21(21290, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21296, Skills.MINING),
	GOLD_ORE_22(21292, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 21298, Skills.MINING),
	GOLD_ORE_23(32433, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 33401, Skills.MINING),
	GOLD_ORE_24(32432, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 33400, Skills.MINING),
	GOLD_ORE_25(32434, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 33402, Skills.MINING),
	GOLD_ORE_26(31065, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 37639, Skills.MINING),
	GOLD_ORE_27(31066, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 37649, Skills.MINING),
	GOLD_ORE_28(31067, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 37650, Skills.MINING),
	GOLD_ORE_29(37311, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 11553, Skills.MINING),
	GOLD_ORE_30(37310, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 11552, Skills.MINING),
	GOLD_ORE_31(37312, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 11554, Skills.MINING),
	GOLD_ORE_32(37471, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 15249, Skills.MINING),
	GOLD_ORE_33(37473, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 15251, Skills.MINING),
	GOLD_ORE_34(37472, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 15250, Skills.MINING),
	GOLD_ORE_49(42033, 40, 0.6, 100 | 200 << 16, 65.0, 444, 1, "gold rocks", null, 452, Skills.MINING),
	 
	/**
	 * Granite.
	 */
	GRANITE(10947, 45, 0.2, 10 | 20 << 16, 50.0, 6979, 1, "granite", null, 10945, Skills.MINING),

	/**
	 * Rubium.
	 */
	RUBIUM(29746, 46, 0.6, 50 | 100 << 16, 17.5, 12630, 1, "rubium", null, 29747, Skills.MINING),

	/**
	 * Mithril ore.
	 */
	MITHRIL_ORE_0(2103, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 452, Skills.MINING),
	MITHRIL_ORE_1(2102, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 450, Skills.MINING),
	MITHRIL_ORE_2(4988, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 4994, Skills.MINING),
	MITHRIL_ORE_3(4989, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 4995, Skills.MINING),
	MITHRIL_ORE_4(4990, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 4996, Skills.MINING),
	MITHRIL_ORE_5(11943, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 11553, Skills.MINING),
	MITHRIL_ORE_6(11942, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 11552, Skills.MINING),
	MITHRIL_ORE_7(11945, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 11555, Skills.MINING),
	MITHRIL_ORE_8(11944, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 11554, Skills.MINING),
	MITHRIL_ORE_9(11947, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 11557, Skills.MINING),
	MITHRIL_ORE_10(11946, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 11556, Skills.MINING),
	MITHRIL_ORE_11(14855, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 25373, Skills.MINING),
	MITHRIL_ORE_12(14854, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 25372, Skills.MINING),
	MITHRIL_ORE_13(14853, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 25371, Skills.MINING),
	MITHRIL_ORE_14(16687, 50, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 450, Skills.MINING),
	MITHRIL_ORE_15(20421, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 20407, Skills.MINING),
	MITHRIL_ORE_16(20420, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 20445, Skills.MINING),
	MITHRIL_ORE_17(20419, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 20444, Skills.MINING),
	MITHRIL_ORE_18(20418, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 20443, Skills.MINING),
	MITHRIL_ORE_19(19012, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 19021, Skills.MINING),
	MITHRIL_ORE_20(19013, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 19016, Skills.MINING),
	MITHRIL_ORE_21(19014, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 19017, Skills.MINING),
	MITHRIL_ORE_22(21278, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 21296, Skills.MINING),
	MITHRIL_ORE_23(21279, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 21297, Skills.MINING),
	MITHRIL_ORE_24(21280, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 21298, Skills.MINING),
	MITHRIL_ORE_25(25369, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 10586, Skills.MINING),
	MITHRIL_ORE_26(25368, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 10585, Skills.MINING),
	MITHRIL_ORE_27(25370, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 10587, Skills.MINING),
	MITHRIL_ORE_28(29236, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 29218, Skills.MINING),
	MITHRIL_ORE_29(29237, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 29219, Skills.MINING),
	MITHRIL_ORE_30(29238, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 29220, Skills.MINING),
	MITHRIL_ORE_31(32439, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 33401, Skills.MINING),
	MITHRIL_ORE_32(32438, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 33400, Skills.MINING),
	MITHRIL_ORE_33(32440, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 33402, Skills.MINING),
	MITHRIL_ORE_34(31087, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 37649, Skills.MINING),
	MITHRIL_ORE_35(31086, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 37639, Skills.MINING),
	MITHRIL_ORE_36(31088, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 37650, Skills.MINING),
	MITHRIL_ORE_37(31170, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 14832, Skills.MINING),
	MITHRIL_ORE_38(31171, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 14833, Skills.MINING),
	MITHRIL_ORE_39(31172, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 14834, Skills.MINING),
	MITHRIL_ORE_40(37692, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 21296, Skills.MINING),
	MITHRIL_ORE_41(37693, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 21297, Skills.MINING),
	MITHRIL_ORE_42(37694, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 21298, Skills.MINING),
	MITHRIL_ORE_49(42036, 55, 0.70, 200 | 400 << 16, 80.0, 447, 1, "mithril rocks", null, 452, Skills.MINING),

	/**
	 * Adamantite ore.
	 */
	ADAMANTITE_ORE_0(2105, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 452, Skills.MINING), 
	ADAMANTITE_ORE_1(2104, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 450, Skills.MINING), 
	ADAMANTITE_ORE_2(4991, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 4994, Skills.MINING),
	ADAMANTITE_ORE_3(4992, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 4995, Skills.MINING),
	ADAMANTITE_ORE_4(4993, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 4996, Skills.MINING), 
	ADAMANTITE_ORE_5(11941, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 11554, Skills.MINING),
	ADAMANTITE_ORE_6(11940, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 11553, Skills.MINING), 
	ADAMANTITE_ORE_7(11939, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 11552, Skills.MINING), 
	ADAMANTITE_ORE_8(14864, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 25373, Skills.MINING), 
	ADAMANTITE_ORE_9(14863, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 25372, Skills.MINING), 
	ADAMANTITE_ORE_10(14862, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 25371, Skills.MINING),
	ADAMANTITE_ORE_11(20417, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 20407, Skills.MINING), 
	ADAMANTITE_ORE_12(20416, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 20445, Skills.MINING), 
	ADAMANTITE_ORE_13(20414, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 20443, Skills.MINING), 
	ADAMANTITE_ORE_14(20415, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 20444, Skills.MINING), 
	ADAMANTITE_ORE_15(19020, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 19017, Skills.MINING), 
	ADAMANTITE_ORE_16(19018, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 19021, Skills.MINING), 
	ADAMANTITE_ORE_17(19019, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 19016, Skills.MINING), 
	ADAMANTITE_ORE_18(21275, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 21296, Skills.MINING), 
	ADAMANTITE_ORE_19(21276, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 21297, Skills.MINING), 
	ADAMANTITE_ORE_20(21277, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 21298, Skills.MINING), 
	ADAMANTITE_ORE_21(29233, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 29218, Skills.MINING), 
	ADAMANTITE_ORE_22(29234, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 29219, Skills.MINING), 
	ADAMANTITE_ORE_23(29235, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 29220, Skills.MINING),
	ADAMANTITE_ORE_24(32435, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 33400, Skills.MINING), 
	ADAMANTITE_ORE_25(32437, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 33402, Skills.MINING), 
	ADAMANTITE_ORE_26(32436, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 33401, Skills.MINING), 
	ADAMANTITE_ORE_27(31083, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 37639, Skills.MINING), 
	ADAMANTITE_ORE_28(31085, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 37650, Skills.MINING), 
	ADAMANTITE_ORE_29(31084, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 37649, Skills.MINING), 
	ADAMANTITE_ORE_30(31173, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 14832, Skills.MINING), 
	ADAMANTITE_ORE_31(31174, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 14833, Skills.MINING), 
	ADAMANTITE_ORE_32(31175, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 14834, Skills.MINING), 
	ADAMANTITE_ORE_33(37468, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 15249, Skills.MINING), 
	ADAMANTITE_ORE_34(37469, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 15250, Skills.MINING), 
	ADAMANTITE_ORE_35(37470, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 15251, Skills.MINING), 
	ADAMANTITE_ORE_36(37689, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 21296, Skills.MINING), 
	ADAMANTITE_ORE_37(37690, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 21297, Skills.MINING), 
	ADAMANTITE_ORE_38(37691, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 21298, Skills.MINING),
	ADAMANTITE_ORE_39(42037, 70, 0.85, 400 | 800 << 16, 95.0, 449, 1, "adamant rocks", null, 452, Skills.MINING), 

	/**
	 * Runite ore.
	 */
	RUNITE_ORE_0(2107, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 452, Skills.MINING),
	RUNITE_ORE_1(2106, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 450, Skills.MINING),
	RUNITE_ORE_2(6669, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 21296, Skills.MINING),
	RUNITE_ORE_3(6671, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 21298, Skills.MINING),
	RUNITE_ORE_4(6670, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 21297, Skills.MINING),
	RUNITE_ORE_5(14861, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 25373, Skills.MINING),
	RUNITE_ORE_6(14860, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 25372, Skills.MINING),
	RUNITE_ORE_7(14859, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 25371, Skills.MINING),
	RUNITE_ORE_8(33079, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 33401, Skills.MINING),
	RUNITE_ORE_9(33078, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 33400, Skills.MINING),
	RUNITE_ORE_10(37208, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 21296, Skills.MINING),
	RUNITE_ORE_11(37465, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 15249, Skills.MINING),
	RUNITE_ORE_12(37466, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 15250, Skills.MINING),
	RUNITE_ORE_13(37467, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 15251, Skills.MINING),
	RUNITE_ORE_14(37695, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 21297, Skills.MINING),
	RUNITE_ORE_15(37696, 85, 0.95, 1250 | 2500 << 16, 125.0, 451, 1, "runite rocks", null, 21298, Skills.MINING),

	/**
	 * Gem rocks.
	 */
	GEM_ROCK_0(23567, 40, 0.95, 166 | 175 << 16, 65, 1625, 1, "gem rocks", null, 21297, Skills.MINING),
	GEM_ROCK_1(23566, 40, 0.95, 166 | 175 << 16, 65, 1625, 1, "gem rocks", null, 21296, Skills.MINING),
	GEM_ROCK_2(23568, 40, 0.95, 166 | 175 << 16, 65, 1625, 1, "gem rocks", null, 21298, Skills.MINING);

	/**
	 * The resources mapping.
	 */
	private static final Map<Integer, SkillingResource> RESOURCES = new HashMap<>();

	/**
	 * Populate the mapping.
	 */
	static {
		for (SkillingResource resource : SkillingResource.values()) {
			if (RESOURCES.containsKey(resource.id)) {
				System.err.println("Already contained resource " + resource.getId() + " - " + resource.name());
			}
			RESOURCES.put(resource.id, resource);
		}
	}

	/**
	 * The resource id.
	 */
	private final int id;

	/**
	 * The level required.
	 */
	private final int level;

	/**
	 * The rate.
	 */
	private final double rate;

	/**
	 * The respawn rate.
	 */
	private final int respawnRate;

	/**
	 * The experience to be rewarded.
	 */
	private final double experience;

	/**
	 * The reward item id.
	 */
	private final int reward;

	/**
	 * The amount to reward.
	 */
	private final int rewardAmount;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The animation.
	 */
	private final Animation animation;

	/**
	 * The id to replace this id with when the resource runs out.
	 */
	private final int emptyId;

	/**
	 * The skill id.
	 */
	private final int skillId;

	/**
	 * Represents if its a farming resource.
	 */
	private final boolean farming;

	/**
	 * Constructs a new {@code SkillingResource} {@code Object}.
	 * @param id The id.
	 * @param level The level required.
	 * @param rate The rate.
	 * @param respawnRate The respawn rate (lowest ticks | highest ticks << 16).
	 * @param experience The experience.
	 * @param reward The reward item id.
	 * @param rewardAmount The reward amount.
	 * @param name The name.
	 * @param animation The animation.
	 * @param emptyId The id to replace this id with when the resource runs out.
	 * @param skillId The skill id.
	 */
	private SkillingResource(int id, int level, double rate, int respawnRate, double experience, int reward, int rewardAmount, String name, Animation animation, int emptyId, int skillId) {
		this.id = id;
		this.level = level;
		this.rate = rate;
		this.respawnRate = respawnRate;
		this.experience = experience;
		this.reward = reward;
		this.rewardAmount = rewardAmount;
		this.name = name;
		this.animation = animation;
		this.emptyId = emptyId;
		this.skillId = skillId;
		this.farming = false;
	}

	/**
	 * Constructs a new {@code SkillingResource} {@code Object}.
	 * @param id The id.
	 * @param level The level required.
	 * @param rate The rate.
	 * @param respawnRate The respawn rate (lowest ticks | highest ticks << 16).
	 * @param experience The experience.
	 * @param reward The reward item id.
	 * @param rewardAmount The reward amount.
	 * @param name The name.
	 * @param animation The animation.
	 * @param emptyId The id to replace this id with when the resource runs out.
	 * @param skillId The skill id.
	 */
	private SkillingResource(int id, int level, double rate, int respawnRate, double experience, int reward, int rewardAmount, String name, Animation animation, int emptyId, int skillId, boolean farming) {
		this.id = id;
		this.level = level;
		this.rate = rate;
		this.respawnRate = respawnRate;
		this.experience = experience;
		this.reward = reward;
		this.rewardAmount = rewardAmount;
		this.name = name;
		this.animation = animation;
		this.emptyId = emptyId;
		this.skillId = skillId;
		this.farming = farming;
	}

	/**
	 * Gets the skilling resource for the given id.
	 * @param id The id.
	 * @return The resource.
	 */
	public static SkillingResource forId(int id) {
		return RESOURCES.get(id);
	}

	/**
	 * Checks if the object id is an empty resource.
	 * @param id The object id.
	 * @return {@code True} if so.
	 */
	public static boolean isEmpty(int id) {
		for (SkillingResource r : SkillingResource.values()) {
			if (r.getEmptyId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the current respawn duration (in ticks).
	 * @return The respawn duration.
	 */
	public int getRespawnDuration() {
		int minimum = respawnRate & 0xFFFF;
		int maximum = (respawnRate >> 16) & 0xFFFF;
		double playerRatio = ServerConstants.MAX_PLAYERS / Repository.getPlayers().size();
		return (int) (minimum + ((maximum - minimum) / playerRatio));
	}

	/**
	 * Gets the maximum respawn time (in ticks).
	 * @return The maximum respawn time.
	 */
	public int getMaximumRespawn() {
		return (respawnRate >> 16) & 0xFFFF;
	}

	/**
	 * Gets the minimum respawn time (in ticks).
	 * @return The minimum respawn time.
	 */
	public int getMinimumRespawn() {
		return respawnRate & 0xFFFF;
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the rate.
	 * @return The rate.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Gets the respawnRate.
	 * @return The respawnRate.
	 */
	public int getRespawnRate() {
		return respawnRate;
	}

	/**
	 * Gets the experience.
	 * @return The experience.
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * Gets the reward.
	 * @return The reward.
	 */
	public int getReward() {
		return reward;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Gets the emptyId.
	 * @return The emptyId.
	 */
	public int getEmptyId() {
		return emptyId;
	}

	/**
	 * Gets the skillId.
	 * @return The skillId.
	 */
	public int getSkillId() {
		return skillId;
	}

	/**
	 * Gets the rewardAmount.
	 * @return The rewardAmount.
	 */
	public int getRewardAmount() {
		return rewardAmount;
	}

	/**
	 * Gets the farming.
	 * @return The farming.
	 */
	public boolean isFarming() {
		return farming;
	}
}