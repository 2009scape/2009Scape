package core.game.system;

import core.ServerConstants;
import plugin.ge.GrandExchangeDatabase;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.PlayerParser;
import core.game.world.repository.Repository;
import plugin.ge.OfferManager;
import plugin.interaction.object.dmc.DMCHandler;

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
			for(Player player : Repository.getPlayers()){
				DMCHandler dmc = player.getAttribute("dmc",null);
				if(dmc != null){
					dmc.clear(false);
				}
			}
			save(ServerConstants.DATA_PATH);
			System.exit(0);
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
		OfferManager.save();
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
					p.getLogoutPlugins().forEach(playerPlugin -> {
						try {
							playerPlugin.newInstance(p);
						} catch (Throwable throwable) {
							throwable.printStackTrace();
						}
					});
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
//		ServerStore.dump(directory + "store/");
		SystemLogger.log("[SystemTerminator] Saved player accounts!");
	}
}