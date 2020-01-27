package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the VanessaDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class VanessaDialogue extends DialoguePlugin {

	public VanessaDialogue() {

	}

	public VanessaDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 2305 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "What are you selling?", "Can you give me any Farming advice?", "I'm okay, thank you.");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "What are you selling?", "Can you give me any Farming advice?", "I'm okay, thank you.");
			stage = 10;
			break;
		case 10:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you give me any Farming advice?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm okay, thank you.");
				stage = 30;
				break;

			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes - ask a gardener.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new VanessaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello. How can I help you?");
		stage = 0;
		return true;
	}
}
