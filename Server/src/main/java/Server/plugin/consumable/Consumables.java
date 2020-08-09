package plugin.consumable;

import core.game.node.item.Item;

import java.util.HashMap;

/**
 * Represents a repository of active consumables in the framework.
 * @author 'Vexia
 * @date 22/12/2013
 */
public enum Consumables {

	/** Meat */
	COOKED_MEAT(new Food(2142, 3)),
	SHRIMPS(new Food(315, 3)),
	COOKED_CHICKEN(new Food(2140, 3)),
	COOKED_RABBIT(new Food(3228, 5)),
	ANCHOVIES(new Food(319, 1)),
	SARDINE(new Food(325, 4)),
	POISON_KARAMBWAN(new Food(3142, -5)),
	UGTHANKI_MEAT(new Food(1861, 3)),
	HERRING(new Food(347, 5)),
	MACKEREL(new Food(355, 6)),
	ROAST_BIRD_MEAT(new Food(9980, 6)),
	THIN_SNAIL(new Food(3369, 5)),
	TROUT(new Food(333, 7)),
	SPIDER_ON_STICK(new Food(6297, new ConsumableProperties(7, 6305))),
	SPIDER_ON_SHAFT(new Food(6299, 7)),
	ROAST_RABBIT(new Food(7223, 7)),
	LEAN_SNAIL(new Food(3371, 8)),
	COD(new Food(339, 7)),
	PIKE(new Food(351, 8)),
	ROAST_BEAST_MEAT(new Food(9988, 8)),
	COOKED_CRAB_MEAT_5(new Food(7521, new ConsumableProperties(2, 7523))),
	COOKED_CRAB_MEAT_4(new Food(7523, new ConsumableProperties(2, 7524))),
	COOKED_CRAB_MEAT_3(new Food(7524, new ConsumableProperties(2, 7525))),
	COOKED_CRAB_MEAT_2(new Food(7525, new ConsumableProperties(2, 7526))),
	COOKED_CRAB_MEAT_1(new Food(7526, 2)),
	FAT_SNAIL(new Food(3373, 9)),
	SALMON(new Food(329, 9)),
	SLIMY_EEL(new Food(3381, 6)),
	TUNA(new Food(361, 10)),
	COOKED_KARAMBWAN(new Food(3144, 18)),
	COOKED_CHOMPY(new Food(2878, 10)),
	COOKED_FISHCAKE(new Food(7530, 11)),
	RAINBOW_FISH(new Food(10136, 11)),
	CAVE_EEL(new Food(5003, 7)),
	LOBSTER(new Food(379, 12)),
	COOKED_JUBBLY(new Food(7568, 15)),
	BASS(new Food(365, 13)),
	SWORDFISH(new Food(373, 14)),
	LAVA_EEL(new Food(2149, 14)),
	MONKFISH(new Food(7946, 16)),
	SHARK(new Food(385, 20)),
	SEA_TURTLE(new Food(397, 21)),
	MANTA_RAY(new Food(391, 22)),
	KARAMBWANJI(new Food(3151, 3)),

	/** Bread */
	BREAD(new Food(2309, 5)),

