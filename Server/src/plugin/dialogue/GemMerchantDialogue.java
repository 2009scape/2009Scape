package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the gem merchant dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GemMerchantDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GemMerchantDialogue} {@code Object}.
	 */
	public GemMerchantDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GemMerchantDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GemMerchantDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GemMerchantDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Here, look at my lovely gems.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			npc.openShop(player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 570 };
	}
}
