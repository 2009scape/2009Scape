package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the jeff npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class JeffDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code JeffDialogue} {@code Object}.
	 */
	public JeffDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code JeffDialogue} {@code Object}.
	 * @param player the player.
	 */
	public JeffDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new JeffDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Tell me, is the guard still watching us?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Why would you care if there's a guard watching you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh, forget it.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3240 };
	}
}
