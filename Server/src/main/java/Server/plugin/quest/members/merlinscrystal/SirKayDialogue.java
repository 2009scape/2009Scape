package plugin.quest.members.merlinscrystal;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for king arthur.
 * @author Vexia
 * @author Splinter
 * @version 2.0
 */
public final class SirKayDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 */
	public SirKayDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 * @param player the player.
	 */
	public SirKayDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SirKayDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		boolean male = true;
		String gend = male ? "sir" : "madam";
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Good day " + gend + "!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getQuestRepository().getQuest("Merlin's Crystal").getStage(player) == 10) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you know how Merlin got trapped?");
				stage = 1;
			} else if (player.getQuestRepository().getQuest("Merlin's Crystal").getStage(player) == 20) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Any idea how to get into Morgan Le Faye's stronghold?");
				stage = 5;
			}
			if (player.getQuestRepository().getQuest("Merlin's Crystal").getStage(player) != 10 && player.getQuestRepository().getQuest("Merlin's Crystal").getStage(player) != 20) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am afraid I have no time to chat.");
				stage = 15;
			}
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I would guess this is the work of the evil Morgan Le", "Faye!");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "And where could I find her?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "She lives in her stronghold to the south of here,", "guarded by some renegade knights led by Sir Mordred.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Any idea how to get into Morgan Le Faye's stronghold?");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, you've got me stumped there...");
			player.getQuestRepository().getQuest("Merlin's Crystal").setStage(player, 20);
			player.getQuestRepository().syncronizeTab(player);
			stage = 15;
			break;
		case 15:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 240, 241 };
	}
}