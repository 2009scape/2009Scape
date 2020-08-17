package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;

/**
 * Represents any item that has a consumption option such as 'Eat' or 'Drink'.
 */
public abstract class Consumable implements Plugin<Object> {

	/**
	 * Represents the item IDs of all the variants of a consumable where the last one is often the empty container, if it has any.
	 */
	protected final int[] ids;

	/**
	 * Represents the effect to apply on the player once the item is consumed.
	 */
	protected final ConsumableEffect effect;

	/**
	 * Represents the messages to send to the player when it consumes the item.
	 */
	protected final String[] messages;

	/**
	 * Represents the animation that the player will execute when consuming the item.
	 */
	protected Animation animation = null;

	public Consumable(final int[] ids, final ConsumableEffect effect, final String... messages) {
		this.ids = ids;
		this.effect = effect;
		this.messages = messages;
	}

	public Consumable(final int[] ids, final ConsumableEffect effect, final Animation animation, final String... messages) {
		this.ids = ids;
		this.effect = effect;
		this.animation = animation;
		this.messages = messages;
	}

	public void consume(final Item item, final Player player) {
		executeConsumptionActions(player);
		final int nextItemId = getNextItemId(item.getId());
		if (nextItemId != -1) {
			player.getInventory().replace(new Item(nextItemId), item.getSlot());
		} else {
			player.getInventory().remove(item);
		}
		final int initialLifePoints = player.getSkills().getLifepoints();
		Consumables.getConsumableById(item.getId()).effect.activate(player);
		sendMessages(player, initialLifePoints, item, messages);
	}

	protected void sendMessages(final Player player, final int initialLifePoints, final Item item, String[] messages) {
		if (messages.length == 0) {
			sendDefaultMessages(player, item);
			sendHealingMessage(player, initialLifePoints);
		} else {
			sendCustomMessages(player, messages);
		}
	}

	protected void sendHealingMessage(final Player player, final int initialLifePoints) {
		if (player.getSkills().getLifepoints() > initialLifePoints) {
			player.getPacketDispatch().sendMessage("It heals some health.");
		}
	}

	protected void sendCustomMessages(final Player player, final String[] messages) {
		for (String message : messages) {
			player.getPacketDispatch().sendMessage(message);
		}
	}

	protected abstract void sendDefaultMessages(final Player player, final Item item);

	protected abstract void executeConsumptionActions(Player player);

	protected int getNextItemId(final int currentConsumableId) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == currentConsumableId && i != ids.length - 1) {
				return ids[i + 1];
			}
		}
		return -1;
	}

	public String getFormattedName(Item item) {
		return item.getName().replace("(4)", "").replace("(3)", "").replace("(2)", "").replace("(1)", "").trim().toLowerCase();
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Consumables.add(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	public int getHealthEffectValue(Player player) {
		return effect.getHealthEffectValue(player);
	}

	public int[] getIds() {
		return ids;
	}
}
