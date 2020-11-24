package plugin.activity.barrows

import core.game.component.Component
import core.game.container.access.InterfaceContainer
import core.game.content.global.BossKillCounter
import core.game.node.entity.player.Player
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.game.node.item.WeightedChanceItem
import core.tools.ItemNames
import core.tools.RandomFunction
import java.util.*

/**
 * The reward chest.
 * @author Ceikry
 * and slightly kermit
 */
object RewardChest {
    /**
     * The low profit drop table.
     */
    private val DROP_TABLE = arrayOf(
            //Weighted total = 3050
            WeightedChanceItem(ItemNames.COINS,             1, 5306, 950),
            WeightedChanceItem(ItemNames.MIND_RUNE,        60, 60, 300),
            WeightedChanceItem(ItemNames.MIND_RUNE,       100, 850, 300),
            WeightedChanceItem(ItemNames.CHAOS_RUNE,      115, 720, 300),
            WeightedChanceItem(ItemNames.DEATH_RUNE,       15, 15, 300),
            WeightedChanceItem(ItemNames.DEATH_RUNE,       70, 230, 300),
            WeightedChanceItem(ItemNames.BLOOD_RUNE,       35, 230, 300),
            WeightedChanceItem(ItemNames.BOLT_RACK_4740,   35, 280, 300),
            //Weight total = 975
            WeightedChanceItem(ItemNames.SUPER_DEFENCE2,    1, 1, 325),
            WeightedChanceItem(ItemNames.PRAYER_POTION2,    1, 1, 325),
            WeightedChanceItem(ItemNames.RESTORE_POTION2,   1, 1, 325),
            //Weight total = 53
            WeightedChanceItem(ItemNames.TOOTH_HALF_OF_KEY, 1, 1, 25),
            WeightedChanceItem(ItemNames.LOOP_HALF_OF_KEY,  1, 1, 25),
            WeightedChanceItem(ItemNames.DRAGON_MED_HELM,   1, 1, 3),
            //Weight total = 120 BARROWS ITEMS V
            WeightedChanceItem(4708, 1, 1, 5),
            WeightedChanceItem(4710, 1, 1, 5),
            WeightedChanceItem(4712, 1, 1, 5),
            WeightedChanceItem(4714, 1, 1, 5),
            WeightedChanceItem(4716, 1, 1, 5),
            WeightedChanceItem(4718, 1, 1, 5),
            WeightedChanceItem(4720, 1, 1, 5),
            WeightedChanceItem(4722, 1, 1, 5),
            WeightedChanceItem(4724, 1, 1, 5),
            WeightedChanceItem(4726, 1, 1, 5),
            WeightedChanceItem(4728, 1, 1, 5),
            WeightedChanceItem(4730, 1, 1, 5),
            WeightedChanceItem(4732, 1, 1, 5),
            WeightedChanceItem(4734, 1, 1, 5),
            WeightedChanceItem(4736, 1, 1, 5),
            WeightedChanceItem(4738, 1, 1, 5),
            WeightedChanceItem(4745, 1, 1, 5),
            WeightedChanceItem(4747, 1, 1, 5),
            WeightedChanceItem(4749, 1, 1, 5),
            WeightedChanceItem(4751, 1, 1, 5),
            WeightedChanceItem(4753, 1, 1, 5),
            WeightedChanceItem(4755, 1, 1, 5),
            WeightedChanceItem(4757, 1, 1, 5),
            WeightedChanceItem(4759, 1, 1, 5)
    )

    /**
     * Rewards the player.
     *
     * @param player The player.
     */
    @JvmStatic
    fun reward(player: Player) {
        for (killed in player.savedData.activityData.barrowBrothers) {
            if (!killed){
                player.sendMessage("You can't loot the chest until you kill all 6 barrows brothers.")
                player.removeAttribute("barrow:looted")
                // Because they haven't
                // actually looted the
                // chest yet.
                return
            }
        }
        val rewards: MutableList<Item> = ArrayList()
        var maxRolls = 2 + RandomFunction.random(0,player.savedData.activityData.barrowKills / 3)
        if(maxRolls > 6) maxRolls = 6
        for (i in 0 until maxRolls) {
            rewards.add(RandomFunction.rollWeightedChanceTable(*DROP_TABLE))
        }
        InterfaceContainer.generateItems(player, rewards.toTypedArray(), arrayOf("Pog!","Examine"), 364, 4,3,4)
        player.interfaceManager.open(Component(364))
        BossKillCounter.addtoBarrowsCount(player)
        for(item in rewards){
            if(!player.inventory.add(item)){
                GroundItemManager.create(item,player)
            }
        }
    }
}