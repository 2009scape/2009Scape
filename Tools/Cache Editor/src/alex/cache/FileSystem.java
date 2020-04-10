package alex.cache;

import alex.CacheLoader;
import alex.io.Stream;
import alex.util.Methods;

public class FileSystem {

	private Object childBuffers[][];
	private boolean discardEntryBuffers;
	private int discardUnpacked;
	private Object entryBuffers[];
	private int id;
	public ReferenceTable referenceTable;
	public CacheFileWorker worker;

	public FileSystem(int id, boolean discardEntryBuffers, int discardUnpacked) {
		if (discardUnpacked < 0 || discardUnpacked > 2)
			throw new IllegalArgumentException("js5: Invalid value "
					+ discardUnpacked + " supplied for discardunpacked");
		this.id = id;
		this.discardEntryBuffers = discardEntryBuffers;
		this.discardUnpacked = discardUnpacked;
		worker = new CacheFileWorker(id);
		referenceTable = worker.getReferenceTable();
		entryBuffers = new Object[referenceTable.entryIndexCount];
		childBuffers = new Object[referenceTable.entryIndexCount][];
	}

	public void clearChildBuffer(int file) {
		if (childBuffers != null) {
			childBuffers[file] = null;
		}
	}

	public void clearChildBuffers() {
		if (childBuffers != null) {
			for (int i = 0; childBuffers.length > i; i++) {
				childBuffers[i] = null;
			}
		}
	}

	public void clearEntryBuffers() {
		if (entryBuffers != null) {
			for (int i = 0; i < entryBuffers.length; i++) {
				entryBuffers[i] = null;
			}
		}
	}

	public void clearIdentifiers(boolean children, boolean entries) {
		if (children) {
			referenceTable.childIdentTables = null;
			referenceTable.childIdentifiers = null;
		}
		if (entries) {
			referenceTable.entryIdentifiers = null;
			referenceTable.entryIdentTable = null;
		}
	}

	public boolean filesCompleted() {
		boolean complete = true;
		for (int index = 0; referenceTable.entryIndices.length > index; index++) {
			int file = referenceTable.entryIndices[index];
			if (entryBuffers[file] == null) {
				loadBuffer(file);
				if (entryBuffers[file] == null)
					complete = false;
			}
		}
		return complete;
	}

	public int getChildCount() {
		return referenceTable.childIndexCounts.length;
	}

	public int getChildIndexCount(int file) {
		if (!validEntryIndex(file)) {
			return 0;
		}
		return referenceTable.childIndexCounts[file];
	}

	final int[] getChildIndices(int file) {
		int childIndices[] = referenceTable.childIndices[file];
		if (childIndices == null) {
			childIndices = new int[referenceTable.entryChildCounts[file]];
			for (int index = 0; childIndices.length > index; index++)
				childIndices[index] = index;
		}
		return childIndices;
	}

	public byte[] getFile(int file) {
		if (referenceTable.childIndexCounts.length == 1) {
			return getFile(0, file);
		}
		if (!validEntryIndex(file)) {
			return null;
		}
		if (referenceTable.childIndexCounts[file] == 1) {
			return getFile(file, 0);
		} else {
			throw new RuntimeException();
		}
	}
	
	

	public byte[] getFile(int file, int child) {
		return getFile(file, child, null);
	}

	
	/*
	 * packs all container, havnt finished this just use the other putfile which is fully done
	 */
	public boolean putFile(int fileId, int compression, byte[] unpackedContainer) {
		int version = referenceTable.entryVersions[fileId]+1;
		if(worker.putFile(fileId, compression, unpackedContainer, version)) {
			referenceTable.entryVersions[fileId] = version;
			byte[] packedBuffer = worker.getFileBuffer(fileId);
			Methods.CRC32.reset();
			Methods.CRC32.update(packedBuffer, 0, packedBuffer.length-2);
			int crc = (int) Methods.CRC32.getValue();
			referenceTable.entryCrcs[fileId] = crc;
			byte[] packedTable = referenceTable.packTable();
			return CacheLoader.getReferenceCache().writeFile(id, 2, packedTable, worker.generateTableFileVersion(), null);
		}
		return false;
	}
	public byte[] getFile(int file, int child, int keys[]) {
		if (!validIndices(file, child)) {
			return null;
		}
		if (childBuffers[file] == null || childBuffers[file][child] == null) {
			boolean prepared = prepareChildBuffers(file, child, keys);
			if (!prepared) {
				loadBuffer(file);
				boolean prepared1 = prepareChildBuffers(file, child, keys);
				if (!prepared1) {
					return null;
				}
			}
		}
		byte unwrapped[] = (byte[]) childBuffers[file][child];
		if (discardUnpacked != 1) {
			if (discardUnpacked == 2) {
				childBuffers[file] = null;
			}
		} else {
			childBuffers[file][child] = null;
			if (referenceTable.childIndexCounts[file] == 1) {
				childBuffers[file] = null;
			}
		}
		return unwrapped;
	}
	
