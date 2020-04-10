package org.crandor.game.world.map;

import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.build.RegionFlags;
import org.crandor.game.world.update.flag.chunk.ItemUpdateFlag;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.BuildItemContext;
import org.crandor.net.packet.out.ClearGroundItem;
import org.crandor.net.packet.out.ConstructGroundItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents one of the 4 planes of a region.
 * @author Emperor
 *
 */
public final class RegionPlane {
	
	/**
	 * The region size.
	 */
	public static final int REGION_SIZE = 64;
	
	/**
	 * The amount of chunks in this plane.
	 */
	public static final int CHUNK_SIZE = REGION_SIZE >> 3;
	
	/**
	 * Represents a removed game object.
	 */
	public static final GameObject NULL_OBJECT = new GameObject(0, Location.create(0, 0, 0));
	
	/**
	 * The plane.
	 */
	private final int plane;
	
	/**
	 * The region.
	 */
	private final Region region;
	
	/**
	 * The region flags.
	 */
	private final RegionFlags flags;
	
	/**
	 * The region projectile flags.
	 */
	private final RegionFlags projectileFlags;
	
	/**
	 * The region chunks.
	 */
	private final RegionChunk[][] chunks;
	
	/**
	 * The list of NPCs in this region.
	 */
	private final List<NPC> npcs;
	
	/**
	 * The list of players in this region.
	 */
	private final List<Player> players;
	
	/**
	 * The game objects.
	 */
	private GameObject[][] objects;
	
	/**
	 * Constructs a new {@code RegionPlane} {@code Object}.
	 * @param region The region.
	 * @param plane The plane.
	 */
	public RegionPlane(Region region, int plane) {
		this.plane = plane;
		this.region = region;
		this.players = new CopyOnWriteArrayList<Player>();
		this.npcs = new CopyOnWriteArrayList<NPC>();
		Location base = region.getBaseLocation();
		this.flags = new RegionFlags(plane, base.getX(), base.getY());
		this.projectileFlags = new RegionFlags(plane, base.getX(), base.getY(), true);
		this.objects = new GameObject[REGION_SIZE][REGION_SIZE];
		this.chunks = new RegionChunk[CHUNK_SIZE][CHUNK_SIZE];
	}

	/**
	 * Called at the end of the update sequence, if the region is active.
	 */
	public void pulse() {
		int length = chunks.length;
		for (int i = 0; i < length; i++) {
			int rlength = chunks[i].length;
			for (int j = 0; j < rlength; j++ ) {
				RegionChunk chunk = chunks[i][j];
				if (chunk != null) {
					chunk.resetFlags();
				}
			}
		}
	}

	/**
	 * Adds a game object.
	 * @param object The object to add.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param landscape If this object is added through landscape parsing.
	 */
	public void add(GameObject object, int x, int y, boolean landscape) {
		setChunkObject(x, y, object);
		if (landscape) {
			objects[x][y] = object;
		}
		if (object != null) {
			object.setRenderable(true);
		}
	}
	
	/**
	 * Gets the region chunk.
	 * @param chunkX The chunk base x-coordinate.
	 * @param chunkY The chunk base y-coordinate.
	 * @return The region chunk.
	 */
	public RegionChunk getRegionChunk(int chunkX, int chunkY) {
		RegionChunk r = chunks[chunkX][chunkY];
		if (r != null) {
			return r;
		}
		if (region.isBuild()) {
			return chunks[chunkX][chunkY] = new BuildRegionChunk(region.getBaseLocation().transform(chunkX << 3, chunkY << 3, plane), 0, this);
		}
		return chunks[chunkX][chunkY] = new RegionChunk(region.getBaseLocation().transform(chunkX << 3, chunkY << 3, plane), 0, this);
	}
	
	/**
	 * Removes a game object.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 */
	public void remove(int x, int y) {
		remove(x, y, -1);
	}

