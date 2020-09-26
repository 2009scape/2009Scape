package plugin.quest.members;

import core.game.node.entity.player.link.quest.QuestReward;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the gertrudes fortress quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class GertrudesCat extends Quest {

	/**
	 * The kitten that is given to the player at the end of the quest.
	 */
	Item kitten;

	/**
	 * Constructs a new {@code GertrudesCat} {@code Object}.
	 */
	public GertrudesCat() {
		super(
			"Gertrude's Cat",
			67,
			66,
			1,
			180, 0, 1, 100
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				"I can start this quest by speaking to <red>Gertrude.",
				"She can be found to the <red>west of Varrock.");
			break;
		case 10:
			line = writeJournal(player, true,
				"I accepted the challenge of finding Gertrude's lost cat.");
			writeJournal(player, line,
				"I need <red>to speak to Shilop and Wilough <blue>at the <red>marketplace.");
			break;
		case 20:
			line = writeJournal(player, true,
				"I accepted the challenge of finding Gertrude's lost cat.",
				"I spoke to Shilop, Gertrude's son.");
			writeJournal(player, line,
				"I need to <red>go to their play area <blue>and <red>find the lost cat and",
				"<red>return it to Gertrude.");
			break;
		case 30:
			line = writeJournal(player, true,
				"I accepted the challenge of finding Gertrude's lost cat.",
				"I spoke to Shilop, Gertrude's son.",
				"I found the lost cat but it won't come back.");
			writeJournal(player, line,
				"<red>I still need to <red>get her to follow me home.");
			break;
		case 40:
		case 50:
			line = writeJournal(player, true,
				"I accepted the challenge of finding Gertrude's lost cat.",
				"I spoke to Shilop, Gertrude's son.",
				"I found the lost cat but it won't come back.",
				"I gave the cat milk and sardines.");
			writeJournal(player, line,
				"",
				"I still need to <red>get her to follow me home.");
			break;
		case 60:
			line = writeJournal(player, true,
				"I accepted the challenge of finding Gertrude's lost cat.",
				"I spoke to Shilop, Gertrude's son.",
				"I found the lost cat but it won't come back.",
				"I gave the cat milk and sardines.");
			writeJournal(player, line,
				"She ran off home.");
			break;
		case 100:
			line = writeJournal(player, true,
				"I helped Gertrude to find her lost cat,",
				"I fed it and returned her missing kitten,",
				"Gertrude gave me my very own pet for a reward.");
			writeJournal(player, ++line,
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public Item getRewardComponentItem() {
		return kitten;
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward("A kitten!"),
			new QuestReward(Skills.COOKING, 1525),
			new QuestReward( new Item(1897), "A chocolate cake"),
			new QuestReward( new Item(2003), "A bowl of stew"),
			new QuestReward("Raise cats."),
		};
	}

	@Override
	public void finish(Player player) {
		kitten = getKitten();
		super.finish(player);
		if (player.getInventory().add(kitten, player)) {
			player.getFamiliarManager().summon(kitten, true);
		}
	}

	/**
	 * Method used to get a random kitten.
	 * @return the item.
	 */
	public Item getKitten() {
		return new Item(RandomFunction.random(1555, 1560));
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
