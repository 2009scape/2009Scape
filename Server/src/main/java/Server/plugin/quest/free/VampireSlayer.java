package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
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
		switch (getStage(player)) {
			case 0:
				writeJournal(player,
					BLUE + "I can start this quest by speaking to " + RED + "Morgan who is in",
					RED + "Draynor Village.",
					"",
					BLUE + "Requirements:",
					BLUE + "Must be able to kill a level 34 " + RED + "Vampire.");
				break;
			case 10:
				writeJournal(player,
					"<str>I spoke to Morgan in Draynor Village. He told me that the",
					"<str>locals are being attacked by a terrifying Vampire!",
					"",
					BLUE + "I need to speak to " + RED + "Dr Harlow " + BLUE + "who can normally be found in",
					BLUE + "the " + RED + " Blue Moon Inn" + BLUE + " in " + RED + "Varrock.");
				break;
			case 20:
				writeJournal(player,
					"<str>I spoke to Morgan in Draynor Village. He told me that the",
					"<str>locals are being attacked by a terrifying Vampire!",
					"",
					"<str>I have spoken to Dr Harlow. He seemed terribly drunk, and",
					"<str>he kept asking me to buy him drinks.",
					"",
					BLUE + "I should see what advice " + RED + "Dr Harlow" + BLUE + " can give me about killing",
					RED + "Vampires.",
					BLUE + "When I'm ready, I should go to " + RED + "Draynor Manor" + BLUE + ", north of",
					BLUE + "Draynor to kill the " + RED + "Vampire" + BLUE + " that's living in the basement."
				);
				break;
			case 30:
				writeJournal(player,
					"<str>I spoke to Morgan in Draynor Village. He told me that the",
					"<str>locals are being attacked by a terrifying Vampire!",
					"",
					"<str>I have spoken to Dr Harlow. He seemed terribly drunk, and",
					"<str>he kept asking me to buy him drinks.",
					"",
					"<str>Dr Harlow gave me a stake to finish off the Vampire then",
					"<str>I'm fighting it. I've got a hammer to drive the stake deep",
					"<str>into the Vampire's chest, and I have some garlic which",
					"<str>should weaken the Vampire.",
					BLUE + "When I'm ready, I should go to " + RED + "Draynor Manor" + BLUE + ", north of",
					BLUE + "Draynor to kill the " + RED + "Vampire" + BLUE + " that's living in the basement.");
				break;
			case 100:
				writeJournal(player,
					"<str>I spoke to Morgan in Draynor Village. He told me that the",
					"<str>locals are being attacked by a terrifying Vampire!",
					"",
					"<str>I have spoken to Dr Harlow. He seemed terribly drunk, and",
					"<str>he kept asking me to buy him drinks.",
					"",
					"<str>I have killed the Vampire, Count Draynor. Draynor Village is",
					"<str>now safe!",
					"",
					"<col=FF0000>QUEST COMPLETE!");
				break;
		}
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(1549, 260);
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
