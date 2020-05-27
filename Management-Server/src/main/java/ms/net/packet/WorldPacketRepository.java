package ms.net.packet;

import java.nio.ByteBuffer;
import java.util.List;

import ms.ServerConstants;
import ms.net.IoSession;
import ms.system.PunishmentStorage;
import ms.world.GameServer;
import ms.world.PlayerSession;
import ms.world.WorldDatabase;
import ms.system.communication.ClanRank;
import ms.system.communication.ClanRepository;
import ms.system.communication.CommunicationInfo;
import ms.world.info.Response;
import ms.world.info.UIDInfo;

/**
 * Repository class for world packets.
 * @author Emperor
 *
 */
public final class WorldPacketRepository {

	/**
	 * Sends the player registry response.
	 * @param server The game server.
	 * @param player The player session.
	 * @param response The registry response.
	 */
	public static void sendRegistryResponse(GameServer server, PlayerSession player, Response response) {
		IoBuffer buffer = new IoBuffer(0, PacketHeader.BYTE);
		buffer.putString(player.getUsername());
		buffer.put((byte) response.opcode());
		if (response == Response.MOVING_WORLD) {
			long delay = ServerConstants.WORLD_SWITCH_DELAY - (System.currentTimeMillis() - player.getDisconnectionTime());
			buffer.put((byte) (delay / 1000));
		}
		server.getSession().write(buffer);
	}

	/**
	 * Sends a message to the player.
	 * @param player The player.
	 * @param message The message to send.
	 */
	public static void sendPlayerMessage(PlayerSession player, String message) {
		if (player == null) {
			return;
		}
		IoBuffer buffer = new IoBuffer(2, PacketHeader.BYTE);
		buffer.putString(player.getUsername());
		buffer.putString(message);
		player.getWorld().getSession().write(buffer);
	}

	public static void sendPlayerMessage(PlayerSession player, String[] messages) {
		if (player == null) {
			return;
		}
		for (String message : messages)
			sendPlayerMessage(player, message);
	}

	/**
	 * Sends the contact information.
	 * @param player The player.
	 */
	public static void sendContactInformation(PlayerSession player) {
		CommunicationInfo info = player.getCommunication();
		IoBuffer buffer = new IoBuffer(3, PacketHeader.SHORT);
		buffer.putString(player.getUsername());
		buffer.put(info.getContacts().size());
		for (String contact : info.getContacts().keySet()) {
			buffer.putString(contact);
			buffer.put(info.getRank(contact).ordinal());
			buffer.put(CommunicationInfo.getWorldId(player, contact));
		}
		buffer.put(info.getBlocked().size());
		for (String contact : info.getBlocked()) {
			buffer.putString(contact);
		}
		if (info.getCurrentClan() == null) {
			buffer.put(0);
		} else {
			buffer.put(1);
			buffer.putString(info.getCurrentClan());
		}
		player.getWorld().getSession().write(buffer);
	}

	/**
	 * Sends a contact update.
	 * @param player The player who's contacts we're changing.
	 * @param contact The contact to update.
	 * @param block If we're updating the blocked list.
	 * @param remove If the contact should be removed.
	 * @param worldId The world id of the contact.
	 * @param rank The clan rank.
	 */
	public static void sendContactUpdate(PlayerSession player, String contact, boolean block, boolean remove, int worldId, ClanRank rank) {
		IoBuffer buffer = new IoBuffer(4, PacketHeader.BYTE);
		buffer.putString(player.getUsername());
		buffer.putString(contact);
		buffer.put((byte) (block ? 1 : 0));
		if (rank != null) {
			buffer.put((byte) (2 + rank.ordinal()));
		} else {
			buffer.put((byte) (remove ? 1 : 0));
			if (!block && !remove) {
				buffer.put((byte) worldId);
			}
		}
		player.getWorld().getSession().write(buffer);
	}

	/**
	 * Sends a clan message.
	 * @param player The player to send the message to.
	 * @param p The player sending the message.
	 * @param message The message to send.
	 * @param type The message type.
	 */
	public static void sendMessage(PlayerSession player, PlayerSession p, int type, String message) {
		IoBuffer buffer = new IoBuffer(5, PacketHeader.BYTE);
		buffer.putString(player.getUsername());
		buffer.putString(p.getUsername());
		buffer.put((byte) type);
		buffer.put((byte) p.getChatIcon());
		buffer.putString(message);
		player.getWorld().getSession().write(buffer);
	}

