package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;

/**
 * Handles the VarrockSwordShopDialogue dialogue.
 * @author 'Vexia
 */
public class VarrockSwordShopDialogue extends DialoguePlugin {

	public VarrockSwordShopDialogue() {

	}

	public VarrockSwordShopDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 551 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, I'm okay for swords right now.");
			stage = 1;
			break;
		case 1:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, please.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I'm okay for swords right now.");
				stage = 10;
				break;
			}

			break;
		case 2:
			end();
			npc.openShop(player);
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Come back if you need any.");
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

		return new VarrockSwordShopDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello, adventurer. Can I interest you in some swords?");
		stage = 0;
		return true;
	}
}
