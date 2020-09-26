package plugin.quest.members.waterfallquest;

import core.game.node.entity.player.link.quest.QuestReward;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * The main type for the waterfall quest.
 * @author Splinter
 */
@InitializablePlugin
public class WaterFall extends Quest {

	/**
	 * The name of this quest.
	 */
	public static final String NAME = "Waterfall";

	/**
	 * Constructs a new {@code WaterFall} {@code Object}.
	 */
	public WaterFall() {
		super(
			NAME,
			65,
			64,
			1,
			65, 0, 1, 10
		);
	}

	static String[][] JOURNAL_ENTRIES = new String[][]{
		new String[]{
			"I spoke to <red>Almera <blue>in a house close to the Baxtorian",
			"waterfall. Her son was missing so I offered to help find",
			"him. The boy, <red>Hudon's <blue>looking for treasure in the waterfall.",
			""
		},
		new String[]{
			"I found Hudon a short raft ride down the river. He is",
			"convinced there is treasure here somewhere. Maybe I",
			"need to do a little research.",
			""
		},
		new String[]{
			"I found a book in the tourist office about Baxtorian. The",
			"book tells of a sad love story about 2 elf lovers. It ends",
			"with Baxtorian withdrawing to his home under the waterfall",
			"after his wife dies. It is told that only Glarial could enter",
			"their home.",
			""
		},
		new String[]{
			"The book also mentions <red>Glarial's tomb <blue>and a pebble, it",
			"appears that the pebble is used to enter the tomb."
		},
		new String[]{
			"From what I understand <red>Glarial's pebble <blue>was hidden in a ",
			"cave under the <red>Tree Gnome Village <blue>by <red>Golrie's <blue>ancestors.",
			""
		},
		new String[]{
			"Inside the tomb I found Glarial's amulet and ashes.",
			""
		},
		new String[]{
			"I finally got access to the derelict home of Baxtorian. The",
			"door must have been keyed to Glarial's amulet.",
			""
		},
		new String[]{
			"I have found a room containing the <red>Chalice of Eternity. <blue>The",
			"chalice is suspended in midair just out of reach."
		},
		new String[]{
			"",
			"Using Glarial's ashes as a counterweight, I was able to",
			"remove the treasure that had been left in the chalice."
		}
	};

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line = 12;
		switch (stage) {
		case 0:
			writeJournal(player, line,
				"I can start this quest by speaking to <red>Almera <blue>in her house",
				"next to the <red>Baxtorian Falls.",
				"",
				"I need to be able to fight <red>Level 84 Giants.");
			break;
		case 10:
			writeJournal(player, line, JOURNAL_ENTRIES[0]);
			break;
		case 20:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			writeJournal(player, line, JOURNAL_ENTRIES[1]);
			break;
		case 30:
			boolean hasPebble = player.getInventory().containsItem(new Item(294, 1));
			boolean hasAmulet = player.getInventory().containsItem(new Item(295, 1)) || player.getEquipment().containsOneItem(295);
			boolean isInside = player.getLocation().getY() >= 9902;

			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, isInside || hasAmulet, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, isInside || hasAmulet || hasPebble, JOURNAL_ENTRIES[4]);

			if (isInside || hasAmulet) {
				line = writeJournal(player, line, true, JOURNAL_ENTRIES[5]);
			}
			if (isInside) {
				line = writeJournal(player, line, true, JOURNAL_ENTRIES[6]);
				writeJournal(player, line, JOURNAL_ENTRIES[7]);
			}
			break;
		case 100:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[5]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[6]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[7]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[8]);
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!</col>");
			break;
		}
	}

	@Override
	public Item getRewardComponentItem() {
		return new Item(1601);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.STRENGTH, 13750),
			new QuestReward(Skills.ATTACK, 13750),
			new QuestReward(new Item(1601, 2)), // Diamond
			new QuestReward(new Item(2357, 2)), // Gold bar
			new QuestReward(new Item(299, 40), "Mithril seeds"), // Mithril seed
		};
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new AlmeraDialogue(), new BookOnBaxtorianPlugin(), new GolrieDialogue(), new HadleyDialogue(), new HudonDialogue(), new WaterfallPlugin(), new WaterfallTreeDialogue());
		return this;
	}

}
