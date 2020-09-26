package plugin.quest.members.animalmagnetism;

import core.game.node.entity.player.link.quest.QuestRequirement;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.quest.free.ErnestTheChicken;
import plugin.quest.free.therestlessghost.RestlessGhost;
import plugin.quest.members.PriestInPeril;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.PluginManager;

import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.ContainerHandler;
import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.HammerMagnetPlugin;
import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.ResearchNoteHandler;
import core.plugin.InitializablePlugin;
import plugin.quest.members.animalmagnetism.AnimalMagnetismPlugin.UndeadTreePlugin;

/**
 * Handles the animal magnetism quest.
 * @author Vexia
 */
@InitializablePlugin
public final class AnimalMagnetism extends Quest {

	/**
	 * The name of this quest.
	 */
	public static String NAME = "Animal Magnetism";

	/**
	 * The crone made amulet item.
	 */
	public static final Item CRONE_AMULET = new Item(10500);

	/**
	 * The selected iron item.
	 */
	public static final Item SELECTED_IRON = new Item(10488);

	/**
	 * The bar magnet item.
	 */
	public static final Item BAR_MAGNET = new Item(10489);

	/**
	 * The undead twigs item.
	 */
	public static final Item UNDEAD_TWIGS = new Item(10490);

	/**
	 * The blessed axe item.
	 */
	public static final Item BLESSED_AXE = new Item(10491);

	/**
	 * The research notes.
	 */
	public static final Item RESEARCH_NOTES = new Item(10492);

	/**
	 * The translated notes.
	 */
	public static final Item TRANSLATED_NOTES = new Item(10493);

	/**
	 * The pattern item.
	 */
	public static final Item PATTERN = new Item(10494);

	/**
	 * The container item.
	 */
	public static final Item CONTAINER = new Item(10495);

	/**
	 * The polished items.
	 */
	public static final Item POLISHED_BUTTONS = new Item(10496);

	/**
	 * The hard leather item.
	 */
	public static final Item HARD_LEATHER = new Item(1743);

	/**
	 * The avas attractor item.
	 */
	public static final Item AVAS_ATTRACTOR = new Item(10498);

	/**
	 * The avas accumulator item.
	 */
	public static final Item AVAS_ACCUMULATOR = new Item(10499);

	/**
	 * The reward item
	 */
	private Item avas_device;

