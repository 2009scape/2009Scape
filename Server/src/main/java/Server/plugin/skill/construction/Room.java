package plugin.skill.construction;


import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.*;

/**
 * Represents a room.
 * @author Emperor
 *
 */
public final class Room {
	
	/**
	 * The default room type.
	 */
	public static final int CHAMBER = 0x0;
	
	/**
	 * The rooftop room type.
	 */
	public static final int ROOF = 0x1;
	
	/**
	 * The dungeon room type.
	 */
	public static final int DUNGEON = 0x2;
	
	/**
	 * The dungeon room type.
	 */
	public static final int LAND = 0x4;

	/**
	 * The room properties.
	 */
	private RoomProperties properties;

	/**
	 * The region chunk.
	 */
	private RegionChunk chunk;
		
	/**
	 * The hotspots.
	 */
	private Hotspot[] hotspots; 
	
	/**
	 * The current rotation of the room.
	 */
	private Direction rotation = Direction.NORTH;
	
	/**
	 * Constructs a new {@code Room} {@code Object}.
	 * @param properties The room properties.
	 */
	public Room(RoomProperties properties) {
		this.properties = properties;
	}

	/**
	 * Creates a new room.
	 * @param player The player.
	 * @param properties The room properties.
	 * @return The room.
	 */
	public static Room create(Player player, RoomProperties properties) {
		Room room = new Room(properties);
		room.configure(player.getHouseManager().getStyle());
		return room;
	}
	
	/**
	 * Configures the room.
	 */
	public void configure(HousingStyle style) {
		this.hotspots = new Hotspot[properties.getHotspots().length];
		for (int i = 0; i < hotspots.length; i++) {
			hotspots[i] = properties.getHotspots()[i].copy();
		}
		decorate(style);
	}

	/**
	 * Redecorates the room.
	 * @param style The house style.
	 */
	public void decorate(HousingStyle style) {
		Region region = RegionManager.forId(style.getRegionId());
		Region.load(region, true);
		chunk = region.getPlanes()[style.getPlane()].getRegionChunk(properties.getChunkX(), properties.getChunkY());
	}
	
	/**
	 * Gets the hotspot object for the given hotspot type.
	 * @param hotspot The hotspot type.
	 * @return The hotspot.
	 */
	public Hotspot getHotspot(BuildHotspot hotspot) {
		for (Hotspot h : hotspots) {
			if (h.getHotspot() == hotspot) {
				return h;
			}
		}
		return null;
	}
	
	/**
	 * Checks if the building hotspot has been built.
	 * @param hotspot The building hotspot.
	 * @return {@code True} if so.
	 */
	public boolean isBuilt(BuildHotspot hotspot) {
		Hotspot h = getHotspot(hotspot);
		return h != null && h.getDecorationIndex() > -1;
	}
	
	/**
	 * Loads all the decorations.
	 * @param housePlane The plane.
	 * @param chunk The chunk used in the dynamic region.
     */
	public void loadDecorations(int housePlane, BuildRegionChunk chunk, HouseManager house) {
		for (int i = 0; i < hotspots.length; i++) {
			Hotspot spot = hotspots[i];
			int x = spot.getChunkX();
			int y = spot.getChunkY();
			if (spot.getHotspot() == null) {
				continue;
			}
			int index = chunk.getIndex(x, y, spot.getHotspot().getObjectId(house.getStyle()));
			GameObject[][] objects = chunk.getObjects(index);
			GameObject object = objects[x][y];
			if (object != null && object.getId() == spot.getHotspot().getObjectId(house.getStyle())) {
				if (spot.getDecorationIndex() > -1 && spot.getDecorationIndex() < spot.getHotspot().getDecorations().length) {
					int id = spot.getHotspot().getDecorations()[spot.getDecorationIndex()].getObjectId(house.getStyle());
					if (spot.getHotspot().getType() == BuildHotspotType.CREST) {
						id += house.getCrest().ordinal();
					}
					ObjectBuilder.replace(object, object.transform(id, object.getRotation(), chunk.getCurrentBase().transform(x, y, 0)));
				}
				else if (object.getId() == BuildHotspot.WINDOW.getObjectId(house.getStyle()) || (!house.isBuildingMode() && object.getId() == BuildHotspot.CHAPEL_WINDOW.getObjectId(house.getStyle()))) {
					chunk.add(object.transform(house.getStyle().getWindowStyle().getObjectId(house.getStyle()), object.getRotation(), object.getType()));
				}
				int[] pos = RegionChunk.getRotatedPosition(x, y, object.getSizeX(), object.getSizeY(), 0, rotation.toInteger());
				spot.setCurrentX(pos[0]);
				spot.setCurrentY(pos[1]);
			}
		}
		if (!house.isBuildingMode()) {
			removeHotspots(housePlane, house, chunk);
		}
		if (rotation != Direction.NORTH && chunk.getRotation() == 0) {
			chunk.rotate(rotation);
		}
	}
	
