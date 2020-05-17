package plugin.interaction.inter;

import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.content.global.travel.canoe.Canoe;
import org.crandor.game.content.global.travel.canoe.CanoeExtension;
import org.crandor.game.content.global.travel.canoe.CanoeStation;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the canoe interface plugins.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CanoeInterfacePlugin extends ComponentPlugin {

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ComponentDefinition.put(52, this);
        ComponentDefinition.put(53, this);
        return this;
    }

    @Override
    public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
        final CanoeExtension extension = CanoeExtension.extension(player);
        switch (component.getId()) {
            case 52:
                final Canoe canoe = Canoe.getCanoeFromChild(button);
                if (canoe == null) {
                    return true;
                }
                extension.craftCanoe(canoe);
                break;
            case 53:
                final CanoeStation station = CanoeStation.getStationFromButton(button);
                if (station == null) {
                    return true;
                }
                if (extension.getStage() < 3) {
                    return true;
                }
                extension.travel(station);
                break;
        }
        return true;
    }

}