package org.crandor.game.content.skill.free.crafting.jewellery;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

import java.util.HashMap;

/**
 * Represents a useful class for jewellery crafting information.
 * @author Ceikry
 */
public class JewelleryCrafting {

	/**
	 * Represents constants of useful items.
	 */
	public static final int RING_MOULD = 1592, AMULET_MOULD = 1595, NECKLACE_MOULD = 1597, BRACELET_MOULD = 11065, GOLD_BAR = 2357, SAPPHIRE = 1607, EMERALD = 1605, RUBY = 1603, DIAMOND = 1601, DRAGONSTONE = 1615, ONYX = 6573;

	/**
	 * Represents the anum of jewellery data.
	 * @author 'Vexia
	 */
	public enum JewelleryItem {
		//Ring
		GOLD_RING(5, 15, 19, 1635, GOLD_BAR),
		SAPPIRE_RING(20, 40, 21, 1637, SAPPHIRE, GOLD_BAR),
		EMERALD_RING(27, 55, 23, 1639, EMERALD, GOLD_BAR),
		RUBY_RING(34, 70, 25, 1641, RUBY, GOLD_BAR),
		DIAMOND_RING(43, 85, 27, 1643, DIAMOND, GOLD_BAR),
		DRAGONSTONE_RING(55, 100, 29, 1645, DRAGONSTONE, GOLD_BAR),
		ONYX_RING(67, 115, 31, 6575, 6573, GOLD_BAR),

		//Necklaces
		GOLD_NECKLACE(6, 20, 41, 1654, 2357),
		SAPPHIRE_NECKLACE(22, 55, 43, 1656, 1607, 2357),
		EMERALD_NECKLACE(29, 60, 45, 1658, 1605, 2357),
		RUBY_NECKLACE(40, 75, 47, 1660, 1603, 2357),
		DIAMOND_NECKLACE(56, 90, 49, 1662, 1601, 2357),
		DRAGONSTONE_NECKLACE(72, 105, 51, 1664, 1615, 2357),
		ONYX_NECKLACE(82, 120, 53, 6577, 6573, 2357),
		SLAYER_RING(75, 15, 34, 13281, 4155, GOLD_BAR),


		//Amulet
		GOLD_AMULET(8, 30, 60, 1673, 2357),
		SAPPHIRE_AMULET(24, 65, 62, 1675, 1607, 2357),
		EMERALD_AMULET(31, 70, 64, 1677, 1605, 2357),
		RUBY_AMULET(50, 85, 66, 1679, 1603, 2357),
		DIAMOND_AMULET(70, 100, 68, 1681, 1601, 2357),
		DRAGONSTONE_AMULET(80, 150, 70, 1683, 1615, 2357),
		ONYX_AMULET(90, 165, 72, 6579, 6573, 2357),

		//Bracelet
		GOLD_BRACELET(7, 25, 79, 11069, 2357),
		SAPPHIRE_BRACELET(23, 60, 81, 11072, 1607, 2357),
		EMERALD_BRACELET(30, 65, 83, 11076, 1605, 2357),
		RUBY_BRACELET(42, 80, 85, 11085, 1603, 2357),
		DIAMOND_BRACELET(58, 95, 87, 11092, 1601, 2357),
		DRAGONSTONE_BRACELET(74, 110, 89, 11115, 1615, 2357),
		ONYX_BRACELET(84, 125, 91, 11130, 6573, 2357);

		public static HashMap<Integer, JewelleryItem> productMap = new HashMap<>();
		static{
			JewelleryItem[] jewelleryArray = JewelleryItem.values();
			int length = jewelleryArray.length;
			for(int i = 0; i < length; i++){
				productMap.putIfAbsent(jewelleryArray[i].sendItem,jewelleryArray[i]);
			}
		}

		/**
		 * Represents the item ids.
		 */
		private final int[] items;

		/**
		 * Represents the send item.
		 */
		private final int sendItem;

		/**
		 * Represents the component id.
		 */
		private final int componentId;

		/**
		 * Represents the level.
		 */
		private final int level;

		/**
		 * Represesnts the experience gained.
		 */
		private final double experience;

		/**
		 * Constructs a new {@code Jewellery.java} {@code Object}.
		 * @param mould the mould.
		 * @param level the level.
		 * @param experience the experience.
		 */
		JewelleryItem(int level, int experience, int componentId, int sendItem, int... items) {
			this.level = level;
			this.experience = experience;
			this.componentId = componentId;
			this.sendItem = sendItem;
			this.items = items;
		}

		/**
		 * Gets the jewellery by the product.
		 * @param id the id.
		 * @return the jewellery.
		 */
		public static JewelleryItem forProduct(int id) {
			return productMap.get(id);
		}

		/**
		 * Gets the items.
		 * @return The items.
		 */
		public int[] getItems() {
			return items;
		}

		/**
		 * Gets the sendItem.
		 * @return The sendItem.
		 */
		public int getSendItem() {
			return sendItem;
		}

		/**
		 * Gets the componentId.
		 * @return The componentId.
		 */
		public int getComponentId() {
			return componentId;
		}

