package plugin.interaction.item.withitem;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to turn regular logs into a different colour.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public class FirelighterPlugin extends UseWithHandler {

	/**
	 * Represents the logs item.
	 */
	private static final Item LOGS = new Item(1511);

	/**
	 * Constructs a new {@code FirelighterPlugin} {@code Object}.
	 */
	public FirelighterPlugin() {
		super(1511);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (FireLighter lighter : FireLighter.values()) {
			addHandler(lighter.getLighter().getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final FireLighter lighter = FireLighter.forLighter(event.getUsedItem().getId() == 1511 ? event.getBaseItem() : event.getUsedItem());
		if (player.getInventory().remove(lighter.getLighter(), LOGS)) {
			player.getInventory().add(lighter.getLog());
		}
		return true;
	}

	/**
	 * Represents a fire lighter.
	 * @author 'Vexia
	 * @date 28/12/2013
	 */
	public enum FireLighter {
		RED(new Item(7329), new Item(7404)), GREEN(new Item(7330), new Item(7405)), BLUE(new Item(7331), new Item(7406)), PURPLE(new Item(10326), new Item(10329)), WHITE(new Item(10327), new Item(10328));

		/**
		 * Represents the fire lighter.
		 */
		private final Item lighter;

		/**
		 * Represents the log to turn into.
		 */
		private final Item log;

		/**
		 * Constructs a new {@code FirelighterPlugin.java} {@code Object}.
		 * @param lighter the lighter.
		 * @param log the log.
		 */
		FireLighter(Item lighter, Item log) {
			this.lighter = lighter;
			this.log = log;
		}

		/**
		 * Gets the lighter.
		 * @return The lighter.
		 */
		public Item getLighter() {
			return lighter;
		}

		/**
		 * Gets the log.
		 * @return The log.
		 */
		public Item getLog() {
			return log;
		}

		/**
		 * Gets the fire lighter from the lighter used.
		 * @return the fire lighter data.
		 */
		public static FireLighter forLighter(final Item item) {
			for (FireLighter lighter : values()) {
				if (lighter.getLighter().getId() == item.getId()) {
					return lighter;
				}
			}
			return null;
		}
	}
}
