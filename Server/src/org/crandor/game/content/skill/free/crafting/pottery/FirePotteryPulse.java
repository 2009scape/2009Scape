package org.crandor.game.content.skill.free.crafting.pottery;

import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used to fire pottery.
 * @author 'Vexia
 */
public final class FirePotteryPulse extends SkillPulse<Item> {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(899);

	/**
	 * Represents the pottery item.
	 */
	private final PotteryItem pottery;

	/**
	 * Represents the amount to make.
	 */
	private int amount;

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code FirePotteryPulse} {@code Object}.
	 * @param player the player.
	 * @param node the node.
	 * @param pottery the pottery.
	 * @param amount the amount.
	 */
	public FirePotteryPulse(Player player, Item node, final PotteryItem pottery, final int amount) {
		super(player, node);
		this.pottery = pottery;
		this.amount = amount;
	}

	@Override
	public boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.CRAFTING) < pottery.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a crafting level of " + pottery.getLevel() + " in order to do this.");
			return false;
		}
		if (!player.getInventory().containsItem(pottery.getUnfinished())) {
			player.getPacketDispatch().sendMessage("You need a " + pottery.name().toLowerCase() + "in order to do this.");
			return false;
		}
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
		if (player.getInventory().remove(pottery.getUnfinished())) {
			if (player.getLocation().getY() == 3408 && player.getAttribute("spun-bowl", false) && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 9)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 9, true);
			}
			final Item item = pottery.getProduct();
		    player.getInventory().add(item);
			Perks.addDouble(player, item);
			player.getSkills().addExperience(Skills.CRAFTING, pottery.getFireExp(), true);
			player.getPacketDispatch().sendMessage("You put the " + pottery.getUnfinished().getName().toLowerCase() + " in the oven.");
			player.getPacketDispatch().sendMessage("You remove a " + pottery.getProduct().getName().toLowerCase() + " from the oven.");
		}
		amount--;
		return amount < 1;
	}

}
