package ms.net;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ms.ServerConstants;
import ms.net.producer.HSEventProducer;
import ms.world.GameServer;

/**
 * Represents a connected I/O session.
 * @author Emperor
 * 
 */
public class IoSession {
	
	/**
	 * The handshake event producer.
	 */
	private static final EventProducer HANDSHAKE_PRODUCER = new HSEventProducer();

	/**
	 * The selection key.
	 */
	private final SelectionKey key;
	
	/**
	 * The executor service.
	 */
	private final ExecutorService service;
	
	/**
	 * The event producer.
	 */
	private EventProducer producer = HANDSHAKE_PRODUCER;
	
	/**
	 * The currently queued writing data.
	 */
	private List<ByteBuffer> writingQueue = new ArrayList<>();
	
	/**
	 * The currently queued reading data.
	 */
	private ByteBuffer readingQueue;
	
	/**
	 * The writing lock.
	 */
	private Lock writingLock = new ReentrantLock();
	
	/**
	 * The name hash.
	 */
	private int nameHash;
	
	/**
	 * The server key.
	 */
	private long serverKey;
	
	/**
	 * The JS-5 encryption value.
	 */
	private int js5Encryption;
	
	/**
	 * If the session is active.
	 */
	private boolean active = true;
	
	/**
	 * The last ping time stamp.
	 */
	private long lastPing;
	
	/**
	 * The address.
	 */
	private final String address;
	
	/**
	 * The game server object for this session.
	 */
	private GameServer gameServer;
	
	/**
	 * Constructs a new {@code IoSession}.
	 * @param key The selection key.
	 * @param service The executor service.
	 */
	public IoSession(SelectionKey key, ExecutorService service) {
		this.key = key;
		this.service = service;
		String address = getRemoteAddress().replaceAll("/", "").split(":")[0];
		if (address.equals("127.0.0.1")) {
			address = ServerConstants.HOST_ADDRESS;
		}
		this.address = address;
	}

	/**
	 * Fires a write event created using the current event producer.
	 * @param context The event context.
	 */
	public void write(Object context) {
		write(context, false);
	}
	
	/**
	 * Fires a write event created using the current event producer.
	 * @param context The event context.
	 * @param instant If the event should be instantly executed on this thread.
	 */
	public void write(Object context, boolean instant) {
		if (context == null) {
			throw new IllegalStateException("Invalid writing context!");
		}
		if (instant) {
			producer.produceWriter(this, context).run();
			return;
		}
		service.execute(producer.produceWriter(this, context));
	}
	
	/**
	 * Sends the packet data (without write event encoding).
	 * @param buffer The buffer.
	 */
	public void queue(ByteBuffer buffer) {
		try {
			writingLock.tryLock(1000L, TimeUnit.MILLISECONDS);
		} catch (Exception e){
			System.out.println(e);
			writingLock.unlock();
			return;
		}
		writingQueue.add(buffer);
		writingLock.unlock();
		write();
	}
	
	/**
	 * Handles the writing of all buffers in the queue.
	 */
	public void write() {
		if (!key.isValid()) {
			disconnect();
			return;
		}
		writingLock.lock();
		SocketChannel channel = (SocketChannel) key.channel();
		try {
			while (!writingQueue.isEmpty()) {
				ByteBuffer buffer = writingQueue.get(0);
				channel.write(buffer);
				if (buffer.hasRemaining()) {
					key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
					break;
				}
				writingQueue.remove(0);
			}
		} catch (IOException e) {
			disconnect();
		}
		writingLock.unlock();
	}

	/**
	 * Disconnects the session.
	 */
	public void disconnect() {
		try {
			if (!active) {
				return;
			}
			active = false;
			key.cancel();
			SocketChannel channel = (SocketChannel) key.channel();
			channel.socket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the IP-address (without the port).
	 * @return The address.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Gets the remote address of this session.
	 * @return The remote address, as a String.
	 */
	public String getRemoteAddress() {
		try {
			return ((SocketChannel) key.channel()).getRemoteAddress().toString();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * Gets the current event producer.
	 * @return The producer.
	 */
	public EventProducer getProducer() {
		return producer;
	}

	/**
	 * Sets the event producer.
	 * @param producer The producer to set.
	 */
	public void setProducer(EventProducer producer) {
		this.producer = producer;
	}

	/**
	 * Gets the queued reading data.
	 * @return The readingQueue.
	 */
	public ByteBuffer getReadingQueue() {
		synchronized (this) {
			return readingQueue;
		}
	}

	/**
	 * Queues reading data.
	 * @param readingQueue The readingQueue to set.
	 */
	public void setReadingQueue(ByteBuffer readingQueue) {
		synchronized (this) {
			this.readingQueue = readingQueue;
		}
	}
	
	/**
	 * Gets the writing lock.
	 * @return The writing lock.
	 */
	public Lock getWritingLock() {
		return writingLock;
	}

	/**
	 * Gets the selection key.
	 * @return The selection key.
	 */
	public SelectionKey getKey() {
		return key;
	}

	/**
	 * @return The active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return The js5Encryption.
	 */
	public int getJs5Encryption() {
		return js5Encryption;
	}

	/**
	 * @param js5Encryption The js5Encryption to set.
	 */
	public void setJs5Encryption(int js5Encryption) {
		this.js5Encryption = js5Encryption;
	}
	
	/**
	 * Gets the lastPing.
	 * @return The lastPing.
	 */
	public long getLastPing() {
		return lastPing;
	}

	/**
	 * Sets the lastPing.
	 * @param lastPing The lastPing to set.
	 */
	public void setLastPing(long lastPing) {
		this.lastPing = lastPing;
	}

	/**
	 * Gets the nameHash.
	 * @return The nameHash.
	 */
	public int getNameHash() {
		return nameHash;
	}

	/**
	 * Sets the nameHash.
	 * @param nameHash The nameHash to set.
	 */
	public void setNameHash(int nameHash) {
		this.nameHash = nameHash;
	}

	/**
	 * Gets the serverKey.
	 * @return The serverKey.
	 */
	public long getServerKey() {
		return serverKey;
	}

	/**
	 * Sets the serverKey.
	 * @param serverKey The serverKey to set.
	 */
	public void setServerKey(long serverKey) {
		this.serverKey = serverKey;
	}

	/**
	 * Gets the gameServer value.
	 * @return The gameServer.
	 */
	public GameServer getGameServer() {
		return gameServer;
	}

	/**
	 * Sets the gameServer value.
	 * @param gameServer The gameServer to set.
	 */
	public void setGameServer(GameServer gameServer) {
		this.gameServer = gameServer;
	}

}