package org.crandor.game.node.entity.impl;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.player.ForceMovementFlag;

/**
 * The force movement handler.
 * @author Emperor
 */
public class ForceMovement extends Pulse {

	/**
	 * The walking speed.
	 */
	public static final int WALKING_SPEED = 10;

	/**
	 * The running speed.
	 */
	public static final int RUNNING_SPEED = 20;

	/**
	 * The walking animation.
	 */
	public static final Animation WALK_ANIMATION = Animation.create(819);

	/**
	 * The entity.
	 */
	protected Entity entity;

	/**
	 * The location to start the force movement from.
	 */
	private Location start;

	/**
	 * The destination.
	 */
	private Location destination;

	/**
	 * The animation.
	 */
	private Animation startAnim;

	/**
	 * The animation.
	 */
	protected Animation animation;

	/**
	 * The ending animation.
	 */
	private Animation endAnimation = null;

	/**
	 * The direction.
	 */
	protected Direction direction;

	/**
	 * The commencing speed.
	 */
	private int commenceSpeed;

	/**
	 * The path speed.
	 */
	private int pathSpeed;

	/**
	 * Constructs a new {@code ForceMovement} {@code Object}.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination.
	 * @param startAnim The start animation.
	 * @param animation The animation
	 * @param direction The direction.
	 * @param ticks The amount of ticks.
	 */
	public ForceMovement(Entity e, Location start, Location destination, Animation startAnim, Animation animation, Direction direction, int commenceSpeed, int pathSpeed) {
		super(1, e);
		this.entity = e;
		this.start = start;
		this.destination = destination;
		this.startAnim = startAnim;
		this.animation = animation;
		this.direction = direction;
		this.commenceSpeed = commenceSpeed;
		this.pathSpeed = pathSpeed;
	}

	/**
	 * Constructs a new {@code ForceMovement} {@code Object}.
	 * @param e the entity.
	 * @param start the start location.
	 * @param end the destination.
	 * @param animation the animation.
	 * @param speed The path speed.
	 */
	public ForceMovement(Entity e, Location start, Location end, Animation animation, int speed) {
		this(e, start, end, WALK_ANIMATION, animation, direction(start, end), WALKING_SPEED, speed);
	}

	/**
	 * Constructs a new {@code ForceMovement} {@code Object}.
	 * @param e the entity.
	 * @param start the start location.
	 * @param destination the destination.
	 * @param animation the animation.
	 */
	public ForceMovement(Entity e, Location start, Location destination, Animation animation) {
		this(e, start, destination, WALK_ANIMATION, animation, direction(start, destination), WALKING_SPEED, WALKING_SPEED);
	}

	/**
	 * Constructs a new {@code ForceMovement} {@code Object}.
	 * @param e the entity.
	 * @param start the start loc.
	 * @param destination the destination.
	 * @param animation the animation.
	 */
	public ForceMovement(Location start, Location destination, Animation animation) {
		this(null, start, destination, WALK_ANIMATION, animation, direction(start, destination), WALKING_SPEED, WALKING_SPEED);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param destination The destination location.
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location destination) {
		return run(e, e.getLocation(), destination, WALK_ANIMATION, WALK_ANIMATION, direction(e.getLocation(), destination), WALKING_SPEED, WALKING_SPEED);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination location.
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location start, Location destination) {
		return run(e, start, destination, WALK_ANIMATION, WALK_ANIMATION, direction(e.getLocation(), destination), WALKING_SPEED, WALKING_SPEED);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination location.
	 * @param animation The animation.
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location start, Location destination, Animation animation) {
		return run(e, start, destination, WALK_ANIMATION, animation, direction(start, destination), WALKING_SPEED, WALKING_SPEED);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination location.
	 * @param animation The animation.
	 * @param speed The path speed.
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location start, Location destination, Animation animation, int speed) {
		return run(e, start, destination, WALK_ANIMATION, animation, direction(start, destination), WALKING_SPEED, speed);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination location.
	 * @param animation The animation.
	 * @param direction The direction.
	 * @param speed The speed (in ticks).
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location start, Location destination, Animation startAnim, Animation animation) {
		return run(e, start, destination, startAnim, animation, direction(start, destination), WALKING_SPEED, WALKING_SPEED);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination location.
	 * @param animation The animation.
	 * @param direction The direction.
	 * @param speed The speed (in ticks).
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location start, Location destination, Animation startAnim, Animation animation, Direction direction) {
		return run(e, start, destination, startAnim, animation, direction, WALKING_SPEED, WALKING_SPEED);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination location.
	 * @param animation The animation.
	 * @param direction The direction.
	 * @param pathSpeed The speed (in ticks).
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location start, Location destination, Animation startAnim, Animation animation, Direction direction, int pathSpeed) {
		return run(e, start, destination, startAnim, animation, direction, WALKING_SPEED, pathSpeed);
	}

