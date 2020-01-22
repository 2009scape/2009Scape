package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the RoladDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class RoladDialogue extends DialoguePlugin {

	public RoladDialogue() {

	}

	public RoladDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1841 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ehm... well... my name is " + player.getUsername() + ", if that rings any bell?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No, never heard of you.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new RoladDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh, hello... do I know you?");
		stage = 0;
		return true;
	}
}
