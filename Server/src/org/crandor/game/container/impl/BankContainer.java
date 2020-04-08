package org.crandor.game.container.impl;

import org.crandor.game.component.CloseEvent;
import org.crandor.game.component.Component;
import org.crandor.game.container.*;
import org.crandor.game.container.access.BitregisterAssembler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.IronmanMode;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.mysql.impl.ItemConfigSQLHandler;
import org.crandor.game.world.GameWorld;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ContainerContext;
import org.crandor.net.packet.out.ContainerPacket;

import java.nio.ByteBuffer;

/**
 * Represents the bank container.
 * @author Emperor
 */
public final class BankContainer extends Container {

	/**
	 * The bank container size.
	 */
	public static final int SIZE = 800;
	
	/**
	 * The maximum amount of bank tabs
	 */
	public static final int TAB_SIZE = 11;
	
	/**
	 * The player reference.
	 */
	private Player player;

	/**
	 * The bank listener.
	 */
	private final BankListener listener;

	/**
	 * Set {@code true} to note items.
	 */
	private boolean noteItems;

	/**
	 * If the bank is open.
	 */
	private boolean open;
	
	/**
	 * The last x-amount entered.
	 */
	private int lastAmountX = 50;
	
	/**
	 * The current tab index.
	 */
	private int tabIndex = 10;
	
	/**
	 * The tab start indexes.
	 */
	private final int[] tabStartSlot = new int[TAB_SIZE];

	/**
	 * If inserting items is enabled.
	 */
	private boolean insertItems;

	/**
	 * Construct a new {@code BankContainer} {@code Object}.
	 * @param player The player reference.
	 */
	public BankContainer(Player player) {
		super(SIZE, ContainerType.ALWAYS_STACK, SortType.HASH);
		super.register(listener = new BankListener(player));
		this.player = player;
	}

