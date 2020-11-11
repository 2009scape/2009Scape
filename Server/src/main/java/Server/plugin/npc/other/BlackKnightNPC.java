package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;

/**
 * Represents the a duck npc.
 * @author afaroutdude
 */
@InitializablePlugin
public final class BlackKnightNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code BlackKnightNPC} {@code Object}.
	 */
	public BlackKnightNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code BlackKnightNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public BlackKnightNPC(int id, Location location) {
		super(id, location, true);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new BlackKnightNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			final Player player = killer.asPlayer();
			if (!player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(1, 3)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player, 1, 3, true);
			}
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 178, 179, 610, 2698, 6189 };
	}

}
