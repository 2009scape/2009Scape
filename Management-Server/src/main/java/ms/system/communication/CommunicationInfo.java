package ms.system.communication;

import ms.ServerConstants;
import ms.net.packet.WorldPacketRepository;
import ms.system.util.ByteBufferUtils;
import ms.system.util.StringUtils;
import ms.world.GameServer;
import ms.world.PlayerSession;
import ms.world.WorldDatabase;

import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Holds communication information.
 * @author Emperor
 *
 */
public final class CommunicationInfo {

	/**
	 * The maximum list size.
	 */
	public static final int MAX_LIST_SIZE = 200;

	/**
	 * The clan ranks.
	 */
	private final Map<String, ClanRank> contacts = new HashMap<>();

	/**
	 * The list of blocked players.
	 */
	private final List<String> blocked = new ArrayList<>();

	/**
	 * The player's clan name.
	 */
	private String clanName = "";

	/**
	 * The current clan this player is in.
	 */
	private String currentClan = "2009scape";

	/**
	 * The rank required for joining.
	 */
	private ClanRank joinRequirement = ClanRank.FRIEND;

	/**
	 * The rank required for messaging.
	 */
	private ClanRank messageRequirement = ClanRank.NONE;

	/**
	 * The rank required for kicking members.
	 */
	private ClanRank kickRequirement = ClanRank.OWNER;

	/**
	 * The rank required for loot-share.
	 */
	private ClanRank lootRequirement = ClanRank.ADMINISTRATOR;

	/**
	 * The public chat setting.
	 */
	private int publicChatSetting = 0;

	/**
	 * The private chat setting.
	 */
	private int privateChatSetting = 0;

	/**
	 * The trade setting.
	 */
	private int tradeSetting = 0;

	/**
	 * The player session.
	 */
	private PlayerSession player;

	/**
	 * Constructs a new {@code CommunicationInfo} {@code Object}.
	 * @param player The player.
	 */
	public CommunicationInfo(PlayerSession player) {
		this.setPlayer(player);
	}

	/**
	 * Called when the player logs in.
	 */
	public void sync() {
		if (privateChatSetting != 2) {
			for (GameServer server : WorldDatabase.getWorlds()) {
				if (server != null && server.isActive()) {
					List<String> names = new ArrayList<>();
					for (PlayerSession p : server.getPlayers().values()) {
						if (p.isActive() && p.getCommunication().contacts.containsKey(player.getUsername())) {
							if (privateChatSetting == 0 || contacts.containsKey(p.getUsername())) {
								names.add(p.getUsername());
							}
						}
					}
					WorldPacketRepository.notifyPlayers(server, player, names);
				}
			}
		}
	}

	/**
	 * Called when the player logs out.
	 */
	public void clear() {
		for (GameServer server : WorldDatabase.getWorlds()) {
			if (server != null && server.isActive()) {
				WorldPacketRepository.notifyLogout(server, player);
			}
		}
		if (player.getClan() != null) {
			player.getClan().leave(player, true);
		}
	}

	/**
	 * Saves the communication info.
	 * @param statement The buffer.
	 * @throws SQLException The exception if thrown.
	 */
	public void save(PreparedStatement statement) throws SQLException {
		String contacts = "";
		String blocked = "";
		for (int i = 0; i < this.blocked.size(); i++) {
			blocked += (i == 0 ? "" : ",") + this.blocked.get(i);
		}
		int count = 0;
		for (Entry<String, ClanRank> entry : this.contacts.entrySet()) {
			contacts += "{" + entry.getKey() + "," + entry.getValue().ordinal() + "}" + (count == this.contacts.size() - 1 ? "" : "~");
			count++;
		}
		statement.setString(3, contacts);
		statement.setString(4, blocked);
		statement.setString(5, clanName);
		statement.setString(6, currentClan);
		statement.setString(7, joinRequirement.ordinal() + "," + messageRequirement.ordinal() + "," + kickRequirement.ordinal() + "," + lootRequirement.ordinal());
		statement.setString(8, publicChatSetting + "," + privateChatSetting + "," + tradeSetting);
	}

