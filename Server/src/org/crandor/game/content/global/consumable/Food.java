package org.crandor.game.content.global.consumable;

import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;

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

	public Food(int food, ConsumableProperties foodProperties) {
		super(new Item(food), foodProperties);
	}

	public Food(int food, int health){
		super(new Item(food), new ConsumableProperties(health));
	}

	public Food(int food, int health, String eatMsg){
		super(new Item(food),new ConsumableProperties(health));
		this.eatMessage = eatMsg;
	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param item the item.
//	 * @param foodProperties the food properties.
//	 * @param cookingProperties the cooking properties.
//	 */
//	public Food(final Item item, final ConsumableProperties foodProperties, final CookingProperties cookingProperties) {
//		this(item, null, null, foodProperties, cookingProperties);
//	}
//
//	public Food(final int item, final int raw, final int burnt, final ConsumableProperties foodProperties){
//		this(new Item(item), new Item(raw), new Item(burn), foodProperties);
//	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param item the item id.
//	 * @param raw the raw item id.
//	 * @param burnt the burnt item id.
//	 * @param foodProperties the food properties.
//	 * @param cookingProperties the cooking properties.
//	 */
//	public Food(final int item, final int raw, final int burnt, final ConsumableProperties foodProperties, final CookingProperties cookingProperties) {
//		this(new Item(item), new Item(raw), new Item(burnt), foodProperties, cookingProperties);
//	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param foodProperties the food properties.
//	 */
//	public Food(final Item item, final Item raw, final Item burnt, ConsumableProperties foodProperties) {
//		this(item, raw, burnt, foodProperties, null);
//	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param item the item.
//	 * @param foodProperties the consumable properties.
//	 */
//	public Food(final int item, ConsumableProperties foodProperties) {
//		this(new Item(item), null, null, foodProperties);
//	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param item the item.
//	 * @param emptyItem the empty item.
//	 * @param foodProperties the consumable properties.
//	 */
//	public Food(final int item, final int emptyItem, ConsumableProperties foodProperties) {
//		this(new Item(item), null, null, foodProperties);
//		this.emptyItem = new Item(emptyItem, 1);
//	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param item the item.
//	 * @param emptyItem the empty item.
//	 * @param foodProperties the consumable properties.
//	 */
//	public Food(final int item, final int emptyItem, String emptyMessage, ConsumableProperties foodProperties) {
//		this(new Item(item), null, null, foodProperties);
//		this.emptyMessage = emptyMessage;
//		this.emptyItem = new Item(emptyItem);
//	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param item the item.
//	 * @param health the health.
//	 */
//	public Food(final int item, final int health) {
//		this(new Item(item), null, null, new ConsumableProperties(health));
//	}
//
//	/**
//	 * Constructs a new {@code Food} {@code Object}.
//	 * @param item the item.
//	 * @param health the health.
//	 */
//	public Food(final int item, final int health, String... messages) {
//		this(new Item(item), null, null, new ConsumableProperties(health));
//		this.messages = messages;
//	}

	/**
	 * Constructs a new {@code Food} {@code Object}.
	 */
	public Food() {
		/**
		 * empty.
		 */
	}

	@Override
	public void consume(final Item item, final Player player) {
		int restore = 2;
		if (getProperties() != null) {
			restore = getProperties().getHealing();
		}
		consume(item, player, restore, messages);
	}

	@Override
	public void consume(final Item item, final Player player, int heal, String... messages) {
		final int initial = player.getSkills().getLifepoints();
		remove(player, item);
		message(player, item, initial, messages == null ? this.messages : messages);
	}

	/**
	 * Gets the message when you eat.
	 * @return the message to display.
	 */
	public String getEatMessage() {
		return "You eat the " + getItem().getName().toLowerCase() + ".";
	}

}
