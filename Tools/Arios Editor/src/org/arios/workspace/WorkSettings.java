package org.arios.workspace;

import java.awt.Dimension;
import java.io.File;

/**
 * The settings for the work space.
 * @author Vexia
 *
 */
public class WorkSettings {
	
	/**
	 * The size of the work station.
	 */
	public static final Dimension SIZE = new Dimension(1280, 800 - 100);
	
	/**
	 * The version of the program.
	 */
	public static final double VERSION = 1.0;

	/**
	 * The cache path.
	 */
	private String cachePath;
	
	/**
	 * The store path.s
	 */
	private String storePath;
	
	/**
	 * Constructs a new {@Code WorkSettings} {@Code Object}
	 * @param cachePath the cache path.
	 * @param storePath the store path.
	 */
	public WorkSettings(String cachePath, String storePath) {
		this.cachePath = cachePath;
		this.storePath = storePath;
	}
	
	/**
	 * Creates a default work space setting.
	 * @return the settings.s
	 */
	public static WorkSettings create() {
		File file = new File("");
		String store = file.getAbsolutePath();
		System.err.println(store);
		store = store.substring(0, store.lastIndexOf(File.separator) + 1) + "Source" + File.separator + "data" + File.separator + "store";
		return new WorkSettings("data" + File.separator + "cache", store);//System.getProperty("user.home") + "/Dropbox/Arios RSPS/Source/data/store");
	}

	/**
	 * Gets the cachePath.
	 * @return the cachePath.
	 */
	public String getCachePath() {
		return cachePath;
	}

	/**
	 * Sets the cachePath.
	 * @param cachePath the cachePath to set
	 */
	public void setCachePath(String cachePath) {
		this.cachePath = cachePath;
	}

	/**
	 * Gets the storePath.
	 * @return the storePath.
	 */
	public String getStorePath() {
		return storePath;
	}

	/**
	 * Sets the storePath.
	 * @param storePath the storePath to set
	 */
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	
}
