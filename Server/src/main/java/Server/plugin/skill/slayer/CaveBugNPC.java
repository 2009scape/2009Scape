package plugin.skill.slayer;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneBorders;
import core.plugin.InitializablePlugin;

/**
 * Handles the cave bug npc.
 * @author Vexia
 *
 */
@InitializablePlugin
public class CaveBugNPC extends AbstractNPC {
	
	/** 
	 * The cave border.
	 */
//	private static final ZoneBorders CAVE_BORDER = new ZoneBorders(3139, 9534, 3260, 9587);

	/**
	 * Constructs the {@code CaveBugNPC}
	 */
	public CaveBugNPC() {
		super(-1, null);
	}
	
	/**
	 * Constructs the {@code CaveBugNPC} 
	 * @param id The id.
	 * @param location The location.
	 */
	public CaveBugNPC(int id, Location location) {
		super(id, location);
	}
	
	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			Player p = killer.asPlayer();
/*			AchievementDiary diary = p.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE);
			if (!diary.isComplete(0, 0) && CAVE_BORDER.insideBorder(p)) {
				diary.updateTask(p, 0, 0, true);
			}*/
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new CaveBugNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] {1832, 1833, 5750};
	}

}
