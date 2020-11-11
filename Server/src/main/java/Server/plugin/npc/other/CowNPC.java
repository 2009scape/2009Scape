package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the a cow npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CowNPC extends AbstractNPC {

	/**
	 * Represents the force chat to use.
	 */
	private static final String FORCE_CHAT = "Moo";

	/**
	 * Constructs a new {@code CowNPC} {@code Object}.
	 */
	public CowNPC() {
		super(0, null, true);
	}

	/**
	 * Constructs a new {@code CowNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public CowNPC(int id, Location location) {
		super(id, location, true);
	}

	@Override
	public void tick() {
		if (RandomFunction.random(45) == 5 && this.getId() != 1766) { // calves don't moo
			sendChat(FORCE_CHAT);
		}
		super.tick();
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new CowNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer != null) {
			Player player = killer.asPlayer();
			// Obtain a cow-hide from a cow in the field north-east of<br><br>Lumbridge
			if (player.getViewport().getRegion().getId() == 12850 || player.getViewport().getRegion().getId() == 12851) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.LUMBRIDGE, 1, 1);
			}
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 81, 397, 955, 1766, 1767, 3309 };
	}

}
