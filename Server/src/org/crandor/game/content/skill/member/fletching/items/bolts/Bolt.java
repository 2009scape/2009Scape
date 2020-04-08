/*
package org.crandor.game.content.skill.member.fletching.items.bolts;

import org.crandor.game.node.item.Item;

*/
/**
 * Represents an enum of bolts.
 * @author 'Vexia
 *//*

public enum Bolt {
	BRONZE_BOLT(new Item(9375), new Item(877), 9, 0.5),
	BLURITE_BOLT(new Item(9376), new Item(9139), 24, 1),
	IRON_BOLT(new Item(9377), new Item(9140), 39, 1.5),
	SILVER_BOLT(new Item(9382), new Item(9145), 43, 2.5),
	STEEL_BOLT(new Item(9378), new Item(9141), 46, 3.5),
	MITHRIL_BOLT(new Item(9379), new Item(9142), 54, 5),
	ADAMANTITE_BOLT(new Item(9380), new Item(9143), 61, 7),
	RUNITE_BOLT(new Item(9381), new Item(9144), 69, 10),
	BROAD_BOLT(new Item(13279), new Item(13280), 55, 3);

	*/
/**
	 * The item required.
	 *//*

	private final Item item;

	*/
/**
	 * The product recieved.
	 *//*

	private final Item product;

	*/
/**
	 * The level required.
	 *//*

	private final int level;

	*/
/**
	 * The experience gained.
	 *//*

	private final double experience;

	*/
/**
	 * Constructs a new {@code Bolt} {@code Object}.
	 * @param item the item.
	 * @param product the product.
	 * @param level the level.
	 * @param experience the experienece.
	 *//*

	Bolt(Item item, Item product, int level, double experience) {
		this.item = item;
		this.product = product;
		this.level = level;
		this.experience = experience;
	}

	*/
/**
	 * Gets the item.
	 * @return The item.
	 *//*

	public Item getItem() {
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
	 * Method used to get the bolt for the item.
	 * @param item the item.
	 * @return the bolt.
	 *//*

	public static Bolt forItem(final Item item) {
		for (Bolt bolt : Bolt.values()) {
			if (bolt.getItem().getId() == item.getId()) {
				return bolt;
			}
		}
		return null;
	}
}
*/
