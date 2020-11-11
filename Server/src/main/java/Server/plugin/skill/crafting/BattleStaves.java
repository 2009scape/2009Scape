package plugin.skill.crafting;

/**
 * Represents a battlestave.
 * @author 'Vexia
 */
public enum BattleStaves {
	WATER(571, 1395, 54, 100, 215, 149),
	EARTH(575, 1399, 58, 112.5, 29415, 151),
	FIRE(569, 1393, 62, 125, 2153, 152),
	AIR(573, 1397, 66, 137.5, 2152, 150);

	/**
	 * The obelisk used.
	 */
	private final int obelisk;

	/**
	 * The product made.
	 */
	private final int product;

	/**
	 * The level needed to make the staff.
	 */
	private final int level;

	/**
	 * The exp gained.
	 */
	private final double exp;

	/**
	 * The object id.
	 */
	private final int objectId;

	/**
	 * The graphic id.
	 */
	private final int graphicId;

	/**
	 * Constructs a new {@code BattleStaves} {@Code Object}
	 * @param obelisk the obelisk.
	 * @param product the product.
	 * @param level the lever.
	 * @param exp the experience.
	 * @param objectId the id.
	 * @param graphicId the id.
	 */
	BattleStaves(int obelisk, int product, int level, double exp, final int objectId, final int graphicId) {
		this.obelisk = obelisk;
		this.product = product;
		this.level = level;
		this.exp = exp;
		this.objectId = objectId;
		this.graphicId = graphicId;
	}

	/**
	 * Gets a battle stave by the id.
	 * @param id the id.
	 * @return the object.
	 */
	public static BattleStaves forId(int id) {
		for (BattleStaves staff : BattleStaves.values()) {
			if (staff.getObelisk() == id) {
				return staff;
			}
		}
		return null;
	}

	/**
	 * Gets the obelisk.
	 * @return The obelisk.
	 */
	public int getObelisk() {
		return obelisk;
	}

	/**
	 * Gets the product.
	 * @return The product.
	 */
	public int getProduct() {
		return product;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the objectId.
	 * @return The objectId.
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * Gets the graphicId.
	 * @return The graphicId.
	 */
	public int getGraphicId() {
		return graphicId;
	}

	/**
	 * Gets the exp.
	 * @return The exp.
	 */
	public double getExp() {
		return exp;
	}
}
