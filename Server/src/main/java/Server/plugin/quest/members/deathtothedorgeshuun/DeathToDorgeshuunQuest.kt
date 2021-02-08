package plugin.quest.members.deathtodorgeshuun
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.item.GroundItem
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.tools.Items
import plugin.skill.Skills

/**
 * Represents the Death to the Doreshuun quest journal
 * @author plex
 */
@InitializablePlugin
class DeathToDorgeshuunQuest: Quest("Death to the Dorgeshuun", 43, 42, 1) {
    override fun newInstance(`object`: Any?): Quest {
        return this
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        player ?: return
        var line = 11
        if(stage == 0){
            line(player,"I can start this quest by speaking to !!Mistag?? in the !!Dorgeshuun mines??",line++)
            line++
            line(player, "To start this quest I require:", line++)
            line(player, "!!23 Thieving??", line++, player.skills.getLevel(Skills.THIEVING) >= 23)
            line(player, "!!23 Agility??", line++, player.skills.getLevel(Skills.AGILITY) >= 23)
            line++
            line(player, "I must have also completed:", line++)
            line(player, "!!Goblin Diplomacy??", line++, player.questRepository.getQuest("Goblin Diplomacy").getStage(player) == 100 )
            line(player, "!!The Lost Tribe??", line++, player.questRepository.getQuest("Lost Tribe").getStage(player) == 100 )
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
    }

    override fun hasRequirements(player: Player?): Boolean{

        player ?: return false

        if(player.skills.getLevel(Skills.THIEVING) < 23)
            return false
        if(player.skills.getLevel(Skills.AGILITY) < 23)
            return false
        if(player.questRepository.getQuest("Goblin Diplomacy").getStage(player) != 100)
            return false
        if(player.questRepository.getQuest("Lost Tribe").getStage(player) != 100)
            return false

        return true
    }
    override fun getConfig(player: Player?, stage: Int): IntArray {
        if(stage == 100) return intArrayOf(1282, 90)
        if(stage > 0) return intArrayOf(1282, 1)
        else return intArrayOf(1282, 0)
    }
}