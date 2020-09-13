package plugin.quest.members.animalmagnetism;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
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
	 * The requirements messages.
	 */
	private static final String[] REQS = new String[] {
		"I must have completed Restless Ghost.",
		"I must have completed Ernest the Chicken",
		"I must have completed Priest in Peril.",
		"Level 30 Ranged",
		"Level 18 Slayer",
		"Level 19 Crafting",
		"Level 35 Woodcutting"
	};

	/**
	 * The requirements.
	 */
	private boolean[] requirements = new boolean[7];

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

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line = JOURNAL_TEXT_START;
		
		if (stage >= 10 && stage < 100) {
			line = writeJournal(player, line,
				"<red>Ava <blue>has asked me for <red>undead chickens<blue>. One will go toward",
				"<blue>making her bed more comfortable, the other will be used in",
				"<blue>some unexplained reward for me."
			);
		}

		switch (stage) {
		case 0:
			line = writeJournal(player,
				"<blue>I can start this quest by talking to",
				"<red>Ava <blue>who lives in <red>Draynor Manor.",
				"",
				"<blue>Minimum requirements:"
			);
			drawRequirements(player, line);
			break;
		case 10:
			writeJournal(player, line,
				"<blue>I need to find someone who will supply <red>undead chickens <blue>to",
				"<blue>me. Perhaps the <red>farm near Port Phasmatys <blue>sells them...");
			break;
		case 11:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<blue>The<red> ghost farmer<blue> wants me to talk to his <red>wife<blue> for him. I",
				"<blue>need to do this before he will sell chickens.");
			break;
		case 12:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<blue>The<red> ghost farmer<blue> wants me to talk to his <red>wife<blue> for him. I",
				"<blue>need to do this before he will sell chickens.",
				"<blue>The <red>ghost farmer<blue>'s <red>wife <blue>needs to know bank information",
				"<blue>that only the farmer can supply.");
			break;
		case 13:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<blue>The <red>ghost farmer<blue> won't tell me the information his <red>wife <blue>is",
				"<blue>after. Perhaps I should talk to her again.");
			break;
		case 14:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<blue>The <red>ghost farmer<blue>'s <red>wife <blue>still needs to know bank",
				"<blue>information that only the farmer can supply.");
			break;
		case 15:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<blue>I still need to find a way to allow the <red>undead farmer<blue> and his",
				"<red>wife <blue>to communicate with each other.");
			break;
		case 16:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<blue>I should talk to the <red>crone <blue>west of the undead farm and ask",
				"<blue>about <red>ghostspeak amulet<blue>s. Perhaps she can enable the",
				"<red>ghost farmer<blue> to talk to his <red>wife<blue> directly.");
			break;
		case 17:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<blue>I need to talk the <red>crone<blue> while I have a <red>ghostspeak",
				"<red>amulet <blue>so that she can create a new amulet specifically",
				"<blue>for the <red>ghost farmer.");
			break;
		case 18:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<blue>I should give the <red>ghost farmer <blue>a <red>crone-made amulet <blue>so",
				"<blue>that he can talk directly to his <red>wife.");
			break;
		case 19:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<blue>The <red>farmer <blue>seems friendlier now; I need to talk to him",
				"<blue>about the <red>undead chickens.");
			break;
		case 20:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<blue>The <red>ghost farmer<blue> caught some chickens; now I need to buy",
				"<blue>2 from him and deliver them to Ava.");
			break;
		case 25:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<blue>I need to talk to the <red>Witch <blue>in <red>Draynor Manor <blue>about",
				"<red>magically attuned magnets<blue>. Apparently, the <red>undead",
				"<red>chicken <blue>will be using magnets in my reward.");
			break;
		case 26:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward",
				"<blue>I need to deliver 5 <red>iron bars <blue>to the <red>Witch <blue>in <red>Draynor Manor.",
				"<blue>She will select one most suitable for both magnetising and",
				"mystical use.");
			break;
		case 27:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward",
				"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
				"<str>She will select one most suitable for both magnetising and",
				"<str>mystical use.",
				"<blue>I need to make a <red>magnet <blue>by hammering the <red>selected iron",
				"<blue>bar while facing north in <red>Rimmington mines. <blue>I then need",
				"<blue>to pass this magnet to <red>Ava.");
			break;
		case 28:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward",
				"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
				"<str>She will select one most suitable for both magnetising and",
				"<str>mystical use.",
				"<str>I need to make a magnet by hammering the selected iron",
				"<str>bar while facing north in Rimmington mines. I then need",
				"<str>to pass this magnet to Ava.",
				"<blue>I need to chop some wood from the <red>undead trees <blue>near",
				"<red>Draynor Manor. Ava <blue>can use this wood as a source of",
				"<blue>unending arrow shafts in my reward. She suggested that I",
				"<blue>use a Woodcutting axe made of nothing less powerful than",
				"<blue>mithril.");
			break;
		case 29:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward",
				"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
				"<str>She will select one most suitable for both magnetising and",
				"<str>mystical use.",
				"<str>I need to make a magnet by hammering the selected iron",
				"<str>bar while facing north in Rimmington mines. I then need",
				"<str>to pass this magnet to Ava.",
				"<blue>I need to find some way of chopping the <red>undead trees",
				"<blue>near <red>Draynor Manor <blue>so that <red>Ava <blue>can use this wood as a",
				"<blue>source of unending arrow shafts.",
				"<blue>Perhaps <red>Ava<blue> could give me some advice...");
			break;
		case 30:
		case 31:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward",
				"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
				"<str>She will select one most suitable for both magnetising and",
				"<str>mystical use.",
				"<str>I need to make a magnet by hammering the selected iron",
				"<str>bar while facing north in Rimmington mines. I then need",
				"<str>to pass this magnet to Ava.",
				"<str>I need to find some way of chopping the undead trees",
				"<str>near Draynor Manor so that Ava can use this wood as a",
				"<str>source of unending arrow shafts.",
				"<str>Ava suspects that Turael, the Slayer Master in Burthorpe,",
				"<str>might be able to help.",
				"<blue>I need to collect a <red>holy symbol of Saradomin<blue> and a <red>mithril",
				"<red>axe. Turael<blue>, the Burthorpe Slayer, can use these to",
				"<blue> construct a new axe for my undead tree cutting.");
			break;
		case 32:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward",
				"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
				"<str>She will select one most suitable for both magnetising and",
				"<str>mystical use.",
				"<str>I need to make a magnet by hammering the selected iron",
				"<str>bar while facing north in Rimmington mines. I then need",
				"<str>to pass this magnet to Ava.",
				"<str>I need to find some way of chopping the undead trees",
				"<str>near Draynor Manor so that Ava can use this wood as a",
				"<str>source of unending arrow shafts.",
				"<str>Ava suspects that Turael, the Slayer Master in Burthorpe,",
				"<str>might be able to help.",
				"<str>I need to collect a holy symbol of Saradomin and a mithril",
				"<str>axe. Turael, the Burthorpe Slayer, can use these to",
				"<str> construct a new axe for my undead tree cutting.",
				"<str>I need to chop some undead wood with the silver-edged",
				"<str>mithril axe. Then Ava will want the wood for constructing",
				"<str>my reward.",
				"<blue>I should ask <red>Ava <blue>for the <red>garbled research notes <blue>that she",
				"<blue>cannot translate. When translated, these notes will tell",
				"<blue>her how to combine the <red>undead wood, undead chicken <blue>and",
				"<red>magnet <blue>into some bizarre device.");
			break;
		case 33:
			if (!player.hasItem(TRANSLATED_NOTES)) {
				writeJournal(player, line,
					"<str>I need to find someone who will supply undead chickens to",
					"<str>me. Perhaps the farm near Port Phasmatys sells them...",
					"<str>The ghost farmer wants me to talk to his wife for him. I",
					"<str>need to do this before he will sell chickens.",
					"<str>I should talk to the crone west of the undead farm and ask",
					"<str>about ghostspeak amulets. Perhaps she can enable the",
					"<str>ghost farmer to talk to his wife directly.",
					"<str>I need to talk the crone while I have a ghostspeak",
					"<str>amulet so that she can create a new amulet specifically",
					"<str>for the ghost farmer.",
					"<str>I should give the ghost farmer a crone-made amulet so",
					"<str>that he can talk directly to his wife.",
					"<str>The farmer seems friendlier now; I need to talk to him",
					"<str>about the undead chickens.",
					"<str>The farmer has agreed to sell chickens; now he needs to",
					"<str>catch one for me.",
					"<str>The ghost farmer caught some chickens; now I need to buy",
					"<str>2 from him and deliver them to Ava.",
					"<str>I need to talk to the Witch in Draynor Manor about",
					"<str>magically attuned magnets. Apparently, the undead",
					"<str>chicken will be using magnets in my reward",
					"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
					"<str>She will select one most suitable for both magnetising and",
					"<str>mystical use.",
					"<str>I need to make a magnet by hammering the selected iron",
					"<str>bar while facing north in Rimmington mines. I then need",
					"<str>to pass this magnet to Ava.",
					"<str>I need to find some way of chopping the undead trees",
					"<str>near Draynor Manor so that Ava can use this wood as a",
					"<str>source of unending arrow shafts.",
					"<str>Ava suspects that Turael, the Slayer Master in Burthorpe,",
					"<str>might be able to help.",
					"<str>I need to collect a holy symbol of Saradomin and a mithril",
					"<str>axe. Turael, the Burthorpe Slayer, can use these to",
					"<str> construct a new axe for my undead tree cutting.",
					"<str>I need to chop some undead wood with the silver-edged",
					"<str>mithril axe. Then Ava will want the wood for constructing",
					"<str>my reward.",
					"<str>I should ask Ava for the garbled research notes that she",
					"<str>cannot translate. When translated, these notes will tell",
					"<str>her how to combine the undead wood, undead chicken and",
					"<str>magnet into some bizarre device.",
					"<blue>The <red>research notes <blue>must be translated. I should try to",
					"<blue>decipher them even though they look like total gibberish.",
					"<blue>to me.");
			} else {
				writeJournal(player, line,
					"<str>I need to find someone who will supply undead chickens to",
					"<str>me. Perhaps the farm near Port Phasmatys sells them...",
					"<str>The ghost farmer wants me to talk to his wife for him. I",
					"<str>need to do this before he will sell chickens.",
					"<str>I should talk to the crone west of the undead farm and ask",
					"<str>about ghostspeak amulets. Perhaps she can enable the",
					"<str>ghost farmer to talk to his wife directly.",
					"<str>I need to talk the crone while I have a ghostspeak",
					"<str>amulet so that she can create a new amulet specifically",
					"<str>for the ghost farmer.",
					"<str>I should give the ghost farmer a crone-made amulet so",
					"<str>that he can talk directly to his wife.",
					"<str>The farmer seems friendlier now; I need to talk to him",
					"<str>about the undead chickens.",
					"<str>The farmer has agreed to sell chickens; now he needs to",
					"<str>catch one for me.",
					"<str>The ghost farmer caught some chickens; now I need to buy",
					"<str>2 from him and deliver them to Ava.",
					"<str>I need to talk to the Witch in Draynor Manor about",
					"<str>magically attuned magnets. Apparently, the undead",
					"<str>chicken will be using magnets in my reward",
					"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
					"<str>She will select one most suitable for both magnetising and",
					"<str>mystical use.",
					"<str>I need to make a magnet by hammering the selected iron",
					"<str>bar while facing north in Rimmington mines. I then need",
					"<str>to pass this magnet to Ava.",
					"<str>I need to find some way of chopping the undead trees",
					"<str>near Draynor Manor so that Ava can use this wood as a",
					"<str>source of unending arrow shafts.",
					"<str>Ava suspects that Turael, the Slayer Master in Burthorpe,",
					"<str>might be able to help.",
					"<str>I need to collect a holy symbol of Saradomin and a mithril",
					"<str>axe. Turael, the Burthorpe Slayer, can use these to",
					"<str> construct a new axe for my undead tree cutting.",
					"<str>I need to chop some undead wood with the silver-edged",
					"<str>mithril axe. Then Ava will want the wood for constructing",
					"<str>my reward.",
					"<str>I should ask Ava for the garbled research notes that she",
					"<str>cannot translate. When translated, these notes will tell",
					"<str>her how to combine the undead wood, undead chicken and",
					"<str>magnet into some bizarre device.",
					"<str>The research notes must be translated. I should try to",
					"<str>decipher them even though they look like total gibberish.",
					"<str>to me.",
					"<blue>The notes look less confusing now. <red>Ava <blue>will want to see",
					"<blue>these <red>translated research notes.");
			}
			break;
		case 34:
			writeJournal(player, line,
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens; now I need to buy",
				"<str>2 from him and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward",
				"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
				"<str>She will select one most suitable for both magnetising and",
				"<str>mystical use.",
				"<str>I need to make a magnet by hammering the selected iron",
				"<str>bar while facing north in Rimmington mines. I then need",
				"<str>to pass this magnet to Ava.",
				"<str>I need to find some way of chopping the undead trees",
				"<str>near Draynor Manor so that Ava can use this wood as a",
				"<str>source of unending arrow shafts.",
				"<str>Ava suspects that Turael, the Slayer Master in Burthorpe,",
				"<str>might be able to help.",
				"<str>I need to collect a holy symbol of Saradomin and a mithril",
				"<str>axe. Turael, the Burthorpe Slayer, can use these to",
				"<str> construct a new axe for my undead tree cutting.",
				"<str>I need to chop some undead wood with the silver-edged",
				"<str>mithril axe. Then Ava will want the wood for constructing",
				"<str>my reward.",
				"<str>I should ask Ava for the garbled research notes that she",
				"<str>cannot translate. When translated, these notes will tell",
				"<str>her how to combine the undead wood, undead chicken and",
				"<str>magnet into some bizarre device.",
				"<str>The research notes must be translated. I should try to",
				"<str>decipher them even though they look like total gibberish.",
				"<str>to me.",
				"<str>The notes look less confusing now. Ava will want to see",
				"<str>these translated research notes.",
				"<blue>Almost finished! I must combine the <red>pattern <blue>which Ava",
				"<blue>gave to me with some <red>polished buttons <blue>and a bit of <red>hard",
				"<red>leather. Ava <blue>tells me that the <red>H.A.M hideout <blue>is a good",
				"place to obtain <red>buttons.");
			break;
		case 100:
			writeJournal(player,
				"<str>Ava has asked me for undead chickens. One will go",
				"<str>towards making her bed more comfortable, the other will",
				"<str>be used in some unexplained reward for me.",
				"",
				"<str>I need to find someone who will supply undead chickens to",
				"<str>me. Perhaps the farm near Port Phasmatys sells them...",
				"<str>The ghost farmer wants me to talk to his wife for him. I",
				"<str>need to do this before he will sell the chickens.",
				"<str>I should talk to the crone west of the undead farm and ask",
				"<str>about ghostspeak amulets. Perhaps she can enable the",
				"<str>ghost farmer to talk to his wife directly.",
				"<str>I need to talk to the crone while I have a ghostspeak",
				"<str>amulet so that she can create a new amulet specifically",
				"<str>for the ghost farmer.",
				"<str>I should give the ghost farmer a crone-made amulet so",
				"<str>that he can talk directly to his wife.",
				"<str>The farmer seems friendlier now; I need to talk to him",
				"<str>about the undead chickens.",
				"<str>The farmer has agreed to sell chickens; now he needs to",
				"<str>catch one for me.",
				"<str>The ghost farmer caught some chickens;now I need to buy",
				"<str>2 and deliver them to Ava.",
				"<str>I need to talk to the Witch in Draynor Manor about",
				"<str>magically attuned magnets. Apparently, the undead",
				"<str>chicken will be using magnets in my reward.",
				"<str>I need to deliver 5 iron bars to the Witch in Draynor Manor.",
				"<str>She will select one most suitable for both magnetising and",
				"<str>mystical use.",
				"<str>I need to make a magnet by hammering the selected iron",
				"<str>bar while facing north in Rimmington mines. I then need",
				"<str>to pass this magnet to Ava.",
				"<str>I need to find some way of chopping the undead trees",
				"<str>near Draynor manor so that Ava can use this wood as a",
				"<str>source of unending arrow shafts.",
				"<str>Ava suspects that Turael, the Slayer Master in Burthorpe,",
				"<str>might be able to help.",
				"<str>I need to collect a holy symbol of Saradomin and a mithril",
				"<str>axe. Turael, the Burthorpe Slayer, can use these to",
				"<str>construct a new axe for my undead tree cutting.",
				"<str>I need to chop some undead wood with the silver edged",
				"<str>mithril axe. Then Ava will want the wood for constructing",
				"<str>my reward.",
				"<str>I should ask Ava for the garbled research notes that she",
				"<str>cannot translate. When translated, these notes will tell",
				"<str>her how to combine the undead wood, undead chicken and",
				"<str>magnet into some bizarre device.",
				"<str>The research notes must be translated. I should try to",
				"<str>decipher them even though they look like total gibberish",
				"<str>to me.",
				"<str>The notes look less confusing now. Ava will want to see",
				"<str>these translated research notes.",
				"<str>Almost finished!I must combine the pattern which Ava",
				"<str>gave to me with some polished buttons and a bit of hard",
				"<str>leather.",
				"<str>Ava wants the completed container. She can then combine",
				"<str>it with the undead chicken, undead wood and magnet.",
				"",
				"<col=FF0000>QUEST COMPLETE!",
				"<red>Ava's reward for me is an arrow attracting and creating",
				"<red>backpack.",
				"<blue>The method is this: the <red>undead chicken<blue> can attract lost,",
				"<blue>stray arrowheads with a magnet, add wood from the",
				"<blue>undead twigs and then finish the arrows using its own",
				"<blue>feathers. This will give me an unending source of arrows.",
				"<blue>The cunning bird will also attract some of the arrows which I",
				"<blue>have fired, preventing these arrows from falling upon the",
				"<blue>ground.",
				"<blue>If I lost my device, I can talk to <red>Ava<blue> for a new one,",
				"<blue>although it will cost me around 1000 gold.",
				"<blue>Once I achieve a Ranger level of 50 or more, I can upgrade",
				"<blue>the attractor if I give <red>Ava <blue>75 steel arrows.");
			break;
		}
	}

	/**
	 * Draws the requirements.
	 */
	private void drawRequirements(Player player, int line) {
		hasRequirements(player);
		for (int i = 0; i < requirements.length; i++) {
			writeJournal(player, line++, (requirements[i] ? "<str>" : "<red>") + REQS[i]);
		}
	}

	@Override
	public boolean hasRequirements(Player player) {
		requirements[0] = player.getQuestRepository().isComplete("The Restless Ghost");
		requirements[1] = player.getQuestRepository().isComplete("Ernest the Chicken");
		requirements[2] = player.getQuestRepository().isComplete(PriestInPeril.NAME);
		requirements[3] = player.getSkills().getStaticLevel(Skills.RANGE) >= 30;
		requirements[4] = player.getSkills().getStaticLevel(Skills.SLAYER) >= 18;
		requirements[5] = player.getSkills().getStaticLevel(Skills.CRAFTING) >= 19;
		requirements[6] = player.getSkills().getStaticLevel(Skills.WOODCUTTING) >= 35;
		for (boolean bool : requirements) {
			if (!bool) {
				return false;
			}
		}
		return true;
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
