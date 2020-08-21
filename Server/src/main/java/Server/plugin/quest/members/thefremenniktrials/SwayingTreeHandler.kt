package plugin.quest.members.thefremenniktrials

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager
import plugin.skill.gather.SkillingTool

@InitializablePlugin
class SwayingTreeHandler : PluginInteraction(4142) {

    class ChoppingPulse(val player: Player) : Pulse() {
        var counter = 0
        override fun pulse(): Boolean {
            when(counter++){
                0 -> player.animator.animate(SkillingTool.getHatchet(player).animation)
                4 -> player.animator.reset().also { player.inventory.add(Item(3692));return true}
            }
            return false
        }
    }

    override fun handle(player: Player?, node: Node?): Boolean {
        class toTreePulse(player: Player) : MovementPulse(player,DestinationFlag.OBJECT.getDestination(player,node)){
            override fun pulse(): Boolean {
                SkillingTool.getHatchet(player)?.let { GameWorld.submit(ChoppingPulse(player!!)).also { return true } }

                player?.sendMessage("You need an axe which you have the woodcutting level to use to do this.")
                return true
            }
        }

        if(node is GameObject){
            if(!player?.inventory?.containsItem(Item(3692))!!){
                GameWorld.submit(toTreePulse(player))
                return true
            } else {
                player.sendMessage("You already have a branch.")
            }
        }
        return false
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        PluginInteractionManager.register(this,PluginInteractionManager.InteractionType.OBJECT)
        return this
    }

}