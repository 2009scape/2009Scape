package org.crandor.game.content.skill.free.cooking.recipe.pizza;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.cooking.recipe.Recipe;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents the generic recipe for a pizza.
 * @author ceikry
 */
public abstract class PizzaRecipe extends Recipe {

	/**
	 * Represents the plain pizza.
	 */
	protected static final Item PLAIN_PIZZA = new Item(2289);

	/**
	 * Method used to get the experience gained from adding the final
	 * ingredient.
	 * @return the experience.
	 */
	public abstract double getExperience();

	/**
	 * Method used to get the level required.
	 * @return the level required.
	 */
	public abstract int getLevel();

	@Override
	public void mix(final Player player, final NodeUsageEvent event) {
		if (player.getSkills().getLevel(Skills.COOKING) < getLevel()) {
			player.getDialogueInterpreter().sendDialogue("You need a Cooking level of at least " + getLevel() + " in order to do this.");
			return;
		}
		super.singleMix(player, event);
		player.getSkills().addExperience(Skills.COOKING, getExperience(), true);
	}

	@Override
	public Item[] getParts() {
		return new Item[] {};
	}

	@Override
	public Item getBase() {
		return PLAIN_PIZZA;
	}

	@Override
	public String getMixMessage(final NodeUsageEvent event) {
		return "You add " + event.getBaseItem().getName().toLowerCase() + " to the pizza.";
	}

	@Override
	public boolean isSingular() {
		return true;
	}

}
