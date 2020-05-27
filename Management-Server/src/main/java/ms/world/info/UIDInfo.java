package ms.world.info;

import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import ms.system.util.ByteBufferUtils;

/**
 * The unique info of a player.
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
		this.mac = mac;
		this.serial = serial;
	}
	
	/**
	 * Parses the data from a prepared statement. 
	 * @param set The result set
	 * @throws SQLException The exception if thrown.
	 */
	public void parse(ResultSet set) throws SQLException {
		setIp(parseFormat(set.getString("ip")));
		setCompName(parseFormat(set.getString("computerName")));
		setMac(parseFormat(set.getString("mac")));
		setSerial(parseFormat(set.getString("serial")));
	}
	
	/**
	 * Saves the UID data on the buffer.
	 * @param buffer The buffer.
	 */
	public void save(ByteBuffer buffer) {
		save(buffer, ip, 1);
		save(buffer, compName, 2);
		save(buffer, mac, 3);
		save(buffer, serial, 4);
		buffer.put((byte) 0);
	}

	/**
	 * Parses the UID data from the buffer.
	 * @param buffer The buffer.
	 */
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get()) != 0) {
			switch (opcode) {
			case 1:
				ip = ByteBufferUtils.getString(buffer);
				break;
			case 2:
				compName = ByteBufferUtils.getString(buffer);
				break;
			case 3:
				mac = ByteBufferUtils.getString(buffer);
				break;
			case 4:
				serial = ByteBufferUtils.getString(buffer);
				break;
			}
		}
	}
	
	/**
	 * Parses a string with a certain format.
	 * @param string the string.
	 * @return the string.
	 */
	private String parseFormat(String string) {
		if (string == null || string == "") {
			return null;
		}
		StringTokenizer token = new StringTokenizer(string, "|");
		String s = "";
		int t = token.countTokens();
		for (int i = 0; i < t; i++) {
			s = token.nextToken();
		}
		return s;
	}
	
	/**
	 * Saves a string value to the buffer.
	 * @param buffer the buffer.
	 * @param value the value.
	 * @param opcode the opcode.
	 */
	private void save(ByteBuffer buffer, String value, int opcode) {
		if (value == null) {
			return;
		}
		ByteBufferUtils.putString(value, buffer.put((byte) opcode));
	}

	/**
	 * Converts a to string in format mode for an admin or mod.
	 * @param admin the admin.
	 * @return the string.
	 */
	public String toString(boolean admin) {
		String format = toString();
		if (!admin) {//formats for non-admins
			String[] tokens = format.split("serial=");
			format = format.replace("serial=", "uid=").replace(tokens[tokens.length - 1], "*****");
		}
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

	/**
	 * Sets the ip.
	 * @param ip the ip to set.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Sets the compName.
	 * @param compName the compName to set.
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}

	/**
	 * Sets the mac.
	 * @param mac the mac to set.
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * Sets the serial.
	 * @param serial the serial to set.
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	@Override
	public String toString() {
		return "[ip=" + ip + ", compName=" + compName + ", mac=" + mac + ", serial=" + serial + "]";
	}
}
