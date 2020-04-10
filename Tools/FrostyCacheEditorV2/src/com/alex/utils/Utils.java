package com.alex.utils;

import java.math.BigInteger;

import com.alex.io.OutputStream;
import com.alex.store.Store;

public final class Utils {

	public static byte[] cryptRSA(byte[] data, BigInteger exponent, BigInteger modulus) {
		return new BigInteger(data).modPow(exponent, modulus).toByteArray();
	}
	
	public static byte[] getArchivePacketData(int indexId, int archiveId,
			byte[] archive) {
		OutputStream stream = new OutputStream(archive.length + 4);
		stream.writeByte(indexId);
		stream.writeShort(archiveId);
		stream.writeByte(0); // priority, no compression
		stream.writeInt(archive.length);
		int offset = 8;
		for (int index = 0; index < archive.length; index++) {
			if (offset == 512) {
				stream.writeByte(-1);
				offset = 1;
			}
			stream.writeByte(archive[index]);
			offset++;
		}
		byte[] packet = new byte[stream.getOffset()];
		stream.setOffset(0);
		stream.getBytes(packet, 0, packet.length);
		return packet;
	}

	public static int getNameHash(String name) {
		return name.toLowerCase().hashCode();
	}

	/*public static final int getInterfaceDefinitionsSize(Store store) {
		return store.getIndexes()[3].getLastArchiveId();
	}
       

	public static final int getInterfaceDefinitionsComponentsSize(Store store,
			int interfaceId) {
		return store.getIndexes()[3].getLastFileId(interfaceId);
            //return store.getIndexes()[3].getValidFilesCount(interfaceId);
	}*/
       public static final int getInterfaceDefinitionsSize(Store store) {
		return store.getIndexes()[3].getLastArchiveId() + 1;
	}

	public static final int getInterfaceDefinitionsComponentsSize(Store store,
			int interfaceId) {
		return store.getIndexes()[3].getLastFileId(interfaceId) + 1;
	}

	public static final int getAnimationDefinitionsSize(Store store) {
		int lastArchiveId = store.getIndexes()[20].getLastArchiveId();
		return lastArchiveId
				* 128
				+ store.getIndexes()[20]
						.getValidFilesCount(lastArchiveId);
	}
	public static final int getItemDefinitionsSize(Store store) {
		int lastArchiveId = store.getIndexes()[19].getLastArchiveId();
		return lastArchiveId * 256
				+ store.getIndexes()[19].getValidFilesCount(lastArchiveId);
	}

    public static int getNPCDefinitionsSize(Store store) {
    		int lastArchiveId = store.getIndexes()[18].getLastArchiveId();
		return lastArchiveId * 256
				+ store.getIndexes()[18].getValidFilesCount(lastArchiveId);
    }
    	public static final int getObjectDefinitionsSize(Store store) {
		int lastArchiveId = store.getIndexes()[16].getLastArchiveId();
		return lastArchiveId
				* 256
				+ store.getIndexes()[16]
						.getValidFilesCount(lastArchiveId);
	}
        public static final int getGraphicDefinitionsSize(Store store) {
		int lastArchiveId = store.getIndexes()[21].getLastArchiveId();
		return lastArchiveId
				* 256
				+ store.getIndexes()[21]
						.getValidFilesCount(lastArchiveId);
	}




	private Utils() {

	}

}
