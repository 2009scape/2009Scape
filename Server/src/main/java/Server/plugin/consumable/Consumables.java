package plugin.consumable;

import core.game.node.entity.state.EntityState;
import core.game.world.update.flag.context.Animation;
import plugin.consumable.effects.*;
import plugin.skill.Skills;

import java.util.HashMap;

/**
 * Represents a repository of active consumables in the framework.
 */
public enum Consumables {

	/** Meats */
	COOKED_MEAT(new Food(new int[] {2142}, new HealingEffect(3))),
	SHRIMPS(new Food(new int[] {315}, new HealingEffect(3))),
	COOKED_CHICKEN(new Food(new int[] {2140}, new HealingEffect(3))),
	COOKED_RABBIT(new Food(new int[] {3228}, new HealingEffect(5))),
	ANCHOVIES(new Food(new int[] {319}, new HealingEffect(1))),
	SARDINE(new Food(new int[] {325}, new HealingEffect(4))),
	POISON_KARAMBWAN(new Food(new int[] {3146}, new PoisonKarambwanEffect())),
	UGTHANKI_MEAT(new Food(new int[] {1861}, new HealingEffect(3))),
	HERRING(new Food(new int[] {347}, new HealingEffect(5))),
	MACKEREL(new Food(new int[] {355}, new HealingEffect(6))),
	ROAST_BIRD_MEAT(new Food(new int[] {9980}, new HealingEffect(6))),
	THIN_SNAIL(new Food(new int[] {3369}, new HealingEffect(5))),
	TROUT(new Food(new int[] {333}, new HealingEffect(7))),
	SPIDER_ON_STICK(new Food(new int[] {6297, 6305}, new HealingEffect(7))),
	SPIDER_ON_SHAFT(new Food(new int[] {6299}, new HealingEffect(7))),
	ROAST_RABBIT(new Food(new int[] {7223}, new HealingEffect(7))),
	LEAN_SNAIL(new Food(new int[] {3371}, new HealingEffect(8))),
	COD(new Food(new int[] {339}, new HealingEffect(7))),
	PIKE(new Food(new int[] {351}, new HealingEffect(8))),
	ROAST_BEAST_MEAT(new Food(new int[] {9988}, new HealingEffect(8))),
	COOKED_CRAB_MEAT(new Food(new int[] {7521, 7523, 7524, 7525, 7526}, new HealingEffect(2))),
	FAT_SNAIL(new Food(new int[] {3373}, new HealingEffect(9))),
	SALMON(new Food(new int[] {329}, new HealingEffect(9))),
	SLIMY_EEL(new Food(new int[] {3381}, new HealingEffect(6))),
	TUNA(new Food(new int[] {361}, new HealingEffect(10))),
	COOKED_KARAMBWAN(new Food(new int[] {3144}, new HealingEffect(18))),
	COOKED_CHOMPY(new Food(new int[] {2878}, new HealingEffect(10))),
	RAINBOW_FISH(new Food(new int[] {10136}, new HealingEffect(11))),
	CAVE_EEL(new Food(new int[] {5003}, new HealingEffect(7))),
	LOBSTER(new Food(new int[] {379}, new HealingEffect(12))),
	COOKED_JUBBLY(new Food(new int[] {7568}, new HealingEffect(15))),
	BASS(new Food(new int[] {365}, new HealingEffect(13))),
	SWORDFISH(new Food(new int[] {373}, new HealingEffect(14))),
	LAVA_EEL(new Food(new int[] {2149}, new HealingEffect(14))),
	MONKFISH(new Food(new int[] {7946}, new HealingEffect(16))),
	SHARK(new Food(new int[] {385}, new HealingEffect(20))),
	SEA_TURTLE(new Food(new int[] {397}, new HealingEffect(21))),
	MANTA_RAY(new Food(new int[] {391}, new HealingEffect(22))),
	KARAMBWANJI(new Food(new int[] {3151}, new HealingEffect(3))),
	STUFFED_SNAKE(new Food(new int[] {7579}, new HealingEffect(20), "You eat the stuffed snake-it's quite a meal! It tastes like chicken.")),

	/** Breads */
	BREAD(new Food(new int[] {2309}, new HealingEffect(5))),
	BAGUETTE(new Food(new int[] {6961}, new HealingEffect(6))),

	/** Sandwiches */
	TRIANGLE_SANDWICH(new Food(new int[] {6962}, new HealingEffect(6))),
	SQUARE_SANDWICH(new Food(new int[] {6965}, new HealingEffect(6))),
	SEAWEED_SANDWICH(new FakeConsumable(3168, new String[] {"You really, really do not want to eat that."})),

	/** Kebabs */
	UGTHANKI_KEBAB(new Food(new int[] {1883}, new UgthankiKebabEffect())),
	UGTHANKI_KEBAB_SMELLING(new Food(new int[] {1885}, new SmellingUgthankiKebabEffect())),
	KEBAB(new Food(new int[] {1971}, new KebabEffect())),
	SUPER_KEBAB(new Food(new int[] {4608}, new SuperKebabEffect())),