	/**
	 * Parses the communication info from the database.
	 * @param set The result set.
	 * @throws SQLException The exception if thrown.
	 */
	public void parse(ResultSet set) throws SQLException {
		String contacts = set.getString("contacts");
		String[] tokens;
		if (contacts != null && !contacts.isEmpty()) {
			String[] datas = contacts.split("~");
			for (String d : datas) {
				tokens = d.replace("{", "").replace("}", "").split(",");
				if (tokens.length < 2) {
					continue;
				}
				this.contacts.put(tokens[0], ClanRank.values()[Integer.parseInt(tokens[1])]);
			}
		}
		String bl = set.getString("blocked");
		if (bl != null && !bl.isEmpty()) {
			tokens = bl.split(",");
			for (String name : tokens) {
				blocked.add(name);
			}
		}
		clanName = set.getString("clanName");
		currentClan = set.getString("currentClan");
		String clanReqs = set.getString("clanReqs");
		if (!clanReqs.isEmpty()) {
			tokens = clanReqs.split(",");
			ClanRank rank = null;
			int ordinal = 0;
			for (int i = 0; i < tokens.length; i++) {
				ordinal = Integer.parseInt(tokens[i]);
				if (ordinal < 0 || ordinal > ClanRank.values().length -1) {
					continue;
				}
				rank = ClanRank.values()[ordinal];
				switch (i) {
					case 0:
						joinRequirement = rank;
						break;
					case 1:
						messageRequirement = rank;
						break;
					case 2:
						if (ordinal < 3 || ordinal > 8) {
							break;
						}
						kickRequirement = rank;
						break;
					case 3:
						lootRequirement = rank;
						break;
				}
			}
		}
		String chatSettings = set.getString("chatSettings");
		if (!chatSettings.isEmpty()) {
			tokens = chatSettings.split(",");
			for (int i = 0; i < tokens.length; i++) {
				switch (i) {
					case 0:
						publicChatSetting = Integer.parseInt(tokens[0]);
						break;
					case 1:
						privateChatSetting = Integer.parseInt(tokens[1]);
						break;
					case 2:
						tradeSetting = Integer.parseInt(tokens[2]);
						break;
				}
			}
		}
	}

	/**
	 * Saves the communication info.
	 * @param buffer The buffer.
	 */
	public void save(ByteBuffer buffer) {
		buffer.put((byte) contacts.size());
		for (String name : contacts.keySet()) {
			ByteBufferUtils.putString(name, buffer);
			buffer.put((byte) contacts.get(name).ordinal());
		}
		buffer.put((byte) blocked.size());
		for (String name : blocked) {
			ByteBufferUtils.putString(name, buffer);
		}
		ByteBufferUtils.putString(clanName, buffer);
		if (currentClan != null) {
			ByteBufferUtils.putString(currentClan, buffer.put((byte) 1));
		} else {
			buffer.put((byte) 0);
		}
		buffer.put((byte) joinRequirement.ordinal());
		buffer.put((byte) messageRequirement.ordinal());
		buffer.put((byte) kickRequirement.ordinal());
		buffer.put((byte) lootRequirement.ordinal());
		buffer.put((byte) publicChatSetting);
		buffer.put((byte) privateChatSetting);
		buffer.put((byte) tradeSetting);
	}

