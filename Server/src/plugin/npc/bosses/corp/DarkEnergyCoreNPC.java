package plugin.npc.bosses.corp;

import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.state.EntityState;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the Dark Energy Core NPC.
 * @author Emperor
 *
 */
@InitializablePlugin
public final class DarkEnergyCoreNPC extends AbstractNPC {

	/**
	 * The corporeal beast.
	 */
	private NPC master;

	/**
	 * The amount of ticks.
	 */
	private int ticks = 0;
	
	/**
	 * The amount of failed attacks.
	 */
	private int fails = 0;
	
	/**
	 * Constructs a new {@code DarkEnergyCoreNPC} {@code Object}.
	 */
	public DarkEnergyCoreNPC() {
		this(8127, null);
	}
	
	/**
	 * Constructs a new {@code DarkEnergyCoreNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public DarkEnergyCoreNPC(int id, Location location) {
		super(id, location, false);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		DarkEnergyCoreNPC core = new DarkEnergyCoreNPC(id, location);
		if (objects.length > 0) {
			core.master = (NPC) objects[0];
		}
		return core;
	}
	
	@Override
	public boolean canStartCombat(Entity victim) {
		return false; //No combat needed.
	}
	
	@Override
	public void handleTickActions() {
		ticks++;
		boolean poisoned = getStateManager().hasState(EntityState.POISONED);
		if (getStateManager().hasState(EntityState.STUNNED) || isInvisible()) {
			return;
		}
		if (fails == 0 && poisoned && (ticks % 100) != 0) {
			return;
		}
		if (ticks % 2 == 0) {
			boolean jump = true;
			for (Player p : getViewport().getCurrentPlane().getPlayers()) {
				if (p.getLocation().withinDistance(getLocation(), 1)) {
					jump = false;
					int hit = 5 + RandomFunction.random(6);
					p.getImpactHandler().manualHit(master, hit, HitsplatType.NORMAL);
					master.getSkills().heal(hit);
					p.getPacketDispatch().sendMessage("The dark core creature steals some life from you for its master.");
				}
			}
			if (jump) {
				Entity victim = master.getProperties().getCombatPulse().getVictim();
				if (++fails >= 3 && victim != null && victim.getViewport().getCurrentPlane() == getViewport().getCurrentPlane()) {
					jump(victim.getLocation());
					fails = 0;
				}
			} else {
				fails = 0;
			}
		}
	}

	/**
	 * Handles jumping towards a new location.
	 * @param location The location.
	 */
	private void jump(final Location location) {
		setInvisible(true);
		Projectile.create(getLocation(), location, 1828, 0, 0, 0, 60, 20, 0).send();
		GameWorld.Pulser.submit(new Pulse(2, this) {
			@Override
			public boolean pulse() {
				getProperties().setTeleportLocation(location);
				setInvisible(false);
				return true;
			}
		});
	}

	@Override
	public int[] getIds() {
		return new int[] { 8127 };
	}

}
