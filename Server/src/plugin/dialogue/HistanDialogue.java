package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the histan dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HistanDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HistanDialogue} {@code Object}.
	 */
	public HistanDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HistanDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HistanDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HistanDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hi!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Welcome to Burthorpe Supplies. Your last shop before", "heading north into the mountains!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Would you like to buy something?");
			stage = 2;
			break;
		case 2:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thanks.");
			stage = 3;
			break;
		case 3:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, please.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, thanks.");
				stage = 20;
				break;
			}
			break;
		case 10:
			end();
			npc.openShop(player);
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1083 };
	}
}
