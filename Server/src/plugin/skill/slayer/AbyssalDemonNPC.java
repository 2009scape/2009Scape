package plugin.skill.slayer;

import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the abyssal npc.
 * @author Vexia
 */
@InitializablePlugin
public class AbyssalDemonNPC extends AbstractNPC {

	/**
	 * The melee swing handler.
	 */
	private static final MeleeSwingHandler SWING_HANDLER = new MeleeSwingHandler() {

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			if (victim instanceof Player && RandomFunction.random(8) <= 2) {
				boolean npc = RandomFunction.random(100) <= 50;
				Entity source = npc ? victim : entity;
				Entity teleported = npc ? entity : victim;
				Location loc = source.getLocation().transform(Direction.values()[RandomFunction.random(Direction.values().length)], 1);
				if (loc != null && !loc.equals(source.getLocation()) && RegionManager.isTeleportPermitted(loc) && RegionManager.getObject(loc) == null) {
					teleported.graphics(Graphics.create(409));
					teleported.teleport(loc, 1);
				}
			}
			return super.swing(entity, victim, state);
		}
	};

	/**
	 * Constructs a new {@Code AbyssalNPC} {@Code Object}
	 */
	public AbyssalDemonNPC() {
		super(-1, null);
	}

	/**
	 * Constructs a new {@Code AbyssalNPC} {@Code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public AbyssalDemonNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return SWING_HANDLER;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new AbyssalDemonNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return Tasks.ABYSSAL_DEMONS.getNpcs();
	}

}
