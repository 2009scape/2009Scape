package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.audio.Audio;
import core.game.world.update.flag.context.Animation;

/**
 * Represents a drink.
 * @author 'Vexia
 * @date 23/12/2013
 */
public class Drink extends Consumable {

	/**
	 * Represents the sound of drinking.
	 */
	public static final Audio SOUND = new Audio(4580, 1, 0);

	public Drink(int[] ids, ConsumableEffect effect, String... messages) {
		super(ids, effect, messages);
		animation = new Animation(1327);
	}

	public Drink(int[] ids, ConsumableEffect effect, Animation animation, String... messages) {
		super(ids, effect, animation, messages);
	}

	@Override
	protected void executeConsumptionActions(Player player) {
		player.animate(animation);
		playDrinkingSound(player);
	}

	protected void playDrinkingSound(Player player) {
		player.getAudioManager().send(SOUND);
	}
}
