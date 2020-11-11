package core.game.content.global;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;

/**
 * Represents an enchanted jewellery.
 * @author Vexia
 */
public enum EnchantedJewellery {
	RING_OF_SLAYING(new String[] { "Slayer Tower.", "Fremmenik Slayer Dungeon.", "Pollniveach Slayer Dungeon.", "Nowhere." }, new Location[] { Location.create(3429, 3533, 0), Location.create(2793, 3615, 0), Location.create(3313, 2960, 0) }, true, 13281, 13282, 13283, 13284, 13285, 13286, 13287, 13288), 
	RING_OF_DUELING(new String[] { "Al Kharid Duel Arena.", "Castle Wars Arena.", "Nowhere." }, new Location[] { Location.create(3314, 3235, 0), Location.create(2442, 3089, 0) }, true, 2552, 2554, 2556, 2558, 2560, 2562, 2564, 2566), 
	AMULET_OF_GLORY(new String[] { "Edgeville", "Karamja", "Draynor Village", "Al-Kharid", "Nowhere." }, new Location[] { Location.create(3087, 3495, 0), Location.create(2919, 3175, 0), Location.create(3104, 3249, 0), Location.create(3304, 3124, 0) }, 1712, 1710, 1708, 1706, 1704), 
	AMULET_OF_GLORY_T(new String[] { "Edgeville", "Karamja", "Draynor Village", "Al-Kharid", "Nowhere." }, new Location[] { Location.create(3087, 3495, 0), Location.create(2919, 3175, 0), Location.create(3081, 3250, 0), Location.create(3304, 3124, 0) }, 10354, 10356, 10358, 10360, 10362), 
	GAMES_NECKLACE(new String[] { "Burthorpe", "Barbarian Assault", "Clan Wars", "Bounty Hunter", "Corporeal Beast" }, new Location[] { Location.create(2899, 3563, 0), Location.create(2520, 3571, 0), Location.create(3266, 3686, 0), Location.create(3179, 3685, 0), Location.create(2885, 4372, 2) }, true, 3853, 3855, 3857, 3859, 3861, 3863, 3865, 3867), 
	DIGSITE_PENDANT(new String[] {}, new Location[] { Location.create(3342, 3445, 0) }, true, 11194, 11193, 11192, 11191, 11190), 
	COMBAT_BRACELET(new String[] { "Champions' Guild", "Monastery", "Ranging Guild", "Warriors' Guild", "Nowhere." }, new Location[] { Location.create(3191, 3365, 0), Location.create(3052, 3472, 0), Location.create(2657, 3439, 0), Location.create(2878, 3546, 0) }, 11118, 11120, 11122, 11124, 11126), 
	SKILLS_NECKLACE(new String[] { "Fishing Guild", "Mining Guild", "Crafting Guild", "Cooking Guild", "Nowhere." }, new Location[] { Location.create(2611, 3392, 0), Location.create(3016, 3338, 0), Location.create(2933, 3290, 0), Location.create(3143, 3442, 0) }, 11105, 11107, 11109, 11111, 11113),
	RING_OF_WEALTH(new String[] {"Grand Exchange","Nowhere."}, new Location[] {Location.create(3163, 3464, 0)},14646,14644,14642,14640,14638);

	/**
	 * Represents the teleport animation.
	 */
	private static final Animation ANIMATION = new Animation(714);

	/**
	 * Represents the graphics to use.
	 */
	private static final Graphics GRAPHICS = new Graphics(308, 100, 50);

	/**
	 * Represents the charge numbers.
	 */
	private static final char[] NUMBERS = new char[] { '1', '2', '3', '4', '5', '6', '7', '8' };

	/**
	 * Represents the teleport options.
	 */
	private final String[] options;

	/**
	 * Represents the locations.
	 */
	private final Location[] locations;

	/**
	 * Represents the ids of the jewellery.
	 */
	private final int[] ids;

	/**
	 * Represents if it crumbles away into nothing.
	 */
	private final boolean crumble;

	/**
	 * Constructs a new {@code EnchantedJewelleryPlugin} {@code Object}.
	 * @param options the options.
	 * @param locations the locations.
	 * @parma crumble if it crumbles.
	 * @param ids the ids.
	 */
	EnchantedJewellery(final String[] options, final Location[] locations, final boolean crumble, final int... ids) {
		this.options = options;
		this.locations = locations;
		this.ids = ids;
		this.crumble = crumble;
	}

	/**
	 * Constructs a new {@code EnchantedJewelleryPlugin} {@code Object}.
	 * @param options the options.
	 * @param locations the locations.
	 * @param ids the ids.
	 */
	EnchantedJewellery(final String[] options, final Location[] locations, final int... ids) {
		this(options, locations, false, ids);
	}

