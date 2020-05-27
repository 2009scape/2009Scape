package ms.system.util;

/**
 * Represents a command.
 * @author Emperor
 *
 */
public abstract class Command {

	/**
	 * The command name.
	 */
	private final String name;
	
	/**
	 * The command info.
	 */
	private final String info;
	
	/**
	 * Constructs a new {@code Command} {@code Object}.
	 * @param name The command name.
	 * @param info The command info.
	 */
	public Command(String name, String info) {
		this.name = name;
		this.info = info;
	}
	
	/**
	 * Runs the command.
	 * @param args The arguments.
	 */
	public abstract void run(String...args);

	/**
	 * Gets the name value.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the info value.
	 * @return The info.
	 */
	public String getInfo() {
		return info;
	}

}