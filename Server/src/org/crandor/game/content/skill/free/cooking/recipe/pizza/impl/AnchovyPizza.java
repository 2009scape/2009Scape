package org.crandor.game.content.skill.free.cooking.recipe.pizza.impl;

import org.crandor.game.content.skill.free.cooking.recipe.pizza.PizzaRecipe;
import org.crandor.game.node.item.Item;

/**
 * Represents the anchovy pizza. This recipe consists of adding anchovies to a
 * plain pizza.
 * @author 'Vexia
 * @date 22/12/2013
 */
public class AnchovyPizza extends PizzaRecipe {

	/**
	 * Represents the anchovy pizza item.
	 */
	private static final Item ANCHOVY_PIZZA = new Item(2297);

	/**
	 * Represents the anchovies item.
	 */
	private static final Item ANCHOVIES = new Item(319);

	@Override
	public double getExperience() {
		return 39;
	}

	@Override
	public Item getProduct() {
		return ANCHOVY_PIZZA;
	}

	@Override
	public Item[] getIngredients() {
		return new Item[] { ANCHOVIES };
	}

	@Override
	public int getLevel() {
		return 55;
	}

}
