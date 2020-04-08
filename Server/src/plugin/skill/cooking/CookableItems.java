package plugin.skill.cooking;

import org.crandor.game.node.item.Item;

import java.util.HashMap;

public enum CookableItems {
    /** meats */
    CHICKEN(2140,2138,2144,1,30,30),
    UGTHANKI(2140,2138,2144,1,40,40),
    RABBIT(3228,3226,7222,1,30,30),
    DARK_CRAB(14939, 14937, 14941, 90, 215, 100),
    CRAB(7521, 7518, 7520,21, 100, 100),

    /** fish */
    SHRIMP(315, 317, 7954,1, 45, 35),
    KARAMBWANJI(3151, 3150, 592,1, 10, 30),
    SARDINE(325, 327, 369,1, 40, 38),
    ANCHOVIES(319, 321, 323,1, 30, 34),
    HERRING(347, 345, 357,5, 50, 41),
    MACKEREL(355, 353, 357,10, 60, 45),
    TROUT(333, 335, 343,15, 70, 50),
    COD(339, 341, 343,18, 75, 52),
    PIKE(351, 349, 343,20, 80, 53),
    SALMON(329, 331, 343,25, 58, 90),
    SLIMY_EEL(3381, 3379, 3383,28, 95, 58),
    TUNA(361, 359, 367,30, 100, 64),
    RAINBOW_FISH(10136, 10138, 10140,35, 110, 60),
    CAVE_EEL(5003, 5001, 5002,38, 115, 40),
    LOBSTER(379, 377, 381,40, 120, 74),
    BASS(365, 363, 367,43, 130, 80),
    SWORDFISH(373, 371, 375,45, 140, 86),
    LAVA_EEL(2149, 2148, 3383,53, 30, 53),
    MONKFISH(7946, 7944, 7948,62, 150, 92),
    SHARK(385, 383, 387,80, 210, 100),
    SEA_TURTLE(397, 395, 399,82, 212, 100),
    MANTA_RAY(391, 389, 393,91, 216, 100),
    KARAMBWAN(3144, 3142, 3146,1, 80, 30),

    /** snails */
    THIN_SNAIL(3369, 3363, 3375,12, 70, 70),
    LEAN_SNAIL(3371, 3365, 3375,17, 80, 80),
    FAT_SNAIL(3373, 3367, 3375,22, 95, 95),

    //Bread
    BREAD(2309, 2307, 2311,1,40,40),

    //Cake
    CAKE(1891, 1889, 1903, 40, 180, 95),

    //Beef(s) (Rat, Bear, Cow, Yak)
    BEEF_1(2142,2132,2146,1,30,30),
    BEEF_2(2142,2134,2146,1,30,30),
    BEEF_3(2142,2136,2146,1,30,30),

    //Skewered foods
    SKEWER_1(9988, 9986, 9990,21, 82, 82),
    SKEWER_2(2878, 2876, 7226,30, 140, 140),
    SKEWER_3(7568, 7566, 7570,41, 160, 140),
    SKEWER_4(9980, 9984, 9982,11, 62, 62),
    SKEWER_5(7223, 7224, 7222,16, 70, 62),

    //Pies
    PIE_1(2325, 2321, 2329, 10, 78, 60),
    PIE_2(2327, 2319, 2329, 20, 110, 80),
    PIE_3(7170, 7169, 2329, 29,128,85),
    PIE_4(2323,2317,2329,30,130,85),
    GARDEN_PIE(7178,7176,2329,34,138, 89),
    FISH_PIE(7188,7186,2329,47,164,110),
    ADMIRAL_PIE(7198,7196,2329,70,210,140),
    WILD_PIE(7208,7206,2329,85,240,140),
    SUMMER_PIE(7218,7216,2329,95,260,160),

    //Pizzas
    PIZZA_1(2289, 2287,2305, 35, 143, 86),

    //Bowl foods
    BOWL_1(2003, 2001, 2005,25, 117, 58),
    BOWL_2(4239, 4237, 4239,20, 52, 1),
    BOWL_3(7078, 7076, 7090,13, 50, 60),
    BOWL_4(7084, 1871, 7092,43, 60, 70),
    BOWL_5(7082, 7080, 7094,57, 120, 80);

    public static HashMap<Integer,CookableItems>cookingMap = new HashMap<>();
    public static HashMap<Integer, CookableItems>intentionalBurnMap = new HashMap<>();
    public int raw,cooked,level,burnLevel,burnt;
    double experience;
    CookableItems(int cooked, int raw, int burnt, int level, double experience, int burnLevel){
        this.raw = raw;
        this.cooked = cooked;
        this.burnt = burnt;
        this.level = level;
        this.experience = experience;
        this.burnLevel = burnLevel;

    }

    static{
        CookableItems[] cookableItems = values();
        int cookableItemsLength = cookableItems.length;
        for(int x = 0; x < cookableItemsLength; x++){
            CookableItems item = cookableItems[x];
            cookingMap.putIfAbsent(item.raw,item);
            intentionalBurnMap.putIfAbsent(item.cooked,item);
        }
    }

    public static CookableItems forId(int id){
        return cookingMap.get(id);
    }

    public static Item getCooked(int id){
        return new Item(cookingMap.get(id).cooked);
    }

    public static Item getBurnt(int id){
        return new Item(cookingMap.get(id).burnt);
    }

    public static Item getRaw(int id){
        return new Item(cookingMap.get(id).raw);
    }

    public static boolean intentionalBurn(int id){
        return (intentionalBurnMap.get(id) != null);
    }

    public static Item getIntentionalBurn(int id){
        return new Item(intentionalBurnMap.get(id).burnt);
    }

    public static int getBurnLevel(int id){
        return cookingMap.get(id).burnLevel;
    }
}
