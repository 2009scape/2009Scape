package org.crandor.game.world.map;

import org.crandor.cache.Cache;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.music.MusicZone;
import org.crandor.game.system.mysql.impl.RegionSQLHandler;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.build.LandscapeParser;
import org.crandor.game.world.map.build.MapscapeParser;
import org.crandor.game.world.map.zone.RegionZone;
import org.crandor.game.world.repository.Repository;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a region.
 *
 * @author Emperor
 */
public class Region {

	/**
	 * The default size of a region.
	 */
	public static final int SIZE = 64;

	/**
	 * The region x-coordinate.
	 */
	private final int x;

	/**
	 * The region y-coordinate.
	 */
	private final int y;

	/**
	 * The region planes.
	 */
	private final RegionPlane[] planes = new RegionPlane[4];

	/**
	 * The activity pulse.
	 */
	private final Pulse activityPulse;

	/**
	 * The region zones lying in this region.
	 */
	private final List<RegionZone> regionZones = new ArrayList<>();

	/**
	 * The music zones lying in this region.
	 */
	private final List<MusicZone> musicZones = new ArrayList<>();

	/**
	 * If the region is active.
	 */
	private boolean active;

	/**
	 * The amount of objects in this region.
	 */
	private int objectCount;

	/**
	 * If the region has flags.
	 */
	private boolean hasFlags;

	/**
	 * If the region has been loaded.
	 */
	private boolean loaded;

	/**
	 * The amount of players viewing this region.
	 */
	private int viewAmount;

	/**
	 * If the region can be edited.
	 */
	private boolean build;

	/**
	 * If all planes should be updated when in this region (instead of just current one).
	 */
	private boolean updateAllPlanes;

	/**
	 * Constructs a new {@code Region} {@code Object}.
	 * @param x The x-coordinate of the region.
	 * @param y The y-coordinate of the region.
	 */
	public Region(int x, int y) {
		this.x = x;
		this.y = y;
		for (int plane = 0; plane < 4; plane++) {
			planes[plane] = new RegionPlane(this, plane);
		}
		this.activityPulse = new Pulse(50) {
			@Override
			public boolean pulse() {
				flagInactive();
				return true;
			}
		};
		activityPulse.stop();
	}

	/**
	 * Gets the base location.
	 * @return The base location.
	 */
	public Location getBaseLocation() {
		return Location.create(x << 6, y << 6, 0);
	}

	/**
	 * Adds a region zone to this region.
	 * @param zone The region zone.
	 */
	public void add(RegionZone zone) {
		regionZones.add(zone);
		for (RegionPlane plane : planes) {
			for (NPC npc : plane.getNpcs()) {
				npc.getZoneMonitor().updateLocation(npc.getLocation());
			}
			for (Player p : plane.getPlayers()) {
				p.getZoneMonitor().updateLocation(p.getLocation());
			}
		}
	}

	/**
	 * Adds a player to this region.
	 * @param player The player.
	 */
	public void add(Player player) {
		planes[player.getLocation().getZ()].add(player);
		flagActive();
	}

	/**
	 * Adds an npc to this region.
	 * @param npc The npc.
	 */
	public void add(NPC npc) {
		planes[npc.getLocation().getZ()].add(npc);
	}

	/**
	 * Removes an NPC from this region.
	 * @param npc The NPC.
	 */
	public void remove(NPC npc) {
		RegionPlane plane = npc.getViewport().getCurrentPlane();
		if (plane != null && plane != planes[npc.getLocation().getZ()]) {
			plane.remove(npc);
		}
		planes[npc.getLocation().getZ()].remove(npc);
	}

	/**
	 * Removes a player from this region.
	 * @param player The player.
	 */
	public void remove(Player player) {
		player.getViewport().getCurrentPlane().remove(player);
		checkInactive();
	}

	/**
	 * Checks if the region is inactive, if so it will start the inactivity flagging.
	 * @return {@code True} if the region is inactive.
	 */
	public boolean checkInactive() {
		return isInactive(true);
	}

