package plugin.dialogue;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the dialoge used for kaleb paramaya.
 * @author Vexia
 */
@InitializablePlugin
public class KalebParamaya extends DialoguePlugin {

	/**
	 * The achievement diary.
	 */
	private AchievementDiary diary;

	/**
	 * Constructs a new {@code KalebParamaya} {@code Object}
	 */
	public KalebParamaya() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KalebParamaya} {@code Object}
	 * @param player the player.
	 */
	public KalebParamaya(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KalebParamaya(player);
	}

	@Override
	public boolean open(Object... args) {
		player("I have a question about my Achievement Diary.");
		stage = 41;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 41:
			if (diary == null) {
				diary = player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA);
			}
			if (diary.isComplete(1) && !diary.isLevelRewarded(1)) {
				player("I've done all the medium tasks in my Karamja", "Achievement Diary.");
				stage = 440;
				break;
			}
			if (diary.isLevelRewarded(1) && diary.isComplete(1) && !player.hasItem(diary.getType().getRewards(1)[0])) {
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
			npc("Yes I see that, you'll be wanting your", "reward then I assume?");
			stage++;
			break;
		case 441:
			player("Yes please.");
			stage++;
			break;
		case 442:
			diary.setLevelRewarded(1);
			for (Item i : diary.getType().getRewards(1)) {
				player.getInventory().add(i, player);
			}
			npc("These Karamja gloves are a symbol of your explorin'", "on the island. All the merchants will recognise them", "and mabe give you a discount. I'll", "have a word with some of the seafarin' folk who sail to");
			stage++;
			break;
		case 443:
			npc("Port Sarim and Ardougne, so they'll take you on board", "half price if you're wearing them. Ttake this lamp I", "found washed ashore too.");
			stage++;
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
			npc("To claim the different Karamja gloves, speak to Pirate", "Jackie the Fruit in Brim Haven, one of the jungle foresters", "near the Kharazi Jungle, or me.");
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
		return new int[] { 512 };
	}

}
