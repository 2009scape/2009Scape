package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the knagi mau dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class KangaiMauDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KangaiMauDialogue} {@code Object}.
	 */
	public KangaiMauDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KangaiMauDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KangaiMauDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KangaiMauDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello. I Kangai Mau of the Rantuki tribe.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Nice to meet you!");
			stage = 1;
			break;
		case 1:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 846 };
	}
}
