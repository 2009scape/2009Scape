package org.arios.workspace.node.npc;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.arios.cache.ServerStore;
import org.arios.cache.misc.DefinitionSize;

/**
 * Manages the drop tables of npcs.
 * @author Vexia
 *
 */
public class NPCDropManager {
	
	/**
	 * The mapping of npc drop tables.
	 */
	private static final Map<Integer, DropTable> DROP_TABLES = new HashMap<>();
	
	/**
	 * Constructs a new {@Code NPCDropManager} {@Code Object}
	 */
	public NPCDropManager() {
		/**
		 * empty.
		 */
	}
	
	/**
	 * Parses the npc drops.
	 */
	public static void parse() {
		ByteBuffer buffer = ServerStore.getArchive("npc_drops");
		int npcId = -1;
		while ((npcId = buffer.getShort()) != -1) {
			buffer.getShort();
			DropTable table = new DropTable();
			TableType type = null;
			int tableOpcode = -1;
			DROP_TABLES.put(npcId, table);
			while ((tableOpcode = buffer.get()) != 0) {
				switch (tableOpcode) {
				case 1:
					type = TableType.DEFAULT;
					break;
				case 2:
					type = TableType.CHARM;
					break;
				case 3:
					type = TableType.MAIN;
					break;
				}
				int itemId = -1;
				while ((itemId = buffer.getShort()) != -1) {
					table.addDrop(type, NPCDrop.create(itemId, buffer));
				}
			}
		}
	}
	
	/**
	 * Dumps the npc drops.
	 */
	public static void save() {
		int capacity = 10000000;
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		for (int i = 0; i < DefinitionSize.getNPCDefinitionsSize(); i++) {
			buffer.putShort((short) i); //Npc id.
			buffer.putShort((short) -1);
			for (Entry<TableType, List<NPCDrop>> entry : NPCDropManager.getTable(i).getDropTable().entrySet()) {
				buffer.put((byte) entry.getKey().opcode());
				for (NPCDrop drop : entry.getValue()) {
					drop.save(buffer);
				}
				buffer.putShort((short) -1);
			}
			buffer.put((byte) 0);
		}
		buffer.putShort((short) -1);
		ServerStore.setArchive("npc_drops", (ByteBuffer) buffer.flip(), false);
	}
	
	/**
	 * Gets an npc drop table.
	 * @param id the id. 
	 * @param type the type.
	 * @return the drop.
	 */
	public static NPCDrop[] getDropTable(int id, TableType type) {
		return getDrops(id, type).toArray(new NPCDrop[] {});
	}
	
	/**
	 * Gets the drop tables.
	 * @param id the id.
	 * @return the table.
	 */
	public Map<TableType, List<NPCDrop>> getDropTables(int id) {
		DropTable table = getTable(id);
		return table.getDropTable();
	}
	
	/**
	 * Creates a new table for an npc.
	 * @param id the id.
	 */
	public static void addTable(int id) {
		if (!DROP_TABLES.containsKey(id)) {
			DROP_TABLES.put(id, new DropTable());
		} else {
			System.out.println("Already had a drop table!");
		}
	}
	
	/**
	 * Gets the list of npc drops.
	 * @param id the id.
	 * @param type the type.
	 * @return the list of drops.
	 */
	public static List<NPCDrop> getDrops(int id, TableType type) {
		DropTable table = getTable(id);
		return table.getDrops(type);
	}
	
	/**
	 * Gets the drop table.
	 * @param id the id.
	 * @return the table
	 */
	public static DropTable getTable(int id) {
		DropTable table = getDropTables().get(id);
		if (table == null) {
			addTable(id);
			table = getDropTables().get(id);
		}
		return table;
	}
	
	/**
	 * Gets the dropTables.
	 * @return the dropTables.
	 */
	public static Map<Integer, DropTable> getDropTables() {
		return DROP_TABLES;
	}

	/**
	 * A drop table.
	 * @author Vexia
	 *
	 */
	public static class DropTable {
		
		/**
		 * The drop table.
		 */
		private final Map<TableType, List<NPCDrop>> dropTable = new HashMap<>();
		
		/**
		 * Constructs a new {@Code DropTable} {@Code Object}
		 */
		public DropTable() {
			for (TableType type : TableType.values()) {
				dropTable.put(type, new ArrayList<NPCDrop>());
			}
		}

		/**
		 * Adds a drop.
		 * @param type the type.
		 * @param drop the drop.
		 */
		public void addDrop(TableType type, NPCDrop drop) {
			List<NPCDrop> drops = dropTable.get(type);
			drops.add(drop);
		}
		
		/**
		 * Removes a drop.
		 * @param type the type.
		 * @param drop the drop.
		 */
		public void removeDrop(TableType type, NPCDrop drop) {
			List<NPCDrop> drops = dropTable.get(type);
			drops.remove(drop);
		}
		
		/**
		 * Gets a list of drops for a type.
		 * @param type the type.
		 * @return the type.
		 */
		public List<NPCDrop> getDrops(TableType type) {
			return dropTable.get(type);
		}

		/**
		 * Gets the dropTable.
		 * @return the dropTable.
		 */
		public Map<TableType, List<NPCDrop>> getDropTable() {
			return dropTable;
		}
		
	}

 }
