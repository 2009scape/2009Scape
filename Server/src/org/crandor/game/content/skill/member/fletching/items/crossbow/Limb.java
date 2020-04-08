package org.crandor.game.content.skill.member.fletching.items.crossbow;

import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the enum for limbs.
 * @author 'Vexia
 */
public enum Limb {
	WOODEN_STOCK(new Item(9440), new Item(9420), new Item(9454), 9, 12, new Animation(4436)),
	OAK_STOCK(new Item(9442), new Item(9422), new Item(9176), 24, 32, new Animation(4437)),
	WILLOW_STOCK(new Item(9444), new Item(9423), new Item(9457), 39, 44, new Animation(4438)),
	TEAK_STOCK(new Item(9446), new Item(9425), new Item(9459), 46, 54, new Animation(4439)),
	MAPLE_STOCK(new Item(9448), new Item(9427), new Item(9461), 54, 64, new Animation(4440)),
	MAHOGANY_STOCK(new Item(9450), new Item(9429), new Item(9463), 61, 82, new Animation(4441)),
	YEW_STOCK(new Item(9452), new Item(9431), new Item(9465), 69, 100, new Animation(4442));

	/**
	 * Constructs a new {@code StringcrosbowPlugin.java} {@code Object}.
	 * @param stock the stock.
	 * @param limb the limb.
	 * @param product the product.
	 * @param level the level.
	 * @param experience the experience.
	 * @param animation the animation.
	 */
	Limb(Item stock, Item limb, Item product, int level, double experience, Animation animation) {
		this.stock = stock;
		this.limb = limb;
		this.product = product;
		this.level = level;
		this.experience = experience;
		this.animation = animation;
	}

	/**
	 * The stock.
	 */
	private final Item stock;

	/**
	 * The limb.
	 */
	private final Item limb;

	/**
	 * The product.
	 */
	private final Item product;

	/**
	 * The level.
	 */
	private final int level;

	/**
	 * The experience.
	 */
	private final double experience;

	/**
	 * The animation.
	 */
	private final Animation animation;

	/**
	 * Gets the stock.
	 * @return The stock.
	 */
	public Item getStock() {
		return stock;
	}

	/**
	 * Gets the limb.
	 * @return The limb.
	 */
	public Item getLimb() {
		return limb;
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
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Method used to get the {@link Limb} for the item.
	 * @param item the item.
	 * @return the limb.
	 */
	public static Limb forItems(final Item item, final Item second) {
		for (Limb l : Limb.values()) {
			if (l.getLimb().getId() == item.getId() && l.getStock().getId() == second.getId() || l.getLimb().getId() == second.getId() && l.getStock().getId() == item.getId()) {
				return l;
			}
		}
		return null;
	}
}