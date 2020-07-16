package plugin.shootingstar;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;

@InitializablePlugin
@PluginManifest(name="ShootingStars")
public class ScoreboardHandler extends OptionHandler {
    int index = 0;
    int ifaceid = ShootingStarScoreboard.iface.getId();
    @Override
    public boolean handle(Player player, Node node, String option) {
        ScoreboardManager.getEntries().forEach(e -> {
            player.getPacketDispatch().sendString("" + Math.floor(((GameWorld.getTicks() - e.time) / 0.6) / 60) + " minutes ago",ifaceid,index + 6);
            player.getPacketDispatch().sendString(e.playerName,ifaceid,index + 11);
            index++;
        });
        index = 0;
        player.getInterfaceManager().open(ShootingStarScoreboard.iface);
        return true;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ObjectDefinition.forId(38669).getConfigurations().put("option:read",this);

        return this;
    }
}
