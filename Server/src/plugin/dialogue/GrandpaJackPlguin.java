package plugin.dialogue;

import org.crandor.ServerConstants;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.DonatorType;
import org.crandor.game.node.entity.player.link.RunScript;
import org.crandor.game.node.entity.player.link.TeleportManager.TeleportType;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.Location;

/**
 * Represents the plugin used for the grandpa jack npc.
 * @author Vexia
 */
@InitializablePlugin
public final class GrandpaJackPlguin extends DialoguePlugin {

	/**
	 * If we're in the donator zone.
	 */
	private boolean donatorZone;

	/**
	 * Constructs a new {@code GrandpaJackPlguin} {@code Object}.
	 */
	public GrandpaJackPlguin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GrandpaJackPlugin} {@code Object}.
	 * @param player the player.
	 */
	public GrandpaJackPlguin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GrandpaJackPlguin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		donatorZone = player.getZoneMonitor().isInZone("Donator Zone");
		if (!donatorZone) {
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Hello young man!", "Come to visit old Grandpa Jack? I can tell ye stories", "for sure. I used to be the best fisherman these parts", "have seen!");
			stage = 1;
		} else {
			npc("Welcome, " + player.getUsername() + ". Before you is the wonderful land", "of the donators. Here at your disposal is access to", "an overubandance of resources. Your support for", "Keldagrim has not gone unrecognized.");
			stage = 3;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry, just passing through.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		case 3:
			npc("Is there anything else I can assist you with?");
			stage++;
			break;
		case 4:
			options("How do I leave this area?", "What is beyond the waterfall?", "Can you teleport me somewhere?", "No thanks.");
			stage = 5;
			break;
		case 5:
			switch (buttonId) {
			case 1:
				npc("You can use any teleport type or use the fairy", "ring located in the middle to be teleported back", "to lumbridge.");
				stage = 4;
				break;
			case 2:
				npc("Beyond that waterfall lays a land of extreme benefits", "accessible to only the most supportive players of Keldagrim.", "The area contains skilling resources including trees,", "rocks, fish and banks to sooth for easy skilling.");
				stage = 8;
				break;
			case 3:
				npc("Yes child, just tell me a location.");
				stage = 10;
				break;
			case 4:
				player("No thanks.");
				stage++;
				break;
			}
			break;
		case 6:
			npc("Very well, enjoy your day.");
			stage++;
			break;
		case 7:
			end();
			break;
		case 8:
			player("Am I allowed to venture past the waterfall?");
			stage++;
			break;
		case 9:
			if (player.getDonatorType() == DonatorType.EXTREME) {
				npc("Yes child! You are an extreme donator and have shown", "great compassion for Keldagrim. To show our gratitude", "we offer you the land of extreme donators.");
			} else {
				npc("I apologize child, only extreme donators are allowed", "to access that part of the land.");
			}
			stage = 4;
			break;
		case 10:
			interpreter.sendInput(true, "Enter a location: (e.g varrock)");
			player.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					String place = (String) getValue();
					place = place.replace("_", "");
					Location destination = null;
					for (Object[] data : ServerConstants.TELEPORT_DESTINATIONS_DONATOR) {
						for (int i = 1; i < data.length; i++) {
							if (place.equalsIgnoreCase((String) data[i])) {
								destination = (Location) data[0];
								break;
							}
						}
					}
					if (destination != null) {
						player.getTeleporter().send(destination, TeleportType.FAIRY_RING);
					} else {
						npc("Sorry child, I either don't know where that is or", "cannot teleport you there.");
					}
					return true;
				}
			});
			stage++;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 230 };
	}
}
