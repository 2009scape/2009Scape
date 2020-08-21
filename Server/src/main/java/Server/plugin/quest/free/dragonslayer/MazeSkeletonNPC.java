package plugin.quest.free.dragonslayer;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.world.map.Location;
import core.tools.RandomFunction;

/**
 * Represents a skeleton in melzars maze.
 * @author 'Vexia
 * @version 1.0
 */
public final class MazeSkeletonNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 90 };

	/**
	 * Represents the location to be near to count as a maze npc.
	 */
	private static Location LOCATION = Location.create(2927, 3253, 2);

	/**
	 * Constructs a new {@code MazeSkeletonNPC} {@code Object}.
	 */
	public MazeSkeletonNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MazeSkeletonNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	private MazeSkeletonNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MazeSkeletonNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer.getLocation().withinDistance(LOCATION)) {
			if (killer instanceof Player) {
				if (RandomFunction.random(0, 5) == 2) {
					GroundItemManager.create(DragonSlayer.YELLOW_KEY, getLocation(), ((Player) killer));
				}
			}
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
