package plugin.dialogue

import core.Util
import core.game.content.ItemNames
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import java.util.concurrent.TimeUnit

@InitializablePlugin
class GeoffreyDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        val diary = player.achievementDiaryManager.getDiary(DiaryType.SEERS_VILLAGE)
        if (diary.levelRewarded.any()) {
            player("Hello there. Are you Geoff-erm-Flax? I've been told that", "you'll give me some flax.")
            // If 1 day has not passed since last flax reward
            if (player.getAttribute("diary:seers:flax-timer", 0) > System.currentTimeMillis()) {
                stage = 98
                return true
            }
            // If player cannot receive flax reward
            if (!player.inventory.hasSpaceFor(Item(ItemNames.FLAX_NOTED, 1))) {
                stage = 99
                return true
            }
            // Determine flax reward by seers diary reward status
            when (diary.reward) {
                -1 -> stage = 999
                0 -> stage = 100
                1 -> stage = 101
                2 -> stage = 102
            }
        } else {
            player("Hello there. You look busy.")
            stage = 0
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return GeoffreyDialogue(player)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            999 -> end()
            0 -> npc("Yes, I am very busy. Picking GLORIOUS flax.", "The GLORIOUS flax won't pick itself. So I pick it.", "I pick it all day long.").also { stage++ }
            1 -> player("Wow, all that flax must really mount up. What do you do with it all?").also { stage++ }
            2 -> npc("I give it away! I love picking the GLORIOUS flax,", "but, if I let it all mount up, I wouldn't have any", "room for more GLORIOUS flax.").also { stage++ }
            3 -> player("So, you're just picking the flax for fun? You must", "really like flax.").also { stage++ }
            4 -> npc("'Like' the flax? I don't just 'like' flax. The", "GLORIOUS flax is my calling, my reason to live.", "I just love the feeling of it in my hands!").also { stage++ }
            5 -> player("Erm, okay. Maybe I can have some of your spare flax?").also { stage++ }
            6 -> npc("No. I don't trust outsiders. Who knows what depraved", "things you would do with the GLORIOUS flax? Only", "locals know how to treat it right.").also { stage++ }
            7 -> player("I know this area! It's, erm, Seers' Village. There's", "a pub and, er, a bank.").also { stage++ }
            8 -> npc("Pah! You call that local knowledge? Perhaps if you", "were wearing some kind of item from one of the", "seers, I might trust you.").also { stage = 999 }

            98 -> npc("I've already given you your GLORIOUS flax", "for the day. Come back tomorrow.").also { stage = 999 } // TODO find accurate dialogue
            99 -> npc("Yes, but your inventory is full. Come back", "when you have some space for GLORIOUS flax.").also { stage = 999 } // TODO find accurate dialogue
            100 -> {rewardFlax(30, "Yes. The seers have instructed me to give you an", "allowance of 30 GLORIOUS flax a day. I'm not going", "to argue with them, so here you go.")} // TODO find accurate dialogue
            101 -> {rewardFlax(60, "Yes. Stankers has instructed me to give you an", "allowance of 60 GLORIOUS flax a day. I'm not going", "to argue with a dwarf, so here you go.")} // TODO find accurate dialogue
            102 -> {rewardFlax(120, "Yes. Sir Kay has instructed me to give you an", "allowance of 120 GLORIOUS flax a day. I'm not going", "to argue with a knight, so here you go.")}
        }
        return true
    }

    fun rewardFlax(n: Int, vararg messages: String): Unit {
        npc(*messages)
        player.inventory.add(Item(ItemNames.FLAX_NOTED, n))
        player.setAttribute("/save:diary:seers:flax-timer", Util.nextMidnight(System.currentTimeMillis()))
        stage = 999
    }

    override fun getIds(): IntArray {
        return intArrayOf(8590)
    }

}