	/**
	 * Checks if the region is inactive.
	 * @param runPulse If the pulse for flagging the region as inactive should be ran.
	 * @return {@code True} if so.
	 */
	public boolean isInactive(boolean runPulse) {
		if (isViewed()) {
			return false;
		}
		for (RegionPlane p : planes) {
			if (!p.getPlayers().isEmpty()) {
				return false;
			}
		}
		if (runPulse) {
			if (!activityPulse.isRunning()) {
				activityPulse.restart();
				activityPulse.start();
				GameWorld.Pulser.submit(activityPulse);
			}
		}
		return true;
	}

	/**
	 * Checks if this region has the inactivity flagging pulse running.
	 * @return {@code True} if so.
	 */
	public boolean isPendingRemoval() {
		return activityPulse.isRunning();
	}

	/**
	 * Flags the region as active.
	 */
	public void flagActive() {
		activityPulse.stop();
		if (!active) {
			active = true;
			load(this);
			for (RegionPlane r : planes) {
				for (NPC n : r.getNpcs()) {
					if (n.isActive()) {
						Repository.addRenderableNPC(n);
					}
				}
			}
		}
	}

	/**
	 * Flags the region as inactive.
	 */
	protected void flagInactive() {
		unload(this);
		active = false;
	}

	/**
	 * Loads the flags for a region.
	 * @param r The region.
	 */
	public static void load(Region r) {
		load(r, r.build);
	}

