package plugin.quest.free;

import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
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
	 * @param player The player to construct the class for.
	 */
	public SheepShearer() {
		super("Sheep Shearer", 28, 27, 1, 179, 0, 20, 21);
	}

	/**
	 * Represents the wool item.
	 */
	public static final Item WOOL = new Item(1759);

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("150 Crafting XP", 277, 9 + 2); 
		player.getPacketDispatch().sendString("60 coins", 277, 10 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(1735, 240, 277, 3 + 2);
		player.getSkills().addExperience(Skills.CRAFTING, 150);
		player.getInterfaceManager().closeChatbox();
		if (player.getInventory().freeSlots() == 0) {
			GroundItemManager.create(new Item(995, 60), player.getLocation());
		} else {
			player.getInventory().add(new Item(995, 60));
		}
		player.getPacketDispatch().sendMessage("Congratulations! Quest complete!");
	}

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		if (stage < 10) {
			player.getPacketDispatch().sendString("<col=08088A>I can start this quest by speaking to <col=8A0808>Farmer Fred</col> <col=08088A>at his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>farm</col> <col=08088A>just a little way <col=8A0808>North West of Lumbridge", 275, 5+ 7);
			return;
		}
		if (stage == 10 || stage == 90) {
			player.getPacketDispatch().sendString("<str>I can start this quest by speaking to Farmer Fred at his", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>farm just a little way North West of Lumbridge</str>", 275, 5+ 7);
			int wool = getWoolCollect(player);
			if (wool == 0) {
				player.getPacketDispatch().sendString("<col=08088A>I have enough <col=8A0808>balls of wool</col> <col=08088A>to give <col=8A0808>Fred</col> <col=08088A> and get my</col> <col=8A0808>reward", 275, 7+ 7);
				player.getPacketDispatch().sendString("<col=8A0808>money!", 275, 8+ 7);
			}
			if (wool != 0) {
				player.getPacketDispatch().sendString("<col=08088A>I need to collect " + wool + " <col=8A0808>more balls of wool.", 275, 7+ 7);
			}
		}
		if (stage == 100) {
			player.getPacketDispatch().sendString("<str>I brought Farmer Fred 20 balls of wool, and he paid me for", 275, 4+ 7);
			player.getPacketDispatch().sendString("<str>it!</str>", 275, 5+ 7);
			player.getPacketDispatch().sendString("<col=FF0000>QUEST COMPLETE!</col>", 275, 7+ 7);
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
	 * Method used to add wool to the current amoutn given.
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
