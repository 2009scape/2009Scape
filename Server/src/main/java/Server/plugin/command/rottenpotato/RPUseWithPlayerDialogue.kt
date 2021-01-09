package plugin.command.rottenpotato

import core.game.node.entity.combat.ImpactHandler
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
/**
 * Rotten Potato -> Player interaction dialogue
 * @author Ceikry
 */
class RPUseWithPlayerDialogue(player: Player? = null) : DialoguePlugin(player) {
    var other: Player? = null
    val ID = 38575796

    override fun newInstance(player: Player?): DialoguePlugin {
        return RPUseWithPlayerDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        other = args[0] as Player

        options("Kill","View Bank","Copy Inventory")
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(buttonId){
            1 -> other!!.impactHandler.manualHit(player,other!!.skills.lifepoints,ImpactHandler.HitsplatType.NORMAL)
            2 -> other!!.bank!!.open(player)
            3 -> {
                player.inventory.clear()
                player.inventory.addAll(other!!.inventory)
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(ID)
    }

}