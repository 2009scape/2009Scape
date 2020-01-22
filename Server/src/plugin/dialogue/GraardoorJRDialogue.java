package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Dialogue for the boss pet, General Graardoor JR.
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class GraardoorJRDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GraardoorJRDialogue} {@code Object}.
	 */
	public GraardoorJRDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GraardoorJRDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GraardoorJRDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GraardoorJRDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Not sure this is going to be worth my time but...", "how are you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "SFudghoigdfpDSOPGnbSOBNfdbdnopbdnopbddfnopdf", "pofhdARRRGGGGH!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nope. Not worth it.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8594 };
	}
}
