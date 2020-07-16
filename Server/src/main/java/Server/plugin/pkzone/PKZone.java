package plugin.pkzone;

import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;

@InitializablePlugin
@PluginManifest(name="PKZone")
public class PKZone extends MapZone implements Plugin<Object> {
    public PKZone() {super("PK Zone",true);}

    @Override
    public void configure() {
        ZoneBorders edgeville = new ZoneBorders(3071, 3472,3117, 3524);
        register(edgeville);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ZoneBuilder.configure(this);
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
