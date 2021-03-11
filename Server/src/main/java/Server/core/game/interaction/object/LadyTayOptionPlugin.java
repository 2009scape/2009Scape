package core.game.interaction.object;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.item.Item;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Adds a barrel from which you can take swamp tar on the Lady Tay
 * @author f00b
 */
@Initializable
public class LadyTayOptionPlugin extends OptionHandler {


    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        /* the tar barrels on the lower deck of the Lady Zay */
        ObjectDefinition.forId(16860).getHandlers().put("option:take-from", this);
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        int id = node instanceof GameObject ? ((GameObject) node).getId() : ((NPC) node).getId();
        final Item tar = new Item(1939); // Swamp tar
        switch (option) {
        case "take-from":
            if (id == 16860) {
                if (!player.getInventory().hasSpaceFor(tar)) {
                    player.getPacketDispatch().sendMessage("Not enough space in your inventory!");
                    return true;
                }
                player.getInventory().add(tar);
                player.getPacketDispatch().sendMessage("You take some tar from the barrel.");
                return true;
            }
        }
        return false;
    }
}
