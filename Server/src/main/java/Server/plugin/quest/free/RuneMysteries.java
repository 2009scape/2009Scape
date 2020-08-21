package plugin.quest.free;

import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the rune mysteries fortress quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class RuneMysteries extends Quest {


	/**
	 * Constructs a new {@code RuneMysteries} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public RuneMysteries() {
		super("Rune Mysteries", 27, 26, 1, 63, 0, 1, 6);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		if (getStage(player) == 0) {
			line(player, BLUE + "I can start this quest by speaking to " + RED + "Duke Horacio " + BLUE + "of", 4+ 7);
			line(player, RED + "Lumbridge, " + BLUE + "upstairs in " + RED + "Lumbridge Castle.", 5+ 7);
		}
		if (getStage(player) == 10) {
			line(player, "<str>I spoke to Duke Horacio and he showed me a strange", 4+ 7);
			line(player, "<str>talisman that had been found by one of his subjects.", 5+ 7);
			line(player, "<str>I agreed to take it to the Wizards' Tower, South West of", 6+ 7);
			line(player, "<str>Lumbridge for further examination by the wizards.", 7+ 7);
			line(player, BLUE + "I need to find the " + RED + "Head Wizard " + BLUE + "and give him the " + RED + "Talisman", 8+ 7);
		}
		if (getStage(player) == 20) {
			line(player, "<str>I spoke to Duke Horacio and he showed me a strange", 4+ 7);
			line(player, "<str>talisman that had been found by one of his subjects.", 5+ 7);
			line(player, "<str>I agreed to take it to the Wizards' Tower, South West of", 6+ 7);
			line(player, "<str>Lumbridge for further examination by the wizards.", 7+ 7);
			line(player, "<str>I gave the Talisman to the Wizard but I didn't want to help", 8+ 7);
			line(player, "<str>him in his research right now.", 9+ 7);
			line(player, BLUE + "I should talk to " + RED + "Sedridor " + BLUE + "again to continue this quest.", 10+ 7);
		}
		if (getStage(player) == 30) {
			line(player, "<str>I spoke to Duke Horacio and he showed me a strange", 4+ 7);
			line(player, "<str>talisman that had been found by one of his subjects.", 5+ 7);
			line(player, "<str>I agreed to take it to the Wizards' Tower, South West of", 6+ 7);
			line(player, "<str>Lumbridge for further examination by the wizards.", 7+ 7);
			line(player, "<str>I gave the Talisman to the Head of the Tower and", 8+ 7);
			line(player, "<str>agreed to help him with his research into rune stones.", 9+ 7);
			line(player, BLUE + "I should take this " + RED + "Research Package " + BLUE + "to " + RED + "Aubury " + BLUE + "in " + RED + "Varrock", 10+ 7);
		}
		if (getStage(player) == 40) {
			line(player, "<str>I spoke to Duke Horacio and he showed me a strange", 4+ 7);
			line(player, "<str>talisman that had been found by one of his subjects.", 5+ 7);
			line(player, "<str>I agreed to take it to the Wizards' Tower, South West of", 6+ 7);
			line(player, "<str>Lumbridge for further examination by the wizards.", 7+ 7);
			line(player, "<str>I gave the Talisman to the Head of the Tower and", 8+ 7);
			line(player, "<str>agreed to help him with his research into rune stones.", 9+ 7);
			line(player, "<str>I took the research package to Varrock and delivered it.", 10+ 7);
			line(player, BLUE + "I should speak to " + RED + "Aubury " + BLUE + "again when he has finished", 11+ 7);
			line(player, BLUE + "examining the " + RED + "research package " + BLUE + " I have delivered to him.", 12+ 7);
		}
		if (getStage(player) == 50) {
			line(player, "<str>I spoke to Duke Horacio and he showed me a strange", 4+ 7);
			line(player, "<str>talisman that had been found by one of his subjects.", 5+ 7);
			line(player, "<str>I agreed to take it to the Wizards' Tower, South West of", 6+ 7);
			line(player, "<str>Lumbridge for further examination by the wizards.", 7+ 7);
			line(player, "<str>I gave the Talisman to the Head of the Tower and", 8+ 7);
			line(player, "<str>agreed to help him with his research into rune stones.", 9+ 7);
			line(player, "<str>I took the research package to Varrock and delivered it.", 10+ 7);
			line(player, "<str>Aubury was interested in the research package and gave", 11+ 7);
			line(player, "<str>me his own research notes to deliver to Sedridor.", 12+ 7);
			line(player, BLUE + "I should take the " + RED + "notes " + BLUE + "to " + RED + "Sedridor " + BLUE + "and see what he says", 13+ 7);
		}
		if (stage == 100) {
			line(player, "<str>I spoke to Duke Horacio and he showed me a strange", 4+ 7);
			line(player, "<str>talisman that had been found by one of his subjects.", 5+ 7);
			line(player, "<str>I agreed to take it to the Wizards' Tower, South West of", 6+ 7);
			line(player, "<str>Lumbridge for further examination by the wizards.", 7+ 7);
			line(player, "<str>I gave the Talisman to the Head of the Tower and", 8+ 7);
			line(player, "<str>agreed to help him with his research into rune stones.", 9+ 7);
			line(player, "<str>I took the research package to Varrock and delivered it.", 10+ 7);
			line(player, "<str>Aubury was interested in the research package and gave", 11+ 7);
			line(player, "<str>me his own research notes to deliver to Sedridor.", 12+ 7);
			line(player, "<str>I brought Sedridor the research notes that Aubury had", 13+ 7);
			line(player, "<str>compiled so that he could compare their research. They", 14+ 7);
			line(player, "<str>They discovered that it was now possible to create new rune", 15+ 7);
			line(player, "<str>stones, a skill that had been thought lost forever.", 16+ 7);
			line(player, "<str>In return for all of my help they taught me how to do this,", 17+ 7);
			line(player, "<str>and will teleport me to mine blank runes anytime.", 18+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!", 20 + 7);
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("Runecrafting skill", 277, 9 + 2);
		player.getPacketDispatch().sendString("Air talisman", 277, 10 + 2);
		player.getPacketDispatch().sendString("You have completed the Rune Mysteries Quest!", 277, 2 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(1438, 240, 277, 3 + 2);
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