		/**
		 * Gets the level.
		 * @return The level.
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Gets the experience.
		 * @return The experience.
		 */
		public double getExperience() {
			return experience;
		}
	}

	/**
	 * Method used to open the jewellery crafting interface.
	 * @param player the player.
	 */
	public static void open(final Player player) {
		player.getInterfaceManager().open(new Component(446));
		if (player.getInventory().contains(RING_MOULD, 1)) {
			player.getPacketDispatch().sendInterfaceConfig(446, 14, true);
			/** hides the mould and text. */
		}
		if (player.getInventory().contains(NECKLACE_MOULD, 1)) {
			player.getPacketDispatch().sendInterfaceConfig(446, 36, true);
			/** hides the mould and text. */
		}
		if (player.getInventory().contains(AMULET_MOULD, 1)) {
			player.getPacketDispatch().sendInterfaceConfig(446, 55, true);
			/** hides the mould and text. */
		}
		if (player.getInventory().contains(BRACELET_MOULD, 1)) {
			player.getPacketDispatch().sendInterfaceConfig(446, 74, true);
			/** hides the mould and text. */
		}
		for (JewelleryItem data : JewelleryItem.values()) {
			int length = 0;
			for (int i = 0; i < data.items.length; i++) {
				if (player.getInventory().contains(data.getItems()[i], 1)) {
					length++;
				}
			}
			if (!player.getInventory().contains(mouldFor(data.name()), 1)) {
				length--;
			}
			if (length == data.getItems().length && player.getSkills().getLevel(Skills.CRAFTING) > data.getLevel()) {
				player.getPacketDispatch().sendItemZoomOnInterface(data.getSendItem(), 170, 446, data.getComponentId());
			} else {
				String name = ItemDefinition.forId(data.getSendItem()).getName().toLowerCase();
				if (name.contains("amulet") && player.getInventory().contains(mouldFor(data.name()), 1)) {
					for (int i = 0; i < data.items.length; i++) {
						if (!player.getInventory().contains(data.getItems()[i], 1)) {
							player.getPacketDispatch().sendItemZoomOnInterface(1685, 220, 446, data.getComponentId());
							break;
						}
					}
				}
				if (name.contains("ring") && !player.getInventory().contains(RING_MOULD, 1)) {
					continue;
				}
				if (name.contains("necklace") && !player.getInventory().contains(NECKLACE_MOULD, 1)) {
					continue;
				}
				if (data == JewelleryItem.DRAGONSTONE_AMULET && !player.getInventory().contains(AMULET_MOULD, 1)) {
					continue;
				}
				if (name.contains("amulet") || name.equalsIgnoreCase("AMULET_MOULD") && !player.getInventory().contains(AMULET_MOULD, 1)) {
					continue;
				}
				if (name.contains("bracelet") && !player.getInventory().contains(BRACELET_MOULD, 1)) {
					continue;
				}
				player.getPacketDispatch().sendItemZoomOnInterface(name.contains("ring") ? 1647 : name.contains("necklace") ? 1666 : name.contains("amulet") || name.contains("ammy") ? 1685 : name.contains("bracelet") ? 11067 : -1, 1, 446, data.getComponentId());
			}
		}
	}

	/**
	 * Represents the making of a jewellery.
	 * @param player the player.
	 * @param data the data.
	 * @param amount the amount.
	 */
	public static final void make(final Player player, JewelleryItem data, int amount) {
		int length = 0;
		int amt = 0;
		if (data.name().contains("GOLD")) {
			amt = player.getInventory().getAmount(new Item(GOLD_BAR));
		} else {
			int first = player.getInventory().getAmount(new Item(data.getItems()[0]));
			int second = player.getInventory().getAmount(new Item(data.getItems()[1]));
			if (first == second) {
				amt = first;
			} else if (first > second) {
				amt = second;
			} else {
				amt = first;
			}
		}
		if (amount > amt) {
			amount = amt;
		}
		for (int i = 0; i < data.items.length; i++) {
			if (player.getInventory().contains(data.getItems()[i], amount)) {
				length++;
			}
		}
		if (length != data.getItems().length) {
			player.getPacketDispatch().sendMessage("You don't have the required items to make this item.");
			return;
		}
		if (player.getSkills().getLevel(Skills.CRAFTING) < data.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a crafting level of " + data.getLevel() + " to craft this.");
			return;
		}
		Item items[] = new Item[data.items.length];
		int index = 0;
		for (int i = 0; i < data.items.length; i++) {
			items[index] = new Item(data.items[i], 1 * amount);
			index++;
		}
		player.getInterfaceManager().close();
		player.getPulseManager().run(new JewelleryPulse(player, null, data, amount));
	}

	/**
	 * Gets the mould id for the name.
	 * @param name the name.
	 * @return the mould id.
	 */
	public static int mouldFor(String name) {
		name = name.toLowerCase();
		if (name.contains("ring")) {
			return RING_MOULD;
		}
		if (name.contains("necklace")) {
			return NECKLACE_MOULD;
		}
		if (name.contains("amulet")) {
			return AMULET_MOULD;
		}
		if (name.contains("bracelet")) {
			return BRACELET_MOULD;
		}
		return -1;
	}
}
