package plugin.activity.gwd;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.InteractionType;
import core.game.node.entity.combat.equipment.ArmourSet;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.player.Player;
import core.game.node.entity.state.EntityState;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.tools.RandomFunction;

/**
 * Handles K'ril Tsutsaroth's combat.
 * @author Emperor
 */
public final class GWDTsutsarothSwingHandler extends CombatSwingHandler {

	/**
	 * The melee attack animation.
	 */
	private static final Animation MELEE_ATTACK = new Animation(6945, Priority.HIGH);

	/**
	 * The range attack animation.
	 */
	private static final Animation MAGIC_ATTACK = new Animation(6947, Priority.HIGH);

	/**
	 * The magic start graphic.
	 */
	private static final Graphics MAGIC_START = new Graphics(1210);

	/**
	 * If K'ril is performing its special attack.
	 */
	private boolean special;

	/**
	 * Constructs a new {@code GWDTsutsarothSwingHandler} {@Code Object}.
	 */
	public GWDTsutsarothSwingHandler() {
		super(CombatStyle.MELEE);
	}

	@Override
	public InteractionType canSwing(Entity entity, Entity victim) {
		return CombatStyle.MELEE.getSwingHandler().canSwing(entity, victim);
	}

	@Override
	public int swing(Entity entity, Entity victim, BattleState state) {
		int ticks = 1;
		special = false;
		int hit = 0;
		CombatStyle style = CombatStyle.MELEE;
		if (RandomFunction.randomize(10) < 4) {
			ticks += (int) Math.ceil(entity.getLocation().getDistance(victim.getLocation()) * 0.3);
			style = CombatStyle.MAGIC;
		} else if (RandomFunction.randomize(10) == 0) {
			if (special = (victim instanceof Player)) {
				((Player) victim).getPacketDispatch().sendMessage("K'ril Tsutsaroth slams through your protection prayer, leaving you feeling drained.");
			}
			entity.sendChat("YARRRRRRR!");
		}
		if (style.getSwingHandler().isAccurateImpact(entity, victim)) {
			int max = style.getSwingHandler().calculateHit(entity, victim, special ? 1.08 : 1.0);
			hit = RandomFunction.random(max);
			state.setMaximumHit(max);
			if (style == CombatStyle.MELEE) {
				victim.getStateManager().register(EntityState.POISONED, false, 168, entity);
			}
			if (special) {
				((Player) victim).getSkills().decrementPrayerPoints(hit / 2);
			}
		}
		state.setEstimatedHit(hit);
		state.setStyle(style);
		return ticks;
	}

	@Override
	public void visualize(Entity entity, Entity victim, BattleState state) {
		switch (state.getStyle()) {
		case MELEE:
			entity.animate(MELEE_ATTACK);
			break;
		default:
			entity.visualize(MAGIC_ATTACK, MAGIC_START);
			Projectile.magic(entity, victim, 1211, 0, 0, 46, 1).send();
			break;
		}
	}

	@Override
	public ArmourSet getArmourSet(Entity e) {
		return getType().getSwingHandler().getArmourSet(e);
	}

	@Override
	public double getSetMultiplier(Entity e, int skillId) {
		return getType().getSwingHandler().getSetMultiplier(e, skillId);
	}

	@Override
	public void impact(Entity entity, Entity victim, BattleState state) {
		if (state.getStyle() == CombatStyle.MAGIC) {
			if (state.getEstimatedHit() > -1) {
				victim.getImpactHandler().handleImpact(entity, state.getEstimatedHit(), CombatStyle.MAGIC, state);
			}
			return;
		}
		state.getStyle().getSwingHandler().impact(entity, victim, state);
	}

	@Override
	public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
		victim.animate(victim.getProperties().getDefenceAnimation());
	}

	@Override
	public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
		super.adjustBattleState(entity, victim, state);
	}

	@Override
	protected int getFormattedHit(Entity entity, Entity victim, BattleState state, int hit) {
		if (!special) {
			if (state.getArmourEffect() != ArmourSet.VERAC && victim.hasProtectionPrayer(state.getStyle())) {
				hit *= entity instanceof Player ? 0.6 : 0;
			}
		}
		return formatHit(victim, hit);
	}

	@Override
	public int calculateAccuracy(Entity entity) {
		return getType().getSwingHandler().calculateAccuracy(entity);
	}

	@Override
	public int calculateDefence(Entity entity, Entity attacker) {
		return getType().getSwingHandler().calculateDefence(entity, attacker);
	}

	@Override
	public int calculateHit(Entity entity, Entity victim, double modifier) {
		return getType().getSwingHandler().calculateHit(entity, victim, modifier);
	}

}