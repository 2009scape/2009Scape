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
class AliTheGuard(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.FRIENDLY,"Hello there!")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npc(FacialExpression.ANNOYED,"I'm working. What do you have to say that's so urgent?").also { stage++ }
            1 -> options ("What can you tell me about Al Kharid?", "So, what do you do here?",
                "I hear you work for Ali Morrisane...", "I hear you've been threatening the other shopkeepers.").also {  stage++ }
            2 -> when(buttonId){
                1 -> player(FacialExpression.ASKING, "What can you tell me about Al Kharid?").also { stage = 10 }
                2 -> player(FacialExpression.ASKING, "So, what do you do here?").also { stage = 20 }
                3 -> player(FacialExpression.ASKING, "I hear you work for Ali Morrisane...").also { stage = 30 }
                4 -> player(FacialExpression.ASKING, "I hear you've been threatening the other shopkeepers.").also { stage = 40 }
            }

            10 -> npc(FacialExpression.FRIENDLY,"There's a lot of space here. More open space than back home.").also { stage++ }
            11 -> player(FacialExpression.ASKING,"So where is back home?").also { stage++ }
            12 -> npc(FacialExpression.FRIENDLY,"Pollnivneach. It's a town to the south of the", "Shantay Pass.").also { stage = 1 }

            20 -> npc(
                FacialExpression.ANNOYED,"I'm on guard duty.", "Making sure nobody tries to steal anything from",
                "the house and tents in the middle of town.").also { stage++ }
            21 -> player(FacialExpression.ASKING,"Why are you only guarding those buildings?").also { stage++ }
            22 -> npc(FacialExpression.SUSPICIOUS,"That's all I've been hired to guard.").also { stage = 1 }

            30 -> npc(FacialExpression.FRIENDLY,"Yeah, he hired me.", "He owns this house and these two tents, too.").also { stage++ }
            31 -> player(FacialExpression.HALF_ASKING,"Is the work good?").also { stage++ }
            32 -> npc(FacialExpression.FRIENDLY,"It pays better than back home.").also { stage++ }
            33 -> player(FacialExpression.ASKING,"Why, what did you do back home?").also { stage++ }
            34 -> npc(FacialExpression.SUSPICIOUS,"Never you mind.").also { stage++ }
            35 -> npc(FacialExpression.FRIENDLY,"But Ali Morrisane pays us well, at least.").also { stage++ }
            36 -> player(FacialExpression.ASKING,"Maybe I should talk to him...").also { stage++ }
            37 -> npc(FacialExpression.FRIENDLY,"Why not?", "He always likes to meet potential business partners.").also { stage = 1 }

            40 -> npc(FacialExpression.ANNOYED,"So? They talk too much.").also { stage++ }
            41 -> player(FacialExpression.HALF_ASKING,"You're not going to deny it?").also { stage++ }
            42 -> npc(FacialExpression.LOUDLY_LAUGHING,"Why bother?", "None of them can fight back, after all.").also { stage = 99 }

            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return AliTheGuard(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(2823)
    }
}