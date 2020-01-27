package org.crandor.game.content.skill.member.construction;


//import org.arios.game.content.global.DeadmanTimedAction;
//import org.arios.game.node.entity.player.info.login.SavingModule;

import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.login.SavingModule;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.*;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.update.flag.context.Animation;

import java.awt.*;
import java.nio.ByteBuffer;

/**
 * Manages the player's house.
 * @author Emperor
 *
 */
public final class HouseManager implements SavingModule {

	/**
	 * The current region.
	 */
	private DynamicRegion region;
	
	/**
	 * The current region.
	 */
	private DynamicRegion dungeon;

	/**
	 * The house location.
	 */
	private HouseLocation location = HouseLocation.NOWHERE;

	/**
	 * The house style.
	 */
	private HousingStyle style = HousingStyle.BASIC_WOOD;

	/**
	 * The house zone.
	 */
	private final HouseZone zone = new HouseZone(this);
	
	/**
	 * The player's house rooms.
	 */
	private final Room[][][] rooms = new Room[4][8][8];

	/**
	 * If building mode is enabled.
	 */
	private boolean buildingMode;

	/**
	 * If the player has used the portal to lock their house.
	 */
	private boolean locked;

	/**
	 * The player's servant.
	 */
	private Servant servant;

	/**
	 * If the house has a dungeon.
	 */
	private boolean hasDungeon;

	/**
	 * The player's crest.
	 */
	private CrestType crest = CrestType.ASGARNIA;
	
	/**
	 * Constructs a new {@code HouseManager} {@code Object}.
	 */
	public HouseManager() {
		/*
		 * empty.
		 */
	}


