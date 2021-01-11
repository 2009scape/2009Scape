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
    @JvmStatic
    fun getLoot(rolls: Int): ArrayList<Item>{
        val loot = ArrayList<Item>()
        for(i in 0 until rolls){
            loot.add(RandomFunction.rollWeightedChanceTable(listOf(*lootTable)))
        }
        return loot
    }

    val lootTable = arrayOf(
            WeightedChanceItem(Items.RAW_SHRIMPS_317,1,15),
            WeightedChanceItem(Items.RAW_SARDINE_327,1,15),
            WeightedChanceItem(Items.RAW_ANCHOVIES_321,1,15),
            WeightedChanceItem(Items.RAW_TUNA_359,1,14),
            WeightedChanceItem(Items.RAW_LOBSTER_377,1,14),
            WeightedChanceItem(Items.RAW_SWORDFISH_371,1,13),
            WeightedChanceItem(Items.RAW_SHARK_383,1,12),
            WeightedChanceItem(Items.RAW_SEA_TURTLE_395,1,12),
            WeightedChanceItem(Items.RAW_MANTA_RAY_389,1,12),
            WeightedChanceItem(Items.BROKEN_ARROW_687,1,7),
            WeightedChanceItem(Items.BROKEN_GLASS_1469,1,7),
            WeightedChanceItem(Items.BROKEN_STAFF_689,1,7),
            WeightedChanceItem(Items.BUTTONS_688,1,8),
            WeightedChanceItem(Items.DAMAGED_ARMOUR_697,1,7),
            WeightedChanceItem(Items.OLD_BOOT_685,1,6),
            WeightedChanceItem(Items.OYSTER_407,1,5),
            WeightedChanceItem(Items.EMPTY_POT_1931,1,5),
            WeightedChanceItem(Items.RUSTY_SWORD_686,1,5),
            //Inauthentic rewards
            WeightedChanceItem(Items.LOOP_HALF_OF_A_KEY_987,1,2),
            WeightedChanceItem(Items.TOOTH_HALF_OF_A_KEY_985,1,2),
            WeightedChanceItem(Items.CASKET_405,1,4),
            WeightedChanceItem(Items.PIRATES_HAT_2651,1,1),
            WeightedChanceItem(Items.LUCKY_CUTLASS_7140,1,1)
    )
}