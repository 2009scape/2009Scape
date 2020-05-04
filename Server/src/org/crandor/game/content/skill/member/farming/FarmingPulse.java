package org.crandor.game.content.skill.member.farming;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.callback.CallBack;
import org.crandor.game.world.repository.Repository;

/**
 * Represents the pulsed used to update all players farming states.
 * @author 'Vexia
 */
public final class FarmingPulse extends Pulse implements CallBack {

	@Override
	public boolean pulse() {
		for (Player p : Repository.getPlayers()) {
			if (p == null) {
				continue;
			}
			p.getFarmingManager().cycle();
		}
		return false;
	}

	@Override
	public boolean call() {
		GameWorld.Pulser.submit(this);
		return true;
	}

}
