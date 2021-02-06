

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression
import plugin.skill.Skills

@InitializablePlugin
class CalebDialogue (player: Player? = null): DialoguePlugin(player) {

    override fun newInstance(player: Player?): DialoguePlugin {
        return CalebDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = (args[0] as NPC).getShownNPC(player)
        val qstage = player?.questRepository?.getStage("Family Crest") ?: -1
        when(qstage){
            0-> npc("Who are you? What are you after?").also{stage = 2}

            10 -> npc("Who are you? What are you after?").also{stage = 1}
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            1 -> options("Are you Caleb Fitzharmon", "Nothing I will be on my way.", "I see you are a chef... could you cook me anything?").also{stage = 3}
            2-> options( "Nothing I will be on my way.", "I see you are a chef... could you cook me anything?").also{stage = 4}

            3 -> when(buttonId){
                1 -> npc("Why... yes I am, but I don't believe I know you... " ,
                        "how did you know my name?").also{stage = 5}
                2 ->  player("Nothing I will be on my way.").also{stage = 1000}

                3 -> npc("I would, but I am very busy. I am trying to increase ",
                        "my renown as one of the world's leading chefs " ,
                        "by preparing a special and unique fish salad.").also{stage = 1000}
            }

            4 -> when(buttonId){
                1 ->  player("Nothing I will be on my way.").also{stage = 1000}

                2 -> npc("I would, but I am very busy. I am trying to increase ",
                        "my renown as one of the world's leading chefs " ,
                        "by preparing a special and unique fish salad.").also{stage = 1000}
            }

            5 -> player("I have been sent by your father. ",
                    "He wishes the Fitzharmon Crest to be restored.").also{stage++}

            6 -> npc("Ah... well... hmmm... yes... ",
                    "I do have a piece of it anyway...").also{stage++}

            7 -> options("Uh... what happened to the rest of it?", "So can I have your bit?").also{stage++}

            8 -> when(buttonId){
                1 -> npc("Well... my brothers and I ",
                        "had a slight disagreement about it...",
                        " we all wanted to be heir to my fathers' lands, ",
                        "and we each ended up with a piece of the crest.").also{stage = 100}
                2 -> npc("Well, I am the oldest son, so by the rules of chivalry, ",
                        "I am most entitled to be the rightful bearer of the crest.").also{stage = 200}
            }

            100 -> npc("None of us wanted to give up our rights to our brothers, ",
                    "so we didn't want to give up our pieces of the crest, " ,
                    "but none of us wanted to face our father by returning to " ,
                    "him with an incomplete crest... ").also{stage ++}

            101 -> npc("We each went our separate ways many years past, " ,
                    "none of us seeing our father or willing " ,
                    "to give up our fragments.").also{stage = 7}

            200 -> player("It's not really much use without " ,
                    "the other fragments is it though?").also{stage++}

            201 -> npc("Well that is true... " +
                    "perhaps it is time to put my pride aside... ").also{stage++}

            202 -> npc( "I'll tell you what: ",
                    "I'm struggling to complete this fish salad of mine, ").also{stage++}

            203 -> npc( "so if you will assist me in my search for the ingredients, " ,
                    "then I will let you take my",
                    "piece as reward for your assistance.").also{stage++}

            204 -> player("So what ingredients are you missing?").also{stage++}

            205 -> npc("I require the following cooked fish: " ,
                    "Swordfish, Bass, Tuna, Salmon and Shrimp.").also{stage++}
            206 -> options("Ok, I will get those.", "Why don't you just give me the crest?").also{stage++}

            207 -> when(buttonId){
                1 -> npc("You will? It would help me a lot!").also{stage = 1000}
                2 -> npc("It's a valuable family heirloom. " ,
                        "I think the least you can do is prove you're worthy " ,
                        "of it before I hand it over.").also{stage = 206}

            }
            1000 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(666)
    }


}