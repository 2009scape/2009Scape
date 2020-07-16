package plugin.pkzone;

import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.repository.Repository;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import plugin.CorePluginTypes.ManagerPlugin;
import plugin.CorePluginTypes.Managers;

@InitializablePlugin
@PluginManifest(name="PKZone")
public class PKZoneManager extends ManagerPlugin {
    private static int nextCheck = 0;

    @Override
    public void tick() {
        if(GameWorld.getTicks() > nextCheck) {
            for (Player p : Repository.PLAYER_NAMES.values()) {
                if (p == null) {
                    continue;
                }
                if (!p.isActive()) {
                    continue;
                }
                if (!(p.getZoneMonitor().isInZone("PK Zone") || p.getZoneMonitor().isInZone("Wilderness"))) {
                    p.getProperties().setTeleportLocation(Location.create(3087, 3502, 0));
                }
            }
            nextCheck = GameWorld.getTicks() + 100;
        }
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        Managers.register(this);
        return this;
    }
}
