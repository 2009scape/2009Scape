package plugin.skill.farming.wrapper.handler;

import core.game.system.SystemLogger;
import plugin.skill.farming.wrapper.PatchCycle;

import java.util.concurrent.TimeUnit;

/**
 * Represents the handler of the growth effect of a patch.
 * @author Vexia
 */
public final class GrowthHandler {

	/**
	 * Represents the patch cycle.
	 */
	private final PatchCycle cycle;

	/**
	 * Constructs a new {@code GrowthHandler} {@code Object}.
	 * @param cycle the cycle.
	 */
	public GrowthHandler(PatchCycle cycle) {
		this.cycle = cycle;
	}

	/**
	 * Method used to handle the growth of a patch.
	 */
	public void handle() {
		final int stages = cycle.getPassedStages();
		setGrowthUpdate();
		for (int i = 1; i <= stages; i++) {
			if (isWeedy()) {
				growWeeds(1);
			} else {
				if (!grow()) {
					return;
				}
			}
		}
	}

	/**
	 * Method used to cycle weeds on a patch.
	 * @param stage the next stage.
	 */
	public void growWeeds(int stage) {
		final int nextStage = cycle.getState() - stage;
		cycle.addConfigValue(cycle.getWrapper().hasScarecrow() ? nextStage < 33 ? 33 : nextStage : nextStage < 0 ? 0 : nextStage);
		if (cycle.getState() == 0 || cycle.getState() == 33) {
			cycle.setGrowthTime(0L);
		}
	}

	/**
	 * Method used to grow the patch to the next stage.
	 * @return {@code True} if we should stop.
	 */
	public boolean grow() {
		if (cycle.getDiseaseHandler().handle()) {
			return false;
		}
		cycle.getWaterHandler().removeWater();
		if (!cycle.getNode().exceedsGrowth(cycle)) {
			cycle.getNode().grow(cycle);
		}
		return true;
	}

	/**
	 * Checks if the patch is fully grown.
	 * @return {@code True} if so.
	 */
	public boolean isFullGrown() {
		if (cycle.getNode() == null) {
			return false;
		}
		return cycle.getNode().isFullGrown(cycle);
	}

	/**
	 * Method used to set add the next growth time.
	 */
	public void setGrowthUpdate() {
		cycle.setGrowthTime(System.currentTimeMillis() + getGrowthUpdate());
	}

	/**
	 * Gets the amount of growth update to add.
	 * @return the amt of time.
	 */
	public long getGrowthUpdate() {
		final boolean weedy = isWeedy();
		return (weedy ? TimeUnit.MINUTES.toMillis(5) : TimeUnit.MINUTES.toMillis(cycle.getNode().getMinutes()));
	}

	/**
	 * Checks if its a weedy growth.
	 * @return {@code True} if do.
	 */
	public boolean isWeedy() {
		return cycle.getWrapper().isWeedy() || cycle.getWrapper().isEmpty();
	}

	/**
	 * Checks if the patch can grow.
	 * @return the {@code True} if so.
	 */
	public boolean canGrow() {
		return cycle.getGrowthTime() < System.currentTimeMillis() && cycle.getGrowthTime() != 0L && (isWeedy() || isGrowing() || cycle.getDiseaseHandler().isDiseased() || cycle.getWaterHandler().isWatered());
	}

	/**
	 * Checks if the patch is growing.
	 * @return {@code True} if so.
	 */
	public boolean isGrowing() {
		if (cycle.getNode() == null) {
			return false;
		}
		return cycle.getNode().isGrowing(cycle);
	}

}
