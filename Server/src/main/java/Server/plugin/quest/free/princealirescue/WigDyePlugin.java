package plugin.quest.free.princealirescue;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Plugin;

/**
 * Represents the plugin used to dye a wig yellow.
 * @author 'Vexia
 * @version 1.0
 */
public final class WigDyePlugin extends UseWithHandler {

	/**
	 * Represents the yellow dye item.
	 */
	private static final Item YELLOW_DYE = new Item(1765);

	/**
	 * Represents the wig item.
	 */
	private static final Item WIG = new Item(2421);

	/**
	 * Represents the yellow wig item.
	 */
	private static final Item YELLOW_WIG = new Item(2419);

	/**
	 * Constructs a new {@code WigDyePlugin} {@code Object}.
	 */
	public WigDyePlugin() {
		super(1765);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(2421, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (player.getInventory().remove(WIG, YELLOW_DYE)) {
			player.getInventory().add(YELLOW_WIG);
			player.getPacketDispatch().sendMessage("You dye the wig blonde.");
		}
		return true;
	}

}
