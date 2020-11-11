package core.game.content.global;

import core.game.node.item.Item;

/**
 * Represents a dye.
 * @author Vexia
 */
public enum Dyes {
	BLACK(new Item(4622)),
	RED(new Item(1763)),
	YELLOW(new Item(1765)),
	BLUE(new Item(1767)),
	ORANGE(new Item(1769)),
	GREEN(new Item(1771)),
	PURPLE(new Item(1773)),
	PINK(new Item(6955));

	/**
	 * The dye item.
	 */
	private final Item item;

	/**
	 * Constructs a new {@code Dyes} {@code Object}.
	 * @param item the item.
	 */
	Dyes(Item item) {
		this.item = item;
	}

	/**
	 * Gets the dye for the item.
	 * @param item the item.
	 * @return the dye.
	 */
	public static Dyes forItem(Item item) {
		for (Dyes d : values()) {
			if (d.getItem().getId() == item.getId()) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Gets the item.
	 * @return The item.
	 */
	public Item getItem() {
		return item;
	}

}
