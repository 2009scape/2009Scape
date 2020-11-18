package core.game.content.global;

import core.game.node.item.Item;

/**
 * Represents the repair item type.
 * @author Vexia
 */
public enum RepairItem {
	BRONZE_HATCHET(new Item(494, 1), new Item(1351, 1), 0),
	BRONZE_PICKAXE(new Item(468, 1), new Item(1265, 1), 0),
	IRON_HATCHET(new Item(496, 1), new Item(1349, 1), 0),
	IRON_PICKAXE(new Item(470, 1), new Item(1267, 1), 0),
	STEEL_HATCHET(new Item(498, 1), new Item(1353, 1), 0),
	STEEL_PICKAXE(new Item(472, 1), new Item(1269, 1), 14),
	BLACK_HATCHET(new Item(500, 1), new Item(1361, 1), 10),
	MITHRIL_HATCHET(new Item(502, 1), new Item(1355, 1), 18),
	MITHRIL_PICKAXE(new Item(474, 1), new Item(1273, 1), 43),
	ADAMANT_HATCHET(new Item(504, 1), new Item(1357, 1), 43),
	ADAMANT_PICKAXE(new Item(476, 1), new Item(1271, 1), 107),
	RUNE_HATCHET(new Item(506, 1), new Item(1359, 1), 427),
	RUNE_PICKAXE(new Item(478, 1), new Item(1275, 1), 1100),
	DRAGON_HATCHET(new Item(6741, 1), new Item(6739, 1), 1800);

	/**
	 * The item id.
	 */
	private final Item item;

	/**
	 * The product item.
	 */
	private final Item product;

	/**
	 * The cost of the money to repair.
	 */
	private final int cost;

	/**
	 * Constructs a new {@code BobRepairItem} {@code Object}.
	 * @param item the item.
	 * @param product the product.
	 * @param cost the cost.
	 */
	RepairItem(Item item, Item product, int cost) {
		this.item = item;
		this.product = product;
		this.cost = cost;
	}

	/**
	 * Gets the item.
	 * @return The item.
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Gets the product.
	 * @return The product.
	 */
	public Item getProduct() {
		return product;
	}

	/**
	 * Gets the cost.
	 * @return The cost.
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Gets the reapir item by the id.
	 * @param id the id.
	 * @return the repair item.
	 */
	public static RepairItem forId(int id) {
		for (RepairItem item : RepairItem.values())
			if (item.item.getId() == id)
				return item;
		return null;
	}
}