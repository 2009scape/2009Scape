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
 * Represents the interface plugin to handle thessalia interfaces.
 */
@InitializablePlugin
public final class ThessaliaInterface extends ComponentPlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 1000);

	//Since the order of the clothing is different in the initial character creation menu, we have to reorder the arrays
	// It's possible to reorder the actual legs, arms and chest IDs in characterdesign.java, but it's probably best to keep them independent.
	private static final int[] TORSO_COLORS = new int[] {24, 23, 2, 22, 12, 11, 6, 19, 4, 0, 9, 13, 25, 8, 15, 26, 21, 7, 20, 14, 10, 28, 27, 3, 5, 18, 17, 1, 16};
	private static final int[] LEG_COLORS = new int[] {26, 24, 23, 3, 22, 13, 12, 7, 19, 5, 1, 10, 14, 25, 9, 0, 21, 8, 20, 15, 11, 28, 27, 4, 6, 18, 17, 2, 16};

	private static final int[] MALE_TORSO_IDS = {111, 113, 114, 115, 112, 116, 18, 19, 20, 21, 22, 23, 24, 25};
	private static final int[] MALE_ARMS_IDS = {105, 108, 106, 107, 109, 110, 28, 26, 27, 29, 30, 31};
	private static final int[] MALE_LEGS_IDS = {36, 85, 37, 89,  90, 40, 86, 88, 39, 38, 87};

	private static final int[] FEMALE_TORSO_IDS = {153, 155, 156, 157, 154, 158, 56, 57, 58, 59, 60};
	private static final int[] FEMALE_ARMS_IDS = {147, 150, 148, 149, 151, 152, 64, 61, 63, 65, 62};
	private static final int[] FEMALE_LEGS_IDS = {129, 130, 128, 74, 133, 134, 77, 131, 132, 75, 73, 76, 72, 70, 71}; //ID 71 is called shorts in game but is actually a skirt (In older menu too, Jagex Logic?)

	//Default ID for Clothing Category (Torso, Arms, Legs). Used for Recolouring later.
	private static int categorySelection;


	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(591, this);
		ComponentDefinition.put(594, this);
		categorySelection = 182; //Resets the Category Selection (in case of gender change/last category change) everytime the menu is accessed
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
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
	 * Handles the interface buttons for a male player
	 * @param player the player.
	 * @param button the button id in the interface
	 **/
	private static boolean handleMaleInterfaceButtons(Player player, int button) {
		switch(button){

			//Top Styles
			case 185: case 186: case 187: case 188:
			case 189: case 190: case 191: case 192:
			case 193: case 194: case 195: case 196:
			case 197: case 198:
				player.setAttribute("newTorso", MALE_TORSO_IDS[button-185]);
				break;

			//Arms Styles
			case 199: case 200: case 201: case 202:
			case 203: case 204: case 205: case 206:
			case 207: case 208: case 209: case 210:
				player.setAttribute("newArms", MALE_ARMS_IDS[button-199]);
				break;

			//Legs Styles
			case 211: case 212: case 213: case 214:
			case 215: case 216: case 217: case 218:
			case 219: case 220: case 221:
				player.setAttribute("newLegs", MALE_LEGS_IDS[button-211]);
				break;

			//Clothing Colour Buttons
			case 252: case 253: case 254: case 255: case 256:
			case 257: case 258: case 269: case 260: case 261:
			case 262: case 263: case 264: case 265: case 266:
			case 267: case 268: case 279: case 270: case 271:
			case 272: case 273: case 274: case 275: case 276:
			case 277: case 278: case 289: case 280:
				changeClothingColour(player, categorySelection, button);
				break;

			//Are they selecting Tops, Arms, or Pants? Needed for Recolouring
			case 182:
				categorySelection = 182;
				break;
			case 183:
				categorySelection = 183;
				break;
			case 184:
				categorySelection = 184;
				break;

			//Confirm Buttons
			case 180:
			case 297:
				confirm(player);
				break;
		}
		return true;
	}

	/**
	 * Handles the interface buttons for a Female player
	 * @param player the player.
	 * @param button the button id in the interface
	 **/
	private static boolean handleFemaleInterfaceButtons(Player player, int button) {
		switch(button){

			//Top Styles
			case 186: case 187: case 188: case 189:
			case 190: case 191: case 192: case 193:
			case 194: case 195: case 196:
				player.setAttribute("newTorso", FEMALE_TORSO_IDS[button-186]);
				break;

			//Arms Styles
			case 197: case 198: case 199: case 200:
			case 201: case 202: case 203: case 204:
			case 205: case 206: case 207:
				player.setAttribute("newArms", FEMALE_ARMS_IDS[button-197]);
				break;

			//Legs Styles
			case 208: case 209: case 210: case 211:
			case 212: case 213: case 214: case 215:
			case 216: case 217: case 218: case 219:
			case 220: case 221: case 222:
				player.setAttribute("newLegs", FEMALE_LEGS_IDS[button-208]);
				break;

			//Clothing Colour Buttons
			case 253: case 254: case 255: case 256: case 257:
			case 258: case 259: case 260: case 261: case 262:
			case 263: case 264: case 265: case 266: case 267:
			case 268: case 269: case 270: case 271: case 272:
			case 273: case 274: case 275: case 276: case 277:
			case 278: case 279: case 280: case 281:
				changeClothingColour(player, categorySelection, button);
				break;

			//Are they selecting Tops, Arms, or Pants? Needed for Recolouring
			case 183:
				categorySelection = 183;
				break;
			case 184:
				categorySelection = 184;
				break;
			case 185:
				categorySelection = 185;
				break;

			//Confirm buttons
			case 181:
			case 298:
				confirm(player);
				break;
		}
		return true;
	}

	/**
	 * Changes Torso or Leg Colour depending on the value of the category selected
	 * @param player the player.
	 * @param categorySelection the category button (arms/legs/torso) chosen by the player for recolouring.\
	 * @param button the button id of the colour chosen
	 */
	private static void changeClothingColour(Player player, int categorySelection, int button) {
		//Male Recolours, have to check if male because of button id overlap
		if((categorySelection == 182 || categorySelection == 183) && player.isMale()){
			player.setAttribute("newTorsoColour", TORSO_COLORS[button-252]);
		}
		else if(categorySelection == 184 && player.isMale()){
			player.setAttribute("newLegsColour", LEG_COLORS[button-252]);
		}

		//Female Recolours, have to check if male because of button id overlap
		//because of overlap, we have to include 182 in case the player never clicks on one of the category buttons
		//the player.isMale() == false check should prevent any bugs arising from this...?
		if((categorySelection == 182 || categorySelection == 183 || categorySelection == 184) && player.isMale() == false){
			player.setAttribute("newTorsoColour", TORSO_COLORS[button-253]);
		}
		else if(categorySelection == 185 && player.isMale() == false){
			player.setAttribute("newLegsColour", LEG_COLORS[button-253]);
		}
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
			if (player.getAttribute("newTorso") != null) {
				player.getAppearance().getTorso().changeLook((Integer) player.getAttribute("newTorso"));
			}
			if (player.getAttribute("newArms") != null) {
				player.getAppearance().getArms().changeLook((Integer) player.getAttribute("newArms"));
			}
			if (player.getAttribute("newLegs") != null) {
				player.getAppearance().getLegs().changeLook((Integer) player.getAttribute("newLegs"));
			}
			if (player.getAttribute("newTorsoColour") != null) {
				player.getAppearance().getTorso().changeColor((Integer) player.getAttribute("newTorsoColour"));
			}
			if (player.getAttribute("newLegsColour") != null) {
				player.getAppearance().getLegs().changeColor((Integer) player.getAttribute("newLegsColour"));
			}

			//Updates the Player Look, Closes the interface, and farewell from Thessalia
			player.getAppearance().sync();
			player.getInterfaceManager().close();
			//TODO: Remove Changing Booth graphic and switch to Changing Booth disappearing animation. Needs to also work if player clicks away
			player.getDialogueInterpreter().sendDialogues(Repository.findNPC(548), FacialExpression.AMAZED, "Woah! Fabulous! You look absolutely great!");
		}
	}
}
