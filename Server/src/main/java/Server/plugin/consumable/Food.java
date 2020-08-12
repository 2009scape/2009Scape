package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import plugin.consumable.effects.HealingEffect;

/**
 * Represents a consumable food.
 * @author 'Vexia
 * @date 22/12/2013
 */
public class Food extends Consumable {
	private String eatMessage;

	/**
	 * Represents the food consumtion sound.
	 */
	public static final Audio SOUND = new Audio(2393, 1, 1);

	public Food(final int[] ids, final ConsumableEffect effect, final String... messages) {
		super(ids, effect, messages);
		animation = new Animation(829);
	}

	public Food(int[] ids, ConsumableEffect effect, Animation animation, String... messages) {
		super(ids, effect, animation, messages);
	}

	@Override
	protected void executeConsumptionActions(Player player) {
		player.animate(animation);
		playEatingSound(player);
	}

	private void playEatingSound(Player player) {
		player.getAudioManager().send(SOUND);
	}
}
