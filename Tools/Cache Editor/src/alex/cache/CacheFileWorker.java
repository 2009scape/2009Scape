package alex.cache;

import alex.CacheLoader;
import alex.util.Methods;

public class CacheFileWorker {

	private CacheFile cache;
	private int id;
	private ReferenceTable referenceTable;
	private int tableVersion;
	public CacheFileWorker(int id) {
		cache = new CacheFile(id, CacheLoader.dataFile, CacheLoader.indexFiles[id], 0xf4240);
		this.id = id;
		byte[] buffer = CacheLoader.getReferenceCache().readFile(id);
		tableVersion = (buffer[buffer.length - 2] << 8 & 0xff00) + (buffer[-1 + buffer.length] & 0xff);
		referenceTable = new ReferenceTable(buffer);
	}

	public byte[] getFileBuffer(int file) {
		return cache.readFile(file);
	}

	public ReferenceTable getReferenceTable() {
		return referenceTable;
	}
	
	public int generateTableFileVersion() {
		tableVersion++;
		return tableVersion;
	}
	
	public int getTableFileVersion() {
		return tableVersion;
	}
	
	public boolean putFile(int fileId, int compression, byte[] data, int version) {
		return putFile(fileId, compression, data, version, null);
	}
	
	public boolean putFile(int fileId, int compression, byte[] data, int version, int[] keys) {
		return cache.writeFile(fileId, compression, data, version, keys);
	}
}
