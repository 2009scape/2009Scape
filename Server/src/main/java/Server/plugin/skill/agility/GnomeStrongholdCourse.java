package plugin.skill.agility;

import core.cache.def.impl.ObjectDefinition;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.system.task.LocationLogoutTask;
import core.game.system.task.LogoutTask;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;

/**
 * Handles the gnome stronghold agility course.
 * @author Emperor
 */
@InitializablePlugin
public final class GnomeStrongholdCourse extends AgilityCourse {

	/**
	 * The pipes currently in usage.
	 */
	private static final int[] USED_PIPES = new int[2];

	/**
	 * The trainer NPCs.
	 */
	private static final NPC[] TRAINERS = new NPC[5];

	/**
	 * Constructs a new {@code GnomeStrongholdCourse} {@code Object}.
	 */
	public GnomeStrongholdCourse() {
		this(null);
	}

	/**
	 * Constructs a new {@code GnomeStrongholdCourse} {@code Object}.
	 * @param player The player.
	 */
	public GnomeStrongholdCourse(Player player) {
		super(player, 7, 39.0);
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		getCourse(player); // Sets the extension.
		GameObject object = (GameObject) node;
		switch (object.getId()) {

			//Log Cross
			case 2295:
				TRAINERS[0].sendChat("Okay get over that log, quick quick!");
				player.getPacketDispatch().sendMessage("You walk carefully across the slippery log...");
				AgilityHandler.walk(player, 0, Location.create(2474, 3436, 0), Location.create(2474, 3429, 0), Animation.create(155), 7.5, "...You make it safely to the other side.");
				return true;

			//Net Climb
			case 2285:
				TRAINERS[1].sendChat("Move it, move it, move it!");
				player.getPacketDispatch().sendMessage("You climb the netting...");
				AgilityHandler.climb(player, 1, Animation.create(828), object.getLocation().transform(0, -1, 1), 7.5, null);
				return true;

			//branch climb
			case 35970:
				TRAINERS[2].sendChat("That's it - straight up.");
				player.getPacketDispatch().sendMessage("You climb the tree..");
				AgilityHandler.climb(player, 2, Animation.create(828), Location.create(2473, 3420, 2), 5.0, "...To the platform above.");
				return true;

			//tight-rope
			case 2312:
				TRAINERS[3].sendChat("Come on scaredy cat, get across that rope!");
				player.getPacketDispatch().sendMessage("You carefully cross the tightrope.");
				AgilityHandler.walk(player, 3, Location.create(2477, 3420, 2), Location.create(2483, 3420, 2), Animation.create(155), 7.5, null);
				return true;

			//Other side of tight-rope
			case 4059:
				player.getPacketDispatch().sendMessage("You can't do that from here.");
				return true;

			//Branches on the other side of tight-rope
			case 2314:
			case 2315:
				player.getPacketDispatch().sendMessage("You climb down the tree..");
				AgilityHandler.climb(player, 4, Animation.create(828), Location.create(2487, 3420, 0), 5.0, "You land on the ground.");
				return true;

			//Second Net Climb
			case 2286:
				TRAINERS[4].sendChat("My Granny can move faster than you.");
				player.faceLocation(player.getLocation().transform(0, 2, 0));
				player.getPacketDispatch().sendMessage("You climb the netting...");
				AgilityHandler.climb(player, 5, Animation.create(828), player.getLocation().transform(0, 2, 0), 7.5, null);
				return true;

			//Pipe Squeeze
			case 4058:
			case 154:
				final int index = object.getId() == 154 ? 0 : 1; //If the player clicks on the left pipe, set index to 0, otherwise 1
				final int x = 2484 + (index * 3); //change the x coordinates for walking/animations depending on index multiplier
				if (object.getLocation().getY() == 3435) {
					player.getPacketDispatch().sendMessage("You can't do that from here.");
					return true;
				}
				if (USED_PIPES[index] > GameWorld.getTicks()) {
					player.getPacketDispatch().sendMessage("The pipe is being used.");
					return true;
				}
				USED_PIPES[index] = GameWorld.getTicks() + 10;
				player.lock(12);

				//Animations and force walking
				//X variable is determined by both index and x variables before
				AgilityHandler.forceWalk(player, -1, Location.create(x, 3430, 0), Location.create(x, 3433, 0), Animation.create(749), 10, 0, null);
				AgilityHandler.forceWalk(player, -1, Location.create(x, 3433, 0), Location.create(x, 3435, 0), Animation.create(844), 10, 0, null, 5);
				AgilityHandler.forceWalk(player, 6, Location.create(x, 3435, 0), Location.create(x, 3437, 0), Animation.create(748), 20, 7.5, null, 8);
				player.addExtension(LogoutTask.class, new LocationLogoutTask(12, Location.create(x, 3430, 0)));
				return true;
		}
		return false;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		GameObject object = (GameObject) n;
		switch (object.getId()) {
		case 2295:
			return Location.create(2474, 3436, 0);
		case 2286:
			int x = node.getLocation().getX();
			if (x < n.getLocation().getX()) {
				x = n.getLocation().getX();
			} else if (x > n.getLocation().getX() + 1) {
				x = n.getLocation().getX() + 1;
			}
			return Location.create(x, n.getLocation().getY() - 1, 0);
		case 4058:
		case 154:
			if (n.getLocation().getY() == 3431) {
				return n.getLocation().transform(0, -1, 0);
			}
		}
		return null;
	}

	@Override
	public void configure() {
		TRAINERS[0] = NPC.create(162, Location.create(2473, 3438, 0));
		TRAINERS[1] = NPC.create(162, Location.create(2478, 3426, 0));
		TRAINERS[2] = NPC.create(162, Location.create(2474, 3422, 1));
		TRAINERS[3] = NPC.create(162, Location.create(2472, 3419, 2));
		TRAINERS[4] = NPC.create(162, Location.create(2489, 3425, 0));
		for (NPC npc : TRAINERS) {
			npc.init();
			npc.setWalkRadius(3);
		}
		ObjectDefinition.forId(2295).getConfigurations().put("option:walk-across", this);
		ObjectDefinition.forId(2285).getConfigurations().put("option:climb-over", this);
		ObjectDefinition.forId(35970).getConfigurations().put("option:climb", this);
		ObjectDefinition.forId(2312).getConfigurations().put("option:walk-on", this);
		ObjectDefinition.forId(4059).getConfigurations().put("option:walk-on", this);
		ObjectDefinition.forId(2314).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(2315).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(2286).getConfigurations().put("option:climb-over", this);
		ObjectDefinition.forId(4058).getConfigurations().put("option:squeeze-through", this);
		ObjectDefinition.forId(154).getConfigurations().put("option:squeeze-through", this);
	}

	@Override
	public AgilityCourse createInstance(Player player) {
		return new GnomeStrongholdCourse(player);
	}

}
