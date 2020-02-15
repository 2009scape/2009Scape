package plugin.interaction.inter;

import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to handle the interface of changing hairs.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HairInterfacePlugin extends ComponentPlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 2000);

	private static final int[] HAIR_COLORS = new int[] {20, 19, 10, 18, 4, 5, 15, 7, 0, 6, 21, 9, 22, 17, 8, 16, 11, 24, 23, 3, 2, 1, 14, 13, 12};

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(592, this);
		ComponentDefinition.put(596, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		//TODO: Have Changing Booth Animation Fall down and stay on the character. Outside of the OpCode because we don't want the animation repeating
		switch (opcode) {
			case 155:
				if(player.isMale()){
					handleMaleInterfaceButtons(player, button);
					break;
				}else{
					handleFemaleInterfaceButtons(player, button);
					break;
				}
		}
		return true;
	}

	/**
	 * Represents the method used to handle the buttons for the male hairdresser interface.
	 * @param player the player.
	 * @param button the button,
	 */
	public static boolean handleMaleInterfaceButtons(Player player, int button) {

		switch (button) {

			//Male Hair Styles
			case 65: case 66: case 67: case 68: case 69:
			case 70: case 71: case 72: case 73:
				player.setAttribute("newHair", button-65);
				break;
			case 74: case 75: case 76: case 77: case 78: case 79: case 80:
				player.setAttribute("newHair", button+17);
				break;
			case 81: case 82: case 83: case 84: case 85: case 86:
				player.setAttribute("newHair", button+180);
				break;
			case 89: case 90:
				player.setAttribute("newHair", button+178);
				break;

			//Male Hair Colour Styles
			//As the array is not ordered (because of the customization menu),
			//I just copied the array over until a better solution could be thought of.
			case 229: case 230: case 231: case 232: case 233:
			case 234: case 235: case 236: case 237: case 238:
			case 239: case 240: case 241: case 242: case 243:
			case 244: case 245: case 246: case 247: case 248:
			case 249: case 250: case 251: case 252: case 253:
				player.setAttribute("newHairColour", HAIR_COLORS[button - 229]);
				break;

			//Male Beard Styles
			case 105: case 106: case 107: case 108: case 109:
			case 110: case 111: case 112:
				player.setAttribute("newBeard", button-95);
				break;
			case 113: case 114: case 115: case 116: case 117:
			case 118: case 119:
				player.setAttribute("newBeard", button-15);
				break;
			case 120:
				player.setAttribute("newBeard", button+185);
				break;
			case 123:
				player.setAttribute("newBeard", button+183);
				break;
			case 126:
				player.setAttribute("newBeard", button+181);
				break;
			case 129:
				player.setAttribute("newBeard", button+179);
				break;

			//Confirm Buttons
			case 196: //cash bag
			case 274: //The thumbs up
				confirm(player);
				/** confirms the players choice on design. **/
				break;
		}
		return true;
	}

	/**
	 * Represents the method used to handle the buttons for the female hairdresser interface.
	 * @param player the player.
	 * @param button the button,
	 */
	public static boolean handleFemaleInterfaceButtons(Player player, int button) {

		switch (button) {

			//Female Hair Styles
			case 148: case 149: case 150: case 151: case 152:
			case 153: case 154: case 155: case 156: case 157:
				player.setAttribute("newHair", button-103);
				break;
			case 158: case 159: case 160: case 161: case 162:
			case 163: case 164: case 165: case 166: case 167:
			case 168: case 169:
				player.setAttribute("newHair", button-23);
				break;
			case 170: case 171: case 172: case 173: case 174:
			case 175: case 176: case 177: case 178: case 179:
			case 180: case 181:
				player.setAttribute("newHair", button+99);
				break;

			//Female Hair Colour Styles
			//Unfortunately no 'fancy' calculations I can think of due to the array in CharacterDesign.java
			//I just copied the array over until a better solution could be thought of.
			case 73: case 74: case 75: case 76: case 77:
			case 78: case 79: case 80: case 81: case 82:
			case 83: case 84: case 85: case 86: case 87:
			case 88: case 89: case 90: case 91: case 92:
			case 93: case 94: case 95: case 96: case 97:
				player.setAttribute("newHairColour", HAIR_COLORS[button-73]);
				break;

			//Confirm buttons
			case 68: //cash bag
			case 100: //The thumbs up
				confirm(player);
				/** confirms the players choice on design. **/
				break;
		}
		return true;
	}

	/**
	 * The accepting of your new style.
	 * @param player the player.
	 */
	public static void confirm(Player player) {
		if (!player.getInventory().containsItem(COINS)) {
			return;
		}

		//Sets the player look
		if (player.getInventory().remove(COINS)) {
			if (player.getAttribute("newHair") != null) {
				player.getAppearance().getHair().changeLook((Integer) player.getAttribute("newHair"));
			}
			if (player.getAttribute("newHairColour") != null) {
				player.getAppearance().getHair().changeColor((Integer) player.getAttribute("newHairColour"));
			}
			if (player.getAttribute("newBeard") != null) {
				player.getAppearance().getBeard().changeLook((Integer) player.getAttribute("newBeard"));
			}

			//Updates the Player Look, Closes the interface, and farewell from the Hairdresser
			player.getAppearance().sync();
			player.getInterfaceManager().close();
			//TODO: Remove Changing Booth graphic and switch to Changing Booth disappearing animation. Needs to also work if player clicks away
			player.getDialogueInterpreter().sendDialogues(Repository.findNPC(598), FacialExpression.HAPPY, "Hope you like the new do!");
		}
	}
}
