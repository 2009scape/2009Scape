/*
package org.crandor.game.content.skill.member.fletching.items.gem;

import org.crandor.game.node.item.Item;

*/
/**
 * Represents a gem bolt.
 * @author 'Vexia
 * @date 01/12/2013
 *//*

public enum GemBolt {
	OPAL(new Item(877, 10), new Item(45, 10), new Item(879, 10), 11, 1.5),
	PEARL(new Item(9140, 10), new Item(46, 10), new Item(880, 10), 41, 3.2), 
	JADE(new Item(9139, 10), new Item(9187, 10), new Item(9335, 10), 26, 2.4),
	RED_TOPAZ(new Item(9141, 10), new Item(9188, 10), new Item(9336, 10), 48, 3.9),
	SAPPHIRE(new Item(9142, 10), new Item(9189, 10), new Item(9337, 10), 56, 4), 
	EMERALD(new Item(9142, 10), new Item(9190, 10), new Item(9338, 10), 58, 5.5), 
	RUBY(new Item(9143, 10), new Item(9191, 10), new Item(9339, 10), 63, 6.3),
	DIAMOND(new Item(9143, 10), new Item(9192, 10), new Item(9340, 10), 65, 7),
	DRAGONSTONE(new Item(9144, 10), new Item(9193, 10), new Item(9341, 10), 71, 8.2),
	ONYX(new Item(9144, 10), new Item(9194, 10), new Item(9342, 10), 73, 9.4);

	*/
/**
	 * Constructs a new {@code GemBolt} {@code Object}.
	 * @param base the base.
	 * @param tip the tip.
	 * @param level the level.
	 * @param experience the experience.
	 *//*

	GemBolt(Item base, Item tip, Item product, int level, double experience) {
		this.base = base;
		this.tip = tip;
		this.product = product;
		this.level = level;
		this.experience = experience;
	}

	*/
/**
	 * Represents the base item.
	 *//*

	private final Item base;

	*/
/**
	 * Represents the tip to attach.
	 *//*

	private final Item tip;

	*/
/**
	 * Represents the product.
	 *//*

	private final Item product;

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
	 * Gets the base.
	 * @return The base.
	 *//*

	public Item getBase() {
		return base;
	}

	*/
/**
	 * Gets the tip.
	 * @return The tip.
	 *//*

	public Item getTip() {
		return tip;
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
	 * Method used to get the gem bolt from the id.
	 * @param boltt the boltt.
	 * @param tip the tip.
	 * @return the bolt.
	 *//*

	public static GemBolt forItems(final Item boltt, final Item tip) {
		for (GemBolt bolt : values()) {
			if (bolt.getBase().getId() == boltt.getId() && bolt.getTip().getId() == tip.getId() || bolt.getBase().getId() == tip.getId() && bolt.getTip().getId() == boltt.getId()) {
				return bolt;
			}
		}
		return null;
	}
}
*/
