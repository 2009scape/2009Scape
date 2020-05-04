package org.crandor.game.content.global.action;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles digging with a spade.
 * @author Emperor
 */
public final class DigSpadeHandler {

	/**
	 * The digging actions.
	 */
	private static final Map<Location, DigAction> ACTIONS = new HashMap<>();

	/**
	 * The digging animation.
	 */
	public static final Animation ANIMATION = Animation.create(830);

	/**
	 * Handles a digging reward.
	 * @param player The player.
	 * @return {@code True} if the reward got handled.
	 */
	public static boolean dig(final Player player) {
		final DigAction action = ACTIONS.get(player.getLocation());
		player.animate(ANIMATION);
		player.lock(1);
		if (action != null) {
			GameWorld.Pulser.submit(new Pulse(1, player) {
				@Override
				public boolean pulse() {
					action.run(player);
					return true;
				}
			});
			return true;
		}
		return false;
	}

	/**
	 * Registers a new digging reward.
	 * @param location The location to dig on.
	 * @param action The reward.
	 * @return {@code True} if the reward got registered.
	 */
	public static boolean register(Location location, DigAction action) {
		if (ACTIONS.containsKey(location)) {
			System.err.println("Already contained dig reward for location " + location + ".");
			return false;
		}
		ACTIONS.put(location, action);
		return true;
	}
}