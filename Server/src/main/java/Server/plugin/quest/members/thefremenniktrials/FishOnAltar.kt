package core.plugin.quest.fremtrials

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.interaction.NodeUsageEvent
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.dialogue.FacialExpression
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class FishOnAltar : PluginInteraction(383, 389, 395){
    var fishUsed = 0

    override fun handle(player: Player?, event: NodeUsageEvent?): Boolean {
        fishUsed = event?.used!!.id
        val with = event.usedWith
        SystemLogger.log("Trying to handle with this")
        SystemLogger.log("${with is GameObject}")
        if(with is GameObject && with.id == 4141){
            if(player?.questRepository?.getStage("Fremennik Trials")!! >= 10){
                GameWorld.submit(moveToPulse(player,with,fishUsed))
                return true
            }
        }
        return false
    }

    class moveToPulse(val player: Player?, dest: Node, val fish: Int) : MovementPulse(player,dest, DestinationFlag.OBJECT){
        override fun pulse(): Boolean {
            if(player?.inventory?.containsItem(Item(3689))!!){
                GameWorld.submit(spiritPulse(player, fish))
            } else {
                player.dialogueInterpreter.sendDialogue("I should probably have my lyre with me.")
            }
            return true
        }
    }

    class spiritPulse(val player: Player?, val fish: Int) : Pulse(){
        var counter = 0
        val npc = NPC(1273,player?.location)
        override fun pulse(): Boolean {
            when(counter++){
                0 -> npc.init().also { player?.lock() }.also { player?.inventory?.remove(Item(fish)) }
                1 -> npc.moveStep()
                2 -> npc.face(player).also { player?.face(npc) }
                3 -> player?.dialogueInterpreter?.sendDialogues(npc,FacialExpression.HAPPY,"I will kindly accept this offering, and","bestow upon you a gift in return.")
                4 -> player?.inventory?.remove(Item(3689))
                5 -> when(fish){
                    383 -> player?.inventory?.add(Item(6125))
                    389 -> player?.inventory?.add(Item(6126))
                    395 -> player?.inventory?.add(Item(6127))
                }
                6 -> player?.unlock()
                10 -> npc.clear().also { return true }
            }
            return false
        }
    }

    override fun fireEvent(identifier: String, vararg args: Any): Any {
        return Unit
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        PluginInteractionManager.register(this,PluginInteractionManager.InteractionType.USEWITH)
        return this
    }

}