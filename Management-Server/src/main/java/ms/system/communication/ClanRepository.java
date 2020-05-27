package ms.system.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ms.net.packet.WorldPacketRepository;
import ms.system.util.StringUtils;
import ms.world.GameServer;
import ms.world.PlayerSession;
import ms.world.WorldDatabase;
import ms.world.info.UIDInfo;

/**
 * Holds clan related information.
 * @author Emperor
 *
 */
public final class ClanRepository {

	/**
	 * The mapping of active clans.
	 */
	private static final Map<String, ClanRepository> CLANS = new HashMap<>();

	/**
	 * The maximum amount of members to be in a clan chat.
	 */
	public static final int MAX_MEMBERS = 100;

	/**
	 * The owner's details.
	 */
	private PlayerSession owner;

	/**
	 * The list of players currently in the chat.
	 */
	private final List<PlayerSession> players = new ArrayList<>(MAX_MEMBERS);

	/**
	 * The banned players.
	 */
	private final Map<String, Long> banned = new HashMap<>();

	/**
	 * Constructs a new {@code ClanRepository} {@code Object}.
	 */
	private ClanRepository() {
		/*
		 * empty.
		 */
	}

	/**
	 * Gets the clan repository for the given username.
	 * @param ownerName The clan owner's name.
	 * @return The clan repository.
	 */
	public static ClanRepository get(GameServer server, String ownerName) {
		ClanRepository clan = CLANS.get(ownerName);
		if (clan != null) {
			return clan;
		}
		PlayerSession owner = WorldDatabase.getPlayer(ownerName);
		if (owner == null) {
			owner = new PlayerSession(ownerName, ownerName, new UIDInfo());
			owner.parse();
		}
		if (owner.getCommunication().getClanName().equals("")) {
			return null;
		}
		clan = new ClanRepository();
		clan.owner = owner;
		CLANS.put(ownerName, clan);
		return clan;
	}

	/**
	 * Enters the clan chat.
	 * @param player The player.
	 */
	public void enter(PlayerSession player) {
		if (players.size() >= MAX_MEMBERS && !owner.getUsername().equals("keldagrim")) {
			WorldPacketRepository.sendPlayerMessage(player, "The channel you tried to join is full.:clan:");
			return;
		}
		if (player != owner && player.getRights() != 2) {
			if (isBanned(player.getUsername()) || owner.getCommunication().getBlocked().contains(player.getUsername())) {
				WorldPacketRepository.sendPlayerMessage(player, "You are temporarily banned from this clan channel.:clan:");
				return;
			}
			ClanRank rank = getRank(player);
			if (rank.ordinal() < getJoinRequirement().ordinal()) {
				WorldPacketRepository.sendPlayerMessage(player, "You do not have a high enough rank to join this clan channel.:clan:");
				return;
			}
		}
		if (!players.contains(player)) {
			players.add(player);
		}
		WorldPacketRepository.sendPlayerMessage(player, "Now talking in clan channel " + owner.getCommunication().getClanName() + ".:clan:");
		WorldPacketRepository.sendPlayerMessage(player, "To talk, start each line of chat with the / symbol.:clan:");
		player.getCommunication().setCurrentClan(owner.getUsername());
		player.setClan(this);
		update();
	}

	/**
	 * Leaves the clan chat.
	 * @param player The player to leave.
	 * @param remove If the player should be removed from the list.
	 */
	public void leave(PlayerSession player, boolean remove) {
		if (remove) {
			players.remove(player);
			update();
			if (players.size() < 1) {
				banned.clear();
			}
		}
		WorldPacketRepository.sendPlayerMessage(player, "You have left the channel.:clan:");
		player.setClan(null);
		player.getCommunication().setCurrentClan(null);
		if (player.isActive()) {
			WorldPacketRepository.sendLeaveClan(player);
		}
	}

	/**
	 * Sends a message to all players in the chat.
	 * @param player The player sending the message.
	 * @param message The message to send.
	 */
	public void message(PlayerSession player, String message) {
		if (player != owner && player.getRights() != 2) {
			ClanRank rank = getRank(player);
			if (rank.ordinal() < getMessageRequirement().ordinal()) {
				WorldPacketRepository.sendPlayerMessage(player, "You do not have a high enough rank to talk in this clan channel.:clan:");
				return;
			}
		}
		for (Iterator<PlayerSession> it = players.iterator(); it.hasNext();) {
			PlayerSession p = it.next();
			if (p != null) {
				WorldPacketRepository.sendMessage(p, player, 2, message);
			}
		}
	}

	/**
	 * Updates the clan chat.
	 */
	public void update() {
		for (GameServer server : WorldDatabase.getWorlds()) {
			if (server != null && server.isActive()) {
				WorldPacketRepository.sendClanInformation(server, this);
			}
		}
	}

