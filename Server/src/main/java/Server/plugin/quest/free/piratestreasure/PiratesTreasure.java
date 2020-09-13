package plugin.quest.free.piratestreasure;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
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
	 * Represents the casket rewards (pirates treasure).
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
		super(
			"Pirate's Treasure",
			23,
			22,
			2,
			71, 0, 1, 4
		);
	}

	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new PiratesTreasurePlugin());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line = JOURNAL_TEXT_START;
		switch (stage) {
		case 0:
			writeJournal(player,
				BLUE + "I can start this quest by speaking to " + RED + "Redbeard Frank " + BLUE + "who",
				BLUE + "is at " + RED + "Port Sarim.",
				BLUE + "There aren't any requirements for this quest.");
			break;
		case 10:
			if (!player.getInventory().containsItem(KARAMJAN_RUM)) {
				writeJournal(player,
					BLUE + "I have spoken to " + RED + "Redbeard Frank. " + BLUE + "He has agreed to tell me",
					BLUE + "the location of some " + RED + "treasure " + BLUE + "for some " + RED + "Karamjan rum.",
					BLUE + "I have taken employment on the " + RED + "banana plantation" + BLUE + ", as the",
					RED + "Customs Officers " + BLUE + "might not notice the " + RED + "rum " + BLUE + "if it is covered",
					BLUE + "in " + RED + "bananas.",
					"",
					BLUE + "Now all I need is some " + RED + "rum " + BLUE + "to hide in the next crate",
					BLUE + "destined for " + RED + "Wydin's store" + BLUE + "...");
			} else {
				writeJournal(player,
					BLUE + "I have spoken to " + RED + "Redbeard Frank " + BLUE + ". He has agreed to tell me",
					BLUE + "the location of some " + RED + "treasure " + BLUE + "for some " + RED + "Karamjan rum.",
					"",
					BLUE + "I have the " + RED + "Karamjan rum. " + BLUE + "I should take it to " + RED + "Redbeard Frank.");
			}
			break;
		case 20:
			line = writeJournal(player, line,
				"<str>I have smuggled some rum off Karamja, and retrieved it",
				"<str>from the back room of Wydin's shop.",
				"",
				BLUE + "I have given the rum to " + RED + "Redbeard Frank. " + BLUE + "He has told me",
				BLUE + "that the " + RED + "treasure " + BLUE + "is hidden in the chest in the upstairs",
				BLUE + "room of the " + RED + "Blue Moon Inn " + BLUE + "in " + RED + "Varrock.",
				""
			);
			if (player.getAttributes().containsKey("pirate-read")) {
				writeJournal(player, line,
					BLUE + "The note reads " + RED + "'Visit the city of the White Knights. In the",
					RED + "park, Saradomin points to the X which marks the spot.'",
					""
				);
				if (!player.getInventory().containsItem(PIRATE_MESSAGE) && !player.getBank().containsItem(PIRATE_MESSAGE)) {
					writeJournal(player, line,
						BLUE + "It's a good job I remembered that, as I have lost the " + RED + "note."
					);
				}
				break;
			}
			if (player.getInventory().containsItem(PIRATE_MESSAGE)) {
				writeJournal(player, line,
					BLUE + "I have opened the chest in the " + RED + "Blue Moon" + BLUE + ", and found a",
					RED + "note " + BLUE + "inside. I think it will tell me where to dig.");
				break;
			}
			if (player.getInventory().containsItem(KEY) || player.getBank().containsItem(KEY)) {
				writeJournal(player, line,
					BLUE + "I have a " + RED + "key " + BLUE + "that can be used to unlock the chest that",
					BLUE + "holds the treasure.");
			} else {
				writeJournal(player, line,
					BLUE + "I have lost the " + RED + "key " + BLUE + "that " + RED + "Redbeard Frank " + BLUE + "gave me. I should",
					BLUE + "see if he has another.");
			}
			break;
		case 100:
			writeJournal(player,
				"<str>The note reads 'Visit the city of the White Knights. In the",
				"<str>park, Saradomin points to the X which marks the spot.'",
				"",
				"<col=FF0000>QUEST COMPLETE!",
				"",
				BLUE + "I've found a treasure, gained 2 Quest Points and gained",
				BLUE + "access to the Pay-fare option to travel to and from", 
				BLUE + "Karamja!");
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.removeAttribute("gardener-attack");
		player.removeAttribute("pirate-read");
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(7956, 230);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(CASKET, "One-Eyed Hector's Treasure"),
			new QuestReward("Chest"),
			new QuestReward("You can also use the Pay-"),
			new QuestReward("fare option to go to and from"),
			new QuestReward("Karamja")
		};
	}
}
