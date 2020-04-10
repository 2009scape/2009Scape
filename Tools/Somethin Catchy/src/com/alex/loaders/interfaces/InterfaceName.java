package com.alex.loaders.interfaces;

import java.io.IOException;

import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

public class InterfaceName {

	public static final char[] VALID_CHARS = {  'a', 'b', 'c', 'd', 'e',
		'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	
	public static void printAllCombinations4Letters(){     
    }  
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Store rscache = new Store("cache697/", false);
		
		System.out.println( rscache.getIndexes()[Constants.INTERFACE_DEFINITIONS_INDEX].getTable().isNamed());
		
		System.out.println(rscache.getIndexes()[Constants.INTERFACE_DEFINITIONS_INDEX].getArchiveId("chat"));
		System.out.println(Utils.getNameHash("price checker"));
	/*	System.out.println(Utils.getNameHash("prayer"));
		System.out.println(Utils.unhash(Utils.getNameHash("t")));*/
		//System.out.println(Utils.getNameHash("prayer"));
		
	/*	int hash = rscache.getIndexes()[Constants.INTERFACE_DEFINITIONS_INDEX].getTable().getArchives()[884].getNameHash();
		for(char l1 : VALID_CHARS) {
			System.out.println(l1);
			for(char l2 : VALID_CHARS) {
				for(char l3 : VALID_CHARS) {

					for(char l4 : VALID_CHARS) {
						for(char l5 : VALID_CHARS) {
							for(char l6 : VALID_CHARS) {
							String name = new String(new char[] {l1, l2, l3, l4,l5, l6});
							if(Utils.getNameHash(name) == hash)
								System.out.println(name);
							}
						}
					}
				}
			}
		}*/
		
	}
	
	
	
}
