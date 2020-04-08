/*
package plugin.consumable.food;

import org.crandor.game.content.global.consumable.ConsumableProperties;
import org.crandor.game.content.global.consumable.CookingProperties;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.object.GameObject;

*/
/**
 * Represents the bread food that is based in it's own class because of the
 * conditions related to Tutorial Island.
 * @author 'Vexia
 * @version 1.0
 *//*

@InitializablePlugin
public final class CakePlugin extends Food {

	*/
/**
	 * Represents the cooking properties of bread.
	 *//*

	private static final CakeProperties PROPERTIES = new CakeProperties();

	*/
/**
	 * Represents the cake tin item.
	 *//*

	private static final Item CAKE_TIN = new Item(1887);

	*/
/**
	 * Constructs a new {@code Bread} {@code Object}.
	 *//*

	public CakePlugin() {
		super(1891, 1889, 1903, new ConsumableProperties(4, 1893), PROPERTIES);
	}

	*/
/**
	 * Represents the cake properties.
	 * @author 'Vexia
	 * @date 22/12/2013
	 *//*

	public static class CakeProperties extends CookingProperties {

		*/
/**
		 * Constructs a new {@code Bread} {@code Object}.
		 *//*

		public CakeProperties() {
			super(40, 180, 95, true, "You successfully bake a cake.", CookingProperties.FAIL_MESSAGE);
		}

		@Override
		public boolean cook(final Food food, final Player player, final GameObject object) {
			if (!player.getInventory().add(CAKE_TIN)) {
				GroundItemManager.create(CAKE_TIN, player);
			}
			return super.cook(food, player, object);
		}
	}
}
*/
