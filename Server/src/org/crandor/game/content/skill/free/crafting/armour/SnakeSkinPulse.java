package org.crandor.game.content.skill.free.crafting.armour;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used to craft snake skin.
 * @author 'Vexia
 */
public final class SnakeSkinPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = Animation.create(1249);

	/**
	 * The snake skin.
	 */
	private final SnakeSkin skin;

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
	public SnakeSkinPulse(Player player, Item node, int amount, SnakeSkin skin) {
		super(player, node);
		this.amount = amount;
		this.skin = skin;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.CRAFTING) < skin.getLevel()) {
			player.getDialogueInterpreter().sendDialogue("You need a crafting level of " + skin.getLevel() + " to make this.");
			return false;
		}
		if (!player.getInventory().contains(LeatherCrafting.NEEDLE, 1)) {
			return false;
		}
		if (!player.getInventory().containsItem(LeatherCrafting.THREAD)) {
			player.getDialogueInterpreter().sendDialogue("You need thread to make this.");
			return false;
		}
		if (!player.getInventory().contains(6289, skin.getRequiredAmount())) {
			player.getDialogueInterpreter().sendDialogue("You need " + skin.getRequiredAmount() + " snakeskins in order to do this.");
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
		if (player.getInventory().remove(new Item(6289, skin.getRequiredAmount()))) {
			Item item = skin.getProduct();
		    player.getInventory().add(item);
			player.getSkills().addExperience(Skills.CRAFTING, skin.getExperience(), true);
			LeatherCrafting.decayThread(player);
			if (LeatherCrafting.isLastThread(player)) {
				LeatherCrafting.removeThread(player);
			}
		}
		amount--;
		return amount < 1;
	}

}
