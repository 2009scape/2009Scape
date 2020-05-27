package ms.world;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ms.ServerConstants;
import ms.system.communication.ClanRepository;
import ms.system.communication.CommunicationInfo;
import ms.system.mysql.SQLManager;
import ms.world.info.UIDInfo;

/**
 * Represents a player session.
 * @author Emperor
 *
 */
public final class PlayerSession {
	
	/**
	 * The player's communication info.
	 */
	private CommunicationInfo communication = new CommunicationInfo(this);

	/**
	 * The game server the player is currently in.
	 */
	private GameServer world;

	/**
	 * The current clan.
	 */
	private ClanRepository clan;
	
	/**
	 * The player's UID info.
	 */
	private UIDInfo uid;

	/**
	 * The username.
	 */
	private final String username;

	/**
	 * The password.
	 */
	private String password;

	/**
	 * The player's rights.
	 */
	private int rights;

	/**
	 * The world id.
	 */
	private int worldId;

	/**
	 * If the player session is active.
	 */
	private boolean active;

	/**
	 * The time stamp of last disconnection.
	 */
	private long disconnectionTime = 0l;

	/**
	 * How long the player is banned for.
	 */
	private long banTime;
	
	/**
	 * How long the player is muted for.
	 */
	private long muteTime;

	/**
	 * The last world the player logged in.
	 */
	private int lastWorld = -1;

	/**
	 * The chat icon.
	 */
	private int chatIcon;

	/**
	 * Constructs a new {@code PlayerSession} {@code Object}.
	 * @param username The username.
	 * @param password The password.
	 * @param ipAddress The ip address.
	 * @param macAddress The mac address.
	 * @param computerName The computer name.
	 * @param serial The motherboard serial key.
	 */
	public PlayerSession(String username, String password, UIDInfo uid) {
		this.username = username;
		this.password = password;
		this.uid = uid;
	}

