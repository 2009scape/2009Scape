package plugin.interaction.item.withobject;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;

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
        def.getConfigurations().put("option:remove-coal",this);
        def.getConfigurations().put("option:investigate",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        int coalInTruck = player.getAttribute("coal-truck-inventory",0);
        int freeSpace = player.getInventory().freeSlots();
        List<Item> toAdd = new ArrayList<>();
        switch(option){
            case "remove-coal":
                if(player.getAttribute("coal-truck-inventory",0) == 0) {
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
            int coalInTruck = event.getPlayer().getAttribute("coal-truck-inventory",0);
            while(event.getPlayer().getInventory().containsItem(COAL)){
                if(coalInTruck < 120){
                    event.getPlayer().getInventory().remove(COAL);
                    coalInTruck++;
                } else {
                    event.getPlayer().getPacketDispatch().sendMessage("You have filled up the coal truck.");
                    break;
                }
            }
            event.getPlayer().setAttribute("/save:coal-truck-inventory",coalInTruck);
            return true;
        }
    }
}
