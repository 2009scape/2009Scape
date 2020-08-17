package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

public class Drink extends Consumable {

	public static final Audio SOUND = new Audio(4580, 1, 1);

	public Drink(int[] ids, ConsumableEffect effect, String... messages) {
		super(ids, effect, messages);
		animation = new Animation(1327);
	}

	public Drink(int[] ids, ConsumableEffect effect, Animation animation, String... messages) {
		super(ids, effect, animation, messages);
	}

	@Override
	protected void sendDefaultMessages(Player player, Item item) {
		player.getPacketDispatch().sendMessage("You drink the " + getFormattedName(item) + ".");
	}

	@Override
	protected void executeConsumptionActions(Player player) {
		player.animate(animation);
		player.getAudioManager().send(SOUND);
	}

	@Override
	public String getFormattedName(Item item) {
		return item.getName().replace("(4)", "").replace("(3)", "").replace("(2)", "").replace("(1)", "").replace("(m4)", "").replace("(m3)", "").replace("(m2)", "").replace("(m1)", "").replace("(m)", "").trim().toLowerCase();
	}
}
