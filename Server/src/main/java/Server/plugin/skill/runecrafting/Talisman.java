package plugin.skill.runecrafting;

import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.map.Location;

/**
 * Represents a talisman.
 * @author 'Vexia
 */
public enum Talisman {
	AIR(new Item(1438), MysteriousRuin.AIR), MIND(new Item(1448), MysteriousRuin.MIND), WATER(new Item(1444), MysteriousRuin.WATER), EARTH(new Item(1440), MysteriousRuin.EARTH), FIRE(new Item(1442), MysteriousRuin.FIRE), ELEMENTAL(new Item(5516), null), BODY(new Item(1446), MysteriousRuin.BODY), COSMIC(new Item(1454), MysteriousRuin.COSMIC), CHAOS(new Item(1452), MysteriousRuin.CHAOS), NATURE(new Item(1462), MysteriousRuin.NATURE), LAW(new Item(1458), MysteriousRuin.LAW), DEATH(new Item(1456), MysteriousRuin.DEATH), BLOOD(new Item(1450), null), SOUL(new Item(1460), null);

	/**
	 * Constructs a new {@code Talisman} {@code Object}.
     */
	Talisman(final Item talisman, MysteriousRuin ruin) {
		this.talisman = talisman;
		this.ruin = ruin;
	}

	/**
	 * Represents the talisman item.
	 */
	private final Item talisman;

	/**
	 * Represents the mysterious ruin.
	 */
	private final MysteriousRuin ruin;

	/**
	 * Gets the talisman.
	 * @return The talisman.
	 */
	public Item getTalisman() {
		return talisman;
	}

	/**
	 * Method used to locate a ruin.
	 */
	public final void locate(final Player player) {
		if (this == ELEMENTAL || getRuin() == null) {
			player.getPacketDispatch().sendMessage("You cannot tell which direction the Talisman is pulling...");
			return;
		}
		String direction = "";
		Location loc = getRuin().getBase();
		if (player.getLocation().getY() > loc.getY() && player.getLocation().getX() - 1 > loc.getX())
			direction = "south-west";
		else if (player.getLocation().getX() < loc.getX() && player.getLocation().getY() > loc.getY())
			direction = "south-east";
		else if (player.getLocation().getX() > loc.getX() + 1 && player.getLocation().getY() < loc.getY())
			direction = "north-west";
		else if (player.getLocation().getX() < loc.getX() && player.getLocation().getY() < loc.getY())
			direction = "north-east";
		else if (player.getLocation().getY() < loc.getY())
			direction = "north";
		else if (player.getLocation().getY() > loc.getY())
			direction = "south";
		else if (player.getLocation().getX() < loc.getX() + 1)
			direction = "east";
		else if (player.getLocation().getX() > loc.getX() + 1)
			direction = "west";
		player.getPacketDispatch().sendMessage("The talisman pulls towards the " + direction + ".");
	}

	/**
	 * Gets the ruin.
	 * @return The ruin.
	 */
	public MysteriousRuin getRuin() {
		for (MysteriousRuin ruin : MysteriousRuin.values()) {
			if (ruin.name().equals(name())) {
				return ruin;
			}
		}
		return ruin;
	}

	/**
	 * Gets the tiara.
	 * @return the tiara.
	 */
	public Tiara getTiara() {
		for (Tiara tiara : Tiara.values()) {
			if (tiara.name().equals(name())) {
				return tiara;
			}
		}
		return null;
	}

	/**
	 * Method used to get the <code>Talisman</code> by the item.
	 * @param item the item.
	 * @return the <code>Talisman</code> or <code>Null</code>.
	 */
	public static Talisman forItem(final Item item) {
		for (Talisman talisman : values()) {
			if (talisman.getTalisman().getId() == item.getId()) {
				return talisman;
			}
		}
		return null;
	}

	/**
	 * Method used to get the <code>Talisman</code> by the item.
	 * @return the <code>Talisman</code> or <code>Null</code>.
	 */
	public static Talisman forName(final String name) {
		for (Talisman talisman : values()) {
			if (talisman.name().equals(name)) {
				return talisman;
			}
		}
		return null;
	}
}
