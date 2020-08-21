package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class SigliTheHuntsman(player: Player? = Player(PlayerDetails("",""))) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        if(player?.getAttribute("fremtrials:sigli-vote",false)!!){
            npc("You have my vote!")
            stage = 1000
            return true
        }
        if(player?.getAttribute("fremtrials:draugen-killed",false)!!){
            npc("I saw the entire hunt. Let me take that talisman from","you, I would be honored to speak out for you to our","council of elders after such a hunt, outerlander.")
            stage = 100
            return true
        }
        npc("What do you want outerlander?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> player("Are you a member of the council?").also{stage++}
            1 -> npc("The Fremennik council of elders? I am pleased to say","that I am. My value as a huntsman is recognized by","my position there.").also { stage++ }
            2 -> player("I was wondering if I could persuade you to vouch for","me as a member of your clan?").also { stage++ }
            3 -> npc("You? ... well... I am not totally against the idea","outerlander. If you can demonstrate some hunting skills","then perhaps I may offer my vote.").also { stage++ }
            4 -> player("How can I prove my hunting skills to you? I can go","kill, like, a hundred chickens for you right now!").also { stage++ }
            5 -> npc("Chickens? You think that would impress me?").also { stage++ }
            6 -> player("Cows then? I can kill cows until, er, the cows come","home.").also { stage++ }
            7 -> npc("No. The prey I have in mind for you to prove your","worth to me is something far more dangerous. Far","more difficult. Far more deadly.").also { stage++ }
            8 -> player("Not... Giant Rats?!?!").also { stage++ }
            9 -> npc("I suspect you are mocking me outerlander. You will","need to prove your skill as a hunter to me by tracking","and defeating... The Draugen.").also { stage++ }
            10 -> options("What's a Draugen?","Forget it.").also { stage++ }
            11 -> when(buttonId){
                    1 -> player("What's a Draugen? Some kind of cheap rip-","off of a dragon?").also { stage++ }
                    2 -> player("Forget it.").also { stage = 1000 }
                  }

                //What's a Draugen
                12 -> npc("Hmmm.. No, the words are slightly similar I suppose,","but they are very different creatures.").also { stage++ }
                13 -> npc("The Draugen is an evil ghost from Fremennik","mythology, that devours souls of those brave","warriors who meet their ends at sea.").also { stage++ }
                14 -> npc("It stalks the coastlines, invisible to all. It brings us bad","fortunes, and curses our journeys across the seas. It is","also unkillable by normal means.").also { stage++ }
                15 -> player("...Let me get this straight; You want me to hunt an","unkillable, invincible, and invisible enemy? How am I","supposed to do that?").also { stage++ }
                16 -> npc("Well outerlander, should you accept my challenge I will","show you a special hunter's trick that will hellp you. Do","you accept the challenge?").also { stage++ }
                17 -> options("Yes","No").also { stage++ }
                18 -> when(buttonId){
                        1 -> player("Well, I need every vote I can get in the council of","elders, but this certainly sounds impossible to do...").also { stage++ }
                        2 -> player("No, I don't think I will.").also { stage = 1000 }
                        }

                        //Yes
                        19 -> npc("Not at all outerlander. The Draugen is indeed","impossible to kill but that is not the same as being","impossible to fight against.").also { stage++ }
                        20 -> npc("Every time he takes a Fremennik life, he gains in","power, so to keep it from becoming too powerful we","hunters hunt it and steal its life force.").also { stage++ }
                        21 -> npc("We do this with a special talisman. Here, take it, it will","let you track the Draugen while it's invisible, and when","you defeat it will absorb its essence.").also { stage++; player?.inventory?.add(Item(3696)) }
                        22 -> npc("I want you to track the Draugen, defeat it, and store","its essence in that talisman for me. If you can do this","important task for my clan, I will vote for you.").also { stage++ }
                        23 -> npc("Take care of the talisman, and see me when you have","completed this task.").also{stage = 1000; player?.setAttribute("/save:fremtrials:sigli-accepted",true)}

            //Draugen killed
            100 -> player("Thanks!").also {
                                                        player.removeAttribute("fremtrials:draugen-killed");
                                                        player.setAttribute("fremtrials:sigli-vote",true)
                                                        player?.setAttribute("/save:fremtrials:votes",player.getAttribute("fremtrials:votes",0) + 1)
                                                        player?.inventory?.remove(Item(3697))
                                                        stage = 1000
                                                     }

            1000 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SigliTheHuntsman(player)
    }



    override fun getIds(): IntArray {
        return intArrayOf(1281)
    }

}