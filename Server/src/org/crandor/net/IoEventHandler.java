package org.crandor.net;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ExecutorService;

/**
 * I/O event handling.
 * @author Emperor
 */
public class IoEventHandler {

	/**
	 * The executor service.
	 */
	protected final ExecutorService service;

	/**
	 * Constructs a new {@code IoEventHandler}.
	 * @param service The executor service used for handling events.
	 */
	public IoEventHandler(ExecutorService service) {
		this.service = service;
	}

	/**
	 * Called when making a new connection.
	 * @param key The selection key.
	 * @throws IOException When an I/O exception occurs.
	 */
	public void connect(SelectionKey key) throws IOException {
		/*
		 * empty.
		 */
	}

	/**
	 * Used for accepting a new connection.
	 * @param key The selection key.
	 * @param selector The selector.
	 * @throws IOException When an I/O exception occurs.
	 */
	public void accept(SelectionKey key, Selector selector) throws IOException {
		SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
		sc.configureBlocking(false);
		sc.socket().setTcpNoDelay(true);
		sc.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * Reads the incoming packet data.
	 * @param key The selection key.
	 * @throws IOException When an I/O exception occurs.
	 */
	public void read(SelectionKey key) throws IOException {
		ReadableByteChannel channel = (ReadableByteChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(100_000);
		IoSession session = (IoSession) key.attachment();
		if (channel.read(buffer) == -1) {
			//just when a client closes their client, nothing to worry about.
			throw new IOException("An existing connection was disconnected!");
		}
		buffer.flip();
		if (session == null) {
			key.attach(session = new IoSession(key, service));
		}
		service.execute(session.getProducer().produceReader(session, buffer));
	}

	/**
	 * Writes the outgoing packet data.
	 * @param key The selection key.
	 */
	public void write(SelectionKey key) {
		IoSession session = (IoSession) key.attachment();
		key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
		session.write();
	}

	/**
	 * Disconnects a connection.
	 * @param key The selection key.
	 * @param t The occurred exception (if any).
	 */
	public void disconnect(SelectionKey key, Throwable t) {
		try {
			IoSession session = (IoSession) key.attachment();
			String cause = "" + t;
			if (t != null && !(t instanceof ClosedChannelException || cause.contains("De externe host") || cause.contains("De software op uw") || cause.contains("An established connection was aborted") || cause.contains("An existing connection") || cause.contains("AsynchronousClose"))) {
				t.printStackTrace();
			}
			if (session != null) {
				session.disconnect();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}