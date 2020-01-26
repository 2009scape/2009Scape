package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the wayne npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WayneDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code WayneDialogue} {@code Object}.
	 */
	public WayneDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WayneDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WayneDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WayneDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Welcome to Wayne's Chains. Do you wanna buy or", "sell some chain mail?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes please.", "No thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				end();
				break;

			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 581 };
	}
}
