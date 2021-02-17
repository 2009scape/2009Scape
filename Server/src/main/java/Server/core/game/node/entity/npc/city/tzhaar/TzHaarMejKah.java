package core.game.node.entity.npc.city.tzhaar;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Handles the TzHaarMejKah dialogue.
 * @author 'Vexia
 */
@Initializable
public class TzHaarMejKah extends DialoguePlugin {

	public TzHaarMejKah() {

	}

	public TzHaarMejKah(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TzHaarMejKah(player);
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
			interpreter.sendOptions("Select an Option", "What is this place?", "Who's the current champion?", "What did you call me?", "No I'm fine thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who's the current champion?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What did you call me?");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No I'm fine thanks.");
				stage = 40;
				break;

			}
			break;
		case 40:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Are you not JalYt-Ket?");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I guess so...");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well then, no problems.");
			stage = 13;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is the fight pit, TzHaar-Xil made it for their sport", "but many JalYt come here to fight too.", "If you are wanting to fight then enter the cage, you", "will be summoned when the next round is ready to begin.");
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
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No rules, you use whatever you want. Last person", "standing wins and is declared champion, they stay in", "the pit for the next fight.");
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
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You ask a lot of questions.", "Champion gets TokKul as reward, but more fights the more", "TokKul they get.");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...");
			stage = 19;
			break;
		case 19:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Before you ask, TokKul is like your coins.");
			stage = 400;
			break;
		case 400:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Gold is like you JalYt, soft and easily broken, we use", "hard rock forged in fire like TzHaar!");
			stage = 401;
			break;
		case 401:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah that would be " + npc.getAttribute("fp_champn", "JalYt-Ket-Emperor"));
			stage = 21;
			break;
		case 21:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2618 };
	}
}
