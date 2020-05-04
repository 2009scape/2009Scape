package org.crandor.game.system.communication;

import org.crandor.cache.misc.buffer.ByteBufferUtils;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.monitor.PlayerMonitor;
import org.crandor.game.system.mysql.SQLTable;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.repository.Repository;
import org.crandor.net.amsc.MSPacketRepository;
import org.crandor.net.amsc.WorldCommunicator;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ContactContext;
import org.crandor.net.packet.context.MessageContext;
import org.crandor.net.packet.out.CommunicationMessage;
import org.crandor.net.packet.out.ContactPackets;
import org.crandor.tools.StringUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Holds communication information.
 * @author Emperor
 */
public final class CommunicationInfo {

	/**
	 * The maximum list size.
	 */
	public static final int MAX_LIST_SIZE = 200;

	/**
	 * The clan ranks.
	 */
	private final Map<String, Contact> contacts = new HashMap<>();

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
	private String currentClan = "";

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
	private ClanRank lootRequirement = ClanRank.KELDAGRIM_MOD;

	/**
	 * If lootshare is enabled.
	 */
	private boolean lootShare;

	/**
	 * The current clan joined.
	 */
	private ClanRepository clan = null;

	/**
	 * The loot-share pulse.
	 */
	private Pulse lootSharePulse;

	/**
	 * Constructs a new {@code CommunicationInfo} {@code Object}.
	 */
	public CommunicationInfo() {
		/*
		 * empty.
		 */
	}

	/**
	 * Saves the communication info.
	 * @param table The SQL table to update.
	 */
	public void save(SQLTable table) {
		String contacts = "";
		String blocked = "";
		for (int i = 0; i < this.blocked.size(); i++) {
			blocked += (i == 0 ? "" : ",") + this.blocked.get(i);
		}
		int count = 0;
		for (Entry<String, Contact> entry : this.contacts.entrySet()) {
			contacts += "{" + entry.getKey() + "," + entry.getValue().getRank().ordinal() + "}" + (count == this.contacts.size() - 1 ? "" : "~");
			count++;
		}
		table.getColumn("blocked").updateValue(blocked);
		table.getColumn("contacts").updateValue(contacts);
		table.getColumn("clanName").updateValue(clanName);
		table.getColumn("currentClan").updateValue(currentClan);
		table.getColumn("clanReqs").updateValue(joinRequirement.ordinal() + "," + messageRequirement.ordinal() + "," + kickRequirement.ordinal() + "," + lootRequirement.ordinal());
	}

