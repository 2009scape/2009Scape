package plugin.random.rivertroll;

import core.cache.def.impl.NPCDefinition;
import plugin.ame.AntiMacroEvent;
import plugin.ame.AntiMacroNPC;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.tools.RandomFunction;

/**
 * Handles the river troll npc.
 * @author Vexia
 */
public final class RiverTrollNPC extends AntiMacroNPC {

	/**
	 * The river troll npc.
	 */
	private static final int[] IDS = new int[] { 391, 392, 393, 394, 395, 396 };

	/**
	 * Constructs a new {@code RiverTrollNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 * @param event the event.
	 * @param player the player.
     */
	public RiverTrollNPC(int id, Location location, AntiMacroEvent event, Player player) {
		super(id, location, event, player);
	}

	@Override
	public void init() {
		// Remove.
		NPCDefinition def = NPCDefinition.forId(getId());
		def.getHandlers().put("attack_animation", new Animation(284));
		def.getHandlers().put("defence_animation", new Animation(285));
		def.getHandlers().put("death_animation", new Animation(287));
		super.init();
		setRespawn(false);
		getProperties().getCombatPulse().attack(player);
		sendChat("Fishies be mine! Leave dem fishies!");
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (!getProperties().getCombatPulse().isAttacking()) {
			getProperties().getCombatPulse().attack(player);
		}
		if (getProperties().getCombatPulse().isAttacking()) {
			if (RandomFunction.random(20) < 3) {
				sendChat("Fishies be mine! Leave dem fishies!");
			}
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