	/** Pies */
	REDBERRY_PIE(new HalfableFood(new int[] {2325, 2333, 2313}, new HealingEffect(5))),
	MEAT_PIE(new HalfableFood(new int[] {2327, 2331, 2313}, new HealingEffect(6))),
	APPLE_PIE(new HalfableFood(new int[] {2323, 2335, 2313}, new HealingEffect(7))),
	GARDEN_PIE(new HalfableFood(new int[] {7178, 7180, 2313}, new MultiEffect(new HealingEffect(6), new SkillEffect(Skills.FARMING, 3, 0)))),
	FISH_PIE(new HalfableFood(new int[] {7188, 7190, 2313}, new MultiEffect(new HealingEffect(6), new SkillEffect(Skills.FISHING, 3, 0)))),
	ADMIRAL_PIE(new HalfableFood(new int[] {7198, 7200, 2313}, new MultiEffect(new HealingEffect(8), new SkillEffect(Skills.FISHING, 5, 0)))),
	WILD_PIE(new HalfableFood(new int[] {7208, 7210, 2313}, new MultiEffect(new SkillEffect(Skills.SLAYER, 5, 0), new SkillEffect(Skills.RANGE, 4, 0), new HealingEffect(11)))),
	SUMMER_PIE(new HalfableFood(new int[] {7218, 7220, 2313}, new MultiEffect(new HealingEffect(11), new SkillEffect(Skills.AGILITY, 5, 0), new EnergyEffect(10)))),

	/** Stews */
	STEW(new Food(new int[] {1923, 2003}, new HealingEffect(11))),
	SPICY_STEW(new Food(new int[] {7479, 2003}, new HealingEffect(11))),
	CURRY(new Food(new int[] {2011, 2003}, new HealingEffect(19))),

	/** Pizzas */
	PLAIN_PIZZA(new HalfableFood(new int[] {2289, 2291}, new HealingEffect(7))),
	MEAT_PIZZA(new HalfableFood(new int[] {2293, 2295}, new HealingEffect(8))),
	ANCHOVY_PIZZA(new HalfableFood(new int[] {2297, 2299}, new HealingEffect(9))),
	PINEAPPLE_PIZZA(new HalfableFood(new int[] {2301, 2303}, new HealingEffect(11))),

	/** Cakes */
	CAKE(new Cake(new int[] {1891, 1893, 1895}, new HealingEffect(4), "You eat part of the cake.", "You eat some more cake.", "You eat the slice of cake.")),
	CHOCOLATE_CAKE(new Cake(new int[] {1897, 1899, 1901}, new HealingEffect(5), "You eat part of the chocolate cake.", "You eat some more of the chocolate cake.", "You eat the slice of cake.")),
	ROCK_CAKE(new Food(new int[] {2379}, new RockCakeEffect(), "The rock cake resists all attempts to eat it.")),
	DWARVEN_ROCK_CAKE(new Food(new int[] {7510, 7510}, new DwarvenRockCakeEffect())),
	HOT_DWARVEN_ROCK_CAKE(new Food(new int[] {7509, 7509}, new DwarvenRockCakeEffect())),
	COOKED_FISHCAKE(new Food(new int[] {7530}, new HealingEffect(11))),
	MINT_CAKE(new Food(new int[] {9475}, new EnergyEffect(50))),

	/** Vegetables */
	BAKED_POTATO(new Food(new int[] {6701}, new HealingEffect(2))),
	SPICY_SAUCE(new Food(new int[] {7072, 1923}, new HealingEffect(2))),
	CHILLI_CON_CARNE(new Food(new int[] {7062, 1923}, new HealingEffect(5))),
	SCRAMBLED_EGG(new Food(new int[] {7078, 1923}, new HealingEffect(5))),
	EGG_AND_TOMATO(new Food(new int[] {7064, 1923}, new HealingEffect(8))),
	SWEET_CORN(new Food(new int[] {5988}, new MultiEffect(new HealingEffect(1), new PercentageHealthEffect(10)))),
	SWEETCORN_BOWL(new Food(new int[] {7088, 1923}, new MultiEffect(new HealingEffect(1), new PercentageHealthEffect(10)))),
	POTATO_WITH_BUTTER(new Food(new int[] {6703}, new HealingEffect(7))),
	CHILLI_POTATO(new Food(new int[] {7054}, new HealingEffect(14))),
	FRIED_ONIONS(new Food(new int[] {7084, 1923}, new HealingEffect(5))),
	FRIED_MUSHROOMS(new Food(new int[] {7082, 1923}, new HealingEffect(5))),
	POTATO_WITH_CHEESE(new Food(new int[] {6705}, new HealingEffect(9))),
	EGG_POTATO(new Food(new int[] {7056}, new HealingEffect(11))),
	MUSHROOMS_AND_ONIONS(new Food(new int[] {7066, 1923}, new HealingEffect(11))),
	MUSHROOM_POTATO(new Food(new int[] {7058, 1923}, new HealingEffect(20))),
	TUNA_AND_CORN(new Food(new int[] {7068, 1923}, new HealingEffect(13))),
	TUNA_POTATO(new Food(new int[] {7060}, new HealingEffect(22))),
	ONION(new Food(new int[] {1957}, new HealingEffect(2), "It's always sad to see a grown man/woman cry.")),
	CABBAGE(new Food(new int[] {1965}, new HealingEffect(2), "You eat the cabbage. Yuck!")),
	DRAYNOR_CABBAGE(new Food(new int[] {1967}, new DraynorCabbageEffect(), "You eat the cabbage.", "It seems to taste nicer than normal.")),
	EVIL_TURNIP(new Food(new int[] {12134, 12136, 12138}, new HealingEffect(6))),
	SPINACH_ROLL(new Food(new int[] {1969}, new HealingEffect(2))),

	/** Dairies */
	POT_OF_CREAM(new Food(new int[] {2130}, new HealingEffect(1))),
	CHEESE(new Food(new int[] {1985}, new HealingEffect(2))),
	CHOCOLATEY_MILK(new Drink(new int[] {1977, 1925}, new HealingEffect(4))),

