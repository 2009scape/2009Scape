package plugin.interaction.object;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.CloseEvent;
import org.crandor.game.component.Component;
import org.crandor.game.component.ComponentDefinition;
import org.crandor.game.component.ComponentPlugin;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.TeleportManager.TeleportType;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the fairy ring plugin.
 * @author Vexia
 * @author Aero
 */
@InitializablePlugin
public class FairyRingPlugin extends OptionHandler {

	/**
	 * The selector letters.
	 */
	public final static String[][] SELECTOR_LETTERS = new String[][] { { "a", "b", "c", "d" }, { "i", "j", "k", "l" }, { "p", "q", "r", "s" } };

	/**
	 * The rings.
	 */
	private static final int[] RINGS = new int[] { 12003, 12094, 12095, 14058, 14061, 14064, 14067, 14070, 14073, 14076, 14079, 14082, 14085, 14088, 14091, 14094, 14097, 14100, 14103, 14106, 14109, 14112, 14115, 14118, 14121, 14124, 14127, 14130, 14133, 14136, 14139, 14142, 14145, 14148, 14151, 14154, 14157, 14160, 16181, 16184, 23047, 27325, 37727 };

	/**
	 * The main fairy ring.
	 */
	private static final int MAIN_RING = 12128;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int i : RINGS) {
			ObjectDefinition.forId(i).getConfigurations().put("option:use", this);
		}
		ObjectDefinition.forId(MAIN_RING).getConfigurations().put("option:use", this);
		PluginManager.definePlugin(new FairyInterfaceHandler());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "use":
			if (!player.getEquipment().contains(772, 1) && !player.getEquipment().contains(9084, 1)) {
				player.sendMessage("The fairy ring only works for those who wield fairy magic.");
				return true;
			}
			switch (node.getId()) {
			case MAIN_RING:
				openFairyRing(player);
				break;
			default:
				player.getTeleporter().send(Location.create(2412, 4434, 0), TeleportType.FAIRY_RING);
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Handles the fairy interface handler.
	 * @author Vexia
	 * @author Aero
	 */
	public static class FairyInterfaceHandler extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.forId(734).setPlugin(this);
			ComponentDefinition.forId(735).setPlugin(this);
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			switch (component.getId()) {
			case 734:// teleport interface
				if (player.getAttribute("fairy-delay", 0) > GameWorld.getTicks()) {
					return true;
				}
				if (button == 21) {
					teleport(player);
					return true;
				}
				player.setAttribute("fairy-delay", GameWorld.getTicks() + 4);
				updateCombos(player, button, (button % 2 != 0));
				break;
			case 735:// travel log
				switch (button) {
				case 12:
					break;
				}
				break;
			}
			return true;
		}

	}

	/**
	 * Opens the fairy ring interface.
	 * @param player the player.
	 */
	private void openFairyRing(Player player) {
		reset(player);
		player.getInterfaceManager().open(new Component(734).setCloseEvent(new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				player.getInterfaceManager().closeSingleTab();
				return true;
			}
		}));
		player.getInterfaceManager().openSingleTab(new Component(735));
		FairyRing.drawLog(player);
		player.sendMessage("Please enter the code in slowly and <col=FF0000>one letter at a time</col> to avoid the interface");
		player.sendMessage("teleporting you to the wrong location.");
	}

	/**
	 * Updates the combo boxes.
	 * @param player the player.
	 * @param componentID the comp id.
	 * @param clockwise if clockwise.
	 */
	private static final void updateCombos(Player player, int componentID, boolean clockwise) {
		int index = ((componentID - 23) >> 1);
		if (index == -1) {
			player.getInterfaceManager().close();
			return;
		}
		int[] combination = getCombination(player);
		// player.sendMessage("" + componentID + ", " + index + ", " +
		// combination[index] + ", clockwise=" + clockwise);
		// player.sendMessage("Before selection=" + getComboString(player));
		boolean dbl = false;
		if (getComboString(player).contains("D") && componentID == 23 && combination[index] == 3 || getComboString(player).contains("S") && componentID == 27 && combination[index] == 3 || getComboString(player).contains("L") && componentID == 25 && combination[index] == 3) {
			dbl = true;
		}
		if (dbl) {
			if (clockwise) {
				combination[index] -= 2;
			}
			// player.sendMessage("dbl=" + true);
			player.setAttribute("fairy-delay", GameWorld.getTicks() + 8);
		} else {
			if (clockwise) {
				combination[index]--;
			} else {
				combination[index]++;
			}
		}
		combination = updateCombination(combination);
		setCombination(player, combination);
		/*
		 * for (int i = 0; i < 3; i++) { //player.getConfigManager().set((816 +
		 * i), (combination[i] == 1 ? 3 : combination[i] == 3 ? 1 :
		 * combination[i])); }
		 */
		// player.sendMessage("After selection=" + getComboString(player));
	}

	/**
	 * Gets the combo string.
	 * @param player the player.
	 * @return the player.
	 */
	public static String getComboString(Player player) {
		int index = 0;
		StringBuilder string = new StringBuilder();
		int[] combination = getCombination(player);
		for (int letterIndex : combination) {
			string.append(SELECTOR_LETTERS[index++][letterIndex]);
		}
		return string.toString().toUpperCase();
	}

	/**
	 * Teleports the player.
	 * @param player the player.
	 */
	private static final void teleport(final Player player) {
		int[] combination = getCombination(player);
		StringBuilder string = new StringBuilder();
		int index = 0;
		for (int letterIndex : combination) {
			string.append(SELECTOR_LETTERS[index++][letterIndex]);
		}
		// player.sendMessage("Selected combo=" +
		// string.toString().toUpperCase());
		FairyRing fairyRing = null;
		try {
			fairyRing = FairyRing.valueOf(string.toString().toUpperCase());
		} catch (IllegalArgumentException e) {

		}
		Location tile = fairyRing != null ? fairyRing.getTile() : null;
		if (fairyRing == null || tile == null) {
			Location center = new Location(2412, 4434, 0);
			tile = Location.create(2412, 4431, 0);
			;
			if (RandomFunction.random(2) == 1) {
				tile = center.transform(RandomFunction.random(2, 6), RandomFunction.random(2, 6), 0);
			} else {
				tile = center.transform(RandomFunction.random(-2, -6), RandomFunction.random(-2, -6), 0);
			}
			if (!RegionManager.isTeleportPermitted(tile) || RegionManager.getObject(tile) != null) {
				tile = Location.create(2412, 4431, 0);
			}
			GameWorld.Pulser.submit(new Pulse(4, player) {

				@Override
				public boolean pulse() {
					player.getDialogueInterpreter().sendDialogues(player, null, "Wow, fairy magic sure is useful, I hardly moved at all!");
					player.sendMessage("Please enter the code in slowly and <col=FF0000>one letter at a time</col> to avoid the interface");
					player.sendMessage("teleporting you to the wrong location.");
					return true;
				}

			});
		}
		if (fairyRing != null && fairyRing.getTile() != null) {
			player.sendMessage("" + fairyRing);
			if (!player.getSavedData().getGlobalData().hasTravelLog(fairyRing.ordinal())) {
				player.getSavedData().getGlobalData().setTravelLog(fairyRing.ordinal());
			}
		}
		if (fairyRing != null && fairyRing == FairyRing.DIS) {
			if (!player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(1, 1)) {
				player.getAchievementDiaryManager().updateTask(player, DiaryType.LUMBRIDGE, 1, 1, true);
			}
		}
		sendTeleport(player, tile);
	}

	/**
	 * Resets the players combos.
	 * @param player the player.
	 */
	private static void reset(Player player) {
		player.removeAttribute("fairy-delay");
		player.removeAttribute("fairy_location_combo");
		for (int i = 0; i < 3; i++) {
			player.getConfigManager().set((816 + i), 0);
		}
	}

	/**
	 * Sends the fairy teleport for a player.
	 * @param player The player.
	 * @param tile The tile to teleport to.
	 */
	public static final void sendTeleport(Player player, Location tile) {
		reset(player);
		player.getInterfaceManager().close();
		player.getTeleporter().send(tile, TeleportType.FAIRY_RING);
	}

	/**
	 * Updates the combinations.
	 * @param combination the combination.
	 * @return the combos.
	 */
	private static final int[] updateCombination(int[] combination) {
		int index = 0;
		for (int value : combination) {
			combination[index++] = (value & 0x3);
		}
		return combination;
	}

	/**
	 * Gets the combonations.
	 * @param player the player.
	 * @return the combos.
	 */
	public static final int[] getCombination(Player player) {
		int[] combination = player.getAttribute("fairy_location_combo", null);
		if (combination == null) {
			combination = new int[3];
			setCombination(player, combination);
		}
		return combination;
	}

	/**
	 * Sets the combonations.
	 * @param player the player.
	 * @param combination the combos.
	 */
	private static void setCombination(Player player, int[] combination) {
		player.setAttribute("fairy_location_combo", combination);
	}

	/**
	 * An enumeration holding data on the different fairy ring codes.
	 * @author Aero Date: 2 Sep 2014, 18:18:41
	 */
	public enum FairyRing {

		AIQ(Location.create(2996, 3114, 0), "Asgarnia: Mudskipper Point", 15), 
		AIR(Location.create(2700, 3247, 0), "Islands: South of Witchaven", 16), 
		AJQ(Location.create(2735, 5221, 0), "Dungeons: Dark cave south of Dorgesh-Kaann", 19), 
		ALR(Location.create(3059, 4875, 0), "Other realms: Abyssal Area", 28), 
		AJR(Location.create(2780, 3613, 0), "Kandarin: Slayer cave south-east of Rellekka", 20), 
		AJS(Location.create(2500, 3896, 0), "Islands: Penguins near Miscellania", 21), 
		AKQ(Location.create(2319, 3619, 0), "Kandarin: Piscatoris Hunter area", 23), 
		AKS(Location.create(2571, 2956, 0), "Feldip Hills: Jungle Hunter area", 25), 
		ALQ(Location.create(3597, 3495, 0), "Morytania: Haunted Woods east of Canifis", 27), 
		ALS(Location.create(2644, 3495, 0), "Kandarin: McGrubor's Wood", 29), 
		BIP(Location.create(3410, 3324, 0), "Islands: River Salve", 30), 
		BIQ(Location.create(3251, 3095, 0), "Kharidian Desert: Near Kalphite hive", 31),
		BIS(Location.create(2635, 3266, 0), "Kandarin: Ardougne Zoo unicorns", 33), 
		BJR(null, "Other Realms: Realm of the Fisher King", 36), 
		BKP(Location.create(2385, 3035, 0), "Feldip Hills: South of Castle Wars", 38),
		BKQ(Location.create(3041, 4532, 0), "Other realms: Enchanted Valley", 39), 
		BKR(Location.create(3469, 3431, 0), "Morytania: Mort Myre, south of Canifis", 40), 
		BLR(Location.create(2740, 3351, 0), "Kandarin: Legends' Guild", 44), 
		CIP(Location.create(2513, 3884, 0), "Islands: Miscellania", 46), 
		CIQ(Location.create(2528, 3127, 0), "Kandarin: North-west of Yanille", 47), 
		CJR(Location.create(2705, 3576, 0), "Kandarin: Sinclair Mansion", 52), 
		CKP(Location.create(2075, 4848, 0), "Other realms: Cosmic Entity's plane", 54), 
		CKR(Location.create(2801, 3003, 0), "Karamja: South of Tai Bwo Wannai Village", 56), 
		CKS(Location.create(3447, 3470, 0), "Morytania: Canifis", 57), 
		CLP(Location.create(3082, 3206, 0), "Islands: South of Draynor Village", 58), 		
		CLS(Location.create(2682, 3081, 0), "Islands: Jungle spiders near Yanille", 61), 
		DIR(Location.create(3038, 5348, 0), "Other realms: Goraks' Plane", 64), 
		DIS(Location.create(3108, 3149, 0), "Misthalin: Wizards' Tower", 65), 
		DJP(Location.create(2658, 3230, 0), "Kandarin: Tower of Life", 66),
		DJR(Location.create(2676, 3587, 0), "Kandarin: Sinclair Mansion (west)", 68), 
		DKP(Location.create(2900, 3111, 0), "Karamja: South of Musa Point", 70), 
		DKR(Location.create(3129, 3496, 0), "Misthalin: Edgeville", 72), 
		DKS(Location.create(2744, 3719, 0), "Kandarin: Snowy Hunter area", 73),
		DLQ(Location.create(3423, 3016, 0), "Kharidian Desert: North of Nardah", 75), 
		DLR(Location.create(2213, 3099, 0), "Islands: Poison Waste south of Isafdar", 76),
		AIS(null), 
		AIP(null), 
		AKP(null), 
		FAIRY_HOME(Location.create(2412, 4434, 0));

		/**
		 * The tile.
		 */
		private Location tile;

		/**
		 * The tip.
		 */
		private String tip;

		/**
		 * The child id.
		 */
		private final int childId;

		/**
		 * Constructs a new FairyRing.
		 * @param tile The tile.
		 */
		private FairyRing(Location tile) {
			this(tile, "", -1);
		}

		/**
		 * Constructs a new {@Code FairyRing} {@Code Object}
		 * @param tile the tile.
		 * @param tip the tip.
		 */
		private FairyRing(Location tile, String tip) {
			this(tile, tip, -1);
		}

		/**
		 * Constructs a new FairyRing.
		 * @param tile The tile.
		 * @param tip The tip.
		 */
		private FairyRing(Location tile, String tip, int childId) {
			this.tile = tile;
			this.tip = tip;
			this.childId = childId;
		}

		/**
		 * Draws the travel log.
		 * @param player the player.
		 */
		public static void drawLog(Player player) {
			/*
			 * for (int i = 0; i < CHILDS.length; i++) {
			 * player.getPacketDispatch().sendString("<br>" + CHILDS[i], 735,
			 * CHILDS[i]); }
			 */
			for (int i = 0; i < FairyRing.values().length; i++) {
				if (!player.getSavedData().getGlobalData().hasTravelLog(i)) {
					continue;
				}
				FairyRing ring = FairyRing.values()[i];
				if (ring.getChildId() == -1) {
					continue;
				}
				player.getPacketDispatch().sendString("<br>" + ring.getTip(), 735, ring.getChildId());
			}
		}

		/**
		 * Gets the landscape tile.
		 * @return The tile.
		 */
		public Location getTile() {
			return tile;
		}

		/**
		 * Gets the tip.
		 * @return The tip.
		 */
		public String getTip() {
			return tip;
		}

		/**
		 * Gets the childId.
		 * @return the childId
		 */
		public int getChildId() {
			return childId;
		}
	}
}
