package core.game.system;

import core.ServerConstants;
import plugin.ge.GEOfferDispatch;
import plugin.ge.GrandExchangeDatabase;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.PlayerParser;
import core.game.world.repository.Repository;

import java.io.File;
import java.util.Iterator;

/**
 * Handles the terminating of the system.
 * @author Emperor
 * 
 */
public final class SystemTermination {

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
			save(ServerConstants.DATA_PATH);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		SystemLogger.log("[SystemTerminator] Server successfully terminated!");
	}

	/**
	 * Saves all system data on the directory.
	 * @param directory The base directory.
	 */
	public void save(String directory) {
		File file = new File(directory);
		SystemLogger.log("[SystemTerminator] Saving data [dir=" + file.getAbsolutePath() + "]...");
		if (!file.isDirectory()) {
			file.mkdir();
		}
		GrandExchangeDatabase.save();
		GEOfferDispatch.dump();
		SystemLogger.log("[SystemTerminator] Saved Grand Exchange databases!");
		Repository.getDisconnectionQueue().clear();
		for (Iterator<Player> it = Repository.getPlayers().iterator(); it.hasNext();) {
			try {
				Player p = it.next();
				if (p != null && !p.isArtificial()) { // Should never be null.
					p.removeAttribute("combat-time");
					p.clear();
					PlayerParser.save(p);
					p.getDetails().save();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
//		ServerStore.dump(directory + "store/");
		SystemLogger.log("[SystemTerminator] Saved player accounts!");
	}
}