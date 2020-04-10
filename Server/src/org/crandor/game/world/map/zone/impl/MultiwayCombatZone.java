package org.crandor.game.world.map.zone.impl;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.MapDistance;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.InterfaceContext;
import org.crandor.net.packet.out.CloseInterface;
import org.crandor.net.packet.out.Interface;

/**
 * Handles the multiway combat zones.
 * @author Emperor
 */
public final class MultiwayCombatZone extends MapZone {

	/**
	 * The instance.
	 */
	private static final MultiwayCombatZone INSTANCE = new MultiwayCombatZone();

	/**
	 * Constructs a new {@code MultiwayCombatZone} {@code Object}.
	 */
	private MultiwayCombatZone() {
		super("Multicombat", true);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2055, 2405, 3970, 3818));
		registerRegion(12341);
		// Kalphite queen lair
		registerRegion(13972);
		// Abbys area
		registerRegion(12107);
		registerRegion(7505);
		// Rock crabs.
		registerRegion(10554);
		// Wizard's tower
		registerRegion(12337);
		// zmi
		registerRegion(13131);
		// Kraken
		registerRegion(14682);
		//top of GE
		register(new ZoneBorders(3154, 3483, 3176, 3500, 2));
		// Tzhaar caves
		register(new ZoneBorders(2424, 5105, 2536, 5183));
		register(new ZoneBorders(2487, 10113, 2563, 10174));
		registerRegion(7236);
		registerRegion(7492);
		registerRegion(7748);
		registerRegion(12610);
		register(new ZoneBorders(3097, 4224, 3225, 4317));
		register(new ZoneBorders(3116, 5412, 3362, 5584));
		register(new ZoneBorders(3078, 5520, 3123, 5552, 0));
		registerRegion(11844); //Corporeal beast
		registerRegion(10329);//TDS
		registerRegion(13370);//Venenatis
		registerRegion(12603);//Callisto
		registerRegion(14939);//Kalphite Stronghold Cave
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			boolean resizable = p.getInterfaceManager().isResizable();
			PacketRepository.send(Interface.class, new InterfaceContext(p, p.getInterfaceManager().getWindowPaneId(), resizable ? 17 : 7, 745, true));
			p.getPacketDispatch().sendInterfaceConfig(745, 1, false);
		}
		e.getProperties().setMultiZone(true);
		return super.enter(e);
	}
	
	@Override
	public boolean leave(Entity e, boolean logout) {
		if (!logout && e instanceof Player) {
			Player p = (Player) e;
			boolean resizable = p.getInterfaceManager().isResizable();
			PacketRepository.send(CloseInterface.class, new InterfaceContext(p, p.getInterfaceManager().getWindowPaneId(), resizable ? 17 : 7, 745, true));
		}
		e.getProperties().setMultiZone(false);
		return super.leave(e, logout);
	}

	@Override
	public boolean move(Entity e, Location loc, Location destination) {
		// Disables "NPC stacking".
		if (e.getProperties().isNPCWalkable()) {
			return true;
		}
		boolean pestControl = e.getViewport().getRegion().getRegionId() == 10536;
		boolean player = e instanceof Player;
		if (!player) {
			Direction dir = Direction.getDirection(loc, destination);
			if (dir.getStepX() != 0 && dir.getStepY() != 0) {
				return true; // Allow diagonal steps so people can still "stack"
				// npcs (see barraging mummies)
			}
		}
		if (e instanceof NPC || pestControl) {
			for (NPC n : RegionManager.getLocalNpcs(e, MapDistance.RENDERING.getDistance() / 2)) {
				if (n.isInvisible() || !n.getDefinition().hasAttackOption() || n == e) {
					continue;
				}
				if (player && pestControl && !(n.getId() >= 3772 && n.getId() <= 3776)) {
					continue;
				}
				Location l = n.getLocation();
				// TODO: Better support for sizes.
				int s = n.size() - 1;
				int x = destination.getX();
				int y = destination.getY();
				if (x > l.getX()) {
					x += e.size() - 1;
				}
				if (y > l.getY()) {
					y += e.size() - 1;
				}
				if (l.getX() <= x && l.getY() <= y && (l.getX() + s) >= x && (l.getY() + s) >= y) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Gets the instance.
	 * @return The instance.
	 */
	public static MultiwayCombatZone getInstance() {
		return INSTANCE;
	}

}