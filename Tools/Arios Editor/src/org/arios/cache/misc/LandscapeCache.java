package org.arios.cache.misc;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.Cache;

/**
 * Holds the map cache.
 * 
 * @author Emperor
 * 
 */
public final class LandscapeCache {

	/**
	 * The map indices buffer.
	 */
	private static ByteBuffer mapIndices;

	/**
	 * The landscapes;
	 */
	private static final Map<Integer, ByteBuffer> landscapes = new HashMap<>();

	/**
	 * The amount of indexes.
	 */
	private static int indexes;

	/**
	 * The cache length.
	 */
	private static int cacheLength;

	/**
	 * The indexes list.
	 */
	private static int[] indices = null;

	/**
	 * The path.
	 */
	private static String path;

	/**
	 * Initializes the landscape cache stuff.
	 * 
	 * @param path
	 *            The cache path.
	 * @throws Throwable
	 *             When an exception occurs.
	 */
	public static void init(String path) throws Throwable {
		LandscapeCache.path = path;
		try (RandomAccessFile raf = new RandomAccessFile(path + "/idx_reference.dat", "r");
				FileChannel channel = raf.getChannel()) {
			mapIndices = channel.map(MapMode.READ_ONLY, 0, channel.size());
			raf.close();
			channel.close();
		}
		cacheLength = (int) new File(path + "/map_cache_file.idx0").length();
		ByteBuffer buffer = mapIndices.duplicate();
		indexes = buffer.getShort() & 0xFFFF;
		indices = new int[indexes];
		for (int i = 0; i < indexes; i++) {
			indices[i] = buffer.getInt();
		}
	}

	/**
	 * Gets the buffer for the grabbing of a map index.
	 * 
	 * @param index
	 *            The map index.
	 * @return The byte buffer.
	 * @throws IOException
	 *             When an I/O exception occurs.
	 */
	public static ByteBuffer getGrabMapIndex(int index) throws IOException {
		if (!landscapes.containsKey(index)) {
			try (RandomAccessFile raf = new RandomAccessFile(path + "/map_cache_file.idx0", "r");
					FileChannel channel = raf.getChannel()) {
				int size = (int) ((index >= indexes - 1 ? channel.size() : indices[index + 1]) - indices[index]);
				ByteBuffer buffer = channel.map(MapMode.READ_ONLY, indices[index], size);
				raf.close();
				channel.close();
				int length = (index >= indexes - 1 ? buffer.remaining() : (indices[index + 1] - indices[index])) - 2;
				if (length < 1) {
					buffer = ByteBuffer.allocate(0);
				}
				ByteBuffer data = ByteBuffer.allocate(buffer.remaining() + 5);
				data.put((byte) 240) // opcode
				.putShort((short) index).putShort((short) buffer.remaining());
				data.put(buffer);
				landscapes.put(index, data);
			}
		}
		return landscapes.get(index);
	}

	/**
	 * Gets the buffer for a map index.
	 * 
	 * @param index
	 *            The map index.
	 * @return The byte buffer.
	 * @throws IOException
	 *             When an I/O exception occurs.
	 */
	public static ByteBuffer getMapIndex(int index) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(path + "/map_cache_file.idx0", "r");
				FileChannel channel = raf.getChannel()) {
			int size = (int) ((index >= indexes - 1 ? channel.size() : indices[index + 1]) - indices[index]);
			ByteBuffer buffer = channel.map(MapMode.READ_ONLY, indices[index], size);
			raf.close();
			channel.close();
			int length = (index >= indexes - 1 ? buffer.remaining() : (indices[index + 1] - indices[index])) - 2;
			if (length < 1) {
				buffer = ByteBuffer.allocate(0);
			}
			return buffer;
		}
	}

	/**
	 * Gets the landscape byte buffer.
	 * 
	 * @param regionId
	 *            The region id.
	 * @return The landscape buffer.
	 */
	public static byte[] getLandscape(int regionId) {
		int index = LandscapeCache.indexFor(regionId);
		return forId(index);
	}

	/**
	 * Gets the maps for the given id.
	 * 
	 * @param id
	 *            The id.
	 * @return The map data.
	 */
	public static byte[] forId(int id) {
		if (id < 0) {
			return new byte[0];
		}
		try (RandomAccessFile raf = new RandomAccessFile(path + "/map_cache_file.idx0", "r");
				FileChannel channel = raf.getChannel()) {
			int size = (int) ((id >= indexes - 1 ? channel.size() : indices[id + 1]) - indices[id]);
			if (size < 0) {
				System.out.println("Index " + id + " has invalid size!");
				raf.close();
				channel.close();
				return new byte[0];
			}
			MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, indices[id], size);
			raf.close();
			channel.close();
			int length = size - 2;//(id >= indexes - 1 ? buffer.remaining() : (indices[id + 1] - indices[id])) - 2;
			if (length < 1) {
				return new byte[0];
			}
			int decompressedLength = buffer.getShort() & 0xFFFF;
			byte[] b = new byte[length];
			buffer.get(b);
			byte[] data = new byte[decompressedLength];
			try {
				GZipDecompressor.decompress(data, b, 0, b.length);
			} catch (Throwable t) {
				System.err.println("Failed to decompress idx " + id + "!");
				return new byte[0];
			}
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	/**
	 * Gets the index for the region id.
	 * 
	 * @param regionId
	 *            The region id.
	 * @return The index.
	 */
	public static int indexFor(int regionId) {
		int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			return Cache.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
	}

	/**
	 * Gets the reference table buffer.
	 * 
	 * @return The reference table buffer.
	 */
	public static ByteBuffer getReferenceTable() {
		ByteBuffer buffer = ByteBuffer.allocate(mapIndices.remaining() + 10);
		return buffer.put((byte) 251).putInt(LandscapeCache.getMapIndices().remaining()).putInt(cacheLength).put(LandscapeCache.getMapIndices().duplicate());
	}

	/**
	 * Gets the mapIndices.
	 * 
	 * @return The mapIndices.
	 */
	public static ByteBuffer getMapIndices() {
		return mapIndices;
	}

}