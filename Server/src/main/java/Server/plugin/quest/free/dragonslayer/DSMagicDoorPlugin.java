package plugin.quest.free.dragonslayer;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.plugin.Plugin;

/**
 * Represents the dragon slayer magic door plugin.
 * @author Vexia
 * 
 */
public final class DSMagicDoorPlugin extends UseWithHandler {

	/**
	 * Represents the location to go to.
	 */
	private static final Location LOC = Location.create(3049, 9840, 0);

	/**
	 * Represents the ids of the items.
	 */
	private static final int[] IDS = new int[] { 301, 1791, 950, 1907 };

	/**
	 * Constructs a new {@code DSMagicDoorPlugin} {@code Object}.
	 */
	public DSMagicDoorPlugin() {
		super(301, 1791, 950, 1907);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(25115, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) < 20) {
			return true;
		}
		if (player.getInventory().remove(event.getUsedItem())) {
			player.getPacketDispatch().sendMessage("You put " + event.getUsedItem().getName().toLowerCase() + " into the opening in the door.");
			int index = 0;
			for (int i = 0; i < IDS.length; i++) {
				if (IDS[i] == event.getUsedItem().getId()) {
					index = i;
					break;
				}
			}
			player.getSavedData().getQuestData().getDragonSlayerItems()[index] = true;
			DragonSlayer.handleMagicDoor(player, false);
		}
		return true;
	}

	@Override
	public Location getDestination(final Player player, Node node) {
		return LOC;
	}
}
