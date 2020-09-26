package plugin.quest.members;

import core.game.node.entity.player.link.quest.QuestReward;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the demon slayer quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class WolfWhistle extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "Wolf Whistle";

	/**
	 * The wolf bones item.
	 */
	public static final Item WOLF_BONES = new Item(2859, 2);

	/**
	 * Constructs a new {@code WolfWhistle} {@code Object}.
	 */
	public WolfWhistle() {
		super(
			NAME,
			146,
			145,
			1
		);
	}

	static String[][] JOURNAL_ENTRIES = new String[][]{
		new String[]{
			"Having spoken to <red>Pikkupstix<blue>, it seems that all I have to do",
			"is get rid of the <red>little rabbit upstairs in his house."
		},
		new String[]{
			"It appears that I have underestimated the rabbit in this",
			"case; it is some <red>huge rabbit-wolf-monster-bird-thing<blue>. I",
			"think I should speak to <red>Pikkupstix <blue>to find out what is going",
			"on."
		},
		new String[]{
			"",
			"I have spoken to <red>Pikkupstix<blue>, who has promised to teach me",
			"the secrets of <red>Summoning <blue>if I can help dismiss the <red>giant",
			"<red>wolpertinger<blue>. To do this, I need to bring him <red>2 lots of wolf",
			"<red>bones<blue>."
		},
		new String[]{
			"",
			"Pikkupstix has given me some <red>gold charms<blue>, <red>spirit shards",
			"and <red>pouches<blue>, with which to make a <red>spirit wolf pouch <blue>and",
			"some <red>Howl scrolls<blue>. I will then be able to use them to dismiss",
			"the <red>giant wolpertinger<blue>."
		},
		new String[]{
			"I have infused the 2 spirit wolf pouches, but I need to",
			"transform one of them into scrolls at the obelisk."
		},
		new String[]{
			"I have dismissed the <red>giant wolpertinger<blue>."
		}
	};

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				"I can begin this quest by talking to <red>Pikkupstix<blue>, who lives in",
				"<red>Taverley.");
			break;
		case 10:
			writeJournal(player, JOURNAL_ENTRIES[0]);
			break;
		case 20:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			writeJournal(player, line, JOURNAL_ENTRIES[1]);
			break;
		case 30:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[2]);
			writeJournal(player, line, player.getInventory().containsItem(WOLF_BONES),
				"I need to get 2 lots of wolf bones.");
			break;
		case 40:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true,
				"I have given Pikkupstix all of the items he requested.");
			line = writeJournal(player, line, JOURNAL_ENTRIES[3]);
			writeJournal(player, line,
				"I need to open the <red>trapdoor <blue>with the <red>trapdoor key <blue>that I",
				"have been given.");
			break;
		case 50:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[3]);
			writeJournal(player, line, JOURNAL_ENTRIES[4]);
			break;
		case 60:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			writeJournal(player, line, JOURNAL_ENTRIES[5]);
			break;
		case 100:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[5]);
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.removeAttribute("searched-body");
		player.getInterfaceManager().openInfoBars();
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		int val = player.getConfigManager().get(1178);
		boolean open = val >= 4096;
		if (stage == 100) {
			if (val == 5 || val == 0) {
				return new int[] { 1178, 32989 };
			} else if (val == 4101) {
				return new int[] { 1178, 28893 };
			}
			return new int[] { 1178, val };
		}
		return new int[] { 1178, stage > 0 ? 5 + (open ? 4096 : 0) : 0 };
	}

	@Override
	public Item getRewardComponentItem() {
		return new Item(12047);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.SUMMONING, 276),
			new QuestReward("Access to the Summoning"),
			new QuestReward("skill."),
			new QuestReward(new Item(12158, 275)), // Gold charms
		};
	}

	@Override
	public Quest newInstance(Object object) {
		return this;
	}
}
