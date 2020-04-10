package org.arios.workspace.node.npc;

import java.nio.ByteBuffer;

import org.arios.cache.def.impl.ItemDefinition;

/**
 * Represents an npc drop.
 * @author Vexia
 *
 */
public class NPCDrop {

	/**
	 * The item id.
	 */
	private int itemId;

	/**
	 * The minimum amount.
	 */
	private int minAmount;

	/**
	 * The maximum amount.
	 */
	private int maxAmount;

	/**
	 * The chance.
	 */
	private double chance;

	/**
	 * The drop frequency.
	 */
	private DropFrequency frequency;

	/**
	 * The set rate.
	 */
	private int setRate = -1;

	/**
	 * Constructs a new {@code TableRow} {@code Object}
	 * @param itemId the item id.
	 * @param minAmount the min amount.
	 * @param maxAmount the max amount.
	 * @param chance the chance.
	 * @param frequency the freqnecy.
	 */
	public NPCDrop(int itemId, int minAmount, int maxAmount, double chance, DropFrequency frequency, int setRate) {
		this.itemId = itemId;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.chance = chance;
		this.frequency = frequency;
		this.setSetRate(setRate);
	}

	/**
	 * Saves to the byte buffer.
	 * @param buffer the buffer.
	 */
	public void save(ByteBuffer buffer) {
		buffer.putShort((short) itemId);
		buffer.putInt(minAmount);
		buffer.putInt(maxAmount);
		buffer.putDouble(chance);
		buffer.put((byte) frequency.ordinal());
		buffer.put((byte) (setRate == -1 ? 0 : 1));
		if (setRate != -1) {
			buffer.putInt(setRate);
		}
	}

	/**
	 * Creates a table row.
	 * @param itemId the item id.
	 * @param buffer the buffer.
	 * @return the row.
	 */
	public static NPCDrop create(int itemId, ByteBuffer buffer) {
		//buffer.get() == 0 ? -1 : buffer.getInt()
		//http://puu.sh/bxHJ3/0acb4ea8d9.png
		return new NPCDrop(itemId, buffer.getInt(), buffer.getInt(), buffer.getDouble(), DropFrequency.values()[buffer.get()], buffer.get() == 0 ? -1 : buffer.getInt());
	}

	/**
	 * Gets the data.
	 * @return the data.
	 */
	public Object[] getData() {
		return new Object[] {String.valueOf(itemId), String.valueOf(minAmount), String.valueOf(maxAmount), String.valueOf(chance), frequency.name(), String.valueOf(setRate)};
	}

	/**
	 * Sets the minAmount.
	 * @param minAmount the minAmount to set.
	 */
	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * Sets the maxAmount.
	 * @param maxAmount the maxAmount to set.
	 */
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * Sets the itemId.
	 * @param itemId the itemId to set.
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Sets the chance.
	 * @param chance the chance to set.
	 */
	public void setChance(double chance) {
		this.chance = chance;
	}

	/**
	 * Sets the frequency.
	 * @param frequency the frequency to set.
	 */
	public void setFrequency(DropFrequency frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets the max amount.
	 * @return the amount.
	 */
	public int getMaxAmount() {
		return maxAmount;
	}

	/**
	 * Gets the min amount.
	 * @return the min amount.
	 */
	public int getMinAmount() {
		return minAmount;
	}

	/**
	 * Gets the name.
	 * @return the name.
	 */
	public String getName() {
		return ItemDefinition.forId(itemId).getName();
	}

	/** 
	 * Gets the itemId.
	 * @return the itemId.
	 */
	public int getItemId() {
		return itemId;
	}

	/** 
	 * Gets the chance.
	 * @return the chance.
	 */
	public double getChance() {
		return chance;
	}

	/** 
	 * Gets the frequency.
	 * @return the frequency.
	 */
	public DropFrequency getFrequency() {
		return frequency;
	}

	@Override
	public String toString() {
		return "NPCDrop [itemId=" + itemId + ", name=" + getName() + ", minAmount=" + minAmount
				+ ", maxAmount=" + maxAmount + ", chance=" + chance
				+ ", frequency=" + frequency + "]";
	}

	/**
	 * Gets the setRate.
	 * @return the setRate.
	 */
	public int getSetRate() {
		return setRate;
	}

	/**
	 * Sets the setRate.
	 * @param setRate the setRate to set
	 */
	public void setSetRate(int setRate) {
		this.setRate = setRate;
	}

}
