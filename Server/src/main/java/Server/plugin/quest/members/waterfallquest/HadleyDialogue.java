package plugin.quest.members.waterfallquest;

import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;

/**
 * Handles Almera's Dialogue for the Waterfall Quest.
 * @author Splinter
 */
public class HadleyDialogue extends DialoguePlugin {

	public HadleyDialogue() {

	}

	public HadleyDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("hadley_dialogue"), 302 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 100:
			end();
			break;

		/* Main dialogue sequence */
		case 0:
			if (player.getInventory().contains(292, 1)) {
				interpreter.sendDialogues(302, FacialExpression.HALF_GUILTY, "I hope you're enjoying your stay, there should be lots", "of useful information in that book: places to go, people to", "see.");
				stage = 100;
			} else {
				interpreter.sendDialogues(302, FacialExpression.HALF_GUILTY, "Are you on holiday? If so you've come to the right", "place. I'm Hadley the tourist guide, anything you need", "to know just ask me. We have some of the most unspoilt", "wildlife and scenery in " + GameWorld.getName() + ".");
				stage = 1;
			}
			break;
		case 1:
			interpreter.sendDialogues(302, FacialExpression.HALF_GUILTY, "People come from miles around to fish in the clear lakes", "or to wander the beautiful hill sides.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "It is quite pretty.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(302, FacialExpression.HALF_GUILTY, "Surely prety is an understatement kind Sir. Beautiful,", "amazing or possibly life-changing would be more suitable", "wording. Have you seen the Baxtorian waterfall?", "Named after the elf king who was buried underneath.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks then, goodbye.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(302, FacialExpression.HALF_GUILTY, "Enjoy your visit.");
			stage = 100;
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HadleyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there.");
		stage = 0;
		return true;
	}
}