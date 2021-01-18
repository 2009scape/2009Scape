package plugin.random.treespirit;

import core.cache.def.impl.NPCDefinition;
import plugin.ame.AntiMacroEvent;
import plugin.ame.AntiMacroNPC;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.tools.RandomFunction;

/**
 * Handles the Tree Spirit NPCs
 * @author Splinter
 */
public final class TreeSpiritRandomNPC extends AntiMacroNPC {

	/**
	 * The tree spirit npc ids.
	 */
	private static final int[] IDS = new int[] { 438, 439, 440, 441, 442, 443 };

	/**
	 * Constructs a new {@code TreeSpiritRandomNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 * @param event the event.
	 * @param player the player.
     */
	public TreeSpiritRandomNPC(int id, Location location, AntiMacroEvent event, Player player) {
		super(id, location, event, player);
	}

	@Override
	public void init() {
		NPCDefinition def = NPCDefinition.forId(getId());
		def.getHandlers().put("attack_animation", new Animation(94));
		def.getHandlers().put("defence_animation", new Animation(95));
		def.getHandlers().put("death_animation", new Animation(96));
		super.init();
		setRespawn(false);
		getProperties().getCombatPulse().attack(player);
		sendChat("Leave these woods and never return!");
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (!getProperties().getCombatPulse().isAttacking()) {
			getProperties().getCombatPulse().attack(player);
		}
		if (getProperties().getCombatPulse().isAttacking()) {
			if (RandomFunction.random(40) < 2) {
				sendChat("Leave these woods and never return!");
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
