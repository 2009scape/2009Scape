package plugin.CorePluginTypes;

import core.plugin.Plugin;

public abstract class ManagerPlugin implements Plugin<Object> {
    public abstract void tick();

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
