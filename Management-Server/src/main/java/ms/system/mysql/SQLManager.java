package ms.system.mysql;

import ms.ServerConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the sql connections.
 * @author Vexia
 * 
 */
public final class SQLManager {

	/**
	 * If the sql manager is locally hosted.
	 */
	public static final boolean LOCAL = true;

    /**
     * The database URL.
     */
    public static final String DATABASE_URL = (LOCAL ? "127.0.0.1" : "keldagrim.org") + ":3306/" + (SQLManager.LOCAL ? "global" : ServerConstants.DATABASE_NAMES[1]);

    /**
     * The username of the user.
     */
    private static final String USERNAME = (LOCAL ? "root" : "keldagr1_user");

    /**
     * The password of the user.
     */
    private static final String PASSWORD = (LOCAL ? "" : "2jf4wkz$");
	
	
	/**
	 * IF the sql manager is initialized.
	 */
	private static boolean initialized;
	
	/**
	 * Constructs a new {@code SQLManager} {@code Object}
	 */
	public SQLManager() {
		/**
		 * empty.
		 */
	}
	
	/**
	 * Initializes the sql manager.
	 */
	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		initialized = true;
		WorldListSQLHandler.clearWorldList();
	}
	
	/**
	 * Gets a connection from the pool.
	 * @return The connection.
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + DATABASE_URL,  USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Error: Mysql error message=" + e.getMessage() + ".");
		}
		return null;
	}

	/**
	 * Releases the connection so it's available for usage.
	 * @param connection The connection.
	 */
	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the initialized.
	 * @return the initialized
	 */
	public static boolean isInitialized() {
		return initialized;
	}

	/**
	 * Sets the bainitialized.
	 * @param initialized the initialized to set.
	 */
	public static void setInitialized(boolean initialized) {
		SQLManager.initialized = initialized;
	}

}
