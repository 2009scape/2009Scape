package emperor;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import alex.cache.loaders.OverlayDefinition;

import com.alex.io.InputStream;
import com.alex.store.Store;
import com.alex.utils.Constants;
import com.alex.utils.Utils;

import emperor.ObjectMap.GameObject;

/**
 * Used for editting maps.
 * @author Emperor
 *
 */
public final class MapEditor {

	/**
	 * The valid revisions.
	 */
	private static final int[] VALID_REVISIONS = {
		377, 468, 474, 498, 503, 508, 538, 546, 562, 569, 666, 788
	};
	
	/**
	 * The xtea keys used to encrypt the maps.
	 */
	private static final int[] XTEA_KEYS = {
		14881828, -6662814, 58238456, 146761213
	};
	
	/**
	 * The mapscape type (floor).
	 */
	private static final String MAP_TYPE = "m";
	
	/**
	 * The landscape type (objects).
	 */
	private static final String LAND_TYPE = "l";
	
	/**
	 * The map cache index.
	 */
	private static final int MAP_INDEX = 5;
	
	/**
	 * The cache file store to change.
	 */
	private static Store store;
	
	/**
	 * Used the update the maps.
	 * @throws Throwable When an exception occurs.
	 */
	private static void update() throws Throwable {
//		replaceObjects(new GameObject[][] {
//			{ new GameObject(5281, 3666, 3521, 1, 10, 0), new GameObject(5281, 3666, 3520, 1, 10, 0) }
//		});
//		copy(13099, 13099, new Store("./508/"), new int[] { 273193181, -1465876115, -151667950, 40605898 });
		replaceMapPart(13099, new Store("./468/"), new int[] {-636687345, -1379232722, -1661855973, 666075756}, 18, 36, 31, 49, 0);
//		int regionId = 13099;
//		System.out.println("Revisions for region " + regionId + ": " + Arrays.toString(getValidRevisions(regionId)) + ".");
	}
	
	/**
	 * Replaces a part of the map.
	 * @param regionId The region id.
	 * @param from The store to copy from.
	 * @param xteaKeys The XTEA keys used to decrypt the region from the store to copy from.
	 * @param southWestX The south west x (on region) coordinate of the part to replace.
	 * @param southWestY The south west y (on region) coordinate of the part to replace.
	 * @param northEastX The north east x (on region) coordinate of the part to replace.
	 * @param northEastY The north east y (on region) coordinate of the part to replace.
	 */
	static void replaceMapPart(int regionId, Store from, int[] xteaKeys, int southWestX, int southWestY, int northEastX, int northEastY, int...planes) {
		ObjectMap map = new ObjectMap();
		map.map(new InputStream(getLandscape(regionId, store, XTEA_KEYS)));
		for (Iterator<GameObject> it = map.getObjects().iterator(); it.hasNext();) {
			GameObject object = it.next();
			if (object.loc.x >= southWestX && object.loc.x <= northEastX && object.loc.y >= southWestY && object.loc.y <= northEastY) {
				it.remove();
			}
		}
		ObjectMap m = new ObjectMap();
		m.map(new InputStream(getLandscape(regionId, from, xteaKeys)));
		for (GameObject object : m.getObjects()) {
			for (int z : planes) {
				if (object.loc.z == z && object.loc.x >= southWestX && object.loc.x <= northEastX && object.loc.y >= southWestY && object.loc.y <= northEastY) {
					map.getObjects().add(object);
					break;
				}
			}
		}
		packLandscape(regionId, map.generate(), store, XTEA_KEYS);
		LandMap l = new LandMap();
		l.map(ByteBuffer.wrap(store.getIndexes()[5].getFile(getArchiveIndex(MAP_TYPE, regionId, store))));
		LandMap lm = new LandMap();
		lm.map(ByteBuffer.wrap(from.getIndexes()[5].getFile(getArchiveIndex(MAP_TYPE, regionId, from))));
		for (int z : planes) {
			for (int x = southWestX; x <= northEastX; x++) {
				for (int y = southWestY; y <= northEastY; y++) {
					l.defaultOpcodes[z][x][y] = lm.defaultOpcodes[z][x][y];
					l.height[z][x][y] = lm.height[z][x][y];
					l.overlayOpcodes[z][x][y] = lm.overlayOpcodes[z][x][y];
					l.overlays[z][x][y] = lm.overlays[z][x][y];
					l.underlays[z][x][y] = lm.underlays[z][x][y];
				}
			}
		}
		packMapscape(regionId, l.generate(), store);
	}
	
