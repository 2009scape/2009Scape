package plugin.consumable;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;

/**
 * Represents a dynamic consumable item.
 * @author 'Vexia
 * @date 22/12/2013
 */
public abstract class Consumable implements Plugin<Object> {

	protected final int[] ids;

	protected final ConsumableEffect effect;

	/**
	 * Represents the messages to display when consumed.
	 */
	protected final String[] messages;

	protected Animation animation = null;

	/**
	 * Constructs a new {@code Consumable} {@code Object}.
	 * @param item the item.
	 * @parma properties the properties.
	 */
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

	/**
	 * Method called when this consumables is consumed.
	 * @param player the player.
	 */
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
		} else {
			sendCustomMessages(player, messages);
		}
		sendHealingMessage(player, initialLifePoints);
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

	/**
	 * Gets the value if this consumable is a food.
	 * @return the <code>True</code> if so.
	 */
	public boolean isFood() {
		return this instanceof Food;
	}

	/**
	 * Gets the value if this consumable is a drink.
	 * @return the <code>True</code> if so.
	 */
	public boolean isDrink() {
		return this instanceof Drink;
	}

	/**
	 * Gets this consumable as a drink.
	 * @return the drink.
	 */
	public Drink asDrink() {
		return ((Drink) this);
	}

	/**
	 * Gets the consumable as a food instance.
	 * @return the food consumable.
	 */
	public Food asFood() {
		return ((Food) this);
	}

	/**
	 * Gets the formated name of the item.
	 * @param item the item.
	 * @return the name.
	 */
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
