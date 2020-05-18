package org.crandor.game.node.entity.player.link.prayer;

import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.NodeTask;

/**
 * Represents an event used to drain prayer points.
 * @author 'Vexia
 */
public final class DrainTask extends NodeTask {

	/**
	 * Constructs a new {@code DrainTask} {@code Object}.
	 */
	public DrainTask() {
		super(2);
	}

	@Override
	public boolean exec(Node node, Node... n) {
		if (((Player) node).getPrayer().getActive().isEmpty()) {
			return true;
		}
		((Player) node).getSkills().decrementPrayerPoints(getDrain(((Player) node).getPrayer()));
		return ((Player) node).getSkills().getPrayerPoints() <= 0;
	}

	@Override
	public void stop(Node node, Node... n) {
		final Player player = ((Player) node);
		if (player.getSkills().getPrayerPoints() <= 0) {
			player.getPrayer().reset();
			player.getAudioManager().send(2672);
			player.getPacketDispatch().sendMessage("You have run out of prayer points, you must recharge at an altar.");
		}
		super.stop(node, n);
	}

	@Override
	public void start(Node node, Node... n) {
		((Player) node).removeAttribute("prayer-message");
		super.start(node, n);
	}

	@Override
	public boolean removeFor(String s, Node node, Node... n) {
		return true;
	}

	/**
	 * Method used to initialize this pulse.
	 * @param player the player.
	 */
	public void init(final Player player) {
		if (getPulse() == null || !getPulse().isRunning()) {
			schedule(player);
		}
	}

	/**
	 * Method used to return that drain tick.
	 * @param the prayer manager.
	 * @return the drain tick, converted to an integer.
	 */
	public double getDrain(final Prayer prayer) {
		double amountDrain = 0;
		for (PrayerType type : prayer.getActive()) {
			double drain = type.getDrain();
			double bonus = 0.035 * prayer.getPlayer().getProperties().getBonuses()[12];
			drain = drain * (1 + bonus);
			drain = 0.6 / drain;
			amountDrain += drain;
		}
		return amountDrain;
	}
}
