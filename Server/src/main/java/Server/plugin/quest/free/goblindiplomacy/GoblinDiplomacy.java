package plugin.quest.free.goblindiplomacy;

import core.game.node.entity.player.link.quest.QuestReward;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
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
	 * Represents the orange goblin mail.
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
		super(
			"Goblin Diplomacy",
			20,
			19,
			5
		);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new GDiplomacyCutscene(), new GoblinDiplomacyPlugin(), new GrubfootDialogue());
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				"I can start this quest by speaking to <red>Generals Wartface",
				"<red>and Bentnoze <blue>in the <red>Goblin Village.",
				"There are no requirements for this quest.");
			break;
		case 10:
			line = writeJournal(player, true,
				"I spoke to Generals Wartface and Bentnoze in the Goblin",
				"Village and found that the goblins were on the brink of civil",
				"war over the colour of their armour. I offered to help the",
				"generals by finding another colour that they both like."
			);
			if (player.getInventory().containsItem(ORANGE_MAIL)) {
				writeJournal(player, line,
					"I have some <red>Orange Goblin Armour. <blue>I should show it to the",
					"generals."
				);
			} else {
				writeJournal(player, line,
					"I should bring the goblins some <red>Orange Goblin Armour",
					"Maybe the generals will know where to get some."
				);
			}
			break;
		case 20:
			line = writeJournal(player, true,
				"I spoke to Generals Wartface and Bentnoze in the Goblin",
				"Village and found that the goblins were on the brink of civil",
				"war over the colour of their armour. I offered to help the",
				"generals by finding another colour that they both like.",
				"",
				"I brought the goblins some orange goblin armour, but they",
				"didn't like it."
			);
			if (player.getInventory().containsItem(BLUE_MAIL)) {
				writeJournal(player, line,
					"I have some <red>Blue Goblin Armour. <blue>I should show it to the",
					"generals."
				);
			} else {
				writeJournal(player, line,
					"I should bring the goblins some <red>Blue Goblin Armour",
					"Maybe the generals will know where to get some."
				);
			}
			break;
		case 30:
			line = writeJournal(player, true,
				"I spoke to Generals Wartface and Bentnoze in the Goblin",
				"Village and found that the goblins were on the brink of civil",
				"war over the colour of their armour. I offered to help the",
				"generals by finding another colour that they both like.",
				"",
				"I brought the goblins some orange goblin armour, but they",
				"didn't like it.",
				"",
				"I brought the goblins some blue goblin armour, but they",
				"didn't like it."
			);
			if (player.getInventory().containsItem(GOBLIN_MAIL)) {
				writeJournal(player, line,
					"I have some <red>Brown Goblin Armour. <blue>I should show it to the",
					"generals."
				);
			} else {
				writeJournal(player, line,
					"I should bring the goblins some <red>Brown Goblin Armour",
					"Maybe the generals will know where to get some."
				);
			}
			break;
		case 100:
			line = writeJournal(player, true,
				"I spoke to Generals Wartface and Bentnoze in the Goblin",
				"Village and found that the goblins were on the brink of civil",
				"war over the colour of their armour. I offered to help the",
				"generals by finding another colour that they both like.",
				"",
				"I brought the goblins some orange goblin armour, but they",
				"didn't like it.",
				"",
				"I brought the goblins some blue goblin armour, but they",
				"didn't like it.",
				"",
				"Unfortunately the goblins were very stupid, and it turned",
				"out that they liked the original colour the most. That's goblins",
				"for you I guess.");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!</col>");
			break;
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

	@Override
	public Item getRewardComponentItem() {
		return new Item(288);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(Skills.CRAFTING, 200),
			new QuestReward(GOLD_BAR, "A gold bar"),
		};
	}

}
