package plugin.skill.slayer;

import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.equipment.SwitchAttack;
import org.crandor.game.node.entity.combat.handlers.DragonfireSwingHandler;
import org.crandor.game.node.entity.combat.handlers.MultiSwingHandler;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles a mithril dragon npc.
 * @author Vexia
 */
@InitializablePlugin
public final class MithrilDragonNPC extends AbstractNPC {

	/**
	 * The dragonfire attack.
	 */
	private static final SwitchAttack DRAGONFIRE = DragonfireSwingHandler.get(false, 52, new Animation(81, Priority.HIGH), Graphics.create(1), null, null);

	/**
	 * Handles the combat.
	 */
	private final CombatSwingHandler combatAction = new MultiSwingHandler(true, new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), new Animation(80, Priority.HIGH)), new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), new Animation(80, Priority.HIGH)), new SwitchAttack(CombatStyle.MAGIC.getSwingHandler(), new Animation(81, Priority.HIGH), null, null, Projectile.create((Entity) null, null, 500, 20, 20, 41, 40, 18, 255)), DRAGONFIRE, new SwitchAttack(CombatStyle.RANGE.getSwingHandler(), new Animation(81, Priority.HIGH), null, null, Projectile.create((Entity) null, null, 16, 20, 20, 41, 40, 18, 255)));

	/**
	 * Constructs a new {@code MithrilDragonNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public MithrilDragonNPC(int id, Location location) {
		super(id, location);
	}

	/**
	 * Constructs a new {@code MithrilDragonNPC} {@code Object}.
	 */
	public MithrilDragonNPC() {
		super(0, null);
	}

	@Override
	public void sendImpact(BattleState state) {
		CombatStyle style = state.getStyle();
		if (style == null) {
			return;
		}
		int maxHit = state.getEstimatedHit();
		if (maxHit < 1) {
			return;
		}
		switch (style) {
		case MELEE:
			maxHit = 28;
			break;
		case MAGIC:
			maxHit = 18;
			break;
		case RANGE:
			maxHit = 18;
			break;
		}
		if (state.getEstimatedHit() > maxHit) {
			state.setEstimatedHit(RandomFunction.random(maxHit - 5, maxHit));
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MithrilDragonNPC(id, location);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatAction;
	}

	@Override
	public int getDragonfireProtection(boolean fire) {
		return 0x2 | 0x4 | 0x8;
	}

	@Override
	public int[] getIds() {
		return Tasks.MITHRIL_DRAGON.getNpcs();
	}
}
