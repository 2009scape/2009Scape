package plugin.npc.bosses;

import java.util.ArrayList;
import java.util.List;

import org.crandor.game.content.global.BossKillCounter;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.combat.equipment.ArmourSet;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

import org.crandor.plugin.InitializablePlugin;
import plugin.interaction.object.dmc.DMCHandler;

/**
 * Handles the Kalphite Queen.
 * @author Emperor
 * @version 1.0
 */
@InitializablePlugin
public final class KalphiteQueenNPC extends AbstractNPC {

	/**
	 * The default spawn location.
	 */
	// 3474, 9495, 0
	private static final Location DEFAULT_SPAWN = Location.create(3222, 3217, 0);

	/**
	 * The transform animation.
	 */
	private static final Animation TRANSFORM_ANIM = new Animation(6270, Priority.HIGH);

	/**
	 * The combat swing handler.
	 */
	private CombatSwingHandler combatHandler = new KQCombatSwingHandler();

	/**
	 * Constructs a new {@code KalphiteQueenNPC} {@code Object}.
	 */
	public KalphiteQueenNPC() {
		this(1158, DEFAULT_SPAWN);
	}

	/**
	 * Constructs a new {@code KalphiteQueenNPC} {@code Object}.
	 */
	public KalphiteQueenNPC(int id, Location spawn) {
		super(id, spawn);
		super.setAggressive(true);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatHandler;
	}

	@Override
	public boolean hasProtectionPrayer(CombatStyle style) {
		if (getId() == 1158) {
			return style == CombatStyle.MAGIC || style == CombatStyle.RANGE;
		}
		return style == CombatStyle.MELEE;
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void init() {
		super.init();
		setRespawn(true);
		configureBossData();
	}

	@Override
	public void finalizeDeath(Entity killer) {
		if (getId() == 1160) {
			removeAttribute("disable:drop");
			reTransform();
			super.finalizeDeath(killer);
			BossKillCounter.addtoKillcount((Player) killer, 1160);
			return;
		}
		BossKillCounter.addtoKillcount((Player) killer, this.getId());
		setAttribute("disable:drop", true);
		super.finalizeDeath(killer);
		super.setRespawnTick(-1);
		super.getWalkingQueue().reset();
		super.getLocks().lockMovement(10);
		transform(1160);
		getProperties().setTeleportLocation(null);
		getAnimator().setPriority(Priority.LOW);
		visualize(TRANSFORM_ANIM, Graphics.create(1055));
		lock(TRANSFORM_ANIM.getDuration() + 1);
	}

	@Override
	public void sendImpact(BattleState state) {
		if (state.getEstimatedHit() > 31) {
			state.setEstimatedHit(RandomFunction.random(1, 12));
		}
		if (state.getVictim() instanceof Player) {
			Player player = state.getVictim().asPlayer();
			DMCHandler handler = player.getAttribute("dmc");
			if (handler != null && handler.getCannon() != null && handler.getCannon().isActive()) {

			}
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new KalphiteQueenNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1158, 1160 };
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int id : getIds()) {
			mapping.put(id, this);
		}
		return this;
	}

	/**
	 * Handles the Kalphite Queen combat swings.
	 * @author Emperor
	 */
	static class KQCombatSwingHandler extends CombatSwingHandler {

		/**
		 * The melee attack anim for the first form.
		 */
		private static final Animation MELEE_ATTACK_1 = new Animation(6241, Priority.HIGH);

		/**
		 * The range/magic attack anim for the first form.
		 */
		private static final Animation RANGE_ATTACK_1 = new Animation(6240, Priority.HIGH);

		/**
		 * The magic graphic for the first form.
		 */
		private static final Graphics MAGIC_GFX_1 = Graphics.create(278);

		/**
		 * The melee attack anim for the first form.
		 */
		private static final Animation MELEE_ATTACK_2 = new Animation(6235, Priority.HIGH);

		/**
		 * The range/magic attack anim for the first form.
		 */
		private static final Animation RANGE_ATTACK_2 = new Animation(6234, Priority.HIGH);

		/**
		 * The magic graphic for the first form.
		 */
		private static final Graphics MAGIC_GFX_2 = Graphics.create(279);

		/**
		 * The end graphic for magic.
		 */
		private static final Graphics MAGIC_END_GFX = Graphics.create(281);

		/**
		 * The style.
		 */
		private CombatStyle style = CombatStyle.RANGE;

		/**
		 * Constructs a new {@code KalphiteQueenNPC} {@code Object}.
		 */
		public KQCombatSwingHandler() {
			super(CombatStyle.RANGE);
		}

		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			return getType().getSwingHandler().canSwing(entity, victim);
		}

