package core.game.node.entity.skill.thieving;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle thieving options.
 * @author 'Vexia
 * @date 22/10/2013
 */
@Initializable
public class ThievingOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("pick-pocket", this);
		NPCDefinition.setOptionHandler("pickpocket", this);
		ObjectDefinition.setOptionHandler("steal-from", this);
		ObjectDefinition.setOptionHandler("steal from", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "pick-pocket":
		case "pickpocket":
			player.getPulseManager().run(new PickpocketPulse(player, (NPC) node, Pickpocket.forNPC((NPC) node)));
			break;
		case "steal-from":
		case "steal from":
			player.getPulseManager().run(new StallThiefPulse(player, (GameObject) node, Stall.forObject((GameObject) node)));
			player.getLocks().lockInteractions(6);
			break;
		}
		return true;
	}

}
