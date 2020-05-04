package org.crandor.game.node.entity.state;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;

import java.nio.ByteBuffer;

/**
 * Represents a state pulse.
 * @author Emperor
 */
public abstract class StatePulse extends Pulse {

	/**
	 * The entity.
	 */
	protected final Entity entity;

	/**
	 * Constructs a new {@code StatePulse} {@code Object}.
	 * @param entity The entity.
	 * @param ticks The amount of ticks.
	 */
	public StatePulse(Entity entity, int ticks) {
		super(ticks, entity);
		super.stop();
		this.entity = entity;
	}

	/**
	 * Checks if data has to be saved.
	 * @return {@code True} if so.
	 */
	public abstract boolean isSaveRequired();

	/**
	 * Saves the state data.
	 * @param buffer The buffer.
	 */
	public abstract void save(ByteBuffer buffer);

	/**
	 * Parses the state data.
	 * @param entity The entity.
	 * @param buffer The buffer.
	 * @return The state pulse created.
	 */
	public abstract StatePulse parse(Entity entity, ByteBuffer buffer);

	/**
	 * Creates a new instance of this state pulse.
	 * @param entity The entity.
	 * @param args The arguments.
	 * @return The state pulse.
	 */
	public abstract StatePulse create(Entity entity, Object... args);

	/**
	 * Checks if this pulse can be ran for the given entity.
	 * @param entity The entity.
	 * @return {@code True} if so.
	 */
	public boolean canRun(Entity entity) {
		return true;
	}

	/**
	 * Called when the pulse gets manually removed.
	 */
	public void remove() {
		/*
		 * empty.
		 */
	}

	/**
	 * Runs the pulse.
	 */
	public void run() {
		if (isRunning()) {
			return;
		}
		restart();
		start();
		GameWorld.Pulser.submit(this);
	}

}