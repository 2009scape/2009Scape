package core.game.interaction.item;

import core.cache.def.impl.ItemDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.TeleportManager.TeleportType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle teletabs.
 * @author 'Vexia
 */
@Initializable
public class TeleTabsOptionPlugin extends OptionHandler {

	/**
	 * Represents the enum of tele tabs.
	 * @author 'Vexia
	 */
	public enum TeleTabs {
		ADDOUGNE_TELEPORT(8011, Location.create(2662, 3307, 0), 61),
		AIR_ALTAR_TELEPORT(13599, Location.create(2978, 3296, 0), 0),
		ASTRAL_ALTAR_TELEPORT(13611, Location.create(2156, 3862, 0), 0),
		BLOOD_ALTAR_TELEPORT(13610, Location.create(3559, 9778, 0), 0),
		BODY_ALTAR_TELEPORT(13604, Location.create(3055, 3443, 0), 0),
		CAMELOT_TELEPORT(8010, Location.create(2757, 3477, 0), 55.5),
		CHAOS_ALTAR_TELEPORT(13606, Location.create(3058, 3593, 0), 0),
		COSMIC_ALTAR_TELEPORT(13605, Location.create(2411, 4380, 0), 0),
		DEATH_ALTAR_TELEPORT(13609, Location.create(1863, 4639, 0), 0),
		EARTH_ALTAR_TELEPORT(13602, Location.create(3304, 3472, 0), 0),
		FALADOR_TELEPORT(8009, Location.create(2966, 3380, 0), 47),
		FIRE_ALTAR_TELEPORT(13603, Location.create(3311, 3252, 0), 0),
		LAW_ALTAR_TELEPORT(13608, Location.create(2857, 3378, 0), 0),
		LUMBRIDGE_TELEPORT(8008, Location.create(3222, 3218, 0), 41),
		MIND_ALTAR_TELEPORT(13600, Location.create(2979, 3510, 0), 0),
		NATURE_ALTAR_TELEPORT(13607, Location.create(2868, 3013, 0), 0),
		VARROCK_TELEPORT(8007, Location.create(3212, 3423, 0), 35),
		WATCH_TOWER_TELEPORT(8012, Location.create(2548, 3114, 0), 68),
		WATER_ALTAR_TELEPORT(13601, Location.create(3182, 3162, 0), 0);

		/**
		 * Method used to get the teletab by the id.
		 * @param id the id.
		 * @return the teletab.
		 */
		public static TeleTabs forId(int id) {
			for (TeleTabs tab : TeleTabs.values()) {
				if (tab == null)
					continue;
				if (tab.getItem() == id)
					return tab;
			}
			return null;
		}

		/**
		 * The location.
		 */
		private Location location;

		/**
		 * The item id.
		 */
		private int item;

		/**
		 * The experience gained.
		 */
		private double exp;

		/**
		 * Constructs a new {@code TeleTabsOptionPlugin} {@code Object}.
		 * @param item the item.
		 * @param location the location.
		 * @param exp the exp.
		 */
		TeleTabs(int item, Location location, double exp) {
			setItem(item);
			setLocation(location);
			this.exp = exp;
		}

		/**
		 * @return the item
		 */
		public int getItem() {
			return item;
		}

		/**
		 * @return the location
		 */
		public Location getLocation() {
			return location;
		}

		/**
		 * @param item the item to set
		 */
		public void setItem(int item) {
			this.item = item;
		}

		/**
		 * @param location the location to set
		 */
		public void setLocation(Location location) {
			this.location = location;
		}

		/**
		 * @return the exp.
		 */
		public double getExp() {
			return exp;
		}

		/**
		 * @param exp the exp to set.
		 */
		public void setExp(double exp) {
			this.exp = exp;
		}
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final Item item = (Item) node;
		if (item == null) {
			return false;
		}
		final TeleTabs tab = TeleTabs.forId(item.getId());
		if (tab == null) {
			return false;
		}
		player.getInterfaceManager().close();
		player.lock(5);
		if (player.getInventory().contains(item.getId(), 1)) {
			if (player.getTeleporter().send(tab.getLocation(), TeleportType.TELETABS, 1)) {
				player.getInventory().remove(new Item(item.getId()), item.getSlot(), true);
			}
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("break", this);
		return this;
	}
}
