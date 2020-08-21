package plugin.quest.members.thefremenniktrials

import core.game.interaction.NodeUsageEvent
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.world.map.RegionManager
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class KegOnKegAction : PluginInteraction(3712){

    override fun handle(player: Player?, event: NodeUsageEvent?): Boolean {
        val used = event?.used
        val with = event?.usedWith
        if(with?.id?.equals(3711)!! && player?.isInLongHouse()!!){
            if(!player.getAttribute("fremtrials:keg-mixed",false)!!){
                if(player.getAttribute("fremtrials:cherrybomb",false)) {
                    player.inventory?.remove(used as? Item)
                    player.setAttribute("/save:fremtrials:keg-mixed", true)
                    player.sendMessage("The cherry bomb in the pipe goes off.")
                    RegionManager.getLocalEntitys(player).stream().forEach { e -> e.sendChat("What was THAT??") }
                    player.sendMessage("You mix the kegs together.")
                    return true
                } else {
                    player.dialogueInterpreter?.sendDialogue("I can't do this right now. I should create","a distraction.")
                    return true
                }
            }
            return false
        }
        return false
    }

    fun Player.isInLongHouse(): Boolean{
        return this.location.x >= 2655 && this.location.y >= 3665 && this.location.x <= 2662 && this.location.y <= 3681
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        PluginInteractionManager.register(this,PluginInteractionManager.InteractionType.USEWITH)
        return this
    }

}