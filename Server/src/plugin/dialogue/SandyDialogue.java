package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the SandyDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SandyDialogue extends DialoguePlugin {

	public SandyDialogue() {

	}

	public SandyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SandyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Nice day for sand isn't it?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		end();
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3110 };
	}
}
