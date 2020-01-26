package org.arios.workspace.node.npc;

/**
 * Represents the different types of drop frequency.
 * @author Emperor
 * 
 */
public enum DropFrequency {

	/**
	 * This gets dropped all the time.
	 */
	ALWAYS,

	/**
	 * This gets commonly dropped.
	 */
	COMMON,

	/**
	 * This drop is uncommon.
	 */
	UNCOMMON,

	/**
	 * This gets rarely dropped.
	 */
	RARE,

	/**
	 * This gets very rarely dropped.
	 */
	VERY_RARE;

	
	/**
	 * Gets the frequency by the name.
	 * @param name the name.
	 * @return the value.
	 */
	public static DropFrequency forName(String name) {
		for (DropFrequency n : values()) {
			if (n.name().equals(name)) {
				return n;
			}
		}
		return null;
	}
	
}