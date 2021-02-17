package core.game.content.dialogue

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Dialogue for Lensa on Jaitizso
 * @author qmqz
 */
@Initializable
class LensaDialogue(player: Player? = null) : DialoguePlugin(player){
    fun gender (male : String = "brother", female : String = "sister") = if (player.isMale) male else female

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.FRIENDLY,"Hello.")
        stage = 0
        return true
    }
    //should say [Fremennik name] after gender, but this is not yet implemented

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npc(FacialExpression.FURIOUS,"My apologies " + gender() + "!", "I have not time to tarry here with you!").also { stage++ }
            1 -> player(FacialExpression.AFRAID,"That's okay, I didn't really want anything.").also { stage = 99 }
            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return LensaDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(5494)
    }
}