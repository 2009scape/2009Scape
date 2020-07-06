package plugin.skill.crafting;

/**
 * Represents a silver product.
 * @author 'Vexia
 */
public enum SilverProduct {
	UNBLESSED(16, 1599, 1714, 16, 50, 1716),
	UNHOLY(23, 1594, 1720, 17, 50, 1724),
	SICKLE(30, 2976, 2961, 18, 50, -1),
	TIARA(44, 5523, 5525, 23, 52.5, -1),
	DEMONIC_SIGIL(59, 6747, 6748, 20, 50, -1),
	SILVTHRIL_CHAIN(73, 13153, 13154, 1, 100, -1),
	LIGHTNING_ROD(37, 4200, 4201, 20, 50, -1),
	SILVTHRIL_ROD(52, 7649, 7637, 99, 1, -1),
	SILVER_BOLTS(66, 9434, 9382, 21, 50, -1);
	/**
	 * The button id.
	 */
	private final int button;

	/**
	 * The product item.
	 */
	private final int product;

	/**
	 * The item needed.
	 */
	private final int needed;

	/**
	 * The level needed.
	 */
	private final int level;

	/**
	 * The exp gained.
	 */
	private final double exp;

	/**
	 * The strung id.
	 */
	private final int strung;

	/**
	 * Constructs a new {@code SilverDefinitions.java} {@Code Object}
	 * @param button
	 * @param product
	 * @param level
	 * @param exp
	 */
	SilverProduct(int button, int needed, int product, int level, double exp, int strung) {
		this.button = button;
		this.needed = needed;
		this.product = product;
		this.level = level;
		this.exp = exp;
		this.strung = strung;
	}

	/**
	 * returns the value.
	 * @param id
	 * @return
	 */
	public static SilverProduct forId(int id) {
		for (SilverProduct silver : SilverProduct.values()) {
			if (silver.button == id) {
				return silver;
			}
		}
		return null;
	}

	/**
	 * Gets the button.
	 * @return The button.
	 */
	public int getButton() {
		return button;
	}

	/**
	 * Gets the product.
	 * @return The product.
	 */
	public int getProduct() {
		return product;
	}

	/**
	 * Gets the needed.
	 * @return The needed.
	 */
	public int getNeeded() {
		return needed;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the exp.
	 * @return The exp.
	 */
	public double getExp() {
		return exp;
	}

	/**
	 * Gets the strung.
	 * @return The strung.
	 */
	public int getStrung() {
		return strung;
	}

	/**
	 * returns the value.
	 * @param id
	 * @return
	 */
	public static SilverProduct forProductId(int id) {
		for (SilverProduct silver : SilverProduct.values()) {
			if (silver.getProduct() == id) {
				return silver;
			}
		}
		return null;
	}
}
