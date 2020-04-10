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
		register(new ZoneBorders(3210, 9333, 3339, 9424));
		register(new ZoneBorders(2607, 3296, 2644, 3332));
		register(new ZoneBorders(2949, 3370, 3001, 3392));
		register(new ZoneBorders(3250, 9800, 3342, 9870));
		register(new ZoneBorders(3190, 3648, 3327, 3839));
		register(new ZoneBorders(3200, 3840, 3390, 3967));
		register(new ZoneBorders(2992, 3912, 3007, 3967));
		register(new ZoneBorders(2946, 3816, 2959, 3831));
		register(new ZoneBorders(3008, 3856, 3199, 3903));
		register(new ZoneBorders(3008, 3600, 3071, 3711));
		register(new ZoneBorders(3072, 3608, 3327, 3647));
		register(new ZoneBorders(2624, 2550, 2690, 2619));
		register(new ZoneBorders(2371, 5062, 2422, 5117));
		register(new ZoneBorders(2896, 3595, 2927, 3630));
		register(new ZoneBorders(2820, 5250, 2955, 5370));
		register(new ZoneBorders(2892, 4435, 2932, 4464));
		register(new ZoneBorders(2724, 5071, 2747, 5109));
		register(new ZoneBorders(2256, 4680, 2287, 4711));
		register(new ZoneBorders(3107, 3234, 3134, 3259));
		register(new ZoneBorders(2931, 3514, 2940, 3518));
		register(new ZoneBorders(2869, 3687, 2940, 3839));
		register(new ZoneBorders(1728, 5120, 1791, 5247));
		register(new ZoneBorders(3136, 3523, 3328, 3710));
		registerRegion(13105, new ZoneBorders(3282, 3159, 3303, 3177));
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