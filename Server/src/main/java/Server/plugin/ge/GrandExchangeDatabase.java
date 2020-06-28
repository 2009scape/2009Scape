package plugin.ge;

import core.cache.def.impl.ItemDefinition;
import core.game.system.SystemLogger;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the grand exchange database.
 * @author Emperor
 */
public final class GrandExchangeDatabase {

	/**
	 * The grand exchange database mapping.
	 */
	private static final Map<Integer, GrandExchangeEntry> DATABASE = new HashMap<>();

	/**
	 * The minimum amount of unique trades required for an entry to change its
	 * value.
	 */
	private static final int MINIMUM_TRADES = 10;// 200

	/**
	 * The amount of hours between each update cycle.
	 */
	private static final int UPDATE_CYCLE_HOURS = 3;

	/**
	 * The next update.
	 */
	private static long nextUpdate;

	/**
	 * If the G.E database has initialized.
	 */
	private static boolean initialized;

	/**
	 * Initializes the database
	 */
	public static void init() {
		String path = "data/" + "eco/";
		if (!new File(path + "grand_exchange_db.emp").exists()) {
			dump("data/");
			System.err.println("ge db wasn't found!");
		}
		SystemLogger.log("Found GE DB... attempting to load.");
		try (RandomAccessFile raf = new RandomAccessFile(path + "grand_exchange_db.emp", "rw")) {
			nextUpdate = raf.readLong();
			int length = raf.readInt();
			SystemLogger.log("Reading DB File... length: " + length);
			for (int i = 0; i < length; i++) {
				int itemId = raf.readShort() & 0xFFFF;
				GrandExchangeEntry entry = new GrandExchangeEntry(itemId);
				entry.setValue(raf.readInt());
				if (entry.getValue() < 1) {
					entry.setValue(1);
				}
				int logLength = raf.readByte() & 0xFF;
				entry.setLogLength(logLength);
				for (int index = 0; index < logLength; index++) {
					entry.getValueLog()[index] = raf.readInt();
				}
				entry.setUniqueTrades(raf.readShort());
				entry.setTotalValue(raf.readLong());
				entry.setLastUpdate(raf.readLong());
				SystemLogger.log("Putting " + itemId + " to the DB hashmap");
				DATABASE.put(itemId, entry);
			}
			checkUpdate();
			raf.close();
			initialized = true;
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Updates the entry values, if needed.
	 */
	public static void checkUpdate() {
		if (nextUpdate < System.currentTimeMillis()) {
			updateValues();
		}
	}

	/**
	 * Dumps the grand exchange database.
	 * @param directory The directory to save to.
	 */
	public static void dump(String directory) {
		File f = new File(directory + "eco/grand_exchange_db.emp", "rw");
		if (f.exists()) {
			f.delete();
		}
		try (RandomAccessFile raf = new RandomAccessFile(directory + "eco/grand_exchange_db.emp", "rw")) {
			if(DATABASE.size() == 0){
				for(ItemDefinition d : ItemDefinition.getDefinitions().values()){
					if(d.isTradeable()) {
						DATABASE.put(d.getId(), new GrandExchangeEntry(d.getId()));
					}
				}
			}
			raf.writeLong(nextUpdate);
			raf.writeInt(DATABASE.size());
			for (GrandExchangeEntry entry : DATABASE.values()) {
				raf.writeShort(entry.getItemId());
				raf.writeInt(entry.getValue());
				raf.writeByte(entry.getLogLength());
				for (int i = 0; i < entry.getLogLength(); i++) {
					raf.writeInt(entry.getValueLog()[i]);
				}
				raf.writeShort(entry.getUniqueTrades());
				raf.writeLong(entry.getTotalValue());
				raf.writeLong(entry.getLastUpdate());
			}
			raf.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Updates the item values.
	 */
	public static void updateValues() {
		try {
			for (GrandExchangeEntry entry : DATABASE.values()) {
				if (entry.getUniqueTrades() < MINIMUM_TRADES || entry.getTotalValue() == 0) {
					continue;
				}
				double newAverage = entry.getTotalValue() / entry.getUniqueTrades();
				double changePercentage = newAverage / (double) (entry.getValue() + .001);
				if (changePercentage == 1.0) {
					continue;
				} else if (changePercentage > 1.15) {
					changePercentage = 1.15;
				} else if (changePercentage < 0.85) {
					changePercentage = 0.85;
				}
				int newValue = (int) (entry.getValue() * changePercentage);
				if (newValue == entry.getValue()) {
					if (changePercentage > 1.0) { // Fixes 1gp not being
						// influenced.
						newValue++;
					} else if (newValue > 0) {
						newValue--;
					}
				}
				entry.updateValue(newValue);
				entry.setLastUpdate(nextUpdate);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		nextUpdate = System.currentTimeMillis() + (UPDATE_CYCLE_HOURS * (60 * 60 * 1000));
	}

	/**
	 * Gets the database.
	 * @return The database.
	 */
	public static Map<Integer, GrandExchangeEntry> getDatabase() {
		return DATABASE;
	}

	/**
	 * Gets the nextUpdate.
	 * @return The nextUpdate.
	 */
	public static long getNextUpdate() {
		return nextUpdate;
	}

	/**
	 * Sets the nextUpdate.
	 * @param nextUpdate The nextUpdate to set.
	 */
	public static void setNextUpdate(long nextUpdate) {
		GrandExchangeDatabase.nextUpdate = nextUpdate;
	}

	/**
	 * Checks if the grand exchange database has initialized.
	 * @return {@code True} if so.
	 */
	public static boolean hasInitialized() {
		return initialized;
	}

}