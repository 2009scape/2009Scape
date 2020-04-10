package org.arios;

import org.arios.cache.def.impl.ItemDefinition;
import org.arios.workspace.WorkLogger;
import org.arios.workspace.WorkSpace;
import org.arios.workspace.editor.EditorType;
import org.arios.workspace.node.Node;

/**
 * The class used to run the program.
 * @author Vexias
 *
 */
public final class Runner {

	/**
	 * The main method.
	 * @param args the arguments.
	 * @throws Throwable if thrown.
	 */
	public static void main(String...args) throws Throwable {
		System.setOut(new WorkLogger(System.out));
		WorkSpace.getWorkSpace().init();
		//setNonSpawnables();
	}

	/**
	 * Sets the non spawnable.
	 */
	public static void setNonSpawnables() {
		String[] names = new String[] {"godsword", "verac", "ahrim", "dharock", "torag", "guthin", "karil", "armadyl", "bandos", "bandos godsword", "armadyl godsword", "zamorak godsword", "saradomin godsword", "saradomin hilt", "zamorak hilt", "bandos hilt", "armadyl hilt", "3rd age", "clue scroll", "casket"};
		for (Node<?> i : EditorType.ITEM.getTab().getNodes().values()) {
			if ((boolean)i.getConfigValue("spawnable") == false) {
				if (i.getName().equals("Clue scroll") || i.getName().equals("Casket")) {
					continue;
				}
				//System.err.println("" + i.toString() + " is not spawnable!");
				continue;
			} 
			if (!(((ItemDefinition) i.getDefinition()).isUnnoted())) {
				continue;
			}
			if (i.getName().equals("Whoopsie")) {
				continue;
			}
			if (i.getName().startsWith("D'hide body") || i.getName().startsWith("Elegant") || i.getName().contains("ele'")|| i.getName().endsWith("scroll") || i.getName().contains("pouch") || i.getName().endsWith("charm") || i.getName().startsWith("Pouch") || i.getName().equals("Zamorak robe") || i.getName().equals("Cape of legends") || i.getName().startsWith("Rock-shell") || i.getName().startsWith("Yak-hide") || i.getName().equals("Chompy bird hat") || i.getName().equals("Hat") || i.getName().equals("Boots") || i.getName().equals("Robe bottoms") || i.getName().equals("Robe top") || i.getName().toLowerCase().contains("heraldic") || i.getName().startsWith("D'hide") || (i.getName().equals("Gloves") && i.getId() < 7461) || i.getName().startsWith("Ghostly") || i.getName().startsWith("Fremennik")) {
				i.setConfig("trade-override", true);
			}
			if (!((ItemDefinition)i.getDefinition()).getConfiguration("tradeable", false) && ((ItemDefinition)i.getDefinition()).hasAction("wear") && !((ItemDefinition)i.getDefinition()).getConfiguration("trade-override", false)) {
				//System.err.println("Item=" + i);
			}
			for (String name : names) {
				if (i.getName().toLowerCase().startsWith(name)) {
					i.setConfig("spawnable", false);
					System.err.println("Setting non spawnable for - " + i.toString() + "!");
					break;
				}
			}
		}
	}

}