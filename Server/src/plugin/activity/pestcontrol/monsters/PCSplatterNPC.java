package plugin.activity.pestcontrol.monsters;

import java.util.ArrayList;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;
import plugin.activity.pestcontrol.PestControlSession;

/**
 * Handles the pest control splatter NPC.
 * @author Emperor
 */
public final class PCSplatterNPC extends AbstractNPC {

	/**
	 * The current session.
	 */
	private PestControlSession session;

	/**
	 * If the splatter is exploding.
	 */
	private boolean exploding;

	/**
	 * Constructs a new {@code PCSplatterNPC} {@code Object}.
	 */
	public PCSplatterNPC() {
		super(3727, null);
	}

	/**
	 * Constructs a new {@code PCSplatterNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public PCSplatterNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public void init() {
		super.init();
		super.walkRadius = 64;
		session = getExtension(PestControlSession.class);
	}

	@Override
	public void tick() {
		super.tick();
		if (exploding || session == null) {
			return;
		}
		if (getProperties().getCombatPulse().isAttacking()) {
			return;
		}
		for (GameObject o : session.getBarricades()) {
			if (o.getLocation().isNextTo(this)) {
				commenceDeath(this);
				break;
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
	public void commenceDeath(Entity killer) {
		exploding = true;
		visualize(new Animation(3888, Priority.VERY_HIGH), Graphics.create(649 + (getId() - 3727)));
		GameWorld.Pulser.submit(new Pulse(1, this) {
			@Override
			public boolean pulse() {
				explode();
				return true;
			}
		});
	}

	@Override
	public void finalizeDeath(Entity killer) {

	}

	/**
	 * Handles the exploding of the splatter.
	 */
	private void explode() {
		int maximum = getProperties().getCurrentCombatLevel() / 3;
		int minimum = maximum / 2;
		if (session != null) {
			for (GameObject o : new ArrayList<>(session.getBarricades())) {
				if (o.getLocation().isNextTo(this)) {
					int newId = o.getId() + (o.getId() < 14233 ? 3 : 4);
					boolean destroyed = !isTarget(newId);
					final GameObject newTarget = o.transform(newId, o.getRotation(), destroyed ? 22 : o.getType());
					if (session.getBarricades().remove(o)) {
						session.getBarricades().add(newTarget);
						ObjectBuilder.replace(o, newTarget);
					}
				}
			}
		}
		for (Player p : RegionManager.getLocalPlayers(this, 1)) {
			p.getImpactHandler().manualHit(this, RandomFunction.random(minimum, maximum), null);
		}
		for (NPC npc : RegionManager.getLocalNpcs(this, 1)) {
			if (npc != this) {
				npc.getImpactHandler().manualHit(this, RandomFunction.random(minimum, maximum), null);
			}
		}
		clear();
	}

	/**
	 * Checks if the object id is a possible target.
	 * @param id The object id.
	 * @return {@code true} if so.
	 */
	private static boolean isTarget(int id) {
		for (int object : PestControlSession.INVALID_OBJECT_IDS) {
			if (id == object) {
				return false;
			}
		}
		return true;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new PCSplatterNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3727, 3728, 3729, 3730, 3731 };
	}

}