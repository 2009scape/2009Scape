package plugin.skill.farming.patch;

import plugin.skill.Skills;
import plugin.skill.farming.FarmingNode;
import plugin.skill.farming.wrapper.PatchCycle;
import core.game.node.item.Item;

/**
 * Represents a tree node.
 * @author 'Vexia
 */
public class TreeNode extends FarmingNode {

	/**
	 * Constructs a new {@code TreebNode} {@code Object}.
	 * @param seed the sed.
	 * @param product the product.
	 * @param base the base.
	 * @param growthCycles the cycles.
	 * @param minutes the minutes.
	 * @param level the level.
	 * @param experiences the experiences.
	 */
	public TreeNode(Item seed, Item product, int base, int growthCycles, int minutes, int level, double[] experiences, final Item... protection) {
		super(seed, product, base, growthCycles, minutes, level, experiences, protection);
	}

	@Override
	public void checkHealth(final PatchCycle cycle) {
		cycle.addConfigValue(getCheckHealthBase(cycle));
		// don't need to wory about falador shield because it doesn't apply to any tree nodes
		cycle.getPlayer().getSkills().addExperience(Skills.FARMING, getExperiences()[1], true);
		cycle.getPlayer().getPacketDispatch().sendMessage("You examine the " + cycle.getWrapper().getName() + " for signs of disease and find that it's in perfect health.");
	}

	@Override
	public int getNextStage(PatchCycle cycle) {
		if (isStump(cycle)) {
			return cycle.getState() - 1;
		}
		return super.getNextStage(cycle);
	}

	@Override
	public boolean exceedsGrowth(PatchCycle cycle) {
		if (isStump(cycle)) {
			return false;
		}
		return super.exceedsGrowth(cycle);
	}

	@Override
	public boolean isFullGrown(PatchCycle cycle) {
		if (cycle.getState() == (cycle.getNode().getBase() + (cycle.getNode().getGrowthCycles() + 1))) {
			return true;
		}
		return super.isFullGrown(cycle);
	}

	@Override
	public boolean isGrowing(PatchCycle cycle) {
		if (isStump(cycle)) {
			return true;
		}
		return super.isGrowing(cycle);
	}

	@Override
	public boolean canDisease(PatchCycle cycle) {
		if (isStump(cycle)) {
			return false;
		}
		return super.canDisease(cycle);
	}

	@Override
	public boolean isStump(PatchCycle cycle) {
		return cycle.getState() == getBase() + (getGrowthCycles() + 2);
	}

	@Override
	public int getDiseaseBase() {
		return 73 - 9;
	}

	@Override
	public int getDeathBase() {
		return 137 - 8;
	}

	/**
	 * Gets the check health base.
	 * @param cycle the cycle.
	 * @return the base.
	 */
	public int getCheckHealthBase(PatchCycle cycle) {
		return cycle.getState() + 1;
	}
}
