package plugin.activity.gwd;

import java.util.ArrayList;
import java.util.List;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.InteractionType;
import core.game.node.entity.combat.equipment.ArmourSet;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.npc.NPC;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.tools.RandomFunction;

/**
 * Handles Kree'arra's combat.
 * @author Emperor
 */
public final class GWDKreeArraSwingHandler extends CombatSwingHandler {

	/**
	 * The boss chamber.
	 */
	private static final ZoneBorders CHAMBER = new ZoneBorders(2824, 5296, 2842, 5308);

	/**
	 * The melee attack animation.
	 */
	private static final Animation MELEE_ATTACK = new Animation(6977, Priority.HIGH);

	/**
	 * The range attack animation.
	 */
	private static final Animation RANGE_ATTACK = new Animation(6976, Priority.HIGH);

	/**
	 * The end graphic.
	 */
	private static final Graphics END_GRAPHIC = new Graphics(80, 96);

	/**
	 * Constructs a new {@code GWDKreeArraSwingHandler} {@Code Object}.
	 */
	public GWDKreeArraSwingHandler() {
		super(CombatStyle.RANGE);
	}

	@Override
	public InteractionType canSwing(Entity entity, Entity victim) {
		return CombatStyle.RANGE.getSwingHandler().canSwing(entity, victim);
	}

	@Override
	public int swing(Entity entity, Entity victim, BattleState state) {
		int ticks = 1;
		int distance = entity.size() >> 1;
		if (!entity.inCombat()) {
			distance++;
		}
		if (victim.getLocation().withinDistance(entity.getCenterLocation(), distance) && RandomFunction.RANDOM.nextBoolean()) {
			int hit = 0;
			int max = CombatStyle.MELEE.getSwingHandler().calculateHit(entity, victim, 1.0);
			if (CombatStyle.MELEE.getSwingHandler().isAccurateImpact(entity, victim, CombatStyle.MELEE)) {
				hit = RandomFunction.random(max);
			}
			state.setEstimatedHit(hit);
			hit = 0;
			if (CombatStyle.MELEE.getSwingHandler().isAccurateImpact(entity, victim, CombatStyle.MELEE)) {
				hit = RandomFunction.random(max);
			}
			state.setSecondaryHit(hit);
			state.setMaximumHit(max);
			state.setStyle(CombatStyle.MELEE);
		} else {
			ticks += (int) Math.ceil(entity.getLocation().getDistance(victim.getLocation()) * 0.3);
			NPC npc = (NPC) entity;
			List<BattleState> list = new ArrayList<>();
			for (Entity t : RegionManager.getLocalPlayers(npc, 28)) {
				if (!CHAMBER.insideBorder(t.getLocation())) {
					continue;
				}
				if (t.isAttackable(npc, CombatStyle.RANGE)) {
					list.add(new BattleState(entity, t));
				}
			}
			BattleState[] targets;
			state.setStyle(CombatStyle.RANGE);
			state.setTargets(targets = list.toArray(new BattleState[list.size()]));
			for (BattleState s : targets) {
				CombatStyle style = RandomFunction.randomize(10) < 3 ? CombatStyle.MAGIC : CombatStyle.RANGE;
				s.setStyle(style);
				int hit = 0;
				if (style.getSwingHandler().isAccurateImpact(entity, s.getVictim(), style)) {
					int max = style.getSwingHandler().calculateHit(entity, s.getVictim(), 1.0);
					s.setMaximumHit(max);
					if(style.equals(CombatStyle.RANGE)){
						max = 71;
					}	
					hit = RandomFunction.random(max);
				}
				s.setEstimatedHit(hit);
			}
		}
		return ticks;
	}

	@Override
	public void visualize(Entity entity, Entity victim, BattleState state) {
		switch (state.getStyle()) {
		case MELEE:
			entity.animate(MELEE_ATTACK);
			break;
		default:
			entity.animate(RANGE_ATTACK);
			for (BattleState s : state.getTargets()) {
				int gfxId = 1197;
				if (s.getStyle() == CombatStyle.MAGIC) {
					gfxId = 1198;
				}
				Projectile.ranged(entity, s.getVictim(), gfxId, 92, 36, 46, 5).send();
			}
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
		if (state.getStyle() == CombatStyle.MELEE) {
			state.getStyle().getSwingHandler().impact(entity, victim, state);
			return;
		}
		for (BattleState s : state.getTargets()) {
			if (s == null || s.getEstimatedHit() < 0) {
				continue;
			}
			int hit = s.getEstimatedHit();
			s.getVictim().getImpactHandler().handleImpact(entity, hit, s.getStyle(), s);
		}
	}

	@Override
	public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
		if (state.getStyle() == CombatStyle.MELEE) {
			victim.animate(victim.getProperties().getDefenceAnimation());
			return;
		}
		for (BattleState s : state.getTargets()) {
			s.getVictim().animate(s.getVictim().getProperties().getDefenceAnimation());
			if (RandomFunction.randomize(10) < 8) {
				Direction dir = Direction.getLogicalDirection(entity.getLocation(), s.getVictim().getLocation());
				Location destination = s.getVictim().getLocation().transform(dir.getStepX(), dir.getStepY(), 0);
				if (CHAMBER.insideBorder(destination)) {
					s.getVictim().getProperties().setTeleportLocation(destination);
					s.getVictim().graphics(END_GRAPHIC);
				}
			}
		}
	}

	@Override
	public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
		if (state.getStyle() == CombatStyle.MELEE) {
			state.getStyle().getSwingHandler().adjustBattleState(entity, victim, state);
			return;
		}
		for (BattleState s : state.getTargets()) {
			s.getStyle().getSwingHandler().adjustBattleState(entity, s.getVictim(), s);
		}
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