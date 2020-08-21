package plugin.quest.members.fishingcontest;

import core.game.interaction.MovementPulse;
import core.game.interaction.NodeUsageEvent;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.tools.ItemNames;
import plugin.quest.PluginInteraction;
import plugin.quest.PluginInteractionManager;

@InitializablePlugin
public class GarlicPipeInteraction extends PluginInteraction {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        setIds(new int[]{FishingContest.GARLIC.getId(),41});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.USEWITH);
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.OBJECT);
        return this;
    }

    @Override
    public boolean handle(Player player, NodeUsageEvent event) {
        System.out.println("Trying to handle it");
        if(event.getUsed() instanceof Item && event.getUsedWith() instanceof GameObject){
            GameObject usedWith = event.getUsedWith().asObject();
            Item used = event.getUsedItem();

            if(used.getId() == ItemNames.GARLIC_1550 && usedWith.getId() == 41 && usedWith.getLocation().equals(Location.create(2638, 3446, 0)) && player.getQuestRepository().getStage("Fishing Contest") > 0){
                player.getPulseManager().run(new MovementPulse(player, usedWith.getLocation().transform(0, -1, 0)) {
                    @Override
                    public boolean pulse() {
                        player.getDialogueInterpreter().sendDialogue("You stuff the garlic into the pipe.");
                        player.getInventory().remove(new Item(ItemNames.GARLIC_1550));
                        player.setAttribute("fishing_contest:garlic",true);
                        return true;
                    }
                }, "movement");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean handle(Player player, Node node) {
        if(node instanceof GameObject){
            GameObject object = node.asObject();
            if(object.getId() == 41 && object.getLocation().equals(Location.create(2638, 3446, 0)) && player.getAttribute("fishing_contest:garlic",false)){
                player.getPulseManager().run(new MovementPulse(player, object.getLocation().transform(0, -1, 0)) {
                    @Override
                    public boolean pulse() {
                        player.getDialogueInterpreter().sendDialogue("This is the pipe I stuffed that garlic into.");
                        return true;
                    }
                }, "movement");
                return true;
            }
        }
        return false;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
