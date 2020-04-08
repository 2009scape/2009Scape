package plugin.npc.bosses.wilderness;

import org.crandor.game.content.global.BossKillCounter;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.combat.equipment.ArmourSet;
import org.crandor.game.node.entity.combat.equipment.FireType;
import org.crandor.game.node.entity.combat.handlers.DragonfireSwingHandler;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the King Black Dragon NPC.
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class KingBlackDragonNPC extends AbstractNPC {

	/**
	 * The default spawn location.
	 */
	private static final Location DEFAULT_SPAWN = Location.create(2273, 4698, 0);

	/**
	 * The combat swing handler.
	 */
	private CombatSwingHandler combatHandler = new KBDCombatSwingHandler();

	
	public KingBlackDragonNPC() {
		super(-1,null);
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		BossKillCounter.addtoKillcount((Player) killer, this.getId());
	}

	/**
	 * Constructs a new {@code KingBlackDragonNPC} {@code Object}.
	 * @param id the id.
	 * @param l the l.
	 */
	public KingBlackDragonNPC(int id, Location l) {
		super(id, l);
	}

	@Override
	public void init() {
		super.init();
		configureBossData();
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new KingBlackDragonNPC(id, location);
	}

	@Override
	public int getDragonfireProtection(boolean fire) {
		return 0x2 | 0x4 | 0x8;
	}

	@Override
	public int[] getIds() {
		return new int[] { 50 };
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatHandler;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		return super.newInstance(arg);
	}

	/**
	 * Handles the King Black Dragon's combat swings.
	 * @author Emperor
	 */
	static class KBDCombatSwingHandler extends CombatSwingHandler {

		/**
		 * The style.
		 */
		private CombatStyle style = CombatStyle.RANGE;

		/**
		 * Dragonfire.
		 */
		private static final DragonfireSwingHandler DRAGONFIRE = new DragonfireSwingHandler(false, 56, null, true);

		/**
		 * The melee attack animation.
		 */
		private static final Animation MELEE_ATTACK = new Animation(80, Priority.HIGH);

		/**
		 * The fire type.
		 */
		private FireType fireType = FireType.FIERY_BREATH;

		/**
		 * Constructs a new {@code KBDCombatSwingHandler} {@Code Object}.
		 * @param type The combat style.
		 */
		public KBDCombatSwingHandler() {
			super(CombatStyle.RANGE);
		}

		@Override
		public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
			if (style == CombatStyle.RANGE) {
				fireType.getTask().run(victim, entity);
				state.setStyle(null);
				DRAGONFIRE.adjustBattleState(entity, victim, state);
				state.setStyle(CombatStyle.RANGE);
				return;
			}
			style.getSwingHandler().adjustBattleState(entity, victim, state);
		}

		@Override
		public int calculateAccuracy(Entity entity) {
			if (style == CombatStyle.MELEE) {
				return style.getSwingHandler().calculateAccuracy(entity);
			}
			return CombatStyle.MAGIC.getSwingHandler().calculateAccuracy(entity);
		}

		@Override
		public int calculateDefence(Entity entity, Entity attacker) {
			if (style == CombatStyle.MELEE) {
				return style.getSwingHandler().calculateDefence(entity, attacker);
			}
			return CombatStyle.MAGIC.getSwingHandler().calculateDefence(entity, attacker);
		}

		@Override
		public int calculateHit(Entity entity, Entity victim, double modifier) {
			if (style == CombatStyle.MELEE) {
				return style.getSwingHandler().calculateHit(entity, victim, modifier);
			}
			int max = 56;
			int damage = max;
			if (victim instanceof Player) {
				int val = victim.getDragonfireProtection(true);
				if ((val & 0x2) != 0) {
					damage *= 0.5;
				}
				if ((val & 0x4) != 0) {
					damage -= (int) (damage * 0.85);
				}
				if ((val & 0x8) != 0) {
					damage *= 0.6;
				}
				if (damage < 3) {
					damage = 3;
				}
			}
			if (fireType != FireType.FIERY_BREATH) {
				damage += 11;
			}
			return damage;
		}

		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			if (!isProjectileClipped(entity, victim, false)) {
				return InteractionType.NO_INTERACT;
			}
			if (victim.getCenterLocation().withinDistance(entity.getCenterLocation(), getCombatDistance(entity, victim, 9)) && super.canSwing(entity, victim) == InteractionType.STILL_INTERACT) {
				entity.getWalkingQueue().reset();
				return InteractionType.STILL_INTERACT;
			}
			return InteractionType.NO_INTERACT;
		}

		@Override
		public ArmourSet getArmourSet(Entity e) {
			return style.getSwingHandler().getArmourSet(e);
		}

		@Override
		public double getSetMultiplier(Entity e, int skillId) {
			return style.getSwingHandler().getSetMultiplier(e, skillId);
		}

		@Override
		public void impact(Entity entity, Entity victim, BattleState state) {
			style.getSwingHandler().impact(entity, victim, state);
		}

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			style = CombatStyle.RANGE;
			int hit = 0;
			int ticks = 1;
			if (victim.getCenterLocation().withinDistance(entity.getCenterLocation(), getCombatDistance(entity, victim, 1)) && RandomFunction.random(10) < 7) {
				style = CombatStyle.MELEE;
			} else {
				ticks += (int) Math.ceil(entity.getLocation().getDistance(victim.getLocation()) * 0.3);
			}
			fireType = FireType.values()[RandomFunction.random(FireType.values().length)];
			state.setStyle(style);
			if (isAccurateImpact(entity, victim)) {
				int max = calculateHit(entity, victim, 1.0);
				state.setMaximumHit(max);
				hit = RandomFunction.random(max);
			}
			state.setEstimatedHit(hit);
			((NPC) entity).getAggressiveHandler().setPauseTicks(2);
			return ticks;
		}

		@Override
		public void visualize(Entity entity, Entity victim, BattleState state) {
			switch (style) {
			case MELEE:
				entity.animate(MELEE_ATTACK);
				break;
			case RANGE:
				Projectile.ranged(entity, victim, fireType.getProjectileId(), 20, 36, 50, 15).send();
				entity.animate(fireType.getAnimation());
				break;
			default:
				break;
			}
		}

		@Override
		public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
			if (style != CombatStyle.MELEE) {
				DRAGONFIRE.visualizeImpact(entity, victim, state);
			} else {
				style.getSwingHandler().visualizeImpact(entity, victim, state);
			}
		}

		/**
		 * Gets the fire type.
		 * @return the type.
		 */
		public FireType getFireType() {
			return fireType;
		}

	}
}
