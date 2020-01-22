package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the bros np.c
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BrosDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BrosDialogue} {@code Object}.
	 */
	public BrosDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BrosDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BrosDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrosDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Out of my way, punk");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6026, 6032 };
	}
}
