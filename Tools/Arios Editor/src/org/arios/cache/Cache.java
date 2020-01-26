package org.arios.cache;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import org.arios.workspace.WorkSpace;

/**
 * A cache reader.
 * @author Emperor
 * @author Dragonkk
 */
public final class Cache {

	/**
	 * The cache file manager.
	 */
	private static CacheFileManager[] cacheFileManagers;
	
	/**
	 * The container cache file informer.
	 */
	private static CacheFile referenceFile;
	
	/**
	 * Construct a new instance.
	 */
	private Cache(String location) {
		try {
			init(location);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the cache reader.
	 * @param path The cache path.
	 * @throws Throwable When an exception occurs.
	 */
	public static final void init(String path) throws Throwable {
		byte[] cacheFileBuffer = new byte[520];
		RandomAccessFile containersInformFile = new RandomAccessFile(path + "/main_file_cache.idx255", "r");
		RandomAccessFile dataFile =	new RandomAccessFile(path + "/main_file_cache.dat2", "r");
		referenceFile = new CacheFile(255, containersInformFile, dataFile, 500000, cacheFileBuffer);
		int length = (int) (containersInformFile.length() / 6);
		cacheFileManagers = new CacheFileManager[length];
		for (int i = 0; i < length; i++) {
			File f = new File(path + "/main_file_cache.idx" + i);
			if (f.exists() && f.length() > 0) {
				cacheFileManagers[i] = new CacheFileManager(new CacheFile(i, new RandomAccessFile(f, "r"), dataFile, 1000000, cacheFileBuffer), true);
				if (cacheFileManagers[i].getInformation() == null) {
					System.out.println("Error loading cache index " + i + ": no information.");
					cacheFileManagers[i] = null;
				}
			}
		}
	}
	
	/**
	 * Initaizes the cache.
	 * @throws Throwable  when an exception occurs.
	 */
	public static final void init() throws Throwable {
		init(WorkSpace.getWorkSpace().getSettings().getCachePath());
	}
	
	/**
	 * Gets the archive buffer for the grab requests.
	 * @param index The index id.
	 * @param archive The archive id.
	 * @param priority The priority.
	 * @param encryptionValue The current encryption value.
	 * @return The byte buffer.
	 */
	public static ByteBuffer getArchiveData(int index, int archive, boolean priority, int encryptionValue) {
		byte[] data = index == 255 ? 
				referenceFile.getContainerData(archive) : 
					cacheFileManagers[index].getCacheFile().getContainerData(archive);
		if (data == null) {
			return null;
		}
		int compression = data[0] & 0xff;
		int length = ((data[1] & 0xff) << 24) + ((data[2] & 0xff) << 16) + ((data[3] & 0xff) << 8) + (data[4] & 0xff);
		int settings = compression;
		if (!priority) {
			settings |= 0x80;
		}
		int realLength = compression != 0 ? length + 4 : length;
        ByteBuffer buffer = ByteBuffer.allocate((realLength + 5) + (realLength / 512) + 10);
		buffer.put((byte) index);
		buffer.putShort((short) archive);
		buffer.put((byte) settings);
		buffer.putInt(length);
		for (int i = 5; i < realLength + 5; i++) {
			if (buffer.position() % 512 == 0) {
				buffer.put((byte) 255);
			}
			buffer.put(data[i]);
		}
		if (encryptionValue != 0) {
			for (int i = 0; i < buffer.position(); i++) {
				buffer.put(i, (byte) (buffer.get(i) ^ encryptionValue));
			}
		}
		buffer.flip();
		return buffer;
	}
	
	/**
	 * Generate the reference data for the cache files.
	 * @return The reference data byte array.
	 */
	public static final byte[] generateReferenceData() {
		ByteBuffer buffer = ByteBuffer.allocate(cacheFileManagers.length * 8);
		for (int index = 0; index < cacheFileManagers.length; index++) {
			if (cacheFileManagers[index] == null) {
				buffer.putInt(index == 24 ? 609698396 : 0);
				buffer.putInt(0);
				continue;
			}
			buffer.putInt(cacheFileManagers[index].getInformation().getInformationContainer().getCrc());
			buffer.putInt(cacheFileManagers[index].getInformation().getRevision());
		}
		return buffer.array();
	}
	
	/**
	 * Get the cache file managers.
	 * @return The cache file managers.
	 */
	public static final CacheFileManager[] getIndexes() {
		return cacheFileManagers;
	}
	
	/**
	 * Get the container cache file informer.
	 * @return The container cache file informer.
	 */
	public static final CacheFile getReferenceFile() {
		return referenceFile;
	}
	
}