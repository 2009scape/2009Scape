package com.alex.tools.clientCacheUpdater;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.alex.loaders.images.IndexedColorImageFile;
import com.alex.loaders.items.ItemDefinitions;
import com.alex.store.Index;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

public class UpdateCache {

	
/*	public static void main(String[] args) throws IOException {
		Store rscache = new Store("cache697/");
		Store cache = new Store("cache667_2/", false);
	//	System.out.println(rscache.getIndexes()[36].getTable().getValidArchiveIds().length);
		for(int i = 0; i < rscache.getIndexes()[3].getLastArchiveId(); i++) {
			if(i == 548 || i == 746)
				continue;
			cache.getIndexes()[3].putArchive(i, rscache, false, false);
		}
		cache.getIndexes()[3].rewriteTable();
		//Interface inter = new Interface(746, rscache);
	}*/
	
	// Returns the contents of the file in a byte array.
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

	
	public static void main6(String[] args) throws IOException {
		Store cache = new Store("cache667_2/", false);
		cache.getIndexes()[6].putFile(0, 0, getBytesFromFile(new File("0")));
	}
	
	
	public static void main5(String[] args) throws IOException {
		Store rscache = new Store("cache697/");
		Store cache = new Store("cache667_2/", false);
		boolean result = false;
		//settings
		/*cache.getIndexes()[3].putArchive(261, rscache);
		System.out.println("Packed skill interface: 261, "+result);*/
		//skills
		result = cache.getIndexes()[3].putArchive(320, rscache, false, false);
		System.out.println("Packed skill interface: 320, "+result);
	/*	//equipment
		result = cache.getIndexes()[3].putArchive(387, rscache, false, false);
		System.out.println("Packed skill interface: 387, "+result);*/
		//inventory
		result = cache.getIndexes()[3].putArchive(679, rscache, false, false);
		System.out.println("Packed skill interface: 679, "+result);
		//attack style bar
	//	result = cache.getIndexes()[3].putArchive(884, rscache);
	//	System.out.println("Packed skill interface: 884, "+result);
		cache.getIndexes()[3].rewriteTable();
	}
	
/*	public static void main(String[] args) throws IOException {
		Store rscache = new Store("cache697/");
		Store cache = new Store("cache667_2/", false);
		cache.getIndexes()[17].packIndex(rscache);
	}*/
	
	
	public static void main555(String[] args) throws IOException {
		Store cache = new Store("cache667_2/", false);
		Store originalCache = new Store("rscache/", false);
		cache.addIndex(false, false, Constants.GZIP_COMPRESSION);
		for(int i : originalCache.getIndexes()[19].getTable().getValidArchiveIds()) {
			System.out.println(i);
			for(int i2 : originalCache.getIndexes()[19].getTable().getArchives()[i].getValidFileIds()) {
				try {
					cache.getIndexes()[37].putFile(i, i2, Constants.GZIP_COMPRESSION,  originalCache.getIndexes()[19].getFile(i, i2), null, false, false, -1, -1);
				}catch(Throwable e) {
					e.printStackTrace();
				}
			}	
		}
		cache.getIndexes()[37].rewriteTable();
		//cache.getIndexes()[37].packIndex(19, originalCache, false);
	/*	System.out.println(ItemDefinitions.getItemDefinition(cache, 4708).maleEquipModelId1);
		System.out.println(ItemDefinitions.getItemDefinition(cache, 4708).femaleEquipModelId1);
		System.out.println(ItemDefinitions.getItemDefinition(cache, 4708).invModelId);*/
	//	Store originalCache = new Store("cache667/", false);
	//	cache.addIndex(cache.getIndexes()[7].getTable().isNamed(), cache.getIndexes()[7].getTable().usesWhirpool(), Constants.GZIP_COMPRESSION);
		
	}
	
	public static void main77(String[] args) throws IOException {
		//Store mapcache = new Store("cache667_1/", false);
		Store originalCache = new Store("cache667/", false);
		Store cache = new Store("cache667_2/", false);
		for(int i = 1610; i < 1616; i++)
			cache.getIndexes()[17].putFile(i >>> 8, i & 0xff, originalCache.getIndexes()[17].getFile(i >>> 8, i & 0xff));
		
	/*	 cache.getIndexes()[3].putArchive(320, rscache, false, false);
		 cache.getIndexes()[3].putArchive(667, rscache, false, false);
		 cache.getIndexes()[3].putArchive(751, rscache, false, false);
		 cache.getIndexes()[3].rewriteTable();*/
		 
		/*cache.resetIndex(5, true, mapcache.getIndexes()[5].getTable().usesWhirpool(), Constants.GZIP_COMPRESSION);
		
		boolean result = cache.getIndexes()[5].packIndex(mapcache, false);*/
	//	cache.getIndexes()[8].packIndex(originalCache);
	//	System.out.println("Packed index archives: "+5+", "+result);
	}
	
