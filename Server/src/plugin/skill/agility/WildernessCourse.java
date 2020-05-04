package plugin.skill.agility;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.agility.AgilityCourse;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;

/**
 * Handles the wilderness agility course.
 * @author 'Vexia
 */
@InitializablePlugin
public final class WildernessCourse extends AgilityCourse {

	/**
	 * The rope delay.
	 */
	private static int ropeDelay;

	/**
	 * Constructs a new {@code WildernessCourse} {@code Object}.
	 */
	public WildernessCourse() {
		this(null);
	}

	/**
	 * Constructs a new {@code WildernessCourse} {@code Object}.
	 * @param player the player.
	 */
	public WildernessCourse(Player player) {
		super(player, 5, 499);
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		getCourse(player); // Sets the extension.
		GameObject object = (GameObject) node;
		switch (object.getId()) {
		case 2309:
			handleEntrance(player, object);
			break;
		case 2307:
		case 2308:
			DoorActionHandler.handleAutowalkDoor(player, object);
			handleEntranceObstacle(player, object);
			break;
		case 2288:
			handlePipe(player, object);
			break;
		case 2283:
			handleRopeSwing(player, object);
			break;
		case 37704:
			handleSteppingStones(player, object);
			break;
		case 2297:
			handleLogBalance(player, object);
			break;
		case 2328:
			handleRockClimb(player, object);
			break;
		}
		return true;
	}

