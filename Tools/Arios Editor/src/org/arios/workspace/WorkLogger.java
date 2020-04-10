package org.arios.workspace;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;

/**
 * Loggs the users work.
 * @author Vexia
 *
 */
public class WorkLogger extends PrintStream {

	/**
	 * The current time.
	 */
	private static final Date TIME = Calendar.getInstance().getTime();

	/**
	 * Constructs a new {@code WorkLogger} {@code Object}
	 * @param stream the stream.
	 */
	public WorkLogger(PrintStream stream) {
		super(stream);
	}

	@Override
	public void println(String message) {
		log(message);
	}

	@Override
	public PrintStream printf(String message, Object... objects) {return null;}

	@Override
	public void println(boolean message) {
		log(String.valueOf(message));
	}

	@Override
	public void println(int message) {
		log(String.valueOf(message));
	}

	@Override
	public void println(double message) {
		log(String.valueOf(message));
	}

	@Override
	public void println(char message) {}

	@Override
	public void println(long message) {}

	/**
	 * Method used to log the message.
	 * @param message the message.
	 */
	public void log(final String message) {
		WorkSpace.getWorkSpace().getFrame().log(getDisplay() + message);
	}

	/**
	 * Gets the display details.
	 * @return the string.
	 */
	public String getDisplay() {
		return "[" + TIME + "][" + "Arios" + "]: ";
	}
}
