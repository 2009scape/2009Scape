package alex.cache;

import alex.CacheLoader;
import alex.io.Stream;
import alex.util.LookupTable;
import alex.util.Methods;

public class ReferenceTable {

	int childIdentifiers[][];
	LookupTable childIdentTables[];
	int childIndexCounts[];
	int childIndices[][];
	int crc;
	int entryChildCounts[];
	private int entryCount;
	int entryCrcs[];
	int entryIdentifiers[];
	LookupTable entryIdentTable;
	int entryIndexCount;
	int entryIndices[];
	public int entryVersions[];
	public int revision;
	private int protocol;
	int identifierFlag;
	private boolean needRevisionUpdate;
	
	public ReferenceTable(byte buffer[]) {
		crc = Methods.getCrc(buffer, buffer.length);
		unpackTable(buffer);
	}
	/*
	 * notice if we make it smaller than actualy is we will loss alot of files information
	 * 
	 */
	

	public byte[] packTable() {
		Stream stream = new Stream(2500000);
		if (CacheLoader.OLD_CACHE) {
			stream.putByte(protocol);
			if (protocol >= 6) {
				if(needRevisionUpdate)
					revision++;
				stream.putInt(revision);
			}
			stream.putByte(identifierFlag);
			stream.putShort(entryCount);
			int lastEntryOffset = 0;
			for (int i = 0; entryCount > i; i++) {
				stream.putShort(entryIndices[i] - lastEntryOffset);
				lastEntryOffset = entryIndices[i];
			}
			if (identifierFlag != 0) {
				for (int index = 0; entryCount > index; index++) {
					stream.putInt(entryIdentifiers[entryIndices[index]]);
				}
			}
			for (int index = 0; index < entryCount; index++) {
				stream.putInt(entryCrcs[entryIndices[index]]);
			}
			for (int index = 0; index < entryCount; index++) {
				stream.putInt(entryVersions[entryIndices[index]]);
			}
			for (int index = 0; index < entryCount; index++) {
				stream.putShort(entryChildCounts[entryIndices[index]]);
			}
			for (int index = 0; entryCount > index; index++) {
				int indice = entryIndices[index];
				int lastEntryChildOffset = 0;
				for (int childIndex = 0; entryChildCounts[indice] > childIndex; childIndex++) {
					int nextChildIndice = childIndices[indice] != null ? childIndices[indice][childIndex] : childIndex;
					stream.putShort(nextChildIndice - lastEntryChildOffset);
					lastEntryChildOffset = nextChildIndice;
				}
			}
			if (identifierFlag != 0) {
				for (int index = 0; index < entryCount; index++) {
					int indice = entryIndices[index];
					int entryChildCount = entryChildCounts[indice];
					for (int childIndex = 0; childIndex < entryChildCount; childIndex++) {
						int childIndice;
						if (childIndices[indice] != null) {
							childIndice = childIndices[indice][childIndex];
						} else {
							childIndice = childIndex;
						}
						stream.putInt(childIdentifiers[indice][childIndice]);
					}
				}
			}
		}
		byte[] buffer = new byte[stream.offset];
		stream.offset = 0;
		stream.getBytes(buffer, 0, buffer.length);
		needRevisionUpdate = false;
		return buffer;
	}
	
