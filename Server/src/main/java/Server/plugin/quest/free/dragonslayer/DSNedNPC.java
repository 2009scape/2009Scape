package plugin.quest.free.dragonslayer;

import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;

/**
 * Represents the dragon slayer npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class DSNedNPC extends AbstractNPC {

	/**
	 * Represents the NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 918 };

	/**
	 * Constructs a new {@code DSNedNPC} {@code Object}.
	 */
	public DSNedNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code DSNedNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private DSNedNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new DSNedNPC(id, location);
	}

	@Override
	public boolean isHidden(final Player player) {
		return player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) != 30 && player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) != 40;
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
