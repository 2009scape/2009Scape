package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the brimhaven pirate dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BrimhavenPirateDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BrimhavenPirateDialogue} {@code Object}.
	 */
	public BrimhavenPirateDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BrimhavenPirateDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BrimhavenPirateDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrimhavenPirateDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Man overboard!");
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
		return new int[] { 183, 6349, 6350, 6346, 6347, 6348, 799 };
	}
}
