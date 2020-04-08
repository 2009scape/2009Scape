package org.crandor.game.content.skill.member.farming.pot;

import org.crandor.game.node.item.Item;

import java.util.HashMap;

/**
 * Represents the data for saplings.
 * @author 'Vexia, modified by ceikry
 */
public enum Saplings {
	OAK(new Item(5312), new Item(5358), new Item(5370), 15), WILLOW(new Item(5313), new Item(5359), new Item(5371), 30), MAPLE(new Item(5314), new Item(5360), new Item(5372), 45), YEW(new Item(5315), new Item(5361), new Item(5373), 60), MAGIC(new Item(5316), new Item(5362), new Item(5374), 75), APPLE(new Item(5283), new Item(5480), new Item(5496), 27), BANANA(new Item(5284), new Item(5481), new Item(5497), 33), ORANGE(new Item(5285), new Item(5482), new Item(5498), 39), CURRY(new Item(5286), new Item(5483), new Item(5499), 42), PINEAPPLE(new Item(5287), new Item(5484), new Item(5500), 51), PAPAYA(new Item(5288), new Item(5485), new Item(5501), 57), PALM(new Item(5289), new Item(5486), new Item(5502), 68), CALQUAT(new Item(5290), new Item(5487), new Item(5503), 72);

	/**
	 * Represents the seed.
	 */
	private final Item seed;

	/**
	 * Represents the item seedling.
	 */
	private final Item seedling;

	/**
	 * Represents the item sappling.
	 */
	private final Item sapling;

	/**
	 * Represents the required farming level.
	 */
	private final int level;

	/**
	 * Constructs a new {@code Saplings} {@code Object}.
	 * @param seed the seed.
	 * @param seedling the seedling.
	 * @param sapling the sapling.
	 * @param level the level.
	 */
	private Saplings(Item seed, Item seedling, Item sapling, final int level) {
		this.seed = seed;
		this.seedling = seedling;
		this.sapling = sapling;
		this.level = level;
	}

	public static HashMap<Integer,Saplings> seedMap = new HashMap<>();
	public static HashMap<Integer,Saplings> seedlingMap = new HashMap<>();

	static {
		Saplings[] sapArray = Saplings.values();
		int sapLength = sapArray.length;
		for(int x = 0; x < sapLength; x++){
			Saplings current = sapArray[x];
			seedMap.putIfAbsent(current.seed.getId(),current);
			seedlingMap.putIfAbsent(current.seedling.getId(),current);
		}
	}

	/**
	 * Gets the sapling by the seed id.
	 * @param seed the seed.
	 * @return the sapling.
	 */
	public static Saplings forSeed(final Item seed) {
		return seedMap.get(seed.getId());
	}

	/**
	 * Gets the sapling by the seedling id.
	 * @param seedling the seedling.
	 * @return the sapling.
	 */
	public static Saplings forSeedling(final Item seedling) {
		return seedlingMap.get(seedling.getId());
	}

	/**
	 * Gets the seed.
	 * @return The seed.
	 */
	public Item getSeed() {
		return seed;
	}

	/**
	 * Gets the seedling.
	 * @return The seedling.
	 */
	public Item getSeedling() {
		return seedling;
	}

	/**
	 * Gets the sapling.
	 * @return The sapling.
	 */
	public Item getSapling() {
		return sapling;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

}
