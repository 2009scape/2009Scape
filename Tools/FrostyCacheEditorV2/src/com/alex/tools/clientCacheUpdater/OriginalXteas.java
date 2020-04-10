package com.alex.tools.clientCacheUpdater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public final class OriginalXteas {

	public final static HashMap<Integer, int[]> mapContainersXteas = new HashMap<Integer, int[]>();
	
	
	public static final int[] getXteas(int regionId) {
		return mapContainersXteas.get(regionId);
	}
	public static void init() {
			loadUnpackedXteas();
	}
	
	
	public static final void delete() {
		
	}
	
	public static final void loadUnpackedXteas() {
		try {
			File unpacked = new File("cache667_protected/keys");
			File[] xteasFiles = unpacked.listFiles();
			for (File region : xteasFiles) {
				String name = region.getName();
				if (!name.contains(".txt")) {
					region.delete();
					continue;
				}
				int regionId = Short.parseShort(name.replace(".txt", ""));
				if(regionId <= 0) {
					region.delete();
					continue;
				}
				BufferedReader in = new BufferedReader(new FileReader(region));
				final int[] xteas = new int[4];
				for (int index = 0; index < 4; index++) {
					xteas[index] = Integer.parseInt(in.readLine());
				}
				mapContainersXteas.put(regionId, xteas);
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private OriginalXteas() {
		
	}
	
}
