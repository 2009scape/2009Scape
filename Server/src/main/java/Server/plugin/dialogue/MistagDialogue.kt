package plugin.dialogue

import core.game.component.Component
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.plugin.InitializablePlugin
import core.tools.Items
import plugin.activity.ActivityManager
import plugin.quest.members.thelosttribe.GoblinFollower

@InitializablePlugin
class MistagDialogue (player: Player? = null) : DialoguePlugin(player){
    override fun newInstance(player: Player?): DialoguePlugin {
        return MistagDialogue(player)
    }

    override fun npc(vararg messages: String?): Component {
        return npc(FacialExpression.OLD_NORMAL,*messages)
    }

    override fun open(vararg args: Any?): Boolean {
        if(args.size > 0 && args[0] == "greeting"){
            npc("A human knows ancient greeting?")
            stage = 100
            return true
        }
        if(player.questRepository.getQuest("Death to the Dorgeshuun").hasRequirements(player)){
            npc("It is good to see you again! The Dorgeshuun Council " ,
                    "would like to ask a favour of you, if you are interested.")
            stage = 200
            return true
        }

        if(!player.getAttribute("mistag-greeted",false)){
            npc("Who...who are you? How did you get in here?")
            stage = -100
            return true
        }
        if(player.questRepository.getQuest("Lost Tribe").getStage(player) == 45){
            npc("Greetings, friend. I am sorry I panicked when I saw you.")
            stage = 102
            return true
        }
        if(player.questRepository.getQuest("Lost Tribe").getStage(player) == 50){
            npc("Hello, friend?")
            stage = 150
            return true
        }

        npc("Hello friend!").also { stage = 0 }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            //Pre-greeting
            -100 -> npc("Help! A surface dweller this deep in our mines? We will","all be destroyed!").also { stage++ }
            -99 -> end()

            //Normal Dialogue
            0 -> options("May I mine the rocks here?","Can you show me the way out?").also { stage++ }
            1 -> when(buttonId){
                1 -> player("May I mine the rocks here?").also { stage = 10 }
                2 -> player("Can you show me the way out of the mine?").also { stage = 20 }
            }
            10 -> npc("Certainly, friend!").also { stage = 1000 }
            20 -> npc("Certainly!").also { stage++ }
            21 -> end().also {
                GoblinFollower.sendToLumbridge(player)
            }

            //Greeting dialogue
            100 -> npc("Perhaps you are a friend after all!").also { stage++ }
            101 -> npc("Greetings, friend. I am sorry I panicked when I saw you.").also { stage++ }
            102 -> npc("Our legends tell of the surface as a place of horror and","violence, where the gods forced us to fight in terrible","battles.").also { stage++ }
            103 -> npc("When I saw a surface-dweller appear I was afraid it","was a return to the old days!").also { stage++ }
            104 -> player("Did you break in to the castle cellar?").also { stage++ }
            105 -> npc("It was an accident. We were following a seam of iron","and suddenly we found ourselves in a room!").also { stage++ }
            106 -> npc("We blocked up our tunnel behind us and ran back","here. Then we did what cave goblins always do when","there is a problem: we hid and hoped it would go away.").also { stage++ }
            107 -> npc("We meant no harm! Please tell the ruler of the above","people that we want to make peace.").also { stage = 1000; player.questRepository.getQuest("Lost Tribe").setStage(player,46) }

            //Peace treaty dialogue
            150 -> player("I have a peace treaty from the Duke of Lumbridge.").also { stage++ }
            151 -> npc("A peace treaty? Then you will not invade?").also { stage++ }
            152 -> player("No. As long as you stick to the terms of this treaty","there will be no conflict. The Duke of Lumbridge wants","to meet your ruler to sign it.").also { stage++ }
            153 -> npc("I will summon Ur-tag, our headman, at once.").also { stage++ }
            154 -> {
                end()
                ActivityManager.start(player,"Lost Tribe Cutscene",false)
            }

            200 -> options("What is this favour?",
                    "Can I sell you some ore?",
                    "Why do the Dorgeshuun live underground?",
                    "What happened to your arm?",
                    "Can you show me the way out of the mines?").also{stage++}
            201-> when(buttonId){
                1 -> stage = 1000
                2 -> stage = 1000
                3 -> stage = 1000
                4 -> stage = 1000
                5 -> stage = 20
            }
            1000 -> end()

        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(2084)
    }

}