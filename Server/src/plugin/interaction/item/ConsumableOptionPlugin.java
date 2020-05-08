package plugin.interaction.item;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.global.consumable.Consumable;
import org.crandor.game.content.global.consumable.ConsumableProperties;
import org.crandor.game.content.global.consumable.Consumables;
import org.crandor.game.content.global.consumable.Drink;
import org.crandor.game.content.global.consumable.Food;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to consume a consumable item.
 * @author 'Vexia
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class ConsumableOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("eat", this);
		ItemDefinition.setOptionHandler("drink", this);
		return this;
	}
	
	/**
	 * The last item that was recently eaten.
	 * Used for Karambwan "1-ticking"
	 */
	int lastEaten = -1;
	
	@Override
	public boolean handle(final Player player, final Node node, final String option) {
		if (player.getLocks().isLocked(option)) {
			return true;
		}
		boolean food = option.equals("eat");
		if(node.asItem().getId() != 3144 || (node.asItem().getId() == 3144 && lastEaten == 3144)){
			player.getLocks().lock(option, 3);
		}
		if (!food) {
			player.getLocks().lock("eat", 2);
		}
		Item item = (Item) node;
		if (player.getInventory().get(item.getSlot()) != item) {
			return false;
		}
		Consumable consumable = food ? Consumables.getFoodByItemID(item.getId()) : Consumables.getDrinkByItemID(item.getId());
		if (consumable == null) {
			consumable = food ? new Food(item.getId(), new ConsumableProperties(1)) : new Drink(item.getId(), new ConsumableProperties(1));
		}
		consumable.consume(((Item) node), player);
		if (food) {
			player.getProperties().getCombatPulse().delayNextAttack(3);
		}
		lastEaten = node.asItem().getId();
		return true;
	}
}
