package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Rerpesents the dialogue plugin used for the celburg npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CeolburgDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CeolburgDialogue} {@code Object}.
	 */
	public CeolburgDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CeolburgDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CeolburgDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CeolburgDialogue(player);
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
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Trolls!");
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
		return new int[] { 1089 };
	}
}
