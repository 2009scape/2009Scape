package plugin.npc;

import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles the Hans NPC.
 * @author Emperor
 */
@InitializablePlugin
public final class HansNPC extends AbstractNPC {

	/**
	 * The default spawn location.
	 */
	private static final Location DEFAULT_SPAWN = Location.create(3221, 3218, 0);

	/**
	 * The movement path.
	 */
	private static final Location[] MOVEMENT_PATH = { Location.create(3221, 3214, 0), Location.create(3218, 3211, 0), Location.create(3218, 3208, 0), Location.create(3214, 3205, 0), Location.create(3202, 3205, 0), Location.create(3202, 3232, 0), Location.create(3203, 3233, 0), Location.create(3207, 3233, 0), Location.create(3210, 3230, 0), Location.create(3219, 3230, 0), Location.create(3219, 3222, 0), Location.create(3221, 3222, 0) };

	/**
	 * Constructs a new {@code HansNPC} {@Code Object}.
	 */
	public HansNPC() {
		super(0, DEFAULT_SPAWN);
	}

	/**
	 * Constructs a new {@code HansNPC} {@Code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public HansNPC(int id, Location location) {
		super(id, location, false);
	}

	@Override
	public void configure() {
		super.configure();
		if (isWalks()) {
			configureMovementPath(MOVEMENT_PATH);
		}
		setWalks(true);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new HansNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 0 };
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		init();
		return super.newInstance(arg);
	}
}
