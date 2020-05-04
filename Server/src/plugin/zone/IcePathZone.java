package plugin.zone;

import org.crandor.game.component.Component;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * The God Wars entrance zone monitor.
 * @author Emperor
 */
@InitializablePlugin
public final class IcePathZone extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code IcePathZone} {@code Object}.
	 */
	public IcePathZone() {
		super("Ice path zone", true);
	}

	@Override
	public void configure() {
		ZoneBorders border = new ZoneBorders(2821, 3712, 2940, 3839);
		border.addException(new ZoneBorders(2821, 3712, 2838, 3745));
		register(border);
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			final Player player = (Player) e;
			player.getInterfaceManager().openOverlay(new Component(482)); // TODO:
			// find
			// the
			// real
			// one
			Pulse pulse = new Pulse(10, player) {
				@Override
				public boolean pulse() {
					if (player.getLocks().isMovementLocked()) {
						return false;
					}
					player.getSettings().updateRunEnergy(100);
					player.getImpactHandler().manualHit(player, 1, HitsplatType.NORMAL);
					int skill = RandomFunction.randomize(7);
					if (skill == 3 || skill == 5) {
						skill++;
					}
					player.getSkills().updateLevel(skill, -1, 0);
					return false;
				}
			};
			player.setAttribute("ice_path_pulse", pulse);
			GameWorld.Pulser.submit(pulse);
		}
		return true;
	}

	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			((Player) e).getInterfaceManager().closeOverlay();
			Pulse pulse = e.getAttribute("ice_path_pulse");
			if (pulse != null) {
				pulse.stop();
				e.removeAttribute("ice_path_pulse");
			}
		}
		return true;
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
}
