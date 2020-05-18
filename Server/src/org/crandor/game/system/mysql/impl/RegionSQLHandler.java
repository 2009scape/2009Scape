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
import java.util.StringTokenizer;

/**
 * Handles the generating of region XTEA keys.
 * @author Vexia
 *
 */
public class RegionSQLHandler extends SQLEntryHandler<Object> {

	/**
	 * The default XTEA keys.
	 */
	public static final int[] DEFAULT_REGION_KEYS = new int[] { 14881828, -6662814, 58238456, 146761213 };

	/**
	 * Region XTEA-keys.
	 */
	private static final Map<Integer, int[]> REGION_XTEA = new HashMap<>();

	/**
	 * Constructs a new {@Code RegionSQLHandler} {@Code Object}
	 */
	public RegionSQLHandler() {
		super(null, "", "", "");
	}

	@Override
	public void parse() throws SQLException {
		connection = getConnection();
		if (connection == null) {
			SQLManager.close(connection);
			return;
		}
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+(SQLManager.LOCAL ? "server" : ServerConstants.DATABASE_NAMES[0]) + ".region_xtea");
		ResultSet set = statement.executeQuery();
		while (set.next()) {
			parseRegion(set.getInt(1), set.getString(2));
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
	 * Parses a region xtea's data.
	 * @param id the id.
	 * @param data the data.
	 */
	public void parseRegion(int id, String data) {
		StringTokenizer token = new StringTokenizer(data, ",");
		REGION_XTEA.put(id, new int[]{Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())});
	}
	
	/**
	 * Gets the XTEA keys to decrypt the region's object mapping.
	 * @param regionId The region's id.
	 * @return The region XTEA keys.
	 */
	public static int[] getRegionXTEA(int regionId) {
		int[] keys = REGION_XTEA.get(regionId);
		if (keys == null) {
			return DEFAULT_REGION_KEYS;
		}
//		return keys;
		return DEFAULT_REGION_KEYS;
	}

	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}
}