	/**
	 * Loads the flags for a region.
	 * @param r The region.
	 * @param build if all objects in this region should be stored (rather than just the ones with options).
	 */
	public static void load(Region r, boolean build) {
		try {
			if (r.isLoaded() && r.isBuild() == build) {
				return;
			}
			r.build = build;
			boolean dynamic = r instanceof DynamicRegion;
			int regionId = dynamic ? ((DynamicRegion) r).getRegionId() : r.getId();
			int regionX = regionId >> 8 & 0xFF;
			int regionY = regionId & 0xFF;
			int mapscapeId = Cache.getIndexes()[5].getArchiveId("m" + regionX + "_"+ regionY);
			byte[][][] mapscapeData = new byte[4][SIZE][SIZE];
			for (RegionPlane plane : r.planes) {
				plane.getFlags().setLandscape(new boolean[SIZE][SIZE]);
				plane.getFlags().setClippingFlags(new int[SIZE][SIZE]);
				plane.getProjectileFlags().setClippingFlags(new int[SIZE][SIZE]);
			}
			if (mapscapeId > -1) {
				ByteBuffer mapscape = ByteBuffer.wrap(Cache.getIndexes()[5].getCacheFile().getContainerUnpackedData(mapscapeId));
				MapscapeParser.parse(r, mapscapeData, mapscape);
			}
			r.hasFlags = dynamic;
			r.setLoaded(true);
			int landscapeId = Cache.getIndexes()[5].getArchiveId("l" + regionX + "_" + regionY);
			if (landscapeId > -1) {
				byte[] landscape = Cache.getIndexes()[5].getFileData(landscapeId, 0, RegionSQLHandler.getRegionXTEA(regionId));
				if (landscape == null || landscape.length < 4) {
					return;
				}
				r.hasFlags = true;
				try {
					LandscapeParser.parse(r, mapscapeData, ByteBuffer.wrap(landscape), build);
				} catch (Throwable t) {
					new Throwable("Failed parsing region " + regionId + "!", t).printStackTrace();
				}
			}
			MapscapeParser.clipMapscape(r, mapscapeData);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Unloads a region.
	 * @param r The region.
	 */
	private static void unload(Region r) {
		if (r.isViewed()) {
			System.err.println("Players viewing region!");
			r.flagActive();
			return;
		}
		for (RegionPlane p : r.planes) {
			if (!p.getPlayers().isEmpty()) {
				System.err.println("Players still in region!");
				r.flagActive();
				return;
			}
		}
		for (RegionPlane p : r.planes) {
			p.clear();
			if (!(r instanceof DynamicRegion)) {
				for (NPC n : p.getNpcs()) {
					n.onRegionInactivity();
				}
			}
		}
	}

	/**
	 * Checks if the region is being viewed by a player.
	 * @return {@code True} if so.
	 */
	public boolean isViewed() {
		synchronized (this) {
			return viewAmount > 0;
		}
	}

	/**
	 * Increments the view amount.
	 * @return The view amount after incrementing.
	 */
	public int incrementViewAmount() {
		synchronized (this) {
			return ++viewAmount;
		}
	}

	/**
	 * Decrements the amount of viewers.
	 * @return The view amount after decrementing.
	 */
	public int decrementViewAmount() {
		synchronized (this) {
			if (viewAmount < 1) {
				//System.err.println("View amount is " + (viewAmount - 1));
				viewAmount++;
			}
			return --viewAmount;
		}
	}

	/**
	 * Gets the active.
	 * @return The active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 * @param active The active to set.
	 * @deprecated This should not be used, instead use the {@link #flagInactive()},
	 * 				{@link #flagActive()} & {@link #checkInactive()} methods to safely change the activity state.
	 */
	@Deprecated
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets the region id.
	 * @return The region id.
	 */
	public int getId() {
		return x << 8 | y;
	}

	/**
	 * Gets the real region id (this returns the copied region id for dynamic regions).
	 * @return The region  id.
	 */
	public int getRegionId() {
		return getId();
	}

	/**
	 * Gets the x.
	 * @return The x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y.
	 * @return The y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the planes.
	 * @return The planes.
	 */
	public RegionPlane[] getPlanes() {
		return planes;
	}

	/**
	 * Gets the regionZones.
	 * @return The regionZones.
	 */
	public List<RegionZone> getRegionZones() {
		return regionZones;
	}

	/**
	 * Gets the musicZones.
	 * @return The musicZones.
	 */
	public List<MusicZone> getMusicZones() {
		return musicZones;
	}

	/**
	 * Gets the object count.
	 * @return The object count.
	 */
	public int getObjectCount() {
		return objectCount;
	}

	/**
	 * Sets the object count.
	 * @param objectCount The object count.
	 */
	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}

	/**
	 * Gets the hasFlags.
	 * @return The hasFlags.
	 */
	public boolean isHasFlags() {
		return hasFlags;
	}

	/**
	 * Sets the hasFlags.
	 * @param hasFlags The hasFlags to set.
	 */
	public void setHasFlags(boolean hasFlags) {
		this.hasFlags = hasFlags;
	}

	/**
	 * Sets the region time out duration.
	 * @param ticks The amount of ticks before the region is flagged as inactive.
	 */
	public void setRegionTimeOut(int ticks) {
		activityPulse.setDelay(ticks);
	}

	/**
	 * Gets the loaded.
	 * @return The loaded.
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * Sets the loaded.
	 * @param loaded The loaded to set.
	 */
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * Gets the viewAmount.
	 * @return The viewAmount.
	 */
	public int getViewAmount() {
		return viewAmount;
	}

	/**
	 * Sets the viewAmount.
	 * @param viewAmount The viewAmount to set.
	 */
	public void setViewAmount(int viewAmount) {
		this.viewAmount = viewAmount;
	}

	/**
	 * Gets the build.
	 * @return the build
	 */
	public boolean isBuild() {
		return build;
	}

	/**
	 * Sets the build.
	 * @param build the build to set.
	 */
	public void setBuild(boolean build) {
		this.build = build;
	}

	/**
	 * Gets the updateAllPlanes value.
	 * @return The updateAllPlanes.
	 */
	public boolean isUpdateAllPlanes() {
		return updateAllPlanes;
	}

	/**
	 * Sets the updateAllPlanes value.
	 * @param updateAllPlanes The updateAllPlanes to set.
	 */
	public void setUpdateAllPlanes(boolean updateAllPlanes) {
		this.updateAllPlanes = updateAllPlanes;
	}
}