	/** Pies */
	REDBERRY_PIE(new Food(2325, new ConsumableProperties(5, 2333))),
	HALF_A_REDBERRY_PIE(new Food(2333, new ConsumableProperties(5, 2313))),
	MEAT_PIE(new Food(2327, new ConsumableProperties(6, 2331))),
	HALF_A_MEAT_PIE(new Food(2331, new ConsumableProperties(6, 2313))),
	APPLE_PIE(new Food(2323, new ConsumableProperties(7, 2335))),
	HALF_AN_APPLE_PIE(new Food(2335, new ConsumableProperties(7, 2313))),
	GARDEN_PIE(new Food(7178, new ConsumableProperties(6, 7180))),
	HALF_A_GARDEN_PIE(new Food(7180, new ConsumableProperties(6, 2313))),
	FISH_PIE(new Food(7188, new ConsumableProperties(6, 7190))),
	HALF_A_FISH_PIE(new Food(7190, new ConsumableProperties(6, 2313))),
	BOTANICAL_PIE(new Food(19662, new ConsumableProperties(6, 19659))),
	HALF_A_BOTANICAL_PIE(new Food(19659, new ConsumableProperties(6, 2313))),
	ADMIRAL_PIE(new Food(7198, new ConsumableProperties(8, 7200))),
	HALF_AN_ADMIRAL_PIE(new Food(7200, new ConsumableProperties(8, 2313))),
	WILD_PIE(new Food(7208, new ConsumableProperties(11, 7210))),
	HALF_A_WILD_PIE(new Food(7210, new ConsumableProperties(11, 2313))),
	SUMMER_PIE(new Food(7218, new ConsumableProperties(11, 7220))),
	HALF_A_SUMMER_PIE(new Food(7220, new ConsumableProperties(11, 2313))),

	/** Stews */
	STEW(new Food(1923, new ConsumableProperties(11, 2003))),
	SPICY_STEW(new Food(7479, new ConsumableProperties(11, 2003))),
	CURRY(new Food(2011, new ConsumableProperties(19, 2003))),

	/** Pizzas */
	PLAIN_PIZZA(new Food(2289, new ConsumableProperties(7, 2291))),
	HALF_A_PLAIN_PIZZA(new Food(2291, 7)),
	MEAT_PIZZA(new Food(2293, new ConsumableProperties(8, 2295))),
	HALF_A_MEAT_PIZZA(new Food(2295, 8)),
	ANCHOVY_PIZZA(new Food(2297, new ConsumableProperties(9, 2299))),
	HALF_A_ANCHOVY_PIZZA(new Food(2299, 9)),
	PINEAPPLE_PIZZA(new Food(2301, new ConsumableProperties(11, 2303))),
	HALF_A_PINEAPPLE_PIZZA(new Food(2303, 11)),

	/** Cakes */
	CAKE(new Food(1891, new ConsumableProperties(4, 1893))),
	TWO_THIRDS_OF_A_CAKE(new Food(1893, new ConsumableProperties(4, 1895))),
	SLICE_OF_CAKE(new Food(1895, 4)),
	CHOCOLATE_CAKE(new Food(1897, new ConsumableProperties(5, 1899))),
	TWO_THIRDS_OF_A_CHOCOLATE_CAKE(new Food(1899, new ConsumableProperties(5, 1901))),
	SLICE_OF_CHOCOLATE_CAKE(new Food(1901, 5)),
	ROCK_CAKE(new Food(2379, -5, "Ow! I nearly broke a tooth!")),
	MINT_CAKE(new Food(9475, 0)),

	/** Vegetable */
	BAKED_POTATO(new Food(6701, 2)),
	SPICY_SAUCE(new Food(7072, new ConsumableProperties(2, 1923))),
	CHILLI_CON_CARNE(new Food(7062, new ConsumableProperties(5, 1923))),
	SCRAMBLED_EGG(new Food(7062, new ConsumableProperties(5, 1923))),
	EGG_AND_TOMATO(new Food(7064, new ConsumableProperties(8, 1923))),
	SWEET_CORN(new Food(5988, 10)),
	SWEETCORN_BOWL(new Food(7088, new ConsumableProperties(10, 1923))),
	POTATO_WITH_BUTTER(new Food(6703, 7)),
	CHILLI_POTATO(new Food(7054, 14)),
	FRIED_ONIONS(new Food(7084, new ConsumableProperties(5, 1023))),
	FRIED_MUSHROOMS(new Food(7082, new ConsumableProperties(5, 1023))),
	POTATO_WITH_CHEESE(new Food(6705, 9)),
	EGG_POTATO(new Food(7056, 11)),
	MUSHROOMS_AND_ONIONS(new Food(7066, new ConsumableProperties(11, 1923))),
	MUSHROOM_POTATO(new Food(7058, new ConsumableProperties(20, 1923))),
	TUNA_AND_CORN(new Food(7068, new ConsumableProperties(13, 1923))),
	TUNA_POTATO(new Food(7060, 22)),
	ONION(new Food(1957, 2, "It's always sad to see a grown man/woman cry")),
	CABBAGE(new Food(1965, 2, "You eat the cabbage. Yuck!")),
	EVIL_TURNIP(new Food(12134, new ConsumableProperties(15, 12136))),
	TWO_THIRD_OF_AN_EVIL_TURNIP(new Food(12136, new ConsumableProperties(15, 12138))),
	ONE_THIRD_OF_AN_EVIL_TURNIP(new Food(12138, 15)),

