package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for fortunato.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FortunatoDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FortunatoDialogue} {@code Object}.
	 */
	public FortunatoDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FortunatoDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FortunatoDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FortunatoDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Can I help you at all?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, what are you selling?", "Not at the momennt.");
			stage = 1;
			break;
		case 1:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, what are you selling?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Then move along, you filthy ragamuffin, I have customers", "to server!");
				stage = 24;
				break;
			}

			break;
		case 10:
			end();
			npc.openShop(player);
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, indeed. The finest wine in Misthalin.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Care to take a look at my wares?");
			stage = 269;
			break;
		case 269:
			interpreter.sendOptions("Select an Option", "Yes.", "Not at the moment.");
			stage = 22;
			break;
		case 22:

			switch (buttonId) {
			case 1:
				end();
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Not at the moment.");
				stage = 23;
				break;
			}

			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Then move along, you filthy ragamuffin, I have customers", "to server!");
			stage = 24;
			break;
		case 24:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3671 };
	}
}
