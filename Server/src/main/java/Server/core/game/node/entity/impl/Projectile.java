package core.game.node.entity.impl;

import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.system.SystemLogger;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.chunk.ProjectileUpdateFlag;

/**
 * Represents a projectile to send.
 * @author Emperor
 */
public class Projectile {

	/**
	 * The source node.
	 */
	private Entity source;

	/**
	 * The source's centered location.
	 */
	private Location sourceLocation;

	/**
	 * The victim.
	 */
	private Entity victim;

	/**
	 * The projectile's gfx id.
	 */
	private int projectileId;

	/**
	 * The start height.
	 */
	private int startHeight;

	/**
	 * The ending height.
	 */
	private int endHeight;

	/**
	 * The start delay.
	 */
	private int startDelay;

	/**
	 * The speed.
	 */
	private int speed;

	/**
	 * The angle.
	 */
	private int angle;

	/**
	 * The distance to start.
	 */
	private int distance;

	/**
	 * The end location (used for location based projectiles).
	 */
	private Location endLocation;

	/**
	 * Creates a new projectile.
	 * @param source The source entity.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @return The created projectile.
	 */
	public static Projectile create(Entity source, Entity victim, int projectileId) {
		int speed = (int) (46 + (getLocation(source).getDistance(victim.getLocation()) * 5));
		return new Projectile(source, victim, projectileId, 40, 36, 41, speed, 5, source == null ? 11 : source.size() << 5);
	}

	/**
	 * Creates a new projectile.
	 * @param source The source node.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @return The created projectile.
	 */
	public static Projectile create(Entity source, Entity victim, int projectileId, int startHeight, int endHeight) {
		int speed = (int) (46 + (getLocation(source).getDistance(victim.getLocation()) * 5));
		return new Projectile(source, victim, projectileId, startHeight, endHeight, 41, speed, 5, source == null ? 11 : source.size() << 5);
	}

	/**
	 * Creates a new projectile.
	 * @param source The source node.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @param startDelay The start delay.
	 * @return The created projectile.
	 */
	public static Projectile create(Entity source, Entity victim, int projectileId, int startHeight, int endHeight, int startDelay) {
		int speed = (int) (46 + (getLocation(source).getDistance(victim.getLocation()) * 5));
		return new Projectile(source, victim, projectileId, startHeight, endHeight, startDelay, speed, 5, source == null ? 11 : source.size() << 5);
	}

	/**
	 * Creates a new projectile.
	 * @param source The source node.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @param startDelay The start delay.
	 * @param speed The projectile speed.
	 * @return The created projectile.
	 */
	public static Projectile create(Entity source, Entity victim, int projectileId, int startHeight, int endHeight, int startDelay, int speed) {
		return new Projectile(source, victim, projectileId, startHeight, endHeight, startDelay, speed, 5, source == null ? 11 : source.size() << 5);
	}

	/**
	 * Creates a new projectile.
	 * @param source The source node.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @param startDelay The start delay.
	 * @param speed The projectile speed.
	 * @param angle The angle.
	 * @return The created projectile.
	 */
	public static Projectile create(Entity source, Entity victim, int projectileId, int startHeight, int endHeight, int startDelay, int speed, int angle) {
		return new Projectile(source, victim, projectileId, startHeight, endHeight, startDelay, speed, angle, source.size() << 5);
	}

	/**
	 * Creates a new projectile.
	 * @param source The source node.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @param startDelay The startDelay.
	 * @param speed The projectile speed.
	 * @param angle The angle.
	 * @param distance The distance to start from.
	 * @return The created projectile.
	 */
	public static Projectile create(Entity source, Entity victim, int projectileId, int startHeight, int endHeight, int startDelay, int speed, int angle, int distance) {
		return new Projectile(source, victim, projectileId, startHeight, endHeight, startDelay, speed, angle, distance);
	}

	/**
	 * Creates a new projectile.
	 * @param destination The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @param startDelay The startDelay.
	 * @param speed The projectile speed.
	 * @param angle The angle.
	 * @param distance The distance to start from.
	 * @return The created projectile.
	 */
	public static Projectile create(Location start, Location destination, int projectileId, int startHeight, int endHeight, int startDelay, int speed, int angle, int distance) {
		return new Projectile(start, destination, projectileId, startHeight, endHeight, startDelay, speed, angle, distance);
	}

