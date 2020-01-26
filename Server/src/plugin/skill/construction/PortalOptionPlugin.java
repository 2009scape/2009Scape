package plugin.skill.construction;


import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.member.construction.HouseLocation;
import org.crandor.game.content.skill.member.construction.HouseManager;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.*;

/**
 * Handles the house portal options.
 * @author Emperor
 *
 */
@InitializablePlugin
public final class PortalOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (HouseLocation hl : HouseLocation.values()) {
			ObjectDefinition.forId(hl.getPortalId()).getConfigurations().put("option:enter", this);
		}
		ObjectDefinition.forId(13405).getConfigurations().put("option:lock", this);
		ObjectDefinition.forId(13405).getConfigurations().put("option:enter", this);
		PluginManager.definePlugin(new PortalDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		GameObject object = node.asObject();
		if (object.getId() == 13405 && option.equals("enter")) {
			HouseManager.leave(player);
			return true;
		}
		if (option.equals("lock")) {
			if (player.getHouseManager().isInHouse(player)) {
				player.getHouseManager().setLocked(player.getHouseManager().isLocked() ? false : true);
				player.sendMessage("Your house is now "+(player.getHouseManager().isLocked() ? "locked." : "unlocked." ));
				return true;
			}
			player.sendMessage("This is not your house.");
			return true;
		}
		player.setAttribute("con:portal", object.getId());
		player.getDialogueInterpreter().open("con:portal");
		return true;
	}
	
	/**
	 * Handles the portal dialogue.
	 * @author Emperor
	 */
	@PluginManifest(type= PluginType.DIALOGUE)
	private class PortalDialogue extends DialoguePlugin {
		
		/**
		 * Constructs a new {@code PortalDialogue} {@code Object}
		 */
		public PortalDialogue() {
			
		}
		
		/**
		 * Constructs a new {@code PortalDialogue} {@code Object}
		 * @param player The player.
		 */
		public PortalDialogue(Player player) {
			super(player);
		}
		
		@Override
		public DialoguePlugin newInstance(Player player) {
			return new PortalDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			//If the player does not have a house
			if (!player.getHouseManager().hasHouse()) {
				player.getPacketDispatch().sendMessage("<col=FF0000>You don't have a house, talk to an estate agent to purchase a house.");
			}
			//If the Player has a house, but does not have their house moved to the portal they are interacting with
			else if (player.getHouseManager().getLocation().getPortalId() != player.getAttribute("con:portal", -1)) {
				player.getPacketDispatch().sendMessage("<col=FF0000>Your house is in " + player.getHouseManager().getLocation().getName() + ".");
				player.sendMessage("<col=FF0000>Speak with an estate agent to change your house location.");
			}

			//If the above do not qualify, give the player options for housing
			else {
				options("Go to your house", "Go to your house (building mode)", "Go to a friend's house", "Never mind");
				stage = 1;

			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			end();
			switch (stage) {
			case 1:
				switch (buttonId) {
				case 1:
				case 2:
					player.getHouseManager().enter(player, buttonId == 2, true);
					break;
				case 3:
					player.getDialogueInterpreter().sendInput(true, "Enter friend's name:");
					player.setAttribute("runscript", new RunScript() {
						@Override
						public boolean handle() {
							Player p = Repository.getPlayerByName((String) getValue());
							if (p == null || !p.isActive()) {
								player.getPacketDispatch().sendMessage("This player is not online.");
								return true;
							}
							if (p.getUsername().equals(player.getUsername())) {
								player.getPacketDispatch().sendMessage("You aren't a friend of yourself!");
								return true;
							}
							if (!p.getHouseManager().isLoaded()) {
								player.getPacketDispatch().sendMessage("This player is not at home right now.");
								return true;
							}
							if (p.getHouseManager().isBuildingMode()) {
								player.getPacketDispatch().sendMessage("This player is in building mode.");
								return true;
							}
							if (p.getHouseManager().isLocked()) {
								player.getPacketDispatch().sendMessage("The other player has locked their house.");
								return true;
							}
							p.setAttribute("poh_owner", getValue());
							p.getHouseManager().enter(player, false, false);
							return true;
						}
					});
					break;
				}
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("con:portal") };
		}
	}
}