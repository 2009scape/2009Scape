package plugin.interaction.city;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Portal in wiz tower
 * @author ceik
 */

@InitializablePlugin
public class RCGuildPortal extends OptionHandler {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(38279).getConfigurations().put("option:examine",this);
        ObjectDefinition.forId(38279).getConfigurations().put("option:enter",this);
        return null;
    }

    @Override
    public boolean handle(Player player, Node node, String string){
        if(string.equals("examine")){
            player.getPacketDispatch().sendMessage("The portal to the runecrafting guild.");
        }else {
            player.getPacketDispatch().sendMessage("Currently under construction.");
        }
        return true;
    }
}