	/**
	 * Kicks a player from the clan chat.
	 * @param player The player.
	 */
	public void kick(PlayerSession player, PlayerSession target) {
		ClanRank rank = getRank(target);
		if (target.getRights() == 2) {
			WorldPacketRepository.sendPlayerMessage(player, "You can't kick an administrator.:clan:");
			return;
		}
		System.out.println(rank + ", " + player.getUsername());
		if (player.getRights() < 1/*!= 2 && rank.ordinal() < getKickRequirement().ordinal()*/) {
			WorldPacketRepository.sendPlayerMessage(player, "You do not have a high enough rank to kick in this clan channel.:clan:");
			return;
		}
		if (target == owner) {
			WorldPacketRepository.sendPlayerMessage(player, "You can't kick the owner of this clan channel.:clan:");
			return;
		}
		if (target == player) {
			WorldPacketRepository.sendPlayerMessage(player, "You can't kick yourself.:clan:");
			return;
		}
		for (PlayerSession p : players) {
			WorldPacketRepository.sendMessage(p, player, 2, "[Attempting to kick/ban " + StringUtils.formatDisplayName(target.getUsername()) + " from this Clan Chat.]");
		}
		leave(target, true);
		banned.put(target.getUsername(), System.currentTimeMillis() + (3_600_000));
		WorldPacketRepository.sendPlayerMessage(target, "You have been kicked from the channel.:clan:");
	}

	/**
	 * Gets the rank for the given player.
	 * @param player The player.
	 * @return The rank.
	 */
	public ClanRank getRank(PlayerSession player) {
		ClanRank rank = owner.getCommunication().getContacts().get(player.getUsername());
		if (player.getRights() == 2 && player != owner) {
			return ClanRank.KELDAGRIM_MOD;
		}
		if (rank == null) {
			if (player == owner) {
				return ClanRank.OWNER;
			}
			return ClanRank.NONE;
		}
		return rank;
	}

	/**
	 * Checks if the player is banned.
	 * @param username The username of the player.
	 * @return {@code True} if so.
	 */
	private boolean isBanned(String username) {
		Long time = banned.get(username);
		if (time == null) {
			return false;
		}
		if (time < System.currentTimeMillis()) {
			banned.remove(username);
			return false;
		}
		return true;
	}

	/**
	 * Clears the clan chat.
	 * @param disable If the clan chat is getting disabled.
	 */
	public void clean(boolean disable) {
		for (Iterator<PlayerSession> it = players.iterator(); it.hasNext();) {
			PlayerSession player = it.next();
			boolean remove = disable;
			if (!remove) {
				remove = getRank(player).ordinal() < getJoinRequirement().getValue();
			}
			if (remove) {
				leave(player, false);
				it.remove();
			}
		}
		if (players.isEmpty()) {
			banned.clear();
		}
		update();
	}

	/**
	 * Renames the clan chat.
	 * @param name The new clan name.
	 */
	public void rename(String name) {
		owner.getCommunication().setClanName(name);
		update();
	}

	/**
	 * Gets the clan name.
	 * @return The clan name.
	 */
	public String getName() {
		return owner.getCommunication().getClanName();
	}

	/**
	 * Gets the owner value.
	 * @return The owner.
	 */
	public PlayerSession getOwner() {
		return owner;
	}

	/**
	 * Sets the owner value.
	 * @param owner The owner to set.
	 */
	public void setOwner(PlayerSession owner) {
		this.owner = owner;
	}

	/**
	 * Gets the players value.
	 * @return The players.
	 */
	public List<PlayerSession> getPlayers() {
		return players;
	}

	/**
	 * Gets the clans value.
	 * @return The clans.
	 */
	public static Map<String, ClanRepository> getClans() {
		return CLANS;
	}

	/**
	 * Gets the joinRequirement value.
	 * @return The joinRequirement.
	 */
	public ClanRank getJoinRequirement() {
		return owner.getCommunication().getJoinRequirement();
	}

	/**
	 * Gets the messageRequirement value.
	 * @return The messageRequirement.
	 */
	public ClanRank getMessageRequirement() {
		return owner.getCommunication().getMessageRequirement();
	}

	/**
	 * Gets the kickRequirement value.
	 * @return The kickRequirement.
	 */
	public ClanRank getKickRequirement() {
		return owner.getCommunication().getKickRequirement();
	}

	/**
	 * Gets the lootRequirement value.
	 * @return The lootRequirement.
	 */
	public ClanRank getLootRequirement() {
		return owner.getCommunication().getLootRequirement();
	}

	/**
	 * Gets the banned value.
	 * @return The banned.
	 */
	public Map<String, Long> getBanned() {
		return banned;
	}
}