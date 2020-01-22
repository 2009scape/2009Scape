package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the RomilyWeaklaxDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class RomilyWeaklaxDialogue extends DialoguePlugin {

	public RomilyWeaklaxDialogue() {

	}

	public RomilyWeaklaxDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3205 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "I'd like to buy some pies.", "Do you need any help?", "I'm good thanks.");
			stage = 1;
			break;
		case 1:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'd like to buy some pies.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Do you need any help?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'm good thanks.");
				stage = 50;
				break;
			}

			break;
		case 10:
			end();
			npc.openShop(player);
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Actually I could, you see I'm running out of stock and I", "don't have tme to bake any more pies. would you be", "willing to bake me some pies? I'll pay you well for them.");
			stage = 21;
			break;
		case 21:
			interpreter.sendOptions("Select an Option", "Sure, what do you need?", "Sorry, I can't help you.");
			stage = 22;
			break;
		case 22:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sure, what do you need?");
				stage = 200;
				break;
			case 2:
				break;
			}

			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Great, can you bake me 11 Wild Pies please.");
			stage = 201;
			break;
		case 201:
			end();
			break;
		case 50:
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new RomilyWeaklaxDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello and welcome to my pie shop, how can I help you?");
		stage = 0;
		return true;
	}
}
