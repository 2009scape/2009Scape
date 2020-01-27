package plugin.interaction.npc;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Handles the RoavarDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class RoavarDialogue extends DialoguePlugin {

	public RoavarDialogue() {

	}

	public RoavarDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new RoavarDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Greeting traveller. Welcome to 'The Hair Of The Dog'", "Tavern. What can I do you for?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Can I buy a beer?", "Can I hear some gossipp?", "Do you have a spare silver sickle?", "Nothing thanks.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I buy a beer?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I hear some gossip?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Would you happen to have a spare silver sickle?");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Nothing thanks.");
				stage = 40;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well that's my speciality! The local brew's named", "'Moonlight Mead' and will set you back 5 gold.", "Waddya say? Fancy a pint?");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "Yes please.", "Actually, no thanks.");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes please.");
				stage = 15;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Actually, no thanks.");
				stage = 14;
				break;

			}
			break;
		case 14:
			end();
			break;
		case 15:
			if (player.getInventory().contains(995, 5)) {
				if (player.getInventory().remove(new Item(995, 5))) {
					player.getInventory().add(new Item(2955, 1));
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Here ya go pal. Enjoy!");
					stage = 16;
				}
			} else {
				end();
				player.getPacketDispatch().sendMessage("You need 5 gold coins to buy a pint of beer.");
			}
			break;
		case 16:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am not one to gossip!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			if (player.getInventory().contains(2963, 1)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I don't have a spare lying around, sorry friend.", "Hopefully you'll find something else that can protect you", "against ghasts!");
				stage = 31;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, well I do have one lying around.", "I suppose you could have it.");
				stage = 42;
			}
			break;
		case 31:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "...I don't know why you talked to me if you don't want", "anything then...");
			stage = 41;
			break;
		case 41:
			end();
			break;
		case 42:
			if (player.getInventory().freeSlots() < 1) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh, nevermind. It seems your backpack is full.");
			} else {
				interpreter.sendDialogue("The bartender hands you a silver sickle.");
				player.getInventory().add(new Item(2963, 1));
			}
			stage = 31;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1042 };
	}
}
