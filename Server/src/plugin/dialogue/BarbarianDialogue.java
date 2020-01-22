package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for barbarians.
 * @author 'Vexia
 * @version 1.5
 */
@InitializablePlugin
public final class BarbarianDialogue extends DialoguePlugin {

	/**
	 * Represents the barbarian npc ids.
	 */
	private static final int[] IDS = new int[] { 12, 3246, 3247, 3248, 3249, 3250, 3251, 3252, 3253, 3255, 3256, 3257, 3258, 3259, 3260, 3261, 3262, 3263, 5909 };

	/**
	 * Constructs a new {@code BarbarianDialogue} {@code Object}.
	 */
	public BarbarianDialogue() {
		/**
		 * empty
		 */
	}

	/**
	 * Constructs a new {@code BarbarianDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BarbarianDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BarbarianDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "What do you want, outerlander?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Let's fight!", "Goodbye.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Let's fight!");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Goodbye.");
				stage = 10;
				break;
			}
			break;
		case 10:
			end();
			break;
		case 20:
			end();
			npc.getProperties().getCombatPulse().attack(player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return IDS;
	}

}
