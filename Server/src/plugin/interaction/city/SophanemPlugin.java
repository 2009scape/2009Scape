package plugin.interaction.city;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * The plugin for handling stuff in Sophanem.
 * @author jamix77
 *
 */
@InitializablePlugin
public class SophanemPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(20277).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(20275).getConfigurations().put("option:climb-down", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : ((Item) node).getId();
		switch (id) {
		case 20275:
			ClimbActionHandler.climb(player, new Animation(827), Location.create(2799, 5160, 0));
			break;
		case 20277:
			ClimbActionHandler.climb(player, new Animation(828), Location.create(3315,2796,0));
			break;
		}
		return true;
	}

}
