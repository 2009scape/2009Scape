package ms.net.producer;

import java.nio.ByteBuffer;

import ms.net.EventProducer;
import ms.net.IoReadEvent;
import ms.net.IoSession;
import ms.net.IoWriteEvent;
import ms.net.event.PacketReadEvent;
import ms.net.event.PacketWriteEvent;

/**
 * The packet event producer.
 * @author Emperor
 *
 */
public final class PacketEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new PacketReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new PacketWriteEvent(session, context);
	}

}