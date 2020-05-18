package org.crandor.game.world.map.zone;

import org.crandor.game.world.callback.CallBack;
import org.crandor.game.world.map.zone.impl.*;


/**
 * Loads all the default zones.
 * @author Emperor
 */
public class ZoneBuilder implements CallBack {

	@Override
	public boolean call() {
		configure(WildernessZone.getInstance());
		configure(MultiwayCombatZone.getInstance());
		configure(new ModeratorZone());
		configure(new DarkZone());
		configure(new KaramjaZone());
		configure(new BankZone());
		return true;
	}

	/**
	 * Configures the map zone.
	 * @param zone The map zone.
	 */
	public static void configure(MapZone zone) {
		zone.setUid(zone.getName().hashCode());
		zone.configure();
	}
}