	/**
	 * Parses the communication info from the buffer.
	 * @param buffer The buffer.
	 */
	public void parse(ByteBuffer buffer) {
		int length = buffer.get() & 0xFF;
		for (int i = 0; i < length; i++) {
			contacts.put(ByteBufferUtils.getString(buffer), ClanRank.values()[buffer.get() & 0xFF]);
		}
		length = buffer.get() & 0xFF;
		for (int i = 0; i < length; i++) {
			blocked.add(ByteBufferUtils.getString(buffer));
		}
		clanName = ByteBufferUtils.getString(buffer);
		if (buffer.get() == 1) {
			currentClan = ByteBufferUtils.getString(buffer);
		}
		joinRequirement = ClanRank.values()[buffer.get()];
		messageRequirement = ClanRank.values()[buffer.get()];
		kickRequirement = ClanRank.values()[buffer.get()];
		lootRequirement = ClanRank.values()[buffer.get()];
		publicChatSetting = buffer.get();
		privateChatSetting = buffer.get();
		tradeSetting = buffer.get();
	}

	/**
	 * Sends a message to the target.
	 * @param target The target.
	 * @param message The message to send.
	 */
	public void sendMessage(String target, String message) {
		PlayerSession receiver = WorldDatabase.getPlayer(target);
		if (receiver == null || !receiver.isActive()) {
			WorldPacketRepository.sendPlayerMessage(player, "That player is currently offline.");
			return;
		}
		WorldPacketRepository.sendMessage(player, receiver, 0, message);
		WorldPacketRepository.sendMessage(receiver, player, 1, message);
	}

	/**
	 * Adds a contact.
	 * @param contact The contact to add.
	 */
	public void add(String contact) {
		if (contacts.size() >= MAX_LIST_SIZE) {
			WorldPacketRepository.sendPlayerMessage(player, "Your friend list is full.");
			return;
		}
		if (blocked.contains(contact)) {
			WorldPacketRepository.sendPlayerMessage(player, "Please remove " + StringUtils.formatDisplayName(contact) + " from your ignored list first.");
			return;
		}
		if (contacts.containsKey(contact)) {
			WorldPacketRepository.sendPlayerMessage(player, StringUtils.formatDisplayName(contact) + " is already on your friend list.");
			return;
		}
		contacts.put(contact, ClanRank.FRIEND);
		WorldPacketRepository.sendContactUpdate(player, contact, false, false, getWorldId(player, contact), null);
		ClanRepository clan = ClanRepository.getClans().get(player.getUsername());
		if (clan != null) {
			clan.update();
		}
		if (privateChatSetting == 1) {
			PlayerSession other = WorldDatabase.getPlayer(contact);
			if (other != null && other.isActive() && other.getCommunication().getContacts().containsKey(player.getUsername())) {
				WorldPacketRepository.sendContactUpdate(other, player.getUsername(), false, false, getWorldId(other, player.getUsername()), null);
			}
		}
	}

	/**
	 * Gets the world id for the given contact.
	 * @param player The player.
	 * @param contact The contact.
	 * @return The world id to display.
	 */
	public static int getWorldId(PlayerSession player, String contact) {
		PlayerSession p = WorldDatabase.getPlayer(contact);
		if (p == null || !p.isActive() || p.getCommunication().getPrivateChatSetting() == 2) {
			return 0;
		}
		if (p.getCommunication().getBlocked().contains(player.getUsername())) {
			return 0;
		}
		if (p.getCommunication().getPrivateChatSetting() == 1) {
			if (p.getCommunication().getContacts().containsKey(player.getUsername())) {
				return p.getWorldId();
			}
			return 0;
		}
		return p.getWorldId();
	}

	/**
	 * Removes a contact.
	 * @param contact The contact to remove.
	 * @param block If the contact should be removed from the block list.
	 */
	public void remove(String contact, boolean block) {
		PlayerSession other = WorldDatabase.getPlayer(contact);
		if (block) {
			blocked.remove(contact);
			if (other != null && other.isActive() && other.getCommunication().getContacts().containsKey(player.getUsername())) {
				WorldPacketRepository.sendContactUpdate(other, player.getUsername(), false, false, getWorldId(other, player.getUsername()), null);
			}
		} else {
			contacts.remove(contact);
			ClanRepository clan = ClanRepository.getClans().get(player.getUsername());
			if (clan != null) {
				clan.update();
			}
			if (privateChatSetting == 1 && other != null && other.isActive() && other.getCommunication().getContacts().containsKey(player.getUsername())) {
				WorldPacketRepository.sendContactUpdate(other, player.getUsername(), false, false, getWorldId(other, player.getUsername()), null);
			}
		}
		WorldPacketRepository.sendContactUpdate(player, contact, block, true, 0, null);
	}


