package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the filelio dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FilelioDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FilelioDialogue} {@code Object}.
	 */
	public FilelioDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FilelioDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FilelioDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FilelioDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "H-hello. You l-look like a s-stranger to these p-parts.", "Would you l-ike to buy something? I h-have some s-", "special offers at the m-minute...some s-sample bottles for", "s-storing s-snail slime.");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thanks.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, please.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks.");
				stage = 20;
				break;

			}
			break;
		case 20:
			end();
			break;
		case 10:
			end();
			npc.openShop(player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1040 };
	}
}
