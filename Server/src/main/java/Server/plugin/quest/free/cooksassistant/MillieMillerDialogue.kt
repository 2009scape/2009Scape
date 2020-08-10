package plugin.quest.free.cooksassistant

import core.ServerConstants
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression

@InitializablePlugin
class MillieMillerDialogue (player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {

        npc(FacialExpression.HAPPY, "Hello Adventurer. Welcome to Mill Lane Mill. Can I", "help you?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {

            //Continuation of Millie's greeting
            0 -> options("Who are you?", "What is this place?", "How do I mill flour?", "I'm fine, thanks.").also { stage++ }
            1 -> when (buttonId) {
                1 -> player(FacialExpression.ASKING,"Who are you?").also { stage = 10 }
                2 -> player(FacialExpression.ASKING,"What is this place?").also { stage = 20 }
                3 -> player(FacialExpression.ASKING, "How do I mill flour?").also { stage = 30 }
                4 -> player(FacialExpression.NEUTRAL,"I'm fine, thanks.").also { stage = 1000 }
            }

            //Who are you?
            10 -> npc(FacialExpression.FRIENDLY,"I'm Miss Millicent Miller the Miller of Mill Lane Mill.","Our family have been milling flour for generations.").also { stage = 10 }
            11 -> player(FacialExpression.FRIENDLY,"It's a good business to be in. People will always need","flour.").also { stage++ }
            12 -> player(FacialExpression.ASKING,"How do I mill flour?").also { stage = 30 }

            //What is this place?
            20 -> npc(FacialExpression.SUSPICIOUS,"This is Mill Lane Mill. Millers of the finest flour in", ServerConstants.SERVER_NAME + ", and home to the Miller family for many","generations").also { stage++ }
            21 -> npc(FacialExpression.HAPPY,"We take grain from the field nearby and mill into flour.").also { stage++ }
            22 -> player(FacialExpression.ASKING, "How do I mill flour?").also { stage = 30 }

            //How do I mill flour?
            30 -> npc(FacialExpression.FRIENDLY, "Making flour is pretty easy. First of all you need to", "get some grain. You can pick some from wheat fields.","There is one just outside the Mill, but there are many","others scattered across " + ServerConstants.SERVER_NAME + ". Feel free to pick wheat").also { stage++ }
            31 -> npc(FacialExpression.FRIENDLY,"from our field! There always seems to be plenty of","wheat there.").also { stage++ }
            32 -> player(FacialExpression.ASKING, "Then I bring my wheat here?").also { stage++ }
            33 -> npc(FacialExpression.FRIENDLY,"Yes, or one of the other mills in " + ServerConstants.SERVER_NAME +". They all work","the same way. Just take your grain to the top floor of","the mill (up two ladders, there are three floors including","this one) and then place some grain in to the hopper.").also { stage++ }
            34 -> npc(FacialExpression.HAPPY, "Then you need to start the grinding process by pulling","the hopper lever. You can add more grain, but each","time you add grain you have to pul the hopper lever","again.").also { stage++ }
            35 -> player(FacialExpression.ASKING,"So where does the flour go then?").also { stage++ }
            36 -> npc(FacialExpression.SUSPICIOUS,"The flour appears in this room here, you'll need a pot","to put the flour into. One pot will hold the flour made","by one load of grain").also { stage++ }
            37 -> npc(FacialExpression.HAPPY,"And that's it! You now have some pots of finely ground","flour of the highest quality. Ideal for making tasty cakes","or delicous bread. I'm not a cook so you'll have to ask a","cook to ind out how to bake things.").also { stage++ }
            38 -> npc(FacialExpression.HAPPY,"Great! Thanks for your help.").also { stage = 1000 }

            //Conversation Endpoint
            1000 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return MillieMillerDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(3806)
    }
}
