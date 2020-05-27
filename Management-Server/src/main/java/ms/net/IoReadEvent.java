package ms.net;

import java.nio.ByteBuffer;

/**
 * Handles a reading event.
 * @author Emperor
 * 
 */
public abstract class IoReadEvent implements Runnable {
	
	/**
	 * The I/O session.
	 */
	private final IoSession session;
	
	/**
	 * The buffer.
	 */
	private ByteBuffer buffer;
	
	/**
	 * If the queued reading buffer was used (debugging purposes).
	 */
	protected boolean usedQueuedBuffer;
	
	/**
	 * Constructs a new {@code IoReadEvent}.
	 * @param session The session.
	 * @param buffer The buffer to read from.
	 */
	public IoReadEvent(IoSession session, ByteBuffer buffer) {
		this.session = session;
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		try {
			if (session.getReadingQueue() != null) {
				buffer = session.getReadingQueue().put(buffer);
				buffer.flip();
				session.setReadingQueue(null);
				usedQueuedBuffer = true;
			}
			read(session, buffer);
		} catch (Throwable t) {
			t.printStackTrace();
			session.disconnect();
		}
	}
	
	/**
	 * Queues the buffer until more data has been received.
	 * @param data The data that has been read already.
	 */
	public void queueBuffer(int...data) {
		ByteBuffer queue = ByteBuffer.allocate(data.length + buffer.remaining() + 100_000);
		for (int value : data) {
			queue.put((byte) value);
		}
		queue.put(buffer);
		session.setReadingQueue(queue);
	}

	/**
	 * Reads the data from the buffer.
	 * @param session The session.
	 * @param buffer The buffer to read from.
	 */
	public abstract void read(IoSession session, ByteBuffer buffer);
	
}
