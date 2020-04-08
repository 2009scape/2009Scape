package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

@InitializablePlugin
public class LookAtOptionPlugin extends OptionHandler {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        for (int i = 18877; i <= 18900; i++)
        {
            ObjectDefinition.forId(i).getConfigurations().put("option:look at", this);
        }
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        player.getPacketDispatch().sendMessage("The " + node.getName().toLowerCase() + " seem to be going south-west.");
        return true;

    }
}
