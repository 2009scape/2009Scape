package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.link.quest.Quest;

/**
 * Represents the dialogue used to handle the interaction between veronica.
 * @author 'Vexia
 * @date 24/12/2013
 */
@InitializablePlugin
public class VeronicaDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code VeronicaDialogue} {@code Object}.
	 */
	public VeronicaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code VeronicaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public VeronicaDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 285 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Ernest the Chicken");
		switch (quest.getStage(player)) {
		case 0:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "Aha, sounds like a quest. I'll help.", "No, I'm looking for something to kill.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "Aha, sounds like a quest. I'll help.");
					stage = 4;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "No, I'm looking for something to kill.");
					stage = 2;
					break;
				}
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "Oooh, you violent person you.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			case 4:
				interpreter.sendDialogues(npc, null, "Yes yes, I suppose it is a quest. My fiance Ernest and", "I came upon this house.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(npc, null, "Seeing as we were a little lost Ernest decided to go in", "and ask for direction.");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, null, "That was an hour ago. That house looks spooky, can", "you go and see if you can find him for me?");
				stage = 7;
				break;
			case 7:
				interpreter.sendDialogues(player, null, "Ok, I'll see what I can do.");
				stage = 8;
				break;
			case 8:
				quest.start(player);
				player.getQuestRepository().syncronizeTab(player);
				interpreter.sendDialogues(npc, null, "Thank you, thank you. I'm very grateful.");
				stage = 9;
				break;
			case 9:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 9:
				end();
				break;
			case 0:
				interpreter.sendDialogues(player, null, "No, not yet.");
				stage = 1;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, null, "Yes, he's a chicken.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "I know he's not exactly brave but I think you're being", "a bit harsh.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "No no, he's been turned into an actual chicken by a", "mad scientist.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Eeeeeek!");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, null, "My poor darling, why must these things happen to us.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(player, null, "Well I'm doing my best to turn him back.");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, null, "Well be quick, I'm sure being a chicken can't be good", "for him.");
				stage = 7;
				break;
			case 7:
				end();
				break;
			}
			break;
		case 100:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(player, null, "Where is he now?");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "Oh he went off to talk to some green warty guy. I'm", "sure he'll be back soon.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new VeronicaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		final Quest quest = player.getQuestRepository().getQuest("Ernest the Chicken");
		switch (quest.getStage(player)) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Can you please help me? I'm in a terrible spot of", "trouble.");
			stage = 0;
			break;
		case 10:
			interpreter.sendDialogues(npc, null, "Have you foudn my sweetheart yet?");
			stage = 0;
			break;
		case 20:
			interpreter.sendDialogues(npc, null, "Have you foudn my sweetheart yet?");
			stage = 0;
			break;
		case 100:
			interpreter.sendDialogues(npc, null, "Thank you for resucing Ernest.");
			stage = 0;
			break;
		}
		return true;
	}
}
