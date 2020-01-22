package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the RoomikDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class RoomikDialogue extends DialoguePlugin {

	public RoomikDialogue() {

	}

	public RoomikDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 585 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Choose an option:", "Let's see what you've got, then.", "No thanks, I've got all the crafting equipment I need.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No thanks, I've got all the crafting euipment I need.");
				stage = 20;
				break;

			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Okay. Fare well on your travels.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new RoomikDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Would you like to buy some Crafting equipment?");
		stage = 0;
		return true;
	}
}
