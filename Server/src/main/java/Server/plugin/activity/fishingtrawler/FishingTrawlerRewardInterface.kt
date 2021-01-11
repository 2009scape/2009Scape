package plugin.activity.fishingtrawler

import core.game.component.Component
import core.game.component.ComponentDefinition
import core.game.component.ComponentPlugin
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.Plugin
import kotlin.math.ceil

/**
 * THIS will have to be implemented at a later date.
 * Interface 367 in our cache, for whatever reason, opens correctly but all the children
 * are null or cant hold items. This interface cant even be opened in 2 different versions
 * of a cache editor I have. Something is completely fucked about this interface
 * in particular.
 */
class FishingTrawlerRewardInterface : ComponentPlugin() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        ComponentDefinition.put(367,this)
        return this
    }

    override fun open(player: Player?, component: Component?) {
        super.open(player, component)
        val session: FishingTrawlerSession? = player?.getAttribute("ft-session",null)
        session ?: return

        val numRolls = ceil(session.fishAmount / session.players.size.toDouble()).toInt()
        player?.removeAttribute("ft-session")

        val loot = ArrayList<Item>()
        for(i in 0 until numRolls){

        }
    }

    override fun handle(player: Player?, component: Component?, opcode: Int, button: Int, slot: Int, itemId: Int): Boolean {
        TODO("Not yet implemented")
    }

}