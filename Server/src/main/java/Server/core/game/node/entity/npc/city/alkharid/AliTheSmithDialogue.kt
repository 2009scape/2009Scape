package core.game.node.entity.npc.city.alkharid

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AliTheSmithDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.FRIENDLY,"Hello there!")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npc(FacialExpression.FRIENDLY,"You seem rather cheerful. Is there anything you're after?").also { stage++ }
            1 -> options ("What can you tell me about Al Kharid?", "So, what do you do here?",
                "I hear you work for Ali Morrisane...", "I hear you've been threatening the other shopkeepers.").also {  stage++ }
            2 -> when(buttonId){
                1 -> player(FacialExpression.ASKING, "What can you tell me about Al Kharid?").also { stage = 10 }
                2 -> player(FacialExpression.ASKING, "So, what do you do here?").also { stage = 20 }
                3 -> player(FacialExpression.ASKING, "I hear you work for Ali Morrisane...").also { stage = 30 }
                4 -> player(FacialExpression.HALF_ASKING, "I hear you've been threatening the other shopkeepers.").also { stage = 40 }
            }

            10 ->npc(FacialExpression.FRIENDLY,"Well, it's hot and full of sand.").also { stage++ }
            11 ->player(FacialExpression.ASKING,"Anything else?").also { stage++ }
            12 ->npc(FacialExpression.FRIENDLY,"Don't ask me, I'm not from around here.").also { stage++ }
            13 ->player(FacialExpression.HALF_ASKING,"So where are you from?").also { stage++ }
            14 ->npc(FacialExpression.HALF_GUILTY,"A town to the south of the Shantay Pass,","called Pollnivneach.").also { stage = 1 }

            20 ->npc(FacialExpression.SAD,"Not very much, at the moment.", "I came from further south to set up a smithy here...").also { stage++ }
            21 ->npc(FacialExpression.SAD,"...but we still haven't set up the shops,", "so we have no customers yet.").also { stage = 1 }

            30 -> npc(FacialExpression.FRIENDLY,"Yes, he's the one who persuaded us to come here.").also { stage++ }
            31 -> npc(
                FacialExpression.HALF_GUILTY,"He said we'd have lots of customers once we set up",
                "our shops, and all he asks for is a cut"," of the profits.").also { stage++ }
            32 -> player(FacialExpression.ASKING,"Maybe I should talk to him...").also { stage++ }
            33 -> npc(FacialExpression.FRIENDLY,"Of course.", "He's always happy to talk to possible business partners.").also { stage = 1 }

            40 -> npc(FacialExpression.SUSPICIOUS,"What? Who's spreading that rumour?").also { stage++ }
            41 -> player(FacialExpression.THINKING,"Well, one of the shopkeepers told me a man with", "a large hammer came to threaten them.").also { stage++ }
            42 -> npc(FacialExpression.SUSPICIOUS,"Don't pay any attention to those people.").also { stage++ }
            43 -> npc(FacialExpression.LAUGH,"They're just worried that they'll lose money", "when we open our shops.").also { stage = 99 }

            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return AliTheSmithDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(2820)
    }
}