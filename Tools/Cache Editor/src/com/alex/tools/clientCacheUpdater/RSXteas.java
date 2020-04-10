package com.alex.tools.clientCacheUpdater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public final class RSXteas {

	public final static HashMap<Integer, int[]> mapContainersXteas = new HashMap<Integer, int[]>();
	
	
	public static final int[] getXteas(int regionId) {
		return mapContainersXteas.get(regionId);
	}
	public static void init() {
			loadUnpackedXteas(468);
	}
	
	
	
	public static final void loadUnpackedXteas(int revision) {
		try {
			File unpacked = new File("xteas" + revision + "/");
			File[] xteasFiles = unpacked.listFiles();
			for (File region : xteasFiles) {
				String name = region.getName();
				if (!name.contains(".txt")) {
					region.delete();
					continue;
				}
				int regionId = -1;
				try {
					regionId = Short.parseShort(name.replace(".txt", ""));
				} catch (Throwable t) {
					continue;
				}
				if (regionId <= 0) {
					region.delete();
					continue;
				}
				BufferedReader in = new BufferedReader(new FileReader(region));
				final int[] xteas = new int[4];
				boolean delete = true;
				for (int index = 0; index < 4; index++) {
					xteas[index] = Integer.parseInt(in.readLine());
					if (xteas[index] != 0) {
						delete = false;
					}
				}
				in.close();
				if (delete) {
					region.delete();
					continue;
				}
				mapContainersXteas.put(regionId, xteas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private RSXteas() {
		
	}
	
}
