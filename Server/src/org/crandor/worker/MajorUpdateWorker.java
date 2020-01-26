package org.crandor.worker;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.crandor.game.system.SystemManager;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.UpdateSequence;
import org.crandor.gui.tab.StatisticsTab;

/**
 * The major update worker, this handles the game updating etc.
 * @author Emperor
 */
public final class MajorUpdateWorker implements Runnable {

	/**
	 * The updating sequence to use.
	 */
	private final UpdateSequence sequence = new UpdateSequence();

	/**
	 * The executor.
	 */
	private final Executor EXECUTOR = Executors.newSingleThreadExecutor();

	/**
	 * The start time of a cycle.
	 */
	private long start;

	/**
	 * If the major update worker has started.
	 */
	private boolean started;

	/**
	 * Constructs a new {@code MajorUpdateWorker} {@code Object}.
	 */
	public MajorUpdateWorker() {
		/*
		 * Empty.
		 */
	}

	/**
	 * Starts the update worker.
	 */
	public void start() {
		if (started) {
			return;
		}
		started = true;
		EXECUTOR.execute(MajorUpdateWorker.this);
	}

	@Override
	public void run() {
		while (SystemManager.isActive()) {
			try {
				start = System.currentTimeMillis();
				GameWorld.pulse();
				sequence.start();
				sequence.run();
				sequence.end();
				Repository.getDisconnectionQueue().update();
				sleep();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		started = false;
	}

	/**
	 * Lets the current thread sleep.
	 * @throws InterruptedException When the thread is interrupted.
	 */
	private void sleep() throws InterruptedException {
		// How many ticks off we are
		final int TICK_SPEED = (int) (600 / 1); //Allows you to make server ticks faster than normal
		StatisticsTab.reportPerformance((int) (System.currentTimeMillis() - start));
		long duration = TICK_SPEED - ((System.currentTimeMillis() - start) % TICK_SPEED);
		if (duration > 0) {
			Thread.sleep(duration);
		} else {
			System.err.println("Updating cycle duration took " + -duration + "ms too long!");
		}
	}

	/**
	 * Gets the started.
	 * @return The started.
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * Sets the started.
	 * @param started The started to set.
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}
}