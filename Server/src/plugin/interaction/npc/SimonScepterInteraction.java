package plugin.interaction.npc;

import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import plugin.dialogue.SimonTempleton;

/**
 * Handles when a player uses a pharaoh scepter on Simon (necessary to be accurate)
 * @author ceik
 */
@InitializablePlugin
public final class SimonScepterInteraction extends UseWithHandler {

    public SimonScepterInteraction() {
        super(9044, 9046, 9048, 9050);
    }
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        addHandler(3123, NPC_TYPE, this);
        //PluginManager.definePlugin(new SimonScepterInteraction());
        return this;
    }
    @Override
    public boolean handle(NodeUsageEvent event){
        final Player player = event.getPlayer();
        player.getDialogueInterpreter().open(3123, ((NPC)event.getUsedWith()), true, false, event.getUsedItem().getId());
        return true;
    }
}
