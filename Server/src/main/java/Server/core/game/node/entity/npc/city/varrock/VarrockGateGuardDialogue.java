package core.game.node.entity.npc.city.varrock;

import core.game.content.dialogue.DialoguePlugin;
import core.game.content.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.plugin.Initializable;
import core.game.node.entity.player.Player;

/**
 * Handles the VarrockGateGuardDialogue dialogue.
 * @author 'Vexia
 */
@Initializable
public class VarrockGateGuardDialogue extends DialoguePlugin {

	public VarrockGateGuardDialogue() {

	}

	public VarrockGateGuardDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 368 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			end();
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new VarrockGateGuardDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Please don't disturb me, I've got to keep an eye out for", "suspicious indiduals.");
		stage = 0;
		return true;
	}
}
