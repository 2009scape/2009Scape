package org.crandor.game.node.entity.impl;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.Point;
import org.crandor.game.world.map.RegionManager;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The walking queue.
 * @author Emperor
 */
public final class WalkingQueue {

	/**
	 * The walking queue.
	 */
	private final Deque<Point> walkingQueue = new LinkedList<Point>();

	/**
	 * The entity.
	 */
	private final Entity entity;

	/**
	 * The current walking direction.
	 */
	private int walkDir = -1;

	/**
	 * The current running direction.
	 */
	private int runDir = -1;

	/**
	 * If the entity is running (set to true when holding the ctrl button +
	 * click).
	 */
	private boolean running = false;

	/**
	 * If running is disabled.
	 */
	private boolean runDisabled;

	/**
	 * The last location this entity walked on.
	 */
	private Location footPrint;

	/**
	 * Constructs a new {@code WalkingQueue} {@code Object}.
	 * @param entity The entity.
	 */
	public WalkingQueue(Entity entity) {
		this.entity = entity;
		this.footPrint = entity.getLocation();
	}

	/**
	 * Updates the walking queue.
	 */
	public void update() {
		boolean isPlayer = entity instanceof Player;
		this.walkDir = -1;
		this.runDir = -1;
		if (updateTeleport()) {
			return;
		}
		if (isPlayer && updateRegion(entity.getLocation(), true)) {
			return;
		}
		Point point = walkingQueue.poll();
		if (point == null) {
			updateRunEnergy(false);
			return;
		}
		if (isPlayer && ((Player) entity).getSettings().getRunEnergy() < 1.0) {
			running = false;
			((Player) entity).getSettings().setRunToggled(false);
		}
		Point runPoint = null;
		if (point.getDirection() == null) {
			point = walkingQueue.poll();
		}
		int walkDirection = -1;
		int runDirection = -1;
		if (isRunningBoth() && (point == null || !point.isRunDisabled())) {
			runPoint = walkingQueue.poll();
		}
		if (point != null) {
			if (point.getDirection() == null) {
				return;
			}
			walkDirection = point.getDirection().ordinal();
		}
		if (runPoint != null) {
			runDirection = runPoint.getDirection().ordinal();
		}
		int diffX = 0;
		int diffY = 0;
		if (walkDirection != -1) {
			diffX = point.getDiffX();
			diffY = point.getDiffY();
		}
		if (runDirection != -1) {
			footPrint = entity.getLocation().transform(diffX, diffY, 0);
			diffX += runPoint.getDiffX();
			diffY += runPoint.getDiffY();
			updateRunEnergy(true);
		} else {
			updateRunEnergy(false);
		}
		if (diffX != 0 || diffY != 0) {
			Location walk = entity.getLocation();
			if (point != null) {
				walk = walk.transform(point.getDiffX(), point.getDiffY(), 0);
				if (!entity.getZoneMonitor().move(entity.getLocation(), walk)) {
					reset();
					if (entity.getPulseManager().isMovingPulse()) {
						entity.getPulseManager().clear(); // TODO: Check for
						// bugs
					}
					return;
				}
			}
			Location dest = entity.getLocation().transform(diffX, diffY, 0);
			if (runPoint != null) {
				if (!entity.getZoneMonitor().move(walk, dest)) {
					dest = dest.transform(-runPoint.getDiffX(), -runPoint.getDiffY(), 0);
					runPoint = null;
					runDirection = -1;
					reset();
					if (entity.getPulseManager().isMovingPulse()) {
						entity.getPulseManager().clear(); // TODO: Check for
						// bugs
					}
				}
			}
			if (runPoint != null) {
				entity.setDirection(runPoint.getDirection());
			} else if (point != null) {
				entity.setDirection(point.getDirection());
			}
			footPrint = entity.getLocation();
			entity.setLocation(dest);
			RegionManager.move(entity);
		}
		this.walkDir = walkDirection;
		this.runDir = runDirection;
	}

