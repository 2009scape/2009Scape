package plugin.quest.members.merlinscrystal;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Handles the dialogue for Sir Lucan
 * @author Splinter
 * @author Vexia
 */
public class SirLucan extends DialoguePlugin {

	/**
	 * Constructs a new {@code SirLucan} {@code Object}
	 */
	public SirLucan() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SirLucan} {@code Object}
	 * @param player the player.
	 */
	public SirLucan(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there adventurer.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
			if (quest.getStage(player) == 100) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Congratulations on freeing Merlin!");
				stage = 20;
			} else if (quest.getStage(player) >= 10 && quest.getStage(player) < 50) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Any suggestions on freeing Merlin?");
				stage = 1;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am afraid I have no time to chat.");
				stage = 20;
			}
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I've tried all the weapons I can find, yet none are", "powerful enough to break his crystal prison...");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Perhaps the mighty Excalibur would be strong enough...");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Excalibur eh? Where would I find that?");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The Lady of the Lake is looking atfer it I believe... but", "I know not where she resides currently.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks. I'll try and find someone who does.");
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
		return new SirLucan(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 245 };
	}
}