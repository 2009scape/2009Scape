package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue used for the mazion npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MazionDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MazionDialogue} {@code Object}.
	 */
	public MazionDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MazionDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MazionDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MazionDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Please leave me along, a parrot stole my banana.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3114 };
	}
}
