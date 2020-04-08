package org.crandor.game.content.skill.member.fletching.items.crossbow;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.fletching.Fletching;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents the skill pulse of attaching limbs.
 * @author Ceikry
 */
public class LimbPulse extends SkillPulse<Item> {

	/**
	 * Represents the limbs.
	 */
	private final Fletching.Limb limb;

	/**
	 * Represents the amount.
	 */
	private int amount;

	/**
	 * Constructs a new {@code StringcrossbowPlugin.java} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 */
	public LimbPulse(Player player, Item node, final Fletching.Limb limb, int amount) {
		super(player, node);
		this.limb = limb;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.FLETCHING) < limb.level) {
			player.getDialogueInterpreter().sendDialogue("You need a fletching level of " + limb.level + " to attach these limbs.");
			return false;
		}
		if (!player.getInventory().containsItem(new Item(limb.limb))) {
			player.getDialogueInterpreter().sendDialogue("That's not the correct limb to attach.");
			return false;
		}
		if (!player.getInventory().containsItem(new Item(limb.stock))) {
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		player.animate(limb.animation);
	}

	@Override
	public boolean reward() {
		if (getDelay() == 1) {
			super.setDelay(6);
			return false;
		}
		if (player.getInventory().remove(new Item(limb.stock), new Item(limb.limb))) {
			player.getInventory().add(new Item(limb.product));
			player.getSkills().addExperience(Skills.FLETCHING, limb.experience, true);
			player.getPacketDispatch().sendMessage("You attach the metal limbs to the stock.");
		}
		if (!player.getInventory().containsItem(new Item(limb.limb))) {
			return true;
		}
		amount--;
		return amount == 0;
	}

	@Override
	public void message(int type) {
	}

}
