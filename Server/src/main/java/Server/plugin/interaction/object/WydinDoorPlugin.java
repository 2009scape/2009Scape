package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used for the door near wydin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WydinDoorPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2069).getHandlers().put("option:open", this);
		return null;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!player.getEquipment().contains(1005, 1) && player.getLocation().getX() > 3011) {
			player.getDialogueInterpreter().open(557, true, true);
			return true;
		} else {
			final GameObject object = (GameObject) node;
			DoorActionHandler.handleAutowalkDoor(player, object);
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return DoorActionHandler.getDestination(((Entity) node), ((GameObject) n));
	}
}
