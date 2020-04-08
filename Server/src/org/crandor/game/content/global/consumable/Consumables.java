package org.crandor.game.content.global.consumable;

import org.crandor.game.node.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a repository of active consumables in the framework.
 * @author 'Vexia
 * @date 22/12/2013
 */
public enum Consumables {
	/** meats */
	CHICKEN(new Food(2140, 3)),
	UGTHANKI(new Food(1861, 2)),
	RABBIT(new Food(3228,5)),
	DARK_CRAB(new Food(14939, 22)),
	CRAB(new Food(7521, 10)),
	/** fish */
	KARAMBWANJI(new Food(3151, 3)),
	SARDINE(new Food(325, 4)),
	ANCHOVIES(new Food(319, 1)),
	HERRING(new Food(347, 5)),
	MACKEREL(new Food(355, 6)),
	TROUT(new Food(333, 7)),
	COD(new Food(339, 7)),
	PIKE(new Food(351, 8)),
	SALMON(new Food(329, 9)),
	SLIMY_EEL(new Food(3381, 6)),
	TUNA(new Food(361, 10)),
	RAINBOW_FISH(new Food(10136, 11)),
	CAVE_EEL(new Food(5003, 7)),
	LOBSTER(new Food(379, 12)),
	BASS(new Food(365, 13)),
	SWORDFISH(new Food(373, 14)),
	LAVA_EEL(new Food(2149, 14)),
	MONKFISH(new Food(7946, 16)),
	SHARK(new Food(385, 20)),
	SEA_TURTLE(new Food(397, 21)),
	MANTA_RAY(new Food(391, 22)),
	KARAMBWAN(new Food(3144, 18)),
	/** snails */
	THIN_SNAIL(new Food(3369,5)),
	LEAN_SNAIL(new Food(3371,8)),
	FAT_SNAIL(new Food(3373, 9)),
	/** cakes */
	CAKE(new Food(1891,new ConsumableProperties(4, 1893))),
	TWO_THIRD_CAKE(new Food(1893, new ConsumableProperties(4, 1895))),
	SLICE_OF_CAKE(new Food(1895, new ConsumableProperties(4))),
	CHOCOLATE_CAKE(new Food(1897, new ConsumableProperties(5, 1899))),
	TWO_THIRD_CHOCOLATE_CAKE(new Food(1899, new ConsumableProperties(5, 1901))),
	SLICE_OF_CHOCOLATE_CAKE(new Food(1901, new ConsumableProperties(5))),
	/** special */
	PUMPKIN(new Food(1959, 14)),
	EASTER_EGG(new Food(1961, 14)),
	/** fruits */
	BANANA(new Food(1963, 2)),
	LEMON(new Food(2102, 2)),
	LIME(new Food(2120, 2)),
	ORANGE(new Food(2108, 2)),
	PAPAYA_FRUIT(new Food(5972, 2)),
	STRAWBERRY(new Food(5504, 2)),
	TOMATO(new Food(1982, 2)),
	PEACH(new Food(6883, 8)),
	/** vegetables */
	CABAGE(new Food(1965, 2, "You eat the cabbage. Yuck!")),
	ONION(new Food(1957, 2, "It's always sad to see a grown man/woman cry")),
	EVIL_TURNIP(new Food(12134, new ConsumableProperties(15, 12136))),
	EVIL_TURNIP_2_3(new Food(12136, new ConsumableProperties(15, 12138))),
	EVIL_TURNIP_1_3(new Food(12138, 15)),
	/** misc */
	BREAD(new Food(2309, 5)),
	SWEET_CORN(new Food(5988, 3)),
	BAKED_POTATO(new Food(6701, 2)),
	POTATO_WITH_BUTTER(new Food(6703,7)),
	POTATO_WITH_CHEESE(new Food(6705, 9)),
	EGG_POTATO(new Food(7056,11)),
	MUSHROOM_POTATO(new Food(7058,16)),
	TUNA_POTATO(new Food(7060,22)),
	CHILLI_POTATO(new Food(7054,14)),
	FIELD_RATION(new Food(7934,12)),
	SKEWER_1(new Food(9988,2)),
	SKEWER_2(new Food(2878,2)),
	SKEWER_3(new Food(7568,2)),
	SKEWER_4(new Food(9980,2)),
	SKEWER_5(new Food(7223,2)),
	/** drinks */
	CHOCOLATEY_MILK(new Drink(1977, new ConsumableProperties(2, 1925))),
	CUP_OF_NETTLE_TEA(new Drink(4838, new ConsumableProperties(2, 1980))),
	GIN(new Drink(2019, new ConsumableProperties(2, 7921))),
	WHISKY(new Drink(2017, new ConsumableProperties(2, 7921))),
	VODKA(new Drink(2015, new ConsumableProperties(2, 7921))),
	JUG_OF_WINE(new Drink(1993, new ConsumableProperties(7, 1935))),
	KARAMJAN_RUN(new Drink(431, new ConsumableProperties(2))),
	KHALI_BREW(new Drink(77, new ConsumableProperties(2, 7921))),
	NETTLE_TEA(new Drink(4239, new ConsumableProperties(4, 1923))),
	BOTTLE_OF_WINE(new Drink(7919, new ConsumableProperties(2, 7921))),
	/** potions */
	STRENGTH_POTION(new Potion(PotionEffect.STRENGTH_POTION)),
	ATTACK_POTION(new Potion(PotionEffect.ATTACK_POTION)),
	DEFENCE_POTION(new Potion(PotionEffect.DEFENCE_POTION)),
	RANGING_POTION(new Potion(PotionEffect.RANGING_POTION)),
	MAGIC_POTION(new Potion(PotionEffect.MAGIC_POTION)),
	SUPER_STRENGTH_POTION(new Potion(PotionEffect.SUPER_STRENGTH)),
	SUPER_ATTACK_POTION(new Potion(PotionEffect.SUPER_ATTACK)),
	SUPER_DEFENCE_POTION(new Potion(PotionEffect.SUPER_DEFENCE)),
	AGILITY_POTION(new Potion(PotionEffect.AGILITY_POTION)),
	HUNTER_POTION(new Potion(PotionEffect.HUNTER_POTION));

