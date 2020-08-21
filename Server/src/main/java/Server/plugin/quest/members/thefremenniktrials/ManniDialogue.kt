package plugin.quest.members.thefremenniktrials

import core.game.node.entity.impl.Animator
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression

@InitializablePlugin
class ManniDialogue(player: Player? = Player(PlayerDetails("",""))) : DialoguePlugin(player){
    var curNPC: NPC? = NPC(0,Location(0,0,0))
    override fun open(vararg args: Any?): Boolean {
        curNPC = args[0] as? NPC
        if(player?.questRepository?.getStage("Fremennik Trials")!! > 0){
            if(player?.getAttribute("fremtrials:manni-accepted",false) == true) {
                if (player?.inventory?.containsItem(Item(3712)) == true || player?.inventory?.containsItem(Item(3711)) == true) {
                    npc("Ah, I see you have your keg of beer. Are ye ready to", "drink against each other?")
                    stage = 101
                    return true
                } else {
                    npc("Come back when you're ready to begin", "the contest.")
                    stage = 1000;
                    return true
                }
            } else if(player?.getAttribute("fremtrials:manni-vote",false) == true){
                npc("e have my vote!")
                stage = 1000
                return true
            }
            player("Hello there!")
            stage = 0
            return true
        } else {
            TODO("Uncertain what should go here")
        }
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> {npc("Hello outerlander. I overheard your conversation with","Brundt just now. You wish to become a member of the","Fremennik?");stage++}
            1 -> {player("That's right! Why, are you on the counsel?");stage++}
            2 -> {npc("Do not let my drink-ssussed appearance fool you, I","earnt my place on the council many years past.I am","always glad to see new blood enter our tribe, and will","happily vote for you.");stage++}
            3 -> {player("Great!");stage++}
            4 -> {npc("Providing you can pass a little test for me. As a","Fremennik, you will need to show cunning, stamina,","fastitude, and an iron constitution. I know of only one","way to test all of these.");stage++}
            5 -> {player("And what's that?");stage++}
            6 -> {npc("Why, a drinking contest!");stage++}
            7 -> {npc("The task is simple enough! You versus me, a stiff drink","each, last man standing wins the trial. So what say you?");stage++}
            8 -> {options("Yes","No");stage++}
            9 -> when(buttonId){
                    1 -> {player("A drinking contest? Easy. Set them up, and I'll knock","them back.");stage++}
                    2 -> {player("I don't like the sound of that.");stage = 12}
                }

                //Yes
                10 -> {npc("When you are ready to begin, go and pick up a keg","from that table over there, and come back here.");stage++}
                11 -> {npc("We start when you have your keg of beer with you","and finish when one of us can drink no more and","yields.");stage = 1000;player?.setAttribute("/save:fremtrials:manni-accepted",true)}

                //No
                12 -> {npc("That's a shame."); stage = 1000}


            //Begin drinking competition
            101 -> {options("Yes","No");stage++}
            102 -> when(buttonId){
                    1 -> {player("Yes, let's start this drinking contest!");stage++}
                    2 -> {player("No, I don't think I am.");stage = 1000}
                  }
            103 -> {npc("As you wish outerlander; I will drink first, then you will","drink.");stage++}
            104 -> {GameWorld.submit(DrinkingPulse(player,curNPC,player?.getAttribute("fremtrials:keg-mixed",false))); end()}

            1000 -> end()
        }
        return true
    }

    class DrinkingPulse(val player: Player?,val npc: NPC?, val lowAlcohol: Boolean? = false) : Pulse(){
        var counter = 0
        override fun pulse(): Boolean {
            if(!lowAlcohol!!){
                when(counter++){
                    0 -> {player?.lock();npc?.lock(); npc?.isNeverWalks = true}
                    1 -> {player?.face(npc); npc?.face(player)}
                    3 -> npc?.animator?.animate(Animation(1330,Animator.Priority.HIGH))
                    5 -> {player?.animator?.animate(Animation(1330,Animator.Priority.HIGH)); player?.inventory?.remove(Item(3711))}
                    7 -> player?.dialogueInterpreter?.sendDialogues(player,FacialExpression.DRUNK,"Ish no fair!! I canna drink another drop! I alsho","feel veddy, veddy ill...")
                    15 -> player?.dialogueInterpreter?.sendDialogues(npc,FacialExpression.DRUNK,"I guessh I win then ouddaladder! (hic) Niche try,","anyway!")
                    16 -> {player?.unlock(); npc?.unlock(); player?.face(player);npc?.face(npc); npc?.isNeverWalks = false; return true}
                }
            } else {
                when(counter++){
                    0 -> {player?.lock();npc?.lock(); npc?.isNeverWalks = true}
                    1 -> {player?.face(npc); npc?.face(player)}
                    3 -> npc?.animator?.animate(Animation(1330,Animator.Priority.HIGH))
                    5 -> {player?.animator?.animate(Animation(1330,Animator.Priority.HIGH)); player?.inventory?.remove(Item(3711))}
                    7 -> player?.dialogueInterpreter?.sendDialogues(player,FacialExpression.HAPPY,"Aaaah, lovely stuff. So you want to get the next round","in, or shall I? You don't look so good there!")
                    15 -> player?.dialogueInterpreter?.sendDialogues(npc,FacialExpression.DRUNK,"Wassha? Guh? You drank that whole keg! But it dinnna","affect you at all! I conshede! You can probably","outdrink me!")
                    21 -> player?.dialogueInterpreter?.sendDialogues(npc,FacialExpression.DRUNK,"I jusht can't (hic) believe it! Thatsh shome might fine","drinking legs you got! Anyone who can drink like","THAT getsh my vote atta somsh.... coumah... gets my","vote!")
                    22 -> {player?.unlock(); npc?.unlock(); player?.face(player);npc?.face(npc); npc?.isNeverWalks = false;player?.removeAttribute("fremtrials:cherrybomb");player?.removeAttribute("fremtrials:manni-accepted");player?.removeAttribute("fremtrials:keg-mixed"); player?.setAttribute("/save:fremtrials:manni-vote",true); player?.setAttribute("/save:fremtrials:votes",player.getAttribute("fremtrials:votes",0) + 1); return true}
                }
            }
            return false
        }
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return ManniDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(1286)
    }

}