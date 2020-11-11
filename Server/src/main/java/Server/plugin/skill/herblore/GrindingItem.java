package plugin.skill.herblore;

import core.game.content.ItemNames;
import core.game.node.item.Item;

/**
 * Represents an item that will can be ground by pestle and mortar.
 * @author Vexia
 */
public enum GrindingItem {
	UNICORN_HORN(new Item[] { new Item(237) },new Item(235)),
	CHOCOLATE_BAR(new Item[] { new Item(1973) }, new Item(1975)),
	KEBBIT_TEETH(new Item[] { new Item(10109) }, new Item(10111)),
	BIRDS_NEST(new Item[] { new Item(5070), new Item(5071), new Item(5072), new Item(5073), new Item(5074), new Item(5075) }, new Item(6693)),
	GOAT_HORN(new Item[] { new Item(9735) }, new Item(9736)),
	MUD_RUNE(new Item[] { new Item(4698) }, new Item(9594)),
	ASHES(new Item[] { new Item(592) }, new Item(8865)),
	POISON_KARAMBWAN(new Item[] { new Item(3146) }, new Item(3152)),
	FISHING_BAIT(new Item[] { new Item(313) }, new Item(12129)),
	SEAWEED(new Item[] { new Item(401) }, new Item(6683)),
	BAT_BONES(new Item[] { new Item(530) }, new Item(2391)),
	CHARCOAL(new Item[] { new Item(973) }, new Item(704)),
	ASTRAL_RUNE_SHARDS(new Item[] { new Item(11156) }, new Item(11155)),
	GARLIC(new Item[] { new Item(1550) }, new Item(4668)),
	DRAGON_SCALE(new Item[] { new Item(243) }, new Item(241)),
	ANCHOVIES(new Item[] { new Item(319) }, new Item(11266)),
	GUAM_LEAF(new Item[] { new Item(ItemNames.GUAM) }, new Item(ItemNames.GROUND_GUAM_6681));

	/**
	 * Represents the item to grind.
	 */
	private final Item[] items;

	/**
	 * Represents the product item.
	 */
	private final Item product;

	/**
	 * Constructs a new {@code GrindingItem} {@code Object}.
	 * @param items the items.
	 * @param product the product.
	 */
	GrindingItem(final Item[] items, final Item product) {
		this.items = items;
		this.product = product;
	}

	/**
	 * Gets the item.
	 * @return The item.
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * Gets the product.
	 * @return The product.
	 */
	public Item getProduct() {
		return product;
	}

	/**
	 * Gets the grinding item.
	 * @param item the item.
	 * @return the item.
	 */
	public static GrindingItem forItem(final Item item) {
		for (GrindingItem g : values()) {
			for (Item i : g.getItems()) {
				if (i.getId() == item.getId()) {
					return g;
				}
			}
		}
		return null;
	}
}