	public boolean putFile(int fileId, int child, int[] keys, int compression, byte[] data) {
		return putFile(fileId, child, keys, compression, data, null, null);
	}
	public boolean putFile(int fileId, int childId, int[] keys, int compression, byte[] data, String fileName, String childName) {
		if(!validEntryIndex(fileId))
			referenceTable.expandTable(fileId+1);
		int oldChildCount = referenceTable.entryChildCounts[fileId];
		if (!validIndices(fileId, childId))
			referenceTable.expandTableChilds(fileId, childId+1); //gonna create thid now
		int childCount = referenceTable.entryChildCounts[fileId];
		if (!validIndices(fileId, childId)) {
			return false;
		}
		byte[] unpackedContainer;
		if (childCount > 1) {
			byte childBufferData[][] = null;
			if(oldChildCount > 0) {
			byte[] unpackedData = Methods.unpackContainer(worker.getFileBuffer(fileId));
			int length = unpackedData.length;
			int amtOfLoops = 0xff & unpackedData[--length];
			length -= amtOfLoops * (oldChildCount * 4);
			Stream stream = new Stream(unpackedData);
			int childBufferLength[] = new int[oldChildCount];
			stream.offset = length;
			for (int l2 = 0; l2 < amtOfLoops; l2++) {
				int offset = 0;
				for (int childIndex = 0; oldChildCount > childIndex; childIndex++) {
					offset += stream.getInt();
					childBufferLength[childIndex] += offset;
				}
			}
			childBufferData = new byte[oldChildCount][];
			for (int childIndex = 0; childIndex < oldChildCount; childIndex++) {
				childBufferData[childIndex] = new byte[childBufferLength[childIndex]];
				childBufferLength[childIndex] = 0;
			}
			stream.offset = length;
			int unpackedOff = 0;
			for (int loop = 0; amtOfLoops > loop; loop++) {
				int dataRead = 0;
				for (int childIndex = 0; oldChildCount > childIndex; childIndex++) {
					dataRead += stream.getInt();
					System.arraycopy(unpackedData, unpackedOff, childBufferData[childIndex], childBufferLength[childIndex],dataRead);
					unpackedOff += dataRead;
					childBufferLength[childIndex] += dataRead;
				}
			}
			}
			//we setted new data
			Stream outStream = new Stream(250000);
			int amtOfLoops = 1; //dont change this
			byte[][] childsData = new byte[childCount][];
			if(childBufferData != null)
				for(int index = 0; index < oldChildCount; index++) 
					childsData[index] = childBufferData[index];
			childsData[childId] = data;
			//added files data
			for(int index = 0; index < childCount; index++) {
				if(childsData[index] != null) {
					for(int i = 0; i < childsData[index].length; i++) {
						outStream.putByte(childsData[index][i]);
					}
				}
			}
			//added files lengths
			int lastLength = 0;
			for(int index = 0; index < childCount; index++) {
				outStream.putInt((childsData[index] == null ? 0 : childsData[index].length)-lastLength);
				lastLength = childsData[index] == null ? 0 : childsData[index].length;
			}
			outStream.putByte(amtOfLoops);
			unpackedContainer = new byte[outStream.offset];
			outStream.offset = 0;
			outStream.getBytes(unpackedContainer, 0, unpackedContainer.length);
		}else
			unpackedContainer = data;
		int version = referenceTable.entryVersions[fileId]+1;
		if(worker.putFile(fileId, compression, unpackedContainer, version, keys)) {
			referenceTable.entryVersions[fileId] = version;
			byte[] packedBuffer = worker.getFileBuffer(fileId);
			Methods.CRC32.reset();
			Methods.CRC32.update(packedBuffer, 0, packedBuffer.length-2);
			referenceTable.entryCrcs[fileId] = (int) Methods.CRC32.getValue();
			if(referenceTable.identifierFlag != 0) {
				referenceTable.entryIdentifiers[fileId] = fileName == null ? -1 : Methods.hashFile(fileName);
				referenceTable.childIdentifiers[fileId][childId] = childName == null ? -1 : Methods.hashFile(childName);
			}
			CacheLoader.getReferenceCache().writeFile(id, 2, referenceTable.packTable(), worker.generateTableFileVersion(), null);
		}
		
		return true;
	}
	
