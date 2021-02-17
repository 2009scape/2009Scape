package core.game.content.dialogue

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Dialogue for Legends Guards at the Legends Guild
 * @author qmqz
 */
@Initializable
class LegendsGuardDialogue(player: Player? = null) : DialoguePlugin(player){
    fun gender (male : String = "sir", female : String = "madam") = if (player.isMale) male else female

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.FRIENDLY,"! ! ! Attention ! ! !")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npc(FacialExpression.FRIENDLY,"Legends Guild Member Approaching").also { stage++ }
            1 -> npc(FacialExpression.FRIENDLY,"Welcome "+ gender() + "!",  "I hope you enjoy your time in the Legends Guild.").also { stage = 99 }

            //while doing legend's quest
            10 -> npc(FacialExpression.FRIENDLY,"I hope the quest is going well " + gender() + ".").also { stage = 99 }

            //after the legend's quest is done
            20 -> npc(FacialExpression.FRIENDLY,"Legends Guild Member Approaching!").also { stage = 99 }

            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return LegendsGuardDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(398, 399)
    }
}