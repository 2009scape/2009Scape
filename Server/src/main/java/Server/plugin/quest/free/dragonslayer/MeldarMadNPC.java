package plugin.quest.free.dragonslayer;

import plugin.skill.Skills;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatSpell;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.DeathTask;
import core.game.node.entity.combat.InteractionType;
import core.game.node.entity.combat.equipment.ArmourSet;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.tools.RandomFunction;

/**
 * Represents the medlar the mad npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class MeldarMadNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 753 };

	/**
	 * Represents the cabbage npc.
	 */
	public static final Item CABBAGE = new Item(1965);

	/**
	 * Represents the messages to say when fighting.
	 */
	public static final String[] MESSAGES = new String[] { "Feel the wrath of my feet!", "By the power of custard!", "Let me drink my tea in peace.", "Leave me alone, I need to feed my pet rock." };

	/**
	 * Represents the swing handler.
	 */
	private final MeldarSwingHandler combatHandler = new MeldarSwingHandler();

	/**
	 * Constructs a new {@code MeldarMadNPC} {@code Object}.
	 */
	public MeldarMadNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MeldarMadNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private MeldarMadNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MeldarMadNPC(id, location);
	}

	@Override
	public void init() {
		super.init();
		getSkills().setLevel(Skills.MAGIC, 40);
		getSkills().setLevel(Skills.ATTACK, 30);
		getSkills().setLevel(Skills.STRENGTH, 35);
		getSkills().setLevel(Skills.DEFENCE, 10);
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (!DeathTask.isDead(this) && getProperties().getCombatPulse().isAttacking() && RandomFunction.random(0, 4) == 1) {
			sendChat(MESSAGES[RandomFunction.random(MESSAGES.length)]);
		}
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		GroundItemManager.create(DragonSlayer.PURPLE_KEY, getLocation(), ((Player) killer));
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatHandler;
	}

	@Override
	public int[] getIds() {
		return ID;
	}

	/**
	 * Represents the combat swing handler for meldar the mad npc.
	 * @author 'Vexia
	 */
	public class MeldarSwingHandler extends CombatSwingHandler {

		/**
		 * Represents the style to use.
		 */
		private CombatStyle style = CombatStyle.MAGIC;

		/**
		 * Represents the spell ids.
		 */
		private final int SPELL_IDS[] = new int[] { 8, 2, 7, 11 };

		/**
		 * Constructs a new {@code MeldarMapNPC} {@code Object}.
		 */
		public MeldarSwingHandler() {
			super(CombatStyle.MAGIC);
		}

		@Override
		public InteractionType canSwing(Entity entity, Entity victim) {
			return getType().getSwingHandler().canSwing(entity, victim);
		}

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			if (RandomFunction.random(5) == 3) {
				style = CombatStyle.MELEE;
			} else {
				style = CombatStyle.MAGIC;
			}
			return 2;
		}

		@Override
		public void visualize(Entity entity, Entity victim, BattleState state) {
			if (style == CombatStyle.MAGIC) {
				state.setSpell(getCombatSpell());
				for (int i = 0; i < 2; i++) {
					Location l = getLocation().transform(RandomFunction.random(1, 5), RandomFunction.random(1, 5), 0);
					if (RegionManager.isTeleportPermitted(l) && GroundItemManager.get(CABBAGE.getId(), l, null) == null && l.getY() <= 9651 && l.getY() >= 9644) {
						if (victim instanceof Player)
							((Player) victim).getPacketDispatch().sendPositionedGraphic(86, 1, 0, l);
						GroundItemManager.create(CABBAGE, l, ((Player) victim));
					}
				}
			}
			style.getSwingHandler().visualize(entity, victim, state);
		}

		@Override
		public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
			style.getSwingHandler().visualizeImpact(entity, victim, state);
		}

		@Override
		public void adjustBattleState(Entity entity, Entity victim, BattleState state) {
			style.getSwingHandler().adjustBattleState(entity, victim, state);
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
			style.getSwingHandler().impact(entity, victim, state);
		}

		@Override
		public ArmourSet getArmourSet(Entity e) {
			return style.getSwingHandler().getArmourSet(e);
		}

		@Override
		public double getSetMultiplier(Entity e, int skillId) {
			return style.getSwingHandler().getSetMultiplier(e, skillId);
		}

		/**
		 * Method used to get a combat spell.
		 * @return the spell.
		 */
		public CombatSpell getCombatSpell() {
			return ((CombatSpell) SpellBookManager.SpellBook.MODERN.getSpell(SPELL_IDS[RandomFunction.random(SPELL_IDS.length)]));
		}
	}
}