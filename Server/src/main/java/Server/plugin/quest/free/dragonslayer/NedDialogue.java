package plugin.quest.free.dragonslayer;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.world.GameWorld;

/**
 * Represents the dialogue to handle the transcript of hed.
 * @author 'Vexia
 * @date 31/12/2013
 */
public final class NedDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item to remove.
	 */
	private static final Item COINS = new Item(995, 15);

	/**
	 * Represents the rope item to add.
	 */
	private static final Item ROPE = new Item(954);

	/**
	 * Represents the ball of wool item.
	 */
	private static final Item WOOL = new Item(1759, 4);

	/**
	 * Represents the wool item needed to make a wig.
	 */
	private static final Item WIG_WOOL = new Item(1759, 3);

	/**
	 * Represents the undyed wig item.
	 */
	private static final Item WIG = new Item(2421);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * What quest option to use.
	 */
	private String q;
	
	/**
	 * The achievement diary.
	 */
	private AchievementDiary diary;

	/**
	 * Constructs a new {@code NedDialogue} {@code Object}.
	 */
	public NedDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code NedDialogue} {@code Object}.
	 * @param player the player.
	 */
	public NedDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new NedDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		if (player.getQuestRepository().getQuest("Dragon Slayer").isStarted(player) && player.getQuestRepository().getQuest("Prince Ali Rescue").isStarted(player)) {
			options("Dragon Slayer", "Prince Ali Rescue");
			stage = -400;
			return true;
		}
		if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 20) {
			if (player.getSavedData().getQuestData().getDragonSlayerAttribute("ned")) {
				player("Will you take me to Crandor now, then?");
				stage = 520;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, hello there, lad. Me friends call me Ned. I was a", "man of the sea, but it's past me now. Could I be", "making or selling you some rope?");
			stage = 499;
			return true;
		}
		if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 30) {
			player("Will you take me to Crandor now, then?");
			stage = 520;
			return true;
		}
		switch (quest.getStage(player)) {
		case 20:// wig
		case 30:
		case 40:
		case 50:
			interpreter.sendOptions("Select an Option", "Ned, could you make other things from wool?", "Yes, I would like some rope.", "No thanks Ned, I don't need any.");
			stage = 40;
			break;
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, hello there, lad. Me friends call me Ned. I was a", "man of the sea, but it's past me now. Could I be", "making or selling you some rope?");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case -400:
			switch (buttonId) {
			case 1:
				if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 20) {
					if (player.getSavedData().getQuestData().getDragonSlayerAttribute("ned")) {
						player("Will you take me to Crandor now, then?");
						stage = 520;
						return true;
					}
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, hello there, lad. Me friends call me Ned. I was a", "man of the sea, but it's past me now. Could I be", "making or selling you some rope?");
					stage = 499;
					return true;
				}
				if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 30) {
					player("Will you take me to Crandor now, then?");
					stage = 520;
					return true;
				}
				q = "dSlayer";
				return true;
			case 2:
				switch (quest.getStage(player)) {
				case 40:
				case 50:
				case 20:// wig
				case 30:
					interpreter.sendOptions("Select an Option", "Ned, could you make other things from wool?", "Yes, I would like some rope.", "No thanks Ned, I don't need any.");
					stage = 40;
					break;
				default:
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, hello there, lad. Me friends call me Ned. I was a", "man of the sea, but it's past me now. Could I be", "making or selling you some rope?");
					stage = 0;
					break;
				}
				q = "ali";
				return true;
			}
		}
		if ((q == null || q.equals("dSlayer")) && (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 20 || (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 30) && player.getSavedData().getQuestData().getDragonSlayerAttribute("repaired"))) {
			switch (stage) {
			case 499:
				player("You're a sailor? Could you take me to Crandor?");
				stage = 500;
				break;
			case 500:
				npc("Well, I was a sailor. I've not been able to get work at", "sea these days, though. They say I'm too old.");
				stage = 501;
				break;
			case 501:
				npc("Sorry, where was it you said you wanted to go?");
				stage = 502;
				break;
			case 502:
				player("To the island of Crandor.");
				stage = 503;
				break;
			case 503:
				npc("Crandor? You've got to be out of your mind!");
				stage = 504;
				break;
			case 504:
				npc("But... It would be a chance to sail a ship once more.", "I'd sail anywhere if it was a chance to sail again.");
				stage = 505;
				break;
			case 505:
				npc("Then again, no captain in his right mind would sail to", "that island...");
				stage = 506;
				break;
			case 506:
				npc("Ah, you only live once! I'll do it!");
				player.getSavedData().getQuestData().setDragonSlayerAttribute("ned", true);
				stage = 507;
				break;
			case 507:
				npc("So, where's your ship?");
				stage = 508;
				break;
			case 508:
				if (player.getSavedData().getQuestData().getDragonSlayerAttribute("ship")) {
					player("It's the Lady Lumbridge, in Port Sarim.");
					stage = 509;
				} else {
					player("I'm still looking...");
					stage = 511;
				}
				break;
			case 509:
				npc("That old pile of junk? Last I heard, she wasn't", "seaworthy.");
				stage = 510;
				break;
			case 510:
				if (player.getSavedData().getQuestData().getDragonSlayerAttribute("repaired")) {
					player("I fixed her up!");
					stage = 512;
				} else {
					player("Oh, I better go inspect her.");
					stage = 511;
				}
				break;
			case 511:
				end();
				break;
			case 512:
				npc("You did? Excellent!");
				stage = 513;
				break;
			case 513:
				npc("Just show me the map and we can get ready to go!");
				stage = 514;
				break;
			case 514:
				if (player.getInventory().containsItem(DragonSlayer.CRANDOR_MAP)) {
					player("Here you go.");
					stage = 515;
				} else {
					player.getPacketDispatch().sendMessage("You don't have the map to Crandor.");
					end();
				}
				break;
			case 515:
				if (player.getInventory().remove(DragonSlayer.CRANDOR_MAP)) {
					interpreter.sendItemMessage(DragonSlayer.CRANDOR_MAP.getId(), "You hand the map to Ned.");
					player.getQuestRepository().getQuest("Dragon Slayer").setStage(player, 30);
					stage = 516;
				}
				break;
			case 516:
				npc("Excellent! I'll meet you at the ship, then.");
				stage = 517;
				break;
			case 517:
				end();
				break;
			case 520:
				if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) == 30) {
					npc("I Said I would and old Ned is a man of his word! I'll", "meet you on board the Lady Lumbridge in Port Sarim.");
					stage = 517;
					return true;
				}
				npc("I Said I would and old Ned is a man of his word!");
				stage = 521;
				break;
			case 521:
				npc("So, where's your ship?");
				stage = 508;
				break;
			}
			return true;
		}
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, I would like some rope.", "No thanks Ned, I don't need any.", "Ask about Achievement Diaries");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, I would like some rope.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks Ned, I don't need any.");
				stage = 20;
				break;
			case 3:
				player("I'd like to talk about Achievement Diaries.");
				stage = 900;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, I can sell you some rope for 15 coins. Or I can", "be making you some if you gets me 4 balls of wool. I", "strands them together I does, makes em strong.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You make rope from wool?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of course you can!");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I thought you needed hemp or jute.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you want some rope or not?");
			stage = 15;
			break;
		case 15:
			if (!player.getInventory().containsItem(WOOL)) {
				interpreter.sendOptions("Select an Option", "Okay, please sell me some rope.", "That's a little more than I want to pay.", "I will go and get some wool.");
				stage = 16;
			} else {
				interpreter.sendOptions("Select an Option", "Okay, please sell me some rope.", "I have some balls of wool. Could you make me some rope?", "That's a little more than I want to pay.");
				stage = 17;
			}
			break;
		case 17:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, please sell me some rope.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I have some balls of wool.", "Could you make me some rope?");
				stage = 120;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's a little more than I want to pay.");
				stage = 200;
				break;
			}
			break;
		case 16:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, please sell me some rope.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That's a little more than I want to pay.");
				stage = 200;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I will go and get some wool.");
				stage = 300;
				break;
			}
			break;
		case 40:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ned could you make other things from wool?");
				stage = 41;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, I would like some rope.");
				stage = 10;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks Ned, I don't need any.");
				stage = 20;
				break;
			}
			break;
		case 41:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am sure I can. What are you thinking of?");
			stage = 42;
			break;
		case 42:
			interpreter.sendOptions("Select an Option", "Could you knit me a sweater?", "How about some sort of wig?", "Could you repair the arrow holes in the back of my shirt?");
			stage = 43;
			break;
		case 43:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Could you knit me a sweater?");
				stage = 44;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How about some sort of wig?");
				stage = 50;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Could you repair the arrow holes in the", "back of my shirt?");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah yes, it's a tough world these days. There's a few", "brave enough to attack from 10 metres away.");
				stage = 47;
				break;
			}
			break;
		case 44:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do I look like a member of a sewing circle?", "Be off wi' you. I have fought monsters.", "that would turn your hair blue.");
			stage = 45;
			break;
		case 45:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I dont't need to be laughted at just 'cos I am getting", "a bit old.");
			stage = 46;
			break;
		case 46:
			end();
			break;
		case 47:
			interpreter.sendDialogue("Ned pulls out a nettle and attacks your shirt.");
			stage = 48;
			break;
		case 48:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There you go, good as new.");
			stage = 46;
			break;
		case 50:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well... That's an interesting thought. Yes, I think I", "could do something. Give me 3 balls of wool and I", "might be able to do it.");
			stage = 51;
			break;
		case 51:
			interpreter.sendOptions("Select an Option", "I have that now. Please, make me a wig.", "I will come back when I need you to make me one.");
			stage = 52;
			break;
		case 52:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "I have that now. Please, make me a wig.");
				stage = 53;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "I will come back when I need you to make me one.");
				stage = 46;
				break;
			}
			break;
		case 53:
			if (!player.getInventory().containsItem(WIG_WOOL)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh, I seem to have forgotten my wool.");
				stage = 46;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Okay, I will have a go.");
			stage = 54;
			break;
		case 54:
			interpreter.sendDialogue("You hand Ned 3 balls of wool. Ned works with the wool", "His hands move with a speed you couldn't imagine.");
			stage = 55;
			break;
		case 55:
			if (player.getInventory().remove(WIG_WOOL)) {
				if (!player.getInventory().add(WIG)) {
					GroundItemManager.create(WIG, player);
				}
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Here you go, how's that for a quick effort?", "Not bad I think!");
			stage = 56;
			break;
		case 56:
			interpreter.sendDialogue("Ned gives you a pretty good wig.");
			stage = 57;
			break;
		case 57:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Thanks Ned, there's more to you than meets the eye.");
			stage = 46;
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There you go, finest rope in " + GameWorld.getName() + ".");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogue("You hand Ned 15 coins. Ned gives you a coil of rope.");
			stage = 102;
			break;
		case 102:
			if (player.getInventory().remove(COINS)) {
				if (!player.getInventory().add(ROPE)) {
					GroundItemManager.create(ROPE, player);
				}
			} else {
				player.getPacketDispatch().sendMessage("You don't have enough coins to pay for rope.");
			}
			end();
			break;
		case 120:
			interpreter.sendDialogues(npc, null, "Sure I can.");
			stage = 121;
			break;
		case 121:
			interpreter.sendDialogue("You hand over 4 balls of wool. Ned gives you a coil of rope.");
			stage = 122;
			break;
		case 122:
			if (player.getInventory().remove(WOOL)) {
				player.getInventory().add(ROPE);
			}
			end();
			break;
		case 200:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, if you ever need rope that's the price. Sorry.", "An old sailor needs money for a little drop o' rum.");
			stage = 201;
			break;
		case 201:
			end();
			break;
		case 300:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Aye, you do that. Remember, it takes 4 balls of wool to", "make strong rope.");
			stage = 301;
			break;
		case 301:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, old Neddy is always here if you do. Tell your", "friends. I can always be using the business.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 900:
			if (diary == null) {
				diary = player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE);
			}
			if (diary.isComplete(2) && !diary.hasReward(2)) {
				player("I've done all the hard tasks in my Lumbridge", "Achievement Diary.");
				stage = 950;
				break;
			}
			if (diary.hasReward(2) && diary.isComplete(2) && !player.hasItem(diary.getType().getRewards(2)[0])) {
				player("I've seemed to have lost my explorer's ring...");
				stage = 960;
				break;
			}
			options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
			stage = 901;
			break;
		case 901:
			switch (buttonId) {
			case 1:
				player("What is the Achievement Diary?");
				stage = 910;
				break;
			case 2:
				player("What are the rewards?");
				stage = 920;
				break;
			case 3:
				player("How do I claim the rewards?");
				stage = 930;
				break;
			case 4:
				player("See you later!");
				stage = 940;
				break;
			}
			break;
		case 910:
			npc("Ah, well it's a diary that helps you keep track of", "particular achievements you've made in the world of", "Keldagrim. In Lumbridge and Draynor i can help you", "discover some very useful things indeed.");
			stage++;
			break;
		case 911:
			npc("Eventually with enough exploration you will be", "rewarded for your explorative efforts.");
			stage++;
			break;
		case 912:
			npc("You can access your Achievement Diary by going to", "the Quest Journal. When you've opened the Quest", "Journal click on the green star icon on the top right", "hand corner. This will open the diary.");
			stage = 900;
			break;
		case 920:
			npc("Ah, well there are different rewards for each", "Achievement Diary. For completing the Lumbridge and", "Draynor diary you are presented with an explorer's", "ring.");
			stage++;
			break;
		case 921:
			npc("This ring will become increasingly useful with each", "section of the diary that you complete.");
			stage = 900;
			break;
		case 930:
			npc("You need to complete the taks so that they're all ticked", "off then you can claim your reward. Most of them are", "straightforward although you might find some required", "quests to be started, if not finished.");
			stage++;
			break;
		case 931:
			npc("To claim the explorer's ring speak to Bob in Bob's", "Axes in Lumbridge, Ned in Draynor Village or myself.");
			stage = 900;
			break;
		case 940:
			end();
			break;
		case 950:
			npc("Yes I see that, you'll be wanting your", "reward then I assume?");
			stage++;
			break;
		case 951:
			player("Yes please.");
			stage++;
			break;
		case 952:
			diary.setRewarded(2);
			for (Item i : diary.getType().getRewards(2)) {
				player.getInventory().add(i, player);
			}
			npc("This ring is a representation of the adventures you", "went on to complete your tasks.");
			stage ++;
			break;
		case 953:
			player("Wow, thanks!");
			stage = 900;
			break;
		case 960:
			player.getInventory().add(diary.getType().getRewards(0)[0], player);
			npc("You better be more careful this time.");
			stage = 900;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 743 };
	}
}
