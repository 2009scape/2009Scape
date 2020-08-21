package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class SwensenTheNavigator(player: Player? = Player(PlayerDetails("",""))) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        if(player?.getAttribute("fremtrials:maze-complete",false) == true){
            npc("Outerlander! You have finished my maze!","I am genuinely impressed!")
            stage = 100
            return true
        } else if(player?.getAttribute("fremtrials:swensen-vote",false) == true){
            npc("You have my vote!")
            stage = 1000
            return true
        }
        player("Hello!")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> {player("I am trying to become a member of the Fremennik","clan! The Chieftan told me that I may be able to gain","your vote at the council of elders?");stage++}
            1 -> {npc("You wish to stop being an outerlander? I can","understand that! I have no reason why I would prevent","you becoming a Fremennik...");stage++}
            2 -> {npc("...but you must first pass a little test for me to prove","you are worthy.");stage++}
            3 -> {player("What kind of test?");stage++}
            4 -> {npc("Well, I serve our clan as a navigator. The seas can be","a fearful place when you know not where you are","heading.");stage++}
            5 -> {npc("Should something happen to me, all members of our","tribe have some basic sense of direction so that they","may always return safely home.");stage++}
            6 -> {npc("If you are able to demonstrate to me that you too have","a good sense of direction then I will recommend you to","the rest of the council of elders immediately.");stage++}
            7 -> {player("Well, how would I go about showing that?");stage++}
            8 -> {npc("Ah, a simple task! Below this building I have constructed","a maze; should you be able to walk from one side to the","other that will be proof to me.");stage++}
            9 -> {npc("You wish to try my challenge?");stage++}
            10 -> {options("Yes","No");stage++}
            11 -> when(buttonId){
                    1 -> {player("A maze? Is that all? Sure, it sounds simple enough.");stage++}
                    2 -> {player("No, that sounds too hard.");stage = 1000}
                 }

                //Yes
                12 -> {npc("I will warn you outerlander, this maze was designed by","myself, and is of the most fiendish complexity!");stage++}
                13 -> {player("Oh really? Watch and learn...");stage = 1000;player?.setAttribute("/save:fremtrials:swensen-accepted",true)}

            //After maze
            100 -> {player("So does that mean I can rely on your vote at the","council of elders to allow me into your village?");stage++}
            101 -> {npc("Of course outerlander! I am nothing if not a man of","my word!");stage++}
            102 -> {
                    player("Thanks!");
                    player?.removeAttribute("fremtrials:maze-complete")
                    player?.removeAttribute("fremtrials:swensen-accepted")
                    player?.setAttribute("/save:fremtrials:swensen-vote",true)
                    player?.setAttribute("/save:fremtrials:votes",player.getAttribute("fremtrials:votes",0) + 1)
                    stage = 1000
                  }

            1000 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SwensenTheNavigator(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(1283)
    }

}