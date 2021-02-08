package plugin.quest.members.familycrest


import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class JohnathonDialogue(player: Player? = null): DialoguePlugin(player)  {
    val CREST_PIECE: Item = Item(781)
    override fun newInstance(player: Player?): DialoguePlugin {
        return JohnathonDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = (args[0] as NPC).getShownNPC(player)
        val qstage = player?.questRepository?.getStage("Family Crest") ?: -1

        if(qstage == 100){
            options("Can you enchant these gauntlets for me?", "Nevermind")
            stage = 6000
            return true
        }

        if(qstage < 16){
            npc("I dont feel so well... maybe we can talk later")
            stage = 1000;
        }
        else{
            when(qstage){
                16 -> player("Greetings. Would you happen to be Johnathon Fitzharmon?").also { stage = 1}
                17 -> npc("What... what did that spider... DO to me? " ,
                        "I... I feel so weak... " ,
                        "I can hardly... think at all...").also { stage = 1000 }
                18 -> sendDialogue("You use the potion on Johnathon").also { stage = 100 }
                19 -> player("I'm trying to kill this demon Chronozon that you mentioned...").also { stage = 200 }
            }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){

            1 -> npc("That... I am...").also { stage++ }
            2 -> player("I am here to retrieve your fragment " ,
                    "of the Fitzharmon family crest.").also { stage++ }
            3 -> npc("The... poison... it is all... " ,
                    "too much... My head... " ,
                    "will not... stop spinning...").also { stage++ }
            4 -> sendDialogue("Sweat is pouring down Jonathons'face.").also { stage = 1000
            player.questRepository.getQuest("Family Crest").setStage(player, 17);
            }

            100 -> npc("Ooooh... thank you... Wow! " ,
                    "I'm feeling a lot better now! " ,
                    "That potion really seems to have done the trick!").also{stage++}
            101 -> npc("How can I reward you?").also { stage++ }

            102 -> player("I've come here for your piece of the Fitzharmon family crest.").also { stage++}

            103 -> npc("You have? Unfortunately I don't have it any more... " ,
                    "in my attempts to slay the fiendish Chronozon, the blood demon, " ,
                    "I lost a lot of equipment in our last battle when he " ,
                    "bested me and forced me away from his den. He probably still has it now.").also{
                stage = 200
                player.questRepository.getQuest("Family Crest").setStage(player, 19)
                    }

            200 -> options("So is this Chronozon hard to defeat?", "Where can I find Chronozon?", "So how did you end up getting poisoned?", "I will be on my way now.").also{stage++}
            201 -> when(buttonId){
                1-> npc("Well... you will have to be a skilled Mage to defeat him, " ,
                        "and my powers are not good enough yet. " ,
                        "You will need to hit him once with each of the four " ,
                        "elemental spells of death before he will be defeated.").also{stage = 1000}

                2->npc("The fiend has made his lair in Edgeville Dungeon. " ,
                        "When you come in down the ladder in Edgeville, follow" ,
                        "the corridor north until you reach a room with skeletons. " ,
                        "That passageway to the left will lead you to him.").also{stage = 1000}

                3 -> npc("Those accursed poison spiders that surround " ,
                        "the entrance to Chronozon's lair... " ,
                        "I must have taken a nip from one of them " ,
                        "as I attempted to make my escape.").also{stage = 1000}

                4 -> npc("My thanks for the assistance adventure").also{stage = 1000}

            }
            6000 -> when(buttonId){
                1-> if(DoMissingGuantletCheck() != -1){
                    var gauntletID = DoMissingGuantletCheck()

                    if(gauntletID == 777){
                        npc("You already have the Chaos Guantlets.")
                        stage = 1000
                    }
                    else{
                        npc("Here you go")
                        player.inventory.remove(Item(gauntletID))
                        player.inventory.add(Item(777))
                        stage = 1000
                    }
                }
                else{
                    npc("You do not have the guantlets with you in your inventory")
                    stage = 1000
                }
                2-> player("Never mind").also{stage = 1000}
            }


            1000 -> end()
        }
        return true;
    }

    private fun DoMissingGuantletCheck(): Int{
        var itemsToCheck = listOf(775, 776, 777, 778)
        for(item in itemsToCheck){
            if(player.inventory.containItems(item))
                return item
        }
        return -1
    }

    override fun getIds(): IntArray {
        return intArrayOf(668)
    }

}