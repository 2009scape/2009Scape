package plugin.interaction.inter;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used for the skilling interface.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class SkillInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(499, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		player.varpManager.get(965)
				.setVarbit(0,player.getAttribute("skillMenu",-1))
				.setVarbit(10, button - 10).send(player);
		return true;
	}
}