	/**
	 * Gets the revisions of the caches having this region.
	 * @param regionId The region id.
	 * @return The cache revisions.
	 */
	public static int[] getValidRevisions(int regionId) {
		int[] revisions = new int[VALID_REVISIONS.length];
		int count = 0;
		for (int revision : VALID_REVISIONS) {
			String rev = revision == 498 ? "clean_498" : Integer.toString(revision);
			try {
				Store store = new Store("./" + rev + "/");
				System.out.println("./" + rev + "/");
				if (getArchiveIndex(LAND_TYPE, regionId, store) > -1) {
					revisions[count++] = revision;
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return Arrays.copyOf(revisions, count);
	}
	
	/**
	 * Copies a region.
	 * @param fromId The region id to copy.
	 * @param toId The region id to paste on.
	 * @param from The store to get the data from.
	 * @param xtea The XTEA keys to decrypt the map.
	 */
	static void copy(int fromId, int toId, Store from, int[] xtea) {
		copy(LAND_TYPE, fromId, toId, from, xtea);
		copy(MAP_TYPE, fromId, toId, from, null);
	}

	/**
	 * Copies the landscape from a region.
	 * @param fromId The region id to copy the landscape from.
	 * @param toId The region id to paste the landscape on.
	 * @param from the store to get the data from.
	 * @param xtea The XTEA keys to decrypt the landscape.
	 */
	private static void copy(String type, int fromId, int toId, Store from, int[] xtea) {
		int index = getArchiveIndex(type, fromId, from);
		if (index < 0) {
			throw new IllegalArgumentException("Region " + fromId + " does not exist!");
		}
		byte[] bs = from.getIndexes()[MAP_INDEX].getFile(index, 0, xtea);
		if (bs == null || bs.length < 1) {
			throw new IllegalArgumentException("Region " + fromId + " is invalid!");
		}
		index = getArchiveIndex(type, toId, store);
		if (index < 0) {
			index = findEmptyArchive(store, 0);
			System.out.println("Creating new region - id=" + index + "!");
		}
		store.getIndexes()[MAP_INDEX].putFile(index, 0, Constants.GZIP_COMPRESSION, bs, type == LAND_TYPE ? XTEA_KEYS : null, true, true, getNameHash(type, toId), -1);
	}
	
	/**
	 * Replaces objects.
	 * @param changes The array of object changes.
	 * @throws Throwable when an exception occurs.
	 */
	static void replaceObjects(GameObject[][] changes) throws Throwable {
		Map<Integer, Map<GameObject, GameObject>> objects = new HashMap<>();
		for (int i = 0; i < changes.length; i++) {
			GameObject old = changes[i][0];
			int regionId = (old.loc.x >> 6) << 8 | (old.loc.y >> 6);
			old = old.getLocal();
			Map<GameObject, GameObject> map = objects.get(regionId);
			if (map == null) {
				objects.put(regionId, map = new HashMap<>());
			}
			GameObject replace = changes[i][1];
			if (replace != null) {
				replace = replace.getLocal();
			}
			map.put(old, replace);
		}
		int count = 0;
		for (int regionId : objects.keySet()) {
			Map<GameObject, GameObject> replacements = objects.get(regionId);
			ObjectMap map = new ObjectMap();
			map.map(new InputStream(getLandscape(regionId, store, XTEA_KEYS)));
			for (GameObject object : replacements.keySet()) {
				GameObject current = map.get(object);
				if (current == null) {
					throw new IllegalArgumentException("Could not find object " + object + "!");
				}
				map.getObjects().remove(current);
				current = replacements.get(object);
				if (current != null) {
					map.getObjects().add(current);
				}
				count++;
			}
			packLandscape(regionId, map.generate(), store, XTEA_KEYS);
		}
		System.out.println("Changed " + count + " objects in " + objects.size() + " regions!");
	}
	
	/**
	 * Packs the landscape.
	 * @param regionId The region id to pack on.
	 * @param data The landscape data to pack.
	 * @param store The store used.
	 * @param xtea The XTEA keys.
	 */
	private static void packMapscape(int regionId, byte[] data, Store store) {
		int index = getArchiveIndex(MAP_TYPE, regionId, store);
		store.getIndexes()[MAP_INDEX].putFile(index, 0, Constants.GZIP_COMPRESSION, data, null, true, true, getNameHash(MAP_TYPE, regionId), -1);
	}
	
	/**
	 * Packs the landscape.
	 * @param regionId The region id to pack on.
	 * @param data The landscape data to pack.
	 * @param store The store used.
	 * @param xtea The XTEA keys.
	 */
	private static void packLandscape(int regionId, byte[] data, Store store, int[] xtea) {
		int index = getArchiveIndex(LAND_TYPE, regionId, store);
		store.getIndexes()[MAP_INDEX].putFile(index, 0, Constants.GZIP_COMPRESSION, data, xtea, true, true, getNameHash("l", regionId), -1);
	}
	
	/**
	 * Gets the landscape data.
	 * @param regionId The region id.
	 * @param store The store to get the landscape data from.
	 * @param xtea The XTEA keys used to decrypt the landscape.
	 * @return The landscape data.
	 */
	private static byte[] getLandscape(int regionId, Store store, int[] xtea) {
		int index = getArchiveIndex(LAND_TYPE, regionId, store);
		if (index < 0) {
			throw new IllegalArgumentException("Region " + regionId + " does not exist!");
		}
		byte[] bs = store.getIndexes()[MAP_INDEX].getFile(index, 0, xtea);
		if (bs == null) {
			throw new IllegalArgumentException("Region " + regionId + " has no valid landscape!");
		}
		return bs;
	}

	/**
	 * Finds an empty archive id.
	 * @param store The store to check.
	 * @param offset The archive offset to start checking from.
	 * @return The new archive index.
	 */
	private static int findEmptyArchive(Store store, int offset) {
		for (int index = offset; index < 50000; index++) {
			if (!store.getIndexes()[MAP_INDEX].archiveExists(index)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * Gets the name hash for the given region id.
	 * @param type The archive type "m"=mapscape, "l"=landscape.
	 * @param regionId The region id.
	 * @return The name hash.
	 */
	private static int getNameHash(String type, int regionId) {
		int x = regionId >> 8 & 0xFF;
		int y = regionId & 0xFF;
		return Utils.getNameHash(type + x + "_" + y);
	}
	
	/**
	 * Gets the archive index.
	 * @param type The archive type "m"=mapscape, "l"=landscape.
	 * @param regionId The region id.
	 * @param store The store.
	 * @return The archive index.
	 */
	private static int getArchiveIndex(String type, int regionId, Store store) {
		int x = regionId >> 8 & 0xFF;
		int y = regionId & 0xFF;
		return store.getIndexes()[MAP_INDEX].getArchiveId(type + x + "_" + y);
	}
	
	/**
	 * The main method.
	 * @param args The arguments cast on runtime.
	 * @throws Throwable When an exception occurs.
	 */
	public static void main(String...args) throws Throwable {
		String revision = "498";
		if (args.length > 0) {
			revision = args[0];
		}
		System.out.println("Updating revision " + revision + "...");
		long start = System.currentTimeMillis();
		store = new Store("./" + revision + "/");
		update();
		System.out.println("Finished after " + (System.currentTimeMillis() - start) + " milliseconds.");
	}
	
	/**
	 * Checks if the region is valid.
	 * @param regionId The region id.
	 * @param store The store.
	 * @return {@code True} if so.
	 */
	public static boolean isValid(int regionId, int[] xtea, Store store) {
		int index = getArchiveIndex("l", regionId, store);
		if (index > -1) {
			byte[] bs = store.getIndexes()[MAP_INDEX].getFile(index, 0, xtea);
			if (bs == null) {
				if (regionId == 11082) { //Elf city is an empty region
					return true;
				}
				ByteBuffer buffer = ByteBuffer.wrap(store.getIndexes()[5].getFile(getArchiveIndex("m", regionId, store), 0));
				byte[][][] mapscape = new byte[4][64][64];
				boolean ocean = true;
				main: for (int z = 0; z < 4; z++) {
					for (int i = 0; i < 64; i++) {
						for (int j = 0; j < 64; j++) {
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
										ocean = false;
										break main;
									}
								} else if (value <= 81) {
									mapscape[z][i][j] = (byte) (value - 49);
								}
							}
						}
					}
				}
				if (!ocean) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Debugs the world map.
	 */
	static void debugWorldMap() {
		int regions = 0;
		int missing = 0;
		for (int x = 0; x < 255; x++) {
			for (int y = 0; y < 255; y++) {
				int regionId = x << 8 | y;
				if (!isValid(regionId, XTEA_KEYS, store)) {
					missing++;
					System.out.println("Missing region " + regionId + "!");
				}
			}
		}
		System.out.println("World map is missing " + missing + "/" + regions + " regions!");
	}
	
}