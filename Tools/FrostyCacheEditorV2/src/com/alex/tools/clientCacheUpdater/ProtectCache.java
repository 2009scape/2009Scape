package com.alex.tools.clientCacheUpdater;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import com.alex.store.Index;
import com.alex.store.Store;

public class ProtectCache {

	
	
	public static void main(String[] args) throws IOException {
		boolean encryptMaps = true;
		boolean encryptTables = false;
		Store cache = new Store("718/cacheEncrypted/");
		
		
		if(encryptMaps) {
			Store rscache = new Store("718/rscache/");
			Index index = cache.getIndexes()[5];
			Index rsIndex = rscache.getIndexes()[5];
			for(int regionId = 0; regionId < 25000; regionId++) {
				int regionX = (regionId >> 8) * 64;
				int regionY = (regionId & 0xff) * 64;	
				
				String name;
				int[] keys = null;
				name = "l"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
				if(rsIndex.getFile(rsIndex.getArchiveId(name), 0) == null) {//not backgground file
					int archiveId = index.getArchiveId(name);
					if(archiveId != -1) {
						keys = writeKeys(regionId);
						if(!index.encryptArchive(archiveId, null, keys, false, false))
							throw new RuntimeException("FAIL");
					}
				}
				name = "ul"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
				if(rsIndex.getFile(rsIndex.getArchiveId(name), 0) == null) {//not backgground file
					int archiveId = index.getArchiveId(name);
					if(archiveId != -1) {
						if(keys == null)
							keys = writeKeys(regionId);
						if(!index.encryptArchive(archiveId, null, keys, false, false))
							throw new RuntimeException("FAIL");
					}
				}
			}
			index.rewriteTable();
		}
		
		if(encryptTables) {
			int[][] keys = new int[cache.getIndexes().length][];
			for(int i = 0; i < keys.length; i++) {
				 keys[i] = generateKeys();
				 if(cache.getIndexes()[i] == null)
					 continue;
				 System.out.println("encrypting idx table: "+i);
				 cache.getIndexes()[i].setKeys(keys[i]);
				 cache.getIndexes()[i].rewriteTable();
			}
			for(int i = 0; i < keys.length; i++)
				System.out.println(Arrays.toString(keys[i]));
		}

	}
	
	
	public static int[] generateKeys() {
		int[] keys = new int[4];
		for (int index = 0; index < keys.length; index++) 
			keys[index] = new Random().nextInt();
		return keys;
	}
	
	public static int[] writeKeys(int regionId) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("718/maps/unpacked/"+regionId+".txt"));
		int[] keys = generateKeys();
		for (int index = 0; index < keys.length; index++) {
			writer.write("" + keys[index]);
			writer.newLine();
			writer.flush();
		}
		System.out.println("Region: "+regionId+", "+Arrays.toString(keys));
		return keys;
		
	}

}
