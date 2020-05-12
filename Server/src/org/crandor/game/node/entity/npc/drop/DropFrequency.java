package org.crandor.game.node.entity.npc.drop;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents the different types of drop frequency.
 * @author Emperor
 */
public enum DropFrequency {

	/**
	 * This gets dropped all the time.
	 */
	ALWAYS,

	/**
	 * This gets commonly dropped.
	 */
	COMMON,

	/**
	 * This drop is uncommon.
	 */
	UNCOMMON,

	/**
	 * This gets rarely dropped.
	 */
	RARE,

	/**
	 * This gets very rarely dropped.
	 */
	VERY_RARE;

	static int[] RATES = {1, 5, 15, 30, 60};
	final static HashMap<DropFrequency,Integer> rateMap = new HashMap<>();
	static{
		Arrays.stream(DropFrequency.values()).forEach(freq -> rateMap.putIfAbsent(freq,RATES[freq.ordinal()]));
	}

	public static int rate(DropFrequency frequency){
		return rateMap.get(frequency);
	}

}