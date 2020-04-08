/*
package org.crandor.game.content.skill.member.fletching;

import org.crandor.game.component.Component;
import org.crandor.game.node.item.Item;

*/
/**
 * Represents the multiple fletching types(log types)
 * @author 'Vexia
 *//*

public enum FletchType {
	LOG(new Item(1511), FletchItem.ARROW_SHAFT, FletchItem.SHORTBOW, FletchItem.LONGBOW, FletchItem.WOODEN_STOCK), OAK(new Item(1521), FletchItem.OAK_SHORTBOW, FletchItem.OAK_LONGBOW, FletchItem.OAK_STOCK), WILLOW(new Item(1519), FletchItem.WILLOW_SHORTBOW, FletchItem.WILLOW_LONGBOW, FletchItem.WILLOW_STOCK), MAPLE(new Item(1517), FletchItem.MAPLE_SHORTBOW, FletchItem.MAPLE_LONGBOW, FletchItem.MAPLE_STOCK), TEAK(new Item(6333), FletchItem.TEAK_STOCK), MAHOGANY(new Item(6332), FletchItem.MAHOGANY_STOCK), YEW(new Item(1515), FletchItem.YEW_SHORTBOW, FletchItem.YEW_LONGBOW, FletchItem.YEW_STOCK), MAGIC(new Item(1513), FletchItem.MAGIC_SHORTBOW, FletchItem.MAGIC_LONGBOW);

	*/
/**
	 * Constructs a new {@code FletchType} {@code Object}.
	 * @param log the log.
	 * @param items the item.s
	 *//*

	FletchType(final Item log, final FletchItem... items) {
		this.log = log;
		this.items = items;
	}

	*/
/**
	 * Represents the log of this type.
	 *//*

	private final Item log;

	*/
/**
	 * Represents the fletching items of this type.
	 *//*

	private FletchItem[] items;

	*/
/**
	 * Gets the items.
	 * @return The items.
	 *//*

	public FletchItem[] getItems() {
		return items;
	}

	*/
/**
	 * Gets the log.
	 * @return The log.
	 *//*

	public Item getLog() {
		return log;
	}

	*/
/**
	 * Method used to get the component based on type.
	 * @return the component.
	 *//*

	public Component getComponent() {
		return items.length > 1 ? new Component(301 + items.length) : new Component(309);
	}

	*/
/**
	 * Method used to get the <code>FletchType</code> based on the log item.
	 * @param item the item.
	 * @return the fletch type.
	 *//*

	public static FletchType forItem(final Item item) {
		for (FletchType type : FletchType.values()) {
			if (type.getLog().getId() == item.getId()) {
				return type;
			}
		}
		return null;
	}
}
*/