	/** Dairy */
	POT_OF_CREAM(new Food(2130, 0)),
	CHEESE(new Food(1985, 2)),
	CHOCOLATEY_MILK(new Drink(1977, new ConsumableProperties(4, 1925))),

	/** Fruits */
	BANANA(new Food(1963, 2)),
	SLICED_BANANA(new Food(3162, 2)),
	RED_BANANA(new Food(7572, 5, "You eat the red banana. It's tastier than your average banana." )),
	SLICED_RED_BANANA(new Food(7574, 5, "You eat the sliced red banana. Yum.")),
	ORANGE(new Food(2108, 2)),
	ORANGE_CHUNKS(new Food(2110, 2)),
	ORANGE_SLICES(new Food(2112, 2)),
	PAPAYA_FRUIT(new Food(5972, 2)),
	PINEAPPLE_CHUNKS(new Food(2116, 2)),
	PINEAPPLE_RING(new Food(2118, 2)),
	DWELLBERRIES(new Food(2126, 0)),
	JANGERBERRIES(new Food(247, 0)),
	STRAWBERRY(new Food(5504, 2)),
	TOMATO(new Food(1982, 2)),
	WATERMELON_SLICE(new Food(5984, 0)),
	LEMON(new Food(2102, 2)),
	LEMON_CHUNKS(new Food(2104, 2)),
	LEMON_SLICES(new Food(2106, 2)),
	LIME(new Food(2120, 2)),
	LIME_CHUNKS(new Food(2122, 2)),
	LIME_SLICES(new Food(2124, 2)),
	PEACH(new Food(6883, 8)),
	WHITE_TREE_FRUIT(new Food(6469, 3)),
	STRANGE_FRUIT(new Food(464, 0)),

	/** Gnome Cooking */
	TOAD_CRUNCHIES(new Food(2217, 8)),
	PREMADE_TD_CRUNCH(new Food(2243, 8)),
	SPICY_CRUNCHIES(new Food(2213, 7)),
	PREMADE_SY_CRUNCH(new Food(2241, 7)),
	WORM_CRUNCHIES(new Food(2205, 8)),
	PREMADE_WM_CRUNC(new Food(2237, 8)),
	CHOCCHIP_CRUNCHIES(new Food(2209, 7)),
	PREMADE_CH_CRUNCH(new Food(2239, 7)),
	FRUIT_BATTA(new Food(2277, 11)),
	PREMADE_FRT_BATTA(new Food(2225, 11)),
	TOAD_BATTA(new Food(2255, 11)),
	PREMADE_TD_BATTA(new Food(2221, 11)),
	WORM_BATTA(new Food(2253, 11)),
	PREMADE_WM_BATTA(new Food(2219, 11)),
	VEGETABLE_BATTA(new Food(2281, 11)),
	PREMADE_VEG_BATTA(new Food(2227, 11)),
	CHEESE_AND_TOMATOES_BATTA(new Food(2259, 11)),
	PREMADE_CT_BATTA(new Food(2223, 11)),
	WORM_HOLE(new Food(2191, 12)),
	PREMADE_WORM_HOLE(new Food(2233, 12)),
	VEG_BALL(new Food(2195, 12)),
	PREMADE_VEG_BALL(new Food(2235, 12)),
	TANGLED_TOADS_LEGS(new Food(2187, 15)),
	PREMADE_TTL(new Food(2231, 15)),
	CHOCOLATE_BOMB(new Food(2195, 15)),
	PREMADE_CHOC_BOMB(new Food(2229, 15)),

