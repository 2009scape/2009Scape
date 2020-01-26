package plugin.dialogue;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the SawmillOperator dialogue.
 * @author Vexia
 */
@InitializablePlugin
public class SawmillOperator extends DialoguePlugin {

	public SawmillOperator() {

	}

	public SawmillOperator(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SawmillOperator(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Do you want me to make some planks for you? Or", "would you be interested in some other housing supplies?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Planks please!", "What kind of planks can you make?", "Can I buy some housing supplies?", "Nothing, thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Planks please!");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What kind of planks can you make?");
				stage = 20;
				break;
			case 3:
				end();
				npc.openShop(player);
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nothing, thanks.");
				stage = 40;
				break;
			}
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well come back when you want some. You can't get", "good quality planks anywhere but here!");
			stage = 41;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "What kind of planks do you want?");
			stage = 11;
			break;
		case 11:
			end();
			player.getInterfaceManager().open(new Component(403));
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I can make planks from wood, oak, teak and mahogany.", "I don't make planks from other woods as they're no", "good for making furniture.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Wood and oak are all over the place, but teak and", "mahogany can only be found in a few places like", "Karamja and Etceteria.");
			stage = 22;
			break;
		case 22:
			end();
			break;
		case 41:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4250 };
	}
}
