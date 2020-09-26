package plugin.quest.members.dwarfcannon;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;
import plugin.skill.Skills;

/**
 * Represents the dwarf cannon quest.
 * @author Vexia
 */
@InitializablePlugin
public class DwarfCannon extends Quest {

	/**
	 * The name of this quest.
	 */
	public static final String NAME = "Dwarf Cannon";

	/**
	 * The dwarf remains item.
	 */
	public static final Item DWARF_REMAINS = new Item(0);

	/**
	 * The toolkit item.
	 */
	public static final Item TOOLKIT = new Item(1);

	/**
	 * The nulodion notes.
	 */
	public static final Item NULODION_NOTES = new Item(3);

	/**
	 * The mould item.
	 */
	public static final Item MOULD = new Item(4);

	/**
	 * Constructs a new {@Code DwarfCannon} {@Code Object}
	 */
	public DwarfCannon() {
		super(
			NAME,
			49,
			48,
			1
		);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new LollkDialogue());
		PluginManager.definePlugin(new NulodionDialogue());
		PluginManager.definePlugin(new CaptainLawgofNPC());
		PluginManager.definePlugin(new CannonBallPlugin());
		PluginManager.definePlugin(new CaptainLawgofDialogue());
		PluginManager.definePlugin(new DwarfCannonPlugin());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				"<blue>I can start this quest by speaking to <red>Lawgof the Dwarven",
				"<red>Captain of the Black Watch <blue>, he is defending an area",
				"<red>North-west of the Fishing Guild <blue>against <red>goblin <blue>attack.");
			break;
		case 10:
			line = writeJournal(player, true,
				"I have spoken to Captain Lawgof, he recruited me into the",
				"Black Guard and asked me to help the dwarves.",
				"");
			if (player.getConfigManager().get(1) == 2016) {
				writeJournal(player, line,
					"<str>I have repaired all the broken railings,",
					"<blue>I should report back to <red>Captain Lawgof."
				);
			} else {
				writeJournal(player, line,
					"<blue>My first task is to <red>fix the broken railings",
					"<blue>in the dwarves defensive perimeter."
				);
			}
			break;
		case 20:
			line = writeJournal(player, true,
				"I have spoken to Captain Lawgof, he recruited me into the",
				"Black Guard and asked me to help the dwarves.",
				""
			);
			if (player.hasItem(DWARF_REMAINS)) {
				writeJournal(player, line,
					"<str>I went to the watchtower where I found the remains of",
					"<str>Gilob.",
					"<blue>I should take them back to <red>Captain Lawgof.");
			} else {
				writeJournal(player, line,
					"<str>I have repaired all the broken railings,",
					"<blue>Captain Lawgof has asked me to check up on his guards at",
					"<red>the watchtower <blue>to the South of this camp.");
			}
			break;
		case 30:
			writeJournal(player,
				"<str>I have spoken to Captain Lawgof, he recruited me into the",
				"<str>Black Guard and asked me to help the dwarves.",
				"",
				"<str>I gave the remains to Captain Lawgof.",
				"<blue>he sent me to find the <red>Goblin base<blue>, South-east of the",
				"<blue>camp.");
			break;
		case 40:
			writeJournal(player,
				"<str>I have spoken to Captain Lawgof, he recruited me into the",
				"<str>Black Guard and asked me to help the dwarves.",
				"",
				"<str>I have rescued Lollk and sent him back to the Captain.",
				"<blue>I need to <red>speak to Captain Lawgof <blue>again.");
			break;
		case 50:
			writeJournal(player,
				"<str>I have spoken to Captain Lawgof, he recruited me into the",
				"<str>Black Guard and asked me to help the dwarves.",
				"",
				"<blue>Captain Lawgof has asked me to <red>fix the multicannon.");
			break;
		case 60:
			writeJournal(player,
				"<str>I have spoken to Captain Lawgof, he recruited me into the",
				"<str>Black Guard and asked me to help the dwarves.",
				"",
				"<str>I've fixed the broken multicannon,",
				"<blue>I need to <red>speak to Captain Lawgof <blue>again.");
			break;
		case 70:
			writeJournal(player,
				"<str>I have spoken to Captain Lawgof, he recruited me into the",
				"<str>Black Guard and asked me to help the dwarves.",
				"",
				"<blue>Captain Lawgof asked me to find <red>Nulodion the Engineer<blue>, he",
				"<blue>needs to know what <red>ammunition the multicannon <blue>fires.");
			break;
		case 80:
			writeJournal(player,
				"<str>I have spoken to Captain Lawgof, he recruited me into the",
				"<str>Black Guard and asked me to help the dwarves.",
				"",
				"<str>I've spoken to Nulodion,",
				"<str>He gave me an ammo mould and notes",
				"<blue>I need to <red>speak to Captain Lawgof <blue>again.");
			break;
		case 100:
			writeJournal(player,
				"<str>I have spoken to Captain Lawgof, he recruited me into the",
				"<str>Black Guard and asked me to help the dwarves.",
				"",
				"<str>I've helped the dwarves keep out the Goblins, and found",
				"<str>the remains of their friend.",
				"<str>I fixed the cannon and got a mould for Captain Lawgof.",
				"<blue>I can now <red>buy a multicannon <blue>from <red>Nulodion <blue>as a reward.",
				"",
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public void start(Player player) {
		super.start(player);
		player.getConfigManager().set(0, 1);
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		int val = 0;
		if (stage >= 100) {
			val = 11;
		} else if (stage > 0) {
			val = player.getConfigManager().get(0);
		}
		return new int[] { 0, val };
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(TOOLKIT.getId(), 235);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(Skills.CRAFTING, 750),
			new QuestReward("Permission to purchase and"),
			new QuestReward("use the Dwarf Multicannon"),
		};
	}

}
