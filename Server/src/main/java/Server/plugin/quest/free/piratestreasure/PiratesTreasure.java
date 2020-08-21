package plugin.quest.free.piratestreasure;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the pirates treasure quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public final class PiratesTreasure extends Quest {

	/**
	 * Represents the message component for pirates treasure quest.
	 */
	public static final Component MESSAGE_COMPONENT = new Component(222);

	/**
	 * Represents the cakset rewards (pirates treasure).
	 */
	public static final Item[] CASKET_REWARDS = new Item[] { new Item(995, 450), new Item(1635), new Item(1605) };

	/**
	 * Represents the karamjan rum item.
	 */
	public static final Item KARAMJAN_RUM = new Item(431);

	/**
	 * Represents the chest key item.
	 */
	public static final Item KEY = new Item(432);

	/**
	 * Represents the pirate message item.
	 */
	public static final Item PIRATE_MESSAGE = new Item(433);

	/**
	 * Represents the casket item.
	 */
	public static final Item CASKET = new Item(7956);
	
	/**
	 * Constructs a new {@Code PiratesTreasure} {@Code Object}
	 */
	public PiratesTreasure() {
		super("Pirate's Treasure", 23, 22, 2, 71, 0, 1, 4);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new PiratesTreasurePlugin());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to " + RED + "Redbeard Frank " + BLUE + "who", 275, 4+ 7);
			player.getPacketDispatch().sendString(BLUE + "is at " + RED + " Port Sarim.", 275, 5+ 7);
			player.getPacketDispatch().sendString(BLUE + "There aren't any requirements for this quest.", 275, 7+ 7);
			break;
		case 10:
			if (!player.getInventory().containsItem(KARAMJAN_RUM)) {
				line(player, BLUE + "I have spoken to " + RED + " Redbeard Frank." + BLUE + " He has agreed to tell me", 4+ 7);
				line(player, BLUE + "the location of some " + RED + " treasure " + BLUE + "for some " + RED + "Karamja Rum.", 5+ 7);
				line(player, BLUE + "I have taken employment on the " + RED + "banana plantation " + BLUE + ", as the", 6+ 7);
				line(player, RED + "Customs Officers " + BLUE + "might not notice the " + RED + "rum " + BLUE + "if it is covered", 7+ 7);
				line(player, BLUE + "in " + RED + "bananas.", 8+ 7);
				line(player, BLUE + "Now all I need is some " + RED + " rum " + BLUE + "to hide in the next crate", 10+ 7);
				line(player, BLUE + "destined for " + RED + "Wydin's store" + BLUE + "...", 11+ 7);
			} else {
				line(player, BLUE + "I have spoken to " + RED + "Redbeard Frank " + BLUE + ". He has agreed to tell me", 5+ 7);
				line(player, BLUE + "the location of some " + RED + "treasure " + BLUE + "for some " + RED + "Karamja Rum.", 6+ 7);
				line(player, BLUE + "I have the " + RED + "Karamja Rum. " + BLUE + "I should take it to " + RED + "Redbeard Frank.", 8+ 7);
			}
			break;
		case 20:
			line(player, "<str>I have smuggled some rum off Karamja, and retrieved it", 4+ 7);
			line(player, "<str>from the back room of Wydin's shop.", 5+ 7);
			line(player, BLUE + "I have given the rum to " + RED + "Redbeard Frank." + BLUE + " He has told me", 7+ 7);
			line(player, BLUE + "that the " + RED + "treasure " + BLUE + "is hidden in the chest in the upstairs", 8+ 7);
			line(player, BLUE + "room of the " + RED + "Blue Moon Inn " + BLUE + "in " + RED + "Varrock.", 9+ 7);
			if (player.getAttributes().containsKey("pirate-read")) {
				line(player, BLUE + "The note reads " + RED + "'Visit the city of the White Knights. In the", 11+ 7);
				line(player, RED + "park, Saradomin points to the X which marks the spot.'", 12+ 7);
				if (!player.getInventory().containsItem(PIRATE_MESSAGE) && !player.getBank().containsItem(PIRATE_MESSAGE)) {
					line(player, BLUE + "It's a good job I remembered that, as I have lost the " + RED + "note.", 14+ 7);
				}
				return;
			}
			if (player.getInventory().containsItem(PIRATE_MESSAGE)) {
				line(player, BLUE + "I have opened the chest in the " + RED + "Blue Moon" + BLUE + ", and found a", 11+ 7);
				line(player, RED + "note " + BLUE + "inside. I think it will tell me where to dig.", 12+ 7);
				return;
			}
			if (player.getInventory().containsItem(KEY)) {
				line(player, BLUE + "I have a " + RED + "key " + BLUE + "that can be used to unlock the chest that", 11+ 7);
				line(player, BLUE + "holds the treasure.", 12+ 7);
			} else {
				line(player, BLUE + "I have lost the " + RED + "key " + BLUE + "that " + RED + "Readbeard Frank " + BLUE + "gave me. I should", 11+ 7);
				line(player, BLUE + "see if he has another.", 12+ 7);
			}
			break;
		case 100:
			line(player, "<str>The note reads 'Visit the city of the White Knights. In the", 4+ 7);
			line(player, "<str>park, Saradomin points to the X which marks the spot.'", 5+ 7);
			line(player, "<col=FF0000>QUEST COMPLETE!", 7+ 7);
			line(player, BLUE + "I've found a treasure, gained 2 Quest Points and gained", 9+ 7);
			line(player, BLUE + "access to the Pay-fare option to travel to and from", 10+ 7);
			line(player, BLUE + "Karamja!", 11+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("2 Quest Points", 277, 8 + 2);
		player.getPacketDispatch().sendString("One-Eyes Hector's Treasure", 277, 9 + 2);
		player.getPacketDispatch().sendString("Chest", 277, 10 + 2);
		player.getPacketDispatch().sendString("You can also use the Pay-", 277, 11 + 2);
		player.getPacketDispatch().sendString("fare option to go to and from", 277, 12 + 2);
		player.getPacketDispatch().sendString("Karamja", 277, 13 + 2);
		player.getPacketDispatch().sendString("You have completed the Pirate's Treasure Quest!", 277, 2 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(7956, 230, 277, 3 + 2);
		player.removeAttribute("gardener-attack");
		player.removeAttribute("pirate-read");
		if (!player.getInventory().add(CASKET)) {
			GroundItemManager.create(CASKET, player);
		}
	}

}
