package org.crandor.game.node.entity.player.info;

import org.crandor.game.node.entity.player.info.portal.DonatorType;
import org.crandor.game.node.entity.player.info.portal.Icon;
import org.crandor.game.node.entity.player.info.portal.PlayerSQLManager;
import org.crandor.game.node.entity.player.info.portal.PortalShop;
import org.crandor.game.system.communication.CommunicationInfo;
import org.crandor.net.IoSession;

import java.util.concurrent.TimeUnit;

/**
 * Stores the details of a player's account.
 * @author Vexia
 * 
 */
public class PlayerDetails {
	
	/**
	 * The sql manager for this account.
	 */
	private final PlayerSQLManager sqlManager = new PlayerSQLManager(this);
	
	/**
	 * The communication info.
	 */
	private final CommunicationInfo communicationInfo = new CommunicationInfo();
	
	/**
	 * The manager for the accounts online shopping data.
	 */
	private final PortalShop shop = new PortalShop(this);

	/**
	 * The unique id info.
	 */
	private final UIDInfo info = new UIDInfo();

	/**
	 * The username of the account.
	 */
	private final String username;

	/**
	 * The password of the account.
	 */
	private String password;

	/**
	 * The unique id of the account.
	 */
	private int uid;

	/**
	 * The account's last game login.
	 */
	private long lastLogin = -1;

	/**
	 * The account's last game login.
	 */
	private long timePlayed = 0;
	
	/**
	 * The time the player is muted for.
	 */
	private long muteTime;
	
	/**
	 * The time the player is banned for.
	 */
	private long banTime;

	/**
	 * If the sql data has been parsed.
	 */
	private boolean parsed;
	
	/**
	 * The rights of the player.
	 */
	private Rights rights = Rights.REGULAR_PLAYER;
	
	/**
	 * The donator type value.
	 */
	private DonatorType donatorType;
	
	/**
	 * The chat icon value.
	 */
	private Icon icon;
	
	/**
	 * Represents the session.
	 */
	private IoSession session;

	/**
	 * Constructs a new {@code PlayerDetails}.
	 * @param username the username to set.
	 * @param password the password to set.
	 */
	public PlayerDetails(String username, String password) {
		this.username = username;
		this.password = password;
		this.uid = username.hashCode();
	}
	
	/**
	 * Constructs a new {@Code PlayerDetails} {@Code Object}
	 * @param username the username to set.
	 */
	public PlayerDetails(String username) {
		this(username, null);
	}
	
	/**
	 * Parses the details from the database for this object.
	 * @return {@code True} if parsed.
	 */
	public boolean parse() {
		if (sqlManager.parse()) {
			return parsed = true;
		}
		return parsed = false;
	}
	
	/**
	 * Saves the player's details.
	 * @return {@code True} if saved.
	 */
	public boolean save() {
		sqlManager.save();
		return true;
	}
	
	/**
	 * Gets the player details for an account name.
	 * @param username the username.
	 * @return the player details.
	 */
	public static PlayerDetails getDetails(String username) {
		PlayerDetails details = new PlayerDetails(username);
		if (!details.parse()) {
			return details = null;
		}
		return details;
	}
	
	/**
	 * Checks if the ban is permanent.
	 * @return {@code True} if so.
	 */
	public boolean isPermBan() {
		return TimeUnit.MILLISECONDS.toDays(banTime - System.currentTimeMillis()) > 1000;
	}

	/**
	 * Checks if the player is muted.
	 * @return {@code True} if so.
	 */
	public boolean isBanned() {
		return banTime > System.currentTimeMillis();
	}
	
	/**
	 * Checks if the mute is permanent.
	 * @return {@code True} if so.
	 */
	public boolean isPermMute() {
		return TimeUnit.MILLISECONDS.toDays(muteTime - System.currentTimeMillis()) > 1000;
	}

	/**
	 * Checks if the player is muted.
	 * @return {@code True} if so.
	 */
	public boolean isMuted() {
		return muteTime > System.currentTimeMillis();
	}
	
	/**
	 * Checks if the player is a donator.
	 * @return {@code True} if so.
	 */
	public boolean isDonator() {
		return donatorType != null;
	}
	
