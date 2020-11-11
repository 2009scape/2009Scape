package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the a duck npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DuckNPC extends AbstractNPC {

	/**
	 * Represents the force chat to use.
	 */
	private static final String FORCE_CHAT = "Quack!";

	/**
	 * Constructs a new {@code DuckNPC} {@code Object}.
	 */
	public DuckNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code DuckNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public DuckNPC(int id, Location location) {
		super(id, location, true);
	}

	@Override
	public void tick() {
		if (RandomFunction.random(45) == 5) {
			sendChat(FORCE_CHAT);
		}
		super.tick();
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new DuckNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			final Player player = killer.asPlayer();
			if (this.getLocation().withinDistance(new Location(2991,3383,0))) {
				player.getAchievementDiaryManager().finishTask(player,DiaryType.FALADOR,0, 9);
			}
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 46, 2693, 6113 };
	}

}
