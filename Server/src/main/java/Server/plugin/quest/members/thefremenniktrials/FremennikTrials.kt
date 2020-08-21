/*
package plugin.quest.fremtrials

import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.InitializablePlugin
import plugin.skill.Skills

@InitializablePlugin
class FremennikTrials : Quest("Fremennik Trials",64,63,3,347,0,1,10){

    class SkillRequirement(val skill: Int?, val level: Int?)

    val requirements = arrayListOf<SkillRequirement>()

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        val started = player?.questRepository?.getStage("Fremennik Trials")!! > 0

        if(!started){
            line(player,"Requirements to complete quest:",line++)
            line += 1
            line(player,"Level 40 Woodcutting",line++, player.skills.getStaticLevel(Skills.WOODCUTTING) >= 40)
            line(player,"Level 40 Crafting",line++,player.skills.getStaticLevel(Skills.CRAFTING) >= 40)
            line(player,"Level 25 Fletching",line++,player.skills.getStaticLevel(Skills.FLETCHING) >= 25)
            line(player,"I must also be able to defeat a !!level 69 enemy?? and must",line++)
            line(player,"not be afraid of !!combat without any weapons or armour.",line++)
            line += 1
            line(player,"I can start this quest by speaking to !!Chieftan Brundt?? on",line++)
            line(player,"the !!Fremennik Longhall,?? which is in the town of !!Rellekka?? to",line++)
            line(player,"the north of !!Sinclair Mansion??.",line)
        } else {
            line(player,"In order to join the Fremenniks, I need to",line++)
            line(player,"!!earn the approval?? of !!7 members?? of the elder council.",line++)
            line(player,"I've written down the members who I can try to help:",line++)
            line(player,"Manni the Reveller",line++,player.getAttribute("fremtrials:manni-vote",false))
            line(player,"Swensen the Navigator",line++,player.getAttribute("fremtrials:swensen-vote",false))
            line(player,"Sigli the Huntsman",line++,player.getAttribute("fremtrials:sigli-vote",false))
            line(player,"Olaf the Bard",line++,player.getAttribute("fremtrials:olaf-vote",false))
            line(player,"So far I have gotten ${player.getAttribute("fremtrials:votes",0)} votes.",line++)
        }
    }

    override fun newInstance(`object`: Any?): Quest {
        requirements.add(SkillRequirement(Skills.FLETCHING,25))
        requirements.add(SkillRequirement(Skills.CRAFTING,40))
        requirements.add(SkillRequirement(Skills.WOODCUTTING,40))
        return this
    }

}*/
