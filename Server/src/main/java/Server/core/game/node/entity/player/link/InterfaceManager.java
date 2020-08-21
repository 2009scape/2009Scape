package core.game.node.entity.player.link;

import core.game.component.Component;
import core.game.component.InterfaceType;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.net.packet.PacketRepository;
import core.net.packet.context.InterfaceContext;
import core.net.packet.context.WindowsPaneContext;
import core.net.packet.out.CloseInterface;
import core.net.packet.out.Interface;
import core.net.packet.out.WindowsPane;

/**
 * Manages a player's interfaces.
 * @author Emperor
 * 
 */
public final class InterfaceManager {

	/**
	 * The default windows pane.
	 */
	public static final int WINDOWS_PANE = 548;

	/**
	 * The default chat box interface.
	 */
	public static final int DEFAULT_CHATBOX = 137;

	/**
	 * The default tabs.
	 */
	public static final int[] DEFAULT_TABS = { 92, 320, 274, 149, 387, 271, 192, 662, 550, 551, 589, 261, 464, 187, 182 };

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The amount of interface packets sent.
	 */
	private int packetCount;

	/**
	 * The windows pane.
	 */
	private Component windowsPane;

	/**
	 * The currently opened component.
	 */
	private Component opened;

	/**
	 * The tabs.
	 */
	private Component[] tabs = new Component[15];

	/**
	 * The chatbox component.
	 */
	private Component chatbox;

	/**
	 * The single tab.
	 */
	private Component singleTab;

	/**
	 * The overlay component.
	 */
	private Component overlay;

	/**
	 * The currently opened tab's index.
	 */
	private int currentTabIndex = 3;

	/**
	 * Constructs a new {@code InterfaceManager} {@code Object}.
	 * 
	 * @param player
	 *            The player.
	 */
	public InterfaceManager(Player player) {
		this.player = player;
	}

	/**
	 * Opens the windows pane.
	 * 
	 * @param windowsPane
	 *            The windows pane.
	 * @return The component instance.
	 */
	public Component openWindowsPane(Component windowsPane) {
		return openWindowsPane(windowsPane, false);
	}

	/**
	 * Opens the windows pane.
	 * 
	 * @param windowsPane
	 *            The windows pane.
	 * @return The component instance.
	 */
	public Component openWindowsPane(Component windowsPane, boolean overlap) {
		this.windowsPane = windowsPane;
		if (windowsPane.getDefinition().getType() != InterfaceType.WINDOW_PANE) {
			System.err.println("Set interface type to WINDOW_PANE for component " + windowsPane.getId() + ", definition requires updating!");
			windowsPane.getDefinition().setType(InterfaceType.WINDOW_PANE);
		}
		PacketRepository.send(WindowsPane.class, new WindowsPaneContext(player, windowsPane.getId(), overlap ? 1 : 0));
		windowsPane.open(player);
		return windowsPane;
	}

	public void openWindowsPane(Component windowsPane, int type) {
		this.windowsPane = windowsPane;
		if (windowsPane.getDefinition().getType() != InterfaceType.WINDOW_PANE) {
			System.err.println("Set interface type to WINDOW_PANE for component " + windowsPane.getId() + ", definition requires updating!");
			windowsPane.getDefinition().setType(InterfaceType.SINGLE_TAB);
		}
		PacketRepository.send(WindowsPane.class, new WindowsPaneContext(player, windowsPane.getId(), type));
		windowsPane.open(player);
	}

	/**
	 * Opens a component.
	 * 
	 * @param componentId
	 *            The component id.
	 * @return The opened component.
	 */
	public Component openComponent(int componentId) {
		return open(new Component(componentId));
	}

	/**
	 * Opens a component.
	 * @param component The component to open.
	 * @return The opened component.
	 */
	public Component open(Component component) {
		if (!close()) {
			return null;
		}
		component.open(player);
		return opened = component;
	}

	/**
	 * Checks if a main interface.
	 * @return {@code True} if so.
	 */
	public boolean isOpened() {
		return opened != null;
	}

	/**
	 * Checks if the player has a chat box interface opened (disregarding default chat box).
	 * @return {@code True} if so.
	 */
	public boolean hasChatbox() {
		return chatbox != null && chatbox.getId() != DEFAULT_CHATBOX;
	}

