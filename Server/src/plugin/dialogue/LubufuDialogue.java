package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the lubufu dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LubufuDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LubufuDialogue} {@code Object}.
	 */
	public LubufuDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LubufuDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LubufuDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LubufuDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Watch where you're going, young whippersnapper!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "I wasn't going anywhere...", "What's a whippersnapper?", "Who are you?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I wasn't going anywhere...");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's a whippersnapper?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Who are you?");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Well then go away from here!");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It's a whip. Which snaps. Like me. Now leave!");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I am Lubufu - the only fisherman who knows the secret", "of the Karambwan!");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What's a Karambwan?");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "What a foolish question! Now leave!");
			stage = 33;
			break;
		case 33:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1171 };
	}
}
