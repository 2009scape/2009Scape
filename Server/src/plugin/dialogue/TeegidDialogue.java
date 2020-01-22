package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Direction;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.Location;

/**
 * Handles the TeegidDialogue dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class TeegidDialogue extends DialoguePlugin {

	public TeegidDialogue() {

	}

	public TeegidDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1213 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yeah. What is it to you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nice day for it.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Suppose it is.");
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

		return new TeegidDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc.setDirection(Direction.SOUTH);
		npc.faceLocation(Location.create(2923, 3418, 0));
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "So, you're doing laundry, eh?");
		stage = 0;
		npc.faceLocation(Location.create(2923, 3418, 0));
		return true;
	}
}
