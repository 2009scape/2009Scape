package emperor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apollo.fs.IndexedFileSystem;
import org.apollo.fs.util.ZipUtils;

import alex.cache.loaders.OverlayDefinition;

import com.alex.io.InputStream;
import com.alex.store.Index;
import com.alex.store.Store;
import com.alex.tools.clientCacheUpdater.RSXteas;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

import emperor.ObjectMap.GameObject;

/**
 * @author Emperor
 */
public class LandscapeEditor {
	
	public static final boolean COPY_OUT = true;
	
	public static final void main(String...args) throws Throwable {
		if (COPY_OUT) {
			for (File f : new File("./mapcache_out/").listFiles()) {
				copyFile(f, new File("./mapcache/" + f.getName()));
			}
		}
//		Store store = new Store("./498/");
//		packMaps(store);
//		checkNonOceanic(store, new int[] {8240, 8241, 8242, 8243, 8249, 8250, 8251, 8254, 8255, 8256, 8505, 8506, 8507, 8510, 8511, 8512, 8761, 8762, 8764, 8765, 8766, 8767, 8768, 9018, 9019, 9020, 9021, 9022, 9023, 9024, 9262, 9274, 9277, 9278, 9279, 9280, 9363, 9518, 9529, 9530, 9533, 9534, 9535, 9536, 9539, 9618, 9784, 9785, 9786, 9787, 9788, 9789, 9790, 9791, 9792, 10023, 10024, 10025, 10026, 10027, 10041, 10045, 10046, 10047, 10048, 10279, 10280, 10281, 10282, 10283, 10298, 10299, 10302, 10303, 10304, 10535, 10538, 10539, 10541, 10543, 10555, 10556, 10557, 10560, 10568, 10570, 10791, 10792, 10796, 10797, 10798, 10799, 10800, 10813, 10815, 10816, 10824, 10825, 10826, 11047, 11048, 11049, 11052, 11069, 11070, 11071, 11072, 11080, 11082, 11303, 11305, 11307, 11308, 11326, 11327, 11328, 11559, 11560, 11561, 11563, 11564, 11582, 11583, 11584, 11815, 11816, 11817, 11818, 11819, 11820, 11838, 11839, 11840, 12071, 12072, 12073, 12074, 12075, 12076, 12077, 12094, 12095, 12096, 12333, 12334, 12350, 12351, 12352, 12606, 12607, 12608, 12862, 12863, 12864, 13118, 13119, 13120, 13374, 13375, 13376, 13466, 13610, 13628, 13629, 13630, 13631, 13632, 13866, 13867, 13868, 14128, 14136, 14379, 14380, 14381, 14382, 14383, 14384, 14392, 14635, 14636, 14640, 14891, 14892, 14896, 14903, 14904, 15147, 15152, 15158, 15160, 15403, 15404, 15405, 15407, 15408, 15414, 15415, 15416});//new int[] {6731, 6985, 8022, 8240, 8241, 8242, 8243, 8249, 8250, 8251, 8254, 8255, 8256, 8280, 8505, 8506, 8507, 8510, 8511, 8512, 8513, 8515, 8761, 8762, 8764, 8765, 8766, 8767, 8768, 9018, 9019, 9020, 9021, 9022, 9023, 9024, 9262, 9274, 9277, 9278, 9279, 9280, 9363, 9518, 9529, 9530, 9533, 9534, 9535, 9536, 9539, 9618, 9784, 9785, 9786, 9787, 9788, 9789, 9790, 9791, 9792, 10023, 10024, 10025, 10026, 10027, 10041, 10045, 10046, 10047, 10048, 10129, 10279, 10280, 10281, 10282, 10283, 10298, 10299, 10302, 10303, 10304, 10308, 10535, 10538, 10539, 10541, 10543, 10555, 10556, 10557, 10560, 10568, 10570, 10583, 10791, 10792, 10796, 10797, 10798, 10799, 10800, 10813, 10815, 10816, 10824, 10825, 10826, 11047, 11048, 11049, 11052, 11069, 11070, 11071, 11072, 11080, 11082, 11303, 11304, 11305, 11307, 11308, 11326, 11327, 11328, 11559, 11560, 11561, 11563, 11564, 11582, 11583, 11584, 11815, 11816, 11817, 11818, 11819, 11820, 11838, 11839, 11840, 12071, 12072, 12073, 12074, 12075, 12076, 12077, 12094, 12095, 12096, 12333, 12334, 12350, 12351, 12352, 12606, 12607, 12608, 12627, 12862, 12863, 12864, 12889, 12890, 13118, 13119, 13120, 13144, 13145, 13146, 13354, 13374, 13375, 13376, 13400, 13401, 13402, 13466, 13610, 13625, 13626, 13628, 13629, 13630, 13631, 13632, 13866, 13867, 13868, 14128, 14136, 14379, 14380, 14381, 14382, 14383, 14384, 14392, 14635, 14636, 14640, 14648, 14891, 14892, 14896, 14903, 14904, 15147, 15152, 15158, 15160, 15403, 15404, 15405, 15407, 15408, 15414, 15415, 15416});
//		generateCache(store);
//		override(store, 788, 12187);
//		packOSRSMaps(store);
//		packLandscape(store);
//		pack377Maps(store);
//		addMissingMaps(store);
//		createMap(store);
//		changeMap(store);
	}
	