	/**
	 * Gets the energy drain rate for this player.
	 * @param player The player.
	 * @return The energy drain rate.
	 */
	private double getEnergyDrainRate(Player player) {
		double rate = 0.55;
		if (player.getSettings().getWeight() > 0.0) {
			rate *= 1 + (player.getSettings().getWeight() / 100);
		}
		return rate;
	}

	/**
	 * Gets the energy restore amount.
	 * @param player The player.
	 * @return The amount to restore.
	 */
	private double getEnergyRestore(Player player) {
		double rate = 100 / ((175 - (player.getSkills().getLevel(Skills.AGILITY))) / 0.6);
		return rate;
	}

	/**
	 * Increases the current amount of run energy, and updates it.
	 * @param decrease If we should decrease the run energy.
	 */
	public void updateRunEnergy(boolean decrease) {
		if (!(entity instanceof Player)) {
			return;
		}
		Player p = (Player) entity;
		if (!decrease && p.getSettings().getRunEnergy() >= 100.0) {
			return;
		}
		double drain = decrease ? getEnergyDrainRate(p) : -getEnergyRestore(p);
		p.getSettings().updateRunEnergy(drain);
	}

	/**
	 * Checks if the player is teleporting, if so does the teleporting and
	 * returns true.
	 * @return {@code True} if the player is teleporting, {@code false} if not.
	 */
	public boolean updateTeleport() {
		if (entity.getProperties().getTeleportLocation() != null) {
			reset(false);
			entity.setLocation(entity.getProperties().getTeleportLocation());
			entity.getProperties().setTeleportLocation(null);
			if (entity instanceof Player) {
				Player p = (Player) entity;
				Location last = p.getPlayerFlags().getLastSceneGraph();
				if (last == null) {
					last = p.getLocation();
				}
				if ((last.getRegionX() - entity.getLocation().getRegionX()) >= 4 || (last.getRegionX() - entity.getLocation().getRegionX()) <= -4) {
					p.getPlayerFlags().setUpdateSceneGraph(true);
				} else if ((last.getRegionY() - entity.getLocation().getRegionY()) >= 4 || (last.getRegionY() - entity.getLocation().getRegionY()) <= -4) {
					p.getPlayerFlags().setUpdateSceneGraph(true);
				}
			}
			RegionManager.move(entity);
			footPrint = entity.getLocation();
			entity.getProperties().setTeleporting(true);
			return true;
		}
		return false;
	}

	/**
	 * Checks if the region should be updated, if so we set the update flag and
	 * return true.
	 * @return {@code True} if the region updated, {@code false} if not.
	 */
	public boolean updateRegion(Location location, boolean move) {
		Player p = (Player) entity;
		Location lastRegion = p.getPlayerFlags().getLastSceneGraph();
		if (lastRegion == null) {
			lastRegion = location;
		}
		int rx = lastRegion.getRegionX();
		int ry = lastRegion.getRegionY();
		int cx = location.getRegionX();
		int cy = location.getRegionY();
		if ((rx - cx) >= 4) {
			p.getPlayerFlags().setUpdateSceneGraph(true);
		} else if ((rx - cx) <= -4) {
			p.getPlayerFlags().setUpdateSceneGraph(true);
		}
		if ((ry - cy) >= 4) {
			p.getPlayerFlags().setUpdateSceneGraph(true);
		} else if ((ry - cy) <= -4) {
			p.getPlayerFlags().setUpdateSceneGraph(true);
		}
		if (move && p.getPlayerFlags().isUpdateSceneGraph()) {
			RegionManager.move(entity);
			return true;
		}
		return false;
	}

	/**
	 * Walks back to the last location.
	 */
	public void walkBack() {
		entity.getPulseManager().clear();
		reset();
		addPath(footPrint.getX(), footPrint.getY());
	}

