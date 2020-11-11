package plugin.skill.farming;

import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.component.InterfaceType;
import core.game.container.Container;
import core.game.container.ContainerType;
import core.game.content.ItemNames;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.item.Item;

import java.nio.ByteBuffer;

/**
 * Represents a managing class used for farming equipment.
 * @author Vexia
 */
public final class FarmingEquipment implements SavingModule {

	/**
	 * Represents the items to use in the store.
	 */
	private static final Item[] ITEMS = new Item[] {
			new Item(ItemNames.RAKE_5341),
			new Item(ItemNames.SEED_DIBBER_5343),
			new Item(ItemNames.SPADE_952),
			new Item(ItemNames.SECATEURS_5329),
			new Item(ItemNames.WATERING_CAN_5331),
			new Item(ItemNames.GARDENING_TROWEL),
			new Item(ItemNames.BUCKET_1925),
			new Item(ItemNames.COMPOST_6032),
			new Item(ItemNames.SUPERCOMPOST_6034)
	};

	/**
	 * Represents the watering cans.
	 */
	private static final Item[] WATERING_CANS = new Item[] { new Item(5340), new Item(5339), new Item(5338), new Item(5337), new Item(5336), new Item(5335), new Item(5334), new Item(5333), new Item(5331) };

	/**
	 * Types of storable secateurs
	 */
	private static final Item[] SECATEURS = new Item[] { new Item(ItemNames.MAGIC_SECATEURS_7409), new Item(ItemNames.SECATEURS_5329) };

	/**
	 * Represents the equipment store component.
	 */
	private static final Component STORE_COMPONENT = new Component(125).setCloseEvent(new StoreCloseEvent());

	/**
	 * Represents the tab component.
	 */
	private static final Component TAB_COMPONENT = new Component(126);

	/**
	 * Represents the config used for the store.
	 */
	private static final int CONFIG = 615;

	/**
	 * Represents the container.
	 */
	private final Container container = new Container(9, ContainerType.ALWAYS_STACK);

	@Override
	public void save(ByteBuffer buffer) {
		container.save(buffer);
	}

	@Override
	public void parse(ByteBuffer buffer) {
		container.parse(buffer);
	}

	/**
	 * Method used to open the equipment store.
	 * @param player the player.
	 */
	public void open(final Player player) {
		player.getInterfaceManager().open(STORE_COMPONENT);
		player.getInterfaceManager().openSingleTab(TAB_COMPONENT);
		update(player);
	}

	/**
	 * Method used to store an item from a slot.
	 * @param player the player.
	 * @param slot the slot.
	 * @param amount the amount.
	 * @return {@code True} if stored.
	 */
	public boolean store(final Player player, final int slot, int amount) {
		final String name = getItemName(slot);
		if (!hasItem(player, slot, false)) {
			player.getPacketDispatch().sendMessage("You haven't got " + (name.equals("secateurs") || name.equals("supercompost") || name.equals("compost") ? "any " : "a ") + name + " to store.");
			return false;
		}
		final int inventoryAmount = player.getInventory().getAmount(slot == 3 ? new Item(getSecateurs(player).getId(), 1) : slot == 4 ? new Item(getWateringCan(player).getId(), 1) : ITEMS[slot]);
		if (amount > inventoryAmount) {
			amount = inventoryAmount;
		}
		if (amount > getMaxAdd(slot)) {
			amount = getMaxAdd(slot);
		}
		if ((getEquipmentAmount(slot) + amount) > getMaxAdd(slot)) {
			player.getPacketDispatch().sendMessage("You cannot store more than " + getAddName(slot) + " " + name + (getMaxAdd(slot) > 1 ? "s" : "") + " in here.");
			return false;
		}
		final Item item = slot == 3 ? new Item(getSecateurs(player).getId(), amount) : slot == 4 ? new Item(getWateringCan(player).getId(), amount) : new Item(ITEMS[slot].getId(), amount);
		if (player.getInventory().remove(item)) {
			if (slot >= 6) {
				int oldAmt = container.getAmount(item);
				container.replace(new Item(item.getId(), item.getAmount() + oldAmt), slot, true);
			} else {
				container.replace(new Item(item.getId(), item.getAmount()), slot, true);
			}
			update(player);
			return true;
		}
		return false;
	}

