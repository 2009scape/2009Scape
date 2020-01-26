package alex.cache.loaders;

import java.util.HashMap;
import java.util.Map;

import com.alex.io.InputStream;
import com.alex.store.Store;

/**
 * Handles config definition reading.
 * @author Emperor
 *
 */
public final class ConfigFileDefinition {

	/**
	 * The config definitions mapping.
	 */
	private static final Map<Integer, ConfigFileDefinition> MAPPING = new HashMap<>();
	
	/**
	 * The bit size flags.
	 */
	private static final int[] BITS = new int[32];
	
	/**
	 * The file id.
	 */
	private final int id;

	/**
	 * The config id.
	 */
	private int configId;
	
	/**
	 * The bit shift amount.
	 */
	private int bitShift;
	
	/**
	 * The bit amount.
	 */
	private int bitSize;
	
	/**
	 * Constructs a new {@code ConfigFileDefinition} {@code Object}.
	 * @param id The file id.
	 */
	public ConfigFileDefinition(int id) {
		this.id = id;
	}

	/**
	 * Initializes the bit flags.
	 */
	static {
		int flag = 2;
		for (int i = 0; i < 32; i++) {
			BITS[i] = flag - 1;
			flag += flag;
		}
	}
	
	/**
	 * Gets the config file definitions for the given file id.
	 * @param id The file id.
	 * @return The definition.
	 */
	public static ConfigFileDefinition forId(int id, Store store) {
		ConfigFileDefinition def = MAPPING.get(id);
		if (def != null) {
			return def;
		}
		byte[] bs = store.getIndexes()[22].getFile(id >>> 1416501898, id & 0x3ff);
		if (bs == null) {
			return null;
		}
		def = new ConfigFileDefinition(id);
		InputStream buffer = new InputStream(bs);
		int opcode = 0;
		while ((opcode = buffer.readByte()) != 0) {
			if (opcode == 1) {
				def.configId = buffer.readShort();
				def.bitShift = buffer.readByte();
				def.bitSize = buffer.readByte();
			}
		}
		return def;
	}
	
	/**
	 * Gets the mapping.
	 * @return The mapping.
	 */
	public static Map<Integer, ConfigFileDefinition> getMapping() {
		return MAPPING;
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Gets the configId.
	 * @return The configId.
	 */
	public int getConfigId() {
		return configId;
	}

	/**
	 * Gets the bitShift.
	 * @return The bitShift.
	 */
	public int getBitShift() {
		return bitShift;
	}

	/**
	 * Gets the bitSize.
	 * @return The bitSize.
	 */
	public int getBitSize() {
		return bitSize;
	}
}