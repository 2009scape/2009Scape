package core.plugin.quest.fremtrials

import core.cache.def.impl.ItemDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.impl.Animator
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import core.plugin.InitializablePlugin
import core.plugin.Plugin

@InitializablePlugin
class LyreOptionHandler : OptionHandler(){
    val ids = arrayListOf(14591,14590,6127,6126,6125,3691,3690)

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        if(player?.questRepository?.getStage("Fremennik Trials")!! < 20){
            player.sendMessage("You lack the knowledge to play this.")
            return true
        }
        if(ids.isLast(ids.indexOf(node?.id))){
            player.sendMessage("Your lyre is out of charges!")
        } else {
            player.inventory?.remove(node as Item)
            player.inventory?.add(Item(ids.getNext(node?.id)))
            player.lock()
            GameWorld.submit(telePulse(player))
        }
        return true
    }

    class telePulse(val player: Player) : Pulse(){
        var counter = 0
        override fun pulse(): Boolean {
            when(counter++){
                0 -> player.animator.animate(Animation(9600,Animator.Priority.VERY_HIGH), Graphics(1682))
                6 -> player.properties.teleportLocation = Location.create(2661, 3646, 0)
                7 -> player.unlock().also { return true }
            }
            return false
        }
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        for(id in ids){
            ItemDefinition.forId(id).configurations.put("option:play",this)
        }
        return this
    }

    private fun <T> ArrayList<T>.getNext(any: Any?): T{ //gets the next member from a list
        val indexOfCurrent = this.indexOf(any)
        return get(indexOfCurrent + 1)
    }

    private fun <T> ArrayList<T>.isLast(current: Int): Boolean{
        return current + 1 > size - 1
    }

}