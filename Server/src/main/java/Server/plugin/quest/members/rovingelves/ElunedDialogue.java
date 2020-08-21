package plugin.quest.members.rovingelves;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * Handles Eluned's Dialogue for Roving Elves.
 * @author Splinter
 */
public class ElunedDialogue extends DialoguePlugin {

	public ElunedDialogue() {

	}

	public ElunedDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1679 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Roving Elves");
		switch (stage) {
		case 500:
			end();
			break;
		case 1000:
			if (quest.getStage(player) == 15 && !player.getInventory().contains(RovingElves.CONSECRATION_SEED_CHARGED.getId(), 1)) {
				interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Hello, any luck finding the consecration seed?");
				stage = 1002;
			}
			if (quest.getStage(player) == 15 && player.getInventory().contains(RovingElves.CONSECRATION_SEED_CHARGED.getId(), 1)) {
				interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "You still have the charged seed with you, I can feel it.", "Hurry adventurer, go plant the seed and free", "my grandmother's spirit.");
				stage = 500;
			}
			if (quest.getStage(player) == 15 && player.getInventory().contains(RovingElves.CONSECRATION_SEED.getId(), 1)) {
				interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Hello, any luck finding the consecration seed?");
				stage = 1002;
			}
			if (quest.getStage(player) == 20) {
				interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Hey... how's it going? Have you managed to", "reconsecrate Glarial's resting place?");
				stage = 12;
			} else if (quest.getStage(player) != 15) {
				interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Hello there, it's a lovely day for a walk in the woods.", "So what can I help you with?");
				stage = 1001;
			}
			break;
		case 1001:
			if (quest.getStage(player) >= 100) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I am looking to buy teleportation crystals.");
				stage = 1200;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm just looking around.");
				stage = 500;
			}
			break;
		case 1002:
			if (player.getInventory().contains(RovingElves.CONSECRATION_SEED.getId(), 1)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, I have it here.");
				stage = 6;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I forgot what you told me to do.");
				stage = 1003;
			}
			break;
		case 1003:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Re-enter Glarial's tomb and defeat the tomb's guardian.", "Take the consecration seed it is guarding and", "bring it back to me.");
			stage = 500;
			break;
		case 1200:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Very well. I'll sell you a brand new teleportation", "crystal for 750 gold. What do you say?");
			stage = 1202;
			break;
		case 1202:
			interpreter.sendOptions("Select an Option", "Buy a crystal", "Nevermind");
			stage = 1203;
			break;
		case 1203:
			switch (buttonId) {
			case 1:
				stage = 1204;
				if (!player.getInventory().contains(995, 750)) {
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Actually, I don't have enough coins.");
				} else {
					interpreter.sendDialogue("You purchase an eleven teleporation crystal for 750 gold.");
					if (player.getInventory().remove(new Item(995, 750))) {
						player.getInventory().add(new Item(6099, 1));
					}
				}
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Nevermind, I really must be going.");
				stage = 1204;
				break;
			}
			break;
		case 1204:
			end();
			break;

		/* Main dialogue */

		case 1:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "That I do... It is elvish tradition to plant a specially", "enchanted crystal seed at the graves of our ancestors.", "The seed will create guardians to protect the area.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Unfortunately the crystal seed must be tuned to the", "person it's protecting... a new seed won't do. But you", "should be able to recover the seed from her old tomb.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "The tomb's guardian will be protecting the seed, you'll", "need to defeat him to get it. Once you have it, return", "here and I will re-enchant it.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "OK... I'll be back as soon as I have it.");
			stage = 5;
			break;
		case 5:
			quest.setStage(player, 15);
			end();
			break;
		case 6:
			interpreter.sendItemMessage(RovingElves.CONSECRATION_SEED.getId(), "You hand the crystal seed to Eluned.");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How odd. I can see her lips moving... But there's no", "sound.");
			stage = 9;
			break;
		case 9:
			if (player.getInventory().remove(RovingElves.CONSECRATION_SEED)) {
				player.getInventory().add(RovingElves.CONSECRATION_SEED_CHARGED);
			}
			interpreter.sendItemMessage(RovingElves.CONSECRATION_SEED_CHARGED.getId(), "Eluned hands you an enchanted crystal seed.");
			stage = 10;
			break;
		case 10:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Plant this seed in Glarial's new tomb, close to her", "remains and she will rest in peace.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "OK.");
			stage = 500;
			break;
		case 12:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, it's done.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(1679, FacialExpression.HALF_GUILTY, "Well done... You should go see Islwyn, but I'd guess he", "already knows.");
			stage = 500;
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ElunedDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		final Quest quest = player.getQuestRepository().getQuest("Roving Elves");
		if (quest.getStage(player) == 10) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hey there... Islwyn said you may be able to help me.", "He told me you know how to consecrate ground for an", "elven burial. I need to reconsecrate Glarial's resting", "place.");
			stage = 1;
		} else {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Good day.");
			stage = 1000;
		}
		return true;
	}
}