	/** Ale */
	ASGARNIAN_ALE(new Drink(1905, new ConsumableProperties(2, 1919))),
	ASGARNIAN_ALE_4(new Drink(5785, new ConsumableProperties(2, 5783))),
	ASGARNIAN_ALE_3(new Drink(5783, new ConsumableProperties(2, 5781))),
	ASGARNIAN_ALE_2(new Drink(5781, new ConsumableProperties(2, 5779))),
	ASGARNIAN_ALE_1(new Drink(5779, new ConsumableProperties(2, 5769))),
	ASGARNIAN_ALE_M(new Drink(5739, new ConsumableProperties(2, 1919))),
	ASGARNIAN_ALE_M4(new Drink(5865, new ConsumableProperties(2, 5863))),
	ASGARNIAN_ALE_M3(new Drink(5863, new ConsumableProperties(2, 5861))),
	ASGARNIAN_ALE_M2(new Drink(5861, new ConsumableProperties(2, 5859))),
	ASGARNIAN_ALE_M1(new Drink(5859, new ConsumableProperties(2, 5769))),

	AXEMANS_FOLLY(new Drink(5751, new ConsumableProperties(1, 1919))),
	AXEMANS_FOLLY_4(new Drink(5825, new ConsumableProperties(1, 5823))),
	AXEMANS_FOLLY_3(new Drink(5823, new ConsumableProperties(1, 5821))),
	AXEMANS_FOLLY_2(new Drink(5821, new ConsumableProperties(1, 5819))),
	AXEMANS_FOLLY_1(new Drink(5819, new ConsumableProperties(1, 5769))),
	AXEMANS_FOLLY_M(new Drink(5753, new ConsumableProperties(2, 1919))),
	AXEMANS_FOLLY_M4(new Drink(5905, new ConsumableProperties(2, 5903))),
	AXEMANS_FOLLY_M3(new Drink(5903, new ConsumableProperties(2, 5901))),
	AXEMANS_FOLLY_M2(new Drink(5901, new ConsumableProperties(2, 5899))),
	AXEMANS_FOLLY_M1(new Drink(5899, new ConsumableProperties(2, 5769))),

	BANDITS_BREW(new Drink(4627, new ConsumableProperties(1, 1919))),

	BEER(new Drink(1917, new ConsumableProperties(1, 1919))),
	BEER_TANKARD(new Drink(3803, new ConsumableProperties(0, 3805), "You quaff the beer. You feel slightly reinvigorated... but very dizzy too.")),
	KEG_OF_BEER(new Drink(3801, new ConsumableProperties(15), "You chug the keg. You feel reinvigorated... ...but extremely drunk, too.")),

	CHEFS_DELIGHT(new Drink(5755, new ConsumableProperties(1, 1919))),
	CHEFS_DELIGHT_4(new Drink(5833, new ConsumableProperties(1, 5831))),
	CHEFS_DELIGHT_3(new Drink(5831, new ConsumableProperties(1, 5829))),
	CHEFS_DELIGHT_2(new Drink(5829, new ConsumableProperties(1, 5827))),
	CHEFS_DELIGHT_1(new Drink(5827, new ConsumableProperties(1, 5769))),
	CHEFS_DELIGHT_M(new Drink(5757, new ConsumableProperties(2, 1919))),
	CHEFS_DELIGHT_M4(new Drink(5913, new ConsumableProperties(2, 5911))),
	CHEFS_DELIGHT_M3(new Drink(5911, new ConsumableProperties(2, 5909))),
	CHEFS_DELIGHT_M2(new Drink(5909, new ConsumableProperties(2, 5907))),
	CHEFS_DELIGHT_M1(new Drink(5907, new ConsumableProperties(2, 5769))),

