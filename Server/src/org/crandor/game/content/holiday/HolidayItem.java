package org.crandor.game.content.holiday;

import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for releasing trade-able holiday items.
 * @author Emperor
 */
public final class HolidayItem extends GroundItem {

	/**
	 * The location of a holiday item spawn.
	 */
	private static Map<Integer, Location> itemLoc = new HashMap<>();
	
	/**
	 * Constructs a new {@code HolidayItem} {@code Object}.
	 * @param item the item.
	 * @param location the location.
	 */
	public HolidayItem(Item item, Location location) {
		super(item, location);
	}

	/**
	 * Starts random holiday item spawning.
	 * @param spawn The item.
	 * @param interval The interval between moving the location.
	 * @param spawnLocations The spawn locations.
	 */
	public static void startRandomSpawn(final Item spawn, int interval, final Location... spawnLocations) {
		GameWorld.Pulser.submit(new Pulse(interval) {
			GroundItem item;
			int lastIndex = -1;

			@Override
			public boolean pulse() {
				if (item != null) {
					if (!item.isRemoved()) {
						Graphics.send(Graphics.create(85), item.getLocation());
					} else if (ItemLimitation.decreaseAmount(spawn.getId())) {
						System.out.println("Released all " + spawn.getName() + "s!");
						return true;
					}
					GroundItemManager.destroy(item);
				}
				int index = RandomFunction.random(spawnLocations.length);
				if (index == lastIndex) {
					index = (index + 1) % spawnLocations.length;
				}
				spawn.setLocation(spawnLocations[index]);
				item = GroundItemManager.create(new HolidayItem(spawn, spawnLocations[index]));
				itemLoc.put(item.getId(), item.getLocation());
				lastIndex = index;
				return false;
			}
		});
	}

	@Override
	public String toString() {
		return "HolidaySpawn [name=" + getName() + ", loc=" + getLocation() + "]";
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public boolean isPrivate() {
		return false;
	}

	@Override
	public boolean isAutoSpawn() {
		return false;
	}

	@Override
	public void respawn() {
		/*
		 * empty.
		 */
	}
	
	/**
	 * Returns the location of a holiday item.
	 * @param itemId the itemId to check
	 * @return location of the item
	 */
	public static Location getHolidayItemLocation(int itemId) {
		if (itemLoc.get(itemId) != null) {
			return itemLoc.get(itemId);			
		}
		return null;
	}
}