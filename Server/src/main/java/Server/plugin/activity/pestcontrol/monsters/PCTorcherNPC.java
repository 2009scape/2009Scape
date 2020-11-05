package plugin.activity.pestcontrol.monsters;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatSpell;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.InteractionType;
import core.game.node.entity.combat.equipment.SpellType;
import core.game.node.entity.combat.handlers.MagicSwingHandler;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.world.map.Location;
import core.game.world.map.MapDistance;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Plugin;
import plugin.activity.pestcontrol.PestControlSession;

/**
 * Handles the torcher pest control NPC.
 * @author Emperor
 */
public final class PCTorcherNPC extends AbstractNPC {

	/**
	 * The torcher spell.
	 */
	private static final TorcherSpell SPELL = new TorcherSpell();

	/**
	 * The combat swing handler.
	 */
	private static final CombatSwingHandler SWING_HANDLER = new MagicSwingHandler() {

		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			if (!isProjectileClipped(entity, victim, false)) {
				return InteractionType.NO_INTERACT;
			}
			if (victim.getCenterLocation().withinDistance(entity.getCenterLocation(), 15) && isAttackable(entity, victim) != InteractionType.NO_INTERACT) {
				if (victim.getLocation().withinDistance(entity.getLocation(), MapDistance.RENDERING.getDistance() / 2)) {
					entity.getWalkingQueue().reset();
				}
				return InteractionType.STILL_INTERACT;
			}
			return InteractionType.NO_INTERACT;
		}
	};

	/**
	 * The pest control session.
	 */
	private PestControlSession session;

	/**
	 * Constructs a new {@code PCTorcherNPC} {@code Object}.
	 */
	public PCTorcherNPC() {
		super(3752, null);
	}

	/**
	 * Constructs a new {@code PCTorcherNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public PCTorcherNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public void init() {
		super.setAggressive(true);
		super.init();
		super.getDefinition().setCombatDistance(10);
		super.walkRadius = 64;
		getProperties().getCombatPulse().setStyle(CombatStyle.MAGIC);
		super.getProperties().setAutocastSpell(SPELL);
		session = getExtension(PestControlSession.class);
	}

	@Override
	public void tick() {
		super.tick();
		if (session != null && !inCombat() && !getProperties().getCombatPulse().isAttacking()) {
			getProperties().getCombatPulse().attack(session.getSquire());
		}
	}

	@Override
	public void onImpact(final Entity entity, BattleState state) {
		super.onImpact(entity, state);
		if (session != null && state != null && entity instanceof Player) {
			int total = 0;
			if (state.getEstimatedHit() > 0) {
				total += state.getEstimatedHit();
			}
			if (state.getSecondaryHit() > 0) {
				total += state.getSecondaryHit();
			}
			session.addZealGained((Player) entity, total);
		}
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return SWING_HANDLER;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new PCTorcherNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3752, 3753, 3754, 3755, 3756, 3757, 3758, 3759, 3760, 3761 };
	}

}

class TorcherSpell extends CombatSpell {

	/**
	 * Constructs a new {@code TorcherSpell} {@code Object}.
	 */
	public TorcherSpell() {
		super(SpellType.STRIKE, SpellBook.MODERN, 0, 0.0, -1, -1, new Animation(3882, Priority.HIGH), Graphics.create(646), Projectile.create((Entity) null, null, 647, 40, 36, 52, 75, 15, 11), new Graphics(648, 96));
	}

	@Override
	public int getMaximumImpact(Entity entity, Entity victim, BattleState state) {
		return entity.getProperties().getCurrentCombatLevel() / 7;
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		return this;
	}

}