	/**
	 * Parses the communication info from the database.
	 * @param table The sql table to parse.
	 */
	public void parse(SQLTable table) {
		String[] tokens = null;
		if (table.getColumn("contacts").getValue() != null ) {
			String contacts = (String) table.getColumn("contacts").getValue();
			if (contacts != "") {
				String[] datas = contacts.split("~");
				Contact contact = null;
				for (String d : datas) {
					tokens = d.replace("{", "").replace("}", "").split(",");
					if (tokens.length == 0) {
						continue;
					}
					contact = new Contact(tokens[0]);
					contact.setRank(ClanRank.values()[Integer.valueOf(tokens[1])]);
					this.contacts.put(tokens[0], contact);
				}
			}
		}
		if (table.getColumn("blocked").getValue() != null ) {
			String blocked = (String) table.getColumn("blocked").getValue();
			if (blocked != "") {
				tokens = blocked.split(",");
				for (String name : tokens) {
					this.blocked.add(name);
				}
			}
		}
		clanName = ((String) table.getColumn("clanName").getValue());
		currentClan = (String) table.getColumn("currentClan").getValue(); 
		String clanReqs = (String) table.getColumn("clanReqs").getValue();
		if (clanReqs == "") {
			return;
		}
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

	/**
	 * Toggles the loot-share.
	 */
	public void toggleLootshare(final Player player) {
		if (lootShare) {
			lootShare = false;
			player.getConfigManager().set(1083, 0);
		} else if (lootSharePulse != null) {
			lootSharePulse.stop();
			lootSharePulse = null;
			player.getConfigManager().set(1083, 0);
		} else if (!lootShare) {
			player.getConfigManager().set(1083, 2);
			lootSharePulse = new Pulse(GameWorld.getSettings().isDevMode() ? 5 : 200, player) {
				@Override
				public boolean pulse() {
					lootShare = true;
					lootSharePulse = null;
					player.getConfigManager().set(1083, 1);
					return true;
				}
			};
			GameWorld.Pulser.submit(lootSharePulse);
		}
	}

	/**
	 * Roar temp
	 * @param buffer
	 */
	public void parsePrevious(ByteBuffer buffer) {
		int size = buffer.get() & 0xFF;
		for (int i = 0; i < size; i++) {
			String name = ByteBufferUtils.getString(buffer);
			Contact contact = new Contact(name);
			contact.setRank(ClanRank.FRIEND);
			contacts.put(name, contact);
		}
		size = buffer.get() & 0xFF;
		for (int i = 0; i < size; i++) {
			blocked.add(ByteBufferUtils.getString(buffer));
		}
		if (buffer.get() == 1) {
			ByteBufferUtils.getString(buffer);
		}
	}

	/**
	 * Synchronizes the contact lists.
	 */
	public void sync(Player player) {
		if (WorldCommunicator.isEnabled()) {
			if (!player.isArtificial()) {
				MSPacketRepository.requestCommunicationInfo(player.getName());
			}
			return;
		}
		if (player.getSettings().getPrivateChatSetting() != 2) {
			notifyPlayers(player, true, false);
		}
		PacketRepository.send(ContactPackets.class, new ContactContext(player, ContactContext.UPDATE_STATE_TYPE));
		PacketRepository.send(ContactPackets.class, new ContactContext(player, ContactContext.IGNORE_LIST_TYPE));
		for (String name : contacts.keySet()) {
			Player p = Repository.getPlayer(name);
			int worldId = 0;
			if (p != null && showActive(player, p)) {
				worldId = GameWorld.getSettings().getWorldId();
			}
			PacketRepository.send(ContactPackets.class, new ContactContext(player, name, worldId));
		}
		if (currentClan != null && !player.isArtificial() && (clan = ClanRepository.get(currentClan)) != null) {
			clan.enter(player);
		}
	}

	/**
	 * Notifies all other players.
	 * @param online If this player is online.
	 * @param chatSetting If it was a chat setting change.
	 */
	public static void notifyPlayers(Player player, boolean online, boolean chatSetting) {
		if (WorldCommunicator.isEnabled()) {
			if (!online && !chatSetting) {
				MSPacketRepository.sendPlayerRemoval(player.getName());
			}
			return;
		}
		for (Player p : Repository.getPlayers()) {
			if (p == player || !p.isActive()) {
				continue;
			}
			if (hasContact(p, player.getName())) {
				int worldId = 0;
				if (online && showActive(p, player)) {
					worldId = GameWorld.getSettings().getWorldId();
				}
				p.getCommunication().getContacts().get(player.getName()).setWorldId(worldId);
				PacketRepository.send(ContactPackets.class, new ContactContext(p, player.getName(), worldId));
			}
		}
		if (!online && !chatSetting && player.getCommunication().getClan() != null) {
			player.getCommunication().getClan().leave(player, true);
		}
	}

	/**
	 * Sends a message to the target.
	 * @param player The player sending the message.
	 * @param target The target.
	 * @param message The message to send.
	 */
	public static void sendMessage(Player player, String target, String message) {
		if (WorldCommunicator.isEnabled()) {
			StringBuilder sb = new StringBuilder(message);
			sb.append(" => ").append(target);
			player.getMonitor().log(sb.toString(), PlayerMonitor.PRIVATE_CHAT_LOG);
			MSPacketRepository.sendPrivateMessage(player, target, message);
			return;
		}
		if (!player.getDetails().getCommunication().contacts.containsKey(target)) {
			return;
		}
		Player p = Repository.getPlayer(target);
		if (p == null || !p.isActive() || !showActive(p, player)) {
			player.getPacketDispatch().sendMessage("That player is currently offline.");
			return;
		}
		if (!GameWorld.getSettings().isDevMode()) {
			StringBuilder sb = new StringBuilder(message);
			sb.append(" => ").append(target);
			player.getMonitor().log(sb.toString(), PlayerMonitor.PRIVATE_CHAT_LOG);
		}
		PacketRepository.send(CommunicationMessage.class, new MessageContext(player, p, MessageContext.SEND_MESSAGE, message));
		PacketRepository.send(CommunicationMessage.class, new MessageContext(p, player, MessageContext.RECIEVE_MESSAGE, message));
	}

	/**
	 * Adds a contact.
	 * @param contact The contact to add.
	 */
	public static void add(Player player, String contact) {
		CommunicationInfo info = player.getDetails().getCommunication();
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendContactUpdate(player.getName(), contact, false, false, null);
			return;
		}
		if (info.contacts.size() >= MAX_LIST_SIZE) {
			player.getPacketDispatch().sendMessage("Your friend list is full.");
			return;
		}
		if (info.contacts.containsKey(contact)) {
			player.getPacketDispatch().sendMessage(StringUtils.formatDisplayName(contact) + " is already on your friend list.");
			return;
		}
		ClanRepository clan = ClanRepository.get(player.getName(), false);
		if (clan != null) {
			clan.rank(contact, ClanRank.FRIEND);
		}
		info.contacts.put(contact, new Contact(contact));
		Player target = Repository.getPlayer(contact);
		if (target != null) {
			if (showActive(player, target)) {
				PacketRepository.send(ContactPackets.class, new ContactContext(player, contact, GameWorld.getSettings().getWorldId()));
			}
			if (player.getSettings().getPrivateChatSetting() == 1 && showActive(target, player)) {
				PacketRepository.send(ContactPackets.class, new ContactContext(target, player.getName(), GameWorld.getSettings().getWorldId()));
			}
		}
	}

