package plugin.quest.members.familycrest

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class AvanDialogue (player: Player? = null): DialoguePlugin(player) {
    override fun newInstance(player: Player?): DialoguePlugin {
        return AvanDialogue(player);
    }

    override fun open(vararg args: Any?): Boolean {
        npc = (args[0] as NPC).getShownNPC(player)
        val qstage = player?.questRepository?.getStage("Family Crest") ?: -1
        if(qstage < 13){
            npc("What? Can't you see I'm busy?")
            stage = 1;
        }else{
            when(qstage){
                13 -> options("Why are you lurking around a scorpion pit?", "I'm looking for a man... his name is Avan Fitzharmon.").also{stage = 2}
                14 -> npc("So how are you doing getting me my perfect gold jewelry?").also{stage = 100}
                15 -> npc("So how are you doing getting me my perfect gold jewelry?").also{stage = 200}
            }
        }

        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {

        when(stage){
            1 -> player("Well, sooooorry...").also{stage = 1000}

            2->when(buttonId){
                1 -> npc("It's a good place to find gold...").also{stage = 1000}
                2 -> npc("Then you have found him. My name is Avan Fitzharmon.").also{stage++}
            }

            3 -> player("You have a part of your family crest. I am on a quest ",
                    "to retrieve all of the fragmented pieces ",
                            "and restore the crest.").also{stage++}

            4 -> npc("Ha! I suppose one of my worthless brothers ",
                    "has sent you on this quest then?").also{stage++}

            5 -> player("No, it was your father who has asked me to do this for him.").also{stage++}

            6 -> npc("My... my father wishes this? Then that is a different matter. ",
                    "I will let you have my crest shard, adventurer, ",
                    "but you must first do something for me.").also{stage++}

            7 -> npc("There is a certain lady I am trying to impress. ",
                    "As a man of noble birth, I can not give her just ",
                    "any gold trinket to show my devotion. ").also{stage++}

            8 -> npc("What I intend to give her, is a golden ring, " ,
                    "embedded with the finest precious red stone available, " ,
                    "and a necklace to match this ring. ").also { stage++ }

            9 -> npc("The problem however for me, is that ",
                    "not just any old gold will be suitable. ",
                    "I seek only the purest, the most high quality of gold ",
                    "what I seek, if you will, is perfect gold.").also{stage++}
            10 -> npc("None of the gold around here is even " ,
                    "remotely suitable in terms of quality. ",
                    "I have searched far and wide for the perfect gold I desire, ",
                    "but have had no success so in finding it I am afraid. ").also { stage++ }

            11 -> npc("If you can find me my perfect gold, " ,
                    "make a ring and necklace from it, and add rubies to them, ",
                    "I will gladly hand over my fragment of " ,
                    "the family crest to you.").also{stage++}

            12 -> player("Can you give me any help on finding this 'perfect gold'?").also{stage++}

            13 -> npc("I thought I had found a solid lead on its whereabouts ",
                    "when I heard of a dwarf who is an expert " ,
                    "on gold who goes by the name of 'Boot'. ").also { stage++ }

            14 -> npc("Unfortunately he has apparently returned to this home,",
                    "somewhere in the mountains, " ,
                    "and I have no idea how to find him.").also { stage++}

            15 -> player("Well, I'll see what I can do.").also{
                stage = 1000
                player.questRepository.getQuest("Family Crest").setStage(player, 14)
            }

            100 -> player("I'm still after that 'perfect gold'.").also { stage++ }

            101 -> npc("I know how you feel... for such a long time " ,
                    "I have searched and searched for the elusive perfect gold... " ,
                    "I thought I had gotten a good lead on finding it ").also{stage++}

            102 -> npc("when I heard talk of a dwarven expert on gold named Boot " ,
                    "some time back, but unfortunately for me," ,
                    " he has returned to his mountain home, which I cannot find.").also { stage = 1000}

            200 -> player("I have spoken to Boot the dwarf about the location " ,
                    "of 'perfect gold', " ,
                    "but haven't managed to make you your jewelry yet.").also { stage++ }
            201 -> npc("Well, I won't entrust you with my piece of the crest " ,
                    "until you have brought me a necklace of perfect gold " ,
                    "with a red precious stone, and a perfect gold ring to match.").also { stage = 1000 }
            1000 -> end()
        }

        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(663)
    }

}