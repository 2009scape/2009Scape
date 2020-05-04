package plugin.activity.pestcontrol.monsters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatPulse;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;
import plugin.activity.pestcontrol.PestControlSession;

/**
 * Handles the pest control shifter NPCs.
 * @author Emperor
 */
public final class PCShifterNPC extends AbstractNPC {

	/**
	 * The pest control session.
	 */
	private PestControlSession session;

	/**
	 * The combat swing handler.
	 */
	private static final CombatSwingHandler SWING_HANDLER = new MeleeSwingHandler() {
		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			// Allows for diagonal combat
			return CombatStyle.RANGE.getSwingHandler().canSwing(entity, victim);
		}
	};

	/**
	 * Constructs a new {@code PCShifterNPC} {@code Object}.
	 */
	public PCShifterNPC() {
		super(3732, null);
	}

	/**
	 * Constructs a new {@code PCShifterNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public PCShifterNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public void init() {
		super.setAggressive(true);
		super.init();
		super.getDefinition().setCombatDistance(1);
		super.walkRadius = 64;
		getProperties().getCombatPulse().setStyle(CombatStyle.MELEE);
		session = getExtension(PestControlSession.class);
	}

	@Override
	public void tick() {
		super.tick();
		CombatPulse pulse = getProperties().getCombatPulse();
		if (session != null && !inCombat() && !pulse.isAttacking() && RandomFunction.RANDOM.nextInt(50) < 2) {
			pulse.attack(session.getSquire());
		}
		if (pulse.isAttacking() && !getLocation().withinDistance(pulse.getVictim().getLocation(), 5)) {
			if (session == null || session.isActive()) {
				teleport(session, this, getDestination(pulse.getVictim()));
			}
		}
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

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return SWING_HANDLER;
	}

	/**
	 * Gets the destination location.
	 * @param victim The victim.
	 * @return The destination.
	 */
	private Location getDestination(Entity victim) {
		List<Location> locations = new ArrayList<>();
		int radius = 2;
		for (int x = -radius; x < radius + 1; x++) {
			for (int y = -radius; y < radius + 1; y++) {
				if (x != 0 && y != 0) {
					locations.add(victim.getLocation().transform(x, y, 0));
				}
			}
		}
		Collections.shuffle(locations, RandomFunction.RANDOM);
		for (Location l : locations) {
			if (RegionManager.isTeleportPermitted(l)) {
				return l;
			}
		}
		return null;
	}

	/**
	 * Teleports the entity to the destination location.
	 * @param session The pest control session.
	 * @param entity The entity.
	 * @param destination The destination.
	 */
	public static void teleport(final PestControlSession session, final Entity entity, final Location destination) {
		if (destination == null || session != null && destination.getRegionId() != session.getRegion().getId()) {
			return;
		}
		Graphics.send(Graphics.create(654), entity.getLocation());
		entity.getProperties().setTeleportLocation(destination);
		entity.getWalkingQueue().reset();
		entity.getLocks().lockMovement(2);
		entity.lock(3);
		GameWorld.Pulser.submit(new Pulse(1, entity) {
			@Override
			public boolean pulse() {
				entity.animate(Animation.create(3904));
				Graphics.send(Graphics.create(654), destination);
				return true;
			}
		});
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new PCShifterNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3732, 3733, 3734, 3735, 3736, 3737, 3738, 3739, 3740, 3741 };
	}

}