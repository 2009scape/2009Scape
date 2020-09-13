package plugin.skill.runecrafting;

import core.cache.def.impl.ItemDefinition;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import plugin.quest.members.lostcity.LostCity;

/**
 * Represents an altar an it's relative information(corresponding ruin, etc)
 * @author 'Vexia
 * @date 01/11/2013
 */
public enum Altar {
	AIR(2478, 2465, 7139, MysteriousRuin.AIR, Rune.AIR),
	MIND(2479, 2466, 7140, MysteriousRuin.MIND, Rune.MIND),
	WATER(2480, 2467, 7137, MysteriousRuin.WATER, Rune.WATER), 
	EARTH(2481, 2468, 7130, MysteriousRuin.EARTH, Rune.EARTH),
	FIRE(2482, 2469, 7129, MysteriousRuin.FIRE, Rune.FIRE),
	BODY(2483, 2470, 7131, MysteriousRuin.BODY, Rune.BODY),
	COSMIC(2484, 2471, 7132, MysteriousRuin.COSMIC, Rune.COSMIC),
	CHAOS(2487, 2474, 7134, MysteriousRuin.CHAOS, Rune.CHAOS),
	ASTRAL(17010, 0, 0, null, Rune.ASTRAL), 
	NATURE(2486, 2473, 7133, MysteriousRuin.NATURE, Rune.NATURE),
	LAW(2485, 2472, 7135, MysteriousRuin.LAW, Rune.LAW), 
	DEATH(2488, 2475, 7136, MysteriousRuin.DEATH, Rune.DEATH), 
	BLOOD(30624, 2477, 7141, MysteriousRuin.BLOOD, Rune.BLOOD), 
	SOUL(2489, 0, 7138, null, Rune.SOUL), OURANIA(26847, 0, 0, null, null);

	/**
	 * Represents the object of the altar.
	 */
	private final int object;

	/**
	 * Represents the portal object.
	 */
	private final int portal;

	/**
	 * The rift id.
	 */
	private final int riftId;

	/**
	 * Represents the corresponding ruin.
	 */
	private final MysteriousRuin ruin;

	/**
	 * Represents the rune constructed.
	 */
	private final Rune rune;

	/**
	 * Constructs a new {@code Altar} {@code Object}.
	 * @param object the object.
	 * @param ruin the ruin.
	 * @param rune the rune.
	 */
	Altar(final int object, final int portal, final int riftId, final MysteriousRuin ruin, final Rune rune) {
		this.object = object;
		this.portal = portal;
		this.riftId = riftId;
		this.ruin = ruin;
		this.rune = rune;
	}

	/**
	 * Enters a rift.
	 * @param player the player.
	 */
	public void enterRift(Player player) {
		if (this == LAW) {
			if (!ItemDefinition.canEnterEntrana(player)) {
				player.sendMessage("You can't take weapons and armour into the law rift.");
				return;
			}
		}
		if (this == COSMIC && !player.getQuestRepository().isComplete(LostCity.NAME)) {
			player.getPacketDispatch().sendMessage("You need to have completed the Lost City quest in order to do that.");
			return;
		}
		if (getRuin() == null) {
			return;
		}
		if (getRuin().getEnd() == null) {
			return;
		}
		player.getProperties().setTeleportLocation(getRuin().getEnd());
	}

	/**
	 * Gets the object.
	 * @return The object.
	 */
	public int getObject() {
		return object;
	}

	/**
	 * Gets the ruin.
	 * @return The ruin.
	 */
	public MysteriousRuin getRuin() {
		return ruin;
	}

	/**
	 * Gets the rune.
	 * @return The rune.
	 */
	public Rune getRune() {
		return rune;
	}

	/**
	 * Checks if its the ourania altar.
	 * @return the ourania.
	 */
	public boolean isOurania() {
		return getRune() == null;
	}

	/**
	 * Gets the talisman.
	 * @return the talisman.
	 */
	public Talisman getTalisman() {
		for (Talisman talisman : Talisman.values()) {
			if (talisman.name().equals(name())) {
				return talisman;
			}
		}
		return null;
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
	 * Method used to get the <code>Altar</code> by the object.
	 * @param object the object.
	 * @return the <code>Altar</code> or <code>Null</code>.
	 */
	public static Altar forObject(final GameObject object) {
		for (Altar altar : values()) {
			if (altar.getObject() == object.getId() || altar.getPortal() == object.getId() || object.getId() == altar.getRiftId()) {
				return altar;
			}
		}
		return null;
	}

	/**
	 * Gets the portal.
	 * @return The portal.
	 */
	public int getPortal() {
		return portal;
	}

	/**
	 * Gets the riftId.
	 * @return The riftId.
	 */
	public int getRiftId() {
		return riftId;
	}

}