	private final boolean prepareChildBuffers(int file, int child, int keys[]) {
		if (!validEntryIndex(file)) {
			return false;
		}
		if (entryBuffers[file] == null) {
			return false;
		}
		int childCount = referenceTable.entryChildCounts[file];
		int childIndices[] = referenceTable.childIndices[file];
		if (childBuffers[file] == null) {
			childBuffers[file] = new Object[referenceTable.childIndexCounts[file]];
		}
		Object buffers[] = childBuffers[file];
		boolean prepared = true;
		for (int childIndex = 0; childCount > childIndex; childIndex++) {
			int childIndice;
			if (childIndices == null) {
				childIndice = childIndex;
			} else {
				childIndice = childIndices[childIndex];
			}
			if (buffers[childIndice] != null) {
				continue;
			}
			prepared = false;
			break;
		}

		if (prepared) {
			return true;
		}
		byte unwrapped[];
		if (keys != null && (~keys[0] != -1 || keys[1] != 0 || keys[2] != 0 || ~keys[3] != -1)) {
			unwrapped = Methods.copyBuffer((byte[]) entryBuffers[file]);// Methods.unwrapBuffer(entryBuffers[file],
																		// true);
			Stream stream = new Stream(unwrapped);
			stream.decodeXTEA(keys, 5, stream.payload.length);
		} else {
			unwrapped = (byte[]) entryBuffers[file];// Methods.unwrapBuffer(entryBuffers[file],
													// false);
		}
		byte unpackedData[];
		try {
			unpackedData = Methods.unpackContainer(unwrapped);
		} catch (RuntimeException runtimeexception) {
			throw runtimeexception;
		}
		if (discardEntryBuffers) {
			entryBuffers[file] = null;
		}
		if (childCount > 1) {
			if (discardUnpacked != 2) {
				int length = unpackedData.length;
				int amtOfLoops = 0xff & unpackedData[--length];
				length -= amtOfLoops * (childCount * 4);
				Stream stream = new Stream(unpackedData);
				int childBufferOffset[] = new int[childCount];
				stream.offset = length;
				for (int l2 = 0; l2 < amtOfLoops; l2++) {
					int childLength = 0;
					for (int childIndex = 0; childCount > childIndex; childIndex++) {
						childLength += stream.getInt();
					//	System.out.println(childLength);
						childBufferOffset[childIndex] += childLength;
					//	System.out.println(offset);
					}

				}

				byte childBufferData[][] = new byte[childCount][];
				for (int childIndex = 0; childIndex < childCount; childIndex++) {
					childBufferData[childIndex] = new byte[childBufferOffset[childIndex]];
					childBufferOffset[childIndex] = 0;
				}
				stream.offset = length;
				int unpackedOff = 0;
				for (int loop = 0; amtOfLoops > loop; loop++) {
					int dataRead = 0;
					for (int childIndex = 0; childCount > childIndex; childIndex++) {
						dataRead += stream.getInt();
						System.arraycopy(unpackedData, unpackedOff, childBufferData[childIndex], childBufferOffset[childIndex],dataRead);
						unpackedOff += dataRead;
						childBufferOffset[childIndex] += dataRead;
					}
				}

				for (int index = 0; childCount > index; index++) {
					int childIndice;
					if (childIndices != null) {
						childIndice = childIndices[index];
					} else {
						childIndice = index;
					}
					if (discardUnpacked != 0) {
						buffers[childIndice] = childBufferData[index];
					} else {
						buffers[childIndice] = childBufferData[index];// Methods.wrapBuffer(childBufs[j6],
													// false);
					}
				}

				//after here useless
			} else {
				int unpackedLength = unpackedData.length;
				int lastUnpackedByte = unpackedData[--unpackedLength] & 0xff;
				unpackedLength -= lastUnpackedByte * childCount * 4;
				Stream stream_2 = new Stream(unpackedData);
				int childOffset = 0;
				stream_2.offset = unpackedLength;
				int childIndice = 0;
				for (int k3 = 0; k3 < lastUnpackedByte; k3++) {
					int dataLength = 0;
					for (int childIndex = 0; childCount > childIndex; childIndex++) {
						dataLength += stream_2.getInt();
						int thisChildIndice;
						if (childIndices != null) {
							thisChildIndice = childIndices[childIndex];
						} else {
							thisChildIndice = childIndex;
						}
						if (child == thisChildIndice) {
							childIndice = thisChildIndice;
							childOffset += dataLength;
						}
					}

				}

				if (childOffset == 0) {
					return true;
				}
				byte childBufferData[] = new byte[childOffset];
				stream_2.offset = unpackedLength;
				childOffset = 0;
				int unpackedOffset = 0;
				for (int l5 = 0; l5 < lastUnpackedByte; l5++) {
					int dataLength = 0;
					for (int childIndex = 0; childIndex < childCount; childIndex++) {
						dataLength += stream_2.getInt();
						int thisChildIndice;
						if (childIndices == null) {
							thisChildIndice = childIndex;
						} else {
							thisChildIndice = childIndices[childIndex];
						}
						if (thisChildIndice == child) {
							System.arraycopy(unpackedData, unpackedOffset, childBufferData, childOffset, dataLength);
							childOffset += dataLength;
						}
						unpackedOffset += dataLength;
					}

				}

				buffers[childIndice] = childBufferData;
			}
		} else {
			int l1;
			if (childIndices != null) {
				l1 = childIndices[0];
			} else {
				l1 = 0;
			}
			if (discardUnpacked == 0) {
				buffers[l1] = unpackedData;// Methods.wrapBuffer(unpacked, false);
			} else {
				buffers[l1] = unpackedData;
			}
		}
		return true;
	}

