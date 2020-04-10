package com.alex.tools.clientCacheUpdater;

import java.io.IOException;

import com.alex.store.Index;
import com.alex.store.Store;
import com.alex.utils.Constants;

public class CopyCache {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Store cache = new Store("718/cache/");
		Store newCache = new Store("718/cacheCleaned/");
		for(int i = 0; i < cache.getIndexes().length; i++) {
			Index index = cache.getIndexes()[i];
			newCache.addIndex(index.getTable().isNamed(), index.getTable().usesWhirpool(), Constants.GZIP_COMPRESSION);
			newCache.getIndexes()[i].packIndex(cache);
			newCache.getIndexes()[i].getTable().setRevision(cache.getIndexes()[i].getTable().getRevision());
			newCache.getIndexes()[i].rewriteTable();
		}
	}

}
