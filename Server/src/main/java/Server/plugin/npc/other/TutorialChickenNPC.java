package plugin.npc.other;

import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.world.map.Location;

/**
 * Represents the tutorial chicken npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class TutorialChickenNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code TutorialChickenNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public TutorialChickenNPC(int id, Location location) {
		super(id, location, true);
	}

	/**
	 * Constructs a new {@code TutorialChickenNPC} {@code Object}.
	 */
	public TutorialChickenNPC() {
		super(0, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new TutorialChickenNPC(id, location);
	}

	@Override
	public void onImpact(Entity entity, BattleState state) {
		super.onImpact(entity, state);
		if (state.getStyle() == CombatStyle.MAGIC) {
			final Player player = (Player) entity;
			if (TutorialSession.getExtension(player).getStage() == 70) {
				TutorialStage.load(player, 71, false);
			}
		}
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
	}

	@Override
	public int[] getIds() {
		return new int[] { 41 };
	}

}
