package plugin.skill.farming;

import core.game.node.entity.player.Player;
import core.game.system.SystemLogger;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.callback.CallBack;
import core.game.world.repository.Repository;

/**
 * Represents the pulsed used to update all players farming states.
 * @author 'Vexia
 */
public final class FarmingPulse extends Pulse implements CallBack {

	@Override
	public boolean pulse() {
		for (Player p : Repository.getPlayers()) {
			if (p == null || p.isArtificial()) {
				continue;
			}
			p.getFarmingManager().cycle();
		}
		return false;
	}

	@Override
	public boolean call() {
		GameWorld.getPulser().submit(this);
		return true;
	}

}
