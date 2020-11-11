package plugin.dialogue;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.emote.Emotes;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Handles the explorer jack dialogue.
 * @author Vexia
 *
 */
@InitializablePlugin
public class ExplorerJackDialogue extends DialoguePlugin {

	/**
	 * The achievement diary.
	 */
	private AchievementDiary diary;
	private final int level = 0;

	/**
	 * Constructs the new {@code ExplorerJackDialogue}
	 */
	public ExplorerJackDialogue() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs the new {@code ExplorerJackDialogue}
	 * @param player The player.
	 */
	public ExplorerJackDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ExplorerJackDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc("What ho! Where did you come from?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (diary == null) {
			diary = player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE);
		}
		switch (stage) {
		case -1:
			if (diary.isComplete(level, true) && !diary.isLevelRewarded(level)) {
				player("I've done all the beginner tasks in my Lumbridge", "Achievement Diary.");
				stage = 50;
				break;
			}
			if (diary.isLevelRewarded(level) && diary.isComplete(level, true) && !player.hasItem(diary.getType().getRewards(level)[0])) {
				player("I've seemed to have lost my explorer's ring...");
				stage = 60;
				break;
			}
			options("What is the Achievement Diary?", "What are the rewards?", "How do I claim the rewards?", "See you later.");
			stage = 0;
			break;
		case 0:
			switch (buttonId) {
			case 1:
				player("What is the Achievement Diary?");
				stage = 10;
				break;
			case 2:
				player("What are the rewards?");
				stage = 20;
				break;
			case 3:
				player("How do I claim the rewards?");
				stage = 30;
				break;
			case 4:
				player("See you later!");
				stage = 40;
				break;
			}
			break;
		case 1:
			player("Oh sorry. I was just looking around");
			stage++;
			break;
		case 2:
			npc("Oh thats perfectly alright. Mi Casa and all that what!");
			stage++;
			break;
		case 3:
			player("Uh...and all what?");
			stage++;
			break;
		case 4:
			npc("Splendid! I love a person with a sense of humour. I bet", "you're from Ardougne eh? Ha!");
			stage = -1;
			break;
		case 10:
			npc("Ah, well it's a diary that helps you keep track of", "particular achievements you've made in the world of", "Keldagrim. In Lumbridge and Draynor i can help you", "discover some very useful things indeed.");
			stage++;
			break;
		case 11:
			npc("Eventually with enough exploration you will be", "rewarded for your explorative efforts.");
			stage++;
			break;
		case 12:
			npc("You can access your Achievement Diary by going to", "the Quest Journal. When you've opened the Quest", "Journal click on the green star icon on the top right", "hand corner. This will open the diary.");
			stage = -1;
			break;
		case 20:
			npc("Ah, well there are different rewards for each", "Achievement Diary. For completing the Lumbridge and", "Draynor diary you are presented with an explorer's", "ring.");
			stage++;
			break;
		case 21:
			npc("This ring will become increasingly useful with each", "section of the diary that you complete.");
			stage = -1;
			break;
		case 30:
			npc("You need to complete the taks so that they're all ticked", "off then you can claim your reward. Most of them are", "straightforward although you might find some required", "quests to be started, if not finished.");
			stage++;
			break;
		case 31:
			npc("To claim the explorer's ring speak to Bob in Bob's", "Axes in Lumbridge, Ned in Draynor Village or myself.");
			stage = -1;
			break;
		case 40:
			end();
			break;
		case 50:
			npc("Yes I see that, you'll be wanting your", "reward then I assume?");
			stage++;
			break;
		case 51:
			player("Yes please.");
			stage++;
			break;
		case 52:
			diary.setLevelRewarded(level);
			for (Item i : diary.getType().getRewards(level)) {
				player.getInventory().add(i, player);
			}
			player.getEmoteManager().unlock(Emotes.EXPLORE);
			npc("This ring is a representation of the adventures you", "went on to complete your tasks.");
			stage ++;
			break;
		case 53:
			player("Wow, thanks!");
			stage = -1;
			break;
		case 60:
			player.getInventory().add(diary.getType().getRewards(level)[0], player);
			npc("You better be more careful this time.");
			stage = -1;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] {7969};
	}

}
