package org.arios.cache;

import java.nio.ByteBuffer;

import org.arios.cache.misc.ContainersInformation;
import org.arios.cache.misc.StringUtils;

/**
 * A cache file manager.
 * @author Dragonkk
 * 
 */
public final class CacheFileManager {

	/**
	 * The cache file.
	 */
	private CacheFile cacheFile;

	/**
	 * The containers information.
	 */
	private ContainersInformation information;

	/**
	 * Discard a files data.
	 */
	private boolean discardFilesData;

	/**
	 * A array holding file data.
	 */
	private byte[][][] filesData;

	/**
	 * Construct a new cache file manager.
	 * @param cacheFile The cache file.
	 * @param discardFilesData To discard a files data.
	 */
	public CacheFileManager(CacheFile cacheFile, boolean discardFilesData) {
		this.cacheFile = cacheFile;
		this.discardFilesData = discardFilesData;
		byte[] informContainerPackedData = Cache.getReferenceFile().getContainerData(cacheFile.getIndexFileId());
		if (informContainerPackedData == null) {
			return;
		}
		information = new ContainersInformation(informContainerPackedData);
		resetFilesData();
	}

	/**
	 * Get the cache file.
	 * @return The cache file.
	 */
	public CacheFile getCacheFile() {
		return cacheFile;
	}

	/**
	 * Get the containers size.
	 * @return The containers size.
	 */
	public int getContainersSize() {
		return information.getContainers().length;
	}

	/**
	 * Get the files size.
	 * @param containerId The container id.
	 * @return The files size.
	 */
	public int getFilesSize(int containerId) {
		if (!validContainer(containerId)) {
			return -1;
		}
		return information.getContainers()[containerId].getFiles().length;
	}

	/**
	 * Reset the file data.
	 */
	public void resetFilesData() {
		filesData = new byte[information.getContainers().length][][];
	}

	/**
	 * Check if a file is valid.
	 * @param containerId The container id.
	 * @param fileId The file id.
	 * @return If the file is valid {@code true}.
	 */
	public boolean validFile(int containerId, int fileId) {
		if (!validContainer(containerId)) {
			return false;
		}
		if (fileId < 0 || information.getContainers()[containerId].getFiles().length <= fileId) {
			return false;
		}
		return true;

	}

	/**
	 * If a container is valid.
	 * @param containerId The container id.
	 * @return If the container is valid {@code true}.
	 */
	public boolean validContainer(int containerId) {
		if (containerId < 0 || information.getContainers().length <= containerId) {
			return false;
		}
		return true;
	}

	/**
	 * Get the file ids.
	 * @param containerId The container id.
	 * @return The file ids.
	 */
	public int[] getFileIds(int containerId) {
		if (!validContainer(containerId)) {
			return null;
		}
		return information.getContainers()[containerId].getFilesIndexes();
	}

	/**
	 * Get the archive id.
	 * @param name The archive name.
	 * @return The archive id.
	 */
	public int getArchiveId(String name) {
		if (name == null) {
			return -1;
		}
		int hash = StringUtils.getNameHash(name);
		for (int containerIndex = 0; containerIndex < information.getContainersIndexes().length; containerIndex++) {
			if (information.getContainers()[information.getContainersIndexes()[containerIndex]].getNameHash() == hash) {
				return information.getContainersIndexes()[containerIndex];
			}
		}
		return -1;
	}

	/**
	 * Get the file data.
	 * @param containerId The container id.
	 * @param fileId The file id.
	 * @return The get file data.
	 */
	public byte[] getFileData(int containerId, int fileId) {
		return getFileData(containerId, fileId, null);
	}

	/**
	 * Load the file data.
	 * @param archiveId The container id.
	 * @param keys The container keys.
	 * @return If the file data is loaded {@code true}.
	 */
	public boolean loadFilesData(int archiveId, int[] keys) {
		byte[] data = cacheFile.getContainerUnpackedData(archiveId, keys);
		if (data == null) {
			return false;
		}
		if(filesData[archiveId] == null) {
			if(information.getContainers()[archiveId] == null) {
				return false; //container inform doesnt exist anymore
			}
			filesData[archiveId] = new byte[information.getContainers()[archiveId].getFiles().length][];
		}
		if (information.getContainers()[archiveId].getFilesIndexes().length == 1) {
			int fileId = information.getContainers()[archiveId].getFilesIndexes()[0];
			filesData[archiveId][fileId] = data;
		} else {
			int readPosition = data.length;
			int amtOfLoops = data[--readPosition] & 0xff;
			readPosition -= amtOfLoops * (information.getContainers()[archiveId].getFilesIndexes().length * 4);
			ByteBuffer buffer = ByteBuffer.wrap(data);
			int filesSize[] = new int[information.getContainers()[archiveId].getFilesIndexes().length];
			buffer.position(readPosition);
			for (int loop = 0; loop < amtOfLoops; loop++) {
				int offset = 0;
				for (int fileIndex = 0; fileIndex < information.getContainers()[archiveId].getFilesIndexes().length; fileIndex++) {
					filesSize[fileIndex] += offset += buffer.getInt();
				}
			}
			byte[][] filesBufferData = new byte[information.getContainers()[archiveId].getFilesIndexes().length][];
			for (int fileIndex = 0; fileIndex < information.getContainers()[archiveId].getFilesIndexes().length; fileIndex++) {
				filesBufferData[fileIndex] = new byte[filesSize[fileIndex]];
				filesSize[fileIndex] = 0;
			}
			buffer.position(readPosition);
			int sourceOffset = 0;
			for (int loop = 0; loop < amtOfLoops; loop++) {
				int dataRead = 0;
				for (int fileIndex = 0; fileIndex < information.getContainers()[archiveId].getFilesIndexes().length; fileIndex++) {
					dataRead += buffer.getInt();
					System.arraycopy(data, sourceOffset, filesBufferData[fileIndex], filesSize[fileIndex],dataRead);
					sourceOffset += dataRead;
					filesSize[fileIndex] += dataRead;
				}
			}
			for (int fileIndex = 0; fileIndex < information.getContainers()[archiveId].getFilesIndexes().length; fileIndex++) {
				filesData[archiveId][information.getContainers()[archiveId].getFilesIndexes()[fileIndex]] = filesBufferData[fileIndex];
			}
		}
		return true;

	}

	/**
	 * Get the file data.
	 * @param containerId The container id.
	 * @param fileId The file id.
	 * @param xteaKeys The container keys.
	 * @return The file data.
	 */
	public byte[] getFileData(int containerId, int fileId, int[] xteaKeys) {
		if (!validFile(containerId, fileId)) {
			return null;
		}
		if (filesData[containerId] == null || filesData[containerId][fileId] == null) {
			if (!loadFilesData(containerId, xteaKeys)) {
				return null;
			}
		}
		byte[] data = filesData[containerId][fileId];
		if (discardFilesData) {
			if (filesData[containerId].length == 1) {
				filesData[containerId] = null;
			} else {
				filesData[containerId][fileId] = null;
			}
		}
		return data;
	}

	/**
	 * Get the containers information.
	 * @return The containers information.
	 */
	public ContainersInformation getInformation() {
		return information;
	}
}