	/**
	 * Creates a new magic-speed based projectile.
	 * @param source The source node.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @param startDelay The start delay type.
	 * @param angle The angle.
	 * @return The created projectile.
	 */
	public static Projectile magic(Entity source, Entity victim, int projectileId, int startHeight, int endHeight, int startDelay, int angle) {
		int speed = (int) (46 + (getLocation(source).getDistance(victim.getLocation()) * 10));
		return new Projectile(source, victim, projectileId, startHeight, endHeight, startDelay, speed, angle, source.size() << 5);
	}

	/**
	 * Creates a new range-speed based projectile.
	 * @param source The source node.
	 * @param victim The victim.
	 * @param projectileId The projectile's gfx id;
	 * @param startHeight The starting height.
	 * @param endHeight The ending height.
	 * @param startDelay The start delay.
	 * @param angle The angle.
	 * @return The created projectile.
	 */
	public static Projectile ranged(Entity source, Entity victim, int projectileId, int startHeight, int endHeight, int startDelay, int angle) {
		int speed = (int) (46 + (getLocation(source).getDistance(victim.getLocation()) * 5));
		return new Projectile(source, victim, projectileId, startHeight, endHeight, startDelay, speed, angle, source.size() << 5);
	}

	/**
	 * Constructs a new {@code Projectile} {@code Object}.
	 */
	public Projectile() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new projectile.
	 * @param source The source node.
	 * @param victim The entity victim.
	 * @param projectileId The projectile gfx id.
	 * @param startHeight The start height.
	 * @param endHeight The end height.
	 * @param startDelay The start delay.
	 * @param speed The projectile speed.
	 * @param angle The projectile angle.
	 * @param distance The distance.
	 */
	private Projectile(Entity source, Entity victim, int projectileId, int startHeight, int endHeight, int startDelay, int speed, int angle, int distance) {
		this.source = source;
		this.sourceLocation = getLocation(source);
		this.victim = victim;
		this.projectileId = projectileId;
		this.startHeight = startHeight;
		this.endHeight = endHeight;
		this.startDelay = startDelay;
		this.speed = speed;
		this.angle = angle;
		this.distance = distance;
	}

	/**
	 * Constructs a new {@code Projectile} {@code Object}.
	 * @param start The start location.
	 * @param projectileId The projectile id.
	 * @param startHeight The start height.
	 * @param endHeight The end height.
	 * @param startDelay The start delay.
	 * @param speed The speed.
	 * @param angle The angle.
	 * @param distance The distance.
	 */
	private Projectile(Location start, Location l, int projectileId, int startHeight, int endHeight, int startDelay, int speed, int angle, int distance) {
		this.sourceLocation = start;
		this.endLocation = l;
		this.projectileId = projectileId;
		this.startHeight = startHeight;
		this.endHeight = endHeight;
		this.startDelay = startDelay;
		this.speed = speed;
		this.angle = angle;
		this.distance = distance;
	}

	/**
	 * Gets the speed.
	 * @param source the source.
	 * @param targetLoc the target location.
	 * @return
	 */
	public static int getSpeed(Entity source, Location targetLoc) {
		return (int) (46 + (getLocation(source).getDistance(targetLoc) * 5));
	}

	/**
	 * Gets the source location on construction.
	 * @param n The node.
	 * @return The centered location.
	 */
	public static Location getLocation(Entity n) {
		if (n == null) {
			return null;
		}
		return n.getCenterLocation();
	}

	/**
	 * Changes the projectile so it sends from the source mob to the victim mob
	 * given.
	 * @param source The source mob.
	 * @param victim The victim mob.
	 * @return The projectile instance.
	 */
	public Projectile transform(Entity source, Entity victim) {
		return transform(source, victim, source instanceof NPC, 46, 5);
	}

	/**
	 * Changes the projectile so it sends from the source mob to the victim mob
	 * given.
	 * @param source The source mob.
	 * @param victim The victim mob.
	 * @param npc If the source should be handled as an NPC.
	 * @param baseSpeed The base speed.
	 * @param modifiedSpeed The modified speed.
	 * @return The projectile instance.
	 */
	public Projectile transform(Entity source, Entity victim, boolean npc, int baseSpeed, int modifiedSpeed) {
		this.source = source;
		this.sourceLocation = getLocation(source);
		this.victim = victim;
		this.speed = (int) (baseSpeed + sourceLocation.getDistance(victim.getLocation()) * modifiedSpeed);
		if (npc) {
			this.distance = source.size() << 5;
		}
		return this;
	}