		/**
		 * Gets the range targets.
		 * @param e The entity.
		 * @param victim The victim.
		 * @return The targets array.
		 */
		private BattleState[] getRangeTargets(Entity e, Entity victim) {
			List<BattleState> list = new ArrayList<>();
			for (Entity t : RegionManager.getLocalPlayers(victim, -1 + (int) e.getCenterLocation().getDistance(victim.getLocation()))) {
				if (t.isAttackable(e, CombatStyle.RANGE)) {
					list.add(new BattleState(e, t));
				}
			}
			return list.toArray(new BattleState[list.size()]);
		}

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			style = super.getType();
			super.setType(CombatStyle.values()[1 + RandomFunction.RANDOM.nextInt(2)]);
			int ticks = 1;
			if (CombatStyle.MELEE.getSwingHandler().canSwing(entity, victim) != InteractionType.NO_INTERACT && RandomFunction.random(10) < 4) {
				style = CombatStyle.MELEE;
			} else {
				ticks += (int) Math.ceil(entity.getLocation().getDistance(victim.getLocation()) * (style == CombatStyle.MAGIC ? 0.5 : 0.3));
			}
			BattleState[] targets = new BattleState[] { state };
			if (style == CombatStyle.RANGE) {
				targets = getRangeTargets(entity, victim);
				state.setTargets(targets);
			}
			state.setStyle(style);
			int max = calculateHit(entity, victim, 1.0);
			if (max > 31) {
				max = RandomFunction.random(5, 28);
			}
			for (BattleState s : targets) {
				int hit = 0;
				s.setStyle(style);
				if (isAccurateImpact(entity, s.getVictim())) {
					s.setMaximumHit(max);
					hit = RandomFunction.random(max);
				}
				if (victim.hasProtectionPrayer(CombatStyle.RANGE)) {
					s.setEstimatedHit(0);
				} else {
					s.setEstimatedHit(hit);
				}
			}
			return ticks;
		}

		@Override
		public void visualize(Entity entity, Entity victim, BattleState state) {
			boolean secondForm = entity instanceof NPC && ((NPC) entity).getId() == 1160;
			switch (state.getStyle()) {
			case MELEE:
				entity.animate(secondForm ? MELEE_ATTACK_2 : MELEE_ATTACK_1);
				break;
			case RANGE:
				for (BattleState s : state.getTargets()) {
					if (s != null) {
						Projectile.ranged(entity, s.getVictim(), secondForm ? 289 : 288, 41, 36, 50, 15).send();
					}
				}
				entity.animate(secondForm ? RANGE_ATTACK_2 : RANGE_ATTACK_1);
				break;
			case MAGIC:
				Projectile.magic(entity, victim, 280, 41, 36, 50, 15).send();
				entity.visualize(secondForm ? RANGE_ATTACK_2 : RANGE_ATTACK_1, secondForm ? MAGIC_GFX_2 : MAGIC_GFX_1);
				break;
			}
		}

		@Override
		public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
			if (state.getStyle() == CombatStyle.MAGIC) {
				victim.graphics(MAGIC_END_GFX);
			}
			state.getStyle().getSwingHandler().visualizeImpact(entity, victim, state);
		}

		@Override
		public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
			state.getStyle().getSwingHandler().adjustBattleState(entity, victim, state);
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
		public void impact(Entity entity, Entity victim, BattleState state) {
			CombatStyle style = state.getStyle();
			if (style == CombatStyle.RANGE && state.getTargets() != null) {
				for (BattleState s : state.getTargets()) {
					style.getSwingHandler().impact(entity, s.getVictim(), s);
				}
				return;
			}
			if (style == CombatStyle.MAGIC) {
				BattleState[] states = new BattleState[entity.getViewport().getCurrentPlane().getPlayers().size()];
				int index = 1;
				if (states.length == 0) {
					return;
				}
				for (Player p : entity.getViewport().getCurrentPlane().getPlayers()) {
					if (p != victim) {
						states[index++] = new BattleState(entity, p);
					}
				}
				states[0] = new BattleState();
				handleMagicImpact(entity, entity, victim, states, 0);
				return;
			}
			style.getSwingHandler().impact(entity, victim, state);
		}

		/**
		 * Handles a magic impact.
		 * @param e The entity.
		 * @param last The last entity to receive the impact.
		 * @param victim The victim.
		 * @param targets The list of targets.
		 * @param index The current target index.
		 */
		private void handleMagicImpact(final Entity e, final Entity last, final Entity victim, final BattleState[] targets, int index) {
			if (targets == null || index >= targets.length) {
				return;
			}
			BattleState s = targets[index];
			if (s == null) {
				return;
			}
			int hit = 0;
			if (CombatStyle.MAGIC.getSwingHandler().isAccurateImpact(e, victim)) {
				hit = RandomFunction.RANDOM.nextInt(32);
			}
			if (victim.hasProtectionPrayer(CombatStyle.MAGIC)) {
				hit = 0;
			}
			s.setEstimatedHit(hit);
			CombatStyle.MAGIC.getSwingHandler().adjustBattleState(e, victim, s);
			victim.getImpactHandler().handleImpact(e, s.getEstimatedHit(), CombatStyle.MAGIC, s);
			CombatStyle.MAGIC.getSwingHandler().onImpact(e, victim, s);
			while (++index < targets.length && (s = targets[index]) == null)
				;
			if (s == null || index >= targets.length) {
				return;
			}
			final BattleState t = s;
			final int targetIndex = index;
			t.getVictim().graphics(MAGIC_END_GFX);
			Projectile.create(victim, t.getVictim(), 280, 41, 36, 0, 30, 15, 11).send();
			GameWorld.Pulser.submit(new Pulse(1, t.getVictim()) {
				@Override
				public boolean pulse() {
					handleMagicImpact(e, victim, t.getVictim(), targets, targetIndex);
					return true;
				}
			});
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
