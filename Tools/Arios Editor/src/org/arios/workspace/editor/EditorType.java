package org.arios.workspace.editor;

import org.arios.workspace.node.item.ItemEditor;
import org.arios.workspace.node.item.shop.ShopEditor;
import org.arios.workspace.node.npc.NPCEditor;

/**
 * An editor type.
 * @author Vexia
 *
 */
public enum EditorType {
	NPC(new NPCEditor("NPC Editor")),
	ITEM(new ItemEditor("Item Editor")),
	SHOP(new ShopEditor("Shop Editor"));

	/**
	 * The tab.
	 */
	private final EditorTab tab;

	/**
	 * Constructs a new {@Code EditorType} {@Code Object}
	 * @param tab the tab.
	 */
	private EditorType(EditorTab tab) {
		this.tab = tab;
	}
	
	/**
	 * Initializes the editors.
	 */
	public static void init() {
		for (EditorType type : values()) {
			type.getTab().parse(); 
		}
	}
	
	/**
	 * Gets an editor type by the name.
	 * @param actionCommand the command.
 	 * @return the editor.
	 */
	public static EditorType forName(String actionCommand) {
		for (EditorType type : values()) {
			if (type.getTab().getName().equals(actionCommand) || ("Close " + type.getTab().getName()).equals(actionCommand)) {
				return type;
			}
		}
		return NPC;
	}

	/**
	 * Gets the tab.
	 * @return the tab.
	 */
	public EditorTab getTab() {
		return tab;
	}
	
}