	public void expandTable(int newEntryCount) {
		int[] newEntryIndices = new int[newEntryCount];
		int count = entryIndexCount - 1; //the old count
		//copys the indices and creates new indices
		System.arraycopy(entryIndices, 0, newEntryIndices, 0, entryIndices.length);
		for(int index = entryIndices.length; index < newEntryIndices.length; index++) {
			newEntryIndices[index] = index == 0 ? 1 : newEntryIndices[index-1]+1;
			if (newEntryIndices[index] > count)
				count = newEntryIndices[index];
		}
		
		//creates new stuff with new size
		int newEntryIndexCount = count + 1;
		int[] newChildIndexCounts = new int[newEntryIndexCount];
		int[][] newChildIndices = new int[newEntryIndexCount][];
		int[] newEntryVersions = new int[newEntryIndexCount];
		int[] newEntryCrcs = new int[newEntryIndexCount];
		int[] newEntryChildCounts = new int[newEntryIndexCount];
		LookupTable newEntryIdentTable = null;
		int[] newEntryIdentifiers = null;
		
		if (identifierFlag != 0) {
			newEntryIdentifiers = new int[newEntryIndexCount];
			//sets default identifiers
			for (int l1 = 0; l1 < newEntryIndexCount; l1++) {
				newEntryIdentifiers[l1] = -1;
			}
			//copys the old entry identifiers
			System.arraycopy(entryIdentifiers, 0, newEntryIdentifiers, 0, entryIdentifiers.length);
			newEntryIdentTable = new LookupTable(newEntryIdentifiers);
		}
		
		//copys the old entrycrcs
		System.arraycopy(entryCrcs, 0, newEntryCrcs, 0, entryCrcs.length);
		//copys the old entryVersions
		System.arraycopy(entryVersions, 0, newEntryVersions, 0, entryVersions.length);
		//copys the old entryChildCounts
		System.arraycopy(entryChildCounts, 0, newEntryChildCounts, 0, entryChildCounts.length);
		
		for (int index = 0; newEntryCount > index; index++) {
			int indice = newEntryIndices[index];
			if(childIndices.length > indice) {
				int entryChildCount = newEntryChildCounts[indice];
				for (int childIndex = 0; entryChildCount > childIndex; childIndex++)
					newChildIndices[indice] = childIndices[indice];
				newChildIndexCounts[index] = childIndexCounts[indice];
			}else{
				int entryChildCount = newEntryChildCounts[indice];
				newChildIndices[indice] = new int[entryChildCount];
				newChildIndexCounts[index] = 1;
			}
		}
		
		LookupTable[] newChildIdentTables = null;
		int[][] newChildIdentifiers = null;
		if (identifierFlag != 0) {
			newChildIdentifiers = new int[1 + count][];
			newChildIdentTables = new LookupTable[1 + count];
			for (int index = 0; index < newEntryCount; index++) {
				int indice = newEntryIndices[index];
				int entryChildCount = newEntryChildCounts[indice];
				newChildIdentifiers[indice] = new int[newChildIndexCounts[indice]];
				for (int childIndex = 0; childIndex < newChildIndexCounts[indice]; childIndex++) {
					newChildIdentifiers[indice][childIndex] = -1;
				}
				for (int childIndex = 0; childIndex < entryChildCount; childIndex++) {
					int childIndice;
					if (newChildIndices[indice] != null) {
						childIndice = newChildIndices[indice][childIndex];
					} else {
						childIndice = childIndex;
					}
					if(newChildIdentifiers.length > indice)
						newChildIdentifiers[indice][childIndice] = childIdentifiers[indice][childIndice];
					
				}
				newChildIdentTables[indice] = new LookupTable(newChildIdentifiers[indice]);
			}
		}
		
		//sets the new entrys that were expanded
		entryCount = newEntryCount;
		entryIndices = newEntryIndices;
		entryIndexCount = newEntryIndexCount;
		childIndexCounts = newChildIndexCounts;
		childIndices = newChildIndices;
		entryVersions = newEntryVersions;
		entryCrcs = newEntryCrcs;
		entryChildCounts = newEntryChildCounts;
		entryIdentTable = newEntryIdentTable;
		entryIdentifiers = newEntryIdentifiers;
		childIdentTables = newChildIdentTables;
		childIdentifiers = newChildIdentifiers;
		//on end
		
		needRevisionUpdate = true;
	}
	
	public void expandTableChilds(int indice, int entryChildCount) {
		int[] newChildIndices = new int[entryChildCount];
		int count = childIndexCounts[indice] - 1; 
		if(childIndices[indice] != null)
			System.arraycopy(childIndices[indice], 0, newChildIndices, 0, childIndices[indice].length);
		for(int index = childIndices[indice] == null ? 0 : childIndices[indice].length; index < newChildIndices.length; index++) {
			newChildIndices[index] = index == 0 ? 1 : newChildIndices[index-1]+1;
			if (newChildIndices[index] > count)
				count = newChildIndices[index];
			
		}
		int newChildIndexCounts = count+1;
		int[] newChildIdentifiers = null;
		LookupTable newChildIdentTable = null;
		if (identifierFlag != 0) {
			newChildIdentifiers = new int[newChildIndexCounts];
			//sets default identifiers
			for (int l1 = 0; l1 < newChildIndexCounts; l1++) {
				newChildIdentifiers[l1] = -1;
			}
			//copys the old entry identifiers
			if(childIdentifiers[indice] != null)
				System.arraycopy(childIdentifiers[indice], 0, newChildIdentifiers, 0, childIdentifiers[indice].length);
			newChildIdentTable = new LookupTable(newChildIdentifiers);
			childIdentTables[indice] = newChildIdentTable;
			childIdentifiers[indice] = newChildIdentifiers;
		}
		childIndices[indice] = newChildIndices;
		childIndexCounts[indice] = newChildIndexCounts;
		entryChildCounts[indice] = entryChildCount;
	}
	
