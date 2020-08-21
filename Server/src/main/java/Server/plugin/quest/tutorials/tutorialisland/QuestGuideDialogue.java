package plugin.quest.tutorials.tutorialisland;

import core.game.component.Component;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;

/**
 * Handles the quest guide.
 * @author Vexia
 */
@InitializablePlugin
public class QuestGuideDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code QuestGuide} {@code Object}.
	 */
	public QuestGuideDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code QuestGuideDialogue} {@code Object}
	 * @param player the player.
	 */
	public QuestGuideDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		switch (TutorialSession.getExtension(player).getStage()) {
		case 27:
			Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah. Welcome adventurer. I'm here to tell you all about", "quests. Lets start by opening the Quest list."));
			stage = 0;
			break;
		case 28:
			Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Now you have the journal open. I'll tell you a bit about", "it At the moment all the quests are shown in red. Which", "means you have not started them yet."));
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (TutorialSession.getExtension(player).getStage()) {
		case 27:
			switch (stage) {
			case 0:
				Component.setUnclosable(player, interpreter.sendPlaneMessageWithBlueTitle("Open the Quest Journal.", "Click on the flashing icon next to your inventory.", "", "", ""));
				player.getConfigManager().set(1021, 3);
				player.getInterfaceManager().openTab(new Component(274));
				break;
			}
			break;
		case 28:
			switch (stage) {
			case 0:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When you start a quest it will change colour to yellow,", "and to green when you've finished. This is so you can", "easily see what's complete, what's started and what's left", "to begin."));
				stage = 1;
				break;
			case 1:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The start of quests are easy to find. Look out for the", "star icons on the minimap, just like the one you should", "see marking my house."));
				stage = 2;
				break;
			case 2:
				Component.setUnclosable(player, interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There's not a lot more I can tell you about questing.", "You have to experience the thrill of it yourself to fully", "understand. You may find some adevnture in the caves", "under my house."));
				stage = 3;
				break;
			case 3:
				end();
				TutorialStage.load(player, 29, false);
				break;
			}
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new QuestGuideDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 949 };
	}
}
