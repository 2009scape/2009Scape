package core.net.packet.context;

import core.game.node.entity.player.Player;
import core.game.world.update.flag.context.Animation;
import core.net.packet.Context;

/**
 * Represents the context of animating an object.
 * @author Emperor
 * @author 'Vexia
 * @date 10/11/2013
 */
public class AnimateObjectContext implements Context {

	/**
	 * Represents the player of the context.
	 */
	private final Player player;

	/**
	 * Represents the animation to use.
	 */
	private final Animation animation;

	/**
	 * Constructs a new {@code AnimateObjectContext} {@code Object}.
	 * @param player the player.
	 * @param animation the animation.
	 */
	public AnimateObjectContext(Player player, Animation animation) {
		this.player = player;
		this.animation = animation;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}
}