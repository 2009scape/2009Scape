package ms.system.mysql;

/**
 * Represents a column used in an SQL table.
 * @author Emperor
 *
 */
public final class SQLColumn {

	/**
	 * The name.
	 */
	private final String name;
	
	/**
	 * The data type.
	 */
	private final Class<?> type;
	
	/**
	 * If this column should never be updated in the database.
	 */
	private final boolean neverUpdate;
	
	/**
	 * The value.
	 */
	private Object value;
	
	/**
	 * If the column value changed.
	 */
	private boolean changed;
	
	/**
	 * Constructs a new {@code SQLColumn} {@code Object}.
	 * @param name The column name.
	 * @param type The data type.
	 */
	public SQLColumn(String name, Class<?> type) {
		this(name, type, false);
	}
	
	/**
	 * Constructs a new {@code SQLColumn} {@code Object}.
	 * @param name The column name.
	 * @param type The data type.
	 * @param neverUpdate If this column should never be updated in the database.
	 */
	public SQLColumn(String name, Class<?> type, boolean neverUpdate) {
		this.name = name;
		this.type = type;
		this.neverUpdate = neverUpdate;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates the value.
	 * @param value The value.
	 */
	public void updateValue(Object value) {
		this.changed = value != this.value;
		this.value = value;
	}
	
	/**
	 * Gets the value.
	 * @return The value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * @param value The value to set.
	 */
	public void setValue(Object value) {
		this.value = value;
		this.changed = false;
	}

	/**
	 * Gets the changed.
	 * @return The changed.
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Sets the changed.
	 * @param changed The changed to set.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * Gets the neverUpdate.
	 * @return The neverUpdate.
	 */
	public boolean isNeverUpdate() {
		return neverUpdate;
	}
}