package plugin.worldevents.holiday

import core.game.node.entity.combat.ImpactHandler
import core.game.node.entity.player.Player
import core.game.node.item.Item
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression

class GrimDialogue(player: Player? = null) : DialoguePlugin(player){
    var firstSpeak = true
    val candy = Item(14084)
    override fun newInstance(player: Player?): DialoguePlugin {
        return GrimDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        firstSpeak = !player.getAttribute("hween:grim_spoken",false)

        if(firstSpeak){
            npc("YOU! Yes.... you! Come here!")
            stage = 0
        } else {
            npc("Hello, again, adventurer...")
            stage = 100
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> player(FacialExpression.AFRAID,"W-what... what do you want with","me?").also { stage++ }
            1 -> npc("I want you.... I NEED you....").also { stage++ }
            2 -> npc("TO BRING ME CANDY! Yes, candy...").also { stage++ }
            3 -> player(FacialExpression.THINKING,"Candy...? You want me to bring","you... candy?").also { stage++ }
            4 -> npc("Yes, candy! Did I not speak clearly","enough?").also { stage++ }
            5 -> player(FacialExpression.ASKING,"Well how do I even get candy?").also { stage++ }
            6 -> npc("It seems my candy collection has been scattered","into everything in 2009Scape!").also { stage++ }
            7 -> npc("I broke open a rock earlier when I was moving","my chair here, and I found one!").also { stage++ }
            8 -> npc("I suspect some of the vile creatures of","2009Scape have gotten ahold of some as well.").also { stage++ }
            9 -> npc("I need YOU to go collect this for me.").also { stage++ }
            10 -> player(FacialExpression.THINKING,"And what will I get in exchange?").also { stage++ }
            11 -> npc("Well I won't KILL YOU for starters.").also { stage++ }
            12 -> player(FacialExpression.ANGRY_WITH_SMILE, "Is that it?!").also { stage++ }
            13 -> npc("Well, I guess I could also give you this","odd currency. I suspect one of these mortal","shops allows you to buy holiday items with it.").also { stage++ }
            14 -> player(FacialExpression.AMAZED, "YOU MEAN CREDITS?!").also { stage++ }
            15 -> npc("Yes, I suppose I do.").also { stage++ }
            16 -> npc("I will give you 2 credits for every candy","you bring me.").also { stage++ }
            17 -> npc("NOW GET TO WORK!").also { player.setAttribute("/save:hween:grim_spoken",true); stage = 1000 }


            100 -> npc("I do hope you have... candy for me?").also { stage++ }
            101 -> if(player.inventory.containsItem(candy)){
                    player("Yes, I do! Here you go.").also { stage = 150 }
                } else {
                    player(FacialExpression.SAD, "No, I don't.").also { stage++ }
                }
            102 -> npc("THEN GET TO WORK!").also { player.impactHandler.manualHit(player,5,ImpactHandler.HitsplatType.DISEASE); stage++ }
            103 -> player("YES SIR!").also { stage = 1000 }


            150 -> {
                val candies = player.inventory.getAmount(candy)
                player.inventory.remove(Item(candy.id,candies))
                player.details.credits += candies * 2
                npc("Thank you, adventurer, I have awarded you","with ${candies * 2} 'credits.'").also { stage = 1000 }
            }


            1000 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(6390)
    }

}