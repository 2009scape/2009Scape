package core.net.amsc;

import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.PlayerDetails;
import core.game.node.entity.player.info.Rights;
import core.game.node.entity.player.info.login.LoginParser;
import core.game.node.entity.player.info.login.Response;
import core.game.system.SystemLogger;
import core.game.system.SystemManager;
import core.game.system.SystemState;
import core.game.system.communication.*;
import core.game.world.GameWorld;
import core.game.world.repository.Repository;
import core.net.packet.IoBuffer;
import core.net.packet.PacketHeader;
import core.net.packet.PacketRepository;
import core.net.packet.context.ClanContext;
import core.net.packet.context.ContactContext;
import core.net.packet.context.MessageContext;
import core.net.packet.out.CommunicationMessage;
import core.net.packet.out.ContactPackets;
import core.net.packet.out.UpdateClanChat;

import java.nio.ByteBuffer;

/**
 * The Management server packet repository.
 * @author Emperor
 */
public final class MSPacketRepository {

	/**
	 * Handles a player info update.
	 *
     */
	public static void sendInfoUpdate(Player player) {
		IoBuffer buffer = new IoBuffer(14, PacketHeader.BYTE);
		buffer.putString(player.getName());
		buffer.put(Rights.getChatIcon(player));
		WorldCommunicator.getSession().write(buffer);
		player.getAppearance().sync();
	}

