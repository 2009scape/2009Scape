package plugin.quest.free.blackknightsfortress;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.Plugin;

/**
 * Represents the plugin used to send the cabbage down the hole.
 * @author 'Vexia
 * @version 1.0
 */
public class BKCabbagePlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code BKCabbagePlugin} {@code Object}.
	 */
	public BKCabbagePlugin() {
		super(1965, 1967);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(2336, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Quest quest = player.getQuestRepository().getQuest("Black Knights' Fortress");
		if (quest.getStage(player) == 20) {
			if (event.getUsedItem().getId() == 1967) {
				player.getDialogueInterpreter().sendDialogue("This is the wrong sort of cabbage!");
				return true;
			}
			player.getDialogueInterpreter().open(992752973, true, true);
			return true;
		} else {
			player.getDialogueInterpreter().sendDialogues(player, null, "Why exactly would I want to do that?");
		}
		return false;
	}

}
