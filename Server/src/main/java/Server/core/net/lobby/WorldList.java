package core.net.lobby;

import core.game.world.GameWorld;
import core.net.IoSession;
import core.net.packet.IoBuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the world list.
 * @author Emperor
 *
 */
public final class WorldList {

	/**
	 * The value for Australia.
	 */
	public static final int COUNTRY_AUSTRALIA = 16;

	/**
	 * The value for Belgium.
	 */
	public static final int COUNTRY_BELGIUM = 22;

	/**
	 * The value for Brazil.
	 */
	public static final int COUNTRY_BRAZIL = 31;

	/**
	 * The value for Canada.
	 */
	public static final int COUNTRY_CANADA = 38;

	/**
	 * The value for Denmark.
	 */
	public static final int COUNTRY_DENMARK = 58;

	/**
	 * The value for Finland.
	 */
	public static final int COUNTRY_FINLAND = 69;

	/**
	 * The value for Ireland.
	 */
	public static final int COUNTRY_IRELAND = 101;

	/**
	 * The value for Mexico.
	 */
	public static final int COUNTRY_MEXICO = 152;

	/**
	 * The value for the Netherlands.
	 */
	public static final int COUNTRY_NETHERLANDS = 161;

	/**
	 * The value for Norway.
	 */
	public static final int COUNTRY_NORWAY = 162;

	/**
	 * The value for Sweden.
	 */
	public static final int COUNTRY_SWEDEN = 191;

	/**
	 * The value for the UK.
	 */
	public static final int COUNTRY_UK = 77;

	/**
	 * The value for USA.
	 */
	public static final int COUNTRY_USA = 225;

	/**
	 * If the world is free to play.
	 */
	public static final int FLAG_NON_MEMBERS = 0;

	/**
	 * If the world is a members world.
	 */
	public static final int FLAG_MEMBERS = 1;

	/**
	 * If the world is a quick chat world
	 */
	public static final int FLAG_QUICK_CHAT = 2;

	/**
	 * If the world is a PvP-world.
	 */
	public static final int FLAG_PVP = 4;

	/**
	 * If the world is a lootshare world.
	 */
	public static final int FLAG_LOOTSHARE = 8;

	/**
	 * A list holding all the currently loaded worlds.
	 */
	private static final List<WorldDefinition> WORLD_LIST = new ArrayList<WorldDefinition>();

	/**
	 * The last update time stamp (in server ticks).
	 */
	private static int updateStamp = 0;

	/**
	 * Populates the world list.
	 */
	static {
		addWorld(new WorldDefinition(1, 0, FLAG_MEMBERS | FLAG_LOOTSHARE, "2009Scape Classic", "127.0.0.1", "Anywhere, USA", COUNTRY_USA));
	}

	/**
	 * Adds a world to the world list.
	 * @param def The world definitions.
	 */
	public static void addWorld(WorldDefinition def) {
		WORLD_LIST.add(def);
		flagUpdate();
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
		if (updateStamp != WorldList.updateStamp) {
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
	 * Adds the world configuration on the packet.
	 * @param buffer The current packet.
	 */
	private static void putWorldListinfo(IoBuffer buffer) {
		buffer.putSmart(WORLD_LIST.size());
		putCountryInfo(buffer);
		buffer.putSmart(0);
		buffer.putSmart(WORLD_LIST.size());
		buffer.putSmart(WORLD_LIST.size());
		for (WorldDefinition w : WORLD_LIST) {
			buffer.putSmart(w.getWorldId());
			buffer.put(w.getLocation());
			buffer.putInt(w.getFlag());
			buffer.putJagString(w.getActivity());
			buffer.putJagString(w.getIp());
		}
		buffer.putInt(updateStamp);
	}

	/**
	 * Adds the world status on the packet.
	 * @param buffer The current packet.
	 */
	private static void putPlayerInfo(IoBuffer buffer) {
		for (WorldDefinition w : WORLD_LIST) {
			buffer.putSmart(w.getWorldId());
			buffer.putShort(w.getPlayerCount());
		}
	}

	/**
	 * Sets the countries for each world.
	 * @param buffer The current packet.
	 */
	private static void putCountryInfo(IoBuffer buffer) {
		for (WorldDefinition w : WORLD_LIST) {
			buffer.putSmart(w.getCountry());
			buffer.putJagString(w.getRegion());
		}
	}

	/**
	 * Gets the updateStamp.
	 * @return the updateStamp
	 */
	public static int getUpdateStamp() {
		return updateStamp;
	}

	/**
	 * Sets the baupdateStamp.
	 * @param updateStamp the updateStamp to set.
	 */
	public static void flagUpdate() {
		WorldList.updateStamp = GameWorld.getTicks();
	}
}