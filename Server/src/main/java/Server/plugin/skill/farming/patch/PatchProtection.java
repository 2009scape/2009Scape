package plugin.skill.farming.patch;

import core.game.world.map.Location;

/**
 * Represents a patch protection.
 * @author 'Vexia
 * @version 1.0
 */
public enum PatchProtection {
	FALADOR(new Location(3054, 3307, 0), new Location(3051, 3309, 0), new Location(3058, 3307, 0)),
	CATHERBY(new Location(2809, 3463, 0), new Location(2808, 3460, 0), new Location(2808, 3467, 0)),
	HEMENSTER(new Location(2666, 3374, 0), new Location(2665, 3371, 0), new Location(2665, 3378, 0)),
	PORT_PHASMATYS(new Location(3601, 3525, 0), new Location(3603, 3521, 0), new Location(3599, 3529, 0));

	/**
	 * Represents flower locations.
	 */
	private final Location flowerLocation;

	/**
	 * Represents allotment locations.
	 */
	private final Location[] allotmentLocations;

	/**
	 * Constructs a new {@code PatchProtection} {@code Object}.
	 * @param flowerLocation the flower location.
	 * @param allotmentLocations the allotment locations.
	 */
	PatchProtection(Location flowerLocation, Location... allotmentLocations) {
		this.flowerLocation = flowerLocation;
		this.allotmentLocations = allotmentLocations;
	}

	/**
	 * Gets the flowerLocation.
	 * @return The flowerLocation.
	 */
	public Location getFlowerLocation() {
		return flowerLocation;
	}

	/**
	 * Gets the allotmentLocations.
	 * @return The allotmentLocations.
	 */
	public Location[] getAllotmentLocations() {
		return allotmentLocations;
	}

}
