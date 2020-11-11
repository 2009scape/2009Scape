package plugin.interaction.item.withobject;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles coal truck interactions
 * @author ceik
 */

@InitializablePlugin
public class CoalTrucksHandler extends OptionHandler {
    private static final Item COAL = new Item(453,1);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        PluginManager.definePlugin(new useCoalWithTruck());
        ObjectDefinition def = ObjectDefinition.forId(2114);
        def.getHandlers().put("option:remove-coal",this);
        def.getHandlers().put("option:investigate",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        int coalInTruck = player.getAttribute("coal-truck-inventory",0);
        int freeSpace = player.getInventory().freeSlots();
        List<Item> toAdd = new ArrayList<>();
        switch(option){
            case "remove-coal":
                if(coalInTruck == 0) {
                    player.getDialogueInterpreter().sendDialogue("The coal truck is empty.");
                    return true;
                }
                while((coalInTruck > 0)){
                    if(freeSpace > 0){
                        toAdd.add(COAL);
                        coalInTruck--;
                        freeSpace--;
                    } else {
                        break;
                    }
                }
                player.getInventory().addList(toAdd);
                player.setAttribute("/save:coal-truck-inventory", coalInTruck);
                if (coalInTruck == 0
                        && !player.getAchievementDiaryManager().hasCompletedTask(DiaryType.SEERS_VILLAGE,1,2)
                        && player.getViewport().getRegion().getId() == 10806 // region 10294 is at coal truck mine, region 10806 is in seers village
                        && player.getAttribute("diary:seers:coal-truck-full", false)) {
                    player.removeAttribute("diary:seers:coal-truck-full");
                    player.getAchievementDiaryManager().finishTask(player,DiaryType.SEERS_VILLAGE,1,2);
                }
                if (!player.getAchievementDiaryManager().hasCompletedTask(DiaryType.SEERS_VILLAGE,1,2)
                        && player.getViewport().getRegion().getId() == 10294) { // region 10294 is at coal truck mine, region 10806 is in seers village
                    player.setAttribute("/save:diary:seers:coal-truck-full", false);
                }
                break;
            case "investigate":
                player.getDialogueInterpreter().sendDialogue("There is currently " + coalInTruck + " coal in the truck.","The truck has space for " + (120 - coalInTruck) + " more coal.");
                break;
        }
        return true;
    }

    public class useCoalWithTruck extends UseWithHandler{
        public useCoalWithTruck(){
            super(COAL.getId());
        }
        @Override
        public Plugin<Object> newInstance(Object arg) throws Throwable {
            addHandler(2114, OBJECT_TYPE,this);
            return this;
        }

        @Override
        public boolean handle(NodeUsageEvent event) {
            Player player = event.getPlayer();
            int coalInTruck = player.getAttribute("coal-truck-inventory",0);
            while(player.getInventory().containsItem(COAL)){
                if(coalInTruck < 120) {
                    event.getPlayer().getInventory().remove(COAL);
                    coalInTruck++;
                }
                if(coalInTruck >= 120) {
                    event.getPlayer().getPacketDispatch().sendMessage("You have filled up the coal truck.");
                    if (!player.getAchievementDiaryManager().getDiary(DiaryType.SEERS_VILLAGE).isComplete(1,2)
                            && player.getViewport().getRegion().getId() == 10294) { // region 10294 is at coal truck mine, region 10806 is in seers village
                        player.setAttribute("/save:diary:seers:coal-truck-full", true);
                    }
                    break;
                }
            }
            event.getPlayer().setAttribute("/save:coal-truck-inventory",coalInTruck);
            return true;
        }
    }
}
