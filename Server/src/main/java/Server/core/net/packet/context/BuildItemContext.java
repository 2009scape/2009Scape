package core.net.packet.context;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.net.packet.Context;

/**
 * Represents the build item packet context, <br> which is used for
 * construct/clear item outgoing packet.
 * @author Emperor
 */
public final class BuildItemContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The item to send.
	 */
	private final Item item;

	/**
	 * The old item amount.
	 */
	private final int oldAmount;

	/**
	 * Constructs a new {@code BuildObjectContext} {@code Object}.
	 * @param player The player
	 * @param item The item to send.
	 */
	public BuildItemContext(Player player, Item item) {
		this(player, item, 0);
	}

	/**
	 * Constructs a new {@code BuildObjectContext} {@code Object}.
	 * @param player The player
	 * @param item The item to send.
	 * @param oldAmount The old item amount.
	 */
	public BuildItemContext(Player player, Item item, int oldAmount) {
		this.player = player;
		this.item = item;
		this.oldAmount = oldAmount;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the item.
	 * @return The item.
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Gets the oldAmount.
	 * @return The oldAmount.
	 */
	public int getOldAmount() {
		return oldAmount;
	}

}