package ms.world.info;

/**
 * The country flags.
 * @author Emperor
 *
 */
public enum CountryFlag {

	/**
	 * Belgium
	 */
	BELGIUM(22),
	
	/**
	 * Canada.
	 */
	CANADA(38),
	
	/**
	 * The united kingdom.
	 */
	UNITED_KINGDOM(77),
	
	/**
	 * Netherlands
	 */
	NETHERLANDS(161),
	
	/**
	 * Sweden
	 */
	SWEDEN(191),
	
	/**
	 * Finland
	 */
	FINLAND(69),
	
	/**
	 * Australia.
	 */
	AUSTRALIA(16),
	
	/**
	 * America (JewSA)
	 */
	JEWSA(225),
	
	/**
	 * Norway
	 */
	NORWAY(162),
	
	/**
	 * Ireland
	 */
	IRELAND(101),
	
	/**
	 * DENMARK
	 */
	DENMARK(58),
	
	/**
	 * Brazil
	 */
	BRAZIL(31),
	
	/**
	 * Mexico
	 */
	MEXICO(152),
	
	;
	
	/**
	 * The flag id.
	 */
	private final int id;

	/**
	 * Constructs a new {@code CountryFlag} {@code Object}.
	 * @param id The flag id.
	 */
	private CountryFlag(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}