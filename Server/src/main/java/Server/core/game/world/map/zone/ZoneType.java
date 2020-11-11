package core.game.world.map.zone;

/**
 * Represents the default zone types.
 * @author Emperor
 */
public enum ZoneType {

	/**
	 * Default zone.
	 */
	DEFAULT(0),

	/**
	 * Safe area.
	 */
	SAFE(1),

	/**
	 * Player-owner house type.
	 */
	P_O_H(2),

	/**
	 * Castle wars zone.
	 */
	CASTLE_WARS(3),

	/**
	 * Trouble brewing zone.
	 */
	TROUBLE_BREWING(4),

	/**
	 * Barbarian assault zone.
	 */
	BARBARIAN_ASSAULT(5), ;

	/**
	 * The zone id.
	 */
	private final int id;

	/**
	 * Constructs a new {@code ZoneType} {@code Object}.
	 * @param id The zone id.
	 */
	ZoneType(int id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}
}