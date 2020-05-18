package org.crandor.game.content.skill.member.farming;

import org.crandor.game.content.global.SkillcapePerks;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.farming.patch.Allotments;
import org.crandor.game.content.skill.member.farming.patch.Flowers;
import org.crandor.game.content.skill.member.farming.patch.PatchProtection;
import org.crandor.game.content.skill.member.farming.wrapper.PatchCycle;
import org.crandor.game.content.skill.member.farming.wrapper.PatchWrapper;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.tools.RandomFunction;

/**
 * Represents a farming node such as an allotment type.
 * @author 'Vexia
 * @version 1.0
 */
public class FarmingNode {

	/**
	 * Represents the seed item.
	 */
	private final Item seed;

	/**
	 * Represents the product item.
	 */
	private final Item product;

	/**
	 * Represents the base loc of the config.
	 */
	private final int base;

	/**
	 * Represents the amount of growth cycles.
	 */
	private final int growthCycles;

	/**
	 * Represents the minutes between growth cycles.
	 */
	private final int minutes;

	/**
	 * Represents the level required.
	 */
	private final int level;

	/**
	 * Represents the experiences.
	 */
	private final double[] experiences;

	/**
	 * Represents the item protection.
	 */
	private final Item[] protection;

	/**
	 * Represents the fower protection needed.
	 */
	private Flowers flower;

	/**
	 * Constructs a new {@code FarmingNode} {@code Object}.
	 * @param seed the seed item.
	 * @param product the product.
	 * @param base the base.
	 * @param growthCycles the growth cycles.
	 * @param minutes the minutes between grwoth.
	 * @param level the level required.
	 * @param experiences the experiences agined.
	 */
	public FarmingNode(Item seed, Item product, final int base, int growthCycles, int minutes, int level, double[] experiences, final Item... protection) {
		this.seed = seed;
		this.product = product;
		this.base = base;
		this.growthCycles = growthCycles;
		this.minutes = minutes;
		this.level = level;
		this.experiences = experiences;
		this.protection = protection;
	}

	/**
	 * Constructs a new {@code FarmingNode} {@code Object}.
	 * @param seed the seed item.
	 * @param product the product.
	 * @param base the base.
	 * @param growthCycles the growth cycles.
	 * @param minutes the minutes between grwoth.
	 * @param level the level required.
	 * @param experiences the experiences agined.
	 */
	public FarmingNode(Item seed, Item product, final int base, int growthCycles, int minutes, int level, double[] experiences, final Flowers flower, final Item... protection) {
		this(seed, product, base, growthCycles, minutes, level, experiences, protection);
		this.flower = flower;
	}

	/**
	 * Method used to increment the stage of a patch cycle.
	 * @param cycle the cycle.
	 */
	public void grow(final PatchCycle cycle) {
		cycle.addConfigValue(getNextStage(cycle));
		if (isFullGrown(cycle)) {
			cycle.setProtection(false);
			cycle.setHarvestAmount(getHarvestAmount(cycle));
		}
	}

	/**
	 * Method used to check the health of a node.
	 * @param cycle the cycle.
	 */
	public void checkHealth(final PatchCycle cycle) {
	}

	/**
	 * Method used to pick a nodes content.
	 * @param cycle the cycle.
	 * @return if picked all.
	 */
	public boolean pick(final PatchCycle cycle) {
		return true;
	}

	/**
	 * Checks if the node is currently a stump.
	 * @param cycle the cycle.
	 * @return {@code True} if so.
	 */
	public boolean isStump(PatchCycle cycle) {
		return false;
	}

	/**
	 * Checks if the node is full grown based on the cycle.
	 * @param cycle the cycle.
	 * @return {@code True} if so.
	 */
	public boolean isFullGrown(final PatchCycle cycle) {
		return cycle.getState() == (cycle.getNode().getBase() + cycle.getNode().getGrowthCycles());
	}

	/**
	 * Checks if the node is growing.
	 * @param cycle the cycle.
	 * @return {@code True} if growing.
	 */
	public boolean isGrowing(final PatchCycle cycle) {
		final int base = cycle.getNode().getBase();
		final int cycles = cycle.getNode().getGrowthCycles();
		final int state = cycle.getState();
		return state >= base && state < (base + cycles);
	}

	/**
	 * Checks if a patch can become diseased.
	 * @param cycle the cycle.
	 * @return {@code True} if so.
	 */
	public boolean canDisease(final PatchCycle cycle) {
		if (cycle.getGrowthHandler().isFullGrown() || cycle.isProtected() || isFlowerProtected(cycle)) {
			return false;
		}
		final int random = RandomFunction.random(100);
		int mod = cycle.getWaterHandler().isWatered() ? 0 : (cycle.getCompostThreshold() > 1 ? 10 : RandomFunction.random(30, 50));
		mod -= 8;
		return cycle.getState() != cycle.getNode().getBase() && random < mod;
	}