	/**
	 * Creates and runs a new force movement pulse.
	 * @param e The entity.
	 * @param start The start location.
	 * @param destination The destination location.
	 * @param animation The animation.
	 * @param direction The direction.
	 * @param speed The speed (in ticks).
	 * @return The created ForceMovement object.
	 */
	public static ForceMovement run(Entity e, Location start, Location destination, Animation startAnim, Animation animation, Direction direction, int commenceSpeed, int pathSpeed) {
		if (startAnim != null) {
			startAnim.setPriority(Animator.Priority.VERY_HIGH);
		}
		if (animation != null) {
			animation.setPriority(Animator.Priority.VERY_HIGH);
		}
		ForceMovement fm = new ForceMovement(e, start, destination, startAnim, animation, direction, commenceSpeed, pathSpeed);
		fm.start();
		GameWorld.Pulser.submit(fm);
		return fm;
	}

	/**
	 * Method used to run the force movement.
	 * @param e the entity.
	 */
	public void run(final Entity e, final int speed) {
		this.entity = e;
		int commence = (int) start.getDistance(e.getLocation());
		if (commence != 0 && commenceSpeed != 0) {
			commence = (int) (1 + (commence / (commenceSpeed * 0.1)));
		}
		int path = 1 + (int) Math.ceil(start.getDistance(destination) / (pathSpeed * 0.1));
		this.pathSpeed = pathSpeed == 0 ? path : speed;
		this.commenceSpeed = commence;
		start();
		GameWorld.Pulser.submit(this);
	}

	/**
	 * Method used to run the movement.
	 * @param e the entity.
	 */
	public void run(final Entity e) {
		run(e, 0);
	}

	/**
	 * Method used to run the movement.
	 */
	public void run() {
		run(entity);
	}

	/**
	 * Gets the direction value.
	 * @param s The start location.
	 * @param d The destination.
	 * @return The direction object.
	 */
	public static Direction direction(Location s, Location d) {
		Location delta = Location.getDelta(s, d);
		int x = Math.abs(delta.getX());
		int y = Math.abs(delta.getY());
		if (x > y) {
			return Direction.getDirection(delta.getX(), 0);
		}
		return Direction.getDirection(0, delta.getY());
	}

	@Override
	public void start() {
		commenceSpeed = (int) Math.ceil(start.getDistance(entity.getLocation()) / (commenceSpeed * 0.1));
		pathSpeed = (int) Math.ceil(start.getDistance(destination) / (pathSpeed * 0.1));
		if (commenceSpeed != 0) {
			entity.animate(startAnim);
			super.setDelay(commenceSpeed);
		} else {
			entity.animate(animation);
			super.setDelay(pathSpeed);
		}
		int ticks = 1 + commenceSpeed + pathSpeed;
		entity.getImpactHandler().setDisabledTicks(ticks);
		entity.lock(ticks);
		entity.getUpdateMasks().register(new ForceMovementFlag(this));
		entity.getWalkingQueue().updateRegion(destination, false);
		super.start();
	}

	@Override
	public boolean pulse() {
		if (commenceSpeed != 0) {
			entity.animate(animation);
			setDelay(pathSpeed);
			commenceSpeed = 0;
			entity.getProperties().setTeleportLocation(start);
			return false;
		}
		return true;
	}

	@Override
	public void stop() {
		super.stop();
		entity.getProperties().setTeleportLocation(destination);
		if (endAnimation != null) {
			entity.animate(endAnimation);
		}
	}

	/**
	 * Gets the start.
	 * @return The start.
	 */
	public Location getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 * @param start The start to set.
	 */
	public void setStart(Location start) {
		this.start = start;
	}

	/**
	 * Gets the destination.
	 * @return The destination.
	 */
	public Location getDestination() {
		return destination;
	}

	/**
	 * Sets the destination.
	 * @param destination The destination to set.
	 */
	public void setDestination(Location destination) {
		this.destination = destination;
	}

	/**
	 * Gets the direction.
	 * @return The direction.
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the direction.
	 * @param direction The direction to set.
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Gets the commenceSpeed.
	 * @return The commenceSpeed.
	 */
	public int getCommenceSpeed() {
		return commenceSpeed;
	}

	/**
	 * Sets the commenceSpeed.
	 * @param commenceSpeed The commenceSpeed to set.
	 */
	public void setCommenceSpeed(int commenceSpeed) {
		this.commenceSpeed = commenceSpeed;
	}

	/**
	 * Sets the entity.
	 * @param entity the entity.
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * Gets the pathSpeed.
	 * @return The pathSpeed.
	 */
	public int getPathSpeed() {
		return pathSpeed;
	}

	/**
	 * Sets the pathSpeed.
	 * @param pathSpeed The pathSpeed to set.
	 */
	public void setPathSpeed(int pathSpeed) {
		this.pathSpeed = pathSpeed;
	}

	/**
	 * Gets the entity.
	 * @return The entity.
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * Gets the startAnim.
	 * @return The startAnim.
	 */
	public Animation getStartAnim() {
		return startAnim;
	}

	/**
	 * Sets the startAnim.
	 * @param startAnim The startAnim to set.
	 */
	public void setStartAnim(Animation startAnim) {
		this.startAnim = startAnim;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Sets the animation.
	 * @param animation The animation to set.
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * Gets the endAnimation.
	 * @return The endAnimation.
	 */
	public Animation getEndAnimation() {
		return endAnimation;
	}

	/**
	 * Sets the endAnimation.
	 * @param endAnimation The endAnimation to set.
	 */
	public void setEndAnimation(Animation endAnimation) {
		this.endAnimation = endAnimation;
	}
}