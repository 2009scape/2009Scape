package org.crandor.game.content.eco.ge;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages several resources being "pumped" into the game by creating offers in
 * the grand exchange.
 * @author Emperor
 */
public final class ResourceManager {

	/**
	 * The resources used for "kick-starting" the eco.
	 */
	private static final int[] RESOURCES = { 3122, 4153, 6809, 10564, 10589, 1215, 4587, 1305, 1434, 7158, 3204, 1377, 1249, 11212, 11230, 6523, 6527, 6528, 6525, 6524, 6128, 6129, 6130, 6131, 6133, 6135, 6137, 6139, 6141, 6143, 6145, 6147, 6149, 6151, 6153 };

	/**
	 * The database path.
	 */
	private static final String DB_PATH = "eco/ge_resource.emp";

	/**
	 * The current stock of resources.
	 */
	private static final List<GrandExchangeOffer> STOCK = new ArrayList<>();

	/**
	 * Loads the resources stock.
	 */
	public static void init() {
		File file = new File("data/" + DB_PATH);
		if (!file.exists()) {
			return;
		}
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel c = raf.getChannel()) {
			ByteBuffer b = c.map(MapMode.READ_WRITE, 0, c.size());
			int itemId = -1;
			while ((itemId = b.getShort()) != -1) {
				boolean sale = b.get() == 1;
				GrandExchangeOffer offer = new GrandExchangeOffer(itemId, sale);
				offer.setAmount(b.getInt());
				offer.setCompletedAmount(b.getInt());
				offer.setOfferedValue(b.getInt());
				int value = offer.getOfferedValue();
				int shopValue = ItemDefinition.forId(itemId).getValue();
				if (value < (shopValue * 1.05)) {
					value = (int) (shopValue * 1.05);
				}
				offer.setOfferedValue(value);
				offer.setTimeStamp(b.getLong());
				offer.setState(OfferState.values()[b.get()]);
				offer.setTotalCoinExchange(b.getInt());
				offer.setPlayerUID(-1);
				offer.setUid(STOCK.size() + 1);
				STOCK.add(offer);
			}
			raf.close();
			c.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Dumps the current resources.
	 * @param directory The directory to save to.
	 */
	public static void dump(String directory) {
		File file = new File(directory + DB_PATH);
		ByteBuffer b = ByteBuffer.allocate(50_000_000);
		for (GrandExchangeOffer offer : STOCK) {
			if (offer == null || offer.getState() == OfferState.COMPLETED) {
				continue;
			}
			b.putShort((short) offer.getItemId());
			b.put((byte) (offer.isSell() ? 1 : 0));
			b.putInt(offer.getAmount());
			b.putInt(offer.getCompletedAmount());
			b.putInt(offer.getOfferedValue());
			b.putLong(offer.getTimeStamp());
			b.put((byte) offer.getState().ordinal());
			b.putInt(offer.getTotalCoinExchange());
		}
		b.putShort((short) -1);
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel c = raf.getChannel()) {
			b.flip();
			c.write(b);
			raf.close();
			c.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Clears the resource offer.
	 * @param itemId The item id to clear.
	 */
	public static void clearResource(int itemId) {
		for (Iterator<GrandExchangeOffer> it = STOCK.iterator(); it.hasNext();) {
			GrandExchangeOffer offer = it.next();
			if (offer.getItemId() == itemId) {
				offer.setCompletedAmount(offer.getAmount());
				offer.setState(OfferState.COMPLETED);
				it.remove();
			}
		}
	}

	/**
	 * Adds a new resource offer.
	 * @param itemId The item id.
	 * @param amount The amount.
	 * @param sell If the G.E should sell the resource.
	 */
	public static void addResource(int itemId, int amount, boolean sell) {
		GrandExchangeOffer offer = new GrandExchangeOffer(itemId, sell);
		if (offer.getEntry() == null) {
			System.out.println("No Grand Exchange entry found for item " + itemId + "!");
			return;
		}
		offer.setState(OfferState.REGISTERED);
		offer.setAmount(amount);
		offer.setOfferedValue((int) (new Item(itemId).getValue() * 1.05));
		offer.setPlayerUID(-1);
		offer.setUid(STOCK.size() + 1);
		offer.setTimeStamp(System.currentTimeMillis());
		STOCK.add(offer);
	}

	public static void main(String... args) throws Throwable {
		GameWorld.prompt(false);
	}


	/**
	 * Gets the stock.
	 * @return The stock.
	 */
	public static List<GrandExchangeOffer> getStock() {
		return STOCK;
	}

}