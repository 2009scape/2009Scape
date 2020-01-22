package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Dialogue for the boss pet, Calliso JR
 * @author Splinter
 * @version 1.0
 */
@InitializablePlugin
public final class CallistoCubDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 */
	public CallistoCubDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ChaosElementalJRDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CallistoCubDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CallistoCubDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Why the grizzly face?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "You're not funny...");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You should get in the.... sun more.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "You're really not funny...");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "One second, let me take a picture of you with my....", "kodiak camera.");
			stage = 4;
			break;
		case 4:
			npc(FacialExpression.OSRS_NORMAL, "...");
			stage = 5;
			break;
		case 5:
			player("Feeling... blue.");
			stage = 6;
			break;
		case 6:
			npc(FacialExpression.OSRS_NORMAL, "If you don't stop, I'm going to leave some...", "brown... at your feet, human.");
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
		return new int[] { 8597 };
	}
}
