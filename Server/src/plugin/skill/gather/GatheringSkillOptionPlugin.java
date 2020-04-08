package plugin.skill.gather;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.skill.free.gather.GatheringSkillPulse;
import org.crandor.game.content.skill.free.gather.mining.MiningSkillPulse;
import org.crandor.game.content.skill.free.gather.woodcutting.WoodcuttingSkillPulse;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

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
			player.getPulseManager().run(new MiningSkillPulse(player, (GameObject) node));
		} else {
			player.getPulseManager().run(new WoodcuttingSkillPulse(player, (GameObject) node));
		}
		return true;
	}

}
