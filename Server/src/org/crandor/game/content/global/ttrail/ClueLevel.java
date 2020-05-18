package org.crandor.game.content.global.ttrail;

import org.crandor.game.component.CloseEvent;
import org.crandor.game.component.Component;
import org.crandor.game.container.access.InterfaceContainer;
import org.crandor.game.node.entity.npc.drop.DropFrequency;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;
import org.crandor.tools.RandomFunction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A clue scroll level.
 * 
 * @author Vexia
 */
public enum ClueLevel {

	EASY(new Item(2714), 1 << 16 | 5, new ChanceItem(2633, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2635, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2637, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2587, 1, 1, DropFrequency.UNCOMMON), // black
			// t
			new ChanceItem(2583, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2585, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3472, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2589, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2595, 1, 1, DropFrequency.UNCOMMON), // black
			// g
			new ChanceItem(2591, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2593, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3473, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2597, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2631, 1, 1, DropFrequency.UNCOMMON), // highway
			// man
			// mask
			new ChanceItem(7392, 1, 1, DropFrequency.UNCOMMON), // wizard t
			new ChanceItem(7396, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7388, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7390, 1, 1, DropFrequency.UNCOMMON), // wizard
			// g
			new ChanceItem(7386, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7394, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7362, 1, 1, DropFrequency.UNCOMMON), // studded
			// g
			new ChanceItem(7366, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7364, 1, 1, DropFrequency.UNCOMMON), // studded
			// t
			new ChanceItem(7368, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10404, 1, 1, DropFrequency.UNCOMMON), // red
			// ele
			// shirt
			new ChanceItem(10406, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10424, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10426, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10408, 1, 1, DropFrequency.UNCOMMON), // blue
			// ele
			// shirt
			new ChanceItem(10410, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10428, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10430, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10412, 1, 1, DropFrequency.UNCOMMON), // green
			// ele
			// shirt
			new ChanceItem(10414, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10432, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10434, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10316, 1, 1, DropFrequency.RARE), // bob
			// the
			// cat
			new ChanceItem(10318, 1, 1, DropFrequency.RARE), new ChanceItem(10320, 1, 1, DropFrequency.RARE), new ChanceItem(10322, 1, 1, DropFrequency.RARE), new ChanceItem(10324, 1, 1, DropFrequency.RARE), new ChanceItem(10392, 1, 1, DropFrequency.RARE), // emote
			// enhancers
			new ChanceItem(10394, 1, 1, DropFrequency.RARE), new ChanceItem(10396, 1, 1, DropFrequency.RARE), new ChanceItem(10398, 1, 1, DropFrequency.RARE), new ChanceItem(10366, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10458, 1, 1, DropFrequency.UNCOMMON), // vestement
			// robes(sara,
			// guthix,
			// zammy)
			new ChanceItem(10464, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10462, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10466, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10460, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10468, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13095, 1, 1, DropFrequency.UNCOMMON), // black
			// cane
			new ChanceItem(13105, 1, 1, DropFrequency.UNCOMMON), // spikey
																	// helmet
			new ChanceItem(1077, 1, 1, DropFrequency.COMMON), new ChanceItem(1089, 1, 1, DropFrequency.COMMON), new ChanceItem(1107, 1, 1, DropFrequency.COMMON), new ChanceItem(1125, 1, 1, DropFrequency.COMMON), new ChanceItem(1151, 1, 1, DropFrequency.COMMON), new ChanceItem(1165, 1, 1, DropFrequency.COMMON), new ChanceItem(1179, 1, 1, DropFrequency.COMMON), new ChanceItem(1195, 1, 1, DropFrequency.COMMON), new ChanceItem(1217, 1, 1, DropFrequency.COMMON), new ChanceItem(1283, 1, 1, DropFrequency.COMMON), new ChanceItem(1297, 1, 1, DropFrequency.COMMON), new ChanceItem(1313, 1, 1, DropFrequency.COMMON), new ChanceItem(1327, 1, 1, DropFrequency.COMMON), new ChanceItem(1341, 1, 1, DropFrequency.COMMON), new ChanceItem(1361, 1, 1, DropFrequency.COMMON), new ChanceItem(1367, 1, 1, DropFrequency.COMMON), new ChanceItem(1426, 1, 1, DropFrequency.COMMON), new ChanceItem(3098, 1, 1, DropFrequency.COMMON), new ChanceItem(4821, 1, 1, DropFrequency.COMMON), new ChanceItem(8779, 4, 38, DropFrequency.COMMON), new ChanceItem(850, 1, 4, DropFrequency.COMMON), new ChanceItem(334, 4, 19, DropFrequency.COMMON), new ChanceItem(1169, 1, 1, DropFrequency.COMMON), new ChanceItem(1059, 1, 1, DropFrequency.COMMON), new ChanceItem(1061, 1, 1, DropFrequency.COMMON), new ChanceItem(1063, 1, 1, DropFrequency.COMMON), new ChanceItem(1095, 1, 1, DropFrequency.COMMON), new ChanceItem(1129, 1, 1, DropFrequency.COMMON), new ChanceItem(1167, 1, 1, DropFrequency.COMMON), new ChanceItem(1131, 1, 1, DropFrequency.COMMON), new ChanceItem(858, 1, 4, DropFrequency.COMMON), new ChanceItem(330, 3, 23, DropFrequency.COMMON), new ChanceItem(1441, 1, 3, DropFrequency.COMMON), new ChanceItem(1443, 1, 3, DropFrequency.COMMON), new ChanceItem(1270, 1, 3, DropFrequency.COMMON), new ChanceItem(1097, 1, 1, DropFrequency.COMMON), new ChanceItem(1133, 1, 1, DropFrequency.COMMON)),

