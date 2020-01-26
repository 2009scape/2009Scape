package org.arios.workspace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.arios.cache.Cache;
import org.arios.cache.ServerStore;
import org.arios.workspace.editor.EditorTab;
import org.arios.workspace.editor.EditorType;


/**
 * The users work space.
 * @author Vexia
 *
 */
public final class WorkSpace {

	/**
	 * The workspace singleton.
	 */
	private static WorkSpace WORKSPACE = new WorkSpace();

	/**
	 * The work settings.
	 */
	private final WorkSettings settings;

	/**
	 * The working frame.
	 */
	private WorkFrame frame;

	/**
	 * The current editor.
	 */
	private EditorTab editor;

	/**
	 * Constructs a new {@code WorkSpace} {@code Object}
	 * @param settings th∂e settings.
	 */
	public WorkSpace(WorkSettings settings) {
		this.settings = settings;
	}

	/**
	 * Constructs a new {@Code WorkSpace} {@Code Object}
	 */
	public WorkSpace() {
		this(WorkSettings.create());
	}

	/**
	 * Initializes this work space.
	 * @return the work space.
	 * @throws Throwable the throwable.
	 */
	public WorkSpace init() throws Throwable {
		setFrame(new WorkFrame());
		Cache.init();
		ServerStore.init();
		EditorType.init();
		System.out.println("Initialized the Arios editor!");
		return this;
	}

	/**
	 * saves the editor.
	 */
	public void save() {
		save(false);
	}

	/**
	 * Saves the editors.
	 */
	public void save(boolean force) {
		if (!force && frame.getEditors().size() == 0) {
			JOptionPane.showMessageDialog(null, "Your workspace is empty.");
			return;
		}
		/*String[] types = new String[] {"bronze", "iron", "steel", "black", "blurite", "mithril", "adamant", "rune", "dragon"};
		
		for (Node<?> i : EditorType.ITEM.getTab().getNodes().values()) {
			Item item = (Item) i;
			for (String s : types) {
				if (item.getName().toLowerCase().startsWith(s) && item.getName().contains("sword") && !item.getName().contains("2h")) {
					System.err.println(item.getName());
					item.setConfig("attack_audios", new Short[] {2517, 2517, 2500, 2517});
				}
			}
		}
		for (Node<?> i : EditorType.NPC.getTab().getNodes().values()) {
			NPC n = (NPC) i;
			if (n.getId() == 6263 || n.getId() == 6261 || n.getId() == 6265) {
				System.err.println(n);
				NPCDrop[] t = n.getDrobTable(TableType.MAIN);
				for (NPCDrop d : t) {
					if (d.getItemId() >= 11710) {
						d.setSetRate(5400);
					}
				}
			}
		}*/
		backup();
		for (EditorType type : frame.getEditors()) {
			type.getTab().preSave();
			type.getTab().save();
		}
		ServerStore.createStaticStore(settings.getStorePath());
		System.out.println("Saved the workspace!");
	}

	/**
	 * Backs up the cache.
	 */
	private void backup() {
		/*File file = new File("./data/backup/");
		if (!file.exists()) {
			file.mkdir();
		}
		File cache = new File(settings.getStorePath() + "/static_cache.arios");
		copyFile(cache, new File("./data/backup/static_cache_" + (new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + ".arios")));*/
	}

	/**
	 * Replaces a directory with another.
	 * @param from the path to copy.
	 * @param toDirectory the path to copy to.
	 */
	public void replaceCache(String from, String toDirectory) {
		File fileFrom = new File(from);
		List<File> files = new ArrayList<>();
		if (fileFrom.isDirectory()) {
			files.addAll(Arrays.asList(fileFrom.listFiles()));
		} else {
			files.add(fileFrom);
		}
		for (File file : files) {
			copyFile(file, new File(toDirectory + "/" + file.getName()));
		}
		JOptionPane.showMessageDialog(null, "Replaced a cache from " + from + " to " + toDirectory + "!");
	}

	/**
	 * Copies a file.
	 * @param in The file to be copied.
	 * @param out The file to copy to.
	 */
	private static void copyFile(File in, File out) {
		try (FileChannel channel = new FileInputStream(in).getChannel()) {
			try (FileChannel output = new FileOutputStream(out).getChannel()) {
				channel.transferTo(0, channel.size(), output);
				channel.close();
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the settings.
	 * @return the settings.
	 */
	public WorkSettings getSettings() {
		return settings;
	}

	/**
	 * Gets the frame.
	 * @return the frame.
	 */
	public WorkFrame getFrame() {
		return frame;
	}

	/**
	 * Sets the frame.
	 * @param frame the frame to set
	 */
	public void setFrame(WorkFrame frame) {
		this.frame = frame;
	}

	/**
	 * Gets the work space.
	 * @return the work space.
	 */
	public static WorkSpace getWorkSpace() {
		return WORKSPACE;
	}

	/**
	 * Sets the work space.
	 * @param workspace the space.
	 */
	public static void setWorkSpace(WorkSpace workSpace) {
		WORKSPACE = workSpace;
	}

	/**
	 * Gets the editor.
	 * @return the editor.
	 */
	public EditorTab getEditor() {
		return editor;
	}

	/**
	 * Sets the editor.
	 * @param editor the editor to set
	 */
	public void setEditor(EditorTab editor) {
		this.editor = editor;
	}

}