	/**
	 * Removes a contact.
	 * @param contact The contact to remove.
	 * @param block If the contact should be removed from the block list.
	 */
	public static void remove(Player player, String contact, boolean block) {
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendContactUpdate(player.getName(), contact, true, block, null);
			return;
		}
		CommunicationInfo info = player.getDetails().getCommunication();
		if (block) {
			info.blocked.remove(contact);
			Player target = Repository.getPlayer(contact);
			if (target != null && hasContact(target, player.getName())) {
				int worldId = 0;
				if (showActive(target, player)) {
					worldId = GameWorld.getSettings().getWorldId();
				}
				PacketRepository.send(ContactPackets.class, new ContactContext(target, player.getName(), worldId));
			}
		} else {
			info.contacts.remove(contact);
			ClanRepository clan = ClanRepository.get(player.getName(), false);
			if (clan != null) {
				clan.rank(contact, ClanRank.NONE);
			}
			if (player.getSettings().getPrivateChatSetting() == 1) {
				Player target = Repository.getPlayer(contact);
				if (target != null) {
					PacketRepository.send(ContactPackets.class, new ContactContext(target, player.getName(), 0));
				}
			}
		}
	}

	/**
	 * Adds a blocked contact.
	 * @param contact The contact to block.
	 */
	public static void block(Player player, String contact) {
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendContactUpdate(player.getName(), contact, false, true, null);
			return;
		}
		CommunicationInfo info = player.getDetails().getCommunication();
		if (info.blocked.size() >= MAX_LIST_SIZE) {
			player.getPacketDispatch().sendMessage("Your ignore list is full.");
			return;
		}
		if (info.blocked.contains(contact)) {
			player.getPacketDispatch().sendMessage(StringUtils.formatDisplayName(contact) + " is already on your ignore list.");
			return;
		}
		info.blocked.add(contact);
		Player target = Repository.getPlayer(contact);
		if (target != null && hasContact(target, player.getName())) {
			PacketRepository.send(ContactPackets.class, new ContactContext(target, player.getName(), 0));
		}
	}

	/**
	 * Updates the clan rank of a certain contact.
	 * @param contact The contact.
	 * @param clanRank The clan rank to set.
	 */
	public static void updateClanRank(Player player, String contact, ClanRank clanRank) {
		CommunicationInfo info = player.getDetails().getCommunication();
		Contact c = info.contacts.get(contact);
		if (c == null) {
			System.err.println("Could not find contact " + contact + " to update clan rank!");
			return;
		}
		c.setRank(clanRank);
		ClanRepository clan = ClanRepository.get(player.getName());
		if (clan != null) {
			clan.rank(contact, clanRank);
		}
		int worldId = 0;
		if (CommunicationInfo.showActive(player, contact)) {
			worldId = c.getWorldId();
		}
		PacketRepository.send(ContactPackets.class, new ContactContext(player, contact, worldId));
	}

	/**
	 * Checks if the player has the contact added.
	 * @param player The player.
	 * @param contact The contact.
	 * @return {@code True} if so.
	 */
	public static boolean hasContact(Player player, String contact) {
		return player.getDetails().getCommunication().contacts.containsKey(contact);
	}

	/**
	 * Checks if the target should be shown as online.
	 * @param name The target's name.
	 * @return {@code True} if so.
	 */
	public static boolean showActive(Player player, String name) {
		Player p = Repository.getPlayer(name);
		if (p != null) {
			return showActive(player, p);
		}
		return false;
	}

	/**
	 * Checks if the target should be shown as online.
	 * @param target The target.
	 * @return {@True} if so.
	 */
	public static boolean showActive(Player player, Player target) {
		if (target.getName().equals(player.getName())) {
			return false;
		}
		if (target.getCommunication().getBlocked().contains(player.getName())) {
			return false;
		}
		switch (target.getSettings().getPrivateChatSetting()) {
		case 1:
			if (!hasContact(target, player.getName())) {
				return false;
			}
			return true;
		case 2:
			return false;
		}
		return true;
	}

	/**
	 * Gets the contacts value.
	 * @return The contacts.
	 */
	public Map<String, Contact> getContacts() {
		return contacts;
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
	@Deprecated
	public void setCurrentClan(String currentClan) {
		this.currentClan = currentClan;
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
	 * Gets the clan.
	 * @return the clan
	 */
	public ClanRepository getClan() {
		return clan;
	}

	/**
	 * Sets the clan.
	 * @param clan the clan to set.
	 */
	public void setClan(ClanRepository clan) {
		this.clan = clan;
		this.currentClan = clan == null ? null : clan.getOwner();
	}

	/**
	 * Gets the lootShare.
	 * @return the lootShare
	 */
	public boolean isLootShare() {
		return lootShare;
	}

	/**
	 * Sets the lootShare.
	 * @param lootShare the lootShare to set.
	 */
	public void setLootShare(boolean lootShare) {
		this.lootShare = lootShare;
	}
}