	/**
	 * Safely closes the currently opened interface.
	 */
	public boolean close() {
		if (player.getAttribute("runscript", null) != null) {
			player.removeAttribute("runscript");
			player.getPacketDispatch().sendRunScript(101, "");
		}
		if (opened != null && opened.close(player)) {
			if (opened != null && (!opened.getDefinition().isWalkable() || opened.getId() == 14)) {
				PacketRepository.send(CloseInterface.class, new InterfaceContext(player, opened.getDefinition().getWindowPaneId(isResizable()), opened.getDefinition().getChildId(isResizable()), opened.getId(), opened.getDefinition().isWalkable()));
			}
			opened = null;
		}
		return opened == null;
	}

	/**
	 * Checks if the current interface is walkable.
	 * @return <code>True</code> if so.
	 */
	public boolean isWalkable() {
		if (opened != null) {
			if (opened.getId() == 389) {
				return false;
			}
			if (opened.getDefinition().isWalkable()) {
				return true;
			}
		}
		return true;
	}

	/**
	 * Safely closes the component.
	 * @param component The component.
	 * @return {@code True} if the component successfully closed.
	 */
	public boolean close(Component component) {
		if (component.close(player)) {
			if (component.getId() == DEFAULT_CHATBOX) {
				return true;
			}
			if (component.getDefinition().getType() == InterfaceType.TAB) {
				PacketRepository.send(CloseInterface.class, new InterfaceContext(player, component.getDefinition().getWindowPaneId(isResizable()), component.getDefinition().getChildId(isResizable()) + component.getDefinition().getTabIndex(), component.getId(), component.getDefinition().isWalkable()));
				return true;
			}
			PacketRepository.send(CloseInterface.class, new InterfaceContext(player, component.getDefinition().getWindowPaneId(isResizable()), component.getDefinition().getChildId(isResizable()), component.getId(), component.getDefinition().isWalkable()));
			return true;
		}
		return false;
	}

	/**
	 * Closes the chatbox interface.
	 */
	public void closeChatbox() {
		if (chatbox != null && chatbox.getId() != DEFAULT_CHATBOX) {
			if (close(chatbox)) {
				openChatbox(DEFAULT_CHATBOX);
				player.getPacketDispatch().sendRunScript(101, "");
			}
		}
	}

	/**
	 * Opens a tab and removes the other tabs.
	 * @param component The component to open.
	 * @return The component.
	 */
	public Component openSingleTab(Component component) {
		if (component.getDefinition().getType() != InterfaceType.SINGLE_TAB) {
			System.err.println("Set interface type to SINGLE_TAB for component " + component.getId() + ", definition requires updating!");
			component.getDefinition().setType(InterfaceType.SINGLE_TAB);
		}
		component.open(player);
		if (component.getCloseEvent() == null) {
			component.setCloseEvent((player, c) -> {
//				openDefaultTabs();
				return true;
			});
		}
		return singleTab = component;
	}

	/**
	 * Closes the current single tab opened.
	 */
	public boolean closeSingleTab() {
		if (singleTab != null && close(singleTab)) {
			singleTab = null;
		}
		return true;
	}

	/**
	 * Gets the currently opened single tab.
	 * @return The tab opened.
	 */
	public Component getSingleTab() {
		return singleTab;
	}

	/**
	 * Hides the tabs.
	 * @param tabs The tab indexes.
	 */
	public void hideTabs(int... tabs) {
		if (true) {
			removeTabs(tabs);
			return;
		}
		@SuppressWarnings("unused")
		boolean changeViewedTab = false;
		for (int slot : tabs) {
			if (slot == currentTabIndex) {
				changeViewedTab = true;
			}
			Component tab = this.tabs[slot];
			if (tab != null && !tab.isHidden()) {
				int child = (slot < 7 ? 38 : 13) + slot;
				boolean resize = isResizable();
				player.getPacketDispatch().sendInterfaceConfig(getWindowPaneId(), child, true);
				player.getPacketDispatch().sendInterfaceConfig(getWindowPaneId(), child + 7, true);
				tab.setHidden(true);
			}
		}
		if (changeViewedTab) {
			int currentIndex = -1;
			if (this.tabs[3] == null) {
				for (int i = 0; i < this.tabs.length; i++) {
					if (this.tabs[i] != null) {
						currentIndex = i;
						break;
					}
				}
			} else {
				currentIndex = 3;
			}
			if (currentIndex > -1) {
				setViewedTab(currentIndex);
			}
		}
	}
	
