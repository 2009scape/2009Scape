package plugin.quest.free;

import core.game.node.entity.player.link.quest.QuestReward;
import core.game.node.entity.player.link.quest.QuestRewardComponentItem;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the sheep shearer quest.
 * @author 'Veixa
 */
@InitializablePlugin
public class SheepShearer extends Quest {

	/**
	 * Constructs a new {@code SheepShearer} {@code Object}.
	 */
	public SheepShearer() {
		super(
			"Sheep Shearer",
			28,
			27,
			1,
			179, 0, 20, 21);
	}

	/**
	 * Represents the wool item.
	 */
	public static final Item WOOL = new Item(1759);

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getInterfaceManager().closeChatbox();
	}

	@Override
	public QuestRewardComponentItem getRewardComponentItem() {
		return new QuestRewardComponentItem(1735, 240);
	}

	@Override
	public QuestReward[] getQuestRewards(Player player) {
		return new QuestReward[]{
			new QuestReward(Skills.CRAFTING, 150),
			new QuestReward(new Item(995, 60)),
		};
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		int line;
		switch (stage) {
			case 0:
				writeJournal(player,
					"I can start this quest by speaking to <red>Farmer Fred <blue>at his",
					"<red>farm <blue>just a little way <red>North West of Lumbridge"
				);
				break;
			case 10:
			case 90:
				line = writeJournal(player, true,
					"I can start this quest by speaking to Farmer Fred at his",
					"farm just a little way North West of Lumbridge",
					""
				);
				int woolLeftToCollect = getWoolCollect(player);
				if (woolLeftToCollect == 0) {
					writeJournal(player, line,
						"I have enough <red>balls of wool <blue>to give <red>Fred <blue>and get my <red>reward",
						"<red>money!"
					);
				} else {
					writeJournal(player, line,
						"I need to collect " + woolLeftToCollect + " <red>more balls of wool."
					);
				}
				break;
			case 100:
				line = writeJournal(player, true,
					"I brought Farmer Fred 20 balls of wool, and he paid me for",
					"it!");
				writeJournal(player, ++line,
					"<col=FF0000>QUEST COMPLETE!</col>");
				break;
			}
	}

	/**
	 * Method used to get the wool given.
	 * @param player the player.
	 * @return the wool given.
	 */
	public static int getWoolGiven(final Player player) {
		return player.getAttribute("sheep-shearer:wool", 0);
	}

	/**
	 * Method used to get the wool left to give.
	 * @param player the player.
	 * @return the wool.
	 */
	public static int getWoolLeft(final Player player) {
		int left = 20 - getWoolGiven(player);
		return left;
	}

	/**
	 * Method used to add wool to the current amount given.
	 * @param player the player.
	 * @return <code>True</code> if added.
	 */
	public static boolean addWool(final Player player) {
		final int removeAmount = getWoolRemove(player);
		if (player.getInventory().remove(new Item(1759, removeAmount))) {
			player.setAttribute("sheep-shearer:wool", getWoolGiven(player) + removeAmount);
			return true;
		}
		return false;
	}

	/**
	 * Method used to get the wool to remove.
	 * @param player the player.
	 * @return the amount that can be removed from the inventory.
	 */
	public static int getWoolRemove(final Player player) {
		int woolAmount = player.getInventory().getAmount(WOOL);
		int remove = getWoolLeft(player) - (getWoolLeft(player) - woolAmount);
		int realRemove = remove > 20 ? remove - (remove - getWoolLeft(player)) : remove;
		if (realRemove > getWoolLeft(player)) {
			realRemove = getWoolLeft(player);
		}
		return realRemove;
	}

	/**
	 * Method used to get the wool left to collect.
	 * @param player the player.
	 * @return the wool.
	 */
	public static int getWoolCollect(final Player player) {
		return 20 - (getWoolGiven(player) + getWoolRemove(player));
	}

	@Override
	public Quest newInstance(Object object) {
		// TODO Auto-generated method stub
		return this;
	}

}
