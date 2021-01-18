package core.game.node.entity.npc.agg;

import core.ServerConstants;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.DeathTask;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.game.world.GameWorld;
import core.tools.RandomFunction;

/**
 * Used to handle entity aggressiveness.
 * @author Emperor
 */
public final class AggressiveHandler {

	/**
	 * The entity.
	 */
	private final Entity entity;

	/**
	 * The radius.
	 */
	private int radius = 4;

	/**
	 * The amount of ticks to pause aggressiveness with.
	 */
	private int pauseTicks = 0;

	/**
	 * If target switching is enabled.
	 */
	private boolean targetSwitching;

	/**
	 * The aggressiveness behavior.
	 */
	private final AggressiveBehavior behavior;

	/**
	 * The aggressive chance ratio (1/10).
	 */
	private int chanceRatio = 2;

	/**
	 * The tolerance data.
	 */
	private final int[] playerTolerance = new int[ServerConstants.MAX_PLAYERS];

	/**
	 * If tolerance is allowed.
	 */
	private boolean allowTolerance = true;

	/**
	 * Constructs a new {@code AggressiveHandler} {@code Object}.
	 * @param entity The entity.
	 * @param behavior The aggressive behavior.
	 */
	public AggressiveHandler(Entity entity, AggressiveBehavior behavior) {
		this.entity = entity;
		this.behavior = behavior;
	}

	/**
	 * Selects a target.
	 * @return {@code True} if the entity has selected a target.
	 */
	public boolean selectTarget() {
		if (pauseTicks > GameWorld.getTicks() || entity.getLocks().isInteractionLocked()) {
			return false;
		}
		if ((!targetSwitching && entity.getProperties().getCombatPulse().isAttacking()) || DeathTask.isDead(entity)) {
			return false;
		}
		if (RandomFunction.randomize(10) > chanceRatio) {
			return false;
		}
		Entity target = behavior.getLogicalTarget(entity, behavior.getPossibleTargets(entity, radius));
		if (target instanceof Player) {
			if (target.getAttribute("ignore_aggression", false)) {
				if (((Player) target).getRights().equals(Rights.ADMINISTRATOR)) {
					return false;
				}
			}
		}
		if (target != null) {
			target.setAttribute("aggressor", entity);
			if (target != entity.getProperties().getCombatPulse().getVictim()) {
				if (entity.getProperties().getCombatPulse().isAttacking()) {
					entity.getProperties().getCombatPulse().setVictim(target);
					entity.face(target);
				} else {
					entity.getProperties().getCombatPulse().attack(target);
				}
				return true;
			}
		}
		return entity.getProperties().getCombatPulse().isAttacking();
	}

	/**
	 * Removes the tolerance of a player.
	 * @param index The player index.
	 */
	public synchronized void removeTolerance(int index) {
		playerTolerance[index] = 0;
	}

	/**
	 * Gets the radius.
	 * @return The radius.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the radius.
	 * @param radius The radius to set.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Gets the pauseTicks.
	 * @return The pauseTicks.
	 */
	public int getPauseTicks() {
		return pauseTicks;
	}

	/**
	 * Sets the pauseTicks.
	 * @param pauseTicks The amount of ticks to pause for.
	 */
	public void setPauseTicks(int pauseTicks) {
		this.pauseTicks = GameWorld.getTicks() + pauseTicks;
	}

	/**
	 * Gets the playerTolerance.
	 * @return The playerTolerance.
	 */
	public int[] getPlayerTolerance() {
		return playerTolerance;
	}

	/**
	 * Gets the targetSwitching.
	 * @return The targetSwitching.
	 */
	public boolean isTargetSwitching() {
		return targetSwitching;
	}

	/**
	 * Sets the targetSwitching.
	 * @param targetSwitching The targetSwitching to set.
	 */
	public void setTargetSwitching(boolean targetSwitching) {
		this.targetSwitching = targetSwitching;
	}

	/**
	 * Gets the chanceRatio.
	 * @return The chanceRatio.
	 */
	public int getChanceRatio() {
		return chanceRatio;
	}

	/**
	 * Sets the chanceRatio (a ratio of 0-10).
	 * @param chanceRatio The chanceRatio to set.
	 */
	public void setChanceRatio(int chanceRatio) {
		this.chanceRatio = chanceRatio;
	}

	/**
	 * Gets the allowTolerance.
	 * @return The allowTolerance.
	 */
	public boolean isAllowTolerance() {
		return allowTolerance;
	}

	/**
	 * Sets the allowTolerance.
	 * @param allowTolerance The allowTolerance to set.
	 */
	public void setAllowTolerance(boolean allowTolerance) {
		this.allowTolerance = allowTolerance;
	}

}