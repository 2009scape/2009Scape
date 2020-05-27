package ms.system.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * A class holding methods to execute tasks.
 * @author Emperor
 *
 */
public final class TaskExecutor {

	/**
	 * The executor to use.
	 */
	private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

	/**
	 * The SQL task executor.
	 */
	private static final ScheduledExecutorService SQL_EXECUTOR = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * Constructs a new {@code TaskExecutor} {@code Object}.
	 */
	private TaskExecutor() {
		/*
		 * empty.
		 */
	}
	
	/**
	 * Executes an SQL handling task.
	 * @param task The task.
	 */
	public static void executeSQL(Runnable task) {
		SQL_EXECUTOR.execute(task);
	}
	
	/**
	 * Executes the task.
	 * @param task The task to execute.
	 */
	public static void execute(Runnable task) {
		EXECUTOR.execute(task);
	}
	
	/**
	 * Gets the executor.
	 * @return The executor.
	 */
	public static ScheduledExecutorService getExecutor() {
		return EXECUTOR;
	}
}