	/**
	 * Adds a blocked contact.
	 * @param contact The contact to block.
	 */
	public void block(String contact) {
		if (blocked.size() >= MAX_LIST_SIZE) {
			WorldPacketRepository.sendPlayerMessage(player, "Your ignore list is full.");
			return;
		}
		if (contacts.containsKey(contact)) {
			WorldPacketRepository.sendPlayerMessage(player, "Please remove " + StringUtils.formatDisplayName(contact) + " from your friends list first.");
			return;
		}
		if (blocked.contains(contact)) {
			WorldPacketRepository.sendPlayerMessage(player, StringUtils.formatDisplayName(contact) + " is already on your friend list.");
			return;
		}
		blocked.add(contact);
		WorldPacketRepository.sendContactUpdate(player, contact, true, false, 0, null);
		PlayerSession other = WorldDatabase.getPlayer(contact);
		if (other != null && other.isActive() && other.getCommunication().getContacts().containsKey(player.getUsername())) {
			WorldPacketRepository.sendContactUpdate(other, player.getUsername(), false, false, 0, null);
		}
	}

	/**
	 * Updates the clan rank of a certain contact.
	 * @param contact The contact.
	 * @param clanRank The clan rank to set.
	 */
	public void updateClanRank(String contact, ClanRank clanRank) {
		if (!contacts.containsKey(contact)) {
			System.err.println("Could not find contact " + contact + " to update clan rank!");
			return;
		}
		contacts.put(contact, clanRank);
		ClanRepository clan = ClanRepository.getClans().get(player.getUsername());
		if (clan != null) {
			clan.update();
		}

		WorldPacketRepository.sendContactUpdate(player, contact, false, false, 0, clanRank);
	}

	/**
	 * Updates the settings.
	 * @param publicSetting The public chat setting.
	 * @param privateSetting The private chat setting.
	 * @param tradeSetting The trade setting.
	 */
	public void updateSettings(int publicSetting, int privateSetting, int tradeSetting) {
		this.publicChatSetting = publicSetting;
		this.tradeSetting = tradeSetting;
		if (this.privateChatSetting != privateSetting) {
			updatePrivateSetting(privateSetting);
		}
	}

	/**
	 * Updates the private chat setting. 
	 * @param privateSetting The private chat setting.
	 */
	private void updatePrivateSetting(int privateSetting) {
		this.privateChatSetting = privateSetting;
		for (GameServer server : WorldDatabase.getWorlds()) {
			if (server != null && server.isActive()) {
				if (privateSetting == 2) {
					WorldPacketRepository.notifyLogout(server, player);
					continue;
				}
				for (PlayerSession p : server.getPlayers().values()) {
					if (p.isActive() && p.getCommunication().contacts.containsKey(player.getUsername())) {
						WorldPacketRepository.sendContactUpdate(p, player.getUsername(), false, false, getWorldId(p, player.getUsername()), null);
					}
				}
			}
		}
	}

	/**
	 * Gets the contacts value.
	 * @return The contacts.
	 */
	public Map<String, ClanRank> getContacts() {
		return contacts;
	}

	/**
	 * Gets the clan rank for the given contact.
	 * @param contact The contact.
	 * @return The rank.
	 */
	public ClanRank getRank(String contact) {
		for (String name : ServerConstants.ADMINISTRATORS) {
			if (contact.equals(name)) {
				return ClanRank.ADMINISTRATOR;
			}
		}
		return contacts.get(contact);
	}

