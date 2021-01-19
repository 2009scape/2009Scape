package core.cache.def.impl;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import core.cache.Cache;
import core.game.Varbit;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;

/**
 * Handles config definition reading.
 * @author Emperor
 */
public final class VarbitDefinition {

	/**
	 * The config definitions mapping.
	 */
	private static final Map<Integer, VarbitDefinition> MAPPING = new HashMap<>();

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
	public VarbitDefinition(int id) {
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
	public static VarbitDefinition forObjectID(int id) {
		return forId(id,10);
	}

	public static VarbitDefinition forNPCID(int id){
		return forId(id,10);
	}

	public static VarbitDefinition forItemID(int id){
		return forId(id,30);
	}

	public static VarbitDefinition forId(int id, int shiftAmount){
		/*VarbitDefinition def = MAPPING.get(id);
		if (def != null) {
			return def;
		}*/
		VarbitDefinition def;
		def = new VarbitDefinition(id);
		byte[] bs = Cache.getIndexes()[22].getFileData(id >>> 10, id & 0x3ff);
		if (bs != null) {
			ByteBuffer buffer = ByteBuffer.wrap(bs);
			int opcode = 0;
			while ((opcode = buffer.get() & 0xFF) != 0) {
				if (opcode == 1) {
					def.configId = buffer.getShort() & 0xFFFF;
					def.bitShift = buffer.get() & 0xFF;
					def.bitSize = buffer.get() & 0xFF;
				}
			}
		}
		MAPPING.put(id, def);
		return def;
	}

	public static void main(String... args) throws Throwable {
		GameWorld.prompt(false);
		for (int i = 0; i < 15000; i++) {
			VarbitDefinition def = forObjectID(i);
			if (def != null && def.configId == 33) {
				System.out.println("Config file [id=" + i + ", shift=" + def.bitShift + "]!");
			}
		}
	}

	/**
	 * Gets the current config value for this file.
	 * @param player The player.
	 * @return The config value.
	 */
	public int getValue(Player player) {
		Varbit bit = player.varpManager.get(getConfigId()).getVarbit(getBitShift());
		if(bit != null){
			return bit.getValue();
		}
		int size = BITS[bitSize - bitShift];
		return size & player.getConfigManager().get(configId) >> bitShift;
	}

	/**
	 * Gets the mapping.
	 * @return The mapping.
	 */
	public static Map<Integer, VarbitDefinition> getMapping() {
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

	@Override
	public String toString() {
		return "ConfigFileDefinition [id=" + id + ", configId=" + configId + ", bitShift=" + bitShift + ", bitSize=" + bitSize + "]";
	}
}