	/**
	 * Sends clan information to the server.
	 * @param server The server.
	 * @param clan The clan.
	 */
	public static void sendClanInformation(GameServer server, ClanRepository clan) {
		IoBuffer buffer = new IoBuffer(6, PacketHeader.SHORT);
		buffer.putString(clan.getOwner().getUsername());
		buffer.putString(clan.getName());
		int length = clan.getPlayers().size();
		if (length > ClanRepository.MAX_MEMBERS) {
			length = ClanRepository.MAX_MEMBERS;
		}
		buffer.put((byte) length);
		for (int i = 0; i < length; i++) {
			PlayerSession player = clan.getPlayers().get(i);
			buffer.putString(player.getUsername());
			buffer.put(player.getWorldId());
			buffer.put((byte) clan.getRank(player).ordinal());
		}
		buffer.put((byte) clan.getJoinRequirement().ordinal());
		buffer.put((byte) clan.getKickRequirement().ordinal());
		buffer.put((byte) clan.getMessageRequirement().ordinal());
		buffer.put((byte) clan.getLootRequirement().ordinal());
		server.getSession().write(buffer);
	}

	/**
	 * Sends the leave clan packet.
	 * @param player The player leaving the clan.
	 */
	public static void sendLeaveClan(PlayerSession player) {
		IoBuffer buffer = new IoBuffer(7, PacketHeader.BYTE);
		buffer.putString(player.getUsername());
		player.getWorld().getSession().write(buffer);
	}

	/**
	 * Sends the player login notification.
	 * @param server The server.
	 * @param player The player logging in.
	 * @param names The names.
	 */
	public static void notifyPlayers(GameServer server, PlayerSession player, List<String> names) {
		IoBuffer buffer = new IoBuffer(8, PacketHeader.SHORT);
		buffer.putString(player.getUsername());
		buffer.put((byte) player.getWorldId());
		buffer.put((byte) names.size());
		for (String name : names) {
			buffer.putString(name);
		}
		server.getSession().write(buffer);
	}

	/**
	 * Notifies the game server a player logged out.
	 * @param server The game server to notify.
	 * @param player The player logging out.
	 */
	public static void notifyLogout(GameServer server, PlayerSession player) {
		IoBuffer buffer = new IoBuffer(9, PacketHeader.BYTE);
		buffer.putString(player.getUsername());
		server.getSession().write(buffer);
	}

	/**
	 * Sends the update countdown to the server.
	 * @param server The server.
	 * @param ticks The amount of ticks left.
	 */
	public static void sendUpdate(GameServer server, int ticks) {
		IoBuffer buffer = new IoBuffer(10);
		buffer.putInt(ticks);
		server.getSession().write(buffer);
	}

	/**
	 * Sends the punishment update packet.
	 * @param world The world to send the packet to.
	 * @param key The punishment key.
	 * @param type The punishment type.
	 * @param duration The duration of the punishment.
	 */
	public static void sendPunishUpdate(GameServer world, String key, int type, long duration) {
		IoBuffer buffer = new IoBuffer(11, PacketHeader.BYTE);
		buffer.putString(key);
		buffer.put((byte) type);
		buffer.putLong(duration);
		world.getSession().write(buffer);
	}

	/**
	 * Sends a configuration reload.
	 * @param world the world.
	 */
	public static void sendConfigReload(GameServer world) {
		world.getSession().write(new IoBuffer(15, PacketHeader.BYTE));
	}