	/**
	 * Parses the session's data from the database.
	 * @return {@code True} if parsed.
	 */
	public boolean parse() {
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return false;
		}
		ResultSet result = null;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("SELECT * FROM " + "members" + " WHERE " + "" + "username" + "='" + username.toLowerCase() + "' LIMIT 1");
			result = statement.executeQuery();
			if (result == null || !result.next()) {
				SQLManager.close(connection);
				return false;
			}
			rights = result.getInt("rights");
			disconnectionTime = result.getLong("disconnectTime");
			lastWorld = result.getInt("lastWorld");
			banTime = result.getLong("banTime");
			communication.parse(result);
			uid.parse(result);
			SQLManager.close(connection);
		} catch (SQLException ex) {
			ex.printStackTrace();
			SQLManager.close(connection);
			return false;
		}
		return true;
	}
	
	/**
	 * Called when a session is removed.
	 */
	public void remove() {
		if (world != null && world.getInfo().getRevision() == 498) {
			return;
		}
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return;
		}
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("UPDATE members SET disconnectTime = ?, lastWorld = ?, contacts = ?, blocked = ?, clanName = ?, currentClan = ?, clanReqs = ?, chatSettings = ?, online= ? WHERE username = ?");
			statement.setLong(1, System.currentTimeMillis());
			statement.setInt(2, worldId);
			communication.save(statement);
			statement.setBoolean(9, false);
			statement.setString(10, username);
			statement.executeUpdate();
			SQLManager.close(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			SQLManager.close(connection);
		}
		communication.clear();
	}

	/**
	 * Gets a player session.
	 * @param username the username.
	 * @return the player session.
	 */
	public static PlayerSession get(String username) {
		PlayerSession session = new PlayerSession(username, "", new UIDInfo());
		if (!session.parse()) {
			return null;
		}
		return session;
	}

	/**
	 * Configures the player session.
	 */
	public void configure() {
		ClanRepository clan = ClanRepository.getClans().get(username);
		if (clan != null) {
			clan.setOwner(this);
		}
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return;
		}
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("UPDATE members SET online = ?, lastWorld = ?, lastLogin = ? WHERE username = ?");
			statement.setBoolean(1, true);
			statement.setInt(2, worldId);
			statement.setLong(3, System.currentTimeMillis());
			statement.setString(4, username);
			statement.execute();
			SQLManager.close(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			SQLManager.close(connection);
		}
	}

	/**
	 * Checks if the player has just moved worlds.
	 * @return {@code True} if so.
	 */
	public boolean hasMovedWorld() {
		if (rights == 2) {
			return false;
		}
		return System.currentTimeMillis() - disconnectionTime < ServerConstants.WORLD_SWITCH_DELAY;
	}

	/**
	 * Gets the username value.
	 * @return The username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the password value.
	 * @return The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the chat icon.
	 * @return The chat icon.
	 */
	public int getChatIcon() {
		return chatIcon;
	}

	/**
	 * Gets the ipAddress value.
	 * @return The ipAddress.
	 */
	public String getIpAddress() {
		return uid.getIp();
	}

	/**
	 * Gets the macAddress value.
	 * @return The macAddress.
	 */
	public String getMacAddress() {
		return uid.getMac();
	}

	/**
	 * Gets the computerName value.
	 * @return The computerName.
	 */
	public String getComputerName() {
		return uid.getCompName();
	}

	/**
	 * Gets the worldId value.
	 * @return The worldId.
	 */
	public int getWorldId() {
		return worldId;
	}

	/**
	 * Sets the worldId value.
	 * @param worldId The worldId to set.
	 */
	public void setWorldId(int worldId) {
		this.worldId = worldId;
	}

	/**
	 * Gets the active value.
	 * @return The active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active value.
	 * @param active The active to set.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets the serialKey value.
	 * @return The serialKey.
	 */
	public String getSerialKey() {
		return uid.getSerial();
	}
	
	/**
	 * Gets the rights value.
	 * @return The rights.
	 */
	public int getRights() {
		return rights;
	}

	/**
	 * Sets the rights value.
	 * @param rights The rights to set.
	 */
	public void setRights(int rights) {
		this.rights = rights;
	}

	/**
	 * If the player is banned.
	 * @return {@code true} if so.
	 */
	public boolean isBanned() {
		return banTime > System.currentTimeMillis();
	}

	/**
	 * Gets the banTime value.
	 * @return The banTime.
	 */
	public long getBanTime() {
		return banTime;
	}

	/**
	 * Gets the muteTime.
	 * @return the muteTime.
	 */
	public long getMuteTime() {
		return muteTime;
	}


	/**
	 * Sets the muteTime.
	 * @param muteTime the muteTime to set
	 */
	public void setMuteTime(long muteTime) {
		this.muteTime = muteTime;
	}


	/**
	 * Gets the disconnectionTime value.
	 * @return The disconnectionTime.
	 */
	public long getDisconnectionTime() {
		return disconnectionTime;
	}

	/**
	 * Gets the lastWorld value.
	 * @return The lastWorld.
	 */
	public int getLastWorld() {
		return lastWorld;
	}

	/**
	 * Sets the lastWorld value.
	 * @param lastWorld The lastWorld to set.
	 */
	public void setLastWorld(int lastWorld) {
		this.lastWorld = lastWorld;
	}

	/**
	 * Gets the communication value.
	 * @return The communication.
	 */
	public CommunicationInfo getCommunication() {
		return communication;
	}

	/**
	 * Sets the communication value.
	 * @param communication The communication to set.
	 */
	public void setCommunication(CommunicationInfo communication) {
		this.communication = communication;
	}

	/**
	 * Gets the world value.
	 * @return The world.
	 */
	public GameServer getWorld() {
		return world;
	}

	/**
	 * Sets the world value.
	 * @param world The world to set.
	 */
	public void setWorld(GameServer world) {
		this.world = world;
	}

	/**
	 * Gets the clan value.
	 * @return The clan.
	 */
	public ClanRepository getClan() {
		return clan;
	}

	/**
	 * Sets the clan value.
	 * @param clan The clan to set.
	 */
	public void setClan(ClanRepository clan) {
		this.clan = clan;
	}

	/**
	 * Sets the chat icon.
	 * @param chatIcon
	 */
	public void setChatIcon(int chatIcon) {
		this.chatIcon = chatIcon;
	}

	/**
	 * Gets the uid.
	 * @return the uid.
	 */
	public UIDInfo getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 * @param uid the uid to set
	 */
	public void setUid(UIDInfo uid) {
		this.uid = uid;
	}
	
	/**
	 * Sets the disconnect time.
	 * @param time the time.
	 */
	public void setDisconnectTime(long time) {
		this.disconnectionTime = time;
	}

	@Override
	public boolean equals(Object o) {
		return username.equals(((PlayerSession) o).username);
	}

	@Override
	public String toString() {
		return "player [name=" + username + ", pass=" + password + ", ip=" + uid.getIp() + ", mac=" + uid.getMac() + ", comp=" + uid.getCompName() + ", msk=" + uid.getSerial() + "]";
	}

}