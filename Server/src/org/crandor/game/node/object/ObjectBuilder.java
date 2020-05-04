package org.crandor.game.node.object;

import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.build.LandscapeParser;
import org.crandor.game.world.update.flag.chunk.ObjectUpdateFlag;

/**
 * An aiding class for object constructing/removing.
 *
 * @author Emperor
 */
public final class ObjectBuilder {

	/**
	 * Replaces a game object.
	 *
	 * @param remove    The object to remove.
	 * @param construct The object to add.
	 * @return {@code True} if successful.
	 */
	public static boolean replace(GameObject remove, GameObject construct) {
		return replace(remove, construct, true, false);
	}

	/**
	 * Replaces a game object.
	 *
	 * @param remove    The object to remove.
	 * @param construct The object to add.
	 * @param clip      If clipping should be adjusted.
	 * @return {@code True} if successful.
	 */
	public static boolean replace(GameObject remove, GameObject construct, boolean clip, boolean permanent) {
		if (!clip) {
			return replaceClientSide(remove, construct, -1);
		}
		remove = remove.getWrapper();
		GameObject current = LandscapeParser.removeGameObject(remove);
		if (current == null) {
			if (GameWorld.getSettings().isDevMode()) {
				System.err.println("Object could not be replaced - object to remove is invalid.");
			}
			return false;
		}
		if (current.getRestorePulse() != null) {
			current.getRestorePulse().stop();
			current.setRestorePulse(null);
		}
		if (current instanceof Constructed) {
			GameObject previous = ((Constructed) current).getReplaced();
			if (previous != null && previous.equals(construct)) {
				LandscapeParser.addGameObject(previous);
				update(current, previous);
				return true;
			}
		}
		Constructed constructed = construct.asConstructed();
		if (!permanent) {
			constructed.setReplaced(current);
		}
		LandscapeParser.addGameObject(constructed);
		update(current, constructed);
		return true;
	}

	/**
	 * Replaces the object client sided alone.
	 *
	 * @param remove       The object to remove.
	 * @param construct    The object to replace with.
	 * @param restoreTicks The restoration ticks.
	 * @return {@code True} if successful.
	 */
	private static boolean replaceClientSide(final GameObject remove, final GameObject construct, int restoreTicks) {
		RegionManager.getRegionChunk(remove.getLocation()).flag(new ObjectUpdateFlag(remove, true));
		RegionManager.getRegionChunk(construct.getLocation()).flag(new ObjectUpdateFlag(construct, false));
		if (restoreTicks > 0) {
			GameWorld.Pulser.submit(new Pulse(restoreTicks) {
				@Override
				public boolean pulse() {
					return replaceClientSide(construct, remove, -1);
				}
			});
		}
		return true;
	}

	/**
	 * Replaces a game object temporarily.
	 *
	 * @param remove       The object to remove.
	 * @param construct    The object to add.
	 * @param restoreTicks The amount of ticks before the object gets restored.
	 * @return {@code True} if successful.
	 */
	public static boolean replace(GameObject remove, GameObject construct, int restoreTicks) {
		return replace(remove, construct, restoreTicks, true);
	}

	/**
	 * Replaces a game object temporarily.
	 *
	 * @param remove       The object to remove.
	 * @param construct    The object to add.
	 * @param restoreTicks The amount of ticks before the object gets restored.
	 * @return {@code True} if successful.
	 */
	public static boolean replace(GameObject remove, GameObject construct, int restoreTicks, final boolean clip) {
		if (!clip) {
			return replaceClientSide(remove, construct, restoreTicks);
		}
		remove = remove.getWrapper();
		GameObject current = LandscapeParser.removeGameObject(remove);
		if (current == null) {
			if (GameWorld.getSettings().isDevMode()) {
				System.err.println("Object could not be replaced - object to remove is invalid.");
			}
			return false;
		}
		if (current.getRestorePulse() != null) {
			current.getRestorePulse().stop();
			current.setRestorePulse(null);
		}
		if (current instanceof Constructed) {
			GameObject previous = ((Constructed) current).getReplaced();
			if (previous != null && previous.equals(construct)) {
				// Shouldn't happen.
				throw new IllegalStateException("Can't temporarily replace an already temporary object!");
			}
		}
		final Constructed constructed = construct.asConstructed();
		constructed.setReplaced(current);
		LandscapeParser.addGameObject(constructed);
		update(current, constructed);
		if (restoreTicks < 0) {
			return true;
		}
		constructed.setRestorePulse(new Pulse(restoreTicks) {
			@Override
			public boolean pulse() {
				replace(constructed, constructed.getReplaced());
				return true;
			}
		});
		GameWorld.Pulser.submit(constructed.getRestorePulse());
		return true;
	}

	/**
	 * Adds a game object.
	 *
	 * @param object The object to add.
	 * @return {@code True} if successful.
	 */
	public static Constructed add(GameObject object) {
		return add(object, -1);
	}

	/**
	 * Adds a game object.
	 *
	 * @param object The object to add.
	 * @param ticks  The amount of ticks this object should last for (-1 for
	 *               permanent).
	 * @return {@code True} if successful.
	 */
	public static Constructed add(GameObject object, int ticks, final GroundItem... items) {
		object = object.getWrapper();
		final Constructed constructed = object.asConstructed();
		LandscapeParser.addGameObject(constructed);
		update(constructed);
		if (ticks > -1) {
			GameWorld.Pulser.submit(new Pulse(ticks, object) {
				@Override
				public boolean pulse() {
					remove(constructed);
					if (items != null) {
						for (int i = 0; i < items.length; i++) {
							GroundItemManager.create(items[i]);
						}
					}
					return true;
				}
			});
		}
		return constructed;
	}

	/**
	 * Removes all objects within a box
	 * @param objectId - the object id to remove
	 * @param southWest
	 * @param northEast
	 * @return
	 */
	public static boolean removeAll(int objectId, Location southWest, Location northEast) {
		if (southWest.getX() > northEast.getX() || southWest.getY() > northEast.getY())
			return false;

		int differenceX = northEast.getX() - southWest.getX();
		int differenceY = northEast.getY() - southWest.getY();

		for (int x = 0; x <= differenceX; x++) {
			for (int y = 0; y <= differenceY; y++){
				GameObject object = new GameObject(objectId, Location.create(southWest.getX() + x, southWest.getY() + y, southWest.getZ()));
				remove(object);
			}
		}
		return true;
	}

	/**
	 * Removes a game object.
	 *
	 * @param object The object to remove.
	 * @return {@code True} if successful.
	 */
	public static boolean remove(GameObject object) {
		if (object == null) {
			return false;
		}
		object = object.getWrapper();
		GameObject current = LandscapeParser.removeGameObject(object);
		if (current == null) {
			return false;
		}
		update(current);
		return true;
	}

	/**
	 * Removes a game object.
	 *
	 * @param object       the object.
	 * @param respawnTicks the respawn ticks.
	 * @return {@code True}if removed.
	 */
	public static boolean remove(final GameObject object, int respawnTicks) {
		if (remove(object)) {
			GameWorld.Pulser.submit(new Pulse(respawnTicks) {

				@Override
				public boolean pulse() {
					add(object);
					return true;
				}

			});
			return true;
		}
		return false;
	}

	/**
	 * Updates the game object on all the player's screen.
	 *
	 * @param objects The game objects.
	 */
	public static void update(GameObject... objects) {
		for (GameObject o : objects) {
			if (o == null) {
				continue;
			}
			RegionManager.getRegionChunk(o.getLocation()).flag(new ObjectUpdateFlag(o, !o.isActive()));
		}
	}
}