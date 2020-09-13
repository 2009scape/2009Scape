package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;

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
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello, who are you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi, I'm Phillipa! Juliet's cousin? I like to keep an eye on", "her, make sure that dashing young Romeo doesn't just", "steal her away from here under our plain old noses!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "He'd do it too you know... he's ever so dashing, and", "cavalier, in a wet blanket sort of way.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Romeo? Where would I find him then?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, that's a good question! Who knows where his", "head's at most of the time... in the clouds most likely!");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "But he's probably chasing the ladies who frequent", "Varrock market. He does like a bit of kiss chase so I've", "heard!");
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
