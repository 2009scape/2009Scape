package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents The KnightSword quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class TheKnightsSword extends Quest {
	
	/**
	 * Represents the portrait item.
	 */
	private static final Item PORTRAIT = new Item(666);

	/**
	 * Constructs a new {@code TheKnightsSword} {@code Object}.
	 */
	public TheKnightsSword() {
		super(
			"The Knight's Sword",
			22,
			21,
			1,
			122, 0, 1, 7
		);
	}

	@Override
	public Quest newInstance(Object object) {
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				BLUE + "I can start this quest by speaking to the " + RED + "Squire " + BLUE + "in the",
				BLUE + "courtyard of the " + RED + "White Knights' Castle " + BLUE + "in " + RED + "southern Falador",
				BLUE + "To complete this quest I need:",
				RED + "Level 10 Mining",
				BLUE + "and to be unafraid of " + RED + "Level 57 Ice Warriors.");
			break;
		case 10:
			writeJournal(player,
				"<str>I told the Squire I would help him to replace the sword he",
				"<str>has lost. It could only be made by an Imcando Dwarf.",
				BLUE + "The Squire suggests I speak to " + RED + "Reldo " + BLUE + "in the " + RED + " Varrock Palace",
				RED + "Library " + BLUE + "for information about the " + RED + "Imcando Dwarves.");
			break;
		case 20:
			writeJournal(player,
				"<str>I told the Squire I would help him to replace the sword he",
				"<str>has lost. It could only be made by an Imcando Dwarf.",
				BLUE + "Reldo couldn't give me much information about the",
				RED + "Imcando " + BLUE + "except a few live on the " + RED + "southern peninsula of",
				RED + "Asgarnia, " + BLUE + "they dislike strangers, and LOVE " + RED + "redberry pies.");
			break;
		case 30:
			writeJournal(player,
				"<str>I told the Squire I would help him to replace the sword he",
				"<str>has lost. It could only be made by an Imcando Dwarf.",
				"<str>I found an Imcando Dwarf named Thurgo thanks to",
				"<str>information provided by Reldo. He wasn't very talkative",
				"<str>until I gave him a Redberry pie, which he gobbled up.",
				BLUE + "He will help me now I have gained his trust through " + RED + "pie.");
			break;
		case 40:
			writeJournal(player,
				"<str>I told the Squire I would help him to replace the sword he",
				"<str>has lost. It could only be made by an Imcando Dwarf.",
				"<str>I found an Imcando Dwarf named Thurgo thanks to",
				"<str>information provided by Reldo. He wasn't very talkative",
				"<str>until I gave him a Redberry pie, which he gobbled up.",
				RED + "Thurgo " + BLUE + "needs a " + RED + "picture of the sword " + BLUE + "before he can help.",
				BLUE + "I should probably ask the " + RED + "Squire " + BLUE + "about obtaining one.");
			break;
		case 50:
			line = writeJournal(player,
				"<str>I told the Squire I would help him to replace the sword he",
				"<str>has lost. It could only be made by an Imcando Dwarf.",
				"<str>I found an Imcando Dwarf named Thurgo thanks to",
				"<str>information provided by Reldo. He wasn't very talkative",
				"<str>until I gave him a Redberry pie, which he gobbled up.",
				"<str>Thurgo needed a picture of the sword to replace."
			);
			if (!player.getInventory().containsItem(PORTRAIT)) {
				writeJournal(player, line,
					BLUE + "The Squire told me about a " + RED + "portrait ",
					BLUE + "which has a " + RED + "picture of the sword " + BLUE + "in " + RED + "Sir Vyvin's room"
				);
			} else {
				writeJournal(player, line,
					BLUE + "I now have a picture of the " + RED + "Knight's Sword " + BLUE + "- I should take it",
					BLUE + "to " + RED + "Thurgo " + BLUE + "so that he can duplicate it."
				);
			}
			break;
		case 60:
			line = writeJournal(player,
				"<str>I told the Squire I would help him to replace the sword he",
				"<str>has lost. It could only be made by an Imcando Dwarf.",
				"<str>I found an Imcando Dwarf named Thurgo thanks to",
				"<str>information provided by Reldo. He wasn't very talkative",
				"<str>until I gave him a Redberry pie, which he gobbled up.",
				"<str>Thurgo needed a picture of the sword before he could",
				"<str>start work on a replacement. I took him a portrait of it.");
			if (player.getInventory().contains(667, 1) || player.getEquipment().contains(667, 1) || player.getBank().contains(667, 1)) {
				writeJournal(player, line,
					"<str>Thurgo has now smithed me a replica of Sir Vyvin's sword.",
					"",
					BLUE + "I should return it to the " + RED + "Squire " + BLUE + "for my " + RED + "reward."
				);
			} else {
				writeJournal(player, line,
					BLUE + "according to " + RED + "Thurgo " + BLUE + "to make a " + RED + "replica sword " + BLUE + "he will need",
					RED + "two Iron Bars " + BLUE + "and some " + RED + "Blurite Ore. Blurite Ore " + BLUE + "can only be",
					BLUE + "found " + RED + "deep in the caves below Thurgo's house."
				);
			}
			break;
		case 100:
			writeJournal(player,
				"<str>Thurgo needed a picture of the sword before he could",
				"<str>start work on a replacement. I took him a portrait of it.",
				"<str>After bringing Thurgo two iron bars and some blurite ore",
				"<str>he made me a fine replica of Sir Vyvin's Sword, which I",
				"<str>returned to the Squire for a reward.",
				"",
				"<col=FF0000>QUEST COMPLETE!</col>");
			break;
		}
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(667, 230);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.SMITHING, 12725),
		};
	}
}
