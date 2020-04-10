/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editor.item;

import com.alex.loaders.items.ItemDefinitions;
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
public class ItemListDumper {
    private static Store STORE;
    	public static void main(String[] args) {
		try {
                    STORE = new Store("C:/Users/Travis/Documents/rscd/data/");
			new ItemListDumper();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ItemListDumper() throws IOException {
		File file = new File("C:/Users/Travis/Documents/781 ItemList.txt"); //= new File("information/itemlist.txt");
		if (file.exists())
			file.delete();
		else
			file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		//writer.append("//Version = 709\n");
		writer.append("//Version = 781\n");
		writer.flush();
		for (int id = 0; id < Utils.getItemDefinitionsSize(STORE); id++) {
			ItemDefinitions def = ItemDefinitions.getItemDefinition(STORE, id);
			if (def.getName().equals("null"))
				continue;
			writer.append(id+" - "+def.getName());
			writer.newLine();
			writer.flush();
		}
		writer.close();
	}

	  public static int convertInt(String str) {
	    try {
	      int i = Integer.parseInt(str);
	      return i; } catch (NumberFormatException e) {
	    }
	    return 0;
	  }
}
