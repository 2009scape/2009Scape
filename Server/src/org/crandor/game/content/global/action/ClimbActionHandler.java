package org.crandor.game.content.global.action;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Handles a ladder climbing reward.
 * @author Emperor
 */
public final class ClimbActionHandler {

	/**
	 * Represents the climb up animation of ladders.
	 */
	public static final Animation CLIMB_UP = new Animation(828);

	/**
	 * Represents the climb down animation of ladders.
	 */
	public static final Animation CLIMB_DOWN = new Animation(827);

	/**
	 * The climb dialogue.
	 */
	public static DialoguePlugin CLIMB_DIALOGUE = new ClimbDialogue();

	/**
	 * Handles the climbing of a rope.
	 * @param player The player.
	 * @param object The rope object.
	 * @param option The option.
	 */
	public static void climbRope(Player player, GameObject object, String option) {

	}

	/**
	 * Handles the climbing of a trap door.
	 * @param player The player.
	 * @param object The trap door object.
	 * @param option The option.
	 */
	public static void climbTrapdoor(Player player, GameObject object, String option) {

	}

	/**
	 * Handles the climbing of a ladder.
	 * @param player The player.
	 * @param object The game object.
	 * @param option The option.
	 */
	public static void climbLadder(Player player, GameObject object, String option) {
		GameObject newLadder = null;
		Animation animation = CLIMB_UP;
		switch (option) {
		case "climb-up":
			newLadder = getLadder(object, false);
			break;
		case "climb-down":
			if (object.getName().equals("Trapdoor")) {
				animation = CLIMB_DOWN;
			}
			newLadder = getLadder(object, true);
			break;
		case "climb":
			GameObject upperLadder = getLadder(object, false);
			GameObject downLadder = getLadder(object, true);
			if (upperLadder == null && downLadder != null) {
				climbLadder(player, object, "climb-down");
				return;
			}
			if (upperLadder != null && downLadder == null) {
				climbLadder(player, object, "climb-up");
				return;
			}
			DialoguePlugin dial = CLIMB_DIALOGUE.newInstance(player);
			if (dial != null && dial.open(object)) {
				player.getDialogueInterpreter().setDialogue(dial);
			}
			return;
		}
		Location destination = newLadder != null ? getDestination(newLadder) : null;
		if (newLadder == null || destination == null) {
			player.getPacketDispatch().sendMessage("The ladder doesn't seem to lead anywhere.");
			return;
		}
		if (object.getName().startsWith("Stair")) {
			animation = null;
		}
		climb(player, animation, destination);
	}

	/**
	 * Gets the teleport destination.
	 * @param object The object to teleport to.
	 * @return The teleport destination.
	 */
	public static Location getDestination(GameObject object) {
		int sizeX = object.getDefinition().sizeX;
		int sizeY = object.getDefinition().sizeY;
		if (object.getRotation() % 2 != 0) {
			int switcher = sizeX;
			sizeX = sizeY;
			sizeY = switcher;
		}
		Direction dir = Direction.forWalkFlag(object.getDefinition().getWalkingFlag(), object.getRotation());
		if (dir != null) {
			return getDestination(object, sizeX, sizeY, dir, 0);
		}
		switch (object.getRotation()) {
		case 0:
			return getDestination(object, sizeX, sizeY, Direction.SOUTH, 0);
		case 1:
			return getDestination(object, sizeX, sizeY, Direction.EAST, 0);
		case 2:
			return getDestination(object, sizeX, sizeY, Direction.NORTH, 0);
		case 3:
			return getDestination(object, sizeX, sizeY, Direction.WEST, 0);
		}
		return null;
	}

	/**
	 * Gets the destination for the given object.
	 * @param object The object.
	 * @param dir The preferred direction from the object.
	 * @return The teleporting destination.
	 */
	private static Location getDestination(GameObject object, int sizeX, int sizeY, Direction dir, int count) {
		Location loc = object.getLocation();
		if (dir.toInteger() % 2 != 0) {
			int x = dir.getStepX();
			if (x > 0) {
				x *= sizeX;
			}
			for (int y = 0; y < sizeY; y++) {
				Location l = loc.transform(x, y, 0);
				if (RegionManager.isTeleportPermitted(l) && dir.canMove(l)) {
					return l;
				}
			}
		} else {
			int y = dir.getStepY();
			if (y > 0) {
				y *= sizeY;
			}
			for (int x = 0; x < sizeX; x++) {
				Location l = loc.transform(x, y, 0);
				if (RegionManager.isTeleportPermitted(l) && dir.canMove(l)) {
					return l;
				}
			}
		}
		if (count == 3) {
			return null;
		}
		return getDestination(object, sizeX, sizeY, Direction.get((dir.toInteger() + 1) % 4), count + 1);
	}

