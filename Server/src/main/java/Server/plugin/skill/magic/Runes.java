package plugin.skill.magic;

import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.item.Item;

/**
 * Represents the <b>Constants</b> of runes.
 * @author 'Vexia
 */
public enum Runes {
	AIR_RUNE(556), WATER_RUNE(555), EARTH_RUNE(557), FIRE_RUNE(554), MIND_RUNE(558), NATURE_RUNE(561), CHAOS_RUNE(562), DEATH_RUNE(560), COSMIC_RUNE(564), BLOOD_RUNE(565), SOUL_RUNE(566), ASTRAL_RUNE(9075), LAW_RUNE(563),  BODY_RUNE(559), SARADOMIN_STAFF(2415), GUTHIX_STAFF(2416), ZAMORAK_STAFF(2417), ZURIELS_STAFF(13867);

	/**
	 * Constructs a new {@code Runes} {@code Object}.
	 * @param id the id.
	 */
	Runes(int id) {
		this.id = id;
	}

	/**
	 * The id of the rune.
	 */
	private int id;

	/**
	 * Gets the id of the rune.
	 * @return the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the rune by the id.
	 * @param id the id.
	 * @return the id.
	 */
	public static Runes forId(int id) {
		for (Runes rune : Runes.values()) {
			if (rune.getId() == id) {
				return rune;
			}
		}
		return null;
	}

	/**
	 * Checks if the player has an infinite amt of runes.
	 * @param rune the rune.
	 * @param weapon the weapon.
	 * @return {@code true} if so.
	 */
	public static final boolean isInfinite(Runes rune, Item weapon, SpellType... type) {
		if (weapon == null || rune == null) {
			return false;
		}
		if (type != null) {
			if (weapon.getId() == 2415 && rune == Runes.SARADOMIN_STAFF && type.length == 1) {
				if (type[0] == SpellType.GOD_STRIKE) {
					return true;
				}
			}
			if (weapon.getId() == 2416 && rune == Runes.GUTHIX_STAFF && type.length == 1) {
				if (type[0] == SpellType.GOD_STRIKE) {
					return true;
				}
			}
			if (weapon.getId() == 13867 && rune == Runes.ZURIELS_STAFF && type.length == 1) {
				if (type[0] == SpellType.BARRAGE || type[0] == SpellType.BLITZ 
						|| type[0] == SpellType.RUSH || type[0] == SpellType.BURST) {
					return true;
				}
			}
			if (weapon.getId() == 2417 && rune == Runes.ZAMORAK_STAFF && type.length == 1) {
				if (type[0] == SpellType.GOD_STRIKE) {
					return true;
				}
			}
		}
		if (rune == Runes.AIR_RUNE) {
			if (weapon.getId() == 1381 || weapon.getId() == 1397) // air staff
				return true;
		} else if (rune == Runes.WATER_RUNE) {
			if (weapon.getId() == 1383 || weapon.getId() == 1395) // water staff
				return true;
		} else if (rune == Runes.EARTH_RUNE) {
			if (weapon.getId() == 1385 || weapon.getId() == 1399) // earth staff
				return true;
		} else if (rune == Runes.FIRE_RUNE) {
			if (weapon.getId() == 1387 || weapon.getId() == 1393) // fire staff
				return true;
		}
		return false;
	}

	/**
	 * Method used to transform this item.
	 * @return the item.
	 */
	public Item transform() {
		return new Item(id);
	}

	/**
	 * Gets the item of this rune.
	 * @param amount The amount.
	 * @return The item instance.
	 */
	public Item getItem(int amount) {
		return new Item(id, amount);
	}
}
