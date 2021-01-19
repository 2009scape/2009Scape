package plugin.quest.members.allfiredup

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin
import plugin.dialogue.FacialExpression

@InitializablePlugin
class BlazeSharpeyeDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun newInstance(player: Player?): DialoguePlugin {
        return BlazeSharpeyeDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = (args[0] as NPC).getShownNPC(player)
        val qstage = player?.questRepository?.getStage("All Fired Up") ?: -1
        when(qstage){
              0 -> player.dialogueInterpreter.sendDialogue("He seems uninterested in talking.").also { stage = 1000 }
             10 -> player("So, what's going on?").also { stage = 100 }
             20 -> npc("Well, go on, light the fire, then.").also { stage = 1000 }
             30 -> npc("Ah, I see you've managed to successfully light the","beacon and have mastered our super-secret beacon","lighting technique. Well done!").also { stage = 200 }
             40,50 -> npc("Please go and light the other beacon.").also { stage = 116 }
             60 -> npc("Ah, ${player.username}, you're back! Great news - I can see","you've managed to light the beacon over yonder. Many","thanks!").also { stage = 300 }
             70 -> npc("Well, GO ON, BURN IT!!!").also { stage = 1000 }
             80 -> player("I've added some logs to the beacon; it's now blazing","away.").also { stage = 400 }
             90 -> npc("Go talk to King Roald!").also { stage = 1000 }
             else -> npc("Ah yes, hello adventurer!").also { stage = 600 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            //Stage = 10
            100 -> npc("Well, King Roald has commissioned these lovely beacons","to serve as an early warning system. We'll be able to","see their huge, majestic flames from great distances.").also { stage++ }
            101 -> npc("There are 14 beacons in total, spanning the south","and west borders of the Wilderness.").also { stage++ }
            102 -> player("It sounds like you've already got everything figured","out. How do I fit into this?").also { stage++ }
            103 -> npc("Well, to test these beacons, we need someone to light a","couple of them for us, so that we can determine","whether or not they'll actually be visible.").also { stage++ }
            104 -> player("You mean, you went out and built these huge things","without knowing that they'd work?").also { stage++ }
            105 -> npc("Dont interrupt. Now, yours truly has recruited some","of the sharpest eyes this side of the River Salve to look","after these beacons and to watch the horizon for signs","of danger.").also { stage++ }
            106 -> npc("In case of emergency, we've all got a supply of logs","and can start a fire faster than a cat can sneeze.").also { stage++ }
            107 -> npc("As soon as we spot a threat from the Wilderness or the","glow from another beacon, we can light our own","beacons and send a warning right across Misthalin,","faster than any courier could ever run.").also { stage++ }
            108 -> npc("We've agreed to use gnomish firelighters to colour the","flames of our beacons' fires in an actual emergency, so","it's safe to test the network with normal fires.").also { stage++ }
            109 -> player("So, what do you want me to actually do?").also { stage++ }
            110 -> npc("I need you to light a couple of beacons for me to make","sure we can see the glow from the flames over the","horizon.").also { stage++ }
            111 -> npc("So! First of all, you'll need to know how to light a beacon.").also { stage++ }
            112 -> npc("Our technique is super-secret, but quite effective. All","you do is put twenty logs of the same type on a","beacon and...").also { stage++ }
            113 -> npc(FacialExpression.AMAZED,"SET IT ON FIRE WITH A TINDERBOX").also { stage++ }
            114 -> player("You really enjoy your job, don't you?").also { stage++ }
            115 -> npc("Yes. Yes I do. Now, why don't you go over there and","try lighting that beacon. Show us what you've got.").also { stage++; player.questRepository.getQuest("All Fired Up").setStage(player,20) }
            116 -> options("Does it matter what type of log I use?","Okay.").also { stage++ }
            117 -> when(buttonId){
                1 -> player("Does it matter what type of log I use?").also { stage = 150 }
                2 -> player("Okay.").also { stage = 1000 }
            }
            150 -> npc("Normal, Oak, Willow, Maple, Yew and Magic.").also { stage = 1000 }

            //Stage = 30
            200 -> player("Well, it's not like it was all that complicated.").also { stage++ }
            201 -> npc("Well, apparently, not for someone of your Firemaking","calibre and expertise. Now that you've got the hang of","things, we can get this show on the road.").also { stage++ }
            202 -> npc("If you'd be so kind as to light the beacon to the west","and report back to me, I can make sure I can clearly","see its glow on the horizon.").also { stage++ }
            203 -> npc("It's near the limestone quarry, north-east of Varrock,","west of the Rag and Bone Man's hovel.").also { stage++ }
            204 -> npc("My colleague, Squire Fyre, is tending that beacon.","She'll help you out if you run into any trouble.").also { stage = 116; player.questRepository.getQuest("All Fired Up").setStage(player,40) }

            //Stage = 60
            300 -> player("Can you really see it from this far away?").also { stage++ }
            301 -> npc("Absolutely! I can see a warm, delicious glow on the","horizon. If you have sharp eyes and know what to look","for it's trivial.").also { stage++ }
            302 -> npc("This beacon, however, is struggling at the moment.","Do you see how the fire has died down?").also { stage++ }
            303 -> player("Hmm, yes. The fire is a bit smaller and the logs look","rather charred.").also { stage++ }
            304 -> npc("If a beacon's fire starts to die down, you can restor it","to its blazing glory by adding five logs.").also { stage++ }
            305 -> npc("You wouldn't mind topping this one up for me, would","you? Oh, how I love to see things burn!").also { stage++; player.questRepository.getQuest("All Fired Up").setStage(player, 70) }
            306 -> options("Oh, alright, then.","Don't you have logs of your own you can use?").also { stage++ }
            307 -> when(buttonId){
                1 -> player("Oh, alright, then.").also { stage++ }
                2 -> player("Don't you have logs of your own that you","can use?").also { stage = 310 }
            }
            308 -> npc("MORE FIRE for ME!!!").also { stage++ }
            309 -> player("Backing away now...").also { stage = 1000 }
            310 -> npc("Nope.").also { stage = 1000 }


            //Stage = 80
            400 -> npc("Beautiful...mesmerising...").also { stage++ }
            401 -> player("Uh...Blaze?").also { stage++ }
            402 -> npc("Ah, yes, it's you again. Brilliant work; an incandescent","effort.").also { stage++ }
            403 -> player("So, what now?").also { stage++ }
            404 -> npc("We're all pretty pleased with the results of the trial run","and are gearing up for more serious testing. There's a","lot at stake, so we'll need to test out the entire network","as soon as possible.").also { stage++ }
            405 -> options("When's all this to take place?","More serious testing?").also { stage++ }
            406 -> when(buttonId){
                1 -> player("When's all this taking place?").also { stage = 410 }
                2 -> player("More serious testing?").also { stage = 420 }
            }
            410 -> npc("Imminently, I'm sure - we're just waiting for the word","from King Roald. Speaking of which, have you reported","back to him about the progress we've made?").also { stage++ }
            411 -> player("Not yet, I'm afraid.").also { stage++ }
            412 -> npc("Well, what are you waiting for? This is a serious","matter! I'm sure King Roald is on the edge of his","throne, waiting for the news.").also { stage++ }
            413 -> player("I'll get right on that.").also { stage = 1000; player.questRepository.getQuest("All Fired Up").setStage(player,90) }
            420 -> npc("Yes... YESS HAHAHAHHA FIRE").also { stage = 412 }

            1000 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(8065)
    }

}