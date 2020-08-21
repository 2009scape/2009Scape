package plugin.quest.members.waterfallquest;

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
	 * @param player The player to construct the class for.
	 */
	public WaterFall() {
		super("Waterfall", 65, 64, 1, 65, 0, 1, 10);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			line(player, "<blue>I can start this quest by speaking to <red>Almera<blue> in her house<n><blue> next to the <red>Baxtorian Falls.<n><n><blue>I need to be able to fight <red>Level 84 Giants.", 12);
			break;
		case 10:
			line(player, "<blue>I spoke to <red>Almera<blue> in a house close to the Baxtorian<n><blue>waterfall. Her son was missing so I offered to help find<n><blue>him. The boy, <red>Hudon's<blue> looking for treasure in the waterfall.", 12);
			break;
		case 20:
			line(player, "<str>I spoke to Almera in a house close to the Baxtorian<n><str>waterfall. Her son was missing so I offered to help find<n><str>him. The boy, Hudon's looking for treasure in the waterfall.<n><n><blue>I found Hudon a short raft ride down the river. He is<n><blue>convinced there is treasure here somewhere. Maybe I<n><blue>need to do a little research.", 12);
			break;
		case 30:
			if (player.getInventory().containsItem(new Item(294, 1))) {
				line(player, "<str>I spoke to Almera in a house close to the Baxtorian<n><str>waterfall. Her son was missing so I offered to help find<n><str>him. The boy, Hudon's looking for treasure in the waterfall.<n><n><str>I found Hudon a short raft ride down the river. He is<n><str>convinced there is treasure here somewhere. Maybe I<n><str>need to do a little research.<n><n><str>I found a book in the tourist office about Baxtorian. The<n><str>book tells of a sad love story about 2 elf lovers. It ends<n><str>with Baxtorian withdrawing to his home under the waterfall<n><str>after his wife dies. It is told that only Glarial could enter<n><str>their home.<n><n><blue>The book also mentions <red>Glarial's tomb<blue> and a pebble, it<n><blue>appears that the pebble is used to enter the tomb.<n><str>From what I understand Glarial's pebble was hidden in a <n><str> cave under the Tree Gnome Village by Golrie's ancestors.", 12);
			} else {
				line(player, "<str>I spoke to Almera in a house close to the Baxtorian<n><str>waterfall. Her son was missing so I offered to help find<n><str>him. The boy, Hudon's looking for treasure in the waterfall.<n><n><str>I found Hudon a short raft ride down the river. He is<n><str>convinced there is treasure here somewhere. Maybe I<n><str>need to do a little research.<n><n><str>I found a book in the tourist office about Baxtorian. The<n><str>book tells of a sad love story about 2 elf lovers. It ends<n><str>with Baxtorian withdrawing to his home under the waterfall<n><str>after his wife dies. It is told that only Glarial could enter<n><str>their home.<n><n><blue>The book also mentions <red>Glarial's tomb<blue> and a pebble, it<n><blue>appears that the pebble is used to enter the tomb.<n><blue>From what I understand <red>Glarial's pebble<blue> was hidden in a <n><blue> cave under the <red>Tree Gnome Village<blue> by<red> Golrie's<blue> ancestors.", 12);
			}
			if (player.getInventory().containsItem(new Item(295, 1)) || player.getEquipment().containsOneItem(295)) {
				line(player, "<str>I spoke to Almera in a house close to the Baxtorian<n><str>waterfall. Her son was missing so I offered to help find<n><str>him. The boy, Hudon's looking for treasure in the waterfall.<n><n><str>I found Hudon a short raft ride down the river. He is<n><str>convinced there is treasure here somewhere. Maybe I<n><str>need to do a little research.<n><n><str>I found a book in the tourist office about Baxtorian. The<n><str>book tells of a sad love story about 2 elf lovers. It ends<n><str>with Baxtorian withdrawing to his home under the waterfall<n><str>after his wife dies. It is told that only Glarial could enter<n><str>their home.<n><n><str>The book also mentions Glarial's tomb and a pebble, it<n><str>appears that the pebble is used to enter the tomb.<n><str>From what I understand Glarial's pebble was hidden in a <n><str> cave under the Tree Gnome Village by Golrie's ancestors.<n><n><blue>Inside the tomb I found Glarial's amulet and ashes.", 12);
			}
			if (player.getLocation().getY() >= 9902) {
				line(player, "<str>I spoke to Almera in a house close to the Baxtorian<n><str>waterfall. Her son was missing so I offered to help find<n><str>him. The boy, Hudon's looking for treasure in the waterfall.<n><n><str>I found Hudon a short raft ride down the river. He is<n><str>convinced there is treasure here somewhere. Maybe I<n><str>need to do a little research.<n><n><str>I found a book in the tourist office about Baxtorian. The<n><str>book tells of a sad love story about 2 elf lovers. It ends<n><str>with Baxtorian withdrawing to his home under the waterfall<n><str>after his wife dies. It is told that only Glarial could enter<n><str>their home.<n><n><str>The book also mentions Glarial's tomb and a pebble, it<n><str>appears that the pebble is used to enter the tomb.<n><str>From what I understand Glarial's pebble was hidden in a <n><str> cave under the Tree Gnome Village by Golrie's ancestors.<n><n><str>Inside the tomb I found Glarial's amulet and ashes.<n><n><str>I finally got access to the derelict home of Baxtorian. The<n><str>door must have been keyed to Glarial's amulet.<n><n><blue>I have found a room containing the <red>Chalice of Eternity.<blue> The<n><blue>chalice is suspended in midair just out of reach.", 12);
			}
			break;
		case 100:
			line(player, "<str>I spoke to Almera in a house close to the Baxtorian<n><str>waterfall. Her son was missing so I offered to help find<n><str>him. The boy, Hudon's looking for treasure in the waterfall.<n><n><str>I found Hudon a short raft ride down the river. He is<n><str>convinced there is treasure here somewhere. Maybe I<n><str>need to do a little research.<n><n><str>I found a book in the tourist office about Baxtorian. The<n><str>book tells of a sad love story about 2 elf lovers. It ends<n><str>with Baxtorian withdrawing to his home under the waterfall<n><str>after his wife dies. It is told that only Glarial could enter<n><str>their home.<n><n><str>The book also mentions Glarial's tomb and a pebble, it<n><str>appears that the pebble is used to enter the tomb.<n><str>From what I understand Glarial's pebble was hidden in a <n><str> cave under the Tree Gnome Village by Golrie's ancestors.<n><n><str>Inside the tomb I found Glarial's amulet and ashes.<n><n><str>I finally got access to the derelict home of Baxtorian. The<n><str>door must have been keyed to Glarial's amulet.<n><n><str>I have found a room containing the Chalice of Eternity. The<n><str>chalice is suspended in midair just out of reach. <n><n><str>Using Glarial's ashes as a counterweight, I was able to<n><str>remove the treasure that had been left in the chalice.<n><n><col=FF0000>QUEST COMPLETE!</col>", 12);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getQuestRepository().syncronizeTab(player);
		player.getConfigManager().set(101, player.getQuestRepository().getPoints());

		player.getPacketDispatch().sendString("1 Quest Point", 277, 8+ 2);
		player.getPacketDispatch().sendString("13,750 Strength XP", 277, 9+ 2);
		player.getPacketDispatch().sendString("13,750 Attack XP", 277, 10+ 2);
		player.getPacketDispatch().sendString("2 diamonds", 277, 11+ 2);
		player.getPacketDispatch().sendString("2 gold bars", 277, 12+ 2);
		player.getPacketDispatch().sendString("40 Mithril seeds", 277, 13+ 2);
		player.getPacketDispatch().sendString("You have completed the Waterfall Quest!", 277, 2+ 2);
		player.getPacketDispatch().sendItemZoomOnInterface(1601, 230, 277, 3+ 2);
		player.getSkills().addExperience(Skills.STRENGTH, 13750);
		player.getSkills().addExperience(Skills.ATTACK, 13750);
		player.getInventory().add(new Item(1601, 2));// diamonds
		player.getInventory().add(new Item(2357, 2));// gold bars
		player.getInventory().add(new Item(299, 40));// seeds

		setStage(player, 100);
		player.getQuestRepository().syncronizeTab(player);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new AlmeraDialogue(), new BookOnBaxtorianPlugin(), new GolrieDialogue(), new HadleyDialogue(), new HudonDialogue(), new WaterfallPlugin(), new WaterfallTreeDialogue());
		return this;
	}

}