	/** Fruits */
	BANANA(new Food(new int[] {1963}, new HealingEffect(2))),
	SLICED_BANANA(new Food(new int[] {3162}, new HealingEffect(2))),
	RED_BANANA(new Food(new int[] {7572}, new HealingEffect(5), "You eat the red banana. It's tastier than your average banana.")),
	SLICED_RED_BANANA(new Food(new int[] {7574}, new HealingEffect(5), "You eat the sliced red banana. Yum.")),
	ORANGE(new Food(new int[] {2108}, new HealingEffect(2))),
	ORANGE_CHUNKS(new Food(new int[] {2110}, new HealingEffect(2))),
	ORANGE_SLICES(new Food(new int[] {2112}, new HealingEffect(2))),
	PAPAYA_FRUIT(new Food(new int[] {5972}, new HealingEffect(2))),
	TENTI_PINEAPPLE(new FakeConsumable(1851, new String[] {"Try using a knife to slice it into pieces."})),
	PINEAPPLE(new FakeConsumable(2114, new String[] {"Try using a knife to slice it into pieces."})),
	PINEAPPLE_CHUNKS(new Food(new int[] {2116}, new HealingEffect(2))),
	PINEAPPLE_RING(new Food(new int[] {2118}, new HealingEffect(2))),
	DWELLBERRIES(new Food(new int[] {2126}, new HealingEffect(2))),
	JANGERBERRIES(new Food(new int[] {247}, new MultiEffect(new SkillEffect(Skills.ATTACK, 2, 0), new SkillEffect(Skills.STRENGTH, 1, 0), new PrayerEffect(1, 0), new SkillEffect(Skills.DEFENCE, -1, 0)))),
	STRAWBERRY(new Food(new int[] {5504}, new MultiEffect(new HealingEffect(1), new PercentageHealthEffect(6)))),
	TOMATO(new Food(new int[] {1982}, new HealingEffect(2))),
	WATERMELON(new FakeConsumable(5982, new String[] {"Try using a knife to slice it into pieces."})),
	WATERMELON_SLICE(new Food(new int[] {5984}, new PercentageHealthEffect(5))),
	LEMON(new Food(new int[] {2102}, new HealingEffect(2))),
	LEMON_CHUNKS(new Food(new int[] {2104}, new HealingEffect(2))),
	LEMON_SLICES(new Food(new int[] {2106}, new HealingEffect(2))),
	LIME(new Food(new int[] {2120}, new HealingEffect(2))),
	LIME_CHUNKS(new Food(new int[] {2122}, new HealingEffect(2))),
	LIME_SLICES(new Food(new int[] {2124}, new HealingEffect(2))),
	PEACH(new Food(new int[] {6883}, new HealingEffect(8))),
	WHITE_TREE_FRUIT(new Food(new int[] {6469}, new MultiEffect(new RandomEnergyEffect(5, 10), new HealingEffect(3)))),
	STRANGE_FRUIT(new Food(new int[] {464}, new MultiEffect(new RemoveStateEffect(EntityState.POISONED.ordinal()), new EnergyEffect(30)))),

	/** Gnome Cooking */
	TOAD_CRUNCHIES(new Food(new int[] {2217}, new HealingEffect(12))),
	PREMADE_TD_CRUNCH(new Food(new int[] {2243}, new HealingEffect(12))),
	SPICY_CRUNCHIES(new Food(new int[] {2213}, new HealingEffect(7))),
	PREMADE_SY_CRUNCH(new Food(new int[] {2241}, new HealingEffect(7))),
	WORM_CRUNCHIES(new Food(new int[] {2205}, new HealingEffect(8))),
	PREMADE_WM_CRUNC(new Food(new int[] {2237}, new HealingEffect(8))),
	CHOCCHIP_CRUNCHIES(new Food(new int[] {2209}, new HealingEffect(7))),
	PREMADE_CH_CRUNCH(new Food(new int[] {2239}, new HealingEffect(7))),
	FRUIT_BATTA(new Food(new int[] {2277}, new HealingEffect(11))),
	PREMADE_FRT_BATTA(new Food(new int[] {2225}, new HealingEffect(11))),
	TOAD_BATTA(new Food(new int[] {2255}, new HealingEffect(11))),
	PREMADE_TD_BATTA(new Food(new int[] {2221}, new HealingEffect(11))),
	WORM_BATTA(new Food(new int[] {2253}, new HealingEffect(11))),
	PREMADE_WM_BATTA(new Food(new int[] {2219}, new HealingEffect(11))),
	VEGETABLE_BATTA(new Food(new int[] {2281}, new HealingEffect(11))),
	PREMADE_VEG_BATTA(new Food(new int[] {2227}, new HealingEffect(11))),
	CHEESE_AND_TOMATOES_BATTA(new Food(new int[] {2259}, new HealingEffect(11))),
	PREMADE_CT_BATTA(new Food(new int[] {2223}, new HealingEffect(11))),
	WORM_HOLE(new Food(new int[] {2191}, new HealingEffect(12))),
	PREMADE_WORM_HOLE(new Food(new int[] {2233}, new HealingEffect(12))),
	VEG_BALL(new Food(new int[] {2195}, new HealingEffect(12))),
	PREMADE_VEG_BALL(new Food(new int[] {2235}, new HealingEffect(12))),
	TANGLED_TOADS_LEGS(new Food(new int[] {2187}, new HealingEffect(15))),
	PREMADE_TTL(new Food(new int[] {2231}, new HealingEffect(15))),
	CHOCOLATE_BOMB(new Food(new int[] {2195}, new HealingEffect(15))),
	PREMADE_CHOC_BOMB(new Food(new int[] {2229}, new HealingEffect(15))),

