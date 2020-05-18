package org.crandor.game.content.skill.free.crafting.gem;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;

/**
 * Represents the pulse used to cut a gem.
 * @author 'Vexia
 */
public final class GemCutPulse extends SkillPulse<Item> {

	/**
	 * Represents the chisel item.
	 */
	private static final Item CHISEL = new Item(1755);

	/**
	 * Represents the gem to cut.
	 */
	private final Gems gem;

	/**
	 * Represents the amount to make.
	 */
	private int amount;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code GemCuttingPulse} {@Code Object}
	 * @param player the player.
	 * @param item the item cut.
	 */
	public GemCutPulse(Player player, Item item, int ammount, final Gems gem) {
		super(player, item);
		this.amount = ammount;
		this.gem = gem;
		this.resetAnimation = false;
	}

	@Override
	public boolean checkRequirements() {
		if (!player.getInventory().containsItem(CHISEL)) {
			return false;
		}
		if (!player.getInventory().containsItem(gem.getUncut())) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.CRAFTING) < gem.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a crafting level of " + gem.getLevel() + " to craft this gem.");
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		if (ticks % 5 == 0 || ticks < 1) {
			player.getAudioManager().send(2586);
			player.animate(gem.getAnimation());
		}
	}

	@Override
	public boolean reward() {
		if (++ticks % 1 != 0) {
			return false;
		}
		if (player.getInventory().remove(gem.getUncut())) {
			final Item item = gem.getGem();
		    player.getInventory().add(item);
			player.getSkills().addExperience(Skills.CRAFTING, gem.getExp(), true);
		}
		amount--;
		return amount < 1;
	}
}
