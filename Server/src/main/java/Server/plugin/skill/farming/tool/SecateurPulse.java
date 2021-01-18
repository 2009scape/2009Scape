package plugin.skill.farming.tool;

import plugin.skill.farming.patch.PickingNode;
import plugin.skill.farming.patch.TreeNode;
import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

/**
 * Represents the pulse used when using secateurs.
 * @author 'Vexia
 */
public final class SecateurPulse extends ToolAction {

	/**
	 * Represents the watering animation to use.
	 */
	private static final Animation ANIMATION = new Animation(2275);

	/**
	 * Constructs a new {@code SecateurPulse} {@code Object}.
	 */
	public SecateurPulse() {
		super(null, null, null);
	}

	/**
	 * Constructs a new {@code SecateurPulse} {@code Object}.
	 * @param player the player.
	 * @param wrapper the wrapper.
     */
	public SecateurPulse(final Player player, final PatchWrapper wrapper, final Item item) {
		super(player, wrapper, item);
	}

	@Override
	public ToolAction newInstance(Player player, PatchWrapper wrapper, Item item) {
		return new SecateurPulse(player, wrapper, item);
	}

	@Override
	public boolean pulse() {
		if (ticks == 0) {
			player.animate(ANIMATION);
		}
		if (++ticks % 4 != 0) {
			return false;
		}
		if (!isReward(3)) {
			return false;
		}
		wrapper.getCycle().getDiseaseHandler().cure(player, false);
		return true;
	}

	@Override
	public void stop() {
		super.stop();
		player.getAnimator().reset();
	}

	@Override
	public boolean canInteract(String command) {
		if (wrapper.getNode() != null && !(wrapper.getNode() instanceof PickingNode || wrapper.getNode() instanceof TreeNode) || wrapper.isWeedy() || wrapper.isEmpty() || !wrapper.getCycle().getDiseaseHandler().isDiseased()) {
			player.getPacketDispatch().sendMessage("Nothing interesting happens.");
			return false;
		}
		return true;
	}

}
