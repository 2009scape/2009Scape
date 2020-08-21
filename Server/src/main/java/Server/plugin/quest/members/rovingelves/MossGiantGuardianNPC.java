package plugin.quest.members.rovingelves;

import core.cache.def.impl.NPCDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * The level 84 Moss Giant in Glarial's tomb.
 * @author Splinter
 */
public final class MossGiantGuardianNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code MossGiantGuardianNPC} {@code Object}.
	 */
	public MossGiantGuardianNPC() {
		super(0, null);
	}

	/**
	 * Constructs a new {@code MossGiantGuardianNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public MossGiantGuardianNPC(int id, Location location) {
		super(id, location);
		this.setAggressive(true);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new MossGiantGuardianNPC(id, location);
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			final Player player = (Player) killer;
			final Quest quest = player.getQuestRepository().getQuest("Roving Elves");
			if (quest.getStage(player) == 15 && !player.getInventory().contains(RovingElves.CONSECRATION_SEED.getId(), 1)) {
				player.getPacketDispatch().sendMessages("A small grey seed drops on the ground.");
				GroundItemManager.create(new Item(RovingElves.CONSECRATION_SEED.getId()), getLocation(), player);
			}
		}
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				NPCDefinition.forId(getIds()[0]).getConfigurations().put("option:attack", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				((NPC) node).attack(player);
				return true;
			}

		});
		return super.newInstance(arg);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1681 };
	}

}
