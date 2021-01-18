package core.game.node.entity.player.info;

import core.game.node.entity.player.Player;
import core.tools.StringUtils;

/**
 * The unique machine information of an account.
 * @author Vexia
 * 
 */
public class UIDInfo {

	/**
	 * The ip address.
	 */
	private String ip;

	/**
	 * The computer name.
	 */
	private String compName;

	/**
	 * The mac-address.
	 */
	private String mac;

	/**
	 * The motherboard serial of the user.
	 */
	private String serial;

	/**
	 * Constructs a new {@code UIDInfo} {@code Object}
	 */
	public UIDInfo() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code UIDInfo} {@code Object}
	 * @param ip the ip.
	 * @param compName the computer name.
	 * @param mac the mac.
	 * @param serial the serial.
	 */
	public UIDInfo(String ip, String compName, String mac, String serial) {
		this.ip = ip;
		this.compName = compName;
		this.mac = mac.replace(":", "-");
		this.serial = serial;
	}

	/**
	 * Translates the unique info from another object.
	 * @param other the other information.
	 */
	public void translate(UIDInfo other) {
		ip = other.ip;
		compName = other.compName;
		mac = other.mac.replace(":", "-");
		serial = other.serial;
	}

	/**
	 * Converts a to string in format mode for an admin or mod.
	 * @return the string.
	 */
	public String toString(Player player, Player target) {
		boolean admin = player.isAdmin();
		String format = toString();
		if (!admin) {// formats for non-admins
			String[] tokens = format.split("serial=");
			format = format.replace("serial=", "uid=").replace(tokens[tokens.length - 1], "*****");
		}
		player.sendMessage("[----------Info Debug----------]");
		String[] lines = StringUtils.splitIntoLine(format, 60);
		player.sendMessages(lines);
		player.sendMessage("[-------------------------------]");
		return format;
	}
	
	/**
	 * Gets the compName.
	 * @return the compName
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * Gets the ip.
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Gets the mac.
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Gets the serial.
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}

	@Override
	public String toString() {
		// make sure serials always at end
		return "[ip=" + ip + ", compName=" + compName + ", mac=" + mac + ", serial=" + serial + "]";
	}

}