	/**
	 * Represents the consumable.
	 */
	private Consumable consumable;

	/**
	 * Represents the list of foods only. This list can be used for direct
	 * searching.
	 */
	private static final List<Food> FOODS = new ArrayList<>();

	/**
	 * Represents te list of drinks only. This list can be used for direct
	 * searching.
	 */
	private static final List<Drink> DRINKS = new ArrayList<>();

	/**
	 * Represents the list of all consumables.
	 */
	private static final List<Consumable> CONSUMABLES = new ArrayList<>();

	/**
	 * Constructs a new {@code Consumables} {@code Object}.
	 * @param consumable the consumbale.
	 */
	Consumables(Consumable consumable) {
		this.consumable = consumable;
	}

	/**
	 * Constructs a new {@code Consumables} {@code Object}.
	 * @param consumable the consumable.
	 * @param drinkSet the drinkset.
	 */
	Consumables(Drink drink) {
		this.consumable = drink;
	}

	/**
	 * Gets the food.
	 * @return The food.
	 */
	public Consumable getConsumable() {
		return consumable;
	}

	/**
	 * Gets the list of foods.
	 * @return the foods.
	 */
	public static List<Food> getFoods() {
		return FOODS;
	}

	/**
	 * Method used to get the {@link Consumable} by the item associated with it.
	 * @param raw the raw item.
	 * @return the consumable.
	 */
	public static Consumable forConsumable(final Item item) {
		for (Consumable consumable : CONSUMABLES) {
			if (consumable.isDrink()) {
				Consumable d = forDrink(item);
				if (d != null) {
					return d;
				}
			}
			if (consumable.getItem().getId() == item.getId()) {
				return consumable;
			}
		}
		return null;
	}

	/**
	 * Method used to get the {@link Food} by the item associated with it.
	 * @note this is a more direct search.
	 * @param item the item.
	 * @return the food.
	 */
	public static Food forFood(final Item item) {
		for (Food food : FOODS) {
			if (food.getItem().getId() == item.getId()) {
				return food;
			}
		}
		return null;
	}


	/**
	 * Method used to get the {@link Drink} by the item id.
	 * @param item the item.
	 * @return the drink.
	 */
	public static Drink forDrink(final Item item) {
		for (Drink drink : DRINKS) {
			if (item.getId() == drink.getItem().getId()) {
				return drink;
			}
			if (drink.getDrinks() == null) {
				continue;
			}
			for (Item i : drink.getDrinks()) {
				if (i.getId() == item.getId()) {
					return drink;
				}
			}
		}
		return null;
	}

	/**
	 * Method used to add a consumable to its search engine.
	 * @param consumable the consumable.
	 */
	public static void add(final Consumable consumable) {
		if (consumable.isDrink()) {
			DRINKS.add(consumable.asDrink());
		} else {
			FOODS.add(consumable.asFood());
		}
		CONSUMABLES.add(consumable);
	}

	/**
	 * Static modifier used to populate search engine lists.
	 */
	static {
		for (Consumables consumable : Consumables.values()) {
			if (consumable.getConsumable().isFood()) {
				FOODS.add(consumable.getConsumable().asFood());
			} else if (consumable.getConsumable().isDrink()) {
				DRINKS.add(consumable.getConsumable().asDrink());
			}
			CONSUMABLES.add(consumable.getConsumable());
		}
	}
}