package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the witch's potion quest.
 * @author 'Vexia
 */
@InitializablePlugin
public class WitchsPotion extends Quest {

	/**
	 * Constructs a new {@code WitchsPotion} {@code Object}.
	 */
	public WitchsPotion() {
		super(
			"Witch's Potion",
			31,
			30,
			1,
			67, 0, 1, 3
		);
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (getStage(player)) {
		case 0:
			writeJournal(player,
				BLUE + "I can start this quest by speaking to " + RED + "Hetty " + BLUE + "in her house in",
				RED + "Rimmington" + BLUE + ", West of " + RED + "Port Sarim");
			break;
		case 20:
			writeJournal(player,
				"<str>I spoke to Hetty in her house at Rimmington. hetty told me",
				"<str>she could increase my magic power if I can bring her",
				"<str>certain ingredients for a potion.",
				"",
				BLUE + "Hetty needs me to bring her the following:",
				player.getInventory().contains(1957, 1) ? "<str>I have an onion with me" : "<red>An onion",
				player.getInventory().contains(300, 1) ? "<str>I have a rat's tail with me" : "<red>A rat's tail",
				player.getInventory().contains(2146, 1) ? "<str>I have a piece of burnt meat with me" : "<red>A piece of burnt meat",
				player.getInventory().contains(221, 1) ? "<str>I have an eye of newt with me": "<red>An eye of newt"
			);
			break;
		case 40:
			writeJournal(player,
				"<str>I brought her an onion, a rat's tail, a piece of burnt meat",
				"<str>and eye of newt which she used to make a potion.",
				"",
				BLUE + "I should drink from the " + RED + "cauldron" + BLUE + " and improve my magic!");
			break;
		case 100:
			writeJournal(player,
				"<str>I brought her an onion, a rat's tail, a piece of burnt meat",
				"<str>and an eye of newt which she used to make a potion.",
				"",
				"<str>I drank from the cauldron and my magic power increased!",
				"",
				"<col=FF0000>QUEST COMPLETE!");
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getInterfaceManager().closeChatbox();
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(221, 240);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.MAGIC, 325),
		};
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}
}