	public static void packLogo(Store cache) throws IOException {
		int id = 2498;
		IndexedColorImageFile f = null;
		try {
			f = new IndexedColorImageFile(ImageIO.read(new File("bg/logo.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] data = f.encodeFile();
		cache.getIndexes()[8].putFile(id, 0, data);
		
		//back background
		for(int i = 4139; i <= 4146; i++) {
			try {
				cache.getIndexes()[8].putFile(i, 0, new IndexedColorImageFile(ImageIO.read(new File("bg/"+i+".gif"))).encodeFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < 4; i++) {
			int realid = 3769 + i;
			id = 3769 + i;
			cache.getIndexes()[8].putFile(id, 0, new IndexedColorImageFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3779 + i;
			cache.getIndexes()[8].putFile(id, 0, new IndexedColorImageFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3783 + (i >= 2 ? (i-2) : i + 2);
			cache.getIndexes()[8].putFile(id, 0, new IndexedColorImageFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3769 + i;
			cache.getIndexes()[34].putFile(id, 0, new IndexedColorImageFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3779 + i;
			cache.getIndexes()[34].putFile(id, 0, new IndexedColorImageFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3783 + (i >= 2 ? (i-2) : i + 2);
			cache.getIndexes()[34].putFile(id, 0, new IndexedColorImageFile(ImageIO.read(new File("bg/"+realid+".gif"))).encodeFile());
			id = 3769 + i;
			cache.getIndexes()[32].putFile(id, 0, SpritesDumper.getImage(new File("bg/"+realid+".png")));
			id = 3779 + i;
			cache.getIndexes()[32].putFile(id, 0, SpritesDumper.getImage(new File("bg/"+realid+".png")));
			id = 3783 + (i >= 2 ? (i-2) : i + 2);
			cache.getIndexes()[32].putFile(id, 0, SpritesDumper.getImage(new File("bg/"+realid+".png")));;
			
			
			System.out.println("added file: "+i);
		}
	}
	
	public static void packDonatorIcon(Store cache) {
		int id = 1455;
		IndexedColorImageFile f = null;
		try {
			f = new IndexedColorImageFile(cache, id, 0);
			BufferedImage icon = ImageIO.read(new File("1455.png"));
			System.out.println("Added icon: "+f.addImage(icon)+".");
			BufferedImage icon2 = ImageIO.read(new File("1455f.png"));
			System.out.println("Added icon2: "+f.addImage(icon2)+".");
			BufferedImage icon3 = ImageIO.read(new File("crown_green.gif"));
			System.out.println("Added icon3: "+f.addImage(icon3)+".");
			BufferedImage icon4 = ImageIO.read(new File("1455_11.png"));
			System.out.println("Added icon4: "+f.addImage(icon4)+".");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		cache.getIndexes()[8].putFile(id, 0, f.encodeFile());
	}
	
	public static void packMatrixIcon(Store cache) {
		int id = 2173;
		IndexedColorImageFile f = null;
		try {
			f = new IndexedColorImageFile(ImageIO.read(new File("2173.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] data = f.encodeFile();
		cache.getIndexes()[8].putFile(id, 0, data);
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
		int modelID = packCustomModel(cache, getBytesFromFile(new File("donatorCape.dat")));
		System.out.println("model id "+modelID);
		ItemDefinitions donatorCape = ItemDefinitions.getItemDefinition(cache, 9747);
		donatorCape.setName("Donator cape");
		//donatorCape.getInventoryOptions()[2] = "Customise";
		donatorCape.femaleEquipModelId1 = modelID;
		donatorCape.maleEquipModelId1 = modelID;
		donatorCape.invModelId = modelID;
		donatorCape.resetModelColors();
		//	donatorCape.changeModelColor();
		int newId = 29999;
		System.out.println(cache.getIndexes()[19].putFile(newId >>> 8, 0xff & newId, donatorCape.encode()));
	}
	
	public static void main(String[] args) throws IOException {
		boolean updateJustMaps = false;
		boolean addOldItems = true;
		Store rscache = new Store("cache697/");
		Store cache = new Store("cache667_2/", false);
		Store originalCache = new Store("cache667/", false);
		if(addOldItems)
			cache.resetIndex(19, false, false, Constants.GZIP_COMPRESSION);
		
		cache.resetIndex(7, false, false, Constants.GZIP_COMPRESSION);
		cache.getIndexes()[7].packIndex(originalCache);
		
		if(!updateJustMaps) {
		for(int i = 0; i < cache.getIndexes().length; i++) {
			if(i != 3 //interfaces
					&& i != 5 //maps
					&& i != 12 //client scripts
					&& i != 33
					&& i != 30) //native libs
				{
				boolean result = cache.getIndexes()[i].packIndex(rscache, true);
				System.out.println("Packed index archives: "+i+", "+result);
			}
		}
		System.out.println("Adding logo...");
		packLogo(cache);
		System.out.println("Adding donator icon...");
		packDonatorIcon(cache);
		System.out.println("Adding Matrix icon...");
		packMatrixIcon(cache);
		System.out.println("Adding Custom items...");
		packCustomItems(cache);
		if(addOldItems) {
			System.out.println("Adding back old item definitions...");
			int currentSize = 30000;//Utils.getItemDefinitionsSize(cache);
			System.out.println(currentSize);
			int oldSize = Utils.getItemDefinitionsSize(originalCache);
			for(int i = currentSize ; i < currentSize+oldSize; i++) {
				int newItemId = i; 
				int oldItemId = i - currentSize;
				cache.getIndexes()[19].putFile(newItemId >>> 8, 0xff & newItemId, Constants.GZIP_COMPRESSION, originalCache.getIndexes()[19].getFile(oldItemId >>> 8, 0xff & oldItemId), null, false, false, -1, -1);
			}
			cache.getIndexes()[19].rewriteTable();
		}
		System.out.println("Recovering Client Script Maps...");
		for(int i : originalCache.getIndexes()[17].getTable().getValidArchiveIds()) {
			for(int i2 : originalCache.getIndexes()[17].getTable().getArchives()[i].getValidFileIds()) {
				if(!cache.getIndexes()[17].fileExists(i, i2) || cache.getIndexes()[17].getFile(i, i2).length == 1) {
					cache.getIndexes()[17].putFile(i, i2, originalCache.getIndexes()[17].getFile(i, i2));
				}
			}
		}
		System.out.println("Recovering Bank Client Script Maps...");
		for(int i = 1610; i < 1616; i++) 
			cache.getIndexes()[17].putFile(i >>> 8, i & 0xff, originalCache.getIndexes()[17].getFile(i >>> 8, i & 0xff));
	
		System.out.println("Adding new interfaces...");
		
		//adds new interfaces
		for(int i = cache.getIndexes()[3].getLastArchiveId()+1; i <= rscache.getIndexes()[3].getLastArchiveId(); i++) {
			if(rscache.getIndexes()[3].archiveExists(i))
				cache.getIndexes()[3].putArchive(i, rscache, false, false);
		}
		 cache.getIndexes()[3].putArchive(320, rscache, false, false);
		 cache.getIndexes()[3].putArchive(751, rscache, false, false);
		 cache.getIndexes()[3].putArchive(1092, rscache, false, false);

		boolean result = cache.getIndexes()[3].rewriteTable();
		cache.getIndexes()[8].rewriteTable();
		System.out.println("Packed new interfaces: "+result);
		}
		boolean result;
	//	int oldRevision = cache.getIndexes()[5].getTable().getRevision();
	//	cache.resetIndex(5, true, cache.getIndexes()[5].getTable().usesWhirpool(), Constants.GZIP_COMPRESSION);
		Index index = cache.getIndexes()[5];
	//	index.getTable().setRevision(oldRevision+1);
		Index rsIndex = rscache.getIndexes()[5];
		
		Index originalIndex = originalCache.getIndexes()[5];
		RSXteas.loadUnpackedXteas(679);
		//OriginalXteas.loadUnpackedXteas();
		
		System.out.println("Updating Maps.");
		for(int regionId = 0; regionId < 30000; regionId++) {
			int regionX = (regionId >> 8) * 64;
			int regionY = (regionId & 0xff) * 64;	
			String name = "m"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			byte[] data = rsIndex.getFile(rsIndex.getArchiveId(name));
			if(data == null)
				data = originalIndex.getFile(originalIndex.getArchiveId(name));
			if(data != null) {
				result = addMapFile(index, name, data);
				System.out.println(name+", "+result);
			}
			name = "um"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rsIndex.getFile(rsIndex.getArchiveId(name));
			if(data == null)
				data = originalIndex.getFile(originalIndex.getArchiveId(name));
			if(data != null) {
				result = addMapFile(index, name, data);
				System.out.println(name+", "+result);
			}
			int[] xteas = RSXteas.getXteas(regionId);
			name = "l"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rsIndex.getFile(rsIndex.getArchiveId(name), 0, xteas);
			/*if(data == null)
				data = originalIndex.getFile(originalIndex.getArchiveId(name), 0, OriginalXteas.getXteas(regionId));
			*/if(data != null) {
				result = addMapFile(index, name, data);
				System.out.println(name+", "+result);
			}
			name = "ul"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rsIndex.getFile(rsIndex.getArchiveId(name), 0, xteas);
			/*if(data == null)
				data = originalIndex.getFile(originalIndex.getArchiveId(name), 0, OriginalXteas.getXteas(regionId));
			*/if(data != null) {
				result = addMapFile(index, name, data);
				System.out.println(name+", "+result);
			}
			name = "n"+ ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8);
			data = rsIndex.getFile(rsIndex.getArchiveId(name), 0);
			if(data == null)
				data = originalIndex.getFile(originalIndex.getArchiveId(name), 0);
			if(data != null) {
				result = addMapFile(index, name, data);
				System.out.println(name+", "+result);
			}
		}
		index.rewriteTable();
	}
	
	public static boolean addMapFile(Index index, String name, byte[] data) {
		int archiveId = index.getArchiveId(name);
		if(archiveId == -1)
			archiveId = index.getTable().getValidArchiveIds().length;
		return index.putFile(archiveId, 0, Constants.GZIP_COMPRESSION, data, null, false, false, Utils.getNameHash(name), -1);
	}
}
