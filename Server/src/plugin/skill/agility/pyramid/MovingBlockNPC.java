package plugin.skill.agility.pyramid;

import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.MovementHook;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * The moving block npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class MovingBlockNPC extends AbstractNPC {

	/**
	 * The tile locations.
	 */
	private static final Location[][] LOCATIONS = new Location[][] { { Location.create(3366, 2847, 3), Location.create(3367, 2847, 3), Location.create(3367, 2848, 3), Location.create(3366, 2848, 3), Location.create(3365, 2847, 3), Location.create(3365, 2848, 3), Location.create(3368, 2847, 3), Location.create(3368, 2848, 3) }, { Location.create(3374, 2847, 1), Location.create(3374, 2848, 1), Location.create(3375, 2848, 1), Location.create(3375, 2847, 1), Location.create(3374, 2849, 1), Location.create(3375, 2849, 1), Location.create(3374, 2846, 1), Location.create(3375, 2846, 1) } };

	/**
	 * The next move delay.
	 */
	private int nextMove;

	/**
	 * If its moving.
	 */
	private boolean moving;

	/**
	 * Constructs a new {@code MovingBlockNPC} {@code Object}.
	 */
	public MovingBlockNPC() {
		super(-1, null);
	}

	/**
	 * Constructs a new {@code MovingBlockNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public MovingBlockNPC(int id, Location location) {
		super(id, location, false);
	}

	@Override
	public void tick() {
		super.tick();
		if (nextMove < GameWorld.getTicks() && nextMove != -1) {
			Direction dir = getId() == 3124 ? Direction.EAST : Direction.NORTH;
			Location loc = getLocation().transform(dir, 2);
			getWalkingQueue().reset();
			getWalkingQueue().addPath(loc.getX(), loc.getY());
			for (Player p : RegionManager.getLocalPlayers(getTileLocations()[0], 2)) {
				checkBlock(p);
			}
			moving = true;
			GameWorld.Pulser.submit(new Pulse(1, this) {
				int counter;

				@Override
				public boolean pulse() {
					switch (++counter) {
					case 1:
						for (Player p : RegionManager.getLocalPlayers(getTileLocations()[0], 2)) {
							checkBlock(p);
						}
						break;
					case 3:
						getWalkingQueue().reset();
						getWalkingQueue().addPath(getProperties().getSpawnLocation().getX(), getProperties().getSpawnLocation().getY());
						nextMove = GameWorld.getTicks() + 13;
						break;
					case 5:
						moving = false;
						return true;
					}
					return false;
				}
			});
			nextMove = -1;
		}
	}

	@Override
	public void init() {
		super.init();
		final MovingBlockTrap trap = new MovingBlockTrap();
		for (Location l : getTileLocations()) {
			AgilityPyramidZone.LOCATION_TRAPS.put(l, trap);
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MovingBlockNPC(id, location);
	}

	/**
	 * Checks if a player is going to get hit by a block.
	 * @param player the player.
	 */
	public void checkBlock(final Player player) {
		if (player.getAttribute("block-move", -1) > GameWorld.getTicks()) {
			return;
		}
		Location[] locs = getTileLocations();
		for (int i = 0; i < 4; i++) {
			if (locs[i].equals(player.getLocation())) {
				GameWorld.Pulser.submit(new Pulse(1, player) {
					@Override
					public boolean pulse() {
						boolean close = getProperties().getSpawnLocation().getDistance(player.getLocation()) > (getId() == 3124 ? 2 : 2.3);
						Location dest = null;
						if (getId() == 3124) {
							if (player.getLocation().getY() == 2847) {
								dest = Location.create(3376, 2847, 1);
							} else if (player.getLocation().getY() == 2848) {
								dest = Location.create(3376, 2848, 1);
							}
						} else {
							if (player.getLocation().getX() == 3366) {
								dest = Location.create(3366, 2849, 3);
							} else if (player.getLocation().getX() == 3367) {
								dest = Location.create(3367, 2849, 3);
							}
						}
						player.lock(4);
						player.setAttribute("block-move", GameWorld.getTicks() + 4);
						AgilityHandler.failWalk(player, close ? 1 : 3, player.getLocation(), dest, AgilityPyramidCourse.transformLevel(dest), Animation.create(3066), 10, 8, null, getId() == 3124 ? Direction.WEST : Direction.SOUTH);
						return true;
					}
				});
				return;
			}
		}
	}

	/**
	 * Gets the tile locations.
	 * @return the locations.
	 */
	public Location[] getTileLocations() {
		return LOCATIONS[3125 - getId()];
	}

	@Override
	public int[] getIds() {
		return new int[] { 3124, 3125 };
	}

	/**
	 * The movement hook to handle a moving block.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class MovingBlockTrap implements MovementHook {

		@Override
		public boolean handle(Entity e, Location l) {
			if (moving) {
				return false;
			}
			return true;
		}

	}
}
