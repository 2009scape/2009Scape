package plugin.interaction.`object`

import core.cache.def.impl.ObjectDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.`object`.ObjectBuilder
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.ItemNames

/**
 * Handles the chopping down of dense jungle, mainly to grant access to the Kharazi Jungle.
 * @author Ceikry
 */
@InitializablePlugin
class JungleBushHandler : OptionHandler(){
    val chopped_bush = 2895
    val chop_a  = Animation(910)
    val chop_b  = Animation(2382)

    override fun newInstance(arg: Any?): Plugin<Any> {
        ObjectDefinition.forId(2892).handlers["option:chop-down"] = this
        ObjectDefinition.forId(2893).handlers["option:chop-down"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        val toChop = node.asObject()
        if(checkRequirement(player)){
            GameWorld.Pulser.submit(object : Pulse(0){
                var ticks = 0
                override fun pulse(): Boolean {
                    when(ticks++){
                        0 -> player.animator.animate(chop_a).also { player.lock() }
                        1 -> player.animator.animate(chop_b)
                        2 -> ObjectBuilder.replace(toChop, GameObject(chopped_bush, toChop.location, toChop.rotation),20)
                        3 -> {player.walkingQueue.reset(); player.walkingQueue.addPath(toChop.location.x, toChop.location.y,true)}
                        4 -> player.unlock().also { return true }
                    }
                    return false
                }
            })
        } else {
            player.sendMessage("You need a machete to get through this dense jungle.")
        }
        return true
    }

    fun checkRequirement(player: Player): Boolean{
        val machete = Item(ItemNames.MACHETE_975)
        val jade_machete = Item(ItemNames.JADE_MACHETE_6315)
        val opal_machete = Item(ItemNames.OPAL_MACHETE_6313)
        val red_topaz_machete = Item(ItemNames.RED_TOPAZ_MACHETE_6317)
        return player.inventory.containsItem(machete) || player.inventory.containsItem(jade_machete) || player.inventory.containsItem(opal_machete) || player.inventory.containsItem(red_topaz_machete)
    }
}
