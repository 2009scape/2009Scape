package org.crandor.net.event;

import org.crandor.game.system.SystemLogger;
import org.crandor.game.world.GameWorld;
import org.crandor.net.IoReadEvent;
import org.crandor.net.IoSession;
import org.crandor.net.packet.IncomingPacket;
import org.crandor.net.packet.IoBuffer;
import org.crandor.net.packet.PacketRepository;

import java.nio.ByteBuffer;

/**
 * Handles game packet reading.
 * @author Emperor
 */
public final class GameReadEvent extends IoReadEvent {

	/**
	 * The incoming packet sizes, sorted by opcode.
	 */
	public static final int[] PACKET_SIZES = { 
			-3, -3, -3, 2, 2, -3, 8, -3, -3, 6, // 0-9
			4, -3, -3, -3, -3, -3, -3, 0, -3, -3, // 10-19
			4, 4, 1, 4, -3, -3, -3, 16, -3, -3, // 20-29
			2, -3, -3, 6, 8, -3, -3, -3, -3, -1, // 30-39
			-3, -3, -3, -3, -1, -3, -3, -3, 6, -3, // 40-49
			-3, -3, -3, 6, -3, 8, -3, 8, -3, -3, // 50-59
			-3, -3, -3, -3, 6, -1, 6, -3, 2, -3, // 60-69
			-3, 2, 2, 12, -3, 6, -3, -1, 2, 12, // 70-79
			-3, 8, 12, -3, 6, 8, -3, -3, -3, -3, // 80-89
			-3, -3, 2, 0, 2, -3, -3, -3, 4, 10, // 90-99
			-3, 14, -3, -3, 8, -3, 2, -3, -3, 6, // 100-109
			0, 2, -3, -3, 2, 10, -3, -1, -3, -3, // 110-119
			8, -3, -3, -1, 6, -3, -3, -3, -3, -3, // 120-129
			-3, 10, 6, 2, 14, 8, -3, 4, -3, -3, // 130-139
			-3, -3, -3, -3, -3, -3, -3, -3, 2, -3, // 140-149
			-3, -3, -3, 8, 8, 6, 8, 3, -3, -3, // 150-159
			-3, 8, 8, -3, -3, -3, 6, -1, 6, -3, // 160-169
			6, -3, -3, -3, -3, 2, -3, 2, -1, 4, // 170-179
			2, -3, -3, -3, 0, -3, -3, -3, 9, -3, // 180-189
			-3, -3, -3, -3, 6, 8, 6, -3, -3, 6, // 190-199
			-3, -1, -3, -3, -3, -3, 8, -3, -3, -3, // 200-209
			-3, -3, -3, 8, -3, -1, -3, -3, 2, -3, // 210-219
			-3, -3, -3, -3, -3, -3, -3, -3, 6, -3, // 220-229
			-3, 9, -3, 12, 6, -3, -3, -1, -3, 8, // 230-239
			-3, -3, -3, 6, 8, 0, -3, 6, 10, -3, // 240-249
			-3, -3, -3, 14, 6, -3 // 250-255
	};

	/**
	 * Constructs a new {@code GameReadEvent}.
	 * @param session The session.
	 * @param buffer The buffer to read from.
	 */
	public GameReadEvent(IoSession session, ByteBuffer buffer) {
		super(session, buffer);
	}

	@Override
	public void read(IoSession session, ByteBuffer buffer) {
		int last = -1;
		while (buffer.hasRemaining()) {
			int opcode = buffer.get() & 0xFF;
			if (session == null || session.getPlayer() == null) {
				continue;
			}
			if (opcode >= PACKET_SIZES.length) {
				break;
			}
			int header = PACKET_SIZES[opcode];
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
			IoBuffer buf = new IoBuffer(opcode, null, ByteBuffer.wrap(data));
			IncomingPacket packet = PacketRepository.getIncoming(opcode);
			session.setLastPing(System.currentTimeMillis());
			if (packet == null) {
				if (GameWorld.getSettings().isDevMode()) {
					SystemLogger.log("Unhandled packet [opcode=" + opcode + ", previous=" + last + ", size=" + size + ", header=" + header + "]");
				}
				continue;
			}
			last = opcode;
			try {
				packet.decode(session.getPlayer(), opcode, buf);
				//System.out.println("Handled packed " + opcode + "!");
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
		System.err.println("Invalid packet [opcode=" + opcode + ", last=" + last + ", queued=" + usedQueuedBuffer + "], header=" + header+"!");
		return -1;
	}

}