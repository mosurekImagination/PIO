import java.sql.*;

/**
 * Klasa ³¹cz¹ca aplikacjê z baz¹ danych "hurtownia" znajduj¹c¹ siê na serwerze typu MariaDB.
 */
public class Context { 

	private Connection conn = null;

	public Connection getConnection() {
		return conn;
	}

	/**
	 * Metoda otwieraj¹ca po³¹czenie z baz¹ danych.
	 */
	public void openDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/hurtownia", "root", null);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metoda zamykaj¹ca po³¹czenie z baz¹ danych.
	 */
	public void closeDB() {
		try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
	}

}
