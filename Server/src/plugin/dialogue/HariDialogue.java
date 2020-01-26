package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the hari dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HariDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HariDialogue} {@code Object}.
	 */
	public HariDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HariDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HariDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HariDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Who are you?", "Can you teach me about canoeing?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "My name is Hari.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It's really quite simple to make. Just walk down to that", "tree on the bank and chop it down.");
				stage = 18;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "And what are you going here Hari?");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Like most people who come to Edgeville, I am here to seek", "adventure in the Wilderness.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I found a secret underground riber that will take me quite", "a long way north.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Underground river? Where does it come out?");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It comes out in a pond located deep in Wilderness.");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I had to find a very special type of canoe to get me up", "the river though, would you like to know more?");
			stage = 16;
			break;
		case 16:
			interpreter.sendOptions("Select an Option", "Yes", "No");
			stage = 17;
			break;
		case 17:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It's really quite simple to make. Just walk down to that", "tree on the bank and chop it down.");
				stage = 18;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 18:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "When you have done that you can shape the log further", "with your hatchet to make a canoe.");
			stage = 19;
			break;
		case 19:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3330 };
	}

}
