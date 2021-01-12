package plugin.activity.fishingtrawler

import core.game.node.item.Item
import core.game.node.item.WeightedChanceItem
import core.tools.Items
import core.tools.RandomFunction

/**
 * Rolls/stores the loot table for fishing trawler
 * @author Ceikry
 */
object TrawlerLoot {
    val junkItems = arrayOf(Items.BROKEN_ARMOUR_698, Items.BROKEN_ARROW_687, Items.OLD_BOOT_685, Items.BROKEN_GLASS_1469, Items.BROKEN_STAFF_689, Items.BUTTONS_688,Items.DAMAGED_ARMOUR_697, Items.RUSTY_SWORD_686, Items.EMPTY_POT_1931, Items.OYSTER_407)
    @JvmStatic
    fun getLoot(rolls: Int, skipJunk: Boolean): ArrayList<Item>{
        val loot = ArrayList<Item>()
        for(i in 0 until rolls){
            loot.add(RandomFunction.rollWeightedChanceTable(listOf(*lootTable)))
        }
        if(skipJunk){
            val removeList = ArrayList<Item>()
            for(item in loot){
                if(junkItems.contains(item.id)) removeList.add(item)
            }
            loot.removeAll(removeList)
        }
        return loot
    }

    val lootTable = arrayOf(
            WeightedChanceItem(Items.RAW_SHRIMPS_317,1,150),
            WeightedChanceItem(Items.RAW_SARDINE_327,1,150),
            WeightedChanceItem(Items.RAW_ANCHOVIES_321,1,150),
            WeightedChanceItem(Items.RAW_TUNA_359,1,140),
            WeightedChanceItem(Items.RAW_LOBSTER_377,1,140),
            WeightedChanceItem(Items.RAW_SWORDFISH_371,1,130),
            WeightedChanceItem(Items.RAW_SHARK_383,1,120),
            WeightedChanceItem(Items.RAW_SEA_TURTLE_395,1,120),
            WeightedChanceItem(Items.RAW_MANTA_RAY_389,1,120),
            WeightedChanceItem(Items.BROKEN_ARROW_687,1,70),
            WeightedChanceItem(Items.BROKEN_GLASS_1469,1,70),
            WeightedChanceItem(Items.BROKEN_STAFF_689,1,70),
            WeightedChanceItem(Items.BUTTONS_688,1,80),
            WeightedChanceItem(Items.DAMAGED_ARMOUR_697,1,70),
            WeightedChanceItem(Items.OLD_BOOT_685,1,60),
            WeightedChanceItem(Items.OYSTER_407,1,50),
            WeightedChanceItem(Items.EMPTY_POT_1931,1,50),
            WeightedChanceItem(Items.RUSTY_SWORD_686,1,50),
            //Inauthentic rewards
            WeightedChanceItem(Items.LOOP_HALF_OF_A_KEY_987,1,40),
            WeightedChanceItem(Items.TOOTH_HALF_OF_A_KEY_985,1,40),
            WeightedChanceItem(Items.CASKET_405,1,60),
            WeightedChanceItem(Items.PIRATES_HAT_2651,1,2),
            WeightedChanceItem(Items.LUCKY_CUTLASS_7140,1,2)
    )
}