package plugin.boredom;

import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import plugin.CorePluginTypes.ManagerPlugin;
import plugin.CorePluginTypes.Managers;

import java.util.ArrayList;
import java.util.List;

@InitializablePlugin
@PluginManifest(name="Boredom")
public class BoredomManager extends ManagerPlugin {
    public static List<BoredPlayer> players = new ArrayList<>();

    @Override
    public void tick() {
        players.forEach(BoredPlayer::tick);
        players.removeIf(p -> p.done);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        Managers.register(this);
        return this;
    }

    public static void submit(Player p, int npc_id){
        p.getAppearance().transformNPC(npc_id);
        players.add(new BoredPlayer(p, 100));
    }
}
