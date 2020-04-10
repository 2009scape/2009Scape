package org.arios.workspace.node.npc;

/**
 * A table type.
 * @author VExia
 */
public enum TableType {
	DEFAULT,
	CHARM,
	MAIN;
	
	/**
	 * Its opcode.
	 * @return the opcode.
	 */
	public int opcode() {
		return ordinal() + 1;
	}
}