	public void save(ByteBuffer buffer) {
		buffer.put((byte) location.ordinal());
		buffer.put((byte) style.ordinal());
		if (hasServant()) {
			servant.save(buffer);
		} else {
			buffer.put((byte) -1);
		}
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					Room room = rooms[z][x][y];
					if (room != null) {
						buffer.put((byte) z).put((byte) x).put((byte) y);
						buffer.put((byte) room.getProperties().ordinal());
						buffer.put((byte) room.getRotation().toInteger());
						for (int i = 0; i < room.getHotspots().length; i++) {
							if (room.getHotspots()[i].getDecorationIndex() > -1) {
								buffer.put((byte) i);
								buffer.put((byte) room.getHotspots()[i].getDecorationIndex());
							}
						}
						buffer.put((byte) -1);
					}
				}
			}
		}
		buffer.put((byte) -1);//Eof
	}


	public void parse(ByteBuffer buffer) {
		location = HouseLocation.values()[buffer.get() & 0xFF];
		style = HousingStyle.values()[buffer.get() & 0xFF];
		servant = Servant.parse(buffer);
		int z = 0;
		while ((z = buffer.get()) != -1) {
			if (z == 3) {
				hasDungeon = true;
			}
			int x = buffer.get();
			int y = buffer.get();
			Room room = rooms[z][x][y] = new Room(RoomProperties.values()[buffer.get() & 0xFF]);
			room.configure(style);
			room.setRotation(Direction.get(buffer.get() & 0xFF));
			int spot = 0;
			while ((spot = buffer.get()) != -1) {
				room.getHotspots()[spot].setDecorationIndex(buffer.get() & 0xFF);
			}
		}
	}

	/**
	 * Enters the player's house.
	 * @param player The player entering.
	 * @param buildingMode If building mode is enabled.
	 * @param teleport if the entry was a teleport.
	 */
	public void enter(final Player player, boolean buildingMode, boolean teleport) {
		enter(player, buildingMode);
	}
	
	/**
	 * Enter's the player's house.
	 * @param player
	 * @param buildingMode
	 */
	public void enter(final Player player, boolean buildingMode) {
		if (HouseManager.this.buildingMode != buildingMode || !isLoaded()) {
			HouseManager.this.buildingMode = buildingMode;
			construct();
		}
		player.setAttribute("poh_entry", HouseManager.this);
		player.lock(1);
		player.sendMessage("House location: " + region.getBaseLocation() + ", entry: " + getEnterLocation());
		player.getProperties().setTeleportLocation(getEnterLocation());
		player.getInterfaceManager().openComponent(399);
		player.getConfigManager().set(261, buildingMode);
		player.getConfigManager().set(262, getRoomAmount());
		player.getAudioManager().send(new Audio(984));
//		player.getMusicPlayer().unlock(454, true);
		GameWorld.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				if (hasServant()){
					spawnServant();
					if (servant.isGreet()){
						player.getDialogueInterpreter().sendDialogues(servant.getType().getId(), servant.getType().getId() == 4243 ? FacialExpression.HALF_GUILTY : null, "Welcome.");
					}
				}
//				player.getInterfaceManager().switchWindowMode(1);
				player.getInterfaceManager().close();
				return true;
			}
		});
		/*if (player.getHouseManager() == this && location.equals(HouseLocation.WHITERIDGE)) {
			//player.getAchievementDiaryManager().updateTask(player, DiaryType.WHITERIDGE, 2, 1, true);
		}*/
		if (location.equals(HouseLocation.YANILLE)) {
			//player.getAchievementDiaryManager().updateTask(player, DiaryType.ARDOUGNE, 1, 5, true);
		}
	}

	/**
	 * Leaves this house.
	 * @param player The player leaving.
	 */
	public static void leave(Player player) {
		HouseManager house = player.getAttribute("poh_entry", player.getHouseManager());
		if (house.getRegion() == null){
			return;
		}
		if (house.isInHouse(player)) {
			player.animate(Animation.RESET);
			player.setLocation(house.location.getExitLocation());
		}
	}

	/**
	 * Toggles the building mode.
	 * @param player The house owner.
	 * @param enable If the building mode should be enabled.
	 */
	public void toggleBuildingMode(Player player, boolean enable) {
		if (!isInHouse(player)) {
			player.getPacketDispatch().sendMessage("Building mode really only helps if you're in a house.");
			return;
		}
		if (buildingMode != enable) {
			if (enable) {
				expelGuests(player);
			}
			buildingMode = enable;
			reload(player, enable);
			player.getPacketDispatch().sendMessage("Building mode is now " + (buildingMode ? "on." : "off."));
		}
	}

    /**
     * Reloads the house.
     * @param player The player.
     * @param buildingMode If building mode should be enabled.
     */
    public void reload(Player player, boolean buildingMode) {
        DynamicRegion r = region;
        if ((player.getViewport().getRegion() == dungeon)) {
            r = dungeon;
        }
        int diffX = player.getLocation().getX() - r.getBaseLocation().getX();
        int diffY = player.getLocation().getY() - r.getBaseLocation().getY();
        int diffZ = player.getLocation().getZ() - r.getBaseLocation().getZ();
        region = null;
        dungeon = null;
        enter(player, buildingMode, false);
        player.getProperties().setTeleportLocation((player.getViewport().getRegion() == dungeon ? dungeon : region).getBaseLocation().transform(diffX, diffY, diffZ));
    }

	/**
	 * Expels the guests from the house.
	 * @param player The house owner.
	 */
	public void expelGuests(Player player) {
		if (isLoaded()) {
			for (RegionPlane plane : region.getPlanes()) {
				for (Player p : plane.getPlayers()) {
					if (p != player) {
						leave(p);
					}
				}
			}
			if (dungeon != null) {
				for (RegionPlane plane : dungeon.getPlanes()) {
					for (Player p : plane.getPlayers()) {
						if (p != player) {
							leave(p);
						}
					}
				}
			}
		}
	}

	/**
	 * Gets the entering location.
	 * @return The entering location.
	 */
	public Location getEnterLocation() {
		if (region == null) {
			System.err.println("House wasn't constructed yet!");
			return null;
		}
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Room room = rooms[0][x][y];
				if (room != null && (room.getProperties() == RoomProperties.GARDEN || room.getProperties() == RoomProperties.FORMAL_GARDEN)) {
					for (Hotspot h : room.getHotspots()) {
						if (h.getDecorationIndex() > -1) {
							Decoration d = h.getHotspot().getDecorations()[h.getDecorationIndex()];
							if (d == Decoration.PORTAL) {
								return region.getBaseLocation().transform(x * 8 + h.getChunkX(), y * 8 + h.getChunkY() + 2, 0);
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Redecorates the house.
	 * @param style The new style.
	 */
	public void redecorate(HousingStyle style) {
		this.style = style;
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					Room room = rooms[z][x][y];
					if (room != null) {
						room.decorate(style);
					}
				}
			}
		}
	}

	/**
	 * Clears all the rooms (<b>Including portal room!</b>).
	 */
	@Deprecated
	public void clearRooms() {
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					rooms[z][x][y] = null;
				}
			}
		}
	}

	/**
	 * Creates the default house.
	 * @param location The house location.
	 */
	public void create(HouseLocation location) {
		clearRooms();
		Room room = rooms[0][4][3] = new Room(RoomProperties.GARDEN);
		room.configure(style);
		room.getHotspots()[0].setDecorationIndex(0);
		this.location = location;
	}

	/**
	 * Constructs the dynamic region for the house.
	 * @return The region.
	 */
	public DynamicRegion construct() {
		Region from = RegionManager.forId(style.getRegionId());
		Region.load(from, true);
		RegionChunk defaultChunk = from.getPlanes()[style.getPlane()].getRegionChunk(1, 0);
		ZoneBorders borders = DynamicRegion.reserveArea(8, 8);
		region = new DynamicRegion(-1, borders.getSouthWestX() >> 6, borders.getSouthWestY() >> 6);
		region.setBorders(borders);
		region.setUpdateAllPlanes(true);
		RegionManager.getRegionCache().put(region.getId(), region);
		configureRoofs();
		for (int z = 0; z < 3; z++) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					Room room = rooms[z][x][y];
					if (room != null) {
						if (room.getProperties().isRoof() && buildingMode) {
							continue;
						}
						BuildRegionChunk copy = room.getChunk().copy(region.getPlanes()[z]);
						region.replaceChunk(z, x, y, copy, from);
						room.loadDecorations(z, copy, this);
					} else {
						region.replaceChunk(z, x, y, z != 0 ? null : defaultChunk.copy(region.getPlanes()[0]), from);
					}
				}
			}
		}
		if (hasDungeon()) {
			defaultChunk = from.getPlanes()[style.getPlane()].getRegionChunk(3, 0);
			borders = DynamicRegion.reserveArea(8, 8);
			dungeon = new DynamicRegion(-1, borders.getSouthWestX() >> 6, borders.getSouthWestY() >> 6);
			dungeon.setBorders(borders);
			dungeon.setUpdateAllPlanes(true);
			RegionManager.getRegionCache().put(dungeon.getId(), dungeon);
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					Room room = rooms[3][x][y];
					if (hasRoom(3, x, y)) {
						BuildRegionChunk copy = room.getChunk().copy(dungeon.getPlanes()[0]);
						dungeon.replaceChunk(0, x, y, copy, from);
						room.loadDecorations(3, copy, this);
					} else {
						dungeon.replaceChunk(0, x, y, buildingMode ? null : defaultChunk.copy(dungeon.getPlanes()[0]), from);
					} 
				}
			}
			region.link(dungeon);
		}
		ZoneBuilder.configure(zone);
		return region;
	}
	
	/**
	 * Configures the rooftops.
	 */
	public void configureRoofs() {
//		boolean[][][] roofs = new boolean[2][8][8];
//		for (int x = 0; x < 8; x++) {
//			for (int y = 0; y < 8; y++) {
//				Room room = rooms[0][x][y];
//				if (room != null && room.getProperties().isChamber()) {
//					room = rooms[1][x][y];
//					int z = 1;
//					if (room != null && room.getProperties().isChamber()) {
//						z = 2;
//					}
//					if (x > 0 )
//				}
//			}
//		}
	}

	/**
	 * Gets the current room plane.
	 * @param l The location.
	 * @return The plane of the room.
	 */
	public Room getRoom(Location l) {
		int z = l.getZ();
		if (dungeon != null && l.getRegionId() == dungeon.getId()) {
			z = 3;
		}
		return rooms[z][l.getChunkX()][l.getChunkY()];
	}

	/**
	 * Gets the hotspot for the given object.
	 * @param object The object.
	 * @return The hotspot.
	 */
	public Hotspot getHotspot(GameObject object) {
		Room room = getRoom(object.getLocation());
		if (room == null) {
			return null;
		}
		int chunkX = object.getLocation().getChunkOffsetX();
		int chunkY = object.getLocation().getChunkOffsetY();
		for (Hotspot h : room.getHotspots()) {
			//System.out.println(h.getHotspot().getObjectId(style) + ", "+object.protocol() + ", " +h.getCurrentX() + ", " +chunkX+", "+h.getCurrentY()+", "+chunkY);
			if (h.getChunkX() == chunkX && h.getChunkY() == chunkY && h.getHotspot().getObjectId(style) == object.getId()) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Checks if a room exists on the given location.
	 * @param z The plane.
	 * @param roomX The room x-coordinate.
	 * @param roomY The room y-coordinate.
	 * @return {@code True} if so.
	 */
	public boolean hasRoom(int z, int roomX, int roomY) {
		Room room = rooms[z][roomX][roomY];
		return room != null && !room.getProperties().isRoof();
	}
	
	/**
	 * Enters the dungeon.
	 * @param player The player.
	 */
	public void enterDungeon(Player player) {
		if (!hasDungeon()) {
			return;
		}
		int diffX = player.getLocation().getLocalX();
		int diffY = player.getLocation().getLocalY();
		player.getProperties().setTeleportLocation(dungeon.getBaseLocation().transform(diffX, diffY, 0));
	}
	
	/**
	 * Checks if an exit exists on the given room.
	 * @param roomX The x-coordinate of the room.
	 * @param roomY The y-coordinate of the room.
	 * @param direction The exit direction.
	 * @return {@code True} if so.
	 */
	public boolean hasExit(int z, int roomX, int roomY, Direction direction) {
		Room room = rooms[z][roomX][roomY];
		int index = (direction.toInteger() + 3) % 4;
		return room != null && room.getExits()[index];
	}

	/**
	 * Gets the amount of rooms.
	 * @return The amount of rooms.
	 */
	public int getRoomAmount() {
		int count = 0;
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					Room r = rooms[z][x][y];
					if (r != null && !r.getProperties().isRoof()) {
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Gets the amount of portals available.
	 * @return The amount of portals.
	 */
	public int getPortalAmount() {
		int count = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Room room = rooms[0][x][y];
				if (room != null && (room.getProperties() == RoomProperties.GARDEN
						|| room.getProperties() == RoomProperties.FORMAL_GARDEN) && room.getHotspots()[0].getDecorationIndex() == 0) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Gets the current house boundaries.
	 * @return The boundaries.
	 */
	public Rectangle getBoundaries() {
		int startX = 99;
		int startY = 99;
		int endX = 0;
		int endY = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (rooms[0][x][y] != null) {
					if (x < startX) startX = x;
					if (y < startY) startY = y;
					if (x > endX) endX = x;
					if (y > endY) endY = y;
				}
			}
		}
		return new Rectangle(startX, startY, (endX - startX) + 1, (endY - startY) + 1);
	}

	/**
	 * Gets the maximum dimension for the house boundaries.
	 * @param player The player.
	 * @return The dimension value (value X value = dimension)
	 */
	public int getMaximumDimension(Player player) {
		int level = player.getSkills().getStaticLevel(Skills.CONSTRUCTION);
		if (level >= 60) {
			return 7;
		}
		if (level >= 45) {
			return 6;
		}
		if (level >= 30) {
			return 5;
		}
		if (level >= 15) {
			return 4;
		}
		return 3;
	}

	/**
	 * Gets the maximum amount of rooms available for the player.
	 * @param player The player.
	 * @return The maximum amount of rooms.
	 */
	public int getMaximumRooms(Player player) { 
		int level = player.getSkills().getStaticLevel(Skills.CONSTRUCTION);
		if (level >= 99) return 30;
		if (level >= 96) return 29;
		if (level >= 92) return 28;
		if (level >= 86) return 27;
		if (level >= 80) return 26;
		if (level >= 74) return 25;
		if (level >= 68) return 24;
		if (level >= 62) return 23;
		if (level >= 56) return 22;
		if (level >= 50) return 21;
		return 20;
	}

	/**
	 * Spawns the servant inside the player's home.
	 */
	private void spawnServant(){
		servant.setLocation(getEnterLocation());
		servant.setWalkRadius(getRoomAmount() * 2);
		servant.setWalks(true);
		servant.init();
	}

	/**
	 * Checks if the player has a servant.
	 * @return {@code True} if so.
	 */
	public boolean hasServant() {
		return servant != null;
	}

	/**
	 * Checks if the player is in his own house (or dungeon).
	 * @param player The player.
	 * @return {@code True} if so.
	 */
	public boolean isInHouse(Player player) {
		return isLoaded() && (player.getViewport().getRegion() == region || player.getViewport().getRegion() == dungeon);
	}
	
	/**
	 * Checks if the player is in his dungeon.
	 * @param player The player.
	 * @return {@code True} if so.
	 */
	public static boolean isInDungeon(Player player) {
		return player.getViewport().getRegion() == player.getHouseManager().dungeon;
	}

	/**
	 * Checks if the house region was constructed and active.
	 * @return {@code True} if an active region for the house exists.
	 */
	public boolean isLoaded() {
		return region != null && region.isActive() || dungeon != null && dungeon.isActive();
	}

	/**
	 * Gets the hasHouse.
	 * @return The hasHouse.
	 */
	public boolean hasHouse() {
		return location != HouseLocation.NOWHERE;
	}

	/**
	 * Checks if the house has a dungeon.
	 * @return {@code True} if so.
	 */
	public boolean hasDungeon() {
		return hasDungeon;
	}

	/**
	 * Sets the has dungeon value.
	 * @param hasDungeon If the house has a dungeon.
	 */
	public void setHasDungeon(boolean hasDungeon) {
		this.hasDungeon = hasDungeon;
	}

	/**
	 * Gets the rooms.
	 * @return The rooms.
	 */
	public Room[][][] getRooms() {
		return rooms;
	}

	/**
	 * Gets the location.
	 * @return The location.
	 */
	public HouseLocation getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 * @param location The location to set.
	 */
	public void setLocation(HouseLocation location) {
		this.location = location;
	}

	/**
	 * Checks if the building mode is enabled.
	 * @return {@code True} if so.
	 */
	public boolean isBuildingMode() {
		return buildingMode;
	}

	/**
	 * Checks if the player has locked their house.
	 * @return {@code True} if so.
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets the house to locked.
	 * @param locked true or false
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets the region.
	 * @return The region.
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Gets the dungeon region.
	 * @return The dungeon region.
	 */
	public Region getDungeonRegion() {
		return dungeon;
	}

	/**
	 * Gets the style.
	 * @return the style
	 */
	public HousingStyle getStyle() {
		return style;
	}

	/**
	 * Sets the style.
	 * @param style the style to set.
	 */
	public void setStyle(HousingStyle style) {
		this.style = style;
	}

	/**
	 * Gets the player's servant
	 * @return the servant.
	 */
	public Servant getServant(){
		return servant;
	}

	/**
	 * Sets the player's servant
	 * @param servant The servant to set.
	 */
	public void setServant(Servant servant){
		this.servant = servant;
	}

	/**
	 * Gets the crest value.
	 * @return The crest.
	 */
	public CrestType getCrest() {
		return crest;
	}

	/**
	 * Sets the crest value.
	 * @param crest The crest to set.
	 */
	public void setCrest(CrestType crest) {
		this.crest = crest;
	}

	/**
	 * @return the zone
	 */
	public HouseZone getZone() {
		return zone;
	}
}