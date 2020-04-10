package com.alex.store;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.Arrays;

import com.alex.io.OutputStream;
import com.alex.util.whirlpool.Whirlpool;
import com.alex.utils.Constants;
import com.alex.utils.Utils;


public final class Store {
	
	private Index[] indexes;
	private MainFile index255;
	private String path;
	private RandomAccessFile data;
	private boolean newProtocol;
	public Store(String path) throws IOException {
		this(path, Constants.CLIENT_BUILD >= 704);
	}
	
	public Store(String path, boolean newProtocol) throws IOException {
		this(path, newProtocol, null);
	}
	
	public Store(String path, boolean newProtocol, int[][] keys) throws IOException {
		this.path = path;
		this.newProtocol = newProtocol;
		data = new RandomAccessFile(path + "main_file_cache.dat2", "rw");
		index255 = new MainFile(255, data, new RandomAccessFile(path + "main_file_cache.idx255", "rw"), newProtocol);
		int idxsCount = index255.getArchivesCount();
		indexes = new Index[idxsCount];
		for(int id = 0; id < idxsCount; id++) {
			Index index = new Index(index255, new MainFile(id, data, new RandomAccessFile(path + "main_file_cache.idx"+id, "rw" )
			, newProtocol), keys == null ? null : keys[id]);
			if(index.getTable() == null)
				continue;
			indexes[id] = index;
		}
	}
	
	public final byte[] generateIndex255Archive255Current(BigInteger grab_server_private_exponent, BigInteger grab_server_modulus) {
		OutputStream stream = new OutputStream();
		stream.writeByte(getIndexes().length);
		for (int index = 0; index < getIndexes().length; index++) {
			if (getIndexes()[index] == null) {
				stream.writeInt(0);
				stream.writeInt(0);
				stream.writeBytes(new byte[64]);
				continue;
			}
			stream.writeInt(getIndexes()[index].getCRC());
			stream.writeInt(getIndexes()[index].getTable().getRevision());
			stream.writeBytes(getIndexes()[index].getWhirlpool());
			if(Constants.ENCRYPTED_CACHE) {
				//custom protection, encryption of tables addition, by me dragonkk ofc
				if(getIndexes()[index].getKeys() != null)
					for(int key : getIndexes()[index].getKeys()) 
						stream.writeInt(key);
				else for(int i = 0; i < 4; i++)
					stream.writeInt(0);
			}
		}
		byte[] archive = new byte[stream.getOffset()];
		stream.setOffset(0);
		stream.getBytes(archive, 0, archive.length);
		
		OutputStream hashStream = new OutputStream(65);
		hashStream.writeByte(0);
		hashStream.writeBytes(Whirlpool.getHash(archive, 0, archive.length));
		byte[] hash = new byte[hashStream.getOffset()];
		hashStream.setOffset(0);
		hashStream.getBytes(hash, 0, hash.length);
		if(grab_server_private_exponent != null && grab_server_modulus != null)
			hash = Utils.cryptRSA(hash, grab_server_private_exponent, grab_server_modulus);
		stream.writeBytes(hash);
		archive = new byte[stream.getOffset()];
		stream.setOffset(0);
		stream.getBytes(archive, 0, archive.length);
		return archive;
	}
	
	@SuppressWarnings("unused")
	public byte[] generateIndex255Archive255() {
		return Constants.CLIENT_BUILD < 614 ? generateIndex255Archive255Outdated() : generateIndex255Archive255Current(null, null);
	}
	
	/*
	 * old code
	 */
	public byte[] generateIndex255Archive255Outdated() {
		OutputStream stream = new OutputStream(indexes.length * 8);
		for(int index = 0; index < indexes.length; index++) {
			if(indexes[index] == null) {
				stream.writeInt(0);
				stream.writeInt(0);
				continue;
			}
			stream.writeInt(indexes[index].getCRC());
			stream.writeInt(indexes[index].getTable().getRevision());
		}
		byte[] archive = new byte[stream.getOffset()];
		stream.setOffset(0);
		stream.getBytes(archive, 0, archive.length);
		return archive;
	}
	
	public Index[] getIndexes() {
		return indexes;
	}
	
	public MainFile getIndex255() {
		return index255;
	}
	/*
	 * returns index
	 */
	public int addIndex(boolean named, boolean usesWhirpool, int tableCompression) throws IOException {
		int id = indexes.length;
		Index[] newIndexes = Arrays.copyOf(indexes, indexes.length+1);
		resetIndex(id, newIndexes, named, usesWhirpool, tableCompression);
		indexes = newIndexes;
		return id;
	}
	
	public void resetIndex(int id, boolean named, boolean usesWhirpool, int tableCompression) throws FileNotFoundException, IOException {
		resetIndex(id, indexes, named, usesWhirpool, tableCompression);
	}
	
	public void resetIndex(int id, Index[] indexes, boolean named, boolean usesWhirpool, int tableCompression) throws FileNotFoundException, IOException {
		OutputStream stream = new OutputStream(4);
		stream.writeByte(5);
		stream.writeByte((named ? 0x1 : 0) | (usesWhirpool ? 0x2 : 0));
		stream.writeShort(0);
		byte[] archiveData = new byte[stream.getOffset()];
		stream.setOffset(0);
		stream.getBytes(archiveData, 0, archiveData.length);
		Archive archive = new Archive(id, tableCompression, -1, archiveData);
		index255.putArchiveData(id, archive.compress());
		indexes[id] = new Index(index255, new MainFile(id, data, new RandomAccessFile(path + "main_file_cache.idx"+id, "rw"), newProtocol), null);
	}
	
}
