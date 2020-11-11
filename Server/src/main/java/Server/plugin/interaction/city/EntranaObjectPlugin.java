package plugin.interaction.city;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.TeleportManager;
import core.game.world.map.Location;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents a plugin used to handle entrana related objects.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class EntranaObjectPlugin extends OptionHandler {

	/**
	 * Represents the location to teleport to.
	 */
	private static final Location LOCATION = Location.create(3208, 3764, 0);// magic door

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2408).getHandlers().put("option:climb-down", this);
		ObjectDefinition.forId(2407).getHandlers().put("option:open", this);// magic door
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "climb-down":
			player.getDialogueInterpreter().open(656, Repository.findNPC(656));
			break;
		case "open":
			player.getPacketDispatch().sendMessage("You feel the world around you dissolve...");
			player.getTeleporter().send(LOCATION, TeleportManager.TeleportType.ENTRANA_MAGIC_DOOR);
			break;
		}
		return true;
	}

}
