package plugin.quest.members.waterfallquest;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
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

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line = 12;
		switch (stage) {
		case 0:
			writeJournal(player, line,
				"<blue>I can start this quest by speaking to <red>Almera<blue> in her house",
				"<blue> next to the <red>Baxtorian Falls.",
				"",
				"<blue>I need to be able to fight <red>Level 84 Giants.");
			break;
		case 10:
			writeJournal(player, line,
				"<blue>I spoke to <red>Almera<blue> in a house close to the Baxtorian",
				"<blue>waterfall. Her son was missing so I offered to help find",
				"<blue>him. The boy, <red>Hudon's<blue> looking for treasure in the waterfall.");
			break;
		case 20:
			writeJournal(player, line,
				"<str>I spoke to Almera in a house close to the Baxtorian",
				"<str>waterfall. Her son was missing so I offered to help find",
				"<str>him. The boy, Hudon's looking for treasure in the waterfall.",
				"",
				"<blue>I found Hudon a short raft ride down the river. He is",
				"<blue>convinced there is treasure here somewhere. Maybe I",
				"<blue>need to do a little research.");
			break;
		case 30:
			if (player.getInventory().containsItem(new Item(294, 1))) {
				writeJournal(player, line,
					"<str>I spoke to Almera in a house close to the Baxtorian",
					"<str>waterfall. Her son was missing so I offered to help find",
					"<str>him. The boy, Hudon's looking for treasure in the waterfall.",
					"",
					"<str>I found Hudon a short raft ride down the river. He is",
					"<str>convinced there is treasure here somewhere. Maybe I",
					"<str>need to do a little research.",
					"",
					"<str>I found a book in the tourist office about Baxtorian. The",
					"<str>book tells of a sad love story about 2 elf lovers. It ends",
					"<str>with Baxtorian withdrawing to his home under the waterfall",
					"<str>after his wife dies. It is told that only Glarial could enter",
					"<str>their home.",
					"",
					"<blue>The book also mentions <red>Glarial's tomb<blue> and a pebble, it",
					"<blue>appears that the pebble is used to enter the tomb.",
					"<str>From what I understand Glarial's pebble was hidden in a ",
					"<str> cave under the Tree Gnome Village by Golrie's ancestors."
				);
			} else {
				writeJournal(player, line,
					"<str>I spoke to Almera in a house close to the Baxtorian",
					"<str>waterfall. Her son was missing so I offered to help find",
					"<str>him. The boy, Hudon's looking for treasure in the waterfall.",
					"",
					"<str>I found Hudon a short raft ride down the river. He is",
					"<str>convinced there is treasure here somewhere. Maybe I",
					"<str>need to do a little research.",
					"",
					"<str>I found a book in the tourist office about Baxtorian. The",
					"<str>book tells of a sad love story about 2 elf lovers. It ends",
					"<str>with Baxtorian withdrawing to his home under the waterfall",
					"<str>after his wife dies. It is told that only Glarial could enter",
					"<str>their home.",
					"",
					"<blue>The book also mentions <red>Glarial's tomb<blue> and a pebble, it",
					"<blue>appears that the pebble is used to enter the tomb.",
					"<blue>From what I understand <red>Glarial's pebble<blue> was hidden in a ",
					"<blue> cave under the <red>Tree Gnome Village<blue> by<red> Golrie's<blue> ancestors."
				);
			}
			if (player.getInventory().containsItem(new Item(295, 1)) || player.getEquipment().containsOneItem(295)) {
				writeJournal(player, line,
					"<str>I spoke to Almera in a house close to the Baxtorian",
					"<str>waterfall. Her son was missing so I offered to help find",
					"<str>him. The boy, Hudon's looking for treasure in the waterfall.",
					"",
					"<str>I found Hudon a short raft ride down the river. He is",
					"<str>convinced there is treasure here somewhere. Maybe I",
					"<str>need to do a little research.",
					"",
					"<str>I found a book in the tourist office about Baxtorian. The",
					"<str>book tells of a sad love story about 2 elf lovers. It ends",
					"<str>with Baxtorian withdrawing to his home under the waterfall",
					"<str>after his wife dies. It is told that only Glarial could enter",
					"<str>their home.",
					"",
					"<str>The book also mentions Glarial's tomb and a pebble, it",
					"<str>appears that the pebble is used to enter the tomb.",
					"<str>From what I understand Glarial's pebble was hidden in a ",
					"<str> cave under the Tree Gnome Village by Golrie's ancestors.",
					"",
					"<blue>Inside the tomb I found Glarial's amulet and ashes."
				);
			}
			if (player.getLocation().getY() >= 9902) {
				writeJournal(player, line,
					"<str>I spoke to Almera in a house close to the Baxtorian",
					"<str>waterfall. Her son was missing so I offered to help find",
					"<str>him. The boy, Hudon's looking for treasure in the waterfall.",
					"",
					"<str>I found Hudon a short raft ride down the river. He is",
					"<str>convinced there is treasure here somewhere. Maybe I",
					"<str>need to do a little research.",
					"",
					"<str>I found a book in the tourist office about Baxtorian. The",
					"<str>book tells of a sad love story about 2 elf lovers. It ends",
					"<str>with Baxtorian withdrawing to his home under the waterfall",
					"<str>after his wife dies. It is told that only Glarial could enter",
					"<str>their home.",
					"",
					"<str>The book also mentions Glarial's tomb and a pebble, it",
					"<str>appears that the pebble is used to enter the tomb.",
					"<str>From what I understand Glarial's pebble was hidden in a ",
					"<str> cave under the Tree Gnome Village by Golrie's ancestors.",
					"",
					"<str>Inside the tomb I found Glarial's amulet and ashes.",
					"",
					"<str>I finally got access to the derelict home of Baxtorian. The",
					"<str>door must have been keyed to Glarial's amulet.",
					"",
					"<blue>I have found a room containing the <red>Chalice of Eternity.<blue> The",
					"<blue>chalice is suspended in midair just out of reach."
				);
			}
			break;
		case 100:
			writeJournal(player, line,
				"<str>I spoke to Almera in a house close to the Baxtorian",
				"<str>waterfall. Her son was missing so I offered to help find",
				"<str>him. The boy, Hudon's looking for treasure in the waterfall.",
				"",
				"<str>I found Hudon a short raft ride down the river. He is",
				"<str>convinced there is treasure here somewhere. Maybe I",
				"<str>need to do a little research.",
				"",
				"<str>I found a book in the tourist office about Baxtorian. The",
				"<str>book tells of a sad love story about 2 elf lovers. It ends",
				"<str>with Baxtorian withdrawing to his home under the waterfall",
				"<str>after his wife dies. It is told that only Glarial could enter",
				"<str>their home.",
				"",
				"<str>The book also mentions Glarial's tomb and a pebble, it",
				"<str>appears that the pebble is used to enter the tomb.",
				"<str>From what I understand Glarial's pebble was hidden in a ",
				"<str> cave under the Tree Gnome Village by Golrie's ancestors.",
				"",
				"<str>Inside the tomb I found Glarial's amulet and ashes.",
				"",
				"<str>I finally got access to the derelict home of Baxtorian. The",
				"<str>door must have been keyed to Glarial's amulet.",
				"",
				"<str>I have found a room containing the Chalice of Eternity. The",
				"<str>chalice is suspended in midair just out of reach. ",
				"",
				"<str>Using Glarial's ashes as a counterweight, I was able to",
				"<str>remove the treasure that had been left in the chalice.",
				"",
				"<col=FF0000>QUEST COMPLETE!</col>");
			break;
		}
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(1601, 230);
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
