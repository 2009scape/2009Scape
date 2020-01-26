package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the PhillipaDialogue dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class PhillipaDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code PhillipaDialogue} {@code Object}.
	 */
	public PhillipaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code PhillipaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public PhillipaDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PhillipaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello, who are you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hi, I'm Phillipa! Juliet's cousin? I like to keep an eye on", "her, make sure that dashing young Romeo doesn't just", "steal away from here under our plain old noses!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "He'd do it to you know... he's ever so dashing, and", "cavalier, in a wet blanket sort of way.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Romeo? Where would I find him then?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well, that's a good question! Who knows where his", "head's at most of the time... in the clouds most likely!");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "But he's probably chasing the ladies who frequent", "Varrockk market. He does like a bit of kiss chase so I've", "heard!");
			stage = 5;
			break;
		case 5:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3325 };
	}
}
