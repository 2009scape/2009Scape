package plugin.zone.phasmatys;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the ghost sailor dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class GhostSailorDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GhostSailorDialogue} {@code Object}.
	 */
	public GhostSailorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GhostSailorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GhostSailorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GhostSailorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (PhasmatysZone.hasAmulet(player)) {
			player("Hi there. Why do you still bother having ships here? I", "mean - you're dead, what use are they to you?");
			stage = 10;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Woooo wooo wooooo woooo");
			stage = 0;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogue("You cannot understand the ghost.");
			stage = 1;
			break;
		case 1:
			end();
			break;
		case 10:
			npc("We keep ships because we still need trade in", "Phasmatys. Every trader that comes to Phastmatys is", "made to worship the Ectofuntus, so that the Ectopower", "doesn't run out.");
			stage++;
			break;
		case 11:
			player("So, without traders to worship in the Temple you're", "history right?");
			stage++;
			break;
		case 12:
			npc("Aye, matey.");
			stage++;
			break;
		case 13:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1703, 1704 };
	}
}