	/**
	 * Gets a new {@code Projectile} {@code Object} based of this projectile
	 * object.
	 * @param source The source entity.
	 * @param victim The victim entity.
	 * @param speedMultiplier The speed multiplier.
	 * @return The created {@code Projectile} {@code Object}.
	 */
	public Projectile copy(Entity source, Entity victim, double speedMultiplier) {
		// int distance = source instanceof NPC ? source.size() << 6 : 11;
		int speed = (int) (this.speed + (source.getLocation().getDistance(victim.getLocation()) * speedMultiplier));
		return new Projectile(source, victim, projectileId, startHeight, endHeight, startDelay, speed, angle, distance);
	}

	/**
	 * Transforms the projectile so it is location based and it sends to the
	 * location.
	 * @param source The source mob.
	 * @param l The location.
	 * @return The projectile instance.
	 */
	public Projectile transform(Entity source, Location l) {
		return transform(source, l, source instanceof NPC, 46, 5);
	}

	/**
	 * Transforms a projectile to be location based, with updated parameters.
	 * @param source The mob sending this projectile.
	 * @param l The end location.
	 * @param npc If the mob should be handled as an npc.
	 * @param baseSpeed The base speed.
	 * @param modifiedSpeed The modified speed.
	 * @return The projectile instance.
	 */
	public Projectile transform(Entity source, Location l, boolean npc, int baseSpeed, int modifiedSpeed) {
		this.source = source;
		this.sourceLocation = getLocation(source);
		this.endLocation = l;
		this.speed = (int) (baseSpeed + sourceLocation.getDistance(l) * modifiedSpeed);
		if (npc) {
			this.distance = source.size() << 5;
		}
		return this;
	}

	/**
	 * Sends this projectile.
	 */
	public void send() {
		send(this);
	}

	/**
	 * Sends the projectile.
	 * @param p The projectile.
	 */
	public static void send(Projectile p) {
		RegionManager.getRegionChunk(p.getSourceLocation()).flag(new ProjectileUpdateFlag(p));
	}

	/**
	 * Transforms the projectile (to start from the source & end at the victim)
	 * and sends it.
	 * @param source The source.
	 * @param victim The victim.
	 * @param p The projectile to send.
	 */
	public static void send(Entity source, Entity victim, Projectile p) {
		send(p.transform(source, victim));
	}

	/**
	 * Transforms the projectile (to start from the source & end at the
	 * locations) and sends it.
	 * @param source The source.
	 * @param l The location.
	 * @param p The projectile to send.
	 */
	public static void send(Entity source, Location l, Projectile p) {
		send(p.transform(source, l));
	}

	/**
	 * @param source The source node.
	 */
	public void setSource(Entity source) {
		this.source = source;
	}

	/**
	 * @return The source node.
	 */
	public Entity getSource() {
		return source;
	}

	/**
	 * @param sourceLocation the sourceLocation to set
	 */
	public void setSourceLocation(Location sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	/**
	 * @return the sourceLocation
	 */
	public Location getSourceLocation() {
		return sourceLocation;
	}

	/**
	 * @param victim The entity victim.
	 */
	public void setVictim(Entity victim) {
		this.victim = victim;
	}

	/**
	 * @return The entity victim.
	 */
	public Entity getVictim() {
		return victim;
	}

	/**
	 * @param projectileId the projectileId to set
	 */
	public void setProjectileId(int projectileId) {
		this.projectileId = projectileId;
	}

	/**
	 * @return the projectileId
	 */
	public int getProjectileId() {
		return projectileId;
	}

	/**
	 * @param startHeight the startHeight to set
	 */
	public void setStartHeight(int startHeight) {
		this.startHeight = startHeight;
	}

	/**
	 * @return the startHeight
	 */
	public int getStartHeight() {
		return startHeight;
	}

	/**
	 * @param endHeight the endHeight to set
	 */
	public void setEndHeight(int endHeight) {
		this.endHeight = endHeight;
	}

	/**
	 * @return the endHeight
	 */
	public int getEndHeight() {
		return endHeight;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setStartDelay(int delay) {
		this.startDelay = delay;
	}

	/**
	 * @return the start delay.
	 */
	public int getStartDelay() {
		return startDelay;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}

	/**
	 * @return the angle
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Checks if the projectile is location based.
	 * @return {@code True} if so, {@code false} if not.
	 */
	public boolean isLocationBased() {
		return endLocation != null;
	}

	/**
	 * @return the endLocation
	 */
	public Location getEndLocation() {
		return endLocation;
	}

	/**
	 * @param endLocation the endLocation to set
	 */
	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

}