package plugin.interaction.item;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.TeleportManager.TeleportType;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

@InitializablePlugin
public class HouseTeleTabOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(8013).getConfigurations().put("option:break", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (player.getHouseManager().getLocation().getExitLocation() == null) {
			player.sendMessage("You must have a house to teleport to before attempting that.");
			return false;
		}
		player.getInterfaceManager().close();
		player.lock(5);
		Location location = player.getHouseManager().getLocation().getExitLocation();
		if (player.getInventory().contains(node.asItem().getId(), 1)) {
			if (player.getTeleporter().send(location.transform(0, RandomFunction.random(3), 0), TeleportType.TELETABS, 1)) {
				player.getInventory().remove(new Item(node.asItem().getId(),1));
				return true;
			}
		}
		return false;
	}

}
