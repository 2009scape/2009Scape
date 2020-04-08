package org.crandor.game.content.skill.member.fletching.items.darts;

import org.crandor.game.node.item.Item;

/**
 * Represents the enum to hold dart info.
 * @author 'Vexia
 */
public enum Dart {
	BRONZE_DART(new Item(819), new Item(806), 1, 1.8),
	IRON_DART(new Item(820), new Item(807), 22, 3.8),
	STEEL_DART(new Item(821), new Item(808), 37, 7.5),
	MITHRIL_DART(new Item(822), new Item(809), 52, 11.2),
	ADAMANT_DART(new Item(823), new Item(810), 67, 15),
	RUNE_DART(new Item(824), new Item(811), 81, 18.8),
	DRAGON_DART(new Item(11232), new Item(11230), 95, 25);
	/**
	 * Constructs a new {@code Dart} {@code Object}.
	 * @param item the item.
	 * @param product the product.
	 * @param level the level.
	 * @param experience the experience.
	 */
	Dart(final Item item, final Item product, final int level, final double experience) {
		this.item = item;
		this.product = product;
		this.level = level;
		this.experience = experience;
	}

	/**
	 * Represents the item required.
	 */
	private final Item item;

	/**
	 * Represents the product gained.
	 */
	private final Item product;

	/**
	 * Represents the level required.
	 */
	private final int level;

	/**
	 * Represents the experience gained.
	 */
	private final double experience;

	/**
	 * Gets the item.
	 * @return The item.
	 */
	public Item getItem() {
		return item;
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
	 * Gets the experience.
	 * @return The experience.
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * Method used to get the dart for the item.
	 * @param item the item.
	 * @return the dart.
	 */
	public static Dart forItem(final Item item) {
		for (Dart dart : Dart.values()) {
			if (dart.getItem().getId() == item.getId()) {
				return dart;
			}
		}
		return null;
	}
}
