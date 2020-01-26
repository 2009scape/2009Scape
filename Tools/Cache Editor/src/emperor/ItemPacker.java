package emperor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Index;
import com.alex.store.Store;

/**
 * Packs items.
 * @author Vexia
 *
 */
public class ItemPacker {

	/**
	 * The store to pack to.
	 */
	private static Store store;

	/**
	 * The main method.
	 * @param args the arguments.
	 * @throws IOException the exception.
	 */
	public static void main(String...args) throws IOException {
		store = new Store("./498/");
		String modelName = "models/44590.dat";
		packItem(modelName, "Dragon claws");
	}
	
	/**
	 * Gets the size.
	 * @return the size.
	 */
	public static int getSize() {
		Index index = store.getIndexes()[19];
		int lastId = index.getLastArchiveId();
		int fileSize = index.getFile(lastId).length;
		System.err.println(fileSize);
		System.err.println(index.getValidFilesCount(lastId));
		int size = lastId * 256 + fileSize;
		return size;//13247, 51, 191

	}

	/**
	 * Packs an item.
	 * @param modelName the model name.
	 * @param itemName the name.
	 * @throws IOException the exception.
	 */
	public static void packItem(String modelName, String itemName) throws IOException {
		ItemDefinitions def = buildItem(modelName, itemName);
		System.out.println("Attempting to pack the model - " + modelName + ", for item name - " + itemName);
		packCustomItem(def);
		System.out.println("Item packed.");
	}

	/**
	 * Packs a custom model.
	 * @param data the data.
	 * @return the model.
	 */
	public static int packCustomModel(byte[] data) {
		int archiveId = store.getIndexes()[19].getLastArchiveId()+1;
		if(store.getIndexes()[19].putFile(archiveId, 0, data)) {
			return archiveId;
		}
		return -1;
	}

	/**
	 * Builds an item.
	 * @param modelName the name.
	 * @param itemName the item name.
	 * @return the def.
	 * @throws IOException
	 */
	public static ItemDefinitions buildItem(String modelName, String itemName) throws IOException {
		int modelId = packCustomModel(getBytesFromFile(new File(modelName)));
		ItemDefinitions definition =  ItemDefinitions.getItemDefinition(store, 3101);
		definition.setName(itemName);
		definition.femaleEquipModelId1 = modelId;
		definition.maleEquipModelId1 = modelId;
		definition.invModelId = modelId;
		return definition;
	}
	
	/** 
	 * Packs the custom item.
 	 * @param cache the cache. 
	 * @param id the id.
	 * @param def the def.
	 */
	public static void packCustomItem(ItemDefinitions def) {
		int id = 13248;
		store.getIndexes()[19].putFile(id >>> 8, 0xff & id, def.encode());
	}

	/**
	 * Gets all the bytes from the file.
	 * @param file the file.
	 * @return the bytes.
	 * @throws IOException the exception.
	 */
	@SuppressWarnings("resource")
	public static byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
	    // Get the size of the file
	    long length = file.length();
	    // You cannot create an array using a long type.
	    // It needs to be an int type.
	    // Before converting to an int type, check
	    // to ensure that file is not larger than Integer.MAX_VALUE.
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];
	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }
	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
}
