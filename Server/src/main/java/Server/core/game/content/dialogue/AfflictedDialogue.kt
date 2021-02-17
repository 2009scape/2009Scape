package core.game.content.dialogue

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.RandomFunction
import java.util.*
import java.util.Arrays


/**
 * @author qmqz
 */

@Initializable
class AfflictedDialogue : DialoguePlugin {
    private val chats = arrayOf("ughugh", "knows'is", "knows'is", "nots", "pirsl", "wot's", "zurgle", "gurghl", "mee's", "seysyi", "sfriess", "says")

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        chats.shuffle()
        interpreter.sendDialogues( npc, FacialExpression.ASKING, chats.copyOfRange(0, RandomFunction.random(1, 6)).contentToString()
                .replace("[", "").replace("]", "").replace(",", ""))
        return true
    }

    constructor()
    constructor(player: Player?) : super(player)

    override fun getIds(): IntArray {
        return intArrayOf(1257, 1258, 1261, 1262)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        end()
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return AfflictedDialogue(player)
    }
}