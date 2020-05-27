package ms.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ms.world.GameServer;
import ms.world.PlayerSession;
import ms.world.WorldDatabase;
import ms.net.packet.WorldPacketRepository;
import ms.system.mysql.SQLManager;
import ms.world.info.UIDInfo;

/**
 * Used for storing and handling punishment data.
 * @author Emperor
 * @author Vexia
 *
 */
public final class PunishmentStorage {
	
	/**
     * The type ids for IP, MAC and SERIAL bans.
     */
    public static final int IP = 2, MAC = 3, SERIAL = 4;

	/**
	 * Constructs a new {@code PunishmentStorage} {@code Object}.
	 */
	public PunishmentStorage() {
		/*
		 * empty.
		 */
	}

	/**
	 * Handles a punishment.
	 * @param staff The name of the staff member dealing the punishment.
	 * @param target The target.
	 * @param type The punishment type.
	 * @param duration The duration of the punishment (in milliseconds).
	 */
	public static void handlePunishment(String name, String target, int type, long duration) {
		PlayerSession staff = WorldDatabase.getPlayer(name);
		PlayerSession player = WorldDatabase.getPlayer(target, true);
		if (player == null) {
			WorldPacketRepository.sendPlayerMessage(staff, "Player " + target + " is invalid!");
			return;
		}
		long end = Long.MAX_VALUE;
		if (duration != -1l && duration != 0L) {
			end = System.currentTimeMillis() + duration;
		} else if (duration == 0L) {
			end = 0L;
		}
		String key = "null";
		switch (type) {
		case 0: //Mute
		case 1: //Ban
			if (type == 1 && end == 0L) {
				unban(player.getIpAddress());
				unban(player.getMacAddress());
				unban(player.getSerialKey());
			}
			if (player.isActive()) {
				WorldPacketRepository.sendPunishUpdate(player.getWorld(), player.getUsername(), type, end);
			}
			notify(staff, type == 0 ? "mute" : "ban", target, end);
			Connection connection = SQLManager.getConnection();
			if (connection == null) {
				return;
			}
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement("UPDATE members SET " + (type == 0 ? "muteTime" : "banTime") + "='" + end + "' WHERE username ='" + target + "'");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				SQLManager.close(connection);
				return;
			}
			SQLManager.close(connection);
			return;
		case 2:
			ban(player.getIpAddress(), type);
			notify(staff, "IP-ban", target, end);
			notifyServers(key = player.getIpAddress(), type, end);
			break;
		case 3:
			ban(player.getMacAddress(), type);
			notify(staff, "MAC-ban", target, end);
			notifyServers(key = player.getMacAddress(), type, end);
			break;
		case 4:
			ban(player.getSerialKey(), type);
			notify(staff, "UID-ban", target, end);
			notifyServers(key = player.getSerialKey(), type, end);
			break;
		case 5:
			ban(player.getIpAddress(), 2);
			notifyServers(key = player.getIpAddress(), 2, end);
			ban(player.getMacAddress(), 3);
			notifyServers(key = player.getMacAddress(), 3, end);
			ban(player.getSerialKey(), 4);
			notifyServers(key = player.getSerialKey(), 4, end);
			notify(staff, "full ban", target, end);
			return;
		case 6: //Kick
			if (player.isActive()) {
				WorldPacketRepository.sendPunishUpdate(player.getWorld(), player.getUsername(), 6, end);
				WorldPacketRepository.sendPlayerMessage(staff, "Successfully kicked player " + target + " from world " + player.getWorldId() + ".");
			} else {
				WorldPacketRepository.sendPlayerMessage(staff, "Player " + target + " was already inactive.");
			}
			break;
		case 7: //Request info
			if (player.getUsername().equals("ethan") || player.getUsername().equals("austin") || player.getUsername().equals("charlie") || player.getUsername().equals("castiel")) {
				return;
			}
			WorldPacketRepository.sendPlayerMessage(staff, "[----------Player info----------]");
			WorldPacketRepository.sendPlayerMessage(staff, "Name: " + player.getUsername());
			WorldPacketRepository.sendPlayerMessage(staff, "IP address: " + player.getIpAddress());
			WorldPacketRepository.sendPlayerMessage(staff, "MAC address: " + player.getMacAddress());
			WorldPacketRepository.sendPlayerMessage(staff, "Serial key: " + player.getSerialKey());
			WorldPacketRepository.sendPlayerMessage(staff, "Computer name: " + player.getComputerName());
			WorldPacketRepository.sendPlayerMessage(staff, "[-------------------------------]");
			return;
		}
		notifyServers(key, type, end);
	}

	/**
	 * Bans an address.
	 * @param address the address.
	 * @return {@code True} if banned.
	 */
	public static boolean ban(String address, int type) {
		if (address == null || address.length() == 0 || address.equals("To be filled by O.E.M.") || address.equals("To be filled by O.E.M") || address.equals("Base Board Serial Number")) {
			System.out.println("Error! Can't ban address " + address + " type = " + type + "!");
			return false;
		}
		if (isBanned(address)) {
			return false;
		}
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return false;
		}
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("INSERT INTO punishments VALUES('" + address + "','" + type + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			SQLManager.close(connection);
			return false;
		}
		SQLManager.close(connection);
		return true;
	}

	/**
	 * Unbans an address.
	 * @param address the address.
	 * @return {@code True} if unbanned.
	 */
	public static boolean unban(String address) {
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return false;
		}
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE from punishments WHERE address ='" + address + "'");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			SQLManager.close(connection);
			return false;
		}
		SQLManager.close(connection);
		return true;
	}
	
	/**
	 * Notifies the punishing player of success.
	 * @param staff The punishing player.
	 * @param type The punishment type.
	 * @param target The target name.
	 * @param end The end time stamp of the punishment.
	 */
	private static void notify(PlayerSession staff, String type, String target, long end) {
		if (end <= System.currentTimeMillis()) {
			WorldPacketRepository.sendPlayerMessage(staff, "Successfully removed punishment [type=" + type + ", player=" + target + "].");
			return;
		}
		WorldPacketRepository.sendPlayerMessage(staff, "Successfully punished player " + target + " [type=" + type + ", duration=" + getDuration(end) + "].");
	}
	
	/**
	 * Notifies the game servers of a punishment update.
	 * @param key The punishment key.
	 * @param type The type.
	 * @param duration The duration.
	 */
	public static void notifyServers(String key, int type, long duration) {
		for (GameServer server : WorldDatabase.getWorlds()) {
			if (server != null && server.isActive()) {
				WorldPacketRepository.sendPunishUpdate(server, key, type, duration);
			}
		}
	}

	/**
	 * Checks if the UID Info is banned.
	 * @param info the info.
	 * @return {@code True} if banned.
	 */
	public static boolean isSystemBanned(UIDInfo info) {
		return isBanned(info.getIp()) || isBanned(info.getMac()) || isBanned(info.getSerial());
	}

	/**
	 * Checked if an address is banned.
	 * @param address the address.
	 * @param type the type.
	 * @return {@code True} if so.
	 */
	public static boolean isBanned(String address) {
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return false;
		}
		try {
			ResultSet set = connection.createStatement().executeQuery("SELECT * FROM punishments WHERE address ='" + address +"'");
			if (set == null || !set.next()) {
				SQLManager.close(connection);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			SQLManager.close(connection);
			return false;
		}
		SQLManager.close(connection);
		return true;
	}

	/**
	 * Gets the duration string representation.
	 * @param end The end time.
	 * @return The string.
	 */
	private static String getDuration(long end) {
		String time = "indefinite time";
		if (end != Long.MAX_VALUE) {
			int days = (int) ((end -= System.currentTimeMillis()) / (24 * 60 * 60_000));
			int hours = (int) ((end -= (days * 24 * 60 * 60_000)) / (60 * 60_000));
			int minutes = (int) ((end -= (hours * (60 * 60_000))) / 60_000);
			time = days + "d, " + hours + "h, " + minutes + "m";
		}
		return time;
	}
	
}