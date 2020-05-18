package org.crandor.game.content.skill.free.crafting.armour;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.crafting.armour.LeatherCrafting.DragonHide;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.StringUtils;

/**
 * Represents a pulse used to craft dragon armour.
 * @author 'Vexia
 */
public final class DragonCraftPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = Animation.create(1249);

	/**
	 * Represents the dragon hide type.
	 */
	private DragonHide hide;

	/**
	 * Represents the amount to make.
	 */
	private int amount;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code DragonCraftPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param soft the soft.
	 * @param amount the amount.
	 */
	public DragonCraftPulse(Player player, Item node, DragonHide hide, int amount) {
		super(player, node);
		this.hide = hide;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.CRAFTING) < hide.getLevel()) {
			player.getDialogueInterpreter().sendDialogue("You need a crafting level of " + hide.getLevel() + " to make " + ItemDefinition.forId(hide.getProduct()).getName() + ".");
			amount = 0;
			return false;
		}
		if (!player.getInventory().contains(LeatherCrafting.NEEDLE, 1)) {
			player.getDialogueInterpreter().sendDialogue("You need a needle to make this.");
			amount = 0;
			return false;
		}
		if (!player.getInventory().containsItem(LeatherCrafting.THREAD)) {
			player.getDialogueInterpreter().sendDialogue("You need thread to make this.");
			amount = 0;
			return false;
		}
		if (!player.getInventory().contains(hide.getLeather(), hide.getAmount())) {
			player.getDialogueInterpreter().sendDialogue("You need " + hide.getAmount() + " " + ItemDefinition.forId(hide.getLeather()).getName().toLowerCase() + " to make this.");
			amount = 0;
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
		if (player.getInventory().remove(new Item(hide.getLeather(), hide.getAmount()))) {
			if (hide.name().contains("VAMBS")) {
				player.getPacketDispatch().sendMessage("You make a pair of " + ItemDefinition.forId(hide.getProduct()).getName().toLowerCase() + "'s.");
			} else {
				player.getPacketDispatch().sendMessage("You make " + (StringUtils.isPlusN(ItemDefinition.forId(hide.getProduct()).getName().toLowerCase()) ? "an" : "a") + " " + ItemDefinition.forId(hide.getProduct()).getName().toLowerCase() + ".");
			}
			Item item = new Item(hide.getProduct());
			player.getInventory().add(item);
			player.getSkills().addExperience(Skills.CRAFTING, hide.getExperience(), true);
			LeatherCrafting.decayThread(player);
			if (LeatherCrafting.isLastThread(player)) {
				LeatherCrafting.removeThread(player);
			}
			amount--;
		}
		return amount < 1;
	}

	@Override
	public void message(int type) {

	}
}
