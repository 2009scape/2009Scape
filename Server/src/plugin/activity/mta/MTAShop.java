package plugin.activity.mta;

import java.util.ArrayList;
import java.util.List;

import org.crandor.game.component.CloseEvent;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.container.Container;
import org.crandor.game.container.ContainerType;
import org.crandor.game.container.access.InterfaceContainer;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.plugin.Plugin;

/**
 * Represents the mage training arena shop.
 * @author Vexia
 */
public class MTAShop {

	/**
	 * The magic training arena shop items.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(6908, 100), new Item(6910, 100), new Item(6912, 100), new Item(6914, 100), new Item(6916, 100), new Item(6918, 100), new Item(6920, 100), new Item(6922, 100), new Item(6924, 100), new Item(6889, 100), new Item(6926, 100), new Item(4695, 100), new Item(4696, 100), new Item(4697, 100), new Item(4698, 100), new Item(4694, 100), new Item(4699, 100), new Item(564, 100), new Item(562, 100), new Item(561, 100), new Item(560, 100), new Item(563, 100), new Item(566, 100), new Item(565, 100) };

	/**
	 * The prices.
	 */
	private static final int[][] PRICES = new int[][] { { 30, 30, 300, 30 }, { 60, 60, 600, 60 }, { 150, 200, 1500, 150 }, { 240, 240, 2400, 240 }, { 400, 450, 4000, 400 }, { 350, 400, 3000, 350 }, { 120, 120, 1200, 120 }, { 175, 225, 1500, 175 }, { 450, 500, 5000, 450 }, { 500, 550, 6000, 500 }, { 200, 300, 2000, 200 }, { 1, 1, 15, 1 }, { 1, 1, 15, 1 }, { 1, 1, 15, 1 }, { 1, 1, 15, 1 }, { 1, 1, 15, 1 }, { 1, 1, 15, 1 }, { 0, 0, 5, 0 }, { 0, 1, 5, 1 }, { 0, 1, 0, 1 }, { 2, 1, 20, 1 }, { 2, 0, 0, 0 }, { 2, 2, 25, 2 }, { 2, 2, 25, 2 } };

	/**
	 * The container.
	 */
	private final Container container = new Container(ITEMS.length, ContainerType.SHOP);

	/**
	 * The mage training arena shop plugin.
	 */
	private final MTAShopPlugin shopPlugin = new MTAShopPlugin();

	/**
	 * The viewers.
	 */
	private final List<Player> viewers = new ArrayList<>();

	/**
	 * The shop component.
	 */
	private final Component component = new Component(197).setCloseEvent(new CloseEvent() {

		@Override
		public boolean close(Player player, Component c) {
			if (player == null) {
				return true;
			}
			viewers.remove(player);
			return true;
		}

	});

	/**
	 * Constructs a new {@Code MTAShop} {@Code Object}
	 */
	public MTAShop() {
		container.add(ITEMS);
		component.setPlugin(shopPlugin);
		GameWorld.Pulser.submit(new Pulse(100) {
			@Override
			public boolean pulse() {
				for (int i = 0; i < container.toArray().length; i++) {
					final boolean main = true;
					final Item item = container.toArray()[i];
					if (item == null) {
						continue;
					}
					if (main) {
						if (item.getAmount() < 100) {
							item.setAmount(item.getAmount() + 1);
						}
						MTAShop.this.update();
					}
				}
				return false;
			}
		});
	}

	/**
	 * Opens the shop.
	 * @param player the player.
	 */
	public void open(final Player player) {
		viewers.add(player);
		player.getInterfaceManager().open(component);
		update();
		updatePoints(player);
		GameWorld.Pulser.submit(new Pulse(1, player) {

			@Override
			public boolean pulse() {
				updatePoints(player);
				return true;
			}

		});
	}

	/**
	 * Updates the viewers.
	 */
	private void update() {
		for (Player p : viewers) {
			if (p == null || !p.isActive()) {
				continue;
			}
			InterfaceContainer.generateItems(p, container.toArray(), new String[] { "Buy", "Value" }, 197, 16, 4, 7);
		}
	}

