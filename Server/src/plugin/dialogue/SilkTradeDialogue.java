package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the SilkTradeDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SilkTradeDialogue extends DialoguePlugin {

	public SilkTradeDialogue() {

	}

	public SilkTradeDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 539 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "How much are they?", "No, slik doesn't suit me.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "How much are they?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, silk doesn't suit me.");
				stage = 2000;
				break;

			}

			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "3gp.");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "No, that's too much for me.", "Okay, that sounds good.");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, that's too much for me.");
				stage = 110;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Okay, that sounds good.");
				stage = 6000;
				break;

			}
			break;
		case 110:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "2gp and that's as low as I'll go.");
			stage = 111;
			break;
		case 111:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I'm not selling it for any less. You'll only go and sell it", "in Varrock for a profit.");
			stage = 113;
			break;
		case 113:
			interpreter.sendOptions("Select an Option", "2gp sounds good.", "No really, I don't want it.");
			stage = 114;
			break;
		case 114:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "2gp sounds good.");
				stage = 1000;
				break;
			case 2:

				break;

			}
			break;
		case 1000:
			if (player.getInventory().contains(995, 2) && player.getInventory().freeSlots() != 0) {
				player.getInventory().remove(new Item(995, 2));
				player.getInventory().add(new Item(950, 1));
				interpreter.sendDialogue("You buy some silk for 2gp.");
				stage = 1001;
			} else if (player.getInventory().freeSlots() == 0) {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't have enough room, sorry.");
				stage = 2000;
			} else if (!player.getInventory().contains(995, 2)) {
				end();
				player.getPacketDispatch().sendMessage("You need 2 gold coins to buy silk.");
			}
			break;
		case 1001:
			end();
			break;
		case 2000:
			end();
			break;
		case 6000:
			if (player.getInventory().contains(995, 3) && player.getInventory().freeSlots() != 0) {
				player.getInventory().remove(new Item(995, 3));
				player.getInventory().add(new Item(950, 1));
				interpreter.sendDialogue("You buy some silk for 3gp.");
				stage = 1001;
			} else if (player.getInventory().freeSlots() == 0) {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't have enough room, sorry.");
				stage = 2000;
			} else if (!player.getInventory().contains(995, 3)) {
				end();
				player.getPacketDispatch().sendMessage("You need 3 gold coins to buy silk.");
			}
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SilkTradeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Do you want to buy any fine silks?");
		stage = 0;
		return true;
	}
}
