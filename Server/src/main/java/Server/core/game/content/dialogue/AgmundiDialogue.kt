package core.game.content.dialogue

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AgmundiDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.CHILD_NORMAL,"Oh no, not another human... what do you want then?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> player(FacialExpression.ASKING, "Oh, do you get humans here often?").also { stage++ }
            1 -> npc(FacialExpression.CHILD_SUSPICIOUS, "Not that often, no, but sometimes.").also { stage++ }
            2 -> npc(FacialExpression.CHILD_THINKING, "Of course, since you people are too big for dwarven",
            "clothes, they typically don't stay very long.").also { stage++ }
            3 -> player(FacialExpression.SUSPICIOUS, "Why don't you make bigger clothes then?").also { stage++ }
            4 -> npc(FacialExpression.CHILD_GUILTY, "What'd be the point? Besides, I don't make", "these clothes myself.").also { stage++ }

            5 -> options ("Who makes these clothes then?", "I still want to buy your clothes.", "So do you have any quests for me?").also {  stage++ }
            6 -> when(buttonId){
                1 -> player(FacialExpression.GUILTY, "Who makes the clothes then?").also { stage = 10 }
                2 -> npc.openShop(player)
                3 -> npc(FacialExpression.CHILD_SUSPICIOUS,"Quests? Why would I have any quests?").also { stage = 20 }

            }

            10 -> npc(FacialExpression.CHILD_NORMAL,"Oh, my sister, she lives in Keldagrim-East.",
                "Has a little stall on the other side of",
                "the Kelda.").also { stage++ }
            11 -> npc(FacialExpression.CHILD_NORMAL,"If she only worked a little harder, like me,",
                "she wouldn't have to live in the sewers of the city.",
                "Shame really.").also { stage++ }
            12 -> player("The sewers? Your sister lives in the sewers?").also { stage++ }
            13 -> npc(FacialExpression.CHILD_SAD,"Keldagrim-East, such a ghastly place.",
                "Not civil, polite and clean like we are in the West.").also { stage++ }
            14 -> player(FacialExpression.SUSPICIOUS, "Uh-huh.").also { stage=99 }

            20 -> player("Oh, just anything to do would be fine.").also { stage++ }
            21 -> npc(FacialExpression.CHILD_NORMAL,"No, not right now... maybe I'll have something",
                "for you to do later, but nothing at the moment.").also { stage = 99 }

            99 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return AgmundiDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(2161)
    }
}