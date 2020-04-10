package org.crandor.game.world.map.path;

import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.Point;
import org.crandor.game.world.map.RegionManager;

public final class SmartPathfinder extends Pathfinder {

	/**
	 * The x-queue.
	 */
	private int[] queueX;

	/**
	 * The y-queue.
	 */
	private int[] queueY;

	/**
	 * The "via" array.
	 */
	private int[][] via;

	/**
	 * The cost array.
	 */
	private int[][] cost;

	/**
	 * The current writing position.
	 */
	private int writePathPosition;

	/**
	 * The current x-coordinate.
	 */
	private int curX;

	/**
	 * The current y-coordinate.
	 */
	private int curY;

	/**
	 * The destination x-coordinate.
	 */
	private int dstX;

	/**
	 * The destination y-coordinate.
	 */
	private int dstY;

	/**
	 * If a path was found.
	 */
	private boolean foundPath;

	/**
	 * Constructs a new {@code SmartPathfinder} {@code Object}.
	 */
	SmartPathfinder() {
		/*
		 * empty.
		 */
	}

	/**
	 * Resets the pathfinder.
	 */
	public void reset() {
		queueX = new int[4096];
		queueY = new int[4096];
		via = new int[104][104];
		cost = new int[104][104];
		writePathPosition = 0;
	}

	/**
	 * Checks a tile.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param dir The direction.
	 * @param currentCost The current cost.
	 */
	public void check(int x, int y, int dir, int currentCost) {
		queueX[writePathPosition] = x;
		queueY[writePathPosition] = y;
		via[x][y] = dir;
		cost[x][y] = currentCost;
		writePathPosition = writePathPosition + 1 & 0xfff;
	}

