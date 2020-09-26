package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Handles the SanfewDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SanfewDialogue extends DialoguePlugin {

	public SanfewDialogue() {

	}

	public SanfewDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SanfewDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What can I do for you young 'un?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getQuestRepository().getQuest("Drudic Ritual").getStage(player) == 20) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Did you bring me the required ingredients for the", "potion?");
				stage = 100;
				break;
			}
			if (player.getQuestRepository().getQuest("Drudic Ritual").getStage(player) == 10) {
				interpreter.sendOptions("Select an Option", "I've been sent to help purify the Varrock stone circle.", "Actually, I don't need to speak to you.");
				stage = 2;
				break;
			}
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Nothing... I'll just be on my way now.");
			stage = 1;
			break;
		case 1:
			end();
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I've been sent to assist you with the ritual to purify the", "Varrockian stone circle.");
				stage = 5;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Actually, I don't need to speak to you.");
				stage = 3;
				break;
			}
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, we all make mistakes sometimes.");
			stage = 4;
			break;
		case 4:
			end();
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, what I'm struggling with right now is the meats", "needed for the potion to honour Guthix. I need the raw", "meat of four different animals for it, but not just any", "old meats will do.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Each meat has to be dipped individually into the", "Cauldron of Thunder for it to work correctly.");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where can I find this cauldron?");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It is located somewhere in the mysterious underground", "halls which are located somewhere in the woods just", "South of here. They are too dangerous for me to go", "myself however.");
			player.getQuestRepository().getQuest("Drudic Ritual").setStage(player, 20);
			stage = 9;
			break;
		case 9:
			end();
			break;
		case 100:
			if (player.getInventory().containItems(522, 523, 524, 525)) {
				interpreter.sendDialogues(player, null, "Yes, I have all four now!");
				stage = 200;
				break;
			}
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, not yet...");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well let me know when you do young 'un.");
			stage = 102;
			break;
		case 102:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll get on with it.");
			stage = 103;
			break;
		case 103:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Good, good.");
			stage = 104;
			break;
		case 104:
			end();
			break;
		case 200:
			interpreter.sendDialogues(npc, null, "Well hand 'em over then lad!");
			stage = 201;
			break;
		case 201:
			interpreter.sendDialogues(npc, null, "Thank you so much adventurer! These meats will allow", "our potion to honour Guthix to be completed, and bring", "one step closer to reclaiming our stone circle!");
			stage = 202;
			break;
		case 202:
			player.getInventory().remove(new Item(522, 1), new Item(523, 1), new Item(524, 1), new Item(525, 1));
			player.getQuestRepository().getQuest("Drudic Ritual").setStage(player, 99);
			player.getQuestRepository().syncronizeTab(player);
			interpreter.sendDialogues(npc, null, "Now go and talk to Kaqemeex and he will introduce", "you to the wonderful world of herblore and potion", "making!");
			stage = 203;
			break;
		case 203:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 454 };
	}
}
