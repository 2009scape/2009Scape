package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the cuffs npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CuffsDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CuffsDialogue} {@code Object}.
	 */
	public CuffsDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CuffsDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CuffsDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CuffsDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hello. nice day for a walk, isn't it?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "A walk? Oh, yes, that's what we're doing.", "We're just out here for a walk.");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'm glad you're just out here for a walk. A more suspicious", "person would think you were waiting here to attack weak-", "looking travellers.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Nope, we'd never do anuthing like that.", "Just a band of innocent walkers, that's us.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Alright, have a nice walk.");
			stage = 4;
			break;
		case 4:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3237 };
	}
}