	/**
	 * Handles the door entrance.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleEntrance(final Player player, final GameObject object) {
		if (player.getLocation().getY() > 3916 || player.getSkills().getLevel(Skills.AGILITY) >= 52) {
			DoorActionHandler.handleAutowalkDoor(player, object);
			if (player.getLocation().getY() <= 3916) {
				handleEntranceObstacle(player, object);
			}
		} else {
			player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 52 to enter.");
		}
	}

	/**
	 * Handles the entrance obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleEntranceObstacle(final Player player, final GameObject object) {
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int counter;
			final boolean fail = AgilityHandler.hasFailed(player, 1, 0.3);

			@Override
			public boolean pulse() {
				switch (++counter) {
				case 2:
					final Location end = fail ? Location.create(2998, 3924, 0) : object.getId() < 2309 ? Location.create(2998, 3917, 0) : Location.create(2998, 3930, 0);
					final Location start = object.getId() < 2309 ? player.getLocation() : Location.create(2998, 3917, 0);
					player.getPacketDispatch().sendMessage("You go through the gate and try to edge over the ridge...");
					AgilityHandler.walk(player, -1, start, end, Animation.create(155), fail ? 0.0 : 15.00, fail ? "You loose your footing and fail into the wolf pit." : "You skillfully balance across the ridge...");
					break;
				case 9:
					if (fail) {
						AgilityHandler.fail(player, 0, player.getLocation().transform(object.getId() < 2309 ? -2 : 2, 0, 0), Animation.create(object.getId() < 2309 ? 771 : 771), getHitAmount(player), null);
					}
					return fail;
				case 15:
					player.lock(3);
					break;
				case 16:
					Location doorLoc = object.getId() < 2309 ? new Location(2998, 3917, 0) : new Location(2998, 3931, 0);
					DoorActionHandler.handleAutowalkDoor(player, RegionManager.getObject(doorLoc));
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Handles the pipe obstacle.
	 * @param player the pipe.
	 * @param object the object.
	 */
	private void handlePipe(final Player player, final GameObject object) {
		if (object.getLocation().getY() == 3948) {
			player.getPacketDispatch().sendMessage("You can't do that from here.");
			return;
		}
		if (player.getSkills().getLevel(Skills.AGILITY) < 49) {
			player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 49 to do this.");
			return;
		}
		player.lock(12);
		GameWorld.Pulser.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				int x = 3004;
				AgilityHandler.forceWalk(player, -1, Location.create(x, 3937, 0), Location.create(x, 3939, 0), Animation.create(749), 10, 0, null);
				AgilityHandler.forceWalk(player, -1, Location.create(x, 3939, 0), Location.create(x, 3948, 0), Animation.create(844), 10, 0, null, 1);
				AgilityHandler.forceWalk(player, 0, Location.create(x, 3948, 0), Location.create(x, 3950, 0), Animation.create(748), 20, 12.5, null, 14);
				player.addExtension(LogoutTask.class, new LocationLogoutTask(14, Location.create(3004, 3937, 0)));
				return true;
			}
		});
	}

	/**
	 * Handles the rope swing obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleRopeSwing(final Player player, final GameObject object) {
		if (player.getLocation().getY() < 3554) {
			player.getPacketDispatch().sendMessage("You cannot do that from here.");
			return;
		}
		if (ropeDelay > GameWorld.getTicks()) {
			player.getPacketDispatch().sendMessage("The rope is being used.");
			return;
		}
		if (AgilityHandler.hasFailed(player, 1, 0.1)) {
			AgilityHandler.fail(player, 0, Location.create(3005, 10357, 0), null, getHitAmount(player), "You slip and fall to the pit bellow.");
			return;
		}
		ropeDelay = GameWorld.getTicks() + 2;
		player.getPacketDispatch().sendObjectAnimation(object, Animation.create(497), true);
		AgilityHandler.forceWalk(player, 1, player.getLocation(), Location.create(3005, 3958, 0), Animation.create(751), 50, 20, "You skillfully swing across.", 1);
	}

	/**
	 * Handles the stepping stone obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleSteppingStones(final Player player, final GameObject object) {
		final boolean fail = AgilityHandler.hasFailed(player, 1, 0.3);
		player.addExtension(LogoutTask.class, new LocationLogoutTask(12, player.getLocation()));
		GameWorld.Pulser.submit(new Pulse(2, player) {
			int counter;

			@Override
			public boolean pulse() {
				if (counter == 3 && fail) {
					AgilityHandler.fail(player, -1, Location.create(3001, 3963, 0), Animation.create(771), (int) (player.getSkills().getLifepoints() * 0.26), "...You lose your footing and fall into the lava.");
					return true;
				}
				AgilityHandler.forceWalk(player, counter == 5 ? 2 : -1, player.getLocation(), player.getLocation().transform(-1, 0, 0), Animation.create(741), 10, counter == 5 ? 20 : 0, counter != 0 ? null : "You carefully start crossing the stepping stones...");
				return ++counter == 6;
			}

			@Override
			public void stop() {
				super.stop();
			}
		});
	}

	/**
	 * Handles the log balance obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleLogBalance(final Player player, final GameObject object) {
		final boolean failed = AgilityHandler.hasFailed(player, 1, 0.5);
		final Location end = failed ? Location.create(2998, 3945, 0) : Location.create(2994, 3945, 0);
		player.getPacketDispatch().sendMessage("You walk carefully across the slippery log...");
		AgilityHandler.walk(player, failed ? -1 : 3, player.getLocation(), end, Animation.create(155), failed ? 0.0 : 20, failed ? null : "You skillfully edge across the gap.");
		if (failed) {
			GameWorld.Pulser.submit(new Pulse(5, player) {
				@Override
				public boolean pulse() {
					player.faceLocation(Location.create(2998, 3944, 0));
					AgilityHandler.fail(player, 3, Location.create(2998, 10345, 0), Animation.create(770), getHitAmount(player), "You slip and fall onto the spikes below.");
					return true;
				}
			});
			return;
		}
	}

	/**
	 * Handles the rock climbing obstacle.
	 * @param player the player.
	 * @param object the object.
	 */
	private void handleRockClimb(final Player player, final GameObject object) {
		AgilityHandler.walk(player, 4, player.getLocation(), player.getLocation().transform(0, -4, 0), Animation.create(740), 0, "You reach the top.");
		GameWorld.Pulser.submit(new Pulse(4, player) {
			@Override
			public boolean pulse() {
				player.getAnimator().reset();
				return true;
			}
		});
	}

	@Override
	public Location getDestination(Node node, Node n) {
		switch (n.getId()) {
		case 2283:
			return Location.create(3005, 3953, 0);
		case 37704:
			return Location.create(3002, 3960, 0);
		case 2297:
			return Location.create(3002, 3945, 0);
		case 2328:
			return n.getLocation().transform(0, 1, 0);
		}
		return null;
	}

	@Override
	public void configure() {
		ObjectDefinition.forId(2309).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2308).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2307).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2288).getConfigurations().put("option:squeeze-through", this);
		ObjectDefinition.forId(2283).getConfigurations().put("option:swing-on", this);
		ObjectDefinition.forId(37704).getConfigurations().put("option:cross", this);
		ObjectDefinition.forId(2297).getConfigurations().put("option:walk-across", this);
		ObjectDefinition.forId(2328).getConfigurations().put("option:climb", this);
	}

	@Override
	public AgilityCourse createInstance(Player player) {
		return new WildernessCourse(player);
	}

}
