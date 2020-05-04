package org.crandor.game.content.skill.member.hunter;

import org.crandor.game.content.skill.member.hunter.bnet.ImplingNode;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.mysql.impl.NPCConfigSQLHandler;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;

/**
 * Handles an impling npc.
 * @author Vexia
 */
public final class ImplingNPC extends AbstractNPC {

	/**
	 * The impling node.
	 */
	private final ImplingNode impling;

	/**
	 * Constructs a new {@code ImplingNPC} {@code Object}.
	 */
	public ImplingNPC() {
		super(0, null);
		this.impling = null;
		this.setWalks(true);
		this.setWalkRadius(20);
	}

	/**
	 * Constructs a new {@code ImplingNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public ImplingNPC(int id, Location location, ImplingNode impling) {
		super(id, location);
		this.impling = impling;
		if (impling != null) {
			this.getDefinition().getConfigurations().put(NPCConfigSQLHandler.RESPAWN_DELAY, impling.getRespawnTime());
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new ImplingNPC(id, location, null);
	}

	@Override
	public void handleTickActions() {
		if (!getLocks().isMovementLocked()) {
			if (isWalks() && !getPulseManager().hasPulseRunning() && nextWalk < GameWorld.getTicks()) {
				setNextWalk();
				Location l = getLocation().transform(-5 + RandomFunction.random(getWalkRadius()), -5 + RandomFunction.random(getWalkRadius()), 0);
				if (canMove(l)) {
					Pathfinder.find(this, l, true, Pathfinder.PROJECTILE).walk(this);
				}
			}
		}
		if (RandomFunction.random(100) < 4) {
			sendChat("Tee hee!");
		}
		int nextTeleport = getAttribute("nextTeleport", -1);
		if (nextTeleport > -1 && GameWorld.getTicks() > nextTeleport) {
			setAttribute("nextTeleport", GameWorld.getTicks() + 600);
			graphics(new Graphics(590));
			GameWorld.Pulser.submit(new Pulse(1) {
				@Override
				public boolean pulse() {
					teleport(ImpetuousImpulses.LOCATIONS[RandomFunction.random(ImpetuousImpulses.LOCATIONS.length)]);
					return true;
				}
			});
			return;
		}
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (style != CombatStyle.MAGIC) {
			return false;
		}
		if (entity.getProperties().getSpell().getSpellId() == 12 || entity.getProperties().getSpell().getSpellId() == 30 || entity.getProperties().getSpell().getSpellId() == 56) {
			return true;
		}
		return super.isAttackable(entity, style);
	}

	@Override
	public void handleDrops(Player p, Entity killer) {
		getProperties().setTeleportLocation(getProperties().getSpawnLocation());
	}

	@Override
	public void checkImpact(BattleState state) {
	}

	@Override
	public int[] getIds() {
		return new int[] {};
	}

	/**
	 * Gets the impling.
	 * @return The impling.
	 */
	public ImplingNode getImpling() {
		return impling;
	}

}
