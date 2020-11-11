package plugin.skill.runecrafting;

import core.game.node.item.Item;

/**
 * Represents a tiara item and it's corresponding information related to
 * (runecrafting)
 * @author 'Vexia
 * @date 01/11/2013
 */
public enum Tiara {
	AIR(new Item(5527), 25),
	MIND(new Item(5529), 27.5),
	WATER(new Item(5531), 30),
	EARTH(new Item(5535), 32.5),
	FIRE(new Item(5537), 35),
	BODY(new Item(5533), 37.5),
	COSMIC(new Item(5539), 40),
	CHAOS(new Item(5543), 43.5),
	ASTRAL(new Item(9106), 43.5),
	NATURE(new Item(5541), 45),
	LAW(new Item(5545), 47.5),
	DEATH(new Item(5547), 50),
	BLOOD(new Item(5549), 52.5),
	SOUL(new Item(5551), 55);

	/**
	 * Constructs a new {@code Tiara} {@code Object}.
	 * @param tiara the tiara.
	 * @param experience the experience.
	 */
	Tiara(final Item tiara, final double experience) {
		this.tiara = tiara;
		this.experience = experience;
	}

	/**
	 * Represents the tiara item.
	 */
	private final Item tiara;

	/**
	 * Represents the experience gained.
	 */
	private final double experience;

	/**
	 * Gets the tiara.
	 * @return The tiara.
	 */
	public Item getTiara() {
		return tiara;
	}

	/**
	 * Gets the experience.
	 * @return The experience.
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * Method used to get the talisman for this tiara.
	 * @return the talisman.
	 */
	public Talisman getTalisman() {
		for (Talisman talisman : Talisman.values()) {
			if (talisman.name().equals(name())) {
				return talisman;
			}
		}
		return null;
	}

	/**
	 * Method used to get the <code>Tiara</code> by the item.
	 * @param item the item.
	 * @return the <code>Tiara</code> or <code>Null</code>.
	 */
	public static Tiara forItem(final Item item) {
		for (Tiara tiara : values()) {
			if (tiara.getTiara().getId() == item.getId()) {
				return tiara;
			}
		}
		return null;
	}
}
