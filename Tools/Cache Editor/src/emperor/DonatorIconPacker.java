package emperor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.alex.loaders.images.IndexedColorImageFile;
import com.alex.store.Store;

/**
 * Handles the donator icon packing.
 * @author Vexia
 *
 */
public final class DonatorIconPacker {

	/**
	 * The icons to pack.
	 */
	private static String[] ICONS = new String[] {"green", "red", "yellow", "blue", "orange", "pink", "purple", "brown", "world_announce", "rainbow", "whip_icon"};

	/**
	 * The path.
	 */
	private static final String PATH = "./icons";

	/**
	 * The icon dump.
	 */
	private static final String DUMP_PATH = "./icon_dump";

	/**
	 * The archive id.
	 */
	private static final int ACRHIVE_ID = 815;

	/**
	 * The starting index.
	 */
	private static final int START_INDEX = 2;

	/**
	 * The index color image file.
	 */
	private static IndexedColorImageFile colorFile;

	/**
	 * The store to work with.
	 */
	private static Store store;

	/**
	 * Runs the donator icon packer.
	 * @param args the arguments.
	 * @throws IOException the exception.
	 */
	public static void main(String...args) throws IOException {
		setStore(new Store("./498/"));
		colorFile = new IndexedColorImageFile(store, ACRHIVE_ID, 0);
		//colorFile.replaceImage(ImageIO.read(new File("logo.png")), 0);
		colorFile.addImage(ImageIO.read(new File("nazi.png")));
		//colorFile.delete(1);
		//packAll();
		dump();
		save();
	}

	/**
	 * Packs all the icons.
	 * @throws IOException the exception.
	 */
	public static void packAll() throws IOException {
		for (int i = 0; i < ICONS.length; i++) {
			pack(i, getImage(ICONS[i]));
		}
	}

	/**
	 * Packs an image to the cache.
	 * @param index the index.
	 * @param image the image.
	 */
	public static void pack(int index, BufferedImage image) {
		if (image == null) {
			System.out.println("Image null at " + index + "!");
			return;
		}
		String name = ICONS[index];
		int realIndex = START_INDEX + index;
		int indexPacked = 0;
		boolean replace = false;
		if (realIndex < colorFile.getImages().length) {
			colorFile.replaceImage(image, realIndex);
			replace = true;
		} else {
			indexPacked = colorFile.addImage(image);
		}
		save();
		System.out.println("Packing icon with name - " + name + ", chat index=" + realIndex + ", indexPacked=" + indexPacked + ", replace="  + replace + "!");
	}

	/**
	 * Dumps the icon
	 * @throws IOException the exception.
	 */
	public static void dump() throws IOException {
		dumpIcons(DUMP_PATH);
	}

	/**
	 * Dumps the icons to a path.
	 * @param path the path.
	 * @throws IOException the exception.
	 */
	public static void dumpIcons(String path) throws IOException {
		int index = 0;
		System.out.println("Size=" + colorFile.getImages().length);
		for (BufferedImage image : colorFile.getImages()) {
			String name = path + "/icon-" + index++ + ".png";
			ImageIO.write(image, "PNG", new File(name));
			System.out.println("Dumping icon - " + name);
		}
	}

	/**
	 * Saves the index.
	 */
	public static void save() {
		store.getIndexes()[8].putFile(ACRHIVE_ID, 0, colorFile.encodeFile());
	}

	/**
	 * Gets a buffered image.
	 * @param name the name.
	 * @return the image.
	 * @throws IOException the exception.
	 */
	public static BufferedImage getImage(String name) throws IOException {
		return ImageIO.read(new File(PATH + "/" + name + ".png"));
	}

	/** 
	 * Gets the store.
	 * @return the store
	 */
	public static Store getStore() {
		return store;
	}

	/** 
	 * Sets the store.
	 * @param store the store to set
	 */
	public static void setStore(Store store) {
		DonatorIconPacker.store = store;
	}
}
