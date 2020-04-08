package plugin.interaction.city;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.Plugin;


/**
 * Map in RC guild/wiz tower
 * @author ceik
 */
public class RCGuildMap extends OptionHandler {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(38422).getConfigurations().put("option:study",this);
        return null;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        if(option.equals("study")){
            player.getPacketDispatch().sendMessage("A map of Gielinor.");
        }
        return false;
    }
}
