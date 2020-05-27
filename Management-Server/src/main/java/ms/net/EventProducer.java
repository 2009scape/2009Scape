package ms.net;

import java.nio.ByteBuffer;

/**
 * Used for producing I/O events.
 * @author Emperor
 * 
 */
public interface EventProducer {

	/**
	 * Produces a new read event.
	 * @param session The session.
	 * @param buffer The buffer to read.
	 * @return The read event handler.
	 */
	IoReadEvent produceReader(IoSession session, ByteBuffer buffer);

	/**
	 * Produces a new writing event.
	 * @param session The session.
	 * @param context The context.
	 * @return The write event handler.
	 */
	IoWriteEvent produceWriter(IoSession session, Object context);
	
}