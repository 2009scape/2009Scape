package core.game.content.global;

import core.game.node.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the type of bones.
 * @author Apache Ah64
 */
public enum Bones {
	
	BONES(2530, 4.5),
	WOLF_BONES(2859, 4.5), 
	BURNST_BONES(528, 4.5), 
	MONKEY_BONES(3183, 5), 
	MONKEY_BONES2(3179, 5), 
	BAT_BONES(530, 5.3), 
	BIG_BONES(532, 15), 
	JOGRE_BONES(3125, 15), 
	ZOGRE_BONES(4812, 12.5), 
	SHAIKAHAN_BONES(3123, 25), 
	BABY_DRAGON_BONES(534, 30), 
	WYVERN_BONES(6812, 50), 
	DRAGON_BONES(536, 72), 
	FAYRG(4830, 84), 
	RAURG_BONES(4832, 96), 
	DAGANNOTH(6729, 125), 
	OURG_BONES(4834, 140), 
	LAVA_DRAGON_BONES(14693, 85);

	/**
	 * Holds all bones.
	 */
	private static HashMap<Integer, Bones> bones = new HashMap<Integer, Bones>();


	/**
	 * The bone item id.
	 */
	private int itemId;

	/**
	 * The experience given by burying the bone.
	 */
	private double experience;

	/**
	 * Construct a new {@code Bones} {@code Object}.
	 * @param itemId The item id.
	 * @param experience The experience given by burying the bone.
	 */
	private Bones(int itemId, double experience) {
		this.itemId = itemId;
		this.experience = experience;
	}

	/**
	 * Gets the bone meal item.
	 * @return the item.
	 */
	public Item getBoneMeal() {
		return new Item(4255 + ordinal());
	}

	/**
	 * Get the bone experience given when you bury the bone.
	 * @return The experience.
	 */
	public double getExperience() {
		return experience;
	}

	public int getItemId() {
		return itemId;
	}

	/**
	 * Gets the bones for the bone meal.
	 * @param itemId the item.
	 * @return the bones.
	 */
	public static Bones forBoneMeal(int itemId) {
		for (Bones bone : Bones.values()) {
			if (bone.getBoneMeal().getId() == itemId) {
				return bone;
			}
		}
		return null;
	}

	/**
	 * Gets the config value for the bone.
	 * @param value the value.
	 * @param hopper hopper.
	 * @return {@code True} if so.
	 */
	public static Bones forConfigValue(int value, boolean hopper) {
		for (Bones bone : Bones.values()) {
			if (bone.getConfigValue(hopper) == value) {
				return bone;
			}
		}
		return null;
	}

	/**
	 * Gets the config value for the bone type.
	 * @param hopper the hopper.
	 * @return the value.
	 */
	public int getConfigValue(boolean hopper) {
		return ordinal() | (hopper ? 4 : 8) << 16;
	}

	/**
	 * Gets the bone ids.
	 * @return the ids.
	 */
	public static int[] getArray() {
		List<Integer> list = new ArrayList<>();
		for (int i : bones.keySet()) {
			list.add(i);
		}
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/**
	 * Get the bone.
	 * @param itemId The item id.
	 * @return The bone.
	 */
	public static Bones forId(int itemId) {
		return bones.get(itemId);
	}

	/**
	 * Construct the bones.
	 */
	static {
		for (Bones bone : Bones.values()) {
			bones.put(bone.itemId, bone);
		}
	}
}