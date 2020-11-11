package plugin.activity.tzhaar;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.InteractionType;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.combat.equipment.ArmourSet;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.system.task.Pulse;
import core.game.world.map.Location;
import core.game.world.map.MapDistance;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.tools.RandomFunction;

/**
 * Represents a fight caves NPC.
 * @author Emperor
 */
public final class TzhaarFightCaveNPC extends AbstractNPC {

	/**
	 * The NPC ids.
	 */
	private static final int[] NPC_IDS = { 2734, 2735, 2736, 2737, 2738, 2739, 2740, 2741, 2742, 2743, 2744, 2745, 2746 };

	/**
	 * The combat reward.
	 */
	private CombatAction combatAction;

	/**
	 * The activity plugin object.
	 */
	private TzhaarFightCavesPlugin activity;

	/**
	 * If the NPC has spawned minions yet (used for Jad).
	 */
	private boolean spawnedMinions;

	/**
	 * Constructs a new {@code TzhaarFightCaveNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public TzhaarFightCaveNPC(int id, Location location, TzhaarFightCavesPlugin activity) {
		super(id, location);
		this.activity = activity;
	}

	/**
	 * Constructs a new {@code TzhaarFightCaveNPC} {@code Object}.
	 */
	public TzhaarFightCaveNPC() {
		super(2734, null);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatAction;
	}

	@Override
	public void tick() {
		super.tick();
		if (getId() != 2746 && activity != null && !getProperties().getCombatPulse().isAttacking()) {
			getProperties().getCombatPulse().attack(activity.getPlayer());
			face(activity.getPlayer());
		}
	}

	@Override
	public void configure() {
		super.configure();
		CombatStyle style = CombatStyle.MELEE;
		if (getId() == 2739 || getId() == 2740) {
			style = CombatStyle.RANGE;
		} else if (getId() == 2743 || getId() == 2744 || getId() == 2745) {
			style = CombatStyle.MAGIC;
		}
		super.setAggressive(false);
		super.aggressiveHandler = null;
		combatAction = new CombatAction(this, style);
		getProperties().setCombatTimeOut(Integer.MAX_VALUE);
		super.setWalkRadius(64);
		if (activity != null) {
			activity.activeNPCs.add(this);
			if (getId() != 2746) {
				getProperties().getCombatPulse().attack(activity.getPlayer());
			}
		}
	}

