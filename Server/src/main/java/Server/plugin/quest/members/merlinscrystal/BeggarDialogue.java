package plugin.quest.members.merlinscrystal;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Represents the dialogue plugin used for king arthur.
 * @author 'Vexia
 * @author Splinter
 * @version 2.0
 */
public final class BeggarDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 */
	public BeggarDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KingArthurDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BeggarDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BeggarDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.SCARED, "Please kind sir... my family and I are starving...");
		player.lock();
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		switch (stage) {
		case 1:
			if (quest.getStage(player) == 60 && player.getAttribute("beggar_npc") != null) {
				interpreter.sendDialogues(252, FacialExpression.HALF_GUILTY, "Could you find it in your heart to spare me a simple", "loaf of bread?");
				stage = 2;
			} else {
				interpreter.sendDialogue("Despite the fact that he is starving,", "he does not feel like speaking to you at the moment.");
				stage = 15;
			}
			break;
		case 2:
			player.getDialogueInterpreter().sendOptions("Select an Option", "Yes certainly.", "No, I don't have any bread with me.");
			stage = 3;
			break;
		case 3:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, certainly.");
				stage = 4;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I don't have any bread on me at the moment.");
				stage = 15;
				break;
			}
			break;
		case 4:
			if (player.getInventory().contains(2309, 1)) {
				interpreter.sendDialogue("You give the bread to the beggar.");
				player.getInventory().remove(new Item(2309, 1));
				stage = 5;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Actually I don't have any bread on me.");
				stage = 15;
			}
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Thank you very much!");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogue("The beggar has turned into the Lady of the Lake!");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(250, FacialExpression.HALF_GUILTY, "Well done. You have passed my test.");
			npc.transform(250);
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Here is Excalibur, guard it well.");
			stage = 16;
			break;
		case 15:
			player.unlock();
			end();
			break;
		case 16:
			player.unlock();
			player.getInventory().add(new Item(35, 1), player);
			quest.setStage(player, 70);
			end();
			npc.clear();
			break;
		}
		return true;
	}

	@Override
	public boolean close() {
		if (npc.getId() != npc.getOriginalId()) {
			npc.reTransform();
		}
		return super.close();
	}

	@Override
	public void end() {
		if (npc.getId() != npc.getOriginalId()) {
			npc.reTransform();
		}
		super.end();
	}

	@Override
	public int[] getIds() {
		return new int[] { 252 };
	}
}