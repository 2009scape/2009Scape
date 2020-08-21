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
public final class MazeZombieNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 75 };

	/**
	 * Represents the location to be near.
	 */
	private final static Location LOCATION = Location.create(2933, 9641, 0);

	/**
	 * Constructs a new {@code MazeZombieNPC.java} {@code Object}.
	 */
	public MazeZombieNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MazeZombieNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private MazeZombieNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MazeZombieNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer.getLocation().withinDistance(LOCATION)) {
			if (killer instanceof Player) {
				if (RandomFunction.random(0, 3) == 2) {
					GroundItemManager.create(DragonSlayer.BLUE_KEY, getLocation(), ((Player) killer));
				}
			}
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
