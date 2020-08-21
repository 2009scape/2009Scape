package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression

@InitializablePlugin
class AskeladdenDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        player?.let {
            if (it.questRepository.getStage("Fremennik Trials") > 0) {
                player("Hello there.").also { stage = 0; return true }
            }
            if(it.getAttribute("fremtrials:lalli-talkedto",false)!!){
                player("Hello there. I understand you managed to get some", "golden wool from Lalli?")
                stage = 0
                return true
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return AskeladdenDialogue(player)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.CHILD_LOUDLY_LAUGHING, "HAHAHA! Yeah, that Lalli... what a maroon!").also { stage++ }
            1 -> player("So how did you manage to get the wool?").also { stage++ }
            2 -> npc(FacialExpression.CHILD_FRIENDLY, "Well, as you know, I am doing the same trials that you", "are as part of my test of manhood, and that troll is the", "only one who can get that wool.").also { stage++ }
            3 -> npc(FacialExpression.CHILD_NORMAL, "You might have noticed he's kind of... messed in the", "head buddy! He's real paranoid about people stealing his", "golden apples, isn't he?").also { stage++ }
            4 -> player(FacialExpression.HALF_ASKING,"Indeed he is. So how did you manage to get some", "golden wool from him?").also { stage++ }
            5 -> npc(FacialExpression.CHILD_NORMAL, "It was easy buddy! I persuaded him he needed a pet to", "help him guard his apples. A pet that would never sleep!", "A pet that would never need food or exercise!").also { stage++ }
            6 -> npc(FacialExpression.CHILD_NORMAL, "A pet that would never need him to clean up its... well,", "you know, buddy. A pet that would always be loyal to", "him! A faithful companion for life!").also { stage++ }
            7 -> player(FacialExpression.HALF_ASKING, "What pet is this then?").also { stage++ }
            8 -> npc(FacialExpression.CHILD_LOUDLY_LAUGHING, "A pet ROCK!").also { stage++ }
            9 -> npc(FacialExpression.CHILD_NORMAL, "Man, can you believe that stupid troll traded me some", "of his golden wool for a worthless ROCK?").also { stage++ }
            10 -> npc(FacialExpression.CHILD_FRIENDLY, "Buddy, I hafta say: if brains were explosives, that troll", "wouldn't have enough to blow his nose!").also { stage++ }
            11 -> player(FacialExpression.HALF_ASKING, "Do you have any spare rocks then?").also { stage++ }
            12 -> npc(FacialExpression.CHILD_NEUTRAL, "Sure thing buddy, although I have to say, I doubt even", "that troll is stupid enough to fall for the SAME trick", "TWICE in a row! You can try anyways though!").also {
                                                                                                                                                                                                                                            player.setAttribute("/save:fremtrials:askeladden-talkedto",true);
                                                                                                                                                                                                                                            stage++}
            //Give player ROCK


            1000 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(2439)
    }

}