	CIDER(new Drink(5763, new ConsumableProperties(2, 1919))),
	CIDER_4(new Drink(5849, new ConsumableProperties(2, 5847))),
	CIDER_3(new Drink(5847, new ConsumableProperties(2, 5845))),
	CIDER_2(new Drink(5845, new ConsumableProperties(2, 5843))),
	CIDER_1(new Drink(5843, new ConsumableProperties(2, 5769))),
	MATURE_CIDER(new Drink(5765, new ConsumableProperties(2, 1919))),
	CIDER_M4(new Drink(5929, new ConsumableProperties(2, 5927))),
	CIDER_M3(new Drink(5927, new ConsumableProperties(2, 5925))),
	CIDER_M2(new Drink(5925, new ConsumableProperties(2, 5923))),
	CIDER_M1(new Drink(5923, new ConsumableProperties(2, 5769))),

	DRAGON_BITTER(new Drink(1911, new ConsumableProperties(1, 1919))),
	DRAGON_BITTER_4(new Drink(5809, new ConsumableProperties(1, 5807))),
	DRAGON_BITTER_3(new Drink(5807, new ConsumableProperties(1, 5805))),
	DRAGON_BITTER_2(new Drink(5805, new ConsumableProperties(1, 5803))),
	DRAGON_BITTER_1(new Drink(5803, new ConsumableProperties(1, 5769))),
	DRAGON_BITTER_M(new Drink(5745, new ConsumableProperties(2, 1919))),
	DRAGON_BITTER_M4(new Drink(5889, new ConsumableProperties(2, 5887))),
	DRAGON_BITTER_M3(new Drink(5887, new ConsumableProperties(2, 5885))),
	DRAGON_BITTER_M2(new Drink(5885, new ConsumableProperties(2, 5883))),
	DRAGON_BITTER_M1(new Drink(5883, new ConsumableProperties(2, 5769))),

	DWARVEN_STOUT(new Drink(1913, new ConsumableProperties(1, 1919))),
	DWARVEN_STOUT_4(new Drink(5777, new ConsumableProperties(1, 5775))),
	DWARVEN_STOUT_3(new Drink(5775, new ConsumableProperties(1, 5773))),
	DWARVEN_STOUT_2(new Drink(5773, new ConsumableProperties(1, 5771))),
	DWARVEN_STOUT_1(new Drink(5771, new ConsumableProperties(1, 5769))),
	DWARVEN_STOUT_M(new Drink(5747, new ConsumableProperties(1, 1919))),
	DWARVEN_STOUT_M4(new Drink(5857, new ConsumableProperties(1, 5855))),
	DWARVEN_STOUT_M3(new Drink(5855, new ConsumableProperties(1, 5853))),
	DWARVEN_STOUT_M2(new Drink(5853, new ConsumableProperties(1, 5851))),
	DWARVEN_STOUT_M1(new Drink(5851, new ConsumableProperties(1, 5769))),

	GREENMANS_ALE(new Drink(1909, new ConsumableProperties(1, 1919))),
	GREENMANS_ALE_4(new Drink(5793, new ConsumableProperties(1, 5791))),
	GREENMANS_ALE_3(new Drink(5791, new ConsumableProperties(1, 5789))),
	GREENMANS_ALE_2(new Drink(5789, new ConsumableProperties(1, 5787))),
	GREENMANS_ALE_1(new Drink(5787, new ConsumableProperties(1, 5769))),
	GREENMANS_ALE_M(new Drink(5743, new ConsumableProperties(1, 1919))),
	GREENMANS_ALE_M4(new Drink(5873, new ConsumableProperties(1, 5871))),
	GREENMANS_ALE_M3(new Drink(5871, new ConsumableProperties(1, 5869))),
	GREENMANS_ALE_M2(new Drink(5869, new ConsumableProperties(1, 5867))),
	GREENMANS_ALE_M1(new Drink(5867, new ConsumableProperties(1, 5769))),

	GROG(new Drink(1915, new ConsumableProperties(3, 1919))),

