package core.game.content.global.travel.glider;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;

/**
 * Represents an enum of glider locations.
 * @author 'Vexia
 */
public enum Gliders {
	TA_QUIR_PRIW(16, Location.create(2465, 3501, 3), 9, 3809),
	SINDARPOS(17, Location.create(2848, 3497, 0), 1, 3810),
	LEMANTO_ADRA(18, Location.create(3321, 3427, 0), 3, -1),
	AR_HEWO(19, Location.create(3278, 3212, 0), 4, 3812),
	LEMANTOLLY_UNDRI(20, Location.create(2544, 2970, 0), 10, 3813),
	GANDIUS(15, Location.create(2972, 2969, 0), 8, 3811);

	/**
	 * The button of the location.
	 */
	private final int button;

	/**
	 * The location to fly to.
	 */
	private final Location location;

	/**
	 * The config value.
	 */
	private final int config;

	/**
	 * The npc.
	 */
	private final int npc;

	/**
	 * Constructs a new {@code Gliders.java} {@Code Object}
	 * @param button the button.
	 * @param location the location.
	 * @param config the config.
	 * @param npc the npc.
	 */
	Gliders(int button, Location location, int config, int npc) {
		this.button = button;
		this.location = location;
		this.config = config;
		this.npc = npc;
	}

	/**
	 * Sends the config.
	 * @param asNpc the npc.
	 * @param player the player.
	 */
	public static void sendConfig(NPC asNpc, Player player) {
		Gliders g = forNpc(asNpc.getId());
		if (g == null) {
			return;
		}
		player.getConfigManager().set(153, g.getConfig());
	}

	/**
	 * Gets the glider by the npc.
	 * @param npc the npc.
	 * @return the gliders.
	 */
	public static Gliders forNpc(int npc) {
		for (Gliders g : values()) {
			if (g.getNpc() == npc) {
				return g;
			}
		}
		return null;
	}

	/**
	 * Gets the button.
	 * @return The button.
	 */
	public int getButton() {
		return button;
	}

	/**
	 * Gets the location.
	 * @return The location.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Gets the config.
	 * @return The config.
	 */
	public int getConfig() {
		return config;
	}

	/**
	 * Gets the flider value for the button id.
	 * @param id the id.
	 * @return the value.
	 */
	public static Gliders forId(int id) {
		for (Gliders i : Gliders.values()) {
			if (i.getButton() == id) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Gets the npc.
	 * @return the npc
	 */
	public int getNpc() {
		return npc;
	}

}
