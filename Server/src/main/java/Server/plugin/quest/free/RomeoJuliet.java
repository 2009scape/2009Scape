package plugin.quest.free;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the romeo and juliet quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class RomeoJuliet extends Quest {

	/**
	 * Constructs a new {@code RomeoJuliet} {@code Object}.
	 */
	public RomeoJuliet() {
		super(
			"Romeo & Juliet",
			26, 25,
			5,
			144, 0, 1, 100
		);
	}

	static String[][] JOURNAL_ENTRIES = new String[][]{
		new String[]{
			"I have agreed to find Juliet for Romeo and tell her how he",
			"feels. For some reason he can't just do this himself."
		},
		new String[]{
			"I found Juliet on the Western edge of Varrock, and told",
			"her about Romeo. She gave me a message to take back."
		},
		new String[]{
			"I delivered the message to Romeo, and he was sad to hear",
			"that Juliet's father opposed their marriage. However, he",
			"said that Father Lawrence might be able to overcome this."
		},
		new String[]{
			"I found Father Lawrence and he suggested the use of a",
			"potion to fool Juliet's father that she is dead so that",
			"Romeo and Juliet can be together in peace."
		},
		new String[]{
			"I went to the Apothecary regarding making this cadava",
			"potion, and he told me to bring him some cadava berries."
		}
	};

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (getStage(player)) {
		case 0:
			writeJournal(player,
				"I can start this quest by speaking to <red>Romeo <blue>in <red>Varrock",
				"central square by the <red>fountain.");
			break;
		case 10:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			writeJournal(player, line,
				"All I need to do now is find <red>Juliet.");
			break;
		case 20:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			writeJournal(player, line,
				"I should take the <red>message <blue>from <red>Juliet <blue>to <red>Romeo.");
			break;
		case 30:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			writeJournal(player, line,
				"I should find <red>Father Lawrence <blue>and see how we can help.");
			break;
		case 40:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			writeJournal(player, line,
				"I need to find the <red>Apothecary <blue>to make a <red>cadava potion."
			);
			break;
		case 50:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			if (!player.getInventory().contains(753, 1)) {
				writeJournal(player, line,
					"I will have to find some <red>Cadava berries <blue>somewhere!");
			} else {
				writeJournal(player, line,
					"I should take these <red>cadava berries <blue>to the <red>Apothecary.");
			}
			break;
		case 60:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			writeJournal(player, line,
				"I should take this <red>cadava potion <blue>to <red>Juliet."
			);
			break;
		case 70:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			line = writeJournal(player, line, true,
				"After the Apothecary made me the potion, I delivered it to",
				"Juliet. She asked me to tell Romeo the plan."
			);
			writeJournal(player, line,
				"I have to find <red>Romeo <blue>and tell him what's happened."
			);
			break;
		case 100:
			line = writeJournal(player, true,
				"Romeo and Juliet can be together in peace.",
				"I went to the Apothecary regarding making this cadava",
				"potion, and he told me to bring him some cadava berries.",
				"After the Apothecary made me the potion, I delivered it to",
				"Juliet. She asked me to tell Romeo the plan.",
				"I told Romeo what was going to happen, but I'm not exactly",
				"sure he understood what was happening. Ah well, I was",
				"rewarded for all of my help regardless.");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(756, 240);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[0];
	}

	@Override
	public Quest newInstance(Object object) {
		return this;
	}
}