	@Override
	public void finalizeDeath(Entity killer) {
		if (killer.isPlayer()) {
			Player player = killer.asPlayer();
			// Successfully kill a Ket-Zek in the Fight Caves
			if ((getId() == 2743 || getId() == 2744)) {
				player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 2, 1);
			}
		}
		super.finalizeDeath(killer);
	}

	/**
	 * Heals the NPC.
	 * @param amount The amount to heal.
	 */
	public void heal(int amount) {
		if (getSkills().heal(amount) > 0 && getId() == 2745) {
			spawnedMinions = false;
		}
	}

	@Override
	public void onImpact(final Entity entity, final BattleState state) {
		if (getId() == 2746) {
			getPulseManager().run(new Pulse(1, entity) {
				@Override
				public boolean pulse() {
					getProperties().getCombatPulse().attack(entity);
					return true;
				}
			});
			return;
		}
		super.onImpact(entity, state);
		if (getId() == 2736 || getId() == 2737) {
			if (state.getStyle() == CombatStyle.MELEE && getSkills().getLifepoints() > 0) {
				entity.getImpactHandler().manualHit(this, 1 + (state.getEstimatedHit() / 10), HitsplatType.NORMAL, 1);
			}
		} else if (getId() == 2745 && !spawnedMinions && getSkills().getLifepoints() < (getSkills().getMaximumLifepoints() >> 1)) {
			spawnedMinions = true;
			if (activity != null) {
				for (int i = activity.activeNPCs.size() - 1; i < 4; i++) {
					TzhaarFightCaveNPC npc = activity.spawn(2746);
					npc.getProperties().getCombatPulse().attack(this);
					npc.setAttribute("fc_jad", this);
				}
			}
		}
	}

	@Override
	public int getWalkRadius() {
		return 64;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new TzhaarFightCaveNPC(id, location, null);
	}

	@Override
	public int[] getIds() {
		return NPC_IDS;
	}

	/**
	 * Handles a fight cave NPC's combat reward.
	 * @author Emperor
	 */
	static class CombatAction extends CombatSwingHandler {

		/**
		 * The NPC instance.
		 */
		private TzhaarFightCaveNPC npc;

		/**
		 * The main combat style.
		 */
		private CombatStyle main;

		/**
		 * The current combat style.
		 */
		private CombatStyle style;

		/**
		 * If the NPC is TzTok-Jad.
		 */
		boolean jad;

		/**
		 * Constructs a new {@code TzhaarFightCaveNPC} {@code Object}.
		 * @param npc The NPC.
		 * @param main The main combat style.
		 */
		public CombatAction(TzhaarFightCaveNPC npc, CombatStyle main) {
			super(main);
			this.npc = npc;
			this.jad = npc.getId() == 2745;
			this.main = main;
			this.style = getType();
		}

		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			if (getType() != CombatStyle.MELEE) {
				if (!isProjectileClipped(entity, victim, false)) {
					return InteractionType.NO_INTERACT;
				}
				int distance = MapDistance.RENDERING.getDistance();
				if (victim.getCenterLocation().withinDistance(entity.getCenterLocation(), getCombatDistance(entity, victim, distance)) && super.canSwing(entity, victim) != InteractionType.NO_INTERACT) {
					entity.getWalkingQueue().reset();
					return InteractionType.STILL_INTERACT;
				}
				return InteractionType.NO_INTERACT;
			}
			return getType().getSwingHandler().canSwing(entity, victim);
		}

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			style = super.getType();
			int ticks = 1;
			if (jad) {
				main = CombatStyle.values()[1 + RandomFunction.RANDOM.nextInt(2)];
			}
			if (main != CombatStyle.MELEE) {
				style = main;
				if (CombatStyle.MELEE.getSwingHandler().canSwing(entity, victim) != InteractionType.NO_INTERACT && RandomFunction.random(10) < 4) {
					style = CombatStyle.MELEE;
				} else {
					ticks += (int) Math.ceil(entity.getLocation().getDistance(victim.getLocation()) * 0.4);
				}
			}
			int max = calculateHit(entity, victim, 1.0);
			int hit = 0;
			boolean heal = victim instanceof NPC || ((npc.getId() == 2741 || npc.getId() == 2742) && RandomFunction.RANDOM.nextInt(4) < 1);
			if (((NPC) entity).getId() == 2746) {
				NPC j = (NPC) entity.getAttribute("fc_jad", entity);
				if (entity.getLocation().withinDistance(j.getCenterLocation(), (j.size() >> 1) + 1) && RandomFunction.random(4) < 2) {
					heal = true;
					state.setVictim(j);
					victim = j;
				}
			}
			state.setStyle(heal ? null : style);
			if (heal || isAccurateImpact(entity, victim)) {
				state.setMaximumHit(max);
				hit = RandomFunction.random(max) + (heal ? 5 : 0);
			}
			state.setEstimatedHit(hit);
			return ticks;
		}

		@Override
		public void adjustBattleState(Entity entity, Entity victim, BattleState state) {

		}

		@Override
		public void visualize(Entity entity, Entity victim, BattleState state) {
			if (state.getStyle() == null) {
				return;
			}
			switch (state.getStyle()) {
			case MELEE:
				entity.animate(entity.getProperties().getAttackAnimation());
				break;
			case RANGE:
				if (jad) {
					entity.visualize(new Animation(9276, Priority.HIGH), Graphics.create(1625));
				} else {
					Projectile.ranged(entity, victim, 1616, 41, 36, 50, 15).send();
					entity.animate(new Animation(9243, Priority.HIGH));
				}
				break;
			case MAGIC:
				if (jad) {
					Projectile.create(entity, victim, 1627, 96, 36, 70, (int) (victim.getLocation().getDistance(entity.getLocation()) * 10 + 52)).send();
					entity.visualize(new Animation(9300, Priority.HIGH), Graphics.create(1626));
				} else {
					Projectile.magic(entity, victim, 1623, 41, 36, 50, 15).send();
					entity.visualize(new Animation(9266, Priority.HIGH), Graphics.create(1622));
				}
				break;
			}
		}

		@Override
		public void impact(Entity entity, Entity victim, BattleState state) {
			if (state.getStyle() != null && victim.hasProtectionPrayer(state.getStyle())) {
				state.setEstimatedHit(0);
			}
			if (state.getStyle() == null) {
				NPC n = victim instanceof NPC ? ((NPC) victim) : (NPC) npc.activity.getPlayer().getProperties().getCombatPulse().getVictim();
				if (n == null || !(n instanceof TzhaarFightCaveNPC)) {
					n = npc;
				}
				((TzhaarFightCaveNPC) n).heal(state.getEstimatedHit());
				n.graphics(new Graphics(444, 96));
				return;
			}
			if (state.getEstimatedHit() > 0) {
				state.setEstimatedHit(formatHit(victim, state.getEstimatedHit()));
				if (((NPC) entity).getId() == 2734 || ((NPC) entity).getId() == 2735) {
					victim.getSkills().decrementPrayerPoints(state.getEstimatedHit());
				} else if (jad && state.getStyle() != CombatStyle.MELEE) {
					victim.graphics(Graphics.create(157));
				}
			}
			style.getSwingHandler().impact(entity, victim, state);
		}

		@Override
		public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
			if (state.getStyle() == null) {
				return;
			} else if (state.getStyle() == CombatStyle.MAGIC && !jad) {
				victim.graphics(Graphics.create(1624));
			} else if (jad && state.getStyle() == CombatStyle.RANGE) {
				victim.graphics(Graphics.create(451));
			}
			style.getSwingHandler().visualizeImpact(entity, victim, state);
		}

		@Override
		public int calculateAccuracy(Entity entity) {
			return style.getSwingHandler().calculateAccuracy(entity);
		}

		@Override
		public int calculateDefence(Entity entity, Entity attacker) {
			return style.getSwingHandler().calculateDefence(entity, attacker);
		}

		@Override
		public int calculateHit(Entity entity, Entity victim, double modifier) {
			return style.getSwingHandler().calculateHit(entity, victim, modifier);
		}

		@Override
		public ArmourSet getArmourSet(Entity e) {
			return style.getSwingHandler().getArmourSet(e);
		}

		@Override
		public double getSetMultiplier(Entity e, int skillId) {
			return style.getSwingHandler().getSetMultiplier(e, skillId);
		}

	}
}