	/**
	 * Removes the tabs.
	 * @param tabs The tab indexes.
	 */
	@Deprecated
	public void removeTabs(int... tabs) {
		boolean changeViewedTab = false;
		for (int slot : tabs) {
			if (slot == currentTabIndex) {
				changeViewedTab = true;
			}
			Component tab = this.tabs[slot];
			if (tab != null) {
				close(tab);
				this.tabs[slot] = null;
			}
		}
		if (changeViewedTab) {
			int currentIndex = -1;
			if (this.tabs[3] == null) {
				for (int i = 0; i < this.tabs.length; i++) {
					if (this.tabs[i] != null) {
						currentIndex = i;
						break;
					}
				}
			} else {
				currentIndex = 3;
			}
			if (currentIndex > -1) {
				setViewedTab(currentIndex);
			}
		}
	}

	/**
	 * Restores the tabs.
	 */
	public void restoreTabs() {
		for (int i = 0; i < tabs.length; i++) {
			Component tab = tabs[i];
			if (tab == null) {
				switch (i) {
				case 0:
					WeaponInterface inter = player.getExtension(WeaponInterface.class);
					if (inter == null) {
						player.addExtension(WeaponInterface.class, inter = new WeaponInterface(player));
					}
					openTab(0, inter);
					break;
				case 6:
					openTab(6, new Component(player.getSpellBookManager().getSpellBook())); // Magic
					break;
				case 7:
					if (player.getFamiliarManager().hasFamiliar()) {
						openTab(7, new Component(662));
					}
					break;
				default:
					openTab(i, new Component(DEFAULT_TABS[i]));
				}
			}
			else if (tab.isHidden()) {
				int child = (i < 7 ? 38 : 13) + i;
//				boolean resize = isResizable(); //TODO:
				player.getPacketDispatch().sendInterfaceConfig(getWindowPaneId(), child, false);
				player.getPacketDispatch().sendInterfaceConfig(getWindowPaneId(), child + 7, false);
				tabs[i].setHidden(false);
			}
		}
	}

	/**
	 * Opens the default tabs.
	 */
	public void openDefaultTabs() {
		// player.getPacketDispatch().sendInterfaceConfig(548, 51, false);
		WeaponInterface inter = player.getExtension(WeaponInterface.class);
		if (inter == null) {
			player.addExtension(WeaponInterface.class, inter = new WeaponInterface(player));
		}
		//sendTab(16, 747); // Summoning bar
		openTab(0, inter); // Attack
		openTab(1, new Component(320)); // Skills
		openTab(2, new Component(274)); // Quest
		openTab(3, new Component(149)); // inventory
		openTab(4, new Component(387)); // Equipment
		openTab(5, new Component(271)); // Prayer
		openTab(6, new Component(player.getSpellBookManager().getSpellBook())); // Magic
		if (player.getFamiliarManager().hasFamiliar()) {
			openTab(7, new Component(662)); // summoning.
		}
		openTab(8, new Component(550)); // Friends
		openTab(9, new Component(551)); // Ignores
		openTab(10, new Component(589)); // Clan chat
		openTab(11, new Component(261)); // Settings
		openTab(12, new Component(464)); // Emotes
		openTab(13, new Component(187)); // Music
		openTab(14, new Component(182)); // Logout
		if (player.getProperties().getAutocastSpell() != null) {
			inter.selectAutoSpell(inter.getAutospellId(player.getProperties().getAutocastSpell().getSpellId()), true);
		}
	}

