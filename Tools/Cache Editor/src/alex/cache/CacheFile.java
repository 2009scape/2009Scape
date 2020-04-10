package alex.cache;

import java.io.EOFException;
import java.io.IOException;

import alex.io.Stream;
import alex.util.Methods;

public class CacheFile {

	public static byte cacheFileBuffer[] = new byte[520];

	private int cacheId;
	private SeekableFile dataFile;
	private SeekableFile indexFile;
	private int maxLength;

	public CacheFile(int cacheId, SeekableFile dataFile, SeekableFile indexFile, int length) {
		this.indexFile = indexFile;
		this.maxLength = length;
		this.dataFile = dataFile;
		this.cacheId = cacheId;
	}

	public final byte[] readFile(int file) {
		synchronized (dataFile) {
			try {
				if (indexFile.getFileLength() < (6 * file + 6)) {
					return null;
				}
				indexFile.seek(6 * file);
				indexFile.read(CacheFile.cacheFileBuffer, 0, 6);
				int fileSize = (CacheFile.cacheFileBuffer[2] & 0xff)
						+ (((0xff & CacheFile.cacheFileBuffer[0]) << 16) + (CacheFile.cacheFileBuffer[1] << 8 & 0xff00));
				int sector = ((CacheFile.cacheFileBuffer[3] & 0xff) << 16)
						- (-(0xff00 & CacheFile.cacheFileBuffer[4] << 8) - (CacheFile.cacheFileBuffer[5] & 0xff));
				if (fileSize < 0 || fileSize > maxLength) {
					return null;
				}
				if (sector <= 0
						|| dataFile.getFileLength() / 520L < sector) {
					return null;
				}
				byte buffer[] = new byte[fileSize];
				int dataRead = 0;
				int part = 0;
				while (fileSize > dataRead) {
					if (sector == 0) {
						return null;
					}
					dataFile.seek(520 * sector);
					int dataToRead = fileSize - dataRead;
					if (dataToRead > 512) {
						dataToRead = 512;
					}
					dataFile.read(CacheFile.cacheFileBuffer, 0, 8 + dataToRead);
					int currentFile = (0xff & CacheFile.cacheFileBuffer[1])
							+ (0xff00 & CacheFile.cacheFileBuffer[0] << 8);
					int currentPart = ((CacheFile.cacheFileBuffer[2] & 0xff) << 8)
							+ (0xff & CacheFile.cacheFileBuffer[3]);
					int nextSector = (CacheFile.cacheFileBuffer[6] & 0xff)
							+ (0xff00 & CacheFile.cacheFileBuffer[5] << 8)
							+ ((0xff & CacheFile.cacheFileBuffer[4]) << 16);
					int currentCache = CacheFile.cacheFileBuffer[7] & 0xff;
					if (file != currentFile || currentPart != part
							|| cacheId != currentCache) {
						return null;
					}
					if (nextSector < 0
							|| (dataFile.getFileLength() / 520L) < nextSector) {
						return null;
					}
					for (int l2 = 0; dataToRead > l2; l2++) {
						buffer[dataRead++] = CacheFile.cacheFileBuffer[8 + l2];
					}

					part++;
					sector = nextSector;
				}
				return buffer;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public final String toString() {
		return "Cache:" + cacheId;
	}


	public boolean writeFile(int file, int compression, byte[] data, int version, int[] keys) {
		byte[] readyFileData = Methods.packContainer(compression, data);
		if (keys != null && (~keys[0] != -1 || keys[1] != 0 || keys[2] != 0 || ~keys[3] != -1)) {
			Stream stream = new Stream(readyFileData);
			stream.encodeXTEA(keys);
		}
		readyFileData[readyFileData.length - 2] = (byte) (version >>> 8);
		readyFileData[readyFileData.length - 1] = (byte) version;
		return writeFile(file, readyFileData, readyFileData.length);
	}
	
	private final boolean writeFile(int file, byte buffer[], int fileSize) {
		synchronized (dataFile) {
			if (fileSize < 0 || maxLength < fileSize) {
				throw new IllegalArgumentException();
			}
			boolean succ = writeFile(file, buffer, fileSize, true);
			if (!succ) {
				succ = writeFile(file, buffer, fileSize, false);
			}
			return succ;
		}
	}

	private final boolean writeFile(int file, byte buffer[], int fileSize,
			boolean exists) {
		synchronized (dataFile) {
			try {
				int sector;
				if (!exists) {
					sector = (int) ((dataFile.getFileLength() + 519L) / 520L);
					if (sector == 0) {
						sector = 1;
					}
				} else {
					if ((6 * file + 6) > indexFile.getFileLength()) {
						return false;
					}
					indexFile.seek(file * 6);
					indexFile.read(CacheFile.cacheFileBuffer, 0, 6);
					sector = (CacheFile.cacheFileBuffer[5] & 0xff)
							+ (((CacheFile.cacheFileBuffer[4] & 0xff) << 8) + (CacheFile.cacheFileBuffer[3] << 16 & 0xff0000));
					if (sector <= 0
							|| sector > dataFile.getFileLength() / 520L) {
						return false;
					}
				}
				CacheFile.cacheFileBuffer[1] = (byte) (fileSize >> 8);
				CacheFile.cacheFileBuffer[3] = (byte) (sector >> 16);
				CacheFile.cacheFileBuffer[2] = (byte) fileSize;
				CacheFile.cacheFileBuffer[0] = (byte) (fileSize >> 16);
				CacheFile.cacheFileBuffer[4] = (byte) (sector >> 8);
				CacheFile.cacheFileBuffer[5] = (byte) sector;
				indexFile.seek(file * 6);
				indexFile.write(CacheFile.cacheFileBuffer, 0, 6);
				int dataWritten = 0;
				for (int part = 0; dataWritten < fileSize; part++) {
					int nextSector = 0;
					if (exists) {
						dataFile.seek(sector * 520);
						try {
							dataFile.read(CacheFile.cacheFileBuffer, 0, 8);
						} catch (EOFException e) {
							e.printStackTrace();
							break;
						}
						int currentFile = (0xff & CacheFile.cacheFileBuffer[1])
								+ (0xff00 & CacheFile.cacheFileBuffer[0] << 8);
						int currentPart = (0xff & CacheFile.cacheFileBuffer[3])
								+ (0xff00 & CacheFile.cacheFileBuffer[2] << 8);
						nextSector = ((0xff & CacheFile.cacheFileBuffer[4]) << 16)
								+ (((0xff & CacheFile.cacheFileBuffer[5]) << 8) + (0xff & CacheFile.cacheFileBuffer[6]));
						int currentCache = CacheFile.cacheFileBuffer[7] & 0xff;
						if (currentFile != file || part != currentPart
								|| cacheId != currentCache) {
							return false;
						}
						if (nextSector < 0
								|| dataFile.getFileLength() / 520L < nextSector) {
							return false;
						}
					}
					if (nextSector == 0) {
						exists = false;
						nextSector = (int) ((dataFile.getFileLength() + 519L) / 520L);
						if (nextSector == 0) {
							nextSector++;
						}
						if (nextSector == sector) {
							nextSector++;
						}
					}
					CacheFile.cacheFileBuffer[3] = (byte) part;
					if (fileSize - dataWritten <= 512) {
						nextSector = 0;
					}
					CacheFile.cacheFileBuffer[0] = (byte) (file >> 8);
					CacheFile.cacheFileBuffer[1] = (byte) file;
					CacheFile.cacheFileBuffer[2] = (byte) (part >> 8);
					CacheFile.cacheFileBuffer[7] = (byte) cacheId;
					CacheFile.cacheFileBuffer[4] = (byte) (nextSector >> 16);
					CacheFile.cacheFileBuffer[5] = (byte) (nextSector >> 8);
					CacheFile.cacheFileBuffer[6] = (byte) nextSector;
					dataFile.seek(sector * 520);
					dataFile.write(CacheFile.cacheFileBuffer, 0, 8);
					int dataToWrite = fileSize - dataWritten;
					if (dataToWrite > 512) {
						dataToWrite = 512;
					}
					dataFile.write(buffer, dataWritten, dataToWrite);
					dataWritten += dataToWrite;
					sector = nextSector;
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

}
