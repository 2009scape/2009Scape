package core.game.world.map;


import core.game.node.entity.player.Player;
import core.game.node.item.GroundItem;
import core.game.node.item.Item;
import core.game.node.object.Constructed;
import core.game.node.object.GameObject;
import core.game.world.map.build.LandscapeParser;
import core.net.packet.IoBuffer;
import core.net.packet.out.ClearObject;
import core.net.packet.out.ConstructGroundItem;
import core.net.packet.out.ConstructObject;

/**
 * A region chunk, used for easily modifying objects.
 * @author Emperor
 *
 */
public class BuildRegionChunk extends RegionChunk {

	/**
	 * The maximum amount of objects to be stored on one tile in the chunk.
	 */
	public static final int ARRAY_SIZE = 5;

	/**
	 * The list of changes made.
	 */
	private final GameObject[][][] objects;

	/**
	 * Constructs a new {@code BuildRegionChunk} {@code Object}
	 * @param base The base location.
	 * @param rotation The rotation.
	 * @param plane The region plane.
	 */
	public BuildRegionChunk(Location base, int rotation, RegionPlane plane) {
		super(base, rotation, plane);
		this.objects = new GameObject[ARRAY_SIZE][8][8];
		this.objects[0] = super.objects;
	}

	@Override
	protected boolean appendUpdate(Player player, IoBuffer buffer) {
		boolean updated = false;//super.appendUpdate(player, buffer);
		for (int i = 0; i < objects.length; i++) {
			for (int x = 0; x < SIZE; x++) {
				for (int y = 0; y < SIZE; y++) {
					GameObject o = objects[i][x][y];
					if (o instanceof Constructed) {
						ConstructObject.write(buffer, o);
						updated = true;
					}
					else if (o != null && !o.isRenderable()) {
						ClearObject.write(buffer, o);
						updated = true;
					}
				}
			}
		}
		if (items != null) {
			for (Item item : items) {
				if (item != null && item.isActive() && item.getLocation() != null) {
					GroundItem g = (GroundItem) item;
					if (!g.isPrivate() || g.droppedBy(player)) {
						ConstructGroundItem.write(buffer, item);
						updated = true;
					}
				}
			}
		}
		return updated;
	}

