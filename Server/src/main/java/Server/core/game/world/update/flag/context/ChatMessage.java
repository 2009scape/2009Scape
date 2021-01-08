package core.game.world.update.flag.context;

import core.game.node.entity.player.Player;

/**
 * Represents a chat message.
 * @author Emperor
 */
public class ChatMessage {

	/**
	 * The player reference.
	 */
	private Player player;

	/**
	 * The chat text.
	 */
	private String text;

	/**
	 * The effects.
	 */
	private int effects;

	/**
	 * The numChars.
	 */
	private int numChars;

	public boolean isQuickChat = false;

	/**
	 * Constructs a new {@code ChatMessage} {@code Object}.
	 * @param player The player.
	 * @param text The chat text.
	 * @param effects The chat effects.
	 * @param numChars The num chars.
	 */
	public ChatMessage(Player player, String text, int effects, int numChars) {
		this.player = player;
		this.text = text;
		this.effects = effects;
		this.numChars = numChars;
	}

	/**
	 * Constructs a new {@code ChatMessage.java} {@code Object}.
	 * @param player the player.
	 * @param text the text.
	 */
	public ChatMessage(Player player, String text) {
		this.player = player;
		this.text = text;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Get the chat text.
	 * @return The chat text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Get the chat effects.
	 * @return The chat effects.
	 */
	public int getEffects() {
		return effects;
	}

	/**
	 * Get the num chars.
	 * @return The num chars.
	 */
	public int getNumChars() {
		return numChars;
	}
}