package plugin.activity.mta.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.crandor.game.content.dialogue.DialogueAction;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.path.Path;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.net.packet.out.CameraViewPacket;
import org.crandor.tools.RandomFunction;

import plugin.activity.mta.MTAType;
import plugin.activity.mta.MTAZone;

/**
 * Handles the telekinetic zone.
 * @author Vexia
 */
public class TelekineticZone extends MTAZone {

	/**
	 * The statue used in the game.
	 */
	public static final int STATUE = 6888;

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The mazes to choose from.
	 */
	private final List<Maze> mazes = new ArrayList<>();

	/**
	 * The dynamic region.
	 */
	private DynamicRegion region;

	/**
	 * The location.
	 */
	private Location base;

	/**
	 * The maze.
	 */
	private Maze maze;

	/**
	 * The statue item.
	 */
	private GroundItem statue;

	/**
	 * The npc guardian.
	 */
	private NPC guardian;

	/**
	 * The solved mazes.
	 */
	private int solved;

	/**
	 * Constructs a new {@code TelekineticZone} {@code Object}
	 * @param player the player.
	 */
	public TelekineticZone(Player player) {
		super("Telekinetic Theatre", new Item[] {});
		this.player = player;
		if (player != null) {
			mazes.addAll(Arrays.asList(Maze.values()));
			this.solved = player.getSavedData().getActivityData().getSolvedMazes();
		}
	}

	/**
	 * Constructs a new {@code TelekineticZone} {@code Object}
	 */
	public TelekineticZone() {
		this(null);
	}

	/**
	 * Starts the telekinetic game for a player.
	 * @param player the player.
	 */
	public static void start(Player player) {
		setZone(player);
	}

	@Override
	public void update(Player player) {
		player.getPacketDispatch().sendString("" + player.getSavedData().getActivityData().getPizazzPoints(getType().ordinal()),  getType().getOverlay().getId(), 4);
		player.getPacketDispatch().sendString("" + solved, 198, 7);
	}

	@Override
	public boolean leave(Entity entity, boolean logout) {
		if (entity instanceof Player) {
			Player player = entity.asPlayer();
			if (player == null || player != this.player) {
				return super.leave(entity, logout);
			}
			if (logout) {
				player.getSavedData().getActivityData().setSolvedMazes(solved);
			} else {
				player.getSavedData().getActivityData().setSolvedMazes(0);
			}
			if (statue != null) {
				GroundItemManager.destroy(statue);
			}
			if (guardian != null) {
				guardian.clear();
			}
			player.removeAttribute("camera");
		}
		return super.leave(entity, logout);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		return super.interact(e, target, option);
	}

