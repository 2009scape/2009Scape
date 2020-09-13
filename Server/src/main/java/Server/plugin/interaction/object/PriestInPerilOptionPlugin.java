package plugin.interaction.object;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.content.global.action.DoorActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.quest.members.PriestInPeril;

/**
 * Represents the quest node plugin handler.
 * @author 'Vexia
 */
@InitializablePlugin
public class PriestInPerilOptionPlugin extends OptionHandler {
	/**
	 * (non-Javadoc)
	 * @see Plugin#newInstance(Object)
	 */
	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(3444).getHandlers().put("option:open", this);
		/** the gate in the chamber near the dog. */
		ObjectDefinition.forId(3445).getHandlers().put("option:open", this);
		/** the gate in the temple to get to the other side. */
		ObjectDefinition.forId(30707).getHandlers().put("option:open", this);
		/** the "knock-at" door. */
		ObjectDefinition.forId(30707).getHandlers().put("option:knock-at", this);
		/** the "knock-at" door. */
		ObjectDefinition.forId(30708).getHandlers().put("option:open", this);
		/** the "knock-at" door. */
		ObjectDefinition.forId(30708).getHandlers().put("option:knock-at", this);
		/** the "knock-at" door. */
		ObjectDefinition.forId(30575).getHandlers().put("option:climb-up", this);
		/** represents the ladder to climb back up. */
		ObjectDefinition.forId(30575).getHandlers().put("option:climb-up", this);
		/** represents the ladder to climb back up. */
		ObjectDefinition.forId(30728).getHandlers().put("option:open", this);
		/** the coffin. */
		ObjectDefinition.forId(3463).getHandlers().put("option:open", this);
		/** the drezel door. */
		ObjectDefinition.forId(3463).getHandlers().put("option:talk-through", this);
		/** talk-through. */
		ObjectDefinition.forId(3485).getHandlers().put("option:search", this);
		/** teh well. */
		ObjectDefinition.forId(3496).getHandlers().put("option:study", this);
		/** teh golden hammer */
		ObjectDefinition.forId(3496).getHandlers().put("option:take-from", this);
		/** the golden hammer. */
		ObjectDefinition.forId(3498).getHandlers().put("option:study", this);
		/** teh golden needle. */
		ObjectDefinition.forId(3498).getHandlers().put("option:take-from", this);
		/** the golden needle. */
		ObjectDefinition.forId(3495).getHandlers().put("option:study", this);
		/** teh golden pot. */
		ObjectDefinition.forId(3495).getHandlers().put("option:take-from", this);
		/** the golden pot. */
		ObjectDefinition.forId(3497).getHandlers().put("option:study", this);
		/** teh golden feather. */
		ObjectDefinition.forId(3497).getHandlers().put("option:take-from", this);
		/** the golden feather. */
		ObjectDefinition.forId(3494).getHandlers().put("option:study", this);
		/** teh golden candle. */
		ObjectDefinition.forId(3494).getHandlers().put("option:take-from", this);
		/** the golden candle. */
		ObjectDefinition.forId(3499).getHandlers().put("option:study", this);
		/** teh golden key/iron. */
		ObjectDefinition.forId(3499).getHandlers().put("option:take-from", this);
		/** the golden key/iron */
		ObjectDefinition.forId(3493).getHandlers().put("option:study", this);
		/** teh tinder box. */
		ObjectDefinition.forId(3493).getHandlers().put("option:take-from", this);
		/** the golden tinder box. */
		ObjectDefinition.forId(3443).getHandlers().put("option:pass-through", this);
		/** the barrier. */
		ObjectDefinition.forId(30573).getHandlers().put("option:open", this);
		/** the door to get back. */
		NPCDefinition.forId(7711).getHandlers().put("option:attack", this);
		/** represents the dog attack option. */
		ObjectDefinition.forId(30571).getHandlers().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest(PriestInPeril.NAME);
		int id = node instanceof GameObject ? ((GameObject) node).getId() : ((NPC) node).getId();
		switch (option) {
		case "study":
			player.getInterfaceManager().open(new Component(272));
			int item = 0;
			String message = "";
			if (id == 3496) {
				if (!player.getGameAttributes().getAttribute("priest_in_peril:hammer", false)) {
					item = 2949;
				} else {
					item = 2347;
				}
				message = "Saradomin is the hammer that crushes evil everywhere.";
			}
			if (id == 3498) {
				if (!player.getGameAttributes().getAttribute("priest_in_peril:needle", false)) {
					item = 2951;
				} else {
					item = 1733;
				}
				message = "Saradomin is the needle that binds our lives together.";
			}
			if (id == 3495) {
				if (!player.getGameAttributes().getAttribute("priest_in_peril:pot", false)) {
					item = 2948;
				} else {
					item = 1931;
				}
				message = "Saradomin is the vessle that keeps our lives from harm.";
			}
			if (id == 3497) {
				if (!player.getGameAttributes().getAttribute("priest_in_peril:feather", false)) {
					item = 2950;
				} else {
					item = 314;
				}
				message = "Saradomin is the delicate touch that brushes us with love.";
			}
			if (id == 3494) {
				if (!player.getGameAttributes().getAttribute("priest_in_peril:candle", false)) {
					item = 2947;
				} else {
					item = 36;
				}
				message = "Saradomin is the light that shines throughout our lives.";
			}
			if (id == 3499) {
				if (!player.getGameAttributes().getAttribute("priest_in_peril:key", false)) {
					item = 2945;
				} else {
					item = 2944;
				}
				message = "Saradomin is the key that unlocks the mysteries of life.";
			}
			if (id == 3493) {
				if (!player.getGameAttributes().getAttribute("priest_in_peril:tinderbox", false)) {
					item = 2946;
				} else {
					item = 590;
				}
				message = "Saradomin is the spark that lights the fire in our hearts.";
			}
			player.getPacketDispatch().sendString(message, 272, 17);
			player.getPacketDispatch().sendItemZoomOnInterface(item, 175, 272, 4);
			break;
		case "take-from":
			player.getImpactHandler().handleImpact(player, 2, CombatStyle.MELEE);
			player.getPacketDispatch().sendMessage("A holy power prevents you stealing from the monument!");
			break;
		}
		switch (id) {
		case 3444:
			/** the first gate we encounter. */
			if (quest.getStage(player) <= 13) {
				player.getDialogueInterpreter().sendDialogues(player, null, "Hmmm... from the looks of things, it seems as though", "somebody has been trying to force this door open. It's", "still securely locked however.");
				return true;
			}
			DoorActionHandler.handleDoor(player, (GameObject) node);
			break;
		case 30573:
			player.getProperties().setTeleportLocation(Location.create(3440, 9887, 0));
			player.getPacketDispatch().sendMessage("You cilmb down through the trap door...");
			break;
		case 3443:
			/** the barrier. */
			if (!player.getQuestRepository().isComplete(PriestInPeril.NAME)) {
				player.getPacketDispatch().sendMessage("A magic force prevents you from passing through.");
			} else {
				player.getProperties().setTeleportLocation(Location.create(3425, 3485, 0));
				player.getPacketDispatch().sendMessage("You pass through the holy barrier.");
			}
			break;
		case 3485:
			/** the well. */
			player.getDialogueInterpreter().sendDialogue("You look down the well and see the filthy polluted water of the river", "Salve moving slowly along.");
			break;
		case 3463:
			/** the drezel door. */
			switch (option) {
			case "open":

				if (quest.getStage(player) < 15) {
					player.getPacketDispatch().sendMessage("The door is securely locked shut.");
				} else {
					DoorActionHandler.handleDoor(player, (GameObject) node);
				}
				break;
			case "talk-through":
				player.getDialogueInterpreter().open(1047, NPC.create(1047, player.getLocation()));
				break;
			}
			break;
		case 30728:
			/** the coffin. */
			player.getDialogueInterpreter().sendDialogues(player, null, "It sounds like there's something alive inside it. I don't", "think it would be a very good idea to open it...");
			break;
		case 3445:/* the other gate. */
			if (quest.getStage(player) < 17) {
				player.getPacketDispatch().sendMessage("The door is locked shut.");
			} else {
				DoorActionHandler.handleDoor(player, (GameObject) node);
			}
			break;
		case 30707:/** the door to the church. */
		case 30708:
			switch (option) {
			case "open":
				if (quest.getStage(player) > 12) {
					DoorActionHandler.handleDoor(player, (GameObject) node);
				} else {
					player.getPacketDispatch().sendMessage("This door is securely locked from inside.");
				}
				break;
			case "knock-at":
				if (quest.getStage(player) == 10 || quest.getStage(player) == 12 || quest.getStage(player) == 13) {
					player.getDialogueInterpreter().open(54584, node);
				}
				break;
			}
			break;
		case 30575:
			/** represents the ladder to get back up. */
			player.getProperties().setTeleportLocation(Location.create(3405, 3506, 0));
			break;
		case 7711:
			/** the dog. */
			if (quest.getStage(player) == 10) {
				player.getPacketDispatch().sendMessage("You have no reason to attack a helpess dog!");
				return true;
			}
			if (quest.getStage(player) > 10) {
				player.getProperties().getCombatPulse().attack(node);
			}
			if (quest.getStage(player) == 12) {
				player.getPacketDispatch().sendMessage("You've already killed that dog!");
				return true;
			}
			if (quest.getStage(player) >= 13) {
				player.getProperties().getCombatPulse().stop();
				player.getPacketDispatch().sendMessage("I'd better not make the King mad at me again!");
				return true;
			}
			break;
		case 30571:
			player.getProperties().setTeleportLocation(Location.create(3405, 9906, 0));
			break;
		}
		return true;
	}

}
