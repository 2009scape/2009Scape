package plugin.skill.fishing;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.skill.free.fishing.FishingOption;
import org.crandor.game.content.skill.free.fishing.FishingPulse;
import org.crandor.game.content.skill.free.fishing.FishingSpot;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to start fishing.
 * @author Ceikry
 * @version 1.2
 */
@InitializablePlugin
public final class FishingOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("net", this);
		NPCDefinition.setOptionHandler("bait", this);
		NPCDefinition.setOptionHandler("lure", this);
		NPCDefinition.setOptionHandler("cage", this);
		NPCDefinition.setOptionHandler("harpoon", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		NPC npc = (NPC) node;
		FishingSpot spot = FishingSpot.forId(npc.getId());
		if (spot == null) {
			return false;
		}
		FishingOption opt = FishingOption.forName(option);

		if (opt == null) {
			return false;
		}
		player.getPulseManager().run(new FishingPulse(player, npc, opt));
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return null;
	}
}
