package plugin.quest.merlincrystal;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;

/**
 * Handles the SirPalomedes dialogue.
 * @author Vexia
 */
public class SirPalomedes extends DialoguePlugin {

	/**
	 * Constructs a new {@code SirPalomedes} {@code Object}
	 */
	public SirPalomedes() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SirPalomedes} {@code Object}
	 * @param player the player.
	 */
	public SirPalomedes(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello there adventurer, what do you want of me?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
			if (quest.getStage(player) == 100) {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Congratulations on freeing Merlin!");
				stage = 20;
			} else if (quest.getStage(player) >= 10 && quest.getStage(player) < 50) {
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I'd like some advice on breaking that Crystal Merlin's", "trapped in.");
				stage = 1;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I am afraid I have no time to chat.");
				stage = 20;
			}
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Sorry, I cannot help you with that.");
			stage = 20;
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SirPalomedes(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3787 };
	}
}