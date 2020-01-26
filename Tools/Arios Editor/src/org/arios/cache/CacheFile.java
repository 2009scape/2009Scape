package org.arios.cache;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import org.arios.cache.misc.ContainersInformation;
import org.arios.cache.misc.XTEACryption;

/**
 * A cache file.
 * @author Dragonkk
 */
public final class CacheFile {

	/**
	 * The index file id.
	 */
	private int indexFileId;

	/**
	 * The cache file buffer.
	 */
	private byte[] cacheFileBuffer;

	/**
	 * The maximum container size.
	 */
	private int maxContainerSize;

	/**
	 * The index file.
	 */
	private RandomAccessFile indexFile;

	/**
	 * The data file.
	 */
	private RandomAccessFile dataFile;

	/**
	 * Construct a new cache file.
	 * @param indexFileId The index file id.
	 * @param indexFile The index file.
	 * @param dataFile The data file.
	 * @param maxContainerSize The maximum container size.
	 * @param cacheFileBuffer The cache file buffer.
	 */
	public CacheFile(int indexFileId, RandomAccessFile indexFile, RandomAccessFile dataFile, int maxContainerSize, byte[] cacheFileBuffer) {
		this.cacheFileBuffer = cacheFileBuffer;
		this.indexFileId = indexFileId;
		this.maxContainerSize = maxContainerSize;
		this.indexFile = indexFile;
		this.dataFile = dataFile;
	}

	/**
	 * Get the unpacked container data.
	 * @param containerId The container id.
	 * @param xteaKeys The container keys.
	 * @return The unpacked container data.
	 */
	public final byte[] getContainerUnpackedData(int containerId, int[] xteaKeys) {
		byte[] packedData = getContainerData(containerId);
		if (packedData == null) {
			return null;
		}
		if (xteaKeys != null && (xteaKeys[0] != 0 || xteaKeys[1] != 0 || xteaKeys[2] != 0 || xteaKeys[3] != 0)) {
			packedData = XTEACryption.decrypt(xteaKeys, ByteBuffer.wrap(packedData), 5, packedData.length).array();
		}
		return ContainersInformation.unpackCacheContainer(packedData);
	}

	/**
	 * Get the container data for the specified container id.
	 * @param containerId The container id.
	 * @return The container data.
	 */
	public final byte[] getContainerData(int containerId) {
		synchronized(dataFile) {
			try {
				if (indexFile.length() < (6 * containerId + 6)) {
					return null;
				}
				indexFile.seek(6 * containerId);
				indexFile.read(cacheFileBuffer, 0, 6);
				int containerSize = (cacheFileBuffer[2] & 0xff) + (((0xff & cacheFileBuffer[0]) << 16) + (cacheFileBuffer[1] << 8 & 0xff00));
				int sector = ((cacheFileBuffer[3] & 0xff) << 16)- (-(0xff00 & cacheFileBuffer[4] << 8) - (cacheFileBuffer[5] & 0xff));
				if (containerSize < 0 || containerSize > maxContainerSize) {
					return null;
				}
				if (sector <= 0 || dataFile.length() / 520L < sector) {
					return null;
				}
				byte data[] = new byte[containerSize];
				int dataReadCount = 0;
				int part = 0;
				while (containerSize > dataReadCount) {
					if (sector == 0) {
						return null;
					}
					dataFile.seek(520 * sector);
					int dataToReadCount = containerSize - dataReadCount;
					if (dataToReadCount > 512) {
						dataToReadCount = 512;
					}
					dataFile.read(cacheFileBuffer, 0, 8 + dataToReadCount);
					int currentContainerId = (0xff & cacheFileBuffer[1]) + (0xff00 & cacheFileBuffer[0] << 8);
					int currentPart = ((cacheFileBuffer[2] & 0xff) << 8) + (0xff & cacheFileBuffer[3]);
					int nextSector = (cacheFileBuffer[6] & 0xff) + (0xff00 & cacheFileBuffer[5] << 8) + ((0xff & cacheFileBuffer[4]) << 16);
					int currentIndexFileId = cacheFileBuffer[7] & 0xff;
					if (containerId != currentContainerId || currentPart != part|| indexFileId != currentIndexFileId) {
						return null;
					}
					if (nextSector < 0 || (dataFile.length() / 520L) < nextSector) {
						return null;
					}
					for (int index = 0; dataToReadCount > index; index++) {
						data[dataReadCount++] = cacheFileBuffer[8 + index];
					}
					part++;
					sector = nextSector;
				}
				return data;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * Get the index file id.
	 * @return
	 */
	public int getIndexFileId() {
		return indexFileId;
	}

	/**
	 * Get the unpacked container data.
	 * @param containerId The container id.
	 * @return The unpacked container data.
	 */
	public final byte[] getContainerUnpackedData(int containerId) {
		return getContainerUnpackedData(containerId, null);
	}
}

