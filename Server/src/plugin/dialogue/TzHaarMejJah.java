package plugin.dialogue;

import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the TzHaarMejJal dialogue.
 * @author 'Vexia
 * @author Empathy
 */
@InitializablePlugin
public class TzHaarMejJah extends DialoguePlugin {

	public TzHaarMejJah() {

	}

	public TzHaarMejJah(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TzHaarMejJah(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You want help JalYt-Ket-" + player.getUsername() + "?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", player.getInventory().containItems(6570) ? "I have a fire cape here." : "What is this place?", "What did you call me?", "No I'm fine thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				if (player.getInventory().containItems(6570)) {
					interpreter.open(DialogueInterpreter.getDialogueKey("firecape-exchange"), npc);
					break;
				}
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What did you call me?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No I'm fine thanks.");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is the fight caves, TzHaar-Xil made it for practice,", "but many JalYt come here to fight too.", "Just enter the cave and make sure you're prepared.");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "Are there any rules?", "Ok thanks.");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Are there any rules?");
				stage = 14;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok thanks.");
				stage = 13;
				break;
			}
			break;
		case 13:
			end();
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Rules? Survival is the only rule in there.");
			stage = 15;
			break;
		case 15:
			interpreter.sendOptions("Select an Option", "Do I win anything?", "Sounds good.");
			stage = 16;
			break;
		case 16:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do I win anything?");
				stage = 17;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sounds good.");
				stage = 13;
				break;

			}
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You ask a lot of questions.", "Might give you TokKul if you last long enough.");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...");
			stage = 19;
			break;
		case 19:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Before you ask, TokKul is like your Coins.");
			stage = 500;
			break;
		case 500:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Gold is like you JalYt, soft and easily broken, we use", "hard rock forged in fire like TzHaar!");
			stage = 501;
			break;
		case 501:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Are you not JalYt-Ket?");
			stage = 21;
			break;
		case 21:
			interpreter.sendOptions("Selec an Option", "What's a 'JalYt-Key'?", "I guess so...", "No I'm not!");
			stage = 22;
			break;
		case 22:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's a 'JalYt-Ket'?");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I guess so...");
				stage = 200;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No I'm not!");
				stage = 300;
				break;
			}
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That what you are... you tough and strong no?");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well yes I suppose I am...");
			stage = 102;
			break;
		case 102:
			end();
			break;
		case 200:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I guess so....");
			stage = 201;
			break;
		case 201:
			end();
			break;
		case 300:
			end();
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("tzhaar-mej"), 2617 };
	}
}
