package org.crandor.game.node.entity.combat.equipment;

import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles equipment degrading.
 * @author Emperor
 */
public abstract class DegradableEquipment implements Plugin<Object> {

	/**
	 * The equipment lists.
	 */
	@SuppressWarnings("unchecked")
	private static final List<DegradableEquipment>[] EQUIPMENT = new ArrayList[14];

	/**
	 * The equipment slot.
	 */
	private final int slot;

	/**
	 * The item ids.
	 */
	private final int[] itemIds;

	/**
	 * Constructs a new {@code DegradableEquipment} {@code Object}.
	 * @param slot The equipment slot.
	 * @param itemIds The item ids.
	 */
	public DegradableEquipment(int slot, int... itemIds) {
		this.slot = slot;
		this.itemIds = itemIds;
	}

	/**
	 * Handles equipment degrading.
	 * @param player The player.
	 * @param entity The combat entity.
	 * @param attack If the player is attacking instead of defending.
	 */
	public static void degrade(Player player, Entity entity, boolean attack) {
		if (attack) {
			checkDegrade(player, entity, EquipmentContainer.SLOT_WEAPON);
			return;
		}
		for (int i = 0; i < 14; i++) {
			if (i != EquipmentContainer.SLOT_WEAPON && i != EquipmentContainer.SLOT_ARROWS) {
				checkDegrade(player, entity, i);
			}
		}
	}

	/**
	 * Checks the degrading equipment on an equipment slot.
	 * @param player The player.
	 * @param entity The entity.
	 * @param slot The slot to check.
	 */
	public static void checkDegrade(Player player, Entity entity, int slot) {
		Item item = player.getEquipment().get(slot);
		roar: {
			for (DegradableEquipment e : EQUIPMENT[slot]) {
				for (int itemId : e.itemIds) {
					if (itemId == item.getId()) {
						e.degrade(player, entity, item);
						break roar;
					}
				}
			}
		}
	}

	/**
	 * Gets the item to drop.
	 * @param itemId The item id.
	 * @return The dropped item.
	 */
	public static int getDropReplacement(int itemId) {
		for (int i = 0; i < EQUIPMENT.length; i++) {
			if (EQUIPMENT[i] == null) {
				continue;
			}
			for (DegradableEquipment e : EQUIPMENT[i]) {
				for (int id : e.itemIds) {
					if (id == itemId) {
						return e.getDropItem(id);
					}
				}
			}
		}
		return itemId;
	}

	/**
	 * Called when the player receives a hit.
	 * @param player The player.
	 * @param entity The combat entity.
	 * @param item The degrading equipment item.
	 */
	public abstract void degrade(Player player, Entity entity, Item item);

	/**
	 * Gets the item to drop when this equipment is getting dropped.
	 * @param itemId The drop item.
	 * @return The item to drop.
	 */
	public abstract int getDropItem(int itemId);

	@Override
	public DegradableEquipment newInstance(Object arg) {
		List<DegradableEquipment> equipment = EQUIPMENT[slot];
		if (equipment == null) {
			equipment = EQUIPMENT[slot] = new ArrayList<>();
		}
		equipment.add(this);
		return this;
	}

	@Override
	public Object fireEvent(String key, Object... args) {
		return null;
	}

	/**
	 * Gets the slot.
	 * @return The slot.
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * Gets the itemIds.
	 * @return The itemIds.
	 */
	public int[] getItemIds() {
		return itemIds;
	}

}