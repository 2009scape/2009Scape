package plugin.skill.herblore;

import core.cache.def.impl.ItemDefinition;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the plugin used to handle the decanting of potions.
 * @author 'Vexia
 */
@InitializablePlugin
public final class PotionDecantingPlugin extends UseWithHandler {

	/**
	 * Represents the empty item vial.
	 */
	private static final Item EMPTY_VIAL = new Item(229, 1);

	/**
	 * Represents the array of potions to utilitize.
	 */
	public static final int[] POTIONS = new int[] {
	/* empty vial */229,
	/* Strength potion(4) */113,
	/* Strength potion(3) */115,
	/* Strength potion(2) */117,
	/* Strength potion(1) */119,
	/* Attack potion(3) */121,
	/* Attack potion(2) */123,
	/* Attack potion(1) */125,
	/* Restore potion(3) */127,
	/* Restore potion(2) */129,
	/* Restore potion(1) */131,
	/* Defence potion(3) */133,
	/* Defence potion(2) */135,
	/* Defence potion(1) */137,
	/* Prayer potion(3) */139,
	/* Prayer potion(2) */141,
	/* Prayer potion(1) */143,
	/* Super attack(3) */145,
	/* Super attack(2) */147,
	/* Super attack(1) */149,
	/* Fishing potion(3) */151,
	/* Fishing potion(2) */153,
	/* Fishing potion(1) */155,
	/* Super strength(3) */157,
	/* Super strength(2) */159,
	/* Super strength(1) */161,
	/* Super defence(3) */163,
	/* Super defence(2) */165,
	/* Super defence(1) */167,
	/* Ranging potion(3) */169,
	/* Ranging potion(2) */171,
	/* Ranging potion(1) */173,
	/* Antipoison(3) */175,
	/* Antipoison(2) */177,
	/* Antipoison(1) */179,
	/* Super antipoison(3) */181,
	/* Super antipoison(2) */183,
	/* Super antipoison(1) */185,
	/* Bravery potion */739,
	/* Cadava potion */756,
	/* Magic ogre potion */2395,
	/* Attack potion(4) */2428,
	/* Restore potion(4) */2430,
	/* Defence potion(4) */2432,
	/* Prayer potion(4) */2434,
	/* Super attack(4) */2436,
	/* Fishing potion(4) */2438,
	/* Super strength(4) */2440,
	/* Super defence(4) */2442,
	/* Ranging potion(4) */2444,
	/* Antipoison(4) */2446,
	/* Super antipoison(4) */2448,
	/* Antifire potion(4) */2452,
	/* Antifire potion(3) */2454,
	/* Antifire potion(2) */2456,
	/* Antifire potion(1) */2458,
	/* Energy potion(4) */3008,
	/* Energy potion(3) */3010,
	/* Energy potion(2) */3012,
	/* Energy potion(1) */3014,
	/* Super energy(4) */3016,
	/* Super energy(3) */3018,
	/* Super energy(2) */3020,
	/* Super energy(1) */3022,
	/* Super restore(4) */3024,
	/* Super restore(3) */3026,
	/* Super restore(2) */3028,
	/* Super restore(1) */3030,
	/* Agility potion(4) */3032,
	/* Agility potion(3) */3034,
	/* Agility potion(2) */3036,
	/* Agility potion(1) */3038,
	/* Magic potion(4) */3040,
	/* Magic potion(3) */3042,
	/* Magic potion(2) */3044,
	/* Magic potion(1) */3046,
	/* Troll potion */3265,
	/* Explosive potion */4045,
	/* Super kebab */4608,
	/* Strange potion */4836,
	/* Antipoison+(4) */5943,
	/* Antipoison+(3) */5945,
	/* Antipoison+(2) */5947,
	/* Antipoison+(1) */5949,
	/* Antipoison++(4) */5952,
	/* Antipoison++(3) */5954,
	/* Antipoison++(2) */5956,
	/* Antipoison++(1) */5958,
	/* Supercompost */6034,
	/* Compost potion(4) */6470,
	/* Compost potion(3) */6472,
	/* Compost potion(2) */6474,
	/* Compost potion(1) */6476,
	/* Combat potion(4) */9739,
	/* Combat potion(3) */9741,
	/* Combat potion(2) */9743,
	/* Combat potion(1) */9745,
	/* Hunter potion(4) */9998,
	/* Hunter potion(3) */10000,
	/* Hunter potion(2) */10002,
	/* Hunter potion(1) */10004,
	/* Antipoison mix(2) */11433,
	/* Antipoison mix(1) */11435,
	/* Superattack mix(2) */11469,
	/* Superattack mix(1) */11471,
	/* Antifire mix(2) */11505,
	/* Antifire mix(1) */11507,
	/* Goblin potion (4) */11809,
	/* Goblin potion (3) */11810,
	/* Goblin potion (2) */11811,
	/* Goblin potion (1) */11812,
	/* Summoning potion(4) */12140,
	/* Summoning potion(3) */12142,
	/* Summoning potion(2) */12144,
	/* Summoning potion(1) */12146, 
	/* Saradomin brews */ 6685, 6687, 6689, 6691, 
	/* Zamorak brews*/ 2450, 189, 191, 193,
	/* Extended antifire potions*/ 14753, 14755, 14757, 14759,
	/* Super ranging */ 14776, 14777, 14778, 14779,
	/* Super magic */ 14780, 14781, 14782, 14783,
	/* Overloads */ 14784, 14785, 14786, 14787,
	/*Super Combat Pots */ 14871, 14869, 14867, 14865};

