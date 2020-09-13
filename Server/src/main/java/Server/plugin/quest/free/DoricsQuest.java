package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
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
			new int[] {31, 0, 1, 100}
		);
	}
	
	@Override
	public Quest newInstance(Object object) {
		return this;
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (stage) {
		case 0:
			writeJournal(player,
				"I can start this quest by speaking to <red>Doric <blue>who is <red>North of",
				"<red>Falador.",
				"",
				"There aren't any requirements but <red>Level 15 Mining <blue>will help.");
			break;
		case 1:
			writeJournal(player,
				"<str>I have spoken to <red>Doric",
				"",
				"I need to collect some items and bring them to <red>Doric:",
				(player.getInventory().contains(434, 6) ? "<str>" : "") + "<red>6 Clay",
				(player.getInventory().contains(436, 4) ? "<str>" : "") + "<red>4 Copper Ore",
				(player.getInventory().contains(440, 2) ? "<str>" : "") + "<red>2 Iron Ore");
			break;
		case 100:
			writeJournal(player,
				"<str>I have spoken to <red>Doric<blue>.",
				"",
				"<str>I have collected some Clay, Copper Ore, and Iron Ore",
				"",
				"<str>Doric rewarded me for all my hard work",
				"<str>I can now use Doric's Anvils whenever I want",
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
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(1269, 240);
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
