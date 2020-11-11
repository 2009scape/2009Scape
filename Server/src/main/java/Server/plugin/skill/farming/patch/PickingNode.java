package plugin.skill.farming.patch;

import core.game.container.impl.EquipmentContainer;
import core.game.world.map.Location;
import plugin.skill.Skills;
import plugin.skill.farming.FarmingNode;
import plugin.skill.farming.wrapper.PatchCycle;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.tools.StringUtils;

/**
 * Represents a pickable farming node.
 * @author 'Vexia
 */
public class PickingNode extends FarmingNode {

	/**
	 * Represents the maximum amount to pick.
	 */
	protected final int maxPick;

	/**
	 * Constructs a new {@code BushNode} {@code Object}.
	 * @param seed the sed.
	 * @param product the product.
	 * @param base the base.
	 * @param growthCycles the cycles.
	 * @param minutes the minutes.
	 * @param level the level.
	 * @param experiences the experiences.
	 */
	public PickingNode(Item seed, Item product, int base, int growthCycles, int minutes, int level, double[] experiences, final int maxPick, final Item... protection) {
		super(seed, product, base, growthCycles, minutes, level, experiences, protection);
		this.maxPick = maxPick;
	}

	@Override
	public void grow(final PatchCycle cycle) {
		cycle.addConfigValue(getNextStage(cycle));
		if (getProductAmount(cycle.getState()) == maxPick) {
			cycle.setGrowthTime(0L);
		}
	}

	@Override
	public int getNextStage(final PatchCycle cycle) {
		if (isLastStage(cycle.getState())) {
			return getCheckHealthBase();
		} else if (cycle.getState() == getPickedBase(0)) {
			return getPickedBase(getProductAmount(cycle.getState()) + 1);
		}
		return super.getNextStage(cycle);
	}

	boolean hasFaladorShield(Player player) {
		Item shield = player.getEquipment().get(EquipmentContainer.SLOT_SHIELD);
		return shield != null && (shield.getId() == 14577 || shield.getId() == 14580);
	}

	@Override
	public void checkHealth(final PatchCycle cycle) {
		final Player player = cycle.getPlayer();
		cycle.addConfigValue(getPickedBase(maxPick));
		double xp = getExperiences()[2];
		// Check for Falador shield bonus
		if (hasFaladorShield(player) && player.getLocation().withinDistance(PatchProtection.FALADOR.getFlowerLocation(), 20)) {
			xp = xp * 1.1;
		}
		player.getSkills().addExperience(Skills.FARMING, xp, true);
		player.getPacketDispatch().sendMessage("You examine the " + cycle.getWrapper().getName() + " for signs of disease and find that it's in perfect health.");
	}

	@Override
	public boolean pick(final PatchCycle cycle) {
		final Player player = cycle.getPlayer();
		cycle.getGrowthHandler().setGrowthUpdate();
		cycle.addConfigValue(getPickedBase(getProductAmount(cycle.getState()) - 1));
		player.getInventory().add(getProduct());
		double xp = getExperiences()[1];
		// Check for falador shield bonus
		if (hasFaladorShield(player) && player.getLocation().withinDistance(PatchProtection.FALADOR.getFlowerLocation(), 20)) {
			xp = xp * 1.1;
		}
		player.getSkills().addExperience(Skills.FARMING, xp, true);
		if (cycle.getWrapper().getName().contains("tree")) {
			if (this instanceof FruitTreeNode
					&& player.getLocation().withinDistance(new Location(2764,3212,0), 10)) { // check player is near brimhaven fruit tree patch
				player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 12);
			}
			player.getPacketDispatch().sendMessage("You pick " + (StringUtils.isPlusN(getProduct().getName().toLowerCase()) ? "an" : "a") + " " + getProduct().getName().toLowerCase() + ".");
		} else {
			player.getPacketDispatch().sendMessage("You pick some " + getProduct().getName().toLowerCase() + ".");
		}
		return player.getInventory().freeSlots() == 0 || !hasProducts(cycle.getState());
	}

	@Override
	public boolean exceedsGrowth(final PatchCycle cycle) {
		if (hasProducts(cycle.getState())) {
			if (getProductAmount(cycle.getState()) + 1 > maxPick) {
				return true;
			}
		}
		if (hasProducts(cycle.getState()) || cycle.getState() == getPickedBase(0)) {
			return false;
		}
		return super.exceedsGrowth(cycle);
	}

	@Override
	public boolean isFullGrown(final PatchCycle cycle) {
		for (int i = 1; i <= maxPick; i++) {
			if (cycle.getState() == getPickedBase(i)) {
				return true;
			}
		}
		return (cycle.getState() == getCheckHealthBase()) || super.isFullGrown(cycle);
	}

	@Override
	public boolean isGrowing(final PatchCycle cycle) {
		for (int i = 0; i < maxPick; i++) {
			if (cycle.getState() == getPickedBase(i)) {
				return true;
			}
		}
		return super.isGrowing(cycle);
	}

	/**
	 * Gets the check health base.
	 * @return the base.
	 */
	public int getCheckHealthBase() {
		Bushes b = Bushes.forNode(this);
		if (b == null) {
			return 250;
		}
		return 250 + b.ordinal();
	}

	/**
	 * Gets the berry base.
	 * @param amt the amt.
	 * @return the base.
	 */
	public int getPickedBase(int amt) {
		return getBase() + getGrowthCycles() + amt;
	}

	/**
	 * Gets the berry amount.
	 * @param stage the stage.
	 * @return the amount of berries.
	 */
	public int getProductAmount(int stage) {
		for (int i = 0; i < maxPick + 1; i++) {
			if (stage == getPickedBase(i)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks if the plant has berries.
	 * @param state the state.
	 * @return {@code True} if so.
	 */
	public boolean hasProducts(final int state) {
		for (int i = 1; i <= maxPick; i++) {
			if (state == getPickedBase(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if it is the last stage.
	 * @param stage the stage.
	 * @return {@code True} if so.
	 */
	public boolean isLastStage(int stage) {
		return stage == (getBase() + getGrowthCycles()) - 1;
	}

	/**
	 * Gets the maxPick.
	 * @return The maxPick.
	 */
	public int getMaxPick() {
		return maxPick;
	}

}
