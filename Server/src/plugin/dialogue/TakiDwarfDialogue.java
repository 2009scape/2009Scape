package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the TakiDwarfDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class TakiDwarfDialogue extends DialoguePlugin {

	public TakiDwarfDialogue() {

	}

	public TakiDwarfDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 7115 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hi little fellow.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What did you just say to me!?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Arrr! nothing, nothing at all..");
			stage = 3;
			break;
		case 3:
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TakiDwarfDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Arrr!");
		stage = 0;
		return true;
	}
}
