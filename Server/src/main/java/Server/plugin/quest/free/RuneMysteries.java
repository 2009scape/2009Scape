package plugin.quest.free;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the rune mysteries fortress quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class RuneMysteries extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "Rune Mysteries";

	/**
	 * Constructs a new {@code RuneMysteries} {@code Object}.
	 */
	public RuneMysteries() {
		super(
			NAME,
			27,
			26,
			1,
			63, 0, 1, 6
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int questStage = getStage(player);
		switch (questStage) {
			case 0:
				writeJournal(player,
					BLUE + "I can start this quest by speaking to " + RED + "Duke Horacio " + BLUE + "of",
					RED + "Lumbridge, " + BLUE + "upstairs in " + RED + "Lumbridge Castle.");
				break;
			case 10:
				writeJournal(player,
					"<str>I spoke to Duke Horacio and he showed me a strange",
					"<str>talisman that had been found by one of his subjects.",
					"<str>I agreed to take it to the Wizards' Tower, South West of",
					"<str>Lumbridge for further examination by the wizards.",
					BLUE + "I need to find the " + RED + "Head Wizard " + BLUE + "and give him the " + RED + "Talisman");
				break;
			case 20:
				writeJournal(player,
					"<str>I spoke to Duke Horacio and he showed me a strange",
					"<str>talisman that had been found by one of his subjects.",
					"<str>I agreed to take it to the Wizards' Tower, South West of",
					"<str>Lumbridge for further examination by the wizards.",
					"<str>I gave the Talisman to the Wizard but I didn't want to help",
					"<str>him in his research right now.",
					BLUE + "I should talk to " + RED + "Sedridor " + BLUE + "again to continue this quest.");
				break;
			case 30:
				writeJournal(player,
					"<str>I spoke to Duke Horacio and he showed me a strange",
					"<str>talisman that had been found by one of his subjects.",
					"<str>I agreed to take it to the Wizards' Tower, South West of",
					"<str>Lumbridge for further examination by the wizards.",
					"<str>I gave the Talisman to the Head of the Tower and",
					"<str>agreed to help him with his research into rune stones.",
					BLUE + "I should take this " + RED + "Research Package " + BLUE + "to " + RED + "Aubury " + BLUE + "in " + RED + "Varrock");
				break;
			case 40:
				writeJournal(player,
					"<str>I spoke to Duke Horacio and he showed me a strange",
					"<str>talisman that had been found by one of his subjects.",
					"<str>I agreed to take it to the Wizards' Tower, South West of",
					"<str>Lumbridge for further examination by the wizards.",
					"<str>I gave the Talisman to the Head of the Tower and",
					"<str>agreed to help him with his research into rune stones.",
					"<str>I took the research package to Varrock and delivered it.",
					BLUE + "I should speak to " + RED + "Aubury " + BLUE + "again when he has finished",
					BLUE + "examining the " + RED + "research package " + BLUE + " I have delivered to him.");
				break;
			case 50:
				writeJournal(player,
					"<str>I spoke to Duke Horacio and he showed me a strange",
					"<str>talisman that had been found by one of his subjects.",
					"<str>I agreed to take it to the Wizards' Tower, South West of",
					"<str>Lumbridge for further examination by the wizards.",
					"<str>I gave the Talisman to the Head of the Tower and",
					"<str>agreed to help him with his research into rune stones.",
					"<str>I took the research package to Varrock and delivered it.",
					"<str>Aubury was interested in the research package and gave",
					"<str>me his own research notes to deliver to Sedridor.",
					BLUE + "I should take the " + RED + "notes " + BLUE + "to " + RED + "Sedridor " + BLUE + "and see what he says");
				break;
			case 100:
				writeJournal(player,
					"<str>I spoke to Duke Horacio and he showed me a strange",
					"<str>talisman that had been found by one of his subjects.",
					"<str>I agreed to take it to the Wizards' Tower, South West of",
					"<str>Lumbridge for further examination by the wizards.",
					"<str>I gave the Talisman to the Head of the Tower and",
					"<str>agreed to help him with his research into rune stones.",
					"<str>I took the research package to Varrock and delivered it.",
					"<str>Aubury was interested in the research package and gave",
					"<str>me his own research notes to deliver to Sedridor.",
					"<str>I brought Sedridor the research notes that Aubury had",
					"<str>compiled so that he could compare their research. They",
					"<str>They discovered that it was now possible to create new rune",
					"<str>stones, a skill that had been thought lost forever.",
					"<str>In return for all of my help they taught me how to do this,",
					"<str>and will teleport me to mine blank runes anytime.",
					"",
					"<col=FF0000>QUEST COMPLETE!");
				break;
		}
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(1438, 240);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward("Runecrafting skill"),
			new QuestReward("Air talisman"),
		};
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