	/**
	 * Gets the blocked value.
	 * @return The blocked.
	 */
	public List<String> getBlocked() {
		return blocked;
	}

	/**
	 * Gets the clanName value.
	 * @return The clanName.
	 */
	public String getClanName() {
		return clanName;
	}

	/**
	 * Sets the clanName value.
	 * @param clanName The clanName to set.
	 */
	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	/**
	 * Gets the currentClan value.
	 * @return The currentClan.
	 */
	public String getCurrentClan() {
		return currentClan;
	}

	/**
	 * Sets the currentClan value.
	 * @param currentClan The currentClan to set.
	 */
	public void setCurrentClan(String currentClan) {
		this.currentClan = currentClan;
	}

	/**
	 * Gets the player value.
	 * @return The player.
	 */
	public PlayerSession getPlayer() {
		return player;
	}

	/**
	 * Sets the player value.
	 * @param player The player to set.
	 */
	public void setPlayer(PlayerSession player) {
		this.player = player;
	}

	/**
	 * Gets the joinRequirement value.
	 * @return The joinRequirement.
	 */
	public ClanRank getJoinRequirement() {
		return joinRequirement;
	}

	/**
	 * Sets the joinRequirement value.
	 * @param joinRequirement The joinRequirement to set.
	 */
	public void setJoinRequirement(ClanRank joinRequirement) {
		this.joinRequirement = joinRequirement;
	}

	/**
	 * Gets the messageRequirement value.
	 * @return The messageRequirement.
	 */
	public ClanRank getMessageRequirement() {
		return messageRequirement;
	}

	/**
	 * Sets the messageRequirement value.
	 * @param messageRequirement The messageRequirement to set.
	 */
	public void setMessageRequirement(ClanRank messageRequirement) {
		this.messageRequirement = messageRequirement;
	}

	/**
	 * Gets the kickRequirement value.
	 * @return The kickRequirement.
	 */
	public ClanRank getKickRequirement() {
		return kickRequirement;
	}

	/**
	 * Sets the kickRequirement value.
	 * @param kickRequirement The kickRequirement to set.
	 */
	public void setKickRequirement(ClanRank kickRequirement) {
		this.kickRequirement = kickRequirement;
	}

	/**
	 * Gets the lootRequirement value.
	 * @return The lootRequirement.
	 */
	public ClanRank getLootRequirement() {
		return lootRequirement;
	}

	/**
	 * Sets the lootRequirement value.
	 * @param lootRequirement The lootRequirement to set.
	 */
	public void setLootRequirement(ClanRank lootRequirement) {
		this.lootRequirement = lootRequirement;
	}

	/**
	 * Gets the publicChatSetting value.
	 * @return The publicChatSetting.
	 */
	public int getPublicChatSetting() {
		return publicChatSetting;
	}

	/**
	 * Sets the publicChatSetting value.
	 * @param publicChatSetting The publicChatSetting to set.
	 */
	public void setPublicChatSetting(int publicChatSetting) {
		this.publicChatSetting = publicChatSetting;
	}

	/**
	 * Gets the privateChatSetting value.
	 * @return The privateChatSetting.
	 */
	public int getPrivateChatSetting() {
		return privateChatSetting;
	}

	/**
	 * Sets the privateChatSetting value.
	 * @param privateChatSetting The privateChatSetting to set.
	 */
	public void setPrivateChatSetting(int privateChatSetting) {
		this.privateChatSetting = privateChatSetting;
	}

	/**
	 * Gets the tradeSetting value.
	 * @return The tradeSetting.
	 */
	public int getTradeSetting() {
		return tradeSetting;
	}

	/**
	 * Sets the tradeSetting value.
	 * @param tradeSetting The tradeSetting to set.
	 */
	public void setTradeSetting(int tradeSetting) {
		this.tradeSetting = tradeSetting;
	}

}