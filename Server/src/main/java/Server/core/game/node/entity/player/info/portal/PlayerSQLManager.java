package core.game.node.entity.player.info.portal;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.PlayerDetails;
import core.game.node.entity.player.info.Rights;
import core.game.node.entity.player.info.login.Response;
import core.game.system.SystemManager;
import core.game.system.mysql.SQLColumn;
import core.game.system.mysql.SQLEntryHandler;
import core.game.system.mysql.SQLManager;
import core.game.system.mysql.SQLTable;
import core.game.system.mysql.impl.PlayerSQLHandler;
import core.net.amsc.WorldCommunicator;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manages the SQL aspect for an account.
 * @author Vexia
 * 
 */
public final class PlayerSQLManager {

	/**
	 * The representation of an account sql table.
	 */
	private final SQLTable table = new SQLTable(
			new SQLColumn("UID", Integer.class),
			new SQLColumn("rights", Integer.class), 
			new SQLColumn("donatorType", Integer.class), 
			new SQLColumn("credits", Integer.class), 
			new SQLColumn("icon", Integer.class), 
			new SQLColumn("perks", String.class), 
			new SQLColumn("ip", String.class, false), 
			new SQLColumn("mac", String.class, false), 
			new SQLColumn("serial", String.class, false), 
			new SQLColumn("computerName", String.class, false), 
			new SQLColumn("netWorth", BigInteger.class, false), 
			new SQLColumn("ironManMode", String.class, false), 
			new SQLColumn("bank", String.class, false), 
			new SQLColumn("inventory", String.class, false),
			new SQLColumn("equipment", String.class, false), 
			new SQLColumn("ge", String.class, false),
			new SQLColumn("muteTime", Long.class),
			new SQLColumn("banTime", Long.class),
			new SQLColumn("contacts", String.class),
			new SQLColumn("blocked", String.class),
			new SQLColumn("clanName", String.class),
			new SQLColumn("currentClan", String.class),
			new SQLColumn("clanReqs", String.class),
			new SQLColumn("timePlayed", Long.class),
			new SQLColumn("lastLogin", Long.class),
			new SQLColumn("online", Integer.class),
			new SQLColumn("lastGameIp", String.class));

	/**
	 * The details the SQL manager is managing.
	 */
	private final PlayerDetails details;

	/**
	 * Constructs a new {@Code PlayerSQLManager} {@Code Object}
	 * @param details the details.
	 */
	public PlayerSQLManager(PlayerDetails details) {
		this.details = details;
	}

	/**
	 * Parses the SQL table.
	 * @return {@code True} if parsed.
	 */
	@SuppressWarnings("deprecation")
	public boolean parse() {
		if(!SQLEntryHandler.read(new PlayerSQLHandler(details))) {
			return false;
		}
		details.getCommunication().parse(table);
		details.credits = (int) table.getColumn("credits").getValue();
		details.setBanTime((long) table.getColumn("banTime").getValue());
		details.setMuteTime((long) table.getColumn("muteTime").getValue());
		details.setIcon(Icon.forId((int) table.getColumn("icon").getValue()));
		details.setRights(Rights.forId((int) table.getColumn("rights").getValue()));
		details.setLastLogin(System.currentTimeMillis());
		details.setTimePlayed((long) table.getColumn("timePlayed").getValue());
		return true;
	}

	/**
	 * Saves the changed SQL columns to the database.
	 */
	public void save() {
		SQLEntryHandler.write(new PlayerSQLHandler(details));
	}

