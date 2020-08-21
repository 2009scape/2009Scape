package plugin.quest.free.dragonslayer;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.world.map.Location;

/**
 * Represents the lesser demon maze npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class MazeDemonNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 82 };

	/**
	 * Represents the location to be near to count as a maze npc.
	 */
	private final static Location LOCATION = Location.create(2936, 9652, 0);

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 */
	public MazeDemonNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MazeDemonNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	private MazeDemonNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MazeDemonNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer.getLocation().withinDistance(LOCATION)) {
			if (killer instanceof Player) {
				GroundItemManager.create(DragonSlayer.GREEN_KEY, getLocation(), ((Player) killer));
			}
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