	/**
	 * Method used to teleport the player to the desired location.
	 * @param player the player.
	 * @param item the item.
	 * @param index the index.
	 * @param operate If the player is operating.
	 */
	public void use(final Player player, final Item item, final int index, boolean operate) {
		if ((index + 1) == getIds().length || item.getSlot() < 0) {
			return;
		}
		if (index > getLocations().length - 1) {
			return;
		}
		int itemIndex = getItemIndex(item);
		Item replace = item;
		if (!isLast(itemIndex)) {
			if (!(isCrumble() && itemIndex == getIds().length - 1)) {
				replace = getReplace(getNext(itemIndex));
			}
		} else {
			if (!isCrumble()) {
				replace = getReplace(getIds()[getIds().length - 1]);
			}
		}
		if (index > getLocations().length - 1) {
			return;
		}
		if (!operate && !player.getInventory().containsItem(item)) {
			player.sendMessage("Ooops, you don't have it anymore ;)");
			return;
		} else if (operate && !player.getEquipment().containsItem(item)) {
			player.sendMessage("Ooops, you don't have it anymore ;)");
			return;
		}
		if (teleport(player, itemIndex, replace, getLocation(index))) {
			if (!isLast(itemIndex) && !(isCrumble() && itemIndex == getIds().length - 1)) {
				if (operate) {
					player.getEquipment().replace(replace, item.getSlot());
				} else {
					player.getInventory().replace(replace, item.getSlot());
				}
			} else {
				if (isCrumble()) {
					if (operate) {
						player.getEquipment().replace(null, item.getSlot());
					} else {
						if(item.getName().contains("slaying")){
							player.getInventory().replace(new Item(4155, 1), item.getSlot());
							player.sendMessage("Your Ring of Slaying reverts back into a regular enchanted gem.");
						} else {
							player.getInventory().replace(null, item.getSlot());	
						}
					}
				}
			}
			if (getLocation(index).withinDistance(new Location(2657,3439,0))) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE,2,0);
			}
		}
	}

	/**
	 * Method used to teleport to a location.
	 * @param player the player.
	 * @param itemIndex the old item index.
	 * @param item the item.
	 * @param location the location.
	 */
	private boolean teleport(final Player player, final int itemIndex, final Item item, final Location location) {
		if (player.isTeleBlocked()) {
			player.sendMessage("A magical force has stopped you from teleporting.");
			return false;
		}
		if (!player.getZoneMonitor().teleport(1, item)) {
			return false;
		}
		player.lock();
		player.visualize(ANIMATION, GRAPHICS);
		player.getImpactHandler().setDisabledTicks(4);
		GameWorld.getPulser().submit(new Pulse(4, player) {
			@Override
			public boolean pulse() {
				player.unlock();
				player.getProperties().setTeleportLocation(location);
				player.getAnimator().reset();
				return true;
			}
		});
		return true;
	}

	/**
	 * Gets the charges of an item.
	 * @param item the item.
	 * @return the charges.
	 */
	public static String getCharges(Item item) {
		String[] tokens = item.getName().replace("(t", "(").replace("(", " ").replace(")", "").split(" ");
		return tokens[tokens.length - 1];
	}

	/**
	 * Gets the replacement item.
	 * @param id the id.
	 * @return the item.
	 */
	public Item getReplace(int id) {
		return new Item(id);
	}

	/**
	 * Gets the name.
	 * @param item the item.
	 * @return the name.
	 */
	public String getName(Item item) {
		String name = item.getName().toLowerCase().replace("(t", "(").replace("(", "").replace(")", "");
		for (char number : NUMBERS) {
			name = name.replace(number, '/');
		}
		return name.trim().replace("/", "");
	}

	/**
	 * Gets the name type.
	 * @param item the item.
	 * @return
	 */
	public String getNameType(Item item) {
		return this == GAMES_NECKLACE ? "games necklace" : this == DIGSITE_PENDANT ? "necklace" : this == COMBAT_BRACELET ? "bracelet" : this == SKILLS_NECKLACE ? "necklace" : item.getName().toLowerCase().split(" ")[0];
	}

	/**
	 * Checks if the index is last.
	 * @param index the index.
	 * @return {@code True} if so.
	 */
	public boolean isLast(int index) {
		return !isCrumble() ? index == (ids.length - 1) : index == ids.length;
	}

	/**
	 * Gets the next index.
	 * @param index the index.
	 * @return the new id
	 */
	public int getNext(int index) {
		return ids[index + 1];
	}

	/**
	 * Gets the location.
	 * @param index the index.
	 * @return the location.
	 */
	public Location getLocation(int index) {
		if (index > locations.length) {
			index = locations.length - 1;
		}
		return locations[index];
	}

	/**
	 * Gets the options.
	 * @return The options.
	 */
	public String[] getOptions() {
		return options;
	}

	/**
	 * Gets the locations.
	 * @return The locations.
	 */
	public Location[] getLocations() {
		return locations;
	}

	/**
	 * Gets the ids.
	 * @return The ids.
	 */
	public int[] getIds() {
		return ids;
	}

	public boolean isCrumble() {
		return crumble;
	}

	/**
	 * Gets the enchanted jewellery.
	 * @param item the item.
	 * @return {@code EnchantedJewellery}.
	 */
	public static EnchantedJewellery forItem(final Item item) {
		for (EnchantedJewellery jewellery : values()) {
			for (int i : jewellery.getIds()) {
				if (i == item.getId()) {
					return jewellery;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the index.
	 * @param item the item.
	 * @return the item index.
	 */
	public int getItemIndex(Item item) {
		for (int i = 0; i < getIds().length; i++) {
			if (getIds()[i] == item.getId()) {
				return i;
			}
		}
		return -1;
	}
}