package org.crandor.tools;

import org.crandor.cache.Cache;
import org.crandor.cache.def.impl.ItemDefinition;


public class Test {
	
	//@SuppressWarnings("unused")
	public static void main(String...args) {
		Cache.init();
		/*while(true) {
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
		    System.gc();
            System.out.println("ur gay");
        }*/
	/*	for (int i = 0; i < Cache.getNPCDefinitionsSize(); i++) {
			if (NPCDefinition.forId(i).getName().toLowerCase().contains("beaver")) {
				//System.out.println(i + ", " + NPCDefinition.forId(i).getName());
			}
		}
		System.out.println("---Objects----");
		for (int i = 0; i < Cache.getObjectDefinitionsSize(); i++) {
			ObjectDefinition def = ObjectDefinition.forId(i);
			if (def.getName().toLowerCase().contains("snow")) {
				//System.out.println(i + ", " + def.getName() + ", " + Arrays.toString(def.getOptions()));
			}
		}*/
		System.out.println("------Items-------");
		int count = 0;
		for (int i = 0; i < Cache.getItemDefinitionsSize(); i++) {

			ItemDefinition def = ItemDefinition.forId(i);
			if (def.getName().equalsIgnoreCase("null"))
			    continue;
			if (def.getName().contains("ironman") || def.getName().startsWith("Ironman"))
			    continue;
			if (!def.isUnnoted())
                continue;
			if (def.isMembersOnly())
                continue;
			if (def.hasWearAction())
                System.out.println("new Item(" + def.getId() + "), //" + def.getName() + "\n");
//			    System.out.println(def.getName() + ", " + i + " - " + !def.isUnnoted() + " - COUNT = "  + count++);
		}
	}
}
