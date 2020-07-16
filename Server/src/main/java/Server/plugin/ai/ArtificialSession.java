package plugin.ai;

import java.nio.ByteBuffer;

import core.net.IoSession;

/**
 * Represents an artificial networking session.
 * @author Emperor
 */
public final class ArtificialSession extends IoSession {

	/**
	 * The artificial session singleton.
	 */
	private static final ArtificialSession SINGLETON = new ArtificialSession();

	/**
	 * Constructs a new {@code ArtificialSession} {@code Object}.
	 */
	private ArtificialSession() {
		super(null, null);
	}

	@Override
	public String getRemoteAddress() {
		return "127.0.0.1";
	}

	@Override
	public void write(Object context, boolean instant) {
	}

	@Override
	public void queue(ByteBuffer buffer) {
	}

	@Override
	public void write() {
	}

	@Override
	public void disconnect() {
	}

	/**
	 * @return the singleton.
	 */
	public static ArtificialSession getSingleton() {
		return SINGLETON;
	}
}