	/**
	 * Constructs a new {@code AnimalMagnetism} {@code Object}.
	 */
	public AnimalMagnetism() {
		super(
			"Animal Magnetism",
			33,
			32,
			1
		);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new AvaDialogue());
		PluginManager.definePlugin(new AliceDialogue());
		PluginManager.definePlugin(new WitchDialogue());
		PluginManager.definePlugin(new ContainerHandler());
		PluginManager.definePlugin(new UndeadTreePlugin());
		PluginManager.definePlugin(new AvasDevicePlugin());
		PluginManager.definePlugin(new HammerMagnetPlugin());
		PluginManager.definePlugin(new ResearchNoteHandler());
		PluginManager.definePlugin(new AliceHusbandDialogue());
		PluginManager.definePlugin(new AnimalMagnetismPlugin());
		return this;
	}

	static String[][] JOURNAL_ENTRIES = new String[][]{
		new String[]{
			"I need to find someone who will supply <red>undead chickens <blue>to",
			"me. Perhaps the <red>farm near Port Phasmatys <blue>sells them..."
		},
		new String[]{
			"The <red>ghost farmer <blue>wants me to talk to his <red>wife <blue>for him. I",
			"need to do this before he will sell chickens."
		},
		new String[]{
			"I should talk to the <red>crone <blue>west of the undead farm and ask",
			"about <red>ghostspeak amulet<blue>s. Perhaps she can enable the",
			"<red>ghost farmer <blue>to talk to his <red>wife <blue>directly."
		},
		new String[]{
			"I need to talk the <red>crone <blue>while I have a <red>ghostspeak",
			"<red>amulet <blue>so that she can create a new amulet specifically",
			"for the <red>ghost farmer."
		},
		new String[]{
			"I should give the <red>ghost farmer <blue>a <red>crone-made amulet <blue>so",
			"that he can talk directly to his <red>wife."
		},
		new String[]{
			"The <red>farmer <blue>seems friendlier now; I need to talk to him",
			"about the <red>undead chickens."
		},
		new String[]{
			"The farmer has agreed to sell chickens; now he needs to",
			"catch one for me.",
		},
		new String[]{
			"The <red>ghost farmer <blue>caught some chickens; now I need to buy",
			"2 from him and deliver them to Ava."
		},
		new String[]{
			"I need to talk to the <red>Witch <blue>in <red>Draynor Manor <blue>about",
			"<red>magically attuned magnets<blue>. Apparently, the <red>undead",
			"<red>chicken <blue>will be using magnets in my reward."
		},
		new String[]{
			"I need to deliver 5 <red>iron bars <blue>to the <red>Witch <blue>in <red>Draynor Manor.",
			"She will select one most suitable for both magnetising and",
			"mystical use."
		},
		new String[]{
			"I need to make a <red>magnet <blue>by hammering the <red>selected iron",
			"bar while facing north in <red>Rimmington mines<blue>. I then need",
			"to pass this magnet to <red>Ava."
		},
		new String[]{
			"I need to find some way of chopping the <red>undead trees",
			"near <red>Draynor Manor <blue>so that <red>Ava <blue>can use this wood as a",
			"source of unending arrow shafts."
		},
		new String[]{
			"Ava suspects that <red>Turael<blue>, the Slayer Master in Burthorpe,",
			"might be able to help."
		},
		new String[]{
			"I need to collect a <red>holy symbol of Saradomin <blue>and a <red>mithril",
			"<red>axe<blue>. <red>Turael<blue>, the Burthorpe Slayer, can use these to",
			"construct a new axe for my undead tree cutting."
		},
		new String[]{
			"I need to chop some undead wood with the silver-edged",
			"mithril axe. Then Ava will want the wood for constructing",
			"my reward.",
		},
		new String[]{
			"I should ask <red>Ava <blue>for the <red>garbled research notes <blue>that she",
			"cannot translate. When translated, these notes will tell",
			"her how to combine the <red>undead wood<blue>, <red>undead chicken <blue>and",
			"<red>magnet <blue>into some bizarre device."
		},
		new String[]{
			"The <red>research notes <blue>must be translated. I should try to",
			"decipher them even though they look like total gibberish",
			"to me."
		},
		new String[]{
			"The notes look less confusing now. <red>Ava <blue>will want to see",
			"these <red>translated research notes<blue>."
		},
		new String[]{
			"Almost finished! I must combine the <red>pattern <blue>which Ava",
			"gave to me with some <red>polished buttons <blue>and a bit of <red>hard",
		},
		new String[]{
			"<red>leather.",
		},
		new String[]{
			"Ava wants the completed container. She can then combine",
			"it with the undead chicken, undead wood and magnet."
		}
	};

	@Override
	public String[][] getJournalEntries() {
		return JOURNAL_ENTRIES;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line = JOURNAL_TEXT_START;

		if (stage >= 10) {
			line = writeJournal(player, line, stage == 100,
				"<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward",
				"making her bed more comfortable, the other will be used in",
				"some unexplained reward for me."
			);
		}

		switch (stage) {
		case 0:
			line = writeJournal(player,
				"I can start this quest by talking to",
				"<red>Ava <blue>who lives in <red>Draynor Manor.",
				"",
				"Minimum requirements:"
			);
			writeJournal(player, line, getQuestRequirementsJournal(player));
			break;
		case 10:
			writeJournal(player, line, JOURNAL_ENTRIES[0]);
			break;
		case 11:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			writeJournal(player, line, JOURNAL_ENTRIES[1]);
			break;
		case 12:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, JOURNAL_ENTRIES[1]);
			writeJournal(player, line,
				"The <red>ghost farmer<blue>'s <red>wife <blue>needs to know bank information",
				"that only the farmer can supply.");
			break;
		case 13:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			writeJournal(player, line,
				"The <red>ghost farmer <blue>won't tell me the information his <red>wife <blue>is",
				"after. Perhaps I should talk to her again.");
			break;
		case 14:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			writeJournal(player, line,
				"The <red>ghost farmer<blue>'s <red>wife <blue>still needs to know bank",
				"information that only the farmer can supply.");
			break;
		case 15:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			writeJournal(player, line,
				"I still need to find a way to allow the <red>undead farmer <blue>and his",
				"<red>wife <blue>to communicate with each other.");
			break;
		case 16:
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[0]);
			line = writeJournal(player, line, true, JOURNAL_ENTRIES[1]);
			writeJournal(player, line, JOURNAL_ENTRIES[2]);
			break;
		case 17:
			line = writeJournalEntries(player, line, true, 2);
			writeJournal(player, line, JOURNAL_ENTRIES[3]);
			break;
		case 18:
			line = writeJournalEntries(player, line, true, 3);
			writeJournal(player, line, JOURNAL_ENTRIES[4]);
			break;
		case 19:
			line = writeJournalEntries(player, line, true, 4);
			writeJournal(player, line, JOURNAL_ENTRIES[5]);
			break;
		case 20:
			line = writeJournalEntries(player, line, true, 6);
			writeJournal(player, line, JOURNAL_ENTRIES[7]);
			break;
		case 25:
			line = writeJournalEntries(player, line, true, 7);
			writeJournal(player, line, JOURNAL_ENTRIES[8]);
			break;
		case 26:
			line = writeJournalEntries(player, line, true, 8);
			writeJournal(player, line, JOURNAL_ENTRIES[9]);
			break;
		case 27:
			line = writeJournalEntries(player, line, true, 9);
			writeJournal(player, line, JOURNAL_ENTRIES[10]);
			break;
		case 28:
			line = writeJournalEntries(player, line, true, 10);
			writeJournal(player, line,
				"I need to chop some wood from the <red>undead trees <blue>near",
				"<red>Draynor Manor<blue>. <red>Ava <blue>can use this wood as a source of",
				"unending arrow shafts in my reward. She suggested that I",
				"use a Woodcutting axe made of nothing less powerful than",
				"mithril.");
			break;
		case 29:
			line = writeJournalEntries(player, line, true, 10);
			line = writeJournal(player, line, JOURNAL_ENTRIES[11]);
			writeJournal(player, line,
				"Perhaps <red>Ava <blue>could give me some advice...");
			break;
		case 30:
			line = writeJournalEntries(player, line, true, 11);
			writeJournal(player, line, JOURNAL_ENTRIES[12]);
			break;
		case 31:
			line = writeJournalEntries(player, line, true, 11);
			line = writeJournal(player, line, JOURNAL_ENTRIES[12]);
			writeJournal(player, line, JOURNAL_ENTRIES[13]);
			break;
		case 32:
			line = writeJournalEntries(player, line, true, 14);
			writeJournal(player, line, JOURNAL_ENTRIES[15]);
			break;
		case 33:
			line = writeJournalEntries(player, line, true, 15);
			if (!player.hasItem(TRANSLATED_NOTES)) {
				writeJournal(player, line, JOURNAL_ENTRIES[16]);
			} else {
				line = writeJournal(player, line, true, JOURNAL_ENTRIES[16]);
				writeJournal(player, line, JOURNAL_ENTRIES[17]);
			}
			break;
		case 34:
			line = writeJournalEntries(player, line, true, 17);
			if (!player.hasItem(CONTAINER)) {
				line = writeJournal(player, line, JOURNAL_ENTRIES[18]);
				writeJournal(player, line,
					"<red>leather<blue>. <red>Ava <blue>tells me that the <red>H.A.M hideout <blue>is a good",
					"place to obtain <red>buttons."
				);
			} else {
				line = writeJournal(player, line, true, JOURNAL_ENTRIES[18]);
				line = writeJournal(player, line, true, JOURNAL_ENTRIES[19]);
				writeJournal(player, line, JOURNAL_ENTRIES[20]);
			}
			break;
		case 100:
			line = writeJournalEntries(player, line, true, 20);
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!",
				"<red>Ava's reward for me is an arrow attracting and creating",
				"<red>backpack.",
				"The method is this: the <red>undead chicken <blue>can attract lost,",
				"stray arrowheads with a magnet, add wood from the",
				"undead twigs and then finish the arrows using its own",
				"feathers. This will give me an unending source of arrows.",
				"The cunning bird will also attract some of the arrows which I",
				"have fired, preventing these arrows from falling upon the",
				"ground.",
				"If I lose my device, I can talk to <red>Ava <blue>for a new one,",
				"although it will cost me around 1000 gold.",
				"Once I achieve a Ranger level of 50 or more, I can upgrade",
				"the attractor if I give <red>Ava <blue>75 steel arrows.");
			break;
		}
	}

	@Override
	public QuestRequirement[] getQuestRequirements(Player player) {
		return new QuestRequirement[]{
			new QuestRequirement(player.getQuestRepository().getQuest(RestlessGhost.NAME), "<red>I must have completed " + RestlessGhost.NAME + "."),
			new QuestRequirement(player.getQuestRepository().getQuest(ErnestTheChicken.NAME), "<red>I must have completed " + ErnestTheChicken.NAME + "."),
			new QuestRequirement(player.getQuestRepository().getQuest(PriestInPeril.NAME), "<red>I must have completed " + PriestInPeril.NAME + "."),
			new QuestRequirement(Skills.RANGE, 30),
			new QuestRequirement(Skills.SLAYER, 18),
			new QuestRequirement(Skills.CRAFTING, 19),
			new QuestRequirement(Skills.WOODCUTTING, 35),
		};
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(avas_device.getId(), 235);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.CRAFTING, 1000, "1000 XP in each of Crafting"),
			new QuestReward(Skills.FLETCHING, 1000, "Fletching and Slayer"),
			new QuestReward(Skills.WOODCUTTING, 2500),
			new QuestReward(avas_device, "Ava's device"),
			new QuestReward(Skills.SLAYER, 1000, ""),
		};
	}

	@Override
	public void finish(Player player) {
		avas_device = player.getSkills().getStaticLevel(Skills.RANGE) >= 50 ? AVAS_ACCUMULATOR : AVAS_ATTRACTOR;
		super.finish(player);
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		if (getStage(player) >= 28 && getStage(player) != 100) {
			return new int[] { 939, 150 };
		}
		int val = stage < 100 && stage > 0 ? 10 : stage >= 100 ? 240 : 0;
		return new int[] { 939, val };
	}
}