	MOONLIGHT_MEAD(new Drink(2955, new ConsumableProperties(4, 1919), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_4(new Drink(5817, new ConsumableProperties(4, 5815), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_3(new Drink(5815, new ConsumableProperties(4, 5813), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_2(new Drink(5813, new ConsumableProperties(4, 5811), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_1(new Drink(5811, new ConsumableProperties(4, 5769), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_M(new Drink(5749, new ConsumableProperties(6, 1919), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_M4(new Drink(5897, new ConsumableProperties(6, 5895), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_M3(new Drink(5895, new ConsumableProperties(6, 5893), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_M2(new Drink(5893, new ConsumableProperties(6, 5891), "It tastes like something just died in your mouth.")),
	MOONLIGHT_MEAD_M1(new Drink(5891, new ConsumableProperties(6, 5769), "It tastes like something just died in your mouth.")),

	SLAYERS_RESPITE(new Drink(5759, new ConsumableProperties(1, 1919))),
	SLAYERS_RESPITE_4(new Drink(5841, new ConsumableProperties(1, 5839))),
	SLAYERS_RESPITE_3(new Drink(5839, new ConsumableProperties(1, 5837))),
	SLAYERS_RESPITE_2(new Drink(5837, new ConsumableProperties(1, 5835))),
	SLAYERS_RESPITE_1(new Drink(5835, new ConsumableProperties(1, 5769))),
	SLAYERS_RESPITE_M(new Drink(5761, new ConsumableProperties(1, 1919))),
	SLAYERS_RESPITE_M4(new Drink(5841, new ConsumableProperties(1, 5839))),
	SLAYERS_RESPITE_M3(new Drink(5839, new ConsumableProperties(1, 5837))),
	SLAYERS_RESPITE_M2(new Drink(5837, new ConsumableProperties(1, 5835))),
	SLAYERS_RESPITE_M1(new Drink(5835, new ConsumableProperties(1, 5769))),

	WIZARDS_MIND_BOMB(new Drink(1907, new ConsumableProperties(0, 1919))),
	MATURE_WMB(new Drink(5741, new ConsumableProperties(1, 1919))),

	/** Cocktails */
	FRUIT_BLAST(new Drink(2084, new ConsumableProperties(9, 2026))),
	PREMADE_FR_BLAST(new Food(2034, 9)),
	PINEAPPLE_PUNCH(new Drink(2048, new ConsumableProperties(9, 2026))),
	PREMADE_P_PUNCH(new Drink(2036, new ConsumableProperties(9, 2026))),
	WIZARD_BLIZZARD(new Drink(2054, new ConsumableProperties(5, 2026))),
	PREMADE_WIZ_BLZD(new Drink(2040, new ConsumableProperties(5, 2026))),
	SHORT_GREEN_GUY(new Drink(2080, new ConsumableProperties(5, 2026))),
	PREMADE_SGG(new Drink(2038, new ConsumableProperties(5, 2026))),
	DRUNK_DRAGON(new Drink(2092, new ConsumableProperties(5, 2026))),
	PREMADE_DR_DRAGON(new Drink(2032, new ConsumableProperties(5, 2026))),
	CHOC_SATURDAY(new Drink(2074, new ConsumableProperties(5, 2026))),
	PREMADE_CHOC_SDY(new Drink(2030, new ConsumableProperties(5, 2026))),
	BLURBERRY_SPECIAL(new Drink(2064, new ConsumableProperties(7, 2026))),
	PREMADE_BLURB_SP(new Drink(2028, new ConsumableProperties(7, 2026))),

	/** Bottled Drinks */
	KARAMJAN_RUM(new Drink(431, new ConsumableProperties(5))),
	BRAINDEATH_RUM(new Drink(7157, new ConsumableProperties(0))),
	RUM_TROUBLE_BREWING_RED(new Drink(8940, new ConsumableProperties(0), "Oh gods! It tastes like burning!")),
	RUM_TROUBLE_BREWING_BLUE(new Drink(8941, new ConsumableProperties(0), "My Liver! My Liver is melting!")),
	VODKA(new Drink(2015, new ConsumableProperties(5, 7921))),
	GIN(new Drink(2019, new ConsumableProperties(5, 7921))),
	BRANDY(new Drink(2021, new ConsumableProperties(5, 7921))),
	WHISKY(new Drink(2017, new ConsumableProperties(5, 7921))),
	BOTTLE_OF_WINE(new Drink(7919, new ConsumableProperties(14, 7921))),

	/** Wine */
	JUG_OF_WINE(new Drink(1993, new ConsumableProperties(11, 1935))),
	HALF_FULL_WINE_JUG(new Drink(1989, new ConsumableProperties(7, 1935))),

	/** Tea */
	CUP_OF_TEA(new Drink(712, new ConsumableProperties(3, 1980), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_NETTLE(new Drink(4242, new ConsumableProperties(3, 1980), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_MILKY_NETTLE(new Drink(4243, new ConsumableProperties(3, 1980), "Aaah, nothing like a nice cuppa tea!")),
	NETTLE_TEA(new Drink(4239, new ConsumableProperties(3, 1923), "Aaah, nothing like a nice cuppa tea!")),
	NETTLE_TEA_MILKY(new Drink(4240, new ConsumableProperties(3, 1923), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_CLAY(new Drink(7730, new ConsumableProperties(3, 7728), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_CLAY_MILKY(new Drink(7731, new ConsumableProperties(3, 7728), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_WHITE(new Drink(7733, new ConsumableProperties(3, 7732), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_WHITE_MILKY(new Drink(7734, new ConsumableProperties(3, 7732), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_GOLD(new Drink(7736, new ConsumableProperties(3, 7735), "Aaah, nothing like a nice cuppa tea!")),
	CUP_OF_TEA_GOLD_MILKY(new Drink(7737, new ConsumableProperties(3, 7735), "Aaah, nothing like a nice cuppa tea!")),
	GUTHIX_REST_4(new Drink(4417, new ConsumableProperties(5, 4419))),
	GUTHIX_REST_3(new Drink(4419, new ConsumableProperties(5, 4421))),
	GUTHIX_REST_2(new Drink(4421, new ConsumableProperties(5, 4423))),
	GUTHIX_REST_1(new Drink(4423, new ConsumableProperties(5, 1980))),

	/** Miscellaneous */
	CHOCOLATE_BAR(new Food(1973, 3)),
	PURPLE_SWEETS(new Food(4561, 3)),
	PURPLE_SWEETS_STACKABLE(new Food(10476, 3)),
	FIELD_RATION(new Food(7934,10)),

	/** Special Events */
	PUMPKIN(new Food(1959, 14)),
	EASTER_EGG(new Food(1961, 14));

	public static HashMap<Integer,Consumable> consumables = new HashMap<>();

	/**
	 * Represents the consumable.
	 */
	private Consumable consumable;

	/**
	 * Constructs a new {@code Consumables} {@code Object}.
	 * @param consumable the consumable.
	 */
	Consumables(Consumable consumable) {
		this.consumable = consumable;
	}

	/**
	 * Gets the consumable.
	 * @return the consumable.
	 */
	public Consumable getConsumable() {
		return consumable;
	}

	/**
	 * Method used to get the {@link Food} by the item ID associated with it.
	 * @note this is a more direct search.
	 * @param itemId the item ID.
	 * @return the food.
	 */
	public static Consumable getConsumableById(final int itemId) {
		return consumables.get(itemId);
	}

	/**
	 * Method used to add a consumable to its search engine.
	 * @param consumable the consumable.
	 */
	public static void add(final Consumable consumable) {
		consumables.putIfAbsent(consumable.getItem().getId(), consumable);
	}

	/**
	 * Static modifier used to populate search engine lists.
	 */
	static {
		for (Consumables consumable : Consumables.values()) {
			add(consumable.consumable);
		}
	}
}