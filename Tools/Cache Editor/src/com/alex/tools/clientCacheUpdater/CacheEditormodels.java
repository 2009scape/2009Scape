package com.alex.tools.clientCacheUpdater;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.alex.loaders.images.IndexedColorImageFile;
import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Index;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

public class CacheEditormodels {

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
	
	public static int packCustomModel(Store cache, byte[] data) {
		//recommended id 80000+ since rs uses all ids till 66000
		int archiveId = cache.getIndexes()[7].getLastArchiveId()+1;
		if(cache.getIndexes()[7].putFile(archiveId, 0, data))
				return archiveId;
		System.out.println("Failing packing model "+archiveId);
		return -1;
	}
	
	public static void packCustomItems(Store cache) throws IOException {
		int modelID = packCustomModel(cache, getBytesFromFile(new File("pkcapefinalb.dat")));
		ItemDefinitions pkCape = ItemDefinitions.getItemDefinition(cache, 9747);
		pkCape.setName("PK Cape");
		//donatorCape.getInventoryOptions()[2] = "Customise";
		pkCape.femaleEquipModelId1 = modelID;
		pkCape.maleEquipModelId1 = modelID;
		pkCape.invModelId = modelID;
		pkCape.resetModelColors();
		packCustomItem(cache, 30000, pkCape);
		
		/*int wearModelID = packCustomModel(cache, getBytesFromFile(new File("718/lightSaber/wear.dat")));
		int invModelID = packCustomModel(cache, getBytesFromFile(new File("718/lightSaber/inv.dat")));
		ItemDefinitions lightSaber = ItemDefinitions.getItemDefinition(cache, 2402);
		lightSaber.setName("Light Saber");
		lightSaber.getInventoryOptions()[2] = "Customise";
		lightSaber.femaleEquipModelId1 = wearModelID;
		lightSaber.maleEquipModelId1 = wearModelID;
		lightSaber.invModelId = invModelID;
		lightSaber.resetModelColors();
		packCustomItem(cache, 29998, lightSaber);*/
	}
	
	public static void packCustomItem(Store cache, int id, ItemDefinitions def) {
		cache.getIndexes()[19].putFile(id >>> 8, 0xff & id, def.encode());
		
	}
	
	
	
	/*
	 * divides bg
	 */
	public static void divideBackgrounds() throws IOException {
		BufferedImage background = ImageIO.read(new File("718/sprites/bg.jpg"));
		int id = 4139;
		int sx = background.getWidth() / 4;
		int sy = background.getHeight() / 2;
		for(int y = 0; y < 2; y++) {
			for(int x = 0; x < 4; x++) {
				BufferedImage part = background.getSubimage(x * sx, y * sy, sx, sy);
				ImageIO.write(part, "gif", new File("718/sprites/bg/"+(id++)+".gif"));
			}
		}
		BufferedImage load = ImageIO.read(new File("718/sprites/load.png"));
		id = 3769;
		sx = load.getWidth() / 2;
		sy = load.getHeight() / 2;
		for(int y = 0; y < 2; y++) {
			for(int x = 0; x < 2; x++) {
				BufferedImage part = load.getSubimage(x * sx, y * sy, sx, sy);
				ImageIO.write(part, "png", new File("718/sprites/load/"+id+".png"));
				ImageIO.write(part, "gif", new File("718/sprites/load/"+(id++)+".gif"));
			}
		}
	}
	
	public static byte[] getImage(File file) throws IOException {
		ImageOutputStream stream = ImageIO.createImageOutputStream(file);
		byte[] data = new byte[(int) stream.length()];
		stream.read(data);
		return data;
	}
	
	public static void main(String[] args) throws IOException {
		boolean beta = false;
		boolean addNewItemDefinitions = false; //only needed once
		boolean divideBackgrounds = false; //only needed once
		if(divideBackgrounds)
			divideBackgrounds();
		Store rscache = new Store(beta ? "718/rsCacheBeta/" : "718/rscache/");
		Store cache = new Store(beta ? "718/cacheBeta/" : "718/cache/");
		boolean result;
		cache.resetIndex(7, false, false, Constants.GZIP_COMPRESSION);
		for(int i = 0; i < cache.getIndexes().length; i++) {
			if(i != 3 //interfaces
					&& i != 5 //maps
					&& i != 12) //client scripts
				{
				result = cache.getIndexes()[i].packIndex(rscache, true);
				System.out.println("Packed index archives: "+i+", "+result);
			}
		}
		if(addNewItemDefinitions) {
			System.out.println("Packing old item definitions...");
			Store cache667 = new Store("cache667/", false);
			int currentSize = 30000;//Utils.getItemDefinitionsSize(cache);
			int oldSize = Utils.getItemDefinitionsSize(cache667);
			for(int i = currentSize ; i < currentSize+oldSize; i++) {
				int newItemId = i; 
				int oldItemId = i - currentSize;
				cache.getIndexes()[19].putFile(newItemId >>> 8, 0xff & newItemId, Constants.GZIP_COMPRESSION, cache667.getIndexes()[19].getFile(oldItemId >>> 8, 0xff & oldItemId), null, false, false, -1, -1);
			}
			result = cache.getIndexes()[19].rewriteTable();
			System.out.println("Packed old item definitions: "+result);
		}
	}
}		