package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
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

	private static final Item BLURITE_SWORD = new Item(667);

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
				"I can start this quest by speaking to the <red>Squire <blue>in the",
				"courtyard of the <red>White Knights' Castle <blue>in <red>southern Falador",
				"To complete this quest I need:",
				"<red>Level 10 Mining",
				"and to be unafraid of <red>Level 57 Ice Warriors.");
			break;
		case 10:
			line = writeJournal(player, true,
				"I told the Squire I would help him to replace the sword he",
				"has lost. It could only be made by an Imcando Dwarf.");
			writeJournal(player, line,
				"The Squire suggests I speak to <red>Reldo <blue>in the <red>Varrock Palace",
				"<red>Library <blue>for information about the <red>Imcando Dwarves.");
			break;
		case 20:
			line = writeJournal(player, true,
				"I told the Squire I would help him to replace the sword he",
				"has lost. It could only be made by an Imcando Dwarf.");
			writeJournal(player, line,
				"Reldo couldn't give me much information about the",
				"<red>Imcando <blue>except a few live on the <red>southern peninsula of",
				"<red>Asgarnia, <blue>they dislike strangers, and LOVE <red>redberry pies.");
			break;
		case 30:
			line = writeJournal(player, true,
				"I told the Squire I would help him to replace the sword he",
				"has lost. It could only be made by an Imcando Dwarf.",
				"I found an Imcando Dwarf named Thurgo thanks to",
				"information provided by Reldo. He wasn't very talkative",
				"until I gave him a Redberry pie, which he gobbled up.");
			writeJournal(player, line,
				"He will help me now I have gained his trust through <red>pie.");
			break;
		case 40:
			line = writeJournal(player, true,
				"I told the Squire I would help him to replace the sword he",
				"has lost. It could only be made by an Imcando Dwarf.",
				"I found an Imcando Dwarf named Thurgo thanks to",
				"information provided by Reldo. He wasn't very talkative",
				"until I gave him a Redberry pie, which he gobbled up."
			);
			writeJournal(player, line,
				"<red>Thurgo <blue>needs a <red>picture of the sword <blue>before he can help.",
				"I should probably ask the <red>Squire <blue>about obtaining one.");
			break;
		case 50:
			line = writeJournal(player, true,
				"I told the Squire I would help him to replace the sword he",
				"has lost. It could only be made by an Imcando Dwarf.",
				"I found an Imcando Dwarf named Thurgo thanks to",
				"information provided by Reldo. He wasn't very talkative",
				"until I gave him a Redberry pie, which he gobbled up.",
				"Thurgo needed a picture of the sword to replace."
			);
			if (!player.getInventory().containsItem(PORTRAIT)) {
				writeJournal(player, line,
					"The Squire told me about a <red>portrait ",
					"which has a <red>picture of the sword <blue>in <red>Sir Vyvin's room."
				);
			} else {
				writeJournal(player, line,
					"I now have a picture of the <red>Knight's Sword <blue>- I should take it",
					"to <red>Thurgo <blue>so that he can duplicate it."
				);
			}
			break;
		case 60:
			line = writeJournal(player, true,
				"I told the Squire I would help him to replace the sword he",
				"has lost. It could only be made by an Imcando Dwarf.",
				"I found an Imcando Dwarf named Thurgo thanks to",
				"information provided by Reldo. He wasn't very talkative",
				"until I gave him a Redberry pie, which he gobbled up.",
				"Thurgo needed a picture of the sword before he could",
				"start work on a replacement. I took him a portrait of it.");
			if (player.hasItem(BLURITE_SWORD)) {
				line = writeJournal(player, line, true,
					"Thurgo has now smithed me a replica of Sir Vyvin's sword.");
				writeJournal(player, ++line,
					"I should return it to the <red>Squire <blue>for my <red>reward."
				);
			} else {
				writeJournal(player, line,
					"According to <red>Thurgo<blue>, to make a <red>replica sword <blue>he will need",
					"<red>two Iron Bars <blue>and some <red>Blurite Ore. Blurite Ore <blue>can only be",
					"found <red>deep in the caves below Thurgo's house."
				);
			}
			break;
		case 100:
			line = writeJournal(player, true,
				"Thurgo needed a picture of the sword before he could",
				"start work on a replacement. I took him a portrait of it.",
				"After bringing Thurgo two iron bars and some blurite ore",
				"he made me a fine replica of Sir Vyvin's Sword, which I",
				"returned to the Squire for a reward.");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!</col>");
			break;
		}
	}

	@Override
	public Item getRewardComponentItem() {
		return new Item(667);
	}

	@Override
	public QuestReward[] getQuestRewards() {
		return new QuestReward[]{
			new QuestReward(Skills.SMITHING, 12725),
		};
	}
}
