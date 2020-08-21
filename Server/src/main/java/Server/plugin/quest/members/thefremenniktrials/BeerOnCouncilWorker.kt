package plugin.quest.members.thefremenniktrials

import core.game.interaction.NodeUsageEvent
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class BeerOnCouncilWorker : PluginInteraction(1917){

    override fun handle(player: Player?, event: NodeUsageEvent?): Boolean {
        val with = event?.usedWith
        if(with?.id?.equals(1287)!! && with is NPC){
            player?.dialogueInterpreter?.open(1287,true,true)
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