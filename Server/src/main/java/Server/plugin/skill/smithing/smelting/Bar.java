package plugin.skill.smithing.smelting;

import core.game.node.item.Item;

/**
 * Represents the <b>Bar</b> that can be created during a <p> Smelting </p>
 * session.
 * @author 'Vexia
 */
public enum Bar {
	BRONZE(1, 6.2, new Item(2349, 1), new Item(436, 1), new Item(438, 1)), 
	BLURITE(8, 8, new Item(9467, 1), new Item(668, 1)), 
	IRON(15, 12.5, new Item(2351, 1), new Item(440)), 
	SILVER(20, 13.7, new Item(2355, 1), new Item(442, 1)), 
	STEEL(30, 17.5, new Item(2353, 1), new Item(453, 2), new Item(440, 1)), 
	GOLD(40, 22.5, new Item(2357, 1), new Item(444, 1)), 
	MITHRIL(50, 30, new Item(2359, 1), new Item(447, 1), new Item(453, 4)), 
	ADAMANT(70, 37.5, new Item(2361, 1), new Item(449, 1), new Item(453, 6)), 
	RUNITE(85, 50, new Item(2363, 1), new Item(451, 1), new Item(453, 8)),
	PERFECT_GOLD(40, 22.5,  new Item(2365, 1) ,new Item(446, 1));

	/**
	 * The ore required.
	 */
	private final Item[] ores;

	/**
	 * The product gained.
	 */
	private final Item product;

	/**
	 * The level required.
	 */
	private final int level;

	/**
	 * The experience gained.
	 */
	private final double experience;

	/**
	 * Constructs a new {@code Bar} {@code Object}.
	 * @param level the level.
	 * @param experience the experience.
	 * @param product the product.
	 * @param ores the ores.
	 */
	Bar(int level, double experience, Item product, Item... ores) {
		this.level = level;
		this.experience = experience;
		this.product = product;
		this.ores = ores;
	}

	public static Bar forId(int id) {
		for (Bar bar : Bar.values()) {
			if (bar.getProduct().getId() == id) {
				return bar;
			}
		}
		return null;
	}

	public static Bar forOre(int id) {
		for (Bar bar : Bar.values()) {
			for (Item i : bar.getOres()) {
				if (i.getId() == id) {
					return bar;
				}
			}
		}
		return null;
	}

	/**
	 * @return the ores.
	 */
	public Item[] getOres() {
		return ores;
	}

	/**
	 * @return the product.
	 */
	public Item getProduct() {
		return product;
	}

	/**
	 * @return the level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the experience.
	 */
	public double getExperience() {
		return experience;
	}

}
