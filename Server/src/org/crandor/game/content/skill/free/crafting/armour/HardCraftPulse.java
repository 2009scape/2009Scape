package org.crandor.game.content.skill.free.crafting.armour;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used to craft hard leather.
 * @author 'Vexia
 */
public final class HardCraftPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = Animation.create(1249);

	/**
	 * Represents the amount to make.
	 */
	private int amount;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code HardCraftPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param soft the soft.
	 * @param amount the amount.
	 */
	public HardCraftPulse(Player player, Item node, int amount) {
		super(player, node);
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.CRAFTING) < 28) {
			player.getDialogueInterpreter().sendDialogue("You need a crafting level of " + 28 + " to make a hardleather body.");
			return false;
		}
		if (!player.getInventory().contains(LeatherCrafting.NEEDLE, 1)) {
			return false;
		}
		if (!player.getInventory().contains(LeatherCrafting.HARD_LEATHER, 1)) {
			return false;
		}
		if (!player.getInventory().containsItem(LeatherCrafting.THREAD)) {
			player.getDialogueInterpreter().sendDialogue("You need thread to make this.");
			return false;
		}
		player.getInterfaceManager().close();
		return true;
	}

	@Override
	public void animate() {
		if (ticks % 5 == 0) {
			player.animate(ANIMATION);
		}
	}

	@Override
	public boolean reward() {
		if (++ticks % 5 != 0) {
			return false;
		}
		if (player.getInventory().remove(new Item(LeatherCrafting.HARD_LEATHER))) {
			Item item = new Item(1131);
		    player.getInventory().add(item);
			player.getSkills().addExperience(Skills.CRAFTING, 35, true);
			LeatherCrafting.decayThread(player);
			if (LeatherCrafting.isLastThread(player)) {
				LeatherCrafting.removeThread(player);
			}
		}
		amount--;
		return amount < 1;
	}

}