	@Override
	public Path find(Location start, int moverSize, Location end, int sizeX, int sizeY, int rotation, int type, int walkingFlag, boolean near, ClipMaskSupplier clipMaskSupplier) {
		reset();
		Path path = new Path();
		foundPath = false;
		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				via[x][y] = 0;
				cost[x][y] = 99999999;
			}
		}
		int z = start.getZ();
		Location location = Location.create((start.getRegionX() - 6) << 3, (start.getRegionY() - 6) << 3, z);
		curX = start.getSceneX();
		curY = start.getSceneY();
		dstX = end.getSceneX(start);
		dstY = end.getSceneY(start);
		int attempts;
		int readPosition;
		check(curX, curY, 99, 0);
		if (moverSize < 2) {
			checkSingleTraversal(end, sizeX, sizeY, type, rotation, walkingFlag, location, clipMaskSupplier);
		} else if (moverSize == 2) {
			checkDoubleTraversal(end, sizeX, sizeY, type, rotation, walkingFlag, location, clipMaskSupplier);
		} else {
			checkVariableTraversal(end, moverSize, sizeX, sizeY, type, rotation, walkingFlag, location, clipMaskSupplier);
		}
		if (!foundPath) {
			if (near) {
				int fullCost = 1000;
				int thisCost = 100;
				int depth = 10;
				for (int x = dstX - depth; x <= dstX + depth; x++) {
					for (int y = dstY - depth; y <= dstY + depth; y++) {
						if (x >= 0 && y >= 0 && x < 104 && y < 104 && cost[x][y] < 100) {
							int diffX = 0;
							if (x < dstX) {
								diffX = dstX - x;
							} else if (x > dstX + sizeX - 1) {
								diffX = x - (dstX + sizeX - 1);
							}
							int diffY = 0;
							if (y < dstY) {
								diffY = dstY - y;
							} else if (y > dstY + sizeY - 1) {
								diffY = y - (dstY + sizeY - 1);
							}
							int totalCost = diffX * diffX + diffY * diffY;
							if (totalCost < fullCost || (totalCost == fullCost && (cost[x][y] < thisCost))) {
								fullCost = totalCost;
								thisCost = cost[x][y];
								curX = x;
								curY = y;
							}
						}
					}
				}
				if (fullCost == 1000) {
					return path;
				}
				path.setMoveNear(true);
			}
		}
		readPosition = 0;
		queueX[readPosition] = curX;
		queueY[readPosition++] = curY;
		int previousDirection;
		attempts = 0;
		for (int directionFlag = previousDirection = via[curX][curY]; curX != start.getSceneX() || curY != start.getSceneY(); directionFlag = via[curX][curY]) {
			if (++attempts > queueX.length) {
				return path;
			}
			if (directionFlag != previousDirection) {
				previousDirection = directionFlag;
				queueX[readPosition] = curX;
				queueY[readPosition++] = curY;
			}
			if ((directionFlag & WEST_FLAG) != 0) {
				curX++;
			} else if ((directionFlag & EAST_FLAG) != 0) {
				curX--;
			}
			if ((directionFlag & SOUTH_FLAG) != 0) {
				curY++;
			} else if ((directionFlag & NORTH_FLAG) != 0) {
				curY--;
			}
		}
		int size = readPosition--;
		int absX = location.getX() + queueX[readPosition];
		int absY = location.getY() + queueY[readPosition];
		path.getPoints().add(new Point(absX, absY));
		for (int i = 1; i < size; i++) {
			readPosition--;
			absX = location.getX() + queueX[readPosition];
			absY = location.getY() + queueY[readPosition];
			path.getPoints().add(new Point(absX, absY));
		}
		path.setSuccesful(true);
		return path;
	}

	/**
	 * Checks possible traversal for a size 1 entity.
	 * @param end The destination location.
	 * @param sizeX The x-size of the destination.
	 * @param sizeY The y-size of the destination.
	 * @param type The object type.
	 * @param rotation The object rotation.
	 * @param walkingFlag The walking flag.
	 * @param location The viewport location.
	 */
	private void checkSingleTraversal(Location end, int sizeX, int sizeY, int type, int rotation, int walkingFlag, Location location, ClipMaskSupplier clipMaskSupplier) {
		int readPosition = 0;
		int z = location.getZ();
		while (writePathPosition != readPosition) {
			curX = queueX[readPosition];
			curY = queueY[readPosition];
			readPosition = readPosition + 1 & 0xfff;
			if (curX == dstX && curY == dstY) {
				foundPath = true;
				break;
			}
			try {

				int absX = location.getX() + curX;
				int absY = location.getY() + curY;
				if (type != 0) {
					if ((type < 5 || type == 10) && canDoorInteract(absX, absY, 1, end.getX(), end.getY(), type - 1, rotation, z, clipMaskSupplier)) {
						foundPath = true;
						break;
					}
					if (type < 10 && canDecorationInteract(absX, absY, 1, end.getX(), end.getY(), type - 1, rotation, z, clipMaskSupplier)) {
						foundPath = true;
						break;
					}
				}
				if (sizeX != 0 && sizeY != 0 && canInteract(absX, absY, 1, end.getX(), end.getY(), sizeX, sizeY, walkingFlag, z, clipMaskSupplier)) {
					foundPath = true;
					break;
				}
				int thisCost = cost[curX][curY] + 1;
				if (curY > 0 && via[curX][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY - 1) & 0x12c0102) == 0) {
					check(curX, curY - 1, SOUTH_FLAG, thisCost);
				}
				if (curX > 0 && via[curX - 1][curY] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY) & 0x12c0108) == 0) {
					check(curX - 1, curY, WEST_FLAG, thisCost);
				}
				if (curY < 103 && via[curX][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY + 1) & 0x12c0120) == 0) {
					check(curX, curY + 1, NORTH_FLAG, thisCost);
				}
				if (curX < 103 && via[curX + 1][curY] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY) & 0x12c0180) == 0) {
					check(curX + 1, curY, EAST_FLAG, thisCost);
				}
				if (curX > 0 && curY > 0 && via[curX - 1][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY - 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY) & 0x12c0108) == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY - 1) & 0x12c0102) == 0) {
					check(curX - 1, curY - 1, SOUTH_WEST_FLAG, thisCost);
				}
				if (curX > 0 && curY < 103 && via[curX - 1][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + 1) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY) & 0x12c0108) == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY + 1) & 0x12c0120) == 0) {
					check(curX - 1, curY + 1, NORTH_WEST_FLAG, thisCost);
				}
				if (curX < 103 && curY > 0 && via[curX + 1][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY - 1) & 0x12c0183) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY) & 0x12c0180) == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY - 1) & 0x12c0102) == 0) {
					check(curX + 1, curY - 1, SOUTH_EAST_FLAG, thisCost);
				}
				if (curX < 103 && curY < 103 && via[curX + 1][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY + 1) & 0x12c01e0) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY) & 0x12c0180) == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY + 1) & 0x12c0120) == 0) {
					check(curX + 1, curY + 1, NORTH_EAST_FLAG, thisCost);
				}
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("curX " + curX + " curY" + curY + " via " + via[curX + 1] + via[curY + 1]);
			}
		}
	}

	/**
	 * Checks possible traversal for a size 2 entity.
	 * @param end The destination location.
	 * @param sizeX The x-size of the destination.
	 * @param sizeY The y-size of the destination.
	 * @param type The object type.
	 * @param rotation The object rotation.
	 * @param walkingFlag The walking flag.
	 * @param location The viewport location.
	 */
	private void checkDoubleTraversal(Location end, int sizeX, int sizeY, int type, int rotation, int walkingFlag, Location location, ClipMaskSupplier clipMaskSupplier) {
		int readPosition = 0;
		int z = location.getZ();
		while (writePathPosition != readPosition) {
			curX = queueX[readPosition];
			curY = queueY[readPosition];
			readPosition = readPosition + 1 & 0xfff;
			if (curX == dstX && curY == dstY) {
				foundPath = true;
				break;
			}
			int absX = location.getX() + curX;
			int absY = location.getY() + curY;
			if (type != 0) {
				if ((type < 5 || type == 10) && canDoorInteract(absX, absY, 2, end.getX(), end.getY(), type - 1, rotation, z, clipMaskSupplier)) {
					foundPath = true;
					break;
				}
				if (type < 10 && canDecorationInteract(absX, absY, 2, end.getX(), end.getY(), type - 1, rotation, z, clipMaskSupplier)) {
					foundPath = true;
					break;
				}
			}
			if (sizeX != 0 && sizeY != 0 && canInteract(absX, absY, 2, end.getX(), end.getY(), sizeX, sizeY, walkingFlag, z, clipMaskSupplier)) {
				foundPath = true;
				break;
			}
			int thisCost = cost[curX][curY] + 1;
			if (curY > 0 && via[curX][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY - 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY - 1) & 0x12c0183) == 0) {
				check(curX, curY - 1, SOUTH_FLAG, thisCost);
			}
			if (curX > 0 && via[curX - 1][curY] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + 1) & 0x12c0138) == 0) {
				check(curX - 1, curY, WEST_FLAG, thisCost);
			}
			if (curY < 102 && via[curX][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY + 2) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY + 2) & 0x12c01e0) == 0) {
				check(curX, curY + 1, NORTH_FLAG, thisCost);
			}
			if (curX < 102 && via[curX + 1][curY] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 2, absY) & 0x12c0183) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 2, absY + 1) & 0x12c01e0) == 0) {
				check(curX + 1, curY, EAST_FLAG, thisCost);
			}
			if (curX > 0 && curY > 0 && via[curX - 1][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY - 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY - 1) & 0x12c0183) == 0) {
				check(curX - 1, curY - 1, SOUTH_WEST_FLAG, thisCost);
			}
			if (curX > 0 && curY < 102 && via[curX - 1][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + 2) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY + 2) & 0x12c01e0) == 0) {
				check(curX - 1, curY + 1, NORTH_WEST_FLAG, thisCost);
			}
			if (curX < 102 && curY > 0 && via[curX + 1][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY - 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 2, absY) & 0x12c01e0) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 2, absY - 1) & 0x12c0183) == 0) {
				check(curX + 1, curY - 1, SOUTH_EAST_FLAG, thisCost);
			}
			if (curX < 102 && curY < 102 && via[curX + 1][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY + 2) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 2, absY + 2) & 0x12c01e0) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 2, absY + 1) & 0x12c0183) == 0) {
				check(curX + 1, curY + 1, NORTH_EAST_FLAG, thisCost);
			}
		}
	}

	/**
	 * Checks possible traversal for any sized entity.
	 * @param end The destination location.
	 * @param size The mover size.
	 * @param sizeX The x-size of the destination.
	 * @param sizeY The y-size of the destination.
	 * @param type The object type.
	 * @param rotation The object rotation.
	 * @param walkingFlag The walking flag.
	 * @param location The viewport location.
	 */
	private void checkVariableTraversal(Location end, int size, int sizeX, int sizeY, int type, int rotation, int walkingFlag, Location location, ClipMaskSupplier clipMaskSupplier) {
		int readPosition = 0;
		int z = location.getZ();
		main: while (writePathPosition != readPosition) {
			curX = queueX[readPosition];
			curY = queueY[readPosition];
			readPosition = readPosition + 1 & 0xfff;
			if (curX == dstX && curY == dstY) {
				foundPath = true;
				break;
			}
			int absX = location.getX() + curX;
			int absY = location.getY() + curY;
			if (type != 0) {
				if ((type < 5 || type == 10) && canDoorInteract(absX, absY, size, end.getX(), end.getY(), type - 1, rotation, z, clipMaskSupplier)) {
					foundPath = true;
					break;
				}
				if (type < 10 && canDecorationInteract(absX, absY, size, end.getX(), end.getY(), type - 1, rotation, z, clipMaskSupplier)) {
					foundPath = true;
					break;
				}
			}
			if (sizeX != 0 && sizeY != 0 && canInteract(absX, absY, size, end.getX(), end.getY(), sizeX, sizeY, walkingFlag, z, clipMaskSupplier)) {
				foundPath = true;
				break;
			}
			int thisCost = cost[curX][curY] + 1;
			south: do {
				if (curY > 0 && via[curX][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY - 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + (size - 1), absY - 1) & 0x12c0183) == 0) {
					for (int i = 1; i < size - 1; i++) {
						if ((clipMaskSupplier.getClippingFlag(z, absX + i, absY - 1) & 0x12c018f) != 0) {
							break south;
						}
					}
					check(curX, curY - 1, SOUTH_FLAG, thisCost);
				}
			} while (false);
			west: do {
				if (curX > 0 && via[curX - 1][curY] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + (size - 1)) & 0x12c0138) == 0) {
					for (int i = 1; i < size - 1; i++) {
						if ((clipMaskSupplier.getClippingFlag(z, absX - 1, absY + i) & 0x12c013e) != 0) {
							break west;
						}
					}
					check(curX - 1, curY, WEST_FLAG, thisCost);
				}
			} while (false);
			north: do {
				if (curY < 102 && via[curX][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY + size) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + (size - 1), absY + size) & 0x12c01e0) == 0) {
					for (int i = 1; i < size - 1; i++) {
						if ((clipMaskSupplier.getClippingFlag(z, absX + i, absY + size) & 0x12c01f8) != 0) {
							break north;
						}
					}
					check(curX, curY + 1, NORTH_FLAG, thisCost);
				}
			} while (false);
			east: do {
				if (curX < 102 && via[curX + 1][curY] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + size, absY) & 0x12c0183) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + size, absY + (size - 1)) & 0x12c01e0) == 0) {
					for (int i = 1; i < size - 1; i++) {
						if ((clipMaskSupplier.getClippingFlag(z, absX + size, absY + i) & 0x12c01e3) != 0) {
							break east;
						}
					}
					check(curX + 1, curY, EAST_FLAG, thisCost);
				}
			} while (false);
			southWest: do {
				if (curX > 0 && curY > 0 && via[curX - 1][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + (size - 2)) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY - 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + (size - 2), absY - 1) & 0x12c0183) == 0) {
					for (int i = 1; i < size - 1; i++) {
						if ((clipMaskSupplier.getClippingFlag(z, absX - 1, absY + (i - 1)) & 0x12c013e) != 0 || (clipMaskSupplier.getClippingFlag(z, absX + (i - 1), absY - 1) & 0x12c018f) != 0) {
							break southWest;
						}
					}
					check(curX - 1, curY - 1, SOUTH_WEST_FLAG, thisCost);
				}
			} while (false);
			northWest: do {
				if (curX > 0 && curY < 102 && via[curX - 1][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX - 1, absY + size) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX, absY + size) & 0x12c01e0) == 0) {
					for (int i = 1; i < size - 1; i++) {
						if ((clipMaskSupplier.getClippingFlag(z, absX - 1, absY + (i + 1)) & 0x12c013e) != 0 || (clipMaskSupplier.getClippingFlag(z, absX + (i - 1), absY + size) & 0x12c01f8) != 0) {
							break northWest;
						}
					}
					check(curX - 1, curY + 1, NORTH_WEST_FLAG, thisCost);
				}
			} while (false);
			southEast: do {
				if (curX < 102 && curY > 0 && via[curX + 1][curY - 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY - 1) & 0x12c010e) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + size, absY - 1) & 0x12c0183) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + size, absY + (size - 2)) & 0x12c01e0) == 0) {
					for (int i = 1; i < size - 1; i++) {
						if ((clipMaskSupplier.getClippingFlag(z, absX + size, absY + (i - 1)) & 0x12c01e3) != 0 || (clipMaskSupplier.getClippingFlag(z, absX + (i + 1), absY - 1) & 0x12c018f) != 0) {
							break southEast;
						}
					}
					check(curX + 1, curY - 1, SOUTH_EAST_FLAG, thisCost);
				}
			} while (false);
			if (curX < 102 && curY < 102 && via[curX + 1][curY + 1] == 0 && (clipMaskSupplier.getClippingFlag(z, absX + 1, absY + size) & 0x12c0138) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + size, absY + size) & 0x12c01e0) == 0 && (clipMaskSupplier.getClippingFlag(z, absX + size, absY + 1) & 0x12c0183) == 0) {
				for (int i = 1; i < size - 1; i++) {
					if ((clipMaskSupplier.getClippingFlag(z, absX + (i + 1), absY + size) & 0x12c01f8) != 0 || (clipMaskSupplier.getClippingFlag(z, absX + size, absY + (i + 1)) & 0x12c01e3) != 0) {
						continue main;
					}
				}
				check(curX + 1, curY + 1, NORTH_EAST_FLAG, thisCost);
			}
		}
	}
}