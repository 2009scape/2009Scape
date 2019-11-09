package org.crandor.game.world.map.zone;

import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.ai.minigamebots.pestcontrol.PestControlTestBot;
import org.crandor.game.world.map.Location;
import org.crandor.tools.RandomFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the borders of a zone.
 * @author Emperor
 */
public final class ZoneBorders {

	/**
	 * The south west x-coordinate.
	 */
	private final int southWestX;

	/**
	 * The south west y-coordinate.
	 */
	private final int southWestY;

	/**
	 * The north east x-coordinate.
	 */
	private final int northEastX;

	/**
	 * The north east y-coordinate.
	 */
	private final int northEastY;

	/**
	 * The plane required to be on.
	 */
	private int plane;

	/**
	 * The list of exceptions.
	 */
	private List<ZoneBorders> exceptions;

	/**
	 * If we need to do a zero plane check.
	 */
	private boolean zeroPlaneCheck;

	/**
	 * Constructs a new {@code ZoneBorders} {@code Object}.
	 * @param southWestX The south west x-coordinate.
	 * @param southWestY The south west y-coordinate.
	 * @param northEastX The north east x-coordinate.
	 * @param northEastY The north east y-coordinate.
	 */
	public ZoneBorders(int southWestX, int southWestY, int northEastX, int northEastY) {
		this.southWestX = southWestX;
		this.southWestY = southWestY;
		this.northEastX = northEastX;
		this.northEastY = northEastY;
	}

	/**
	 * Constructs a new {@code ZoneBorders} {@code Object}.
	 * @param southWestX The south west x-coordinate.
	 * @param southWestY The south west y-coordinate.
	 * @param northEastX The north east x-coordinate.
	 * @param northEastY The north east y-coordinate.
	 * @param plane the plane.
	 */
	public ZoneBorders(int southWestX, int southWestY, int northEastX, int northEastY, int plane) {
		this.southWestX = southWestX;
		this.southWestY = southWestY;
		this.northEastX = northEastX;
		this.northEastY = northEastY;
		this.setPlane(plane);
	}

	/**
	 * Constructs a new {@code ZoneBorders} {@code Object}.
	 * @param southWestX The south west x-coordinate.
	 * @param southWestY The south west y-coordinate.
	 * @param northEastX The north east x-coordinate.
	 * @param northEastY The north east y-coordinate.
	 * @param plane the plane.
	 * @param zeroPlaneCheck the plane check.
	 */
	public ZoneBorders(int southWestX, int southWestY, int northEastX, int northEastY, int plane, boolean zeroPlaneCheck) {
		this(southWestX, southWestY, northEastX, northEastY, plane);
		this.zeroPlaneCheck = zeroPlaneCheck;
	}

	/**
	 * Creates zone borders for the given region id.
	 * @param regionId The region id.
	 * @return The zone borders.
	 */
	public static ZoneBorders forRegion(int regionId) {
		int baseX = ((regionId >> 8) & 0xFF) << 6;
		int baseY = (regionId & 0xFF) << 6;
		int size = 64 - 1;
		return new ZoneBorders(baseX, baseY, baseX + size, baseY + size);
	}

	/**
	 * Checks if the location is inside the borders.
	 * @param location The location.
	 * @return {@code True} if the location is inside the zone borders.
	 */
	public boolean insideBorder(Location location) {
		return insideBorder(location.getX(), location.getY(), location.getZ());
	}

	/**
	 * Checks if the node is inside the borders.
	 * @param node the node.
	 * @return {@code True} if so.
	 */
	public boolean insideBorder(Node node) {
		return insideBorder(node.getLocation());
	}

	/**
	 * Checks if the player is in the zone
	 * @param x the x.
	 * @param y the y.
	 * @return the z.
	 */
	public boolean insideBorder(int x, int y) {
		return insideBorder(x, y, 0);
	}

	/**
	 * Checks if the coordinates are inside the borders.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param z The z coordinate.
	 * @return {@code True} if the coordinates lay in the zone borders.
	 */
	public boolean insideBorder(int x, int y, int z) {
		if (zeroPlaneCheck ? z != plane : (plane != 0 && z != plane)) {
			return false;
		}
		if (southWestX <= x && southWestY <= y && northEastX >= x && northEastY >= y) {
			if (exceptions != null) {
				for (ZoneBorders exception : exceptions) {
					if (exception.insideBorder(x, y, z)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Gets the ids of all the regions inside these borders.
	 * @return The region ids.
	 */
	public List<Integer> getRegionIds() {
		List<Integer> regionIds = new ArrayList<>();
		for (int x = southWestX >> 6; x < (northEastX >> 6) + 1; x++) {
			for (int y = southWestY >> 6; y < (northEastY >> 6) + 1; y++) {
				int id = y | x << 8;
				regionIds.add(id);
			}
		}
		return regionIds;
	}

	/**
	 * Gets the southWestX.
	 * @return The southWestX.
	 */
	public int getSouthWestX() {
		return southWestX;
	}

	/**
	 * Gets the southWestY.
	 * @return The southWestY.
	 */
	public int getSouthWestY() {
		return southWestY;
	}

	/**
	 * Gets the northEastX.
	 * @return The northEastX.
	 */
	public int getNorthEastX() {
		return northEastX;
	}

	/**
	 * Gets the northEastY.
	 * @return The northEastY.
	 */
	public int getNorthEastY() {
		return northEastY;
	}

	/**
	 * Gets the exceptions.
	 * @return The exceptions.
	 */
	public List<ZoneBorders> getExceptions() {
		return exceptions;
	}

	public Location getWeightedRandomLoc(int intensity)
	{
		int x = northEastX - southWestX == 0 ? southWestX : RandomFunction.normalRandDist(northEastX - southWestX, intensity) + southWestX;
		int y = northEastY - southWestY == 0 ? southWestY : RandomFunction.normalRandDist(northEastY - southWestY, intensity) + southWestY;
		return new Location(x, y);
	}

	public Location getRandomLoc() {
	    int x = northEastX - southWestX == 0 ? southWestX : new Random().nextInt(northEastX - southWestX + 1) + southWestX;
	    int y = northEastY - southWestY == 0 ? southWestY : new Random().nextInt(northEastY - southWestY + 1) + southWestY;
		return new Location(x, y);
	}

	/**
	 * Adds an exception.
	 * @param exception The exception to add.
	 */
	public void addException(ZoneBorders exception) {
		if (exceptions == null) {
			this.exceptions = new ArrayList<>();
		}
		exceptions.add(exception);
	}

	@Override
	public String toString() {
		return "ZoneBorders [southWestX=" + southWestX + ", southWestY=" + southWestY + ", northEastX=" + northEastX + ", northEastY=" + northEastY + ", exceptions=" + exceptions + "]";
	}

	/**
	 * Gets the bplane.
	 * @return the plane
	 */
	public int getPlane() {
		return plane;
	}

	/**
	 * Sets the baplane.
	 * @param plane the plane to set.
	 */
	public void setPlane(int plane) {
		this.plane = plane;
	}

	public boolean insideRegion(Node n) {
		return insideBorder(n.getLocation().getRegionX(), n.getLocation().getRegionY());
	}
}