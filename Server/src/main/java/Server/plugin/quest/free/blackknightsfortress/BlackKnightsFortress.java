package plugin.quest.free.blackknightsfortress;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the black knights fortress quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public final class BlackKnightsFortress extends Quest {

	/**
	 * Represents the dossier item.
	 */
	public static final Item DOSSIER = new Item(9589);
	
	/**
	 * Constructs a new {@Code BlackKnightsFortress} {@Code Object}
	 */
	public BlackKnightsFortress() {
		super("Black Knights' Fortress", 14, 13, 3, 130, 0, 1, 4);
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
			player.getPacketDispatch().sendString("<col=08088A>I can start this quest by speaking to the <col=8A0808>Sir Amik Varze</col> <col=08088A>at the", 275, 4 + 7);
			player.getPacketDispatch().sendString(RED + " White Knight's Castle " + BLUE + "in " + RED + "Falador.", 275, 12);
			if (player.getQuestRepository().getPoints() < 12) {
				player.getPacketDispatch().sendString(RED + "I must have a total of at least 12 Quest Points", 275, 13);
			} else {
				line(player, "<str>I have a total of at least 12 Quest Points", 13);
			}
			player.getPacketDispatch().sendString(BLUE + "I would have an advantage if I could fight " + RED + "Level 33 Knights", 275, 14);
			player.getPacketDispatch().sendString(BLUE + "and if I had a smithing level of " + RED + "26.", 275, 15);
			break;
		case 10:
			line(player, RED + "Sir Amik Varze " + BLUE + "has asked me to investigate the " + RED + "Black", 4 + 7);
			line(player, RED + "Knights' Fortress " + BLUE + "which is located on " + RED + "Ice Mountain.", 5 + 7);
			line(player, BLUE + "I need to disguise myself to gain entry to the " + RED + "Black", 6 + 7);
			line(player, RED + "Knights' Fortress.", 7 + 7);
			break;
		case 20:
			line(player, "<str>Sir Amik Varze asked me to investigate the Black Knights'", 4 + 7);
			line(player, "<str>Fortress. I sneaked inside disguised as a Guard.", 5 + 7);
			line(player, BLUE + "I eavesdropped on a Witch and the Black Knight Captain", 6+ 7);
			line(player, BLUE + "and discovered that their invincibility potion can be", 7+ 7);
			line(player, BLUE + "destroyed with a normal " + RED + "cabbage.", 8+ 7);
			break;
		case 30:
			line(player, "<str>Sir Amik Varze asked me to investigate the Black Knights'", 4+ 7);
			line(player, "<str>Fortress. I sneaked inside disguised as a Guard.", 5+ 7);
			line(player, "<str>I eavesdropped on a Witch and the Black Knight Captain", 6+ 7);
			line(player, "<str>and discovered that their invincibility potion could be", 7+ 7);
			line(player, "<str>destroyed with a normal cabbage.", 8+ 7);
			line(player, BLUE + "Now that I have sabotaged the witch's potion, I can claim", 9+ 7);
			line(player, BLUE + "my " + RED + "reward " + BLUE + "from " + RED + "Sir Amik Varze " + BLUE + "in " + RED + "Falador Castle.", 10+ 7);
			break;
		case 100:
			line(player, "<str>Sir Amik Varze asked me to investigate the Black Knights'", 4+ 7);
			line(player, "<str>Fortress. I sneaked inside disguised as a Guard.", 5+ 7);
			line(player, "<str>I eavesdropped on a Witch and the Black Knight Captain", 6+ 7);
			line(player, "<str>and discovered that their invincibility potion could be", 7+ 7);
			line(player, "<str>destroyed with a normal cabbage.", 8+ 7);
			line(player, "<str>I found a cabbage, and used it to a destroy the potion, then", 9+ 7);
			line(player, "<str>claimed my reward for a job well done.", 10+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!</col>", 12+ 7);
			line(player, RED + "Reward:", 13+ 7);
			line(player, BLUE + "3 Quest Points", 14+ 7);
			line(player, BLUE + "2500gp", 15+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("3 Quests Points", 277, 10);
		player.getPacketDispatch().sendString("2500 Coins", 277, 11);
		player.getPacketDispatch().sendItemZoomOnInterface(9591, 230, 277, 5);
	}

}
