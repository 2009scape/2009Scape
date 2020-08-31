package core.game.system;

import core.ServerConstants;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.repository.Repository;

import java.util.Iterator;
import java.util.concurrent.Executors;

/**
 * Handles a system update.
 * @author Emperor
 */
public final class SystemUpdate extends Pulse {

	/**
	 * The default countdown for an update, in ticks.
	 */
	public static final int DEFAULT_COUNTDOWN = 100;

	/**
	 * The amount of ticks left of when to create a backup.
	 */
	public static final int BACKUP_TICK = 10;

	/**
	 * If a backup should be created.
	 */
	private boolean createBackup = false;

	/**
	 * Constructs a new {@code SystemUpdate} {@code Object}.
	 */
	protected SystemUpdate() {
		super(DEFAULT_COUNTDOWN);
	}

	@Override
	public boolean pulse() {
		if (getDelay() >= BACKUP_TICK && createBackup) {
			try {
				SystemManager.getTerminator().save(ServerConstants.DATA_PATH);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			setDelay(BACKUP_TICK - 1);
			return false;
		}
		SystemManager.flag(SystemState.TERMINATED);
		return true;
	}

	/**
	 * Notifies the players.
	 */
	public void notifyPlayers() {
		try {
			int time = getDelay() + (createBackup ? BACKUP_TICK : 0);
			for (Iterator<Player> it = Repository.getPlayers().iterator(); it.hasNext();) {
				Player p = it.next();
				if (p != null) { // Should never be null.
					p.getPacketDispatch().sendSystemUpdate(time);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Schedules an update.
	 */
	public void schedule() {
		super.setTicksPassed(0);
		super.start();
		if (GameWorld.getMajorUpdateWorker().getStarted()) {
			notifyPlayers();
			GameWorld.Pulser.submit(this);
			return;
		}
		Executors.newSingleThreadExecutor().submit(new Runnable() {
			@Override
			public void run() {
				while (isRunning()) {
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (update()) {
						break;
					}
				}
			}
		});
	}

	/**
	 * Sets the system update countdown.
	 * @param ticks The amount of ticks.
	 */
	public void setCountdown(int ticks) {
		if (createBackup) {
			if (ticks < BACKUP_TICK) {
				ticks = BACKUP_TICK;
			}
			ticks -= BACKUP_TICK;
		}
		super.setDelay(ticks);
	}

	/**
	 * Cancels the system update task.
	 */
	public void cancel() {
		super.stop();
		SystemManager.flag(SystemState.ACTIVE);
	}

	/**
	 * Gets the createBackup.
	 * @return The createBackup.
	 */
	public boolean isCreateBackup() {
		return createBackup;
	}

	/**
	 * Sets the createBackup.
	 * @param createBackup The createBackup to set.
	 */
	public void setCreateBackup(boolean createBackup) {
		this.createBackup = createBackup;
	}
}