/*
package org.crandor.game.content.skill.member.fletching.items.crossbow;

import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;

*/
/**
 * Represents the enum of stringing crossbows.
 * @author 'Vexia
 *//*

public enum StringCross {
	BRONZE_CBOW(new Item(9454), new Item(9174), 9, 6, new Animation(6671)),
	BLURITE_CBOW(new Item(9456), new Item(9176), 24, 16, new Animation(6672)),
	IRON_CBOW(new Item(9457), new Item(9177), 39, 22, new Animation(6673)),
	STEEL_CBOW(new Item(9459), new Item(9179), 46, 27, new Animation(6674)),
	MITHIRIL_CBOW(new Item(9461), new Item(9181), 54, 32, new Animation(6675)),
	ADAMANT_CBOW(new Item(9463), new Item(9183), 61, 41, new Animation(6676)),
	RUNITE_CBOW(new Item(9465), new Item(9185), 69, 50, new Animation(6677));
	*/
/**
	 * Constructs a new {@code StringcrossbowPlugin.java} {@code Object}.
	 * @param item the item.
	 * @param product the product.
	 * @param level the level.
	 * @param experience the experience.
	 *//*

	StringCross(final Item item, final Item product, final int level, final double experience, final Animation animation) {
		this.item = item;
		this.product = product;
		this.level = level;
		this.experience = experience;
		this.animation = animation;
	}

	*/
/**
	 * The item required.
	 *//*

	private final Item item;

	*/
/**
	 * The item product.
	 *//*

	private final Item product;

	*/
/**
	 * The level required.
	 *//*

	private final int level;

	*/
/**
	 * The experience required.
	 *//*

	private final double experience;

	*/
/**
	 * The animation of stringing.
	 *//*

	private final Animation animation;

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
	 * Method used to get the animation.
	 * @return the animation.
	 *//*

	public Animation getAnimation() {
		return animation;
	}

	*/
/**
	 * Method used to get the string bow for the item.
	 * @param item the item.
	 * @return the string bow.
	 *//*

	public static StringCross forItem(final Item item) {
		for (StringCross bw : StringCross.values()) {
			if (bw.getItem().getId() == item.getId()) {
				return bw;
			}
		}
		return null;
	}
}
*/
