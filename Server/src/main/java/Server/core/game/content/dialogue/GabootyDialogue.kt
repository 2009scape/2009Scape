package core.game.content.dialogue

import com.sun.org.apache.xpath.internal.operations.Bool
import core.cache.def.impl.NPCDefinition
import core.cache.def.impl.ObjectDefinition
import core.game.content.global.shop.Shop
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.system.config.ShopParser
import core.game.system.config.ShopParser.Companion.SHOPS
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Dialogue for Gabooty
 * @author qmqz
 */
@Initializable
class GabootyDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.FRIENDLY, "What do you do here?").also { stage = 0 }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npc(FacialExpression.FRIENDLY,"Not much really... I run a local shop which earns", "me a few trading sticks, but that's about it.", "I keep myself to myself really.").also { stage++ }
            1 -> player(FacialExpression.FRIENDLY, "What are trading sticks?").also { stage++ }
            2 -> npc(FacialExpression.FRIENDLY,
                "They're the local currency Bwana, ",
                "it's used in Tai Bwo Wannai, there's usually",
                "some odd jobs that need doing around the village",
                "which you could do to earn some trading sticks.").also { stage++ }
            3 -> npc(FacialExpression.FRIENDLY,
                "Or, if you have something which the local villagers",
                "might like, you could sell it to me and I'll pay",
                "you for it in trading sticks.").also { stage++ }

            4 -> interpreter.sendOptions("Select an Option",
                "What sort of things do the local villagers like?",
                "You run a shop?",
                "I want to ask another question.",
                "Ok, thanks.").also { stage++ }

            5 ->
                when (buttonId) {
                    1 -> player("What sort of things do the local villagers like?").also { stage = 10 }
                    2 -> player("You run a shop?").also { stage = 21 }
                    3 -> player("I want to ask another question.").also { stage = 23 }
                    4 -> player("Ok, thanks.").also { stage = 99 }
                }

            10 -> npc(FacialExpression.FRIENDLY,
                "Gnome cocktails! It's amazing but we all just love them!",
                "Luckily I've managed to get stocks of the gnome made",
                "cocktails and I supply those to my favourite customers.",
                "However, many of the customers really like the cocktails").also { stage++ }
            11 -> npc(FacialExpression.FRIENDLY,
                "made by the adventurers passing through this way.",
                "If you ever happen to have any, bring them my way! ",
                "I'll give you a good deal on it that's for sure!").also { stage++ }
            12 -> player(FacialExpression.FRIENDLY, "How did the villagers ever get to try any gnome cocktails?").also { stage++ }
            13 -> npc(FacialExpression.FRIENDLY,
                "I think it was that gnome pilot who crashed his glider,",
                "perhaps he had a little mini-bar on board?",
                "Come to think about it, you'd expect the little guy to get ",
                "his stuff together and move on out of here wouldn't you?").also { stage++ }
            14 -> npc(FacialExpression.FRIENDLY, "He must love the jungle to have stayed here so long!").also { stage = 4 }

            21 -> npc(FacialExpression.FRIENDLY,
                "Yes, it's the Tai Bwo cooperative... catchy name huh!",
                "We sell a few local village trinkets and tools.",
                "Also, there are a few items that we're actually",
                "looking to stock for the locals' sake.").also { stage++ }
            22 -> npc(FacialExpression.FRIENDLY,
                "If you happen across any of them please bring them to me,",
                "I'll pay a good price for them.",
                "I'm sure you'll find the prices very reasonable.").also { stage++ }
            23 -> interpreter.sendOptions("Select an Option",
                "What are trading sticks?",
                "You run a shop?",
                "Can I take a look at what you have to sell?",
                "Why do you only take one sort of Gnome cocktail?",
                "I want to ask another question.").also { stage++ }

            24 ->
                when (buttonId) {
                    1 -> player("What are trading sticks?").also { stage = 2 }
                    2 -> player("You run a shop?").also { stage = 21 }
                    3 -> player("Can I take a look at what you have to sell?").also { stage = 30 }
                    4 -> player("Why do you only take one sort of Gnome cocktail?").also { stage = 40 }
                    5 -> player("I want to ask another question.").also { stage = 4 }
                }

            30 -> npc(FacialExpression.FRIENDLY, "Sure you can...which shop would you like to see?",
                "The Cooperative or Drinks store?").also { stage++ }

            31 ->interpreter.sendOptions("Select an Option",
                "The Cooperative",
                "The Drinks Store",
                "None thanks...").also { stage++ }

            32 ->
                when (buttonId) { //gotta add shops
                    1 -> player("The Cooperative").also { stage = 90 }
                    2 -> player("The Drinks Store").also { stage = 91 }
                    3 -> player("None thanks...").also { stage = 99 }
                }

            40 -> npc(FacialExpression.FRIENDLY, "It's funny that, actually. I've managed to get a ",
                "good deal with the pilot of that gnome glider. He can supply",
                "me directly now. However, it's really interesting that the ",
                "local villagers really like the gnome cocktails made by the").also { stage++ }
            41 -> npc(FacialExpression.FRIENDLY, "adventurers passing through this way.").also { stage++ }
            42 -> player(FacialExpression.FRIENDLY, "Why, what's the difference?").also { stage++ }
            43 -> npc(FacialExpression.FRIENDLY, "I think it may just be that they're made fresher,",
            "or there is a slight twist in the flavour of the drink,",
            "you know, a little more of this, a little less of that, it all",
            "adds up and makes for an interesting tipple!").also { stage = 23 }

            90 -> end().also { ShopParser.Companion.openUid(player,226) }
            91 -> end().also { ShopParser.Companion.openUid(player,227) }
            99 -> end()
        }

        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return GabootyDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(2519, 2520, 2521)
    }
}