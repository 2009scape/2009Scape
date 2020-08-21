package plugin.quest.members.thefremenniktrials

import core.game.interaction.NodeUsageEvent
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class BranchFletcher : PluginInteraction(3692){

    class BranchFletchingPulse(val player: Player?) : Pulse(){
        var counter = 0
        override fun pulse(): Boolean {
            when(counter++){
                0 -> player?.animator?.animate(Animation(1248)).also { player!!.lock() }
                3 -> player?.inventory?.remove(Item(3692)).also { player?.inventory?.add(Item(3688)); player!!.unlock(); return true}
            }
            return false
        }
    }

    override fun handle(player: Player?, event: NodeUsageEvent?): Boolean {
        val with = event?.usedWith
        if(with is Item && with.id == 946) {
            player?.let {
                if (it.inventory.containsItem(Item(946))) {
                    GameWorld.submit(BranchFletchingPulse(it))
                } else {
                    it.sendMessage("You need a knife to do this.")
                }
            }
            return true
        }
        return false
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        PluginInteractionManager.register(this,PluginInteractionManager.InteractionType.USEWITH)
        return this
    }

}