package plugin.quest.members.familycrest


import  core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

@InitializablePlugin
class JohnathonAntiPosionInteraction: UseWithHandler(175, 177, 179, 2446 ) {

    override fun newInstance(arg: Any?): Plugin<Any> {
        addHandler(668, NPC_TYPE, this)
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        if (event != null) {
            val qstage = event.player.questRepository.getQuest("Family Crest").getStage(event.player)
            val itemUsed = event.usedItem.id
            if(qstage == 17){
                event.player.questRepository.getQuest("Family Crest").setStage(event.player, 18)

                when(itemUsed){
                    2446 -> event.player.inventory.remove(Item(2446)).also{event.player.inventory.add(Item(175))}
                    175 -> event.player.inventory.remove(Item(175)).also{event.player.inventory.add(Item(177))}
                    177 -> event.player.inventory.remove(Item(177)).also{event.player.inventory.add(Item(179))}
                    179 -> event.player.inventory.remove(Item(179)).also{event.player.inventory.add(Item(229))}
                }

                event.player.getDialogueInterpreter().open(668)
            }else{
                event.player.sendMessage("Nothing interesting happened.")
            }
        }
        return true;
    }


}