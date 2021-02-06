package plugin.quest.members.familycrest


import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.tools.Items
import plugin.activity.allfiredup.AFUBeacon
import plugin.skill.Skills

/**
* Represents the "Family Crest" quest.
* @author Plex
*/
@InitializablePlugin
class FamilyCrest: Quest("Family Crest", 59, 58, 1) {

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player?: return
        if(stage == 0){
            line(player, "I can start this quest by speaking to !!Demintheis??", line++)
            line(player, "in east Varrock", line++)
            line++
            line(player, "To start this quest I require:", line++)
            line(player, "!!40 Crafting??", line++, player.skills.getLevel(Skills.CRAFTING) >= 40)
            line(player, "!!40 Smithing??", line++, player.skills.getLevel(Skills.SMITHING) >= 40)
            line(player, "!!40 Mining??", line++, player.skills.getLevel(Skills.MINING) >= 40)
            line(player, "!!59 Magic??", line++, player.skills.getLevel(Skills.MAGIC) >= 59)
        }
        if(stage == 10){
            line(player, "I have agreed to restore Dimintheis' family crest to him.", line++)
            line(player, "He has asked me to find his son Caleb for him", line++)
        }
    }

    override fun getConfig(player: Player?, stage: Int): IntArray {
        if(stage == 100) return intArrayOf(1282, 90)
        if(stage > 0) return intArrayOf(1282, 1)
        else return intArrayOf(1282, 0)
    }
}