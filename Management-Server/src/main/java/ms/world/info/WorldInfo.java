package ms.world.info;

/**
 * Holds the info of a world server.
 * @author Emperor
 *
 */
public final class WorldInfo {

	/**
	 * The world id.
	 */
	private final int worldId;
	
	/**
	 * The IP-address.
	 */
	private final String address;
	
	/**
	 * The revision of the world.
	 */
	private final int revision;
	
	/**
	 * The country this world is located in.
	 */
	private final CountryFlag country;
	
	/**
	 * The world activity.
	 */
	private final String activity;
	
	/**
	 * If the world is members only.
	 */
	private final boolean members;
	
	/**
	 * If the world is a PvP world.
	 */
	private final boolean pvp;
	
	/**
	 * If the world is a quick chat only world.
	 */
	private final boolean quickChat;

	/**
	 * If the world has lootshare option enabled.
	 */
	private final boolean lootshare;
	
	/**
	 * Constructs a new {@code WorldInfo} {@code Object}.
	 * @param id The world id.
	 * @param address The address.
	 * @param country The country flag.
	 * @param members If the world is members only.
	 */
	public WorldInfo(int id, String address, int revision, CountryFlag country, String activity, boolean members, boolean pvp, boolean quickChat, boolean lootshare) {
		this.worldId = id;
		this.address = address;
		this.revision = revision;
		this.country = country;
		this.activity = activity;
		this.members = members;
		this.pvp = pvp;
		this.quickChat = quickChat;
		this.lootshare = lootshare;
	}

	/**
	 * Gets the settings hash.
	 * @return The settings hash.
	 */
	public int getSettings() {
		int settings = 0;
		if (members) {
			settings |= 0x1;
		}
		if (quickChat) {
			settings |= 0x2;
		}
		if (pvp) {
			settings |= 0x4;
		}
		if (lootshare) {
			settings |= 0x8;
		}
		return settings;
	}

	/**
	 * @return the worldId
	 */
	public int getWorldId() {
		return worldId;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the country
	 */
	public CountryFlag getCountry() {
		return country;
	}

	/**
	 * Gets the members value.
	 * @return The members.
	 */
	public boolean isMembers() {
		return members;
	}

	/**
	 * Gets the activity value.
	 * @return The activity.
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * Gets the pvp value.
	 * @return The pvp.
	 */
	public boolean isPvp() {
		return pvp;
	}

	/**
	 * Gets the quickChat value.
	 * @return The quickChat.
	 */
	public boolean isQuickChat() {
		return quickChat;
	}

	/**
	 * Gets the lootshare value.
	 * @return The lootshare.
	 */
	public boolean isLootshare() {
		return lootshare;
	}

	/**
	 * Gets the revision.
	 * @return the revision.
	 */
	public int getRevision() {
		return revision;
	}
}