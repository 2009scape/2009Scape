package org.crandor.game.content.skill.member.fletching.items.arrow;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

import java.beans.FeatureDescriptor;

/**
 * Represents the arrow pulse for creating unfinished arrows.
 * @author 'Vexia
 */
public final class HeadlessArrowPulse extends SkillPulse<Item> {

	/**
	 * Represents the headless arrow item.
	 */
	private final Item HEADLESS_ARROW = new Item(53);

	/**
	 * Represents the arrow shaft item.
	 */
	private final Item ARROW_SHAFT = new Item(52);

	/**
	 * Represents the feather items.
	 */
	private static final Item[] FEATHER = new Item[] { new Item(314), new Item(10087), new Item(10088), new Item(10089), new Item(10090), new Item(10091) };

	/**
	 * The feather being used.
	 */
	private Item feather;

	/**
	 * Represents the amount to make.
	 */
	private int sets;

	/**
	 * Represents if we should use sets, meaning we have 15 & 15 arrow shafts and feathers.
	 */
	private boolean useSets = false;

	/**
	 * Constructs a new {@code ArrowPulse.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 */
	public HeadlessArrowPulse(Player player, Item node, int sets) {
		super(player, node);
		this.sets = sets;
	}

	@Override
	public boolean checkRequirements() {
		if (!player.getInventory().containsItem(ARROW_SHAFT)) {
			player.getDialogueInterpreter().sendDialogue("You dont have any arrow shafts.");
			return false;
		}
		feather = getFeather();
		if (feather == null) {
			player.getDialogueInterpreter().sendDialogue("You don't have any feathers.");
			return false;
		}
		if (player.getInventory().contains(ARROW_SHAFT.getId(), 15) && player.getInventory().contains(feather.getId(), 15)) {
			useSets = true;
		} else {
			useSets = false;
		}
		return true;
	}

	@Override
	public void animate() {
	}

	@Override
	public boolean reward() {
		if (getDelay() == 1) {
			super.setDelay(3);
			return false;
		}
		if (useSets) {
			HEADLESS_ARROW.setAmount(15);
			feather.setAmount(15);
			ARROW_SHAFT.setAmount(15);
			player.getPacketDispatch().sendMessage("You attach feathers to 15 arrow shafts.");
		} else {
			HEADLESS_ARROW.setAmount(1);
			feather.setAmount(1);
			ARROW_SHAFT.setAmount(1);
			player.getPacketDispatch().sendMessage("You attach a feather to an arrow shaft.");
		}
		if (player.getInventory().remove(ARROW_SHAFT, feather)) {
			player.getSkills().addExperience(Skills.FLETCHING, useSets ? 15 : 1, true);
			player.getInventory().add(HEADLESS_ARROW);
		}
		HEADLESS_ARROW.setAmount(1);
		feather.setAmount(1);
		ARROW_SHAFT.setAmount(1);
		if (!player.getInventory().containsItem(ARROW_SHAFT)) {
			return true;
		}
		if (!player.getInventory().containsItem(feather)) {
			return true;
		}
		sets--;
		return sets == 0;
	}

	@Override
	public void message(int type) {
	}

	/**
	 * Gets the feather item.
	 * @return the item.
	 */
	private Item getFeather() {
		int length = FEATHER.length;
		for (int i = 0; i < length; i++) {
			Item f = FEATHER[i];
			if (player.getInventory().containsItem(f)) {
				return f;
			}
		}
		return null;
	}
}
