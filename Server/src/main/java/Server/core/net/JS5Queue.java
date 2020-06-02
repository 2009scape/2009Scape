package core.net;

import core.game.system.task.TaskExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Handles the JS5 queue for a session.
 * @author Emperor
 */
public final class JS5Queue {

	/**
	 * The queued JS5 requests.
	 */
	private final Map<Integer, Boolean> queue = new HashMap<>();

	/**
	 * The I/O session.
	 */
	private final IoSession session;

	/**
	 * If the queue has been scheduled for release.
	 */
	private boolean scheduledRelease;

	/**
	 * The lock object.
	 */
	private Lock lock = new ReentrantLock();

	/**
	 * Constructs a new {@code JS5Queue} {@code Object}.
	 * @param session The I/O session.
	 */
	public JS5Queue(IoSession session) {
		this.session = session;
	}

	/**
	 * Queues a JS-5 request.
	 * @param container The container.
	 * @param archive The archive.
	 * @param highPriority If the request is high priority.
	 */
	public void queue(int container, int archive, boolean highPriority) {
		try {
			lock.tryLock(1000L, TimeUnit.MILLISECONDS);
		} catch (Exception e){
			System.out.println(e);
			lock.unlock();
			return;
		}
		int key = container << 16 | archive;
		if (queue.containsKey(key)) {
			// System.err.println("Queue already contained request " + container
			// + "," + archive + " > " + (container << 16 | archive) + ".");
		}
		queue.put(key, highPriority);
		lock.unlock();
		release();
	}

	/**
	 * Releases the queue.
	 */
	public void release() {
		lock.lock();
		if (!scheduledRelease) {
			scheduledRelease = true;
			TaskExecutor.getExecutor().schedule(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					for (Integer hash : queue.keySet()) {
						try {
							session.write(new int[] { hash >> 16 & 0xFF, hash & 0xFFFF, queue.get(hash) ? 1 : 0 });
						} catch (Throwable t) {
							t.printStackTrace();
						}
					}
					queue.clear();
					scheduledRelease = false;
					lock.unlock();
				}
			}, 100, TimeUnit.MILLISECONDS);
		}
		lock.unlock();
	}

	/**
	 * Gets the session.
	 * @return the session
	 */
	public IoSession getSession() {
		return session;
	}

	/**
	 * Gets the scheduledRelease.
	 * @return the scheduledRelease
	 */
	public boolean isScheduledRelease() {
		return scheduledRelease;
	}

	/**
	 * Sets the scheduledRelease.
	 * @param scheduledRelease the scheduledRelease to set.
	 */
	public void setScheduledRelease(boolean scheduledRelease) {
		this.scheduledRelease = scheduledRelease;
	}

}