	/**
	 * Opens the information bars (orbs).
	 */
	public void openInfoBars() {
		//Hp orb
		PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 13 : 70, 748, true));
		//Prayer orb
		PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 14 : 71, 749, true));
		//Energy orb
		PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 15 : 72, 750, true));
		//Summoning bar
		PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 16 : 73, 747, true));	
		//Split PM
		PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 71 : 10, 754, true));
	}

	/**
	 * Closes the default tabs.
	 */
	public void closeDefaultTabs() {
		WeaponInterface inter = player.getExtension(WeaponInterface.class);
		if (inter != null) {
			close(inter); // Attack
		}
		close(new Component(320)); // Skills
		close(new Component(274)); // Quest
		close(new Component(259)); // Diary
		close(new Component(149)); // inventory
		close(new Component(387)); // Equipment
		close(new Component(271)); // Prayer
		close(new Component(player.getSpellBookManager().getSpellBook()));
		close(new Component(662)); // summoning.
		close(new Component(550)); // Friends
		close(new Component(551)); // Ignores
		close(new Component(589)); // Clan chat
		close(new Component(261)); // Settings
		close(new Component(464)); // Emotes
		close(new Component(187)); // Music
		close(new Component(182)); // Logout
	}

	/**
	 * Opens a tab.
	 * @param slot The tab slot;
	 * @param component The component.
	 */
	public void openTab(int slot, Component component) {
		if (component.getId() == 92 && !(component instanceof WeaponInterface)) {
			throw new IllegalStateException("Attack tab can only be instanced as " + WeaponInterface.class.getCanonicalName() + "!");
		}
		if (component.getDefinition().getTabIndex() != slot) {
			System.err.println("Set tab index to " + slot + " for component " + component.getId() + ", definition requires updating!");
			component.getDefinition().setTabIndex(slot);
		}
		if (component.getDefinition().getType() != InterfaceType.TAB) {
			System.err.println("Set interface type to TAB for component " + component.getId() + ", definition requires updating!");
			component.getDefinition().setType(InterfaceType.TAB);
		}
		component.open(player);
		tabs[slot] = component;
	}

	/**
	 * Opens a tab.
	 * @param component The component to open.
	 */
	public void openTab(Component component) {
		if (component.getDefinition().getTabIndex() < 0) {
			System.err.println("No component definitions found for tab " + component.getId() + "!");
			return;
		}
		openTab(component.getDefinition().getTabIndex(), component);
	}

	/**
	 * Opens a chat box interface.
	 * @param componentId The component id.
	 */
	public void openChatbox(int componentId) {
		openChatbox(new Component(componentId));
	}

	/**
	 * Opens a chat box interface.
	 * @param component The component to open.
	 */
	public void openChatbox(Component component) {
		if (component.getId() == DEFAULT_CHATBOX) {
			if (chatbox == null || (chatbox.getId() != DEFAULT_CHATBOX && chatbox.getDefinition().getType() == InterfaceType.CHATBOX)) {
				PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 23 : 14, 751, true));
				PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 70 : 75, 752, true));
				PacketRepository.send(Interface.class, new InterfaceContext(player, InterfaceType.CHATBOX.getFixedPaneId(), InterfaceType.CHATBOX.getFixedChildId(), 137, true));
			}
			chatbox = component;
			player.getConfigManager().set(334, 1);
		} else {
			chatbox = component;
			if (chatbox.getDefinition().getType() != InterfaceType.DIALOGUE && chatbox.getDefinition().getType() != InterfaceType.CHATBOX && chatbox.getDefinition().getType() != InterfaceType.CS_CHATBOX) {
				System.err.println("Set interface type to CHATBOX for component " + component.getId() + ", definition requires updating!");
				chatbox.getDefinition().setType(InterfaceType.DIALOGUE);
			}
			chatbox.open(player);
		}
		if (TutorialSession.getExtension(player).getStage() < TutorialSession.MAX_STAGE) {
			Component.setUnclosable(player, getChatbox());
		}
	}

	/**
	 * Switches the player's window mode (fixed, resizable, fullscreen).
	 * @param windowMode The window mode.
	 */
	public void switchWindowMode(int windowMode) {
		if (windowMode != player.getSession().getClientInfo().getWindowMode()) {
			player.getSession().getClientInfo().setWindowMode(windowMode);
			openWindowsPane(new Component(isResizable() ? 746 : 548));
			openDefaultTabs();
			openInfoBars();
			PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 23 : 14, 751, true));
			PacketRepository.send(Interface.class, new InterfaceContext(player, getWindowPaneId(), isResizable() ? 70 : 75, 752, true));
		}
	}

	/**
	 * Gets the component for the given component id.
	 * @param componentId The component id.
	 * @return The component.
	 */
	public Component getComponent(int componentId) {
		if (opened != null && opened.getId() == componentId) {
			return opened;
		}
		if (chatbox != null && chatbox.getId() == componentId) {
			return chatbox;
		}
		if (singleTab != null && singleTab.getId() == componentId) {
			return singleTab;
		}
		if (overlay != null && overlay.getId() == componentId) {
			return overlay;
		}
		if (windowsPane.getId() == componentId) {
			return windowsPane;
		}
		for (Component c : tabs) {
			if (c != null && c.getId() == componentId) {
				return c;
			}
		}
		if (componentId == 751 || componentId == 750 || componentId == 747) {
			//Chatbox settings, run orb & summoning orb.
			return new Component(componentId);
		}
		return null;
	}

	/**
	 * Sets the currently viewed tab.
	 * @param tabIndex The tab index.
	 */
	public void setViewedTab(int tabIndex) {
		if (tabs[tabIndex] == null) {
			throw new IllegalStateException("Tab at index " + tabIndex + " is null!");
		}
		currentTabIndex = tabIndex;
		switch (tabIndex) {
		case 0:
			tabIndex = 1;
			break;
		case 1:
			tabIndex = 2;
			break;
		case 2:
			tabIndex = 3;
			break;
		}
		if (tabIndex > 9) {
			tabIndex--;
		}
		player.getPacketDispatch().sendRunScript(115, "i", tabIndex);
	}

	/**
	 * Checks if the main component opened matches the given component id.
	 * @param id The component id.
	 * @return {@code True} if so.
	 */
	public boolean hasMainComponent(int id) {
		return opened != null && opened.getId() == id;
	}

	/**
	 * Opens an overlay.
	 * @param component The component.
	 */
	public void openOverlay(Component component) {
		if (overlay != null && !overlay.close(player)) {
			return;
		}
		overlay = component;
		if (overlay.getDefinition().getType() != InterfaceType.OVERLAY) {
			System.err.println("Set interface type to OVERLAY for component " + component.getId() + ", definition requires updating!");
			overlay.getDefinition().setType(InterfaceType.OVERLAY);
			overlay.getDefinition().setWalkable(true);
		}
		overlay.open(player);
	}

	/**
	 * Closes the current overlay.
	 */
	public void closeOverlay() {
		if (overlay != null && close(overlay)) {
			overlay = null;
		}
	}

	/**
	 * Gets the weapon tab interface.
	 * @return The weapon interface.
	 */
	public WeaponInterface getWeaponTab() {
		return player.getExtension(WeaponInterface.class);
	}

	/**
	 * Gets the opened.
	 * @return The opened.
	 */
	public Component getOpened() {
		return opened;
	}

	/**
	 * Sets the opened.
	 * @param opened The opened to set.
	 */
	public void setOpened(Component opened) {
		this.opened = opened;
	}

	/**
	 * Gets the tabs.
	 * @return The tabs.
	 */
	public Component[] getTabs() {
		return tabs;
	}

	/**
	 * Sets the tabs.
	 * @param tabs The tabs to set.
	 */
	public void setTabs(Component[] tabs) {
		this.tabs = tabs;
	}

	/**
	 * Gets the chatbox.
	 * @return The chatbox.
	 */
	public Component getChatbox() {
		return chatbox;
	}

	/**
	 * Sets the chatbox.
	 * @param chatbox The chatbox to set.
	 */
	public void setChatbox(Component chatbox) {
		this.chatbox = chatbox;
	}

	/**
	 * Gets the overlay.
	 * @return The overlay.
	 */
	public Component getOverlay() {
		return overlay;
	}

	/**
	 * Sets the overlay.
	 * @param overlay The overlay to set.
	 */
	public void setOverlay(Component overlay) {
		this.overlay = overlay;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the currentTabIndex.
	 * @return The currentTabIndex.
	 */
	public int getCurrentTabIndex() {
		return currentTabIndex;
	}

	/**
	 * Sets the currentTabIndex.
	 * @param currentTabIndex The currentTabIndex to set.
	 */
	public void setCurrentTabIndex(int currentTabIndex) {
		this.currentTabIndex = currentTabIndex;
	}

	/**
	 * Gets the windowsPane.
	 * @return The windowsPane.
	 */
	public Component getWindowsPane() {
		return windowsPane;
	}

	/**
	 * Gets the windows pane id.
	 * @return The window pane id.
	 */
	public int getWindowPaneId() {
		if (windowsPane == null) {
			return 548;
		}
		return windowsPane.getId();
	}

	/**
	 * Gets the default child id.
	 * @return The default child id.
	 */
	public int getDefaultChildId() {
		return isResizable() ? 6 : 11;
	}

	/**
	 * Checks if the player's client is resizable.
	 * @return {@code True} if so.
	 */
	public boolean isResizable() {
		if (player.getSession().getClientInfo() == null) {
			return false;
		}
		return player.getSession().getClientInfo().isResizable();
	}

	/**
	 * Gets the amount of times an interface related packet was sent, then increments it.
	 * @param increment The amount to increment the counter with.
	 * @return The amount of times an interface packet was sent.
	 */
	public int getPacketCount(int increment) {
		int count = packetCount;
		packetCount += increment;
		return count;
	}
}