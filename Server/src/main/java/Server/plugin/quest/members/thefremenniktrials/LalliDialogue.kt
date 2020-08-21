package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression

@InitializablePlugin
class LalliDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        player?.let {
            if (it.questRepository.getStage("Fremennik Trials") > 0) {
                player("Hello there.").also { stage = 0; return true }
            }
            if (it.getAttribute("fremtrials:askeladden-talkedto", false)!!) {
                player("Hello there.")
                stage = 50
                return true
            }
        }
        return true
    }



    override fun newInstance(player: Player?): DialoguePlugin {
        return LalliDialogue(player)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npc(FacialExpression.OLD_SNEAKY,"Bah! Puny humans always try steal Lalli's golden","apples! You go away now!").also { stage++ }
            1 -> player(FacialExpression.ANNOYED,"Actually, I'm not after your golden apples. I was","wondering if I could have some golden wool, I need it","to string a lyre.").also { stage++ }
            2 -> npc(FacialExpression.OLD_SNEAKY,"Ha! You not fool me human! Me am smart! Other","trolls so jealous of how brainy I are, they kick me out","of camp and make me live down here in cave! But me","have last funny!").also { stage++ }
            3 -> npc(FacialExpression.OLD_SNEAKY,"Me find golden apples on tree and me build wall to stop","anyone who not Lalli eating lovely golden apples! Did","me not tell you I are smart?").also { stage++ }
            4 -> player("Yes, yes, you are incredibly clever. Now please can I","have some golden wool?").also { stage++ }
            5 -> npc(FacialExpression.OLD_SNEAKY,"Hmm, me think you not really think I are clever. Me","think you is trying to trick Lalli. Me not like you as much","as other human. He give me present. I give him wool.").also { stage++ }
            6 -> options("Other human?","No, honest, you're REALLY clever.","Can I give you a present?").also { stage++ }
            7 -> when(buttonId){
                1 -> player("Other human? You mean someone else has been here","and you gave them wool?").also { stage = 10 }
                2 -> player("No, honest, you're REALLY clever!").also { stage = 20 }
                3 -> player("Can I give you a present.").also { stage = 30 }
            }

                //Other human?
                10 -> npc(FacialExpression.OLD_SNEAKY,"Human call itself Askeladden! It not trick Lalli. Lalli do","good deal with human! Stupid human get some dumb","wool, but did not get golden apples!").also { stage++ }
                11 -> player("I see... okay, well, bye!").also { player.setAttribute("/save:fremtrials:lalli-talkedto",true); stage++ }

                //Honest, you're clever
                20 -> npc(FacialExpression.OLD_SNEAKY,"Me no believe you tell truth. Go away.").also { stage = 1000 }

                //Can I give you a present
                30 -> npc(FacialExpression.OLD_SNEAKY,"Me think about that.").also { stage = 1000 }

            50 -> npc(FacialExpression.OLD_SNEAKY,"Bah! Puny humans always try steal Lalli's golden","apples! You go away now!").also { stage++ }
            51 -> player("Wait! I have something that might interest you... a pet", "rock!").also { stage++ }
            52 -> npc(FacialExpression.OLD_LAUGH2, "Hah! You think me stupid or something human? Me", "already got one! Me don't want lots of baby rocks either,", "so me don't want another one around the place!").also { stage++ }
            53 -> player(FacialExpression.HALF_CRYING,"Please... all I want is some of your golden wool...").also { stage++ }
            54 -> npc(FacialExpression.OLD_SNEAKY, "Stupid human think he can trick me into giving away", "some golden apples! Hah! Golden apples is all me got to", "eat. you not get them. no way!").also { stage++ }
            55 -> player(FacialExpression.HALF_THINKING, "Hmm... So you're hungry? I think I will have the", "perfect thing for you to eat... I just need to get myself", "an onion, a potato, and a cabbage...").also { stage++ }
            56 -> interpreter.sendDialogue("You have a cunning plan to trick this troll. You need your pet rock,", "a cabbage, a potato and an onion.").also { player.removeAttribute("fremtrials:fremtrials:lalli-talkedto");stage = 1000 }


            1000 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(1270)
    }

}