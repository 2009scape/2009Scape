package plugin.skill.farming.tool;

import plugin.skill.farming.FarmingConstant;
import plugin.skill.farming.FarmingPatch;
import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used when adding plant cure.
 * @author 'Vexia
 */
public final class PlantCurePulse extends ToolAction {

	/**
	 * Represents the watering animation to use.
	 */
	private static final Animation ANIMATION = new Animation(2288);

	/**
	 * Represents the ticks passed.
	 */
	private int ticks;

	/**
	 * Constructs a new {@code PlantCurePulse} {@code Object}.
	 */
	public PlantCurePulse() {
		super(null, null, null);
	}

	/**
	 * Constructs a new {@code PlantCurePulse} {@code Object}.
	 * @param player the player.
	 * @param wrapper the wrapper.
     */
	public PlantCurePulse(final Player player, final PatchWrapper wrapper, final Item item) {
		super(player, wrapper, item);
	}

	@Override
	public ToolAction newInstance(Player player, PatchWrapper wrapper, Item item) {
		return new PlantCurePulse(player, wrapper, item);
	}

	@Override
	public boolean pulse() {
		if (ticks == 0) {
			player.animate(ANIMATION);
		}
		if (!isReward(3)) {
			return false;
		}
		cure(player, wrapper, tool);
		return true;
	}

	/**
	 * Method used to cure a plant.
	 * @param player the player.
	 * @param wrapper the wrapper.
	 * @param item the item.
	 */
	public static void cure(final Player player, final PatchWrapper wrapper, final Item item) {
		if (item != null) {
			player.getInventory().replace(FarmingConstant.VIAL, item.getSlot());
		}
		wrapper.getCycle().getDiseaseHandler().cure(player, true);
	}

	@Override
	public void stop() {
		super.stop();
		player.getAnimator().reset();
	}

	@Override
	public boolean canInteract(String command) {
		if (wrapper.isWeedy() || wrapper.isEmpty()) {
			player.getPacketDispatch().sendMessage("This farming patch is empty.");
			return false;
		}
		if (wrapper.getPatch() == FarmingPatch.TREE || wrapper.getPatch() == FarmingPatch.FRUIT_TREE || wrapper.getPatch() == FarmingPatch.BUSHES) {
			player.getPacketDispatch().sendMessage("You can't cure this patch with plant cure.");
			return false;
		}
		if (!wrapper.getCycle().getDiseaseHandler().isDiseased()) {
			player.getPacketDispatch().sendMessage("This patch doesn't need curing.");
			return false;
		}
		return true;
	}

}
