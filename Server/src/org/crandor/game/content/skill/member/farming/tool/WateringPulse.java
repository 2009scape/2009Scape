package org.crandor.game.content.skill.member.farming.tool;

import org.crandor.game.content.skill.member.farming.FarmingConstant;
import org.crandor.game.content.skill.member.farming.FarmingPatch;
import org.crandor.game.content.skill.member.farming.wrapper.PatchWrapper;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;

/**
 * Represents the pulse used when watering.
 * @author 'Vexia
 */
public final class WateringPulse extends ToolAction {

	/**
	 * Represents the watering animation to use.
	 */
	private static final Animation ANIMATION = new Animation(2293);

	/**
	 * Constructs a new {@code WateringPulse} {@code Object}.
	 */
	public WateringPulse() {
		super(null, null, null);
	}

	/**
	 * Constructs a new {@code WateringPulse} {@code Object}.
	 * @param player the player.
	 * @param wrapper the wrapper.
	 * @param delay the delay.
	 */
	public WateringPulse(final Player player, final PatchWrapper wrapper, final Item item) {
		super(player, wrapper, item);
	}

	@Override
	public ToolAction newInstance(Player player, PatchWrapper wrapper, Item item) {
		return new WateringPulse(player, wrapper, item);
	}

	@Override
	public boolean pulse() {
		if (ticks == 0) {
			player.animate(ANIMATION);
			player.graphics(Graphics.create(410));
		}
		if (!isReward(4)) {
			return false;
		}
		if (wrapper.getCycle().getGrowthHandler().isGrowing()) {
			wrapper.getCycle().getWaterHandler().setWatered();
			if (!player.hasPerk(Perks.GREEN_THUMB)) {
				player.getInventory().replace(getNextCan(), tool.getSlot());
			}
		}
		return true;
	}

	/**
	 * Gets the next can item.
	 * @return the next can.
	 */
	private Item getNextCan() {
		Item can = null;
		int length = PatchTool.WATERING_CAN.getTools().length;
		Item[] tools = PatchTool.WATERING_CAN.getTools();
		for (int i = 0; i < length; i++) {
			if (tools[i].getId() == tool.getId()) {
				return PatchTool.WATERING_CAN.getTools()[(i + 1)];
			}
		}
		return can;
	}

	@Override
	public void stop() {
		super.stop();
		player.getAnimator().reset();
	}

	@Override
	public boolean canInteract(String command) {
		if (wrapper.getPatch() != FarmingPatch.ALLOTMENT && wrapper.getPatch() != FarmingPatch.FLOWER && wrapper.getPatch() != FarmingPatch.HOP) {
			player.getPacketDispatch().sendMessage("This patch doesn't need watering.");
			return false;
		}
		if (wrapper.getCycle().getDeathHandler().isDead() || wrapper.getCycle().getDiseaseHandler().isDiseased()) {
			player.getPacketDispatch().sendMessage("Water isn't going to cure that!");
			return false;
		}
		if (!wrapper.getCycle().getGrowthHandler().isGrowing()) {
			player.getPacketDispatch().sendMessage("This patch doesn't need watering.");
			return false;
		}
		if (tool.getId() == FarmingConstant.WATERING_CAN.getId()) {
			player.getPacketDispatch().sendMessage("This watering can contains no water.");
			return false;
		}
		return true;
	}

}