	/**
	 * Removes the building hotspots from the room.
	 * @param housePlane The room's plane in house.
	 * @param house The house manager.
	 * @param chunk The region chunk used.
	 */
	private void removeHotspots(int housePlane, HouseManager house, BuildRegionChunk chunk) {
		for (int i = 0; i < BuildRegionChunk.ARRAY_SIZE; i++) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					GameObject object = chunk.get(x, y, i);
					if (object != null && object.getDefinition().hasAction("Build")) {
						if (properties.isChamber() && BuildingUtils.isDoorHotspot(object)) {
							if (!placeDoors(house, chunk, object, housePlane, x, y, rotation)) {
								chunk.remove(object);
							}
						} else {
//							BuildHotspot hs = BuildHotspot.forId(object.protocol(), house.getStyle());
//							for (Hotspot h : hotspots) {
//								if (h != null && h.getHotspot() == hs && h.getHotspot().getObjectIds() == null) {
//									chunk.remove(object);
//									break;
//								}
//							}
							chunk.remove(object);
						}
					}
				}
			}
		}
	}

	/**
	 * Places the doors when needed.
	 * @param chunk The chunk.
	 * @param object The object.
	 * @param x The x-coordinate of the object.
	 * @param y The y-coordinate of the object.
	 */
	private boolean placeDoors(HouseManager house, BuildRegionChunk chunk, GameObject object, int z, int x, int y, Direction rotation) {
		int doorX;
		int doorY;
		switch (rotation) {
		case EAST:
			doorX = y;
			doorY = 7 - x;
			break;
		case SOUTH:
			doorX = 7 - x;
			doorY = 7 - y;
			break;
		case WEST:
			doorX = 7 - y;
			doorY = x;
			break;
		default:
			doorX = x;
			doorY = y;
			break;
		}
		int chunkX = chunk.getCurrentBase().getChunkX();
		int chunkY = chunk.getCurrentBase().getChunkY();
		boolean houseExit = true;
		Room r;
		if (doorX == 0 && chunkX > 0 && (r = house.getRooms()[z][chunkX - 1][chunkY]) != null && r.getProperties().isChamber()) {
			houseExit =  false;
		}
		else if (doorX == 7 && chunkX < 7 && (r = house.getRooms()[z][chunkX + 1][chunkY]) != null && r.getProperties().isChamber()) {
			houseExit =  false;
		}
		else if (doorY == 0 && chunkY > 0 && (r = house.getRooms()[z][chunkX][chunkY - 1]) != null && r.getProperties().isChamber()) {
			houseExit =  false;
		}
		else if (doorY == 7 && chunkY < 7 && (r = house.getRooms()[z][chunkX][chunkY + 1]) != null && r.getProperties().isChamber()) {
			houseExit =  false;
		}
		int replaceId = object.getId() % 2 != 0 ? house.getStyle().getDoorId() : house.getStyle().getSecondDoorId();
		houseExit = false;
		if (z != 0 && houseExit) {
			r = house.getRooms()[z][chunkX][chunkY];
			if (r.getProperties().isDungeon()) {
				replaceId = 13065;
			} else {
				replaceId = house.getStyle().getWallId();
			}
		}
		else if (!houseExit) {
			return false;
		}
		return ObjectBuilder.replace(object, object.transform(replaceId, object.getRotation(), chunk.getCurrentBase().transform(x, y, 0)), true, true);
	}
	
	/**
	 * Sets the decoration index for a group of object ids
	 * @param index The index.
	 * @param hs The building hotspot.
	 */
	public void setAllDecorationIndex(int index, BuildHotspot hs) {
		for (int i = 0; i < hotspots.length; i++) {
			Hotspot h = hotspots[i];
			if (h.getHotspot() == hs) {
				h.setDecorationIndex(index);
			}
		}
	}

	/**
	 * Gets the stairs hotspot for this room (or null if no stairs are available).
	 * @return The stairs.
	 */
	public Hotspot getStairs() {
		for (Hotspot h : hotspots) {
			if (h.getHotspot().getType() == BuildHotspotType.STAIRCASE
					|| h.getHotspot() == BuildHotspot.LADDER || h.getHotspot() == BuildHotspot.TRAPDOOR
					|| (h.getHotspot() == BuildHotspot.CENTREPIECE_1 && h.getDecorationIndex() == 4)
					|| (h.getHotspot() == BuildHotspot.CENTREPIECE_2 && h.getDecorationIndex() == 2)) {
				return h;
			}
		}
		return null;
	}
		
	/**
	 * Gets the exit directions
	 * @return The exits information.
	 */
	public boolean[] getExits() {
		return getExits(rotation);
	}

	/**
	 * Gets the exit directions.
	 * @return The directions at which you can exit the room (0=east, 1=south, 2=west, 3=north).
	 */
	public boolean[] getExits(Direction rotation) {
		boolean[] exits = properties.getExits();
		if (chunk.getRotation() != rotation.toInteger()) {
			boolean[] exit = new boolean[exits.length];
			int offset = rotation.toInteger() - chunk.getRotation();
			for (int i = 0; i < 4; i++) {
				exit[(i + offset) % 4] = exits[i];
			}
			return exit;
		}
		return exits;
	}

	/**
	 * Gets the hotspot for the given coordinates.
	 * @param build The build hotspot.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @return The hotspot.
	 */
	public Hotspot getHotspot(BuildHotspot build, int x, int y) {
		for (int i = 0; i < getHotspots().length; i++) {
			Hotspot h = getHotspots()[i];
			if (h.getCurrentX() == x && h.getCurrentY() == y && h.getHotspot() == build) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Sets the properties.
	 * @param properties The properties.
	 */
	public void updateProperties(Player player, RoomProperties properties) {
		this.properties = properties;
		decorate(player.getHouseManager().getStyle());
		if (hotspots.length != properties.getHotspots().length) {
			return;
		}
		for (int i = 0; i < hotspots.length; i++) {
			Hotspot h = hotspots[i];
			Hotspot hs = hotspots[i] = properties.getHotspots()[i].copy();
			hs.setCurrentX(h.getCurrentX());
			hs.setCurrentY(h.getCurrentY());
			hs.setDecorationIndex(h.getDecorationIndex());
		}
	}

	/**
	 * Gets the chunk.
	 * @return The chunk.
	 */
	public RegionChunk getChunk() {
		return chunk;
	}

	/**
	 * Sets the chunk.
	 * @param chunk The chunk to set.
	 */
	public void setChunk(RegionChunk chunk) {
		this.chunk = chunk;
	}

	/**
	 * Gets the hotspots.
	 * @return The hotspots.
	 */
	public Hotspot[] getHotspots() {
		return hotspots;
	}

	/**
	 * Sets the hotspots.
	 * @param hotspots The hotspots to set.
	 */
	public void setHotspots(Hotspot[] hotspots) {
		this.hotspots = hotspots;
	}

	/**
	 * Gets the properties.
	 * @return The properties.
	 */
	public RoomProperties getProperties() {
		return properties;
	}

	/**
	 * Sets the room rotation.
	 * @param rotation The rotation.
	 */
	public void setRotation(Direction rotation) {
		this.rotation = rotation;
	}

	/**
	 * Gets the rotation.
	 * @return The rotation.
	 */
	public Direction getRotation() {
		return rotation;
	}
	
}