	/**
	 * Updates column values with the player instance. 
	 * @param player The player instance.
	 */
	public void update(Player player) {
		if (!WorldCommunicator.isEnabled()) {
			details.getCommunication().save(table);
		}
		table.getColumn("credits").updateValue(player.getDetails().credits);
		table.getColumn("bank").updateValue(player.getBank().format());
		table.getColumn("lastLogin").updateValue(player.getDetails().getLastLogin());
		table.getColumn("ge").updateValue(player.getPlayerGrandExchange().format());
		table.getColumn("inventory").updateValue(player.getInventory().format());
		table.getColumn("equipment").updateValue(player.getEquipment().format());
		table.getColumn("netWorth").updateValue(player.getMonitor().getNetworth());
		table.getColumn("lastGameIp").updateValue(player.getDetails().getIpAddress());
		table.getColumn("ironManMode").updateValue(player.getIronmanManager().getMode().name());
		table.getColumn("timePlayed").updateValue(player.getDetails().getTimePlayed() + (System.currentTimeMillis() - player.getDetails().getLastLogin()));
		table.getColumn("ip").updateValue(getAddressLog((String) table.getColumn("ip").getValue(), details.getInfo().getIp()));
		table.getColumn("mac").updateValue(getAddressLog((String) table.getColumn("mac").getValue(), details.getInfo().getMac()));
		table.getColumn("serial").updateValue(getAddressLog((String) table.getColumn("serial").getValue(), details.getInfo().getSerial()));
		table.getColumn("computerName").updateValue(getAddressLog((String) table.getColumn("computerName").getValue(), details.getInfo().getCompName()));
	}

	/**
	 * Checks if an account was created under a name.
	 * @param name the name.
	 * @param field the field
	 * @return {@code True} if so.
	 */
	public static boolean hasSqlAccount(String name, String field) throws SQLException {
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return true;
		}
		ResultSet result = null;
		PreparedStatement statement;
		statement = connection.prepareStatement("SELECT * FROM " + "members" + " WHERE " + "" + field + "='" + name.toLowerCase() + "' LIMIT 1");
		result = statement.executeQuery();
		if (result == null || !result.next()) {
			SQLManager.close(connection);
			return false;
		}
		SQLManager.close(connection);
		return true;
	}

	public static Response updatePassword(String name, String pass) throws SQLException{
		if (!SQLManager.isInitialized()) {
			return Response.INVALID_CREDENTIALS;
		}
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return Response.INVALID_LOGIN_SERVER;
		}
		PreparedStatement statement;
		statement = connection.prepareStatement("UPDATE " + "members" + " SET password='" + pass + "' WHERE " + "username" + "='" + name.toLowerCase() + "' LIMIT 1");
		boolean Success = statement.execute();
		if(Success) return Response.SUCCESSFUL;
		return null;
	}

	/**
	 * Checks if a username & password are correct.
	 * @param name the name.
	 * @param pass the pass.
	 * @return the response.
	 * @throws SQLException the exception if thrown.
	 */
	public static Response getCredentialResponse(String name, String pass) throws SQLException {
		if (!SQLManager.isInitialized()) {
			return Response.INVALID_CREDENTIALS;
		}
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return Response.INVALID_LOGIN_SERVER;
		}
		ResultSet result = null;
		PreparedStatement statement;
		statement = connection.prepareStatement("SELECT * FROM " + "members" + " WHERE " + "" + "username" + "='" + name.toLowerCase() + "' LIMIT 1");
		result = statement.executeQuery();
		if (result == null || !result.next()) {
			SQLManager.close(connection);
			return Response.INVALID_CREDENTIALS;
		}
		String realPass = result.getString("password");
		if (SystemManager.getEncryption().checkPassword(pass, realPass)) {
			SQLManager.close(connection);
			return Response.SUCCESSFUL;
		}
		SQLManager.close(connection);
		return Response.INVALID_CREDENTIALS;
	}

	/**
	 * Gets the address log.
	 * @param original the original log.
	 * @param address the address.
	 * @return the address.
	 */
	private String getAddressLog(String original, String address) {
		String log = "";
		if (original != null && original.length() > 0) {
			log += original;
			if (log.charAt(log.length() - 1) != '|') {
				log += "|";
			}
		}
		if (address != null && address.length() > 0 && (original == null || !original.contains(address))) {
			log += address + "|";
		}
		if (log.length() > 0 && log.charAt(log.length() - 1) == '|') {
			log = log.substring(0, log.length() - 1);
		}
		if (log == null) {
			log = "";
		}
		return log;
	}

	/**
	 * Gets the details.
	 * @return the details.
	 */
	public PlayerDetails getDetails() {
		return details;
	}

	/**
	 * Gets the table.
	 * @return the table.
	 */
	public SQLTable getTable() {
		return table;
	}
}
