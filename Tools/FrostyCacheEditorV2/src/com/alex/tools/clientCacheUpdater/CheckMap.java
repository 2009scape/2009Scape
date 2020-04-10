package com.alex.tools.clientCacheUpdater;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

public class CheckMap {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	/*	OriginalXteas.init();
		int count = 1;
		Store cache = new Store("cache667_2/", false, CACHE_TABLE_KEYS);
		Store mapsFrom = new Store("newCache/", false);
		for(int regionId = 0; regionId < 30000; regionId++) {
			int regionX = (regionId >> 8) * 64;
			int regionY = (regionId & 0xff) * 64;	
			String name = "l"
					+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			int archiveId = cache.getIndexes()[5].getArchiveId(name);
			if(archiveId != -1)
				continue;
			int archiveId2 = mapsFrom.getIndexes()[5].getArchiveId(name);
			if(archiveId2 == -1)
				continue;
			boolean pass = passArchive(regionId, mapsFrom, cache, name, 5, null, OriginalXteas.getXteas(regionId));
			if(pass) {
				System.out.println("count: "+(count++)+", region: "+regionId);
			}
			//else
				
		}
		cache.getIndexes()[5].rewriteTable();
		cache.getIndexes()[5].resetCachedFiles();*/
		
		Store cache = new Store("cache667_2/", false, null);
		double land = 0;
		double map = 0;
		for(int regionId = 0; regionId < 30000; regionId++) {
			int regionX = (regionId >> 8) * 64;
			int regionY = (regionId & 0xff) * 64;	
			String name1 = "l"
					+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			String name2 = "m"
					+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			if(cache.getIndexes()[5].getArchiveId(name1) != -1)
				land ++;
			if(cache.getIndexes()[5].getArchiveId(name2) != -1)
				map ++;
		}
		System.out.println("land: "+land+", newMaps: "+map);
		double perc = land * 100 / map ;
		System.out.println( perc + "% complete!");
	}
	
	
	public static boolean passArchive(int regionId, Store store1, Store store2, String nameHash, int i, int[] keys1, int[] keys2) {
		if(keys2 != null)
			System.out.println(keys2);
		int archiveId = store1.getIndexes()[i].getArchiveId(nameHash);
		if(archiveId == -1)
			return false;
		int oldArchiveId = store2.getIndexes()[i].getArchiveId(nameHash);
		if(oldArchiveId == -1)
			oldArchiveId = store2.getIndexes()[i].getLastArchiveId()+1;
		byte[] data = store1.getIndexes()[i].getFile(archiveId, 0, keys1);
		if(data == null) 
			return false;
		try {
		boolean pass = store2.getIndexes()[i].putFile(oldArchiveId, 0, Constants.GZIP_COMPRESSION, data, keys2, false, false, Utils.getNameHash(nameHash), -1);
		if(!pass)
			return false;
			int[] keys = writeKeys(regionId);
		return store2.getIndexes()[i].encryptArchive(oldArchiveId, keys2, keys, false, false);
		}catch(Error e) {
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	
	
	public static int[] generateKeys() {
		int[] keys = new int[4];
		for (int index = 0; index < keys.length; index++) 
			keys[index] = new Random().nextInt();
		return keys;
		
	}
	
	public static int[] writeKeys(int regionId) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("cache667_protected/keys/"+regionId+".txt"));
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
