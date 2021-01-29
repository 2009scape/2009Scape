package plugin.skill.agility.pyramid;

import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.system.task.MovementHook;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the agility pyramid area.
 * @author 'Vexia
 * @version 1.0
 */
public final class AgilityPyramidZone extends MapZone implements Plugin<Object> {

	/**
	 * The location based traps.
	 */
	public static final Map<Location, MovementHook> LOCATION_TRAPS = new HashMap<>();

	/**
	 * Constructs a new {@code AgilityPyramid} {@code Object}.
	 */
	public AgilityPyramidZone() {
		super("agility pyramid", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			final Player player = (Player) e;
			AgilityPyramidCourse.addConfig(player, 10872, 1, true);
			AgilityPyramidCourse.addConfig(player, 10873, 3, true);
		}
		return super.enter(e);
	}

	@Override
	public boolean move(Entity e, Location loc, Location dest) {
		if (!e.getLocks().isMovementLocked() && e instanceof Player) {
			MovementHook hook = LOCATION_TRAPS.get(loc);
			if (hook != null) {
				e.setDirection(Direction.getLogicalDirection(loc, dest));
				return hook.handle(e, loc);
			}
		}
		return super.move(e, loc, dest);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3352, 2826, 3378, 2854));
		register(new ZoneBorders(3007, 4672, 3071, 4735));
	}
}
