package plugin.interaction.item;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.TeleportManager.TeleportType;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.impl.WildernessZone;
import org.crandor.plugin.Plugin;

import org.crandor.plugin.InitializablePlugin;

/**
 * Represents the rotten potato plugin.
 * @author 'Vexia
 */
@InitializablePlugin
public final class TeleportCrystalPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(6099).getConfigurations().put("option:activate", this);
		ItemDefinition.forId(6100).getConfigurations().put("option:activate", this);
		ItemDefinition.forId(6101).getConfigurations().put("option:activate", this);
		ItemDefinition.forId(6102).getConfigurations().put("option:activate", this);
		new TeleportCrystalDialogue().init();
		return this;
	}

	 @Override
	    public boolean handle(Player player, Node node, String option) {
	        if (!WildernessZone.checkTeleport(player, 20)) {
	            player.getPacketDispatch().sendMessage("The crystal is unresponsive.");
	            return true;
	        }
	        player.getDialogueInterpreter().open(TeleportCrystalDialogue.ID, 1, node.asItem().getId());
	        //degrade(player, node.asItem());
	        return true;
	    }
	

	@Override
	public boolean isWalk() {
		return false;
	}

	 
    
	/**
	 * Represents the rotten potato dialogue.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class TeleportCrystalDialogue extends DialoguePlugin {

		/**
		 * Represents the rotten potato dialogue id.
		 */
		public static final int ID = 3999111;
		private Integer itemId;

		/**
		 * Constructs a new {@code RottenPotatoDialogue} {@code Object}.
		 */
		public TeleportCrystalDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code RottenPotatoDialogue} {@code Object}.
		 * @param player the player.
		 */
		public TeleportCrystalDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new TeleportCrystalDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			itemId = (Integer) args[1];
			switch ((Integer) args[0]) {
			case 1:
				interpreter.sendOptions("Select an Option", "Save your location", "Teleport to your location", "Contact Bill Teach", "Teleport to Lletya");
				stage = 100;
				break;
			}
			
			
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			NPC npc = new NPC(2376);
			NPC billTeach = new NPC(3155);
			switch (stage) {
			case 100:
				switch(buttonId) {
				case 1:
					interpreter.sendDialogues(npc, null, "That'll cost you 100,000 coins, is that okay?");
					stage = 1000;
					break;
				case 2:
					player.getTeleporter().send(new Location(player.getGlobalData().getSavedX(), player.getGlobalData().getSavedY(), player.getGlobalData().getSavedH()), TeleportType.NORMAL);
					end();
					degrade(player, new Item(itemId));
					break;
				case 3:
					interpreter.sendDialogues(npc, null, "This will use up a charge of your crystal,", "are you sure you wish to continue?");
					stage = 3000;
					break;
				case 4:
					player.getTeleporter().send(new Location(2329, 3172), TeleportType.NORMAL);
			        break;
				}
				break;
			case 1000:
				interpreter.sendOptions("Select an Option", "Yes", "No");
				stage = 2000;
				break;
			case 2000:
				switch(buttonId) {
				case 1:
				if(player.getInventory().getAmount(995) >= 100000) {
					end();
					player.sendMessage("Your location has been saved succesfully!");
					player.getInventory().remove(new Item(995, 100000));
					player.getGlobalData().setSavedLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
				} else {
					interpreter.sendDialogues(npc, null, "I'm sorry, but you don't have enough coins", "to cover this.");
					stage = 7;
				}
				break;
				case 2:
					interpreter.sendOptions("Select an Option", "Save your location", "Teleport to your location", "Contact Bill Teach", "Teleport to Lletya");
					stage = 100;
					break;
					
				}
				break;
			case 3000:
				interpreter.sendOptions("Select an Option", "Yes", "No");
				stage = 4000;
				break;
				
			case 4000:
				switch(buttonId) {
				case 1:
					player.getDialogueInterpreter().open(billTeach.getId());
					degrade(player, new Item(itemId));
				break;
				case 2:
					interpreter.sendOptions("Select an Option", "Save your location", "Teleport to your location", "Contact Bill Teach", "Teleport to Lletya");
					stage = 100;
					break;
				}
				break;
				
			case 7:
				end();
				break;
			}
			return true;
		}


		/**
	     * Degrades the crystal from use.
	     * @param p     the player
	     * @param item  the crystal used
	     */
	    private static void degrade(Player p, Item item) {
	        int id = item.getId();
	        int newItem = item.getId() + 1;
	        if (id < 6102) {
	            p.getInventory().remove(new Item(id, 1));
	            p.getInventory().add(new Item(newItem, 1));
	            p.getPacketDispatch().sendMessage("Your teleportation crystal has degraded from use.");
	        } else {
	            p.getInventory().remove(new Item(id, 1));
	            p.getPacketDispatch().sendMessages("Your teleportation crystal has turned into dust,", "you can purchase a new one from Mama.");
	        }
	    }

		@Override
		public int[] getIds() {
			return new int[] { ID };
		}

	}

}
