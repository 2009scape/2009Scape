package org.crandor.game.world.map.zone.impl;

import org.crandor.game.component.Component;
import org.crandor.game.content.global.LightSource;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.RegionZone;
import org.crandor.game.world.map.zone.ZoneBorders;

/**
 * Handles a dark area.
 * @author Emperor
 */
public final class DarkZone extends MapZone {

	/**
	 * The darkness overlay.
	 */
	public static final Component DARKNESS_OVERLAY = new Component(96) {
		@Override
		public void open(final Player player) {
			Pulse pulse = player.getExtension(DarkZone.class);
			if (pulse != null && pulse.isRunning()) {
				return;
			}
			pulse = new Pulse(2, player) {
				int count = 0;

				@Override
				public boolean pulse() {
					if (count == 0) {
						player.getPacketDispatch().sendMessage("You hear tiny insects skittering over the ground...");
					} else if (count == 5) {
						player.getPacketDispatch().sendMessage("Tiny biting insects swarm all over you!");
					} else if (count > 5) {
						player.getImpactHandler().manualHit(player, 1, HitsplatType.NORMAL);
					}
					count++;
					return false;
				}
			};
			GameWorld.Pulser.submit(pulse);
			player.addExtension(DarkZone.class, pulse);
			super.open(player);
		}

		@Override
		public boolean close(final Player player) {
			if (!super.close(player)) {
				return false;
			}
			Pulse pulse = player.getExtension(DarkZone.class);
			if (pulse != null) {
				pulse.stop();
			}
			return true;
		}
	};

	/**
	 * Constructs a new {@code DarkZone} {@code Object}.
	 */
	public DarkZone() {
		super("Dark zone", true);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(1728, 5120, 1791, 5247));
		registerRegion(12693);
		registerRegion(12949);
	}

	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (e instanceof Player) {
			Player player = (Player) e;
			if (player.getInterfaceManager().getOverlay() != DARKNESS_OVERLAY) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof Item) {
			Item item = (Item) target;
			LightSource s = LightSource.forProductId(item.getId());
			if (s != null) {
				String name = option.getName().toLowerCase();
				if (name.equals("drop")) {
					((Player) e).getPacketDispatch().sendMessage("Dropping the " + s.getName() + " would leave you without a light source.");
					return true;
				}
				if (name.equals("extinguish")) {
					((Player) e).getPacketDispatch().sendMessage("Extinguishing the " + s.getName() + " would leave you without a light source.");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			final Player player = (Player) e;
			LightSource source = LightSource.getActiveLightSource(player);
			if (source == null) {
				player.getInterfaceManager().openOverlay(DARKNESS_OVERLAY);
			} else if (source.getInterfaceId() > 0) {
				player.getInterfaceManager().openOverlay(new Component(source.getInterfaceId()));
			}
		}
		return true;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			((Player) e).getInterfaceManager().closeOverlay();
		}
		return true;
	}

	/**
	 * Updates the overlay.
	 * @param player The player.
	 */
	public void updateOverlay(Player player) {
		LightSource source = LightSource.getActiveLightSource(player);
		int overlay = -1;
		if (player.getInterfaceManager().getOverlay() != null) {
			overlay = player.getInterfaceManager().getOverlay().getId();
		}
		if (source == null) {
			if (overlay != DARKNESS_OVERLAY.getId()) {
				player.getInterfaceManager().openOverlay(DARKNESS_OVERLAY);
			}
			return;
		}
		Pulse pulse = player.getExtension(DarkZone.class);
		if (pulse != null) {
			pulse.stop();
		}
		if (source.getInterfaceId() != overlay) {
			if (source.getInterfaceId() == -1) {
				player.getInterfaceManager().closeOverlay();
				return;
			}
			player.getInterfaceManager().openOverlay(new Component(source.getInterfaceId()));
		}
	}

	/**
	 * Checks if the player is in a dark area and will update accordingly.
	 * @param p The player.
	 */
	public static void checkDarkArea(Player p) {
		for (RegionZone r : p.getZoneMonitor().getZones()) {
			if (r.getZone() instanceof DarkZone) {
				DarkZone zone = (DarkZone) r.getZone();
				zone.updateOverlay(p);
				break;
			}
		}
	}
}