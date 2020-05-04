package org.crandor.game.system.mysql.impl;

import org.crandor.ServerConstants;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.mysql.SQLEntryHandler;
import org.crandor.game.system.mysql.SQLManager;
import org.crandor.game.system.mysql.impl.GroundSpawnSQLHandler.GroundSpawn;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.repository.Repository;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the spawning of ground item spawns.
 * @author Vexia
 *
 */
public class GroundSpawnSQLHandler extends SQLEntryHandler<GroundSpawn> {

	/**
	 * The list of ground item spawns.
	 */
	private static final List<GroundSpawn> SPAWNS = new ArrayList<>();

	/**
	 * The current ground item being spawned.
	 */
	private GroundSpawn spawn;

	/**
	 * Constructs a new {@Code GroundSpawnSQLHandler} {@Code Object}
	 */
	public GroundSpawnSQLHandler() {
		super(null, (SQLManager.LOCAL ? "server" : ServerConstants.DATABASE_NAMES[0]) + ".ground_spawns", "", "");
	}

	/**
	 * Constructs a new {@Code GroundSpawnSqlHandler} {@Code Object}
	 * @param entry the entry.
	 * @param table the table.
	 * @param column the column.
	 * @param value the value.
	 */
	public GroundSpawnSQLHandler(GroundSpawn entry, String table, String column, String value) {
		super(entry, (SQLManager.LOCAL ? "server" : ServerConstants.DATABASE_NAMES[0]) + ".ground_spawns", "item_id", "" + entry.getId());

	}

	@Override
	public void parse() throws SQLException {
		connection = getConnection();
		if (connection == null) {
			SQLManager.close(connection);
			return;
		}
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table);
		ResultSet set = statement.executeQuery();
		while (set.next()) {
			parseItem(set.getInt(1), set.getString(2));
		}
		SQLManager.close(statement.getConnection());
	}

	@Override
	public void create() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	@Override
	public void save() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	/**
	 * Parses an item id's spawns.
	 * @param id the id.
	 * @param data the data.
	 */
	public void parseItem(int id, String data) {
		String[] datas = data.split("-");
		String[] tokens = null;
		for (String d : datas) {
			tokens = d.replace("{", "").replace("}", "").split(",");
			spawn = new GroundSpawn(Integer.valueOf(tokens[4]), new Item(id, Integer.valueOf(tokens[0])), new Location(Integer.valueOf(tokens[1]), Integer.valueOf(tokens[2]), Integer.valueOf(tokens[3])));
			spawn.init();
		}
		spawn = null;
	}

	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}

	/**
	 * Represents a ground spawn item.
	 * @author 'Vexia
	 */
	public static final class GroundSpawn extends GroundItem {

		/**
		 * Represents the rate at which a ground item will respawn.
		 */
		private int respawnRate;

		/**
		 * Constructs a new {@code GroundItemParser} {@code Object}.
		 * @param item the item.
		 * @param location the location.
		 */
		public GroundSpawn(final int respawnRate, Item item, Location location) {
			super(item, location);
			this.respawnRate = respawnRate;
		}

		@Override
		public String toString() {
			return "GroundSpawn [name=" + getName() + ", respawnRate=" + respawnRate + ", loc=" + getLocation() + "]";
		}

		/**
		 * Method used to save this ground item to a byte buffer.
		 * @param buffer the buffer.
		 */
		public final void save(final ByteBuffer buffer) {
			buffer.putInt(respawnRate);
			buffer.putShort((short) getId());
			buffer.putInt(getAmount());
			buffer.putShort((short) (getLocation().getX() & 0xFFFF)).putShort((short) (getLocation().getY() & 0xFFFF)).put((byte) getLocation().getZ());
		}

		/**
		 * Method used to initialize this spawn.
		 */
		public final void init() {
			GroundItemManager.create(this);
			SPAWNS.add(this);
		}

		@Override
		public boolean isActive() {
			return true;
		}

		@Override
		public boolean isPrivate() {
			return false;
		}

		@Override
		public boolean isAutoSpawn() {
			return true;
		}

		@Override
		public void respawn() {
			GameWorld.Pulser.submit(new Pulse(getRespawnDuration()) {
				@Override
				public boolean pulse() {
					GroundItemManager.create(GroundSpawn.this);
					return true;
				}
			});
		}

		/**
		 * Gets the respawn rate.
		 * @return the rate.
		 */
		public int getRespawnRate() {
			return respawnRate;
		}

		/**
		 * Method used to set the respawn rate.
		 * @param min the min.
		 * @param max the max.
		 */
		public void setRespawnRate(final int min, final int max) {
			this.respawnRate = min | max << 16;
		}

		/**
		 * Gets the current respawn duration (in ticks).
		 * @return The respawn duration.
		 */
		public int getRespawnDuration() {
			int minimum = respawnRate & 0xFFFF;
			int maximum = (respawnRate >> 16) & 0xFFFF;
			double playerRatio = ServerConstants.MAX_PLAYERS / Repository.getPlayers().size();
			return (int) (minimum + ((maximum - minimum) / playerRatio));
		}
	}
}
