package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.info.PlayerDetails
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class PoisonSalesman(player: Player? = Player(PlayerDetails("",""))) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        options("Talk about the Murder Mystery Quest","Talk about the Fremennik Trials")
        stage = 0
        return true
    }
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        val fremStage = player?.questRepository?.getStage("Fremennik Trials")
        when(stage){
            0 -> when(buttonId){
                1 -> {player("Err... nevermind");stage = 1000}
                2 -> {player("Hello.");stage = 10}
            }

            //Fremennik Trials
            10 -> if(fremStage!! == 0){ npc("Come see me if you ever need low-alcohol beer!"); stage = 1000}
                  else if(fremStage > 30){npc("Thanks for buying out all that low-alcohol beer!"); stage = 1000}
                  else if(fremStage > 0){npc("Howdy! You seem like someone with discerning taste!","Howsabout you try my brand new range of alcohol?");stage++;}
            11 -> {player("Didn't you used to sell poison?");stage++}
            12 -> {npc("That I did indeed! Peter Potter's Patented","Multipurpose poison! A miracle of modern apothecarys!","My exclusive concoction has been test on...");stage++}
            13 -> {player("Uh, yeah. I've already heard the sales pitch.");stage++}
            14 -> {npc("Sorry stranger, old habits die hard I guess.");stage++}
            15 -> {player("So you don't sell poison anymore?");stage++}
            16 -> {npc("Well I would, but I ran out of stock. Business wasn't","helped with that stuff that happened up at the Sinclair","Mansion much either, I'll be honest.");stage++}
            17 -> {npc("So, being the man of enterprise that I am I decided to","branch out a little bit!");stage++}
            18 -> {player("Into alcohol?");stage++}
            19 -> {npc("Absolutely! The basic premise between alcohol and poison","is pretty much the same, after all! The difference is that","my alcohol has a unique property others do not!");stage++}
            20 -> {player("And what is that?");stage++}
            21 -> {sendDialogue("The salesman takes a deep breath.");stage++}
            22 -> {npc("Ever been too drunk to find your own home? Ever","wished that you could party away all night long, and","still wake up fresh as a daisy the next morning?");stage++}
            23 -> {npc("Thanks to the miracles of modern magic we have come","up with just the solution you need! Peter Potter's","Patented Party Potions!");stage++}
            24 -> {npc("It looks just like beer! It tastes just like beer! It smells","just like beer! But... it's not beer!");stage++}
            25 -> {npc("Our mages have mused for many moments to bring","you this miracle of modern magic! It has all the great","tastes you'd expect, but contains absolutely no alcohol!");stage++}
            26 -> {npc("That's right! You can drink Peter Potter's Patented","Party Potion as much as you want, and suffer","absolutely no ill effects whatsoever!");stage++}
            27 -> {npc("The clean fresh taste you know you can trust, from","the people who brought you: Peter Potters Patented","multipurpose poison, Pete Potters peculiar paint packs");stage++}
            28 -> {npc("and Peter Potters paralyzing panic pins. Available now","from all good stockists! Ask your local bartender now,","and experience the taste revolution of the century!");stage++}
            29 -> {sendDialogue("He seems to have finished for the time being.");stage++}
            30 -> {player("So.. when you say 'all good stockists'...");stage++}
            31 -> {npc("Yes?");stage++}
            32 -> {player("How many inns actually sell this stuff?");stage++}
            33 -> {npc("Well.. nobody has actually bought any yet. Everyone I","try and sell it to always asks me what exactly the point","of beer that has absolutely no effect on you is.");stage++}
            34 -> {player("So what is the point?");stage++}
            35 -> {npc("Well... Um... Er... Hmmm. You, er, don't get drunk.");stage++}
            36 -> {player("I see...");stage++}
            37 -> {npc("Aw man.. you don't want any now do you? I've really","tried to push this product, but I just don't think the","world is ready for beer that doesn't get you drunk.");stage++}
            38 -> {npc("I'm a man ahead of my time I tell you! It's not that","my products are bad, it's that they're too good for the","market!");stage++}
            39 -> {player("Actually, I would like some. How much do you want for it?");stage++}
            40 -> {npc("Y-you would??? Um, okay! I knew I still had the old","salesmans skills going on!");stage++}
            41 -> {npc("I will sell you a keg of it for only 250 gold pieces! So","What do you say?");stage++}
            42 -> {options("Yes","No");stage++}
            43 -> when(buttonId){
                    1 -> {player("Yes please!");stage++}
                    2 -> {player("No, thanks.");stage = 1000}
                 }
                //Yes
                44 -> if(player?.inventory?.containsItem(Item(995,250)) == true){
                            player?.inventory?.remove(Item(995,250))
                            player?.inventory?.add(Item(3712))
                            npc("Here you go.")
                            stage = 1000
                       } else {
                            player("I don't seem to have enough.")
                            stage++
                       }
                45 -> {npc("Well come back when you do!");stage = 1000}


            1000 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return PoisonSalesman(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(820)
    }

}