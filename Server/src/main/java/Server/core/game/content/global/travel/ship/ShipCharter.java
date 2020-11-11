package core.game.content.global.travel.ship;

import core.game.component.Component;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.repository.Repository;
import core.net.packet.PacketRepository;
import core.net.packet.context.MinimapStateContext;
import core.net.packet.out.MinimapState;
import core.tools.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class used to charter ships.
 * @author 'Vexia
 */
public final class ShipCharter {

	/**
	 * Represents the component of charting a ship.
	 */
	private final static Component COMPONENT = new Component(95);

	/**
	 * Represents the ring of charos item.
	 */
	private final static Item RING_OF_CHAROS = new Item(6465);

	/**
	 * Constructs a new {@code ShipCharter} {@code Object}.
	 */
	public ShipCharter() {
		/**
		 * empty.
		 */
	}

	/**
	 * Method used to open the ship chartering interface.
	 * @param player the player.
	 */
	public static void open(final Player player) {
		final Destination current = Destination.getFromBase(player.getLocation());
		if (current != null) {
			int[] hiddenComponents = getHiddenComponents(player, current);
			for (int component : hiddenComponents) {
				player.getPacketDispatch().sendInterfaceConfig(95, component, true);
			}
			player.getInterfaceManager().open(COMPONENT);
		}
	}

	/**
	 * Method used to handle a ship charter.
	 * @param player the player.
	 * @param button the button.
	 */
	public static void handle(final Player player, final int button) {
		final Destination destination = Destination.forButton(button);
		if (destination == null) {
			return;
		}
		final int cost = getCost(player, destination);
		player.getInterfaceManager().close();
		player.getDialogueInterpreter().open(4651, Repository.findNPC(4651), destination, cost);
	}

	/**
	 * Method used to get the cost of the charter.
	 * @param player the player.
	 * @param destination the destination.
	 * @return the cost.
	 */
	public static int getCost(final Player player, Destination destination) {
		int cost = destination.getCost(player, destination);
		if (player.getEquipment().containsItem(RING_OF_CHAROS)) {// TODO: cabin fever quest
			cost -= Math.round((cost / 2.));
		}
		return cost;
	}

	/**
	 * Method used to get the hidden childs on the screen.
	 * @param player the player.
	 * @return the hidden childs.
	 */
	public static int[] getHiddenComponents(final Player player, Destination base) {
		final Destination[] restrictions = new Destination[] { /**
																* 
																* Destination.MOS_LE_HARMLESS,
																*/
		Destination.OO_GLOG, Destination.SHIPYARD, /* Destination.PORT_TYRAS, */
		Destination.CRANDOR };
		List<Integer> childs = new ArrayList<>();
		for (Destination destination : restrictions) {
			childs.add(destination.getXChild());
			childs.add(destination.getNameChild());
		}
		childs.add(base.getXChild());
		childs.add(base.getNameChild());
		if (base == Destination.KARAMJA) {
			childs.add(Destination.PORT_SARIM.getXChild());
			childs.add(Destination.PORT_SARIM.getNameChild());
		}
		if (base == Destination.PORT_SARIM) {
			childs.add(Destination.KARAMJA.getXChild());
			childs.add(Destination.KARAMJA.getNameChild());
		}
		int[] arrayChilds = new int[childs.size()];
		for (int i = 0; i < arrayChilds.length; i++) {
			arrayChilds[i] = childs.get(i);
		}
		return arrayChilds;
	}

	/**
	 * Method used to get the component of ship chartering.
	 * @return the component..
	 */
	public static Component getComponent() {
		return COMPONENT;
	}

	/**
	 * Represents the destination to travel to.
	 * @author 'Vexia
	 */
	public enum Destination {
		CATHERBY(Location.create(2792, 3417, 1), 25, new int[] { 480, -1, 480, 625, 1600, 3250, 1000, 1600, 3200, 3400 }, Location.create(2797, 3414, 0), 3, 14),
		PORT_PHASMATYS(Location.create(3705, 3503, 1), 24, new int[] { 3650, 3250, 1850, -1, -1, -1, 2050, 1850, 3200, 1100 }, Location.create(3702, 3502, 0), 2, 13),
		CRANDOR(new Location(2792, 3417, 1), 32, new int[] { -1, 480, 480, 925, 400, 3650, 1600, 400, 3200, 3800 }, null, 10, 21),
		BRIMHAVEN(Location.create(2763, 3238, 1), 28, new int[] { -1, 480, 480, 925, 400, 3650, 1600, 400, 3200, 3800 }, Location.create(2760, 3238, 0), 6, 17),
		PORT_SARIM(Location.create(3038, 3189, 1), 30, new int[] { 1600, 1000, -1, 325, 1280, 650, 1280, 400, 3200, 1400 }, Location.create(3039, 3193, 0), 8, 19),
		PORT_TYRAS(Location.create(2142, 3122, 0), 23, new int[] { 3200, 3200, 3200, 1600, 3200, 3200, 3200, 3200, -1, 3200 }, Location.create(2143, 3122, 0), 1, 12),
		KARAMJA(Location.create(2957, 3158, 1), 27, new int[] { 200, 480, -1, 225, 400, 1850, -1, 200, 3200, 2000 }, Location.create(2954, 3156, 0), 5, 16),
		PORT_KHAZARD(Location.create(2674, 3141, 1), 29, new int[] { 1600, 1000, -1, 325, 180, 650, 1280, 400, 3200, 1400 }, Location.create(2674, 3144, 0), 7, 18),
		SHIPYARD(Location.create(3001, 3032, 0), 26, new int[] { 400, 1600, 200, 225, 720, 1850, 400, -1, 3200, 900 }, Location.create(3001, 3032, 0), 4, 15),
		OO_GLOG(Location.create(2623, 2857, 0), 33, new int[] { 300, 3400, 2000, 550, 5000, 2800, 1400, 900, 3200, -1 }, Location.create(2622, 2857, 0), 11, 22),
		MOS_LE_HARMLESS(Location.create(3671, 2931, 0), 31, new int[] { 725, 625, 1025, -1, 1025, -1, 325, 275, 1600, 500 }, Location.create(3671, 2933, 0), 9, 20);

