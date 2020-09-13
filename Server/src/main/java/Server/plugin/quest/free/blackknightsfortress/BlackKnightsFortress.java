package plugin.quest.free.blackknightsfortress;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.game.node.item.GroundItemManager;
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
		switch (stage) {
		case 0:
			writeJournal(player,
				"<blue>I can start this quest by speaking to the <red>Sir Amik Varze<blue>at the",
				"<red>White Knights' Castle <blue>in <red>Falador.",
				(player.getQuestRepository().getPoints() < 12 ? "I must" : "<str>I") + " have a total of at least 12 Quest Points",
				"I would have an advantage if I could fight <red>Level 33 Knights",
				"and if I had a smithing level of <red>26.");
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
			writeJournal(player,
				"<str>Sir Amik Varze asked me to investigate the Black Knights'",
				"<str>Fortress. I sneaked inside disguised as a Guard.",
				"<str>I eavesdropped on a Witch and the Black Knight Captain",
				"<str>and discovered that their invincibility potion could be",
				"<str>destroyed with a normal cabbage.",
				"<str>I found a cabbage, and used it to a destroy the potion, then",
				"<str>claimed my reward for a job well done.",
				"",
				"<col=FF0000>QUEST COMPLETE!</col>",
				"<blue>Reward:",
				"<blue>3 Quest Points",
				"<blue>2500 coins");
			break;
		}
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
