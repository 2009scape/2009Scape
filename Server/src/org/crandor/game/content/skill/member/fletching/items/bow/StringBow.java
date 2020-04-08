/*
package org.crandor.game.content.skill.member.fletching.items.bow;

import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;

*/
/**
 * Represents the enum of stringing bows.
 * @author 'Vexia
 *//*

public enum StringBow {
	SHORT_BOW(new Item(50), new Item(841), 5, 5, new Animation(6678)),
	LONG_BOW(new Item(48), new Item(839), 10, 10, new Animation(6684)),
	OAK_SHORTBOW(new Item(54), new Item(843), 20, 16.5, new Animation(6679)),
	OAK_LONGBOW(new Item(56), new Item(845), 25, 25, new Animation(6685)),
	WILLOW_SHORTBOW(new Item(60), new Item(849), 35, 33.3, new Animation(6680)),
	WILLOW_LONGBOW(new Item(58), new Item(847), 40, 41.5, new Animation(6686)),
	MAPLE_SHORTBOW(new Item(64), new Item(853), 50, 50, new Animation(6681)),
	MAPLE_LONGBOW(new Item(62), new Item(851), 55, 58.3, new Animation(6687)),
	YEW_SHORTBOW(new Item(68), new Item(857), 65, 66, new Animation(6682)),
	YEW_LONGBOW(new Item(66), new Item(855), 70, 75, new Animation(6688)),
	MAGIC_SHORTBOW(new Item(72), new Item(861), 80, 83.3, new Animation(6683)),
	MAGIC_LONGBOW(new Item(70), new Item(859), 85, 91.5, new Animation(6689));


	StringBow(final Item item, final Item product, final int level, final double experience, final Animation animation) {
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

	public static StringBow forItem(final int id) {
		for (StringBow bw : StringBow.values()) {
			if (bw.getItem().getId() == id) {
				return bw;
			}
		}
		return null;
	}
}*/
