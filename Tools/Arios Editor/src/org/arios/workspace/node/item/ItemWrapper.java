package org.arios.workspace.node.item;


/**
 * An item wrapper.
 * @author Vexia
 *
 */
public class ItemWrapper {

	/**
	 * The id.
	 */
	private int id;
	
	/**
	 * The amount.
	 */
	private int amount;

	/**
	 * Constructs a new {@Code ItemWrapper} {@Code Object}
	 * @param id the id.
	 * @param amount the amount.
	 */
	public ItemWrapper(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	/**
	 * Gets the id.
	 * @return the id.
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "(" + id + ", " + amount + ")";
	}

	/**
	 * Sets the id.
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the amount.
	 * @return the amount.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
