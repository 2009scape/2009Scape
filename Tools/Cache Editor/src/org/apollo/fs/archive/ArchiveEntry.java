package org.apollo.fs.archive;

import java.nio.ByteBuffer;

/**
 * Represents a single entry in an {@link Archive}.
 * @author Graham
 */
public final class ArchiveEntry {

	/**
	 * The identifier of this entry.
	 */
	private final int identifier;

	/**
	 * The buffer of this entry.
	 */
	private final ByteBuffer buffer;

	/**
	 * Creates a new archive entry.
	 * @param identifier The identifier.
	 * @param buffer The buffer.
	 */
	public ArchiveEntry(int identifier, ByteBuffer buffer) {
		this.identifier = identifier;
		this.buffer = buffer.asReadOnlyBuffer();
	}

	/**
	 * Gets the identifier of this entry.
	 * @return The identifier of this entry.
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * Gets the buffer of this entry.
	 * @return This buffer of this entry.
	 */
	public ByteBuffer getBuffer() {
		return buffer.duplicate();
	}

}
