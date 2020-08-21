package plugin.quest.free.shieldofarrav;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;

/**
 * Represents the dialogue to handle reldo.
 * @author 'Vexia
 * @dtae 27/12/2013
 */
public class ReldoDialogue extends DialoguePlugin {

	/**
	 * Represents the knight sword quest instance.
	 */
	private Quest knightSword;

	/**
	 * Represents the shield of arrav quest instance.
	 */
	private Quest shieldArrav;

	/**
	 * The achievement diary.
	 */
	private AchievementDiary diary;

	/**
	 * If w'ere chatting about our diary.
	 */
	private boolean isDiary;

	/**
	 * Constructs a new {@code ReldoDialogue} {@code Object}.
	 */
	public ReldoDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ReldoDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ReldoDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		knightSword = player.getQuestRepository().getQuest("The Knight's Sword");
		shieldArrav = player.getQuestRepository().getQuest("Shield of Arrav");
		if (args.length == 2 && ((String) args[1]).equals("book")) {
			player("Aha! 'The Shield of Arrav'! Exactly what I was looking", "for.");
			stage = 3;
			return true;
		}
		options("Hello stranger.", "I have a question about my Achievement Diary.");
		stage = -1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (isDiary) {
			switch (stage) {
			case 0:
				if (diary == null) {
					diary = player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK);
				}
				switch (buttonId) {
				case 1:
					player("What is the Achievement Diary?");
					stage = 410;
					break;
				case 2:
					player("What are the rewards?");
					stage = 420;
					break;
				case 3:
					player("How do I claim the rewards?");
					stage = 430;
					break;
				case 4:
					end();
					break;
				}
				break;
			case 41:
				end();
				break;
			case 440:
				npc("Yes I see that, you'll be wanting your", "reward then I assume?");
				stage++;
				break;
			case 441:
				player("Yes please.");
				stage++;
				break;
			case 442:
				diary.setRewarded(1);
				for (Item i : diary.getType().getRewards(1)) {
					player.getInventory().add(i, player);
				}
				npc("This armour is a representation of the adventures you", "went on to complete your tasks.");
				stage += 2;
				break;
			case 444:
				player("Wow, thanks!");
				stage = 41;
				break;
			case 450:
				player.getInventory().add(diary.getType().getRewards(1)[0], player);
				npc("You better be more careful this time.");
				stage = 41;
				break;
			case 410:
				npc("It's a diary that helps you keep track of particular", "achievements. Here in Varrock it can help you", "discover some quite useful things. Eventually, with", "enough exploration, the people of Varrock will reward");
				stage++;
				break;
			case 411:
				npc("you.");
				stage++;
				break;
			case 412:
				npc("You can see what tasks you have listed by clicking on", "the green button in the Quest List.");
				stage = 41;
				break;
			case 420:
				npc("Well, there's three different levels of Varrock Armour,", "which match up with the three levels of difficulty. Each", "has the same rewards as the previous level, and an", "additional one too... but I won't spoil your surprise.");
				stage++;
				break;
			case 421:
				npc("Rest assured, the people of Varrock are happy to see", "you visiting the land.");
				stage = 41;
				break;
			case 430:
				npc("Just complete the tasks so they're all ticked off, then", "you can claim your reward. Most of them are", "straightforward; you might find some require quests to", "be started, if not finished.");
				stage++;
				break;
			case 431:
				npc("To claim the different Varrock Armour, speak to Vannaka", "Rat Burgis, and myself.");
				stage = 41;
				break;
			case 50:
				end();
				break;
			}
			return true;
		}
		if (stage == -1) {
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello stranger.");
				stage = 0;
				break;
			case 2:
				sendDiaryDialogue();
				break;
			}
			return true;
		}
		if (knightSword.getStage(player) == 10) {
			switch (stage) {
			default:
				handleKnightSword(buttonId);
				handleQuest(buttonId);
				break;
			}
			return true;
		}
		switch (shieldArrav.getStage(player)) {
		case 20:
			switch (stage) {
			case 0:
				player("Ok. I've read the book. Do you know where I can find", "the Phoenix Gang?");
				stage = 1;
				break;
			case 1:
				npc("No, I don't. I think I know someone who might", "however.");
				stage = 2;
				break;
			case 2:
				npc("If I were you I would talk to Baraeck, the fur trader in", "the market place. I've heard he has connections with the", "Phoenix Gang.");
				stage = 3;
				break;
			case 3:
				player("Thanks, I'll try that!");
				stage = 4;
				break;
			case 4:
				shieldArrav.setStage(player, 30);
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				player("About that book... where is it again?");
				stage = 1;
				break;
			case 1:
				npc("I'm not sure where it is exactly... but I'm sure it's", "around here somewhere.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			case 14:
				end();
				break;
			case 3:
				interpreter.sendDialogue("You take the book from the bookcase.");
				stage = 4;
				break;
			case 4:
				if (!player.getInventory().add(ShieldofArrav.BOOK)) {
					GroundItemManager.create(ShieldofArrav.BOOK, player);
				}
				end();
				break;
			}
			break;
		case 0:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "I'm in search  of a quest.", "Do you have anything to trade?", "What do you do?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm in search of a quest.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have anything to trade?");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you do?");
					stage = 30;
					break;
				}
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am the palace librarian.");
				stage = 31;
				break;
			case 31:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah. That's why you're in the library then.");
				stage = 32;
				break;
			case 32:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes.");
				stage = 33;
				break;
			case 33:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Only knowledge.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How much do you want for that then?");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, sorry, that was just my little joke. I'm not the", "trading type.");
				stage = 23;
				break;
			case 23:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah well.");
				stage = 24;
				break;
			case 24:
				end();
				break;
			default:
				handleQuest(buttonId);
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "Do you have anything to trade?", "What do you do?");
				stage = 1;
				break;
			default:
				regular(buttonId);
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the quest.
	 */
	public final void handleQuest(int buttonId) {
		switch (stage) {
		case 10:
			npc("Hmmm. I don't... believe there are any here...");
			stage = 11;
			break;
		case 11:
			npc("Let me think actually...");
			stage = 12;
			break;
		case 12:
			npc("Ah yes. I know. If you look in a book called 'The Shield", "of Arrav', you'll find a quest in there.");
			stage = 13;
			break;
		case 13:
			shieldArrav.start(player);
			npc("I'm not not sure where the book is mind you... but I'm", "sure it's around here somewhere.");
			stage = 14;
			break;
		case 14:
			player("Thank you.");
			stage = 15;
			break;
		case 15:
			end();
			break;
		}
	}

	/**
	 * Method used to handle his regular chat.
	 * @param buttonId the buttonId.
	 */
	public void regular(int buttonId) {
		switch (stage) {
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have anything to trade?");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you do?");
				stage = 30;
				break;
			}
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am the palace librarian.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah. That's why you're in the library then.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes.");
			stage = 33;
			break;
		case 33:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Only knowledge.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How much do you want for that then?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, sorry, that was just my little joke. I'm not the", "trading type.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah well.");
			stage = 24;
			break;
		case 24:
			end();
			break;
		}
	}

	/**
	 * Handles the knight sword dial.
	 * @param buttonId the button Id.
	 */
	public void handleKnightSword(int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Do you have anything to trade?", "What do you do?", "What do you know about Imcando dwarves?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have anything to trade?");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you do?");
				stage = 30;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you know about Imcando dwarves?");
				stage = 40;
				break;
			}
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am the palace librarian.");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah. That's why you're in the library then.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes.");
			stage = 33;
			break;
		case 33:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Only knowledge.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How much do you want for that then?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, sorry, that was just my little joke. I'm not the", "trading type.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ah well.");
			stage = 24;
			break;
		case 24:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The Imcando dwarves, you say?");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah yes... for many hundreds of years they were the", "world's most skilled smiths. They used secret smithing", "knowledge passed down from generation to generation.");
			stage = 42;
			break;
		case 42:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Unfortunately, about a century ago, the once thriving", "race was wiped out during the barbarian invasions of", "that time.");
			stage = 43;
			break;
		case 43:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So are there any Imcando left at all?");
			stage = 44;
			break;
		case 44:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I believe a few of them survived, but with the bulk of", "their population destroyed their numbers have dwindled", "even further.");
			stage = 45;
			break;
		case 45:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I believe I remember a couple living in Asgarnia near", "the cliffs on the Asgarnian southern peninusla, but they", "DO tend to keep to themselves.");
			stage = 46;
			break;
		case 46:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "They tend not to tell people that they're the", "descendants of the Imcando, which is why people think", "that the tribe has died out totally, but you may well", "have more luck talking to them if you bring them some");
			stage = 47;
			break;
		case 47:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "redberry pie. They REALLY like redberry pie.");
			stage = 48;
			break;
		case 48:
			knightSword.setStage(player, 20);
			end();
			break;
		}
	}

	/**
	 * Sends the diary dialogue.
	 */
	private void sendDiaryDialogue() {
		isDiary = true;
		if (diary == null) {
			diary = player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK);
		}
		if (diary.isComplete(1) && !diary.hasReward(1)) {
			player("I've done all the medium tasks in my Varrock", "Achievement Diary.");
			stage = 440;
			return;
		}
		if (diary.hasReward(1) && diary.isComplete(1) && !player.hasItem(diary.getType().getRewards(1)[0])) {
			player("I've seemed to have lost my armour...");
			stage = 450;
			return;
		}
		options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
		stage++;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ReldoDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 2660, 2661 };
	}
}