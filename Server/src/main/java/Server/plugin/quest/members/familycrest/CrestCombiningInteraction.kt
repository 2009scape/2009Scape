package plugin.quest.members.familycrest

import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.plugin.Plugin

@InitializablePlugin
class CrestCombiningInteraction: UseWithHandler(779, 780,781) {

    val CREST_AVAN: Item = Item(779)
    val CREST_CALEB: Item = Item(780)
    val CREST_JOHNATHON: Item = Item(781)
    val CREST_FULL: Item = Item(782)



    override fun newInstance(arg: Any?): Plugin<Any> {
        addHandler(779, ITEM_TYPE, this)
        addHandler(780, ITEM_TYPE, this)
        addHandler(781, ITEM_TYPE, this)
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        event ?: return false
        val used = event.used
        return when(used.id){
            779 -> CraftCrest(event.player, event)
            780 -> CraftCrest(event.player, event)
            781 -> CraftCrest(event.player, event)
            else -> false
        }
    }

    private fun CraftCrest(player: Player, event: NodeUsageEvent): Boolean{
        return when(event.usedWith.id){
            779,780,781 ->{
                if(player.inventory.containItems(779, 780, 781)) {
                    player.inventory.remove(CREST_AVAN)
                    player.inventory.remove(CREST_CALEB)
                    player.inventory.remove(CREST_JOHNATHON)
                    player.inventory.add(CREST_FULL)
                    true
                }
                false
            }
            else -> false
        }
    }
}