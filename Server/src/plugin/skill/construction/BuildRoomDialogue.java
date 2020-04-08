package plugin.skill.construction;


import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.construction.BuildingUtils;
import org.crandor.game.content.skill.member.construction.HouseManager;
import org.crandor.game.content.skill.member.construction.Room;
import org.crandor.game.content.skill.member.construction.RoomProperties;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionChunk;
import org.crandor.plugin.InitializablePlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the building a room dialogue.
 * @author Emperor
 *
 */
@InitializablePlugin
public final class BuildRoomDialogue extends DialoguePlugin {

	/**
	 * The door hotspot.
	 */
	private GameObject door;

	/**
	 * The direction.
	 */
	private Direction[] directions;

	/**
	 * The room exits.
	 */
	private boolean[] exits;

	/**
	 * The rotation index.
	 */
	private int index;

	/**
	 * The boundaries of the room to build.
	 */
	private List<GameObject> boundaries = new ArrayList<>();

	/**
	 * The room we're building.
	 */
	private Room room;

	/**
	 * The room x-coordinate.
	 */
	private int roomX;

	/**
	 * The room y-coordinate.
	 */
	private int roomY;
	
	/**
	 * The room z-coordinate (3 for dungeon).
	 */
	private int roomZ;

	/**
	 * Constructs a new {@code BuildRoomDialogue} {@code Object}
	 */
	public BuildRoomDialogue() {
		super();
	}

	/**
	 * Constructs a new {@code BuildRoomDialogue} {@code Object}
	 * @param player The player.
	 */
	public BuildRoomDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BuildRoomDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		player.getInterfaceManager().close();
		RoomProperties props = (RoomProperties) args[0];
		if (player.getSkills().getStaticLevel(Skills.CONSTRUCTION) < props.getLevel()) {
			interpreter.sendPlainMessage(false, "You need a Construction level of " + props.getLevel() + " to buy this room.");
			stage = 2;
			return true;
		}
		if (!player.getInventory().contains(995, props.getCost())) {
			interpreter.sendPlainMessage(false, "You need " + props.getCost() + " coins to buy this room.");
			stage = 2;
			return true;
		}
		this.door = (GameObject) player.getAttribute("con:hsobject");
		int[] pos = BuildingUtils.getRoomPosition(player, door);
		roomX = pos[0];
		roomY = pos[1];
		if (!inBounds()) {
			interpreter.sendPlainMessage(false, "Your house is too large. TODO: correct message");
			stage = 2;
			return true;
		}
		roomZ = player.getLocation().getZ();
		if (roomZ != 0) {
			Room r = player.getHouseManager().getRooms()[roomZ - 1][roomX][roomY];
			if (r == null || r.getProperties().isLand()) {
				interpreter.sendPlainMessage(false, "You can't build a room here, you need a room to build on.");
				stage = 2;
				return true;
			}
		}
		if (HouseManager.isInDungeon(player)) {
			if (props.isLand()) {
				interpreter.sendPlainMessage(false, "You can't build this room inside your dungeon.");
				stage = 2;
				return true;
			}
			roomZ = 3;
		}
		else if (props.isDungeon()) {
			interpreter.sendPlainMessage(false, "You can only build this room in your dungeon.");
			stage = 2;
			return true;
		}
		if (props.isLand() && roomZ != 0) {
			interpreter.sendPlainMessage(false, "A garden can only be on ground floor.");
			stage = 2;
			return true;
		}
		this.room = Room.create(player, props);
		this.exits = room.getExits(Direction.NORTH);
		this.index = 0;
		this.directions = BuildingUtils.getAvailableRotations(player, exits, roomZ, roomX, roomY);
		while (directions[index] == null) {
			if (++index == directions.length) {
				interpreter.sendPlainMessage(false, "There's no space to build this room.");
				stage = 2;
				return true;
			}
		}
		options("Build", "Cancel");
		stage = 1;
		drawGhostRoom();
		return true;
	}

	/**
	 * Checks if the room to be built is in the available boundaries of the house.
	 * @return The boundaries.
	 */
	private boolean inBounds() {
		Rectangle bounds = player.getHouseManager().getBoundaries();
		int max = player.getHouseManager().getMaximumDimension(player);
		if ((roomX < bounds.x || roomX >= bounds.x + bounds.width) && bounds.width >= max) {
			return false;
		}
		if ((roomY < bounds.y || roomY >= bounds.y + bounds.height) && bounds.height >= max) {
			return false;
		}
		return true;
	}

	@Override
	public boolean close() {
		for (GameObject object : boundaries) {
			ObjectBuilder.remove(object);
		}
		boundaries.clear();
		return super.close();
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			switch (buttonId) {
			/*case 1:
			case 2:*/
/*				rotate(buttonId == 2);
				options("Build", "Cancel");
				return true;*/
			case 1:
				if (player.getInventory().remove(new Item(995, room.getProperties().getCost()))) {
					room.setRotation(Direction.NORTH);
					boolean[] exit = new boolean[exits.length];
					for (int i = 0; i < exit.length; i++) {
						exit[(i + index) % exit.length] = exits[i];
					}
					BuildingUtils.buildRoom(player, room, roomZ, roomX, roomY, exit, true);
					end();
					return true;
				}
				interpreter.sendPlainMessage(false, "You need " + room.getProperties().getCost() + " coins to buy this room.");
				stage = 2;
				return true;
			case 2:
				end();
				return true;
			}
			break;
		case 2:
			end();
			return true;
		}
		return false;
	}

	/**
	 * Rotates the room.
	 * @param counter If we're rotating counter clockwise.
	 */
	private void rotate(boolean counter) {
		Direction direction = null;
		while ((direction = directions[index = (index + (counter ? 3 : 1)) % 4]) == null) {
			
		}
		room.setRotation(Direction.NORTH);
		drawGhostRoom();
	}

	/**
	 * Draws the current boundaries of the room to build.
	 */
	private void drawGhostRoom() {
		for (GameObject object : boundaries) {
			ObjectBuilder.remove(object);
		}
		int rotation = Direction.NORTH.toInteger();
		boundaries.clear();
		Location base = player.getViewport().getRegion().getBaseLocation().transform(roomX << 3, roomY << 3, player.getLocation().getZ());
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				GameObject[] objects = room.getChunk().getObjects(x, y);
				for (GameObject object : objects) {
					if (object != null && object.getDefinition().hasAction("build")) {
						int[] pos = RegionChunk.getRotatedPosition(x, y, object.getDefinition().getSizeX(), object.getDefinition().getSizeY(), object.getRotation(), rotation);
						GameObject obj = object.transform(object.getId(), (object.getRotation() + rotation) % 4, base.transform(pos[0], pos[1], 0));
						boundaries.add(ObjectBuilder.add(obj));
					}
				}
			}
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("con:room") };
	}

}