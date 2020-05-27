package ms.net.producer;

import java.nio.ByteBuffer;

import ms.net.EventProducer;
import ms.net.IoReadEvent;
import ms.net.IoSession;
import ms.net.IoWriteEvent;
import ms.net.event.HSReadEvent;
import ms.net.event.HSWriteEvent;

/**
 * Produces I/O events for the handshake protocol.
 * @author Emperor
 * 
 */
public final class HSEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new HSReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new HSWriteEvent(session, context);
	}
	
}