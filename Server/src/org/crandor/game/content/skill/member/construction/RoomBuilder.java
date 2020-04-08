package org.crandor.game.content.skill.member.construction;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.world.map.BuildRegionChunk;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ContainerContext;
import org.crandor.net.packet.out.ContainerPacket;

import java.util.Arrays;

/**
 * Used for building a room.
 * @author Emperor
 *
 */
public final class RoomBuilder {
	
	/**
	 * The directions array.
	 */
	public static final Direction[] DIRECTIONS = {
		Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST
	};

	/**
	 * Opens the building furniture interface.
	 * @param player The player.
	 * @param hotspot The hotspot.
	 */
	public static void openBuildInterface(Player player, BuildHotspot hotspot) {
		player.getInterfaceManager().open(new Component(396));
		Item[] items = new Item[7];
		int value = 1;
		for (int i = 0; i < 7; i++) {
			int offset = i * 5;
			if (i >= hotspot.getDecorations().length) {
				for (int j = 97; j < 102; j++) {
					player.getPacketDispatch().sendString("", 396, j + offset);
				}
				player.getPacketDispatch().sendString("", 396, 140 + i);
				value |= 1 << (i + 1);
				continue;
			}
			Decoration decoration = hotspot.getDecorations()[i];
			if (i < 4) {
				items[i * 2] = new Item(decoration.getInterfaceItem());
			} else {
				items[1 + ((i - 4) * 2)] = new Item(decoration.getInterfaceItem());
			}
			player.getPacketDispatch().sendString(ObjectDefinition.forId(decoration.getObjectId()).getName(), 396, 97 + offset);
			boolean hasRequirements = true;
			for (int j = 0; j < 4; j++) {
				if (j >= decoration.getItems().length) {
					player.getPacketDispatch().sendString("", 396, 98 + offset + j);
				} else {
					Item item = decoration.getItems()[j];
					if (!player.getInventory().containsItem(item)) {
						hasRequirements = false;
					}
					player.getPacketDispatch().sendString(item.getName() + ": " + item.getAmount(), 396, 98 + offset + j);
				}
			}
			player.getPacketDispatch().sendString("Lvl " + decoration.getLevel(), 396, 140 + i);
			if (hasRequirements) {
				value |= 1 << (i + 1);
			}
		}
		player.getConfigManager().set(261, value);
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 396, 132, 91, items, false));
	}

	/**
	 * Builds a decoration object.
	 * @param player The player.
	 * @param deco The decoration.
	 * @param object The object.
	 */ 
	public static void buildDecoration(Player player, BuildHotspot hotspot, Decoration deco, GameObject object) {
		if(!player.isAdmin()){
			if (!player.getInventory().containsItems(deco.getItems())) {
				player.getPacketDispatch().sendMessage("You don't have the required items to build this.");
				return;
			}
			player.getInventory().remove(deco.getItems());
		}
		Location l = object.getLocation();  
		Room room = player.getHouseManager().getRooms()[l.getZ()][l.getLocalX() >> 3][l.getLocalY() >> 3];
		Hotspot h = room.getHotspot(hotspot, l.getX(), l.getY());
		if (h != null) {
			switch(h.getHotspot().getType()) { 
				case INDIVIDUAL:
					ObjectBuilder.replace(object, object.transform(deco.getObjectId()));							
					h.setDecorationIndex(hotspot.getDecorationIndex(deco));			
					break;					
				case RECURSIVE:
					room.setAllDecorationIndex(hotspot.getDecorationIndex(deco), h.getHotspot());
					GameObject[][] objects = player.getHouseManager().getRegion().getPlanes()[l.getZ()].getChunks()[l.getLocalX() >> 3][l.getLocalY() >> 3].getObjects();
					for (int j = 0; j < objects.length; j++) {
						for (int k = 0; k < objects[j].length; k++) {
							GameObject go = objects[j][k];
							if (go != null && go.getId() == object.getId()) {
								ObjectBuilder.replace(go, go.transform(deco.getObjectId()));	
							}
						}
					}
					break;
				case LINKED:
					BuildHotspot[] linkedHotspots = BuildHotspot.getLinkedHotspots(h.getHotspot());
					BuildRegionChunk chunk = (BuildRegionChunk) player.getHouseManager().getRegion().getPlanes()[l.getZ()].getChunks()[l.getLocalX() >> 3][l.getLocalY() >> 3];			
					for (int x = 0; x < 8; x++) {
						for (int y = 0; y < 8; y++) {
							for(BuildHotspot bh : linkedHotspots) {
								int index = chunk.getIndex(x, y, bh.getObjectId());
								GameObject o = chunk.get(x, y, index);
								if (o != null && bh.getObjectId() == o.getId()) {
									ObjectBuilder.replace(o, o.transform(bh.getDecorations()[0].getObjectId()));
									room.setAllDecorationIndex(0, bh);
								}
							}
						}
					}
					break;
			}
		}
	}
	
	/**
	 * Remove the decoration
	 * @param player the player
	 * @param object the object to remove
	 */
	public static void removeDecoration(Player player, GameObject object) {
		Location l = object.getLocation();
		Room room = player.getHouseManager().getRooms()[l.getZ()][l.getLocalX() >> 3][l.getLocalY() >> 3];
		for (int i = 0; i < room.getHotspots().length; i++) {
			Hotspot hotspot = room.getHotspots()[i];
			if (hotspot.getChunkX() == l.getChunkOffsetX() && hotspot.getChunkY() == l.getChunkOffsetY()) {
				switch (hotspot.getHotspot().getType()) {
					case INDIVIDUAL:					
						ObjectBuilder.replace(object, object.transform(hotspot.getHotspot().getObjectId()));
						hotspot.setDecorationIndex(-1);					
						return;
					case RECURSIVE:
						room.setAllDecorationIndex(-1, hotspot.getHotspot());
						GameObject[][] objects = player.getHouseManager().getRegion().getPlanes()[l.getZ()].getChunks()[l.getLocalX() >> 3][l.getLocalY() >> 3].getObjects();
						for (int j = 0; j < objects.length; j++) {
							for (int k = 0; k < objects[j].length; k++) {
								GameObject go = objects[j][k];
								if (go != null && go.getId() == object.getId()) {
									ObjectBuilder.replace(go, go.transform(hotspot.getHotspot().getObjectId()));
								}
							}
						}
						return;
					case LINKED:
						//TODO
						return;
				}
			}
		}
	}

	/**
	 * Builds a room.
	 * @param player The player.
	 * @param room The room to build.
	 */
	public static void buildRoom(Player player, Room room, int z, int x, int y) {
		System.err.println("Building direction = " + room.getRotation().name());
		if(room.getRotation() != Direction.NORTH){
			room.setRotation(Direction.NORTH);
			player.getDialogueInterpreter().sendDialogue("Room rotation is currently disabled.");
		}
		player.getHouseManager().getRooms()[z][x][y] = room;
		player.getPacketDispatch().sendMessage("Building room " + room.getProperties() + ".");
		player.getHouseManager().reload(player, true);
	}
	
	/**
	 * Checks of a room exists
	 * @param player the player
	 * @param door the door hotspot the player is trying to build at
	 * @return true if the room is built already
	 */
	public static boolean roomExists(Player player, GameObject door) {
		int[] location = getRoomPosition(door);
		return player.getHouseManager().getRooms()[player.getLocation().getZ()][location[0]][location[1]] != null;
	}
	
	/**
	 * Gets the room offset.
	 * @param door The door.
	 * @return The room offset [x, y].
	 */
	public static int[] getRoomPosition(GameObject door) {
		Location l = door.getLocation();
		switch (door.getRotation()) {
		case 0: //West
			return new int[] { (l.getLocalX() >> 3) - 1, l.getLocalY() >> 3 };
		case 1: //North
			return new int[] { l.getLocalX() >> 3, (l.getLocalY() >> 3) + 1};
		case 2: //East
			return new int[] { (l.getLocalX() >> 3) + 1, l.getLocalY() >> 3 };
		case 3: //South
			return new int[] { l.getLocalX() >> 3, (l.getLocalY() >> 3) - 1};
		}
		return null;
	}

	/**
	 * Gets the available rotations of the room to build.
	 * @param exits The exits of the room.
	 * @param door The door hotspot used.
	 * @param roomX The room x-coordinate.
	 * @param roomY The room y-coordinate.
	 * @return The available rotations for the room [NORTH, EAST, SOUTH, WEST].
	 */
	public static Direction[] getAvailableRotations(Player player, boolean[] exits, GameObject door, int roomX, int roomY) {
		Direction[] directions = new Direction[4];
		//Exits go to: (0=east, 1=south, 2=west, 3=north)
		boolean[] exit = Arrays.copyOf(exits, exits.length);
		//Door goes to: (0=west, 1=north, 2=east, 3=south)
		int[] info = getExitRequirements(player, door.getLocation().getZ(), roomX, roomY);
		for (int i = 0; i < 4; i++) {
			boolean success = true;
			for (int j = 0; j < 4; j++) {
				if (info[j] == 1 && !exit[j]) {
					success = false;
					break;
				}
				if (info[j] == -1 && exit[j]) {
					success = false;
					break;
				}
			}
			if (success) {
				directions[i] = Direction.NORTH;
			}
			System.out.println(i + ": " + Arrays.toString(exit) + " - " + Arrays.toString(info));
			boolean b = exit[0];
			for (int j = 0; j < exit.length - 1; j++) {
				exit[j] = exit[j + 1];
			}
			exit[exit.length - 1] = b;
		}
		Direction dir = directions[0];
		directions[0] = directions[2];
		directions[2] = dir;
		dir = directions[1];
		directions[1] = directions[3];
		directions[3] = dir;
		return directions;
	}

	/**
	 * Gets the exit requirements for the given room.
	 * @param roomX The room x-coordinate.
	 * @param roomY The room y-coordinate.
	 * @return The disabled exit indexes.
	 */
	private static int[] getExitRequirements(Player player, int z, int roomX, int roomY) {
		//1       2        3       0
		//0=east, 1=south, 2=west, 3=north).
		int[] exits = new int[4];
		if (roomX == 0) {
			exits[2] = -1;
		}
		else if (player.getHouseManager().hasExit(z, roomX - 1, roomY, Direction.EAST)) {
			exits[2] = 1;
		}
		else if (player.getHouseManager().getRooms()[z][roomX - 1][roomY] != null) {
			exits[2] = -1;
		}
		if (roomY == 7) {
			exits[1] = -1;
		}
		else if (player.getHouseManager().hasExit(z, roomX, roomY + 1, Direction.SOUTH)) {
			exits[1] = 1;
		}
		else if (player.getHouseManager().getRooms()[z][roomX][roomY + 1] != null) {
			exits[1] = -1;
		}
		if (roomX == 7) {
			exits[0] = -1;
		}
		else if (player.getHouseManager().hasExit(z, roomX + 1, roomY, Direction.WEST)) {
			exits[0] = 1;
		}
		else if (player.getHouseManager().getRooms()[z][roomX + 1][roomY] != null) {
			exits[0] = -1;
		}
		if (roomY == 0) {
			exits[3] = -1;
		}
		else if (player.getHouseManager().hasExit(z, roomX, roomY - 1, Direction.NORTH)) {
			exits[3] = 1;
		}
		else if (player.getHouseManager().getRooms()[z][roomX][roomY - 1] != null) {
			exits[3] = -1;
		}
		return exits;
	}
}