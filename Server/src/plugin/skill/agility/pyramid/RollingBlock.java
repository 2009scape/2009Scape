package plugin.skill.agility.pyramid;

import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.MovementHook;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the rolling block obstacle.
 * @author 'Vexia
 * @version 1.0
 */
public final class RollingBlock implements MovementHook {

	@Override
	public boolean handle(final Entity e, final Location dest) {
		final Player player = (Player) e;
		final BlockSets set = BlockSets.forLocation(dest);
		final GameObject stone = RegionManager.getObject(set.getBlockLocation());
		final boolean backwards = isBackwards(stone.getDirection(), player.getDirection(), stone.getRotation());
		final boolean fail = backwards || AgilityHandler.hasFailed(player, 2, 0.3);
		player.lock(5);
		AgilityPyramidCourse.addConfig(player, stone, 1, false);
		e.addExtension(LogoutTask.class, new LocationLogoutTask(5, player.getLocation()));
		if (fail) {
			GameWorld.Pulser.submit(new Pulse(1, player) {
				int counter;

				@Override
				public boolean pulse() {
					switch (++counter) {
					case 1:
						final Location end = player.getLocation().transform(stone.getDirection(), -2);
						final int hit = backwards ? 1 : 6;
						if (set == BlockSets.FOUR) {
							AgilityHandler.failWalk(player, 3, dest, end, AgilityPyramidCourse.transformLevel(end), Animation.create(1116), 10, hit, null);
						} else {
							AgilityHandler.failWalk(player, -1, dest, AgilityPyramidCourse.transformLevel(end), AgilityPyramidCourse.transformLevel(end), Animation.create(1116), 10, hit, null);
						}
						break;
					case 3:
						AgilityPyramidCourse.addConfig(player, stone, 0, true);
						return true;
					}
					return false;
				}
			});
			return false;
		}
		AgilityHandler.forceWalk(player, -1, dest, dest.transform(player.getDirection(), 2), Animation.create(1115), 20, 12, null);
		GameWorld.Pulser.submit(new Pulse(3, player) {
			@Override
			public boolean pulse() {
				AgilityPyramidCourse.addConfig(player, stone, 0, true);
				return true;
			}
		});
		return false;
	}

	/**
	 * Checks if the player is going backwards.
	 * @param direction the object dir.
	 * @param dir the player dir.
	 * @param rotation the rotation of the object.
	 * @return {@code True} if so.
	 */
	public boolean isBackwards(Direction direction, Direction dir, int rot) {
		switch (direction) {
		case NORTH:
			break;
		case SOUTH:
			return rot == 2 & dir == Direction.WEST;
		case EAST:
			return rot == 1 && dir == Direction.SOUTH;
		case WEST:
			return rot == 3 && dir == Direction.NORTH;
		default:
			break;
		}
		return false;
	}

	/**
	 * A block set.
	 * @author 'Vexia
	 */
	public enum BlockSets {
		ONE(Location.create(3354, 2841, 0), Location.create(3354, 2842, 1), Location.create(3355, 2842, 1), Location.create(3355, 2841, 1), Location.create(3354, 2841, 1)), TWO(Location.create(3374, 2835, 0), Location.create(3374, 2835, 1), Location.create(3374, 2836, 1), Location.create(3375, 2836, 1), Location.create(3375, 2835, 1)), THREE(Location.create(3368, 2849, 1), Location.create(3368, 2849, 2), Location.create(3369, 2849, 2), Location.create(3369, 2850, 2), Location.create(3368, 2850, 2)), FOUR(Location.create(3048, 4699, 1), Location.create(3048, 4700, 2), Location.create(3049, 4700, 2), Location.create(3049, 4699, 2), Location.create(3048, 4699, 2)), FIVE(Location.create(3044, 4699, 2), Location.create(3044, 4700, 3), Location.create(3045, 4700, 3), Location.create(3045, 4699, 3), Location.create(3044, 4699, 3));

		/**
		 * The block location.
		 */
		private final Location blockLocation;

		/**
		 * The tile locations.
		 */
		private final Location[] tiles;

		/**
		 * Constructs a new {@code BlockSets} {@code Object}.
		 * @param blockLocation the block location.
		 * @param tiles the tiles.
		 */
		private BlockSets(Location blockLocation, Location... tiles) {
			this.blockLocation = blockLocation;
			this.tiles = tiles;
		}

		/**
		 * Gets the set for the location.
		 * @param location the location.
		 * @return the block sets.
		 */
		public static BlockSets forLocation(Location location) {
			for (BlockSets set : values()) {
				for (Location loc : set.getTiles()) {
					if (loc.equals(location)) {
						return set;
					}
				}
			}
			return null;
		}

		/**
		 * Gets the blockLocation.
		 * @return The blockLocation.
		 */
		public Location getBlockLocation() {
			return blockLocation;
		}

		/**
		 * Gets the tiles.
		 * @return The tiles.
		 */
		public Location[] getTiles() {
			return tiles;
		}

		/**
		 * static-block to load block traps.
		 */
		static {
			final RollingBlock block = new RollingBlock();
			for (BlockSets set : values()) {
				for (Location l : set.getTiles()) {
					AgilityPyramidZone.LOCATION_TRAPS.put(l, block);
				}
			}
		}
	}
}
