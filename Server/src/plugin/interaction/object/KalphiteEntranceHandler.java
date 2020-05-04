package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Handles the Kalphite hive entrance.
 * @author Emperor
 */
@InitializablePlugin
public final class KalphiteEntranceHandler extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		UseWithHandler handler = new UseWithHandler(954) {
			@Override
			public boolean handle(NodeUsageEvent event) {
				GameObject object = (GameObject) event.getUsedWith();
				if (object.getId() == 3827 || object.getId() == 23609) {
					if (event.getPlayer().getInventory().remove(event.getUsedItem())) {
						ObjectBuilder.replace(object, object.transform(object.getId() + 1), 500);
						return true;
					}
				}
				return false;
			}

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				return this;
			}
		};
		UseWithHandler.addHandler(3827, UseWithHandler.OBJECT_TYPE, handler);
		UseWithHandler.addHandler(23609, UseWithHandler.OBJECT_TYPE, handler);
		ObjectDefinition.forId(3828).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(3829).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(23610).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(3832).getConfigurations().put("option:climb-up", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		Location destination = null;
		switch (object.getId()) {
		case 3828:
			destination = Location.create(3483, 9509, 2);
			break;
		case 3829:
			destination = Location.create(3229, 3109, 0);
			break;
		case 23610:
			destination = Location.create(3508, 9493, 0);
			break;
		case 3832:
			destination = Location.create(3509, 9496, 2);
			break;
		}
		final Location dest = destination;
		player.lock(2);
		player.animate(Animation.create(828));
		GameWorld.Pulser.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				player.getProperties().setTeleportLocation(dest);
				return true;
			}
		});
		return true;
	}

}
