package plugin.quest.members.waterfallquest;

import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Handles Almera's Dialogue for the Waterfall Quest.
 * @author Splinter
 */
public class AlmeraDialogue extends DialoguePlugin {

	public AlmeraDialogue() {

	}

	public AlmeraDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("almera_dialogue"), 304 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest(WaterFall.NAME);
		switch (stage) {
			/* Main dialogue sequence */
			case 0:
				if (quest.getStage(player) == 0) {
					interpreter.sendDialogues(304, FacialExpression.ASKING, "Ah, hello there. Nice to see an outsider for a change,", "are you busy? I have a problem.");
					stage = 1;
				} else if (quest.getStage(player) == 10) {
					interpreter.sendDialogues(304, FacialExpression.JOLLY, "Hello brave adventurer, have you seen my boy yet?");
					stage = 200;
				} else if (quest.getStage(player) >= 20) {
					interpreter.sendDialogues(304, FacialExpression.JOLLY, "Well hello, you're still around then.");
					stage = 202;
				} else {

				}
				break;
			case 1:
				interpreter.sendOptions("Select an Option", "I'm afraid I'm in a rush.", "How can I help?");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm afraid I am in a rush.");
					stage = 99;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How can I help?");
					stage = 3;
					break;
				}
				break;
			case 3:
				interpreter.sendDialogues(304, FacialExpression.DISGUSTED, "It's my son Hudon, he's always getting to trouble, the", "boy's convinced there's hidden treasure in the river and", "I'm a bit worried about his safety, the poor lad can't", "even swim.");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I could go and take a look for you if you like?");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(304, FacialExpression.DISGUSTED, "Would you? You are kind. You can use the small raft", "out back if you wish, do be careful, the current down", "stream is very strong.");
				quest.start(player);
				stage = 100;
				break;
			case 6:
				interpreter.sendDialogues(304, FacialExpression.HALF_GUILTY, "Perhaps you can speak to Hadley a further bit down", "south for more information on how to find the treasure.");
				stage = 100;
				break;
			case 7:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I did it, I found the treasure under the waterfall!");
				stage = 8;
				break;
			case 8:
				interpreter.sendDialogues(304, FacialExpression.HALF_GUILTY, "Ah, very well done, adventurer!", "My boy Hudon was searching for that treasure too.");
				stage = 9;
				break;
			case 9:
				interpreter.sendDialogues(304, FacialExpression.HALF_GUILTY, "Maybe you could share it with him, he's just a boy.");
				stage = 10;
				break;
			case 10:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "On second thought, I really have to go.");
				stage = 100;
				break;

			case 99:
				interpreter.sendDialogues(304, FacialExpression.HALF_GUILTY, "Oh okay, never mind.");
				stage = 100;
				break;
			case 100:
				end();
				break;
			case 200:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm afraid not, but I'm sure he hasn't gone far.");
				stage = 201;
				break;
			case 201:
				interpreter.sendDialogues(304, FacialExpression.HALF_GUILTY, "I do hope so, you can't be too careful these days.");
				stage = 100;
				break;
			case 202:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I saw Hudon by the river but he refused to come back", "with me.");
				stage = 203;
				break;
			case 203:
				interpreter.sendDialogues(304, FacialExpression.HALF_GUILTY, "Yes he told me, the foolish lad came in drenched to the", "bone, he had fallen into the waterfall, lucky he wasn't", "killed! Now he can spend the rest of the summer in his", "room.");
				stage = 204;
				break;
			case 204:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Any ideas on what I could do while I'm here?");
				stage = 205;
				break;
			case 205:
				interpreter.sendDialogues(304, FacialExpression.HALF_GUILTY, "Why don't you visit the tourist centre south of the", "waterfall?");
				stage = 100;
				break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AlmeraDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		final Quest quest = player.getQuestRepository().getQuest(WaterFall.NAME);
		if (quest.getStage(player) == 100) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello Almera.");
			stage = 7;
		} else {
			interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "Hello.");
			stage = 0;
		}
		return true;
	}
}