package org.crandor.game.content.global.travel.canoe;

import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.tools.StringUtils;

/**
 * Represents a <b>Canoe</b> location.
 * @author 'Vexia
 * @date 09/11/2013
 */
public enum CanoeStation {
	LUMBRIDGE(12163, 47, Location.create(3240, 3242, 0), new Location(3241, 3235, 0)),
	CHAMPIONS_GUILD(12164, 48, Location.create(3199, 3344, 0), new Location(3200, 3341, 0)),
	BARBARIAN_VILLAGE(12165, 3, Location.create(3109, 3415, 0), new Location(3110, 3409, 0)),
	EDGEVILLE(12166, 6, Location.create(3132, 3510, 0), new Location(3130, 3508, 0)),
	WILDERNESS(-1, 49, Location.create(3139, 3796, 0), null);

	/**
	 * Constructs a new {@code CanoeStation} {@code Object}.
	 * @param objectId the object id of the station.
	 * @param button the button representing the station.
	 * @param location the location of the station.
	 * @param destination the location to end at when the station is the destination.
	 */
	CanoeStation(final int objectId, final int button, final Location destination, final Location location) {
		this.object = objectId;
		this.button = button;
		this.destination = destination;
		this.objLocation = location;
	}

	/**
	 * Represents the object id of this station.
	 */
	private final int object;

	/**
	 * Represents the button.
	 */
	private final int button;

	/**
	 * Represents the location to end at when the station is the destination.
	 */
	private final Location destination;

	/**
	 * Represents the location of the canoe station object.
	 */
	private final Location objLocation;

	/**
	 * Gets the object id of the station.
	 * @return The object.
	 */
	public int getObject() {
		return object;
	}

	/**
	 * Method used to get the formatted name of the station.
	 * @return the name.
	 */
	public String getName() {
		return (this == BARBARIAN_VILLAGE || this == CHAMPIONS_GUILD ? "the " : "") + StringUtils.formatDisplayName(name());
	}

	/**
	 * Method used to get the <b>CanoeStation</b> by the <b>GameObject</b>.
	 * @param object the object.
	 * @return the <code>CanoeStation</code>.
	 */
	public static CanoeStation getStationByObject(final GameObject object) {
		CanoeStation[] stations = values();
		for (CanoeStation station : stations) {
			if (station.getObjLocation() != null && station.getObjLocation().equals(object.getLocation())) {
				return station;
			}
		}
		return null;
	}

	/**
	 * Method used to get the config for a floating canoe.
	 * @param canoe the canoe.
	 * @return the config.
	 */
	public int getFloatConfig(final Canoe canoe) {
		int value = 0;
		switch (this) {
		case BARBARIAN_VILLAGE:
			return (canoe != Canoe.LOG ? 65536 * canoe.ordinal() : 0);
		case CHAMPIONS_GUILD:
			return (canoe != Canoe.LOG ? 256 * canoe.ordinal() : 0);
		case EDGEVILLE:
			return (canoe != Canoe.LOG ? 16777216 * canoe.ordinal() : 0);
		case LUMBRIDGE:
			return (canoe != Canoe.LOG ? canoe.ordinal() : 0);
		default:
			break;
		}
		return value;
	}

	/**
	 * Method used to get the config for a crafted canoe.
	 * @param canoe the canoe.
	 * @return the config.
	 */
	public int getCraftConfig(final Canoe canoe, boolean floating) {
		int value = 0;
		switch (this) {
		case BARBARIAN_VILLAGE:
			if (canoe == Canoe.LOG && floating) {
				return 0;
			}
			return (canoe != Canoe.LOG ? 65536 * (canoe.ordinal() + (!floating ? 1 : 0)) : 65536);
		case CHAMPIONS_GUILD:
			return (canoe != Canoe.LOG ? 256 * canoe.ordinal() : 0);
		case EDGEVILLE:
			return (canoe != Canoe.LOG ? 16777216 * canoe.ordinal() : 0);
		case LUMBRIDGE:
			return canoe.ordinal();
		default:
			break;
		}
		return value;
	}

	/**
	 * Method used to get the canoe station from the button.
	 * @param button the button.
	 * @return the station.
	 */
	public static CanoeStation getStationFromButton(final int button) {
		CanoeStation[] stations = values();
		for (CanoeStation station : stations) {
			if (station.button == button) {
				return station;
			}
		}
		return null;
	}

	/**
	 * Gets the buttons.
	 * @return the buttons.
	 */
	public int getButton() {
		return button;
	}

	/**
	 * Gets the destination.
	 * @return The destination.
	 */
	public Location getDestination() {
		return destination;
	}

	/**
	 * Gets the objLocation.
	 * @return The objLocation.
	 */
	public Location getObjLocation() {
		return objLocation;
	}
}
