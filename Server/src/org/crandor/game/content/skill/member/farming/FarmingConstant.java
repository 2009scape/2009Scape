package org.crandor.game.content.skill.member.farming;

import org.crandor.game.content.skill.member.herblore.Herbs;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents farming constants.
 * @author Vexia
 */
public final class FarmingConstant {

	/**
	 * Represents the suitable items for super compost.
	 */
	public static final int[] SUPERCOMPOST_IDS = new int[] { 2114, 5980, 5982, 6043, 6045, 6047, 6049, 6051, 5978, 247, 239 };

	/**
	 * Represents the marigold item.
	 */
	public static final Item MARIGOLD = new Item(6010);

	/**
	 * Represents the potato item.
	 */
	public static final Item POTATO = new Item(1942);

	/**
	 * Represents the tomato item.
	 */
	public static final Item TOMATO = new Item(1982);

	/**
	 * Represents the rotten tomato item.
	 */
	public static final Item ROTTEN_TOMATO = new Item(2518);

	/**
	 * Represents the compost item.
	 */
	public static final Item COMPOST = new Item(6032);

	/**
	 * Represents the super compost item.
	 */
	public static final Item SUPERCOMPOST = new Item(6034);

	/**
	 * Represents the bucket item.
	 */
	public static final Item BUCKET = new Item(1925);

	/**
	 * Represents the weed item.
	 */
	public static final Item WEEDS = new Item(6055);

	/**
	 * Represents the seed dibber item.
	 */
	public static final Item SEED_DIBBER = new Item(5343);

	/**
	 * Represents the spade item.
	 */
	public static final Item SPADE = new Item(952);

	/**
	 * Represents the watering can item.
	 */
	public static final Item WATERING_CAN = new Item(5331);

	/**
	 * Repreents the rosemary item.
	 */
	public static final Item ROSEMARY = new Item(6014);

	/**
	 * Represents the scarecrow item.
	 */
	public static final Item SCARECROW = new Item(6059);

	/**
	 * Represents the nasturtiums item.
	 */
	public static final Item NASTURTIUMS = new Item(6012);

	/**
	 * Represents the vial item.
	 */
	public static final Item VIAL = new Item(229);

	/**
	 * Represents the gardening trowel item.
	 */
	public static final Item GARDENING_TROWEL = new Item(5325);

	/**
	 * Represents the plant pot item.
	 */
	public static final Item PLANT_POT = new Item(5350);

	/**
	 * Gets the farming products used for composting.
	 * @return the products.
	 */
	public static int[] getFarmingProducts() {
		List<Integer> ids = new ArrayList<>();
		for (FarmingPatch patch : FarmingPatch.values()) {
			for (FarmingNode node : patch.getNodes()) {
				ids.add(node.getProduct().getId());
			}
		}
		for (int i : SUPERCOMPOST_IDS) {
			ids.add(i);
		}
		for (Herbs herb : Herbs.values()) {
			ids.add(herb.getHerb().getId());
			ids.add(herb.getProduct().getId());
		}
		ids.add(WEEDS.getId());
		ids.add(TOMATO.getId());
		int[] products = new int[ids.size()];
		for (int i = 0; i < products.length; i++) {
			products[i] = ids.get(i);
		}
		return products;
	}

}
