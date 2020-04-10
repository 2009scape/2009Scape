package org.arios.cache;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.Map;

import org.arios.cache.misc.ByteBufferUtils;
import org.arios.workspace.WorkSpace;

/**
 * The server data storage.
 * @author Emperor
 *
 */
public final class ServerStore {

	/**
	 * The storage.
	 */
	private static Map<String, StoreFile> storage = new HashMap<>();
	
	/**
	 * If the store has initialized.
	 */
	private static boolean initialized;
	
	/**
	 * Initializes the store.
	 */
	public static void init() {
		storage = new HashMap<>();
		File file = new File(WorkSpace.getWorkSpace().getSettings().getStorePath() + "/static_cache.arios");
		if (file.exists()) {
			System.out.println("Loaded store " + file.getAbsolutePath() + "...");
			try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
				FileChannel channel = raf.getChannel();
				ByteBuffer buffer = channel.map(MapMode.READ_WRITE, 0, channel.size());
				int size = buffer.getShort() & 0xFFFF;
				for (int i = 0; i < size; i++) {
					StoreFile store = new StoreFile();
					String archive = ByteBufferUtils.getString(buffer);
					if (storage.containsKey(archive)) {
						throw new IllegalStateException("Duplicate archive found - archive=" + archive + "!");
					}
					byte[] data = new byte[buffer.getInt()];
					buffer.get(data);
					store.setData(data);
					storage.put(archive, store);
				}
				if (buffer.hasRemaining()) {
					throw new IllegalStateException("Unable to read full static store! " + buffer.remaining());
				}
				channel.close();
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		initialized = true;
	}
	
	/**
	 * Used for writing the static store.
	 * @param path The path.
	 */
	public static void createStaticStore(String path) {
		write(path + "/static_cache.arios", false);
	}
	
	/**
	 * Used for writing the static store.
	 */
	public static void createStaticStore() {
		write(WorkSpace.getWorkSpace().getSettings().getStorePath(), false);
	}
	
	/**
	 * Writes all the dynamic storage files (on server termination).
	 * @param path The path.
	 */
	public static void dump(String path) {
		write(path + "/dynamic_cache.arios", true);
	}
	
	/**
	 * Writes the store file to the given file path.
	 * @param filePath The file path.
	 * @param dynamic If the dynamic store is being written.
	 */
	public static void write(String filePath, boolean dynamic) {
		if (!initialized) {
			throw new IllegalStateException("Server store has not been initialized!");
		}
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
		ByteBuffer buffer = ByteBuffer.allocate(1 << 28);
		buffer.putShort((short) 0);
		int size = 0;
		for (String archive : storage.keySet()) {
			StoreFile file = storage.get(archive);
			if (file.isDynamic() != dynamic) {
				continue;
			}
			size++;
			ByteBuffer buf = file.data();
			ByteBufferUtils.putString(archive, buffer);
			buffer.putInt(buf.remaining());
			buffer.put(buf);
		}
		buffer.putShort(0, (short) size);
		buffer.flip();
		try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
			FileChannel channel = raf.getChannel();
			channel.write(buffer);
			channel.close();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the archive data.
	 * @param archive The archive id.
	 * @param buffer The readable buffer.
	 */
	public static void setArchive(String archive, ByteBuffer buffer) {
		setArchive(archive, buffer, true);
	}
	
	/**
	 * Sets the archive data.
	 * @param archive The archive id.
	 * @param buffer The readable buffer.
	 * @param dynamic If the data changes during server runtime.
	 */
	public static void setArchive(String archive, ByteBuffer buffer, boolean dynamic) {
		byte[] data = new byte[buffer.remaining()];
		buffer.get(data);
		setArchive(archive, data, dynamic, true);
	}
	
	/**
	 * Sets the archive data.
	 * @param archive The archive index.
	 * @param data The archive data.
	 * @param dynamic If the data changes during server runtime.
	 */
	public static void setArchive(String archive, byte[] data, boolean dynamic) {
		setArchive(archive, data, dynamic, true);
	}
	
	
	/**
	 * Sets the archive data.
	 * @param archive The archive index.
	 * @param data The archive data.
	 * @param dynamic If the data changes during server runtime.
	 * @param overwrite If the archive should be overwritten.
	 */
	public static void setArchive(String archive, byte[] data, boolean dynamic, boolean overwrite) {
		StoreFile file = storage.get(archive);
		if (file == null) {
			storage.put(archive, file = new StoreFile());
		}
		else if (!overwrite) {
			throw new IllegalStateException("Already contained archive " + archive + "!");
		}
		file.setDynamic(dynamic);
		file.setData(data);
	}
	
	/**
	 * Gets the archive data for the given archive id.
	 * @param archive The archive index.
	 * @return The archive data.
	 */
	public static ByteBuffer getArchive(String archive) {
		return get(archive).data();
	}
	
	/**
	 * Sets the archive file.
	 * @param archive The archive.
	 * @param file The file.
	 */
	public static void set(String archive, StoreFile file) {
		storage.put(archive, file);
	}
	
	/**
	 * Gets the store file for the given archive.
	 * @param archive The archive id.
	 * @return The store file.
	 */
	public static StoreFile get(String archive) {
		return storage.get(archive);
	}
}