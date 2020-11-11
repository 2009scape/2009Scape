package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;

/**
 * Represents a Jogre NPC.
 * @author Vexia
 */
@InitializablePlugin
public class JogreNPC extends AbstractNPC {

	/**
	 * If the jogre is in the pothole zone.
	 */
	private boolean inPothole;

	/**
	 * Constructs a new {@code JogreNPC} {@code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public JogreNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public void init() {
		super.init();
		inPothole = this.getViewport().getRegion().getId() == 11412;
	}

	/**
	 * Constructs a new {@code JogreNPC} {@code Object}
	 */
	public JogreNPC() {
		this(-1, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new JogreNPC(id, location);
	}

	@Override
	public void finalizeDeath(Entity killer) {
		if (inPothole && killer.isPlayer()) {
			Player player = killer.asPlayer();
			player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 0, 9);
		}
		super.finalizeDeath(killer);
	}

	@Override
	public int[] getIds() {
		return new int[] { 113 };
	}

}
