package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Dialogue for the boss pet, Kree'Arra JR.
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class KreeJrDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KreeJrDialogue} {@code Object}.
	 */
	public KreeJrDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KreeJrDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KreeJrDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KreeJrDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Huh... that's odd... I thought that would", "be big news.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "You thought what would be big news?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well there seems to be an absence of a certain", " ornithological piece: a headline regarding mass", "awareness of a certain avian variety.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "What are you talking about?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh have you not heard? It was my understanding that", "everyone had heard....");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Heard wha...... OH NO!!!!?!?!!?!");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "OH WELL THE BIRD, BIRD, BIRD, BIRD", "BIRD IS THE WORD.", "OH WELL THE BIRD, BIRD, BIRD,", "BIRD BIRD IS THE WORD.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogue("There's a slight pause as Kree'Arra Jr. goes stiff.");
			stage = 7;
			break;
		case 7:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8592 };
	}
}
