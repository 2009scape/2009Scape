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
		int line;
		int questStage = getStage(player);
		switch (questStage) {
			case 0:
				writeJournal(player,
					"I can start this quest by speaking to <red>Duke Horacio <blue>of",
					"<red>Lumbridge, <blue>upstairs in <red>Lumbridge Castle.");
				break;
			case 10:
				line = writeJournal(player, true,
					"I spoke to Duke Horacio and he showed me a strange",
					"talisman that had been found by one of his subjects.",
					"I agreed to take it to the Wizards' Tower, South West of",
					"Lumbridge for further examination by the wizards.");
				writeJournal(player, line,
					"I need to find the <red>Head Wizard <blue>and give him the <red>Talisman.");
				break;
			case 20:
				line = writeJournal(player, true,
					"I spoke to Duke Horacio and he showed me a strange",
					"talisman that had been found by one of his subjects.",
					"I agreed to take it to the Wizards' Tower, South West of",
					"Lumbridge for further examination by the wizards.",
					"I gave the Talisman to the Wizard but I didn't want to help",
					"him in his research right now.");
				writeJournal(player, line,
					"I should talk to <red>Sedridor <blue>again to continue this quest.");
				break;
			case 30:
				line = writeJournal(player, true,
					"I spoke to Duke Horacio and he showed me a strange",
					"talisman that had been found by one of his subjects.",
					"I agreed to take it to the Wizards' Tower, South West of",
					"Lumbridge for further examination by the wizards.",
					"I gave the Talisman to the Head of the Tower and",
					"agreed to help him with his research into rune stones.");
				writeJournal(player, line,
					"I should take this <red>Research Package <blue>to <red>Aubury <blue>in <red>Varrock.");
				break;
			case 40:
				line = writeJournal(player, true,
					"I spoke to Duke Horacio and he showed me a strange",
					"talisman that had been found by one of his subjects.",
					"I agreed to take it to the Wizards' Tower, South West of",
					"Lumbridge for further examination by the wizards.",
					"I gave the Talisman to the Head of the Tower and",
					"agreed to help him with his research into rune stones.",
					"I took the research package to Varrock and delivered it.");
				writeJournal(player, line,
					"I should speak to <red>Aubury <blue>again when he has finished",
					"examining the <red>Research Package <blue>I have delivered to him.");
				break;
			case 50:
				line = writeJournal(player, true,
					"I spoke to Duke Horacio and he showed me a strange",
					"talisman that had been found by one of his subjects.",
					"I agreed to take it to the Wizards' Tower, South West of",
					"Lumbridge for further examination by the wizards.",
					"I gave the Talisman to the Head of the Tower and",
					"agreed to help him with his research into rune stones.",
					"I took the research package to Varrock and delivered it.",
					"Aubury was interested in the research package and gave",
					"me his own research notes to deliver to Sedridor.");
				writeJournal(player, line,
					"I should take the <red>notes <blue>to <red>Sedridor <blue>and see what he says.");
				break;
			case 100:
				line = writeJournal(player, true,
					"I spoke to Duke Horacio and he showed me a strange",
					"talisman that had been found by one of his subjects.",
					"I agreed to take it to the Wizards' Tower, South West of",
					"Lumbridge for further examination by the wizards.",
					"I gave the Talisman to the Head of the Tower and",
					"agreed to help him with his research into rune stones.",
					"I took the research package to Varrock and delivered it.",
					"Aubury was interested in the research package and gave",
					"me his own research notes to deliver to Sedridor.",
					"I brought Sedridor the research notes that Aubury had",
					"compiled so that he could compare their research. They",
					"They discovered that it was now possible to create new rune",
					"stones, a skill that had been thought lost forever.",
					"In return for all of my help they taught me how to do this,",
					"and will teleport me to mine blank runes anytime.");
				writeJournal(player, ++line,
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
