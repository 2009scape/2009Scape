package plugin.skill.agility.shortcuts;

import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.content.skill.member.agility.AgilityShortcut;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the monkey bar shortcut.
 * @author Vexia
 */
@InitializablePlugin
public class MonkeyBarShortcut extends AgilityShortcut {

	/**
	 * Represents the location cache data for the monkey bars.
	 */
	private static final Location[][] MBAR_LOCATIONS = new Location[][] { { new Location(3120, 9969, 0), Location.create(3121, 9969, 0) }, { new Location(3119, 9969, 0), Location.create(3120, 9969, 0) }, { new Location(3120, 9964, 0), Location.create(3121, 9964, 0) }, { Location.create(3120, 9963, 0), Location.create(3120, 9964, 0) }, { new Location(2598, 9489, 0), new Location(2597, 9488, 0) }, { new Location(2598, 9489, 0), new Location(2600, 9488, 0) }, { new Location(2598, 9494, 0), new Location(2597, 9495, 0) }, { new Location(2599, 9494, 0), new Location(2600, 9495, 0) } };

	/**
	 * Constructs a new {@Code MonkeyBarShortcut} {@Code Object}
	 */
	public MonkeyBarShortcut() {
		super(new int[] { 29375 }, 1, 14.0, "swing across");
	}

	/**
	 * Constructs a new {@Code MonkeyBarShortcut} {@Code Object}
	 * @param ids the ids.
	 * @param level the level.
	 * @param experience the exp.
	 * @param option the option.
	 */
	public MonkeyBarShortcut(int[] ids, int level, double experience, String option) {
		super(ids, level, experience, option);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		configure(new MonkeyBarShortcut(new int[] { 2321 }, 57, 20, "swing across"));
		return super.newInstance(arg);
	}

	@Override
	public void run(final Player player, final GameObject object, String option, boolean failed) {
		player.lock(5);
		Direction direct = Direction.get((object.getDirection().toInteger() + 2) % 4);
		if (object.getId() == 29375) {
			if (direct == Direction.SOUTH && player.getLocation().getY() < 9969) {
				direct = Direction.NORTH;
			} else if (direct == Direction.NORTH && player.getLocation().getY() >= 9969) {
				direct = Direction.SOUTH;
			}
		} else if (object.getId() == 2321) {
			if (player.getLocation().getY() >= 9494) {
				direct = Direction.SOUTH;
			} else {
				direct = Direction.NORTH;
			}
		}
		player.getAppearance().setAnimations(Animation.create(745));
		final Location start = player.getLocation();
		final Direction dir = direct;
		ForceMovement.run(player, start.transform(dir), start.transform(dir.getStepX() << 1, dir.getStepY() << 1, 0), Animation.create(742), Animation.create(744));
		player.addExtension(LogoutTask.class, new LocationLogoutTask(5, start));
		GameWorld.Pulser.submit(new Pulse(2, player) {
			int count;
			boolean failed;

			@Override
			public boolean pulse() {
				if (++count == 1) {
					if (object.getId() == 2321 && (failed = AgilityHandler.hasFailed(player, 57, 0.01))) {
						setDelay(1);
						player.animate(Animation.create(743));
						return false;
					}
					setDelay(4);
					AgilityHandler.walk(player, -1, player.getLocation().transform(dir), player.getLocation().transform(dir.getStepX() * 4, dir.getStepY() * 4, 0), Animation.create(662), 0.0, null);
					player.addExtension(LogoutTask.class, new LocationLogoutTask(7, start));
				} else if (count == 2) {
					if (failed) {
						player.getAppearance().setAnimations();
						player.getAppearance().sync();
						AgilityHandler.fail(player, 2, new Location(2599, 9564, 0), Animation.create(768), RandomFunction.random(1, 3), null);
						return true;
					}
					AgilityHandler.forceWalk(player, -1, player.getLocation(), player.getLocation().transform(dir), Animation.create(743), 10, getExperience(), null);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public Location getDestination(Node n, Node node) {
		if (node.getLocation().equals(new Location(2598, 9489, 0))) {
			return new Location(2597, 9488, 0);
		} else if (node.getLocation().equals(new Location(2598, 9494, 0))) {
			return new Location(2597, 9495, 0);
		} else if (node.getLocation().equals(new Location(2599, 9489, 0))) {
			return new Location(2600, 9488, 0);
		}
		for (Location[] locations : MBAR_LOCATIONS) {
			if (n.getLocation().equals(locations[0])) {
				return locations[1];
			}
		}
		if (node.getLocation().equals(new Location(2598, 9489, 0)) || node.getLocation().equals(new Location(2599, 9489, 0))) {
			return new Location(2600, 9495, 0);
		}
		return null;
	}
}
