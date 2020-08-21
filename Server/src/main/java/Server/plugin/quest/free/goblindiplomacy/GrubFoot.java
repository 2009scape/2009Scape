package plugin.quest.free.goblindiplomacy;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;

/**
 * Represents a grub foot.
 * @author Vexia
 */
public enum GrubFoot {
	NORMAL(4495, 1, new Item(288)), ORANGE(4497, 4, new Item(286)), BLUE(4498, 5, new Item(287)), BROWN(4496, 6, new Item(288));

	/**
	 * The id.
	 */
	private final int id;

	/**
	 * The config value.
	 */
	private final int value;

	/**
	 * The goblin mail.
	 */
	private final Item mail;

	/**
	 * Constructs a new {@code GrubFoot} {@code Object}.
	 * @param id the id.
	 * @param value the value.
	 * @param mail the mail.
	 */
	private GrubFoot(int id, int value, Item mail) {
		this.id = id;
		this.value = value;
		this.mail = mail;
	}

	/**
	 * Gets the grub foot for the config.
	 * @param player the player.
	 * @return the grub foot.
	 */
	public static GrubFoot forConfig(final Player player) {
		int config = player.getConfigManager().get(62);
		for (GrubFoot foot : values()) {
			if (foot.getValue() == config) {
				if (foot.ordinal() + 1 >= values().length) {
					return BROWN;
				}
				return values()[foot.ordinal() + 1];
			}
		}
		return NORMAL;
	}

	/**
	 * Sets the config value.
	 * @param player the player.
	 */
	public void setConfig(Player player) {
		player.getConfigManager().set(62, value);
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the value.
	 * @return The value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the mail.
	 * @return The mail.
	 */
	public Item getMail() {
		return mail;
	}

}
