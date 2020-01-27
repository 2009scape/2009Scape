package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the doomsayer.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DoomsayerDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DoomsayerDialogue} {@code Object}.
	 */
	public DoomsayerDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DoomsayerDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DoomsayerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DoomsayerDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Dooooom!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "All around us! I can feel it in the air, hear it on the", "wind smell it....also in the air!");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Is there anything we can do about this doom?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There is nothing you need to do my friend! I am the", "Doomsayer, although my real title could be something like", "the Danger Tutor.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Danger Tutor?");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes! I roam the world sensing danger.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If I find a dangerous area, then I put up warning signs", "that will tell you what is so dangerous about that area.");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If you see the signs often enough, then you can", "turn them off; by that time you likely know what the", "area has in store for you.");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "But what if I want to see the warning again?");
			stage = 9;
			break;
		case 9:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That's why I'm waiting here!");
			stage = 10;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If you want to see the warning messages again, I", "can turn them back on for you.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you need to turn on any warning right now?");
			stage = 12;
			break;
		case 12:
			interpreter.sendOptions("Select an Option", "Yes, I do.", "Not right now.");
			stage = 13;
			break;
		case 13:
			switch (buttonId) {
			case 1:
				end();
				player.getWarningMessages().open(player);
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ok, keep an eye out for the messages though!");
				stage = 30;
				break;
			}
			break;
		case 30:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, " I will.");
			stage = 31;
			break;
		case 31:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3777 };
	}
}