	/**
	 * Executes the climbing reward.
	 * @param player The player.
	 * @param animation The climbing animation.
	 * @param destination The destination.
	 */
	public static void climb(final Player player, Animation animation, final Location destination, final String... messages) {
		player.lock(2);
		player.animate(animation);
		GameWorld.Pulser.submit(new Pulse(1) {
			@Override
			public boolean pulse() {
				player.getProperties().setTeleportLocation(destination);
				for (String message : messages) {
					player.getPacketDispatch().sendMessage(message);
				}
				return true;
			}
		});
	}

	/**
	 * Gets the ladder the object leads to.
	 * @param object The ladder object.
	 * @param down If the player is going down a floor.
	 * @return The ladder the current ladder object leads to.
	 */
	private static GameObject getLadder(GameObject object, boolean down) {
		int mod = down ? -1 : 1;
		GameObject ladder = RegionManager.getObject(object.getLocation().transform(0, 0, mod));
		if (ladder == null || !isLadder(ladder)) {
			if (ladder != null && ladder.getName().equals(object.getName())) {
				ladder = RegionManager.getObject(ladder.getLocation().transform(0, 0, mod));
				if (ladder != null) {
					return ladder;
				}
			}
			ladder = findLadder(object.getLocation().transform(0, 0, mod));
			if (ladder == null) {
				ladder = RegionManager.getObject(object.getLocation().transform(0, mod * -6400, 0));
				if (ladder == null) {
					ladder = findLadder(object.getLocation().transform(0, mod * -6400, 0));
				}
			}
		}
		return ladder;
	}

	/**
	 * Finds a ladder (by searching a 10x10 area around the given location).
	 * @param l The location.
	 * @return The ladder.
	 */
	private static GameObject findLadder(Location l) {
		for (int x = -5; x < 6; x++) {
			for (int y = -5; y < 6; y++) {
				GameObject object = RegionManager.getObject(l.transform(x, y, 0));
				if (object != null && isLadder(object)) {
					return object;
				}
			}
		}
		return null;
	}

	/**
	 * Checks if the object is a ladder.
	 * @param object The object.
	 * @return {@code True} if so.
	 */
	private static boolean isLadder(GameObject object) {
		for (String option : object.getDefinition().getOptions()) {
			if (option != null && (option.contains("Climb"))) {
				return true;
			}
		}
		return object.getName().equals("Trapdoor");
	}

	/**
	 * Represents the dialogue plugin used for climbing stairs or a ladder.
	 * @author 'Vexia
	 * @version 1.0
	 */
	static final class ClimbDialogue extends DialoguePlugin {

		/**
		 * Represents the climbing dialogue id.
		 */
		public static final int ID = 8 << 16;

		/**
		 * Constructs a new {@code ClimbDialogue} {@code Object}.
		 */
		public ClimbDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ClimbDialogue} {@code Object}.
		 * @param player the player.
		 */
		public ClimbDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ClimbDialogue(player);
		}

		/**
		 * Represents the object to use.
		 */
		private GameObject object;

		@Override
		public boolean open(Object... args) {
			object = (GameObject) args[0];
			interpreter.sendOptions("What would you like to do?", "Climb Up.", "Climb Down.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				switch (buttonId) {
				case 1:
					player.lock(1);
					GameWorld.Pulser.submit(new Pulse(1) {
						@Override
						public boolean pulse() {
							climbLadder(player, object, "climb-up");
							return true;
						}
					});
					end();
					break;
				case 2:
					player.lock(1);
					GameWorld.Pulser.submit(new Pulse(1) {
						@Override
						public boolean pulse() {
							climbLadder(player, object, "climb-down");
							return true;
						}
					});
					end();
					break;

				}
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { ID };
		}

	}
}