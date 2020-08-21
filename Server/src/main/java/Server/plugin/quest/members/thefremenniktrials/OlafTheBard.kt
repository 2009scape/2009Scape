package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class OlafTheBard(player: Player? = Player(PlayerDetails("",""))) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        if(player?.getAttribute("fremtrials:olaf-accepted",false)!!){
            npc("I can't wait to see your performance.")
            stage = 1000
            return true
        }
        npc("Hello? Yes? You want something outerlander?")
        stage = 0;
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> player("Are you a member of the council?").also { stage++ }
            1 -> npc("Why, indeed I am, outerlander! My talents as an","exemplary musician made it difficult for them not to","accept me! Why do you wish to know this?").also { stage++ }
            2 -> player("Well, I ask because I am currently doing the","Fremennik trials so as to join you clan. I need seven","of the twelve council of elders to vote for me.").also { stage++ }
            3 -> npc("Ahhh... and you wish to earn my vote? I will gladly","accept you as a Fremennik should you be able to prove","yourself to have a little musical ability!").also { stage++ }
            4 -> player("So how would I do that?").also { stage++ }
            5 -> npc("Why, by playing in our longhall! All you need to do is","impress the revellers there with a few verses of an epic","of your own creation!").also { stage++ }
            6 -> npc("So what say you outerlander? Are you up for the","challenge?").also { stage++ }
            7 -> options("Yes","No").also { stage++ }
            8 -> when(buttonId){
                    1 -> player("Sure! This certainly sounds pretty easy to accomplish.","I'll have your vote in no time!").also { stage++ }
                    2 -> player("No, that sounds like too much work.").also { stage = 1000 }
                 }

                //Yes
                9 -> npc("That is great news outerlander! We always need more","music lovers here!").also { stage++;player?.setAttribute("/save:fremtrials:olaf-accepted",true) }
                10 -> player("So how would I go about writing this epic?").also { stage++ }
                11 -> npc("Well, first of all you are going to need an instrument.","Like all true bards you are going to have to make this","yourself.").also { stage++ }
                12 -> player("How do I make an instrument?").also { stage++ }
                13 -> npc("Well, it is a long and drawn-out process. Just east of","this village there is an unusually musical tree that can","be used to make very high quality instruments.").also { stage++ }
                14 -> npc("Cut a piece from it, and then carve it into a special","shape that will allow you to string it. Using a knife as","you would craft any other wooden object would be best","for this.").also { stage++ }
                15 -> player("Then what do I need to do?").also { stage++ }
                16 -> npc("Next you will need to string your lyre. There is a troll","to the South-east who has some golden wool. I would","not recommend using anything else to string your lyre","with.").also { stage++ }
                17 -> player("Anything else?").also { stage++ }
                18 -> npc("Well, when you have crafted your lyre you will need","the blessing of the Fossegrimen to tune your lyre to","perfection befre you even consider a public","performance.").also { stage++ }
                19 -> player("Who or what is Fossegrimen?").also { stage++ }
                20 -> npc("Fossegrimen is a lake spirit that lives just a little way","South-west of this ivllage. Make her an offering of fish,","and you will then be ready for your performance.").also { stage++ }
                21 -> npc("Make sure you give her a suitable offering however. If","the offering is found to be unworthy, then you may","find yourself unable to play your lyre with any skill at","all!").also { stage++ }
                22 -> player("So what would be a worthy offering?").also { stage++ }
                23 -> npc("A raw shark, manta ray, or sea turtle should be","sufficient as an offering.").also { stage++ }
                24 -> player("Ok, what else do I need to do?").also { stage++ }
                25 -> npc("When you have crafted your lyre and been blessed by","Fossegrimen, then you will finally be ready to make","your performance to the revellers at the longhall.").also { stage++ }
                26 -> npc("Head past the bouncers and onto the stage, and then","begin to play. If all goes well, you should find the music","spring to your mind and sing your own epic on the","spot").also { stage++ }
                27 -> npc("I will observe both you and the audience, and if you","show enough talent, I will happily vote in your favour at","the council of elders.").also { stage++ }
                28 -> npc("Is that clear enough, outerlander? Would you like me","to repeat anything?").also { stage++ }
                29 -> player("No thanks. I think I've got it.").also { stage = 1000 }

            1000 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return OlafTheBard(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(1269)
    }
}