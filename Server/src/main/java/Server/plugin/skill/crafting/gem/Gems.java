package plugin.skill.crafting.gem;

import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

/**
 * Represents a gem enumeration.
 * @author 'Vexia
 */
public enum Gems {
	SAPPHIRE(new Item(1623), new Item(1607), 20, new Animation(888), 50),
	EMERALD(new Item(1621), new Item(1605), 27, new Animation(889), 67),
	RUBY(new Item(1619), new Item(1603), 34, new Animation(887), 85),
	DIAMOND(new Item(1617), new Item(1601), 43, new Animation(886), 107.5),
	DRAGONSTONE(new Item(1631), new Item(1615), 55, new Animation(885), 137.5),
	ONYX(new Item(6571), new Item(6573), 67, new Animation(2717), 168),
	OPAL(new Item(1625), new Item(1609), 1, new Animation(890), 10),
	JADE(new Item(1627), new Item(1611), 13, new Animation(891), 20),
	RED_TOPAZ(new Item(1629), new Item(1613), 16, new Animation(892), 25);

	/**
	 * Represents the raw gem.
	 */
	private final Item uncut;

	/**
	 * Represents the gem cut.
	 */
	private final Item gem;

	/**
	 * Represents the level needed.
	 */
	private final int level;

	/**
	 * Represents the animation used.
	 */
	private final Animation animation;

	/**
	 * Represents the experience gained.
	 */
	private final double exp;

	/**
	 * Constructs a new {@code Gems} {@code Object}.
	 * @param uncut the uncut.
	 * @param gem the gem.
	 * @param level the level.
	 * @param animation the animation.
	 * @param exp the exp.
	 */
	private Gems(Item uncut, Item gem, int level, Animation animation, double exp) {
		this.uncut = uncut;
		this.gem = gem;
		this.level = level;
		this.animation = animation;
		this.exp = exp;
	}

	/**
	 * Gets the gem by the id.
	 * @param id the id.
	 * @return the gem.
	 */
	public static Gems forId(final Item item) {
		for (Gems gem : Gems.values()) {
			if (gem.getUncut().getId() == item.getId()) {
				return gem;
			}
		}
		return null;
	}

	/**
	 * Gets the uncut.
	 * @return The uncut.
	 */
	public Item getUncut() {
		return uncut;
	}

	/**
	 * Gets the gem.
	 * @return The gem.
	 */
	public Item getGem() {
		return gem;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Gets the exp.
	 * @return The exp.
	 */
	public double getExp() {
		return exp;
	}
}
