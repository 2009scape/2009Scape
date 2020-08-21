package plugin.quest.free.dragonslayer;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.world.map.Location;

/**
 * Represents the worm brain npc.
 * @author 'Vexia
 * @versin 1.0
 */
public final class WormbrainNPC extends AbstractNPC {

	/**
	 * The NPC ids of NPCs using this plugin.
	 */
	private static final int[] ID = { 745 };

	/**
	 * Constructs a new {@code WormBrainPlugin} {@code Object}.
	 */
	public WormbrainNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code AlKharidWarriorPlugin} {@code Object}.
	 * @param id The NPC id.
	 * @param location The location.
	 */
	private WormbrainNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new WormbrainNPC(id, location);
	}

	@Override
	public void finalizeDeath(final Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			if (((Player) killer).getQuestRepository().getQuest("Dragon Slayer").getStage(killer.asPlayer()) == 20 && !((Player) killer).getInventory().containsItem(DragonSlayer.WORMBRAIN_PIECE) && !((Player) killer).getBank().containsItem(DragonSlayer.WORMBRAIN_PIECE)) {
				GroundItemManager.create(DragonSlayer.WORMBRAIN_PIECE, getLocation(), ((Player) killer));
				((Player) killer).getPacketDispatch().sendMessage("Wormbrain drops a map piece on the floor.");
			}
		}
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (entity instanceof Player) {
			final Player player = (Player) entity;
			if (player.getQuestRepository().getQuest("Dragon Slayer").getStage(player) != 20) {
				player.getPacketDispatch().sendMessage("The goblin is already in prison. You have no reason to attack him.");
				return false;
			}
		}
		return super.isAttackable(entity, style);
	}

	@Override
	public int[] getIds() {
		return ID;
	}

}
