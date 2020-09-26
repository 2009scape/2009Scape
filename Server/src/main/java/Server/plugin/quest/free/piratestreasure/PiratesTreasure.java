package plugin.quest.free.piratestreasure;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.entity.player.link.quest.QuestReward;
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
				"I can start this quest by speaking to <red>Redbeard Frank <blue>who",
				"is at <red>Port Sarim.",
				"There aren't any requirements for this quest.");
			break;
		case 10:
			if (!player.getInventory().containsItem(KARAMJAN_RUM)) {
				writeJournal(player,
					"I have spoken to <red>Redbeard Frank. <blue>He has agreed to tell me",
					"the location of some <red>treasure <blue>for some <red>Karamjan rum.",
					"I have taken employment on the <red>banana plantation<blue>, as the",
					"<red>Customs Officers <blue>might not notice the <red>rum <blue>if it is covered",
					"in <red>bananas.",
					"",
					"Now all I need is some <red>rum <blue>to hide in the next crate",
					"destined for <red>Wydin's store<blue>...");
			} else {
				writeJournal(player,
					"I have spoken to <red>Redbeard Frank <blue>. He has agreed to tell me",
					"the location of some <red>treasure <blue>for some <red>Karamjan rum.",
					"",
					"I have the <red>Karamjan rum. <blue>I should take it to <red>Redbeard Frank.");
			}
			break;
		case 20:
			line = writeJournal(player, line, true,
				"I have smuggled some rum off Karamja, and retrieved it",
				"from the back room of Wydin's shop.",
				"");
			line = writeJournal(player, line,
				"I have given the rum to <red>Redbeard Frank. <blue>He has told me",
				"that the <red>treasure <blue>is hidden in the chest in the upstairs",
				"room of the <red>Blue Moon Inn <blue>in <red>Varrock.",
				""
			);
			if (player.getAttributes().containsKey("pirate-read")) {
				writeJournal(player, line,
					"The note reads <red>'Visit the city of the White Knights. In the",
					"<red>park, Saradomin points to the X which marks the spot.'",
					""
				);
				if (!player.getInventory().containsItem(PIRATE_MESSAGE) && !player.getBank().containsItem(PIRATE_MESSAGE)) {
					writeJournal(player, line,
						"It's a good job I remembered that, as I have lost the <red>note."
					);
				}
				break;
			}
			if (player.getInventory().containsItem(PIRATE_MESSAGE)) {
				writeJournal(player, line,
					"I have opened the chest in the <red>Blue Moon<blue>, and found a",
					"<red>note <blue>inside. I think it will tell me where to dig.");
				break;
			}
			if (player.getInventory().containsItem(KEY) || player.getBank().containsItem(KEY)) {
				writeJournal(player, line,
					"I have a <red>key <blue>that can be used to unlock the chest that",
					"holds the treasure.");
			} else {
				writeJournal(player, line,
					"I have lost the <red>key <blue>that <red>Redbeard Frank <blue>gave me. I should",
					"see if he has another.");
			}
			break;
		case 100:
			line = writeJournal(player, true,
				"The note reads 'Visit the city of the White Knights. In the",
				"park, Saradomin points to the X which marks the spot.'");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!",
				"",
				"I've found a treasure, gained 2 Quest Points and gained",
				"access to the Pay-fare option to travel to and from",
				"Karamja!");
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
	public Item getRewardComponentItem() {
		return new Item(7956);
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
