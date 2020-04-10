package com.alex.tools.clientCacheUpdater;

import java.io.IOException;
import java.util.Random;

import com.alex.store.Archive;
import com.alex.store.ArchiveReference;
import com.alex.store.Index;
import com.alex.store.Store;

public class ArchiveValidation {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Store rscache = new Store("718/cache/");
		for(int i = 0; i < rscache.getIndexes().length; i++) {
			if(i == 5)
				continue;
			Index index = rscache.getIndexes()[i];
			System.out.println("checking index: "+i);
			for(int archiveId : index.getTable().getValidArchiveIds()) {
				Archive archive = index.getArchive(archiveId);
				if(archive == null) {
					System.out.println("Missing:: "+i+", "+archiveId);
					continue;
				}
				ArchiveReference reference = index.getTable().getArchives()[archiveId];
				if(archive.getRevision() != reference.getRevision() ) {
					System.out.println("corrupted: "+i+", "+archiveId);
				}
			}
		}
	}
	
	public static int[] generateKeys() {
		int[] keys = new int[4];
		for (int index = 0; index < keys.length; index++) 
			keys[index] = new Random().nextInt();
		return keys;
	}

}
