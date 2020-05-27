package ms.net;

import java.nio.channels.CancelledKeyException;

/**
 * Handles a writing event.
 * @author Emperor
 */
public abstract class IoWriteEvent implements Runnable {
	
	/**
	 * The I/O session.
	 */
	private final IoSession session;
	
	/**
	 * The buffer.
	 */
	private final Object context;
	
	/**
	 * Constructs a new {@code IoWriteEvent}.
	 * @param session The session.
	 * @param context The write event context.
	 */
	public IoWriteEvent(IoSession session, Object context) {
		this.session = session;
		this.context = context;
	}
	
	@Override
	public void run() {
		try {
			write(session, context);
		} catch (Throwable t) {
			if (!(t instanceof CancelledKeyException)) {
				t.printStackTrace();
			}
			session.disconnect();
		}
	}

	/**
	 * Writes the data.
	 * @param session The session.
	 * @param context The write event context.
	 */
	public abstract void write(IoSession session, Object context);
	
}