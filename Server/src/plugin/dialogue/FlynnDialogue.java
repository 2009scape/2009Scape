package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the flynn npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FlynnDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FlynnDialogue} {@code Object}.
	 */
	public FlynnDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FlynnDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FlynnDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FlynnDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello. Do you want to buy or sell any maces?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "No, thanks.", "Well, I'll have a look, at least.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thanks.");
				stage = 10;
				break;
			case 2:
				end();
				npc.openShop(player);
				break;

			}
			break;
		case 10:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 580 };
	}
}
