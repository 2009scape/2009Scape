package org.arios.cache.misc;
/**
 * A class holding the file containers.
 * @author Dragonkk
 * @author Apache Ah64
 */
public final class FilesContainer extends Container {
	
	/**
	 * The file indexes.
	 */
	private int[] filesIndexes;
	
	/**
	 * The files.
	 */
	private Container[] files;
	
	/**
	 * Construct a new files container.
	 */
	public FilesContainer() {
	
	}

	/**
	 * Set the files.
	 * @param containers The files.
	 */
	public void setFiles(Container[] containers) {
		this.files = containers;
	}

	/**
	 * Get the files.
	 * @return The files.
	 */
	public Container[] getFiles() {
		return files;
	}

	/**
	 * Set the file indexes.
	 * @param containersIndexes The file indexes.
	 */
	public void setFilesIndexes(int[] containersIndexes) {
		this.filesIndexes = containersIndexes;
	}

	/**
	 * Get the file indexes.
	 * @return The file indexes.
	 */
	public int[] getFilesIndexes() {
		return filesIndexes;
	}
}
