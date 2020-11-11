package plugin.skill.cooking.recipe;

import plugin.skill.cooking.recipe.cake.ChocolateCake;
import plugin.skill.cooking.recipe.pie.impl.*;
import plugin.skill.cooking.recipe.pizza.impl.AnchovyPizza;
import plugin.skill.cooking.recipe.pizza.impl.MeatPizza;
import plugin.skill.cooking.recipe.pizza.impl.PineapplePizza;
import plugin.skill.cooking.recipe.pizza.impl.PlainPizza;
import plugin.skill.cooking.recipe.potato.impl.*;
import plugin.skill.cooking.recipe.stew.CurryRecipe;
import plugin.skill.cooking.recipe.stew.StewRecipe;
import plugin.skill.cooking.recipe.topping.impl.*;
import core.game.interaction.NodeUsageEvent;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents a cooking recipe, this is dynamic that can range from a pie to a
 * pizza.
 * @author 'Vexia
 * @date 21/12/2013
 */
public abstract class Recipe {

	/**
	 * Represents the array of active recipes in Keldagrim..
	 */
	public static final Recipe[] RECIPES = new Recipe[] {
			new RedberryPie(), new MeatPie(), new ApplePie(), new MudPie(), new GardenPie(), new FishPie(), new AdmiralPie(), new WildPie(), new SummerPie(),
			new StewRecipe(), new CurryRecipe(),
			new PlainPizza(), new MeatPizza(), new AnchovyPizza(), new PineapplePizza(),
			new ChocolateCake(),
			new ButterPotato(), new ChilliPotato(), new CheesePotato(), new EggPotato(), new MushroomPotato(), new TunaPotato(),
			new SpicySauce(), new ChilliConCarne(), new UncookedEgg(), new EggAndTomato(), new MushroomAndOnion(), new ChoppedOnion(), new SlicedMushroom(), new ChoppedTuna(), new TunaAndCorn(), new OomlieWrap()
	};

	/**
	 * Method used to get the base item.
	 * @return the item.
	 */
	public abstract Item getBase();

	/**
	 * Method used to get the product item.
	 * @return the product item.
	 */
	public abstract Item getProduct();

	/**
	 * Method used to get the ingredients in this recipe.
	 * @return the ingredients.
	 */
	public abstract Item[] getIngredients();

	/**
	 * Method used to get the part items made from ingredients.
	 * @return the part items.
	 */
	public abstract Item[] getParts();

	/**
	 * Method used to get the mixing message.
	 * @param event the node usage event.
	 * @return the message used to mix.
	 */
	public abstract String getMixMessage(final NodeUsageEvent event);

	/**
	 * Method used to check if this is a singular one step recipe.
	 * @return <code>True</code> if so.
	 */
	public abstract boolean isSingular();

	/**
	 * Method used to mix this recipes ingredients.
	 * @param player the player.
	 * @param event the event.
	 */
	public void mix(final Player player, final NodeUsageEvent event) {
		if (getIngredients().length == 1) {
			singleMix(player, event);
		} else {
			multipleMix(player, event);
		}
	}

	/**
	 * Method used to handle a single mixing.
	 * @param player the player.
	 * @param event the event.
	 */
	public void singleMix(final Player player, final NodeUsageEvent event) {
		if (player.getInventory().remove(event.getBaseItem()) && player.getInventory().remove(event.getUsedItem())) {
			player.getInventory().add(getProduct());
			String message = getMixMessage(event);
			if (message != null) {
				player.getPacketDispatch().sendMessage(message);
			}
		}
	}

	/**
	 * Method used to handle mixing multiple item recipes.
	 * @param player the player.
	 * @param event the event.
	 */
	public void multipleMix(final Player player, final NodeUsageEvent event) {
		Item item = null;
		int index = -1;
		for (int counter = 0; counter < getIngredients().length; counter++) {
			item = getIngredients()[counter];
			if (item.getId() == event.getUsedItem().getId() || item.getId() == event.getBaseItem().getId()) {
				index = counter;
				break;
			}
		}
		if (index != -1) {
			if (player.getInventory().remove(event.getBaseItem()) && player.getInventory().remove(event.getUsedItem())) {
				player.getInventory().add(getParts()[index + 1]);
				String message = getMixMessage(event);
				if (message != null) {
					player.getPacketDispatch().sendMessage(message);
				}
			}
		}
	}
}
