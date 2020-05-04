package plugin.activity.pestcontrol.monsters;

import org.crandor.game.interaction.MovementPulse;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.MapDistance;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.tools.RandomFunction;
import plugin.activity.pestcontrol.PestControlSession;

/**
 * Handles the Ravager pest control NPC.
 * @author Emperor
 */
public class PCRavagerNPC extends AbstractNPC {

	/**
	 * The current session.
	 */
	private PestControlSession session;

	/**
	 * Current object target to destroy.
	 */
	private GameObject target;

	/**
	 * The portal index.
	 */
	private int portalIndex;

	/**
	 * Next attack tick.
	 */
	private int nextAttack;

	/**
	 * The limitation borders.
	 */
	private ZoneBorders offLimits;

	/**
	 * Constructs a new {@code PCRavagerNPC} {@code Object}.
	 */
	public PCRavagerNPC() {
		super(3742, null);
	}

	/**
	 * Constructs a new {@code PCRavagerNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public PCRavagerNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new PCRavagerNPC(id, location);
	}

	@Override
	public void init() {
		super.init();
		super.walkRadius = 64;
		if ((session = getExtension(PestControlSession.class)) != null) {
			Location l = session.getRegion().getBaseLocation();
			offLimits = new ZoneBorders(l.getX() + 20, l.getY() + 26, l.getX() + 44, l.getY() + 57);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (session != null && !getProperties().getCombatPulse().isAttacking()) {
			if (target == null) {
				if (findTarget()) {
					getPulseManager().clear();
					walk(target);
				} else if (!getPulseManager().hasPulseRunning() && RandomFunction.RANDOM.nextInt(10) < 2) {
					if (offLimits.insideBorder(getLocation().getX(), getLocation().getY())) {
						walk(session.getPortals()[portalIndex]);
					} else {
						walk(session.getSquire().getLocation());
					}
				}
			} else {
				if (!target.isActive() || !session.getBarricades().contains(target)) {
					attack(null);
				} else if (nextAttack < GameWorld.getTicks() && getLocation().withinDistance(target.getLocation(), 1)) {
					getPulseManager().clear();
					setWalks(false);
					super.getWalkingQueue().reset();
					super.getLocks().lockMovement(2);
					super.faceLocation(target.getLocation());
					super.animate(getProperties().getAttackAnimation());
					int newId = target.getId() + (target.getId() < 14233 ? 3 : 4);
					boolean destroyed = !isTarget(newId);
					int type = destroyed ? 22 : target.getType();
					final GameObject o = target;
					final GameObject newTarget = o.transform(newId, o.getRotation(), type);
					GameWorld.Pulser.submit(new Pulse(1, this, o) {
						@Override
						public boolean pulse() {
							if (getViewport().getRegion().isActive() && session.getBarricades().remove(o)) {
								session.getBarricades().add(newTarget);
								ObjectBuilder.replace(o, newTarget);
							}
							return true;
						}
					});
					target = newTarget;
					if (destroyed) {
						attack(null);
					}
					nextAttack = GameWorld.getTicks() + 5;
				}
			}
		} else {
			attack(null);
		}
		getProperties().setRetaliating(target == null);
	}

	/**
	 * Starts attacking a game object.
	 * @param o The object.
	 */
	private void attack(GameObject o) {
		target = o;
		if (o == null) {
			setWalks(true);
			super.faceLocation(null);
		}
	}

	/**
	 * Walks to the destination.
	 * @param destination The destination.
	 */
	private void walk(Node destination) {
		getPulseManager().run(new MovementPulse(this, destination, Pathfinder.SMART) {
			@Override
			public boolean pulse() {
				return true;
			}
		}, "movement");
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
	 * Finds an object target.
	 * @return {@code True} if a target was found.
	 */
	private boolean findTarget() {
		for (GameObject o : session.getBarricades()) {
			if (o.getLocation().withinDistance(getLocation(), MapDistance.RENDERING.getDistance() / 3) && isTarget(o.getId())) {
				attack(o);
				return true;
			}
		}
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3742, 3743, 3744, 3745, 3746 };
	}

	/**
	 * Gets the portalIndex.
	 * @return The portalIndex.
	 */
	public int getPortalIndex() {
		return portalIndex;
	}

	/**
	 * Sets the portalIndex.
	 * @param portalIndex The portalIndex to set.
	 */
	public void setPortalIndex(int portalIndex) {
		this.portalIndex = portalIndex;
	}

}