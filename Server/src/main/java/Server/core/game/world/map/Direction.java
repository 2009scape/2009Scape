package core.game.world.map;

/**
 * Represents a direction.
 * @author Emperor
 */
public enum Direction {
	/**
	 * 0   1   2
	 *   \ | /
	 * 3 - . - 4
	 *   / | \
	 * 5   6   7
	 */


	/**
	 * The north-west direction.
	 */
	NORTH_WEST(-1, 1, 7, 0x12c0108, 0x12c0120, 0x12c0138),

	/**
	 * The north direction.
	 */
	NORTH(0, 1, 0, 0x12c0120),

	/**
	 * The north-east direction.
	 */
	NORTH_EAST(1, 1, 4, 0x12c0180, 0x12c0120, 0x12c01e0),

	/**
	 * The west direction.
	 */
	WEST(-1, 0, 3, 0x12c0108),

	/**
	 * The east direction.
	 */
	EAST(1, 0, 1, 0x12c0180),

	/**
	 * The south-west direction.
	 */
	SOUTH_WEST(-1, -1, 6, 0x12c0108, 0x12c0102, 0x12c010e),

	/**
	 * The south direction.
	 */
	SOUTH(0, -1, 2, 0x12c0102),

	/**
	 * The south-east direction.
	 */
	SOUTH_EAST(1, -1, 5, 0x12c0180, 0x12c0102, 0x12c0183);

	/**
	 * The amounts of steps on the x-axis.
	 */
	private final int stepX;

	/**
	 * The amounts of steps on the y-axis.
	 */
	private final int stepY;

	/**
	 * The integer value.
	 */
	private final int value;

	/**
	 * The traversal flags.
	 */
	private int[] traversal;

	/**
	 * Constructs a new {@code Direction} {@code Object}.
	 * @param stepX The x-offset to move a step.
	 * @param stepY The y-offset to move a step.
	 * @param value The direction value.
	 * @param traversal The traversal flags.
	 */
	Direction(int stepX, int stepY, int value, int... traversal) {
		this.stepX = stepX;
		this.stepY = stepY;
		this.value = value;
		this.setTraversal(traversal);
	}

	/**
	 * Gets the direction.
	 * @param rotation The int value.
	 * @return The direction.
	 */
	public static Direction get(int rotation) {
		for (Direction dir : Direction.values()) {
			if (dir.value == rotation) {
				return dir;
			}
		}
		throw new IllegalArgumentException("Invalid direction value - " + rotation);
	}

	/**
	 * Gets the walk point for a direction. <br> The point will be the offset to
	 * the location the node is facing.
	 * @param direction The direction.
	 * @return The point.
	 */
	public static Point getWalkPoint(Direction direction) {
		return new Point(direction.stepX, direction.stepY);
	}

	/**
	 * Gets the direction.
	 * @param location The start location.
	 * @param l The end location.
	 * @return The direction.
	 */
	public static Direction getDirection(Location location, Location l) {
		return getDirection(l.getX() - location.getX(), l.getY() - location.getY());
	}

	/**
	 * Gets the direction for movement.
	 * @param diffX The difference between 2 x-coordinates.
	 * @param diffY The difference between 2 y-coordinates.
	 * @return The direction.
	 */
	public static Direction getDirection(int diffX, int diffY) {
		if (diffX < 0) {
			if (diffY < 0) {
				return SOUTH_WEST;
			} else if (diffY > 0) {
				return NORTH_WEST;
			}
			return WEST;
		} else if (diffX > 0) {
			if (diffY < 0) {
				return SOUTH_EAST;
			} else if (diffY > 0) {
				return NORTH_EAST;
			}
			return EAST;
		}
		if (diffY < 0) {
			return SOUTH;
		}
		return NORTH;
	}

	/**
	 * Gets the direction for the given walking flag.
	 * @param walkingFlag The walking flag.
	 * @param rotation The rotation.
	 * @return The direction, or null if the walk flag was 0.
	 */
	public static Direction forWalkFlag(int walkingFlag, int rotation) {
		if (rotation != 0) {
			walkingFlag = (walkingFlag << rotation & 0xf) + (walkingFlag >> 4 - rotation);
		}
		if (walkingFlag > 0) {
			if ((walkingFlag & 0x8) == 0) {
				return Direction.WEST;
			}
			if ((walkingFlag & 0x2) == 0) {
				return Direction.EAST;
			}
			if ((walkingFlag & 0x4) == 0) {
				return Direction.SOUTH;
			}
			if ((walkingFlag & 0x1) == 0) {
				return Direction.NORTH;
			}
		}
		return null;
	}

	/**
	 * Gets the opposite dir.
	 * @return the direction.
	 */
	public Direction getOpposite() {
		return Direction.get(toInteger() + 2 & 3);
	}

	/**
	 * Gets the most logical direction.
	 * @param location The start location.
	 * @param l The end location.
	 * @return The most logical direction.
	 */
	public static Direction getLogicalDirection(Location location, Location l) {
		int offsetX = Math.abs(l.getX() - location.getX());
		int offsetY = Math.abs(l.getY() - location.getY());
		if (offsetX > offsetY) {
			if (l.getX() > location.getX()) {
				return Direction.EAST;
			} else {
				return Direction.WEST;
			}
		} else if (l.getY() < location.getY()) {
			return Direction.SOUTH;
		}
		return Direction.NORTH;
	}

	/**
	 * Method used to go to clue the anme.
	 * @param direction the direction.
	 * @return the name.
	 */
	public String toName(Direction direction) {
		return direction.name().toLowerCase();
	}

	/**
	 * Method used to get the direction to an integer.
	 * @return the integer.
	 */
	public int toInteger() {
		return value;
	}

	/**
	 * Gets the stepX.
	 * @return The stepX.
	 */
	public int getStepX() {
		return stepX;
	}

	/**
	 * Gets the stepY.
	 * @return The stepY.
	 */
	public int getStepY() {
		return stepY;
	}

	/**
	 * Checks if traversal is permitted for this direction.
	 * @param l The location.
	 * @return {@code True} if so.
	 */
	public boolean canMove(Location l) {
		int flag = RegionManager.getClippingFlag(l.getZ(), l.getX(), l.getY());
		for (int f : traversal) {
			if ((flag & f) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the traversal.
	 * @return The traversal.
	 */
	public int[] getTraversal() {
		return traversal;
	}

	/**
	 * Sets the traversal.
	 * @param traversal The traversal to set.
	 */
	public void setTraversal(int[] traversal) {
		this.traversal = traversal;
	}
}