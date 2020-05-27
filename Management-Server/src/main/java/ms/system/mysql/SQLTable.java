package ms.system.mysql;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an SQL table.
 * @author Emperor
 *
 */
public final class SQLTable {

	/**
	 * The columns.
	 */
	private final SQLColumn[] columns;
	
	/**
	 * Constructs a new {@code SQLTable} {@code Object}.
	 * @param columns The columns.
	 */
	public SQLTable(SQLColumn...columns) {
		this.columns = columns;
	}
	
	/**
	 * Gets the column for the given name.
	 * @param name The column name.
	 * @return The column.
	 */
	public SQLColumn getColumn(String name) {
		for (SQLColumn column : columns) {
			if (column.getName().equals(name)) {
				return column;
			}
		}
		return null;
	}

	/**
	 * Gets the changed columns.
	 * @return The columns.
	 */
	public List<SQLColumn> getChanged() {
		List<SQLColumn> updated = new ArrayList<>();
		for (int i = 0; i < columns.length; i++) {
			SQLColumn column = columns[i];
			if (column.isChanged()) {
				updated.add(column);
			}
		}
		return updated;
	}
	
	/**
	 * Gets the columns.
	 * @return The columns.
	 */
	public SQLColumn[] getColumns() {
		return columns;
	}
	
}