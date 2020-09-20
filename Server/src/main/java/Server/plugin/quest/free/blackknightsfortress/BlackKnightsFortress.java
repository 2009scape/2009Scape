package plugin.quest.free.blackknightsfortress;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestRequirement;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the black knights fortress quest.
 * TODO: Cutscene when dropping the cabbage?
 * TODO: The black knights don't attack the player properly when entering the meeting room
 * @author Vexia
 * 
 */
@InitializablePlugin
public final class BlackKnightsFortress extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "Black Knights' Fortress";

	/**
	 * Represents the dossier item.
	 */
	public static final Item DOSSIER = new Item(9589);
	
	/**
	 * Constructs a new {@Code BlackKnightsFortress} {@Code Object}
	 */
	public BlackKnightsFortress() {
		super(
			NAME,
			14,
			13,
			3,
			130, 0, 1, 4
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new BKCabbagePlugin(), new BKFortressPlugin(), new SirAmikVarzeDialogue());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			line = writeJournal(player,
				"<blue>I can start this quest by speaking to the <red>Sir Amik Varze<blue>at the",
				"<red>White Knights' Castle <blue>in <red>Falador.");
			writeJournal(player, line, getQuestRequirementsJournal(player));
			break;
		case 10:
			writeJournal(player,
				"<red>Sir Amik Varze <blue>has asked me to investigate the <red>Black",
				"<red>Knights' Fortress <blue>which is located on <red>Ice Mountain.",
				"<blue>I need to disguise myself to gain entry to the <red>Black",
				"<red>Knights' Fortress.");
			break;
		case 20:
			writeJournal(player,
				"<str>Sir Amik Varze asked me to investigate the Black Knights'",
				"<str>Fortress. I sneaked inside disguised as a Guard.",
				"<blue>I eavesdropped on a Witch and the Black Knight Captain",
				"<blue>and discovered that their invincibility potion can be",
				"<blue>destroyed with a normal <red>cabbage.");
			break;
		case 30:
			writeJournal(player,
				"<str>Sir Amik Varze asked me to investigate the Black Knights'",
				"<str>Fortress. I sneaked inside disguised as a Guard.",
				"<str>I eavesdropped on a Witch and the Black Knight Captain",
				"<str>and discovered that their invincibility potion could be",
				"<str>destroyed with a normal cabbage.",
				"<blue>Now that I have sabotaged the witch's potion, I can claim",
				"<blue>my <red>reward <blue>from <red>Sir Amik Varze <blue>in <red>Falador Castle.");
			break;
		case 100:
			line = writeJournal(player, true,
				"Sir Amik Varze asked me to investigate the Black Knights'",
				"Fortress. I sneaked inside disguised as a Guard.",
				"I eavesdropped on a Witch and the Black Knight Captain",
				"and discovered that their invincibility potion could be",
				"destroyed with a normal cabbage.",
				"I found a cabbage, and used it to a destroy the potion, then",
				"claimed my reward for a job well done.");
			writeJournal(player, ++line,
				"",
				"<col=FF0000>QUEST COMPLETE!</col>",
				"<blue>Reward:",
				"<blue>3 Quest Points",
				"<blue>2500 coins");
			break;
		}
	}

	@Override
	public QuestRequirement[] getQuestRequirements(Player player) {
		return new QuestRequirement[]{
			new QuestRequirement(12, "<str>I", "I must", " have a total of at least 12 Quest Points"),
			new QuestRequirement("I would have an advantage if I could fight <red>Level 33 Knights"),
			new QuestRequirement("and if I had a smithing level of <red>26."),
		};
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(9591, 230);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(new Item(995, 2500)),
		};
	}
}
