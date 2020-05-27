package ms.system.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles an SQL entry.
 * @author Emperor
 * @param <T> The entry type.
 */
public abstract class SQLEntryHandler<T> {

	/**
	 * The connection.
	 */
	protected Connection connection;
	
	/**
	 * The entry.
	 */
	protected T entry;
	
	/**
	 * The result set.
	 */
	protected ResultSet result;

	/**
	 * The table name.
	 */
	protected String table;

	/**
	 * The column name.
	 */
	private String column;

	/**
	 * The value to use.
	 */
	protected String value;
	
	/**
	 * Constructs a new {@code SQLEntryHandler} {@code Object}.
	 * @param entry The entry.
	 * @param table The table name.
	 * @param column column name.
	 * @param value The column value.
	 */
	public SQLEntryHandler(T entry, String table, String column, String value) {
		this.entry = entry;
		this.table = table;
		this.column = column;
		this.value = value;
	}
	
	/**
	 * Reads the SQL entry.
	 * @param entry The entry.
	 */
	public static void read(SQLEntryHandler<?> entry) {
		entry.connection = entry.getConnection();
		if (entry.connection == null) {
			//System.err.println("Could not read SQL data: connection is null!");
			return;
		}
		try {
			entry.read();
			if (entry.result == null || !entry.result.next()) {
				entry.create();
			} else {
				entry.parse();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		SQLManager.close(entry.connection);
		entry.connection = null;
	}

	/**
	 * Writes the entry data on the SQL database.
	 * @param entry The entry.
	 */
	public static void write(SQLEntryHandler<?> entry) {
		write(entry, entry.getConnection(), true);
	}

	/**
	 * Reads the SQL entry.
	 * @param entry The entry.
	 */
	public static void write(SQLEntryHandler<?> entry, Connection connection, boolean commit) {
		if (connection == null) {
			//System.err.println("Could not write SQL data: connection is null!");
			return;
		}
		entry.connection = connection;
		try {
			if (!commit) {
				entry.connection.setAutoCommit(false);
			}
			entry.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (commit) {
			SQLManager.close(entry.connection);
		}
		entry.connection = null;
	}
	
	/**
	 * Reads the table from the SQL database.
	 * @throws SQLException When an SQL exception occurs.
	 */
	public void read() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT " + getReadSelection() + " FROM " + table + " WHERE " + column + " = ?");
		statement.setString(1, value);
		result = statement.executeQuery();
	}
	
	/**
	 * Gets the result.
	 * @return the result.
	 */
	public ResultSet getResult() {
		return result;
	}
	
	/**
	 * Gets the selection.
	 * @return The selection.
	 */
	public String getReadSelection() {
		return "*";
	}
	
	/**
	 * Gets the writing statement.
	 * @param create If we are creating a new row.
	 * @param columns The columns to update.
	 */
	public PreparedStatement getWritingStatement(boolean create, String...columns) {
		PreparedStatement statement = null;
		try {
			StringBuilder sb = new StringBuilder();
			if (create) {
				sb.append("INSERT INTO ").append(table).append(" (").append(column);
				for (String name : columns) {
					sb.append(",").append(name);
				}
				sb.append(") VALUES (?");
				for (int i = 0; i < columns.length; i++) {
					sb.append(",?");
				}
				sb.append(")");
			} else {
				sb.append("UPDATE ").append(table).append(" SET ");
				for (int i = 0; i < columns.length; i++) {
					sb.append(columns[i]).append("=?");
					if (i < columns.length - 1) {
						sb.append(",");
					}
				}
				sb.append(" WHERE ").append(column).append("=?");
			}
			statement = connection.prepareStatement(sb.toString());
			statement.setString(create ? 1 : columns.length + 1, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	/**
	 * Parses the entry.
	 * @throws SQLException When an SQL exception occurs.
	 */
	public abstract void parse() throws SQLException;

	/**
	 * Creates a new row in the database.
	 * @throws SQLException When an SQL exception occurs.
	 */
	public abstract void create() throws SQLException;
	
	/**
	 * Saves the entry.
	 * @throws SQLException When an SQL exception occurs.
	 */
	public abstract void save() throws SQLException;
	
	/**
	 * Gets the connection.
	 * @return The connection.
	 */
	public abstract Connection getConnection();
	
}