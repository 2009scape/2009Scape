package ms.world;

import java.nio.ByteBuffer;

import ms.ServerConstants;
import ms.net.IoSession;
import ms.net.packet.IoBuffer;
import ms.world.info.CountryFlag;
import ms.world.info.WorldInfo;

/**
 * Holds all the world servers.
 * @author Emperor
 *
 */
public class WorldDatabase {

	/**
	 * The game servers.
	 */
	public static final GameServer[] DATABASE = new GameServer[ServerConstants.WORLD_LIMIT];
	
	/**
	 * The update time stamp.
	 */
	private static long updateStamp = System.currentTimeMillis();
	
	/**
	 * Used to prevent {@code WorldDatabase} from getting instantiated.
	 */
	private WorldDatabase() {
		/*
		 * empty.
		 */
	}

	/**
	 * Gets the packet to update the world list in the lobby.
	 * @param player The player.
	 * @param worldConfiguration If the configuration should be added.
	 * @param worldStatus If the status should be added.
	 * @return The {@code OutgoingPacket} to write.
	 */
	public static void sendUpdate(IoSession session, int updateStamp) {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put((byte) 0);
		buf.putShort((short) 0);
		buf.put((byte) 1);
		IoBuffer buffer = new IoBuffer();
		if (updateStamp != (int) WorldDatabase.updateStamp) {
			buf.put((byte) 1); // Indicates an update occured.
			putWorldListinfo(buffer);
		} else {
			buf.put((byte) 0);
		}
		putPlayerInfo(buffer);
		if (buffer.toByteBuffer().position() > 0) {
			buf.put((ByteBuffer) buffer.toByteBuffer().flip());
		}
		buf.putShort(1, (short) (buf.position() - 3));
		session.queue((ByteBuffer) buf.flip());
	}

	/**
	 * Adds the world status on the packet.
	 * @param buffer The current packet.
	 */
	private static void putPlayerInfo(IoBuffer buffer) {
		for (GameServer server : DATABASE) {
			if (server != null) {
				WorldInfo w = server.getInfo();
				buffer.putSmart(w.getWorldId());
				buffer.putShort(server.isActive() ? server.getPlayerAmount() : -1);
			}
		}
	}

	/**
	 * Sets the countries for each world.
	 * @param buffer The current packet.
	 */
	private static void putCountryInfo(IoBuffer buffer) {
		for (CountryFlag country : CountryFlag.values()) {
			buffer.putSmart(country.getId());
			buffer.putJagString(capitalize(country.name().toLowerCase()));
		}
	}
	/*
     * Stole this from a library so we don't need the dependency
	 */
	private static String capitalize(String name) {
		if (name != null && name.length() != 0) {
			char[] chars = name.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			return new String(chars);
		} else {
			return name;
		}
	}

	/**
	 * Adds the world configuration on the packet.
	 * @param buffer The current packet.
	 */
	private static void putWorldListinfo(IoBuffer buffer) {
		buffer.putSmart(CountryFlag.values().length);
		putCountryInfo(buffer);
		buffer.putSmart(0);
		buffer.putSmart(DATABASE.length);
		buffer.putSmart(getRegisteredAmount());
		for (GameServer server : DATABASE) {
			if (server != null) {
				WorldInfo w = server.getInfo();
				buffer.putSmart(w.getWorldId());
				buffer.put(w.getCountry().ordinal());
				buffer.putInt(w.getSettings());
				buffer.putJagString(w.getActivity());
				buffer.putJagString(w.getAddress());
			}
		}
		buffer.putInt((int) updateStamp);
	}
	
	/**
	 * Gets the amount of worlds registered.
	 * @return The amount of worlds registered.
	 */
	public static int getRegisteredAmount() {
		int count = 0;
		for (GameServer server : DATABASE) {
			if (server != null) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Registers a game server.
	 * @param info The game world info.
	 */
	public static GameServer register(WorldInfo info) {
		GameServer server = DATABASE[info.getWorldId()];
		if (server != null && server.getSession().isActive() && !server.getSession().getAddress().equals(info.getAddress())) {
			throw new IllegalStateException("World " + info.getWorldId() + " is already registered!");
		}
		flagUpdate();
		System.out.println("Registered world - [id=" + info.getWorldId() + ", ip=" + info.getAddress() + ", country=" + info.getCountry().name().toLowerCase() + ", revision=" + info.getRevision() + "]!");
		return DATABASE[info.getWorldId()] = new GameServer(info);
	}
	
	/**
	 * Gets the world id of the player.
	 * @param username The username of the player.
	 * @return The world id.
	 */
	public static int getWorldId(String username) {
		return getWorldId(getPlayer(username));
	}
	
	/**
	 * Gets the world id of the player.
	 * @param player The player.
	 * @return The world id.
	 */
	public static int getWorldId(PlayerSession player) {
		if (player == null || !player.isActive()) {
			return 0;
		}
		return player.getWorldId();
	}

	/**
	 * Checks if the game world is active.
	 * @param worldId The world id.
	 * @return {@code True} if so.
	 */
	public static boolean isActive(int worldId) {
		GameServer server = get(worldId);
		return server != null && server.isActive();
	}

	/**
	 * Checks if the player session for the given name is active.
	 * @param username The player's username.
	 * @return {@code True} if so.
	 */
	public static boolean isActivePlayer(String username) {
		PlayerSession session = getPlayer(username);
		return session != null && session.isActive();
	}
	
	/**
	 * Gets the player session for the given name.
	 * @param username The player's username.
	 * @return The player session.
	 */
	public static PlayerSession getPlayer(String username) {
		return getPlayer(username, false);
	}
	
	/**
	 * Gets the player session for the given name.
	 * @param username The player's username.
	 * @param load If we load the users data.
	 * @return The player session.
	 */
	public static PlayerSession getPlayer(String username, boolean load) {
		for (GameServer server : DATABASE) {
			if (server != null && server.isActive()) {
				PlayerSession player = server.getPlayers().get(username);
				if (player != null) {
					return player;
				}
			}
		}
		if (load) {
			return PlayerSession.get(username);
		}
		return null;
	}
	
	/**
	 * Gets the game server for the given world id.
	 * @param worldId The world id.
	 * @return The game server.
	 */
	public static GameServer get(int worldId) {
		return DATABASE[worldId];
	}

	/**
	 * Gets all the game servers.
	 * @return The list of game servers.
	 */
	public static GameServer[] getWorlds() {
		return DATABASE;
	}

	/**
	 * Gets the updateStamp.
	 * @return the updateStamp
	 */
	public static long getUpdateStamp() {
		return updateStamp;
	}

	/**
	 * Sets the updateStamp.
	 * @param updateStamp the updateStamp to set.
	 */
	public static void flagUpdate() {
		WorldDatabase.updateStamp = System.currentTimeMillis();
	}
}