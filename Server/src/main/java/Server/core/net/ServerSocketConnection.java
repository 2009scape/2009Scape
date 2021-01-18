package core.net;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Represents a server socket and its selector.
 * @author Emperor
 */
public final class ServerSocketConnection {

	/**
	 * The selector.
	 */
	private final Selector selector;

	/**
	 * The channel.
	 */
	private final ServerSocketChannel channel;

	/**
	 * The socket channel.
	 */
	private final SocketChannel socket;

	/**
	 * Constructs a new {@code ServerSocketConnection} {@code Object}.
	 * @param selector The selector.
	 * @param channel The channel.
     */
	public ServerSocketConnection(Selector selector, ServerSocketChannel channel) {
		this.selector = selector;
		this.channel = channel;
		this.socket = null;
	}

	/**
	 * Constructs a new {@Code ServerSocketConnection} {@Code
	 * Object}
	 * @param selector The selector.
	 * @param channel The channel.
     */
	public ServerSocketConnection(Selector selector, SocketChannel channel) {
		this.selector = selector;
		this.socket = channel;
		this.channel = null;
	}

	/**
	 * If the channel is used as client.
	 * @return {@code True} if so.
	 */
	public boolean isClient() {
		return socket != null;
	}

	/**
	 * Gets the selector.
	 * @return the selector.
	 */
	public Selector getSelector() {
		return selector;
	}

	/**
	 * Gets the channel.
	 * @return the channel.
	 */
	public ServerSocketChannel getChannel() {
		return channel;
	}

	/**
	 * Gets the socket.
	 * @return the socket
	 */
	public SocketChannel getSocket() {
		return socket;
	}

}