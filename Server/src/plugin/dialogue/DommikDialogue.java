package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the DommikDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class DommikDialogue extends DialoguePlugin {

	public DommikDialogue() {

	}

	public DommikDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 545 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "No, thanks, I've got all the Crafting equipment I need.", "Let's see what you've got, then.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thanks, I've got all the Crafting equipment I need.");
				stage = 10;
				break;
			case 2:
				end();
				npc.openShop(player);
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Okay. Fare well on your travels.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new DommikDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Would you like to buy some Crafting equipment?");
		stage = 0;
		return true;
	}
}
