package plugin.quest.members.waterfallquest;

import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Handles Hudon's Dialogue for the Waterfall Quest.
 * @author Splinter
 */
public class HudonDialogue extends DialoguePlugin {

	public HudonDialogue() {

	}

	public HudonDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("hudon_dialogue"), 305 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest(WaterFall.NAME);
		switch (stage) {

		case 100: // Generic end to the dlg
			end();
			break;
		case 101: // Generic end to the dlg
			player.getPacketDispatch().sendMessage("Hudon is refusing to leave the waterfall.");
			end();
			break;

		/* Main dialogue sequence */
		case 0:
			if (quest.getStage(player) == 10) {
				interpreter.sendDialogues(305, FacialExpression.CHILD_NORMAL, "It looks like you need the help.");
				stage = 1;
			}
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Your mum sent me to find you.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(305, FacialExpression.CHILD_NORMAL, "Don't play nice with me, I know you're looking for the", "treasure too.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where is this treasure you talk of?");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(305, FacialExpression.CHILD_SUSPICIOUS, "Just because I'm small doesn't mean I'm dumb! If I", "told you, you would take it all for yourself.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Maybe I could help.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(305, FacialExpression.CHILD_NORMAL, "I'm fine alone.");
			quest.setStage(player, 20);
			stage = 101;
			break;
		case 20:
			interpreter.sendDialogues(305, FacialExpression.CHILD_NORMAL, "I'll find that treasure soon, just you wait and see.");
			if (quest.getStage(player) == 100) {
				stage = 21;
			} else {
				stage = 100;
			}
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I hate to break it to you kid, but I found the treasure.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(305, FacialExpression.CHILD_NORMAL, "Wha- are you serious? Are you going to at least share?");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(305, FacialExpression.CHILD_NORMAL, "Aww, come on... I helped you find it.", "This isn't fair!");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Life ain't fair kid.");
			stage = 100;
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HudonDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		final Quest quest = player.getQuestRepository().getQuest(WaterFall.NAME);
		if (quest.getStage(player) >= 20) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So you're still here.");
			stage = 20;
		} else {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello son, are you okay? You need help?");
			stage = 0;
		}
		return true;
	}
}