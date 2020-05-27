package ms.net;

import ms.Management;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Handles (NIO-based) networking events using the reactor pattern.
 * @author Emperor
 */
public final class NioReactor implements Runnable {
	
	/**
	 * The executor service.
	 */
	private final ExecutorService service;

	/**
	 * The selector
	 */
	private Selector selector;
	
	/**
	 * The socket channel.
	 */
	private ServerSocketChannel channel;
	
	/**
	 * The I/O event handling instance.
	 */
	private final IoEventHandler eventHandler;
	
	/**
	 * If the reactor is running.
	 */
	private boolean running;
	
	/**
	 * Constructs a new {@code NioReactor}.
	 * @param poolSize The pool size.
	 */
	private NioReactor(int poolSize) {
		this.service = Executors.newSingleThreadScheduledExecutor();
		this.eventHandler = new IoEventHandler(Executors.newFixedThreadPool(poolSize));
	}
	
	/**
	 * Creates and configures a new {@code NioReactor} with a pool size of 1.
	 * @param port The port.
	 * @return The {@code NioReactor} {@code Object}.
	 * @throws IOException When an I/O exception occurs.
	 */
	public static NioReactor configure(int port) throws IOException {
		return configure(port, 1);
	}

	/**
	 * Creates and configures a new {@code NioReactor}.
	 * @param port The port.
	 * @param poolSize The amount of threads in the thread pool.
	 * @return The {@code NioReactor} {@code Object}.
	 * @throws IOException When an I/O exception occurs.
	 */
	public static NioReactor configure(int port, int poolSize) throws IOException {
		NioReactor reactor = new NioReactor(poolSize);
		reactor.channel = ServerSocketChannel.open();
		reactor.selector = Selector.open();
		reactor.channel.bind(new InetSocketAddress(port));
		reactor.channel.configureBlocking(false);
		reactor.channel.register(reactor.selector, SelectionKey.OP_ACCEPT);
		return reactor;
	}
	
	/**
	 * Starts the reactor.
	 */
	public void start() {
		running = true;
		service.execute(this);
	}
	
	@Override
	public void run() {
		while (running && Management.active) {
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				try {
					if (!key.isValid() || !key.channel().isOpen()) {
						key.cancel();
						continue;
					}
					if (key.isAcceptable()) {
						eventHandler.accept(key, selector);
					}
					if (key.isReadable()) {
						eventHandler.read(key);
					}
					else if (key.isWritable()) {
						eventHandler.write(key);
					}
				} catch (Throwable t) {
					eventHandler.disconnect(key, t);
				}
			}
		}
	}
	
	/**
	 * Terminates the reactor (once it's done processing current I/O events).
	 */
	public void terminate() {
		running = false;
	}
	
}