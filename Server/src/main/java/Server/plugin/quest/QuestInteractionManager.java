package plugin.quest;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.Option;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import plugin.quest.fishingcontest.FishingContest;

import java.util.HashMap;

public class QuestInteractionManager{
    private static final HashMap<Integer,QuestInteraction> npcInteractions = new HashMap<>();
    private static final HashMap<Integer,QuestInteraction> objectInteractions = new HashMap<>();
    private static final HashMap<Integer,QuestInteraction> useWithInteractions = new HashMap<>();

    public static void register(QuestInteraction interaction, InteractionType type){
        switch(type){
            case OBJECT:
                for(int i = 0; i < interaction.ids.length; i++){
                    objectInteractions.putIfAbsent(interaction.ids[i],interaction);
                }
                break;
            case USEWITH:
                useWithInteractions.putIfAbsent(interaction.ids[0],interaction);
                break;
            case NPC:
                for(int i = 0; i < interaction.ids.length; i++){
                    npcInteractions.putIfAbsent(interaction.ids[i],interaction);
                }
        }
    }

    public static boolean handle(Player player, GameObject object){
        QuestInteraction i = objectInteractions.get(object.getId());
        if(i == null) {
            return false;
        } else {
            return i.handle(player,object);
        }
    }

    public static boolean handle(Player player, NodeUsageEvent event){
        QuestInteraction i = useWithInteractions.get(event.getUsed().asItem().getId());
        if(i == null) {
            return false;
        } else {
            return i.handle(player,event);
        }
    }

    public static boolean handle(Player player, NPC npc, Option option){
        QuestInteraction i = npcInteractions.get(npc.getId());
        if(i == null) {
            return false;
        } else {
            return i.handle(player,npc,option);
        }
    }

    public enum InteractionType{
        NPC,
        OBJECT,
        USEWITH;
    }
}
