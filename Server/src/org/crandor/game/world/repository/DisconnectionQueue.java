package org.crandor.game.world.repository;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.login.PlayerParser;
import org.crandor.game.system.mysql.SQLEntryHandler;
import org.crandor.game.system.mysql.impl.HighscoreSQLHandler;
import org.crandor.game.system.mysql.impl.PlayerLogSQLHandler;
import org.crandor.game.system.task.TaskExecutor;
import org.crandor.game.world.GameWorld;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles disconnecting players queuing.
 * @author Emperor
 */
public final class DisconnectionQueue {

	/**
	 * The pending disconnections queue.
	 */
	private final Map<String, DisconnectionEntry> queue;

	/**
	 * Constructs a new {@code DisconnectionQueue} {@code Object}.
	 */
	public DisconnectionQueue() {
		queue = new ConcurrentHashMap<>();
	}

	/**
	 * Updates all entries.
	 */
	public void update() {
		if (queue.isEmpty() || ((GameWorld.getTicks() % 3) != 0 && !GameWorld.getSettings().isDevMode())) {
			return;
		}
		int tickFlag = GameWorld.getTicks() - 3;
		Set<String> keySet = queue.keySet();
		Object[] keys = keySet.toArray();
		int length = keys.length;
		for (int i = 0; i < length; i++) {
			String name = (String) keys[i];
			DisconnectionEntry entry = queue.get(name);
			if (entry.getTimeStamp() < tickFlag && finish(entry, false)) {
				queue.remove(name);
			}
		}
	}

	/**
	 * Finishes a disconnection.
	 * @param entry The entry.
	 * @param force If finalization should be forced.
	 */
	private boolean finish(DisconnectionEntry entry, boolean force) {
		final Player player = entry.getPlayer();
		if (!force && !player.allowRemoval()) {
			return false;
		}
		if (entry.isClear()) {
			player.clear();
		}
		Repository.getPlayerNames().remove(player.getName());
		Repository.getLobbyPlayers().remove(player);
		Repository.getPlayers().remove(player);
		if (player.isArtificial()) {
			return true;
		}
		if (!force) {
			TaskExecutor.executeSQL(new Runnable() {
				@Override
				public void run() {
					save(player, true);
				}
			});
			return true;
		}
		save(player, false);
		return true;
	}

	/**
	 * Saves the player.
	 * @param player The player to be saved.
	 * @param sql If the sql database should be updated.
	 */
	public static boolean save(Player player, boolean sql) {
		try {
			PlayerParser.dump(player);
			if (sql) {
				player.getDetails().getSqlManager().update(player);
				player.getDetails().save();
				SQLEntryHandler.write(new HighscoreSQLHandler(player));
				SQLEntryHandler.write(new PlayerLogSQLHandler(player.getMonitor(), player.getName()));
			}
			return true;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return false;
	}

	/**
	 * Gets a queued player.
	 * @param name The player name.
	 * @return The player instance.
	 */
	public Player get(String name) {
		DisconnectionEntry entry = queue.get(name);
		if (entry != null) {
			return entry.getPlayer();
		}
		return null;
	}

	/**
	 * Clears the queue.
	 */
	public void clear() {
		for (DisconnectionEntry entry : queue.values()) {
			finish(entry, true);
		}
		queue.clear();
	}

	/**
	 * Adds a player to the disconnection queue.
	 * @param player The player.
	 */
	public void add(Player player) {
		add(player, false);
	}

	/**
	 * Adds a player to the disconnection queue.
	 * @param player The player.
	 * @param clear If the player should be cleared.
	 */
	public void add(Player player, boolean clear) {
		queue.put(player.getName(), new DisconnectionEntry(player, clear));
	}

	/**
	 * Checks if the queue contains the player name.
	 * @param name The name.
	 * @return {@code True} if so.
	 */
	public boolean contains(String name) {
		return queue.containsKey(name);
	}

	/**
	 * Removes a queued player.
	 * @param name The name.
	 */
	public void remove(String name) {
		queue.remove(name);
	}

	/**
	 * Represents an entry in the disconnection queue, holding the disconnected
	 * player and time stamp of disconnection.
	 * @author Emperor
	 */
	class DisconnectionEntry {

		/**
		 * The time of disconnection.
		 */
		private int timeStamp;

		/**
		 * The player.
		 */
		private final Player player;

		/**
		 * If the {@code Player#clear()} method should be called.
		 */
		private boolean clear;

		/**
		 * Constructs a new {@code DisconnectionQueue} {@code Object}.
		 * @param player The disconnecting player.
		 * @param clear If the player should be cleared.
		 */
		public DisconnectionEntry(Player player, boolean clear) {
			this.player = player;
			this.clear = clear;
			this.timeStamp = GameWorld.getTicks();
		}

		/**
		 * Gets the timeStamp.
		 * @return The timeStamp.
		 */
		public int getTimeStamp() {
			return timeStamp;
		}

		/**
		 * Sets the timeStamp.
		 * @param timeStamp The timeStamp to set.
		 */
		public void setTimeStamp(int timeStamp) {
			this.timeStamp = timeStamp;
		}

		/**
		 * Gets the player.
		 * @return The player.
		 */
		public Player getPlayer() {
			return player;
		}

		/**
		 * Gets the clear.
		 * @return The clear.
		 */
		public boolean isClear() {
			return clear;
		}

		/**
		 * Sets the clear.
		 * @param clear The clear to set.
		 */
		public void setClear(boolean clear) {
			this.clear = clear;
		}
	}
}