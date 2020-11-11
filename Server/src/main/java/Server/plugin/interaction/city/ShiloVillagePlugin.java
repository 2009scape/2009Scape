package plugin.interaction.city;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import plugin.dialogue.DialoguePlugin;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Handles shilo village interactions.
 * @author Vexia
 */
@InitializablePlugin
public final class ShiloVillagePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(511).getHandlers().put("option:pay-fare", this);
		NPCDefinition.forId(510).getHandlers().put("option:pay-fare", this);
		ObjectDefinition.forId(2230).getHandlers().put("option:board", this);// cart
		// travel.
		ObjectDefinition.forId(2230).getHandlers().put("option:pay-fare", this);// cart
		// travel.
		ObjectDefinition.forId(2265).getHandlers().put("option:board", this);// cart
		// travel.
		ObjectDefinition.forId(2265).getHandlers().put("option:pay-fare", this);// cart
		// travel.
		PluginManager.definePlugin(new VillageCartDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 511:
		case 510:
			player.getDialogueInterpreter().open(510, node);
			break;
		case 2230:
		case 2265:
			player.getDialogueInterpreter().open(510, RegionManager.getNpc(player, node.getId() == 2230 ? 510 : 511));
			break;
		}
		return true;
	}

	/**
	 * Handles the vigroy dialogue.
	 * @author Vexia
	 */
	public static final class VillageCartDialogue extends DialoguePlugin {

		/**
		 * If the ride is to shilo.
		 */
		private boolean shilo;

		/**
		 * Constructs a new {@Code VigroyDialogue} {@Code Object}
		 * @param player the player.
		 */
		public VillageCartDialogue(Player player) {
			super(player);
		}

		/**
		 * Constructs a new {@Code VigroyDialogue} {@Code Object}
		 */
		public VillageCartDialogue() {
			/**
			 * empty.
			 */
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new VillageCartDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			shilo = npc.getId() == 510;
			npc("Hello Bwana!");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				npc("I am offering a cart ride to " + (shilo ? "Shilo Village" : "Brimhaven") + " if you're", "interested? It will cost 10 gold coins. Is that Ok?");
				stage++;
				break;
			case 1:
				player("Yes please, I'd like to go to " + (shilo ? "Shilo Village" : "Brimhaven") + ".");
				stage++;
				break;
			case 2:
				npc("Great! Just hop into the cart then and we'll go!");
				stage++;
				break;
			case 3:
				if (!player.getInventory().contains(995, 10)) {
					player.sendMessage("Not enough coins.");
					end();
					break;
				}
				interpreter.sendDialogue("You hop into the cart and the driver urges the horses on. You take", "a taxing journey through the jungle to " + (shilo ? "Shilo Village" : "Brimhaven") + ".");
				stage++;
				break;
			case 4:
				interpreter.sendDialogue("You pay the fare and hand 10 gold coins to " + npc.getName() + ".");
				stage++;
				break;
			case 5:
				if (player.getInventory().remove(new Item(995, 10))) {
					interpreter.sendDialogue("You feel tired from the journey, but at least you didn't have to walk", "all that distance.");
					stage++;
				} else {
					end();
				}
				break;
			case 6:
				end();
				player.getProperties().setTeleportLocation(shilo ? Location.create(2834, 2951, 0) : Location.create(2780, 3212, 0));
				// Use Vigroy and Hajedy's cart service
				player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 1, 3);
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 511, 510 };
		}

	}
}
