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
public final class IceGiantNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code IceGiantNPC} {@code Object}.
	 */
	public IceGiantNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code IceGiantNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public IceGiantNPC(int id, Location location) {
		super(id, location, true);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new IceGiantNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			final Player player = killer.asPlayer();
			if (this.getLocation().withinDistance(new Location(3052, 9573, 0), 100) && !player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(1, 4)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player, 1, 4, true);
			}
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 111, 3072, 4685, 4686, 4687 };
	}

}
