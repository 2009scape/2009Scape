package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the guidors wife dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GuidorsWifeDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GuidorsWifeDialogue} {@code Object}.
	 */
	public GuidorsWifeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GuidorsWifeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GuidorsWifeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GuidorsWifeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length == 2) {
			interpreter.sendDialogues(342, FacialExpression.HALF_GUILTY, "Please leave my husband alone. He's very sick, and I don't", "want anyone bothering him.");
			stage = 100;
			return true;
		}
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendDialogues(342, FacialExpression.HALF_GUILTY, "Oh hello, I can't chat now. I have to keep an eye on my", "husband. He's very ill!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm sorry to hear that!");
			stage = 2;
			break;
		case 2:
			end();
			break;
		case 100:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 342 };
	}
}
