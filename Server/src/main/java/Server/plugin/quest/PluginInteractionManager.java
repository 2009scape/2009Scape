package plugin.quest;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.Option;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.SystemLogger;

import java.util.HashMap;

public class PluginInteractionManager {
    private static final HashMap<Integer, PluginInteraction> npcInteractions = new HashMap<>();
    private static final HashMap<Integer, PluginInteraction> objectInteractions = new HashMap<>();
    private static final HashMap<Integer, PluginInteraction> useWithInteractions = new HashMap<>();
    private static final HashMap<Integer, PluginInteraction> groundItemInteractions = new HashMap<>();

    public static void register(PluginInteraction interaction, InteractionType type){
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
        PluginInteraction i = objectInteractions.get(object.getId());
        if(i == null) {
            return false;
        } else {
            return i.handle(player,object);
        }
    }

    public static boolean handle(Player player, NodeUsageEvent event){
        PluginInteraction i = useWithInteractions.get(event.getUsed().asItem().getId());
        if(i == null) {
            return false;
        } else {
            return i.handle(player,event);
        }
    }

    public static boolean handle(Player player, NPC npc, Option option){
        PluginInteraction i = npcInteractions.get(npc.getId());
        if(i == null) {
            return false;
        } else {
            return i.handle(player,npc,option);
        }
    }

    public static boolean handle(Player player, Item item, Option option){
        PluginInteraction i = groundItemInteractions.get(item.getId());
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
        ITEM
    }
}
