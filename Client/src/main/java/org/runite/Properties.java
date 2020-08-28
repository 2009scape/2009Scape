package org.runite;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;

/**
 * Holds client related properties.
 * @author Emperor
 *
 */
public final class Properties {

	/**
	 * If the modern hotkey layout should be used.
	 */
	private boolean modernHotkeys;
	
	/**
	 * If the theme song should be played.
	 */
	private boolean playThemeSong = true;
	
	/**
	 * If we have notepad wrapping.
	 */
	private boolean notepadWrap = true;
	
	/**
	 * The properties instance.
	 */
	private static Properties singleton;
	
	/**
	 * If we should remember the player.
	 */
	private boolean rememberMe;
	
	/**
	 * If instant attack is enabled.
	 */
	private boolean instantAttack = true;
	
	/**
	 * Constructs a new {@code Properties} {@code Object}.
	 */
	private Properties() {
		/*
		 * empty.
		 */
	}
	
	/**
	 * Loads the properties.
	 * @param file The file.
	 */
	private void load(File file) {
		try {
			RandomAccessFile f = new RandomAccessFile(file, "rw");
			ByteBuffer buffer = f.getChannel().map(MapMode.READ_WRITE, 0, f.length());
			int opcode;
			while ((opcode = buffer.get() & 0xFF) != 0) {
				switch (opcode) {
				case 1:
					modernHotkeys = true;
					break;
				case 2:
					playThemeSong = false;
					break;
				case 3:
					notepadWrap = false;
					break;
				case 4:
					rememberMe = true;
					break;
				case 5:
					instantAttack = false;
					break;
				}
			}
			f.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the properties.
	 */
	public void save() {
		ByteBuffer buffer = ByteBuffer.allocate(512);
		if (modernHotkeys) {
			buffer.put((byte) 1);
		}
		if (!playThemeSong) {
			buffer.put((byte) 2);
		}
		if (!notepadWrap) {
			buffer.put((byte) 3);
		}
		if (rememberMe) {
			buffer.put((byte) 4);
		}
		if (!instantAttack) {
			buffer.put((byte) 5);
		}
		buffer.put((byte) 0);//EOF
		buffer.flip();
		try {
			RandomAccessFile f = new RandomAccessFile(new File(Configurations.getCachePath() + "/client.props"), "rw");
			f.getChannel().write(buffer);
			f.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the instance.
	 * @return The instance.
	 */
	public static Properties get() {
		if (singleton == null) {
			singleton = new Properties();
			File file = new File(Configurations.getCachePath() + "/client.props");
			if (file.exists()) {
				singleton.load(file);
			}
		}
		return singleton;
	}

	/**
	 * @return the modernHotkeys
	 */
	public boolean isModernHotkeys() {
		return modernHotkeys;
	}

	/**
	 * @param modernHotkeys the modernHotkeys to set
	 */
	public void setModernHotkeys(boolean modernHotkeys) {
		this.modernHotkeys = modernHotkeys;
	}

	/**
	 * Checks if instantAttack is {@code true}.
	 * @return {@code True} if so.
	 */
	public boolean isInstantAttack() {
		return instantAttack;
	}

	/**
	 * Sets the instantAttack value.
	 * @param instantAttack The instantAttack to set.
	 */
	public void setInstantAttack(boolean instantAttack) {
		this.instantAttack = instantAttack;
	}
	
}