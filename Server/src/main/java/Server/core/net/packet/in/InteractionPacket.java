package core.net.packet.in;

import core.ServerConstants;
import core.game.interaction.Interaction;
import core.game.interaction.MovementPulse;
import core.game.interaction.Option;
import core.game.interaction.SpecialGroundItems;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.ai.AIPlayer;
import core.game.node.item.GroundItem;
import core.game.node.item.GroundItemManager;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.repository.Repository;
import core.net.packet.IncomingPacket;
import core.net.packet.IoBuffer;
import core.net.packet.PacketRepository;
import core.net.packet.context.PlayerContext;
import core.net.packet.out.ClearMinimapFlag;
import plugin.quest.QuestInteractionManager;

import java.util.List;

/**
 * Handles the incoming interaction packets.
 * @author Emperor
 */
public final class InteractionPacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		if (player == null) {
			return;
		}
		if (buffer.opcode() != 105) {
			player = getPlayer(player);
		}
		if (player.getLocks().isInteractionLocked() || !player.getInterfaceManager().close()) {
			return;
		}
		player.getInterfaceManager().closeChatbox();
		switch (buffer.opcode()) {
		case 78: // NPC reward 1
			int index = buffer.getLEShort();
			handleNPCInteraction(player, 0, index);
			break;
		case 3: // NPC reward 2
			index = buffer.getLEShortA();
			handleNPCInteraction(player, 1, index);
			break;
		case 148: // NPC reward 3
			index = buffer.getShortA();
			handleNPCInteraction(player, 2, index);
			break;
		case 30: // NPC reward 4
			index = buffer.getShort();
			handleNPCInteraction(player, 3, index);
			break;
		case 218: // NPC reward 5
			index = buffer.getLEShort();
			handleNPCInteraction(player, 4, index);
			break;
		case 254: // Object reward 1
			int x = buffer.getLEShort();
			int objectId = buffer.getShortA() & 0xFFFF;
			int y = buffer.getShort();
			handleObjectInteraction(player, 0, x, y, objectId);
			break;
		case 194: // Object reward 2
			y = buffer.getLEShortA();
			x = buffer.getLEShort();
			objectId = buffer.getShort() & 0xFFFF;
			handleObjectInteraction(player, 1, x, y, objectId);
			break;
		case 84: // Object reward 3
			objectId = buffer.getLEShortA() & 0xFFFF;
			y = buffer.getLEShortA();
			x = buffer.getLEShort();
			handleObjectInteraction(player, 2, x, y, objectId);
			break;
		case 152: // Object reward 4 TODO
			objectId = buffer.getLEShortA() & 0xFFFF;
			x = buffer.getLEShort();
			y = buffer.getLEShortA();
			handleObjectInteraction(player, 3, x, y, objectId);
			break;
		case 247://Object reward 4
			y = buffer.getLEShort() & 0xFFFF;
			x = buffer.getLEShortA();
			objectId = buffer.getShort() & 0xFFFF;
			handleObjectInteraction(player, 3, x, y, objectId);
			break;
		case 170: // Object reward 5
			objectId = buffer.getLEShortA() & 0xFFFF;
			x = buffer.getLEShortA();
			y = buffer.getLEShortA();
			handleObjectInteraction(player, 4, x, y, objectId);
			break;
		case 68: // Player reward 1 - Challenge
			index = buffer.getLEShortA();
			handlePlayerInteraction(player, 0, index);
			break;
		/*case : // Player reward 2 TODO
			index = buffer.getLEShort();
			handlePlayerInteraction(player, 1, index);
			break;*/
		case 71: // Player reward 3 - follow
			index = buffer.getLEShortA();
			handlePlayerInteraction(player, 2, index);
			break;
		case 180: // Player reward 4 - trade
			index = buffer.getLEShortA();
			handlePlayerInteraction(player, 3, index);
			break;
		/*case : // Player reward 5 TODO
			index = buffer.getLEShortA();
			handlePlayerInteraction(player, 4, index);
			break;*/
		/*case : // Player reward 6 TODO
			index = buffer.getShort();
			handlePlayerInteraction(player, 5, index);
			break;*/
		case 114: // Player reward 7 - req assistance
			index = buffer.getLEShortA();
			handlePlayerInteraction(player, 6, index);
			break;
		case 175: // Player reward 8 - "whack" (also "control" for AIPs)
			index = buffer.getShortA();
			handlePlayerInteraction(player, 7, index);
			break;
		case 66: // Ground item reward 1
			x = buffer.getLEShort();
			int itemId = buffer.getShort();
			y = buffer.getLEShortA();
			handleGroundItemInteraction(player, 2, itemId, Location.create(x, y, player.getLocation().getZ()));
			break;
		case 33: // Ground item reward 2
			itemId = buffer.getShort();
			x = buffer.getShort();
			y = buffer.getLEShort();
			handleGroundItemInteraction(player, 3, itemId, Location.create(x, y, player.getLocation().getZ()));
			break;
		}
	}

	/**
	 * Handles the NPC interaction.
	 * @param player the player.
	 * @param optionIndexthe option index.
	 * @param index the index.
	 */
	public static void handleNPCInteraction(Player player, int optionIndex, final int index) {
		if (index < 1 || index > ServerConstants.MAX_NPCS) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			return;
		}
		final NPC npc = Repository.getNpcs().get(index);
		if (npc == null) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			return;
		}
		if (player.getAttribute("removenpc", false)) {
			npc.clear();
			player.getPacketDispatch().sendMessage("Removed npc=" + npc.toString());
			return;
		}
		NPC shown = npc.getShownNPC(player);
		final Option option = shown.getInteraction().get(optionIndex);
		if (option == null) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			Interaction.handleInvalidInteraction(player, npc, Option.NULL);
			return;
		}
		player.debug("NPC Interacting with \"" + shown.getUsername() + "\" [index=" + index + ", renderable=" + npc.isRenderable() + "]");
		player.debug("option=" + option.getName() + ", slot=" + option.getIndex() + ", id=" + shown.getId() + " original=" + npc.getId() + ", location=" + npc.getLocation() + "");
		player.debug("spawn=" + npc.getProperties().getSpawnLocation() + ".");
		handleAIPLegion(player, 0, optionIndex, index);
		if(QuestInteractionManager.handle(player,shown,option)){
			return;
		}
		npc.getInteraction().handle(player, option);
	}

	/**
	 * Handles Node interaction with the first index
	 * @param player The interacting player.
	 * @param n The node to interact with.
	 */
	public static void handleObjectInteraction(final Player player, Node n)
	{
		handleObjectInteraction(player, 0, n.getLocation(), n.getId());
	}

	/**
	 * Handles object interaction
	 * @param player The interacting player.
	 * @param optionIndex The option index.
	 * @param l The (x,y) location of the object.
	 * @param objectId The object id.
	 */
	public static void handleObjectInteraction(final Player player, int optionIndex, Location l, int objectId) {
		handleObjectInteraction(player, optionIndex, l.getX(), l.getY(), objectId);
	}

	/**
	 * Handles object interaction
	 * @param player The interacting player.
	 * @param optionIndex The option index.
	 * @param x The x-coordinate of the object.
	 * @param y The y-coordinate of the object.
	 * @param objectId The object id.
	 */
	public static void handleObjectInteraction(final Player player, int optionIndex, int x, int y, int objectId) {
		GameObject object = RegionManager.getObject(player.getLocation().getZ(), x, y, objectId);
		if (objectId == 29735) {//player safety.
			player.getPulseManager().run(new MovementPulse(player, Location.create(x, y, player .getLocation().getZ())) {
				@Override
				public boolean pulse() {
					player.getDialogueInterpreter().sendDialogue("There appears to be a tunnel behind the poster.");
					player.getDialogueInterpreter().addAction((player1, buttonId) -> player1.teleport(new Location(3140, 4230, 2)));
					return true;
				}
			}, "movement");				
			return;
		}
		if (objectId == 6898) {
			object = new GameObject(6898, new Location(3219, 9618));
		} else if (objectId == 6899) {
			object = new GameObject(6899, new Location(3221, 9618));
		}
		if (object == null || object.getId() != objectId) {
			player.debug("GameObject("  + objectId + ") interaction was " + object + " at location " + x + ", " + y + ".");
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			Interaction.handleInvalidInteraction(player, object, Option.NULL);
			return;
		}
		if (!object.isActive()) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			Interaction.handleInvalidInteraction(player, object, Option.NULL);
			return;
		}
		object = object.getChild(player);
		Option option = object.getInteraction().get(optionIndex);
		if (option == null) {
			player.debug("Invalid option" + object + ", original: " + objectId + ".");
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			Interaction.handleInvalidInteraction(player, object, Option.NULL);
			return;
		}
		player.debug(object + ", original=" + objectId + ", option=" + option.getName() + "");
		player.debug("dir=" + object.getDirection());
		if (option.getHandler() != null) {
			player.debug("Object handler: " + option.getHandler().getClass().getSimpleName());
		}
		handleAIPLegion(player, 1, optionIndex, x, y, objectId);
		if(QuestInteractionManager.handle(player,object)){
			return;
		}
		object.getInteraction().handle(player, option);
	}

	/**
	 * Handles player interaction.
	 * @param player The player interacting.
	 * @param optionIndex The option index.
	 * @param index The target index.
	 */
	private static void handlePlayerInteraction(Player player, int optionIndex, int index) {
		if (index < 1 || index > ServerConstants.MAX_PLAYERS) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			return;
		}
		final Player target = Repository.getPlayers().get(index);
		if (target == null || !target.isActive()) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			return;
		}
		final Option option = player.getInteraction().get(optionIndex);
		if (option == null) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			return;
		}
		handleAIPLegion(player, 2, optionIndex, index);
		target.getInteraction().handle(player, option);
	}

	/**
	 * Handles the ground item interaction.
	 * @param player The player.
	 * @param index The index of the reward.
	 * @param itemId The item id.
	 * @param location The location of the item.
	 */
	private static void handleGroundItemInteraction(final Player player, int index, int itemId, Location location) {
		final GroundItem item = GroundItemManager.get(itemId, location, player);
		SpecialGroundItems[] groundItems = SpecialGroundItems.values();
		for(SpecialGroundItems specialItem : groundItems){
			if(specialItem.getItemid() == itemId && specialItem.getLocation().getX() == location.getX() && specialItem.getLocation().getY() == location.getY()){
				player.debug("Using special ground item handler");
				specialItem.getInteraction().handle(player,item.getInteraction().get(index));
				return;
			}
		}
		if (item == null) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			return;
		}
		final Option option = item.getInteraction().get(index);
		if (option == null) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			Interaction.handleInvalidInteraction(player, item, Option.NULL);
			return;
		}
		item.getInteraction().handle(player, option);
	}

	/**
	 * Handles the AIPlayer legion.
	 * @param player The player.
	 * @param type The interaction type.
	 * @param args The arguments.
	 */
	private static void handleAIPLegion(Player player, int type, int... args) {
		if (player.isArtificial()) {
			List<AIPlayer> legion = player.getAttribute("aip_legion");
			if (legion != null) {
				for (AIPlayer aip : legion) {
					if (aip != player) {
						switch (type) {
						case 0:
							handleNPCInteraction(aip, args[0], args[1]);
							break;
						case 1:
							handleObjectInteraction(aip, args[0], args[1], args[2], args[3]);
							break;
						case 2:
							handlePlayerInteraction(aip, args[0], args[1]);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Gets the player instance (used for AIP controlling).
	 * @param player The player.
	 * @return The player instance, or the AIP when the player is controlling an
	 * AIP.
	 */
	private static Player getPlayer(Player player) {
		AIPlayer aip = player.getAttribute("aip_select");
		if (aip != null && aip.getLocation().withinDistance(player.getLocation())) {
			return aip;
		}
		return player;
	}

}