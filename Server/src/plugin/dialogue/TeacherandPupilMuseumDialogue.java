package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the TeacherandPupilMuseumDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class TeacherandPupilMuseumDialogue extends DialoguePlugin {

	public TeacherandPupilMuseumDialogue() {

	}

	public TeacherandPupilMuseumDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5947 };
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

		return new TeacherandPupilMuseumDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(5950, FacialExpression.HALF_GUILTY, "Stop pulling, we've plenty of time to see everything.");
		stage = 0;
		return true;
	}
}
