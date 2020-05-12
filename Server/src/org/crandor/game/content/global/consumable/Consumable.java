package org.crandor.game.content.global.consumable;

import org.crandor.game.content.skill.SkillBonus;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;

/**
 * Represents a dynamic consumable item.
 * @author 'Vexia
 * @date 22/12/2013
 */
public abstract class Consumable implements Plugin<Object> {

	/**
	 * Represents the animation when consuming.
	 */
	protected static final Animation ANIMATION = new Animation(829);

	/**
	 * Represents the empty vial item.
	 */
	protected static final Item VIAL = new Item(229);

	/**
	 * Represents the empty bucket item.
	 */
	protected static final Item BUCKET = new Item(1925);

	/**
	 * Represents the empty jug item.
	 */
	protected static final Item JUG = new Item(1935);

	/**
	 * Represents the empty bowl item.
	 */
	protected static final Item BOWL = new Item(1923);

	/**
	 * Represents the beer glass item.
	 */
	protected static final Item BEER_GLASS = new Item(1919);

	/**
	 * Represents the message used when emptying the consumable.
	 */
	protected static final String EMPTY_MESSAGE = "You empty the contents of the @name on the floor.";

	/**
	 * Represents the consumable item.
	 */
	private Item item;

	/**
	 * Represents the food properties of this food.
	 */
	private ConsumableProperties properties;

	/**
	 * Represents the item the player gets when emptying this item.
	 */
	protected Item emptyItem;

	/**
	 * Represents the message displayed when emptying the consumable.
	 */
	protected String emptyMessage;

	/**
	 * Represents the messages to display when consumed.
	 */
	protected String[] messages = null;

	/**
	 * Constructs a new {@code Consumable} {@code Object}.
	 * @param item the item.
	 * @parma properties the properties.
	 */
	public Consumable(final Item item, final ConsumableProperties properties) {
		this.item = item;
		this.properties = properties;
	}

	/**
	 * Constructs a new {@code Consumable} {@code Object}.
	 */
	public Consumable() {
		/**
		 * empty.
		 */
	}

	/**
	 * Method called when this consumables is consumed.
	 * @param player the player.
	 */
	public void consume(final Item item, final Player player) {
		consume(item, player, properties.getHealing());
	}

	/**
	 * Method called when this consumable is consumed.
	 * @note override if needed, generally for extra effects.
	 * @param player the player consuming this consumable.
	 * @param the healing amount used to override, (generally to alter amt)
	 * @param messages the messages to show.
	 */
	public void consume(final Item item, final Player player, int heal, String... messages) {

	}

	/**
	 * Method used to handle the interaction between food and a node.
	 * @note override if needed.
	 * @param player the player.
	 * @param node the node.
	 */
	public boolean interact(final Player player, final Node node) {
		return interact(player, node, "");
	}

	/**
	 * Method used to handle the interaction between food and a node.
	 * @note override if needed.
	 * @param player the player.
	 * @param node the node.
	 * @param option the option (if any)
	 */
	public boolean interact(final Player player, final Node node, String option) {
		switch (option) {
		case "empty":
			Item item = (Item) node;
			if (item.getSlot() < 0) {
				return false;
			}
			if (player.getInventory().remove(item, item.getSlot(), true)) {
				player.getPacketDispatch().sendMessage(getEmptyMessage(item));
			}
			if (getEmptyItem() != null) {
				player.getInventory().add(getEmptyItem());
				return true;
			}
			Consumable c = Consumables.getConsumableByItem(item);
			if (c != null) {
				if (c.getEmptyItem() != null) {
					player.getInventory().add(c.getEmptyItem());
				}
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to add a skill bonus to a player.
	 * @param player the player.
	 * @param b the bonus.
	 */
	public void addBonus(final Player player, final SkillBonus b) {
		int level = player.getSkills().getStaticLevel(b.getSkillId());
		level = (int) (b.getBaseBonus() + level + (level * b.getBonus()));
		if (b.getBonus() < 0) {
			player.getSkills().setLevel(b.getSkillId(), level);
			return;
		}
		if (player.getSkills().getLevel(b.getSkillId()) <= level) {
			if (b.getSkillId() == Skills.HITPOINTS) {
				if (player.getSkills().getLifepoints() > player.getSkills().getStaticLevel(Skills.HITPOINTS)) {
					return;
				}
				int difference = level - player.getSkills().getStaticLevel(b.getSkillId());
				player.getSkills().setLevel(b.getSkillId(), player.getSkills().getLifepoints() + difference);
			} else {
				player.getSkills().setLevel(b.getSkillId(), level);
			}
		}
	}

	/**
	 * Method used to remove the item and heal the player.
	 * @param player the player.
	 * @param item the item.
	 */
	public void remove(final Player player, final Item item) {
		if (getProperties() == null) {
			System.err.println("VEXIA SUCKS, PROPERTIES ARE NULL " + item.getId());
			return;
		}
		if (!removed(item, player)) {
			return;
		}
		player.animate(ANIMATION);
		player.getSkills().heal(getProperties().getHealing());
		player.getAudioManager().send(this instanceof Drink ? Drink.SOUND : Food.SOUND);
	}

	/**
	 * Checks if an item is removed.
	 * @param item t he item.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean removed(Item item, Player player) {
		if (getProperties().hasNewItem()) {
			if (player.getInventory().replace(getProperties().getNewItem(), item.getSlot()) == null) {
				;
				return false;
			}
		} else {
			if (!player.getInventory().remove(item, item.getSlot(), true)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method used to message the player.
	 * @param player the player.
	 * @param item the item.
	 * @param initial the initial hp amount.
	 */
	public void message(final Player player, final Item item, final int initial, final String... messages) {
		if (messages == null || messages.length == 0) {
			if (this instanceof Food) {
				player.getPacketDispatch().sendMessage("You eat the " + item.getName().trim().toLowerCase() + ".");
			} else if (this instanceof Drink) {
				player.getPacketDispatch().sendMessage("You drink some of " + (item.getName().contains("brew") ? "the foul liquid" : "your " + item.getName().replace("(4)", "").replace("(3)", "").replace("(2)", "").replace("(1)", "").trim().toLowerCase()) + ".");
			}
			if (player.getSkills().getLifepoints() > initial) {
				player.getPacketDispatch().sendMessage("It heals some health.");
			}
		} else {
			for (String message : messages) {
				player.getPacketDispatch().sendMessage(message);
			}
		}
	}

	/**
	 * Gets the item.
	 * @return The item.
	 */
	public Item getItem() {
		if (item != null) {
			return item;
		}
		return item;
	}

	/**
	 * Gets the properties.
	 * @return The properties.
	 */
	public ConsumableProperties getProperties() {
		return properties;
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
	public String getName(Item item) {
		return item.getName().replace("(4)", "").replace("(3)", "").replace("(2)", "").replace("(1)", "").trim().toLowerCase();
	}

	/**
	 * Gets the emptying item.
	 * @return the item.
	 */
	public Item getEmptyItem() {
		return emptyItem;
	}

	/**
	 * Gets the empty message.
	 * @return the message.
	 */
	public String getEmptyMessage(final Item item) {
		return emptyMessage == null ? EMPTY_MESSAGE.replace("@name", getName(item)) : emptyMessage;
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
}
