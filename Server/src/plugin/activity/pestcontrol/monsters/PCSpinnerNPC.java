package plugin.activity.pestcontrol.monsters;

import org.crandor.game.interaction.MovementPulse;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.state.EntityState;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;
import plugin.activity.pestcontrol.PestControlSession;

/**
 * Handles a pest control spinner NPC.
 * @author Emperor
 */
public final class PCSpinnerNPC extends AbstractNPC {

	/**
	 * The current session.
	 */
	private PestControlSession session;

	/**
	 * The portal index.
	 */
	private int portalIndex;

	/**
	 * Constructs a new {@code PCSpinnerNPC} {@code Object}.
	 */
	public PCSpinnerNPC() {
		super(3747, null);
	}

	/**
	 * Constructs a new {@code PCSpinnerNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public PCSpinnerNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public void init() {
		super.init();
		super.walkRadius = 4;
		session = getExtension(PestControlSession.class);
	}

	@Override
	public void tick() {
		super.tick();
		if (session != null && RandomFunction.RANDOM.nextInt(10) < 2) {
			NPC portal = session.getPortals()[portalIndex];
			if (portal.isActive() && portal.getSkills().getLifepoints() > 0) {
				if (portal.getSkills().getLifepoints() < portal.getSkills().getMaximumLifepoints()) {
					if (getLocation().withinDistance(portal.getCenterLocation(), 5)) {
						heal();
					}
					getPulseManager().run(new MovementPulse(this, portal) {
						@Override
						public boolean pulse() {
							return true;
						}
					}, "movement");
				}
			}
		}
	}

	/**
	 * Heals the portal.
	 */
	public void heal() {
		NPC portal = session.getPortals()[portalIndex];
		if (!portal.isActive() || portal.getSkills().getLifepoints() < 1) {
			return;
		}
		faceTemporary(portal, 1);
		visualize(new Animation(3911, Priority.HIGH), Graphics.create(658));
		portal.getSkills().heal(portal.getSkills().getMaximumLifepoints() / 10);
		portal.onImpact(this, null);
	}

	/**
	 * Handles the exploding of a spinner.
	 */
	public void explode() {
		animate(getProperties().getDeathAnimation());
		for (Player p : RegionManager.getLocalPlayers(this, 1)) {
			p.getImpactHandler().manualHit(this, 5, HitsplatType.POISON);
			p.setAttribute("/save:poison_damage", 18);
			p.getStateManager().register(EntityState.POISONED, false, 18, this);
		}
		GameWorld.Pulser.submit(new Pulse(1, this) {
			@Override
			public boolean pulse() {
				clear();
				return true;
			}
		});
	}

	@Override
	public void onImpact(final Entity entity, BattleState state) {
		super.onImpact(entity, state);
		if (session != null && state != null && entity instanceof Player) {
			int total = 0;
			if (state.getEstimatedHit() > 0) {
				total += state.getEstimatedHit();
			}
			if (state.getSecondaryHit() > 0) {
				total += state.getSecondaryHit();
			}
			session.addZealGained((Player) entity, total);
		}
	}
	
	/**
	 * Sets the portal index.
	 * @param portalIndex The portal index.
	 * @return This instance, for chaining.
	 */
	public PCSpinnerNPC setPortalIndex(int portalIndex) {
		this.portalIndex = portalIndex;
		return this;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new PCSpinnerNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3747, 3748, 3749, 3750, 3751 };
	}

}