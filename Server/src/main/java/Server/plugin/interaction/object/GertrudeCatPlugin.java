package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the plugin to handle interactions with gertrudes cat.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GertrudeCatPlugin extends OptionHandler {

	/**
	 * Represents the kitten item.
	 */
	private static final Item KITTEN = new Item(13236);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2620).getConfigurations().put("option:search", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest("Gertrude's Cat");
		switch (option) {
		case "search":
			if (quest.getStage(player) == 50 && !player.getInventory().containsItem(KITTEN) && !player.getBank().containsItem(KITTEN)) {
				quest.setStage(player, 40);
			}
			if (quest.getStage(player) != 40) {
				player.getPacketDispatch().sendMessage("You search the crate.");
				player.getPacketDispatch().sendMessage("You find nothing.");
				if (RandomFunction.random(0, 3) == 1) {
					player.getPacketDispatch().sendMessage("You can hear kittens mewing close by...");
					player.setAttribute("findkitten", true);
				}
			} else {
				if (player.getAttribute("findkitten", false) && player.getInventory().freeSlots() > 0) {
					quest.setStage(player, 50);
					player.getDialogueInterpreter().sendDialogue("You find some kittens! You carefully place them into your backpack.");
					player.getInventory().add(KITTEN);
					return true;
				}
			}
			break;
		}
		return true;
	}

}
