package plugin.skill.cooking;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.tools.ItemNames;

//author: Ceik
@InitializablePlugin
public class CookingRewrite extends UseWithHandler {
    // fires/ranges
    public static final int[] OBJECTS = new int[] { 21302, 13528, 13529, 13533, 13531, 13536, 13539, 13542, 2728, 2729, 2730, 2731, 2732, 2859, 3038, 3039, 3769, 3775, 4265, 4266, 5249, 5499, 5631, 5632, 5981, 9682, 10433, 11404, 11405, 11406, 12102, 12796, 13337, 13881, 14169, 14919, 15156, 20000, 20001, 21620, 21792, 22713, 22714, 23046, 24283, 24284, 25155, 25156, 25465, 25730, 27297, 29139, 30017, 32099, 33500, 34495, 34546, 36973, 37597, 37629, 37726, 114, 4172, 5275, 8750, 16893, 22154, 34410, 34565, 114, 9085, 9086, 9087, 12269, 15398, 25440, 25441, 2724, 2725, 2726, 4618, 4650, 5165, 6093, 6094, 6095, 6096, 8712, 9439, 9440, 9441, 10824, 17640, 17641, 17642, 17643, 18039, 21795, 24285, 24329, 27251, 33498, 35449, 36815, 36816, 37426, 40110 };

    // raw foods
    public CookingRewrite() {
        super(2140, 1861, 3228, 7521, 3151, 325, 319, 347, 355, 333, 339, 351, 329, 3381, 361, 10136, 5003, 379, 365, 373, 2149, 7946, 385, 397, 391, 3369, 3371, 3373, 1893, 1895, 1897, 1899, 1901, 1963, 2102, 2120, 2108, 5972, 5504, 1982, 1965, 1957, 5988, 1781, 6701, 6703, 6705, 7056, 7058, 7060, 1933, 1783, 1927, 1929, 6032, 6034, 2011, 227, 1921, 1937, 7934, 7086, 4239, 7068, 7086, 2003, 7078, 7072, 4239, 7062, 7064, 7084, 1871, 7082, 2309, 1891, 1967, 1971, 2142, 9436, 2142, 2142, 2325, 2333, 2327, 2331, 7170, 2323, 2335, 7178, 7180, 7188, 7190, 7198, 7200, 7208, 7210, 7218, 7220, 2289, 2291, 2293, 2295, 2297, 2299, 2301, 2303, 315, 9988, 2878, 7568, 9980, 7223, 5982, 598, 4293, 10816, 2138, 2134, 3142, 2136, 1859, 3226, 7518, 3150, 327, 321, 345, 353, 335, 341, 349, 331, 3379, 359, 10138, 5001, 377, 363, 371, 2148, 7944, 383, 395, 389, 3363, 3365, 3367, 5986, 401, 1942, 4237, 2001, 7076, 1871, 7080, 2307, 1889, 2132, 2132, 2132, 2132, 2321, 2319, 7168, 2317, 7176, 7186, 7196, 7206, 7216, 2287, 317, 9986, 2876, 7566, 9984, 7224, ItemNames.RAW_OOMLIE_2337, ItemNames.WRAPPED_OOMLIE_2341, ItemNames.COOKED_OOMLIE_WRAP_2343);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        new FoodCookingDialogue().init();
        for (int object : OBJECTS) {
            addHandler(object, OBJECT_TYPE, this);
        }
        return this;
    }

    @Override
    public boolean handle(NodeUsageEvent event) {
        int item = event.getUsedItem().getId();
        Player player = event.getPlayer();
        GameObject object = (GameObject) event.getUsedWith();
        boolean range = object.getName().toLowerCase().contains("range");

        //range checks when necessary
        switch(item){
            case 2132: //sinew
                if(range) {
                    player.getDialogueInterpreter().open(FoodCookingDialogue.DialogueID, item, 9436, true, object);
                    return true;
                }
                break;
            case 401: //soda ash
                if(range) {
                    player.getDialogueInterpreter().open(FoodCookingDialogue.DialogueID, item, 1781, false, object);
                    return true;
                }
                break;
            case 2307:
            case 1891: //cake, bread
                if(!range){
                    player.getPacketDispatch().sendMessage("You need to cook this on a range.");
                    return false;
                }
                break;
        }

        //cook a standard item
        player.getDialogueInterpreter().open(FoodCookingDialogue.DialogueID,item,event.getUsedWith());
        return true;
    }

    public static void cook(Player player, GameObject object, int initial, int product, int amount){
        Item food = new Item(initial);
        if(food.getName().toLowerCase().contains("pizza")){
            player.getPulseManager().run(new PizzaCookingPulse(player,object,initial,product,amount));
        } else if (food.getName().toLowerCase().contains("pie")){
            player.getPulseManager().run(new PieCookingPulse(player,object,initial,product,amount));
        } else if(CookableItems.intentionalBurn(initial)) {
            player.getPulseManager().run(new IntentionalBurnPulse(player, object, initial, product, amount));
        } else {
            player.getPulseManager().run(new StandardCookingPulse(player, object, initial, product, amount));
        }
    }
}
