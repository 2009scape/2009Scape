package core.game.content.global.shop;

import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.net.packet.PacketRepository;
import core.net.packet.context.ContainerContext;
import core.net.packet.out.ContainerPacket;

/**
 * Represents the viewing of a shop.
 * @author 'Vexia
 */
public final class ShopViewer {

	/**
	 * Represents the main component.
	 */
	private static final Component COMPONENT = new Component(620).setCloseEvent(new ShopCloseEvent());

	/**
	 * Represents the single tab component.
	 */
	private static final Component SINGLE_TAB = new Component(621);

	/**
	 * Represents the player.
	 */
	private final Player player;

	/**
	 * Represents the shop being viewed.
	 */
	private Shop shop;

	/**
	 * Represents the tab index.
	 */
	private int tabIndex;

	/**
	 * Constructs a new {@code ShopViewer} {@code Object}.
	 * @param player the player.
	 * @param shop the shop.
	 */
	public ShopViewer(Player player, Shop shop) {
		this.player = player;
		this.shop = shop;
	}

	/**
	 * Extends a shop a shop viewer to the player instance.
	 * @param player the player.
	 * @param shop the shop.
	 * @return the viewer.
	 */
	public static ShopViewer extend(final Player player, Shop shop) {
		ShopViewer viewer = player.getExtension(ShopViewer.class);
		if (viewer != null) {
			return viewer;
		}
		player.addExtension(ShopViewer.class, (viewer = new ShopViewer(player, shop)));
		return viewer;
	}

	/**
	 * Method used to open the shop visual.
	 */
	public void open() {
		update();
		shop.getViewers().add(this);
		player.getInterfaceManager().open(COMPONENT);
		player.getInterfaceManager().openSingleTab(SINGLE_TAB);
		shop.sendStock(player, tabIndex);
		player.getPacketDispatch().sendRunScript(25, "vg", 868, 92);
		player.getPacketDispatch().sendAccessMask(1278, 0, 621, 0, 28);
		player.getPacketDispatch().sendString(shop.getTitle(), 620, 22);
		player.getPacketDispatch().sendRunScript(150, "IviiiIsssssssss", "", "", "", "", "Buy X", "Buy 10", "Buy 5", "Buy 1", "Value", -1, 0, 4, 10, 92, 620 << 16 | 23);
		player.getPacketDispatch().sendRunScript(150, "IviiiIsssssssss", "", "", "", "", "Buy X", "Buy 10", "Buy 5", "Buy 1", "Value", -1, 0, 4, 10, 31, (620 << 16) | 24);
		player.getPacketDispatch().sendRunScript(150, "IviiiIsssssssss", "", "", "", "", "Sell X", "Sell 10", "Sell 5", "Sell 1", "Value", -1, 0, 7, 4, 93, 621 << 16);
	}

	/**
	 * Method used to update the contents on the component.
	 */
	public void update() {
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, -1, -2, 93, player.getInventory().toArray(), 28, false));
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, -1, -2, 92, shop.getContainer(0).toArray(), shop.getContainer(0).itemCount(), false));
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, -1, 64271, 31, shop.getContainer(1).toArray(), shop.getContainer(1).itemCount(), false));
	}

	/**
	 * Method used to show a stock.
     */
	public void showStock(int tabIndex) {
		this.tabIndex = tabIndex;
		shop.sendStock(player, tabIndex);
		update();
	}

	/**
	 * Sets the tabIndex.
	 * @param tabIndex The tabIndex to set.
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the shop.
	 * @return The shop.
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * Gets the tabIndex.
	 * @return The tabIndex.
	 */
	public int getTabIndex() {
		return tabIndex;
	}

	/**
	 * Represents the close event when a shop is closed.
	 * @author 'Vexia
	 */
	public static final class ShopCloseEvent implements CloseEvent {

		@Override
		public boolean close(Player player, Component c) {
			final ShopViewer viewer = player.getExtension(ShopViewer.class);
			if (viewer == null) {
				return true;
			}
			player.removeExtension(ShopViewer.class);
			viewer.getShop().getViewers().remove(viewer);
			player.getInterfaceManager().closeSingleTab();
			player.removeAttribute("spawning_items");
			return true;
		}

	}

}
