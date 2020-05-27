package ms.net.event;

import java.nio.ByteBuffer;

import ms.net.IoSession;
import ms.net.IoWriteEvent;
import ms.net.packet.IoBuffer;

/**
 * Handles world packet writing events.
 * @author Emperor
 *
 */
public final class PacketWriteEvent extends IoWriteEvent {

	
	/**
	 * Constructs a new {@code PacketWriteEvent} {@code Object}.
	 * @param session The I/O session.
	 * @param context The packet context.
	 */
	public PacketWriteEvent(IoSession session, Object context) {
		super(session, context);
	}

	@Override
	public void write(IoSession session, Object context) {
		IoBuffer b = (IoBuffer) context;
		int size = b.toByteBuffer().position();
		ByteBuffer buffer = ByteBuffer.allocate(1 + size + b.getHeader().ordinal());
		buffer.put((byte) b.opcode());
		switch (b.getHeader()) {
		case NORMAL:
			break;
		case BYTE:
			buffer.put((byte) size);
			break;
		case SHORT:
			buffer.putShort((short) size);
			break;
		}
		buffer.put((ByteBuffer) b.toByteBuffer().flip());
		session.queue((ByteBuffer) buffer.flip());
	}

}