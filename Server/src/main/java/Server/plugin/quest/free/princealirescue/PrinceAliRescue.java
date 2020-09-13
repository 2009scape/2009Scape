package plugin.quest.free.princealirescue;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the prince ali rescue quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class PrinceAliRescue extends Quest {

	/**
	 * Represents the rope item.
	 */
	private static final Item ROPE = new Item(954);

	/**
	 * Represents the pink skirt item.
	 */
	private static final Item SKIRT = new Item(1013);

	/**
	 * Represents the yellow wig item.
	 */
	private static final Item YELLOW_WIG = new Item(2419);

	/**
	 * Represents the skin paste item.
	 */
	private static final Item PASTE = new Item(2424);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 700);
	
	/**
	 * Constructs a new {@Code PrinceAliRescue} {@Code Object}
	 */
	public PrinceAliRescue() {
		super(
			"Prince Ali Rescue",
			24,
			23,
			3,
			273, 0, 1, 110
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new LadyKeliDialogue(), new LadyKeliNPC(), new PrinceAliRescuePlugin(), new WigDyePlugin());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			writeJournal(player,
				BLUE + "I can start this quest by speaking to " + RED + "Hassan " + BLUE + "at the palace",
				BLUE + "in" + RED + " Al-Kharid.");
			break;
		case 10:
			writeJournal(player,
				"<str>I started this quest by speaking to Hassan in Al-Kharid",
				"<str>Palace. he told me I should speak to Osman the spymaster.",
				BLUE + "I should go and speak to " + RED + "Osman " + BLUE + "for details on the quest.");
			break;
		case 20:
			writeJournal(player,
				"<str>I started this quest by speaking to Hassan in Al-Kharid",
				"<str>Palace. he told me I should speak to Osman the spymaster.",
				"<str>I should go and speak to Osman for details on the quest.",
				RED + "Prince Ali " + BLUE + "has been " + RED + "kidnapped " + BLUE + "but luckily the spy " + RED + "Leela " + BLUE + "has",
				BLUE + "found he is being held near " + RED + "Draynor village. " + BLUE + "I will need to",
				RED + "disguise " + BLUE + "the " + RED + "Price " + BLUE + "and " + RED + "tie " + BLUE + "up his " + RED + "captop " + BLUE + "to " + RED + "free " + BLUE + "him from",
				BLUE + "their " + RED + "clutches.",
				BLUE + "To do this I should:-",
				BLUE + "Talk to " + RED + "Leela " + BLUE + "near " + RED + "Draynor Village " + BLUE + "for advice.",
				BLUE + "Get a " + RED + "duplicate " + BLUE + "of the " + RED + "key " + BLUE + "that is " + RED + "imprisoning " + BLUE + "the " + RED + "prince.",
				hasItem(player, ROPE) ? "<str>I have some rope with me." : BLUE + "Get some " + RED + "rope " + BLUE + "to tie up the Princes' " + RED + "kidnapper.",
				hasItem(player, PASTE) ? "<str>I have some skin paste suitable for disguise with me." : BLUE + "Get something to " + RED + "colour " + BLUE + "the " + RED + "Princes' skin " + BLUE + "as a " + RED + "disguise.",
				hasItem(player, SKIRT) ? "<str>I have a skirt suitable for a disguise with me." : BLUE + "Get a " + RED + "skirt " + BLUE + "similar to his " + RED + "kidnapper " + BLUE + "as " + RED + "disguise.",
				hasItem(player, YELLOW_WIG) ? "<str>I have a wig suitable for disguise with me." : BLUE + "Get a " + RED + "Wig " + BLUE + "to " + RED + "help disguise " + BLUE + "the " + RED + "prince.");
			break;
		case 30:
			writeJournal(player,
				"<str>I started this quest by speaking to Hassan in Al-Kharid",
				"<str>Palace. he told me I should speak to Osman the spymaster.",
				"<str>I should go and speak to Osman for details on the quest.",
				RED + "Prince Ali " + BLUE + "has been " + RED + "kidnapped " + BLUE + "but luckily the spy " + RED + "Leela " + BLUE + "has",
				BLUE + "found he is being held near " + RED + "draynor village. " + BLUE + "I will need to",
				RED + "disguise " + BLUE + "the " + RED + "Price " + BLUE + "and " + RED + "tie " + BLUE + "up his " + RED + "captop " + BLUE + "to " + RED + "free " + BLUE + "him from",
				BLUE + "their " + RED + "clutches.",
				BLUE + "To do this I should:-",
				BLUE + "Talk to " + RED + "Leela " + BLUE + "near " + RED + "Draynor Village " + BLUE + "for advice.",
				BLUE + "I have duplicated a key, I need to get it from " + RED + "Leela.",
				hasItem(player, ROPE) ? "<str>I have some rope with me." : BLUE + "Get some " + RED + "rope " + BLUE + "to tie up the Princes' " + RED + "kidnapper.",
				hasItem(player, PASTE) ? "<str>I have some skin paste suitable for disguise with me." : BLUE + "Get something to " + RED + "colour " + BLUE + "the " + RED + "Princes' skin " + BLUE + "as a " + RED + "disguise.",
				hasItem(player, SKIRT) ? "<str>I have a skirt suitable for a disguise with me." : BLUE + "Get a " + RED + "skirt " + BLUE + "similar to his " + RED + "kidnapper " + BLUE + "as " + RED + "disguise.",
				hasItem(player, YELLOW_WIG) ? "<str>I have a wig suitable for disguise with me." : BLUE + "Get a " + RED + "Wig " + BLUE + "to " + RED + "help disguise" + BLUE + "the " + RED + "prince.");
			break;
		case 40:
			int line = writeJournal(player,
				"<str>I started this quest by speaking to Hassan in Al-Kharid",
				"<str>Palace. he told me I should speak to Osman the spymaster.",
				"<str>I should go and speak to Osman for details on the quest.");
			if (player.getAttribute("guard-drunk", false)) {
				writeJournal(player, line,
					"<str>Prince Ali has been kidnapped but luckily the spy Leela has",
					"<str>found he is being held near Draynor village. I will need to",
					"<str>disguise the Prince and tie up his captor to free him from",
					"<str>their clutches.",
					"<str>I also had to prevent the Guard from seeing that I was up",
					"<str>to, by getting him drunk.",
					BLUE + "With the guard out of the way, all I have to do now is use",
					BLUE + "the " + RED + "Skin Potion" + BLUE + ", " + RED + "Pink Skirt" + BLUE + ", " + RED + "Rope" + BLUE + ", " + RED + "Blonde Wig " + BLUE + "and " + RED + "Cell Key " + BLUE + "to",
					BLUE + "free " + RED + "Prince Ali " + BLUE + "from his cell somehow.");
			} else {
				writeJournal(player, line,
					BLUE + "Do something to prevent " + RED + "Joe the Guard " + BLUE + "seeing the",
					BLUE + "escape.",
					BLUE + "Use the " + RED + "Skin potion" + BLUE + ", " + RED + "Pink Skirt" + BLUE + "," + RED + "Rope" + BLUE + "," + RED + "Blonde Wig " + BLUE + "and " + RED + "Cell",
					RED + "Key" + BLUE + " to free " + RED + "Prince Ali " + BLUE + "from his cell somehow.");
			}
			break;
		case 50:
			writeJournal(player,
				"<str>I started this quest by speaking to Hassan in Al-Kharid",
				"<str>Palace. he told me I should speak to Osman the spymaster.",
				"<str>I should go and speak to Osman for details on the quest.",
				"<str>Prince Ali has been kidnapped but luckily the spy Leela has",
				"<str>found he is being held near Draynor village. I will need to",
				"<str>disguise the Prince and tie up his captor to free him from",
				"<str>their clutches.",
				"<str>I also had to prevent the Guard from seeing that I was up",
				"<str>to, by getting him drunk.",
				"<str>With the guard disposed of, I used my rope to tie up Lady",
				"<str>Keli in a cupboard, so I could disguise the Prince.",
				BLUE + "I need to " + RED + "Unlock the cell door " + BLUE + "and then give the Prince the",
				RED + "Pink Skirt" + BLUE + ", the " + RED + "Skin paste " + BLUE + "and the " + RED + "Blonde Swig " + BLUE + "so that the",
				BLUE + "can safely " + RED + "escape " + BLUE + "disguised as " + RED + "Lady Keli.");
			break;
		case 60:
			writeJournal(player,
				"<str>I started this quest by speaking to Hassan in Al-Kharid",
				"<str>Palace. he told me I should speak to Osman the spymaster.",
				"<str>I should go and speak to Osman for details on the quest.",
				"<str>Prince Ali has been kidnapped but luckily the spy Leela has",
				"<str>found he is being held near Draynor village. I will need to",
				"<str>disguise the Prince and tie up his captor to free him from",
				"<str>their clutches.",
				"<str>I also had to prevent the Guard from seeing that I was up",
				"<str>to, by getting him drunk.",
				"<str>With the guard disposed of, I used my rope to tie up Lady",
				"<str>Keli in a cupboard, so I could disguise the Prince.",
				"<str>I then used a wig, and some skin paste to make the",
				"<str>prince look like Lady Keli so he could escape to his",
				"<str>freedom with Leela after unlocking his cell door.",
				BLUE + "I should return to " + RED + "Hassan " + BLUE + "to claim my reward.");
			break;
		case 100:
			writeJournal(player,
				"<str>I started this quest by speaking to Hassan in Al-Kharid",
				"<str>Palace. he told me I should speak to Osman the spymaster.",
				"<str>I should go and speak to Osman for details on the quest.",
				"<str>Prince Ali has been kidnapped but luckily the spy Leela has",
				"<str>found he is being held near Draynor village. I will need to",
				"<str>disguise the Prince and tie up his captor to free him from",
				"<str>their clutches.",
				"<str>I also had to prevent the Guard from seeing that I was up",
				"<str>to, by getting him drunk.",
				"<str>With the guard disposed of, I used my rope to tie up Lady",
				"<str>Keli in a cupboard, so I could disguise the Prince.",
				"<str>I then used a wig, and some skin paste to make the",
				"<str>prince look like Lady Keli so he could escape to his",
				"<str>freedom with Leela after unlocking his cell door.",
				"<str>Hassan the chancellor rewarded me for all of my help.",
				"<str>I am now a friend of Al-Kharid and may pass through the",
				"<str>gate leading between Lumbridge and Al-Kharid for free",
				"",
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	/**
	 * Check if the player has the item.
	 * @param player the player.
	 * @param item the item.
	 * @return true or false.
	 */
	public static boolean hasItem(final Player player, final Item item) {
		return player.getInventory().containsItem(item);
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendMessage("The chancellor pays you 700 coins.");
		player.removeAttribute("guard-drunk");
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(995, 20);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(new Item(995, 700)),
		};
	}
}
