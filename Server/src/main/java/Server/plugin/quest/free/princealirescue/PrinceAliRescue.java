package plugin.quest.free.princealirescue;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the prince ali rescue quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class PrinceAliRescue extends Quest {

	/**
	 * Represents the rope item.
	 */
	private static final Item ROPE = new Item(954);

	/**
	 * Represents the pink skirt item.
	 */
	private static final Item SKIRT = new Item(1013);

	/**
	 * Represents the yellow wig item.
	 */
	private static final Item YELLOW_WIG = new Item(2419);

	/**
	 * Represents the skin paste item.
	 */
	private static final Item PASTE = new Item(2424);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 700);
	
	/**
	 * Constructs a new {@Code PrinceAliRescue} {@Code Object}
	 */
	public PrinceAliRescue() {
		super(
			"Prince Ali Rescue",
			24,
			23,
			3,
			273, 0, 1, 110
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new LadyKeliDialogue(), new LadyKeliNPC(), new PrinceAliRescuePlugin(), new WigDyePlugin());
		return this;
	}

	static String[][] JOURNAL_ENTRIES = new String[][]{
		new String[]{
			"I started this quest by speaking to Hassan in Al-Kharid",
			"Palace. He told me I should speak to Osman the spymaster."
		},
		new String[]{
			"I should go and speak to <red>Osman <blue>for details on the quest."
		},
		new String[]{
			"<red>Prince Ali <blue>has been <red>kidnapped <blue>but luckily the spy <red>Leela <blue>has",
			"found he is being held near <red>Draynor Village. <blue>I will need to",
			"<red>disguise <blue>the <red>Prince <blue>and <red>tie <blue>up his <red>captor <blue>to <red>free <blue>him from",
			"their <red>clutches."
		},
		new String[]{
			"I also had to prevent the Guard from seeing what I was up",
			"to by getting him drunk."
		},
		new String[]{
			"With the guard disposed of, I used my rope to tie up Lady",
			"Keli in a cupboard, so I could disguise the Prince."
		},
		new String[]{
			"I then used a wig, and some skin paste to make the",
			"prince look like Lady Keli so he could escape to his",
			"freedom with Leela after unlocking his cell door."
		}
	};

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				"I can start this quest by speaking to <red>Hassan <blue>at the palace",
				"in <red>Al-Kharid.");
			break;
		case 10:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			writeJournal(player, line, JOURNAL_ENTRIES[1]);
			break;
		case 20:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[2]);
			writeJournal(player, line,
				"To do this I should:",
				"Talk to <red>Leela <blue>near <red>Draynor Village <blue>for advice.",
				"Get a <red>duplicate <blue>of the <red>key <blue>that is <red>imprisoning <blue>the <red>prince.",
				hasItem(player, ROPE) ?
					"<str>I have some rope with me." :
					"Get some <red>rope <blue>to tie up the Prince's <red>kidnapper.",
				hasItem(player, PASTE) ?
					"<str>I have some skin paste suitable for disguise with me." :
					"Get something to <red>colour <blue>the <red>Prince's skin <blue>as a <red>disguise.",
				hasItem(player, SKIRT) ?
					"<str>I have a skirt suitable for a disguise with me." :
					"Get a <red>skirt <blue>similar to his <red>kidnapper <blue>as <red>disguise.",
				hasItem(player, YELLOW_WIG) ?
					"<str>I have a wig suitable for disguise with me." :
					"Get a <red>Wig <blue>to <red>help disguise <blue>the <red>prince."
				);
			break;
		case 30:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[2]);
			writeJournal(player, line,
				"To do this I should:",
				"Talk to <red>Leela <blue>near <red>Draynor Village <blue>for advice.",
				"I have duplicated a key, I need to get it from <red>Leela.",
				hasItem(player, ROPE) ?
					"<str>I have some rope with me." :
					"Get some <red>rope <blue>to tie up the Prince's <red>kidnapper.",
				hasItem(player, PASTE) ?
					"<str>I have some skin paste suitable for disguise with me." :
					"Get something to <red>colour <blue>the <red>Prince's skin <blue>as a <red>disguise.",
				hasItem(player, SKIRT) ?
					"<str>I have a skirt suitable for a disguise with me." :
					"Get a <red>skirt <blue>similar to his <red>kidnapper <blue>as <red>disguise.",
				hasItem(player, YELLOW_WIG) ?
					"<str>I have a wig suitable for disguise with me." :
					"Get a <red>Wig <blue>to <red>help disguise <blue>the <red>prince."
			);
			break;
		case 40:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			if (player.getAttribute("guard-drunk", false)) {
				line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
				line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
				writeJournal(player, line,
					"With the guard out of the way, all I have to do now is use",
					"the <red>Skin Potion<blue>, <red>Pink Skirt<blue>, <red>Rope<blue>, <red>Blonde Wig <blue>and <red>Cell Key <blue>to",
					"free <red>Prince Ali <blue>from his cell somehow."
				);
			} else {
				writeJournal(player, line,
					"Do something to prevent <red>Joe the Guard <blue>seeing the",
					"escape.",
					"Use the <red>Skin potion<blue>, <red>Pink Skirt<blue>, <red>Rope<blue>, <red>Blonde Wig <blue>and <red>Cell",
					"<red>Key <blue>to free <red>Prince Ali <blue>from his cell somehow."
				);
			}
			break;
		case 50:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			writeJournal(player, line,
				"I need to <red>Unlock the cell door <blue>and then give the Prince the",
				"<red>Pink Skirt<blue>, the <red>Skin paste <blue>and the <red>Blonde Swig <blue>so that the",
				"can safely <red>escape <blue>disguised as <red>Lady Keli.");
			break;
		case 60:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[5]);
			writeJournal(player, line,
				"I should return to <red>Hassan <blue>to claim my reward.");
			break;
		case 100:
			line = writeJournal(player, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[2]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[3]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[4]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[5]);
			line = writeJournal(player, line, true,
				"Hassan the chancellor rewarded me for all of my help.",
				"I am now a friend of Al-Kharid and may pass through the",
				"gate leading between Lumbridge and Al-Kharid for free");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	/**
	 * Check if the player has the item.
	 * @param player the player.
	 * @param item the item.
	 * @return true or false.
	 */
	public static boolean hasItem(final Player player, final Item item) {
		return player.getInventory().containsItem(item);
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendMessage("The chancellor pays you 700 coins.");
		player.removeAttribute("guard-drunk");
	}

	@Override
	public Item getRewardComponentItem() {
		return new Item(995, 20);
	}

	@Override
	public QuestReward[] getQuestRewards() {
		return new QuestReward[]{
			new QuestReward(COINS),
		};
	}
}
