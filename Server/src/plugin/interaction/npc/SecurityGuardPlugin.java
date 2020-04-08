package plugin.interaction.npc;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.tools.ItemNames;

@InitializablePlugin
public final class SecurityGuardPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new SecurityGuardDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (option.equals("talk-to")) {
			player.getDialogueInterpreter().open(4375);
		}
		return true;
	}

	public final class SecurityGuardDialogue extends DialoguePlugin {

		public SecurityGuardDialogue() {}

		public SecurityGuardDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new SecurityGuardDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			if (player.getInventory().hasSpaceFor(new Item(ItemNames.SECURITY_BOOK_9003))) {
				npc("Here's a Security book for you.", "Have a nice day.");
				player.getInventory().add(new Item(ItemNames.SECURITY_BOOK_9003));
			} else {
				npc("Have a nice day.");
			}
			return false;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			return false;
		}

		@Override
		public int[] getIds() {
			return new int[] { 4375 };
		}
	}
}