	/** Ales */
	ASGOLDIAN_ALE(new FakeConsumable(7508, new String[] {"I don't think I'd like gold in beer thanks. Leave it for the dwarves."})),
	ASGARNIAN_ALE(new Drink(new int[] {1905, 1919}, new MultiEffect(new HealingEffect(2), new SkillEffect(Skills.STRENGTH, 2, 0), new SkillEffect(Skills.ATTACK, -4, 0)), "You drink the ale. You feel slightly reinvigorated...", "...and slightly dizzy too.")),
	ASGARNIAN_ALE_KEG(new Drink(new int[] {5785, 5783, 5781, 5779, 5769}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 2, 0), new SkillEffect(Skills.ATTACK, -4, 0)), new Animation(2289), "You drink the ale. You feel slightly reinvigorated...", "...and slightly dizzy too.")),
	ASGARNIAN_ALE_M(new Drink(new int[] {5739, 1919}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.ATTACK, -6, 0)))),
	ASGARNIAN_ALE_M_KEG(new Drink(new int[] {5865, 5863, 5861, 5859, 5769}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.ATTACK, -6, 0)), new Animation(2289))),
	AXEMANS_FOLLY(new Drink(new int[] {5751, 1919}, new MultiEffect(new SkillEffect(Skills.WOODCUTTING, 1, 0), new HealingEffect(1), new SkillEffect(Skills.STRENGTH, -3, 0), new SkillEffect(Skills.ATTACK, -3, 0)))),
	AXEMANS_FOLLY_KEG(new Drink(new int[] {5825, 5823, 5821, 5819, 5769}, new MultiEffect(new SkillEffect(Skills.WOODCUTTING, 1, 0), new HealingEffect(1), new SkillEffect(Skills.STRENGTH, -3, 0), new SkillEffect(Skills.ATTACK, -3, 0)), new Animation(2289))),
	AXEMANS_FOLLY_M(new Drink(new int[] {5753, 1919}, new MultiEffect(new SkillEffect(Skills.WOODCUTTING, 2, 0), new HealingEffect(2), new SkillEffect(Skills.STRENGTH, -4, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	AXEMANS_FOLLY_M_KEG(new Drink(new int[] {5905, 5903, 5901, 5899, 5769}, new MultiEffect(new SkillEffect(Skills.WOODCUTTING, 2, 0), new HealingEffect(2), new SkillEffect(Skills.STRENGTH, -4, 0), new SkillEffect(Skills.ATTACK, -4, 0)), new Animation(2289))),
	BANDITS_BREW(new Drink(new int[] {4627, 1919}, new MultiEffect(new SkillEffect(Skills.THIEVING, 1, 0), new SkillEffect(Skills.ATTACK, 1, 0), new SkillEffect(Skills.STRENGTH, -1, 0), new SkillEffect(Skills.DEFENCE, -6, 0), new HealingEffect(1)), "You drink the beer. You feel slightly reinvigorated...", "...and slightly dizzy too.")),
	BEER(new Drink(new int[] {1917, 1919}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.STRENGTH, 0, 0.04), new SkillEffect(Skills.ATTACK, 0, -0.07)), "You drink the beer. You feel slightly reinvigorated...", "...and slightly dizzy too.")),
	BEER_TANKARD(new Drink(new int[] {3803, 3805}, new MultiEffect(new SkillEffect(Skills.ATTACK, -9, 0), new SkillEffect(Skills.STRENGTH, 4, 0)), "You quaff the beer. You feel slightly reinvigorated...", "...but very dizzy too.")),
	KEG_OF_BEER(new Drink(new int[] {3801}, new KegOfBeerEffect(), new Animation(1330), "You chug the keg. You feel reinvigorated...", "...but extremely drunk too.")),
	CHEFS_DELIGHT(new Drink(new int[] {5755, 1919}, new MultiEffect(new SkillEffect(Skills.COOKING, 1, 0.05), new HealingEffect(1), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)))),
	CHEFS_DELIGHT_KEG(new Drink(new int[] {5833, 5831, 5829, 5827, 5769}, new MultiEffect(new SkillEffect(Skills.COOKING, 1, 0.05), new HealingEffect(1), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)), new Animation(2289))),
	CHEFS_DELIGHT_M(new Drink(new int[] {5757, 1919}, new MultiEffect(new SkillEffect(Skills.COOKING, 2, 0.05), new HealingEffect(2), new SkillEffect(Skills.ATTACK, -3, 0), new SkillEffect(Skills.STRENGTH, -3, 0)))),
	CHEFS_DELIGHT_M_KEG(new Drink(new int[] {5913, 5911, 5909, 5907, 5769}, new MultiEffect(new SkillEffect(Skills.COOKING, 2, 0.05), new HealingEffect(2), new SkillEffect(Skills.ATTACK, -3, 0), new SkillEffect(Skills.STRENGTH, -3, 0)), new Animation(2289))),
	CIDER(new Drink(new int[] {5763, 1919}, new MultiEffect(new MultiEffect(new HealingEffect(2), new SkillEffect(Skills.FARMING, 1, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0))))),
	CIDER_KEG(new Drink(new int[] {5849, 5847, 5845, 5843, 5769}, new MultiEffect(new MultiEffect(new HealingEffect(2), new SkillEffect(Skills.FARMING, 1, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0))), new Animation(2289))),
	MATURE_CIDER(new Drink(new int[] {5765, 1919}, new MultiEffect(new MultiEffect(new HealingEffect(2), new SkillEffect(Skills.FARMING, 2, 0), new SkillEffect(Skills.ATTACK, -5, 0), new SkillEffect(Skills.STRENGTH, -5, 0))))),
	CIDER_M_KEG(new Drink(new int[] {5929, 5927, 5925, 5923, 5769}, new MultiEffect(new MultiEffect(new HealingEffect(2), new SkillEffect(Skills.FARMING, 2, 0), new SkillEffect(Skills.ATTACK, -5, 0), new SkillEffect(Skills.STRENGTH, -5, 0))), new Animation(2289))),
	DRAGON_BITTER(new Drink(new int[] {1911, 1919}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.STRENGTH, 2, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	DRAGON_BITTER_KEG(new Drink(new int[] {5809, 5807, 5805, 5803, 5769}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.STRENGTH, 2, 0), new SkillEffect(Skills.ATTACK, -4, 0)), new Animation(2289))),
	DRAGON_BITTER_M(new Drink(new int[] {5745, 1919}, new MultiEffect(new HealingEffect(2), new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.ATTACK, -6, 0)))),
	DRAGON_BITTER_M_KEG(new Drink(new int[] {5889, 5887, 5885, 5883, 5769}, new MultiEffect(new HealingEffect(2), new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.ATTACK, -6, 0)), new Animation(2289))),
	DWARVEN_STOUT(new Drink(new int[] {1913, 1919}, new MultiEffect(new SkillEffect(Skills.MINING, 1, 0), new SkillEffect(Skills.SMITHING, 1 ,0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0), new SkillEffect(Skills.DEFENCE, -2, 0), new HealingEffect(1)), "You drink the Dwarven Stout. It tastes foul.", "It tastes pretty strong too.")),
	DWARVEN_STOUT_KEG(new Drink(new int[] {5777, 5775, 5773, 5771, 5769}, new MultiEffect(new SkillEffect(Skills.MINING, 1, 0), new SkillEffect(Skills.SMITHING, 1 ,0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0), new SkillEffect(Skills.DEFENCE, -2, 0), new HealingEffect(1)), new Animation(2289), "You drink the Dwarven Stout. It tastes foul.", "It tastes pretty strong too.")),
	DWARVEN_STOUT_M(new Drink(new int[] {5747, 1919}, new MultiEffect(new SkillEffect(Skills.MINING, 2, 0), new SkillEffect(Skills.SMITHING, 2 ,0), new SkillEffect(Skills.ATTACK, -7, 0), new SkillEffect(Skills.STRENGTH, -7, 0), new SkillEffect(Skills.DEFENCE, -7, 0), new HealingEffect(1)))),
	DWARVEN_STOUT_M_KEG(new Drink(new int[] {5857, 5855, 5853, 5851, 5769}, new MultiEffect(new SkillEffect(Skills.MINING, 2, 0), new SkillEffect(Skills.SMITHING, 2 ,0), new SkillEffect(Skills.ATTACK, -7, 0), new SkillEffect(Skills.STRENGTH, -7, 0), new SkillEffect(Skills.DEFENCE, -7, 0), new HealingEffect(1)), new Animation(2289))),
	GREENMANS_ALE(new Drink(new int[] {1909, 1919}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.HERBLORE, 1, 0), new SkillEffect(Skills.ATTACK, -3, 0), new SkillEffect(Skills.STRENGTH, -3, 0), new SkillEffect(Skills.DEFENCE, -3, 0)))),
	GREENMANS_ALE_KEG(new Drink(new int[] {5793, 5791, 5789, 5787, 5769}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.HERBLORE, 1, 0), new SkillEffect(Skills.ATTACK, -3, 0), new SkillEffect(Skills.STRENGTH, -3, 0), new SkillEffect(Skills.DEFENCE, -3, 0)), new Animation(2289))),
	GREENMANS_ALE_M(new Drink(new int[] {5743, 1919}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.HERBLORE, 2, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)))),
	GREENMANS_ALE_M_KEG(new Drink(new int[] {5873, 5871, 5869, 5867, 5769}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.HERBLORE, 2, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)), new Animation(2289))),
	GROG(new Drink(new int[] {1915, 1919}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.ATTACK, -6, 0)))),
	MOONLIGHT_MEAD(new Drink(new int[] {2955, 1919}, new HealingEffect(4), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_KEG(new Drink(new int[] {5817, 5815, 5813, 5811, 5769}, new HealingEffect(4), new Animation(2289),  "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_M(new Drink(new int[] {5749, 1919}, new HealingEffect(6))),
	MOONLIGHT_MEAD_M_KEG(new Drink(new int[] {5897, 5895, 5893, 5891, 5769}, new HealingEffect(6), new Animation(2289))),
	SLAYERS_RESPITE(new Drink(new int[] {5759, 1919}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.SLAYER, 2, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)))),
	SLAYERS_RESPITE_KEG(new Drink(new int[] {5841, 5839, 5837, 5835, 5769}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.SLAYER, 2, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)), new Animation(2289))),
	SLAYERS_RESPITE_M(new Drink(new int[] {5761, 1919}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.SLAYER, 4, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)))),
	SLAYERS_RESPITE_M_KEG(new Drink(new int[] {5841, 5839, 5837, 5835, 5769}, new MultiEffect(new HealingEffect(1), new SkillEffect(Skills.SLAYER, 4, 0), new SkillEffect(Skills.ATTACK, -2, 0), new SkillEffect(Skills.STRENGTH, -2, 0)), new Animation(2289))),
	WIZARDS_MIND_BOMB(new Drink(new int[] {1907, 1919}, new WizardsMindBombEffect(), "You drink the Wizard's Mind Bomb.", "You feel very strange.")),
	MATURE_WMB(new Drink(new int[] {5741, 1919}, new MatureWmbEffect())),

	/** Cocktails */
	FRUIT_BLAST(new Drink(new int[] {2084, 2026}, new HealingEffect(9))),
	PREMADE_FR_BLAST(new Food(new int[] {2034, 2026}, new HealingEffect(9))),
	PINEAPPLE_PUNCH(new Drink(new int[] {2048, 2026}, new HealingEffect(9), "You drink the cocktail. It tastes great.")),
	PREMADE_P_PUNCH(new Drink(new int[] {2036, 2026}, new HealingEffect(9), "You drink the cocktail. It tastes great.")),
	WIZARD_BLIZZARD(new Drink(new int[] {2054, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 6, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	PREMADE_WIZ_BLZD(new Drink(new int[] {2040, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 6, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	SHORT_GREEN_GUY(new Drink(new int[] {2080, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 4, 0), new SkillEffect(Skills.ATTACK, -3, 0)))),
	PREMADE_SGG(new Drink(new int[] {2038, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 4, 0), new SkillEffect(Skills.ATTACK, -3, 0)))),
	DRUNK_DRAGON(new Drink(new int[] {2092, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 7, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	PREMADE_DR_DRAGON(new Drink(new int[] {2032, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 7, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	CHOC_SATURDAY(new Drink(new int[] {2074, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 7, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	PREMADE_CHOC_SDY(new Drink(new int[] {2030, 2026}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 7, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	BLURBERRY_SPECIAL(new Drink(new int[] {2064, 2026}, new MultiEffect(new HealingEffect(6), new SkillEffect(Skills.STRENGTH, 6, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	PREMADE_BLURB_SP(new Drink(new int[] {2028, 2026}, new MultiEffect(new HealingEffect(6), new SkillEffect(Skills.STRENGTH, 6, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),

	/** Bottled Drinks */
	KARAMJAN_RUM(new Drink(new int[] {431}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 5, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	BRAINDEATH_RUM(new Drink(new int[] {7157}, new MultiEffect(new SkillEffect(Skills.DEFENCE, 0, -0.1), new SkillEffect(Skills.ATTACK, 0, -0.05), new SkillEffect(Skills.PRAYER, 0, -0.05), new SkillEffect(Skills.RANGE, 0, -0.05), new SkillEffect(Skills.MAGIC, 0, -0.05), new SkillEffect(Skills.HERBLORE, 0, -0.05), new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.MINING, 1, 0)), "With a sense of impending doom you drink the 'rum'. You try very hard not to die.")),
	RUM_TROUBLE_BREWING_RED(new Drink(new int[] {8940, 8940}, new TroubleBrewingRumEffect("Oh gods! It tastes like burning!"), new Animation(9605))),
	RUM_TROUBLE_BREWING_BLUE(new Drink(new int[] {8941, 8941}, new TroubleBrewingRumEffect("My Liver! My Liver is melting!"), new Animation(9604))),
	VODKA(new Drink(new int[] {2015}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.ATTACK, -3, 0)))),
	GIN(new Drink(new int[] {2019}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 1, 0), new SkillEffect(Skills.ATTACK, 4, 0), new RandomHealthEffect(3, 4)))),
	BRANDY(new Drink(new int[] {2021}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.ATTACK, 4, 0)))),
	WHISKY(new Drink(new int[] {2017}, new MultiEffect(new HealingEffect(5), new SkillEffect(Skills.STRENGTH, 3, 0), new SkillEffect(Skills.ATTACK, -4, 0)))),
	BOTTLE_OF_WINE(new Drink(new int[] {2015, 7921}, new MultiEffect(new HealingEffect(14), new SkillEffect(Skills.ATTACK, -3, 0)))),

	/** Wine */
	JUG_OF_WINE(new Drink(new int[] {1993, 1935}, new MultiEffect(new HealingEffect(11), new SkillEffect(Skills.ATTACK, -2, 0)))),
	HALF_FULL_WINE_JUG(new Drink(new int[] {1989, 1935}, new HealingEffect(7))),

	/** Tea */
	CUP_OF_TEA(new Drink(new int[] {712, 1980}, new MultiEffect(new HealingEffect(3), new SkillEffect(Skills.ATTACK, 3, 0)), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_NETTLE(new Drink(new int[] {4242, 1980}, new EnergyEffect(10))),
	CUP_OF_TEA_MILKY_NETTLE(new Drink(new int[] {4243, 1980}, new EnergyEffect(10))),
	NETTLE_TEA(new Drink(new int[] {4239, 1980}, new NettleTeaEffect())),
	NETTLE_TEA_MILKY(new Drink(new int[] {4240, 1980}, new NettleTeaEffect())),
	CUP_OF_TEA_CLAY(new Drink(new int[] {7730, 7728}, new SkillEffect(Skills.CONSTRUCTION, 1, 0), "You feel refreshed and ready for more building.")),
	CUP_OF_TEA_CLAY_MILKY(new Drink(new int[] {7731, 7728}, new SkillEffect(Skills.CONSTRUCTION, 1, 0))),
	CUP_OF_TEA_WHITE(new Drink(new int[] {7733, 7732}, new SkillEffect(Skills.CONSTRUCTION, 2, 0), "You feel refreshed and ready for more building.")),
	CUP_OF_TEA_WHITE_MILKY(new Drink(new int[] {7734, 7732}, new SkillEffect(Skills.CONSTRUCTION, 2, 0))),
	CUP_OF_TEA_GOLD(new Drink(new int[] {7736, 7735}, new SkillEffect(Skills.CONSTRUCTION, 3, 0), "You feel refreshed and ready for more building.")),
	CUP_OF_TEA_GOLD_MILKY(new Drink(new int[] {7737, 7735}, new SkillEffect(Skills.CONSTRUCTION, 3, 0))),

	/** Miscellaneous */
	CHOCOLATE_BAR(new Food(new int[] {1973}, new HealingEffect(3))),
	PURPLE_SWEETS(new Food(new int[] {4561}, new HealingEffect(0))),
	PURPLE_SWEETS_STACKABLE(new Food(new int[] {10476, 10476}, new MultiEffect(new EnergyEffect(10), new RandomHealthEffect(1, 3)), "The sugary goodness heals some energy.", "The sugary goodness is yummy.")),
	FIELD_RATION(new Food(new int[] {7934}, new HealingEffect(10))),
	ROLL(new Food(new int[] {6963}, new HealingEffect(6))),
	TCHIKI_MONKEY_NUTS(new Food(new int[] {7573}, new HealingEffect(5), "You eat the Tchiki monkey nuts. They taste nutty.")),
	TCHIKI_MONKEY_PASTE(new Food(new int[] {7575}, new HealingEffect(5), "You eat the Tchiki monkey nut paste. It sticks to the roof of your mouth.")),

	/** Special Events */
	PUMPKIN(new Food(new int[] {1959}, new HealingEffect(14))),
	EASTER_EGG(new Food(new int[] {1961}, new HealingEffect(14))),

	/** Potions */
	STRENGTH(new Potion(new int[] {113, 115, 117, 119}, new SkillEffect(Skills.STRENGTH, 3, 0.1))),
	ATTACK(new Potion(new int[] {2428, 121, 123, 125}, new SkillEffect(Skills.ATTACK, 3, 0.1))),
	DEFENCE(new Potion(new int[] {2432, 133, 135, 137}, new SkillEffect(Skills.DEFENCE, 3, 0.1))),
	RANGING(new Potion(new int[] {2444, 169, 171, 173}, new SkillEffect(Skills.RANGE, 3, 0.1))),
	MAGIC(new Potion(new int[] {3040, 3042, 3044, 3046}, new SkillEffect(Skills.MAGIC, 3, 0.1))),
	SUPER_STRENGTH(new Potion(new int[] {2440, 157, 159, 161}, new SkillEffect(Skills.STRENGTH, 3, 0.2))),
	SUPER_ATTACK(new Potion(new int[] {2436, 145, 147, 149}, new SkillEffect(Skills.ATTACK, 3, 0.2))),
	SUPER_DEFENCE(new Potion(new int[] {2442, 163, 165, 167}, new SkillEffect(Skills.DEFENCE, 3, 0.2))),
	ANTIPOISON(new Potion(new int[] {2446, 175, 177, 179}, new MultiEffect(new SetAttributeEffect("poison:immunity", 143), new RemoveStateEffect(EntityState.POISONED.ordinal())))),
	ANTIPOISON_(new Potion(new int[] {5943, 5945, 5947, 5949}, new MultiEffect(new SetAttributeEffect("poison:immunity", 863), new RemoveStateEffect(EntityState.POISONED.ordinal())))),
	ANTIPOISON__(new Potion(new int[] {5952, 5954, 5956, 5958}, new MultiEffect(new SetAttributeEffect("poison:immunity", 2000), new RemoveStateEffect(EntityState.POISONED.ordinal())))),
	SUPER_ANTIP(new Potion(new int[] {2448, 181, 183, 185}, new MultiEffect(new SetAttributeEffect("poison:immunity", 1000), new RemoveStateEffect(EntityState.POISONED.ordinal())))),
	RELICYM(new Potion(new int[] {4842, 4844, 4846, 4848}, new MultiEffect(new SetAttributeEffect("disease:immunity", 300), new RemoveStateEffect(EntityState.DISEASED.ordinal())))),
	AGILITY(new Potion(new int[] {3032, 3034, 3036, 3038}, new SkillEffect(Skills.AGILITY, 3, 0))),
	HUNTER(new Potion(new int[] {9998, 10000, 10002, 10004}, new SkillEffect(Skills.HUNTER, 3, 0))),
	RESTORE(new Potion(new int[] {2430, 127, 129, 131}, new RestoreEffect(10, 0.3))),
	SARA_BREW(new Potion(new int[] {6685, 6687, 6689, 6691}, new MultiEffect(new SkillEffect(Skills.HITPOINTS, 0, 0.15), new SkillEffect(Skills.ATTACK, 0, -0.10), new SkillEffect(Skills.STRENGTH, 0, -0.10), new SkillEffect(Skills.MAGIC, 0, -0.10), new SkillEffect(Skills.RANGE, 0, -0.10), new SkillEffect(Skills.DEFENCE, 2, 0.2)))),
	SUMMONING(new Potion(new int[] {12140, 12142, 12144, 12146}, new SkillEffect(Skills.SUMMONING, 7, 0.25))),
	COMBAT(new Potion(new int[] {9739, 9741, 9743, 9745}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 3, .1), new SkillEffect(Skills.ATTACK, 3, .1)))),
	ENERGY(new Potion(new int[] {3008, 3010, 3012, 3014}, new MultiEffect(new EnergyEffect(10), new HealingEffect(3)))),
	FISHING(new Potion(new int[] {2438, 151, 153, 155}, new SkillEffect(Skills.FISHING, 3, 0))),
	PRAYER(new Potion(new int[] {2434, 139, 141, 143}, new PrayerEffect(7, 0.25))),
	SUPER_RESTO(new Potion(new int[] {3024, 3026, 3028, 3030}, new MultiEffect(new RestoreEffect(8, 0.25), new PrayerEffect(8, 0.25)))),
	ZAMMY_BREW(new Potion(new int[] {2450, 189, 191, 193}, new MultiEffect(new DamageEffect(0.1, true), new SkillEffect(Skills.ATTACK, 0, 0.15), new SkillEffect(Skills.STRENGTH, 0, 0.25), new SkillEffect(Skills.DEFENCE, 0, -0.1), new RandomPrayerEffect(0, 10)))),
	ANTIFIRE(new Potion(new int[] {2452, 2454, 2456, 2458}, new SetAttributeEffect("fire:immune", 600, true))),
	GUTH_REST(new Potion(new int[] {4417, 4419, 4421, 4423}, new MultiEffect(new RemoveStateEffect(EntityState.POISONED.ordinal()), new EnergyEffect(5), new HealingEffect(5)))),
	MAGIC_ESS(new Potion(new int[] {11491, 11489}, new SkillEffect(Skills.MAGIC,3,0))),
	SANFEW(new Potion(new int[] {10925, 10927, 10929, 10931}, new MultiEffect(new RestoreEffect(8,0.25), new PrayerEffect(8,0.25), new RemoveStateEffect(EntityState.POISONED.ordinal()), new RemoveStateEffect(EntityState.DISEASED.ordinal())))),
	SUPER_ENERGY(new Potion(new int[] {3016, 3018, 3020, 3022}, new EnergyEffect(-1))),

	/** Barbarian Mixes */
	PRAYERMIX(new BarbarianMix(new int[] {11467, 11465}, new MultiEffect(new PrayerEffect(7, 0.25), new HealingEffect(6)))),
	ZAMMY_MIX(new BarbarianMix(new int[] {11523, 11521}, new MultiEffect(new SkillEffect(Skills.HITPOINTS, 0, -0.10), new SkillEffect(Skills.ATTACK, 0, 0.15), new SkillEffect(Skills.STRENGTH, 0, 0.25), new SkillEffect(Skills.DEFENCE, 0, -0.1), new RandomPrayerEffect(0, 10)))),
	ATT_MIX(new BarbarianMix(new int[] {11431, 11429}, new MultiEffect(new SkillEffect(Skills.ATTACK, 3, 0.1), new HealingEffect(3)))),
	ANTIP_MIX(new BarbarianMix(new int[] {11435, 11433}, new MultiEffect(new RemoveStateEffect(EntityState.POISONED.ordinal()), new SetAttributeEffect("poison:immunity", 143), new HealingEffect(3)))),
	RELIC_MIX(new BarbarianMix(new int[] {11439, 11437}, new MultiEffect(new RemoveStateEffect(EntityState.DISEASED.ordinal()), new SetAttributeEffect("disease:immunity", 300), new HealingEffect(3)))),
	STR_MIX(new BarbarianMix(new int[] {11443, 11441}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 3, 0.1), new HealingEffect(3)))),
	RESTO_MIX(new BarbarianMix(new int[] {11449, 11451}, new MultiEffect(new RestoreEffect(10, 0.3), new HealingEffect(3)))),
	ENERGY_MIX(new BarbarianMix(new int[] {11453, 11455}, new MultiEffect(new EnergyEffect(10), new HealingEffect(6)))),
	DEF_MIX(new BarbarianMix(new int[] {11457, 11459}, new MultiEffect(new SkillEffect(Skills.DEFENCE, 3, 0.1), new HealingEffect(6)))),
	AGIL_MIX(new BarbarianMix(new int[] {11461, 11463}, new MultiEffect(new SkillEffect(Skills.AGILITY, 3, 0), new HealingEffect(6)))),
	COMBAT_MIX(new BarbarianMix(new int[] {11445, 11447}, new MultiEffect(new SkillEffect(Skills.ATTACK, 3, 0.1), new SkillEffect(Skills.STRENGTH, 3, 0.1), new HealingEffect(6)))),
	SUPER_ATT_MIX(new BarbarianMix(new int[] {11469, 11471}, new MultiEffect(new SkillEffect(Skills.ATTACK, 5, 0.15), new HealingEffect(6)))),
	FISH_MIX(new BarbarianMix(new int[] {11477, 11479}, new MultiEffect(new SkillEffect(Skills.FISHING, 3, 0), new HealingEffect(6)))),
	SUPER_ENERGY_MIX(new BarbarianMix(new int[] {11481, 11483}, new MultiEffect(new EnergyEffect(20), new HealingEffect(6)))),
	HUNTING_MIX(new BarbarianMix(new int[] {11517, 11519}, new MultiEffect(new SkillEffect(Skills.HUNTER, 3, 0), new HealingEffect(6)))),
	SUPER_STR_MIX(new BarbarianMix(new int[] {11485, 11487}, new MultiEffect(new SkillEffect(Skills.STRENGTH, 5, 0.15), new HealingEffect(6))));

	public static HashMap<Integer,Consumable> consumables = new HashMap<>();

	private final Consumable consumable;

	Consumables(Consumable consumable) {
		this.consumable = consumable;
	}

	public Consumable getConsumable() {
		return consumable;
	}

	public static Consumable getConsumableById(final int itemId) {
		return consumables.get(itemId);
	}

	public static void add(final Consumable consumable) {
		for (int id : consumable.getIds()) {
			consumables.putIfAbsent(id, consumable);
		}
	}

	/*
	  Static modifier used to populate search engine lists.
	 */
	static {
		for (Consumables consumable : Consumables.values()) {
			add(consumable.consumable);
		}
	}
}