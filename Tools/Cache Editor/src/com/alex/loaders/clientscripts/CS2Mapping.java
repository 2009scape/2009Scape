package com.alex.loaders.clientscripts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.alex.store.Store;
import com.alex.utils.ByteBufferUtils;

/**
 * The CS2 mapping.
 * @author Emperor
 *
 */
public final class CS2Mapping {

	/**
	 * The CS2 mappings.
	 */
	private static final Map<Integer, CS2Mapping> maps = new HashMap<>();

	/**
	 * The script id.
	 */
	private final int scriptId;

	/**
	 * Unknown value.
	 */
	private int unknown;

	/**
	 * Second unknown value.
	 */
	private int unknown1;

	/**
	 * The default string value.
	 */
	private String defaultString;

	/**
	 * The default integer value.
	 */
	private int defaultInt;

	/**
	 * The mapping.
	 */
	private HashMap<Integer, Object> map;

	/**
	 * Constructs a new {@code CS2Mapping} {@code Object}.
	 * @param scriptId The script id.
	 */
	public CS2Mapping(int scriptId) {
		this.scriptId = scriptId;
	}
	
	/**
	 * The main method.
	 * @param args The arguments cast on runtime.
	 * @throws Throwable When an exception occurs.
	 */
	public static void main(String...args) throws Throwable {
		Store store = new Store("./666/");
		BufferedWriter bw = new BufferedWriter(new FileWriter("./music_indexes.txt"));
		CS2Mapping musicList = forId(1347, store);
		CS2Mapping idList = forId(1351, store);
		for (int index : musicList.map.keySet()) {
			String name = (String) musicList.map.get(index);
			int id = (int) idList.map.get(index);
			bw.append(name + ": " + id);
			bw.newLine();
		}
//		for (int i = 0; i < 20000; i++) {
//			CS2Mapping mapping = forId(i, store);
//			if (mapping == null || mapping.map == null) {
//				continue;
//			}
//			bw.append(i + " - [default=" + mapping.defaultString + "/" + mapping.defaultInt + "] map=" + mapping.map);
//			bw.newLine();
//		}
		bw.flush();
		bw.close();
	}

	/**
	 * Gets the mapping for the given script id.
	 * @param scriptId The script id.
	 * @return The mapping.
	 */
	public static CS2Mapping forId(int scriptId, Store store) {
		CS2Mapping mapping = maps.get(scriptId);
		if (mapping != null) {
			return mapping;
		}
		mapping = new CS2Mapping(scriptId);
		byte[] bs = store.getIndexes()[17].getFile(scriptId >>> 8, scriptId & 0xFF);
		if (bs != null) {
			mapping.load(ByteBuffer.wrap(bs));
		} else {
			return null;
		}
		maps.put(scriptId, mapping);
		return mapping;
	}

	/**
	 * Loads the mapping data.
	 * @param stream The buffer to read the data from.
	 */
	private void load(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				unknown = buffer.get() & 0xFF;
				break;
			case 2:
				unknown1 = buffer.get() & 0xFF;
				break;
			case 3:
				defaultString = ByteBufferUtils.getString(buffer);
				break;
			case 4:
				defaultInt = buffer.getInt();
				break;
			case 5:
			case 6:
			case 7:
			case 8:
				int size = buffer.getShort() & 0xFFFF;
				map = new HashMap<>(size);
				int loop = opcode > 6 ? buffer.getShort() & 0xFFFF : size;
				for (int i = 0; i < loop; i++) {
					int key = opcode > 6 ? buffer.getShort() & 0xFFFF : buffer.getInt();
					if (opcode % 2 != 0) {
						map.put(key, ByteBufferUtils.getString(buffer));
					} else {
						map.put(key, buffer.getInt());
					}
				}
				break;
				/*
				 * else if (opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
			int count = stream.readUnsignedShort();
			int loop = opcode == 7 || opcode == 8 ? stream.readUnsignedShort()
					: count;
			values = new HashMap<Long, Object>(Utils.getHashMapSize(count));
			for (int i = 0; i < loop; i++) {
				int key = opcode == 7 || opcode == 8 ? stream
						.readUnsignedShort() : stream.readInt();
				Object value = opcode == 5 || opcode == 7 ? stream.readString()
						: stream.readInt();
				values.put((long) key, value);
			}
		}
				 */
			}
		}
	}

	/**
	 * Gets the scriptId.
	 * @return The scriptId.
	 */
	public int getScriptId() {
		return scriptId;
	}

	/**
	 * Gets the unknown.
	 * @return The unknown.
	 */
	public int getUnknown() {
		return unknown;
	}

	/**
	 * Sets the unknown.
	 * @param unknown The unknown to set.
	 */
	public void setUnknown(int unknown) {
		this.unknown = unknown;
	}

	/**
	 * Gets the unknown1.
	 * @return The unknown1.
	 */
	public int getUnknown1() {
		return unknown1;
	}

	/**
	 * Sets the unknown1.
	 * @param unknown1 The unknown1 to set.
	 */
	public void setUnknown1(int unknown1) {
		this.unknown1 = unknown1;
	}

	/**
	 * Gets the defaultString.
	 * @return The defaultString.
	 */
	public String getDefaultString() {
		return defaultString;
	}

	/**
	 * Sets the defaultString.
	 * @param defaultString The defaultString to set.
	 */
	public void setDefaultString(String defaultString) {
		this.defaultString = defaultString;
	}

	/**
	 * Gets the defaultInt.
	 * @return The defaultInt.
	 */
	public int getDefaultInt() {
		return defaultInt;
	}

	/**
	 * Sets the defaultInt.
	 * @param defaultInt The defaultInt to set.
	 */
	public void setDefaultInt(int defaultInt) {
		this.defaultInt = defaultInt;
	}

	/**
	 * Gets the map.
	 * @return The map.
	 */
	public HashMap<Integer, Object> getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 * @param map The map to set.
	 */
	public void setMap(HashMap<Integer, Object> map) {
		this.map = map;
	}
}