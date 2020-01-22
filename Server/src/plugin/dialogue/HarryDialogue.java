package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the harry npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HarryDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HarryDialogue} {@code Object}.
	 */
	public HarryDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HarryDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HarryDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HarryDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Welcome! You can buy Fishing equipment at my store.", "We'll also give you a good price for any fish that you", "catch.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Let's see what you've got, then.", "Sorry, I'm not interested.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, I'm not interested.");
				stage = 20;
				break;
			}
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 576 };
	}
}
