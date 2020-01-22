package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the plugin used for loui legs.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LouiLegsDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LouiLegsDialogue} {@code Object}.
	 */
	public LouiLegsDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LouiLegsDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LouiLegsDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LouiLegsDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hey, wanna buy some armour?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "What have you got?", "No, thank you.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What have you got?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, thank you.");
				stage = 20;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I provide items to help you keep your legs!");
			stage = 11;
			break;
		case 11:
			end();
			npc.openShop(player);
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 542 };
	}
}
