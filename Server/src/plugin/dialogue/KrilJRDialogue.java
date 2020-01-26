package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Dialogue for the boss pet, K'ril Tsutsaroth JR.
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class KrilJRDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KrilJRDialogue} {@code Object}.
	 */
	public KrilJRDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KrilJRDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KrilJRDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KrilJRDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "How's life in the light?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Burns slightly.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You seem much nicer than your father. He's mean.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "If you were stuck in a very dark cave for centuries", "you'd be pretty annoyed too.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I guess.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "He's actually quite mellow really.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Uh.... Yeah.");
			stage = 6;
			break;
		case 6:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8591 };
	}
}
