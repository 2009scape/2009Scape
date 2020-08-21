package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class ChieftanBrundt(player: Player? = Player(PlayerDetails("",""))) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        if(player?.questRepository?.getStage("Fremennik Trials")!! == 0) {
            npc("Greetings outlander!")
            stage = 0
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            //Pre-Quest
            0 -> { options("What is this place?", "Why will no-one talk to me?", "Do you have any quests?", "Nice hat!");stage++}
            1 -> when(buttonId){
                    1 -> TODO("Not yet implemented")
                    2 -> TODO("Not yet implemented")
                    3 -> {player("Do you have any quests?");stage = 300}
                    4 -> TODO("Not yet implemented")
                 }

            //Do you have any quests?
            300 -> {npc("Quests, you say outlander? Well, I would not call it a","quest as such, but if you are brave of heart and strong","of body, perhaps..."); stage++}
            301 -> {npc("No, you would not be interested. Forget I said","anything, outerlander.");stage++}
            302 -> {options("Yes, I am interested.","No, I'm not interested.");stage++}
            303 -> when(buttonId){
                    1 -> {player("Actually, I would be very interested to hear what you","have to offer.");stage = 310}
                    2 -> {player("No, I'm not interested.");stage = 340}
                  }

                //Yes, I am interested in what you have to say.
                310 -> {npc("You would? These are unusual sentiments to hear from","an outerlander! My suggestion was going to be that if","you crave adventure and battle,"); stage++}
                311 -> {npc("and your heart sings for glory, then perhaps you would","be interested in joining our clan, and becoming a","Fremennik yourself?");stage++}
                312 -> {player("What would that involve exactly?");stage++}
                313 -> {npc("Well, there are two ways to become a member of our","clan and call yourself a Fremennik: be born a","Fremennik, or be voted in by our council of elders.");stage++}
                314 -> {player("Well, I think I've missed the first way, but how can I","get the council of elders to vote to let me join your","clan?");stage++}
                315 -> {npc("Well, that I cannot answer myself. You will need to","speak to each of them and see what they require of you","as proof of your dedication.");stage++}
                316 -> {npc("There are twelve council members around this village;","you will need to gain a majority vote of at least seven","councillors in your favor.");stage++}
                317 -> {npc("So, what say you? Give me the word and I will tell all","of my tribe of your intentions, be they yes or nay.");stage++}
                318 -> {options("I want to become a Fremennik!","I don't want to become a Fremennik.");stage++}
                319 -> when(buttonId){
                        1 -> {player("I think I would enjoy the challenge of becoming an","honorary Fremennik. Where and how do I start?");stage = 320}
                        2 -> {player("That sounds too complicated to me.");stage = 322}
                      }

                    //I think I would enjoy the challenge of becoming an honorary fremennik
                    320 -> {npc("As I say outerlander, you must find and speak to the","twelve members of the council of elders, and see what","tasks they might set you.");stage++}
                    321 -> {npc("If you can gain the support of seven of the twelve, then","you will be accepted as one of us without question.");stage = 1000;player?.questRepository?.getQuest("Fremennik Trials")?.start(player)}

                    //That sounds too complicated for me.
                    322 -> {npc("Well, that's what I expect from an outerlander.");stage = 1000}

                //No, I'm not interested
                340 -> TODO("Not implemented yet")


            1000 -> end()
        }
        return true
    }


    override fun newInstance(player: Player?): DialoguePlugin {
        return ChieftanBrundt(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(1294)
    }

}