	/**
	 * Method used to remove an item from your equipment store.
	 * @param player the player.
	 * @param slot the slot.
	 * @param amount the amount.
	 * @return {@code True} if removed.
	 */
	public boolean remove(final Player player, final int slot, int amount) {
		final String name = getItemName(slot);
		if (!hasItem(player, slot, true)) {
			player.getPacketDispatch().sendMessage("You haven't got " + (name.equals("secateurs") || name.equals("supercompost") || name.equals("compost") ? "any " : "a ") + name + " stored in here.");
			return false;
		}
		if (amount == 0) {
			amount = getEquipmentAmount(slot);
		}
		if (amount > getEquipmentAmount(slot)) {
			amount = getEquipmentAmount(slot);
		}
		final Item item = new Item(container.get(slot).getId(), amount);
		if (!player.getInventory().hasSpaceFor(item)) {
			player.getPacketDispatch().sendMessage("You don't have room to hold that.");
			return false;
		}
		if (player.getInventory().add(item)) {
			container.remove(item);
			update(player);
			return true;
		}
		return false;
	}

	/**
	 * Method used to update the interface.
	 * @param player the player.
	 */
	private void update(final Player player) {
		player.getConfigManager().set(CONFIG, getConfigHash());
	}

	/**
	 * Gets the config hash.
	 * @return the config hash.
	 */
	private int getConfigHash() {
		int hash = getEquipmentAmount(0);
		hash |= getEquipmentAmount(1) << 1;
		hash |= getEquipmentAmount(2) << 2;
		hash |= getEquipmentAmount(3) << 3;
		hash |= getCanIndex() << 4;
		hash |= getEquipmentAmount(5) << 8;
		hash |= getEquipmentAmount(6) << 9;
		hash |= getEquipmentAmount(7) << 14;
		hash |= getEquipmentAmount(8) << 22;
		hash |= getSecateursMagic() ? 1 << 30 : 0; // magic secateurs bit
		return hash;
	}

	/**
	 * Gets the amount on a slot.
	 * @param slot the slot.
	 * @return the equipment.
	 */
	public int getEquipmentAmount(int slot) {
		final Item i = container.get(slot);
		return i == null ? 0 : i.getAmount();
	}

	/**
	 * Gets the inventory amount.
	 * @param player the player.
	 * @param slot the slot.
	 * @return the inventory amount.
	 */
	public int getInventoryAmount(final Player player, int slot) {
		if (slot == 3) {
			return player.getInventory().getAmount(getSecateurs(player));
		}
		if (slot == 4) {
			return player.getInventory().getAmount(getWateringCan(player));
		}
		if (slot < 0) {
			return 0;
		}
		return player.getInventory().getAmount(ITEMS[slot]);
	}

	/**
	 * Gets the maximum add of an item to a slot.
	 * @param slot the slot..
	 * @return the maximum amt allowed to be added.
	 */
	public int getMaxAdd(int slot) {
		return slot < 6 ? 1 : slot == 6 ? 31 : 255;
	}

	/**
	 * Gets the add name.
	 * @param slot the slot.
	 * @return the add name.
	 */
	private String getAddName(int slot) {
		return slot < 6 ? "one" : slot == 6 ? "thirty one" : "two hundred fifty five";
	}

	/**
	 * Checks if the player has the item.
	 * @param player the player.
	 * @param slot the slot.
	 * @param store the store.
	 * @return {@code True} if so.
	 */
	private boolean hasItem(final Player player, int slot, final boolean store) {
		return store ? container.get(slot) != null : getInventoryAmount(player, slot) != 0;
	}

	/**
	 * Gets the can index.
	 * @return the index.
	 */
	private int getCanIndex() {
		int canIndex = 0;
		for (int i = 0; i < WATERING_CANS.length; i++) {
			if (container.get(4) == null) {
				break;
			}
			if (container.get(4).getId() == WATERING_CANS[i].getId()) {
				canIndex = 9 - i;
				break;
			}
		}
		return canIndex;
	}

	/**
	 * Gets whether stored secateurs are magic or not
	 */
	private boolean getSecateursMagic() {
		return container.get(3) != null && (container.get(3).getId() == ItemNames.MAGIC_SECATEURS_7409);
	}

	/**
	 * Gets the formated item name.
	 * @param slot the slot.
	 * @return the name.
	 */
	private String getItemName(int slot) {
		if (slot == 4) {
			return "watering can";
		}
		if (slot < 0) {
			return "report me";
		}
		return ITEMS[slot].getName().toLowerCase();
	}

	/**
	 * Gets thes watering can to store.
	 * @param player the player.
	 * @return the item.
	 */
	private Item getWateringCan(final Player player) {
		for (Item i : WATERING_CANS) {
			if (player.getInventory().containsItem(i)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Gets secateurs type from inventory to store
	 */
	private Item getSecateurs(final Player player) {
		for (Item i : SECATEURS) {
			if (player.getInventory().containsItem((i))) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Gets the container.
	 * @return The container.
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * Represents the close event of the equipment store.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class StoreCloseEvent implements CloseEvent {

		@Override
		public boolean close(Player player, Component c) {
			player.getInterfaceManager().closeSingleTab();
			player.getPacketDispatch().sendRunScript(101, "");
			return true;
		}

	}
}
