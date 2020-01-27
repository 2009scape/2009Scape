package plugin.dialogue;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.AchievementDiary;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for pirate jackie.
 * @author Vexia
 */
@InitializablePlugin
public final class PirateJackieDialogue extends DialoguePlugin {

	/**
	 * Represents the interface component.
	 */
	private static final Component COMPONENT = new Component(6);

	/**
	 * The achievement diary.
	 */
	private AchievementDiary diary;

	/**
	 * Constructs a new {@code PirateJackieDialogue} {@code Object}.
	 */
	public PirateJackieDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code PirateJackieDialogue} {@code Object}.
	 * @param player the player.
	 */
	public PirateJackieDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PirateJackieDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ahoy there!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahoy!");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "What is this place?", "What do you do?", "I'd like to trade in my tickets, please.", "I have a question about my Achievement Diary.", "See you later.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is this place?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you do?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like to trade in my tickets, please.");
				stage = 30;
				break;
			case 4:
				player("I have a question about my Achievement Diary.");
				stage = 41;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "See you later.");
				stage = 50;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to the Brimhaven Agility Arena!");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If ye want to know more talk to Cap'n Izzy, he found", "it!");
			stage = 12;
			break;
		case 12:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I be the Jack o' tickets. I exchange the tickets ye", "collect in the Agility Arena for more stuff. Ye can", "obtain more agility experience or some items ye won't", "find anywhere else!");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sounds good!");
			stage = 22;
			break;
		case 22:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Aye, ye be on the right track.");
			stage = 31;
			break;
		case 31:
			end();
			player.getInterfaceManager().open(COMPONENT);
			break;
		case 41:
			if (diary == null) {
				diary = player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA);
			}
			if (diary.isComplete(0) && !diary.hasReward(0)) {
				player("I've done all the easy tasks in my Karamja Achievement", "Diary.");
				stage = 440;
				break;
			}
			if (diary.hasReward(0) && diary.isComplete(0) && !player.hasItem(diary.getType().getRewards(0)[0])) {
				player("I've seemed to have lost my gloves..");
				stage = 450;
				break;
			}
			options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
			stage++;
			break;
		case 42:
			if (diary == null) {
				diary = player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA);
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
		case 440:
			npc("Arr, ye have that, I see yer list. I s'pose ye'll be wanting", "yer reward then!");
			stage++;
			break;
		case 441:
			player("Yes please.");
			stage++;
			break;
		case 442:
			diary.setRewarded(0);
			for (Item i : diary.getType().getRewards(0)) {
				player.getInventory().add(i, player);
			}
			npc("These 'ere Karamja gloves be a symbol of yer explorin'", "on the island. All the merchants will recognise 'em when", "yer wear 'em and mabe give ye a little discount. I'll", "ave a word with some of the seafarin' folk who sail to");
			stage++;
			break;
		case 443:
			npc("Port Sarim and Ardougne, so they'll take ye on board", "half price if year wearin' them. Arrr, take this lamp I", "found washed ashore too.");
			stage++;
			break;
		case 444:
			player("Wow, thanks!");
			stage = 41;
			break;
		case 450:
			player.getInventory().add(diary.getType().getRewards(0)[0], player);
			npc("Arr matey, have another pair. Ye better be more", "careful this time.");
			stage = 41;
			break;
		case 410:
			npc("It's a diary that helps you keep track of particular", "achievements. Here on Karamja it can help you", "discover some quite useful things. Eventually, with", "enough exploration, the people of Karamja will reward");
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
			npc("Well, there's three different pairs of Karamja gloves,", "which match up with the three levels of difficulty. Each", "has the same rewards as the previous level, and an", "additional one too... but I won't spoil your surprise.");
			stage++;
			break;
		case 421:
			npc("Rest assured, the people of Karamja are happy to see", "you visiting the island.");
			stage = 41;
			break;
		case 430:
			npc("Just complete the tasks so they're all ticked off, then", "you can claim yer reward. Most of them are", "straightforward; you might find some require quests to", "be started, if not finished.");
			stage++;
			break;
		case 431:
			npc("To claim the different Karamja gloves, speak to Kaleb", "Paramaya in Shilo Village, one of the jungle foresters", "near the Kharazi Jungle, or me.");
			stage = 41;
			break;
		case 50:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1055 };
	}
}
