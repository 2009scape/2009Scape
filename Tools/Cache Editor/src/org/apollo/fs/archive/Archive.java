package org.apollo.fs.archive;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apollo.fs.util.ByteBufferUtil;
import org.apollo.fs.util.CompressionUtil;

/**
 * Represents an archive.
 * @author Graham
 */
public final class Archive {

	/**
	 * Decodes the archive in the specified buffer.
	 * @param buffer The buffer.
	 * @return The archive.
	 * @throws IOException if an I/O error occurs.
	 */
	public static Archive decode(ByteBuffer buffer) throws IOException {
		int extractedSize = ByteBufferUtil.readUnsignedTriByte(buffer);
		int size = ByteBufferUtil.readUnsignedTriByte(buffer);
		boolean extracted = false;

		if (size != extractedSize) {
			byte[] compressed = new byte[size];
			byte[] uncompressed = new byte[extractedSize];
			buffer.get(compressed);
			CompressionUtil.unbzip2(compressed, uncompressed);
			buffer = ByteBuffer.wrap(uncompressed);
			extracted = true;
		}

		int entries = buffer.getShort() & 0xFFFF;
		int[] identifiers = new int[entries];
		int[] extractedSizes = new int[entries];
		int[] sizes = new int[entries];

		for (int i = 0; i < entries; i++) {
			identifiers[i] = buffer.getInt();
			extractedSizes[i] = ByteBufferUtil.readUnsignedTriByte(buffer);
			sizes[i] = ByteBufferUtil.readUnsignedTriByte(buffer);
		}

		ArchiveEntry[] entry = new ArchiveEntry[entries];

		for (int i = 0; i < entries; i++) {
			ByteBuffer entryBuffer = ByteBuffer.allocate(extractedSizes[i]);
			if (!extracted) {
				byte[] compressed = new byte[sizes[i]];
				byte[] uncompressed = new byte[extractedSizes[i]];
				buffer.get(compressed);
				CompressionUtil.unbzip2(compressed, uncompressed);
				entryBuffer = ByteBuffer.wrap(uncompressed);
			}
			entry[i] = new ArchiveEntry(identifiers[i], entryBuffer);
		}

		return new Archive(entry);
	}

	/**
	 * The entries in this archive.
	 */
	private final ArchiveEntry[] entries;

	/**
	 * Creates a new archive.
	 * @param entries The entries in this archive.
	 */
	public Archive(ArchiveEntry[] entries) {
		this.entries = entries;
	}

	/**
	 * Gets an entry by its name.
	 * @param name The name.
	 * @return The entry.
	 * @throws FileNotFoundException if the file could not be found.
	 */
	public ArchiveEntry getEntry(String name) throws FileNotFoundException {
		int hash = 0;
		name = name.toUpperCase();
		for (int i = 0; i < name.length(); i++) {
			hash = (hash * 61 + name.charAt(i)) - 32;
		}
		for (ArchiveEntry entry : entries) {
			if (entry.getIdentifier() == hash) {
				return entry;
			}
		}
		throw new FileNotFoundException();
	}

}
