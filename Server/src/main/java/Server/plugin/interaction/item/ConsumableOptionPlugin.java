package plugin.interaction.item;

import core.cache.def.impl.ItemDefinition;
import plugin.consumable.Consumable;
import plugin.consumable.ConsumableProperties;
import plugin.consumable.Consumables;
import plugin.consumable.Drink;
import plugin.consumable.Food;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.consumable.potion.Potions;

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
		if(Potions.forId(node.getId()) != null){
			Potions potion = Potions.forId(node.getId());
			potion.consume(player,node.getId());
			return true;
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
