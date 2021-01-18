package plugin.skill.crafting.armour;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents a useful class for leather crafting related information.
 * @author 'Vexia
 */
public final class LeatherCrafting {

	/**
	 * Constant of a needle id.
	 */
	public static final int NEEDLE = 1733;

	/**
	 * Constant of a thread id.
	 */
	public static final Item THREAD = new Item(1734, 1);

	/**
	 * Constant of a needle id.
	 */
	public static final int LEATHER = 1741;

	/**
	 * Constant of a thread id.
	 */
	public static final int HARD_LEATHER = 1743;

	/**
	 * The constants of leather id's related to dragon hides.
	 */
	public static final int GREEN_LEATHER = 1745, BLUE_LEATHER = 2505, RED_LEATHER = 2507, BLACK_LEATHER = 2509;

	/**
	 * The leather interface component.
	 */
	private static final Component COMPONENT = new Component(154);

	/**
	 * Checks if its the last thrad.
	 * @return {@code True} if so.
	 */
	public static boolean isLastThread(final Player player) {
		final Item thread = getThread(player);
		if (thread == null) {
			return false;
		}
		int charge = thread.getCharge();
		return charge >= 1004;
	}

	/**
	 * Method used to decay thread.
     */
	public static void decayThread(final Player player) {
		final Item thread = getThread(player);
		if (thread == null) {
			return;
		}
		int charge = thread.getCharge();
		thread.setCharge(charge + 1);
	}

	/**
	 * Method used to remove thread.
	 * @param player the player.
     */
	public static void removeThread(final Player player) {
		if (player.getInventory().remove(THREAD)) {
			player.getPacketDispatch().sendMessage("You use a reel of your thread.");
			Item thread = getThread(player);
			if (thread != null) {
				thread.setCharge(1000);
			}
		}
	}

	/**
	 * Gets the thread.
	 * @param player the player.
	 * @return the item.
	 */
	public static Item getThread(final Player player) {
		return player.getInventory().get(player.getInventory().getSlot(THREAD));
	}

	/**
	 * Represents the dragon hide crafting data.
	 * @author 'Vexia
	 */
	public enum DragonHide {
		GREEN_D_HIDE_VAMBS(1745, 1, 1065, 57, 62),
		GREEN_D_HIDE_CHAPS(1745, 2, 1099, 60, 124),
		GREEN_D_HIDE_BODY(1745, 3, 1135, 63, 186),
		BLUE_D_HIDE_VAMBS(2505, 1, 2487, 66, 70),
		BLUE_D_HIDE_CHAPS(2505, 2, 2493, 68, 140),
		BLUE_D_HIDE_BODY(2505, 3, 2499, 71, 210),
		RED_D_HIDE_VAMBS(2507, 1, 2489, 73, 78),
		RED_D_HIDE_CHAPS(2507, 2, 2495, 75, 156),
		RED_D_HIDE_BODY(2507, 3, 2501, 77, 234),
		BLACK_D_HIDE_VAMBS(2509, 1, 2491, 79, 86),
		BLACK_D_HIDE_CHAPS(2509, 2, 2497, 82, 172),
		BLACK_D_HIDE_BODY(2509, 3, 2503, 84, 258);

		/**
		 * Constructs a new {@code LeatherCrafting.java} {@code Object}.
		 * @param leather the leather required.
		 * @param amount the amount of leather needed.
		 * @param product the product item.
		 * @param level the level required.
		 * @param experience the experience.
		 */
		DragonHide(int leather, int amount, int product, int level, double experience) {
			this.leather = leather;
			this.amount = amount;
			this.product = product;
			this.level = level;
			this.experience = experience;
		}

		/**
		 * The leather id.
		 */
		private final int leather;

		/**
		 * The leather amount required.
		 */
		private final int amount;

		/**
		 * The product.
		 */
		private final int product;

		/**
		 * The required level.
		 */
		private final int level;

		/**
		 * The experiences gained.
		 */
		private final double experience;

		/**
		 * @return the leather.
		 */
		public int getLeather() {
			return leather;
		}

		/**
		 * @return the amount.
		 */
		public int getAmount() {
			return amount;
		}

		/**
		 * @return the product.
		 */
		public int getProduct() {
			return product;
		}

		/**
		 * @return the level.
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * @return the experience.
		 */
		public double getExperience() {
			return experience;
		}

		/**
		 * Method used to return the type for the leather.
		 * @param leather the leather.
		 * @return the hide.
		 */
		public static DragonHide forLeather(int leather) {
			for (DragonHide hide : DragonHide.values()) {
				if (hide.getLeather() == leather) {
					return hide;
				}
			}
			return null;
		}
	}

	/**
	 * Represents an enumeration of soft leather crafting values.
	 * @author 'Vexia
	 */
	public enum SoftLeather {
		ARMOUR(28, 14, 25, new Item(1129)),
		GLOVES(29, 1, 13.8, new Item(1059)),
		BOOTS(30, 7, 16.3, new Item(1061)),
		VAMBRACES(31, 11, 22, new Item(1063)),
		CHAPS(32, 18, 27, new Item(1095)),
		COIF(33, 38, 37, new Item(1169)),
		COWL(34, 9, 18.5, new Item(1167));

		/**
		 * Constructs a new {@code Soft} {@code Object}.
		 * @param level the level.
		 * @param experience the experience.
		 * @param product the product.
		 */
		SoftLeather(int button, int level, double experience, Item product) {
			this.button = button;
			this.level = level;
			this.experience = experience;
			this.product = product;
		}

		/**
		 * The button.
		 */
		private final int button;

		/**
		 * The level required.
		 */
		private final int level;

		/**
		 * The experience gained.
		 */
		private final double experience;

		/**
		 * The product.
		 */
		private final Item product;

		/**
		 * Method used to open the crafting interface.
		 * @param player the player.
		 */
		public static void open(final Player player) {
			player.getInterfaceManager().open(COMPONENT);
		}

		/**
		 * @return the level.
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * @return the experience.
		 */
		public double getExperience() {
			return experience;
		}

		/**
		 * @return the product.
		 */
		public Item getProduct() {
			return product;
		}

		/**
		 * @return the button.
		 */
		public int getButton() {
			return button;
		}

		/**
		 * Method used to get the value by the id.
		 */
		public static SoftLeather forButton(int button) {
			for (SoftLeather soft : SoftLeather.values()) {
				if (soft.getButton() == button) {
					return soft;
				}
			}
			return null;
		}
	}
}
