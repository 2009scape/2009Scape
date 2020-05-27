package ms.system.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ms.world.GameServer;
import ms.world.info.WorldInfo;

/**
 * Handles the world list SQL database.
 * @author Emperor
 *
 */
public final class WorldListSQLHandler extends SQLEntryHandler<GameServer> {
	
	/**
	 * The table for this sql entry.
	 */
	private static final String TABLE = "worlds";

	/**
	 * Constructs a new {@code WorldListSQLHandler} {@code Object}.
	 * @param entry The game server entry.
	 */
	public WorldListSQLHandler(GameServer entry) {
		super(entry, TABLE, "world", "" + entry.getInfo().getWorldId());
	}

	/**
	 * Clears the world list.
	 */
	public static void clearWorldList() {
		try {
			Connection connection = SQLManager.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void parse() throws SQLException {
		
	}

	@Override
	public void create() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT "+ table + "(world, ip, players, country, member, revision) VALUES('" + value + "', '" + entry.getInfo().getAddress() + "', '" + entry.getPlayerAmount() + "', '" + entry.getInfo().getCountry().getId() + "', '" + (entry.getInfo().isMembers() ? 1 : 0) + "', '" + entry.getInfo().getRevision() + "')");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	@Override
	public void save() throws SQLException {
		super.read();
		if (result == null || !result.next()) {
			create();
			return;
		}
		int players = entry.getPlayerAmount();
		WorldInfo info = entry.getInfo();
		if (!entry.isActive()) {
			players = -1;
		}
		if (players <= 0) {
			PreparedStatement statement = connection.prepareStatement("UPDATE members SET online='0' WHERE lastWorld='" + value + "'");
			statement.executeUpdate();
		}
		PreparedStatement statement = connection.prepareStatement("UPDATE " + table + " SET players='" + players + "', ip='" + info.getAddress() + "', country='" + info.getCountry().getId() + "', member='" + (info.isMembers() ? 1 : 0) + "', revision='" + info.getRevision() + "' WHERE world='" + value + "'");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}
}