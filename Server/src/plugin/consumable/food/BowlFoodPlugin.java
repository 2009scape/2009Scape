package plugin.consumable.food;

import org.crandor.game.content.global.consumable.ConsumableProperties;
import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to handle bowl related foods.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public class BowlFoodPlugin extends Food {

	/**
	 * Represents the empty bowl item.
	 */
	private static final Item BOWL = new Item(1923);

	/**
	 * Constructs a new {@code BowlFoodPlugin.java} {@code Object}.
	 */
	public BowlFoodPlugin() {
		/**
		 * empty.
		 */
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Consumables.add(new BowlFoodPlugin(7086, 2));
		Consumables.add(new BowlFoodPlugin(4239, new ConsumableProperties(2)));
		Consumables.add(new BowlFoodPlugin(7068, 13));
		Consumables.add(new BowlFoodPlugin(7086, 2));
		Consumables.add(new BowlFoodPlugin(2003, 5));
		Consumables.add(new BowlFoodPlugin(7078, 2));
		Consumables.add(new BowlFoodPlugin(7072, 2));
		Consumables.add(new BowlFoodPlugin(4239, 4));
		Consumables.add(new BowlFoodPlugin(7062, 2));
		Consumables.add(new BowlFoodPlugin(7064, 4));
		Consumables.add(new BowlFoodPlugin(7084, new ConsumableProperties(8, 1923)));
		Consumables.add(new BowlFoodPlugin(1871, 2));
		Consumables.add(new BowlFoodPlugin(2011, 11));
		Consumables.add(new BowlFoodPlugin(7082, new ConsumableProperties(9, 1923)));
		return this;
	}

	/**
	 * Constructs a new {@code BowlFood} {@code Object}.
	 * @param item the item.
	 * @param raw the raw item.
	 * @param burnt the burnt item.
	 * @param health the health.
	 * @param cookingProperties the cooking properties.
	 */
	public BowlFoodPlugin(final int item, int health) {
		super(item, new ConsumableProperties(health, BOWL));
	}

	public BowlFoodPlugin(final int item, final ConsumableProperties properties){
		super(item, properties);
	}
}
