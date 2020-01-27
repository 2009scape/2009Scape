package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the heron dialgoue.
 * @author Empathy
 *
 */
@InitializablePlugin
public final class HeronDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code HeronDialogue} {@code Object}.
		 */
		public HeronDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code HeronDialogue} {@code Object}.
		 * 
		 * @param player the player.
		 */
		public HeronDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new HeronDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Hop inside my mouth, if you want to live!");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm not falling for that... I'm not a fish! I've got more", "foresight than that.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 8647 };
		}
	}