	/**
	 * Adds a path to the walking queue.
	 * @param x The last x-coordinate of the path.
	 * @param y The last y-coordinate of the path.
	 */
	public void addPath(int x, int y) {
		addPath(x, y, runDisabled);
	}

	/**
	 * Adds a path to the walking queue.
	 * @param x The last x-coordinate of the path.
	 * @param y The last y-coordinate of the path.
	 * @param runDisabled If running is disabled for this walking path.
	 */
	public void addPath(int x, int y, boolean runDisabled) {
		Point point = walkingQueue.peekLast();
		if (point == null) {
			return;
		}
		int diffX = x - point.getX(), diffY = y - point.getY();
		int max = Math.max(Math.abs(diffX), Math.abs(diffY));
		for (int i = 0; i < max; i++) {
			if (diffX < 0) {
				diffX++;
			} else if (diffX > 0) {
				diffX--;
			}
			if (diffY < 0) {
				diffY++;
			} else if (diffY > 0) {
				diffY--;
			}
			addPoint(x - diffX, y - diffY, runDisabled);
		}
	}

	/**
	 * Adds a point to the walking queue.
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 */
	public void addPoint(int x, int y, boolean runDisabled) {
		Point point = walkingQueue.peekLast();
		if (point == null) {
			return;
		}
		int diffX = x - point.getX(), diffY = y - point.getY();
		Direction direction = Direction.getDirection(diffX, diffY);
		if (direction != null) {
			walkingQueue.add(new Point(x, y, direction, diffX, diffY, runDisabled));
		}
	}

	/**
	 * Checks if the entity is running.
	 * @return {@code True} if a ctrl + click reward was performed, <br> the
	 * player has the run option enabled or the NPC is a familiar, <p>
	 * {@code false} if not.
	 */
	public boolean isRunningBoth() {
		if (entity instanceof Player && ((Player) entity).getSettings().isRunToggled()) {
			return true;
		}
		return running;
	}

	/**
	 * Checks if the entity has a path to walk.
	 * @return {@code True} if so.
	 */
	public boolean hasPath() {
		if (!walkingQueue.isEmpty()) {
			Point p = walkingQueue.peek();
			return p.getDirection() != null;
		}
		return false;
	}

	/**
	 * Checks if the entity is moving.
	 * @return {@code True} if so.
	 */
	public boolean isMoving() {
		return walkDir != -1 || runDir != -1;
	}

	/**
	 * Resets the walking queue.
	 */
	public void reset() {
		reset(running);
	}

	/**
	 * Resets the walking queue.
	 * @param running The running flag (ctrl + click reward).
	 */
	public void reset(boolean running) {
		walkingQueue.clear();
		walkingQueue.add(new Point(entity.getLocation().getX(), entity.getLocation().getY()));
		this.running = running;
	}

	/**
	 * Gets the current walking direction.
	 * @return The walk direction.
	 */
	public int getWalkDir() {
		return walkDir;
	}

	/**
	 * Gets the current run direction.
	 * @return The run direction.
	 */
	public int getRunDir() {
		return runDir;
	}

	/**
	 * Sets the running flag.
	 * @param running The running flag.
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Checks if the player is running.
	 * @return {@code True} if so.
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @return the footPrint
	 */
	public Location getFootPrint() {
		return footPrint;
	}

	/**
	 * @param footPrint the footPrint to set
	 */
	public void setFootPrint(Location footPrint) {
		this.footPrint = footPrint;
	}

	/**
	 * Gets the walking queue.
	 * @return The queue.
	 */
	public Deque<Point> getQueue() {
		return walkingQueue;
	}

	/**
	 * Gets the runDisabled.
	 * @return The runDisabled.
	 */
	public boolean isRunDisabled() {
		return runDisabled;
	}

	/**
	 * Sets the runDisabled.
	 * @param runDisabled The runDisabled to set.
	 */
	public void setRunDisabled(boolean runDisabled) {
		this.runDisabled = runDisabled;
	}
}