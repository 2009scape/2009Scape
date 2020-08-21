package plugin.quest.members.lostcity;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Plugin;

/**
 * Handles the dramen staff cutting plugin.
 * @author Vexia
 */
public final class DramenStaffPlugin extends UseWithHandler {

	/**
	 * The dramen branch item.
	 */
	private static final Item DRAMEN_BRANCH = new Item(771);

	/**
	 * The dramen staff item.
	 */
	private static final Item DRAMEN_STAFF = new Item(772);

	/**
	 * Constructs a new {@code DramenStaffPlugin} {@code Object}.
	 */
	public DramenStaffPlugin() {
		super(DRAMEN_BRANCH.getId());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(946, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (player.getInventory().remove(DRAMEN_BRANCH)) {
			player.getInventory().add(DRAMEN_STAFF);
			player.getPacketDispatch().sendMessage("You carve the branch into a staff.");
		}
		player.lock(1);
		return true;
	}

}
