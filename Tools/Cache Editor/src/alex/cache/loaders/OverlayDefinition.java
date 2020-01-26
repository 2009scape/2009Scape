package alex.cache.loaders;

import java.util.HashMap;
import java.util.Map;

import com.alex.io.InputStream;
import com.alex.store.Store;


public class OverlayDefinition {

	private static final Map<Integer, OverlayDefinition> DEFINITIONS = new HashMap<>();
	private int rgb = -1;
	private int textureId;
	private boolean bool;
	private int id;
	
	public OverlayDefinition(int id) {
		this.id = id;
	}

	public static OverlayDefinition forId(Store store, int id) {
		OverlayDefinition def = DEFINITIONS.get(id);
		if (def != null) {
			return def;
		}
		byte[] data = store.getIndexes()[2].getFile(4, id);
		if (data == null) {
			return null;
		}
		def = new OverlayDefinition(id);
		def.readValues(new InputStream(data), id);
		DEFINITIONS.put(id, def);
		return def;
	}
	
	public void readValues(InputStream buffer, int id) {
		for (;;) {
			int opcode = buffer.readByte();
			if (opcode == 0) {
				break;
			}
			parseOpcode(buffer, opcode, id);
		}
	}
	
	private final void parseOpcode(InputStream buffer, int opcode, int id) {
		switch (opcode) {
		case 1:
            rgb = ((buffer.readByte() & 0xff) << 16) + ((buffer.readByte() & 0xff) << 8) + (buffer.readByte() & 0xff);
			break;
		case 2:
			textureId = buffer.readByte();
			break;
		case 3:
			textureId = buffer.readShort() & 0xFFFF;
			if (textureId == 65535) {
				textureId = -1;
			}
			break;
		case 5:
			bool = false;
			break;
		case 7:
			buffer.readByte();
			buffer.readShort(); //Class68.method1252(false, buffer.getTriByte(124));
			break;
//		case 8: Class17.anInt305 = id;
		case 9:
			buffer.readShort();
			break;
		case 11:
			buffer.readByte();
			break;
		case 13:
			buffer.readByte();
			buffer.readShort();
			break;
		case 14:
			buffer.readByte();
			break;
		}
	}

	/**
	 * @return the textureId
	 */
	public int getTextureId() {
		return textureId;
	}

	/**
	 * @param textureId the textureId to set
	 */
	public void setTextureId(int textureId) {
		this.textureId = textureId;
	}

	/**
	 * @return the bool
	 */
	public boolean isBool() {
		return bool;
	}

	/**
	 * @param bool the bool to set
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the definitions
	 */
	public static Map<Integer, OverlayDefinition> getDefinitions() {
		return DEFINITIONS;
	}

	/**
	 * @return the rgb
	 */
	public int getRgb() {
		return rgb;
	}

	/**
	 * @param rgb the rgb to set
	 */
	public void setRgb(int rgb) {
		this.rgb = rgb;
	}
}