	/**
	 * Open the bank.
	 */
	public void open() {
		if (open) {
			return;
		}
		if (player.getIronmanManager().checkRestriction(IronmanMode.ULTIMATE)) {
			return;
		}
		if (!player.getBankPinManager().isUnlocked() && !GameWorld.getSettings().isDevMode()) {
			player.getBankPinManager().openType(1);
			return;
		}
		player.getInterfaceManager().openComponent(762).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				BankContainer.this.close();
				return true;
			}
		});
		player.getInterfaceManager().openSingleTab(new Component(763));
		super.refresh();
		player.getInventory().getListeners().add(listener);
		player.getInventory().refresh();
		player.getConfigManager().set(1249, lastAmountX);
		player.getPacketDispatch().sendAccessMask(1278, 73, 762, 0, SIZE);
		BitregisterAssembler assembly = new BitregisterAssembler(0, 1, 2, 3, 4, 5);
		assembly.enableExamineOption();
		assembly.enableSlotSwitch();
		player.getPacketDispatch().sendAccessMask(assembly.calculateRegister(), 0, 763, 0, 27);
		player.getPacketDispatch().sendRunScript(1451, "");
		open = true;
		setTabConfigurations();
	}
	
	public void open(Player player) {
		if (open) {
			return;
		}
		if (player.getIronmanManager().checkRestriction(IronmanMode.ULTIMATE)) {
			return;
		}
		if (!player.getBankPinManager().isUnlocked() && !GameWorld.getSettings().isDevMode()) {
			player.getBankPinManager().openType(1);
			return;
		}
		player.getInterfaceManager().openComponent(762).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				BankContainer.this.close();
				return true;
			}
		});
		this.player.getBank().refresh(player.getBank().listener);
		player.getInterfaceManager().openSingleTab(new Component(763));
		player.getInventory().getListeners().add(player.getBank().listener);
		player.getInventory().refresh();
		player.getConfigManager().set(1249, lastAmountX);
		player.getPacketDispatch().sendAccessMask(1278, 73, 762, 0, SIZE);
		BitregisterAssembler assembly = new BitregisterAssembler(0, 1, 2, 3, 4, 5);
		assembly.enableExamineOption();
		assembly.enableSlotSwitch();
		player.getPacketDispatch().sendAccessMask(assembly.calculateRegister(), 0, 763, 0, 27);
		player.getPacketDispatch().sendRunScript(1451, "");
		open = true;
		this.player.getBank().setTabConfigurations(player);
		
	}
	
	@Override
	public long save(ByteBuffer buffer) {
		buffer.putInt(lastAmountX);
		buffer.put((byte) tabStartSlot.length);
		for (int i = 0; i < tabStartSlot.length; i++) {
			buffer.putShort((short) tabStartSlot[i]);
		}
		return super.save(buffer);
	}
	
	@Override
	public int parse(ByteBuffer buffer) {
		lastAmountX = buffer.getInt();
		int length = buffer.get() & 0xFF;
		for (int i = 0; i < length; i++) {
			tabStartSlot[i] = buffer.getShort();
		}
		return super.parse(buffer);
	}

	/**
	 * Closes the bank.
	 */
	public void close() {
		open = false;
		player.getInventory().getListeners().remove(listener);
		player.getInterfaceManager().closeSingleTab();
		player.removeAttribute("search");
	}

	/**
	 * Adds an item to the bank container.
	 * @param slot The item slot.
	 * @param amount The amount.
	 */
	public void addItem(int slot, int amount) {
		if (slot < 0 || slot > player.getInventory().capacity() || amount < 1) {
			return;
		}
		Item item = player.getInventory().get(slot);
		if (item == null) {
			return;
		}
		int maximum = player.getInventory().getAmount(item);
		if (amount > maximum) {
			amount = maximum;
		}
		int maxCount = super.getMaximumAdd(item);
		if (amount > maxCount) {
			amount = maxCount;
			if (amount < 1) {
				player.getPacketDispatch().sendMessage("There is not enough space left in your bank.");
				return;
			}
		}
		if (!item.getDefinition().getConfiguration(ItemConfigSQLHandler.BANKABLE, true)) {
			player.sendMessage("A magical force prevents you from banking this item");
			return;
		}
		item = new Item(item.getId(), amount, item.getCharge());
		boolean unnote = !item.getDefinition().isUnnoted();
		if (player.getInventory().remove(item, slot, true)) {
			Item add = unnote ? new Item(item.getDefinition().getNoteId(), amount, item.getCharge()) : item;
			if (unnote && !add.getDefinition().isUnnoted()) {
				add = item;
			}
			int preferredSlot = -1;
			if (tabIndex != 0 && tabIndex != 10 && !super.contains(add.getId(), 1)) {
				preferredSlot = tabStartSlot[tabIndex] + getItemsInTab(tabIndex);
				insert(freeSlot(), preferredSlot, false);
				increaseTabStartSlots(tabIndex);
			}
			super.add(add, true, preferredSlot);
			setTabConfigurations();
		}
	}
	
	/**
	 * Re-opens the bank interface.
	 */
	public void reopen() {
		if (!open) {
			return;
		}
		player.getInterfaceManager().close();
		open();
		refresh();
	}

	/**
	 * Takes a item from the bank container and adds one to the inventory
	 * container.
	 * @param slot The slot.
	 * @param amount The amount.
	 */
	public void takeItem(int slot, int amount) {
		if (slot < 0 || slot > super.capacity() || amount <= 0) {
			return;
		}
		Item item = get(slot);
		if (item == null) {
			return;
		}
		if (amount > item.getAmount()) {
			amount = item.getAmount(); // It always stacks in the bank.
		}
		item = new Item(item.getId(), amount, item.getCharge());
		int noteId = item.getDefinition().getNoteId();
		Item add = noteItems && noteId > 0 ? new Item(noteId, amount, item.getCharge()) : item;
		int maxCount = player.getInventory().getMaximumAdd(add);
		if (amount > maxCount) {
			item.setAmount(maxCount);
			add.setAmount(maxCount);
			if (maxCount < 1) {
				player.getPacketDispatch().sendMessage("Not enough space in your inventory.");
				return;
			}
		}
		if (noteItems && noteId < 0) {
			player.getPacketDispatch().sendMessage("This item can't be withdrawn as a note.");
			add = item;
		}
		if (super.remove(item, slot, false)) {
			player.getInventory().add(add);
		}
		int tabId = getTabByItemSlot(slot);
		if (get(slot) == null) {
			decreaseTabStartSlots(tabId);
		}
		setTabConfigurations();
		shift();
		if (player.getAttribute("search", false)) {
			reopen();
		}
	}

	/**
	 * Updates the last x-amount entered.
	 * @param amount The amount to set.
	 */
	public void updateLastAmountX(int amount) {
		this.lastAmountX = amount;
		player.getConfigManager().set(1249, amount);
	}
	
	/**
	 * Gets the tab the item slot is in.
	 * @param itemSlot The item slot.
	 * @return The tab index.
	 */
	public int getTabByItemSlot(int itemSlot) {
		int tabId = 0;
		for (int i = 0; i < tabStartSlot.length; i++) {
			if (itemSlot >= tabStartSlot[i]) {
				tabId = i;
			}
		}
		return tabId;
	}
	
	/**
	 * Increases a tab's start slot.
	 * @param startId The start id.
	 */
	public void increaseTabStartSlots(int startId) {
		for (int i = startId + 1; i < tabStartSlot.length; i++) {
			tabStartSlot[i]++;
		}
	}

	/**
	 * Decreases a tab's start slot.
	 * @param startId The start id.
	 */
	public void decreaseTabStartSlots(int startId) {
		if (startId == 10) {
			return;
		}
		for (int i = startId + 1; i < tabStartSlot.length; i++) {
			tabStartSlot[i]--;
		}
		if (getItemsInTab(startId) == 0) {
			collapseTab(startId);
		}
	}
	
	/**
	 * Gets the array index for a tab.
	 * @param tabId The tab id.
	 * @return The array index.
	 */
	public static int getArrayIndex(int tabId) {
		if (tabId == 41 || tabId == 74) {
			return 10;
		}
		int base = 39;
		for (int i = 1; i < 10; i++) {
			if (tabId == base) {
				return i;
			}
			base -= 2;
		}
		return -1;
	}
	
	/**
	 * Sends the bank space values on the interface.
	 */
	public void sendBankSpace() {
		player.getPacketDispatch().sendString(Integer.toString(capacity() - freeSlots()), 762, 97);
		player.getPacketDispatch().sendString(Integer.toString(capacity()), 762, 98);
	}
	
	/**
	 * Collapses a tab.
	 * @param tabId The tab index.
	 */
	public void collapseTab(int tabId) {
		int size = getItemsInTab(tabId);
		Item[] tempTabItems = new Item[size];
		for (int i = 0; i < size; i++) {
			tempTabItems[i] = get(tabStartSlot[tabId] + i);
			replace(null, tabStartSlot[tabId] + i, false);
		}
		shift();
		for (int i = tabId; i < tabStartSlot.length - 1; i++) {
			tabStartSlot[i] = tabStartSlot[i + 1] - size;
		}
		tabStartSlot[10] = tabStartSlot[10] - size;
		for (int i = 0; i < size; i++) {
			int slot = freeSlot();
			replace(tempTabItems[i], slot, false);
		}
		refresh(); //We only refresh once.
		setTabConfigurations();
	}
	
	/**
	 * Sets the tab configs.
	 */
	public void setTabConfigurations() {
		int value = getItemsInTab(1);
		value += getItemsInTab(2) << 10;
		value += getItemsInTab(3) << 20;
		player.getConfigManager().set(1246, value);
		value = getItemsInTab(4);
		value += getItemsInTab(5) << 10;
		value += getItemsInTab(6) << 20;
		player.getConfigManager().set(1247, value);
		value = -2013265920;
		value += (134217728 * (tabIndex == 10 ? 0 : tabIndex));
		value += getItemsInTab(7);
		value += getItemsInTab(8) << 10;
		player.getConfigManager().set(1248, value);
	}
	
	/**
	 * Sets the tab configs.
	 */
	public void setTabConfigurations(Player player) {
		int value = getItemsInTab(1);
		value += getItemsInTab(2) << 10;
		value += getItemsInTab(3) << 20;
		player.getConfigManager().set(1246, value);
		value = getItemsInTab(4);
		value += getItemsInTab(5) << 10;
		value += getItemsInTab(6) << 20;
		player.getConfigManager().set(1247, value);
		value = -2013265920;
		value += (134217728 * (tabIndex == 10 ? 0 : tabIndex));
		value += getItemsInTab(7);
		value += getItemsInTab(8) << 10;
		player.getConfigManager().set(1248, value);
	}
	
	/**
	 * Gets the amount of items in one tab.
	 * @param tabId The tab index.
	 * @return The amount of items in this tab.
	 */
	public int getItemsInTab(int tabId) {
		return tabStartSlot[tabId + 1] - tabStartSlot[tabId];
	}

	/**
	 * Checks if the item can be added.
	 * @param item the item.
	 * @return {@code True} if so.
	 */
	public boolean canAdd(Item item) {
		return item.getDefinition().getConfiguration(ItemConfigSQLHandler.BANKABLE, true);
	}

	/**
	 * Gets the last x-amount.
	 * @return The last x-amount.
	 */
	public int getLastAmountX() {
		return lastAmountX;
	}

	/**
	 * If items have to be noted.
	 * @return If items have to be noted {@code true}.
	 */
	public boolean isNoteItems() {
		return noteItems;
	}

	/**
	 * Set if items have to be noted.
	 * @param noteItems If items have to be noted {@code true}.
	 */
	public void setNoteItems(boolean noteItems) {
		this.noteItems = noteItems;
	}

	/**
	 * Gets the tabStartSlot value.
	 * @return The tabStartSlot.
	 */
	public int[] getTabStartSlot() {
		return tabStartSlot;
	}

	/**
	 * Gets the tabIndex value.
	 * @return The tabIndex.
	 */
	public int getTabIndex() {
		return tabIndex;
	}

	/**
	 * Sets the tabIndex value.
	 * @param tabIndex The tabIndex to set.
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	/**
	 * Sets the insert items value.
	 * @param insertItems The insert items value.
	 */
	public void setInsertItems(boolean insertItems) {
		this.insertItems = insertItems;
	}
	
	/**
	 * Gets the insert items value.
	 * @return {@code True} if inserting items mode is enabled.
	 */
	public boolean isInsertItems() {
		return insertItems;
	}
	
	/**
	 * Checks if the bank is opened.
	 * @return {@code True} if so.
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Listens to the bank container.
	 * @author Emperor
	 */
	private static class BankListener implements ContainerListener {

		/**
		 * The player reference.
		 */
		private Player player;

		/**
		 * Construct a new {@code BankListener} {@code Object}.
		 * @param player The player reference.
		 */
		public BankListener(Player player) {
			this.player = player;
		}

		@Override
		public void update(Container c, ContainerEvent event) {
			if (c instanceof BankContainer) {
				PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 762, 64000, 95, event.getItems(), false, event.getSlots()));
			} else {
				PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 763, 64000, 93, event.getItems(), false, event.getSlots()));
			}
			player.getBank().setTabConfigurations();
			player.getBank().sendBankSpace();
		}

		@Override
		public void refresh(Container c) {
			if (c instanceof BankContainer) {
				PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 762, 64000, 95, c.toArray(), c.capacity(), false));
			} else {
				PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 763, 64000, 93, c.toArray(), 28, false));
			}
			player.getBank().setTabConfigurations();
			player.getBank().sendBankSpace();
		}
	}
}
