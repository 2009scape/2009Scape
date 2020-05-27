package ms.net.event;

import java.nio.ByteBuffer;

import ms.net.IoReadEvent;
import ms.net.IoSession;
import ms.net.packet.WorldPacketRepository;

/**
 * Handles world packet reading events.
 * @author Emperor
 *
 */
public final class PacketReadEvent extends IoReadEvent {
	
	/**
	 * The packet sizes.
	 */
	private static final int[] PACKET_SIZE = {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1
	};
	
	/**
	 * Constructs a new {@code PacketReadEvent} {@code Object}.
	 * @param session The I/O session.
	 * @param buffer The buffer to read from.
	 */
	public PacketReadEvent(IoSession session, ByteBuffer buffer) {
		super(session, buffer);
	}

	@Override
	public void read(IoSession session, ByteBuffer buffer) {
		int last = -1;
		while (buffer.hasRemaining()) {
			int opcode = buffer.get() & 0xFF;
			if (opcode >= PACKET_SIZE.length) {
				break;
			}
			int header = PACKET_SIZE[opcode];
			int size = header;
			if (header < 0) {
				size = getPacketSize(buffer, opcode, header, last);
			}
			if (size == -1) {
				break;
			}
			if (buffer.remaining() < size) {
				switch (header) {
				case -2:
					queueBuffer(opcode, size >> 8, size);
					break;
				case -1:
					queueBuffer(opcode, size);
					break;
				default:
					queueBuffer(opcode);
					break;
				}
				break;
			}
			byte[] data = new byte[size];
			buffer.get(data);
			last = opcode;
			try {
				WorldPacketRepository.handleIncoming(session, opcode, ByteBuffer.wrap(data));
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	/**
	 * Gets the packet size for the given opcode.
	 * @param buffer The buffer.
	 * @param opcode The opcode.
	 * @param header The packet header.
	 * @param last The last opcode.
	 * @return The packet size.
	 */
	private int getPacketSize(ByteBuffer buffer, int opcode, int header, int last) {
		if (header == -1) {
			if (buffer.remaining() < 1) {
				queueBuffer(opcode);
				return -1;
			}
			return buffer.get() & 0xFF;
		}
		if (header == -2) {
			if (buffer.remaining() < 2) {
				queueBuffer(opcode);
				return -1;
			}
			return buffer.getShort() & 0xFFFF;
		}
		System.err.println("Invalid packet [opcode=" + opcode + ", last=" + last + ", queued=" + usedQueuedBuffer + "]!");
		return -1;
	}

}