	@Override
	public void rotate(Direction direction) {
		if (rotation != 0) {
			System.err.println("Region chunk was already rotated!");
			return;
		}
		GameObject[][][] copy = new GameObject[ARRAY_SIZE][SIZE][SIZE];
		int baseX = currentBase.getLocalX();
		int baseY = currentBase.getLocalY();
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				for (int i = 0; i < objects.length; i++) {
					copy[i][x][y] = objects[i][x][y];
					objects[i][x][y] = null;
				}
				plane.getObjects()[baseX + x][baseY + y] = null;
				plane.getFlags().getClippingFlags()[baseX + x][baseY + y] = 0;
			}
		}
		clear();
		rotation = (direction.toInteger() + (direction.toInteger() % 2 == 0 ? 2 : 0)) % 4;
		for (int i = 0; i < objects.length; i++) {
			for (int x = 0; x < SIZE; x++) {
				for (int y = 0; y < SIZE; y++) {
					GameObject object = copy[i][x][y];
					if (object != null) {
						int[] pos = getRotatedPosition(x, y, object.getDefinition().getSizeX(), object.getDefinition().getSizeY(), object.getRotation(), rotation);
						GameObject obj = object.transform(object.getId(), (object.getRotation() + rotation) % 4, object.getLocation().transform(pos[0] - x, pos[1] - y, 0));
						if (object instanceof Constructed) {
							obj = obj.asConstructed();
						}
						obj.setActive(object.isActive());
						obj.setRenderable(object.isRenderable());
						LandscapeParser.flagGameObject(plane, baseX + pos[0], baseY + pos[1], obj, true, true);
					}
				}
			}
		}
	}

	@Override
	public BuildRegionChunk copy(RegionPlane plane) {
		BuildRegionChunk chunk = new BuildRegionChunk(base, rotation, plane);
		for (int i = 0; i < chunk.objects.length; i++) {
			for (int x = 0; x < SIZE; x++) {
				for (int y = 0; y < SIZE; y++) {
					GameObject o = objects[i][x][y];
					if (o instanceof Constructed) {
						chunk.objects[i][x][y] = o.transform(o.getId(), o.getRotation()).asConstructed();
					}
					else if (o != null) {
						chunk.objects[i][x][y] = o.transform(o.getId());
						chunk.objects[i][x][y].setActive(o.isActive());
						chunk.objects[i][x][y].setRenderable(o.isRenderable());
					}
				}
			}
		}
		return chunk;
	}

	@Override
	public void clear() {
		super.clear();
		for (int i = 0; i < objects.length; i++) {
			for (int x = 0; x < objects[i].length; x++) {
				for (int y = 0; y < objects[i][x].length; y++) {
					objects[i][x][y] = null;
				}
			}
		}
	}

	/**
	 * Removes the game object.
	 * @param object The object to remove.
	 */
	public void remove(GameObject object) {
		int chunkX = object.getLocation().getChunkOffsetX();
		int chunkY = object.getLocation().getChunkOffsetY();
		GameObject current = null;
		int index = -1;
		int i = 0;
		while ((current == null || current.getId() != object.getId()) && i < objects.length) {
			current = objects[i++][chunkX][chunkY];
			if ((current == null || current.getId() < 1) && index == -1) {
				index = i - 1;
			}
		}
		if (current != null && current.equals(object)) {
			current.setActive(false);
			object.setRenderable(false);
		}
		else {
			objects[index][chunkX][chunkY] = object;
		}
		object.setActive(false);
		object.setRenderable(false);
	}

	/**
	 * Adds the game object.
	 * @param object The object to add.
	 */
	public void add(GameObject object) {
		int chunkX = object.getLocation().getChunkOffsetX();
		int chunkY = object.getLocation().getChunkOffsetY();
		GameObject current = null;
		int index = -1;
		int i = 0;
		while ((current == null || current.getId() != object.getId()) && i < objects.length) {
			current = objects[i++][chunkX][chunkY];
			if ((current == null || current.getId() < 1) && index == -1) {
				index = i - 1;
			}
		}
		if (current != null && current.equals(object)) {
			current.setActive(true);
			current.setRenderable(true);
		}
		else if (index == -1) {
			throw new IllegalStateException("Insufficient array length for storing object!");
		}
		else {
			objects[index][chunkX][chunkY] = object = object.asConstructed();
		}
		object.setActive(true);
		object.setRenderable(true);
	}

	/**
	 * Stores an object on the region chunk.
	 * @param object The object.
	 */
	public void store(GameObject object) {
		if (object == null) {
			return;
		}
		int chunkX = object.getLocation().getChunkOffsetX();
		int chunkY = object.getLocation().getChunkOffsetY();
		for (int i = 0; i < objects.length; i++) {
			GameObject stat = objects[i][chunkX][chunkY];
			if (stat == null || stat.getId() < 1) {
				objects[i][chunkX][chunkY] = object;
				object.setActive(true);
				object.setRenderable(true);
				return;
			}
		}
		System.err.print("Objects - [");
		for (int i = 0; i < objects.length; i++) {
			System.err.print(objects[i][chunkX][chunkY]);
			if (i < objects.length - 1) {
				System.err.print(", ");
			}
		}
		System.err.println("]!");
		throw new IllegalStateException("Insufficient array length for storing all objects! ");
	}

	/**
	 * Gets the objects index for the given object id.
	 * @param x The x-coordinate on the region chunk.
	 * @param y The y-coordinate on the region chunk.
	 * @param objectId The object id.
	 */
	public int getIndex(int x, int y, int objectId) {
		for (int i = 0; i < objects.length; i++) {
			GameObject o = get(x, y, i);
			if (o != null && ((objectId > -1 && o.getId() == objectId) || (objectId == -1 && o.getDefinition().hasOptions(false)))) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Gets a game object.
	 * @param x The chunk x-coordinate.
	 * @param y The chunk y-coordinate.
	 * @param index The index (0 = default).
	 * @return The object.
	 */
	public GameObject get(int x, int y, int index) {
		return objects[index][x][y];
	}

	@Override
	public GameObject[] getObjects(int chunkX, int chunkY) {
		GameObject[] objects = new GameObject[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			objects[i] = this.objects[i][chunkX][chunkY];
		}
		return objects;
	}

	/**
	 * Gets the objects.
	 * @param index The index.
	 * @return The objects array.
	 */
	public GameObject[][] getObjects(int index) {
		return objects[index];
	}
}