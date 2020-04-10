package com.alex.tools.clientCacheUpdater;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Index;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

public class CacheEditor {

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
		int archiveId = cache.getIndexes()[19].getLastArchiveId()+1;
		if(cache.getIndexes()[19].putFile(archiveId, 0, data))
				return archiveId;
		System.out.println("Failing packing model "+archiveId);
		return -1;
	}
	
	public static void packCustomItems(Store cache) throws IOException {
		int modelID = packCustomModel(cache, getBytesFromFile(new File("44590.dat")));
		if (modelID == -1) {
			System.err.println("Error! Model id =-1!");
			return;
		}
		ItemDefinitions donatorCape = ItemDefinitions.getItemDefinition(cache, 9747);
		donatorCape.setName("Dragon Claws");
		donatorCape.femaleEquipModelId1 = modelID;
		donatorCape.maleEquipModelId1 = modelID;
		donatorCape.invModelId = modelID;
		donatorCape.resetModelColors();
		packCustomItem(cache, 29999, donatorCape);
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
		packCustomItems(new Store("./498/"));
		/*boolean beta = false;
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
		
		/*System.out.println("Packing custom items...");
		packCustomItems(cache);
		
		System.out.println("Adding new interfaces...");
		for(int i = cache.getIndexes()[3].getLastArchiveId()+1; i <= rscache.getIndexes()[3].getLastArchiveId(); i++) {
			if(i == 548 || i == 746)
				continue;
			if(rscache.getIndexes()[3].archiveExists(i))
				cache.getIndexes()[3].putArchive(i, rscache, false, false);
		}
		result = cache.getIndexes()[3].rewriteTable();
		System.out.println("Packed new interfaces: "+result);*/
		
		//System.out.println("Adding custom sprites...");
	
		//adds icons
		//IndexedColorImageFile iconsFile = new IndexedColorImageFile(cache, 1455, 0);
		//BufferedImage icon = ImageIO.read(new File("1455.png"));
		//System.out.println("Added icon: "+iconsFile.addImage(icon)+".");
		//BufferedImage icon2 = ImageIO.read(new File("1455f.png"));
		//System.out.println("Added icon2: "+iconsFile.addImage(icon2)+".");
		//BufferedImage icon3 = ImageIO.read(new File("crown_green.gif"));
		//System.out.println("Added icon3: "+iconsFile.addImage(icon3)+".");
		//BufferedImage icon4 = ImageIO.read(new File("1455_11.png"));
		//System.out.println("Added icon4: "+iconsFile.addImage(icon4)+".");
		//result = cache.getIndexes()[8].putFile(1455, 0, Constants.GZIP_COMPRESSION, iconsFile.encodeFile(), null, false, false, -1, -1);
		//System.out.println("Added icons: "+result);
		
		//result = cache.getIndexes()[8].putFile(2173, 0,  Constants.GZIP_COMPRESSION, 
				//new IndexedColorImageFile(ImageIO.read(new File("2173.png"))).encodeFile()
				//, null, false, false, -1, -1);
		//System.out.println("Added matrix flag: "+result);
		
		//result = cache.getIndexes()[8].putFile(2498, 0,  Constants.GZIP_COMPRESSION, 
				//new IndexedColorImageFile(ImageIO.read(new File("718/sprites/logo.png"))).encodeFile()
				//, null, false, false, -1, -1);
		//System.out.println("Added matrix logo: "+result);
		
		//Login Background
		/*
		for(int i = 4139; i <= 4146; i++) {
			result = cache.getIndexes()[8].putFile(i, 0,  Constants.GZIP_COMPRESSION, 
					new IndexedColorImageFile(ImageIO.read(new File("718/sprites/bg/"+i+".png"))).encodeFile()
					, null, false, false, -1, -1);
		}
		System.out.println("Added noregret background: "+result);
		*s
		//Loading Background
		for(int i = 0; i < 4; i++) {
			int realid = 3769 + i;
			byte[] sprite = new IndexedColorImageFile(ImageIO.read(new File("718/sprites/load/"+realid+".gif"))).encodeFile();
			byte[] image = getImage(new File("718/sprites/load/"+realid+".png"));
			
			int[] ids = new int[] {3769 + i
					, 3779 + i
					, 3783 + (i >= 2 ? (i-2) : i + 2)
					, 8494 + (i >= 2 ? (i-2) : i + 2)
					, 8498 + (i >= 2 ? (i-2) : i + 2)};
			for(int id : ids) {
				result = cache.getIndexes()[8].putFile(id, 0,  Constants.GZIP_COMPRESSION, sprite, null, false, false, -1, -1);
				result = cache.getIndexes()[32].putFile(id, 0, Constants.GZIP_COMPRESSION, image, null, false, false, -1, -1);
				result = cache.getIndexes()[34].putFile(id, 0, Constants.GZIP_COMPRESSION, image, null, false, false, -1, -1);
			}
		}
		//System.out.println("Added  Loading background: "+result);
		
		result = cache.getIndexes()[8].rewriteTable();
		result = cache.getIndexes()[32].rewriteTable();
		result = cache.getIndexes()[34].rewriteTable();
		System.out.println("Added custom sprites: "+result); 
		
		/*RSXteas.loadUnpackedXteas();
		System.out.println("Updating Maps.");
		for(int regionId = 0; regionId < 30000; regionId++) {
			int regionX = (regionId >> 8) * 64;
			int regionY = (regionId & 0xff) * 64;	
			String name = "m"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			byte[] data = rscache.getIndexes()[5].getFile(rscache.getIndexes()[5].getArchiveId(name));
			if(data != null) {
				result = addMapFile(cache.getIndexes()[5], name, data);
				System.out.println(name+", "+result);
			}
			name = "um"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rscache.getIndexes()[5].getFile(rscache.getIndexes()[5].getArchiveId(name));
			if(data != null) {
				result = addMapFile(cache.getIndexes()[5], name, data);
				System.out.println(name+", "+result);
			}
			int[] xteas = RSXteas.getXteas(regionId);
			name = "l"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rscache.getIndexes()[5].getFile(rscache.getIndexes()[5].getArchiveId(name), 0, xteas);
			if(data != null) {
				result = addMapFile(cache.getIndexes()[5], name, data);
				System.out.println(name+", "+result);
			}
			name = "ul"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rscache.getIndexes()[5].getFile(rscache.getIndexes()[5].getArchiveId(name), 0, xteas);
			if(data != null) {
				result = addMapFile(cache.getIndexes()[5], name, data);
				System.out.println(name+", "+result);
			}
			name = "n"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rscache.getIndexes()[5].getFile(rscache.getIndexes()[5].getArchiveId(name), 0);
			if(data != null) {
				result = addMapFile(cache.getIndexes()[5], name, data);
				System.out.println(name+", "+result);
			}
		}
		result = cache.getIndexes()[5].rewriteTable();
		System.out.println("Updated maps: "+result);*/
	}
	
	public static boolean addMapFile(Index index, String name, byte[] data) {
		int archiveId = index.getArchiveId(name);
		if(archiveId == -1)
			archiveId = index.getTable().getValidArchiveIds().length;
		return index.putFile(archiveId, 0, Constants.GZIP_COMPRESSION, data, null, false, false, Utils.getNameHash(name), -1);
	} 
}
