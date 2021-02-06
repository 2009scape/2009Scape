package plugin.quest.members.familycrest


import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression
import plugin.skill.Skills


@InitializablePlugin
/**
* Handles DimintheisDialogue Dialogue
* @author Plex
*/
class DimintheisDialogue(player: Player? = null): DialoguePlugin(player) {
    override fun newInstance(player: Player?): DialoguePlugin {
        return DimintheisDialogue(player)
    }


    override fun open(vararg args: Any?): Boolean {
        npc = (args[0] as NPC).getShownNPC(player)
        val qstage = player?.questRepository?.getStage("Family Crest") ?: -1
        when(qstage) {
            0 -> npc("Hello. My name is Dimintheis, ",
                    "of the noble family Fitzharmon.").also { stage = 1 }
            10 -> player("Where did you say I could find your son Caleb again?").also { stage = 3000 }

        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            1 -> options(
                    "Why would a nobleman live in a dump like this?",
                    "You're rich then? Can i have some money?",
                    "Hi, i am a bold adventurer.").also {stage++}
            2 -> when(buttonId){
                1 -> npc("The King has taken my estate from me ",
                        "until such time as I can show my family crest to him.").also { stage = 3}
                2 -> npc("Gah! Lousy beggar! " ,
                        "Your sort is what's ruining this great land! ",
                        "Why don't you just go and get a " ,
                        "job if you need money so badly?").also { stage = 1000}
                3 -> npc("An adventurer hmmm? How lucky. I may have an adventure for you. " ,
                        "I desperately need my family crest returning to me. " ,
                        "It is of utmost importance.").also { stage = 4 }
            }
            3 -> options("Why would he do that?",
                                  "So where is this crest?").also {stage = 5}

            4 -> options("Why are you so desperate for it?",
                                 "So where is this crest?",
                                 "I'm not interested in that adventure right now").also { stage = 6}
            5 -> when(buttonId){
                1-> npc("Well, there is a long standing rule of chivalry " ,
                        "amongst the Varrockian aristocracy,").also{stage = 2000}
                2-> npc("Well, my three sons took it with them " ,
                        "many years ago when they rode ").also { stage = 2007 }
            }
            6 -> when(buttonId){
                1 -> npc("Well, there is a long standing rule of chivalry " ,
                        "amongst the Varrockian aristocracy,").also{stage = 2000}
                2 -> npc("Well, my three sons took it with them " ,
                        "many years ago when they rode ").also { stage = 2007 }
                3 -> npc("I realise it was a lot to ask of a stranger.").also { stage = 1000}

            }
            2000 -> npc(
                    "where each noble family is in possession of a unique crest, ",
                    "which signifies the honour and lineage of the family. ").also { stage++}
            2001 -> npc("More than this however, it also represents the ",
                                  "lawful rights of each family  to prove their ownership of ",
                                  "their wealth and lands. If the family crest is lost,").also { stage++ }

            2002 -> npc( "then the family's estate is handed over to the ",
                                  "current monarch until the crest is restored.").also{stage++}
            2003 -> npc("This dates back to the times when there was much in-fighting " ,
                                  "amongst the noble families and their clans, and ",
                                  "was introduced as a way of reducing the bloodshed that was ",
                                  "devastating the ranks of the ruling classes at that time.").also { stage++ }
            2004 -> npc("When you captured a rival family's clan, " ,
                    "you also captured their lands and wealth.").also{ stage++}

            2005 -> options("So where is this crest?", "I'm not interested in an adventure right now").also{stage++}

            2006 -> when(buttonId){
                1-> npc("Well, my three sons took it ",
                        "with them many years ago when they rode out ").also { stage++ }

                2 ->  npc("I realise it was a lot to ask of a stranger.").also { stage = 1000}

            }
            2007 -> npc("to fight in the war against the undead necromancer " ,
                    "and his army in the battle to save Varrock.").also { stage++}
            2008 -> npc("For many years I had assumed them all dead, " ,
                    "as I had heard no word from them.").also { stage++}
            2009 -> npc("Recently I heard that my son Caleb is alive and well, " ,
                    "trying to earn his fortune as a great fish chef.").also { stage++ }
            2010 -> npc("I believe he is staying with a friend ",
                    "who lives just outside the west gates of Varrock.").also { stage++}
            2011 -> options("Ok, I will help you", "I'm not interested in an adventure right now").also { stage++}
            2012 -> when(buttonId){
                1 -> npc("I thank you greatly adventurer!").also { stage++}
                2 -> npc("I realise it was a lot to ask of a stranger.").also { stage  = 1000}
            }
            2013 -> if(player.skills.getLevel(Skills.CRAFTING) >= 40 &&
                    player.skills.getLevel(Skills.SMITHING) >= 40 &&
                    player.skills.getLevel(Skills.MINING) >= 40 &&
                    player.skills.getLevel(Skills.MAGIC) >= 59) {
                npc("If you find Caleb, or my other sons... please... ",
                        "let them know their father still loves them...").also { stage = 1000 }.also { player.questRepository.getQuest("Family Crest").start(player) }.also { player.questRepository.syncronizeTab(player) }
            }else{
                npc("But im sorry, but you cannot help me right now").also{stage = 1000}
            }

            3000 ->npc("The only thing I have heard of my son Caleb ",
                    "is that he is trying to earn his fortune as a great fish chef.").also{stage++}
            3001 ->npc("I believe he is staying with a friend ",
                    "who lives just outside the west gates of Varrock.").also{stage = 1000}

            1000 -> end()

        }


        return true;
    }

    override fun getIds(): IntArray {
        return intArrayOf(8171)
    }

}