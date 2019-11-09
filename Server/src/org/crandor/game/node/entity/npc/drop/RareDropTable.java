package org.crandor.game.node.entity.npc.drop;

import org.crandor.cache.ServerStore;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.tools.RandomFunction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the rare drop table.
 * @author Emperor
 */
public final class RareDropTable {

	/**
	 * The item id of the item representing the rare drop table slot in a drop
	 * table.
	 */
	public static final int SLOT_ITEM_ID = 31;

	/**
	 * The table rarity ratio.
	 */
	private static int tableRarityRatio;

	/**
	 * The rare drop table.
	 */
	private static final List<ChanceItem> TABLE = new ArrayList<>();

	/**
	 * Initializes the rare drop table.
	 */
	public static void init() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/rare_drop_table.txt"));
			String s;
			while ((s = br.readLine()) != null) {
				if (s.contains(" //")) {
					s = s.substring(0, s.indexOf(" //"));
				}
				String[] arg = s.replaceAll(" - ", ";").split(";");
				int id = Integer.parseInt(arg[1]);
				int amount = 1;
				int amount2 = amount;
				System.out.println("Rare drop table: " + id);
				if (arg[2].contains("-")) {
					String[] amt = arg[2].split("-");
					amount = Integer.parseInt(amt[0]);
					amount2 = Integer.parseInt(amt[1]);
				} else {
					amount = Integer.parseInt(arg[2]);
				}
				DropFrequency df = DropFrequency.RARE;
				switch (arg[3].toLowerCase()) {
				case "common":
					df = DropFrequency.COMMON;
					break;
				case "uncommon":
					df = DropFrequency.UNCOMMON;
					break;
				case "rare":
					df = DropFrequency.RARE;
					break;
				case "very rare":
					df = DropFrequency.VERY_RARE;
					break;
				}
				TABLE.add(new ChanceItem(id, amount, amount2, 1000, 0.0, df));
			}
			br.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		int slot = 0;
		for (ChanceItem item : TABLE) {
			int rarity = 1000 / item.getDropFrequency().ordinal();
			item.setTableSlot(slot | ((slot += rarity) << 16));
		}
		tableRarityRatio = (int) (slot * 1.33);
	}

	/**
	 * Parses the table.
	 */
	public static void parse() {
		ByteBuffer buffer = ServerStore.getArchive("rare_drop_table");
		int size = buffer.get();
		for (int i = 0; i < size; i++) {
			TABLE.add(new ChanceItem(buffer.getInt(), buffer.getInt(), buffer.getInt(), 1000, 0.0, DropFrequency.values()[buffer.get()]));
		}
		int slot = 0;
		for (ChanceItem item : TABLE) {
			int rarity = 1000 / item.getDropFrequency().ordinal();
			if (item.getId() == 985 || item.getId() == 987) {
				rarity = 9900;
			}
			item.setTableSlot(slot | ((slot += rarity) << 16));
		}
		tableRarityRatio = (int) (slot);
	}

	/**
	 * Writes the table to the buffer.
	 */
	public static void write() {
		final ByteBuffer buffer = ByteBuffer.allocate(100000);
		buffer.put((byte) TABLE.size());
		for (ChanceItem item : TABLE) {
			buffer.putInt(item.getId());
			buffer.putInt(item.getMinimumAmount());
			buffer.putInt(item.getMaximumAmount());
			buffer.put((byte) item.getDropFrequency().ordinal());
		}
		buffer.flip();
		ServerStore.setArchive("rare_drop_table", buffer, false);
	}

	/**
	 * Retrieves a drop from the rare drop table.
	 * @return The chance item to drop (<b>can be null!</b>).
	 */
	public static ChanceItem retrieve() {
		int slot = RandomFunction.random(tableRarityRatio);
		for (ChanceItem item : TABLE) {
			if ((item.getTableSlot() & 0xFFFF) <= slot && (item.getTableSlot() >> 16) > slot) {
				return item;
			}
		}
		return null;
	}
}