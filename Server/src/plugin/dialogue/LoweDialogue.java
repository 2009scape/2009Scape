package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represesents the dialogue plugin used for lowe.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LoweDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LoweDialogue} {@code Object}.
	 */
	public LoweDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LoweDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LoweDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LoweDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Welcome to Lowe's Archery Emporium. Do you want", "to see my wares?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, I prefer to bash things close up.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, I prefer to bash things close up.");
				stage = 3;
				break;
			}

			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Humph, philistine.");
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
		return new int[] { 550 };
	}

}