	static void packMaps(Store store) throws Throwable {
		LandscapeCache.init("./mapcache/", store);
		int[] keys = new int[] { 14881828, -6662814, 58238456, 146761213 };
		int count = 0;
		int failed = 0;
		for (int regionId = 0; regionId < 50_000; regionId++) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			String name = "l" + regionX + "_" + regionY;
			int index = store.getIndexes()[5].getArchiveId(name);
			if (index < 0) {
				continue;
			}
			byte[] b = LandscapeCache.forId(index);
			if (b == null || b.length < 2 || !validRegion(new InputStream(b))) {
				failed++;
				continue;
			}
			if (store.getIndexes()[5].putFile(index, 0, Constants.GZIP_COMPRESSION, b, keys, true, true, Utils.getNameHash(name), -1)) {
				count++;
			} else {
				failed++;
			}
		}
//		store.getIndexes()[5].rewriteTable();
//		store.getIndexes()[5].resetCachedFiles();
		System.out.println("Packed " + count + " maps (failed " + failed + " maps)!");
//		store2.getIndexes()[i].putFile(oldArchiveId, 0, Constants.GZIP_COMPRESSION, data, keys2, false, false, Utils.getNameHash(nameHash), -1);
	}
	
	static void createMap(Store store) throws Throwable {
		ObjectMap map = new ObjectMap();
		for (int x = 0; x < 64; x++) {
			for (int y = 0; y < 64; y++) {
				if (x == 32 || y == 32) {
					continue;
				}
				map.add(1276, x, y, 0, 10, 0);
			}
		}
		byte[] bs = map.generate();
		int regionId = 11110;
		int x = regionId >> 8 & 0xFF;
		int y = regionId & 0xFF;
		LandscapeCache.init("./mapcache/", store);
		int archive = store.getIndexes()[5].getArchiveId("l" + x + "_" + y);
		if (archive > -1) {
			System.out.println("Already contained region " + regionId + " (archive=" + archive + ", len=" + bs.length + " - " +LandscapeCache.forId(archive).length + ")!");
			return;
		}
		for (int ar = 0; ar < 50000; ar++) {
			if (!store.getIndexes()[5].archiveExists(ar)) {
				if (LandscapeCache.forId(ar).length < 1) {
					archive = ar;
					System.out.println("Archive available: " + ar);
					break;
				}
			}
		}
		store.getIndexes()[5].putFile(archive, 0, Constants.GZIP_COMPRESSION, bs, null, true, true,
				Utils.getNameHash("l" + x + "_" + y), -1);
		LandscapeCache.getLandscapes().put(archive, bs);
		LandscapeCache.dump("./mapcache_out/");
		System.out.println("Done!");
	}

	static void changeMap(Store store) throws Throwable {
		int regionId = 12439;
		GameObject[] remove = new GameObject[] { 
				new GameObject(32099, 42, 27, 0, 10, 3)
		};
		GameObject[] replace = new GameObject[] { 
				new GameObject(29139, 42, 27, 0, 10, 3)
		};
		
		LandscapeCache.init("./mapcache/", store);
		ObjectMap map = new ObjectMap();
		map.map(new InputStream(LandscapeCache.getLandscape(regionId)));
		for (int i = 0; i < remove.length; i++) {
			GameObject r = remove[i];
			GameObject object = map.get(r.id, r.loc.x, r.loc.y, r.loc.z, r.type, r.rotation);
			if (object == null) {
				System.err.println("Could not find object!");
				return;
			}
			map.getObjects().remove(object);
			if (replace[i] != null) {
				map.getObjects().add(replace[i]);
			}
		}
		byte[] bs = map.generate();
		LandscapeCache.getLandscapes().put(LandscapeCache.indexFor(regionId), bs);
		LandscapeCache.dump("./mapcache_out/");
	}

	/**
	 * Overrides the regions.
	 * @param store The file store.
	 * @param revision The revision to get the regions from.
	 * @param regionIds The region ids to override.
	 * @throws Throwable When an exception occurs.
	 */
	public static void override(Store store, int revision, int...regionIds) throws Throwable {
		LandscapeCache.init("./mapcache/", store);
		int count = 0;
		if (revision == 377) {
			Store s = new Store("./468/");
			IndexedFileSystem fs = new IndexedFileSystem(new File("./377/"), true);
			for (int regionId : regionIds) {
				int regionX = regionId >> 8 & 0xFF;
				int regionY = regionId & 0xFF;
				int index = store.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
				byte[] bs = null;
				try {
					ByteBuffer buffer = fs.getFile(4, index);
					bs = ZipUtils.unzip(buffer).array();
				} catch (Throwable t) {
					continue;
				}
				if (bs != null && validRegion(new InputStream(bs))) {
					System.out.println("Added region " + regionId + "!");
					count++;
					LandscapeCache.getLandscapes().put(index, bs);
					store.getIndexes()[5].putArchive(s.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString()), s);
				}
			}
			fs.close();
		} else {
			RSXteas.loadUnpackedXteas(revision);
			Store s = new Store("./" + revision + "/");
			boolean newFormat = revision > 750;
			for (int regionId : regionIds) {
				int regionX = regionId >> 8 & 0xFF;
				int regionY = regionId & 0xFF;
				int index = store.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
				int[] xteas = RSXteas.getXteas(regionId);
				byte[] b = newFormat ? s.getIndexes()[5].getFile(regionX | regionY << 7, 0)
						: s.getIndexes()[5].getFile(index, 0, xteas);
				if (b != null && b.length > 1 && validRegion(new InputStream(b))) {
					System.out.println("Added region " + regionId + "!");
					LandscapeCache.getLandscapes().put(index, b);
					count++;
					if (!newFormat) {
						store.getIndexes()[5].putArchive(s.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString()), s);
					}
				}
			}
		}
		LandscapeCache.dump("./mapcache_out/");
		System.out.println("Packed " + count + "/" + regionIds.length + " regions.");
	}

	/**
	 * Fully generates a map cache (from scratch).
	 * @param store The file store.
	 * @throws Throwable
	 */
	public static void generateCache(Store store) throws Throwable {
		LandscapeCache.init("./mapcache/", store);
		Store s = new Store("./508/");
		List<Integer> missingRegions = new ArrayList<>();
		System.out.println("Packing 508 maps...");
		RSXteas.loadUnpackedXteas(508);
		int count = 0;
		for (int regionId = 0; regionId < 50_000; regionId++) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			int index = store.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
			if (index < 0) {
				continue;
			}
			int[] xteas = RSXteas.getXteas(regionId);
			byte[] b = s.getIndexes()[5].getFile(index, 0, xteas);
			if (b == null || b.length < 2 || !validRegion(new InputStream(b))) {
				RandomAccessFile raf = new RandomAccessFile(new File("./508_Maps/" + index), "r");
				ByteBuffer buffer = raf.getChannel().map(MapMode.READ_ONLY, 0, raf.length());
				b = new byte[(int) raf.length()];
				buffer.get(b);
				raf.close();
				if (!validRegion(new InputStream(b))) {
					missingRegions.add(regionId);
					continue;
				}
				System.out.println("Used 508 map data file for index " + index + "!");
			}
			int archiveId = s.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString());
			if (archiveId > -1) {
				store.getIndexes()[5].putArchive(archiveId, s);
			}
			LandscapeCache.getLandscapes().put(index, b);
			count++;
		}
		System.out.println("Added " + count + " 508 regions!");
		System.out.println("Packing 468 maps...");
		RSXteas.loadUnpackedXteas(468);
		s = new Store("./468/");
		int subCount = 0;
		for (int regionId = 0; regionId < 50_000; regionId++) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			int index = s.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
			if (!missingRegions.contains(regionId)) {
				continue;
			}
			int[] xteas = RSXteas.getXteas(regionId);
			byte[] b = s.getIndexes()[5].getFile(index, 0, xteas);
			if (b != null && b.length > 1 && validRegion(new InputStream(b))) {
				System.out.println("Added missing region " + regionId + "!");
				count++;
				subCount++;
				missingRegions.remove((Object) regionId);
				LandscapeCache.getLandscapes().put(index, b);
				store.getIndexes()[5].putArchive(s.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString()), s);
			}
		}
		System.out.println("Added " + subCount + " 468 regions!");
		System.out.println("Packing 377 maps...");
		subCount = 0;
		IndexedFileSystem fs = new IndexedFileSystem(new File("./377/"), true);
		for (int regionId = 0; regionId < 50_000; regionId++) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			int index = store.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
			if (!missingRegions.contains(regionId)) {
				continue;
			}
			byte[] bs = null;
			try {
				ByteBuffer buffer = fs.getFile(4, index);
				bs = ZipUtils.unzip(buffer).array();
			} catch (Throwable t) {
				continue;
			}
			if (bs != null && validRegion(new InputStream(bs))) {
				System.out.println("Added missing region " + regionId + "!");
				count++;
				subCount++;
				missingRegions.remove((Object) regionId);
				LandscapeCache.getLandscapes().put(index, bs);
				store.getIndexes()[5].putArchive(s.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString()), s);
			}
		}
		System.out.println("Added " + subCount + " 377 regions!");
		fs.close();
		System.out.println("Packing 666 maps...");
		RSXteas.loadUnpackedXteas(666);
		s = new Store("./666/");
		subCount = 0;
		for (int regionId = 0; regionId < 50_000; regionId++) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			int index = store.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
			if (index < 0) {
				continue;
			}
			if (!missingRegions.contains(regionId)) {
				continue;
			}
			int[] xteas = RSXteas.getXteas(regionId);
			byte[] b = s.getIndexes()[5].getFile(index, 0, xteas);
			if (b != null && b.length > 1 && validRegion(new InputStream(b))) {
				System.out.println("Added missing region " + regionId + "!");
				count++;
				subCount++;
				missingRegions.remove((Object) regionId);
				LandscapeCache.getLandscapes().put(index, b);
				store.getIndexes()[5].putArchive(s.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString()), s);
			}
		}
		System.out.println("Added " + subCount + " 666 regions!");
		System.out.println("Packing 788 maps...");
		s = new Store("./788/");
		subCount = 0;
		for (int regionId = 0; regionId < 50_000; regionId++) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			if (!missingRegions.contains(regionId) && regionId != 6234) {
				continue;
			}
			int index = regionX | regionY << 7;
			byte[] b = s.getIndexes()[5].getFile(index, 0);
			if (b != null && b.length > 1 && validRegion(new InputStream(b))) {
				index = store.getIndexes()[5].getArchiveId(new StringBuilder("l").append(regionX).append("_").append(regionY).toString());
				System.out.println("Added missing region " + regionId + "!");
				count++;
				subCount++;
				missingRegions.remove((Object) regionId);
				LandscapeCache.getLandscapes().put(index, b);
			}
		}
		System.out.println("Added " + subCount + " 788 regions!");
		LandscapeCache.dump("./mapcache_out/");
		System.out.println("Added a total of " + count + " map regions, missing " + missingRegions.size() + " regions.");
		System.out.println("Missing: " + Arrays.toString(missingRegions.toArray()));
		System.exit(0);
	}
	
	/**
	 * Checks for non-oceanic regions (regions that don't exist purely of sea).
	 * @param store The store.
	 * @param regions The regions array.
	 */
	public static void checkNonOceanic(Store store, int[] regions) {
		List<Integer> missing = new ArrayList<>();
		for (int regionId : regions) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			int mapscapeId = store.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString());
			if (mapscapeId < 0) {
				System.err.println("Invalid mapscape index for region " + regionId + "!");
				continue;
			}
			boolean abort = false;
			ByteBuffer buffer = ByteBuffer.wrap(store.getIndexes()[5].getFile(mapscapeId, 0));
			byte[][][] mapscape = new byte[4][64][64];
			main: for (int z = 0; z < 4; z++) {
				for (int x = 0; x < 64; x++) {
					for (int y = 0; y < 64; y++) {
						while (true) {
							int value = buffer.get() & 0xFF;
							if (value == 0) {
								break;
							}
							if (value == 1) {
								buffer.get();
								break;
							}
							if (value <= 49) {
								int overlay = buffer.get() & 0xFF;
								OverlayDefinition def = OverlayDefinition.forId(store, overlay);
								if (def != null && def.getTextureId() != 25) {
									abort = true;
									break main;
								}
							} else if (value <= 81) {
								mapscape[z][x][y] = (byte) (value - 49);
							}
						}
					}
				}
			}
			if (abort) {
				missing.add(regionId);
			}
		}
		System.out.println("Missing region count: " + missing.size() + "..");
		System.out.println(Arrays.toString(missing.toArray()));
	}
	
	public static void packLandscape(Store store) throws Throwable {
		Store s = new Store("./508/");
		int[] ids = new int[] {13722};
		for (int regionId : ids) {
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			boolean b = store.getIndexes()[5].putArchive(s.getIndexes()[5].getArchiveId(new StringBuilder("m").append(regionX).append("_").append(regionY).toString()), s);
			System.out.println("Packed landscape (" + regionId + "): " + b);
		}
	}
	
	public static boolean addMapFile(Index index, String name, byte[] data) {
		int archiveId = index.getArchiveId(name);
		if(archiveId == -1)
			archiveId = index.getTable().getValidArchiveIds().length;
		return index.putFile(archiveId, 0, Constants.GZIP_COMPRESSION, data, null, false, false, Utils.getNameHash(name), -1);
	}
	
	public static boolean validRegion(InputStream stream) {
		int count = 0;
		for (;;) {
			int offset = stream.readSmart2();
			if (offset == 0) {
				break;
			}
			int location = 0;
			for (;;) {
				offset = stream.readUnsignedSmart();
				if (offset == 0) {
					break;
				}
				location += offset - 1;
				int y = location & 0x3f;
				int x = location >> 6 & 0x3f;
				stream.readUnsignedByte();
				if (x >= 0 && y >= 0 && x < 64 && y < 64) {
					if (++count > 10) {
						return true;
					}
				}
			}
		}
		return false;
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
}