package plugin.quest.free.princealirescue;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
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
		super("Prince Ali Rescue", 24, 23, 3, 273, 0, 1, 110);
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
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to " + RED + "Hassan " + BLUE + "at the palace", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE + "in" + RED + " Al-Kharid.", 275, 5+ 7);
			break;
		case 10:
			line(player, "<str>I started this quest by speaking to Hassan in Al-Kharid", 4+ 7);
			line(player, "<str>Palace. he told me I should speak to Osman the spymaster.", 5+ 7);
			line(player, BLUE + "I should go and speak to " + RED + "Osman " + BLUE + "for details on the quest.", 6+ 7);
			break;
		case 20:
			line(player, "<str>I started this quest by speaking to Hassan in Al-Kharid", 4+ 7);
			line(player, "<str>Palace. he told me I should speak to Osman the spymaster.", 5+ 7);
			line(player, "<str>I should go and speak to Osman for details on the quest.", 6+ 7);
			line(player, RED + "Prince Ali " + BLUE + "has been " + RED + "kidnapped " + BLUE + "but luckily the spy " + RED + "Leela " + BLUE + "has", 7+ 7);
			line(player, BLUE + "found he is being held near " + RED + "Draynor village. " + BLUE + "I will need to", 8+ 7);
			line(player, RED + "disguise " + BLUE + "the " + RED + "Price " + BLUE + "and " + RED + "tie " + BLUE + "up his " + RED + "captop " + BLUE + "to " + RED + "free " + BLUE + "him from", 9+ 7);
			line(player, BLUE + "their " + RED + "clutches.", 10+ 7);
			line(player, BLUE + "To do this I should:-", 11+ 7);
			line(player, BLUE + "Talk to " + RED + "Leela " + BLUE + "near " + RED + "Draynor Village " + BLUE + "for advice.", 12+ 7);
			line(player, BLUE + "Get a " + RED + "duplicate " + BLUE + "of the " + RED + "key " + BLUE + "that is " + RED + "imprisoning " + BLUE + "the " + RED + "prince.", 13+ 7);
			line(player, hasItem(player, ROPE) ? "<str>I have some rope with me." : BLUE + "Get some " + RED + "rope " + BLUE + "to tie up the Princes' " + RED + "kidnapper.", 14+ 7);
			line(player, hasItem(player, PASTE) ? "<str>I have some skin paste sutiable for disguise with me." : BLUE + "Get something to " + RED + "colour " + BLUE + "the " + RED + "Princes' skin " + BLUE + "as a " + RED + "disguise.", 15+ 7);
			line(player, hasItem(player, SKIRT) ? "<str>I have a skirt suitbale for a disguise with me." : BLUE + "Get a " + RED + "skirt " + BLUE + "similar to his " + RED + "kidnapper " + BLUE + "as " + RED + "disguise.", 16+ 7);
			line(player, hasItem(player, YELLOW_WIG) ? "<str>I have a wig suitable for disguise with me." : BLUE + "Get a " + RED + "Wig " + BLUE + "to " + RED + "help disguise " + BLUE + "the " + RED + "prince.", 17+ 7);
			break;
		case 30:
			line(player, "<str>I started this quest by speaking to Hassan in Al-Kharid", 4+ 7);
			line(player, "<str>Palace. he told me I should speak to Osman the spymaster.", 5+ 7);
			line(player, "<Str>I should go and speak to Osman for details on the quest.", 6+ 7);
			line(player, RED + "Prince Ali " + BLUE + "has been " + RED + "kidnapped " + BLUE + "but luckily the spy " + RED + "Leela " + BLUE + "has", 7+ 7);
			line(player, BLUE + "found he is being held near " + RED + "draynor village. " + BLUE + "I will need to", 8+ 7);
			line(player, RED + "disguise " + BLUE + "the " + RED + "Price " + BLUE + "and " + RED + "tie " + BLUE + "up his " + RED + "captop " + BLUE + "to " + RED + "free " + BLUE + "him from", 9+ 7);
			line(player, BLUE + "their " + RED + "clutches.", 10+ 7);
			line(player, BLUE + "To do this I should:-", 11+ 7);
			line(player, BLUE + "Talk to " + RED + "Leela " + BLUE + "near " + RED + "Draynor Village " + BLUE + "for advice.", 12+ 7);
			line(player, BLUE + "I have duplicated a key, I need to get it from " + RED + "Leela.", 13+ 7);
			line(player, hasItem(player, ROPE) ? "<str>I have some rope with me." : BLUE + "Get some " + RED + "rope " + BLUE + "to tie up the Princes' " + RED + "kidnapper.", 14+ 7);
			line(player, hasItem(player, PASTE) ? "<str>I have some skin paste sutiable for disguise with me." : BLUE + "Get something to " + RED + "colour " + BLUE + "the " + RED + "Princes' skin " + BLUE + "as a " + RED + "disguise.", 15+ 7);
			line(player, hasItem(player, SKIRT) ? "<str>I have a skirt suitbale for a disguise with me." : BLUE + "Get a " + RED + "skirt " + BLUE + "similar to his " + RED + "kidnapper " + BLUE + "as " + RED + "disguise.", 16+ 7);
			line(player, hasItem(player, YELLOW_WIG) ? "<str>I have a wig suitable for disguise with me." : BLUE + "Get a " + RED + "Wig " + BLUE + "to " + RED + "help disguise" + BLUE + "the " + RED + "prince.", 17+ 7);
			break;
		case 40:
			line(player, "<str>I started this quest by speaking to Hassan in Al-Kharid", 4+ 7);
			line(player, "<str>Palace. he told me I should speak to Osman the spymaster.", 5+ 7);
			line(player, "<str>I should go and speak to Osman for details on the quest.", 6+ 7);
			if (player.getAttribute("guard-drunk", false)) {
				line(player, "<str>Prince Ali has been kidnapped but luckily the spy Leela has", 7+ 7);
				line(player, "<str>found he is being held near Draynor village. I will need to", 8+ 7);
				line(player, "<str>disguise the Prince and tie up his captor to free him from", 9+ 7);
				line(player, "<str>their glutches.", 10+ 7);
				line(player, "<str>I also had to prevent the Guard from seeing that I was up", 10+ 7);
				line(player, "<str>to, by getting him drunk.", 11+ 7);
				line(player, BLUE + "With the guard out of the way, all I have to do now is use", 11+ 7);
				line(player, BLUE + "the " + RED + "Skin Potion" + BLUE + ", " + RED + "Pink SKirt" + BLUE + ", " + RED + "Rope" + BLUE + ", " + RED + "Blonde Wig " + BLUE + "and " + RED + "Cell Key " + BLUE + "to", 12+ 7);
				line(player, BLUE + "free " + RED + "Prince Ali " + BLUE + "from his cell somehow.", 13+ 7);
			} else {
				line(player, BLUE + "Do something to prevent " + RED + "Joe the Guard " + BLUE + "seeing the", 7+ 7);
				line(player, BLUE + "escape.", 8+ 7);
				line(player, BLUE + "Use the " + RED + "Skin potion" + BLUE + ", " + RED + "Pink Skirt" + BLUE + "," + RED + "Rope" + BLUE + "," + RED + "Blonde Wig " + BLUE + "and " + RED + "Cell", 9+ 7);
				line(player, RED + "Key" + BLUE + " to free " + RED + "Prince Ali " + BLUE + "from his cell somehow.", 10+ 7);
			}
			break;
		case 50:
			line(player, "<str>I started this quest by speaking to Hassan in Al-Kharid", 4+ 7);
			line(player, "<str>Palace. he told me I should speak to Osman the spymaster.", 5+ 7);
			line(player, "<str>I should go and speak to Osman for details on the quest.", 6+ 7);
			line(player, "<str>Prince Ali has been kidnapped but luckily the spy Leela has", 7+ 7);
			line(player, "<str>found he is being held near Draynor village. I will need to", 8+ 7);
			line(player, "<str>disguise the Prince and tie up his captor to free him from", 9+ 7);
			line(player, "<str>their glutches.", 10+ 7);
			line(player, "<str>I also had to prevent the Guar from seeing that I was up", 10+ 7);
			line(player, "<str>to, by getting him drunk.", 11+ 7);
			line(player, "<str>With the guard disposed of, I used my rope to tie up Lady", 11+ 7);
			line(player, "<str>Keli in a cupboard, so I could disguise the Prince.", 12+ 7);
			line(player, BLUE + "I need to " + RED + "Unlock the cell door " + BLUE + "and then give the Prince the", 13+ 7);
			line(player, RED + "Pink Skirt" + BLUE + ", the " + RED + "Skin paste " + BLUE + "and the " + RED + "Blonde Swig " + BLUE + "so that the", 14+ 7);
			line(player, BLUE + "can safely " + RED + "escape " + BLUE + "disguised as " + RED + "Lady Keli.", 15+ 7);
			break;
		case 60:
			line(player, "<str>I started this quest by speaking to Hassan in Al-Kharid", 4+ 7);
			line(player, "<str>Palace. he told me I should speak to Osman the spymaster.", 5+ 7);
			line(player, "<str>I should go and speak to Osman for details on the quest.", 6+ 7);
			line(player, "<str>Prince Ali has been kidnapped but luckily the spy Leela has", 7+ 7);
			line(player, "<str>found he is being held near Draynor village. I will need to", 8+ 7);
			line(player, "<str>disguise the Prince and tie up his captor to free him from", 9+ 7);
			line(player, "<str>their glutches.", 10+ 7);
			line(player, "<str>I also had to prevent the Guar from seeing that I was up", 10+ 7);
			line(player, "<str>to, by getting him drunk.", 11+ 7);
			line(player, "<str>With the guard disposed of, I used my rope to tie up Lady", 11+ 7);
			line(player, "<str>Keli in a cupboard, so I could disguise the Prince.", 12+ 7);
			line(player, "<str>I then used a wig, and some skin paste to make the", 13+ 7);
			line(player, "<str>prince look like Lady Keli so he could escape to his", 14+ 7);
			line(player, "<str>freedom with Leela after unlocking his cell door.", 15+ 7);
			line(player, BLUE + "I should return to " + RED + "Hassan " + BLUE + "to claim my reward.", 16+ 7);
			break;
		case 100:
			line(player, "<str>I started this quest by speaking to Hassan in Al-Kharid", 4+ 7);
			line(player, "<str>Palace. he told me I should speak to Osman the spymaster.", 5+ 7);
			line(player, "<str>I should go and speak to Osman for details on the quest.", 6+ 7);
			line(player, "<str>Prince Ali has been kidnapped but luckily the spy Leela has", 7+ 7);
			line(player, "<str>found he is being held near Draynor village. I will need to", 8+ 7);
			line(player, "<str>disguise the Prince and tie up his captor to free him from", 9+ 7);
			line(player, "<str>their glutches.", 10+ 7);
			line(player, "<str>I also had to prevent the Guar from seeing that I was up", 10+ 7);
			line(player, "<str>to, by getting him drunk.", 11+ 7);
			line(player, "<str>With the guard disposed of, I used my rope to tie up Lady", 11+ 7);
			line(player, "<str>Keli in a cupboard, so I could disguise the Prince.", 12+ 7);
			line(player, "<str>I then used a wig, and some skin paste to make the", 13+ 7);
			line(player, "<str>prince look like Lady Keli so he could escape to his", 14+ 7);
			line(player, "<str>freedom with Leela after unlocking his cell door.", 15+ 7);
			line(player, "<str>Hassan the chancellor rewarded me for all of my help.", 16+ 7);
			line(player, "<str>I am now a friend of Al-Kharid and may pass through the", 17+ 7);
			line(player, "<str>gate leading between Lumbridge and Al-Kharid for free", 18+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!", 19+ 7);
			break;
		}
	}

	/**
	 * Ceck if the player has the item.
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
		player.getPacketDispatch().sendString("3 Quests Points", 277, 8 + 2);
		player.getPacketDispatch().sendString("700 Coins", 277, 9 + 2);
		player.getPacketDispatch().sendString("You have completed the Prince Ali Rescue Quest!", 277, 2 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(995, 20, 230, 277, 3 + 2);
		if (!player.getInventory().add(COINS)) {
			GroundItemManager.create(COINS, player);
		}
		player.removeAttribute("guard-drunk");
	}


}
