package plugin.quest;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.Option;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.SystemLogger;

import java.util.HashMap;

public class QuestInteractionManager{
    private static final HashMap<Integer,QuestInteraction> npcInteractions = new HashMap<>();
    private static final HashMap<Integer,QuestInteraction> objectInteractions = new HashMap<>();
    private static final HashMap<Integer,QuestInteraction> useWithInteractions = new HashMap<>();
    private static final HashMap<Integer,QuestInteraction> groundItemInteractions = new HashMap<>();

    public static void register(QuestInteraction interaction, InteractionType type){
        switch(type){
            case OBJECT:
                for(int i = 0; i < interaction.ids.length; i++){
                    objectInteractions.putIfAbsent(interaction.ids[i],interaction);
                }
                break;
            case USEWITH:
                for(int i = 0; i < interaction.ids.length; i++){
                    useWithInteractions.putIfAbsent(interaction.ids[i],interaction);
                }
                break;
            case NPC:
                for(int i = 0; i < interaction.ids.length; i++){
                    npcInteractions.putIfAbsent(interaction.ids[i],interaction);
                }
                break;
            case ITEM:
                for(int i = 0; i < interaction.ids.length; i++){
                    groundItemInteractions.putIfAbsent(interaction.ids[i],interaction);
                }
                break;
        }
    }

    public static boolean handle(Player player, GameObject object){
        SystemLogger.log("Trying to run...");
        QuestInteraction i = objectInteractions.get(object.getId());
        if(i == null) {
            return false;
        } else {
            return i.handle(player,object);
        }
    }

    public static boolean handle(Player player, NodeUsageEvent event){
        SystemLogger.log("Trying to handle: used: " + event.getUsed().getId() + " with: " + event.getUsedWith().getId() );
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

    public static boolean handle(Player player, Item item, Option option){
        QuestInteraction i = groundItemInteractions.get(item.getId());
        if(i == null){
            return false;
        } else {
            return i.handle(player,item,option);
        }
    }

    public enum InteractionType{
        NPC,
        OBJECT,
        USEWITH,
        ITEM;
    }
}
