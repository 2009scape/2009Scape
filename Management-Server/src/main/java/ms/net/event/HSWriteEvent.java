package ms.net.event;

import java.nio.ByteBuffer;

import ms.net.IoSession;
import ms.net.IoWriteEvent;
import ms.net.producer.RegistryEventProducer;

/**
 * Handles Handshake write events.
 * @author Emperor
 */
public final class HSWriteEvent extends IoWriteEvent {
	
	/**
	 * The login event producer.
	 */
	private static final RegistryEventProducer REGISTRY_PRODUCER = new RegistryEventProducer();
	
	/**
	 * Constructs a new {@code HSWriteEvent} {@code Object}.
	 * @param session The session.
	 * @param context The context.
	 */
	public HSWriteEvent(IoSession session, Object context) {
		super(session, context);
	}

	@Override
	public void write(IoSession session, Object context) {
		ByteBuffer buffer = ByteBuffer.allocate(9);
		buffer.put((byte) 14);
		session.setProducer( REGISTRY_PRODUCER);
		buffer.flip();
		session.queue(buffer);
	}

}