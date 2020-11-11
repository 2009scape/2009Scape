package plugin.interaction.item.withitem;

import core.game.content.ItemNames;
import core.game.content.global.Dyes;
import core.game.content.global.action.SpecialLadders;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Handles the dyeing of a cape.
 * @author afaroutdude
 */
@InitializablePlugin
public final class CapeDyeingPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code CapeDyeingPlugin} {@code Object}.
	 */
	public CapeDyeingPlugin() {
		super(Cape.getIds());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Cape c : Cape.values()) {
			addHandler(c.getDye().getItem().getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final boolean testCape = Cape.isCape(event.getBaseItem());
		final Item cape = Cape.isCape(event.getBaseItem()) ? event.getBaseItem() : event.getUsedItem();
		final Item dye = Cape.isCape(event.getBaseItem()) ? event.getUsedItem() : event.getBaseItem();
		final Item dyedCape = MAP_DYE_TO_CAPE.get(dye);
		if (dyedCape == null) {
			return false;
		}
		if (!cape.equals(dyedCape) && player.getInventory().containsItems(dye, cape) && player.getInventory().remove(dye, cape)) {
			player.getInventory().add(dyedCape);
			if (dye.equals(Dyes.BLACK.getItem())) {
				player.getInventory().add(new Item(ItemNames.VIAL));
			}
			if (dye.equals(Dyes.PINK.getItem()) && !player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(2,5)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player,2,5,true);
			}
		}
		return true;
	}

	/**
	 * A cape to dye.
	 * @author Vexia
	 */
	public enum Cape {
		BLACK(Dyes.BLACK, new Item(1019)),
		RED(Dyes.RED, new Item(1007)),
		BLUE(Dyes.BLUE, new Item(1021)),
		YELLOW(Dyes.YELLOW, new Item(1023)),
		GREEN(Dyes.GREEN, new Item(1027)),
		PURPLE(Dyes.PURPLE, new Item(1029)),
		ORANGE(Dyes.ORANGE, new Item(1031)),
		PINK(Dyes.PINK, new Item(6959));

		/**
		 * The dye for the cape.
		 */
		private final Dyes dye;

		/**
		 * The cape item.
		 */
		private final Item cape;

		/**
		 * Constructs a new {@code Cape} {@code Object}.
		 * @param dye the dye.
		 * @param cape the cape.
		 */
		Cape(Dyes dye, Item cape) {
			this.dye = dye;
			this.cape = cape;
		}

		/**
		 * Gets the dye.
		 * @return The dye.
		 */
		public Dyes getDye() {
			return dye;
		}

		/**
		 * Gets the cape.
		 * @return The cape.
		 */
		public Item getCape() {
			return cape;
		}

		/**
		 * @return an int array of all cape IDs
		 */
		static public int[] getIds() {
			return Stream.of(Cape.values())
					.map(Cape::getCape)
					.map(Item::getId)
					.mapToInt(Integer::intValue).toArray();
		}

		/**
		 * @param potentiallyCape
		 * @return true if passed item is a cape that we can handle
		 */
		static public boolean isCape(Item potentiallyCape){
			for (Cape c : Cape.values()) {
				if (c.getCape().getId() == potentiallyCape.getId()) {
					return true;
				}
			}
			return false;
		}
	}

	public static HashMap<Item, Item> MAP_DYE_TO_CAPE = new HashMap<>();
	static {
		for (Cape c : Cape.values()) {
			MAP_DYE_TO_CAPE.putIfAbsent(c.getDye().getItem(), c.getCape());
		}
	}
}
