package plugin.quest.members.thefremenniktrials

import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.dialogue.FacialExpression
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class SwensenLadder : PluginInteraction(4158){

    override fun handle(player: Player?, node: Node?): Boolean {
        if(node is GameObject){
            if(player?.getAttribute("fremtrials:swensen-accepted",false) == false){
                player.dialogueInterpreter?.sendDialogues(1283,FacialExpression.ANGRY,"Where do you think you're going?")
                return true
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