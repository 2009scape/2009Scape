package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the SeerBartenderDialogur dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SeerBartenderDialogue extends DialoguePlugin {

	public SeerBartenderDialogue() {

	}

	public SeerBartenderDialogue(Player player) {
		super(player);
	}

	public void buy(int item, int ammount) {
		if (player.getInventory().freeSlots() == 0) {
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't seem to have room, sorry.");
			stage = 99;
		}
		if (!player.getInventory().contains(995, ammount)) {
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I don't seem to have enough coins.");
			stage = 99;
		} else {
			end();
			player.getInventory().remove(new Item(995, ammount));
			player.getInventory().add(new Item(item, 1));
			if (item == 1917) {
				player.getPacketDispatch().sendMessage("You buy a beer.");
			} else if (item == 2327) {
				player.getPacketDispatch().sendMessage("You buy a nice hot meat pie.");
			} else {
				player.getPacketDispatch().sendMessage("You buy a bowl of home made stew.");
			}
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 738, 737 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "What do you have?", "Beer please.", "I don't really want anything thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What do you have?");
				stage = 30;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Beer please.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't really want anything thanks.");
				stage = 67;
				break;

			}
			break;
		case 67:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well we have beer, or if you want some food, we have", "our home made stew and meat pies.");
			stage = 31;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "One beer comng up. Ok, that'll be two coins.");
			stage = 101;
			break;
		case 10:
			end();
			break;
		case 31:
			interpreter.sendOptions("Select an Option", "Beer please.", "I'll try the meat pie.", "Could I have some stew please?", "I don't really want anything thanks.");
			stage = 32;
			break;
		case 32:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Beer please.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'll try the meat pie.");
				stage = 200;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Could I have some stew please?");
				stage = 300;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I don't really want anything thanks.");
				stage = 30;
				break;

			}
			break;
		case 99:
			end();
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "One beer comng up. Ok, that'll be two coins.");
			stage = 101;
			break;
		case 101:
			buy(1917, 2);
			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Okay, that will be 16 coins.");
			stage = 201;
			break;
		case 201:
			buy(2327, 16);
			break;
		case 300:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "A bowl of stew, that'll be 20 coins please.");
			stage = 301;
			break;
		case 301:
			buy(2003, 20);
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SeerBartenderDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Good morning, what would you like?");
		stage = 0;
		return true;
	}
}
