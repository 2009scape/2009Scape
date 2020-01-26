package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Dialogue for the boss pet, Venenatis JR
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class VenenatisSpiderlingDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 */
	public VenenatisSpiderlingDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 * @param player the player.
	 */
	public VenenatisSpiderlingDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new VenenatisSpiderlingDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "It's a damn good job I don't have arachnophobia.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "We're misunderstood. Without us in your house,", "you'd be infested with flies and other", "REAL nasties.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Thanks for that enlightening fact.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Everybody gets one.");
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
		return new int[] { 8599 };
	}
}
