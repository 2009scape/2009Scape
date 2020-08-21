package plugin.quest.members.thefremenniktrials

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.world.GameWorld
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class PipeInserter : PluginInteraction(4162){

    override fun handle(player: Player?, node: Node?): Boolean {
        class toPipePulse : MovementPulse(player,DestinationFlag.OBJECT.getDestination(player,node)) {
            override fun pulse(): Boolean {
                if(player?.inventory?.containsItem(Item(3714))!!){
                    player.sendMessage("You stuff the lit object into the pipe.")
                    player.setAttribute("/save:fremtrials:cherrybomb",true)
                    player.inventory?.remove(Item(3714))
                } else {
                    player.sendMessage("What am I supposed to put in there? A shoe?")
                }
                return true
            }
        }

        if(node is GameObject){
            GameWorld.submit(toPipePulse())
            return true
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