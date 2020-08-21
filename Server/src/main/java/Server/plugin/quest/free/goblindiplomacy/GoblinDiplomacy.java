package plugin.quest.free.goblindiplomacy;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the demon slayer quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class GoblinDiplomacy extends Quest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "Goblin Diplomacy";

	/**
	 * Repreresents the orange goblin mail.
	 */
	private static final Item ORANGE_MAIL = new Item(286);

	/**
	 * Represents the blue goblin mail.
	 */
	private static final Item BLUE_MAIL = new Item(287);

	/**
	 * Represents the goblin mail.
	 */
	private static final Item GOBLIN_MAIL = new Item(288);

	/**
	 * Represents the gold bar item.
	 */
	private static final Item GOLD_BAR = new Item(2357);
	
	/**
	 * Constructs a new {@Code GoblinDiplomacy} {@Code Object}
	 */
	public GoblinDiplomacy() {
		super("Goblin Diplomacy", 20, 19, 5);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new GDiplomacyCutscene(), new GoblinDiplomacyPlugin(), new GrubfootDialogue());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to " + RED + "Generals Wartface", 275, 4 + 7);
			player.getPacketDispatch().sendString(RED + "and Bentnoze " + BLUE + "in the " + RED + " Goblin Village.", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE + "There are no requirements for this quest.", 275, 6+ 7);
			break;
		case 10:
			line(player, "<str>I spoke to Generals Wartface and Bentnoze in the Goblin", 4+ 7);
			line(player, "<str>Village and found that the goblins were on the bring of civil", 5+ 7);
			line(player, "<str>war over the colour of their armour. I o dffered to help the", 6+ 7);
			line(player, "<str>generals by finding another colour that they both like.", 7+ 7);
			if (player.getInventory().containsItem(ORANGE_MAIL)) {
				line(player, BLUE + "I have some " + RED + "Orange Goblin Armour. " + BLUE + "I should show it to the", 9+ 7);
				line(player, BLUE + "generals.", 10+ 7);
			} else {
				line(player, BLUE + "I should bring the goblins some " + RED + "Orange Goblin Armour", 9+ 7);
				line(player, BLUE + "Maybe the generals will know where to get some.", 10+ 7);
			}
			break;
		case 20:
			line(player, "<str>I spoke to Generals Wartface and Bentnoze in the Goblin", 4+ 7);
			line(player, "<str>Village and found that the goblins were on the bring of civil", 5+ 7);
			line(player, "<str>war over the colour of their armour. I offered to help the", 6+ 7);
			line(player, "<str>generals by finding another colour that they both like.", 7+ 7);
			line(player, "<str>I brought the goblins some orange goblin armour, but they", 9+ 7);
			line(player, "<str>didn't like it.", 10+ 7);
			if (player.getInventory().containsItem(BLUE_MAIL)) {
				line(player, BLUE + "I have some " + RED + "Blue Goblin Armour. " + BLUE + "I should show it to the", 12+ 7);
				line(player, BLUE + "generals.", 13+ 7);
			} else {
				line(player, BLUE + "I should bring the goblins s+ 7+ 7);e " + RED + "Blue Goblin Armour", 12+ 7);
				line(player, BLUE + "Maybe the generals will know where to get some.", 13+ 7);
			}
			break;
		case 30:
			line(player, "<str>I spoke to Generals Wartface and Bentnoze in the Goblin", 4+ 7);
			line(player, "<str>Village and found that the goblins were on the bring of civil", 5+ 7);
			line(player, "<str>war over the colour of their armour. I offered to help the", 6+ 7);
			line(player, "<str>generals by finding another colour that they both like.", 7+ 7);
			line(player, "<str>I brought the goblins some orange goblin armour, but they", 9+ 7);
			line(player, "<str>didn't like it.", 10+ 7);
			line(player, "<str>I brought the goblins some blue goblin armour, but they", 12+ 7);
			line(player, "<str>didn't like it.", 13+ 7);
			if (player.getInventory().containsItem(GOBLIN_MAIL)) {
				line(player, BLUE + "I have some " + RED + "Brown Goblin Armour. " + BLUE + "I should show it to the", 12+ 7);
				line(player, BLUE + "generals.", 13+ 7);
			} else {
				line(player, BLUE + "I should bring the goblins some " + RED + "Brown Goblin Armour", 12+ 7);
				line(player, BLUE + "Maybe the generals will know where to get some.", 13+ 7);
			}
			break;
		case 100:
			line(player, "<str>I spoke to Generals Wartface and Bentnoze in the Goblin", 4+ 7);
			line(player, "<str>Village and found that the goblins were on the bring of civil", 5+ 7);
			line(player, "<str>war over the colour of their armour. I offered to help the", 6+ 7);
			line(player, "<str>generals by finding another colour that they both like.", 7+ 7);
			line(player, "<str>I brought the goblins some orange goblin armour, but they", 9+ 7);
			line(player, "<str>didn't like it.", 10+ 7);
			line(player, "<str>I brought the goblins some blue goblin armour, but they", 12+ 7);
			line(player, "<str>didn't like it.", 13+ 7);
			line(player, "<str>Unfortunately the goblins were very stupid, and it turned", 15+ 7);
			line(player, "<str>out that they liked the original colour the most. That's goblins", 16+ 7);
			line(player, "<str>for you I guess.", 17+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!</col>", 18+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("5 Quests Points", 277, 8 + 2);
		player.getPacketDispatch().sendString("200 Crafting XP", 277, 9 + 2);
		player.getPacketDispatch().sendString("A gold bar", 277, 10 + 2);
		player.getPacketDispatch().sendString("You have completed the Goblin Diplomacy Quest!", 277, 2 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(288, 230, 277, 3 + 2);
		player.getSkills().addExperience(Skills.CRAFTING, 200);
		if (!player.getInventory().add(GOLD_BAR)) {
			GroundItemManager.create(GOLD_BAR, player);
		}
	}

	@Override
	public int[] getConfig(Player player, int stage) {
		if (stage == 0) {
			return new int[] { 62, 0 };
		}
		if (stage == 10) {
			return new int[] { 62, 1 };
		} else if (stage == 20) {
			return new int[] { 62, 4 };
		} else if (stage == 30) {
			return new int[] { 62, 5 };
		} else if (stage == 100) {
			return new int[] { 62, 6 };
		}
		return new int[] { 62, 0 };
	}

}