	/**
	 * Removes a game object.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param objectId The object id.
	 */
	public void remove(int x, int y, int objectId) {
		int chunkX = x / CHUNK_SIZE;
		int chunkY = y / CHUNK_SIZE;
		int offsetX = x - chunkX * CHUNK_SIZE;
		int offsetY = y - chunkY * CHUNK_SIZE;
		RegionChunk chunk = getRegionChunk(chunkX, chunkY);
		GameObject remove = new GameObject(0, region.getBaseLocation().transform(x, y, plane), 22, 0);
		remove.setRenderable(false);
		if (chunk instanceof BuildRegionChunk) {
			int index = ((BuildRegionChunk) chunk).getIndex(offsetX, offsetY, objectId);
			((BuildRegionChunk) chunk).getObjects(index)[offsetX][offsetY] = remove;
			return;
		}
		chunk.getObjects()[offsetX][offsetY] = remove;
	}
	
	/**
	 * Sets an object on a chunk. 
	 * @param x The regional x-coordinate.
	 * @param y The regional y-coordinate.
	 * @param object The object to set.
	 */
	private RegionChunk setChunkObject(int x, int y, GameObject object) {
		int chunkX = x / CHUNK_SIZE;
		int chunkY = y / CHUNK_SIZE;
		int offsetX = x - chunkX * CHUNK_SIZE;
		int offsetY = y - chunkY * CHUNK_SIZE;
		RegionChunk r = getRegionChunk(chunkX, chunkY);
		if (r instanceof BuildRegionChunk) {
			((BuildRegionChunk) r).store(object);
			return r;
		}
		r.getObjects()[offsetX][offsetY] = object;
		return r;
	}
	
	/**
	 * Gets the game objects.
	 * @return The game objects.
	 */
	public GameObject[][] getObjects() {
		return objects;
	}
	
	/**
	 * Clears this region plane.
	 */
	public void clear() {
		for (RegionChunk[] c : chunks) {
			for (RegionChunk chunk : c) {
				if (chunk != null) {
					chunk.clear();
				}
			}
		}
		if (region instanceof DynamicRegion && objects != null) {
			for (int x = 0; x < REGION_SIZE; x++) {
				for (int y = 0; y < REGION_SIZE; y++) {
					objects[x][y] = null;
				}
			}
			objects = null;
		}
	}
	
	/**
	 * Adds an NPC to this region.
	 * @param npc The NPC to add.
	 */
	public void add(NPC npc) {
		npcs.add(npc);
	}
	
	/**
	 * Adds a player to this region.
	 * @param player The player.
	 */
	public void add(Player player) {
		players.add(player);
	}

	/**
	 * Adds an item to this region.
	 * @param item The item.
	 */
	public void add(Item item) {
		Location l = item.getLocation();
		RegionChunk c = getRegionChunk(l.getLocalX() / RegionChunk.SIZE, l.getLocalY() / RegionChunk.SIZE);
		if (!c.getItems().add(item)) {
			return;
		}
		GroundItem g = (GroundItem) item;
		if (g.isPrivate()) {
			if (g.getDropper() != null) {
				PacketRepository.send(ConstructGroundItem.class, new BuildItemContext(g.getDropper(), item));
			}
			return;
		}
		c.flag(new ItemUpdateFlag(g, ItemUpdateFlag.CONSTRUCT_TYPE));
	}
	
	/**
	 * Removes an NPC from this region.
	 * @param npc The NPC.
	 */
	public void remove(NPC npc) {
		npcs.remove(npc);
	}
	
	/**
	 * Removes a player from this region.
	 * @param player The player.
	 */
	public void remove(Player player) {
		players.remove(player);
	}

