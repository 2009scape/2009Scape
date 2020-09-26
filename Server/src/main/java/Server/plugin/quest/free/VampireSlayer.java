package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.item.Item;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the vampire quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class VampireSlayer extends Quest {

	/**
	 * Constructs a new {@code VampireSlayer} {@code Object}.
	 */
	public VampireSlayer() {
		super(
			"Vampire Slayer",
			30,
			29,
			3,
			178, 0, 1, 3
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (getStage(player)) {
			case 0:
				writeJournal(player,
					"I can start this quest by speaking to <red>Morgan who is in",
					"<red>Draynor Village.",
					"",
					"Requirements:",
					"Must be able to kill a level 34 <red>Vampire.");
				break;
			case 10:
				line = writeJournal(player, true,
					"I spoke to Morgan in Draynor Village. He told me that the",
					"locals are being attacked by a terrifying Vampire!",
					"");
				writeJournal(player, line,
					"I need to speak to <red>Dr Harlow <blue>who can normally be found in",
					"the <red>Blue Moon Inn <blue>in <red>Varrock.");
				break;
			case 20:
				line = writeJournal(player, true,
					"I spoke to Morgan in Draynor Village. He told me that the",
					"locals are being attacked by a terrifying Vampire!",
					"",
					"I have spoken to Dr Harlow. He seemed terribly drunk, and",
					"he kept asking me to buy him drinks.",
					"");
				writeJournal(player, line,
					"I should see what advice <red>Dr Harlow <blue>can give me about killing",
					"<red>Vampires.",
					"When I'm ready, I should go to <red>Draynor Manor<blue>, north of",
					"Draynor to kill the <red>Vampire <blue>that's living in the basement."
				);
				break;
			case 30:
				line = writeJournal(player, true,
					"I spoke to Morgan in Draynor Village. He told me that the",
					"locals are being attacked by a terrifying Vampire!",
					"",
					"I have spoken to Dr Harlow. He seemed terribly drunk, and",
					"he kept asking me to buy him drinks.",
					"",
					"Dr Harlow gave me a stake to finish off the Vampire then",
					"I'm fighting it. I've got a hammer to drive the stake deep",
					"into the Vampire's chest, and I have some garlic which",
					"should weaken the Vampire.");
				writeJournal(player, line,
					"When I'm ready, I should go to <red>Draynor Manor<blue>, north of",
					"Draynor to kill the <red>Vampire <blue>that's living in the basement.");
				break;
			case 100:
				line = writeJournal(player, true,
					"I spoke to Morgan in Draynor Village. He told me that the",
					"locals are being attacked by a terrifying Vampire!",
					"",
					"I have spoken to Dr Harlow. He seemed terribly drunk, and",
					"he kept asking me to buy him drinks.",
					"",
					"I have killed the Vampire, Count Draynor. Draynor Village is",
					"now safe!");
				writeJournal(player, ++line,
					"<col=FF0000>QUEST COMPLETE!");
				break;
		}
	}

	@Override
	public Item getRewardComponentItem() {
		return new Item(1549);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(Skills.ATTACK, 4825),
		};
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