		/**
		 * Constructs a new {@code ShipCharter} {@code Object}.
		 * @param location the location.
		 * @param button the button.
		 * @param costs the money.
		 * @param base the base.
		 * @param components the children.
		 */
		Destination(Location location, int button, int[] costs, final Location base, int... components) {
			this.location = location;
			this.button = button;
			this.costs = costs;
			this.base = base;
			this.childs = components;
		}

		/**
		 * Represents the location of the destination.
		 */
		private final Location location;

		/**
		 * Represents the button of the destination.
		 */
		private final int button;

		/**
		 * Represents the costs from destination to destination.
		 */
		private final int[] costs;

		/**
		 * Represents the base location(how we find where we're at)
		 */
		private final Location base;

		/**
		 * Represents the childs on the screen.
		 */
		private final int[] childs;

		/**
		 * Gets the location.
		 * @return The location.
		 */
		public Location getLocation() {
			return location;
		}

		/**
		 * Gets the button.
		 * @return The button.
		 */
		public int getButton() {
			return button;
		}

		/**
		 * Gets the moneys.
		 * @return The moneys.
		 */
		public int[] getCosts() {
			return costs;
		}

		/**
		 * Gets the base.
		 * @return The base.
		 */
		public Location getBase() {
			return base;
		}

		/**
		 * Gets the childs.
		 * @return The childs.
		 */
		public int[] getComponents() {
			return childs;
		}

		/**
		 * Gets the x component.
		 * @return the component.
		 */
		public int getXChild() {
			return childs[0];
		}

		/**
		 * Gets the name component.
		 * @return the component.
		 */
		public int getNameChild() {
			return childs[1];
		}

		/**
		 * Gets the cost of chartering a ship.
		 * @param player the player.
		 * @return the cost.
		 */
		public int getCost(final Player player, final Destination destination) {
			final Destination current = Destination.getFromBase(player.getLocation());
			if (current == null) {
				return 0;
			}
			final Destination[] costTable = new Destination[] { BRIMHAVEN, CATHERBY, KARAMJA, MOS_LE_HARMLESS, PORT_KHAZARD, PORT_PHASMATYS, PORT_SARIM, SHIPYARD, PORT_TYRAS, OO_GLOG };
			int index = 0;
			for (int i = 0; i < costTable.length; i++) {
				if (costTable[i] == destination) {
					index = i;
					break;
				}
			}
			return current.getCosts()[index];
		}

		/**
		 * Method used to get the destination from the base.
		 * @param location the location.
		 * @return the destination.
		 */
		public static Destination getFromBase(Location location) {
			for (Destination destination : Destination.values()) {
				if (destination.getBase() == null) {
					continue;
				}
				if (destination.getBase().getDistance(location) < 30) {
					return destination;
				}
			}
			return null;
		}

		/**
		 * Method used to get the destination for the button id.
		 * @param button the button.
		 * @return the destination.
		 */
		public static Destination forButton(final int button) {
			for (Destination destination : values()) {
				if (destination.getButton() == button) {
					return destination;
				}
			}
			return null;
		}

		/**
		 * Method used to sail the ship.
		 * @param player the player.
		 */
		public void sail(final Player player) {
			player.lock(7);
			Location start = player.getLocation();
			GameWorld.getPulser().submit(new Pulse(1) {
				int count = 0;

				@Override
				public boolean pulse() {
					switch (count++) {
					case 0:
						player.getInterfaceManager().openOverlay(new Component(115));
						break;
					case 2:
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
						break;
					case 3:
						player.getProperties().setTeleportLocation(getLocation());
						break;
					case 5:
						player.unlock();
						player.getInterfaceManager().close();
						player.getInterfaceManager().closeOverlay();
						player.getInterfaceManager().restoreTabs();
						PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
						player.getPacketDispatch().sendMessage("You pay the fare and sail to " + StringUtils.formatDisplayName(name()) + ".");
						// Charter a ship from the shipyard in the far east of Karamja
						if (start.withinDistance(Location.create(3001,3032,0))) {
							player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 17);
						}
						return true;
					}
					return false;
				}

			});
		}
	}

}
