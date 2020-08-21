package plugin.quest.members.rovingelves;

import plugin.dialogue.DialogueInterpreter;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;

/**
 * Handles Islwyn's dialogue for Roving Elves.
 * @author Splinter
 */
public class IslwynDialogue extends DialoguePlugin {

	public IslwynDialogue() {

	}

	public IslwynDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("islwyn_dialogue"), 1680 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Roving Elves");
		switch (stage) {
		case 500:
			end();
			break;
		case 1000:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Hello there, it's a lovely day for a walk in the woods.", "So what can I help you with?");
			stage = 1001;
			break;
		case 1001:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm just looking around.");
			stage = 500;
			break;
		case 2000:
			if (quest.getStage(player) == 15) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes I have! She explained that I have to visit", "Glarial's old tomb and obtain a consecration seed", "from the temple guardian in there.");
				stage = 2001;
			}
			if (quest.getStage(player) != 15) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Not yet, I'll be back when I have.");
				stage = 500;
			}
			break;
		case 2001:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Good luck against the guardian, adventurer.", "Do it in the name of my grandmother Glarial.");
			stage = 500;
			break;
		/* main dialogue sequence */
		case 0:
			if (quest.getStage(player) == 10 || quest.getStage(player) == 15) {
				interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Have you spoken to Eluned yet?");
				stage = 2000;
			}
			if (quest.getStage(player) == 20) {
				interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "You have returned! Thank you for all you have done.", "Now both me and my grandmother can rest in peace.");
				stage = 19;
			} else if (quest.getStage(player) != 10 && quest.getStage(player) != 15) {
				interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Leave me be, I have no time for easterners. Between", "your lot and them gnomes, all you do is take and", "destroy. No thought for others.");
				stage = 1;
			}
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...but...");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Save your excuses young one! It was one of your", "species that disturbed my grandmother's remains. Will", "she never get the peace she deserves?");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Grandmother?");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Yes! Someone took her ashes from her tomb. If it", "wasn't for them gnomes she'd have been left in peace.", "But now I can sense her restlessness.");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Gnomes?");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Yes gnomes! One of those little pests took the key to", "my grandmother's tomb. He must've given it to the", "human that desecrated the tomb.");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Was your grandmother's name Glarial?");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Yes... How did you know that?");
			stage = 9;
			break;
		case 9:
			interpreter.sendOptions("Do you want to;", "Tell the truth?", "Lie?", "Leave the old elf be?");
			stage = 10;
			break;
		case 10:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "It's a bit of a long tale, but to cut the story short, her", "remains reside in Baxtorian's home. I thought it's where", "she'd want to be. It was I that removed your", "grandmother's ashes.");
				stage = 11;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I just guessed.", "Well, now that that's over, I really need to be", "going.");
				stage = 500;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "On second thought, I really should be going.");
				stage = 500;
				break;
			}
			break;
		case 11:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "You've been in grandfather's home? That's where we", "originally wanted to leave Glarial's ashes to rest, but we", "could not understand how to enter.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "This is gravely concerning... Her resting place must be", "consecrated.");
			stage = 13;
			break;
		case 13:
			interpreter.sendOptions("Select an Option", "Maybe I could help.", "Sounds like you've got a lot to do.");
			stage = 14;
			break;
		case 14:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Maybe I could help. What needs doing to consecrate", "her new tomb?");
				stage = 15;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sounds like you've got a lot to do.");
				stage = 500;
				break;
			}
			break;
		case 15:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Are you offering to help?!? Maybe not all humans are", "as bad as I thought.");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "I don't know the consecration process. You should speak", "with Eluned... she is wise in the ways of the ritual.");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll see what I can do.");
			stage = 18;
			break;
		case 18:
			end();
			quest.start(player);
			break;
		case 19:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How did you know that I have consecrated the tomb?");
			stage = 20;
			break;
		case 20:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Her restlessness has finally left me. Here - I should", "give you something for your effort.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDoubleItemMessage(RovingElves.CRYSTAL_BOW_FULL, RovingElves.CRYSTAL_SHIELD_FULL, "Islwyn shows you a crystal bow and a crystal shield.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Crystal equipment is at its best when new and", "previously unused. The bow does not require", "ammunition and reduces in strength the more it's fired.", "The shield decreases in defensive capabilities the more");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "it's hit. Both the shield and the bow I am carrying only", "have 500 uses before they revert to seed.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Revert to seed? What do you mean?");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Ahhh, young one. It was thousands of years before we", "fully understood that ourselves. All will be explained if", "we feel you are ready. Now which one of these crystal", "creations would you like?");
			stage = 26;
			break;
		case 26:
			interpreter.sendOptions("Select an Option", "Shields are for wimps! Give me the bow!", "I don't like running and hiding behind mushrooms. Shield please!");
			stage = 27;
			break;
		case 27:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Shields are for wimps! Give me the bow!");
				stage = 30;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't like running and hiding behind mushrooms.", "Shield please!");
				stage = 301;
				break;
			}
			break;
		case 30:
			if (!quest.isCompleted(player)) {
				if (!player.getInventory().add(new Item(4222, 1))) {
					GroundItemManager.create(new Item(4222, 1), player);
				}
				end();
				quest.setStage(player, 100);
				quest.finish(player);
			}
			end();
			break;
		case 301:
			if (!quest.isCompleted(player)) {
				if (!player.getInventory().add(new Item(4233, 1))) {
					GroundItemManager.create(new Item(4233, 1), player);
				}
				end();
				quest.setStage(player, 100);
				quest.finish(player);
			}
			end();
			break;
		case 31:
			interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Welcome back to the land of the elves, friend!", "Do you need your seeds charged into equipment?");
			stage = 32;
			break;
		case 32:
			interpreter.sendOptions("Select an Option", "I need to buy a new piece of equipment.", "I need to recharge my seeds into equipment.");
			stage = 33;
			break;
		case 33:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(1680, FacialExpression.HALF_GUILTY, "Ah, very well.", "I will sell you a new bow or shield for 750,000 coins.");
				stage = 37;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I need to recharge my current seeds into equipment.");
				stage = 34;
				break;
			}
			break;
		case 34:
			interpreter.sendOptions("Select an Option", "Recharge seed into bow", "Recharge seed into shield");
			stage = 35;
			break;
		case 35:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Recharge my seed into a bow, please.");
				stage = 36;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Recharge my seed into a shield, please.");
				stage = 3601;
				break;
			}
			break;
		case 36:
			if (!player.getInventory().contains(RovingElves.CRYSTAL_SEED.getId(), 1)) {
				interpreter.sendDialogue("You don't have any seeds to recharge.");
				stage = 500;
			}
			if (!player.getInventory().contains(995, 500000)) {
				interpreter.sendDialogue("You don't have enough coins.");
				stage = 500;
			}
			if (player.getInventory().contains(995, 500000) && player.getInventory().contains(RovingElves.CRYSTAL_SEED.getId(), 1)) {
				if (player.getInventory().remove(RovingElves.CRYSTAL_SEED) && player.getInventory().remove(new Item(995, 500000))) {
					player.getInventory().add(new Item(4214, 1));
					end();
				}
			}
			break;
		case 3601:
			if (!player.getInventory().contains(RovingElves.CRYSTAL_SEED.getId(), 1)) {
				interpreter.sendDialogue("You don't have any seeds to recharge.");
				stage = 500;
			}
			if (!player.getInventory().contains(995, 500000)) {
				interpreter.sendDialogue("You don't have enough coins.");
				stage = 500;
			}
			if (player.getInventory().contains(995, 500000) && player.getInventory().contains(RovingElves.CRYSTAL_SEED.getId(), 1)) {
				if (player.getInventory().remove(RovingElves.CRYSTAL_SEED) && player.getInventory().remove(new Item(995, 500000))) {
					player.getInventory().add(new Item(4225, 1));
					end();
				}
			}
			break;
		case 37:
			interpreter.sendOptions("Select an Option", "Purchase bow", "Purchase shield");
			stage = 38;
			break;
		case 38:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like to buy a new bow.");
				stage = 39;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like to buy a new shield.");
				stage = 40;
				break;
			}
			break;
		case 39:
			if (!player.getInventory().contains(995, 750000)) {
				interpreter.sendDialogue("You don't have enough coins.");
				stage = 500;
			}
			if (player.getInventory().contains(995, 750000)) {
				if (player.getInventory().remove(new Item(995, 750000))) {
					if (!player.getInventory().add(new Item(4214, 1))) {
						GroundItemManager.create(new Item(4214, 1), player);
					}
					end();
				}
			}
			break;
		case 40:
			if (!player.getInventory().contains(995, 750000)) {
				interpreter.sendDialogue("You don't have enough coins.");
				stage = 500;
			}
			if (player.getInventory().contains(995, 750000)) {
				if (player.getInventory().remove(new Item(995, 750000))) {
					if (!player.getInventory().add(new Item(4225, 1))) {
						GroundItemManager.create(new Item(4225, 1), player);
					}
					end();
				}
			}
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new IslwynDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		final Quest quest = player.getQuestRepository().getQuest("Roving Elves");
		final Quest waterfall = player.getQuestRepository().getQuest("Waterfall");
		if (quest.getStage(player) == 0 && waterfall.isCompleted(player)) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there.");
			stage = 0;
		}
		if (!waterfall.isCompleted(player)) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello there.");
			stage = 1000;
		}
		if (quest.isStarted(player) && quest.getStage(player) >= 10) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello Islwyn.");
			stage = 0;
		}
		if (quest.isCompleted(player) || quest.getStage(player) >= 100) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello Islwyn, I'm back.");
			stage = 31;
		}
		return true;
	}
}