package plugin.skill.gather;

import core.cache.def.impl.ObjectDefinition;
import plugin.skill.gather.mining.MiningSkillPulse;
import plugin.skill.gather.woodcutting.WoodcuttingSkillPulse;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the gathering skill option handler plugin.
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class GatheringSkillOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("chop-down", this);
		ObjectDefinition.setOptionHandler("chop down", this);
		ObjectDefinition.setOptionHandler("mine", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if(option.equals("mine")){
			player.getPulseManager().run(new MiningSkillPulse(player, node.asObject()));
		} else {
			player.getPulseManager().run(new WoodcuttingSkillPulse(player, node.asObject()));
		}
		return true;
	}

}
