package plugin.npc.familiar;

import org.crandor.game.content.skill.SkillBonus;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Represents the dreadfowl familiar.
 * @author Emperor
 */
@InitializablePlugin
public final class DreadfowlNPC extends Familiar {

	/**
	 * If the special move is toggled.
	 */
	private boolean specialMove;

	/**
	 * The combat handler.
	 */
	private static final CombatSwingHandler COMBAT_HANDLER = new MeleeSwingHandler() {

		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			if (((DreadfowlNPC) entity).specialMove) {
				return CombatStyle.MAGIC.getSwingHandler().canSwing(entity, victim);
			}
			return super.canSwing(entity, victim);
		}

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			DreadfowlNPC npc = (DreadfowlNPC) entity;
			if (npc.specialMove) {
				npc.specialMove(new FamiliarSpecial(victim));
				npc.specialMove = false;
				return -1;
			}
			npc.specialMove = RandomFunction.randomize(10) == 0;
			return super.swing(entity, victim, state);
		}

	};

	/**
	 * Constructs a new {@code DreadfowlNPC} {@code Object}.
	 */
	public DreadfowlNPC() {
		this(null, 6825);
	}

	/**
	 * Constructs a new {@code DreadfowlNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The NPC id.
	 */
	public DreadfowlNPC(Player owner, int id) {
		super(owner, id, 400, 12043, 3, WeaponInterface.STYLE_CAST);
		super.setCombatHandler(COMBAT_HANDLER);
		boosts.add(new SkillBonus(Skills.FARMING, 1));
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new DreadfowlNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		final Entity target = (Entity) special.getNode();
		if (!canAttack(target)) {
			return false;
		}
		if (!owner.getProperties().getCombatPulse().isAttacking() && !owner.inCombat()) {
			owner.getPacketDispatch().sendMessage("Your familiar can only attack when you're in combat.");
			return false;
		}
		if (getProperties().getCombatPulse().getNextAttack() > GameWorld.getTicks() || CombatStyle.MAGIC.getSwingHandler().canSwing(this, target) == InteractionType.NO_INTERACT) {
			specialMove = true;
			getProperties().getCombatPulse().attack(target);
			return true;
		}
		visualize(new Animation(5387, Priority.HIGH), Graphics.create(1523));
		Projectile.magic(this, target, 1318, 40, 36, 51, 10).send();
		int ticks = 2 + (int) Math.floor(getLocation().getDistance(target.getLocation()) * 0.5);
		getProperties().getCombatPulse().setNextAttack(4);
		GameWorld.Pulser.submit(new Pulse(ticks, this, target) {
			@Override
			public boolean pulse() {
				BattleState state = new BattleState(DreadfowlNPC.this, target);
				int hit = 0;
				if (CombatStyle.MAGIC.getSwingHandler().isAccurateImpact(DreadfowlNPC.this, target)) {
					hit = RandomFunction.randomize(3);
				}
				state.setEstimatedHit(hit);
				target.getImpactHandler().handleImpact(owner, hit, CombatStyle.MAGIC, state);
				return true;
			}
		});
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6825, 6826 };
	}

}
