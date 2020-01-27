package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin for the betty npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BettyDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BettyDialogue} {@code Object}.
	 */
	public BettyDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BettyDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BettyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BettyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to the magic emporium.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Can I see your wares?", "Sorry, I'm not into Magic.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I'm not into Magic.");
				stage = 20;
				break;
			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, if you see ayone who is into Magic, please send", "them my way.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 583 };
	}
}