	public byte[] getFile(String fileName, String childName) {
		fileName = fileName.toLowerCase();
		childName = childName.toLowerCase();
		int file = referenceTable.entryIdentTable.lookupIdentifier(Methods.hashFile(fileName));
		if (!validEntryIndex(file)) {
			return null;
		} else {
			int child = referenceTable.childIdentTables[file].lookupIdentifier(Methods.hashFile(childName));
			return getFile(file, child);
		}
	}

	private final int getFileCompletion(int file) {
		if (entryBuffers[file] != null) {
			return 100;
		} else {
			return 0;
		}
	}

	public int getFileCompletion(String name) {
		name = name.toLowerCase();
		int file = referenceTable.entryIdentTable.lookupIdentifier(Methods
				.hashFile(name));
		return getFileCompletion(file);
	}

	public int getFileIndex(int ident) {
		int file = referenceTable.entryIdentTable.lookupIdentifier(ident);
		if (!validEntryIndex(file)) {
			return -1;
		}
		return file;
	}

	public int getFileIndex(String name) {
		name = name.toLowerCase();
		int index = referenceTable.entryIdentTable.lookupIdentifier(Methods
				.hashFile(name));
		if (!validEntryIndex(index))
			return -1;
		else
			return index;
	}

	public int getReferenceCrc() {
		return referenceTable.crc;
	}

	public int getTotalCompletion() {
		int total = 0;
		int completed = 0;
		for (int k = 0; k < entryBuffers.length; k++) {
			if (referenceTable.entryChildCounts[k] > 0) {
				total += 100;
				completed += getFileCompletion(k);
			}
		}

		if (total == 0) {
			return 100;
		} else {
			return (completed * 100) / total;
		}
	}

	public boolean hasEntryBuffer(int file) {
		if (referenceTable.childIndexCounts.length == 1) {
			return hasEntryBuffer(0, file);
		}
		if (!validEntryIndex(file)) {
			return false;
		}
		if (referenceTable.childIndexCounts[file] == 1) {
			return hasEntryBuffer(file, 0);
		} else {
			throw new RuntimeException();
		}
	}

	public boolean hasEntryBuffer(int file, int child) {
		if (!validIndices(file, child)) {
			return false;
		}
		if (childBuffers[file] != null && childBuffers[file][child] != null) {
			return true;
		}
		if (entryBuffers[file] != null) {
			return true;
		}
		loadBuffer(file);
		return entryBuffers[file] != null;
	}

	final boolean hasEntryBuffer(String fileName, String childName) {
		fileName = fileName.toLowerCase();
		childName = childName.toLowerCase();
		int file = referenceTable.entryIdentTable.lookupIdentifier(Methods
				.hashFile(fileName));
		if (!validEntryIndex(file)) {
			return false;
		}
		int child = referenceTable.childIdentTables[file]
				.lookupIdentifier(Methods.hashFile(childName));
		return hasEntryBuffer(file, child);
	}

	final boolean hasFile(String name) {
		name = name.toLowerCase();
		int file = referenceTable.entryIdentTable.lookupIdentifier(Methods
				.hashFile(name));
		return file >= 0;
	}

	private boolean hasFileBuffer(int file) {
		if (!validEntryIndex(file))
			return false;
		if (entryBuffers[file] != null)
			return true;
		loadBuffer(file);
		return entryBuffers[file] != null;
	}

	private boolean hasFileBuffer(String name) {
		name = name.toLowerCase();
		int file = referenceTable.entryIdentTable.lookupIdentifier(Methods
				.hashFile(name));
		return hasFileBuffer(file);
	}

	public void loadBuffer(int file) {
		entryBuffers[file] = worker.getFileBuffer(file);
	}

	private final boolean validEntryIndex(int file) {
		if (file < 0 || referenceTable.childIndexCounts.length <= file
				|| referenceTable.childIndexCounts[file] == 0)
			return false;
		return true;
	}
	
	private boolean validIndices(int file, int child) {
		if (file < 0 || child < 0
				|| referenceTable.childIndexCounts.length <= file
				|| child >= referenceTable.childIndexCounts[file])
			return false;
		return true;
	}
}