	MEDIUM(new Item(2717), 1 << 16 | 6, new ChanceItem(2605, 1, 1, DropFrequency.UNCOMMON), // Trimmed
			// addy
			// shit
			new ChanceItem(2599, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2601, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2603, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3474, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2613, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2607, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2609, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3475, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2611, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2579, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2647, 1, 1, DropFrequency.UNCOMMON), // Headbands
			new ChanceItem(2645, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2649, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2605, 1, 1, DropFrequency.UNCOMMON), // addy
			// trimmed
			// shit
			new ChanceItem(2599, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2601, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2603, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3474, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2613, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2607, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2609, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3475, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2611, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2577, 1, 1, DropFrequency.RARE), // Boots
			new ChanceItem(2579, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2647, 1, 1, DropFrequency.UNCOMMON), // Headbands
			new ChanceItem(2645, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2649, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7319, 1, 1, DropFrequency.UNCOMMON), // Boaters
			new ChanceItem(7321, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7323, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7325, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7327, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7372, 1, 1, DropFrequency.UNCOMMON), // Trimmed
			// Dragonhide
			// shit
			new ChanceItem(7380, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7370, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7378, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13103, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13097, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13109, 1, 1, DropFrequency.UNCOMMON), // animal
			// masks
			new ChanceItem(13107, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13111, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13113, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13115, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10364, 1, 1, DropFrequency.UNCOMMON), // ammy
			// of
			// strength
			// (t)
			new ChanceItem(10420, 1, 1, DropFrequency.UNCOMMON), // white ele
			new ChanceItem(10422, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10400, 1, 1, DropFrequency.RARE), // black
			// ele
			new ChanceItem(10402, 1, 1, DropFrequency.RARE), new ChanceItem(10416, 1, 1, DropFrequency.UNCOMMON), // purple
			// ele
			new ChanceItem(10418, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10436, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10438, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10296, 1, 1, DropFrequency.UNCOMMON), // addy
			// heraldic
			// helms
			new ChanceItem(10298, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10300, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10302, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10304, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10666, 1, 1, DropFrequency.UNCOMMON), // addy
			// heraldic
			// shield
			new ChanceItem(10669, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10672, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10675, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10678, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10446, 1, 1, DropFrequency.UNCOMMON), // cloaks
			new ChanceItem(10448, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10450, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(1161, 1, 1, DropFrequency.COMMON), new ChanceItem(1123, 1, 1, DropFrequency.COMMON), new ChanceItem(1073, 1, 1, DropFrequency.COMMON), new ChanceItem(1091, 1, 1, DropFrequency.COMMON), new ChanceItem(1199, 1, 1, DropFrequency.COMMON), new ChanceItem(1183, 1, 1, DropFrequency.COMMON), new ChanceItem(1111, 1, 1, DropFrequency.COMMON), new ChanceItem(1211, 1, 1, DropFrequency.COMMON), new ChanceItem(1271, 1, 1, DropFrequency.COMMON), new ChanceItem(1287, 1, 1, DropFrequency.COMMON), new ChanceItem(1301, 1, 1, DropFrequency.COMMON), new ChanceItem(1317, 1, 1, DropFrequency.COMMON), new ChanceItem(1357, 1, 1, DropFrequency.COMMON), new ChanceItem(1371, 1, 1, DropFrequency.COMMON), new ChanceItem(1430, 1, 1, DropFrequency.COMMON), new ChanceItem(4823, 10, 40, DropFrequency.COMMON), new ChanceItem(9183, 1, 1, DropFrequency.COMMON), new ChanceItem(1393, 1, 1, DropFrequency.COMMON), new ChanceItem(1099, 1, 1, DropFrequency.COMMON), new ChanceItem(1135, 1, 1, DropFrequency.COMMON), new ChanceItem(857, 1, 1, DropFrequency.COMMON), new ChanceItem(8781, 10, 43, DropFrequency.COMMON), new ChanceItem(374, 6, 23, DropFrequency.COMMON), new ChanceItem(380, 15, 43, DropFrequency.COMMON), new ChanceItem(10452, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10454, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10456, 1, 1, DropFrequency.UNCOMMON)),

	HARD(new Item(2720), 1 << 16 | 8, new ChanceItem(13101, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(13099, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10470, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10472, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10474, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10440, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10442, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10444, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10362, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2628, 1, 1, DropFrequency.UNCOMMON), // Trimmed
			// rune
			// shit
			new ChanceItem(2623, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2625, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2629, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2619, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3477, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2615, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2617, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2628, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2628, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3476, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2621, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2673, 1, 1, DropFrequency.UNCOMMON), // God
			// armour
			// shit
			new ChanceItem(2669, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2671, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3480, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2675, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2657, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2653, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2655, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2659, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2665, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2661, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3478, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2663, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2667, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3479, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10286, 1, 1, DropFrequency.UNCOMMON), // heraldic
			// shit
			new ChanceItem(10667, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10288, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10670, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10290, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10673, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10292, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10676, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10294, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10679, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7376, 1, 1, DropFrequency.UNCOMMON), // Blue
			// dragonhide
			// trimmed
			new ChanceItem(7384, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7374, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7382, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10368, 1, 1, DropFrequency.UNCOMMON), // God
			// dhide
			new ChanceItem(10370, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10372, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10376, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10378, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10380, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10384, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10386, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10388, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2651, 1, 1, DropFrequency.RARE), // Hats
			new ChanceItem(2581, 1, 1, DropFrequency.RARE), new ChanceItem(2639, 1, 1, DropFrequency.UNCOMMON), // Cavaliers
			new ChanceItem(2641, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(2643, 1, 1, DropFrequency.RARE), new ChanceItem(1163, 1, 1, DropFrequency.COMMON), new ChanceItem(1127, 1, 1, DropFrequency.COMMON), new ChanceItem(1079, 1, 1, DropFrequency.COMMON), new ChanceItem(1093, 1, 1, DropFrequency.COMMON), new ChanceItem(1201, 1, 1, DropFrequency.COMMON), new ChanceItem(1185, 1, 1, DropFrequency.COMMON), new ChanceItem(1213, 1, 1, DropFrequency.COMMON), new ChanceItem(1275, 1, 1, DropFrequency.COMMON), new ChanceItem(1289, 1, 1, DropFrequency.COMMON), new ChanceItem(1303, 1, 1, DropFrequency.COMMON), new ChanceItem(1319, 1, 1, DropFrequency.COMMON), new ChanceItem(1359, 1, 1, DropFrequency.COMMON), new ChanceItem(1373, 1, 1, DropFrequency.COMMON), new ChanceItem(1432, 1, 1, DropFrequency.COMMON), new ChanceItem(4824, 10, 43, DropFrequency.COMMON), new ChanceItem(9144, 6, 23, DropFrequency.COMMON), new ChanceItem(9185, 1, 1, DropFrequency.COMMON), new ChanceItem(1747, 1, 1, DropFrequency.COMMON), new ChanceItem(2503, 1, 1, DropFrequency.COMMON), new ChanceItem(861, 1, 2, DropFrequency.COMMON), new ChanceItem(859, 1, 2, DropFrequency.COMMON), new ChanceItem(8783, 6, 27, DropFrequency.COMMON), new ChanceItem(380, 16, 38, DropFrequency.COMMON), new ChanceItem(386, 7, 32, DropFrequency.COMMON), new ChanceItem(10382, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10374, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(10389, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7398, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7399, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(7400, 1, 1, DropFrequency.UNCOMMON)),

	;

	/**
	 * The default rewards.
	 */
	private static final ChanceItem[] DEFAULT_REWARDS = new ChanceItem[] { new ChanceItem(995, 500, 7000, DropFrequency.COMMON), new ChanceItem(10326, 15, 40, DropFrequency.UNCOMMON), new ChanceItem(7329, 15, 40, DropFrequency.UNCOMMON), new ChanceItem(7331, 15, 40, DropFrequency.UNCOMMON), new ChanceItem(7330, 15, 40, DropFrequency.UNCOMMON), new ChanceItem(10327, 15, 40, DropFrequency.UNCOMMON), new ChanceItem(10476, 1, 27, DropFrequency.UNCOMMON), new ChanceItem(556, 1, 228, DropFrequency.COMMON), new ChanceItem(554, 1, 228, DropFrequency.COMMON), new ChanceItem(557, 1, 228, DropFrequency.COMMON), new ChanceItem(557, 1, 228, DropFrequency.COMMON), new ChanceItem(1694, 1, 1, DropFrequency.RARE), new ChanceItem(1696, 1, 1, DropFrequency.RARE), new ChanceItem(1698, 1, 1, DropFrequency.RARE), new ChanceItem(1700, 1, 1, DropFrequency.RARE), new ChanceItem(1702, 1, 1, DropFrequency.RARE), new ChanceItem(847, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(855, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(859, 1, 1, DropFrequency.UNCOMMON), new ChanceItem(3827, 1, 1, DropFrequency.RARE), new ChanceItem(3828, 1, 1, DropFrequency.RARE), new ChanceItem(3829, 1, 1, DropFrequency.RARE), new ChanceItem(3830, 1, 1, DropFrequency.RARE), new ChanceItem(3831, 1, 1, DropFrequency.RARE), new ChanceItem(3832, 1, 1, DropFrequency.RARE), new ChanceItem(3833, 1, 1, DropFrequency.RARE), new ChanceItem(3834, 1, 1, DropFrequency.RARE), new ChanceItem(3835, 1, 1, DropFrequency.RARE), new ChanceItem(3836, 1, 1, DropFrequency.RARE), new ChanceItem(3837, 1, 1, DropFrequency.RARE), new ChanceItem(3838, 1, 1, DropFrequency.RARE) };

	/**
	 * The super rare items.
	 */
	private static final ChanceItem[] SUPER_RARE = new ChanceItem[] { new ChanceItem(3486, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(3481, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(3483, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(3485, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(3488, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10330, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10332, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10334, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10336, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10338, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10340, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10342, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10344, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10346, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10348, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10350, 1, 1, DropFrequency.VERY_RARE), new ChanceItem(10352, 1, 1, DropFrequency.VERY_RARE) };

	/**
	 * The casket.
	 */
	private final Item casket;

	/**
	 * The length hash of the level.
	 */
	private final int lengthHash;

	/**
	 * The reward items.
	 */
	private final ChanceItem[] rewards;

	/**
	 * Constructs a new {@Code ClueLevel} {@Code Object}
	 * 
	 * @param casket
	 *            the casket.
	 * @param lengthHash
	 *            the hash.
	 * @param rewards
	 *            the rewards.
	 */
	private ClueLevel(Item casket, int lengthHash, ChanceItem... rewards) {
		this.casket = casket;
		this.lengthHash = lengthHash;
		this.rewards = rewards;
	}

	/**
	 * Opens a cakset for the player.
	 * 
	 * @param player
	 *            the player.
	 * @param casket
	 *            the casket.
	 */
	public void open(Player player, Item casket) {
		if (casket != null) {
			if (!player.getInventory().containsItem(casket)) {
				return;
			}
		}
		if (player.getTreasureTrailManager().isCompleted() || GameWorld.getSettings().isDevMode()) {
			final List<Item> rewards = getLoot(player);
			player.getInterfaceManager().open(new Component(364).setCloseEvent(new CloseEvent() {
				private boolean given;

				@Override
				public boolean close(Player player, Component c) {
					if (!given) {
						for (Item item : rewards) {
							player.getInventory().add(item, player);
						}
						given = true;
					}
					return true;
				}
			}));
			if (casket != null) {
				player.getInventory().remove(casket);
			}
			player.getTreasureTrailManager().incrementClues(this);
			player.getTreasureTrailManager().clearTrail();
			player.sendMessage("Well done, you've completed the Treasure Trail!");
			player.sendMessage(getChatColor(this) + "You have completed " + player.getTreasureTrailManager().getCompletedClues(this) + " " + this.getName().toLowerCase() + " Treasure Trails.</col>");
			long value = 0;
			for (Item item : rewards) {
				value += item.getValue();
			}
			player.sendMessage("<col=990000>Your clue is worth approximately " + NumberFormat.getInstance().format(value) + " coins!</col>");
			player.getPacketDispatch().sendAccessMask(1278, 4, 364, 0, 6);
			InterfaceContainer.generateItems(player, rewards.toArray(new Item[] {}), new String[] { "" }, 364, 4, 3, 3);
			return;
		}
		Item clue = ClueScrollPlugin.getClue(this);
		if (clue == null) {
			player.sendMessage("Error! Clue not found! Report to admin.");
			return;
		}
		if (casket != null) {
			player.getInventory().replace(clue, casket.getSlot());
		} else {
			player.getInventory().add(clue);
		}
		player.getTreasureTrailManager().setClueId(clue.getId());
		player.getDialogueInterpreter().sendItemMessage(clue, "You've found another clue!");
	}

	/**
	 * Gets the rewards.ds.
	 * 
	 * @return the rewar
	 */
	public List<Item> getLoot(Player player) {
		List<ChanceItem> items = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		items.addAll(Arrays.asList(DEFAULT_REWARDS));
		List<Item> rewards = new ArrayList<>();
		int size = RandomFunction.random(1, 6);
		if (this == HARD) {
			size = RandomFunction.random(4, 6);
		}
		Item item = null;
		for (int i = 0; i < size; i++) {
			item = getReward(items);
			if (ids.contains(item.getId())) {
				continue;
			}
			ids.add(item.getId());
			rewards.add(item);
		}
		int rand = 1400;// 2000
		if (this == HARD && rand == 11) {
			rewards.remove(0);
			rewards.add(RandomFunction.getChanceItem(SUPER_RARE).getRandomItem());
		}
		return rewards;
	}

	/**
	 * Gets a reward item.
	 * 
	 * @return the item.
	 */
	public Item getReward(List<ChanceItem> items) {
		Collections.shuffle(items);
		return RandomFunction.getChanceItem(items.toArray(new ChanceItem[] {})).getRandomItem();
	}

	/**
	 * Gets the clue level for the casket.
	 * 
	 * @param node
	 *            the node.
	 * @return the level.
	 */
	public static ClueLevel forCasket(Item node) {
		for (ClueLevel level : values()) {
			if (node.getId() == level.getCasket().getId()) {
				return level;
			}
		}
		return null;
	}

	/**
	 * Gets the Chat color to send on completed clues.
	 * @param level The clue level.
	 * @return the chat color.
	 */
	public static String getChatColor(ClueLevel level) {
		if (level == ClueLevel.HARD) {
			return "<col=ff1a1a>";
		}
		if (level == ClueLevel.MEDIUM) {
			return "<col=b38f00>";
		}
		return "<col=00e673>";
	}
	/**
	 * Gets the maximum length.
	 * 
	 * @return the length.
	 */
	public int getMaximumLength() {
		return lengthHash & 0xFFFF;
	}

	/**
	 * Gets the minimum length.
	 * 
	 * @return the length.
	 */
	public int getMinimumLength() {
		return lengthHash >> 16 & 0xFFFF;
	}

	/**
	 * Gets the brewards.
	 * 
	 * @return the rewards
	 */
	public ChanceItem[] getRewards() {
		return rewards;
	}

	/**
	 * Gets the bcasket.
	 * 
	 * @return the casket
	 */
	public Item getCasket() {
		return casket;
	}

	/**
	 * Gets the blengthHash.
	 * 
	 * @return the lengthHash
	 */
	public int getLengthHash() {
		return lengthHash;
	}
	
	/**
	 * Gets the name of the clue level.
	 * @return the name.
	 */
	public String getName() {
		return toString();
	}
}