	/**
	 * Constructs a new {@code PotionDecantingPlugin} {@code Object}.
	 */
	public PotionDecantingPlugin() {
		super(POTIONS);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int i : POTIONS) {
			addHandler(i, ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Item item = event.getUsedItem();
		final Item other = event.getBaseItem();
		final String itemName = formatName(item);
		final String otherName = formatName(other);
		if (!itemName.equals(otherName) && (item.getId() != 229 && other.getId() != 229)) {
			return false;
		}
		final int itemDose = getPotionDose(item);
		final int otherDose = getPotionDose(other);
		if (flagged(itemDose, otherDose)) {
			return false;
		}
		final int[] newDoses = getDoses(itemDose, otherDose);
		if (itemDose == 4 && otherDose == 0 || otherDose == 4 && itemDose == 0) {
			player.getInventory().replace(getItem(item.getName().contains("Vial") ? otherName : itemName, newDoses[1]), item.getSlot());
			player.getInventory().replace(getItem(item.getName().contains("Vial") ? otherName : itemName, newDoses[1]), other.getSlot());
			player.getPacketDispatch().sendMessage("You decant the potion into two equal parts.");
		} else {
			player.getInventory().replace(getItem(item.getName().contains("Vial") ? otherName : itemName, newDoses[0]), other.getSlot());
			player.getInventory().replace(getItem(item.getName().contains("Vial") ? otherName : itemName, newDoses[1]), item.getSlot());
			player.getPacketDispatch().sendMessage("You have combined the liquid into " + (newDoses[0] == 0 ? newDoses[1] : newDoses[0]) + " doses.");
		}
		return true;
	}

	/**
	 * Decants all the potions in a players inventory.
	 * @param player The player
	 */
	public static void decant(Player player) {
		Map<String, Integer> potions = new HashMap<>();
		
		int totalVials = 0;
		int newVials = 0;
		int vialsToGive;
		
		for (Item item : player.getInventory().toArray()) {
			if (item != null) {
				if (getPotionDose(item) > 0 && isPot(item.getId())) {
					String itemName = formatName(item);
					item.setAmount(item.getAmount());
					totalVials += item.getAmount();
					if (player.getInventory().remove(item)) {
						if (potions.get(itemName) != null) {
							potions.put(itemName, potions.get(itemName) + (item.getAmount() * getPotionDose(item)));
						} else {
							potions.put(itemName, item.getAmount() * getPotionDose(item));
						}
					}
				}
			}
		}	
		
		for (Map.Entry<String, Integer> entry : potions.entrySet()) {
			int amount = entry.getValue() / 4;
			int excess = entry.getValue() % 4;
			newVials += amount + (excess > 0 ? 1 : 0);
			Item fourDose = getItem(entry.getKey(), 4);
			player.getInventory().add(new Item(fourDose.getId() + 1, amount), player);
			if (excess > 0) {
			player.getInventory().add(new Item(getItem(entry.getKey(), excess).getId() + 1), player);
			}
		}	
		
		vialsToGive = totalVials - newVials;
		
		if (vialsToGive > 0) { 
			player.getInventory().add(new Item(EMPTY_VIAL.getId() + 1, vialsToGive), player);
		}
	}
	
	/**
	 * Method used to format a name of a potion.
	 * @param item the item.
	 * @return the name.
	 */
	public static String formatName(final Item item) {
		return item.getName().replace("(1)", "").replace("(2)", "").replace("(3)", "").replace("(4)", "").trim();
	}

	/**
	 * Method used to get the potion does between two potions.
	 * @param item the item.
	 * @return the doses.
	 */
	public static int getPotionDose(final Item item) {
		return item.getName().contains("(1)") ? 1 : item.getName().contains("(2)") ? 2 : item.getName().contains("(3)") ? 3 : item.getName().contains("(4)") ? 4 : 0;
	}

	/**
	 * Method used to check if a new potion ca be created based on the doses.
	 * @param itemDose the item dose.
	 * @param otherDose the other dose.
	 * @return <code>True</code> if flagged.
	 */
	public boolean flagged(final int itemDose, final int otherDose) {
		return (itemDose == 4 && otherDose != 0 || itemDose != 4 && otherDose == 0 || itemDose == 4 && otherDose == 3 || itemDose == 0 && otherDose != 4) || (otherDose == 4 && itemDose != 0 || otherDose != 4 && itemDose == 0 || otherDose == 4 && itemDose == 3 || otherDose == 0 && itemDose != 4);
	}

	/**
	 * Method used to return the doses of the new items.
	 * @param itemDose the item dose.
	 * @param otherDose the other dose.
	 * @return the new doses.
	 */
	public int[] getDoses(final int itemDose, final int otherDose) {
		return itemDose == 1 && otherDose == 1 ? new int[] { 2, 0 } : itemDose == 3 && otherDose == 3 ? new int[] { 4, 2 } : itemDose == 2 && otherDose == 2 ? new int[] { 4, 0 } : itemDose == 2 && otherDose == 3 ? new int[] { 4, 1 } : itemDose == 1 && otherDose == 2 ? new int[] { 0, 3 } : itemDose == 4 && otherDose == 0 ? new int[] { 2, 2 } : itemDose == 1 && otherDose == 3 ? new int[] { 0, 4 } : otherDose == 2 && itemDose == 3 ? new int[] { 1, 4 } : otherDose == 1 && itemDose == 2 ? new int[] { 0, 3 } : otherDose == 4 && itemDose == 0 ? new int[] { 2, 2 } : otherDose == 1 && itemDose == 3 ? new int[] { 0, 4 } : itemDose == 0 && otherDose == 4 ? new int[] { 2, 2 } : otherDose == 4 && itemDose == 0 ? new int[] { 2, 2 } : new int[] { 0, 0 };
	}

	/**
	 * Method used to get the new item.
	 * @param name the name.
	 * @param dose the dose of the new item.
	 * @return the new item.
	 */
	public static Item getItem(String name, int dose) {
		ItemDefinition def = null;
		name += (oldSchool(name) ? " " : "")+"(" + dose + ")";	
		if (dose == 0) {
			return EMPTY_VIAL;
		}
		for (int id : POTIONS) {
			def = ItemDefinition.forId(id);
			if (def.getName().equals(name)) {
				return new Item(id);
			}
		}
		return null;
	}
	
	/**
	 * Checks if an item is a pot.
	 * @param itemId The item id.
	 * @return true if so.
	 */
	public static boolean isPot(int itemId) {
		for (int i : POTIONS) {
			if (itemId == i || itemId == (i + 1)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * If an item is oldschool... add an extra space.
	 * @param name
	 * @return
	 * @author Jagex being retarded
	 */
	private static boolean oldSchool(String name){
		if(name.contains("xtended") || name.contains("uper rangin") || name.contains("uper magic") || name.contains("verload")){
			return true;
		}
		return false;
	}
}