	/**
	 * Handles incoming world packets.
	 * @param session The I/O session.
	 * @param opcode The opcode.
	 * @param b The buffer to read from.
	 */
	public static void handleIncoming(IoSession session, int opcode, ByteBuffer b) {
		IoBuffer buffer = new IoBuffer(opcode, PacketHeader.NORMAL, b);
		GameServer server = session.getGameServer();
		switch (opcode) {
		case 0:
			handlePlayerRegistration(server, buffer);
			break;
		case 1:
			handlePlayerRemoval(server, buffer);
			break;
		case 2:
			handlePunishment(server, buffer);
			break;
		case 3:
			handleCommunicationRequest(server, buffer);
			break;
		case 4:
		case 5:
			handleContactUpdate(server, buffer, opcode == 5);
			break;
		case 6:
			handleJoinClan(server, buffer);
			break;
		case 7:
			handleClanRename(server, buffer);
			break;
		case 8:
			handleClanSetting(server, buffer);
			break;
		case 9:
			handleClanKick(server, buffer);
			break;
		case 10:
			handleClanMessage(server, buffer);
			break;
		case 11:
			handlePrivateMessage(server, buffer);
			break;
		case 12:
			handleClanInfoRequest(server, buffer);
			break;
		case 13:
			handleChatSetting(server, buffer);
			break;
		case 14:
			handleInfoUpdate(server, buffer);
			break;
		default:
			System.err.println("Handling incoming packet [opcode=" + opcode + ", size=" + b.limit() + "].");
		}
	}

	/**
	 * Handles the info of a player update.
	 * @param server the server.
	 * @param buffer the buffer.
	 */
	private static void handleInfoUpdate(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		PlayerSession player = server.getPlayers().get(username);
		if (player != null) {
			player.setChatIcon((int) buffer.get()); 
		}
	}

	/**
	 * Handles a player registration.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handlePlayerRegistration(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		String password = buffer.getString();
		String ipAddress = buffer.getString();
		String macAddress = buffer.getString();
		String compName = buffer.getString();
		String serial = buffer.getString();
		int rights = server.getInfo().getRevision() == 498 ? 0 : buffer.getInt();
		int chatIcon = server.getInfo().getRevision() == 498 ? 0 : buffer.get();
		UIDInfo uid = new UIDInfo(ipAddress, compName, macAddress, serial);
		PlayerSession player = new PlayerSession(username, password, new UIDInfo(ipAddress, compName, macAddress, serial));
		if (WorldDatabase.isActivePlayer(username)) {
			sendRegistryResponse(server, player, Response.ALREADY_ONLINE);
			return;
		}
		player.setUid(uid);
		player.setRights(rights);
		player.setChatIcon(chatIcon);
		if (PunishmentStorage.isSystemBanned(uid)) {
			sendRegistryResponse(server, player, Response.BANNED);
			return;
		}
		player.parse();	
		if (player.isBanned()) {
			sendRegistryResponse(server, player, Response.ACCOUNT_DISABLED);
			return;
		}
		if (player.getLastWorld() != server.getInfo().getWorldId() && player.hasMovedWorld()) {
			sendRegistryResponse(server, player, Response.MOVING_WORLD);
			return;
		}
		server.register(player);
	}

	/**
	 * Handles the removal of a player.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handlePlayerRemoval(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		PlayerSession session = server.getPlayers().get(username);
		if (session != null) {
			session.setActive(false);
			PlayerSession player = server.getPlayers().remove(username);
			if (player != null) {
				session.remove();
			}
			session.setWorldId(0);
		}
	}

	/**
	 * Handles a player registration.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handlePunishment(GameServer server, IoBuffer buffer) {
		int type = buffer.get() & 0xFF;
		String target = buffer.getString();
		long duration = buffer.getLong();
		String staff = buffer.getString();
		PunishmentStorage.handlePunishment(staff, target, type, duration);
	}

	/**
	 * Handles the communication info request packet.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handleCommunicationRequest(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		PlayerSession player = server.getPlayers().get(username);
		if (player == null) {
			return;
		}
		sendContactInformation(player);
	}

	/**
	 * Handles a contact update packet.
	 * @param server The server.
	 * @param buffer The buffer to read from.
	 * @param block If the list is for blocked players.
	 */
	private static void handleContactUpdate(GameServer server, IoBuffer buffer, boolean block) {
		String username = buffer.getString();
		PlayerSession player = server.getPlayers().get(username);
		if (player == null) {
			return;
		}
		String contact = buffer.getString();
		switch (buffer.get()) {
		case 0:
			if (block) {
				player.getCommunication().block(contact);
				break;
			}
			player.getCommunication().add(contact);
			break;
		case 1:
			player.getCommunication().remove(contact, block);
			break;
		case 2:
			player.getCommunication().updateClanRank(contact, ClanRank.values()[buffer.get()]);
			break;
		}
	}

