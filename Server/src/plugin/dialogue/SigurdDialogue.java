package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Handles the Sigurd dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SigurdDialogue extends DialoguePlugin {

	public SigurdDialogue() {

	}

	public SigurdDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3329 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Who are you?", "Can you teach me about canoeing?");
			stage = 2;
			break;
		case 2:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Who are you?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It's really quite simple. Just walk down to that", "tree on the water bank and chop it down.");
				stage = 24;
				break;
			}

			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'm Sigurd the Great and Brainy.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Why do they call you the Great and Brainy?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Because I Iinvented the Log Canoe!");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Log Canoe?");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yeash! Me and my cousins were having a great", "party by the river when we decided to have a", " game of 'Smack The SeasonDefinitions'");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Smack the SeasonDefinitions?");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It's a game were you take it in turnsh shmacking", " a tree. First one to uproot the tree winsh!");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Anyway, I won the game with a flying tackle.", " The tree came loose and down the river bank I went", " still holding the tree.");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I woke up a few hours later and found myself", "several miles down river. and thatsh how I", "invented the log canoe!");
			stage = 19;
			break;
		case 19:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So you invented the 'Log Canoe' by falling into a river", "hugging a tree?");
			stage = 20;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well I refined the design from the original", "you know!");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I cut all the branches off to make it more", "comfortable. I could tell you how to if you like?");
			stage = 22;
			break;
		case 22:
			interpreter.sendOptions("Select an Option", "Yes", "No");
			stage = 23;
			break;
		case 23:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "It's really quite simple. Just walk down to that tree", "on the water bank and chop it down.");
				stage = 24;
				break;
			case 2:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Okay, if you change your mind you know where", "to find me.");
				stage = 25;
				break;
			}

			break;
		case 24:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Then take your hatchet to it and shape it how you", "like!");
			stage = 26;
			break;
		case 26:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You look like you know your way around a", "tree, you can you can make many canoes.");
			stage = 27;
			break;
		case 27:
			end();
			break;
		case 25:
			end();
			break;
		case 100:
			break;
		}

		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SigurdDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ha Ha! Hello!");
		stage = 0;
		return true;
	}
}