	@Override
	public boolean parseCommand(Player player, String name, String[] arguments) {
		switch (name) {
		case "childs":
			for (int i = 0; i < 8; i++) {
				player.getPacketDispatch().sendString("child=" + i, 198, i);
			}
			break;
		case "reset":
			if (player.getAttribute("camera", false)) {
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 400, 1, 20));
				player.setAttribute("camera", false);
				return true;
			}
			return true;
		}
		return false;
	}

	@Override
	public void configure() {
		setUid(getName().hashCode());
		region = DynamicRegion.create(13463);
		setBase(Location.create(region.getBorders().getSouthWestX(), region.getBorders().getSouthWestY(), 0));
		registerRegion(region.getId());
	}

	/**
	 * Sets up the player in the maze.
	 */
	public void setUp() {
		Maze maze = this.maze == null ? RandomFunction.getRandomElement(mazes.toArray(new Maze[] {})) : null;
		while (maze == null) {
			maze = RandomFunction.getRandomElement(mazes.toArray(new Maze[] {}));
			if (maze == this.maze) {
				maze = null;
			}
		}
		mazes.remove(maze);
		setMaze(maze);
		setNodes();
		player.teleport(base.transform(maze.getBase()));
	}

	/**
	 * Sets up the nodes.
	 */
	public void setNodes() {
		if (statue != null) {
			GroundItemManager.destroy(statue);
		}
		if (guardian != null) {
			guardian.clear();
		}
		moveStatue(base.transform(maze.getStatueLocation()));
		guardian = NPC.create(3098, base.transform(maze.getGuardianLocation()));
		guardian.setWalks(true);
		guardian.init();
	}

	/**
	 * Moves the statue.
	 * @param location the location.
	 */
	public void moveStatue(Location location) {
		if (statue != null) {
			GroundItemManager.destroy(statue);
		}
		GroundItemManager.create(statue = createGroundItem(location));
	}

	/**
	 * Creates the statue ground item.
	 * @param location the location.
	 * @return the item.
	 */
	public GroundItem createGroundItem(Location location) {
		GroundItem item = new GroundItem(new Item(STATUE), location, player) {
			@Override
			public boolean isActive() {
				return !isRemoved();
			}
		};
		return item;
	}

	/**
	 * Sets the telekinetic zone.
	 * @param player the player.
	 * @return the zone.
	 */
	public static TelekineticZone setZone(Player player) {
		TelekineticZone zone = new TelekineticZone(player);
		zone.configure();
		zone.setUp();
		player.setAttribute("tele-zone", zone);
		return zone;
	}

	/**
	 * Gets the zone.
	 * @param player the player.
	 * @return the zone.
	 */
	public static TelekineticZone getZone(Player player) {
		TelekineticZone zone = player.getAttribute("tele-zone", null);
		if (zone == null) {
			zone = setZone(player);
		}
		return zone;
	}

	/**
	 * Called when the player wins.
	 */
	private void win() {
		solved++;
		GroundItemManager.destroy(statue);
		int points = 2;
		player.getDialogueInterpreter().sendDialogue("Congratulations! You have received two Telekinetic Pizazz Points!");
		if (solved >= 5) {
			mazes.addAll(Arrays.asList(Maze.values()));
			solved = 0;
			points += 8;
			player.getSkills().addExperience(Skills.MAGIC, 1000, true);
			player.getInventory().add(new Item(563, 10));
			player.getSavedData().getActivityData().setSolvedMazes(0);
			player.getDialogueInterpreter().addAction(new DialogueAction() {
				@Override
				public void handle(Player player, int buttonId) {
					player.getDialogueInterpreter().sendDialogue("Congratulations on solving five mazes in a row, have 8 bonus points,", "10 law runes and extra magic XP!");
				}
			});
		}
		incrementPoints(player, MTAType.TELEKINETIC.ordinal(), points);
		TelekineticZone.this.update(player);
		player.setAttribute("camera", false);
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 400, 1, 20));
		NPC mazeGuard = NPC.create(3102, base.transform(maze.getEndLocation()));
		mazeGuard.init();
	}

	/**
	 * Moves the statue with a telekinetic teleport.
	 */
	public void moveStatue() {
		final Direction dir = getDir();
		if (dir == null) {
			player.sendMessage("Invalid move!");
			return;
		}
		player.lock();
		GameWorld.Pulser.submit(new Pulse(1, player) {
			boolean win = false;

			@Override
			public boolean pulse() {
				if (win) {
					win();
					return true;
				}
				Location next = statue.getLocation().transform(dir, 1);
				Path path = Pathfinder.find(statue.getLocation(), next);
				boolean end = !path.isSuccessful() || path.isMoveNear() || path.getPoints().size() != 1;
				if (end) {
					return true;
				}
				if (next.equals(base.transform(maze.getEndLocation()))) {
					win = true;
				}
				moveStatue(next);
				return end;
			}

			@Override
			public void stop() {
				player.unlock();
				super.stop();
			}
		});
	}

	/**
	 * Observes from above.
	 * @param player the player.
	 */
	public void observe(Player player) {
		if (player.getAttribute("camera", false)) {
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 400, 1, 20));
			player.setAttribute("camera", false);
			return;
		}
		player.sendMessage("Click observe on the guardian to reset your camera, or use the command ::reset !");
		Location l = base.transform(maze.getCameraLocation());
		int x = l.getX();
		int y = l.getY();
		int yInc = -10;
		int xInc = 0;
		int speed = 95;
		int height = 798;
		player.setAttribute("camera", true);
		if (maze == Maze.FOURTH) {
			x += 11;
			y += 15;
			height = 799;
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x + xInc, y + yInc, height, 1, speed));
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x - 55, y - 25, height, 1, speed));
			return;
		}
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x + xInc, y + yInc, height, 1, speed));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + xInc, y + yInc, height, 1, speed));
	}

	/**
	 * Resets the player.
	 * @param player the player.
	 */
	public void reset(Player player) {
		moveStatue(base.transform(maze.getStatueLocation()));
	}

	/**
	 * Gets the direction to go.
	 * @return the direction.
	 */
	private Direction getDir() {
		int myX = player.getLocation().getLocalX();
		int myY = player.getLocation().getLocalY();
		// anything >= 23 is north, anything <= is south, any x <= 10 is west,
		// any x >= 21 is east
		int[] data = maze.getData();
		if (myY >= data[0]) {
			return Direction.NORTH;
		} else if (myY <= data[1]) {
			return Direction.SOUTH;
		} else if (myX <= data[2]) {
			return Direction.WEST;
		} else if (myX >= data[3]) {
			return Direction.EAST;
		}
		return null;
	}

	/**
	 * Gets the base.
	 * @return the base
	 */
	public Location getBase() {
		return base;
	}

	/**
	 * Sets the base.
	 * @param base the base to set.
	 */
	public void setBase(Location base) {
		this.base = base;
	}

	/**
	 * Gets the player.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the maze.
	 * @return the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * Sets the maze.
	 * @param maze the maze to set.
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	/**
	 * Gets the solved.
	 * @return the solved
	 */
	public int getSolved() {
		return solved;
	}

	/**
	 * Sets the solved.
	 * @param solved the solved to set.
	 */
	public void setSolved(int solved) {
		this.solved = solved;
	}

	/**
	 * A maze.
	 * @author Vexia
	 */
	public enum Maze {
		FIRST(new Location(8, 54, 0), new Location(15, 41, 0), new Location(8, 39, 0), new Location(19, 50, 0), new Location(15, 45, 0), new int[] { 51, 40, 9, 20 }), // http://puu.sh/cbh82/a0f9f3d206.png
		SECOND(new Location(34, 49, 1), new Location(22, 53, 1), new Location(26, 48, 1), new Location(13, 44, 1), new Location(17, 48, 1), new int[] { 54, 43, 12, 23 }), // http://puu.sh/cbgzG/ab25358b4c.png
		THIRD(new Location(54, 34, 1), new Location(48, 22, 1), new Location(45, 15, 1), new Location(57, 22, 1), new Location(53, 17, 1), new int[] { 23, 12, 47, 58 }), // http://puu.sh/cbgRX/d229c6129b.png

		FOURTH(new Location(50, 42, 1), new Location(46, 49, 1), new Location(57, 41, 1), new Location(55, 49, 1), new Location(50, 53, 1), new int[] { 59, 48, 45, 56 }), // http://puu.sh/cbgMW/7bd91b0ed1.png
		FITH(new Location(46, 32, 0), new Location(45, 14, 0), new Location(45, 23, 0), new Location(47, 18, 0), new Location(45, 13, 0), new int[] { 19, 8, 40, 51 }), // http://puu.sh/cbhuc/83da140de5.png

		SIXTH(new Location(26, 26, 0), new Location(15, 16, 0), new Location(23, 24, 0), new Location(14, 20, 0), new Location(14, 15, 0), new int[] { 21, 10, 9, 10 }), // http://puu.sh/cbgHl/cb55b8011e.png
		SEVENTH(new Location(51, 52, 0), new Location(40, 48, 0), new Location(55, 50, 0), new Location(39, 56, 0), new Location(43, 51, 0), new int[] { 57, 46, 37, 48 }), // http://puu.sh/cbgXl/73a9311a76.png
		EIGTH(new Location(31, 37, 2), new Location(18, 54, 2), new Location(28, 41, 2), new Location(17, 54, 2), new Location(19, 49, 2), new int[] { 55, 44, 14, 25 }), // http://puu.sh/cbgAV/a2f71c6279.png
		NINTH(new Location(40, 16, 2), new Location(20, 10, 2), new Location(34, 15, 2), new Location(11, 19, 2), new Location(16, 14, 2), new int[] { 20, 9, 10, 21 }), // http://puu.sh/cbhbR/59a1477b0c.png
		TENTH(new Location(27, 29, 1), new Location(23, 20, 1), new Location(31, 25, 1), new Location(25, 16, 1), new Location(23, 20, 1), new int[] { 26, 15, 17, 28 });// http://puu.sh/cbh58/1cd226cbc0.png

		/**
		 * The location base of where to start.
		 */
		private final Location base;

		/**
		 * The start location of the statue.
		 */
		private final Location statueLocation;

		/**
		 * The guardian location.
		 */
		private final Location guardianLocation;

		/**
		 * The ending location.
		 */
		private final Location endLocation;

		/**
		 * The location camera.
		 */
		private final Location cameraLocation;

		/**
		 * The data used to create movements.
		 */
		private final int[] data;

		/**
		 * Constructs a new {@code Maze} {@code Object}
		 * @param base the base.
		 * @param statueLocation the statue location.
		 * @param guardianLocation the guardian location.
		 * @param endLocation the end location.
		 * @param cameraLocation the camera location.
		 */
		private Maze(Location base, Location statueLocation, Location guardianLocation, Location endLocation, Location cameraLocation, int[] data) {
			this.base = base;
			this.statueLocation = statueLocation;
			this.guardianLocation = guardianLocation;
			this.endLocation = endLocation;
			this.cameraLocation = cameraLocation;
			this.data = data;
		}

		/**
		 * Gets the base.
		 * @return the base
		 */
		public Location getBase() {
			return base;
		}

		/**
		 * Gets the statueLocation.
		 * @return the statueLocation
		 */
		public Location getStatueLocation() {
			return statueLocation;
		}

		/**
		 * Gets the guardianLocation.
		 * @return the guardianLocation
		 */
		public Location getGuardianLocation() {
			return guardianLocation;
		}

		/**
		 * Gets the endLocation.
		 * @return the endLocation
		 */
		public Location getEndLocation() {
			return endLocation;
		}

		/**
		 * Gets the cameraLocation.
		 * @return the cameraLocation
		 */
		public Location getCameraLocation() {
			return cameraLocation;
		}

		/**
		 * Gets the data.
		 * @return the data
		 */
		public int[] getData() {
			return data;
		}

	}

}
