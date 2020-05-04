package plugin.activity.magearena;

import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.plugin.Plugin;

/**
 * Handles the mage arena zone.
 * @author Vexia
 */
public final class MageArenaZone extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code MageArenaZone} {@code Object}.
	 */
	public MageArenaZone() {
		super("mage arena", true, ZoneRestriction.RANDOM_EVENTS);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public boolean enter(Entity e) {
		return super.enter(e);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			final Player p = (Player) e;
			if (!logout) {
				GameWorld.Pulser.submit(new Pulse(1, e) {
					@Override
					public boolean pulse() {
						if (!p.getZoneMonitor().isInZone("mage arena")) {
							if (hasSession(p)) {
								getSession(p).close();
							}
						}
						return true;
					}
				});
				return true;
			}
			if (hasSession(p)) {
				getSession(p).close();
			}
			if (logout && hasSession(p)) {
				p.setLocation(Location.create(2540, 4717, 0));
			}
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (style != CombatStyle.MAGIC) {
			return false;
		}
		if (target instanceof Player) {
			final Player t = (Player) target;
			if (hasSession(t) && !(e instanceof KolodionNPC)) {
				return false;
			}
		}
		return super.continueAttack(e, target, style, message);
	}

	/**
	 * Gets the session.
	 * @param player the player.
	 * @return the session.
	 */
	public KolodionSession getSession(Player player) {
		return KolodionSession.getSession(player);
	}

	/**
	 * Checks if the player has a session.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	public boolean hasSession(final Player player) {
		return KolodionSession.getSession(player) != null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3093, 3914, 3115, 3952));// the main
		register(new ZoneBorders(3084, 3923, 3086, 3942));
		register(new ZoneBorders(3118, 3924, 3126, 3941));
		register(new ZoneBorders(3082, 3921, 3096, 3942));
		int x = 3089, y = 3914, x2 = 3089, y2 = 3949;
		for (int i = 0; i < 7; i++) {
			register(new ZoneBorders(x - i, y + i, x2 - i, y2 - i));
		}
		register(new ZoneBorders(3119, 3942, 3123, 3945));
		register(new ZoneBorders(3115, 3944, 3119, 3949));
		register(new ZoneBorders(3119, 3942, 3119, 3952));
		register(new ZoneBorders(3120, 3940, 2125, 3945));
		register(new ZoneBorders(3114, 3944, 3120, 3949));
		register(new ZoneBorders(3114, 3942, 3117, 3952));
		x = 3118;
		y = 3914;
		x2 = 3118;
		y2 = 3950;
		for (int i = 0; i < 7; i++) {
			register(new ZoneBorders(x + i, y + i, x2 + i, y2 - i));
		}
		register(new ZoneBorders(3107, 3931, 3118, 3947));
		register(new ZoneBorders(3108, 3919, 3123, 3932));
		register(new ZoneBorders(3108, 3921, 3123, 3932));
		register(new ZoneBorders(3104, 3933, 3117, 3948));
		register(new ZoneBorders(3106, 3920, 3117, 3937));
		register(new ZoneBorders(3115, 3928, 3120, 3942));
	}

}
