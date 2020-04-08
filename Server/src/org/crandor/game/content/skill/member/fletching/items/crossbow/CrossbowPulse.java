/*
package org.crandor.game.content.skill.member.fletching.items.crossbow;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

*/
/**
 * Represents the skill pulse of stringing.
 * @author 'Vexia
 *//*

public class CrossbowPulse extends SkillPulse<Item> {

	*/
/**
	 * Represents the bow string item.
	 *//*

	private final Item BOW_STRING = new Item(9438);

	*/
/**
	 * Represents the string bow.
	 *//*

	private final StringCross bow;

	*/
/**
	 * Represents the amount.
	 *//*

	private int amount;

	*/
/**
	 * Constructs a new {@code StringcrossbowPlugin.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 *//*

	public CrossbowPulse(Player player, Item node, final StringCross bow, int amount) {
		super(player, node);
		this.bow = bow;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.FLETCHING) < bow.getLevel()) {
			player.getDialogueInterpreter().sendDialogue("You need a fletching level of " + bow.getLevel() + " to string this crossbow.");
			return false;
		}
		if (!player.getInventory().containsItem(BOW_STRING)) {
			player.getDialogueInterpreter().sendDialogue("You seem to have run out of bow strings.");
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		player.animate(bow.getAnimation());
	}

	@Override
	public boolean reward() {
		if (getDelay() == 1) {
			super.setDelay(5);
			return false;
		}
		if (player.getInventory().remove(bow.getItem(), BOW_STRING)) {
			player.getInventory().add(bow.getProduct());
			player.getSkills().addExperience(Skills.FLETCHING, bow.getExperience(), true);
			player.getPacketDispatch().sendMessage("You add a string to the crossbow.");
		}
		if (!player.getInventory().containsItem(BOW_STRING) || !player.getInventory().containsItem(bow.getItem())) {
			return true;
		}
		amount--;
		return amount == 0;
	}

	@Override
	public void message(int type) {
		switch (type) {
		case 0:
			break;
		case 1:
			break;
		}
	}

}*/
