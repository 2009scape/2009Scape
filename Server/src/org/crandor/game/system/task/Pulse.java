package org.crandor.game.system.task;

import org.crandor.game.node.Node;
import org.crandor.game.world.GameWorld;

/**
 * Represents a pulse object (a task executed once every 600ms on the
 * MajorUpdateWorker thread).
 * @author Emperor
 */
public abstract class Pulse implements Runnable {

	/**
	 * If the task is still running.
	 */
	private boolean running = true;

	/**
	 * The amount of game-ticks to wait before execution.
	 */
	private int delay;

	/**
	 * The amount of ticks passed.
	 */
	int ticksPassed;

	/**
	 * The nodes that have to be active for the pulse to continue.
	 */
	protected Node[] checks;

	/**
	 * Constructs a new {@code Pulse} {@code Object}.
	 */
	public Pulse() {
		this(1);
	}

	/**
	 * Constructs a new {@code Pulse} object.
	 * @param delay The delay.
	 */
	public Pulse(int delay) {
		this.delay = delay;
	}

	/**
	 * Constructs a new {@code Pulse} object.
	 * @param delay The delay.
	 * @param checks The nodes that have to be active for the pulse to continue.
	 */
	public Pulse(int delay, Node... checks) {
		this.delay = delay;
		this.checks = checks;
	}

	@Override
	public void run() {
		if(update()){
			GameWorld.TASKS.remove(this);
		}
	}

	/**s
	 * Called when the world pulses, once every 600ms.
	 * @return {@code True} if this {@code Pulse} is finished and can be removed,
	 * {@code false} if not.
	 */
	public boolean update() {
		if (hasInactiveNode()) {
			stop();
			return true;
		}
		if (!isRunning()) {
			return true;
		}
		if (++ticksPassed >= delay) {
			ticksPassed = 0;
			if (pulse()) {
				stop();
				return true;
			}
			return !isRunning();
		}
		return false;
	}

	/**
	 * Checks if one of the node checks is inactive.
	 * @return {@code True} if so.
	 */
	public boolean hasInactiveNode() {
		if (checks != null) {
			int size = checks.length;
			for (int i = 0; i < size; i++) {
				Node n = checks[i];
				if (n != null && !n.isActive()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Called after the delay has passed.
	 * @return {@code True} if the pulse has finished and should be removed.
	 */
	public abstract boolean pulse();

	/**
	 * Checks if this pulse should be terminated so the new pulse (represented
	 * in string format) can run.
	 * @param pulse The pulse to run in string format.
	 * @return {@code True} if this pulse should be removed (default).
	 */
	public boolean removeFor(String pulse) {
		return true;
	}

	/**
	 * Adds a node check.
	 * @param index The index.
	 * @param n The node.
	 */
	public void addNodeCheck(int index, Node n) {
		checks[index] = n;
	}

	/**
	 * Gets a node check.
	 * @param index The index.
	 * @return The node.
	 */
	public Node getNodeCheck(int index) {
		return checks[index];
	}

	/**
	 * Manually stop the {@code Pulse} task.
	 */
	public void stop() {
		running = false;
	}

	/**
	 * Manually start the {@code Pulse} task.
	 */
	public void start() {
		running = true;
	}

	/**
	 * Restarts the pulse delay.
	 */
	public void restart() {
		ticksPassed = 0;
	}

	/**
	 * Checks if the pulse is still running.
	 * @return {@code True} if the pulse is still running.
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Gets the delay of this {@code Pulse}.
	 * @return The delay.
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * Sets the delay.
	 * @param delay The delay.
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/**
	 * Sets the amount of ticks passed.
	 * @param ticks The amount of ticks passed in this pulse.
	 */
	public void setTicksPassed(int ticks) {
		this.ticksPassed = ticks;
	}

	/**
	 * Gets the amount of ticks passed.
	 * @return The amount of ticks passed so far.
	 */
	public int getTicksPassed() {
		return ticksPassed;
	}
}