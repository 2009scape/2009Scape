package plugin.skill.runecrafting;

import plugin.skill.magic.Runes;
import core.game.node.item.Item;

/**
 * Represents a <b>Rune</b> type to craft.
 * @author 'Vexia
 * @date 01/11/2013
 */
public enum Rune {
	AIR(Runes.AIR_RUNE.transform(), 1, 5, new int[] { 1, 11, 22, 33, 44, 55, 66, 77, 88, 99 }),
	MIND(Runes.MIND_RUNE.transform(), 2, 5.5, new int[] { 1, 14, 28, 42, 56, 70, 84, 98 }),
	WATER(Runes.WATER_RUNE.transform(), 5, 6, new int[] { 1, 19, 38, 57, 76, 95 }),
	EARTH(Runes.EARTH_RUNE.transform(), 9, 6.5, new int[] { 1, 26, 52, 78 }),
	FIRE(Runes.FIRE_RUNE.transform(), 14, 7, new int[] { 1, 35, 70 }),
	BODY(Runes.BODY_RUNE.transform(), 20, 7.5, new int[] { 1, 46, 92 }),
	COSMIC(Runes.COSMIC_RUNE.transform(), 27, 8, new int[] { 1, 59 }),
	CHAOS(Runes.CHAOS_RUNE.transform(), 35, 8.5, new int[] { 1, 74 }),
	ASTRAL(Runes.ASTRAL_RUNE.transform(), 40, 8.7, new int[] { 1, 82 }),
	NATURE(Runes.NATURE_RUNE.transform(), 44, 9, new int[] { 1, 91 }),
	LAW(Runes.LAW_RUNE.transform(), 54, 9.5),
	DEATH(Runes.DEATH_RUNE.transform(), 65, 10),
	BLOOD(Runes.BLOOD_RUNE.transform(), 77, 10.5),
	SOUL(Runes.SOUL_RUNE.transform(), 90, 11);

	/**
	 * Constructs a new {@code Rune} {@code Object}.
	 * @param rune the rune.
	 * @param level the level.
	 * @param experience the experience.
	 * @param multiple
	 */
	Rune(Item rune, int level, double experience, int... multiple) {
		this.rune = rune;
		this.level = level;
		this.experience = experience;
		this.multiple = multiple;
	}

	/**
	 * Represents the rune item to craft.
	 */
	private final Item rune;

	/**
	 * Represents the level required.
	 */
	private final int level;

	/**
	 * Represents the experience gained.
	 */
	private final double experience;

	/**
	 * Represents the multiple runes allowed to create at a certain level.
	 */
	private final int[] multiple;

	/**
	 * Gets the rune.
	 * @return The rune.
	 */
	public Item getRune() {
		return rune;
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
	 * Gets the multiple.
	 * @return The multiple.
	 */
	public int[] getMultiple() {
		return multiple;
	}

	/**
	 * Checks if this rune uses normal essence.
	 * @return <code>True</code> if so.
	 */
	public final boolean isNormal() {
		return this == AIR || this == MIND || this == WATER || this == EARTH || this == FIRE || this == BODY;
	}

	/**
	 * Checks if this rune is a multi creation rune.
	 * @return <code>True</code> if so.
	 */
	public final boolean isMultiple() {
		return getMultiple() != null;
	}

	/**
	 * Method used to get the <code>Rune</code> by the item.
	 * @param item the item.
	 * @return the <code>Rune</code> or <code>Null</code>.
	 */
	public static Rune forItem(final Item item) {
		for (Rune rune : values()) {
			if (rune.getRune().getId() == item.getId()) {
				return rune;
			}
		}
		return null;
	}

	/**
	 * Method used to the <code>Rune</code> by the name.
	 * @param name the name.
	 * @return the <code>Rune</code> or <code>Null</code>.
	 */
	public static Rune forName(final String name) {
		for (Rune r : Rune.values()) {
			if (r.name().equals(name)) {
				return r;
			}
		}
		return null;
	}
}
