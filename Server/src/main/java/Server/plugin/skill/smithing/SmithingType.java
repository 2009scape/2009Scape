package plugin.skill.smithing;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents a type of smithing.
 * @author Emperor
 */
public enum SmithingType {

	/**
	 * Dagger
	 */
	TYPE_DAGGER(1, 18, 19, new int[] { 24, 23, 22, 21 }, 1),

	/**
	 * Axe
	 */
	TYPE_AXE(1, 26, 27, new int[] { 32, 31, 30, 29 }, 1),

	/**
	 * Mace
	 */
	TYPE_MACE(1, 34, 35, new int[] { 40, 39, 38, 37 }, 1),

	/**
	 * Med helm
	 */
	TYPE_MEDIUM_HELM(1, 42, 43, new int[] { 48, 47, 46, 45 }, 1),

	/**
	 * Crossbow bolt
	 */
	TYPE_CROSSBOW_BOLT(1, 50, 51, new int[] { 56, 55, 54, 53 }, 10),

	/**
	 * Sword
	 */
	TYPE_SWORD(1, 58, 59, new int[] { 64, 63, 62, 61 }, 1),

	/**
	 * Dart tips
	 */
	TYPE_DART_TIP(1, 66, 67, new int[] { 72, 71, 70, 69 }, 10),

	/**
	 * Nails
	 */
	TYPE_NAIL(1, 74, 75, new int[] { 80, 79, 78, 77 }, 15),
	
	/**
	 * Bullseye 
	 */
	TYPE_BULLSEYE(1, 90, 91, new int[] { 96, 95, 94, 93 }, 1),

	/**
	 * Spit Iron
	 */
	TYPE_SPIT_IRON(1, 90, 91, new int[] { 96, 95, 94, 93 }, 1),
	/**
	 * 
	 */
	TYPE_WIRE(1, 90, 91, new int[] { 96, 95, 94, 93 }, 1),

	/**
	 * Arrow Tips
	 */
	TYPE_ARROW_TIP(1, 106, 107, new int[] { 112, 111, 110, 109 }, 15),

	/**
	 * Scimitar
	 */
	TYPE_SCIMITAR(2, 114, 115, new int[] { 120, 119, 118, 117 }, 1),

	/**
	 * Crossbow Limbs
	 */
	TYPE_CROSSBOW_LIMB(1, 122, 123, new int[] { 128, 127, 126, 125 }, 1),

	/**
	 * LongSword
	 */
	TYPE_LONGSWORD(2, 130, 131, new int[] { 136, 135, 134, 133 }, 1),

	/**
	 * Throwing Knife
	 */
	TYPE_THROWING_KNIFE(1, 138, 139, new int[] { 144, 143, 142, 141 }, 5),

	/**
	 * Full helm
	 */
	TYPE_FULL_HELM(2, 146, 147, new int[] { 152, 151, 150, 149 }, 1),

	/**
	 * Square Shield
	 */
	TYPE_SQUARE_SHIELD(2, 154, 155, new int[] { 160, 159, 158, 157 }, 1),

	/**
	 * Latern
	 */
	TYPE_LANTERN(1, 162, 163, new int[] { 168, 167, 166, 165 }, 1),

	/**
	 * Oil Lantern
	 */
	TYPE_OIL_LANTERN(1,162,163,new int[] {168,167,166,165},1),

	/**
	 * Grapple Tips
	 */
	TYPE_GRAPPLE_TIP(1, 170, 171, new int[] { 175, 176, 175, 174, 173 }, 1),

	/**
	 * The studs type.
	 */
	TYPE_STUDS(1, 170, 171, new int[] { 175, 176, 174, 173, 172 }, 1),

	/**
	 * Warhammer
	 */
	TYPE_WARHAMMER(3, 178, 179, new int[] { 184, 183, 182, 181 }, 1),

	/**
	 * Battle axe
	 */
	TYPE_BATTLE_AXE(3, 186, 187, new int[] { 192, 191, 190, 189 }, 1),

	/**
	 * Chainbody
	 */
	TYPE_CHAINBODY(3, 194, 195, new int[] { 200, 199, 198, 197 }, 1),

	/**
	 * Kite shield
	 */
	TYPE_KITE_SHIELD(3, 202, 203, new int[] { 208, 207, 206, 205 }, 1),

	/**
	 * Claws
	 */
	TYPE_CLAWS(2, 210, 211, new int[] { 216, 215, 214, 213 }, 1),

	/**
	 * 2H
	 */
	TYPE_TWO_HAND_SWORD(3, 218, 219, new int[] { 224, 223, 222, 221 }, 1),

	/**
	 * Plate Skirt
	 */
	TYPE_PLATE_SKIRT(3, 226, 227, new int[] { 232, 231, 230, 229 }, 1),

	/**
	 * Platelegs
	 */
	TYPE_PLATELEG(3, 234, 235, new int[] { 240, 239, 238, 237 }, 1),

	/**
	 * Platebody
	 */
	TYPE_PLATEBODY(5, 242, 243, new int[] { 248, 247, 246, 245 }, 1),

	/**
	 * Pickaxe
	 */
	TYPE_PICKAXE(2, 267, 268, new int[] { 273, 272, 271, 270 }, 1);

	private int name;

	private int[] button;

	private int child;

	private int required;

	private int product_amount;

	/**
	 * Constructs a new {@code SmithingType.java} {@code Object}.
	 * @param requiredBars bars.
	 * @param interfaceChild the interface child.
	 * @param nameChild the name child.
	 * @param buttonId the button id.
	 * @param productedAmount the producted amount.
	 */
	SmithingType(int requiredBars, int interfaceChild, int nameChild, int[] buttonId, int productedAmount) {
		this.name = nameChild;
		this.button = buttonId;
		this.child = interfaceChild;
		this.required = requiredBars;
		this.product_amount = productedAmount;
	}

	/**
	 * @return the name.
	 */
	public int getName() {
		return name;
	}

	/**
	 * @return the button.
	 */
	public int[] getButton() {
		return button;
	}

	/**
	 * @return the child.
	 */
	public int getChild() {
		return child;
	}

	/**
	 * @return the required.
	 */
	public int getRequired() {
		return required;
	}

	/**
	 * @return the product_amount.
	 */
	public int getProductAmount() {
		return product_amount;
	}

	/**
	 * @param name the name to set.
	 */
	public void setName(int name) {
		this.name = name;
	}

	public static int forButton(Player player, Bars bar, int button, int item) {
		int count = 0;
		if (bar == null) {
			return -1;
		}
		for (int i = 0; i < bar.getSmithingType().getButton().length; i++) {
			if (bar.getSmithingType().getButton()[i] != button) {
				count++;
			} else {
				if (count == 0) {
					count = 1;
				} else if (count == 1) {
					count = 5;
				} else if (count == 2) {
					count = -1;
				} else if (count == 3) {
					count = player.getInventory().getAmount(new Item(item));
				}
				return count;
			}
		}
		return -1;
	}
}