	/**
	 * Removes an item from this region.
	 * @param item The ground item.
	 */
	public void remove(Item item) {
		Location l = item.getLocation();
		RegionChunk c = getRegionChunk(l.getLocalX() / RegionChunk.SIZE, l.getLocalY() / RegionChunk.SIZE);
		if (!c.getItems().remove(item)) {
			return;
		}
		GroundItem g = (GroundItem) item;
		g.setRemoved(true);
		g.setDecayTime(-2);
		if (g.isPrivate()) {
			if (g.getDropper() != null && g.getDropper().isPlaying() && g.getDropper().getLocation().withinDistance(l)) {
				PacketRepository.send(ClearGroundItem.class, new BuildItemContext(g.getDropper(), item));
			}
			return;
		}
		c.flag(new ItemUpdateFlag(g, ItemUpdateFlag.REMOVE_TYPE));
	}

	/**
	 * Gets the region flags.
	 * @return The flags.
	 */
	public RegionFlags getFlags() {
		return flags;
	}

	/**
	 * Gets the projectileFlags.
	 * @return The projectileFlags.
	 */
	public RegionFlags getProjectileFlags() {
		return projectileFlags;
	}

	/**
	 * Gets the npcs.
	 * @return The npcs.
	 */
	public List<NPC> getNpcs() {
		return npcs;
	}

	public List<Node> getEntities()
	{
	    List<Node> entities = new ArrayList<>(npcs);
	    for (GameObject[] o : getObjects())
		{
			entities.addAll(Arrays.asList(o));
		}

	    return  entities;
	}

	/**
	 * Gets the players.
	 * @return The players.
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets the plane.
	 * @return The plane.
	 */
	public int getPlane() {
		return plane;
	}

	/**
	 * Gets the region.
	 * @return The region.
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Gets an object from a region chunk.
	 * @param x The region x-coordinate.
	 * @param y The region y-coordinate.
	 * @return The game object.
	 */
	public GameObject getChunkObject(int x, int y) {
		return getChunkObject(x, y, -1);
	}

	/**
	 * Gets an object from a region chunk.
	 * @param x The region x-coordinate.
	 * @param y The region y-coordinate.
	 * @param objectId The object id.
	 * @return The game object.
	 */
	public GameObject getChunkObject(int x, int y, int objectId) {
		int chunkX = x / CHUNK_SIZE;
		int chunkY = y / CHUNK_SIZE;
		int offsetX = x - chunkX * CHUNK_SIZE;
		int offsetY = y - chunkY * CHUNK_SIZE;
		RegionChunk chunk = getRegionChunk(chunkX, chunkY);
		if (chunk instanceof BuildRegionChunk) {
			BuildRegionChunk brc = (BuildRegionChunk) chunk;
			return brc.get(offsetX, offsetY, brc.getIndex(offsetX, offsetY, objectId));
		}
		return getRegionChunk(chunkX, chunkY).getObjects()[offsetX][offsetY];
	}
	
	/**
	 * Gets an object from a region chunk.
	 * @param x The region x-coordinate.
	 * @param y The region y-coordinate.
	 * @return The game object.
	 */
	public List<Item> getChunkItems(int x, int y) {
		int chunkX = x / CHUNK_SIZE;
		int chunkY = y / CHUNK_SIZE;
		return getRegionChunk(chunkX, chunkY).getItems();
	}

	/**
	 * Gets a ground item from this plane.
	 * @param itemId The item id.
	 * @param l The location.
	 * @param player The player.
	 * @return The item.
	 */
	public GroundItem getItem(int itemId, Location l, Player player) {
		GroundItem groundItem = null;
		for (Item item : getChunkItems(l.getLocalX(), l.getLocalY())) {
			GroundItem g = (GroundItem) item;
			if (g.getId() == itemId && l.equals(g.getLocation()) && !g.isRemoved()) {
				if (groundItem != null && (!g.isPrivate() || player == null)) {
					continue;
				}
				if ((!g.isPrivate() || player == null || g.droppedBy(player))) {
					groundItem = g;
				}
			}
		}
		return groundItem;
	}
	
	/**
	 * Gets the region chunks.
	 * @return The chunks.
	 */
	public RegionChunk[][] getChunks() {
		return chunks;
	}

}