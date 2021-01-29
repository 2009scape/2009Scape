package core.net.packet.context;

import core.game.node.entity.player.Player;
import core.net.packet.Context;

/**
 * The packet context of the grand exchange update packet.
 * @author Emperor
 */
public class GrandExchangeContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	public final byte idx;
	public final byte state;
	public final short itemID;
	public final boolean isSell;
	public final int value;
	public final int amt;
	public final int completedAmt;
	public final int totalCoinsExchanged;

	/**
	 * Constructs a new {@code GrandExchangeContext} {@code Object}.
	 * @param player The player.
	 * @param state
	 * @param itemID
	 * @param value
	 * @param amt
	 * @param completedAmt
	 * @param totalCoinsExchanged
	 */
	public GrandExchangeContext(Player player, byte idx, byte state, short itemID, boolean isSell, int value, int amt, int completedAmt, int totalCoinsExchanged) {
		this.player = player;
		this.idx = idx;
		this.state = state;
		this.itemID = itemID;
		this.isSell = isSell;
		this.value = value;
		this.amt = amt;
		this.completedAmt = completedAmt;
		this.totalCoinsExchanged = totalCoinsExchanged;
	}

	@Override
	public Player getPlayer() {
		return player;
	}
}