	/**
	 * Gets the next stage.
	 * @param cycle the cycle.
	 * @return the next stage.
	 */
	public int getNextStage(PatchCycle cycle) {
		return cycle.getState() + 1;
	}

	/**
	 * Checks if the cycle exceeds the growth limit.
	 * @param cycle the cycle.
	 * @return {@code True} if so.
	 */
	public boolean exceedsGrowth(PatchCycle cycle) {
		return (cycle.getState() + 1) > (cycle.getNode().getBase() + cycle.getNode().getGrowthCycles());
	}

	/**
	 * Gets the harvest amount.
	 * @param cycle the cycle.
	 * @return the amount.
	 */
	public int getHarvestAmount(final PatchCycle cycle) {
		int min = cycle.getWrapper().getPatch().getMinimumYield();
		int max = cycle.getWrapper().getPatch().getMaximumYield();
		double difference = (max - min) / 2;
		difference *= 1 + (cycle.getPlayer().getSkills().getLevel(Skills.FARMING) * 0.01);
		int mod = 0;
		if (SkillcapePerks.hasSkillcapePerk(cycle.getPlayer(), SkillcapePerks.FARMING)) {
			mod += RandomFunction.random(1, 2);
		}
		//Magic secateurs
		if(cycle.getPlayer().getEquipment().get(3) != null && cycle.getPlayer().getEquipment().get(3).getId() == 7409){
			mod += RandomFunction.random(2, 4);
		}
		int amount = min + RandomFunction.randomize((int) difference) + mod;
		return amount;
	}

	/**
	 * Checks if the farming node is flower protected.
	 * @param cycle the cycle.
	 * @return {@code True} if so.
	 */
	public boolean isFlowerProtected(final PatchCycle cycle) {
		if (flower == null && this != Allotments.SWEETCORN.getFarmingNode()) {
			return false;
		}
		if (cycle.getNode() == null) {
			return false;
		}
		final Player player = cycle.getPlayer();
		for (PatchProtection protection : PatchProtection.values()) {
			final GameObject flower = RegionManager.getObject(protection.getFlowerLocation());
			if (flower == null) {
				continue;
			}
			final PatchWrapper wrapper = player.getFarmingManager().getPatchWrapper(flower.getWrapper().getId(), false);
			if (wrapper == null) {
				continue;
			}
			if ((wrapper.getCycle().getGrowthHandler().isFullGrown()) || (wrapper.hasScarecrow() && this == Allotments.SWEETCORN.getFarmingNode())) {
				if (this == Allotments.SWEETCORN.getFarmingNode() && !wrapper.hasScarecrow()) {
					continue;
				}
				for (Location l : protection.getAllotmentLocations()) {
					final GameObject allotment = RegionManager.getObject(l);
					if (allotment == null) {
						continue;
					}
					final PatchWrapper aWrapper = player.getFarmingManager().getPatchWrapper(allotment.getWrapper().getId(), false);
					if (aWrapper == null) {
						continue;
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gets the death base.
	 * @return the death base.
	 */
	public int getDeathBase() {
		return 193;
	}

	/**
	 * Gets the disease base.
	 * @return the base.
	 */
	public int getDiseaseBase() {
		return 128;
	}

	/**
	 * Gets the water base.
	 * @return the base.
	 */
	public int getWaterBase() {
		return 64;
	}

	/**
	 * Gets the stump base.
	 * @return the stump base.
	 */
	public int getStumpBase() {
		return getBase() + getGrowthCycles() + 2;
	}

	/**
	 * Gets the seed.
	 * @return The seed.
	 */
	public Item getSeed() {
		return seed;
	}

	/**
	 * Gets the product.
	 * @return The product.
	 */
	public Item getProduct() {
		return product;
	}

	/**
	 * Gets the growthCycles.
	 * @return The growthCycles.
	 */
	public int getGrowthCycles() {
		return growthCycles;
	}

	/**
	 * Gets the minutes.
	 * @return The minutes.
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the experiences.
	 * @return The experiences.
	 */
	public double[] getExperiences() {
		return experiences;
	}

	/**
	 * Gets the base.
	 * @return The base.
	 */
	public int getBase() {
		return base;
	}

	/**
	 * Gets the protection.
	 * @return The protection.
	 */
	public Item[] getProtection() {
		return protection;
	}

}
