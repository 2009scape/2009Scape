package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Created for 2009Scape
 * User: Ethan Kyle Millard
 * Date: March 25, 2020
 * Time: 7:19 PM
 */
@InitializablePlugin
public class RuneCraftingGuildObjects extends OptionHandler {


    @Override
    public boolean handle(Player player, Node node, String option) {
        GameObject object = ((GameObject) node);

        switch(object.getId()) {
            case 38279:
                if (player.getViewport().getRegion().getRegionId() == 12337) {
                    player.teleport(Location.create(1696, 5460, 2));
                } else {
                    player.teleport(Location.create(3106, 3160, 1));
                }
                break;
        }

        return true;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(38279).getConfigurations().put("option:enter", this);
        return this;
    }
}
