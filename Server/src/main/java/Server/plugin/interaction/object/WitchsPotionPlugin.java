package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.quest.free.WitchsPotion;

/**
 * Represents the witchs potion plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WitchsPotionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2024).getHandlers().put("option:drink from", this);
		ObjectDefinition.forId(2024).getHandlers().put("option:Drink From", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest(WitchsPotion.NAME);
		switch (quest.getStage(player)) {
		case 20:
		case 100:
			player.getDialogueInterpreter().sendDialogues(player, null, "As nice as that looks I think I'll give it a miss for now.");
			break;
		case 40:
			player.getDialogueInterpreter().open(307, true, 1);
			break;
		}
		return true;
	}

}
