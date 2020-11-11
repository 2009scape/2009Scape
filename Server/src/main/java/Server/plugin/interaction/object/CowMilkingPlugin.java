package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.OptionHandler;
import core.game.interaction.UseWithHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the plugin used to milk a cow.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class CowMilkingPlugin extends OptionHandler {

	/**
	 * Represents the animation to use;
	 */
	private static final Animation ANIMATION = new Animation(2305);

	/**
	 * Represents the items related to cow milking plugin.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(1925, 1), new Item(3727, 1), new Item(1927, 1) };

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (option.equalsIgnoreCase("steal-cowbell")) {
			player.getDialogueInterpreter().open(70099, "You need to have started the Cold War quest to attempt this.");
			return true;
		}
		milk(player, (GameObject) node);
		return true;
	}

	/**
	 * Milks a cow.
	 * @param player the player.
	 * @param object the object.
	 * @return {@code True} if milked.
	 */
	public boolean milk(final Player player, final GameObject object) {
		if (!player.getInventory().contains(1925, 1) && !player.getInventory().contains(3727, 1)) {
			player.getDialogueInterpreter().open(3807, true, true);
			return true;
		}

		player.animate(ANIMATION);
		player.getPulseManager().run(new Pulse(8, player) {
			@Override
			public boolean pulse() {
				if (player.getInventory().remove(ITEMS[0]) || player.getInventory().remove(ITEMS[1])) {
					player.getInventory().add(ITEMS[2]);
					player.getPacketDispatch().sendMessage("You milk the cow.");
				}
				if (player.getInventory().contains(1925, 1) || player.getInventory().contains(3727, 1)) {
					player.animate(ANIMATION);
					return false;
				}
				return true;
			}

			@Override
			public void stop() {
				super.stop();
				player.animate(new Animation(-1));
			}
		});
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(8689).getHandlers().put("option:milk", this);
		ObjectDefinition.forId(12111).getHandlers().put("option:milk", this);
		ObjectDefinition.setOptionHandler("steal-cowbell", this);
		PluginManager.definePlugin(new BucketHandler());
		return this;
	}

	/**
	 * The use with handler for buckets.
	 * @author Vexia
	 */
	public class BucketHandler extends UseWithHandler {

		/**
		 * Constructs a new {@code BucketHandler} {@code Object}
		 */
		public BucketHandler() {
			super(1925);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(8689, OBJECT_TYPE, this);
			addHandler(12111, OBJECT_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			milk(event.getPlayer(), (GameObject) event.getUsedWith());
			return true;
		}

	}

}
