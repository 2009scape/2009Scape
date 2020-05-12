package org.crandor.game.content.skill.member.thieving;

import org.crandor.ServerConstants;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.repository.Repository;
import org.crandor.tools.RandomFunction;

import java.util.*;
import java.util.stream.Stream;

/**
 * Represents a thieving stall.
 * @author Ceikry, Woahscam
 */
public enum Stall {
	VEGETABLE_STALL(new Integer[]{4706, 4708}, new Integer[] { 634 }, 2, new Item[]{new Item(1957, 1), new Item(1965, 1), new Item(1942, 1), new Item(1982, 1), new Item(1550, 1)}, 10, 4,"vegetables"),
	BAKER_STALL(new Integer[]{2561, 6163, 34384}, new Integer[] { 634, 6984, 34381 }, 5, new Item[]{new Item(1891, 1), new Item(2309, 1), new Item(1901, 1)}, 16, 4,"pastries"),
	CRAFTING_STALL(new Integer[]{4874, 6166}, new Integer[] { 4797, 6984 }, 5, new Item[]{new Item(1592, 1), new Item(1597, 1), new Item(1755, 1)}, 16, 12,"crafting supplies"),
	TEA_STALL(new Integer[]{635, 6574}, new Integer[] { 634, 6573 }, 5, new Item[]{new Item(712, 1)}, 16, 12,"tea"),
	SILK_STALL(new Integer[]{34383, 2560}, new Integer[] { 34381, 634 }, 20, new Item[]{new Item(950, 1)}, 24, 13,"silk"),
	WINE_STALL(new Integer[]{2046}, new Integer[] { 634 }, 22, new Item[]{new Item(1935, 1), new Item(1937, 1), new Item(1993, 1), new Item(7919, 1)}, 27, 27,"wine"), //OBJECT MISSING IN CACHE
	MARKET_SEED_STALL(new Integer[]{7053}, new Integer[] { 634 }, 27, new Item[]{new Item(5096, 1), new Item(5097, 1), new Item(5101, 1), new Item(5318, 1), new Item(5319, 1), new Item(5324, 1)}, 10, 19,"seeds"),
	FUR_STALL(new Integer[]{34387, 2563}, new Integer[] { 34381, 634 }, 35, new Item[]{new Item(6814, 1), new Item(958, 1)}, 36, 25,"fur"),
	FISH_STALL(new Integer[]{4277, 4705, 4707}, new Integer[] { 634 }, 42, new Item[]{new Item(331, 1), new Item(359, 1), new Item(377, 1)}, 42, 27,"fish"),
	CROSSBOW_STALL(new Integer[]{17031}, new Integer[] { 6984 }, 49, new Item[]{new Item(877, 3), new Item(9420, 1), new Item(9440, 1)}, 52, 19,"equipment"),
	SILVER_STALL(new Integer[]{2565, 6164, 34382}, new Integer[] { 634, 6984, 34381}, 50, new Item[]{new Item(442, 1)}, 54, 50,"jewellery"),
	SPICE_STALL(new Integer[]{6166}, new Integer[] { 6984 }, 65, new Item[]{new Item(2007, 1)}, 16, 134,"spices"),
	GEM_STALL(new Integer[]{2562, 6162, 34385}, new Integer[] { 634, 6984, 34381 }, 75, new Item[]{new Item(1623, 1), new Item(1605, 1), new Item(1603, 1), new Item(1601, 1)}, 160, 300,"gems"),
	//Ape Atoll Stalls
	SCIMITAR_STALL(new Integer[]{4878}, new Integer[] { 4797 }, 65, new Item[]{new Item(1323, 1)}, 100, 134,"equipment"),
	MAGIC_STALL(new Integer[]{4877}, new Integer[] { 4797 }, 65, new Item[]{new Item(556, 1), new Item(557, 1), new Item(554, 1), new Item(555, 1), new Item(563, 1)}, 100, 134,"equipment"),
	GENERAL_STALL(new Integer[]{4876}, new Integer[] { 4797 }, 5, new Item[]{new Item(1931, 1), new Item(2347, 1), new Item(590, 1)}, 16, 12,"goods"),
	FOOD_STALL(new Integer[]{4875}, new Integer[] { 4797 }, 5, new Item[]{new Item(1963, 1)}, 16, 12,"food");
	//CRAFTING_STALL (Ape Atoll) shares same drops/exp as regular crafting stall

	//Quest Stalls Rocking Out
	//CUSTOMS_EVIDENCE_FILES(new Integer[]{FIND OBJ ID}, FIND OBJ EMPTY ID, 63, new Item[]{new Item(1333, 1), new Item(1617, 1), new Item(1619, 1), new Item(1623, 1), new Item(385, 1), new Item(2359, 1), new Item(2357, 1), new Item(2351, 1), new Item(7114, 1), new Item(7134, 1), new Item(1025, 1), new Item(1281, 1), new Item(1325, 1), new Item(1323, 1), new Item(1321, 1), new Item(995, 300)}, 75, 100);

	public static HashMap<Integer,Stall> idMap = new HashMap<>();
	static{
		Arrays.stream(Stall.values()).forEach(entry -> entry.full_ids.stream().forEach(id -> idMap.putIfAbsent(id, entry)));
	}

	List<Integer> full_ids;
	List<Integer> empty_ids;
	int level, delay;
	Item[] rewards;
	double experience;
	String msgItem;

	Stall(Integer[] full_ids, Integer[] empty_ids, int level, Item[] rewards, double experience, int delay, String msgItem) {
		this.full_ids = new ArrayList<Integer>(Arrays.asList(full_ids));
		this.empty_ids = new ArrayList<Integer>(Arrays.asList(empty_ids));
		this.level = level;
		this.delay = delay;
		this.rewards = rewards;
		this.experience = experience;
		this.msgItem = msgItem;
	}



	public List<Integer> getFullIDs() {
		return full_ids;
	}

	public int getLevel() {
		return level;
	}

	public Item[] getRewards() {
		return rewards;
	}

	public double getExperience() {
		return experience;
	}

	public int getEmpty(int id) {
		int fullIndex = full_ids.indexOf(id);
		return this.empty_ids.get(fullIndex);
	}

	public int getDelay() {
		return delay;
	}

	public Item getRandomLoot() {
		return rewards[RandomFunction.random(rewards.length)];
	}

	public static Stall forObject(final GameObject object) {
		return idMap.get(object.getId());
	}
}
