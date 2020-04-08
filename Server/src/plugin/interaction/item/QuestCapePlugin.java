package plugin.interaction.item;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.global.action.EquipHandler;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used for the quest cape and hood item.
 * @author 'Vexia
 */
@InitializablePlugin
public final class QuestCapePlugin extends OptionHandler {
	final int MAX_QP = 67;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(9813).getConfigurations().put("option:wear", this);
		ItemDefinition.forId(9814).getConfigurations().put("option:wear", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!(player.getQuestRepository().getPoints() >= MAX_QP)) {
			player.getPacketDispatch().sendMessage("You cannot wear this " + node.getName().toLowerCase() + " yet.");
			return true;
		}
		return EquipHandler.SINGLETON.handle(player, node, option);
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
