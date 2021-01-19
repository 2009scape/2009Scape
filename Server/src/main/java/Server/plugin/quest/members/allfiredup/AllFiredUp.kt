package plugin.quest.members.allfiredup

import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.tools.Items
import plugin.activity.allfiredup.AFUBeacon
import plugin.skill.Skills

/**
 * Represents the "All Fired Up" quest.
 * @author Ceikry
 */
@InitializablePlugin
class AllFiredUp : Quest("All Fired Up", 157, 156, 1){
    override fun newInstance(`object`: Any?): Quest {
        return this
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return
        if(stage == 0){
            line(player, "I can start this quest by speaking to !!King Roald?? in Varrock Palace.", line++)
            line++
            line(player, "To start this quest, I require:", line++)
            line(player, "!!43 Firemaking??", line++, player.skills.getLevel(Skills.FIREMAKING) >= 43)
            line(player, "!!Completion of Priest in Peril??", line++, player.questRepository.isComplete("Priest in Peril"))

        } else {
            if(stage >= 10){
                line(player, "!!King Roald?? needs me to test the !!beacons??. He has sent me", line++, stage >= 20)
                line(player, "to speak with !!Blaze Sharpeye??, by the beacon near the !!River Salve??.", line++, stage >= 20)
            }
            if(stage >= 20){
                line(player, "Blaze Sharpeye asked me to light the !!beacon??. I'm going to need:", line++, stage >= 30)
                line(player, "20 logs of a single kind", line++, stage >= 30)
                line(player, "A tinderbox", line++, stage >= 30)
            }
            if(stage == 30){
                line(player, "I lit the beacon. I should speak to !!Blaze Sharpeye?? again.", line++, stage >= 40)
            }
            if(stage >= 40){
                line(player, "Blaze Sharpeye asked me to go and speak with !!Squire Fyre?? near the !!limestone quarry??.", line++, stage >= 50)
            }
            if(stage >= 50){
                line(player, "Squire Fyre permitted me to light the !!beacon??. I'm going to need:", line++, stage >= 60)
                line(player, "20 logs of a single kind", line++, stage >= 60)
                line(player, "A tinderbox", line++, stage >= 60)
            }
            if(stage == 60){
                line(player, "I should return to !!Blaze Sharpeye??.", line++)
            }
            if(stage >= 70){
                line(player, "Blaze asked me to add more logs to the dying fire. I'll need:", line++, stage >= 80)
                line(player, "5 logs of a single kind", line++, stage >= 80)
                line(player, "A tinderbox", line++, stage >= 80)
            }
            if(stage == 80){
                line(player, "I should speak to !!Blaze?? again.", line++)
            }
            if(stage == 90){
                line(player, "I should report to !!King Roald??.", line++)
            }
            if(stage == 100){
                line(player, "I helped King Roald test the beacon network", line++)
                line(player, " and have gained full access to it.", line++)
            }
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        player.packetDispatch.sendString("20,000gp,", 277, 8 + 2)
        player.packetDispatch.sendString("5,500 Firemaking XP", 277, 9 + 2)
        player.packetDispatch.sendString("Full access to the beacon network", 277, 10 + 2)
        player.packetDispatch.sendString("1 Quest Point", 277, 11 + 2)
        player.packetDispatch.sendItemZoomOnInterface(Items.TINDERBOX_590, 235, 277, 3 + 2)
        player.skills.addExperience(Skills.FIREMAKING, 5500.0)
        player.inventory.add(Item(995,20000))
        player.varpManager.unflagSave(1283)
        AFUBeacon.resetAllBeacons(player)
        player.varpManager.get(1283).setVarbit(0,0).send(player)
        player.questRepository.syncronizeTab(player)
    }

    override fun getConfig(player: Player?, stage: Int): IntArray {
        if(stage == 100) return intArrayOf(1282, 90)
        if(stage > 0) return intArrayOf(1282, 1)
        else return intArrayOf(1282, 0)
    }
}