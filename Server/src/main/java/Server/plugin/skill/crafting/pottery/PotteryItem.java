package plugin.skill.crafting.pottery;

import core.game.node.item.Item;

/**
 * Represents a pottery item definition.
 * @author 'Vexia
 */
public enum PotteryItem {
	POT(new Item(1787), new Item(1931), 1, 6.3, 6.3), DISH(new Item(1789), new Item(2313), 7, 15, 10), BOWL(new Item(1791), new Item(1923), 8, 18, 15), PLANT(new Item(5352), new Item(5350), 19, 20, 17.5), LID(new Item(4438), new Item(4440), 25, 20, 20);

	/**
	 * Represents the unfinished product.
	 */
	private final Item unfinished;

	/**
	 * Represents the product.
	 */
	private final Item product;

	/**
	 * Represents the level required.
	 */
	private final int level;

	/**
	 * Represents the exp given.
	 */
	private final double exp;

	/**
	 * Represents the fire exp.
	 */
	private final double fireExp;

	/**
	 * Constructs a new {@code PotteryItem} {@code Object}.
	 * @param unfinished the unfinished.
	 * @param product the product.
	 * @param level the level.
	 * @param exp the exp.
	 * @param fireExp the fire experience.
	 */
	private PotteryItem(Item unfinished, Item product, int level, double exp, double fireExp) {
		this.unfinished = unfinished;
		this.product = product;
		this.level = level;
		this.exp = exp;
		this.fireExp = fireExp;
	}

	/**
	 * Gets the pottery item by the id.
	 * @param id the id.
	 * @return the item.
	 */
	public static PotteryItem forId(int id) {
		for (PotteryItem def : PotteryItem.values()) {
			if (def.getUnfinished().getId() == id) {
				return def;
			}
		}
		return null;
	}

	/**
	 * Gets the unfinished.
	 * @return The unfinished.
	 */
	public Item getUnfinished() {
		return unfinished;
	}

	/**
	 * Gets the product.
	 * @return The product.
	 */
	public Item getProduct() {
		return product;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the exp.
	 * @return The exp.
	 */
	public double getExp() {
		return exp;
	}

	/**
	 * Gets the fireExp.
	 * @return The fireExp.
	 */
	public double getFireExp() {
		return fireExp;
	}

}
