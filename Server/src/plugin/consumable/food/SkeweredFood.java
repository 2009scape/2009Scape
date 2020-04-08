/*
package plugin.consumable.food;

import org.crandor.game.content.global.consumable.ConsumableProperties;
import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.CookingProperties;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

*/
/**
 * Represents a skewered food.
 * @author 'Vexia
 * @date 23/12/2013
 *//*

@InitializablePlugin
public class SkeweredFood extends Food {

	*/
/**
	 * Constructs a new {@code SkeweredFood} {@code Object}.
	 *//*

	public SkeweredFood() {
		*/
/**
		 * empty.
		 *//*

	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Consumables.add(new SkeweredFood(9988, 9986, 9990, new ConsumableProperties(2), new CookingProperties(21, 82, 82, false, true)));
		Consumables.add(new SkeweredFood(2878, 2876, 7226, new ConsumableProperties(2), new CookingProperties(30, 140, 140)));
		Consumables.add(new SkeweredFood(7568, 7566, 7570, new ConsumableProperties(2), new CookingProperties(41, 160, 140)));
		Consumables.add(new SkeweredFood(9980, 9984, 9982, new ConsumableProperties(2), new CookingProperties(11, 62, 62, false, true)));
		Consumables.add(new SkeweredFood(7223, 7224, 7222, new ConsumableProperties(2), new CookingProperties(16, 70, 62, false, true)));
		return this;
	}

	*/
/**
	 * Constructs a new {@code SkeweredFood} {@code Object}.
	 * @param item the item.
	 * @param raw the raw item.
	 * @param burnt the burnt item.
	 * @param foodProperties the food properties.
	 * @param cookingProperties the cooking properties.
	 *//*

	public SkeweredFood(int item, int raw, int burnt, ConsumableProperties foodProperties, CookingProperties cookingProperties) {
		super(item, raw, burnt, foodProperties, new SkeweredProperty(cookingProperties.getLevel(), cookingProperties.getExperience(), cookingProperties.getBurnLevel(), cookingProperties.isRange(), cookingProperties.isSpit()));
	}

	*/
/**
	 * Represents the skewered property.
	 * @author 'Vexia
	 * @date 23/12/2013
	 *//*

	public static class SkeweredProperty extends CookingProperties {

		*/
/**
		 * Represents the iron spit item.
		 *//*

		private static final Item IRON_SPIT = new Item(7225);

		*/
/**
		 * Constructs a new {@code SkeweredFood.java} {@code Object}.
		 * @param level the level.
		 * @param experience the experience.
		 * @param burnLevel the burn level.
		 * @param range the range.
		 * @param spit the spit.
		 * @param messages the messages.
		 *//*

		public SkeweredProperty(int level, double experience, int burnLevel, boolean range, boolean spit) {
			super(level, experience, burnLevel, range, spit, "You successfully roast the meat.", CookingProperties.FAIL_MESSAGE);
		}

		@Override
		public boolean cook(final Food food, final Player player, final GameObject object) {
			if (!super.cook(food, player, object)) {
				if (!player.getInventory().add(IRON_SPIT)) {
					GroundItemManager.create(IRON_SPIT, player.getLocation(), player);
				}
			} else {
				if (RandomFunction.random(15) == 5) {
					player.getPacketDispatch().sendMessage("Your iron spit seems to have broken in the proccess.");
				} else {
					if (!player.getInventory().add(IRON_SPIT)) {
						GroundItemManager.create(IRON_SPIT, player.getLocation(), player);
					}
				}
			}
			return true;
		}
	}
}
*/
