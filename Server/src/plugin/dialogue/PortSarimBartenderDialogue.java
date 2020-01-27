package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the PortSarimBartenderDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class PortSarimBartenderDialogue extends DialoguePlugin {

	public PortSarimBartenderDialogue() {

	}

	public PortSarimBartenderDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 734 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there!");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Choose an option:", "Could I buy a beer, please.", "Bye, then.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Could I buy a beer, please?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Bye, then.");
				stage = 20;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Sure, that will be two gold coins, please.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, here you go.");
			stage = 12;
			break;
		case 12:
			if (player.getInventory().contains(995, 2)) {
				interpreter.sendDialogue("You buy a pint of beer.");
				player.getInventory().remove(new Item(995, 2));
				player.getInventory().add(new Item(1917, 1));
				stage = 13;
			} else {
				player.getPacketDispatch().sendMessage("You need 2 gold coins to buy beer.");
				end();
			}
			break;
		case 13:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Come back soon!");
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

		return new PortSarimBartenderDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Good day to you!");
		stage = 0;
		return true;
	}
}
