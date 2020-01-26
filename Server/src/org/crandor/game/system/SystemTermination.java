package org.crandor.game.system;

import org.crandor.cache.ServerStore;
import org.crandor.game.content.eco.ge.GEOfferDispatch;
import org.crandor.game.content.eco.ge.GrandExchangeDatabase;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.login.PlayerParser;
import org.crandor.game.world.repository.Repository;

import java.io.File;
import java.util.Iterator;

/**
 * Handles the terminating of the system.
 * @author Emperor
 * 
 */
public final class SystemTermination {

	/**
	 * The data directory.
	 */
	private static final String DATA_DIRECTORY = "data/";

	/**
	 * The backup directory.
	 */
	private static final String BACKUP_DIRECTORY = "data/backup/";

	/**
	 * Constructs a new {@code SystemTermination} {@code Object}.
	 */
	protected SystemTermination() {
		/*
		 * empty.
		 */
	}

	/**
	 * Terminates the system safely.
	 */
	public void terminate() {
		SystemLogger.log("[SystemTerminator] Initializing termination sequence - do not shutdown!");
		try {
			save(getDataDirectory());
		} catch (Throwable e) {
			e.printStackTrace();
			try {
				save(getBackupDirectory());
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		SystemLogger.log("[SystemTerminator] Server successfully terminated!");
	}

	/**
	 * Saves all system data on the directory.
	 * @param directory The base directory.
	 * @throws Throwable When an exception occurs.
	 */
	public void save(String directory) throws Throwable {
		File file = new File(directory);
		SystemLogger.log("[SystemTerminator] Saving data [dir=" + file.getAbsolutePath() + "]...");
		if (!file.isDirectory()) {
			file.mkdir();
		}
		GrandExchangeDatabase.dump(directory);
		GEOfferDispatch.dump(directory);
		SystemLogger.log("[SystemTerminator] Saved Grand Exchange databases!");
		Repository.getDisconnectionQueue().clear();
		for (Iterator<Player> it = Repository.getPlayers().iterator(); it.hasNext();) {
			try {
				Player p = it.next();
				if (p != null && !p.isArtificial()) { // Should never be null.
					p.removeAttribute("combat-time");
					p.clear();
					PlayerParser.dump(p, directory);
					p.getDetails().save();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		ServerStore.dump(directory + "store/");
		SystemLogger.log("[SystemTerminator] Saved player accounts!");
	}

	/**
	 * Gets the data directory.
	 * @return the directory.
	 */
	public static String getDataDirectory() {
		return DATA_DIRECTORY;
	}

	/**
	 * Gets the backup directory.
	 * @return the directory.
	 */
	public static String getBackupDirectory() {
		return BACKUP_DIRECTORY;
	}
}