package plugin.interaction.npc;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;

import org.crandor.plugin.InitializablePlugin;
import plugin.skill.herblore.PotionDecantingPlugin;

/**
 * Handles the zahur options.
 * @author Empathy
 *
 */
@InitializablePlugin
public class ZahurOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(3037).getConfigurations().put("option:combine", this);
		PluginManager.definePlugin(new ZahurDialoguePlugin());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		PotionDecantingPlugin.decant(player);
		player.getDialogueInterpreter().sendDialogues(node.asNpc(), FacialExpression.HALF_GUILTY, "There, all done.");
		return true;
	}

	
	/**
	 * Handles the Zahur Dialogue.
	 * @author Empathy
	 *
	 */
	public class ZahurDialoguePlugin extends DialoguePlugin {
		
		/**
		 * Constructs a new {@code ZahurDialoguePlugin} {@code Object}.
		 */
		public ZahurDialoguePlugin() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ZahurDialoguePlugin} {@code Object}.
		 * @param player the player.
		 */
		public ZahurDialoguePlugin(Player player) {
			super(player);
		}
		
		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ZahurDialoguePlugin(player);
		}

		@Override
		public boolean open(Object... args) {
			npc("I can combine your potion vials to try and make", "the potions fit into fewer vials. This service is free.", "Would you like to do this?");
			stage = 1;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 1:
				options("Yes", "No");
				stage = 2;
				break;
			case 2:
				if (buttonId == 1) {
					PotionDecantingPlugin.decant(player);
					npc("There, all done.");
				}
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 3037 };
		}
		
	}
}
