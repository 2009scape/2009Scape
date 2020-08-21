package plugin.quest.free.dragonslayer;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.world.map.Location;
import core.tools.RandomFunction;

/**
 * Represents a ghosts in melzar's maze.
 * @author 'Vexia
 * @version 1.0
 */
public final class MazeGhostNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 103 };

	/**
	 * Represents the location to be near.
	 */
	private static final Location LOCATION = Location.create(2926, 3253, 1);

	/**
	 * Constructs a new {@code MazeGhostNPC} {@code Object}.
	 */
	public MazeGhostNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MazeGhostNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private MazeGhostNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MazeGhostNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer.getLocation().withinDistance(LOCATION)) {
			if (killer instanceof Player) {
				if (RandomFunction.random(0, 4) == 2) {
					GroundItemManager.create(DragonSlayer.ORANGE_KEY, getLocation(), ((Player) killer));
				}
			}
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
