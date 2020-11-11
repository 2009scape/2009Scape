package plugin.npc.other;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.equipment.SwitchAttack;
import core.game.node.entity.combat.handlers.DragonfireSwingHandler;
import core.game.node.entity.combat.handlers.MultiSwingHandler;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.impl.Animator.Priority;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.game.world.update.flag.context.Animation;

/**
 * Handles a metal dragon (bronze, iron, steel).
 * @author Emperor
 */
@InitializablePlugin
public final class MetalDragonNPC extends AbstractNPC {

	/**
	 * The dragonfire attack.
	 */
	private static final SwitchAttack DRAGONFIRE = DragonfireSwingHandler.get(false, 52, new Animation(81, Priority.HIGH), null, null, Projectile.create((Entity) null, null, 54, 40, 36, 41, 46, 20, 255));

	/**
	 * Handles the combat.
	 */
	private final CombatSwingHandler combatAction = new MultiSwingHandler(true, new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), new Animation(80, Priority.HIGH)), new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), new Animation(91, Priority.HIGH)), DRAGONFIRE);

	/**
	 * Constructs a new {@code MetalDragonNPC} {@code Object}.
	 */
	public MetalDragonNPC() {
		super(1590, null);
	}

	/**
	 * Constructs a new {@code MetalDragonNPC} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	public MetalDragonNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return combatAction;
	}

	@Override
	public void finalizeDeath(Entity killer) {
		if (killer.isPlayer() && (killer.getViewport().getRegion().getId() == 10899 || killer.getViewport().getRegion().getId() == 10900)) {
			Player player = killer.asPlayer();
			player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 2, 9);
		}
		super.finalizeDeath(killer);
	}

	@Override
	public int getDragonfireProtection(boolean fire) {
		return 0x2 | 0x4 | 0x8;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MetalDragonNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1590, // Bronze dragon
				1591, // Iron dragon
				1592, // Steel dragon
				3590 // Steel dragon (POH)
		};
	}

}
