package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the TrampDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class TrampDialogue extends DialoguePlugin {

	public TrampDialogue() {

	}

	public TrampDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 11 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendOptions("What would you like to say?", "Yes, I can spare a little money.", "Sorry, you'll have to earn it yourself.");
			stage = 10;
			break;
		case 10:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, I can spare a little money.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.FURIOUS, "Sorry, you'll have to earn it yourself, just like I did.");
				stage = 50;
				break;
			}
			break;
		case 100:
			if (player.getInventory().contains(995, 1)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thank's mate!");
				stage = 101;
			} else {
				end();
				player.getPacketDispatch().sendMessage("You only need one coin to give to this tramp.");
			}
			break;
		case 101:
			end();
			break;
		case 50:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Please yourself.");
			stage = 51;
			break;
		case 51:
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TrampDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Got any spare change, mate?");
		stage = 0;
		return true;
	}
}
