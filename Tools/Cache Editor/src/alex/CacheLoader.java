package alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import alex.cache.CacheFile;
import alex.cache.FileOnDisk;
import alex.cache.FileSystem;
import alex.cache.SeekableFile;
import alex.cache.loaders.ItemDefinition;
import alex.cache.updateServer.UpdateServer;
import alex.io.Stream;
import alex.util.Methods;

/*
 * ----------\_/--------------
 * ----------/-\--------------
 * -------|-/@.@\-|----------
 * ---------\___/-------------
 * ALL CREDITS TO ALEX(DRAGONKK)
 * CREATED DATA 15/04/2011
 * @@alex_dkk@hotmail.com@@
 * ----------------------------
 * ----------------------------
 * ----------------------------
 */
public class CacheLoader {

	private static final String cachePath = "data/cache/";
	public static SeekableFile dataFile;
	private static final FileSystem[] fileSystems = new FileSystem[30];
	public static final SeekableFile[] indexFiles = new SeekableFile[getFileSystems().length];
	public static boolean OLD_CACHE;
	private static CacheFile referenceCache;
	private static SeekableFile referenceFile;


	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Parameters: isOldCache[bool], preload[bool]");
			return;
		}
		OLD_CACHE = Boolean.parseBoolean(args[0]);
		boolean preload = Boolean.parseBoolean(args[1]);
		if (load(preload)) {
			makeTests();
		}
	}
	
	public static boolean putItemOnCache(ItemDefinition item) {
		return fileSystems[Methods.ITEMDEF_IDX_ID].putFile(item.id >>> 8, 0xff & item.id, null, 2, item.packItemDefinition());
	}
		
	
	public static void makeTests() {
		
		ItemDefinition dragonkkAgsDefinition = new ItemDefinition(11694);
		System.out.println("DragonkkAgs: "+dragonkkAgsDefinition.getName());
		dragonkkAgsDefinition.setName("Dragonkk's AGS");
		dragonkkAgsDefinition.id = Methods.getAmountOfItems(); //a new item :o
		dragonkkAgsDefinition.inventoryOptions[0] = "kill Noobs";
		dragonkkAgsDefinition.inventoryOptions[1] = "I love cakes";
		dragonkkAgsDefinition.inventoryOptions[2] = "unban flamable please <3";
		System.out.println("DragonkkAgs Id: "+dragonkkAgsDefinition.id);
		System.out.println(putItemOnCache(dragonkkAgsDefinition));
		
		
		byte[] ukeys = generateUkeysFile();
		System.out.println("UKEYS: "+Arrays.toString(ukeys));
	/*byte[] whipData = fileSystems[19].getFile(4151 >>> 8, 0xff & 4151, null);
	if(fileSystems[19].putFile(11694 >>> 8, 0xff & 11694, null, 2, whipData))
		System.out.println("Packed sucefully.");*/
	}
	
	public static byte[] generateUkeysFile() {
		return UpdateServer.getReadyForSendFile(255, 255, 0, generateUkeysContainer());
	}
	
	public static byte[] generateUkeysContainer() {
		Stream stream = new Stream(5+fileSystems.length * 8);
		for(int index = 0; index < fileSystems.length; index++) {
			if(fileSystems[index] == null) {
				stream.putInt(0);
				stream.putInt(0);
			}
			byte[] buffer = CacheLoader.getReferenceCache().readFile(index);
			stream.putInt(Methods.getCrc(buffer, buffer.length));
			stream.putInt(fileSystems[index].referenceTable.revision);
		}
		byte[] ukeysFile = new byte[stream.offset];
		stream.offset = 0;
		stream.getBytes(ukeysFile, 0, ukeysFile.length);
		return ukeysFile;
	}
	
	
	private static void createFileSystems() {
		for (int id = 0; id < getFileSystems().length; id++) {
			if (indexFiles[id] == null)
				continue;
			boolean discardEntryBuffers = false;
			if (id == 5 || id == 6 || id == 23 || id == 26 || id == 28)
				discardEntryBuffers = true;
			getFileSystems()[id] = new FileSystem(id, discardEntryBuffers, 1);
		}
	}

	public static FileSystem[] getFileSystems() {
		return fileSystems;
	}

	public static CacheFile getReferenceCache() {
		return referenceCache;
	}

	public static boolean load(boolean preload) {
		File[] files = new File(cachePath).listFiles();
		for (File file : files) {
			if (file.getName().startsWith("main_file_cache.idx")) {
				if (file.length() == 0)
					continue;
				try {
					try {
						int id = Integer
								.parseInt(file.getName().split(".idx")[1]);
						if (id == 255)
							referenceFile = new SeekableFile(new FileOnDisk(file), 6000, 0);
						else if (id < fileSystems.length)
							indexFiles[id] = new SeekableFile(new FileOnDisk(file), 6000, 0);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (file.getName().equals("main_file_cache.dat2")) {
				try {
					dataFile = new SeekableFile(new FileOnDisk(file), 5200, 0);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (dataFile == null)
			return false;
		if (referenceFile == null)
			return false;
		referenceCache = new CacheFile(255, dataFile, referenceFile, 0x7a120);
		createFileSystems();
		if(preload) {
			for(int index = 0; index < fileSystems.length; index++) {
				if(fileSystems[index] == null)
					continue;
				fileSystems[index].filesCompleted();
			}
		}
		return true;
	}
}
