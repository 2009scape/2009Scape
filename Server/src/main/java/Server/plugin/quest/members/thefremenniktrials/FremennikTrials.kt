/*
package plugin.quest.members.thefremenniktrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.player.link.quest.QuestRequirement
import core.game.node.entity.player.link.quest.QuestReward
import core.game.node.entity.player.link.quest.QuestRewardComponentItem
import core.plugin.InitializablePlugin
import plugin.skill.Skills

@InitializablePlugin
class FremennikTrials : Quest("Fremennik Trials",64,63,3,347,0,1,10){

    class SkillRequirement(val skill: Int?, val level: Int?)

    val requirements = arrayListOf<SkillRequirement>()

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = JOURNAL_TEXT_START
        val started = player?.questRepository?.getStage("Fremennik Trials")!! > 0

        if(!started){
            line = writeJournal(player, line, "Requirements to complete quest:", "")
            line = writeJournal(player, line, *getQuestRequirementsJournal(player))
            writeJournal(player, line,
                "I must also be able to defeat a <red>level 69 enemy <blue>and must",
                "not be afraid of <red>combat without any weapons or armour.",
                "",
                "I can start this quest by speaking to <red>Chieftain Brundt <blue>on",
                "the <red>Fremennik Longhall, <blue>which is in the town of <red>Rellekka <blue>to",
                "the north of <red>Sinclair Mansion<blue>.")
        } else {
            line = writeJournal(player, line,
                "In order to join the Fremenniks, I need to",
                "<red>earn the approval <blue>of <red>7 members <blue>of the elder council.",
                "I've written down the members who I can try to help:"
            )
            line = writeJournal(player, line, player.getAttribute("fremtrials:manni-vote",false), "Manni the Reveller")
            line = writeJournal(player, line, player.getAttribute("fremtrials:swensen-vote",false), "Swensen the Navigator")
            line = writeJournal(player, line, player.getAttribute("fremtrials:sigli-vote",false), "Sigli the Huntsman")
            line = writeJournal(player, line, player.getAttribute("fremtrials:olaf-vote",false), "Olaf the Bard")
            writeJournal(player, line, "So far I have gotten ${player.getAttribute("fremtrials:votes",0)} votes.")
        }
    }

    override fun getRewardComponentItem(): QuestRewardComponentItem {
        return QuestRewardComponentItem(995, 240)
        TODO("Placeholder")
    }

    override fun getQuestRequirements(): Array<QuestRequirement> {
        return arrayOf(
            QuestRequirement(Skills.WOODCUTTING, 40),
            QuestRequirement(Skills.CRAFTING, 40),
            QuestRequirement(Skills.FLETCHING, 25),
        )
    }

    override fun getQuestRewards(player: Player?): Array<QuestReward> {
        return arrayOf()
    }

    override fun newInstance(`object`: Any?): Quest {
        requirements.add(SkillRequirement(Skills.FLETCHING,25))
        requirements.add(SkillRequirement(Skills.CRAFTING,40))
        requirements.add(SkillRequirement(Skills.WOODCUTTING,40))
        return this
    }

}*/
