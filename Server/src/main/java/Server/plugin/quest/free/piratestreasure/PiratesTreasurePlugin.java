package plugin.quest.free.piratestreasure;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.DigAction;
import core.game.content.global.action.DigSpadeHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.HintIconManager;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.plugin.Plugin;

/**
 * Represents the pirates treasure plugin.
 * @author Vexia
 * 
 */
public final class PiratesTreasurePlugin extends OptionHandler {

	/**
	 * Represents the dig reward to use.
	 */
	private static final DigAction DIG_ACTION = new TreasureDigPlugin();

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2079).getConfigurations().put("option:open", this);// blue
		// moon
		// chest(pirates
		// treasure
		// quest)
		ItemDefinition.forId(433).getConfigurations().put("option:read", this);// pirate
		// message.
		ItemDefinition.forId(7956).getConfigurations().put("option:open", this);
		for (Location l : TreasureDigPlugin.LOCATIONS) {
			DigSpadeHandler.register(l, DIG_ACTION);
		}
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		int id = node instanceof GameObject ? ((GameObject) node).getId() : ((Item) node).getId();
		switch (id) {
		case 2079:
			switch (option) {
			case "open":
				player.getPacketDispatch().sendMessage("The chest is locked.");
				break;
			}
			break;
		case 7956:// hectors casket
			if (player.getInventory().remove(PiratesTreasure.CASKET)) {
				for (Item i : PiratesTreasure.CASKET_REWARDS) {
					if (!player.getInventory().add(i)) {
						GroundItemManager.create(i, player);
					}
				}
				player.getPacketDispatch().sendMessage("You open the casket, and find One-Eyed Hector's treasure.");
			}
			break;
		case 433:
			switch (option) {
			case "read":
				player.getInterfaceManager().open(PiratesTreasure.MESSAGE_COMPONENT);
				player.getPacketDispatch().sendString("Visit the city of the White Knights. In the park,", 222, 5);
				player.getPacketDispatch().sendString("Saradomin points to the X which marks the spot.", 222, 6);
				player.setAttribute("/save:pirate-read", true);
				break;
			}
		}
		return true;
	}

	/**
	 * Represents the treasure dig plugin.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class TreasureDigPlugin implements DigAction {

		/**
		 * Represents the locations at which this event will occur.
		 */
		private static final Location[] LOCATIONS = new Location[] { new Location(2999, 3383, 0), Location.create(3000, 3383, 0) };

		@Override
		public void run(Player player) {
			final Quest quest = player.getQuestRepository().getQuest("Pirate's Treasure");
			player.lock(2);
			if (quest.getStage(player) == 20) {
				if (player.getSavedData().getQuestData().isGardenerAttack()) {
					player.getPacketDispatch().sendMessage("You dig a hole in the ground...");
					player.getPacketDispatch().sendMessage("and find a little chest of treasure.");
					quest.finish(player);
				} else {
					if (player.getAttribute("gardener-dug", false)) {
						return;
					}
					NPC npc = NPC.create(1217, player.getLocation().transform(0, 1, 0));
					npc.setAttribute("target", player);
					npc.setRespawn(false);
					npc.init();
					npc.sendChat("First moles, now this! Take this, vandal!");
					npc.getProperties().getCombatPulse().attack(player);
					HintIconManager.registerHintIcon(player, npc);
					player.setAttribute("gardener-dug", true);
				}
				return;
			}
		}

	}
}
