package org.crandor.game.node.entity.npc;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.DeathTask;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Location;

/**
 * Handles NPCs that are idle on default but can be disturbed (rock crabs, kraken, ...)
 * @author Emperor
 *
 */
public abstract class IdleAbstractNPC extends AbstractNPC {

	/**
	 * The active NPC id.
	 */
	private int activeId;
	
	/**
	 * If the NPC is currently idle.
	 */
	private boolean idle = true;
	
	/**
	 * The timeout ticks.
	 */
	protected int timeout = 30; //18 second time out to go idle again
	
	/**
	 * Constructs a new {@code IdleAbstractNPC} {@code Object}.
	 * @param idleId The NPC id.
	 * @param location The location.
	 */
	public IdleAbstractNPC(int idleId, int activeId, Location location) {
		super(idleId, location);
		this.activeId = activeId;
	}
	
	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (!idle && !getProperties().getCombatPulse().isAttacking()) {
			long time = getAttribute("combat-time", 0l);
			if ((System.currentTimeMillis() - time) > (timeout * 600)) {
				goIdle(); 
			}
		}
	}
	
	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (isInvisible()) {
			return false;
		}
		if (getTask() != null && entity instanceof Player && getTask().levelReq > entity.getSkills().getStaticLevel(Skills.SLAYER)) {
			((Player) entity).getPacketDispatch().sendMessage("You need a higher slayer level to know how to wound this monster.");
		}
		if (DeathTask.isDead(this)) {
			return false;
		}
		if (!entity.getZoneMonitor().continueAttack(this, style)) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the entity is in range for disturbing this NPC.
	 * @param disturber The entity.
	 * @return {@code True} if so.
	 */
	public boolean inDisturbingRange(Entity disturber) {
		if (idle && disturber.getSwingHandler(false).canSwing(disturber, this) != InteractionType.NO_INTERACT) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the entity can currently disturb this NPC (and returns {@code true}), or returns {@code false}.
	 * @param disturber The disturber.
	 * @return {@code True} if the entity could disturb this NPC.
	 */
	public boolean canDisturb(Entity disturber) {
		return idle;
	}

	/**
	 * Disturbs the NPC so it becomes active.
	 * @param disturber The entity disturbing this NPC.
	 */
	public void disturb(Entity disturber) {
		if (disturber != null) {
			disturber.getProperties().getCombatPulse().setCombatFlags(this);
		} else {
			setAttribute("combat-time", System.currentTimeMillis() + 10000);
		}
		getProperties().getCombatPulse().attack(disturber);
		transform(activeId);
		idle = false;
	}

	/**
	 * Makes the NPC go idle again.
	 */
	public void goIdle() {
		if (idle) {
			return;
		}
		idle = true;
		reTransform();
	}
	
	/**
	 * Gets the idle value.
	 * @return The idle.
	 */
	public boolean isIdle() {
		return idle;
	}

	/**
	 * Sets the idle value.
	 * @param idle The idle to set.
	 */
	public void setIdle(boolean idle) {
		this.idle = idle;
	}

}