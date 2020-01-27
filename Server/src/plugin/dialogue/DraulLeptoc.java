package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin for the draul leptoc npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DraulLeptoc extends DialoguePlugin {

	/**
	 * Constructs a new {@code DraulLeptoc} {@code Object}.
	 */
	public DraulLeptoc() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DraulLeptoc} {@code Object}.
	 * @param player the player.
	 */
	public DraulLeptoc(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DraulLeptoc(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What are you doing in my house..why the", "impertinence...the sheer cheek...how dare you violate my", "personal lodgings....");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I...I was just looking around...");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well get out! Get out....this is my house....and don't go", "near my daughter Juliet...she's grounded in her room", "to keep her away from that good for nothing Romeo.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes....sir....");
			stage = 3;
			break;
		case 3:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3324 };
	}
}
