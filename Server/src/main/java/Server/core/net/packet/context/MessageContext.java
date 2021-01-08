package core.net.packet.context;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.game.system.SystemLogger;
import core.net.packet.Context;

/**
 * Packet context for communication message packets.
 * @author Emperor
 */
public final class MessageContext implements Context {

	/**
	 * Represents the packet id to use when sending a message.
	 */
	public static final int SEND_MESSAGE = 71;

	/**
	 * Represents the packet id to use when receiving a message.
	 */
	public static final int RECIEVE_MESSAGE = 0;
	
	/**
	 * Represents the packet id use to send a clan message.
	 */
	public static final int CLAN_MESSAGE = 54;

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The other player.
	 */
	private final String other;

	/**
	 * The chat icon.
	 */
	private final int chatIcon;

	/**
	 * The opcode.
	 */
	private final int opcode;

	/**
	 * The message.
	 */
	private final String message;

	/**
	 * Constructs a new MessageContext Object.
	 * @param player The player.
	 * @param other The communicated player.
	 * @param opcode The opcode.
	 * @param message The message.
	 */
	public MessageContext(Player player, Player other, int opcode, String message) {
		this.player = player;
		this.other = other.getName();
		this.chatIcon = Rights.getChatIcon(other);
		this.opcode = opcode;
		this.message = message;
	}

	/**
	 * Constructs a new MessageContext Object.
	 * @param player The player.
	 * @param other The communicated player.
	 * @param opcode The opcode.
	 * @param message The message.
	 */
	public MessageContext(Player player, String other, int chatIcon, int opcode, String message) {
		this.player = player;
		this.other = other;
		this.chatIcon = chatIcon;
		this.opcode = opcode;
		this.message = message;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the other.
	 * @return The other.
	 */
	public String getOther() {
		return other;
	}

	/**
	 * Gets the opcode.
	 * @return The opcode.
	 */
	public int getOpcode() {
		return opcode;
	}

	/**
	 * Gets the message.
	 * @return The message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the chatIcon.
	 * @return the chatIcon
	 */
	public int getChatIcon() {
		return chatIcon;
	}
}