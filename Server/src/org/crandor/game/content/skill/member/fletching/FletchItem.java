/*
package org.crandor.game.content.skill.member.fletching;

import org.crandor.game.node.item.Item;

*/
/**
 * Represents a fletching item to make.
 * @author 'Vexia
 *//*

public enum FletchItem {
	ARROW_SHAFT(FletchType.LOG, new Item(52, 15), 1, 5), SHORTBOW(FletchType.LOG, new Item(50), 5, 5),
	LONGBOW(FletchType.LOG, new Item(48), 10, 10),
	OAK_SHORTBOW(FletchType.OAK, new Item(54), 20, 16.5),
	OAK_LONGBOW(FletchType.OAK, new Item(56), 25, 25),
	WILLOW_SHORTBOW(FletchType.WILLOW, new Item(60), 35, 33.3),
	WILLOW_LONGBOW(FletchType.WILLOW, new Item(58), 40, 41.5),
	MAPLE_SHORTBOW(FletchType.MAPLE, new Item(64), 50, 50),
	MAPLE_LONGBOW(FletchType.MAPLE, new Item(62), 55, 58.3),
	YEW_SHORTBOW(FletchType.YEW, new Item(68), 65, 55),
	YEW_LONGBOW(FletchType.YEW, new Item(66), 70, 75),
	MAGIC_SHORTBOW(FletchType.MAGIC, new Item(72), 80, 83.3),
	MAGIC_LONGBOW(FletchType.MAGIC, new Item(70), 85, 91.5),
	WOODEN_STOCK(FletchType.LOG, new Item(9440), 9, 6),
	OAK_STOCK(FletchType.OAK, new Item(9442), 24, 16),
	WILLOW_STOCK(FletchType.WILLOW, new Item(9444), 39, 22),
	TEAK_STOCK(FletchType.TEAK, new Item(9446), 46, 27),
	MAPLE_STOCK(FletchType.MAPLE, new Item(9448), 54, 32),
	MAHOGANY_STOCK(FletchType.MAHOGANY, new Item(9450), 61, 41),
	YEW_STOCK(FletchType.YEW, new Item(9452), 69, 50);

	*/
/**
	 * Constructs a new {@code FletchItem.java} {@code Object}.
	 * @param type the type.
	 * @param product the product.
	 * @param level the level.
	 * @param experience the experience.
	 *//*

	FletchItem(final FletchType type, final Item product, final int level, final double experience) {
		this.type = type;
		this.product = product;
		this.level = level;
		this.experience = experience;
	}

	*/
/**
	 * Represents the type this item pertaint o.
	 *//*

	private FletchType type;

	*/
/**
	 * Represents the product of this item.
	 *//*

	private Item product;

	*/
/**
	 * Represents the level.
	 *//*

	private final int level;

	*/
/**
	 * Represents the experience.
	 *//*

	private final double experience;

	*/
/**
	 * Gets the type.
	 * @return The type.
	 *//*

	public FletchType getType() {
		return type;
	}

	*/
/**
	 * Sets the type.
	 * @param type The type to set.
	 *//*

	public void setType(FletchType type) {
		this.type = type;
	}

	*/
/**
	 * Gets the product.
	 * @return The product.
	 *//*

	public Item getProduct() {
		return product;
	}

	*/
/**
	 * Sets the product.
	 * @param product The product to set.
	 *//*

	public void setProduct(Item product) {
		this.product = product;
	}

	*/
/**
	 * Gets the level.
	 * @return The level.
	 *//*

	public int getLevel() {
		return level;
	}

	*/
/**
	 * Gets the experience.
	 * @return The experience.
	 *//*

	public double getExperience() {
		return experience;
	}
}
*/
