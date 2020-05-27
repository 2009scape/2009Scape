package ms.system.communication;

/**
 * Represents the rank of a clan member.
 * @author Emperor
 */
public enum ClanRank {
	NONE(-1, "Anyone"),
	FRIEND(0, "Any friends"),
	RECRUIT(1, "Recruit+"),
	CORPORAL(2, "Corporal+"),
	SERGEANT(3, "Sergeant+"),
	LIEUTENANT(4, "Lieutenant+"),
	CAPTAIN(5, "Captain+"),
	GENERAL(6, "General+"),
	OWNER(7, "Only me"),
	KELDAGRIM_MOD(127, "No-one");
	
	/**
	 * The value of the rank.
	 */
	private final int value;
	
	/**
	 * The requirement info.
	 */
	private final String info;
	
	/**
	 * Constructs a new {@code ClanRank} {@code Object}.
	 * @param value The rank value.
	 * @param info The requirement info.
	 */
	private ClanRank(int value, String info) {
		this.value = value;
		this.info = info;
	}

	/**
	 * Gets the value.
	 * @return The value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the info.
	 * @return The info.
	 */
	public String getInfo() {
		return info;
	}
}