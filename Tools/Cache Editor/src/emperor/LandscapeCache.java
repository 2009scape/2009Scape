package emperor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.Map;

import com.alex.store.Store;
import com.alex.util.gzip.GZipCompressor;
import com.alex.util.gzip.GZipDecompressor;

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
	private static final Map<Integer, byte[]> landscapes = new HashMap<>();

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
	 * The file store.
	 */
	private static Store store;

	/**
	 * Initializes the landscape cache stuff.
	 * 
	 * @param path
	 *            The cache path.
	 * @throws Throwable
	 *             When an exception occurs.
	 */
	public static void init(String path, Store store) throws Throwable {
		LandscapeCache.path = path;
		LandscapeCache.store = store;
		try {
			RandomAccessFile raf = new RandomAccessFile(path + "/idx_reference.dat", "r");
			FileChannel channel = raf.getChannel();
			mapIndices = channel.map(MapMode.READ_ONLY, 0, channel.size());
			raf.close();
			channel.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		cacheLength = (int) new File(path + "/map_cache_file.idx0").length();
		ByteBuffer buffer = mapIndices.duplicate();
		indexes = buffer.getShort() & 0xFFFF;
		indices = new int[indexes];
		for (int i = 0; i < indexes; i++) {
			indices[i] = buffer.getInt();
		}
		int count = 0;
		for (int i = 0; i < indexes; i++) {
			byte[] b = forId(i);
			if (b != null && b.length > 0) {
				landscapes.put(i, b);
				count++;
			}
		}
		System.out.println("Succesfully loaded " + count + "/" + indexes + " regions!");
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
		try {
			RandomAccessFile raf = new RandomAccessFile(path + "/map_cache_file.idx0", "r");
			FileChannel channel = raf.getChannel();
			int size = (int) ((id >= indexes - 1 ? channel.size() : indices[id + 1]) - indices[id]);
			if (size < 3) {
				raf.close();
				channel.close();
				//				System.out.println("Index " + id + " has invalid size!");
				channel.close();
				return new byte[0];
			}
			//System.out.println("Size: " + size + "/" + channel.size() + ", index: " + indices[id]);
			MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, indices[id], size);
			raf.close();
			channel.close();
			int length = size - 2;
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

	public static void dump(String path) throws Throwable {
		indices = new int[indexes];
		int offset = 0;
		ByteBuffer mapCache = ByteBuffer.allocate(10_000_000);
		for (int i = 0; i < indexes; i++) {
			indices[i] = offset;
			byte[] bs = landscapes.get(i);
			if (bs != null && bs.length > 1) {
				mapCache.putShort((short) bs.length);
				byte[] b = GZipCompressor.compress(bs);
				mapCache.put(b);
				offset += 2 + b.length;
			}
		}
		mapCache.flip();
		File f = new File(path + "/map_cache_file.idx0");
		if (f.exists()) {
			if (!f.delete()) {
				System.err.println("Could not delete #1!");
			}
		}
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		FileChannel channel = raf.getChannel();
		channel.write(mapCache);
		raf.close();
		channel.close();
		ByteBuffer buffer = ByteBuffer.allocate(100_000);
		buffer.putShort((short) indexes);
		for (int i = 0; i < indexes; i++) {
			buffer.putInt(indices[i]);
		}
		buffer.flip();
		f = new File(path + "/idx_reference.dat");
		if (f.exists()) {
			if (!f.delete()) {
				System.err.println("Could not delete #2!");
				f = new File(path + "/conflict-idx_reference.dat");
			}
		}
		raf = new RandomAccessFile(f, "rw");
		channel = raf.getChannel();
		channel.write(buffer);
		raf.close();
		channel.close();
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
		return store.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
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

	/**
	 * Gets the landscapes mapping.
	 * @return The mapping.
	 */
	public static Map<Integer, byte[]> getLandscapes() {
		return landscapes;
	}

}