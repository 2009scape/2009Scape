package plugin.quest.free.princealirescue;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.object.GameObject;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle prince ali rescure quest interaction nodes.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class PrinceAliRescuePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2881).getConfigurations().put("option:open", this);// prison
		// door.
		ObjectDefinition.forId(4639).getConfigurations().put("option:open", this);// door
		// to
		// jail
		NPCDefinition.forId(925).getConfigurations().put("option:talk-to", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest("Prince Ali Rescue");
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : node instanceof NPC ? ((NPC) node).getId() : 0;
		switch (id) {
		case 925:
			player.getDialogueInterpreter().open(925, node);
			break;
		case 2881:
			switch (quest.getStage(player)) {
			case 60:
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
				break;
			case 50:
				if (player.getAttribute("keli-gone", 0) > GameWorld.getTicks()) {
					if (player.getInventory().contains(2418, 1)) {
						player.getPacketDispatch().sendMessage("You unlock the door.");
						DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
					} else {
						player.getPacketDispatch().sendMessage("The door is locked.");
					}
				} else {
					player.getPacketDispatch().sendMessage("You'd better get rid of Lady Keli before trying to go through there.");
				}
				break;
			default:
				player.getPacketDispatch().sendMessage("You'd better get rid of Lady Keli before trying to go through there.");
				break;
			}
			break;
		}
		return true;
	}

	static Location[] locs = new Location[] { new Location(3268, 3227, 0), Location.create(3268, 3228, 0), Location.create(3267, 3228, 0), Location.create(3267, 3227, 0) };

	@Override
	public Location getDestination(final Node node, Node n) {
		if (n instanceof NPC) {
			NPC npc = ((NPC) n);
			if (npc.getLocation().equals(new Location(3268, 3226, 0))) {
				return locs[0];
			} else if (npc.getLocation().equals(new Location(3268, 3229, 0))) {
				return locs[1];
			} else if (npc.getLocation().equals(new Location(3267, 3229, 0))) {
				return locs[2];
			} else if (npc.getLocation().equals(new Location(3267, 3226, 0))) {
				return locs[3];
			}
		}
		return null;
	}

}