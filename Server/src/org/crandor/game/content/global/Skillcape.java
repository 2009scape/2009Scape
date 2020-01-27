package org.crandor.game.content.global;

import org.crandor.game.container.Container;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents a utility class for purchasing skillcapes.
 * @author Vexia
 */
public final class Skillcape {

	/**
	 * Represents the amount needed to purchase a cape.
	 */
	private static final Item COINS = new Item(995, 99000);

	/**
	 * Represents the skillcapes to purchase.
	 */
	public static final int[] SKILLCAPES = { 9747, 9753, 9750, 9768, 9756, 9759, 9762, 9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792, 9774, 9771, 9777, 9786, 9810, 9765, 9948, 9789, 12169 };

	/**
	 * Method used to purchase a cape of accomplisment.
	 * @param player the player.
	 * @param skill the skill.
	 * @return {@code True} if purchased.
	 */
	public static boolean purchase(final Player player, final int skill) {
		if (!isMaster(player, skill)) {
			return false;
		}
		if (player.getInventory().freeSlots() < 2) {
			player.getDialogueInterpreter().sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't seem to have inventory space.");
			return false;
		}
		if (!player.getInventory().containsItem(COINS)) {
			player.getDialogueInterpreter().sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't seem to have enough coins with", "me at this time.");
			return false;
		}
		if (player.getInventory().remove(COINS)) {
			return player.getInventory().add(getItems(player, skill));
		} else {
			return false;
		}
	}

	/**
	 * Method used to trim the players skillcapes.
	 * @param player the player.
	 */
	public static void trim(final Player player) {
		final Container[] containers = new Container[] { player.getInventory(), player.getEquipment(), player.getBank() };
		int skill = -1;
		for (Container container : containers) {
			for (Item item : container.toArray()) {
				if (item == null || item.getId() < 9700) {
					continue;
				}
				skill = getCapeIndex(item);
				if (skill != -1) {
					container.replace(new Item(getTrimmed(skill).getId(), item.getAmount()), item.getSlot());
					skill = -1;
				}
			}
		}
	}

	/**
	 * Checks if the player has the appropriate level.
	 * @param player the player.
	 * @param skill the skill.
	 * @return {@code True} if so.
	 */
	public static boolean isMaster(final Player player, final int skill) {
		return player.getSkills().getStaticLevel(skill) == 99;
	}

	/**
	 * Gets the items to purchase.
	 * @param player the player.
	 * @param skill the skill.
	 * @return {@code Items} to buy.
	 */
	public static Item[] getItems(final Player player, final int skill) {
		return new Item[] { new Item(SKILLCAPES[skill] + (player.getSkills().getMasteredSkills() > 1 ? 1 : 0)), new Item(SKILLCAPES[skill] + 2) };
	}

	/**
	 * Gets the trimmed item.
	 * @param skill the skill.
	 * @return the trimmed cape.
	 */
	public static Item getTrimmed(final int skill) {
		return new Item(SKILLCAPES[skill] + 1);
	}

	/**
	 * Gets the cape index by the item.
	 * @param item the item.
	 * @return the skill index, if not (-1).
	 */
	public static int getCapeIndex(final Item item) {
		for (int i = 0; i < SKILLCAPES.length; i++) {
			if (SKILLCAPES[i] == item.getId()) {
				return i;
			}
		}
		return -1;
	}
}
