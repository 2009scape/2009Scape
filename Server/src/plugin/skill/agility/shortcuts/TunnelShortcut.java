package plugin.skill.agility.shortcuts;

import org.crandor.game.content.skill.member.agility.AgilityShortcut;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles a tunnel shortcut.
 * @author Vexia
 */
@InitializablePlugin
public class TunnelShortcut extends AgilityShortcut {

	/**
	 * The climbing down animation.
	 */
	private static final Animation CLIMB_DOWN = Animation.create(2589);

	/**
	 * The crawling through animation.
	 */
	private static final Animation CRAWL_THROUGH = Animation.create(2590);

	/**
	 * The climbing up animation.
	 */
	private static final Animation CLIMB_UP = Animation.create(2591);

	/**
	 * The increment offset.
	 */
	private int offset;

	/**
	 * Constructs a new {@Code TunnelShortcut} {@Code Object}
	 */
	public TunnelShortcut() {
		super(new int[] {}, 0, 0.0);
	}

	/**
	 * Constructs a new {@Code TunnelShortcut} {@Code Object}
	 * @param ids the ids.
	 * @param level the level.
	 * @param experience the experience.
	 * @param options the options.
	 */
	public TunnelShortcut(int[] ids, int level, double experience, int offset, String... options) {
		super(ids, level, experience, options);
		this.offset = offset;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		configure(new TunnelShortcut(new int[] { 9309, 9310 }, 26, 0.0, 0, "climb-into"));
		configure(new TunnelShortcut(new int[] { 9302, 9301 }, 16, 0.0, 1, "climb-into", "climb-under"));
		configure(new TunnelShortcut(new int[] { 14922 }, 1, 0.0, 1, "enter"));
		return this;
	}

	@Override
	public void run(final Player player, GameObject object, String option, boolean failed) {
		player.lock(6);
		final GameObject o = object;
		final Location start = player.getLocation();
		final Direction dir = Direction.getDirection(start, o.getLocation());
		if (object.getLocation().getX() == 2575) {
			offset = 1;
		}
		ForceMovement.run(player, start, o.getLocation(), CLIMB_DOWN, 8);
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int count;

			@Override
			public boolean pulse() {
				switch (++count) {
				case 2:
					player.animate(CRAWL_THROUGH);
					player.getProperties().setTeleportLocation(start.transform(dir, 2 + offset));
					break;
				case 5:
					ForceMovement.run(player, player.getLocation(), start.transform(dir, 4 + offset), CLIMB_UP, 19);
					break;
				case 6:
					player.animate(ForceMovement.WALK_ANIMATION);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n.getId() == 14922) {
			return n.getLocation().transform(getObjectDirection(n.asObject().getDirection()), 1);
		}
		return getStart(n.getLocation(), n.getDirection());
	}

	/**
	 * Gets the start location.
	 * @param location the location.
	 * @param dir the dir.
	 * @return the location.
	 */
	public Location getStart(Location location, Direction dir) {
		switch (dir) {
		case NORTH:
			break;
		case SOUTH:
			break;
		case EAST:
			return location.transform(0, location.getY() == 3111 ? 1 : -1, 0);
		case WEST:
			return location.transform(0, 1, 0);
		default:
			return location;
		}
		return location;
	}
}
