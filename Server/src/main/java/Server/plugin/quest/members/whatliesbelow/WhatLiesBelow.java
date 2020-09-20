package plugin.quest.members.whatliesbelow;

import core.game.node.entity.player.link.quest.QuestRequirement;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.quest.free.RuneMysteries;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * The what lies below quest.
 * @author Vexa
 * 
 */
@InitializablePlugin
public class WhatLiesBelow extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "What Lies Below";

	/**
	 * The empty folder item.
	 */
	public static final Item EMPTY_FOLDER = new Item(11003);

	/**
	 * The rats paper item.
	 */
	public static final Item RATS_PAPER = new Item(11008);

	/**
	 * The used folder.
	 */
	public static final Item USED_FOLDER = new Item(11006);

	/**
	 * The full folder.
	 */
	public static final Item FULL_FOLDER = new Item(11007);

	/**
	 * The rats letter.
	 */
	public static final Item RATS_LETTER = new Item(11009);

	/**
	 * The sin keth diary.
	 */
	public static final Item SIN_KETH_DIARY = new Item(11002);

	/**
	 * The wand item.
	 */
	public static final Item WAND = new Item(11012);

	/**
	 * The infused item.
	 */
	public static final Item INFUSED_WAND = new Item(11013);

	/**
	 * The bowl item.
	 */
	public static final Item BOWL = new Item(1923);

	/**
	 * The suroks letter.
	 */
	public static final Item SUROKS_LETTER = new Item(11010);

	/**
	 * The bacon ring.
	 */
	public static final Item BEACON_RING = new Item(11014);

	@Override
	public QuestRequirement[] getQuestRequirements(Player player) {
		return new QuestRequirement[]{
			new QuestRequirement(Skills.RUNECRAFTING, 35, "<blue>Have level 35 <red>Runecrafting."),
			new QuestRequirement("<blue>Be able to defeat a <red>level 47 enemy."),
			new QuestRequirement(player.getQuestRepository().getQuest(RuneMysteries.NAME), "<blue>I need to have completed the <red>Rune Mysteries <blue>quest."),
			new QuestRequirement(Skills.MINING, 42, "<blue>Have a <red>Mining <blue>level of 42 to use the <red>Chaos Tunnel."),
		};
	}

	/**
	 * Constructs a new {@Code WhatLiesBelow} {@Code Object}
	 */
	public WhatLiesBelow() {
		super(
			NAME,
			136,
			135,
			1
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new WLBelowPlugin());
		return this;
	}
	
	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			int line = writeJournal(player,
				"<blue>I can start this quest by speaking to <red>Rat Burgiss <blue>on the",
				"<blue>road south of <red>Varrock.",
				"<blue>Before I begin I will need to:");
			writeJournal(player, line, getQuestRequirementsJournal(player));
			break;
		case 10:
			writeJournal(player,
				"<red>Rat<blue>, a trader in Varrock, has asked me to help him with a task.",
				"<blue>I need to kill <red>outlaws<blue> west of Varrock so that I can collect 5 of ",
				"<blue>Rat's <red>papers<blue>.");
			break;
		case 20:
			writeJournal(player,
				"<red>Rat<blue>, a trader in Varrock, has asked me to help him with a task.",
				"<str>I need to kill outlaws west of Varrock so that I can collect",
				"<str>5 of Rat's papers.I have delivered Rat's folder to him. Perhaps I",
				"<str>should speak to him again.",
				"<blue>I need to deliver <red>Rat's<blue> letter to <red>Surok Magis",
				"<blue>in <red>Varrock.");
			break;
		case 30:
		case 40:
			writeJournal(player,
				"<str>Rat, a trader in Varrock, has asked me to help him with a task.",
				"<str>Surok, a Wizard in Varrock, has asked me to complete a task for him.",
				"<str>I need to kill the outlaws west of Varrock so that I can collect",
				"<str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I",
				"<str>should speak to him again.<str>I need to deliver Rat's letter to ",
				"<str>Surok Magis in Varrock. <str>I need to talk to Surok about the",
				"<str>secret he has for me.",
				"<blue>I need to infuse the <red>metal wand <blue>with <red>chaos runes <blue>at the <red>Chaos Altar<blue>.",
				"<blue>I also need<blue>to find or buy an empty <red>bowl.");
			break;
		case 50:
			writeJournal(player,
				"<str>Rat, a trader in Varrock, has asked me to help him with a task.",
				"<str>Surok, a Wizard in Varrock, has asked me to complete a task for him.",
				"<str>I need to kill the outlaws west of Varrock so that I can collect",
				"<str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I",
				"<str>should speak to him again.<str>I need to deliver Rat's letter to ",
				"<str>Surok Magis in Varrock. <str>I need to talk to Surok about the",
				"<str>secret he has for me.",
				"<str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.",
				"<str>I also need<str> to find or buy an empty <str>bowl.",
				"<str>I need to infuse the metal wand with chaos runes at the Chaos Altar.",
				"<str>I also need to find or buy an empty bowl.",
				"<str>I need to take the glowing wand I have created back to Surok in Varrock",
				"<str>with an empty bowl.",
				"<str>I need to deliver Surok's letter to Rat who is waiting for me south",
				"<blue>of Varrock. <blue>I should speak to <red>Rat<blue> again; he is waiting for me ",
				"<blue>south of Varrock");
			break;
		case 60:
			writeJournal(player,
				"<str>Rat, a trader in Varrock, has asked me to help him with a task.",
				"<str>Surok, a Wizard in Varrock, has asked me to complete a task for him.",
				"<str>I need to kill the outlaws west of Varrock so that I can collect",
				"<str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I",
				"<str>should speak to him again.<str>I need to deliver Rat's letter to ",
				"<str>Surok Magis in Varrock. <str>I need to talk to Surok about the",
				"<str>secret he has for me.",
				"<str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.",
				"<str>I also need<str> to find or buy an empty <str>bowl.",
				"<str>I need to infuse the metal wand with chaos runes at the Chaos Altar.",
				"<str>I also need to find or buy an empty bowl.",
				"<str>I need to take the glowing wand I have created back to Surok in Varrock",
				"<str>with an empty bowl.",
				"<str>I need to deliver Surok's letter to Rat who is waiting for me south",
				"<str>of Varrock.<str>I should speak to Rat again; he is waiting for me ",
				"<str>south of Varrock",
				"<blue>I need to speak to <red>Zaff <blue>of <red>Zaff's Staffs <blue>in Varrock.");
			break;
		case 70:
			writeJournal(player,
				"<str>Rat, a trader in Varrock, has asked me to help him with a task.",
				"<str>Surok, a Wizard in Varrock, has asked me to complete a task for him.",
				"<str>I need to kill the outlaws west of Varrock so that I can collect",
				"<str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I",
				"<str>should speak to him again.<str>I need to deliver Rat's letter to ",
				"<str>Surok Magis in Varrock. <str>I need to talk to Surok about the",
				"<str>secret he has for me.",
				"<str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.",
				"<str>I also need<str> to find or buy an empty <str>bowl.",
				"<str>I need to infuse the metal wand with chaos runes at the Chaos Altar.",
				"<str>I also need to find or buy an empty bowl.",
				"<str>I need to take the glowing wand I have created back to Surok in Varrock",
				"<str>with an empty bowl.",
				"<str>I need to deliver Surok's letter to Rat who is waiting for me south",
				"<str>of Varrock.<str>I should speak to Rat again; he is waiting for me ",
				"<str>south of Varrock",
				"<str>I need to speak to Zaff of Zaff's Staffs in Varrock.",
				"<blue>I need to tell <red>Surok <blue>in Varrock that he is under arrest.");
			break;
		case 80:
		case 90:
			writeJournal(player,
				"<str>Rat, a trader in Varrock, has asked me to help him with a task.",
				"<str>Surok, a Wizard in Varrock, has asked me to complete a task for him.",
				"<str>I need to kill the outlaws west of Varrock so that I can collect",
				"<str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I",
				"<str>should speak to him again.<str>I need to deliver Rat's letter to ",
				"<str>Surok Magis in Varrock. <str>I need to talk to Surok about the",
				"<str>secret he has for me.",
				"<str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.",
				"<str>I also need<str> to find or buy an empty <str>bowl.",
				"<str>I need to infuse the metal wand with chaos runes at the Chaos Altar.",
				"<str>I also need to find or buy an empty bowl.",
				"<str>I need to take the glowing wand I have created back to Surok in Varrock",
				"<str>with an empty bowl.",
				"<str>I need to deliver Surok's letter to Rat who is waiting for me south",
				"<str>of Varrock.<str>I should speak to Rat again; he is waiting for me ",
				"<str>south of Varrock",
				"<str>I need to speak to Zaff of Zaff's Staffs in Varrock.",
				"<str>I need to tell Surok in Varrock that he is under arrest.",
				"<str>I need to defeat King Roald in Varrock so that Zaff can remove the",
				"<str>mind-control spell.",
				"<blue>I need to tell <red>Rat <blue>what has happened; he is waiting for me",
				"<blue>south of Varrock.");
			break;
		case 100:
			writeJournal(player,
				"<str>Rat, a trader in Varrock, has asked me to help him with a task.",
				"<str>Surok, a Wizard in Varrock, has asked me to complete a task for him.",
				"<str>I need to kill the outlaws west of Varrock so that I can collect",
				"<str>5 of Rat's papers.<str>I have delivered Rat's folder to him. Perhaps I",
				"<str>should speak to him again.<str>I need to deliver Rat's letter to ",
				"<str>Surok Magis in Varrock. <str>I need to talk to Surok about the",
				"<str>secret he has for me.",
				"<str>I need to infuse the <str>metal wand <str>with chaos runes <str>at the <str>Chaos Altar<str>.",
				"<str>I also need<str> to find or buy an empty <str>bowl.",
				"<str>I need to infuse the metal wand with chaos runes at the Chaos Altar.",
				"<str>I also need to find or buy an empty bowl.",
				"<str>I need to take the glowing wand I have created back to Surok in Varrock",
				"<str>with an empty bowl.",
				"<str>I need to deliver Surok's letter to Rat who is waiting for me south",
				"<str>of Varrock.<str>I should speak to Rat again; he is waiting for me ",
				"<str>south of Varrock",
				"<str>I need to speak to Zaff of Zaff's Staffs in Varrock.",
				"<str>I need to tell Surok in Varrock that he is under arrest.",
				"<str>I need to defeat King Roald in Varrock so that Zaff can remove the",
				"<str>mind-control spell.",
				"<str>I need to tell Rat what has happened; he is waiting for me",
				"<str>south of Varrock.",
				"",
				"<col=FF0000>QUEST COMPLETE!",
				"<blue>I have been given information about the <red>Chaos Tunnel<blue>.",
				"<blue>Zaff has given me the <red>Beacon Ring<blue>.");
			break;
		}
	}

	@Override
	public void start(Player player) {
		super.start(player);
		player.getInventory().add(EMPTY_FOLDER, player);
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(BEACON_RING.getId(), 235);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(Skills.RUNECRAFTING, 8000),
			new QuestReward(Skills.DEFENCE, 2000),
			new QuestReward("Beacon Ring"),
			new QuestReward("Knowledge of Chaos Tunnel"),
		};
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		int id = 992;
		if (stage >= 40 && stage != 100) {
			return new int[] { id, (1 << 8) + 1 };
		}
		if (stage == 0) {
			return new int[] { id, 0 };
		} else if (stage > 0 && stage < 100) {
			return new int[] { id, 1 };
		}
		player.getConfigManager().set(1181, (1 << 8) + (1 << 9), true);
		return new int[] { id, 502 };
	}
}
