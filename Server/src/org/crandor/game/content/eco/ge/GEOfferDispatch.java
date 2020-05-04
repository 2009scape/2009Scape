package org.crandor.game.content.eco.ge;

import org.crandor.game.content.eco.EcoStatus;
import org.crandor.game.content.eco.EconomyManagement;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.system.task.TaskExecutor;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.callback.CallBack;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the Grand Exchange offers.
 * @author Emperor
 */
public final class GEOfferDispatch extends Pulse implements CallBack {

	/**
	 * The update notification.
	 */
	public static final String UPDATE_NOTIFICATION = "One or more of your grand exchange offers have been updated.";

	/**
	 * The database path.
	 */
	private static final String DB_PATH = "eco/offer_dispatch_db.emp";

	/**
	 * The offset of the offer UIDs.
	 */
	private static long offsetUID = 1;

	/**
	 * The mapping of all current offers.
	 */
	private static final Map<Long, GrandExchangeOffer> OFFER_MAPPING = new HashMap<>();

	/**
	 * If the database should be dumped.
	 */
	private static boolean dumpDatabase;

	/**
	 * Initializes the Grand Exchange.
	 */
	public static void init() {
		File file = new File("data/" + DB_PATH);
		if (!file.exists()) {
			System.err.println("[GEOfferDispatch]: Could not locate database! [path=" + file.getAbsolutePath() + "]");
			return;
		}
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel c = raf.getChannel()) {
			ByteBuffer b = c.map(MapMode.READ_WRITE, 0, c.size());
			offsetUID = b.getLong();
			long uid;
			while ((uid = b.getLong()) != 0) {
				int itemId = b.getShort();
				boolean sale = b.get() == 1;
				GrandExchangeOffer offer = new GrandExchangeOffer(itemId, sale);
				offer.setUid(uid);
				offer.setAmount(b.getInt());
				offer.setCompletedAmount(b.getInt());
				offer.setOfferedValue(b.getInt());
				offer.setTimeStamp(b.getLong());
				offer.setState(OfferState.values()[b.get()]);
				offer.setTotalCoinExchange(b.getInt());
				offer.setPlayerUID(b.getInt());
				int idx = -1;
				while ((idx = b.get()) != -1) {
					offer.getWithdraw()[idx] = new Item(b.getShort(), b.getInt());
				}
				OFFER_MAPPING.put(uid, offer);
			}
			raf.close();
			c.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		ResourceManager.init();
	}

	/**
	 * Dumps the grand exchange offers.
	 * @param directory The directory to save to.
	 */
	public static void dump(String directory) {
		File file = new File(directory + DB_PATH);
		ByteBuffer b = ByteBuffer.allocate(50_000_000);
		b.putLong(offsetUID);
		for (long uid : OFFER_MAPPING.keySet()) {
			GrandExchangeOffer offer = OFFER_MAPPING.get(uid);
			if (offer == null) {
				continue;
			}
			b.putLong(uid);
			b.putShort((short) offer.getItemId());
			b.put((byte) (offer.isSell() ? 1 : 0));
			b.putInt(offer.getAmount());
			b.putInt(offer.getCompletedAmount());
			b.putInt(offer.getOfferedValue());
			b.putLong(offer.getTimeStamp());
			b.put((byte) offer.getState().ordinal());
			b.putInt(offer.getTotalCoinExchange());
			b.putInt(offer.getPlayerUID());
			for (int i = 0; i < 2; i++) {
				Item item;
				if ((item = offer.getWithdraw()[i]) != null) {
					b.put((byte) i);
					b.putShort((short) item.getId());
					b.putInt(item.getAmount());
				}
			}
			b.put((byte) -1);
		}
		b.putLong(0);
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel c = raf.getChannel()) {
			b.flip();
			c.write(b);
			raf.close();
			c.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		ResourceManager.dump(directory);
	}

	@Override
	public boolean call() {
		init();
		setDelay(1);
		GameWorld.Pulser.submit(this);
		return true;
	}

