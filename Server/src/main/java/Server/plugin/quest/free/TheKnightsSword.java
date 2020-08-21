package plugin.quest.free;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents The KnightSword quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class TheKnightsSword extends Quest {
	
	/**
	 * Represents the portrait item.
	 */
	private static final Item PORTRAIT = new Item(666);

	/**
	 * Constructs a new {@code TheKnightsSword} {@code Object}.
	 * @param player The player.
	 */
	public TheKnightsSword() {
		super("The Knight's Sword", 22, 21, 1, 122, 0, 1, 7);
	}
	
	@Override
	public Quest newInstance(Object object) {
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to the " + RED + "Squire " + BLUE + "in the", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE + "courtyard of the " + RED + "White Knights' Castle " + BLUE + "in " + RED + "southern Falador", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE + "To complete this quest I need:", 275, 6+ 7);
			player.getPacketDispatch().sendString(RED + "Level 10 Mining", 275, 7+ 7);
			player.getPacketDispatch().sendString(BLUE + "and to be unafraid of " + RED + "Level 57 Ice Warriors.", 275, 8+ 7);
			break;
		case 10:
			line(player, "<str>I told the Squire I would help him to replace the sword he", 4+ 7);
			line(player, "<str>has lost. It could only be made by an Imcando Dwarf.", 5+ 7);
			line(player, BLUE + "The Squire suggests I speak to " + RED + "Reldo " + BLUE + "in the " + RED + " Varrock Palace", 6+ 7);
			line(player, RED + "Library " + BLUE + "for information about the " + RED + "Imcando Dwarves", 7+ 7);
			break;
		case 20:
			line(player, "<str>I told the Squire I would help him to replace the sword he", 4+ 7);
			line(player, "<str>has lost. It could only be made by an Imcando Dwarf.", 5+ 7);
			line(player, BLUE + "Reldo couldn't give me much information about the", 6+ 7);
			line(player, RED + "Imcando " + BLUE + "except a few live on the " + RED + "southern peninsula of", 7+ 7);
			line(player, RED + "Asgarnia, " + BLUE + "they dislike stangers, and LOVE " + RED + "redberry pies.", 8+ 7);
			break;
		case 30:
			line(player, "<str>I told the Squire I would help him to replace the sword he", 4+ 7);
			line(player, "<str>has lost. It could only be made by an Imcando Dwarf.", 5+ 7);
			line(player, "<str>I found an Imcando Dwarf named Thurgo thanks to", 6+ 7);
			line(player, "<str>information provided by Reldo. He wasn't very talkative", 7+ 7);
			line(player, "<str>until I gave him a Redberry pie, which he gobbled up.", 8+ 7);
			line(player, BLUE + "He will help me now I have gained his trust thorugh " + RED + "pie", 9+ 7);
			break;
		case 40:
			line(player, "<str>I told the Squire I would help him to replace the sword he", 4+ 7);
			line(player, "<str>has lost. It could only be made by an Imcando Dwarf.", 5+ 7);
			line(player, "<str>I found an Imcando Dwarf named Thurgo thanks to", 6+ 7);
			line(player, "<str>information provided by Reldo. He wasn't very talkative", 7+ 7);
			line(player, "<str>until I gave him a Redberry pie, which he gobbled up.", 8+ 7);
			line(player, RED + "Thurgo " + BLUE + "needs a " + RED + "picture of the sword " + BLUE + "before he can help.", 9+ 7);
			line(player, BLUE + "I should probably ask the " + RED + "Squire " + BLUE + "about obtaining one", 10+ 7);
			break;
		case 50:
			line(player, "<str>I told the Squire I would help him to replace the sword he", 4+ 7);
			line(player, "<str>has lost. It could only be made by an Imcando Dwarf.", 5+ 7);
			line(player, "<str>I found an Imcando Dwarf named Thurgo thanks to", 6+ 7);
			line(player, "<str>information provided by Reldo. He wasn't very talkative", 7+ 7);
			line(player, "<str>until I gave him a Redberry pie, which he gobbled up.", 8+ 7);
			line(player, "<str>Thurgo needed a picture of the sword to replace.", 9+ 7);
			if (!player.getInventory().containsItem(PORTRAIT)) {
				line(player, BLUE + "The Squire told me about a " + RED + "portrait ", 10+ 7);
				line(player, BLUE + "which has a " + RED + "picture of the sword " + BLUE + "in " + RED + "Sir Vyvin's room", 11+ 7);
			} else {
				line(player, BLUE + "I now have a picture of the " + RED + "Knight's Sword " + BLUE + "- I should take it", 10+ 7);
				line(player, BLUE + "to " + RED + "Thurgo " + BLUE + "so that he can duplicate it.", 11+ 7);
			}
			break;
		case 60:
			line(player, "<str>I told the Squire I would help him to replace the sword he", 4+ 7);
			line(player, "<str>has lost. It could onfly be made by an Imcando Dwarf.", 5+ 7);
			line(player, "<str>I found an Imcando Dwarf named Thurgo thanks to", 6+ 7);
			line(player, "<str>information provided by Reldo. He wasn't very talkative", 7+ 7);
			line(player, "<str>until I gave him a Redberry pie, which he gobbled up.", 8+ 7);
			line(player, "<str>Thurgo needed a picture of the sword before he could", 9+ 7);
			line(player, "<str>start work on a replacement. I took him a portrait of it.", 10+ 7);
			if (player.getInventory().contains(667, 1) || player.getEquipment().contains(667, 1) || player.getBank().contains(667, 1)) {
				line(player, "<str>Thurgo has now smithed me a replica of Sir Vyvin's sword.", 11+ 7);
				line(player, BLUE + "I should return it to the " + RED + "Squire " + BLUE + "for my " + RED + "reward", 13+ 7);
			} else {
				line(player, BLUE + "according to " + RED + "Thurgo " + BLUE + "to make a " + RED + "replica sword " + BLUE + "he will need", 11+ 7);
				line(player, RED + "two Iron Bars " + BLUE + "and some " + RED + "Blurite Ore. Blurite Ore " + BLUE + "can only be", 12+ 7);
				line(player, BLUE + "found " + RED + "deep in the caves below Thurgo's house", 13+ 7);
			}
			break;
		case 100:
			line(player, "<str>Thurgo needed a picture of the sword before he could", 4+ 7);
			line(player, "<str>start work on a replacement. I took him a portrait of it.", 5+ 7);
			line(player, "<str>After bringing Thurgo two iron bars and some blurite ore", 6+ 7);
			line(player, "<str>he made me a fine replica of Sir Vyvin's Sword, which I", 7+ 7);
			line(player, "<str>returned to the Squire for a reward.", 8+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!</col>", 10+ 7);
			break;
		}

	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("12,725 Smithing XP", 277, 9 + 2);
		player.getPacketDispatch().sendString("You have completed the Knight's Sword Quest!", 277, 2 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(667, 230, 277, 3 + 2);
		player.getSkills().addExperience(Skills.SMITHING, 12725);
	}

}
