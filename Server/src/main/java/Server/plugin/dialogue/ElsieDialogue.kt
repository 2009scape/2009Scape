package plugin.dialogue

import core.game.content.ItemNames
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.Item
import core.plugin.InitializablePlugin

/**
 * Elsie
 * @author afaroutdude
 */
@InitializablePlugin
class ElsieDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        when (player.inventory.containsOneItem(ItemNames.CUP_OF_TEA_712)) {
            true -> npc("Hello dearie! What can old Elsie do for you?").also { stage = 10 }
            false -> npc("Ooh - that looks like a lovely cup of tea, dearie. Is it for", "me?").also { stage = 100 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            999 -> end()

            10 -> interpreter.sendOptions("What would you like to say?", "What are you making?", "Can you tell me a story?", "Can you tell me how to get rich?").also { stage++ }
            11 ->
                when (buttonId) {
                    1 -> player("What are you making?").also { stage = 20 }
                    2 -> player("Can you tell me a story?").also { stage = 30 }
                    3 -> player("Can you tell me how to get rich?").also { stage = 40 }
                }
            20 -> player("What are you making?").also { stage++ }
            21 -> npc("I'm knitting a new stole for Father Lawrence", "downstairs. He could do with something to keep his", "neck warm, standing in that draughty old church", "all day.").also { stage++ }
            22 -> interpreter.sendOptions("What would you like to say?", "Can you tell me a story?", "Can you tell me how to get rich?").also { stage++ }
            23 ->
                when (buttonId) {
                    1 -> player("Can you tell me a story?").also { stage = 30 }
                    2 -> player("Can you tell me how to get rich?").also { stage = 40 }
                }
            30 -> player("Can you tell me a story?").also { stage++ }
            31 -> npc("Maybe I could tell you a story if you'd fetch me", "a nice cup of tea.").also { stage++ }
            32 -> player("I'll think about it.").also { stage == 999 }
            40 -> player("Can you tell me how to get rich??").also { stage++ }
            41 -> npc("Well, dearie, I'm probably not the best person to ask about money, but I think the best thing would be for you to get a good trade. If you've got a trade you can earn your way, that's what my old father told me,").also { stage++ }
            42 -> npc("Saradomin rest his soul. I hear people try to get rich by fighting in the Wilderness north of here or the Duel Arena in the south, but that's no way for honest folks to earn a living! So get yourself a good trade, and keep").also { stage++ }
            43 -> npc("working at it. There's always folks wanting to buy ore and food around here.").also { stage++ }
            44 -> player("Thanks, old woman.").also { stage++ }
            45 -> interpreter.sendOptions("What would you like to say?", "What are you making?", "Can you tell me a story?").also { stage++ }
            46 ->
                when (buttonId) {
                    1 -> player("What are you making?").also { stage = 20 }
                    2 -> player("Can you tell me a story?").also { stage = 30 }
                }

            100 -> interpreter.sendOptions("What would you like to say?", "Yes, you can have it.", "No, keep your hands off my tea.").also { stage++ }
            101 ->
                when (buttonId) {
                    1 -> player("Yes, you can have it.").also { stage++ }
                    2 -> player("No, keep your hands off my tea.").also { stage = 120 }
                }
            102 -> player("Yes, you can have it.").also { stage++ }
            103 -> npc("Ahh, there's nothing like a nice cuppa tea. I know what,", "I'll tell you a story to thank you for the lovely tea...").also {
                player.inventory.remove(Item(ItemNames.CUP_OF_TEA_712))
                player.achievementDiaryManager.finishTask(player, DiaryType.VARROCK, 0, 14)
                stage++
            }
            104 -> npc("A long time ago, when I was a little girl, there was a", "handsome young man living in Varrock. I saw him here", "in the church quite often. Everyone said he was going", "to become a priest, and we girls were so sad about that.").also { stage++ }
            105 -> npc("But young Dissy - that was the young man's nickname", "- he was a wild young thing. One night he gathered", "some lads together, and after the evening prayer-", "meeting they all put on masks and sneaked down to the").also { stage++ }
            106 -> npc("evil temple in the south of the city, the evil one. The", "next day, there was quite a hubbub. The guards told us", "that someone had painted 'Saradomin pwns' on the wall", "of the Zamorakian temple!").also { stage++ }
            107 -> npc("Now, we'd always been taught to keep well away from", "that dreadful place, but it really did us all good to see", "someone wasn't afraid of the scum who live at that end", "of town. Old Father Packett was furious, but Dissy just").also { stage++ }
            108 -> npc("laughed it off.").also { stage++ }
            109 -> npc("Dissy left town after that, saying he wanted to see the", "world. It was such a shame, he had the most handsome", "shoulders...").also { stage++ }
            110 -> npc("A young man came here looking for stories about Dissy", "- of course, that's not his proper name, but his friends", "called him Dissy - and I told him that one. He said", "Dissy had become a really famous man and there was").also { stage++ }
            111 -> npc("going to be a book about him. Well, that's all good, but I", "do wish Dissy had just come back to Varrock. I did", "miss him so much... well, until I met my Freddie and", "we got married, but that's another story.").also { stage++ }
            112 -> player("Thank you. I'll leave you to your knitting now.").also { stage = 999 }
            120 -> npc("Aww. Maybe another time. Anyway, what can old Elsie do for you?").also { stage++ }
            121 ->
                when (buttonId) {
                    1 -> player("What are you making?").also { stage = 20 }
                    2 -> player("Can you tell me a story?").also { stage = 122 }
                }
            122 -> npc("Well, maybe I could tell a story if you'd give me that lovely cup of tea you've got there.").also { stage = 100 }
        }
        return true;
    }


    override fun newInstance(player: Player?): DialoguePlugin {
        return ElsieDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(5915)
    }
}