package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used to handle the brain archery npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BrianArcheryDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BrianArcheryDialogue} {@code Object}.
	 */
	public BrianArcheryDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BrianArcheryDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BrianArcheryDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrianArcheryDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Would you like to buy some archery equipment?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "No thanks, I've got all the archery equipment I need.", "Let's see what you've got, then.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, thanks, I've got all the archery equipment I need.");
				stage = 10;
				break;
			case 2:
				end();
				npc.openShop(player);
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Okay. Fare well on your travels.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1860 };
	}
}
