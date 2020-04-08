package org.crandor.game.system.mysql.impl;

import org.crandor.ServerConstants;
import org.crandor.game.system.mysql.SQLEntryHandler;
import org.crandor.game.system.mysql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the door config parsing.
 * @author Vexia
 *
 */
public class DoorConfigSQLHandler extends SQLEntryHandler<Object> {

	/**
	 * The doors mapping.
	 */
	private static final Map<Integer, Door> DOORS = new HashMap<>();

	/**
	 * The current door being spawned.
	 */
	private Door door;

	/**
	 * Constructs a new {@Code DoorConfigSQLHandler} {@Code Object}
	 */
	public DoorConfigSQLHandler() {
		super(null, (SQLManager.LOCAL ? "server" : ServerConstants.DATABASE_NAMES[0]) + ".door_configs", "", "");
	}

	@Override
	public void parse() throws SQLException {
		connection = getConnection();
		if (connection == null) {
			SQLManager.close(connection);
			return;
		}
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE id > 0");
		ResultSet set = statement.executeQuery();
		while (set.next()) {
			parseDoor(set.getInt("id"), set);
		}
		door = null;
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
	 * Parses a door.
	 * @param id The id.
	 * @param set The result set.
	 * @throws SQLException The exception.
	 */
	public void parseDoor(int id, ResultSet set) throws SQLException {
		door = new Door(id);
		door.setReplaceId(set.getInt("replaceId"));
		door.setFence(set.getBoolean("fence"));
		door.setMetal(set.getBoolean("metal"));
		DOORS.put(id, door);
	}

	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}
	/**
	 * Gets the door for the given object id.
	 * @param id The object id.
	 * @return The door.
	 */
	public static Door forId(int id) {
		return DOORS.get(id);
	}

	/**
	 * Gets the doors.
	 * @return The doors.
	 */
	public static Map<Integer, Door> getDoors() {
		return DOORS;
	}

	/**
	 * Represents a door.
	 * @author Emperor
	 */
	public static class Door {

		/**
		 * The door's object id.
		 */
		private final int id;

		/**
		 * The door's replace object id.
		 */
		private int replaceId;

		/**
		 * If the door is closed.
		 */
		private boolean fence;

		/**
		 * If the player should automaticly walk through it.
		 */
		private boolean autoWalk;
		
		/**
		 * Is the door metal?
		 */
		private boolean metal;

		/**
		 * Constructs a new {@code DoorManager} {@code Object}.
		 */
		public Door(int id) {
			this.id = id;
		}

		/**
		 * Gets the id.
		 * @return The id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Gets the replaceId.
		 * @return The replaceId.
		 */
		public int getReplaceId() {
			return replaceId;
		}

		/**
		 * Sets the replaceId.
		 * @param replaceId The replaceId to set.
		 */
		public void setReplaceId(int replaceId) {
			this.replaceId = replaceId;
		}

		/**
		 * Gets the autoWalk.
		 * @return The autoWalk.
		 */
		public boolean isAutoWalk() {
			return autoWalk;
		}

		/**
		 * Sets the autoWalk.
		 * @param autoWalk The autoWalk to set.
		 */
		public void setAutoWalk(boolean autoWalk) {
			this.autoWalk = autoWalk;
		}

		/**
		 * Gets the fence.
		 * @return The fence.
		 */
		public boolean isFence() {
			return fence;
		}

		/**
		 * Sets the fence.
		 * @param fence The fence to set.
		 */
		public void setFence(boolean fence) {
			this.fence = fence;
		}
		
		public boolean isMetal() {
			return metal;
		}
		
		public void setMetal(boolean metal) {
			this.metal = metal;
		}

	}
}
