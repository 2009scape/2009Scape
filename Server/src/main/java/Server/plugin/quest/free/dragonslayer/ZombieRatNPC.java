package plugin.quest.free.dragonslayer;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.tools.RandomFunction;

/**
 * Represents a zombie rat npc related to dragon slayer and witch's potion.
 * @author 'Vexia
 * @version 1.0
 */
public final class ZombieRatNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 6088, 6089, 6090 };

	/**
	 * Represents the rat tail item.
	 */
	private static final Item RAT_TAIL = new Item(300, 1);

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 */
	public ZombieRatNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private ZombieRatNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new ZombieRatNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			final Player p = ((Player) killer);
			Quest quest = p.getQuestRepository().getQuest("Dragon Slayer");
			if (RandomFunction.random(0, 4) == 2) {
				GroundItemManager.create(DragonSlayer.RED_KEY, getLocation(), ((Player) killer));
			}
			quest = p.getQuestRepository().getQuest("Witch's Potion");
			if (quest.getStage(p) > 0 && quest.getStage(p) < 100) {
				GroundItemManager.create(RAT_TAIL, getLocation(), p);
			}
			GroundItemManager.create(new Item(526), getLocation(), p);
		}
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