	/**
	 * Registers a player on the management server.
	 *
	 * @param parser The login.
	 */
	public static void sendPlayerRegistry(LoginParser parser) {
		IoBuffer buffer = new IoBuffer(0, PacketHeader.BYTE);
		PlayerDetails d = parser.getDetails();
		buffer.putString(d.getUsername());
		buffer.putString(d.getPassword());
		buffer.putString(d.getIpAddress());
		buffer.putString(d.getMacAddress());
		buffer.putString(d.getCompName());
		buffer.putString(d.getSerial());
		buffer.putInt(d.getRights().toInteger());
		buffer.put((byte) getIcon(d));
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends the player removal packet.
	 *
	 * @param username The name of the player to remove.
	 */
	public static void sendPlayerRemoval(String username) {
		if (!WorldCommunicator.isEnabled()) {
			return;
		}
		IoBuffer buffer = new IoBuffer(1, PacketHeader.BYTE);
		buffer.putString(username);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends the punishment packet to the MS.
	 *
	 * @param player   The player punishing the other player.
	 * @param name     The name of the player to punish.
	 * @param type     The punishment type (0: mute, 1: ban, 2: ip-ban, 3: mac-ban,
	 *                 4: msk-ban)
	 * @param duration The duration of the punishment (in milliseconds, -1 =
	 *                 permanent).
	 */
	public static void sendPunishment(Player player, String name, int type, long duration) {
		IoBuffer buffer = new IoBuffer(2, PacketHeader.BYTE);
		buffer.put(type);
		buffer.putString(name);
		buffer.putLong(duration);
		buffer.putString(player.getName());
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Requests the communication info for the given player.
	 *
	 * @param username The username.
	 */
	public static void requestCommunicationInfo(String username) {
		IoBuffer buffer = new IoBuffer(3, PacketHeader.BYTE);
		buffer.putString(username);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends a contact update.
	 *
	 * @param username The username.
	 * @param contact  The contact's username.
	 * @param remove   If we're removing the contact from the list.
	 * @param block    If the contact list is for the blocked players.
	 * @param rank     The new clan rank (or null when not updating clan rank!).
	 */
	public static void sendContactUpdate(String username, String contact, boolean remove, boolean block, ClanRank rank) {
		IoBuffer buffer = new IoBuffer(block ? 5 : 4, PacketHeader.BYTE);
		buffer.putString(username);
		buffer.putString(contact);
		if (rank != null) {
			buffer.put((byte) 2);
			buffer.put((byte) rank.ordinal());
		} else {
			buffer.put((byte) (remove ? 1 : 0));
		}
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Joins a clan.
	 *
	 * @param player The player joining a clan.
	 * @param name   The clan's owner name.
	 */
	public static void sendJoinClan(Player player, String name) {
		if (name.length() > 0 && !ClanRepository.getClans().containsKey(name)) {
			requestClanInfo(name);
		}
		IoBuffer buffer = new IoBuffer(6, PacketHeader.BYTE);
		buffer.putString(player.getName());
		buffer.putString(name);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends the clan rename packet.
	 *
	 * @param player   The player.
	 * @param clanName The clan name.
	 */
	public static void sendClanRename(Player player, String clanName) {
		IoBuffer buffer = new IoBuffer(7, PacketHeader.BYTE);
		buffer.putString(player.getName());
		buffer.putString(clanName);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sets a clan setting.
	 *
	 * @param player The player.
	 * @param type   The setting type.
	 * @param rank   The rank to set.
	 */
	public static void setClanSetting(Player player, int type, ClanRank rank) {
		if (!WorldCommunicator.isEnabled()) {
			return;
		}
		IoBuffer buffer = new IoBuffer(8, PacketHeader.BYTE);
		buffer.putString(player.getName());
		buffer.put((byte) type);
		if (rank != null) {
			buffer.put((byte) rank.ordinal());
		}
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends the kicking a clan member packet.
	 *
	 * @param username The player's username.
	 * @param name     The name.
	 */
	public static void sendClanKick(String username, String name) {
		IoBuffer buffer = new IoBuffer(9, PacketHeader.BYTE);
		buffer.putString(username);
		buffer.putString(name);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends a clan message.
	 *
	 * @param player  The player sending the message.
	 * @param message The message to send.
	 */
	public static void sendClanMessage(Player player, String message) {
		IoBuffer buffer = new IoBuffer(10, PacketHeader.BYTE);
		buffer.putString(player.getName());
		buffer.putString(message);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends a private message.
	 *
	 * @param player  The player.
	 * @param name    The target name.
	 * @param message The message.
	 */
	public static void sendPrivateMessage(Player player, String name, String message) {
		IoBuffer buffer = new IoBuffer(11, PacketHeader.BYTE);
		buffer.putString(player.getName());
		buffer.putString(name);
		buffer.putString(message);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Requests clan information.
	 *
	 * @param name The clan's owner name.
	 */
	public static void requestClanInfo(String name) {
		IoBuffer buffer = new IoBuffer(12, PacketHeader.BYTE);
		buffer.putString(name);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Sends the chat settings.
	 *
	 * @param player         The player.
	 * @param publicSetting  The public chat setting.
	 * @param privateSetting The private chat setting.
	 * @param tradeSetting   The trade setting.
	 */
	public static void sendChatSetting(Player player, int publicSetting, int privateSetting, int tradeSetting) {
		IoBuffer buffer = new IoBuffer(13, PacketHeader.BYTE);
		buffer.putString(player.getName());
		buffer.put(publicSetting);
		buffer.put(privateSetting);
		buffer.put(tradeSetting);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Handles an incoming packet from the management server.
	 *
	 * @param opcode The opcode.
	 * @param b      The buffer.
	 */
	public static void handleIncoming(int opcode, ByteBuffer b) {
		IoBuffer buffer = new IoBuffer(opcode, PacketHeader.NORMAL, b);
		switch (opcode) {
			case 0:
				handleRegistryResponse(buffer);
				break;
			case 2:
				handlePlayerMessage(buffer);
				break;
			case 3:
				handleContactInformation(buffer);
				break;
			case 4:
				handleContactUpdate(buffer);
				break;
			case 5:
				handleMessage(buffer);
				break;
			case 6:
				handleClanInformation(buffer);
				break;
			case 7:
				handleLeaveClan(buffer);
				break;
			case 8:
				handleContactNotification(buffer);
				break;
			case 9:
				handlePlayerLogout(buffer);
				break;
			case 10:
				handleUpdate(buffer);
				break;
			case 11:
				handlePunishmentUpdate(buffer);
				break;
			case 15:
				SystemManager.getSystemConfig().parse();
				SystemLogger.log("System configurations reloaded.");
				break;
			default:
				System.out.println("Handling incoming packet [opcode=" + opcode + ", size=" + b.limit() + "].");
		}
	}

	/**
	 * Handles the player registry response packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handleRegistryResponse(IoBuffer buffer) {
		String username = buffer.getString();
		int opcode = buffer.get() & 0xFF;
		LoginParser parser = WorldCommunicator.finishLoginAttempt(username);
		if (parser != null) {
			PlayerDetails details = parser.getDetails();
			Response response = Response.get(opcode);
			Player player = null;
			switch (response) {
				case ALREADY_ONLINE:
					player = Repository.getPlayerByName(username);
					if (player == null || player.getSession().isActive() || !player.getSession().getAddress().equals(details.getSession().getAddress())) {
						details.getSession().write(response, true);
						break;
					}
					player.getPacketDispatch().sendLogout();
				case SUCCESSFUL:
					if (!details.getSession().isActive()) {
						sendPlayerRemoval(username);
						break;
					}
					if (player == null) {
						player = new Player(details);
					} else {
						player.updateDetails(details);
					}
					parser.initialize(player, response == Response.ALREADY_ONLINE);
					break;

				case MOVING_WORLD:
					details.getSession().setServerKey(buffer.get());
				default:
					details.getSession().write(response, true);
					break;
			}
		}
	}

	/**
	 * Sends global message to all player nodes.
	 *
	 * @param message The message.
	 */
	public static void sendWorldMessage(String message) {
		IoBuffer buffer = new IoBuffer(12, PacketHeader.BYTE);
		buffer.putString(message);
		WorldCommunicator.getSession().write(buffer);
	}

	/**
	 * Handles sending a message to a player.
	 *
	 * @param buffer The buffer to read from.
	 */
	private static void handlePlayerMessage(IoBuffer buffer) {
		String name = buffer.getString();
		String message = buffer.getString();
		Player player = Repository.getPlayerByName(name);
		if (player != null && player.isActive()) {
			player.getPacketDispatch().sendMessage(message);
		}
	}

	/**
	 * Handles the contact information packet.
	 *
	 * @param buffer The buffer to read from.
	 */
	private static void handleContactInformation(IoBuffer buffer) {
		String username = buffer.getString();
		Player player = Repository.getPlayerByName(username);
		if (player == null || !player.isActive()) {
			return;
		}
		PacketRepository.send(ContactPackets.class, new ContactContext(player, ContactContext.UPDATE_STATE_TYPE));
		player.getCommunication().getContacts().clear();
		int length = buffer.get() & 0xFF;
		for (int i = 0; i < length; i++) {
			String name = buffer.getString();
			Contact contact = new Contact(name);
			contact.setRank(ClanRank.values()[buffer.get() & 0xFF]);
			contact.setWorldId(buffer.get() & 0xFF);
			player.getCommunication().getContacts().put(name, contact);
			PacketRepository.send(ContactPackets.class, new ContactContext(player, name, contact.getWorldId()));
		}
		player.getCommunication().getBlocked().clear();
		length = buffer.get() & 0xFF;
		for (int i = 0; i < length; i++) {
			player.getCommunication().getBlocked().add(buffer.getString());
		}
		PacketRepository.send(ContactPackets.class, new ContactContext(player, ContactContext.IGNORE_LIST_TYPE));
		if (buffer.get() == 1) {
			String name = buffer.getString();
			sendJoinClan(player, name);
		}
	}

	/**
	 * Handles the contact update packet.
	 */
	private static void handleContactUpdate(IoBuffer buffer) {
		String username = buffer.getString();
		String contactName = buffer.getString();
		boolean block = buffer.get() == 1;
		int type = buffer.get();
		Player player = Repository.getPlayerByName(username);
		if (player == null || !player.isActive()) {
			return;
		}
		switch (type) {
			case 0:
				if (block) {
					player.getCommunication().getBlocked().add(contactName);
					PacketRepository.send(ContactPackets.class, new ContactContext(player, ContactContext.IGNORE_LIST_TYPE));
					break;
				}
				int worldId = buffer.get() & 0xFF;
				Contact contact = player.getCommunication().getContacts().get(contactName);
				if (contact == null) {
					player.getCommunication().getContacts().put(contactName, contact = new Contact(contactName));
				}
				contact.setWorldId(worldId);
				PacketRepository.send(ContactPackets.class, new ContactContext(player, contactName, worldId));
				break;
			case 1:
				if (block) {
					player.getCommunication().getBlocked().remove(contactName);
					PacketRepository.send(ContactPackets.class, new ContactContext(player, ContactContext.IGNORE_LIST_TYPE));
					break;
				}
				player.getCommunication().getContacts().remove(contactName);
				break;
			default:
				ClanRank rank = ClanRank.values()[type - 2];
				contact = player.getCommunication().getContacts().get(contactName);
				if (contact == null) {
					// System.err.println("Invalid contact specified [name=" +
					// contact + "]!");
					break;
				}
				contact.setRank(rank);
				ClanRepository clan = ClanRepository.get(username);
				if (clan != null) {
					clan.rank(contactName, rank);
				}
				PacketRepository.send(ContactPackets.class, new ContactContext(player, contactName, contact.getWorldId()));
				break;
		}
	}

	/**
	 * Handles the message packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handleMessage(IoBuffer buffer) {
		String username = buffer.getString();
		String sender = buffer.getString();
		int type = buffer.get() & 0xFF;
		int icon = buffer.get() & 0xFF;
		String message = buffer.getString();
		Player player = Repository.getPlayerByName(username);
		if (player == null || !player.isActive()) {
			return;
		}
		int opcode = MessageContext.SEND_MESSAGE;
		switch (type) {
			case 1:
				opcode = MessageContext.RECIEVE_MESSAGE;
				break;
			case 2:
				opcode = MessageContext.CLAN_MESSAGE;
				break;
		}
		PacketRepository.send(CommunicationMessage.class, new MessageContext(player, sender, icon, opcode, message));
	}

	/**
	 * Handles the clan information packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handleClanInformation(IoBuffer buffer) {
		String owner = buffer.getString();
		ClanRepository clan = ClanRepository.getClans().get(owner);
		if (clan == null) {
			ClanRepository.getClans().put(owner, clan = new ClanRepository(owner));
		} else {
			clan.getRanks().clear();
			clan.getPlayers().clear();
		}
		clan.setName(buffer.getString());
		int length = buffer.get() & 0xFF;
		for (int i = 0; i < length; i++) {
			String name = buffer.getString();
			int worldId = buffer.get() & 0xFF;
			clan.getRanks().put(name, ClanRank.values()[buffer.get() & 0xFF]);
			ClanEntry entry = new ClanEntry(name, worldId);
			if (worldId == GameWorld.getSettings().getWorldId()) {
				Player player = Repository.getPlayerByName(name);
				entry.setPlayer(player);
				if (player != null) {
					player.getCommunication().setClan(clan);
				}
			}
			clan.getPlayers().add(entry);
		}
		clan.setJoinRequirement(ClanRank.values()[buffer.get() & 0xFF]);
		clan.setKickRequirement(ClanRank.values()[buffer.get() & 0xFF]);
		clan.setMessageRequirement(ClanRank.values()[buffer.get() & 0xFF]);
		clan.setLootRequirement(ClanRank.values()[buffer.get() & 0xFF]);
		clan.update();
	}

	/**
	 * Handles the clan information packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handleLeaveClan(IoBuffer buffer) {
		String name = buffer.getString();
		Player player = Repository.getPlayerByName(name);
		if (player == null || !player.isActive()) {
			return;
		}
		if (player.getCommunication().getClan() == null) {
			return;
		}
		PacketRepository.send(UpdateClanChat.class, new ClanContext(player, null, true));
		player.getCommunication().setClan(null);
	}

	/**
	 * Handles a contact notification packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handleContactNotification(IoBuffer buffer) {
		String name = buffer.getString();
		int worldId = buffer.get() & 0xFF;
		int size = buffer.get() & 0xFF;
		for (int i = 0; i < size; i++) {
			String username = buffer.getString();
			Player player = Repository.getPlayerByName(username);
			if (player == null) {
				continue;
			}
			Contact c = player.getCommunication().getContacts().get(name);
			if (c == null) {
				continue;
			}
			c.setWorldId(worldId);
			PacketRepository.send(ContactPackets.class, new ContactContext(player, name, worldId));
		}
	}

	/**
	 * Handles a player logout notification packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handlePlayerLogout(IoBuffer buffer) {
		String name = buffer.getString();
		for (Player p : Repository.getPlayers()) {
			if (CommunicationInfo.hasContact(p, name)) {
				PacketRepository.send(ContactPackets.class, new ContactContext(p, name, 0));
			}
		}
	}

	/**
	 * Handles the update packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handleUpdate(IoBuffer buffer) {
		int ticks = buffer.getInt();
		if (ticks < 0) {
			SystemManager.getUpdater().cancel();
		} else {
			SystemManager.getUpdater().setCountdown(ticks);
			SystemManager.flag(SystemState.UPDATING);
		}
	}

	/**
	 * Handles the punishment update packet.
	 *
	 * @param buffer The buffer.
	 */
	private static void handlePunishmentUpdate(IoBuffer buffer) {
		String key = buffer.getString();
		int type = buffer.get();
		long duration = buffer.getLong();
		switch (type) {
			case 0:
				Player player = Repository.getPlayerByName(key);
				if (player != null && player.isActive()) {
					player.getPacketDispatch().sendMessages((duration > 0L ? new String[]{"You have been muted.", "To prevent further mutes please read the rules."} : new String[]{"You have been unmuted."}));
				}
				player.getDetails().setMuteTime(duration);
				break;
			case 1:
				player = Repository.getPlayerByName(key);
				if (player != null && player.isActive() && duration > System.currentTimeMillis()) {
					player.getSession().disconnect();
				}
				break;
			case 2:
				for (Player p : Repository.getPlayers()) {
					if (p.getDetails().getIpAddress().equals(key)) {
						p.getSession().disconnect();
					}
				}
				break;
			case 3:
				for (Player p : Repository.getPlayers()) {
					if (p == null || key == null || p.getDetails() == null) {
						return;
					}
					if (p.getDetails().getMacAddress().equals(key)) {
						p.getSession().disconnect();
					}
				}
				break;
			case 4:
				for (Player p : Repository.getPlayers()) {
					if (p == null || key == null || p.getDetails() == null) {
						return;
					}
					if (p.getDetails().getSerial().equals(key)) {
						p.getSession().disconnect();
					}
				}
				break;
			case 6:
				player = Repository.getPlayerByName(key);
				if (player != null) {
					player.getPacketDispatch().sendLogout();
					player.clear(true);
					player.getSession().disconnect();
				}
				break;
		}
	}


	/**
	 * Gets the icon to send.
	 *
	 * @param d the details.
	 * @return the icon.
	 */
	private static int getIcon(PlayerDetails d) {
		int icon = 0;
		if (d.getRights() != Rights.REGULAR_PLAYER) {
			icon = d.getRights().toInteger();
		}
		return icon;
	}
}