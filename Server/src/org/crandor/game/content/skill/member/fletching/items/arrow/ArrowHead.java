/*
package org.crandor.game.content.skill.member.fletching.items.arrow;

import org.crandor.game.node.item.Item;

*/
/**
 * Represents the enum storing the arrow head information.
 * @author 'Vexia
 * @note brutal arrows after quest.
 *//*

public enum ArrowHead {
	BRONZE_ARROW(new Item(39), new Item(882), 1, 2.6),
	IRON_ARROW(new Item(40), new Item(884), 15, 3.8),
	STEEL_ARROW(new Item(41), new Item(886), 30, 6.3),
	MITHRIL_ARROW(new Item(42), new Item(888), 45, 8.8),
	ADAMANT_ARROW(new Item(43), new Item(890), 60, 10),
	RUNE_ARROW(new Item(44), new Item(892), 75, 13.8),
	DRAGON_ARROW(new Item(11237), new Item(11212), 90, 16.3),
	BROAD_ARROW(new Item(13278), new Item(4160), 52, 10);

	*/
/**
	 * Constructs a new {@code ArrowHead.java} {@code Object}.
	 * @param item the item.
	 * @param product the product.
	 * @param level the level.
	 * @param experience the experience.
	 *//*

	ArrowHead(Item item, Item product, int level, double experience) {
		this.item = item;
		this.product = product;
		this.level = level;
		this.experience = experience;
	}

	*/
/**
	 * Represents the arrow tip.
	 *//*

	private final Item item;

	*/
/**
	 * Represents the product item.
	 *//*

	private final Item product;

	*/
/**
	 * Represents the level required.
	 *//*

	private final int level;

	*/
/**
	 * Represents the experience gained.
	 *//*

	private final double experience;

	*/
/**
	 * Gets the item.
	 * @return The item.
	 *//*

	public Item getTips() {
		return item;
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

	*/
/**
	 * Gets the arrow head.
	 * @param item the item.
	 * @return the arrow head.
	 *//*

	public static ArrowHead forItem(final Item item) {
		for (ArrowHead arrow : ArrowHead.values()) {
			if (arrow.getTips().getId() == item.getId()) {
				return arrow;
			}
		}
		return null;
	}
}
*/
