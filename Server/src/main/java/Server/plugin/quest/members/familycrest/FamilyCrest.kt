package plugin.quest.members.familycrest


import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.item.GroundItemManager
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
        if(stage >= 10){
            line(player, "I have agreed to restore !!Dimintheis'?? family crest to him.", line++, stage >10)
            line(player, "He has asked me to find his son Caleb for him", line++, stage >10)
        }

        if(stage >= 11){
            line(player, "I found !!Caleb?? at !!Gertrude's house?? outside !!Varrock??", line++,  stage >11)
            line(player, "and told him of my Quest for his father to restore his Family Crest.", line++,  stage >11)
            line(player, "I gave !!Caleb?? the Swordfish, Bass, Tuna, Salmon and Shrimp he needed for his salad in return for his crest piece", line++,  stage >11)

        }
        if(stage >=12){
            line(player, "!!Caleb?? has told me to speak to the !!Gem trader?? in !!Al-Kharid?? to find his brother.", line++,  stage >12)
        }
        if(stage >=13){
            line(player, "I found !!Avan?? by some gold rocks North of !!Al Kharid??.", line++,  stage >13)
        }
        if(stage >= 14){
            line(player, "!!Avan?? has asked me to find the perfect gold, he has heard of a !!dwarf??", line++,  stage >14)
            line(player, "who might know where to get some", line++,  stage >14)
        }
        if(stage >=15){
            line(player, "I have spoken to !!Boot?? and he told me that ", line++,  stage >15)
            line(player, "i can find perfect gold in the !!Witchaven dungeons??", line++,  stage >15)
        }
        if(stage >=16){
            line(player, "!!Avan?? gave me his crest piece in return for a ruby ring ", line++,  stage >16)
            line(player, "and ruby necklace made of high quality 'perfect gold'.", line++,  stage >16)
        }
        if(stage >= 17){
            line(player, "I found !!Johnathon?? looking very ill at the !!Jolly Boar Inn??.", line++,  stage >17)
        }
        if(stage >= 18){
            line(player, "He soon recovered when I used an antipoison potion on him.", line++,  stage >18)
        }
        if(stage >= 19){
            line(player, "He has told me about the Demon !!Chronozon?? located in the !!Edgeville dungeon??", line++, stage > 19)
        }
        if(stage == 100){
            line(player, "I took all three pieces of the crest back to !!Dimintheis?? in !!Varrock??", line++)
            line(player, "As a reward !!Dimintheis?? gave me some !!magical gauntlets?? that could be enchanted ", line++)
            line(player, "by his sons to give them bonuses in specific skills", line++)
            line(player, "and would always return to !!Dimintheis?? for me to reclaim if I ever lost them.", line++)
            line++
            line(player, "!!QUEST COMPLETE!??.", line++)
        }


    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10
        player.packetDispatch.sendItemZoomOnInterface(Items.COOKING_GAUNTLETS_775,230,277,5)
        drawReward(player,"1 Quest Point",ln++)
        drawReward(player,"Family Gauntlets",ln++)
        drawReward(player,"A choice of special abilities for the gauntlets",ln++)
        drawReward(player,"for the gauntlets",ln++)

        if(!player.inventory.add(Item(Items.FAMILY_GAUNTLETS_778))){
            GroundItemManager.create(Item(Items.FAMILY_GAUNTLETS_778),player)
        }

    }
    override fun getConfig(player: Player?, stage: Int): IntArray {
        if(stage == 100) return intArrayOf(1282, 90)
        if(stage > 0) return intArrayOf(1282, 1)
        else return intArrayOf(1282, 0)
    }
}