	/**
	 * Gets the donator value.
	 * @return the value as an integer.
	 */
	public int getDonatorValue() {
		return donatorType == null ? -1 : donatorType.ordinal();
	}
	
	/**
	 * Sets the donator type. 
	 * @param type the type.
	 */
	public void setDonatorType(DonatorType type) {
		this.donatorType = type;
	}

	/**
	 * Gets the donator type.
	 * @return the donator type.
	 */
	public DonatorType getDonatorType() {
		return donatorType;
	}

	/**
	 * Gets the shop.
	 * @return the shop.
	 */
	public PortalShop getShop() {
		return shop;
	}

	/**
	 * Gets the sql manager.
	 * @return the sql manager.
	 */
	public PlayerSQLManager getSqlManager() {
		return sqlManager;
	}

	/**
	 * Gets the rights.
	 * @return The rights.
	 */
	public Rights getRights() {
		return rights;
	}

	/**
	 * Sets the credentials.
	 * @param rights The credentials to set.
	 */
	public void setRights(Rights rights) {
		this.rights = rights;
	}

	/**
	 * Gets the session.
	 * @return The session.
	 */
	public IoSession getSession() {
		return session;
	}

	/**
	 * Sets the session.
	 * @param session The session to set.
	 */
	public void setSession(IoSession session) {
		this.session = session;
	}

	/**
	 * Sets the password.
	 * @param password the password.
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 * @return The username.
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * Gets the uid.
	 * @return the uid.
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 * @param uid the uid.
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * Gets the password.
	 * @return The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Checks if the details have been parsed.
	 * @return {@code True} if parsed.
	 */
	public boolean isParsed() {
		return parsed;
	}

	/**
	 * Gets the mac address.
	 * @return the address.
	 */
	public String getMacAddress() {
		return info.getMac();
	}

	/**
	 * Gets the computer name.
	 * @return the name.
	 */
	public String getCompName() {
		return info.getCompName();
	}

	/**
	 * Gets the ip address.
	 * @return the ip.
	 */
	public String getIpAddress() {
		if (session == null) {
			return info.getIp();
		}
		return session.getAddress();
	}

	/**
	 * Gets the serial.
	 * @return the serial.
	 */
	public String getSerial() {
		return info.getSerial();
	}

	/**
	 * Gets the info.
	 * @return the info
	 */
	public UIDInfo getInfo() {
		return info;
	}

	/**
	 * Gets the communicationInfo.
	 * @return the communicationInfo
	 */
	public CommunicationInfo getCommunication() {
		return communicationInfo;
	}

	/**
	 * Gets the icon.
	 * @return the icon.
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * Sets the icon.
	 * @param icon the icon to set
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * Gets the lastLogin.
	 * @return the lastLogin.
	 */
	public long getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets the lastLogin.
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets the timePlayed.
	 * @return the timePlayed.
	 */
	public long getTimePlayed() {
		return timePlayed;
	}

	/**
	 * Sets the timePlayed.
	 * @param timePlayed the timePlayed to set
	 */
	public void setTimePlayed(long timePlayed) {
		this.timePlayed = timePlayed;
	}
	
	/**
	 * Sets the mute time.
	 * @param muteTime the mute time.
	 */
	public void setMuteTime(long muteTime) {
		this.muteTime = muteTime;
	}
	
	/**
	 * Gets the mute time.
	 * @return The mute time.
	 */
	public long getMuteTime() {
		return muteTime;
	}

	/**
	 * Gets the banTime.
	 * @return the banTime.
	 */
	public long getBanTime() {
		return banTime;
	}

	/**
	 * Sets the banTime.
	 * @param banTime the banTime to set
	 */
	public void setBanTime(long banTime) {
		this.banTime = banTime;
	}

	@Override
	public String toString() {
		return "PlayerDetails [sqlManager=" + sqlManager + ", communicationInfo=" + communicationInfo + ", shop=" + shop + ", info=" + info + ", username=" + username + ", password=" + password + ", uid=" + uid + ", lastLogin=" + lastLogin + ", muteTime=" + muteTime + ", parsed=" + parsed + ", rights=" + rights + ", donatorType=" + donatorType + ", icon=" + icon + ", session=" + session + "]";
	}

}