	/**
	 * Handles a clan related packet.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handleJoinClan(GameServer server, IoBuffer buffer) {
		String name = buffer.getString();
		String clanName = buffer.getString();
		PlayerSession player = server.getPlayers().get(name);
		if (player == null || !player.isActive()) {
			System.err.println("Invalid player specified in clan packet!");
			return;
		}
		if (player.getClan() != null) {
			player.getClan().leave(player, true);
			return;
		}
		if (clanName.length() < 1) {
			sendLeaveClan(player);
			return;
		}
		ClanRepository clan = ClanRepository.get(server, clanName);
		if (clan == null) {
			sendPlayerMessage(player, new String[]{ "The channel you tried to join does not exist.", "Try joining the main clan named '" + ServerConstants.SERVER_NAME + "'.:clan:" });
			return;
		}
		clan.enter(player);
	}

	/**
	 * Handles renaming a clan.
	 * @param server The server.
	 * @param buffer The buffer.
	 */
	private static void handleClanRename(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		String name = buffer.getString();
		PlayerSession player = server.getPlayers().get(username);
		if (player == null || !player.isActive()) {
			return;
		}
		ClanRepository clan = ClanRepository.getClans().get(username);
		player.getCommunication().setClanName(name);
		if (clan != null) {
			if (name.length() < 1) {
				clan.clean(true);
			} else {
				clan.rename(name);
			}
		}
	}

	/**
	 * Handles changing clan settings packet.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handleClanSetting(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		int type = buffer.get();
		ClanRank rank = type < 4 ? ClanRank.values()[buffer.get() & 0xFF] : null;
		PlayerSession player = server.getPlayers().get(username);
		if (player == null || !player.isActive()) {
			return;
		}
		ClanRepository clan = ClanRepository.get(server,username);
		switch (type) {
		case 0:
			player.getCommunication().setJoinRequirement(rank);
			if (clan != null) {
				clan.clean(false);
			}
			break;
		case 1:
			player.getCommunication().setMessageRequirement(rank);
			break;
		case 2:
			player.getCommunication().setKickRequirement(rank);
			if (clan != null) {
				clan.update();
			}
			break;
		case 3:
			player.getCommunication().setLootRequirement(rank);
			break;
		}
	}

	/**
	 * Handles kicking a player from the clan.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handleClanKick(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		String playerName = buffer.getString();
		PlayerSession player = server.getPlayers().get(username);
		if (player == null || !player.isActive() || player.getClan() == null) {
			return;
		}
		PlayerSession target = WorldDatabase.getPlayer(playerName);
		if (target == null || !target.isActive()) {
			return;
		}
		player.getClan().kick(player, target);
	}

	/**
	 * Handles a clan message.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handleClanMessage(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		String message = buffer.getString();
		PlayerSession player = server.getPlayers().get(username);
		if (player == null || !player.isActive() || player.getClan() == null) {
			return;
		}
		player.getClan().message(player, message);
	}


	/**
	 * Handles a clan message.
	 * @param server The game server.
	 * @param buffer The buffer.
	 */
	private static void handlePrivateMessage(GameServer server, IoBuffer buffer) {
		String username = buffer.getString();
		String receiver = buffer.getString();
		String message = buffer.getString();
		PlayerSession player = server.getPlayers().get(username);
		if (player == null || !player.isActive()) {
			return;
		}
		player.getCommunication().sendMessage(receiver, message);
	}

	/**
	 * Handles a clan information request packet.
	 * @param server The server.
	 * @param buffer The buffer.
	 */
	private static void handleClanInfoRequest(GameServer server, IoBuffer buffer) {
		String name = buffer.getString();
		ClanRepository clan = ClanRepository.get(server, name);
		if (clan == null) {
			return;
		}
		sendClanInformation(server, clan);
	}

	/**
	 * Handles a chat setting update packet.
	 * @param server The server.
	 * @param buffer The buffer.
	 */
	private static void handleChatSetting(GameServer server, IoBuffer buffer) {
		String name = buffer.getString();
		int publicSetting = buffer.get();
		int privateSetting = buffer.get();
		int tradeSetting = buffer.get();
		PlayerSession player = server.getPlayers().get(name);
		if (player == null || !player.isActive()) {
			return;
		}
		player.getCommunication().updateSettings(publicSetting, privateSetting, tradeSetting);
	}
}