	private void unpackTable(byte buffer[]) {
		if (CacheLoader.OLD_CACHE) {
			Stream stream = new Stream(Methods.unpackContainer(buffer));
			protocol = stream.getUByte();
			if (protocol != 5 && protocol != 6) {
				throw new RuntimeException();
			}
			if (protocol < 6) {
				revision = 0;
			} else {
				revision = stream.getInt();
			}
			identifierFlag = stream.getUByte();
			entryCount = stream.getUShort();
			int offset = 0;
			entryIndices = new int[entryCount];
			int count = -1;
			for (int index = 0; entryCount > index; index++) {
				entryIndices[index] = offset += stream.getUShort();
				if (entryIndices[index] > count) {
					count = entryIndices[index];
				}
			}

			entryIndexCount = count + 1;
			childIndexCounts = new int[entryIndexCount];
			childIndices = new int[entryIndexCount][];
			entryVersions = new int[entryIndexCount];
			entryCrcs = new int[entryIndexCount];
			entryChildCounts = new int[entryIndexCount];
			if (identifierFlag != 0) {
				entryIdentifiers = new int[entryIndexCount];
				for (int l1 = 0; l1 < entryIndexCount; l1++) {
					entryIdentifiers[l1] = -1;
				}

				for (int index = 0; entryCount > index; index++) {
					entryIdentifiers[entryIndices[index]] = stream.getInt();
				}

				entryIdentTable = new LookupTable(entryIdentifiers);
			}
			for (int index = 0; index < entryCount; index++) {
				entryCrcs[entryIndices[index]] = stream.getInt();
			}

			for (int index = 0; index < entryCount; index++) {
				entryVersions[entryIndices[index]] = stream.getInt();
			}

			for (int index = 0; index < entryCount; index++) {
				entryChildCounts[entryIndices[index]] = stream.getUShort();
			}

			for (int index = 0; entryCount > index; index++) {
				int indice = entryIndices[index];
				int childOffset = 0;
				int entryChildCount = entryChildCounts[indice];
				childIndices[indice] = new int[entryChildCount];
				int childCount = -1;
				for (int childIndex = 0; entryChildCount > childIndex; childIndex++) {
					int childIndice = childIndices[indice][childIndex] = childOffset += stream.getUShort();
					if (childIndice > childCount) {
						childCount = childIndice;
					}
				}

				childIndexCounts[indice] = childCount + 1;
				if ((childCount + 1) == entryChildCount) {
					childIndices[indice] = null;
				}
			}

			if (identifierFlag != 0) {
				childIdentifiers = new int[1 + count][];
				childIdentTables = new LookupTable[1 + count];
				for (int index = 0; index < entryCount; index++) {
					int indice = entryIndices[index];
					int entryChildCount = entryChildCounts[indice];
					childIdentifiers[indice] = new int[childIndexCounts[indice]];
					for (int childIndex = 0; childIndex < childIndexCounts[indice]; childIndex++) {
						childIdentifiers[indice][childIndex] = -1;
					}

					for (int childIndex = 0; childIndex < entryChildCount; childIndex++) {
						int childIndice;
						if (childIndices[indice] != null) {
							childIndice = childIndices[indice][childIndex];
						} else {
							childIndice = childIndex;
						}
						childIdentifiers[indice][childIndice] = stream.getInt();
					}

					childIdentTables[indice] = new LookupTable(childIdentifiers[indice]);
				}

			}
		} else {
			// TODO
		}
	}

}
