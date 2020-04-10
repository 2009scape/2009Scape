/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor.npc;

import com.alex.loaders.npcs.NPCDefinitions;
import com.alex.store.Store;
import com.alex.utils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Travis
 */
public class NPCListDumper {
    private static Store STORE;
    	public static void main(String[] args) throws IOException {
            STORE = new Store("C:/Users/Travis/Documents/rscd/data/");
		File file = new File("C:/Users/Travis/Documents/781 NPCList.txt");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.append("//Version = 781\n");
		writer.flush();
		for (int id = 0; id < (Utils.getNPCDefinitionsSize(STORE)-18433); id++) {
			NPCDefinitions def = NPCDefinitions.getNPCDefinition(STORE, id);
			writer.append(id + " - " + def.name);
			//writer.append("FORMAT1"+id+"FORMAT2"+def.name.replaceAll("`", "")+"FORMAT3\n");
			writer.newLine();
			System.out.println(id + " - " + def.name);
			writer.flush();
		}
		writer.close();
	}

}
