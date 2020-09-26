package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * Represents the Doric's Quest
 * @author Vexia
 * 
 */
@InitializablePlugin
public class DoricsQuest extends Quest {

	/**
	 * Constructs a new {@Code DoricsQuest} {@Code Object}
	 */
	public DoricsQuest() {
		super(
			"Doric's Quest",
			17,
			16,
			1,
			31, 0, 1, 100
		);
	}
	
	@Override
	public Quest newInstance(Object object) {
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
		case 0:
			writeJournal(player,
				"I can start this quest by speaking to <red>Doric <blue>who is <red>North of",
				"<red>Falador.",
				"",
				"There aren't any requirements but <red>Level 15 Mining <blue>will help.");
			break;
		case 1:
			line = writeJournal(player, true,
				"I have spoken to <red>Doric");
			line = writeJournal(player, ++line,
				"I need to collect some items and bring them to <red>Doric:");
			line = writeJournal(player, line, player.getInventory().contains(434, 6), "<red>6 Clay");
			line = writeJournal(player, line, player.getInventory().contains(436, 4), "<red>4 Copper Ore");
			writeJournal(player, line, player.getInventory().contains(440, 2), "<red>2 Iron Ore");
			break;
		case 100:
			line = writeJournal(player, true,
				"I have spoken to <red>Doric<blue>.",
				"",
				"I have collected some Clay, Copper Ore, and Iron Ore",
				"",
				"Doric rewarded me for all my hard work",
				"I can now use Doric's Anvils whenever I want");
			writeJournal(player, line,
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		if (!player.getInventory().add(new Item(995, 180))) {
			GroundItemManager.create(new Item(995, 180), player);
		}
		player.getSkills().addExperience(Skills.MINING, 1300);
		player.getInterfaceManager().closeChatbox();
	}

	@Override
	public String getRewardComponentTitle() {
		return "You have completed Doric's Quest!";
	}

	@Override
	public Item getRewardComponentItem() {
		return new Item(1269);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[] {
			new QuestReward(Skills.MINING, 1300),
			new QuestReward(new Item(995, 180)),
			new QuestReward("Use of Doric's Anvils"),
		};
	}
}
