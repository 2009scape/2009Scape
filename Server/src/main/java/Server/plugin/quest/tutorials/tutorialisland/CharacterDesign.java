package plugin.quest.tutorials.tutorialisland;

import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.appearance.Gender;
import core.tools.RandomFunction;

/**
 * Represents the class used to handle the character design.
 * @author Emperor
 * @author Vexia
 * 
 */
public final class CharacterDesign {

	/**
	 * The male head ids.
	 */
	private static final int[] MALE_HEAD_IDS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 91, 92, 93, 94, 95, 96, 97, 261, 262, 263, 264, 265, 266, 267, 268};

	/**
	 * The female head ids.
	 */
	private static final int[] FEMALE_HEAD_IDS = {45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280};

	/**
	 * The male jaw ids.
	 */
	private static final int[] MALE_JAW_IDS = { 10, 11, 12, 13, 14, 15, 16, 17, 98, 99, 100, 101, 102, 103, 104, 305, 306, 307, 308};

	/**
	 * The female jaw ids.
	 */
	private static final int[] FEMALE_JAW_IDS = { 1000 };

	/**
	 * The male torso ids.
	 */
	private static final int[] MALE_TORSO_IDS = {18, 19, 20, 21, 22, 23, 24, 25, 111, 112, 113, 114, 115, 116 };

	/**
	 * The female torso ids.
	 */
	private static final int[] FEMALE_TORSO_IDS = { 56, 57, 58, 59, 60, 153, 154, 155, 156, 157, 158};

	/**
	 * The male arms ids.
	 */
	private static final int[] MALE_ARMS_IDS = {26, 27, 28, 29, 30, 31, 105, 106, 107, 108, 109, 110};

	/**
	 * The female arms ids.
	 */
	private static final int[] FEMALE_ARMS_IDS = {61, 62, 63, 64, 65, 147, 148, 149, 150, 151, 152 };

	/**
	 * The male hands ids.
	 */
	private static final int[] MALE_HANDS_IDS = {33, 34, 84, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126};

	/**
	 * The female hands ids.
	 */
	private static final int[] FEMALE_HANDS_IDS = {67, 68, 127, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168};

	/**
	 * The male legs ids.
	 */
	private static final int[] MALE_LEGS_IDS = {36, 37, 38, 39, 40, 85, 86, 87, 88, 89, 90};

	/**
	 * The female legs ids.
	 */
	private static final int[] FEMALE_LEGS_IDS = {70, 71, 72, 73, 74, 75, 76, 77, 128, 129, 130, 131, 132, 133, 134 };

	/**
	 * The male feet ids.
	 */
	private static final int[] MALE_FEET_IDS = { 42, 43 };

	/**
	 * The female feet ids.
	 */
	private static final int[] FEMALE_FEET_IDS = { 79, 80 };

	/**
	 * The male look ids.
	 */
	private static final int[][] MALE_LOOK_IDS = { MALE_HEAD_IDS, MALE_JAW_IDS, MALE_TORSO_IDS, MALE_ARMS_IDS, MALE_HANDS_IDS, MALE_LEGS_IDS, MALE_FEET_IDS };

	/**
	 * The female look ids.
	 */
	private static final int[][] FEMALE_LOOK_IDS = { FEMALE_HEAD_IDS, FEMALE_JAW_IDS, FEMALE_TORSO_IDS, FEMALE_ARMS_IDS, FEMALE_HANDS_IDS, FEMALE_LEGS_IDS, FEMALE_FEET_IDS };

	/**
	 * The hair colors.
	 */
	private static final int[] HAIR_COLORS = new int[] {20, 19, 10, 18, 4, 5, 15, 7, 0, 6, 21, 9, 22, 17, 8, 16, 11, 24, 23, 3, 2, 1, 14, 13, 12};

	/**
	 * The torso colors. 
	 */
	private static final int[] TORSO_COLORS = new int[] {24, 23, 2, 22, 12, 11, 6, 19, 4, 0, 9, 13, 25, 8, 15, 26, 21, 7, 20, 14, 10, 28, 27, 3, 5, 18, 17, 1, 16};

	/**
	 * The leg colors.
	 */
	private static final int[] LEG_COLORS = new int[] {26, 24, 23, 3, 22, 13, 12, 7, 19, 5, 1, 10, 14, 25, 9, 0, 21, 8, 20, 15, 11, 28, 27, 4, 6, 18, 17, 2, 16};

	/**
	 * The feet colors.
	 */
	private static final int[] FEET_COLORS = new int[] {0, 1, 2, 3, 4, 5};

	/**
	 * The skin colors.
	 */
	private static final int[] SKIN_COLORS = new int[] {7, 6, 5, 4, 3, 2, 1, 0};

	/**
	 * Method used to open the design.
	 * @param player the player.
	 */
	public static void open(final Player player) {
		player.unlock();
		player.removeAttribute("char-design:accepted");
		player.getPacketDispatch().sendPlayerOnInterface(771, 79); 
		player.getPacketDispatch().sendAnimationInterface(9806, 771, 79);
		player.getAppearance().changeGender(player.getAppearance().getGender());
		Component c = player.getInterfaceManager().openComponent(771);
		if (c != null) {
			c.setCloseEvent(new CloseEvent() {
				@Override
				public boolean close(Player player, Component c) { // Unclosable!
					return player.getAttribute("char-design:accepted", false);
				}
			});
		}
		reset(player);
		player.getPacketDispatch().sendInterfaceConfig(771, 22, false);
		player.getPacketDispatch().sendInterfaceConfig(771, 92, false);
		player.getPacketDispatch().sendInterfaceConfig(771, 97, false);
		player.getConfigManager().set(1262, player.getAppearance().isMale());
	}

	/**
	 * Handles the buttons.
	 * @param opcode the opcode.
	 * @param button the button.
	 */
	public static boolean handleButtons(Player player, int buttonId) {
		switch (buttonId) {
		case 37:
		case 40:
			player.getSettings().toggleMouseButton();
			break;
		case 92://hair style
		case 93:
			changeLook(player, 0, buttonId == 93);
			break;
		case 97:
		case 98://facial hair style
			changeLook(player, 1, buttonId == 98);
			break;
		case 341:
		case 342://torso style
			changeLook(player, 2, buttonId == 342);
			break;
		case 345:
		case 346://arms style
			changeLook(player, 3, buttonId == 346);
			break;
		case 349:
		case 350://wrists style
			changeLook(player, 4, buttonId == 350);
			break;
		case 353:
		case 354://legs style
			changeLook(player, 5, buttonId == 354);	
			break;
		case 357:
		case 358://feet style
			changeLook(player, 6, buttonId == 358);
			break;
		case 49:
		case 52://change gender
			changeGender(player, buttonId == 49);
			break;
		case 321://randomize body
			randomize(player, false);
			return true;
		case 169:
			randomize(player, true);//randomize hair
			return true;
		case 362://confirm
			confirm(player, true);
			return true;
		}
		if (buttonId >= 100 && buttonId <= 124) {
			changeColor(player, 0, HAIR_COLORS, 100, buttonId);
		}
		if (buttonId >= 189 && buttonId <= 217) {
			changeColor(player, 2, TORSO_COLORS, 189, buttonId);
		}
		if (buttonId >= 248 && buttonId <= 276) {
			changeColor(player, 5, LEG_COLORS, 248, buttonId);
		}
		if (buttonId >= 307 && buttonId <= 312) {
			changeColor(player, 6, FEET_COLORS, 307, buttonId);
		}
		if (buttonId <= 158 && buttonId >= 151) {
			changeColor(player, 4, SKIN_COLORS, 158, buttonId);
		}
		return false;
	}

	/**
	 * Changes the gender of a player.
	 * @param player The player.
	 * @param male If we're changing to male.
	 */
	private static void changeGender(Player player, boolean male) {
		player.setAttribute("male", male);
		player.getConfigManager().set(1262, male ? 1 : -1);
		reset(player);
	}

	/**
	 * Changes the look of a player.
	 * @param player The player.
	 * @param index The index.
	 * @param increment If we're incrementing.
	 */
	private static void changeLook(Player player, int index, boolean increment) {
		if (index < 2 && player.getAttribute("first-click:" + index, false) == false) {
			player.setAttribute("first-click:" + index, true);
			return;
		}
		player.setAttribute("look-val:" + index, getValue(player, "look", index, (int) player.getAttribute("look:" + index, 0), increment));
	}

	/**
	 * Changes the color of a player.
	 * @param player The player.
	 * @param index The body index.
	 * @param array The color array.
	 */
	private static void changeColor(Player player, int index, int[] array, int startId, int buttonId) {
		player.setAttribute("color-val:" + index, array[Math.abs(buttonId - startId)]);
	}

	/**
	 * Resets the players design.
	 * @param player the player.
	 */
	private static void reset(Player player) {
		for (int i = 0; i < player.getAppearance().getAppearanceCache().length; i++) {
			player.removeAttribute("look:" + i);
			player.removeAttribute("look-val:" + i);
			player.removeAttribute("color-val:" + i);
		}
		player.removeAttribute("first-click:0");
		player.removeAttribute("first-click:1");
	}

	/**
	 * Randomizes the player's look.
	 * @param player the player.
	 */
	public static void randomize(Player player, boolean head) {
		if (head) {
			changeLook(player, 0, RandomFunction.random(2) == 1);
			changeLook(player, 1, RandomFunction.random(2) == 1);
			changeColor(player, 0, HAIR_COLORS, 100, RandomFunction.random(100, 124));
			changeColor(player, 4, SKIN_COLORS, 158, RandomFunction.random(158, 151));
		} else {
			for (int i = 0; i < player.getAppearance().getAppearanceCache().length; i++) {
				changeLook(player, i, RandomFunction.random(2) == 1);
			}
			changeColor(player, 2, TORSO_COLORS, 189, RandomFunction.random(189, 217));
			changeColor(player, 5, LEG_COLORS, 248, RandomFunction.random(248, 276));
			changeColor(player, 6, FEET_COLORS, 307, RandomFunction.random(307, 312));
		}
		confirm(player, false);
	}

	/**
	 * Confirms the character screen.
	 * @param player The player.
	 * @param If we should close the interface.
	 */
	private static void confirm(Player player, boolean close) {
		if (close) {
			player.setAttribute("char-design:accepted", true);
			player.getInterfaceManager().close();
		}
		player.getAppearance().setGender(player.getAttribute("male", player.getAppearance().isMale()) ? Gender.MALE : Gender.FEMALE);
		for (int i = 0; i < player.getAppearance().getAppearanceCache().length; i++) {
			player.getAppearance().getAppearanceCache()[i].changeLook(player.getAttribute("look-val:" + i, player.getAppearance().getAppearanceCache()[i].getLook()));
			player.getAppearance().getAppearanceCache()[i].changeColor(player.getAttribute("color-val:" + i, player.getAppearance().getAppearanceCache()[i].getColor()));
		}
		player.getAppearance().sync();

	}

	/**
	 * Gets the look value for the index.
	 * @param player The player.
	 * @param key The key.
	 * @param index The index.
	 * @param currentIndex The current index.
	 * @param increment The increment.
	 * @return The look value.
	 */
	private static int getValue(Player player, String key, int index, int currentIndex, boolean increment) {
		int[] array = player.getAttribute("male", player.getAppearance().isMale()) ? MALE_LOOK_IDS[index] : FEMALE_LOOK_IDS[index];
		int val = 0;
		if (increment && currentIndex + 1 > array.length -1) {
			val = (int) array[0];
			currentIndex = 0;
		} else if (!increment && currentIndex -1 < 0) {
			val = (int) array[array.length -1];
			currentIndex = array.length -1;
		} else if (increment) {
			val = (int) array[currentIndex + 1];
			currentIndex++;
		} else {
			val = (int) array[currentIndex - 1];
			currentIndex--;
		}
		player.setAttribute(key + ":" + index, currentIndex);
		return val;
	}

	//Male head ids : Script - 701 [0: 1 1: 2 2: 3 3: 4 4: 5 5: 6 6: 7 7: 8 8: 9 91: 10 92: 11 93: 12 94: 13 95: 14 96: 15 97: 16 261: 17 262: 18 263: 19 264: 20 265: 21 266: 22 267: 23 268: 24 ]
	//Female head ids: Script - 689 [45: 1 46: 2 47: 3 48: 4 49: 5 50: 6 51: 7 52: 8 53: 9 54: 10 135: 11 136: 12 137: 13 138: 14 139: 15 140: 16 141: 17 142: 318 143: 19 144: 20 145: 21 146: 22 269: 23 270: 24 271: 25 272: 26 273: 27 274: 28 275: 29 276: 30 277: 31 278: 32 279: 33 280: 34 ]
	//Male jaw ids: Script - 703 [10: 1 11: 2 12: 3 13: 4 14: 5 15: 6 16: 7 17: 8 98: 9 99: 10 100: 11 101: 12 102: 13 103: 14 104: 15 305: 16 306: 17 307: 18 308: 19 ]
	//Male torso ids: Script - 1128 [19: 1 20: 2 21: 3 22: 4 23: 5 24: 6 25: 7 111: 8 112: 9 113: 10 114: 11 115: 12 116: 13 ]
	//Female torso ids: Script - 1129 [57: 1 58: 2 59: 3 60: 4 153: 5 154: 6 155: 7 156: 8 157: 9 158: 10 ]
	//Male arm ids: Script - 1130 [27: 1 28: 2 29: 3 30: 4 31: 5 105: 6 106: 7 107: 8 108: 9 109: 10 110: 11 ]
	//Female arm ids: Script - 1131 [62: 1 63: 2 64: 3 65: 4 147: 5 148: 6 149: 7 150: 8 151: 9 152: 10 ]
	//Male hand ids: Script - 1132 [34: 1 84: 2 117: 3 118: 4 119: 5 120: 6 121: 7 122: 8 123: 9 124: 10 125: 11 126: 12 ]
	//Female hand ids: Script - 1133 [68: 1 127: 2 159: 3 160: 4 161: 5 162: 6 163: 7 164: 8 165: 9 166: 10 167: 11 168: 12 ]
	//Male leg ids: Script - 1134 [37: 1 38: 2 39: 3 40: 4 85: 5 86: 6 87: 7 88: 8 89: 9 90: 10 ]
	//Female leg ids: Script - 1135 [71: 1 72: 2 73: 3 74: 4 75: 5 76: 6 77: 7 128: 8 129: 9 130: 10 131: 11 132: 12 133: 13 134: 14 ]
	//Male feet ids: Script - 1136 [43: 1 ]
	//Female feet ids: Script - 1137 [80: 1 ]

}