	/**
	 * The item to purchase.
	 * @param player the player.
	 * @param item the item.
	 */
	public void buy(Player player, Item item, int slot) {
		int[] prices = PRICES[slot];
		if (item.getAmount() < 1) {
			player.sendMessage("The shop has ran out of stock.");
			return;
		}
		item = new Item(item.getId(), 1);
		if (!player.getInventory().hasSpaceFor(item)) {
			player.sendMessage("You don't have enough inventory space.");
			return;
		}
		for (int i = 0; i < prices.length; i++) {
			if (getPoints(player, i) < prices[i]) {
				player.sendMessage("You cannot afford that item.");
				return;
			}
		}
		if (item.getId() == 6926 && player.getSavedData().getActivityData().isBonesToPeaches()) {
			player.sendMessage("You already unlocked that spell.");
			return;
		}
		if (slot >= 1 && slot <= 3) {
			Item required = ITEMS[slot - 1];
			if (!player.hasItem(new Item(required.getId(), 1)) && !player.hasItem(new Item(6914, 1))) {
				player.sendMessage("You don't have the required wand in order to buy this upgrade.");
				return;
			}
		}
		if (container.getAmount(item) - 1 <= 0) {
			container.get(slot).setAmount(0);
		} else {
			container.remove(item);
		}
		if (item.getId() == 6926) {
			player.getSavedData().getActivityData().setBonesToPeaches(true);
			player.getDialogueInterpreter().sendDialogue("The guardian teaches you how to use the Bones to Peaches spell!");
		} else {
			player.getInventory().add(item);
		}
		for (int i = 0; i < prices.length; i++) {
			decrementPoints(player, i, prices[i]);
		}
		updatePoints(player);
		update();
	}

	/**
	 * Values an item.
	 * @param player the player.
	 * @param item the item.
	 */
	public void value(Player player, Item item, int slot) {
		int[] prices = PRICES[slot];
		player.sendMessage("The " + item.getName() + " costs " + prices[0] + " Telekinetic, " + prices[1] + " Alchemist,");
		player.sendMessage(prices[2] + " Enchantment and " + prices[3] + " Graveyard Pizazz Points.");
	}

	/**
	 * Updates the points.
	 * @param player the player.
	 */
	public void updatePoints(Player player) {
		player.getPacketDispatch().sendString("" + getPoints(player, 0), 197, 8);// tele
		player.getPacketDispatch().sendString("" + getPoints(player, 2), 197, 9);// enchant
		player.getPacketDispatch().sendString("" + getPoints(player, 1), 197, 10);// alch
		player.getPacketDispatch().sendString("" + getPoints(player, 3), 197, 11);// grave
	}

	/**
	 * Increments the pizazz points.
	 * @param player the player.
	 * @param index the index.
	 * @param increment the increment.
	 */
	public void incrementPoints(Player player, int index, int increment) {
		player.getSavedData().getActivityData().incrementPizazz(index, increment);
	}

	/**
	 * Decrements the pizazz points.
	 * @param player the player.
	 * @param index the index.
	 * @param decrement the decrement.
	 */
	public void decrementPoints(Player player, int index, int decrement) {
		player.getSavedData().getActivityData().decrementPizazz(index, decrement);
	}

	/**
	 * Gets the points.
	 * @param player the player.
	 * @param index the index.
	 * @return the points.
	 */
	public int getPoints(Player player, int index) {
		return player.getSavedData().getActivityData().getPizazzPoints(index);
	}

	/**
	 * The mta shop plugin.
	 * @author Vexia
	 */
	public class MTAShopPlugin extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			return null;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			Item item = container.get(slot);
			if (item == null) {
				return true;
			}
			if (opcode == 230) {
				value(player, item, slot);
			} else {
				buy(player, item, slot);
			}
			return true;
		}

	}
}
