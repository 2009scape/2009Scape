/*
package plugin.quest.witchs_house;

import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;

public final class ExperimentSession {

	*/
/**
	 * The player.
	 *//*

	private final Player player;

	*/
/**
	 * The npc npc.
	 *//*

	private final WitchsExperimentNPC npc;

	*/
/**
	 * Constructs a new {@code ExperimentSession} {@code Object}.
	 *
	 * @param player the player.
	 *//*

	public ExperimentSession(final Player player) {
		this.player = player;
		this.npc = new WitchsExperimentNPC(WitchsExperimentNPC.ExperimentType.values()[0].getId(), Location.create(2936, 3463, 0), this);
		if (player.getExtension(ExperimentSession.class) != null) {
			player.removeExtension(ExperimentSession.class);
		}
		player.addExtension(ExperimentSession.class, this);
	}

	*/
/**
	 * Creates the npc session.
	 *
	 * @param player the player.
	 * @return the session.
	 *//*

	public static ExperimentSession create(Player player) {
		return new ExperimentSession(player);
	}

	*/
/**
	 * Starts the session.
	 *//*

	public void start() {
		if (npc.getType().ordinal() > 0) {
			npc.init();
			npc.getProperties().getCombatPulse().attack(player);
			return;
		}
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int count;

			@Override
			public boolean pulse() {
				if (++count == 1) {
					npc.init();
					npc.setCommenced(true);
					return true;
				}
				return false;
			}
		});
	}

	*/
/**
	 * Closes the session.
	 *//*

	public void close() {
		npc.clear();
		player.removeExtension(ExperimentSession.class);
	}

	*/
/**
	 * Gets the npc session.
	 *
	 * @param player the player.
	 * @return the session.
	 *//*

	public static ExperimentSession getSession(Player player) {
		return player.getExtension(ExperimentSession.class);
	}

	*/
/**
	 * Gets the player.
	 *
	 * @return The player.
	 *//*

	public Player getPlayer() {
		return player;
	}
}*/
