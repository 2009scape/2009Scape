package core.game.node.item;

import core.cache.def.impl.ItemDefinition;
import plugin.ge.GrandExchangeDatabase;
import plugin.ge.GrandExchangeEntry;
import core.game.interaction.DestinationFlag;
import core.game.interaction.Interaction;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.combat.equipment.DegradableEquipment;

/**
 * Represents an item.
 * @author Emperor
 */
public class Item extends Node {

	/**
	 * The identification hash (itemId << 16 | charge)
	 */
	private int idHash;

	/**
	 * The item amount.
	 */
	private int amount;

	/**
	 * The item definition.
	 */
	private ItemDefinition definition;

	/**
	 * Constructs a new {@code Item} {@code Object}. <br> The id will be -1
	 * (thus <b>definition will be <code>null</code></b>
	 */
	public Item() {
		super("null", null);
		super.interaction = new Interaction(this);
		this.idHash = -1 << 16 | 1000;
	}

	/**
	 * Constructs a new fully charged {@code Item} {@code Object}.
	 * @param id The item id.
	 */
	public Item(int id) {
		this(id, 1, 1000);
	}

	/**
	 * Constructs a new fully charged {@code Item} {@code Object}.
	 * @param id The item id.
	 * @param amount The amount.
	 */
	public Item(int id, int amount) {
		this(id, amount, 1000);
	}

	/**
	 * Constructs a new {@code Item} {@code Object}.
	 * @param id The item id.
	 * @param amount The amount.
	 * @param charge The charge.
	 */
	public Item(int id, int amount, int charge) {
		super(ItemDefinition.forId(id).getName(), null);
		super.destinationFlag = DestinationFlag.ITEM;
		super.index = -1; // Item slot.
		super.interaction = new Interaction(this);
		this.idHash = id << 16 | charge;
		this.amount = amount;
		this.definition = ItemDefinition.forId(id);
	}

	/**
	 * Gets this item as a ground item.
	 * @return The item.
	 */
	public Item getDropItem() {
		int itemId = DegradableEquipment.getDropReplacement(getId());
		if (itemId != getId()) {
			return new Item(itemId, getAmount());
		}
		return this;
	}

	/**
	 * Gets the operate option handler.
	 * @return The option handler for the operate option.
	 */
	public OptionHandler getOperateHandler() {
		return ItemDefinition.getOptionHandler(getId(), "operate");
	}

	/**
	 * Gets the value of the item.
	 * @return The value.
	 */
	public long getValue() {
		long value = 1;
		GrandExchangeEntry entry = GrandExchangeDatabase.getDatabase().get(getId());
		if (entry != null) {
			value = entry.getValue();
		}
		if (definition.getValue() > value) {
			value = definition.getValue();
		}
		if (definition.getAlchemyValue(true) > value) {
			value = definition.getAlchemyValue(true);
		}
		return value * getAmount();
	}

	/**
	 * Gets a copy of the item.
	 * @return The item copy.
	 */
	public Item copy() {
		return new Item(getId(), getAmount(), getCharge());
	}

	/**
	 * Gets the item id of the exchange item for this item.
	 * @return The note item id, if this item is unnoted, or the unnoted item id
	 * if this item is noted.
	 */
	public int getNoteChange() {
		int noteId = definition.getNoteId();
		if (noteId > -1) {
			return noteId;
		}
		return getId();
	}

	/**
	 * Gets the id of the noted version of this item.
	 * @return The item's noted id
	 */
	public int getNoteId() {
		return definition.isUnnoted() ? definition.getNoteId() : getId();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return idHash >> 16 & 0xFFFF;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.idHash = id << 16 | (idHash & 0xFFFF);
		this.definition = ItemDefinition.forId(id);
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		if (amount < 0) {
			amount = 0;
		}
		this.amount = amount;
	}

	/**
	 * Checks if the item has atleast one charge left.
	 * @return {@code True} if so.
	 */
	public boolean isCharged() {
		return getCharge() > 0;
	}

	/**
	 * @return the charge
	 */
	public int getCharge() {
		return idHash & 0xFFFF;
	}

	/**
	 * @param charge the charge to set
	 */
	public void setCharge(int charge) {
		this.idHash = (idHash >> 16 & 0xFFFF) << 16 | charge;
	}

	/**
	 * @return the identification hash
	 */
	public int getIdHash() {
		return idHash;
	}

	/**
	 * Sets the id hash.
	 * @param hash the hash to set
	 */
	public void setIdHash(int hash) {
		this.idHash = hash;
	}

	/**
	 * Checks if the item has a wrapper plugin.
	 * @return {@code True} if so.
	 */
	public boolean hasItemPlugin() {
		return getPlugin() != null;
	}

	/**
	 * Gets the item plugin.
	 * @return the plugin.
	 */
	public ItemPlugin getPlugin() {
		if (definition == null) {
			return null;
		}
		return definition.getItemPlugin();
	}

	/**
	 * Gets the definition.
	 * @return The definition.
	 */
	public ItemDefinition getDefinition() {
		return definition;
	}

	/**
	 * Sets the definition.
	 * @param definition The definition to set.
	 */
	public void setDefinition(ItemDefinition definition) {
		this.definition = definition;
	}

	/**
	 * Gets the slot of this item.
	 * @return The container slot, or {@code -1} if the item wasn't added to a
	 * container.
	 */
	public int getSlot() {
		return index;
	}

	@Override
	public String toString() {
		return "Item id=" + getId() + ", name=" + getName() + ", amount=" + amount;
	}

}