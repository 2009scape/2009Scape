package plugin.ge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a grand exchange entry, which contains the item id, current price
 * and previous prices.
 * @author Emperor
 */
public final class GrandExchangeEntry {

	/**
	 * The item id.
	 */
	private final int itemId;

	/**
	 * The item value.
	 */
	private int value;

	/**
	 * The value log.
	 */
	private int[] valueLog = new int[256];

	/**
	 * The log length.
	 */
	private int logLength;

	/**
	 * The amount of unique trades completed.
	 */
	private int uniqueTrades;

	/**
	 * The total value amount of each the unique trades. <br> This is used to
	 * calculate the average price, so we know whether or not to update price.
	 */
	private long totalValue;

	/**
	 * The last update time stamp.
	 */
	private long lastUpdate;

	/**
	 * The mapping of completed buying offers.
	 */
	private Map<Integer, Integer> boughtMap = new HashMap<>();

	/**
	 * Constructs a new {@code GrandExchangeEntry} {@code Object}.
	 * @param itemId The item id.
	 */
	public GrandExchangeEntry(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Updates the item value.
	 * @param value The new item value to set.
	 */
	public void updateValue(int value) {
		int[] newValueLog = new int[256];
		System.arraycopy(valueLog, 0, newValueLog, 1, logLength);
		newValueLog[0] = this.value;
		this.valueLog = newValueLog;
		this.value = value;
		this.uniqueTrades = 0;
		this.totalValue = 0;
		if (++logLength > 255) {
			logLength = 255;
		}
	}

	/**
	 * Influences the price of this item.
	 * @param value The value offered in the exchange.
	 */
	public void influenceValue(int value) {
		if (value < this.value * 0.5) { // Makes sure the player can't influence
			// too much
			value = (int) (this.value * 0.5);
		} else if (value > this.value * 1.5) {
			value = (int) (this.value * 1.5);
		}
		uniqueTrades++;
		totalValue += value;
	}

	/**
	 * Gets the itemId.
	 * @return The itemId.
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Gets the value.
	 * @return The value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * @param value The value to set.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Gets the valueLog.
	 * @return The valueLog.
	 */
	public int[] getValueLog() {
		return valueLog;
	}

	/**
	 * Sets the valueLog.
	 * @param valueLog The valueLog to set.
	 */
	public void setValueLog(int[] valueLog) {
		this.valueLog = valueLog;
	}

	/**
	 * Gets the uniqueTrades.
	 * @return The uniqueTrades.
	 */
	public int getUniqueTrades() {
		return uniqueTrades;
	}

	/**
	 * Sets the uniqueTrades.
	 * @param uniqueTrades The uniqueTrades to set.
	 */
	public void setUniqueTrades(int uniqueTrades) {
		this.uniqueTrades = uniqueTrades;
	}

	/**
	 * Gets the totalValue.
	 * @return The totalValue.
	 */
	public long getTotalValue() {
		return totalValue;
	}

	/**
	 * Sets the totalValue.
	 * @param totalValue The totalValue to set.
	 */
	public void setTotalValue(long totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * Gets the lastUpdate.
	 * @return The lastUpdate.
	 */
	public long getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * Sets the lastUpdate.
	 * @param lastUpdate The lastUpdate to set.
	 */
	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * Gets the logLength.
	 * @return The logLength.
	 */
	public int getLogLength() {
		return logLength;
	}

	/**
	 * Sets the logLength.
	 * @param logLength The logLength to set.
	 */
	public void setLogLength(int logLength) {
		this.logLength = logLength;
	}

	/**
	 * Gets the boughtMap.
	 * @return The boughtMap.
	 */
	public Map<Integer, Integer> getBoughtMap() {
		return boughtMap;
	}

	/**
	 * Sets the boughtMap.
	 * @param boughtMap The boughtMap to set.
	 */
	public void setBoughtMap(Map<Integer, Integer> boughtMap) {
		this.boughtMap = boughtMap;
	}

	@Override
	public String toString() {
		return "GrandExchangeEntry [itemId=" + itemId + ", value=" + value + ", valueLog=" + Arrays.toString(valueLog) + ", logLength=" + logLength + ", uniqueTrades=" + uniqueTrades + ", totalValue=" + totalValue + ", lastUpdate=" + lastUpdate + ", boughtMap=" + boughtMap + "]";
	}
}