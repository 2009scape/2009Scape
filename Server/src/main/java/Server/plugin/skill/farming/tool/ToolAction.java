package plugin.skill.farming.tool;

import plugin.skill.farming.wrapper.PatchWrapper;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;

/**
 * Represents the pulse used when a tool is interacting.
 * @author 'Vexia
 * @version 1.0
 */
public abstract class ToolAction extends Pulse {

	/**
	 * Represents the player.
	 */
	protected Player player;

	/**
	 * Represents the patch wrapper.
	 */
	protected PatchWrapper wrapper;

	/**
	 * Represents the tool used.
	 */
	protected Item tool;

	/**
	 * Represents the ticks it takes to get a weed.
	 */
	protected int ticks;

	/**
	 * Constructs a new {@code ToolPulse} {@code Object}.
	 */
	public ToolAction() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ToolPulse} {@code Object}.
	 * @param player the player.
	 * @param wrapper the wrapper.
	 */
	public ToolAction(Player player, PatchWrapper wrapper, final Item tool) {
		super(1, player);
		this.player = player;
		this.wrapper = wrapper;
		this.tool = tool;
	}

	/**
	 * Creates a new instance of the pulse.
	 * @param player the player.
	 * @param wrapper the wrapper.
	 * @return the instance.
	 */
	public abstract ToolAction newInstance(final Player player, final PatchWrapper wrapper, final Item tool);

	/**
	 * Checks if the player can interact with the patch.
	 * @param command the forced command.
	 * @return {@code True} if so.
	 */
	public abstract boolean canInteract(String command);

	/**
	 * Sets the delay relapse.
	 * @param relapse the relapse.
	 */
	public void setDelayRelapse(int relapse) {
		if (ticks == 0) {
			setDelay(relapse);
		}
	}

	/**
	 * Checks if the reward can happen.
	 * @return {@code True} if so.
	 */
	public boolean isReward(int tick) {
		return ++ticks % tick == 0;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the wrapper.
	 * @return The wrapper.
	 */
	public PatchWrapper getWrapper() {
		return wrapper;
	}

}
