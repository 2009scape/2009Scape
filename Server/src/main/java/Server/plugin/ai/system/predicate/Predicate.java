package plugin.ai.system.predicate;

import javax.security.auth.Destroyable;

import plugin.ai.system.GlobalAIManager;

/**
 * An android predicate.
 * 
 * @author Viper
 * @version 1.0 - 13/07/2016
 */
public abstract class Predicate implements Cloneable, Runnable, Destroyable {
	
	protected GlobalAIManager bot;
	
	/**
	 * Predicate started.
	 */
	private boolean started;
	
	/**
	 * The predicate priority factor.
	 */
	private byte priority;
	
	/**
	 * The predicate type.
	 */
	public abstract PredicateType getType();
	
	/**
	 * Constructs a new {@link Predicate} {@code Object}.
	 * 
	 * @param priority
	 * 			The priority of this predicate.
	 */
	public Predicate(int priority) {
		this.priority = (byte) priority;
	}
	
	public Predicate clone() {
		final Predicate pred = this;
		return new Predicate(priority) {
			
			@Override
			public void run() {
				pred.run();
			}
			
			@Override
			public PredicateType getType() {
				return pred.getType();
			}
		}.setStarted(pred.started);
	}
	
	/**
	 * @return the priority
	 */
	public byte getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(byte priority) {
		this.priority = priority;
	}

	/**
	 * @return the bot
	 */
	public GlobalAIManager getBot() {
		return bot;
	}

	/**
	 * @param bot the bot to set
	 */
	public Predicate setBot(GlobalAIManager bot) {
		this.bot = bot;
		return this;
	}

	/**
	 * @return the started
	 */
	public boolean hasStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public Predicate setStarted(boolean started) {
		this.started = started;
		return this;
	}
	
}