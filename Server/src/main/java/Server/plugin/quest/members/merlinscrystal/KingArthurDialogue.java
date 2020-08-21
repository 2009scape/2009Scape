package plugin.quest.members.merlinscrystal;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the dialogue plugin used for king arthur.
 * @author 'Vexia
 * @author Splinter
 * @version 2.0
 */
public final class KingArthurDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 */
	public KingArthurDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KingArthurDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KingArthurDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		if (quest.getStage(player) == 99) {
			player("I have freed Merlin from his crystal!");
			stage = 900;
			return true;
		}
		if (quest.getStage(player) == 100) {
			npc("Thank you for all of your help!");
			stage = 9;
			return true;
		}
		if (quest.getStage(player) <= 0) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to my court. I am King Arthur.");
			stage = 0;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If you're having any troubles talk to the other", "knights around the room.");
			stage = 9;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		switch (stage) {
		case 900:
			end();
			quest.finish(player);
			break;
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I want to become a knight of the round table!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, in that case I think you need to go on a quest to", "prove yourself worthy.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "My knights all appreciate a good quest.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.DISGUSTED, "Unfortunately, our current quest is to rescue Merlin.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.THINKING, "Back in England, he got himself trapped in some sort of", "magical Crystal. We've moved him from the cave we", "found him in and now he's upstairs in his tower.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I will see what I can do then.");
			quest.start(player);
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Talk to my knights if you need any help.");
			stage = 7;
			break;
		case 7:
			end();
			break;
		case 8:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "test");
			break;
		case 9:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 251 };
	}
}