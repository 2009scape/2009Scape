package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.world.map.Location;

/**
 * Handles the zombie npc.
 * @author Vexia
 *
 */
@InitializablePlugin
public class ZombieNPC extends AbstractNPC {

	/**
	 * Constructs the {@code ZombieNPC}
	 */
	public ZombieNPC() {
		super(0, null);
	}
	
	/**
	 * Constructs the {@code ZombieNPC} 
	 * @param id The id.
	 * @param location The location.
	 */
	public ZombieNPC(int id, Location location) {
		super(id, location);
	}
	
	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			Player player = killer.asPlayer();
			// Defeat a zombie in the sewers under the jail
			if (player.getViewport().getRegion().getId() == 12438 || player.getViewport().getRegion().getId() == 12439) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 1, 18);
			}
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new ZombieNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] {73, 74};
	}

}
