package plugin.skill.slayer;

import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.equipment.SwitchAttack;
import org.crandor.game.node.entity.combat.handlers.MultiSwingHandler;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;

/**
 * Handles the water fiend npc.
 * @author Vexia
 */
@InitializablePlugin
public final class WaterFiendNPC extends AbstractNPC {

	/**
	 * Handles the combat.
	 */
	private final CombatSwingHandler combatAction = new MultiSwingHandler(true, new SwitchAttack(CombatStyle.MAGIC.getSwingHandler(), new Animation(1581, Priority.HIGH), null, null, Projectile.create((Entity) null, null, 500, 15, 30, 50, 50, 14, 255)), new SwitchAttack(CombatStyle.RANGE.getSwingHandler(), new Animation(1581, Priority.HIGH), null, null, Projectile.create((Entity) null, null, 16, 15, 30, 50, 50, 14, 255)));

	/**
	 * Constructs a new {@code WaterFiendNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public WaterFiendNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@code WaterFiendNPC} {@code Object}.
	 */
	public WaterFiendNPC() {
		super(0, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new WaterFiendNPC(id, location);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatAction;
	}

	@Override
	public int[] getIds() {
		return Tasks.WATERFIENDS.getNpcs();
	}

}
