package plugin.quest.free.shieldofarrav;

import java.util.List;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.component.Component;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import core.game.content.global.action.PickupHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.GroundItem;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;

/**
 * Represents the shield of arrav plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class ShieldArravPlugin extends OptionHandler {

	/**
	 * Represents the message component for intel report.
	 */
	private static final Component MESSAGE_COMPONENT = new Component(222);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2402).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(2397).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2399).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2398).getConfigurations().put("option:open", this);
		ItemDefinition.forId(761).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(2403).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2404).getConfigurations().put("option:close", this);
		ObjectDefinition.forId(2404).getConfigurations().put("option:search", this);
		ItemDefinition.forId(767).getConfigurations().put("option:take", this);
		ObjectDefinition.forId(24356).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(2400).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2401).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(2401).getConfigurations().put("option:shut", this);
		ItemDefinition.forId(ShieldofArrav.PHOENIX_CERTIFICATE.getId()).getConfigurations().put("option:read", this);
		ItemDefinition.forId(ShieldofArrav.BLACKARM_CERTIFICATE.getId()).getConfigurations().put("option:read", this);
		ItemDefinition.forId(769).getConfigurations().put("option:read", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest("Shield of Arrav");
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : node instanceof Item ? ((Item) node).getId() : ((NPC) node).getId();
		switch (id) {
		case 769:
			player.getInterfaceManager().open(new Component(526));
			player.getPacketDispatch().sendString("You can use this certificate in order to claim your reward.", 526, 2);
			break;
		case 11173:
			player.getInterfaceManager().open(new Component(525));
			player.getPacketDispatch().sendString("Attach this certificate to another.", 525, 2);
			break;
		case 11174:
			player.getInterfaceManager().open(new Component(529));
			player.getPacketDispatch().sendString("Attach this certificate to another.", 529, 2);
			break;
		case 2400:
			ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(2401));
			break;
		case 2401:
			switch (option) {
			case "search":
				if (quest.getStage(player) == 70 && ShieldofArrav.isBlackArm(player)) {
					if (!player.getInventory().containsItem(ShieldofArrav.BLACKARM_SHIELD) && !player.getBank().containsItem(ShieldofArrav.BLACKARM_SHIELD)) {
						if (!player.getInventory().add(ShieldofArrav.BLACKARM_SHIELD)) {
							GroundItemManager.create(ShieldofArrav.BLACKARM_SHIELD, player);
						}
						player.getDialogueInterpreter().sendItemMessage(ShieldofArrav.BLACKARM_SHIELD.getId(), "You find half of a shield, which you take.");
					} else {
						player.getDialogueInterpreter().sendDialogue("The cupboard is bare.");
					}
				}
				break;
			case "shut":
				ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(2400));
				break;
			}
			break;
		case 24356:
			if (node.getLocation().getX() == 3188) {
				ClimbActionHandler.climb(player, new Animation(828), Location.create(3188, 3392, 1));
			} else {
				ClimbActionHandler.climbLadder(player, (GameObject) node, option);
				return true;
			}
			break;
		case 767:
			List<NPC> npcs = RegionManager.getLocalNpcs(player);
			NPC master = null;
			for (NPC n : npcs) {
				if (n.getId() == 643) {
					master = n;
					break;
				}
			}
			if (master == null) {
				return true;
			}
			if (!ShieldofArrav.isPhoenix(player) && !master.isInvisible()) {
				player.getDialogueInterpreter().open(643, master, true);
			} else {
				return PickupHandler.take(player, (GroundItem) node);
			}
			break;
		case 2403:
			ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(2404));
			break;
		case 2404:
			switch (option) {
			case "close":
				ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(2403));
				break;
			case "search":
				if (quest.getStage(player) == 70 && ShieldofArrav.isPhoenix(player)) {
					if (!player.getInventory().containsItem(ShieldofArrav.PHOENIX_SHIELD) && !player.getBank().containsItem(ShieldofArrav.PHOENIX_SHIELD)) {
						if (!player.getInventory().add(ShieldofArrav.PHOENIX_SHIELD)) {
							GroundItemManager.create(ShieldofArrav.PHOENIX_SHIELD, player);
						}
						player.getDialogueInterpreter().sendItemMessage(ShieldofArrav.PHOENIX_SHIELD.getId(), "You find half of a shield, which you take.");
					} else {
						player.getDialogueInterpreter().sendDialogue("The chest is empty.");
					}
				}
				break;
			}
			break;
		case 761:
			player.getPacketDispatch().sendString("Intelligence Report", 222, 1);
			player.getPacketDispatch().sendString("There is an archeologist hanging around the", 222, 1);
			player.getPacketDispatch().sendString("statue outside of the city, with mining equipment", 222, 2);
			player.getPacketDispatch().sendString("Could she be onto a hidden treasure buried near", 222, 3);
			player.getPacketDispatch().sendString("the statue?", 222, 4);
			player.getPacketDispatch().sendString("There is a new channel being dug and a barge", 222, 5);
			player.getPacketDispatch().sendString("being assembled near the digsite. The Varrock", 222, 6);
			player.getPacketDispatch().sendString("museum seems to be paying for it. Could they", 222, 7);
			player.getPacketDispatch().sendString("have information about new treasures?", 222, 8);
			player.getInterfaceManager().open(MESSAGE_COMPONENT);
			break;
		case 2398:
			if (quest.getStage(player) == 60 && ShieldofArrav.isBlackArmMission(player) && !player.getInventory().containsItem(ShieldofArrav.KEY)) {
				player.getDialogueInterpreter().sendDialogue("This is the door to the weapon stash you were looking for. Maybe if", "you can find another adventurer who happens to be a member of the", "Phoenix Gang, they could help you.");
				return true;
			} else if (quest.getStage(player) == 60 && player.getInventory().containsItem(ShieldofArrav.KEY)) {
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
				return true;
			}
			if (quest.getStage(player) == 70 && player.getInventory().containsItem(ShieldofArrav.KEY)) {
				if (!player.getInventory().containsItem(ShieldofArrav.KEY) && player.getLocation().getY() > 3385) {
					player.getPacketDispatch().sendMessage("You should get a replacement key from Straven to enter here.");
					return true;
				} else {
					DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
					return true;
				}
			}
			player.getPacketDispatch().sendMessage("The door is securely locked.");
			break;
		case 2399:
			if (!ShieldofArrav.isBlackArm(player)) {
				player.getPacketDispatch().sendMessage("This door seems to be locked from the inside.");
			} else {
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			}
			break;
		case 2397:
			if (ShieldofArrav.isPhoenix(player)) {
				player.getPacketDispatch().sendMessage("The door automatically opens for you.");
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
				return true;
			}
			player.getPacketDispatch().sendMessage("The door is securely locked.");
			break;
		case 2402:
			if (quest.getStage(player) != 10) {
				player.getPacketDispatch().sendMessage("A large collection of books.");
			} else {
				if (!player.getInventory().containsItem(ShieldofArrav.BOOK) && !player.getBank().containsItem(ShieldofArrav.BOOK)) {
					player.getDialogueInterpreter().open(2660, Repository.findNPC(2660), "book");
				} else {
					player.getPacketDispatch().sendMessage("You already own 'The Shield of Arrav', the book that was kept here.");
				}
			}
			break;
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public boolean isWalk(final Player player, final Node node) {
		if (node instanceof GroundItem) {
			return true;
		}
		return !(node instanceof Item);
	}

	@Override
	public Location getDestination(Node n, Node node) {
		if (node instanceof GameObject) {
			if (node.getName().toLowerCase().contains("door")) {
				return DoorActionHandler.getDestination((Player) n, (GameObject) node);
			}
		}
		return null;
	}
}
