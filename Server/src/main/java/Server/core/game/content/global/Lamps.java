package core.game.content.global;

import core.game.node.item.Item;

/**
 * Represents an experience lamp.
 * @author Vexia
 */
public enum Lamps {
	GENIE_LAMP(new Item(2528), 10), 
	STRONGHOLD_LAMP(new Item(4447), 500),

	K_ACHIEVEMENT_1(new Item(11137), 1000, 30),
	K_ACHIEVEMENT_2(new Item(11139), 5000, 40),
	K_ACHIEVEMENT_3(new Item(11141), 10000, 50),

	V_ACHIEVEMENT_1(new Item(11753), 1000, 30),
	V_ACHIEVEMENT_2(new Item(11754), 5000, 40),
	V_ACHIEVEMENT_3(new Item(11755), 10000, 50),

	L_ACHIEVEMENT_1(new Item(11185), 500, 1),
	L_ACHIEVEMENT_2(new Item(11186), 1000, 30),
	L_ACHIEVEMENT_3(new Item(11187), 1500, 35),

	FALLY_ACHIEVEMENT_1(new Item(14580), 1000, 30),
	FALLY_ACHIEVEMENT_2(new Item(14581), 5000, 40),
	FALLY_ACHIEVEMENT_3(new Item(14582), 10000, 50),

	FREM_ACHIEVEMENT_1(new Item(14574), 5000, 30),
	FREM_ACHIEVEMENT_2(new Item(14575), 10000, 40),
	FREM_ACHIEVEMENT_3(new Item(14576), 15000, 50),

	SEERS_ACHIEVEMENT_1(new Item(14633), 1000, 30),
	SEERS_ACHIEVEMENT_2(new Item(14634), 5000, 40),
	SEERS_ACHIEVEMENT_3(new Item(14635), 10000, 50);

	/**
	 * The item id.
	 */
	private final Item item;

	/**
	 * The experience gained.
	 */
	private final int experience;

	/**
	 * The level requirement.
	 */
	private final int levelRequirement;

	/**
	 * Constructs a new {@code Lamps} {@code Object}
	 * @param item the item.
	 * @param experience the exp.
	 * @param levelRequirement the level requirement to meet.
	 */
	Lamps(Item item, int experience, int levelRequirement) {
		this.item = item;
		this.experience = experience;
		this.levelRequirement = levelRequirement;
	}

	/**
	 * Constructs a new {@code Lamps} {@code Object}
	 * @param item the item.
	 * @param experience the exp.
	 */
	Lamps(Item item, int experience) {
		this(item, experience, 0);
	}

	/**
	 * Gets the lamp by the item.
	 * @param item the item.
	 * @return the lamp.
	 */
	public static Lamps forItem(Item item) {
		for (Lamps l : values()) {
			if (l.getItem().getId() == item.getId()) {
				return l;
			}
		}
		return null;
	}

	/**
	 * Gets the item.
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Gets the mod.
	 * @return the mod
	 */
	public int getExp() {
		return experience;
	}

	/**
	 * Gets the levelRequirement.
	 * @return the levelRequirement
	 */
	public int getLevelRequirement() {
		return levelRequirement;
	}

}
