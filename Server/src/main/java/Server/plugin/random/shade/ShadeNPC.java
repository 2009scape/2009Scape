package plugin.random.shade;

import plugin.ame.AntiMacroEvent;
import plugin.ame.AntiMacroNPC;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;

/**
 * Handles the shade npc.
 * @author Vexia
 */
public final class ShadeNPC extends AntiMacroNPC {

	/**
	 * The shade npc ids.
	 */
	private static final int[] IDS = new int[] { 425, 426, 427, 428, 429, 430 };

	/**
	 * Constructs a new {@code ShadeNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 * @param event the event.
	 * @param player the player.
     */
	public ShadeNPC(int id, Location location, AntiMacroEvent event, Player player) {
		super(id, location, event, player);
	}

	@Override
	public void init() {
		super.init();
		setRespawn(false);
		getProperties().getCombatPulse().attack(player);
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (!getProperties().getCombatPulse().isAttacking()) {
			getProperties().getCombatPulse().attack(player);
		}
	}

	@Override
	public boolean isIgnoreMultiBoundaries(Entity victim) {
		return victim == player;
	}

	@Override
	public int[] getIds() {
		return IDS;
	}

}