	@Override
	public boolean pulse() {
		if ((GameWorld.getTicks() % 24000) == 0) {
			for (GrandExchangeOffer offer : OFFER_MAPPING.values()) {
				if (offer.isActive() && offer.isLimitation()) {
					updateOffer(offer);
				}
			}
			BuyingLimitation.clear();
		}
		if (dumpDatabase) {
			TaskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					synchronized (GEOfferDispatch.this) {
						dump("data/");
					}
				}
			});
			dumpDatabase = false;
		}
		return false;
	}

	/**
	 * Dispatches an offer.
	 * @param player The player.
	 * @param offer The grand exchange offer.
	 * @return {@code True} if successful.
	 */
	public static boolean dispatch(Player player, GrandExchangeOffer offer) {
		if (offer.getAmount() < 1) {
			player.getPacketDispatch().sendMessage("You must choose the quantity you wish to buy!");
			System.out.println("amountthing");
			return false;
		}
		if (offer.getOfferedValue() < 1) {
			player.getPacketDispatch().sendMessage("You must choose the price you wish to buy for!");
			System.out.println("pricethng");
			return false;
		}
		if (offer.getState() != OfferState.PENDING || offer.getUid() != 0) {
			System.out.println("pendingthing");
			return false;
		}
		offer.setPlayerUID(player.getDetails().getUid());
		offer.setUid(nextUID());
		offer.setState(OfferState.REGISTERED);
		OFFER_MAPPING.put(offer.getUid(), offer);
		offer.setTimeStamp(System.currentTimeMillis());
		player.getGrandExchange().update(offer);
		dumpDatabase = true;
		return true;
	}

	/**
	 * Updates the offer.
	 * @param offer The G.E. offer to update.
	 */
	public static void updateOffer(GrandExchangeOffer offer) {
		if (!offer.isActive()) {
			return;
		}
		for (GrandExchangeOffer o : OFFER_MAPPING.values()) {
			if (o.isSell() != offer.isSell() && o.getItemId() == offer.getItemId() && o.isActive()) {
				exchange(offer, o);
				if (offer.getState() == OfferState.COMPLETED) {
					break;
				}
			}
		}
		if (offer.getState() != OfferState.COMPLETED) {
			for (GrandExchangeOffer o : ResourceManager.getStock()) {
				if (o.isSell() != offer.isSell() && o.getItemId() == offer.getItemId() && o.isActive()) {
					exchange(offer, o);
					if (offer.getState() == OfferState.COMPLETED) {
						break;
					}
				}
			}
		}
	}

	/**
	 * Exchanges between 2 offers.
	 * @param offer The grand exchange offer to update.
	 * @param o The other offer to exchange with.
	 */
	private static void exchange(GrandExchangeOffer offer, GrandExchangeOffer o) {
		if (o.isSell() == offer.isSell()) {
			return;
		}
		if ((offer.isSell() && o.getOfferedValue() < offer.getOfferedValue()) || (!offer.isSell() && o.getOfferedValue() > offer.getOfferedValue())) {
			return;
		}
		int amount = offer.getAmountLeft(true);
		if (amount > o.getAmountLeft(true)) {
			amount = o.getAmountLeft(true);
		}
		if (amount < 1) {
			return;
		}
		int coinDifference = offer.isSell() ? (o.getOfferedValue() - offer.getOfferedValue()) : (offer.getOfferedValue() - o.getOfferedValue());
		if (coinDifference < 0) {
			return;
		}
		if (EconomyManagement.getEcoState() == EcoStatus.DRAINING) {
			coinDifference *= (1.0 - EconomyManagement.getModificationRate());
		}
		offer.setCompletedAmount(offer.getCompletedAmount() + amount);
		o.setCompletedAmount(o.getCompletedAmount() + amount);
		offer.setState(offer.getAmountLeft() < 1 ? OfferState.COMPLETED : OfferState.UPDATED);
		o.setState(o.getAmountLeft() < 1 ? OfferState.COMPLETED : OfferState.UPDATED);
		if (offer.isSell()) {
			if (offer.getAmountLeft() < 1 && offer.getPlayer() != null) {
				offer.getPlayer().getAudioManager().send(new Audio(4042, 1, 1));
			}
			offer.addWithdraw(995, amount * offer.getOfferedValue());
			o.addWithdraw(o.getItemId(), amount);
			BuyingLimitation.updateBoughtAmount(o.getItemId(), o.getPlayerUID(), amount);
		} else {
			if (o.getAmountLeft() < 1 && o.getPlayer() != null) {
				o.getPlayer().getAudioManager().send(new Audio(4042, 1, 1));
			}
			offer.addWithdraw(offer.getItemId(), amount);
			o.addWithdraw(995, amount * o.getOfferedValue());
			BuyingLimitation.updateBoughtAmount(offer.getItemId(), offer.getPlayerUID(), amount);
		}
		if (coinDifference > 0) {
			addCoinDifference(offer, o, coinDifference, amount);
		}
		offer.getEntry().influenceValue(offer.getOfferedValue());
		offer.notify(UPDATE_NOTIFICATION);
		o.notify(UPDATE_NOTIFICATION);
		dumpDatabase = true;
	}

	/**
	 * Adds the coin difference between 2 offers.
	 * @param offer The offer.
	 * @param o The other offer.
	 * @param coinDifference The difference in prices.
	 */
	private static void addCoinDifference(GrandExchangeOffer offer, GrandExchangeOffer o, int coinDifference, int amount) {
		if (!offer.isSell()) {
			offer.addWithdraw(995, coinDifference * amount);
		} else {
			o.addWithdraw(995, coinDifference * amount);
		}
	}

	/**
	 * Gets the offer for the given UID.
	 * @param uid The unique ID given to the offer.
	 * @return The grand exchange offer.
	 */
	public static GrandExchangeOffer forUID(long uid) {
		return OFFER_MAPPING.get(uid);
	}

	/**
	 * Removes the offer for the given UID.
	 * @param uid The UID.
	 * @return {@code True} if successfully removed.
	 */
	public static boolean remove(long uid) {
		return OFFER_MAPPING.remove(uid) != null;
	}

	/**
	 * Gets the next UID.
	 * @return The UID.
	 */
	static long nextUID() {
		long id = offsetUID++;
		if (id == 0) {
			return nextUID();
		}
		return id;
	}

	/**
	 * Gets the offerMapping.
	 * @return the offerMapping
	 */
	public static Map<Long, GrandExchangeOffer> getOfferMapping() {
		return OFFER_MAPPING;
	}

	/**
	 * Sets the dumping flag.
	 * @param dump The dump to set.
	 */
	public static void setDumpDatabase(boolean dump) {
		GEOfferDispatch.dumpDatabase = dump;
	}
}