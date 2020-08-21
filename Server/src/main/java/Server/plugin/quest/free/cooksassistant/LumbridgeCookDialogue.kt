package plugin.quest.free.cooksassistant

import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression

/**
 * Dialogue for the Lumbridge Cook.
 * @author Qweqker
 */

@InitializablePlugin
class LumbridgeCookDialogue (player: Player? = null) : DialoguePlugin(player){

    //Item declaration
    val EMPTY_BUCKET = 1925
    val MILK = 1927
    val EMPTY_POT = 1931
    val FLOUR = 1933
    val EGG = 1944

    //Default settings for the Cook checking for ingredients in the player's inventory
    var gave = false
    var leftoverItems = ""


    override fun open(vararg args: Any?): Boolean {
        if (player?.questRepository?.getQuest("Cook's Assistant")!!.getStage(player) <= 0) { //If the player has ot started cook's assistant
            npc(FacialExpression.SAD, "What am I to do?")
            stage = 0
            return true
        } else if (player?.questRepository?.getQuest("Cook's Assistant")!!.getStage(player) in 10..99) { //During the Cook's Assistant Quest
            if (player.getAttribute("cooks_assistant:all_submitted", false) || (player.getAttribute("cooks_assistant:milk_submitted", false) && player.getAttribute("cooks_assistant:flour_submitted", false) && player.getAttribute("cooks_assistant:egg_submitted", false))){ //If the player has handed all the ingredients to the chef but did not continue the dialogue
                npc(FacialExpression.HAPPY, "You've brought me everything I need! I am saved!", "Thank you!")
                stage = 200
                return true
            } else { //If the player has not handed all the items to the chef
                npc(FacialExpression.SAD, "How are you getting on with finding the ingredients?")
                gave = false //Resetting to false here to prevent dialogue skipping later
                stage = 100
                return true
            }
        }
        //After completing Cook's Assistant
        npc(FacialExpression.HAPPY, "Hello friend, how is the adventuring going?")
        stage = 300
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("What's wrong?", "Can you make me a cake?", "You don't look very happy.", "Nice hat!").also { stage++ }
            1 -> when(buttonId) {
                1 -> player(FacialExpression.NEUTRAL, "What's wrong?").also { stage = 10 }
                2 -> player(FacialExpression.ASKING, "You're a cook, why don't you bake me a cake?").also { stage = 20 }
                3 -> player(FacialExpression.NEUTRAL,"You don't look very happy.").also { stage = 30; }
                4 -> player(FacialExpression.HAPPY, "Nice hat!").also { stage = 40 }
            }

            //What's wrong?
            10 -> npc(FacialExpression.SCARED, "Oh dear, oh dear, oh dear, I'm in a terrible terrible", "mess! It's the Duke's birthday today, and I should be", "making him a lovely big birthday cake.").also { stage++ }
            11 -> npc(FacialExpression.SAD, "I've forgotten to buy the ingredients. I'll never get", "them in time now. He'll sack me! What will I do? I have", "four children and a goat to look after. Would you help", "me? Please?").also { stage++ }
            12 -> options("I'm always happy to help a cook in distress.", "I can't right now, Maybe later.").also { stage++ }
            13 -> when(buttonId) {
                1 -> player(FacialExpression.HAPPY, "Yes, I'll help you.").also { stage = 50 }
                2 -> player(FacialExpression.ANNOYED, "No, I don't feel like it. Maybe later.").also { stage++ }
            }

            //I can't right now
            14 -> npc(FacialExpression.SAD, "Fine. I always knew you Adventurer types were callous", "beasts. Go on your merry way!").also { stage = 1000 }

            //Can you make me a cake?
            20 -> npc(FacialExpression.SAD, "*sniff* Don't talk to me about cakes...").also { stage++ }
            21 -> player(FacialExpression.NEUTRAL, "What's wrong?").also { stage = 10}

            //You don't look very happy
            30 -> npc(FacialExpression.SAD, "No, I'm not. The world is caving in around me - I am", "overcome by dark feelings of impending doom.").also { stage++ }
            31 -> options("What's wrong?","I'd take the rest of the day off if I were you.").also { stage++ }
            32 -> when(buttonId) {
                1 -> player(FacialExpression.NEUTRAL, "What's wrong?").also { stage = 10}
                2 -> player(FacialExpression.NEUTRAL,"I'd take the rest of the day off if I were you.").also { stage++ }
            }
            33 -> npc(FacialExpression.SAD,"No, that's the worst thing I could do. I'd get in terrible","trouble.").also { stage++ }
            34 -> player(FacialExpression.ASKING,"Well maybe you need to take a holiday...").also { stage++ }
            35 -> npc(FacialExpression.SAD,"That would be nice, but the Duke doesn't allow holidays","for core staff").also { stage++ }
            36 -> player(FacialExpression.SUSPICIOUS,"Hmm, why not run away to the sea and start a new","life as a Pirate?").also { stage++ }
            37 -> npc(FacialExpression.SAD,"My wife gets sea sick, and I have an irational fear of","eyepatches. I don't see it working myself.").also { stage++ }
            38 -> player(FacialExpression.NEUTRAL,"I'm afraid I've run out of ideas.").also { stage++ }
            39 -> npc(FacialExpression.SAD,"I know I'm doomed.").also { stage = 21 }

            //Nice hat!
            40 -> npc(FacialExpression.SAD, "Er, thank you. It's a pretty ordinary cook's hat, really.").also { stage++ }
            41 -> player(FacialExpression.HAPPY, "Still, it suits you. The trousers are pretty special too.").also { stage++ }
            42 -> npc(FacialExpression.SAD, "It's all standard-issue cook's uniform.").also { stage++ }
            43 -> player(FacialExpression.HAPPY, "The whole hat, apron, stripy trousers ensemble. It", "works. It makes you looks like a real cook.").also { stage++ }
            44 -> npc(FacialExpression.ANGRY, "I AM a real cook! I haven't got time to be chatting", "about culinary fashion. I'm in desperate need of help!").also { stage = 21 }

            //Yes, I'll help you
            50 -> npc(FacialExpression.HAPPY, "Oh thank you, thank you. I need milk, an egg and", "flour. I'd be very grateful if you can get them for me.").also{ player?.questRepository?.getQuest("Cook's Assistant")?.start(player!!); stage++ }
            51 -> player(FacialExpression.NEUTRAL, "So where do I find these ingredients then?").also { stage = 60 }

            //Where do I find these ingredients?
            60 -> options("Where do I find some flour?","How about milk?","And eggs? Where are they found?","Actually, I know where to find this stuff.").also { stage++ }
            61 -> when(buttonId) {
                1 -> npc(FacialExpression.NEUTRAL,"There is a Mill fairly close, go North and then West.","Mill Lane Mill is just off the road to Draynor. I","usually get my flour from there.").also {stage = 70 }
                2 -> npc(FacialExpression.NEUTRAL,"There is a cattle field on the other side of the river,","just across the road from the Groats' Farm.").also { stage = 71 }
                3 -> npc(FacialExpression.NEUTRAL,"I normally get my eggs from the Groats' farm, on the","other side of the river.").also { stage = 73 }
                4 -> player(FacialExpression.NEUTRAL,"Actually, I know where to find this stuff.").also { stage = 1000 }
            }

            //Where do I find some flour?
            70 -> npc(FacialExpression.SUSPICIOUS,"Talk to Millie, she'll help, she's a lovely girl and a fine","Miler. Make sure you take a pot with you for the flour","though, " + if (player.inventory.contains(EMPTY_POT, 1)) "you've got one on you already." else "there should be one on the table in here.").also { stage = 80 }

            //How about milk?
            71 -> npc(FacialExpression.SUSPICIOUS,"Talk to Gillie Groats, she looks after the Dairy cows -","she'll tell you everything you need to know about","milking cows!").also { stage++ }
            72 ->
                if (player.inventory.contains(EMPTY_BUCKET , 1)) {
                        npc(FacialExpression.NEUTRAL,"You'll need an empty bucket for the milk itself. I do see", "you've got a bucket with you already luckily!").also { stage = 80 }
                } else {
                    npc(FacialExpression.NEUTRAL,"You'll need an empty bucket for the milk itself. The", "general store just north of the castle will sell you one", "for a couple of coins.").also { stage = 80 }
                }

            //And Eggs?
            73 -> npc(FacialExpression.NEUTRAL,"But any chicken should lay eggs.").also { stage = 80 }

            //Alternative menu for "Where do I find these ingredients?"
            80 -> options("Where do I find some flour?","How about milk?","And eggs? Where are they found?","I've got all the information I need. Thanks.").also { stage++ }
            81 -> when(buttonId) {
                1 -> npc(FacialExpression.NEUTRAL,"There is a Mill fairly close, go North and then West.","Mill Lane Mill is just off the road to Draynor. I","usually get my flour from there.").also {stage = 70 }
                2 -> npc(FacialExpression.NEUTRAL,"There is a cattle field on the other side of the river,","just across the road from the Groats' Farm.").also { stage = 71 }
                3 -> npc(FacialExpression.NEUTRAL,"I normally get my eggs from the Groats' farm, on the","other side of the river.").also { stage = 73 }
                4 -> player(FacialExpression.NEUTRAL,"I've got all the information I need. Thanks.").also { stage = 1000 }
            }

            100 ->
                if (!player.getAttribute("cooks_assistant:milk_submitted", false) && player.inventory.contains(MILK, 1)) {
                    player.setAttribute("/save:cooks_assistant:milk_submitted", true).also {
                        player(FacialExpression.HAPPY, "Here's a bucket of milk.");
                        player.inventory.remove(Item(MILK));
                        gave = true
                        stage = 100
                    }
                } else if (!player.getAttribute("cooks_assistant:flour_submitted", false) && player.inventory.contains(FLOUR, 1)) {
                    player.setAttribute("/save:cooks_assistant:flour_submitted", true).also {
                        player(FacialExpression.HAPPY, "Here's a pot of flour.");
                        player.inventory.remove(Item(FLOUR));
                        gave = true
                        stage = 100
                    }
                } else if (!player.getAttribute("cooks_assistant:egg_submitted", false) && player.inventory.contains(EGG, 1)) {
                    player.setAttribute("/save:cooks_assistant:egg_submitted", true).also {
                        player(FacialExpression.HAPPY, "Here's a fresh egg.");
                        player.inventory.remove(Item(EGG));
                        gave = true
                        stage = 100
                    }
                } else {
                    if (gave) {

                        //If this is the first time the player gave an item (or items) to the Lumbridge cook
                        if (!player.getAttribute("cooks_assistant:submitted_some_items", false)) {
                            player.setAttribute("/save:cooks_assistant:submitted_some_items", true)
                        }

                        //If the player has now handed in all the ingredients
                        if (player.getAttribute("cooks_assistant:milk_submitted", false) && player.getAttribute("cooks_assistant:flour_submitted", false) && player.getAttribute("cooks_assistant:egg_submitted", false)) {
                            npc(FacialExpression.HAPPY, "You've brought me everything I need! I am saved!", "Thank you!").also { player.setAttribute("/save:cooks_assistant:all_submitted",true); stage = 200 }
                        } else {
                            npc(FacialExpression.WORRIED,"Thanks for the ingredients you have got so far, please get","the rest quickly. I'm running out of time! The Duke","will throw me into the streets!").also { stage = 151 }
                        }

                    } else { //If the player did not give an item to the Lumbridge cook

                        //If the player also has never submitted anything before
                        if (!player.getAttribute("cooks_assistant:submitted_some_items", false)) {
                            player(FacialExpression.NEUTRAL, "I haven't gotten any of them yet, I'm still looking.").also { stage = 155 }
                        } else {
                            options("I'll get right on it.", "Can you remind me how to find these things again?").also { stage = 161 }
                        }
                    }
                }

            //If the player has submitted some ingredients but not all of them
            150 -> npc(FacialExpression.WORRIED,"Thanks for the ingredients you have got so far, please get","the rest quickly. I'm running out of time! The Duke","will throw me into the streets!").also { stage++ }

            //Checking what the player has left over
            151 -> leftoverItems = "".also{
                if (!player.getAttribute("cooks_assistant:milk_submitted", false)){
                    leftoverItems += "A bucket of milk. ";
                }
                if (!player.getAttribute("cooks_assistant:flour_submitted", false)){
                    leftoverItems += "A pot of flour. ";
                }
                if (!player.getAttribute("cooks_assistant:egg_submitted", false)){
                    leftoverItems += "An egg.";
                }
                if (leftoverItems != ""){
                    sendDialogue("You still need to get:",leftoverItems).also {stage = 160}
                } else {
                    npc(FacialExpression.HAPPY, "You've brought me everything I need! I am saved!", "Thank you!").also { player.setAttribute("/save:cooks_assistant:all_submitted",true); stage = 200 }
                }
            }

            //If the player has yet to collect or hand in any of the ingredients
            155 -> npc(FacialExpression.WORRIED,"Please get the ingredients quickly. I'm running out of","time! The Duke will throw me into the streets!").also { stage++ }
            156 -> sendDialogue("You still need to get:", "A bucket of milk. A pot of flour. An egg.").also { stage = 160 }


            // Menu after checking the items handed in
            160 -> options("I'll get right on it.","Can you remind me how to find these things again?").also { stage++ }
            161 -> when(buttonId) {
                1 -> player(FacialExpression.NEUTRAL,"I'll get right on it.").also { stage = 1000 }
                2 -> player(FacialExpression.ASKING,"Can you remind me how to find these things again?").also { stage = 60 }
            }

            //Final Cooks Assistant Dialogue
            200 -> player(FacialExpression.HAPPY, "So do I get to go to the Duke's Party?").also { stage++ }
            201 -> npc(FacialExpression.SAD, "I'm afraid not, only the big cheeses get to dine with the", "Duke.").also { stage++ }
            202 -> player(FacialExpression.NEUTRAL, "Well, maybe one day I'll be important enough to sit on", "the Duke's table.").also { stage ++ }
            203 -> npc(FacialExpression.NEUTRAL, "Maybe, but I won't be holding my breath.").also { stage++ }

            //Activate the Cook's Assistant Quest Complete Certificate
            204 -> end().also { player?.questRepository?.getQuest("Cook's Assistant")?.finish(player!!) }

            //Dialogue after Cook's Assistant Completion
            300 -> options("I am getting strong and mighty.", "I keep on dying.", "Can I use your range?").also { stage++ }
            301 -> when (buttonId) {
                1 -> player(FacialExpression.HAPPY, "I am getting strong and mighty. Grrr").also { stage = 310 }
                2 -> player(FacialExpression.SAD, "I keep on dying.").also { stage = 320 }
                3 -> player(FacialExpression.ASKING, "Can I use your range?").also { stage = 330 }
            }

            //I am getting strong and mighty
            310 -> npc(FacialExpression.HAPPY,"Glad to hear it!").also { stage = 1000 }

            //I keep on dying
            320 -> npc(FacialExpression.HAPPY, "Ah, well, at least you keep coming back to life too!").also { stage = 1000 }

            //Can I use your range?
            330 -> npc(FacialExpression.HAPPY, "Go ahead! It's a very good range; it's better than most" ,"other ranges.").also { stage++ }
            331 -> npc(FacialExpression.NEUTRAL, "It's called the Cook-o-Matic 100 and it uses a combination","of state-of-the-art temperature regulation and magic.").also { stage++ }
            332 -> player(FacialExpression.ASKING,"Will it mean my food will burn less often?").also { stage++ }
            333 -> npc(FacialExpression.NEUTRAL,"Well, that's what the salesman told us anyway...").also { stage++ }
            334 -> player(FacialExpression.THINKING, "Thanks?").also { stage = 1000 }

            //Conversation Endpoint
            1000 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return LumbridgeCookDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(278)
    }
}