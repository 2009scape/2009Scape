package plugin.npc.other;

import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.Location;

/**
 * Represents the abstract knight npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GuardNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 9, 32, 163, 164, 196, 197, 206, 253, 254, 255, 256, 274, 275, 296, 298, 299, 447, 448, 449, 489, 609, 678, 799, 837, 842, 862, 870, 877, 917, 1200, 1203, 1204, 1317, 1710, 1711, 1712, 2073, 2074, 2134, 2135, 2136, 2236, 2571, 2699, 2700, 2701, 2702, 2703, 3228, 3229, 3230, 3231, 3232, 3233, 3241, 3407, 3408, 3715, 4257, 4258, 4259, 4260, 4307, 4308, 4309, 4310, 4311, 4336, 4375, 4603, 4604, 4605, 4606, 5800, 5801, 5919, 5920 };

	/**
	 * Constructs a new {@code GuardNPC} {@code Object}.
	 */
	public GuardNPC() {
		super(0, null, true);
		this.setAggressive(false);
	}

	/**
	 * Constructs a new {@code GuardNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private GuardNPC(int id, Location location) {
		super(id, location, true);
		this.setAggressive(false);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new GuardNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
