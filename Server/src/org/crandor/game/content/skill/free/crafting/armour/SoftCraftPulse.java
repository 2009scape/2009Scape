package org.crandor.game.content.skill.free.crafting.armour;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.crafting.armour.LeatherCrafting.SoftLeather;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.StringUtils;

/**
 * Represents a pulse used to craft soft leather.
 * @author 'Vexia
 */
public final class SoftCraftPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = Animation.create(1249);

	/**
	 * Represents the leather to use.
	 */
	private SoftLeather soft;

	/**
	 * Represents the amount to make.
	 */
	private int amount;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code SoftCraftPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param leather the soft.
	 * @param amount the amount.
	 */
	public SoftCraftPulse(Player player, Item node, SoftLeather leather, int amount) {
		super(player, node);
		this.soft = leather;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.CRAFTING) < soft.getLevel()) {
			player.getDialogueInterpreter().sendDialogue("You need a crafting level of " + soft.getLevel() + " to make " + (StringUtils.isPlusN(soft.getProduct().getName()) ? "an" : "a" + " " + soft.getProduct().getName()).toLowerCase() + ".");
			return false;
		}
		if (!player.getInventory().contains(LeatherCrafting.NEEDLE, 1)) {
			return false;
		}
		if (!player.getInventory().contains(LeatherCrafting.LEATHER, 1)) {
			return false;
		}
		if (!player.getInventory().containsItem(LeatherCrafting.THREAD)) {
			player.getDialogueInterpreter().sendDialogue("You need thread to make this.");
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
		if (player.getInventory().remove(new Item(LeatherCrafting.LEATHER))) {
			if (soft == SoftLeather.COIF && (player.getViewport().getRegion().getId() == 12851 || player.getViewport().getRegion().getId() == 12850) && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(1, 4)) {
				player.getAchievementDiaryManager().updateTask(player, DiaryType.LUMBRIDGE, 1, 4, true);
			}
			if (soft == SoftLeather.GLOVES || soft == SoftLeather.BOOTS || soft == SoftLeather.VAMBRACES) {
				player.getPacketDispatch().sendMessage("You make a pair of " + soft.getProduct().getName().toLowerCase() + ".");
			} else {
				player.getPacketDispatch().sendMessage("You make " + (StringUtils.isPlusN(soft.getProduct().getName()) ? "an" : "a") + " " + soft.getProduct().getName().toLowerCase() + ".");
			}
			Item item = soft.getProduct();
		    player.getInventory().add(item);
			player.getSkills().addExperience(Skills.CRAFTING, soft.getExperience(), true);
			LeatherCrafting.decayThread(player);
			if (LeatherCrafting.isLastThread(player)) {
				LeatherCrafting.removeThread(player);
			}
		}
		amount--;
		return amount < 1;
	}

}
