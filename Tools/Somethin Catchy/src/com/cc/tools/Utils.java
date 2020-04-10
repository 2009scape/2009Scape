package com.cc.tools;

import com.alex.store.Store;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author travis
 */
public class Utils {
	@SuppressWarnings("resource")
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
		}
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		is.close();
		return bytes;
	}
        
        public static int packCustomModel(Store cache, byte[] data) {
		int archiveId = cache.getIndexes()[7].getLastArchiveId()+1;
		if(cache.getIndexes()[7].putFile(archiveId, 0, data))
				return archiveId;
		System.out.println("Failing packing model "+archiveId);
		return -1;
	}
}
