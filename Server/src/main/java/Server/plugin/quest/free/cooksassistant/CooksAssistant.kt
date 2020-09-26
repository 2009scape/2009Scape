package plugin.quest.free.cooksassistant

import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.player.link.quest.QuestReward
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import plugin.skill.Skills

/**
 * The Quest Journal and Configuration for the Cook's Assistant Quest.
 * @author Qweqker
 */

@InitializablePlugin
class CooksAssistant : Quest(
        NAME,
        15,
        14,
        1,
        29, 0, 1, 2
) {
    companion object {
        const val MILK = 1927
        const val FLOUR = 1933
        const val EGG = 1944
        const val NAME = "Cook's Assistant"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)

        var line = 12
        val questStage = player?.questRepository?.getStage(NAME)!!

        if (questStage < 10) { // If the quest has not been started
            writeJournal(player, line,
                "I can start this quest by speaking to the <red>Cook <blue>in the",
                "<red>Kitchen <blue>on the ground floor of <red>Lumbridge Castle."
            )
        } else if (questStage in 10..99) { // Player has started Cook's Assistant
            // Situation
            line = writeJournal(player, line,
                "It's the <red>Duke of Lumbridge's <blue>birthday and I have to help",
                "his <red>Cook <blue>make him a <red>birthday cake. <blue>To do this I need to",
                "bring him the following ingredients:"
            )

            // If the player has handed in the bucket of milk
            line = if (player.getAttribute("cooks_assistant:milk_submitted", false) || player.getAttribute("cooks_assistant:all_submitted", false)) {
                writeJournal(player, line, true,
                    "<black>I have given the cook a bucket of milk.")
            // If the player has a bucket of milk in their inventory
            } else if (player.inventory.contains(MILK, 1)) {
                writeJournal(player, line,
                    "I have found a <red>bucket of milk <blue>to give to the cook.")
            // If the player satisfies none of the above
            } else {
                writeJournal(player, line,
                    "I need to find a <red>bucket of milk. <blue>There's a cattle field east",
                    "of Lumbridge, I should make sure I take an empty bucket",
                    "with me.")
            }

            // If the player has handed in the pot of flour
            line = if (player.getAttribute("cooks_assistant:flour_submitted", false) || player.getAttribute("cooks_assistant:all_submitted", false)) {
                writeJournal(player, line, true,
                    "<black>I have given the cook a pot of flour.")
            // If the player has a pot of flour in their inventory
            } else if (player.inventory.contains(FLOUR, 1)){
                writeJournal(player, line,
                    "I have found a <red>pot of flour <blue>to give to the cook.")
            // If the player satisfies none of the above
            } else {
                writeJournal(player, line,
                    "I need to find a <red>pot of flour. <blue>There's a mill found north-",
                    "west of Lumbridge, I should take an empty pot with me.")
            }

            // If the player has handed in the egg
            line = if (player.getAttribute("cooks_assistant:egg_submitted", false) || player.getAttribute("cooks_assistant:all_submitted", false)) {
                writeJournal(player, line, true,
                    "<black>I have given the cook an egg.")
            // If the player has an egg in their inventory
            } else if (player.inventory.contains(EGG, 1)){
                writeJournal(player, line,
                    "I have found an <red>egg <blue>to give to the cook.")
            // If the player satisfies none of the above
            } else {
                writeJournal(player, line,
                    "I need to find an <red>egg. <blue>The cook normally gets his eggs from",
                    "the Groats' farm, found just to the west of the cattle",
                    "field.")
            }

            // If the player has handed everything in but was interrupted during the final dialogue
            if (
                player.getAttribute("cooks_assistant:all_submitted", false) ||
                (
                    player.getAttribute("cooks_assistant:milk_submitted", false) &&
                    player.getAttribute("cooks_assistant:flour_submitted", false) &&
                    player.getAttribute("cooks_assistant:egg_submitted", false)
                )
            ) {
                writeJournal(player, line,
                    "I should return to the <red>Cook<blue>."
                )
            }
        } else if (questStage >= 100) {
            writeJournal(player, line, true,
                "<black>It was the Duke of Lumbridge's birthday, but his cook had",
                "<black>forgotten to buy the ingredients he needed to make him a",
                "<black>cake. I brought the cook an egg, some flour and some milk",
                "<black>and then cook made a delicious looking cake with them.",
                "",
                "<black>As a reward he now lets me use his high quality range",
                "<black>which lets me burn things less whenever I wish to cook",
                "<black>there.",
                "",
                "<col=FF0000>QUEST COMPLETE!</col>"
            )
        }
    }

    //The Quest Finish Certificate
    override fun finish(player: Player) {
        super.finish(player)

        //Removing these attributes in the event that they weren't already cleared in the Cook's Dialogue
        player.removeAttribute("cooks_assistant:milk_submitted")
        player.removeAttribute("cooks_assistant:flour_submitted")
        player.removeAttribute("cooks_assistant:egg_submitted")
        player.removeAttribute("cooks_assistant:all_submitted")
        player.removeAttribute("cooks_assistant:submitted_some_items")
    }

    override fun getRewardComponentItem(): Item {
        return Item(1891)
    }

    override fun getQuestRewards(player: Player?): Array<QuestReward> {
        return arrayOf(
            QuestReward(Skills.COOKING, 300)
        )
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}