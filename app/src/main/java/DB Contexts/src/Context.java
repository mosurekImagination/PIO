import java.sql.*;

/**
 * Klasa ��cz�ca aplikacj� z baz� danych "hurtownia" znajduj�c� si� na serwerze typu MariaDB.
 */
public class Context { 

	private Connection conn = null;

	public Connection getConnection() {
		return conn;
	}

	/**
	 * Metoda otwieraj�ca po��czenie z baz� danych.
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
	 * Metoda zamykaj�ca po��czenie z baz� danych.
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
