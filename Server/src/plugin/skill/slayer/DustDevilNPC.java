package plugin.skill.slayer;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.slayer.Equipment;
import org.crandor.game.content.skill.member.slayer.Tasks;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.InteractionType;
import org.crandor.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;

/**
 * Handles the dust devil npc.
 * @author Vexia
 */
@InitializablePlugin
public final class DustDevilNPC extends AbstractNPC {

	/**
	 * The skills.
	 */
	private static final int[] SKILLS = new int[] { Skills.ATTACK, Skills.STRENGTH, Skills.RANGE, Skills.MAGIC };

	/**
	 * The combat handler.
	 */
	private static final MeleeSwingHandler COMBAT_HANDLER = new MeleeSwingHandler() {
		@Override
		public void impact(Entity entity, Entity victim, BattleState state) {
			if (victim instanceof Player) {
				final Player player = (Player) victim;
				if (!Equipment.FACEMASK.hasEquipment(player)) {
					for (int i : SKILLS) {
						player.getSkills().updateLevel(i, -player.getSkills().getStaticLevel(i), 0);
					}
					player.getSkills().decrementPrayerPoints(player.getSkills().getStaticLevel(Skills.PRAYER) / 2);
					state.setEstimatedHit(14);
				}
			}
			super.impact(entity, victim, state);
		}

		@Override
		public InteractionType isAttackable(Entity entity, Entity victim) {
			return CombatStyle.MAGIC.getSwingHandler().isAttackable(entity, victim);
		}
	};

	/**
	 * Constructs a new {@code DustDevilNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public DustDevilNPC(int id, Location location) {
		super(id, location);
		super.getProperties().getCombatPulse().setHandler(COMBAT_HANDLER);
	}

	/**
	 * Constructs a new {@code DustDevilNPC} {@code Object}.
	 */
	public DustDevilNPC() {
		super(0, null);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return COMBAT_HANDLER;
	}

	@Override
	public void checkImpact(BattleState state) {
		super.checkImpact(state);
		if (state.getAttacker() instanceof Player) {
			Player player = (Player) state.getAttacker();
			if (!Equipment.FACEMASK.hasEquipment(player)) {
				state.neutralizeHits();
			}
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new DustDevilNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return Tasks.DUST_DEVILS.getNpcs();
	}

}
