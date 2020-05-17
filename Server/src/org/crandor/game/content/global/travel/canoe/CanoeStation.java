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
	LUMBRIDGE(12163, new int[] { 47 }, Location.create(3240, 3242, 0), new Location(3241, 3235, 0)), CHAMPIONS_GUILD(12164, new int[] { 48 }, Location.create(3199, 3344, 0), new Location(3200, 3341, 0)), BARBARIAN_VILLAGE(12165, new int[] { 3 }, Location.create(3109, 3415, 0), new Location(3110, 3409, 0)), EDGEVILLE(12166, new int[] { 6 }, Location.create(3132, 3510, 0), new Location(3130, 3508, 0)), WILDERNESS(-1, new int[] { 49 }, Location.create(3139, 3796, 0), null);

	/**
	 * Constructs a new {@code CanoeStation} {@code Object}.
	 * @param object the object.
	 * @param location the location.
	 * @param object location.
	 */
	CanoeStation(final int object, final int[] buttons, final Location destination, final Location location) {
		this.object = object;
		this.buttons = buttons;
		this.destination = destination;
		this.objLocation = location;
	}

	/**
	 * Represents the object id of this station.
	 */
	private final int object;

	/**
	 * Represents the buttons.
	 */
	private final int[] buttons;

	/**
	 * Represents the destination to end at.
	 */
	private final Location destination;

	/**
	 * Represents the object location.
	 */
	private final Location objLocation;

	/**
	 * Gets the object.
	 * @return The object.
	 */
	public int getObject() {
		return object;
	}

	/**
	 * Method used to get the formated name of the station.
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
	public static CanoeStation forObject(final GameObject object) {
		CanoeStation[] stations = values();
		int length = stations.length;
		for (int i = 0; i < length; i++) {
			CanoeStation station = stations[i];
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
	 * Method used to get the canoe station for the buttons.
	 * @param buttons the buttons.
	 * @return the station.
	 */
	public static CanoeStation forButton(final int button) {
		CanoeStation[] stations = values();
		int length = stations.length;
		for (int j = 0; j < length; j++) {
			int[] buttons = stations[j].getButtons();
			int length2 = buttons.length;
			for (int i = 0; i < length2; i++) {
				if (buttons[i] == button) {
					return stations[j];
				}
			}
		}
		return null;
	}

	/**
	 * Gets the buttons.
	 * @return the buttons.
	 */
	public int[] getButtons() {
		return buttons;
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
